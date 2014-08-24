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
import java.util.concurrent.atomic.AtomicInteger;
import com.exascale.logging.LogRec;
import com.exascale.logging.PrepareLogRec;
import com.exascale.logging.XAAbortLogRec;
import com.exascale.logging.XACommitLogRec;
import com.exascale.managers.HRDBMSWorker;
import com.exascale.managers.LogManager;
import com.exascale.misc.DataEndMarker;
import com.exascale.tables.Plan;
import com.exascale.tables.Transaction;
import com.exascale.threads.HRDBMSThread;

public final class RunstatsOperator implements Operator, Serializable
{
	private Operator child;
	private final MetaData meta;
	private HashMap<String, String> cols2Types;
	private HashMap<String, Integer> cols2Pos;
	private TreeMap<Integer, String> pos2Col;
	private Operator parent;
	private int node;
	private transient Plan plan;
	private String schema;
	private String table;
	private boolean done = false;
	private Transaction tx;
	
	public void setPlan(Plan plan)
	{
		this.plan = plan;
	}
	
	public void setTransaction(Transaction tx)
	{
		this.tx = tx;
	}

	public RunstatsOperator(String schema, String table, MetaData meta)
	{
		this.meta = meta;
		this.schema = schema;
		this.table = table;
	}

	@Override
	public void add(Operator op) throws Exception
	{
		throw new Exception("RunstatsOperator does not support children");
	}

	@Override
	public ArrayList<Operator> children()
	{
		final ArrayList<Operator> retval = new ArrayList<Operator>(1);
		return retval;
	}

	@Override
	public RunstatsOperator clone()
	{
		final RunstatsOperator retval = new RunstatsOperator(schema, table, meta);
		retval.node = node;
		return retval;
	}

	@Override
	public void close() throws Exception
	{
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
		if (!done)
		{
			done = true;
			MetaData.runstats(schema, table, tx);
			return 1;
		}
		else
		{
			return new DataEndMarker();
		}
	}

	@Override
	public void nextAll(Operator op) throws Exception
	{
	}

	@Override
	public Operator parent()
	{
		return parent;
	}

	@Override
	public void registerParent(Operator op) throws Exception
	{
		throw new Exception("RunstatsOperator does not support parents.");
	}

	@Override
	public void removeChild(Operator op)
	{
	}

	@Override
	public void removeParent(Operator op)
	{
	}

	@Override
	public void reset() throws Exception
	{
		throw new Exception("RunstatsOperator is not resetable");
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
	}
	
	@Override
	public String toString()
	{
		return "RunstatsOperator";
	}

}
