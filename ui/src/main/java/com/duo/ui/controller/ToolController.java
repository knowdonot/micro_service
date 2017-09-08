package com.duo.ui.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.duo.ui.test.Doc2HtmlUtil;

@Controller
@RequestMapping("file")
public class ToolController {

	@RequestMapping(value = "/look/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public String look(@PathVariable("fileName") String fileName) {

		return fileName;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(
			@RequestParam(value = "files", required = true) MultipartFile file,
			HttpServletRequest request) {
		System.out.println("------------------");
		System.out.println(file);
		try {
			// 文件存放目录
			String targPath = "D:" + File.separatorChar + "file"+ File.separatorChar + "upload";

			if (file != null) {// 判断上传的文件是否为空
				String path = null;// 文件路径
				String type = null;// 文件类型
				String fileName = file.getOriginalFilename();// 文件原名称
				System.out.println("上传的文件原名称:" + fileName);
				// 判断文件类型
				type = fileName.indexOf(".") != -1 ? fileName.substring(
						fileName.lastIndexOf(".") + 1, fileName.length())
						: null;
				if (type != null) {// 判断文件类型是否为空
					if ("GIF".equals(type.toUpperCase())
							|| "PNG".equals(type.toUpperCase())
							|| "JPG".equals(type.toUpperCase())
							|| "xls".equalsIgnoreCase(type)
							|| "xlsx".equalsIgnoreCase(type)
							|| "doc".equalsIgnoreCase(type)
							|| "docx".equalsIgnoreCase(type)
							|| "ppt".equalsIgnoreCase(type)
							|| "pptx".equalsIgnoreCase(type)) {
						// 项目在容器中实际发布运行的根路径
						String realPath = request.getSession()
								.getServletContext().getRealPath("/");
						// 自定义的文件名称
						String trueFileName = String.valueOf(System
								.currentTimeMillis()) + fileName;
						// 设置存放图片文件的路径
						path = realPath + trueFileName;
						System.out.println("存放图片文件的路径:" + path);
						// 转存文件到指定的路径
						File targFile = new File(targPath);
						if(targFile.exists()){
							if(targFile.isDirectory()){
								System.out.println("目录已存在");
							}
						}else{
							targFile.mkdir();
						}
						file.transferTo(targFile);
						System.out.println("文件成功上传到指定目录下");
						
						return JSONObject.toJSONString(trueFileName);
					} else {
						System.out.println("不是我们想要的文件类型,请按要求重新上传");
						return null;
					}
				} else {
					System.out.println("文件类型为空");
					return null;
				}
			} else {
				System.out.println("没有找到相对应的文件");
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString("异常");
	}
	
	private String uploadFile(String fromFile,String toFile,String type){
		Doc2HtmlUtil dh =  Doc2HtmlUtil.getDoc2HtmlUtilInstance();
		
		try {
			FileInputStream fileInputStream = new FileInputStream(fromFile);
			dh.file2Html(fileInputStream, toFile, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
