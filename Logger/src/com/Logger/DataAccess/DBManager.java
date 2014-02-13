package com.Logger.DataAccess;

import java.util.Hashtable;
import java.util.Properties;

import com.Logger.Common.FileHelper;
import com.Logger.Impl.LoggerManager;
import com.Logger.Interface.ILogger;

public class DBManager 
{
	private static ILogger _logger = LoggerManager.getLogger(DBManager.class);
	private static Hashtable<String,Database> databases = new Hashtable<String,Database>();
	private static Hashtable<String,Properties> dictConnectionStrings = new Hashtable<String,Properties>();
	
	static{
		dictConnectionStrings = FileHelper.loadProperties("src/config.xml", "c3p0-config","named-config");
	}
	
	public static Database getDatabase(String dbName)
	{
		Properties p = dictConnectionStrings.get(dbName);
		if(p != null)
			return getDatabase(dbName,p);
		else
			return getDatabase(dbName,null);
	}
	
	public static Database getDatabase(String dbName, Properties configs)
	{
		return getDatabase(dbName,configs,ConnectionPoolType.C3P0);
	}
	
	public static Database getDatabase(String dbName,Properties properties,ConnectionPoolType type)
	{
		Database db = databases.get(dbName);
		if(db != null)
		{
			return db;
		}
		else
		{
			try
			{
				db = new Database(properties,type);
				databases.put(dbName, db);
			}
			catch(Exception ex)
			{
				_logger.errorFmt(ex,0, "create database error,dbName:%s,type:%s", dbName,type);
			}
			return db;
		}
	}
}
