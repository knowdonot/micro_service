package com.duo.common.util;

																																																										import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;


public class ContractController  {

	
	private static final String MZSM ="MZSM.cpt";
	private static final String URL_CPT = "http://10.167.210.64:9080/WebReport/ReportServer?reportlet=DOC/01HTL/JKHT/1.0DJR/";
	
	
	public static void main(String[] args) {
		createContract();
		
	}
	
	
	/**
	 * 查看合同
	 * 2017年9月8日
	 * By duoduo
	 * @param request
	 * @param response
	 * @param docId
	 * @param model
	 */
	public void showImg(HttpServletRequest request, HttpServletResponse response,String docId,Model model){
		try {
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/*");
			String dirPath = "D:"+File.separator+docId;
			String tifPath = dirPath.substring(0, dirPath.length()-1)+File.separator+docId;
			String pdfPath = tifPath+".tif";
            response.setContentType("image/*");
            BufferedImage output = ImageIO.read(new File(pdfPath));
            OutputStream out = response.getOutputStream();
    		ImageIO.write(output, "TIF", out);
    		out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生产合同
	 * 2017年9月8日
	 * By duoduo
	 */
	public static void createContract(){
		String locan_code = "JK2017051700000002";
		String savePath ="D:";
		String wordUrl1 = getContractURL(MZSM,locan_code,ContractConstant.WORD);
		// 拼接URL
		URL wUrl1;
		try {
			wUrl1 = new URL(wordUrl1);
			URLConnection wordUrlcon1 = wUrl1.openConnection();
			// 获取连接
			wordUrlcon1.connect();
			// 获取流
			InputStream in = wordUrlcon1.getInputStream();
			String fileName1 = "免责声明_" + ContractConstant.dateToString(new Date(), "yyyyMMddhhmmss") + ".doc";
			byte temp[] = new byte[1024];
			
			OutputStream os = new FileOutputStream(savePath+File.separatorChar+fileName1);
            int size = 0;
            while ((size = in.read(temp)) != -1) { // 每次读取1KB，直至读完
                os.write(temp, 0, size);
            }
            in.close();
            os.close();
            
            
//			
			//9.借款人委托扣款授权书
			String wordUrl9 = getContractURL(MZSM,locan_code,ContractConstant.PDF);
			// 拼接URL
			URL wUrl9 = new URL(wordUrl9);
			URLConnection wordUrlcon9 = wUrl9.openConnection();
			// 获取连接
			wordUrlcon9.connect();
			// 获取流
			InputStream in2 = wordUrlcon9.getInputStream();
			String fileName2 = "免责声明_" + ContractConstant.dateToString(new Date(), "yyyyMMddhhmmss") + ".pdf";
			
			OutputStream os2 = new FileOutputStream(savePath+File.separatorChar+fileName2);
			
			while ((size = in2.read(temp))!=-1) {
				os2.write(temp, 0, size);
			}
			os2.close();
			in2.close();
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	 private static String getContractURL(String rptFileName,String enloanCode ,String  type) {
	   	   String baseUrl = URL_CPT+"{0}&{1}&format={2}";
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
	
	
}
