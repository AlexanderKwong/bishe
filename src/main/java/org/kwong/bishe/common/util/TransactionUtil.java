package org.kwong.bishe.common.util;

import java.util.Calendar;
import java.util.Date;

public class TransactionUtil {
	
	//获取业务处理随机序列号
	public static String getTranSerial() {
		String ser = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String yyyy = calendar.get(Calendar.YEAR) + "";
		String mm = (calendar.get(Calendar.MONTH) + 1) + "";
		if (mm.length() == 1) {
			mm = "0" + mm;
		}
		String dd = calendar.get(Calendar.DAY_OF_MONTH) + "";
		if (dd.length() == 1) {
			dd = "0" + dd;
		}
		String hh = calendar.get(Calendar.HOUR_OF_DAY) + "";
		String mi = calendar.get(Calendar.MINUTE) + "";
		String ss = calendar.get(Calendar.SECOND) + "";
		ser = yyyy + mm + dd + hh + mi + ss + (int) (Math.random() * 1000);
		return ser;
	}
}
