/**
 * 
 */
package com.Logger.Interface;

/**
 * @author liuyuanzhou
 *
 */
public interface ILogger {
	void info(String Message);
	
	void info(int MarkerId,String Message);
	
	void info(int MarkerId,String Message,Exception ex);
	
	void infoFmt(String Message,Object...Params);
	
	void infoFmt(int MarkerId,String Message,Object...Params);
	
	void infoFmt(Exception ex,int MarkerId,String Message,Object...Params);
	
	void warn(String Message);
	
	void warn(int MarkerId,String Message);
	
	void warn(int MarkerId,String Message,Exception ex);
	
	void warnFmt(String Message,Object...Params);
	
	void warnFmt(int MarkerId,String Message,Object...Params);
	
	void warnFmt(Exception ex,int MarkerId,String Message,Object...Parames);
	
	void error(String Message);
	
	void error(int MarkerId,String Message);
	
	void error(int MarkerId,String Message,Exception ex);
	
	void errorFmt(String Message,Object...Params);
	
	void errorFmt(int MarkerId,String Message,Object...Params);
	
	void errorFmt(Exception ex,int MarkerId,String Message,Object...Parames);
}
