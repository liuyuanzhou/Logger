package com.Logger.DataAccess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.Logger.Impl.LoggerManager;
import com.Logger.Interface.IDatabaseOperation;
import com.Logger.Interface.ILogger;

public class Database implements IDatabaseOperation
{
	private static ILogger _logger = LoggerManager.getLogger(Database.class);
	private ConnectionPoolAdapter _adapter;
	
	public Database(Properties properties,ConnectionPoolType type)
	{
		_adapter = getAdapter(properties,type);
	}
	
    ConnectionPoolAdapter getAdapter(Properties p,ConnectionPoolType type)
	{
		try
		{
			switch(type)
			{
			case C3P0:
				return new C3P0PoolAdapter(p,type);
			case DBCP:
			case BoneCP:
			case Proxool:
			default:
				return null;
			}
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public int spExecuteNonQuery(String spName,String[] params,Object... values)
	{
		Connection con = null;
		CallableStatement cstm = null;
		int result = 0;
		try
		{
			System.out.println(String.format("Begin to Execute Sotred Procedure %s %s", spName,getParamsString(values)));
			con = _adapter.getConnection();
			cstm = con.prepareCall(getCallSql(spName,params != null ?params.length:0));
			fillStatememt(cstm, params, values);
			result = cstm.executeUpdate();
		}
		catch(SQLException ex)
		{
			System.out.println(String.format("Execute Sotred Procedure Error,exec %s %s \r\n Error Message:%s",
					spName,getParamsString(values),ex.getMessage()));
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(cstm != null)
					cstm.close();
				if(con != null)
					con.close();
			}
			catch(Exception ex)
			{
				_logger.info("attempt close failed");
			}
		}
		return result;
	}
	
	public Object spExecuteScalar(String spName,String[] params,Object[] values)
	{
		return 0;
	}
	
	public ResultSet spExecuteDataReader(String spName,String[] params,Object[] values)
	{
		return null;
	}
	
	String getParamsString(Object[] values)
	{
		String strReturn = "";
		if(values == null)
			return strReturn;
		for(Object o:values)
		{
			strReturn += o.toString() + ", ";
		}
		return strReturn.substring(0,strReturn.length()-2);
	}
	
	String getCallSql(String spName,int paramsCount)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{call ").append(spName).append("(");
		for(int i=0; i<paramsCount;i++)
		{
			sb.append("?");
			if(i<paramsCount-1)
				sb.append(",");
		}
		sb.append(")}");
		return sb.toString();
	}
	
	void fillStatememt(CallableStatement stmt, String[] params, Object[] values) throws SQLException
	{
		if(values != null)
			for (int i = 0; i < values.length; i++) {
//				if (params == null) {
					stmt.setObject(i + 1, values[i]);
//				} else {
//					stmt.setObject(params[i], values[i]);
//				}
			}
	}
}
