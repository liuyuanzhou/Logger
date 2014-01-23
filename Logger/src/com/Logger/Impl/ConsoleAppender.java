package com.Logger.Impl;

import com.Logger.Config.AppenderType;
import com.Logger.Interface.IAppender;

public class ConsoleAppender implements IAppender
{
	public AppenderType type;
	
	public boolean Enabled;
	
	public ConsoleAppender(AppenderType aType,boolean bEnabled)
	{
		type = aType;
		Enabled = bEnabled;
	}
	
	public void appendLog(Iterable<LoggerEvent> events)
	{
		for(LoggerEvent e : events)
			System.out.println(e.toString());
	}
}
