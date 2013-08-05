<%@page import="com.xpsoft.core.util.ContextUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.*"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@page import="com.xpsoft.oa.service.info.NoticeService"%>
<%@page import="com.xpsoft.oa.service.info.impl.NoticeServiceImpl"%>
<%@page import="com.xpsoft.oa.service.info.NoticeViewDetailService"%>
<%@page import="com.xpsoft.oa.service.info.impl.NoticeViewDetailServiceImpl"%>
<%@page import="com.xpsoft.core.web.paging.PagingBean"%>
<%@page import="com.xpsoft.oa.model.info.Notice"%>
<%@page import="com.xpsoft.oa.model.info.NoticeViewDetail"%>
<%@page import="com.xpsoft.oa.model.system.AppUser"%>
<%@page import="com.xpsoft.oa.service.system.AppUserService"%>
<%@page import="com.xpsoft.oa.service.system.impl.AppUserServiceImpl"%>
<%@page import="com.xpsoft.core.command.QueryFilter"%>
<%
	Long noticeId = null;
	String strId = request.getParameter("noticeId");
	String opt = request.getParameter("opt");
	String userId = request.getParameter("currentUserId");
	NoticeService noticeService = (NoticeService) AppUtil.getBean("noticeService");
	Notice notice = null;
	if (strId != null && !"".equals(strId)) {
		noticeId = Long.valueOf(strId);
	}
	
	
	if(opt != null && !"".equals(opt)){
		//使用页面的方法实现获取上一条,下一条的记录
		QueryFilter filter=new QueryFilter(request);
		List<Notice> list = null;
		filter.getPagingBean().setPageSize(1);//只取一条记录
		if(opt.equals("_next")){
			//点击下一条按钮,则取比当前ID大的下一条记录
			filter.addFilter("Q_noticeId_L_GT", noticeId.toString());
			list= noticeService.getAll(filter);
			if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
				request.setAttribute("__haveNextNoticeFlag","endNext");
			}else if(list.size()==0){
				request.setAttribute("__haveNextNoticeFlag","endNext");
			}
		}else if(opt.equals("_pre")){
			//点击上一条按钮,则取比当前ID小的一条记录
			filter.addFilter("Q_noticeId_L_LT", noticeId.toString());
			filter.addSorted("noticeId","desc");
			list= noticeService.getAll(filter);
			if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
				request.setAttribute("__haveNextNoticeFlag","endPre");
			}else if(list.size()==0){
				request.setAttribute("__haveNextNoticeFlag","endPre");
			}
		}
		
		if(list.size()>0){
			notice = list.get(0);
		}else{
			notice = noticeService.get(noticeId);
		}
	}else{
		notice = noticeService.get(noticeId);
		request.setAttribute("__haveNextNoticeFlag","");
	}
	if(notice!=null){//记录浏览次数与浏览详细情况
		notice.setViewCounts((notice.getViewCounts()==null?0:notice.getViewCounts())+1);
		noticeService.save(notice);
		AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
		NoticeViewDetail noticeViewDetail = new NoticeViewDetail();
		noticeViewDetail.setAppUser(appUserService.get(Long.valueOf(userId)));
		noticeViewDetail.setNotice(notice);
		noticeViewDetail.setViewDate(new Date());
		NoticeViewDetailService noticeViewDetailService = (NoticeViewDetailService) AppUtil.getBean("noticeViewDetailService");
		noticeViewDetailService.save(noticeViewDetail);
	}
	request.setAttribute("notice",notice);
%>
<table width="98%" cellpadding="0" cellspacing="1" style="border: 5px 5px 5px 5px;">
	<tr>
		<td align="center" style="font:2.0em 宋体  ;color:black;font-weight: bold;padding:10px 0px 10px 0px; ">
			${notice.noticeTitle}
			<input type="hidden" value="${__haveNextNoticeFlag}" id="__haveNextNoticeFlag"/>
			<input type="hidden" value="${notice.noticeId }" id="__curNoticeId"/>
		</td>
	</tr>
	
	<tr>
		<td align="center" style="padding:0px 0px 10px 0px;">
				发布人:
			<font color="green">
				${notice.postName}
			</font> 
			&nbsp;&nbsp;浏览次数:
				<a href="#" onclick="NoticeViewDetail.show(${notice.noticeId});"><font color="red">${notice.viewCounts }</font></a>
				&nbsp;生效日期:
			<font color="red">
				<fmt:formatDate value="${notice.effectiveDate}" pattern="yyyy-MM-dd" />
			</font>
				&nbsp;——
			<font color="red">
				<fmt:formatDate value="${notice.expirationDate}" pattern="yyyy-MM-dd" />
			</font>
		</td>
	</tr>
	<tr>
		<td style="border-top:dashed 1px #ccc;" height="28">
		</td>
	</tr>
	<tr >
		<td style="font:13px 宋体;color: black;line-height:24px;">
			${notice.noticeContent}
		</td>
	</tr>
	<tr height="28"></tr>
	<tr>
		<td style="border-top: dashed 1px #ccc;" height="28"></td>
	</tr>
	<c:if test="${not empty notice.attachFiles}">
	<tr>
		<td><font style="font-weight: bold;">附件:</font><c:forEach
			items="${notice.attachFiles}" var="aa">
			<a href="#" onclick="FileAttachDetail.show(${aa.fileId});"
				class="attachment">${aa.fileName}</a>
		</c:forEach></td>
	</tr>
	</c:if>
	<tr height="28"></tr>
</table>