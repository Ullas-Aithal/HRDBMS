package com.exascale.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import com.exascale.exceptions.LockAbortException;
import com.exascale.filesystem.Block;
import com.exascale.misc.MultiHashMap;
import com.exascale.threads.HRDBMSThread;

public class LockManager extends HRDBMSThread
{
	private static SubLockManager[] managers;
	private static final long TIMEOUT = Long.parseLong(HRDBMSWorker.getHParms().getProperty("deadlock_check_secs")) * 1000;
	public static ConcurrentHashMap<Thread, Thread> killed = new ConcurrentHashMap<Thread, Thread>();
	
	static
	{
		managers = new SubLockManager[Runtime.getRuntime().availableProcessors() * 2];
		int i = 0;
		while (i < managers.length)
		{
			managers[i] = new SubLockManager();
			i++;
		}
	}
	
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(TIMEOUT);
			}
			catch(InterruptedException e)
			{}
		
			ArrayList<Vertice> starting = new ArrayList<Vertice>();
			HashMap<Vertice, Vertice> vertices = new HashMap<Vertice, Vertice>();
			HashMap<Thread, SubLockManager> threads2SLMs = new HashMap<Thread, SubLockManager>();
		
			for (SubLockManager manager : managers)
			{
				manager.lock.lock();
				for (Map.Entry entry : manager.waitList.entrySet())
				{
					for (Thread thread : (ArrayList<Thread>)entry.getValue())
					{
						Vertice vertice = new Vertice(manager.threads2Txs.get(thread), thread);
						threads2SLMs.put(thread, manager);
						if (!vertices.containsKey(vertice))
						{
							vertices.put(vertice, vertice);
							starting.add(vertice);
						}
						else
						{
							vertice = vertices.get(vertice);
							vertice.setThread(thread);
						}
					
						Long targetTX = manager.xBlocksToTXs.get(entry.getKey());
						if (targetTX != null)
						{
							//this xLock is what I am waiting on
							Vertice vertice2 = new Vertice(targetTX);
							if (!vertices.containsKey(vertice2))
							{
								vertices.put(vertice2, vertice2);
							}
							else
							{
								vertice2 = vertices.get(vertice2);
							}
						
							vertice.addEdge(vertice2);
						}
						else
						{
							HashSet<Long> targetTXs = manager.sBlocksToTXs.get(entry.getKey());
							for (Long txnum : targetTXs)
							{
								Vertice vertice2 = new Vertice(targetTX);
								if (!vertices.containsKey(vertice2))
								{
									vertices.put(vertice2, vertice2);
								}
								else
								{
									vertice2 = vertices.get(vertice2);
								}
							
								vertice.addEdge(vertice2);
							}
						}
					}
				}
			}
		
			for (Vertice vertice : starting)
			{
				boolean cycle = checkForCycles(vertice);
				if (cycle)
				{
					vertice.removeAllEdges();
					Thread thread = vertice.getThread();
					killed.put(thread, thread);
					SubLockManager manager = threads2SLMs.get(vertice.getThread());
					Long tx = manager.threads2Txs.remove(thread);
					manager.txs2Threads.remove(tx);
					Block b = manager.inverseWaitList.remove(thread);
					ArrayList<Thread> threads = manager.waitList.get(b);
					threads.remove(thread);
					if (threads.size() == 0)
					{
						manager.waitList.remove(b);
					}
					synchronized(vertice.getThread())
					{
						vertice.getThread().notify();
					}
				}
			}
			
			for (SubLockManager manager : managers)
			{
				manager.lock.unlock();
			}
		}
	}
	
	private boolean checkForCycles(Vertice vertice)
	{
		HashSet<Edge> edges = new HashSet<Edge>();
		for (Vertice dest : vertice.edges)
		{
			Edge edge = new Edge(vertice, dest);
			edges.add(edge);
			if (checkForCycles(dest, vertice, edges))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkForCycles(Vertice start, Vertice orig, HashSet<Edge> edges)
	{
		for (Vertice dest : start.edges)
		{
			if (dest.equals(orig))
			{
				return true;
			}
			
			Edge edge = new Edge(start, dest);
			if (edges.contains(edge))
			{
				return false; //someone has a cycle, but its not my problem
			}
			else
			{
				edges.add(edge);
			}
			
			if (checkForCycles(dest, orig, edges))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private static class Edge
	{
		private Vertice source;
		private Vertice dest;
		
		public Edge(Vertice source, Vertice dest)
		{
			this.source = source;
			this.dest = dest;
		}
		
		public boolean equals(Object r)
		{
			Edge rhs = (Edge)r;
			return source.equals(rhs.source) && dest.equals(rhs.dest);
		}
	}
	
	private static class Vertice
	{
		private long txnum;
		private Thread thread;
		private HashSet<Vertice> edges = new HashSet<Vertice>();
		
		public Vertice(Long txnum, Thread thread)
		{
			this.txnum = txnum;
			this.thread = thread;
		}
		
		public Vertice(Long txnum)
		{
			this.txnum = txnum;
		}
		
		public void setThread(Thread thread)
		{
			this.thread = thread;
		}
		
		public void addEdge(Vertice vertice)
		{
			edges.add(vertice);
		}
		
		public void removeAllEdges()
		{
			edges.clear();
		}
		
		public Thread getThread()
		{
			return thread;
		}
		
		public boolean equals(Object r)
		{
			Vertice rhs = (Vertice)r;
			return txnum == rhs.txnum;
		}
		
		public int hashCode()
		{
			return new Long(txnum).hashCode();
		}
	}
	
	public static void verifyClear()
	{
		int i = 0;
		while (i < managers.length)
		{
			managers[i].verifyClear();
			i++;
		}
	}
	
	public static void release(long txnum)
	{
		int i = 0;
		while (i < managers.length)
		{
			managers[i].release(txnum);
			i++;
		}
	}

	public static void sLock(Block b, long txnum) throws LockAbortException
	{
		int hash = (b.hashCode2() & 0x7FFFFFFF) % managers.length;
		managers[hash].sLock(b, txnum);
	}

	public static void unlockSLock(Block b, long txnum)
	{
		int hash = (b.hashCode2() & 0x7FFFFFFF) % managers.length;
		managers[hash].unlockSLock(b, txnum);
	}

	public static void xLock(Block b, long txnum) throws LockAbortException
	{
		int hash = (b.hashCode2() & 0x7FFFFFFF) % managers.length;
		managers[hash].xLock(b, txnum);
	}
}
