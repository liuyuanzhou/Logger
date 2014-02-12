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
//            throw new RuntimeException("com.sqlserver.jdbc.Driver加载失败");
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
//        // 连接关闭时默认将所有未提交的操作回滚。Default: false autoCommitOnClose
//        ds.setAutoCommitOnClose(true);
//
//        // 定义所有连接测试都执行的测试语句。在使用连接测试的情况下这个一显著提高测试速度。注意：
//        // 测试的表必须在初始数据源的时候就存在。Default: null preferredTestQuery
//        ds.setPreferredTestQuery("select 1");
//        // 因性能消耗大请只在需要的时候使用它。如果设为true那么在每个connection提交的
//        // 时候都将校验其有效性。建议使用idleConnectionTestPeriod或automaticTestTable
//        // 等方法来提升连接测试的性能。Default: false testConnectionOnCheckout
//        ds.setTestConnectionOnCheckout(false);
//        // 如果设为true那么在取得连接的同时将校验连接的有效性。Default: false testConnectionOnCheckin
//        ds.setTestConnectionOnCheckin(false);
//        // 获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常。但是数据源仍有效
//        // 保留，并在下次调用getConnection()的时候继续尝试获取连接。如果设为true，那么在尝试
//        // 获取连接失败后该数据源将申明已断开并永久关闭。Default: false breakAfterAcquireFailure
//        ds.setBreakAfterAcquireFailure(false);
//
//        try {
//            // 初始化时获取三个连接，取值应在minPoolSize与maxPoolSize之间。Default: 3
//            // initialPoolSize
//        	ds.setInitialPoolSize(Integer.parseInt(dbProps.getProperty("c3p0.initialPoolSize").trim()));
//            // ds.setInitialPoolSize(3);
//            // 连接池中保留的最大连接数。Default: 15 maxPoolSize
//        	ds.setMaxPoolSize(Integer.parseInt(dbProps.getProperty("c3p0.maxPoolSize").trim()));
//            // ds.setMaxPoolSize(10);
//            // 连接池中保留的最小连接数。
//        	ds.setMinPoolSize(Integer.parseInt(dbProps.getProperty("c3p0.maxPoolSize").trim()));
//            // ds.setMinPoolSize(1);
//            // 当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 acquireIncrement
//        	ds.setAcquireIncrement(Integer.parseInt(dbProps.getProperty("c3p0.acquireIncrement").trim()));
//            // ds.setAcquireIncrement(1);
//            // 每60秒检查所有连接池中的空闲连接。Default: 0 idleConnectionTestPeriod
//        	ds.setIdleConnectionTestPeriod(Integer.parseInt(dbProps.getProperty("c3p0.idleConnectionTestPeriod").trim()));
//            // ds.setIdleConnectionTestPeriod(60);
//            // 最大空闲时间,25000秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0 maxIdleTime
//        	ds.setMaxIdleTime(Integer.parseInt(dbProps.getProperty("c3p0.maxIdleTime").trim()));
//            // ds.setMaxIdleTime(25000);
//            // 定义在从数据库获取新连接失败后重复尝试的次数。Default: 30 acquireRetryAttempts
//        	ds.setAcquireRetryAttempts(Integer.parseInt(dbProps.getProperty("c3p0.acquireRetryAttempts").trim()));
//            // ds.setAcquireRetryAttempts(30);
//            // 两次连接中间隔时间，单位毫秒。Default: 1000 acquireRetryDelay
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
		// 通过反射动态设定数据源属性
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