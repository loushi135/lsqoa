<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.*"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@page import="com.xpsoft.oa.service.hrm.PhotoService"%>
<%@page import="com.xpsoft.oa.service.hrm.impl.PhotoServiceImpl"%>
<%@page import="com.xpsoft.core.web.paging.PagingBean"%>
<%@page import="com.xpsoft.oa.model.hrm.Photo"%>
<%@page import="com.xpsoft.core.command.QueryFilter"%>
<%
	String basePath = request.getContextPath();
	Long id = null;
	String strId = request.getParameter("id");
	String opt = request.getParameter("opt");
	PhotoService photoService = (PhotoService) AppUtil
			.getBean("photoService");
	Photo photo = null;
	if (strId != null && !"".equals(strId)) {
		id = Long.valueOf(strId);
	}

	if (opt != null && !"".equals(opt)) {
		//使用页面的方法实现获取上一条,下一条的记录
		QueryFilter filter = new QueryFilter(request);
		List<Photo> list = null;
		filter.getPagingBean().setPageSize(1);//只取一条记录
		if (opt.equals("_next")) {
			//点击下一条按钮,则取比当前ID大的下一条记录
			filter.addFilter("Q_id_L_GT", id.toString());
			list = photoService.getAll(filter);
			if (filter.getPagingBean().getStart() + 1 == filter
					.getPagingBean().getTotalItems()) {
				request.setAttribute("__haveNextPhotoFlag", "endNext");
			}
		} else if (opt.equals("_pre")) {
			//点击上一条按钮,则取比当前ID小的一条记录
			filter.addFilter("Q_id_L_LT", id.toString());
			filter.addSorted("id", "desc");
			list = photoService.getAll(filter);
			if (filter.getPagingBean().getStart() + 1 == filter
					.getPagingBean().getTotalItems()) {
				request.setAttribute("__haveNextPhotoFlag", "endPre");
			}
		}

		if (list.size() > 0) {
			photo = list.get(0);
		} else {
			photo = photoService.get(id);
		}
	} else {
		photo = photoService.get(id);
		request.setAttribute("__haveNextPhotoFlag", "");
	}
	request.setAttribute("photo", photo);
%>
<table width="98%" cellpadding="0" cellspacing="1">
	<tr>
		<td align="center"
			style="font: 2.0em 宋体; color: black; font-weight: bold; padding: 10px 0px 10px 0px;">
			<input type="hidden" value="${__haveNextPhotoFlag}"
				id="__haveNextPhotoFlag" />
			<input type="hidden" value="${photo.id }" id="__curPhotoId" />
			<input type="hidden" value="${photo.photoName }" id="__curPhotoName" />
			<input type="hidden" value="${photo.photoDesc }" id="__curPhotoDesc" />
			<input type="hidden" value="${photo.user.userId }"
				id="__curPhotoUserId" />
		</td>
	</tr>

	<tr>
		<td align="center" style="padding: 0px 0px 10px 0px;">
			上传者:
			<font color="green"> ${photo.user.fullname} </font> &nbsp;上传时间:
			<font color="red"> <fmt:formatDate value="${photo.createDate}"
					pattern="yyyy-MM-dd" /> </font>
		</td>
	</tr>
	<tr>
		<td style="border-top: dashed 1px #ccc;" height="28">
		</td>
	</tr>
</table>
<div align="center">
	<img  alt="" 
		src="<%=basePath%>/attachFiles/${photo.file.filePath}" onload="AutoResizeImage(900,700,this)">
</div>
