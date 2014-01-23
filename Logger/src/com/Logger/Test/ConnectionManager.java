package com.Logger.Test;

import java.sql.*;
import com.Logger.Impl.LoggerManager;
import com.Logger.Interface.ILogger;

public class ConnectionManager
{
	private final static ILogger logger = LoggerManager.getLogger(ConnectionManager.class.getName(),-1);
	
	public static Connection GetConnection()
	{
		
		
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //����JDBC����  
		 
		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Yuanyuan";   //���ӷ����������ݿ�sample  

		String userName = "sa";   //Ĭ���û���  

		String userPwd = "123456";   //����  

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
