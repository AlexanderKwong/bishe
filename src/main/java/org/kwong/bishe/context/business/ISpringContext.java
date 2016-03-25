package org.kwong.bishe.context.business;

/**
 * 
 * Spring 容器接口方法
 * @auth
 *
 */
public interface ISpringContext {
	<T> T getSpringBean(Class<T> clazz, String name);
}
