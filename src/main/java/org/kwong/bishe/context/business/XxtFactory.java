package org.kwong.bishe.context.business;

import org.kwong.bishe.context.business.purview.IPurviewInfo;
import org.kwong.bishe.common.util.LoggerUtil;
import org.kwong.bishe.common.util.StringUtil;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;


public class XxtFactory {
	
    private static IPurviewInfo purview;

	public static IPurviewInfo getPurview() {
		return purview;
	}

	@SuppressWarnings("rawtypes")
	public static void init(String purviewclass,String lo4j) throws Exception{
		LoggerUtil.info("log4j已经配置...");
		if(lo4j.endsWith(".xml"))
			DOMConfigurator.configure(lo4j);//初始化log4j
		else if(lo4j.endsWith(".properties"))
			PropertyConfigurator.configure(lo4j);
		if(!StringUtil.isNulOrBlank(purviewclass))
		{
			Class classinfo=Class.forName(purviewclass);
			purview=(IPurviewInfo)classinfo.newInstance();//生成权限对象
		}
	
	}
}
