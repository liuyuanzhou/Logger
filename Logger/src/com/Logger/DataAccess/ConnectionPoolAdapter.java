package com.Logger.DataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

public class ConnectionPoolAdapter 
{
	protected ConnectionPoolType type = ConnectionPoolType.C3P0;
	protected DataSource dataSource;
	
	ConnectionPoolAdapter(Properties configs,ConnectionPoolType poolType) throws Exception
	{
		type = poolType;
	}
	
	public Connection getConnection() throws SQLException 
	{
		return dataSource.getConnection();
	}
}
