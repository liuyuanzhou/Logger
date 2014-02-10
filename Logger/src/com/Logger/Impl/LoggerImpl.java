package com.Logger.Impl;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.Logger.Interface.ILogger;

public class LoggerImpl implements ILogger 
{
	private String _loggerName;
	
	private int _loggerId;
	
	private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	
	public LoggerImpl(String LoggerName,int LoggerId)
	{
		_loggerName = LoggerName;
		_loggerId = LoggerId;
	}
	
	public void info(String Message)
	{
		info(0,Message);
	}
	
	public void info(int MarkerId, String Message)
	{
		info(MarkerId,Message,null);
	}
	
	public void info(int MarkerId,String Message,Exception ex)
	{
		if(CanLog(MarkerId,LoggerLevel.Info))
			WriteLog(MarkerId,ex,LoggerLevel.Info,Message);
	}
	
	public void infoFmt(String Message,Object...Params)
	{
		infoFmt(0,Message,Params);
	}
	
	public void infoFmt(int MarkerId,String Message,Object...Params)
	{
		info(MarkerId,String.format(Message,Params),null);
	}
	
	public void infoFmt(Exception ex,int MarkerId,String Message,Object...Params)
	{
		info(MarkerId,String.format(Message,Params),ex);
	}
	
	public void warn(String Message)
	{
		warn(0,Message);
	}
	
	public void warn(int MarkerId,String Message)
	{
		warn(MarkerId,Message,null);
	}
	
	public void warn(int MarkerId,String Message,Exception ex)
	{
		if(CanLog(MarkerId,LoggerLevel.Warn))
			WriteLog(MarkerId,ex,LoggerLevel.Warn,Message);
	}
	
	public void warnFmt(String Message,Object...Params)
	{
		warnFmt(0,Message);
	}
	
	public void warnFmt(int MarkerId,String Message,Object...Params)
	{
		warn(MarkerId,String.format(Message,Params),null);
	}
	
	public void warnFmt(Exception ex,int MarkerId,String Message,Object...Params)
	{
		warn(MarkerId,String.format(Message, Params),ex);
	}
	
	public void error(String Message)
	{
		error(0,Message);
	}
	
	public void error(int MarkerId,String Message)
	{
		error(MarkerId,Message,null);
	}
	
	public void error(int MarkerId,String Message,Exception ex)
	{
		if(CanLog(MarkerId,LoggerLevel.Error))
			WriteLog(MarkerId,ex,LoggerLevel.Error,Message);
	}
	
	public void errorFmt(String Message,Object...Params)
	{
		errorFmt(0,Message,Params);
	}
	
	public void errorFmt(int MarkerId,String Message,Object...Params)
	{
		error(MarkerId,String.format(Message, Params));
	}
	
	public void errorFmt(Exception ex,int MarkerId,String Message,Object...Params)
	{
		error(MarkerId,String.format(Message, Params),ex);
	}
	
	private boolean CanLog(int MarkerId,LoggerLevel Level)
	{
		if(LoggerManager.Config.CanLogIds != null)  //如果开了指定id日志，则必定要记日志
		{
			if(Arrays.asList(LoggerManager.Config.CanLogIds).contains(_loggerId))
				return true;
		}
		if(LoggerManager.Config.CanLogMarkerIds != null)
		{
			if(Arrays.asList(LoggerManager.Config.CanLogMarkerIds).contains(MarkerId))
				return true;
		}
		if(LoggerManager.Config.level.intValue() <= Level.intValue())
			return true;
				
		return false;
	}
	
	private void WriteLog(int MarkerId,Exception ex,LoggerLevel Level,String Message)
	{
		LoggerEvent event = new LoggerEvent();
		event.LoggerName = _loggerName;
		event.LogId = _loggerId;
		event.Level = Level.toString();
		event.LogTime = df.format(new Date());
		event.MarkerId = MarkerId;
		event.ServiceName = LoggerManager.ServiceName;
		event.ProcessId = String.valueOf(getPid());
		event.ThreadId = Thread.currentThread().getName();
		event.ex = ex;
		event.Message = Message;
		event.ComputerName = LoggerManager.ComputerName;
		
		LoggerManager.enqueue(event);
	}
	
	private static int getPid() {  
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
        String name = runtime.getName(); // format: "pid@hostname"  
        try {  
            return Integer.parseInt(name.substring(0, name.indexOf('@')));  
        } catch (Exception e) {  
            return -1;  
        }  
    }  
}
