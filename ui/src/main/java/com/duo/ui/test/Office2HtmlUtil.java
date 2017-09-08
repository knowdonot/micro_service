package com.duo.ui.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
/**
 * 利用jodconverter(基于OpenOffice服务)将文件(*.doc、*.docx、*.xls、*.ppt)转化为html格式或者pdf格式，
 * 使用前请检查OpenOffice服务是否已经开启, OpenOffice进程名称：soffice.exe | soffice.bin
 * 
 * @author yjclsx
 */
public class Office2HtmlUtil {

    private static Office2HtmlUtil doc2HtmlUtil;

    /**
     * 获取Doc2HtmlUtil实例
     */
    public static synchronized Office2HtmlUtil getDoc2HtmlUtilInstance() {
        if (doc2HtmlUtil == null) {
            doc2HtmlUtil = new Office2HtmlUtil();
        }
        return doc2HtmlUtil;
    }

    /**
     * 转换文件成html
     * 
     * @param fromFileInputStream:
     * @throws IOException 
     */
    public String file2Html(InputStream fromFileInputStream, String toFilePath,String type) throws IOException {
    	long s1 = System.currentTimeMillis();
    	Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timesuffix = sdf.format(date);
        String docFileName = null;
        String htmFileName = null;
        if("doc".equals(type)){
            docFileName = "doc_" + timesuffix + ".doc";
            htmFileName = "doc_" + timesuffix + ".html";
        }else if("docx".equals(type)){
            docFileName = "docx_" + timesuffix + ".docx";
            htmFileName = "docx_" + timesuffix + ".html";
        }else if("xls".equals(type)){
            docFileName = "xls_" + timesuffix + ".xls";
            htmFileName = "xls_" + timesuffix + ".html";
        }else if("xlsx".equals(type)){
            docFileName = "xlsx_" + timesuffix + ".xlsx";
            htmFileName = "xlsx_" + timesuffix + ".html";
        }else if("ppt".equals(type)){
            docFileName = "ppt_" + timesuffix + ".ppt";
            htmFileName = "ppt_" + timesuffix + ".html";
        }else if("pptx".equals(type)){
            docFileName = "pptx_" + timesuffix + ".pptx";
            htmFileName = "pptx_" + timesuffix + ".html";
        }else{
        	System.out.println("格式不对");
            return null;
        }

        File htmlOutputFile = new File(toFilePath + File.separatorChar + htmFileName);
        File docInputFile = new File(toFilePath + File.separatorChar + docFileName);
        if (htmlOutputFile.exists())
            htmlOutputFile.delete();
        htmlOutputFile.createNewFile();
        if (docInputFile.exists())
            docInputFile.delete();
        docInputFile.createNewFile();
        /**
         * 由fromFileInputStream构建输入文件
         */
        try {
            OutputStream os = new FileOutputStream(docInputFile);
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            os.close();
            fromFileInputStream.close();
        } catch (IOException e) {
        	System.out.println("复制预览文件失败---------------------------");
        }

        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
        }
//        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
        converter.convert(docInputFile, htmlOutputFile);
        connection.disconnect();
        // 转换完之后删除word文件
        docInputFile.delete();
        
        long s2 = System.currentTimeMillis();
        System.out.println(htmFileName +".html time "+(s2-s1));
        return htmFileName;
    }

   
    public static void main(String[] args) throws IOException {
        Office2HtmlUtil coc2HtmlUtil = getDoc2HtmlUtilInstance();
        File file = null;
        FileInputStream fileInputStream = null;

        
        //微服务平台项目周报_20170505.xlsx
//        file = new File("D:\\file\\社保医院.xls");
        file = new File("D:\\file\\微服务平台项目周报_20170505.xlsx");
        fileInputStream = new FileInputStream(file);
        coc2HtmlUtil.file2Html(fileInputStream, "D:\\file","xls");
//
        file = new File("D:\\file\\微速贷微服务化开发.docx");
        fileInputStream = new FileInputStream(file);
        coc2HtmlUtil.file2Html(fileInputStream, "D:\\file","doc");

        file = new File("D:\\file\\汇诚微服务网关.pptx");
        fileInputStream = new FileInputStream(file);
        coc2HtmlUtil.file2Html(fileInputStream, "D:\\file","ppt");


    }

}