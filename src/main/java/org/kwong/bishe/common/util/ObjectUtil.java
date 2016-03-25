package org.kwong.bishe.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 * Bean 工具类
 * @author Ethan.Lam  2011-7-29
 *
 */
public class ObjectUtil {

	
	 /**
	  * 
	  * 对象拷贝
	  * @param object
	  * @return
	  * @throws Exception
	  */
	 public static Object copy(Object object) throws Exception{
	        // 获得对象的类型
	        Class<?> classType = object.getClass();
//	        LoggerUtil.info("Class:" + classType.getName());
	        // 通过默认构造方法创建一个新的对象
	        Object objectCopy = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
	        // 获得对象的所有属性
	        Field fields[] = classType.getDeclaredFields();
	        for (int i = 0; i < fields.length; i++)
	        {
	            Field field = fields[i];
	            String fieldName = field.getName();
	            String firstLetter = fieldName.substring(0, 1).toUpperCase();
	            // 获得和属性对应的getXXX()方法的名字
	            String getMethodName = "get" + firstLetter + fieldName.substring(1);
	            // 获得和属性对应的setXXX()方法的名字
	            String setMethodName = "set" + firstLetter + fieldName.substring(1);
	            // 获得和属性对应的getXXX()方法
	            Method getMethod = classType.getMethod(getMethodName, new Class[] {});
	            // 获得和属性对应的setXXX()方法
	            Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });
	            // 调用原对象的getXXX()方法
	            Object value = getMethod.invoke(object, new Object[] {});
//	            LoggerUtil.info(fieldName + ":" + value);
	            // 调用拷贝对象的setXXX()方法
	            setMethod.invoke(objectCopy, new Object[] { value });
	        }
	        return objectCopy;
	    }
	
	    
	    /**
	     * 
	     * 复制对象的属性值到另外一个新的对象中
	     * 注意：只支持 普通的 属性  gettting 和 setting 方法属性
	     * 
	     * @param source   
	     * @param dest
	     * @return
	     * @throws Exception
	     * 
	     */
	    public static Object copyObjectVals(Object source,Class dest) throws Exception{
		    	Class sourceClass = source.getClass();
		        // 获得对象的类型
		        // 通过默认构造方法创建一个新的对象
		        Object objectCopy = dest.newInstance();
		        // 获得对象的所有属性
		        Field fields[] = dest.getDeclaredFields();
		        Field field = null;
		        Method getMethod,setMethod;
		        String firstLetter,fieldName,getMethodName,setMethodName="";
		        for (int i = 0; i < fields.length; i++){
		               try{
		        	       field = fields[i];
				           fieldName = field.getName();
				           firstLetter = fieldName.substring(0, 1).toUpperCase();
			        	   getMethodName = "get" + firstLetter + fieldName.substring(1);
			        	   getMethod = sourceClass.getMethod(getMethodName,new Class[]{});
			        	   setMethodName = "set" + firstLetter + fieldName.substring(1);
	                    // 获得和属性对应的setXXX()方法
				           setMethod = dest.getMethod(setMethodName, new Class[]{field.getType()});
				        // 调用原对象的getXXX()方法
				            Object value = getMethod.invoke(source, new Object[]{});
		//		            LoggerUtil.info(fieldName + ":" + value);
				        // 调用拷贝对象的setXXX()方法
				             setMethod.invoke(objectCopy, new Object[] { value });
		                }catch(Exception e){
		        	     e.printStackTrace();
		              }   
		        }
		        return objectCopy;
	    }
	    
	    
	    /**
	     * 
	     * 方法反射调用
	     * @param obj    对象
	     * @param methodName  方法名
	     * @param args    方法对应的参数
	     * @return        返回该方法调用后的 值
	     * @throws Exception
	     */
	    public static Object getValueByMethodName(Object obj,String methodName,Object...args) throws Exception{
	    	if(obj==null)
	    		return null;
	    	Class[] paramsClass = args!=null?new Class[args.length]:new Class[]{};
 	    	int index=0;
	    	if(args!=null){
                for(Object o: args){
//                	LoggerUtil.info(int.class==Integer.TYPE);
//                	LoggerUtil.info(int.class==Integer.class);
                	paramsClass[index] = o.getClass();
                	index++;
                }
	    	}
	    	Method method = obj.getClass().getMethod(methodName,paramsClass);
	    	if(method!=null)
	    	  return method.invoke(obj, args);
	    	return null;
	    }
	    
	    
	   public static void main(String...arg) throws Exception{
		   Test i = new Test();
		   int t = 1000;
		   getValueByMethodName(i,"setT",t);
	   }    
	    
}

class Test{
	
   public void setT(int tt){
	     System.out.print(tt);
   }	
	
	
}