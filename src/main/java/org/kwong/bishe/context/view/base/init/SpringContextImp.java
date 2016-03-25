package org.kwong.bishe.context.view.base.init;

import javax.servlet.ServletContext;

import org.kwong.bishe.context.business.ISpringContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * 根据WEB容器的上下文，得到对应的spring 上下文
 * 目的是向业务逻辑层设置SpringUtil 上下文的环境，
 * 通过 XxtBusinessBootCfg类配置其环境
 * 
 * @author
 *
 */
public class SpringContextImp implements ISpringContext {

	/**
	 * @param  clazz  对应类名
	 * @param  name   Spring容器中对应Bean的名称  
	 * @return Bean 
	 * 
	 */
	
	static WebApplicationContext springContext;
	
	ServletContext servletContext;
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		this.servletContext = servletContext;
	}

	@SuppressWarnings("unchecked")
	public <T> T getSpringBean(Class<T> clazz, String name) {
		return (T)springContext.getBean(name);
	}
}
