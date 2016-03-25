package org.kwong.bishe.common.util;

import java.text.DecimalFormat;

/**
 * 数值类型处理
 * @author
 *
 */
public class NumericalUtil {

	static DecimalFormat df2  = new DecimalFormat("#########0.00");
	
	public static double format(double number){
		return Double.parseDouble(df2.format(number));
		//return Float.parseFloat(df2.format(number));
	}
	public static String formatStr(double number){
		return df2.format(number);
	}
	
	public String formatStr(String number){
		return df2.format(Double.parseDouble(number));
	}			
	
	public String formatStrNotStatic(double number){
		return df2.format(number);
	}		
	
	public String formatStrNotStatic(String number){
		return df2.format(Double.parseDouble(number));
	}	
	//计算百份比
	public static double getParentage(String num){
//			LoggerUtil.info("num1  "+num);
		String nums[]=num.split(",");
		try {
			long n1=Long.parseLong(nums[0]);
			long n2=Long.parseLong(nums[1]);
			if(n1>n2){
				return 100;
			}
			if(n2>0){				
				return NumericalUtil.format(Arith.div(n1, n2,8) *  100);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return 0;
		
	}
	
	public static void main(String[] args) {
		long n1=146;
		long n2=147;
		LoggerUtil.logger.info(Arith.div(n1, n2,8));
		
		LoggerUtil.info("2: "+Arith.mul(Arith.div(n1, n2,8), 100, 2));
	}
}
