package com.duo.ui.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("look")
public class PreOffice {

	// @RequestMapping(value = "/world")
	// @ResponseBody
	// public String lookWorld(HttpServletResponse response,String filepath){
	//
	// System.out.println("-----");
	// BufferedInputStream bis = null;
	// URL url = null;
	// HttpURLConnection httpUrl = null; // 建立链接
	// url = new URL(filepath);
	// httpUrl = (HttpURLConnection) url.openConnection();// 连接指定的资源
	// httpUrl.connect();// 获取网络输入流
	// bis = new BufferedInputStream(httpUrl.getInputStream());
	//
	// String bodyText = null;
	// WordExtractor ex = new WordExtractor(bis);
	// bodyText = ex.getText();
	// response.getWriter().write(bodyText);
	// return "ok";
	// }

//	@RequestMapping(value = "/ppt")
//	@ResponseBody
//	public String lookPPT(HttpServletResponse response, String filepath) {
//
//		BufferedInputStream bis = null;
//		URL url = null;
//		HttpURLConnection httpUrl = null; // 建立链接
//		url = new URL(filepath);
//		httpUrl = (HttpURLConnection) url.openConnection();// 连接指定的资源
//		httpUrl.connect();// 获取网络输入流
//		bis = new BufferedInputStream(httpUrl.getInputStream());
//
//		StringBuffer content = new StringBuffer("");
//		SlideShow ss = new SlideShow(new HSLFSlideShow(bis));
//		Slide[] slides = ss.getSlides();
//		for (int i = 0; i < slides.length; i++) {
//			TextRun[] t = slides[i].getTextRuns();
//			for (int j = 0; j < t.length; j++) {
//				content.append(t[j].getText());
//			}
//			content.append(slides[i].getTitle());
//		}
//		response.getWriter().write(content.toString());
//		return "ppt";
//	}

	@RequestMapping(value = "/excel")
	@ResponseBody
	public String lookExcel(HttpServletResponse response, String filePath) {
//		filePath = "D:"+File.separatorChar+"file"+File.separatorChar+"1.xls";

		//http://localhost:8766/look/excel?filepath=111
		filePath = "http://localhost:8766/upload/file/1.xls";
		try {
			BufferedInputStream bis = null;
			URL url = null;
			HttpURLConnection httpUrl = null; // 建立链接
			url = new URL(filePath);
			httpUrl = (HttpURLConnection) url.openConnection();// 连接指定的资源
			httpUrl.connect();// 获取网络输入流
			bis = new BufferedInputStream(httpUrl.getInputStream());

			StringBuffer content = new StringBuffer();
			HSSFWorkbook workbook = new HSSFWorkbook(bis);
			for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
				HSSFSheet aSheet = workbook.getSheetAt(numSheets);// 获得一个sheet
				content.append("/n");
				if (null == aSheet) {
					continue;
				}
				for (int rowNum = 0; rowNum <= aSheet.getLastRowNum(); rowNum++) {
					content.append("/n");
					HSSFRow aRow = aSheet.getRow(rowNum);
					if (null == aRow) {
						continue;
					}
					for (short cellNum = 0; cellNum <= aRow.getLastCellNum(); cellNum++) {
						HSSFCell aCell = aRow.getCell(cellNum);
						if (null == aCell) {
							continue;
						}
						if (aCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							content.append(aCell.getRichStringCellValue()
									.getString());
						} else if (aCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							boolean b = HSSFDateUtil.isCellDateFormatted(aCell);
							if (b) {
								Date date = aCell.getDateCellValue();
								SimpleDateFormat df = new SimpleDateFormat(
										"yyyy-MM-dd");
								content.append(df.format(date));
							}
						}
					}
				}
			}
			response.getWriter().write(content.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "ok";
	}

}
