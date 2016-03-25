package org.kwong.bishe.context.view.base.init;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.kwong.bishe.context.business.XxtBusinessBootCfg;
import org.kwong.bishe.common.util.LoggerUtil;

public class SpringContextListen implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		LoggerUtil.info("SpringContextListen.contextDestroyed()..");
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			try {
				DriverManager.deregisterDriver(drivers.nextElement());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 初始化系统参数
	public void contextInitialized(ServletContextEvent contextEvent) {
		LoggerUtil.info("程序正执行SpringContextListen.contextInitialized()..");

		String springUtilHandlerClass = contextEvent.getServletContext().getInitParameter("springUtilHandlerClass");
		// WebAppDir
		String webappHome = contextEvent.getServletContext().getRealPath("/");
		// 设置此系统变量到系统中，供log4j 使用，
		System.setProperty("WEBAPP_HOME",webappHome);
		String logCfgPath = webappHome + contextEvent.getServletContext().getInitParameter("log4j");
		try {
			// 构造权限获取方式
			XxtBusinessBootCfg.setupPurviewHandler(null, logCfgPath);
			LoggerUtil.info("系统初始化，设置WEB应用路径WEBAPP_HOME: " + webappHome);
			LoggerUtil.info("已经配置业务逻辑模块....");

			// 初始化此工具，能访问Spring容器中的bean
			SpringContextImp springContext = (SpringContextImp) Class.forName(springUtilHandlerClass).newInstance();
			springContext.setServletContext(contextEvent.getServletContext());
			XxtBusinessBootCfg.setupSpringUtilHandler(springContext.getClass());
		} catch (Exception e) {
			LoggerUtil.error("系统初始化过程中出现异常：[SpringContextListen.contextInitialized() ] ", e);
			e.printStackTrace();
		}
	}

}
