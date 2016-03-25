package org.kwong.bishe.context.view.base.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.kwong.bishe.context.business.XxtBusinessBootCfg;
import org.kwong.bishe.common.util.LoggerUtil;

public class XxtContextListen implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		LoggerUtil.info("XxtContextListen.contextDestroyed()..");
	}
	 
    //初始化系统参数
	public void contextInitialized(ServletContextEvent contextEvent) {		
		LoggerUtil.info("程序正执行XxtContextListen.contextInitialized()..");		
		//得到权限方式对应的类名
		String purviewclass=contextEvent.getServletContext().getInitParameter("purviewclass");
		String springUtilHandlerClass=contextEvent.getServletContext().getInitParameter("springUtilHandlerClass");
		//WebAppDir
		String webappHome = contextEvent.getServletContext().getRealPath("/");
		//设置此系统变量到系统中，供log4j 使用，
		System.setProperty("XXT_WEBAPP_HOME",webappHome);
		String logCfgPath = webappHome+contextEvent.getServletContext().getInitParameter("log4j");
		try {
			
			//构造权限获取方式
			XxtBusinessBootCfg.setupPurviewHandler(purviewclass, logCfgPath);
			LoggerUtil.info("系统初始化，设置WEB应用路径: "+webappHome);
			LoggerUtil.info("已经配置业务逻辑模块....");
			
			//初始化此工具，能访问Spring容器中的bean
			SpringContextImp springContext=(SpringContextImp)Class.forName(springUtilHandlerClass).newInstance();
			springContext.setServletContext(contextEvent.getServletContext());
			XxtBusinessBootCfg.setupSpringUtilHandler(springContext.getClass());
			
			LoggerUtil.info("web已经缓存全部角色权限....");
		    
		} catch (Exception e) {
			LoggerUtil.error("系统初始化过程中出现异常：[XxtContextListen.contextInitialized() ] ",e);
			e.printStackTrace();
		}
	}

}
