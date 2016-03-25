package org.kwong.bishe.common.util;

import java.util.HashMap;

import net.sf.json.JSONObject;

/**
 * 生成json返回结果
 * @author
 */
public class ReturnMessage {
	private boolean success;
	private int returncode;
	private String returnmsg;
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getReturncode() {
		return returncode;
	}
	public void setReturncode(int returncode) {
		this.returncode = returncode;
	}
	public String getReturnmsg() {
		return returnmsg;
	}
	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}
	
	public String getJSONObjectToString(){
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("success", getSuccess());
		map.put("returncode", getReturncode());
		map.put("returnmsg", getReturnmsg());
		
		return JSONObject.fromObject(map).toString();
	}
}
