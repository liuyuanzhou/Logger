package com.Logger.Interface;

import com.Logger.Impl.LoggerEvent;

public interface IAppender {
	
	void appendLog(Iterable<LoggerEvent> events);
}
