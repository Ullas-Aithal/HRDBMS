package com.exascale.optimizer;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import com.exascale.filesystem.RID;
import com.exascale.managers.HRDBMSWorker;
import com.exascale.misc.DataEndMarker;
import com.exascale.misc.MultiHashMap;
import com.exascale.tables.Plan;
import com.exascale.tables.Transaction;
import com.exascale.threads.HRDBMSThread;

public final class UpdateOperator implements Operator, Serializable
{
	private Operator child;
	private final MetaData meta;
	private HashMap<String, String> cols2Types;
	private HashMap<String, Integer> cols2Pos;
	private TreeMap<Integer, String> pos2Col;
	private Operator parent;
	private int node;
	private Plan plan;
	private String schema;
	private String table;
	private AtomicInteger num = new AtomicInteger(0);
	private boolean done = false;
	private MultiHashMap map = new MultiHashMap<Integer, UpdateSetAndRAIK>();
	private Transaction tx;
	private ArrayList<Column> cols;
	private ArrayList<String> buildList;
	
	public void setPlan(Plan plan)
	{
		this.plan = plan;
	}

	public UpdateOperator(String schema, String table, ArrayList<Column> cols, ArrayList<String> buildList, MetaData meta)
	{
		this.schema = schema;
		this.table = table;
		this.meta = meta;
		this.cols = cols;
		this.buildList = buildList;
	}
	
	public void setTransaction(Transaction tx)
	{
		this.tx = tx;
	}

	@Override
	public void add(Operator op) throws Exception
	{
		if (child == null)
		{
			child = op;
			child.registerParent(this);
		}
		else
		{
			throw new Exception("UpdateOperator only supports 1 child.");
		}
	}

	@Override
	public ArrayList<Operator> children()
	{
		final ArrayList<Operator> retval = new ArrayList<Operator>(1);
		retval.add(child);
		return retval;
	}

	@Override
	public UpdateOperator clone()
	{
		final UpdateOperator retval = new UpdateOperator(schema, table, cols, buildList, meta);
		retval.node = node;
		return retval;
	}

	@Override
	public void close() throws Exception
	{
		child.close();
	}

	@Override
	public int getChildPos()
	{
		return 0;
	}

	@Override
	public HashMap<String, Integer> getCols2Pos()
	{
		return cols2Pos;
	}

	@Override
	public HashMap<String, String> getCols2Types()
	{
		return cols2Types;
	}

	@Override
	public MetaData getMeta()
	{
		return meta;
	}

	@Override
	public int getNode()
	{
		return node;
	}

	@Override
	public TreeMap<Integer, String> getPos2Col()
	{
		return pos2Col;
	}

	@Override
	public ArrayList<String> getReferences()
	{
		final ArrayList<String> retval = new ArrayList<String>();
		return retval;
	}

	@Override
	// @?Parallel
	public Object next(Operator op) throws Exception
	{
		while (!done)
		{
			Thread.sleep(1);
		}
		
		if (num.get() == Integer.MIN_VALUE)
		{
			throw new Exception("An error occured during an update operation");
		}
		
		if (num.get() < 0)
		{
			return new DataEndMarker();
		}
		
		int retval = num.get();
		num.set(-1);
		return retval;
	}

	@Override
	public void nextAll(Operator op) throws Exception
	{
		child.nextAll(op);
		Object o = next(op);
		while (!(o instanceof DataEndMarker) && !(o instanceof Exception))
		{
			o = next(op);
		}
	}

	@Override
	public Operator parent()
	{
		return parent;
	}

	@Override
	public void registerParent(Operator op) throws Exception
	{
		throw new Exception("UpdateOperator does not support parents");
	}

	@Override
	public void removeChild(Operator op)
	{
		if (op == child)
		{
			child = null;
			op.removeParent(this);
		}
	}

	@Override
	public void removeParent(Operator op)
	{
		parent = null;
	}

	@Override
	public void reset() throws Exception
	{
		throw new Exception("UpdateOperator does not support reset()");
	}

	@Override
	public void setChildPos(int pos)
	{
	}

	@Override
	public void setNode(int node)
	{
		this.node = node;
	}

	@Override
	public void start() throws Exception
	{
		ArrayList<String> indexes = MetaData.getIndexFileNamesForTable(schema, table, tx);
		HashMap<Integer, Integer> pos2Length = new HashMap<Integer, Integer>();
		HashMap<String, Integer> cols2Pos = MetaData.getCols2PosForTable(schema, table, tx);
		TreeMap<Integer, String> pos2Col = MetaData.getPos2ColForTable(schema, table, tx);
		HashMap<String, String> cols2Types = new MetaData().getCols2TypesForTable(schema, table, tx);
		for (Map.Entry entry : new MetaData().getCols2TypesForTable(schema, table, tx).entrySet())
		{
			if (entry.getValue().equals("CHAR") && cols.contains(entry.getKey()))
			{
				int length = MetaData.getLengthForCharCol(schema, table, (String)entry.getKey(), tx);
				pos2Length.put(child.getCols2Pos().get(entry.getKey()), length);
			}
		}
		Object o = child.next(this);
		while (!(o instanceof DataEndMarker))
		{
			ArrayList<Object> row = (ArrayList<Object>)o;
			cast(row, pos2Col, cols2Types);
			for (Map.Entry entry : pos2Length.entrySet())
			{
				if (((String)row.get((Integer)entry.getKey())).length() > (Integer)entry.getValue())
				{
					num.set(Integer.MIN_VALUE);
					return;
				}
			}
			int node = (Integer)row.get(child.getCols2Pos().get("_RID1"));
			int device = (Integer)row.get(child.getCols2Pos().get("_RID2"));
			int block = (Integer)row.get(child.getCols2Pos().get("_RID3"));
			int rec = (Integer)row.get(child.getCols2Pos().get("_RID4"));
			ArrayList<ArrayList<Object>> indexKeys = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> updateSet = new ArrayList<Object>();
			for (String col : buildList)
			{
				updateSet.add(row.get(child.getCols2Pos().get(col)));
			}
			for (String index : indexes)
			{
				ArrayList<Object> keys = new ArrayList<Object>();
				ArrayList<String> cols = MetaData.getColsFromIndexFileName(index, tx);
				for (String col : cols)
				{
					keys.add(row.get(child.getCols2Pos().get(col)));
				}
				
				indexKeys.add(keys);
			}
			
			RIDAndIndexKeys raik = new RIDAndIndexKeys(new RID(node, device, block, rec), indexKeys);
			UpdateSetAndRAIK entry = new UpdateSetAndRAIK(updateSet, raik);
			map.multiPut(node, entry);
			num.incrementAndGet();
			if (map.size() > Integer.parseInt(HRDBMSWorker.getHParms().getProperty("max_neighbor_nodes")))
			{
				flush(indexes);
				if (num.get() == Integer.MIN_VALUE)
				{
					done = true;
					return;
				}
			}
			else if (map.totalSize() > Integer.parseInt(HRDBMSWorker.getHParms().getProperty("max_batch")))
			{
				flush(indexes);
				if (num.get() == Integer.MIN_VALUE)
				{
					done = true;
					return;
				}
			}
			
			o = child.next(this);
		}
		
		if (map.totalSize() > 0)
		{
			flush(indexes);
		}
		
		done = true;
	}
	
	private void flush(ArrayList<String> indexes)
	{
		ArrayList<FlushThread> threads = new ArrayList<FlushThread>();
		for (Object o : map.getKeySet())
		{
			int node = (Integer)o;
			Vector<UpdateSetAndRAIK> list = map.get(node);
			threads.add(new FlushThread(list, indexes));
		}
		
		for (FlushThread thread : threads)
		{
			thread.start();
		}
		
		for (FlushThread thread : threads)
		{
			while (true)
			{
				try
				{
					thread.join();
					break;
				}
				catch(InterruptedException e)
				{}
			}
			
			if (!thread.getOK())
			{
				num.set(Integer.MIN_VALUE);
			}
		}
		
		map.clear();
	}
	
	private class FlushThread extends HRDBMSThread
	{
		private Vector<UpdateSetAndRAIK> list;
		private ArrayList<String> indexes;
		private boolean ok = true;
		
		public FlushThread(Vector<UpdateSetAndRAIK> list, ArrayList<String> indexes)
		{
			this.list = list;
			this.indexes = indexes;
		}
		
		public boolean getOK()
		{
			return ok;
		}
		
		public void run()
		{
			int node = list.get(0).getRAIK().getRID().getNode();
			//send schema, table, tx, indexes, list, and cols2Pos
			Socket sock = null;
			try
			{
				String hostname = new MetaData().getHostNameForNode(node, tx);
				sock = new Socket(hostname, Integer.parseInt(HRDBMSWorker.getHParms().getProperty("port_number")));
				OutputStream out = sock.getOutputStream();
				byte[] outMsg = "UPDATE          ".getBytes("UTF-8");
				outMsg[8] = 0;
				outMsg[9] = 0;
				outMsg[10] = 0;
				outMsg[11] = 0;
				outMsg[12] = 0;
				outMsg[13] = 0;
				outMsg[14] = 0;
				outMsg[15] = 0;
				out.write(outMsg);
				out.write(longToBytes(tx.number()));
				out.write(stringToBytes(schema));
				out.write(stringToBytes(table));
				ObjectOutputStream objOut = new ObjectOutputStream(out);
				objOut.writeObject(indexes);
				objOut.writeObject(list);
				objOut.writeObject(MetaData.getKeys(indexes, tx));
				objOut.writeObject(MetaData.getTypes(indexes, tx));
				objOut.writeObject(MetaData.getOrders(indexes, tx));
				objOut.writeObject(new MetaData().getCols2PosForTable(schema, table, tx));
				objOut.writeObject(cols);
				objOut.flush();
				out.flush();
				objOut.close();
				getConfirmation(sock);
				out.close();
				sock.close();
			}
			catch(Exception e)
			{
				try
				{
					sock.close();
				}
				catch(Exception f)
				{}
				ok = false;
			}
		}
		
		private void getConfirmation(Socket sock) throws Exception
		{
			InputStream in = sock.getInputStream();
			byte[] inMsg = new byte[2];
			
			int count = 0;
			while (count < 2)
			{
				try
				{
					int temp = in.read(inMsg, count, 2 - count);
					if (temp == -1)
					{
						in.close();
						throw new Exception();
					}
					else
					{
						count += temp;
					}
				}
				catch (final Exception e)
				{
					in.close();
					throw new Exception();
				}
			}
			
			String inStr = new String(inMsg, "UTF-8");
			if (!inStr.equals("OK"))
			{
				in.close();
				throw new Exception();
			}
			
			try
			{
				in.close();
			}
			catch(Exception e)
			{}
		}
	}
	
	private static byte[] intToBytes(int val)
	{
		final byte[] buff = new byte[4];
		buff[0] = (byte)(val >> 24);
		buff[1] = (byte)((val & 0x00FF0000) >> 16);
		buff[2] = (byte)((val & 0x0000FF00) >> 8);
		buff[3] = (byte)((val & 0x000000FF));
		return buff;
	}
	
	private byte[] stringToBytes(String string)
	{
		byte[] data = null;
		try
		{
			data = string.getBytes("UTF-8");
		}
		catch(Exception e)
		{}
		byte[] len = intToBytes(data.length);
		byte[] retval = new byte[data.length + len.length];
		System.arraycopy(len, 0, retval, 0, len.length);
		System.arraycopy(data, 0, retval, len.length, data.length);
		return retval;
	}
	
	private static byte[] longToBytes(long val)
	{
		final byte[] buff = new byte[8];
		buff[0] = (byte)(val >> 56);
		buff[1] = (byte)((val & 0x00FF000000000000L) >> 48);
		buff[2] = (byte)((val & 0x0000FF0000000000L) >> 40);
		buff[3] = (byte)((val & 0x000000FF00000000L) >> 32);
		buff[4] = (byte)((val & 0x00000000FF000000L) >> 24);
		buff[5] = (byte)((val & 0x0000000000FF0000L) >> 16);
		buff[6] = (byte)((val & 0x000000000000FF00L) >> 8);
		buff[7] = (byte)((val & 0x00000000000000FFL));
		return buff;
	}

	@Override
	public String toString()
	{
		return "UpdateOperator";
	}
	
	private void cast(ArrayList<Object> row, TreeMap<Integer, String> pos2Col, HashMap<String, String> cols2Types)
	{
		int i = 0;
		while (i < pos2Col.size())
		{
			String type = cols2Types.get(pos2Col.get(i));
			Object o = row.get(i);
			if (type.equals("INT"))
			{
				if (o instanceof Long)
				{
					row.remove(i);
					row.add(i, ((Long)o).intValue());
				}
				else if (o instanceof Double)
				{
					row.remove(i);
					row.add(i, ((Double)o).intValue());
				}
			}
			else if (type.equals("LONG"))
			{
				if (o instanceof Integer)
				{
					row.remove(i);
					row.add(i, ((Integer)o).longValue());
				}
				else if (o instanceof Double)
				{
					row.remove(i);
					row.add(i, ((Double)o).longValue());
				}
			}
			else if (type.equals("FLOAT"))
			{
				if (o instanceof Integer)
				{
					row.remove(i);
					row.add(i, ((Integer)o).doubleValue());
				}
				else if (o instanceof Long)
				{
					row.remove(i);
					row.add(i, ((Long)o).doubleValue());
				}
			}
			
			i++;
		}
	}
}