package org.kwong.bishe.common.util;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;

public class DESUtil{

	public static  String DecryptDoNet(String message, String key) throws Exception {   
		  BASE64Decoder base64Decoder = new BASE64Decoder();
	      byte[] bytesrc=base64Decoder.decodeBuffer(message);
	      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");   
	      DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("GB2312"));   
	      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
	      SecretKey secretKey = keyFactory.generateSecret(desKeySpec);   
	      IvParameterSpec iv = new IvParameterSpec(key.getBytes("GB2312"));   
	      cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);   
	      byte[] retByte = cipher.doFinal(bytesrc);   
	      
	      return new String(retByte);   
	}   
	 /**

     * 解密

     * @param src 数据源

     * @param key 密钥，长度必须是8的倍数

     * @return   返回解密后的原始数据

     * @throws Exception

     */

      private  byte[] decrypt(byte[] src, byte[] key)throws Exception {

        // DES算法要求有一个可信任的随机数源

        SecureRandom sr = new SecureRandom();

        // 从原始密匙数据创建一个DESKeySpec对象

        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成

        // 一个SecretKey对象

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作

        Cipher cipher = Cipher.getInstance("DES");

        // 用密匙初始化Cipher对象

        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        // 现在，获取数据并解密

        // 正式执行解密操作

        return cipher.doFinal(src);

     }

}