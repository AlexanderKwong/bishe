package org.kwong.bishe.common.util;

import java.util.*;

/**
 * 系统常量设置
 * @author
 * @createTime
 *
 */
public class Const {

	static String AreaConfig = "zj";
	// 免费短信发送企业代码、业务代码
	static public final String free_sp_id_zj = "411293";
	static public final String free_tran_code_zj = "TZJ3910901";
	static public final String sevice_code_zj = "10657061";
	static public final String sevice_code_zjqw = "1065716140";// 浙江全网端口号
	static public final String http_address_zj = "www.zjxxt.com 服务电话：10086";
	static public final String parent_replay_source_zj = "";

	
	  /******关系******/
	public static final Map<String,String> Relation = new HashMap<String,String>();

	static {
	    Relation.put("1", "父亲");
	    Relation.put("2", "母亲");
	    Relation.put("3", "哥哥");
	    Relation.put("4", "弟弟");
	    Relation.put("5", "姐姐");
	    Relation.put("6", "妹妹");
	    Relation.put("7", "爷爷");
	    Relation.put("8", "奶奶");
	    Relation.put("9", "其他");
	    Relation.put("10", "监护人");
	}
	
	public static final Map<String,String> Nations = new HashMap<String,String>();
	static {
		    Nations.put("29", "柯尔克孜族");
		    Nations.put("39", "阿昌族");
		    Nations.put("49", "京族");
		    Nations.put("24", "高山族");
		    Nations.put("50", "塔塔尔族");
			Nations.put("34", "布朗族");
			Nations.put("52", "鄂伦春族");
			Nations.put("30", "土族");
			Nations.put("18", "傣族");
			Nations.put("20", "傈傈族");
			Nations.put("42", "怒族");
			Nations.put("25", "拉祜族");
			Nations.put("57", "中籍外国血统");
			Nations.put("32", "仫佬族");
			Nations.put("43", "乌孜别克族");
			Nations.put("35", "撒拉族");
			Nations.put("13", "瑶族");
			Nations.put("26", "东乡族");
			Nations.put("44", "俄罗斯族");
			Nations.put("9", "布依族");
			Nations.put("19", "黎族");
			Nations.put("51", "独龙族");
			Nations.put("28", "景颇族");
			Nations.put("3", "回族");
			Nations.put("41", "塔吉克族");
			Nations.put("27", "纳西族");
			Nations.put("17", "哈萨克族");
		    Nations.put("14", "土家族");
			Nations.put("1","汉族");
			Nations.put("6", "苗族");
			Nations.put("36", "毛难族");
			Nations.put("5", "维吾尔族");
			Nations.put("16", "白族");
			Nations.put("99", "");
			Nations.put("46", "崩龙族");
			Nations.put("37", "仡佬族");
			Nations.put("56", "基诺族");
			Nations.put("55", "珞巴族");
			Nations.put("33", "羌族");
			Nations.put("15", "哈尼族");
			Nations.put("11", "满族");
			Nations.put("4", "藏族");
			Nations.put("40", "普米族");
			Nations.put("12", "侗族");
			Nations.put("45", "鄂温克族");
			Nations.put("7", "彝族");
			Nations.put("2", "蒙古族");
			Nations.put("38", "锡伯族");
			Nations.put("53", "赫哲族");
			Nations.put("31", "达斡族");
			Nations.put("47", "安族");
			Nations.put("54", "门巴族");
			Nations.put("21", "佤族");
			Nations.put("48", "裕固族");
			Nations.put("10", "朝鲜族");
			Nations.put("22", "畲族");
			Nations.put("8", "壮族");
			Nations.put("98", "其他");
	}
	
}
