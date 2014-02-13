package com.Logger.Common;

public class StringHelper {
	public static boolean isNullOrEmpty(String s)
	{
		if(s == null)
			return true;
		else
			if(s == "")
				return true;
		return false;
	}
	
	public static boolean isNullOrWhiteSpace(String s)
	{
		if(s==null)
			return true;
		else
			if(s.trim() == "")
				return true;
		return false;
	}
}
