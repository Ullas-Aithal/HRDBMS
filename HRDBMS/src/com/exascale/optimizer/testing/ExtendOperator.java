package com.exascale.optimizer.testing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.exascale.optimizer.testing.ResourceManager.DiskBackedHashMap;

public class ExtendOperator implements Operator, Serializable
{
	protected Operator child;
	protected Operator parent;
	protected HashMap<String, String> cols2Types;
	protected HashMap<String, Integer> cols2Pos;
	protected TreeMap<Integer, String> pos2Col;
	protected String prefix;
	protected MetaData meta;
	protected String name;
	protected int node;
	FastStringTokenizer tokens;
	
	public void reset()
	{
		child.reset();
	}
	
	public void setChildPos(int pos)
	{
	}
	
	public int getChildPos()
	{
		return 0;
	}
	
	public ExtendOperator(String prefix, String name, MetaData meta)
	{
		this.prefix = prefix;
		this.meta = meta;
		this.name = name;
		this.tokens = new FastStringTokenizer(prefix, ",", false);
	}
	
	public ExtendOperator clone()
	{
		ExtendOperator retval = new ExtendOperator(prefix, name, meta);
		retval.node = node;
		retval.tokens = tokens.clone();
		return retval;
	}
	
	public int getNode()
	{
		return node;
	}
	
	public void setNode(int node)
	{
		this.node = node;
	}
	
	public ArrayList<String> getReferences()
	{
		ArrayList<String> retval = new ArrayList<String>();
		FastStringTokenizer tokens = new FastStringTokenizer(prefix, ",", false);
		while (tokens.hasMoreTokens())
		{
			String temp = tokens.nextToken();
			if (Character.isLetter(temp.charAt(0)) || (temp.charAt(0) == '_'))
			{
				retval.add(temp);
			}
		}
		
		return retval;
	}
	
	public String getOutputCol()
	{
		return name;
	}
	
	public MetaData getMeta()
	{
		return meta;
	}
	
	public Operator parent()
	{
		return parent;
	}
	
	public ArrayList<Operator> children()
	{
		ArrayList<Operator> retval = new ArrayList<Operator>(1);
		retval.add(child);
		return retval;
	}
	
	public String toString()
	{
		return "ExtendOperator: " + name + "=" + prefix;
	}
	
	@Override
	public void start() throws Exception 
	{
		child.start();
	}
	
	public void nextAll(Operator op) throws Exception
	{
		child.nextAll(op);
		Object o = next(op);
		while (!(o instanceof DataEndMarker))
		{
			o = next(op);
		}
	}

	//@?Parallel
	@Override
	public Object next(Operator op) throws Exception 
	{
		Object o = child.next(this);
		if (o instanceof DataEndMarker)
		{
			return o;
		}
		
		ArrayList<Object> row = (ArrayList<Object>)o; 
		row.add(parsePrefixDouble(prefix, row));
		return row;
	}

	@Override
	public void close() throws Exception 
	{
		child.close();
	}
	
	public void removeChild(Operator op)
	{
		if (op == child)
		{
			child = null;
			op.removeParent(this);
		}
	}
	
	public void removeParent(Operator op)
	{
		parent = null;
	}

	@Override
	public void add(Operator op) throws Exception 
	{
		if (child == null)
		{
			child = op;
			child.registerParent(this);
			if (child.getCols2Types() != null)
			{
				cols2Types = (HashMap<String, String>)child.getCols2Types().clone();
				cols2Types.put(name, "FLOAT");
				cols2Pos = (HashMap<String, Integer>)child.getCols2Pos().clone();
				cols2Pos.put(name, cols2Pos.size());
				pos2Col = (TreeMap<Integer, String>)child.getPos2Col().clone();
				pos2Col.put(pos2Col.size(), name);
			}
		}
		else
		{
			throw new Exception("ExtendOperator only supports 1 child.");
		}
	}

	@Override
	public void registerParent(Operator op) throws Exception
	{
		if (parent == null)
		{
			parent = op;
		}
		else
		{
			throw new Exception("ExtendOperator only supports 1 parent.");
		}
	}

	public HashMap<String, String> getCols2Types() {
		return cols2Types;
	}

	@Override
	public HashMap<String, Integer> getCols2Pos() {
		return cols2Pos;
	}

	@Override
	public TreeMap<Integer, String> getPos2Col() {
		return pos2Col;
	}

	protected Double parsePrefixDouble(String prefix, ArrayList<Object> row)
	{
		Stack<String> parseStack = new Stack<String>();
		Stack<Object> execStack = new Stack<Object>();
		
		for (String token : tokens.allTokens())
		{
			parseStack.push(token);
		}
		
		while (parseStack.size() > 0)
		{
			String temp = parseStack.pop();
			if (temp.equals("*"))
			{
				Double lhs = (Double)execStack.pop();
				Double rhs = (Double)execStack.pop();
				execStack.push(lhs * rhs);
				
			}
			else if (temp.equals("-"))
			{
				Double lhs = (Double)execStack.pop();
				Double rhs = (Double)execStack.pop();
				execStack.push(lhs - rhs);
			}
			else if (temp.equals("+"))
			{
				Double lhs = (Double)execStack.pop();
				Double rhs = (Double)execStack.pop();
				execStack.push(lhs + rhs);
			}
			else if (temp.equals("/"))
			{
				Double lhs = (Double)execStack.pop();
				Double rhs = (Double)execStack.pop();
				execStack.push(lhs / rhs);
			}
			else
			{
				try
				{
					if (Character.isLetter(temp.charAt(0)) || (temp.charAt(0) == '_'))
					{
						Object field = null;
						try
						{
							field = row.get(cols2Pos.get(temp));
						}
						catch(Exception e)
						{
							e.printStackTrace();
							System.out.println("Error getting column " + temp + " from row " + row);
							System.out.println("Cols2Pos = " + cols2Pos);
							System.exit(1);
						}
						if (field instanceof Long)
						{
							execStack.push(ResourceManager.internDouble(new Double(((Long)field).longValue())));
						}
						else if (field instanceof Integer)
						{
							execStack.push(ResourceManager.internDouble(new Double(((Integer)field).intValue())));
						}
						else if (field instanceof Double)
						{
							execStack.push(field);
						}
						else
						{
							System.out.println("Unknown type in ExtendOperator: " + field.getClass());
							System.out.println("Row: " + row);
							System.out.println("Cols2Pos: " + cols2Pos);
							System.exit(1);
						}
					}
					else
					{
						double d = Double.parseDouble(temp);
						execStack.push(d);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.exit(1);
				}
			}
		}
		
		return (Double)execStack.pop();
	}
}
