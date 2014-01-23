package com.Logger.Impl;

public enum LoggerLevel
{
	None(0),
	Info(1),
	Warn(2),
	Error(3),
	System(4);
	
	private int level;
	private LoggerLevel(int _level)
	{
		this.level = _level;
	}
	
	public int intValue()
	{
		return level;
	}
}
