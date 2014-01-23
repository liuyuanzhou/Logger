package com.Logger.Impl;

import com.Logger.Config.AppenderType;
import com.Logger.Interface.IAppender;

public class DataBaseAppender implements IAppender 
{
	public AppenderType type;
	
	public boolean Enabled;
	
	public String Path;
	
	public DataBaseAppender(AppenderType aType,boolean bEnabled,String strPath)
	{
		type = aType;
		Enabled = bEnabled;
		Path = strPath;
	}
	
	public void appendLog(Iterable<LoggerEvent> events)
	{
		for(LoggerEvent e : events)
			System.out.println(e.toString());
	}
}
