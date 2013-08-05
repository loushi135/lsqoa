 package com.xpsoft.core.util;
 
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.core.JdbcTemplate;
 
 public class ExportUtil
 {
   private static final Log logger = LogFactory.getLog(ExportUtil.class);
   
   /**
    * @param list数据源
    * @param jasperName 报表在WEB-INF/report下的名称
    * @param fileName 导出时显示的报表名称
    * @param format 报表格式
    * @param parmasMap 别的参数
    */
   public static void export(List list,String jasperName,String fileName,String format,Map<Object,Object> parmasMap)
   {
	   try{
		   ServletContext context = ServletActionContext.getServletContext(); 
	       String subAddressPath = context.getRealPath(File.separator+"WEB-INF"+File.separator+"report"+File.separator)+File.separator;  //  报表根路径
	       String filePath = subAddressPath+jasperName+".jasper";  //  主报表路径
	       JasperReport fileReport = (JasperReport)JRLoader.loadObject(filePath);
	       Map<Object,Object> parameters = new HashMap<Object,Object>();
	       parameters.put("SUBREPORT_DIR", subAddressPath);  //子报表路径
	       parameters.putAll(parmasMap);
	       JRBeanCollectionDataSource dataSource =new JRBeanCollectionDataSource(list);
	       JasperPrint jasperPrint = JasperFillManager.fillReport(fileReport, parameters, dataSource);
		   HttpServletResponse response = ServletActionContext.getResponse();
	       OutputStream os = response.getOutputStream();
	       fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	       response.setCharacterEncoding("UTF-8");
	       if("xls".equals(format)){
	    	   response.setContentType("application/xls");
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".xls");
	    	   JRXlsExporter exporter = new JRXlsExporter();
//	    	   java.lang.reflect.Field margin = JRBaseReport.class.getDeclaredField("leftMargin");
//			   margin.setAccessible(true);
//			   margin.setInt(jasperPrint,0);
//			   margin = JRBaseReport.class.getDeclaredField("topMargin");
//			   margin.setAccessible(true);
//	    	   margin.setInt(jasperPrint,0);
//	    	   margin = JRBaseReport.class.getDeclaredField("bottomMargin");
//	    	   margin.setAccessible(true);
//	    	   margin.setInt(jasperPrint, 0);
	    	   exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
	    	   exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.TRUE);
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }else if("pdf".equals(format)){
	    	   response.setContentType("application/xls");
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".pdf");
	    	   JRPdfExporter exporter = new JRPdfExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }else if("csv".equals(format)){
	    	   response.setContentType("application/csv");
//	    	   response.setCharacterEncoding("gbk");
//	    	   response.setLocale(Locale.getDefault());
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".csv");
	    	   JRCsvExporter exporter = new JRCsvExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }else if("html".equals(format)){
	    	   response.setContentType("application/html");
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".html");
	    	   JRHtmlExporter exporter = new JRHtmlExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }
	   }catch(Exception e){
		   logger.error(e);
	   }
   }
   
   /**
    * @param list数据源
    * @param jasperName 报表在WEB-INF/report下的名称
    * @param fileName 导出时显示的报表名称
    * @param format 报表格式
    * @param parmasMap 别的参数
    */
   public static void exportByJdbc(String jasperName,String fileName,String format,Map<Object,Object> parmasMap){
	   try{
		   ServletContext context = ServletActionContext.getServletContext(); 
	       String subAddressPath = context.getRealPath(File.separator+"WEB-INF"+File.separator+"report"+File.separator)+File.separator;  //  报表根路径
	       String filePath = subAddressPath+jasperName+".jasper";  //  主报表路径
	       JasperReport fileReport = (JasperReport)JRLoader.loadObject(filePath);
	       Map<Object,Object> parameters = new HashMap<Object,Object>();
	       parameters.put("SUBREPORT_DIR", subAddressPath);  //子报表路径
	       parameters.putAll(parmasMap);
	       JdbcTemplate jdbcTemplate = (JdbcTemplate)AppUtil.getBean("jdbcTemplate");
	       Connection connection = jdbcTemplate.getDataSource().getConnection();
	       JasperPrint jasperPrint = JasperFillManager.fillReport(fileReport, parameters, connection);
		   HttpServletResponse response = ServletActionContext.getResponse();
	       OutputStream os = response.getOutputStream();
	       fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	       response.setCharacterEncoding("UTF-8");
	       if("xls".equals(format)){
	    	   response.setContentType("application/xls");
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".xls");
	    	   JRXlsExporter exporter = new JRXlsExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }else if("pdf".equals(format)){
	    	   response.setContentType("application/xls");
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".pdf");
	    	   JRPdfExporter exporter = new JRPdfExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }else if("csv".equals(format)){
	    	   response.setContentType("application/csv");
	    	   response.setCharacterEncoding("gbk");
	    	   response.setLocale(Locale.getDefault());
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".csv");
	    	   JRCsvExporter exporter = new JRCsvExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }else if("html".equals(format)){
	    	   response.setContentType("application/html");
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".html");
	    	   JRHtmlExporter exporter = new JRHtmlExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }
	   }catch(Exception e){
		   logger.error(e);
	   }
   }
   
   /**
    * @param list数据源
    * @param jasperName 报表在WEB-INF/report下的名称
    * @param fileName 导出时显示的报表名称
    * @param format 报表格式
    * @param parmasMap 别的参数
    */
   public static void exportMultiByJdbc(List<String> jasperNameList,String fileName,String format,Map<Object,Object> parmasMap){
	   try{
		   ServletContext context = ServletActionContext.getServletContext(); 
	       String subAddressPath = context.getRealPath(File.separator+"WEB-INF"+File.separator+"report"+File.separator)+File.separator;  //  报表根路径
	       String imagePath = subAddressPath+"images"+File.separator;  //  报表图片路径
	       List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
	       JdbcTemplate jdbcTemplate = (JdbcTemplate)AppUtil.getBean("jdbcTemplate");
	       Connection connection = jdbcTemplate.getDataSource().getConnection();
	       for(String jasperName:jasperNameList){
	    	   String filePath = subAddressPath+jasperName+".jasper";  //  主报表路径
	    	   JasperReport fileReport = (JasperReport)JRLoader.loadObject(filePath);
	    	   Map<Object,Object> parameters = new HashMap<Object,Object>();
	    	   parameters.put("imagePath", imagePath);  //报表图片路径
	    	   parameters.put("SUBREPORT_DIR", subAddressPath);  //子报表路径
	    	   parameters.putAll(parmasMap);
	    	   JasperPrint jasperPrint = JasperFillManager.fillReport(fileReport, parameters, connection);
	    	   jasperPrintList.add(jasperPrint);
	       }
		   HttpServletResponse response = ServletActionContext.getResponse();
	       OutputStream os = response.getOutputStream();
	       fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	       response.setCharacterEncoding("UTF-8");
//	       ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	       if("xls".equals(format)){
	    	   response.setContentType("application/xls");
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".xls");
	    	   JRXlsExporter exporter = new JRXlsExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }else if("pdf".equals(format)){
	    	   response.setContentType("application/xls");
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".pdf");
	    	   JRPdfExporter exporter = new JRPdfExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }else if("csv".equals(format)){
	    	   response.setContentType("application/csv");
	    	   response.setCharacterEncoding("gbk");
	    	   response.setLocale(Locale.getDefault());
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".csv");
	    	   JRCsvExporter exporter = new JRCsvExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }else if("html".equals(format)){
	    	   response.setContentType("application/html");
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".html");
	    	   JRHtmlExporter exporter = new JRHtmlExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }else if("word".equals(format)){
	    	   response.setContentType("application/msword");
	    	   response.setHeader("Content-Disposition", "filename="+fileName+".doc");
	    	   JRExporter exporter = new JRRtfExporter();
	           exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
	           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	           exporter.exportReport();
	       }
//	       byte[] bytes= baos.toByteArray();//得到这个流  
//	       response.setContentLength(bytes.length);  
//	       ServletOutputStream ouputStream = response.getOutputStream();  
//	       ouputStream.write(bytes, 0, bytes.length);  
//	       ouputStream.flush();  
//	       ouputStream.close();  
	       connection.close();
	       os.flush();
	       os.close();
	   }catch(Exception e){
		   logger.error(e);
	   }
   }
 }

