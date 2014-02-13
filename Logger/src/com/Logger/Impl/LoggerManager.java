package com.Logger.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.Logger.Common.FileHelper;
import com.Logger.Common.StringHelper;
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
		Config = new LoggerConfig();
		Hashtable<String,Properties> dictConfig = FileHelper.loadProperties("src/config.xml", "logger-config", "config");
		if(dictConfig.get("logger") != null){
			String level = String.valueOf(dictConfig.get("logger").get("level"));
			String canLogIds = String.valueOf(dictConfig.get("logger").get("CanLogIds"));
			String canLogMarkerIds = String.valueOf(dictConfig.get("logger").get("CanLogMarkerIds"));
			if(!StringHelper.isNullOrWhiteSpace(level))
				Config.level = LoggerLevel.values()[Integer.parseInt(level)];
			if(!StringHelper.isNullOrWhiteSpace(canLogIds));
			Config.CanLogIds = canLogIds.split(",");
			if(!StringHelper.isNullOrWhiteSpace(canLogMarkerIds));
			Config.CanLogMarkerIds = canLogMarkerIds.split(",");
		}
		List<IAppender> ltAppender = new ArrayList<IAppender>();
		Properties textProperties = dictConfig.get("Text");
		Properties consoleProperties = dictConfig.get("Console");
		Properties databaseProperties = dictConfig.get("Database");
		if(textProperties != null)
		{
			boolean b = Boolean.valueOf(String.valueOf(textProperties.get("Enabled")));
			if(b){
				TextAppender t = new TextAppender(AppenderType.Text,b,String.valueOf(textProperties.get("Path")));
				ltAppender.add(t);
			}
		}
		if(consoleProperties != null)
		{
			boolean b = Boolean.valueOf(String.valueOf(consoleProperties.get("Enabled")));
			if(b){
				ConsoleAppender t = new ConsoleAppender(AppenderType.Text,b);
				ltAppender.add(t);
			}
		}
		if(databaseProperties != null)
		{
			boolean b = Boolean.valueOf(String.valueOf(consoleProperties.get("Enabled")));
			if(b){
				DataBaseAppender t = new DataBaseAppender(AppenderType.Text,b,"");
				ltAppender.add(t);
			}
		}
		Config.appenders = ltAppender;
	}

	public static ILogger getLogger(String LoggerName)
	{
		return getLogger(LoggerName,0);
	}

	@SuppressWarnings("rawtypes")
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
