package org.kwong.bishe.common.util;

/**
 * 
 * 专用于视频url路径的字符串加密（不定长度）
 * @author
 *
 */
public class VideoUrlStrEncryptUtil {
	
	public static int level = 2;
	
	
	public static void main(String[] args) { 
		    String s = "http://v001.52ku.com/vod3gp/vodlink/010b3d9ddcb6d06b47af7c34064fcdd0/5306b203/course/cp2/cs20298/video/20176_1.mp4"; 
		    //String s = "王志军";
			String enStr = encrypt(s);
			System.out.println("加密前："+s);
			System.out.println("加密后："+enStr);
			System.out.println("解密后: "+decrypt(enStr));
			byte tt = 16;
			System.out.println(Integer.toHexString(tt & 0XFF ^ level));
			System.out.println(Integer.toHexString(tt));
	} 

	
	public static String encrypt(String str){
		 return byte2hex(str.getBytes());
	}
	
	public static String decrypt(String encrypt){
		byte[] b = hex2byte(encrypt.getBytes());
		return new String(b);
	}
	
    
	static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException("des conver error!");
		}

		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) (Integer.parseInt(item, 16) ^ level);
		}
		return b2;

	}

	// 字节数组转换成十六进制字符串
	static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (byte bt : b) {
			stmp = (Integer.toHexString(bt & 0XFF ^ level));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	
//AS	
//	private function hex2byte(str:String):ByteArray{
//	    var tep:ByteArray = new ByteArray();
//		var lenght:int = str.length;
//		for(var i:int=0;i<lenght;i+=2){
//		   var s:String =  str.substr(i,2);
//		   //trace("f:"+s);
//		   tep[i/2] = parseInt(s,16)^2;
//		  // trace(parseInt(s,16));
//		}
//	    return tep;
//	}
	
	
	
}
