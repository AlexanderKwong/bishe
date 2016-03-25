package org.kwong.bishe.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


public class JsonObjectSerialize {

    /**
	 * 由字符串反序列化成实体类  针对的是一个实体，此实体中的属性不包括自定义的类型，如Teacher类型，或者List<Teacher>类型 
	 * @param source 传入json中的字符串
	 * @param beanClass 实体类的类型
	 * @return 实体类
	 */
	public static Object getObjFromJsonArrStr(String source,Class beanClass)
	{
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(source);
		return  JSONObject.toBean(jsonObject,beanClass);		
	}
	/**
	 * 由字符串反序列化成实体类  针对的是一个实体，此实体中的属性包括自定义的类型，如Teacher类型，或者List<Teacher>类型
	 * @param jsonArrStr
	 * @param clazz
	 * @param classMap
	 * @return
	 */
	public static Object getObjFromJsonArrStr(String jsonArrStr, Class clazz, Map classMap) 
	{  
		JSONObject jsonObj = JSONObject.fromObject(jsonArrStr);  
                return JSONObject.toBean(jsonObj, clazz, classMap);            
	}

	
	/**
	 * 将string转换成listBean
	 * @param jsonArrStr 需要反序列化的字符串
	 * @param clazz 被反序列化之后的类
	 * @return 实体list
	 */
	@SuppressWarnings("unchecked")
	public static List getListFromJsonArrStr(String jsonArrStr, Class clazz) {  
		List list = new ArrayList();  
		try{
	        JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);  
	        
	        for (int i = 0; i < jsonArr.length(); i++) 
	        {  
	            list.add(JSONObject.toBean(jsonArr.getJSONObject(i), clazz));  
	        }
		}catch(Exception ex){
			ex.printStackTrace();	
		}
		 return list;  
    } 

	/**
	 * 将string转换成listBean 属性中包含实体类等 如List<Student> 而Student中含有属性List<Teacher>
	 * @param jsonArrStr 需要反序列化的字符串
	 * @param clazz 反序列化后的类
	 * @param classMap 将属性中包含的如Teacher加入到一个Map中，格式如map.put("teacher",Teacher.class)
	 * @return 反序列化后的字符串
	 * 使用示例：
		Map classMap = new HashMap();
		//必须要对Parent进行初始化 否则不识别
		Teacher p = new Teacher();
		classMap.put("teacher", p.getClass());
		List mlist = JSONTransfer.getListFromJsonArrStr(resultStr, Student.class, classMap);
	 */
	@SuppressWarnings("unchecked")
	public static List getListFromJsonArrStr(String jsonArrStr, Class clazz, Map classMap) 
	{  
       JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);  
       List list = new ArrayList();  
       for (int i = 0; i < jsonArr.length(); i++) 
       {      	   
           list.add(JSONObject.toBean(jsonArr.getJSONObject(i), clazz, classMap));  
       }  
       return list;  
	}
		
	/**
	 * 序列化操作，无论是单个的对象，还是list，抑或是list中的属性仍包含list，都可以直接序列化成String类型
	 * @param obj 需要被序列化的对象
	 * @return 序列化之后的字符串
	 */
	@SuppressWarnings("unchecked")
	public static String getJsonArrStrFromList(Object obj) 
	{ 
		//返回結果
		String jsonStr = null;  
	    //判空
        if (obj == null) {  
            return "{}";  
        }  
       /* //Json配置      
        JsonConfig jsonCfg = new JsonConfig();  
  
        //注册日期处理器  
        jsonCfg.registerjsonv.registerJsonValueProcessor(java.util.Date.class,  
                new JsonDateValueProcessor(SystemConstants.DateFormat)); */ 
        
        //判断是否是list
        if (obj instanceof Collection || obj instanceof Object[]) {  
            //jsonStr = JSONArray.fromObject(obj, jsonCfg).toString();  
        	jsonStr = JSONArray.fromObject(obj).toString(); 
        } else {  
            //jsonStr = JSONObject.fromObject(obj, jsonCfg).toString();  
        	jsonStr = JSONObject.fromObject(obj).toString();  
        }  	  
        return jsonStr; 
    } 
}
