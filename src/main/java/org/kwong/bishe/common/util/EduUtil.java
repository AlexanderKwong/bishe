package org.kwong.bishe.common.util;

/**
 * 学籍管理相关的工具类
 * @author
 *
 */
public class EduUtil {

	
	 /**
	   * 
	   * 学生学号   自动补对应的 字符位
	   * @param isLeft    是否从左边补位
	   * @param fillChar  自动填补的字符
	   * @param length    填补后的总字符串长度
	   * @param originalValue 原字符串 
	   * @return
	   */
	  public static String stuNoAutoFillString(boolean isLeft,char fillChar,int length,String originalValue){
		  return StringUtil.fillString(isLeft, fillChar, length, originalValue);
	  }
	  
	  
	  /**
	   * 学生学号  去除填充字符
	   * @param isLeft
	   * @param fillChar
	   * @param originalValue
	   * @return
	   */
	  public static String stuNoAutoRemoveFillChar(boolean isLeft,char fillChar,String originalValue){
		  return StringUtil.removeFillChar(isLeft, fillChar, originalValue);
	  }
	
	
}
