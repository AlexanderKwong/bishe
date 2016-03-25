package org.kwong.bishe.common.util;

/**
 * 截取字符串并返回指定长度的字符
 * @author Administrator
 *
 */
public class SubstringDisplay {
	public static String pw="#@!^&%$)";
	/**
	 * 截取字符
	 * @param value
	 * @param lenght
	 * @return
	 */
	public  String SubString(String value, int lenght)
	{
		if(value == null)
			return null;
		
		int ilenght = lenght;
		int i = value.getBytes().length;
		int j = value.length();
		
		if(i == j)
			ilenght = ilenght + 8;
			
		return this.sub(value, ilenght);
	}
	
	public  String SubString(String value)
	{
		if(value == null)
			return null;
		
		int ilenght = 0;
		int i = value.getBytes().length;
		int j = value.length();
		
		if(i == j){
			ilenght = 20;
		}
		else{
			ilenght = 11;
		}
			
		return this.sub(value, ilenght);
	}
	
	private String sub(String value, int lenght)
	{
		if (value != null && value.length() > lenght)
		{
			return value.substring(0, lenght) + "...";
		}
		else
		{
			return value;
		}		
	}
/**
 * 按规则替换字符串
 * @param value 
 * @param style
 * @return
 */
	public String strReplace(String value,String style){
		if(!StringUtil.isNulOrBlank(value)){
			return value.replace(style, "");
		}
		return value;
	}
	/**
	 * 试题处理“”
	 * @param value
	 * @return
	 */
	public String strReplace(String value){
		if(!StringUtil.isNulOrBlank(value)){
			return value.replace("\\\"", "\"").replace("<p>", "").replace("</p>", "").trim();
		}
		return value;
	}
	/**
	 * 
	 * @param str 原字符串
	 * @param str1 被替换的字符
	 * @param str2 替换的字符
	 * @return
	 */
	public String strReplace(String str,String str1,String str2) {
		if (str == null)
			return null;
		
		return str.replace(str1, str2);
	}
	/**
	 * 加密url
	 * @param url
	 * @return
	 */
	public static String en(String url){
		return CryptUtils.encrypt(url, pw);
	} 
	/**
	 * 教师端解密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String teade(String data) throws Exception{
		String url=CryptUtils.decrypt(data, pw);
		if(url.contains(",")){
			url=url.replace(",", "?token=");
		}
		return url;
	}
	/**
	 * 直播获取主讲人名与id
	 * @param name  0 id  1 名
	 * @return 
	 */
	public static String getHostName(String name,int num){
		if(!StringUtil.isNulOrBlank(name)){
			if(name.contains("_")){
				String str[]=name.split("_");
				return str[num];
			}
		}
		return "";
	}
	
	/**一对一的名字分列
	 * @param string
	 * @param n
	 * @return
	 */
	public static String o2oName(String string,int n){
		//六年级语文/数学 一对一辅导课程
		if(!StringUtil.isNulOrBlank(string)){
			String strs[]=string.split(" ");
			if(strs.length>0){
				String gradesub=strs[0];
				if(gradesub.contains("年级")){
					if(n==0){
						string=gradesub.substring(0, 3);
					}else if(n==1){
						string=gradesub.substring(3,string.indexOf(" ")).replace("/", "，");
					}
				}else{
					if(n==0){
						string=gradesub.substring(0, 2);
					}else if(n==1){
						string=gradesub.substring(2,string.indexOf(" ")).replace("/", "，");
					}
				}
			}
		}
		return string;
	}
	
}
