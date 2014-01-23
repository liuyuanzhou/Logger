package com.Logger.Impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.Logger.Config.AppenderType;
import com.Logger.Interface.IAppender;

public class TextAppender implements IAppender
{
	public AppenderType type;
	
	public boolean Enabled;
	
	public String Path;
	
	private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH");//设置日期格式
	
	public TextAppender(AppenderType aType,boolean bEnabled,String strPath)
	{
		type = aType;
		Enabled = bEnabled;
		Path = strPath;
		
		File file = new File(Path+"//"+LoggerManager.ServiceName);
		if(!file.isDirectory())
		{
			file.mkdir();
		}
	}
	
	public void appendLog(Iterable<LoggerEvent> events)
	{
		String strPath = Path+"\\" + LoggerManager.ServiceName + "\\Log_" + df.format(System.currentTimeMillis())+".txt";
		doAppendLog(events,strPath,0);
	}
	
	private void doAppendLog(Iterable<LoggerEvent> events,String strPath,int retryCount)
	{
		File file = new File(strPath + (retryCount == 0 ? "":String.valueOf(retryCount)));
		try
		{
			if(!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file,true);
			for(LoggerEvent e : events)
				fw.write(e.toString());
			fw.close();
		}
		catch(IOException ioex)
		{
			if(retryCount < 3)
				doAppendLog(events,strPath,retryCount + 1);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
