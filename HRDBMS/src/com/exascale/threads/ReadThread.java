package com.exascale.threads;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import com.exascale.filesystem.Block;
import com.exascale.filesystem.Page;
import com.exascale.managers.FileManager;
import com.exascale.managers.HRDBMSWorker;
import com.exascale.tables.Schema;
import com.exascale.tables.Transaction;

public class ReadThread extends HRDBMSThread
{
	private final Page p;
	private final Block b;
	private final ByteBuffer bb;
	private boolean ok = true;
	private Schema schema;
	private ConcurrentHashMap<Integer, Schema> schemaMap;
	private Transaction tx;
	private ArrayList<Integer> fetchPos;

	public ReadThread(Page p, Block b, ByteBuffer bb)
	{
		this.description = "Read thread for buffer Manager";
		this.setWait(false);
		this.p = p;
		this.b = b;
		this.bb = bb;
	}
	
	public ReadThread(Page p, Block b, ByteBuffer bb, Schema schema, ConcurrentHashMap<Integer, Schema> schemaMap, Transaction tx, ArrayList<Integer> fetchPos)
	{
		this.description = "Read thread for buffer Manager";
		this.setWait(false);
		this.p = p;
		this.b = b;
		this.bb = bb;
		this.schema = schema;
		this.schemaMap = schemaMap;
		this.tx = tx;
		this.fetchPos = fetchPos;
	}

	public boolean getOK()
	{
		return ok;
	}

	@Override
	public void run()
	{
		try
		{
			bb.clear();
			bb.position(0);
	
			final FileChannel fc = FileManager.getFile(b.fileName());
			// if (b.number() * bb.capacity() >= fc.size())
			// {
			// HRDBMSWorker.logger.debug("Tried to read from " + b.fileName() +
			// " at block = " + b.number() +
			// " but it was past the range of the file");
			// ok = false;
			// }

			fc.read(bb, ((long)b.number()) * bb.capacity());
			p.setReady();
			
			if (schema != null)
			{
				synchronized (schema)
				{
					tx.read2(p.block(), schema, p);
				}

				schemaMap.put(p.block().number(), schema);
				schema.prepRowIter(fetchPos);
			}
			this.terminate();
		}
		catch (final Exception e)
		{
			HRDBMSWorker.logger.warn("I/O error occurred trying to read file: " + b.fileName() + ":" + b.number(), e);
			ok = false;
			this.terminate();
			return;
		}
		return;
	}
}