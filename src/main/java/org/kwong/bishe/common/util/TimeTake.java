package org.kwong.bishe.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能：获取当前时间——星期一跟星期天的日期
 */
public class TimeTake {

	public static void main(String[] srgs){
		TimeTake.getTime();
	}
	
	@SuppressWarnings("static-access")
	public static String[] getTime(){
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat Fm = new SimpleDateFormat("E"); 
		String time = dateFm.format(new Date());  //当前时间
		String d = Fm.format(new Date()); //星期几
		String[] timeArr = time.split("-");
		int yyy = Integer.parseInt(timeArr[0]);  //取得当前时间的年
		int MMM = Integer.parseInt(timeArr[1]);  //取得当前时间的月
		int ddd = Integer.parseInt(timeArr[2]);  //取得当前时间的天
		TimeTake t = new TimeTake();
		int[] timeArray = new int[6];
		if ("星期一".equals(d) || "星期一" == d) {
			//返回年份、月份跟星期一或星期天的日期
			timeArray = t.tianShu(ddd, MMM, 1, yyy, 0, 6);
		}else if("星期二".equals(d) || "星期二" == d){
			timeArray = t.tianShu(ddd, MMM, 2, yyy, 1, 5);
		}else if("星期三".equals(d) || "星期三" == d){
			timeArray = t.tianShu(ddd, MMM, 3, yyy, 2, 4);
		}else if("星期四".equals(d) || "星期四" == d){
			timeArray = t.tianShu(ddd, MMM, 4, yyy, 3, 3);
		}else if("星期五".equals(d) || "星期五" == d){
			timeArray = t.tianShu(ddd, MMM, 5, yyy, 4, 2);
		}else if("星期六".equals(d) || "星期六" == d){
			timeArray = t.tianShu(ddd, MMM, 6, yyy, 5, 1);
		}else if("星期天".equals(d) || "星期天" == d ||"星期日".equals(d) || "星期日" == d){
			timeArray = t.tianShu(ddd, MMM, 7, yyy, 6, 0);
		}
		else{
			LoggerUtil.info("黑色星期八");
			return null;
		}
		return new String[]{timeArray[0]+"-"+timeArray[1]+"-"+timeArray[4] , timeArray[2]+"-"+timeArray[3]+"-"+timeArray[5]};
	}


	/*
	 * 判断当前月份是大月还是小月
	 * 返回月份共有多少天
	 */
	public static int panDuanDaXiao(int MM,int yyy){
		//如果是二月份
		if(MM == 2){
			//如果是闰年
			if( yyy % 4 == 0 && yyy % 100 != 0 || yyy % 400 == 0 )
			{
				return 29;
			}
			else
			{
				return 28;
			} 
		}
		//如果是小于七月份的单月
		if(MM <= 7 && MM % 2 != 0){
			return 31;
		}
		//如果是小于七月份的双月、二月份除外
		if(MM < 7 && MM % 2 == 0){
			return 30 ;
		}

		//如果是大于等于8月份的单月
		if(MM >= 8 && MM % 2 != 0){
			return 30;
		}
		//如果是大于等于8月份的双月
		if(MM >= 8 && MM % 2 == 0){
			return 31;
		}
		return 0;
	}

	/*
	 * 计算星期天跟星期一的天数
	 * 返回年份、月份(星期一/星期天)跟星期一或星期天的日期
	 * 参数dd为当前天数,MM为当前月数、ee为当前星期数,yyy为当前年数,can1为距离星期一的天数,can1为距离星期天的天数
	 * 其中can1跟can2最为重要
	 */
	@SuppressWarnings("static-access")
	public static int[] tianShu(int dd, int MM ,int ee,int yyy,int can1,int can2){

		int xiYi = 0 ;  //星期一的日期天数
		int xiTi = 0;  //星期天的日期天数
		TimeTake t = new TimeTake();
		int mmNum = t.panDuanDaXiao(MM, yyy);  //取得月份天数
		int mmNum1 = t.panDuanDaXiao(MM - 1, yyy);  //取得上一个月的月份天数

		int yiMM = MM ;  //星期一的月份
		int tiMM = MM;  //星期天的月份
		
		int yiYYY = yyy;  //星期一的年份
		int tiYYY = yyy;  //星期天的年份
		
		
		//如果当前天数减去星期数大于等于0、不用返回上一个月计算
		if( dd - ee >= 0 ){
			if(1 == ee){
				xiYi = dd ;
			}else{
				xiYi = dd - can1 ;  //星期一的日期天数
			}
		}else{
			//如果当前月份不等于1月
			if(yiMM > 1 ){
				yiMM = yiMM - 1 ; //当前月份减一
			}else{
				yiMM = 12 ;
				yiYYY = yiYYY - 1 ;
			}
			xiYi = mmNum1 + dd - ee ; //星期一的日期天数
		}
		
		
		//如果星期天的日期天数小于等于月份天数
		if(7 == ee){
			xiTi = dd ;
		}else{
			xiTi = dd  + can2 ;
		}
		//如果星期天的日期小于等于当前月份日期
		if(xiTi <= mmNum){
			return new int [] {yiYYY,yiMM,tiYYY,tiMM,xiYi,xiTi};
		}else{
			xiTi = xiTi - mmNum ; //星期天的日期天数
			//如果当前月份不等于12月
			if(tiMM < 12 ){
				tiMM = tiMM + 1 ; //当前月份加一
			}else{
				tiMM = 1 ;
				tiYYY = tiYYY + 1 ;
			}
			return new int[]{yiYYY,yiMM,tiYYY,tiMM, xiYi , xiTi};
		}

	}
}
