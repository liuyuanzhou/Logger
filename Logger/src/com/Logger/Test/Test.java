package com.Logger.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.Queue;

import com.Logger.Impl.*;
import com.Logger.Interface.ILogger;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		System.out.println(q.size());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());

		System.out.println(LoggerLevel.Info.intValue());

		ILogger logger = LoggerManager.getLogger(Test.class.getName(), 10000);
//		for(int i=0;i<10;i++)
//		{
//			logger.info(23, "info Message "+String.valueOf(i));
//		}
		Connection con =  ConnectionManager.GetConnection();
		Statement stat = null;
		PreparedStatement = null;
		ResultSet rs = null;
		String sql = "SELECT [UserId],[IsFakeData],[RegisterStatus],[NickName],[MobileNo],[Email] FROM [Yuanyuan].[dbo].[UserInfo]";
		try{
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				System.out.printf("UserId:%s ",rs.getString("UserId"));
				System.out.printf("MoblieNo:%s ",rs.getString("MobileNo"));
				System.out.println();
			}
		}
		catch(Exception ex)
		{
			logger.error(0, "execute sql error",ex);
		}
		try{
		if(rs != null)
			rs.close();
		if(stat != null)
			stat.close();
		if(con != null)
			con.close();
		}
		catch(Exception ex)
		{
			logger.error(0, "dispose connection error!", ex);
		}
	}
}
