package com.Logger.Test;

import java.sql.*;
import com.Logger.Impl.LoggerManager;
import com.Logger.Interface.ILogger;

public class ConnectionManager
{
	private final static ILogger logger = LoggerManager.getLogger(ConnectionManager.class.getName(),-1);
	
	public static Connection GetConnection()
	{
		
		
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //加载JDBC驱动  
		 
		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Yuanyuan";   //连接服务器和数据库sample  

		String userName = "sa";   //默认用户名  

		String userPwd = "123456";   //密码  

		Connection dbConn = null;  

		try {  

			Class.forName(driverName).newInstance();;  

			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);  
		}
		catch(Exception ex)
		{
			logger.errorFmt(ex,0,"Error Occur when get GetConnection,Message:%s",ex.getMessage());
		}
		
		return dbConn;
	}
}
