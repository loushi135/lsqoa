<%@ page language="java" pageEncoding="UTF-8"%>
<%
response.setHeader("Content-Type","application/force-download");  
response.setHeader("Content-Type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");  
response.addHeader("Content-Disposition","attachment;filename="+request.getParameter("fileName") );
out.print(new String(request.getParameter("base64Value").getBytes("iso8859-1"),"UTF-8") );
%>
