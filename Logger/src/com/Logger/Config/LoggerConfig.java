package com.Logger.Config;

import java.util.List;

import com.Logger.Impl.LoggerLevel;
import com.Logger.Interface.IAppender;

public class LoggerConfig 
{
	public LoggerLevel level = LoggerLevel.Info;
	
	public String[] CanLogMarkerIds;
	
	public String[] CanLogIds;
	
	public List<IAppender> appenders;
	
	public LoggerConfig()
	{
		
	}
	
	public LoggerConfig(String strLevel,String strCanLogMarkerIds,String strCanLogIds,List<IAppender> ltAppenders)
	{
		level = LoggerLevel.valueOf(strLevel);
		CanLogMarkerIds = strCanLogMarkerIds.split(",");
		CanLogIds = strCanLogIds.split(",");
		appenders = ltAppenders;
	}
}
