package com.Logger.DataAccess;

import java.sql.ResultSet;
import com.Logger.Interface.IDatabaseOperation;

public class DataBaseOperation implements IDatabaseOperation 
{
	public int spExecuteNonQuery(String spName,String[] params,Object[] values)
	{
		return 0;
	}
	
	public Object spExecuteScalar(String spName,String[] params,Object[] values)
	{
		return 0;
	}
	
	public ResultSet spExecuteDataReader(String spName,String[] params,Object[] values)
	{
		return null;
	}
}
