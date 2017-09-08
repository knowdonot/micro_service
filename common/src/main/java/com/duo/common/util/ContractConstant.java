package com.duo.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * 合同生成获得地址的方法
 * @Class Name carContractUtils
 * @author 
 * @Create In 2016年4月1日
 */
public class ContractConstant {
	//合同地址gps
	public static final String CLJYSQWTS_GPS = "CLJYSQWTS_GPS.cpt";
	public static final String HKGLFWSMS_GPS = "HKGLFWSMS_GPS.cpt";
	public static final String JKRWTKKSQS_GPS = "JKRWTKKSQS_GPS.cpt";
	public static final String JKXY_GPS = "JKXY_GPS.cpt";
	public static final String ST_GPS = "ST_GPS.cpt";
	public static final String XYZXJGLFWXY_GPS = "XYZXJGLFWXY_GPS.cpt";
	//合同地址移交
	public static final String CLJYSQWTS_YJ = "CLJYSQWTS_YJ.cpt";
	public static final String HKGLFWSMS_YJ = "HKGLFWSMS_YJ.cpt";
	public static final String JKRWTKKSQS_YJ = "JKRWTKKSQS_YJ.cpt";
	public static final String JKXY_YJ = "JKXY_YJ.cpt";
	public static final String ST_YJ = "ST_YJ.cpt";
	public static final String XYZXJGLFWXY_YJ = "XYZXJGLFWXY_YJ.cpt";
	//合同地址质押
	public static final String CLJYSQWTS_ZY = "CLJYSQWTS_ZY.cpt";
	public static final String HKGLFWSMS_ZY = "HKGLFWSMS_ZY.cpt";
	public static final String JKRWTKKSQS_ZY = "JKRWTKKSQS_ZY.cpt";
	public static final String JKXY_ZY = "JKXY_ZY.cpt";
	public static final String ST_ZY = "ST_YJ.cpt";
	public static final String XYZXJGLFWXY_ZY = "XYZXJGLFWXY_ZY.cpt";
	public static final String ESCMMHT_ZY = "ESCMMHT_ZY.cpt";
	public static final String JDCZYHT_ZY = "JDCZYHT_ZY.cpt";
	public static final String MFSM_CJ = "MZSM.cpt";//车借免责声明
	
	// 抵押物清单
	public static final String DYWQD_GPS_YJ = "DYWQD.cpt";
	//质押物清单
	public static final String ZYWQD_ZY = "ZYWQD_ZY.cpt";
	//............................展期合同........................................//
	public static final String CJZQXY_GPSYJ = "CJZQXY_GPSYJ.cpt";    //展期协议
	public static final String HKGLFWSMS_GPSYJ = "HKGLFWSMS_GPSYJ.cpt";  //还款管理服务说明书
	public static final String JKRWTKKSQS_GPSYJ = "JKRWTKKSQS_GPSYJ.cpt";      //委托扣款授权书
	public static final String MFSM_ZQ = "MASMZQ.cpt";//车借展期免责声明
	public static final String CJZQXY_ZY = "CJZQXY_ZY.cpt";    //展期协议
	public static final String ZQHKGLFWSMS_ZY = "HKGLFWSMSZQ_ZY.cpt";  //还款管理服务说明书
	public static final String ZQJKRWTKKSQS_ZY= "JKRWTKKSQSZQ_ZY.cpt";      //委托扣款授权书
	
	
	public static final String TBSM = "TBSM.cpt";      //特别说明
	public static final String TBSM_ZQ = "TBSM_ZQ.cpt";      //展期特别说明
	
	//导出的类型
	public static final String PDF = "pdf";
	public static final String WORD = "word";
	/**
	 * 获取String类型的日期。
	 * @param date 时间
	 * @param patten date的格式
	 * @return
	 */
	public static String dateToString(Date date, String patten) {
		DateFormat df = new SimpleDateFormat(patten);
		return df.format(date);
	}
	
	
	 /**
	    * 车借借款URL取得处理    word
	    * @param columnName 字段名字
	    * @return URL
	    */
	   public static String getLoanCarAllURL(String rptFileName,String enloanCode ,String  type) {
		   
		   String url = "http://10.167.210.64:9080/WebReport/ReportServer?reportlet=DOC/01HTL/JKHT/1.0DJR/";
	   	   String baseUrl = url+"{0}&{1}&format={2}";
	   	   baseUrl = baseUrl.replace("{0}", rptFileName);
	 	   try {
	 		  enloanCode = URLEncoder.encode(enloanCode, "UTF8");
	 	   } catch (UnsupportedEncodingException e) {
	 		   return "";
	 	   }
	 	  baseUrl = baseUrl.replace("{1}", "loan_code=" + enloanCode);
	 	  baseUrl = baseUrl.replace("{2}", type);
	 	   return baseUrl;
	   }
	   /**
		 * 从property文件中取得特定项目的值
		 * @param path 配置文件的名称
		 * @param name 项目名称
		 * @return 项目值
		 */
	   public static String getPropertyByName(String path, String name) {
	       String result = "";
	       try {
	           result = ResourceBundle.getBundle(path).getString(name);
	       } catch (Exception e) {
	    	   System.out.println(e.getMessage());
	           return null;
	       }
	       return result;
	   }

}
