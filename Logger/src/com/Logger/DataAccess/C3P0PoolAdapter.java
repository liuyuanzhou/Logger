package com.Logger.DataAccess;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.dom4j.*;

import com.Logger.Common.FileHelper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0PoolAdapter extends ConnectionPoolAdapter
{
	C3P0PoolAdapter(Properties configs,ConnectionPoolType poolType) throws Exception
	{
		super(configs,poolType);
		
//		System.setProperty("com.mchange.v2.c3p0.cfg.xml","F:\\GitSource\\Logger\\Logger\\src\\c3p0-config.xml"); 
		init(configs);
	}
	
//	private void init(ComboPooledDataSource ds) 
//	{
//        Properties dbProps = new Properties();
//        try {
//            InputStream is = new FileInputStream("F:\\GitSource\\Logger\\Logger\\src\\c3p0.properties");
//            dbProps.load(is);
//            _logger.info("db config load success!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            _logger.error("DB config load failed.");
//            throw new RuntimeException("DB config load failed.");
//        }
//        try {
//        	ds.setDriverClass(dbProps.getProperty("c3p0.DriverClass").trim());
//        } catch (PropertyVetoException e1) {
//            throw new RuntimeException("com.sqlserver.jdbc.Driver����ʧ��");
//        }
//        // ds.setJdbcUrl("jdbc:mysql://127.0.0.1/mysession");
//        // ds.setUser("sessadmin");
//        // ds.setPassword("8877007");
//        _logger.error(dbProps.toString());
//        ds.setJdbcUrl(dbProps.getProperty("c3p0.JdbcUrl").trim());
//        ds.setUser(dbProps.getProperty("c3p0.user").trim());
//        String password = dbProps.getProperty("c3p0.password").trim();
//        //password = UtilCommon.dec(password);
//        ds.setPassword(password);
//        //ds.setPassword(UtilCommon.dec(dbProps.getProperty("c3p0.password").trim()));
//        // ���ӹر�ʱĬ�Ͻ�����δ�ύ�Ĳ����ع���Default: false autoCommitOnClose
//        ds.setAutoCommitOnClose(true);
//
//        // �����������Ӳ��Զ�ִ�еĲ�����䡣��ʹ�����Ӳ��Ե���������һ������߲����ٶȡ�ע�⣺
//        // ���Եı�����ڳ�ʼ����Դ��ʱ��ʹ��ڡ�Default: null preferredTestQuery
//        ds.setPreferredTestQuery("select 1");
//        // ���������Ĵ���ֻ����Ҫ��ʱ��ʹ�����������Ϊtrue��ô��ÿ��connection�ύ��
//        // ʱ�򶼽�У������Ч�ԡ�����ʹ��idleConnectionTestPeriod��automaticTestTable
//        // �ȷ������������Ӳ��Ե����ܡ�Default: false testConnectionOnCheckout
//        ds.setTestConnectionOnCheckout(false);
//        // �����Ϊtrue��ô��ȡ�����ӵ�ͬʱ��У�����ӵ���Ч�ԡ�Default: false testConnectionOnCheckin
//        ds.setTestConnectionOnCheckin(false);
//        // ��ȡ����ʧ�ܽ����������еȴ����ӳ�����ȡ���ӵ��߳��׳��쳣����������Դ����Ч
//        // �����������´ε���getConnection()��ʱ��������Ի�ȡ���ӡ������Ϊtrue����ô�ڳ���
//        // ��ȡ����ʧ�ܺ������Դ�������ѶϿ������ùرա�Default: false breakAfterAcquireFailure
//        ds.setBreakAfterAcquireFailure(false);
//
//        try {
//            // ��ʼ��ʱ��ȡ�������ӣ�ȡֵӦ��minPoolSize��maxPoolSize֮�䡣Default: 3
//            // initialPoolSize
//        	ds.setInitialPoolSize(Integer.parseInt(dbProps.getProperty("c3p0.initialPoolSize").trim()));
//            // ds.setInitialPoolSize(3);
//            // ���ӳ��б����������������Default: 15 maxPoolSize
//        	ds.setMaxPoolSize(Integer.parseInt(dbProps.getProperty("c3p0.maxPoolSize").trim()));
//            // ds.setMaxPoolSize(10);
//            // ���ӳ��б�������С��������
//        	ds.setMinPoolSize(Integer.parseInt(dbProps.getProperty("c3p0.maxPoolSize").trim()));
//            // ds.setMinPoolSize(1);
//            // �����ӳ��е����Ӻľ���ʱ��c3p0һ��ͬʱ��ȡ����������Default: 3 acquireIncrement
//        	ds.setAcquireIncrement(Integer.parseInt(dbProps.getProperty("c3p0.acquireIncrement").trim()));
//            // ds.setAcquireIncrement(1);
//            // ÿ60�����������ӳ��еĿ������ӡ�Default: 0 idleConnectionTestPeriod
//        	ds.setIdleConnectionTestPeriod(Integer.parseInt(dbProps.getProperty("c3p0.idleConnectionTestPeriod").trim()));
//            // ds.setIdleConnectionTestPeriod(60);
//            // ������ʱ��,25000����δʹ�������ӱ���������Ϊ0������������Default: 0 maxIdleTime
//        	ds.setMaxIdleTime(Integer.parseInt(dbProps.getProperty("c3p0.maxIdleTime").trim()));
//            // ds.setMaxIdleTime(25000);
//            // �����ڴ����ݿ��ȡ������ʧ�ܺ��ظ����ԵĴ�����Default: 30 acquireRetryAttempts
//        	ds.setAcquireRetryAttempts(Integer.parseInt(dbProps.getProperty("c3p0.acquireRetryAttempts").trim()));
//            // ds.setAcquireRetryAttempts(30);
//            // ���������м��ʱ�䣬��λ���롣Default: 1000 acquireRetryDelay
//        	ds.setAcquireRetryDelay(Integer.parseInt(dbProps.getProperty("c3p0.acquireRetryDelay").trim()));
//            // ds.setAcquireRetryDelay(1000);
//            _logger.info("db set config success!");
//        } catch (Exception e) {
//        	_logger.error("oh, db set config failed!");
//            e.printStackTrace();
//        }
//    }
	
	private void init(ComboPooledDataSource ds)
	{
		try
		{
			ds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ds.setJdbcUrl("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=TraceDB;selectMethod=cursor");
			ds.setUser("sa");
			ds.setPassword("123456");
			ds.setInitialPoolSize(3);
			ds.setMaxIdleTime(30);
			ds.setMaxPoolSize(30);
			ds.setMinPoolSize(3);
			ds.setMaxIdleTimeExcessConnections(15);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void init(Properties configs)
	{
		if(!configs.containsKey("acquireIncrement"))
			configs.setProperty("acquireIncrement", "5");
		if(!configs.containsKey("acquireRetryDelay"))
			configs.setProperty("acquireRetryDelay", "500");
		if(!configs.containsKey("checkoutTimeout"))
			configs.setProperty("checkoutTimeout", "10000");
		if(!configs.containsKey("idleConnectionTestPeriod"))
			configs.setProperty("idleConnectionTestPeriod", "60");
		if(!configs.containsKey("initialPoolSize"))
			configs.setProperty("initialPoolSize", "10");
		if(!configs.containsKey("minPoolSize"))
			configs.setProperty("minPoolSize", "10");
		if(!configs.containsKey("maxPoolSize"))
			configs.setProperty("maxPoolSize", "10");
		if(!configs.containsKey("maxIdleTime"))
			configs.setProperty("maxIdleTime", "30");
//		if(!configs.containsKey("automaticTestTable"))
//			configs.setProperty("automaticTestTable", "c3p0Test");


		dataSource = new ComboPooledDataSource();
		// ͨ�����䶯̬�趨����Դ����
		Class clazz = ComboPooledDataSource.class;
		Method[] methods = null;
		String key = null;
		String val = null;
		String methodName = null;
		Enumeration eunm = configs.propertyNames();
		while (eunm.hasMoreElements()) {
			key = (String) eunm.nextElement();
			val = configs.getProperty(key);
			methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
			methods = clazz.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					try {
						if (method.getParameterTypes()[0].getName() == "int") {
							method.invoke(dataSource, Integer.parseInt(val));
						} else if (method.getParameterTypes()[0].getName() == "long") {
							method.invoke(dataSource, Long.parseLong(val));
						} else if (method.getParameterTypes()[0].getName() == "float") {
							method.invoke(dataSource, Float.parseFloat(val));
						} else if (method.getParameterTypes()[0].getName() == "double") {
							method.invoke(dataSource, Double.parseDouble(val));
						} else if (method.getParameterTypes()[0].getName() == "java.lang.String") {
							method.invoke(dataSource, val);
						} else {
							method.invoke(dataSource, val);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}
}