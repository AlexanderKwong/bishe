package org.kwong.bishe.common.util;

/**
 * 系统日志及控制台输出
 * @author
 *
 */
public class LogUtil {
	
	//写日志到文件
	public static void writeFileLog(OperatorType ot,String content){
		
		
	}
	
	//写日志到数据库
	public static void writeDbLog(OperatorType ot,String content){
 
	}
	
	public static void p(Object...args){
		String msg = "";
		for(Object arg:args){
			msg+= arg+" ";
		}
		LoggerUtil.info(msg);
	}
}
