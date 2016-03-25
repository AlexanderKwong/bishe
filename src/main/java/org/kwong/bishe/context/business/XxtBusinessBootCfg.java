package org.kwong.bishe.context.business;


/**
 * 
 * 业务逻辑层环境的上下文配置
 * @author
 *
 */
public class XxtBusinessBootCfg {

	 /**
	  * 装载SpringUtil
	  * @param handlerClazz
	  * @throws Exception
	  */
     public static void setupSpringUtilHandler(Class handlerClazz) throws Exception{
    	 ISpringContext context = (ISpringContext)handlerClazz.newInstance();//
    	 SpringUtil.initSprintUtil(context);
     }

     /**
      * 装载SpringUtil
      * @param handlerClazzName
      * @throws Exception
      */
     public static void setupSpringUtilHandler(String handlerClazzName) throws Exception{
    	 Class classinfo=Class.forName(handlerClazzName);
    	 ISpringContext context = (ISpringContext)classinfo.newInstance();//
    	 SpringUtil.initSprintUtil(context);
     }
	
     
     /**
      * 
      * 初始化 权限控制器   以及  日志配置文件
      * @param purviewclass 权限实现类  
      * @param logCfgPath   日志配置文件
      * @throws Exception
      */
     public static void setupPurviewHandler(String purviewclass,String logCfgPath) throws Exception{
    	 XxtFactory.init(purviewclass, logCfgPath);
     }
	
	
	
}
