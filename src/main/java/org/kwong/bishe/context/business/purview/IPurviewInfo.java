package org.kwong.bishe.context.business.purview;

import java.util.List;


/**
 * 前台用户权限接口
 * @author
 *
 */
public  abstract class IPurviewInfo {
	public abstract int getRoleId();
	public abstract List getPurviewList(); 
	public abstract String getRoleName();
	public abstract int getRoleType();
	public boolean havePurview(String functionFlag,PurviewType pur) {
		
		return true;
	}
}
