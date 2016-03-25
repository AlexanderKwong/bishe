package org.kwong.bishe.common.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class Util {
	public static Integer pageSize=20;
	
	public static boolean strIsNull(String str){
		return str==null||str.equals("")||str.equals("null")?true:false;
	}
	
	public static String strIsEmpty(String str){
		return str==null||str.equals("")||str.equals("null")?"":str;
	}
	
	/**
	 * 转换数组为相隔字符串
	 * @param ids数组
	 * @param flag间隔符
	 * add by ygl
	 * @return
	 */
	public static String getStringByArray(String[] ids,String flag){
		String restr="";
		for(String id:ids){
			restr=restr+id+flag;
		}
		return restr.endsWith(flag)?restr.substring(0,restr.length()-flag.length()):restr;
	} 
	
	/**
	 * 获取用户IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
             ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
             ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
             ip = request.getRemoteAddr(); 
        } 
        String ips[]=ip.split(",");
        if(ip==null) return null;
        else return ips[0]; 
    } 
	
	
	
	/**
	 * 
	 * 方法:判断权限是否 需要更新
	 * 
	 * @param request
	 * @param roleId
	 * @return
	 *  
	 *    Add By Ethan Lam  At 2011-10-31
	 */
	public static boolean purIsOverTime(HttpServletRequest request,long roleId){
		ServletContext context = request.getSession().getServletContext();
		if(context.getAttribute("sch_pur_"+roleId)==null){
			return true;
		}else if(context.getAttribute("sch_pur_"+roleId+"_Time")!=null){
			String lastUpdateTime = (String) context.getAttribute("sch_pur_"+roleId+"_Time");
			if(System.currentTimeMillis()-Long.parseLong(lastUpdateTime)>=5*60*1000){
				context.setAttribute("sch_pur_"+roleId+"_Time",System.currentTimeMillis()+"");
				return true;
			}
			else
				return false;
		}
		context.setAttribute("sch_pur_"+roleId+"_Time",System.currentTimeMillis()+"");
		return true;
	}
	
	
	public static void main(String[] args){
		LoggerUtil.info("".split(",").length+":"+"".split(",")[0]);
	}
}
