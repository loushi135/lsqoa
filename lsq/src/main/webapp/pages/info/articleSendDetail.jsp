<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.*"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@page import="com.xpsoft.oa.service.info.ArticleSendService"%>
<%@page import="com.xpsoft.oa.service.info.impl.ArticleSendServiceImpl"%>
<%@page import="com.xpsoft.core.web.paging.PagingBean"%>
<%@page import="com.xpsoft.oa.model.info.ArticleSend"%>
<%@page import="com.xpsoft.core.command.QueryFilter"%>
<%
	Long noticeId = null;
	String strId = request.getParameter("noticeId");
	String opt = request.getParameter("opt");
	ArticleSendService articleSendService = (ArticleSendService) AppUtil.getBean("articleSendService");
	ArticleSend articleSend = null;
	if (strId != null && !"".equals(strId)) {
		noticeId = Long.valueOf(strId);
	}
	
	if(opt != null && !"".equals(opt)){
		//使用页面的方法实现获取上一条,下一条的记录
		QueryFilter filter=new QueryFilter(request);
		List<ArticleSend> list = null;
		filter.getPagingBean().setPageSize(1);//只取一条记录
		if(opt.equals("_next")){
			//点击下一条按钮,则取比当前ID大的下一条记录
			filter.addFilter("Q_noticeId_L_GT", noticeId.toString());
			list= articleSendService.getAll(filter);
			if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
				request.setAttribute("__haveNextArticleSendFlag","endNext");
			}
		}else if(opt.equals("_pre")){
			//点击上一条按钮,则取比当前ID小的一条记录
			filter.addFilter("Q_noticeId_L_LT", noticeId.toString());
			filter.addSorted("noticeId","desc");
			list= articleSendService.getAll(filter);
			if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
				request.setAttribute("__haveNextArticleSendFlag","endPre");
			}
		}
		
		if(list.size()>0){
			articleSend = list.get(0);
		}else{
			articleSend = articleSendService.get(noticeId);
		}
	}else{
		articleSend = articleSendService.get(noticeId);
		request.setAttribute("__haveNextArticleSendFlag","");
	}
	request.setAttribute("articleSend",articleSend);
%>
<table width="98%" cellpadding="0" cellspacing="1" style="border: 5px 5px 5px 5px;">
	<tr>
		<td align="center" style="font:2.0em 宋体  ;color:black;font-weight: bold;padding:10px 0px 10px 0px; ">
			${articleSend.noticeTitle}
			<input type="hidden" value="${__haveNextArticleSendFlag}" id="__haveNextArticleSendFlag"/>
			<input type="hidden" value="${articleSend.noticeId }" id="__curArticleSendId"/>
		</td>
	</tr>
	
	<tr>
		<td align="center" style="padding:0px 0px 10px 0px;">
				发布人:
			<font color="green">
				${articleSend.postName}
			</font> 
				&nbsp;生效日期:
			<font color="red">
				<fmt:formatDate value="${articleSend.effectiveDate}" pattern="yyyy-MM-dd" />
			</font>
				&nbsp;——
			<font color="red">
				<fmt:formatDate value="${articleSend.expirationDate}" pattern="yyyy-MM-dd" />
			</font>
		</td>
	</tr>
	<tr>
		<td style="border-top:dashed 1px #ccc;" height="28">
		</td>
	</tr>
	<tr >
		<td style="font:13px 宋体;color: black;line-height:24px;">
			${articleSend.noticeContent}
		</td>
	</tr>
	<tr height="28"></tr>
	<tr>
		<td style="border-top: dashed 1px #ccc;" height="28"></td>
	</tr>
	<c:if test="${not empty articleSend.attachFiles}">
	<tr>
		<td><font style="font-weight: bold;">附件:</font><c:forEach
			items="${articleSend.attachFiles}" var="aa">
			<a href="#" onclick="FileAttachDetail.show(${aa.fileId});"
				class="attachment">${aa.fileName}</a>
		</c:forEach></td>
	</tr>
	</c:if>
	<tr height="28"></tr>
</table>