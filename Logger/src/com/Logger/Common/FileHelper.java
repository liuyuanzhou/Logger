package com.Logger.Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.Logger.Impl.LoggerManager;
import com.Logger.Interface.ILogger;

public class FileHelper 
{
	private static ILogger _logger = LoggerManager.getLogger(FileHelper.class);
	
	public static String readFileAsString(String FilePath)
	{
		StringBuilder sb = new StringBuilder();
		String temp = null;
		try
		{
			File f = new File(FilePath);
			BufferedReader reader = new BufferedReader(new FileReader(f));
			while((temp = reader.readLine()) != null)
			{
				sb.append(temp);
			}
			reader.close();
		}
		catch(Exception ex)
		{
			_logger.errorFmt(ex,0,"Error Occur when readFileAsString,FilePath:%s",FilePath);	
		}
		return sb.toString();
	}
	
	public static Hashtable<String,Properties> loadProperties(String filePath,String RootElementName)
	{
		Hashtable<String,Properties> h = new Hashtable<String,Properties>();
		try
		{
			Document doc = DocumentHelper.parseText(FileHelper.readFileAsString(filePath));
			Element element = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> lt = element.elements(RootElementName);
			for(Element et : lt)
			{
				List<Element> ltProperties = et.elements("property");
				Properties p = new Properties(); 
				for(Element e : ltProperties)
				{
					p.setProperty(e.attributeValue("name"), e.getText());
				}
				h.put(et.attributeValue("name"), p);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return h;
	}
}
