package org.kwong.bishe.common.util;


public class MD5CK {
	/**
	 * 构建一个单态模式,节省内存和CPU
	 */
	private static MD5CK md5 = null;

	public static MD5CK getInstance() {
		if (md5 == null) {
			md5 = new MD5CK();
		}
		return md5;
	}

	/**
	 * 能够接受中文的 MD5 编码
	 * 
	 * @param s
	 * @param charSet
	 * @return 小写的 32位编码
	 */
	public String getMD5ofStr(String s, String charSet) {
		return	MD5Util.md5String(s, charSet);
	}


}
