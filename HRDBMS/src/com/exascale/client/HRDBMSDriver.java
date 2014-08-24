package com.exascale.client;

import java.net.Socket;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import com.exascale.compression.CompressedSocket;

public class HRDBMSDriver implements Driver
{
	static
	{
		try
		{
			DriverManager.registerDriver(new HRDBMSDriver());
		}
		catch (final Exception e)
		{
			e.printStackTrace(System.out);
		}
	}

	@Override
	public boolean acceptsURL(String arg0) throws SQLException
	{
		final String protocol = arg0.substring(0, 14);
		if (!protocol.equals("jdbc:hrdbms://"))
		{
			return false;
		}
		
		return true;
	}

	@Override
	public Connection connect(String arg0, Properties arg1) throws SQLException
	{
		try
		{
			final String protocol = arg0.substring(0, 14);
			if (!protocol.equals("jdbc:hrdbms://"))
			{
				return null;
			}

			final int portDelim = arg0.indexOf(":", 14);
			final String hostname = arg0.substring(14, portDelim);
			final String port = arg0.substring(portDelim + 1);
			int portNum = 0;
			try
			{
				portNum = Integer.parseInt(port);
			}
			catch(Exception e)
			{
				throw new SQLException("Invalid port number!");
			}
			
			Socket sock = null;
			try
			{
				sock = new CompressedSocket(hostname, portNum);
			}
			catch(Exception e)
			{
				throw new SQLException(e.getMessage());
			}
			return new HRDBMSConnection(sock, arg1.getProperty("user"), arg1.getProperty("password"));
		}
		catch (final Exception e)
		{
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public int getMajorVersion()
	{
		return 1;
	}

	@Override
	public int getMinorVersion()
	{
		return 0;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1) throws SQLException
	{
		return new DriverPropertyInfo[0];
	}

	@Override
	public boolean jdbcCompliant()
	{
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException
	{
		throw new SQLFeatureNotSupportedException();
	}

}
