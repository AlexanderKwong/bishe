package org.kwong.bishe.common.util;

import org.apache.log4j.Logger;


/**
 * 
 * 日志工具
 * @author
 *
 */
public class LoggerUtil {

	public static Logger logger = Logger.getLogger(LoggerUtil.class);

	/**
	 * 普通日志
	 * @param message
	 */
	public static void info(String message) {
		logger.info(message);
	}

	/**
	 * 警告日志
	 * @param message
	 */
	public static void warn(String message) {
		logger.warn(message) ;		
	}
	
	/**
	 * 错误日志
	 * @param message
	 * @param t
	 */
	public static void error(String message, Throwable t) {
		logger.error(message, t);
	}

	/**
	 * 
	 * 方法：
	 * 
	 * @param message
	 *  
	 *    Add By Ethan Lam  At 2011-10-12
	 */
	public static void debug(String message) {
		logger.debug(message);
	}
}
