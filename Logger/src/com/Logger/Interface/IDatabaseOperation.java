package com.Logger.Interface;

import java.sql.ResultSet;

public interface IDatabaseOperation 
{
	int spExecuteNonQuery(String spName,String[] params,Object[] values);
	
//	void spBeginExecuteNonQuery(String spName,String[] params,Object[] values,Action<Integer> callback);
	
	Object spExecuteScalar(String spName,String[] params,Object[] values);
	
	ResultSet spExecuteDataReader(String spName,String[] params,Object[] values);
	
//	void spBeginExecuteDataReader(String spName,String[] params,Object[] values,Action<ResultSet> callback);
	
//	DataTable spExecuteDataTable(String spName,String[] params,Object[] values);
//	
//	DataSet spExecuteDataSet(String spName,String[] params,Object[] values);
//	
//	int BulkInsert<T>(String TableName,Iterable<T> values);
}