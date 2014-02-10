package com.Logger.Impl;

import com.Logger.Config.AppenderType;
import com.Logger.DataAccess.DBManager;
import com.Logger.DataAccess.Database;
import com.Logger.Interface.IAppender;

public class DataBaseAppender implements IAppender 
{
	public AppenderType type;
	
	public boolean Enabled;
	
	public String Path;
	
	private Database db = null;
	
	public DataBaseAppender(AppenderType aType,boolean bEnabled,String strPath)
	{
		type = aType;
		Enabled = bEnabled;
		Path = strPath;
		db = DBManager.getDatabase("TraceDB");
	}
	
	public void appendLog(Iterable<LoggerEvent> events)
	{
		for(LoggerEvent e : events)
		{
			System.out.println(e.toString());
			String[] params = {"@MarkerId","@LogId","@ServiceName","@CreateTime",
					"@LoggerName","@ComputerName","@ThreadId","@Message","@Error","@Level","ProcessId"};
			Object[] values = {e.MarkerId,e.LogId,e.ServiceName,e.LogTime,e.LoggerName,
					e.ComputerName,e.ThreadId,e.Message,e.ex==null?"":e.ex.toString(),e.Level,e.ProcessId};
			System.out.println("ready to write to database");
			db.spExecuteNonQuery("AddLog", params, values);
		}
	}
}
