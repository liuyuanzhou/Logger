package com.Logger.Impl;
import java.lang.StringBuilder;

public class LoggerEvent 
{
	public String LoggerName;
	
	public String LogTime;
	
	public String ProcessId;
	
	public int ThreadId;
	
	public String ServiceName;
	
	public int LogId;
	
	public int MarkerId;
	
	public String Message;
	
	public Exception ex;
	
	public int Level;
	
	public String ComputerName;
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("***\r\n-------------------------------------------------------------------------------\r\n");
		sb.append(String.format("%s [%s] Tracing by <%s> at \\\\%s \r\n", LogTime,Level,LoggerName,ComputerName));
		sb.append(String.format("Process: \"%s\" Thread: \"%s\" \r\n",ProcessId,ThreadId));
		sb.append(String.format("Message: %s\r\n",Message));
		if(ex != null)
			sb.append(ex.toString()+"\r\n");
		sb.append(String.format("LogId: %s \r\n",LogId));
		sb.append(String.format("MarkerId: %s \r\n", MarkerId));
		sb.append("-------------------------------------------------------------------------------\r\n");
		
		return sb.toString();
	}
}
