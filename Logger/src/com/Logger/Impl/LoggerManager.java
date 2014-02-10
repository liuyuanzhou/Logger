package com.Logger.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Logger.Config.AppenderInfo;
import com.Logger.Config.AppenderType;
import com.Logger.Config.LoggerConfig;
import com.Logger.Interface.*;
import com.Logger.LazyQueue.LazyQueue;

public class LoggerManager 
{
	private static Map<String,ILogger> _dictLogger = new HashMap<String,ILogger>();
	private static Object _syncRoot = new Object(); 
	public static LoggerConfig Config;
	public static String ServiceName;
	public static String ComputerName;
	private static LazyQueue<LoggerEvent> _queue;
	
	static{
		ServiceName = "TestService";
		ComputerName = System.getenv().get("COMPUTERNAME").toString();
		initConfig();
		Action<ArrayList<LoggerEvent>> callback = new Action<ArrayList<LoggerEvent>>(){
			@Override
			public void run(ArrayList<LoggerEvent> events)
			{
				for(IAppender info : Config.appenders)
				{
					info.appendLog(events);
				}
			}
		};
		_queue = new LazyQueue<LoggerEvent>("LoggerQueue",32,50,callback);
	}
	
	private static void initConfig()
	{
		/* 初始化配置，暂时将配置在代码里面初始化，待后续更改为动态加载   */
		System.out.println("initConfig!");
		Config = new LoggerConfig();
		Config.level = LoggerLevel.Info;
		Config.CanLogIds = null;
		Config.CanLogMarkerIds = null;
		List<AppenderInfo> ltAppenderInfo = new ArrayList<AppenderInfo>();
		ltAppenderInfo.add(new AppenderInfo("Console",true));
		ltAppenderInfo.add(new AppenderInfo("Text",true,"d://Log"));
		ltAppenderInfo.add(new AppenderInfo("DataBase",true,"d://Log"));
		List<IAppender> ltAppender = new ArrayList<IAppender>();
		for(AppenderInfo info : ltAppenderInfo)
		{
			switch(info.Type)
			{
				case "Console":
					ConsoleAppender ca = new ConsoleAppender(AppenderType.Console,info.Enabled);
					ltAppender.add(ca);
					break;
				case "Text":
					TextAppender ta = new TextAppender(AppenderType.Text,info.Enabled,info.Path);
					ltAppender.add(ta);
					break;
				case "DataBase":
					DataBaseAppender da = new DataBaseAppender(AppenderType.DataBase,info.Enabled,info.Path);
					ltAppender.add(da);
					System.out.println("add database");
					break;
				default:
					break;
			}
		}
		Config.appenders = ltAppender;
	}
	
	public static ILogger getLogger(String LoggerName)
	{
		return getLogger(LoggerName,0);
	}
	
	public static ILogger getLogger(Class clazz)
	{
		return getLogger(clazz.getName());
	}
	
	public static ILogger getLogger(String LoggerName, int LoggerId)
	{
		ILogger logger = null;
		synchronized(_syncRoot){
			if(!_dictLogger.containsKey(LoggerName))
			{
				logger = new LoggerImpl(LoggerName,LoggerId);
				_dictLogger.put(LoggerName, logger);
			}
			else
				logger = _dictLogger.get(LoggerName);
			}
		return logger;
	}
	
	public static void enqueue(LoggerEvent event)
	{
		_queue.Enqueue(event);
	}
}
