package com.Logger.Config;

public class AppenderInfo 
{
	public String Type;
	
	public boolean Enabled;
	
	public String Path;
	
	public AppenderInfo(String strType,boolean bEnabled)
	{
		Type = strType;
		Enabled = bEnabled;
		Path = "";
	}
	
	public AppenderInfo(String strType,boolean bEnabled,String strPath)
	{
		Type = strType;
		Enabled = bEnabled;
		Path = strPath;
	}
}
