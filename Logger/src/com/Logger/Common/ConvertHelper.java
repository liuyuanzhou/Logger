package com.Logger.Common;

public class ConvertHelper {
	public static int toInt32(Object o)
	{
		return toInt32(String.valueOf(o));
	}
	
	public static int toInt32(String s)
	{
		return Integer.parseInt(s);
	}
}
