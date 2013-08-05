<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@page import="com.xpsoft.core.util.ContextUtil"%>
<%@page import="common.Logger"%>
<%@page import="com.xpsoft.oa.model.system.UserAgent"%>
<%@page import="com.xpsoft.core.util.UserAgentUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String change=request.getParameter("change");
	Logger log = Logger.getLogger(this.getClass());
	//ip
 	String ip = request.getHeader("x-forwarded-for");  
     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
         ip = request.getHeader("Proxy-Client-IP");  
     }  
     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
         ip = request.getHeader("WL-Proxy-Client-IP");  
     }  
     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
         ip = request.getRemoteAddr();  
     }  
	String userAgentStr=request.getHeader("User-Agent");
	UserAgent userAgent= UserAgentUtil.getUserAgent(userAgentStr);
	if(null==userAgent){
		log.warn("无法匹配客户端系统类型"+"访问地址："+ip+"    User-Agent:"+userAgentStr);
	}else{
		if("Phone".equals(userAgent.getPlatformType())){
			log.warn("[Phone]"+"访问地址："+ip);
			if(!"manual".equals(change)){
				response.sendRedirect(request.getContextPath()+"/system/noticeListMobile.do");//移动平台跳转
			}
		}else if("Pad".equals(userAgent.getPlatformType())){
			log.warn("[Pad]"+"访问地址："+ip);
			if(!"manual".equals(change)){
				response.sendRedirect(request.getContextPath()+"/system/noticeListMobile.do");//移动平台跳转
			}
		}else if("Linux".equals(userAgent.getPlatformType())){
			log.warn("[Linux]"+"访问地址："+ip);
		}else if("Windows".equals(userAgent.getPlatformType())){
			log.warn("[Windows]"+"访问地址："+ip);
			//response.sendRedirect("mobile/m_login.jsp");//移动平台跳转
		}else if("Mac OS".equals(userAgent.getPlatformType())){
			log.warn("[Mac OS]"+"访问地址："+ip);
		}else{
			log.warn("无法匹配客户端系统类型"+"访问地址："+ip);
		}
	}
 %>

<%
	String basePath=request.getContextPath();
	//登录成功后，需要把该用户显示至在线用户
	AppUtil.addOnlineUser(request.getSession().getId(), ContextUtil.getCurrentUser());
	if(ContextUtil.getCurrentUser().getRights().contains("__ALL")){
		request.setAttribute("IS_MANAGER",true);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="msthemecompatible" content="no">
		<title><%=AppUtil.getCompanyName()%>－－办公协同管理系统</title>
		<link rel="Shortcut Icon" href="<%=basePath%>/images/yh.ico" />
		<link rel="Bookmark" href="<%=basePath%>/images/yh.ico" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-all-notheme.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-patch.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/Portal.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/Ext.ux.UploadDialog.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/Ext.ux.form.LovCombo.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css"/>
		<link rel="stylesheet"  type="text/css" href="<%=basePath%>/ext3/ux/css/data-view.css"></script>
    	<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/highslide/highslide.css" />
    	<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/LockingGridView.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/ux-all.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/caltask/calendar.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/bbs.css"/>
		<!-- load the extjs libary -->
		<script type="text/javascript" src="<%=basePath%>/js/dynamic.jsp"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/adapter/ext/ext-base.gzjs"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-all.gzjs"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ScriptMgr.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/js/App.import.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/AppUtil.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/print/Printer-all.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/print/renderers/GridPanel.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/export/Exporter-all.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Ext.ux.IconCombob.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/RowEditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Fckeditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/XmlTreeLoader.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/FileUploadField.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/UploadDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/CheckColumn.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Portal.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/PortalColumn.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Portlet.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Toast.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/GridSummary.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Ext.ux.grid.RowActions.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/DateTimeField.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/TabCloseMenu.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/SuperDateField.js"></script>
	
		<script type="text/javascript" src="<%=basePath%>/js/core/SystemCalendar.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/TreeSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/date.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/TreePanelEditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/TreeXmlLoader.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/WebOffice.js"></script>

		<script type="text/javascript" src="<%=basePath%>/js/selector/UserSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/UserGroupSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/UserSubSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/DepSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/RoleSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/GoodsSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/CarSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/CustomerSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/OnlineUserSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/BookSelector.js"></script>	
		<script type="text/javascript" src="<%=basePath%>/js/selector/ProjectSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/ProjectNewSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/ProjectSealSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/ProviderSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/ContractSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/SuppliersAssessSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/DesignProjectSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/OtherProjectSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/SalesProjectSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/MaterialContractSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/MeetingRoomSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/CitySelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/CourseBoxSelector.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/js/info/MessageWin.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/info/MessageReplyWin.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/info/MessageDetail.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/flow/ProcessNextForm.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/flow/ProcessNextFormForYhoa.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/system/FileAttachDetail.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/system/DiaryDetail.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/task/WorkPlanDetail.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/personal/DutyView.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/personal/DutyForm.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/sound/soundmanager2.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/search/SearchForm.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/changeTheme/changeTheme.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/document/PublicDocumentView.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/document/NewPublicDocumentForm.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/document/PublicDocumentDetail.js"></script>
    	<link href="<%=basePath%>/css/desktop.css" rel="stylesheet" type="text/css" />	
    	<script type="text/javascript" src="<%=basePath%>/js/ScrollText.js"></script>
    	<script type="text/javascript" src="<%=basePath%>/ext3/ext-basex.js"></script>
    	<script type="text/javascript"src="<%=basePath%>/ext3/ux/LockingGridView.js"></script>
    	<script type="text/javascript"src="<%=basePath%>/ext3/ux/RowExpander.js"></script>
    	<script type="text/javascript"src="<%=basePath%>/ext3/ux/ColumnHeaderGroup.js"></script>
    	<script type="text/javascript"src="<%=basePath%>/ext3/ux/Ext.ux.form.LovCombo.js"></script>
		<script type="text/javascript"src="<%=basePath%>/js/core/SystemCalendar.js"></script>
		<script type="text/javascript"src="<%=basePath%>/ext3/ux/highslide/highslide-with-gallery.min.js"></script>
		<script type="text/javascript"src="<%=basePath%>/ext3/ux/highslide/highslideUse.js"></script>
	    <script type="text/javascript">
	       var __companyName="<%=AppUtil.getCompanyName()%>";
	       var __currentUser = '<security:authentication property="principal.fullname" />';
	       var __currentUserMobile = '<security:authentication property="principal.mobile" />';
	       var __currentUserEmail = '<security:authentication property="principal.email" />';
	       var __currentUserEmailPWD = '<security:authentication property="principal.education" />';
	       var __currentUserPosition = '<security:authentication property="principal.position" />';
	       var __currentUserId = '<security:authentication property="principal.userId" />';
	       var __currentUserDept = '<security:authentication property="principal.department.depName" />';
	       var __currentUserDeptId = '<security:authentication property="principal.department.depId" />';		      
	       var __currentUserAccessionTime = '<security:authentication property="principal.accessionTime" />';      
	       var __currentUserSex = '<security:authentication property="principal.title" />';   
	       var __currentUserRoleNames="<%=AppUtil.getRoleName()%>";   
	       var __currentUserFax = '<security:authentication property="principal.fax" />';
	       var __currentUserPhone = '<security:authentication property="principal.phone" />';
	       var __currentUserZip= '<security:authentication property="principal.zip" />';
		   Ext.onReady(function(){
			   	  var storeTheme=getCookie('theme');
			   	  if(storeTheme==null || storeTheme==''){
				   	  storeTheme='ext-all';
			   	  }
			      Ext.util.CSS.swapStyleSheet("theme", __ctxPath+"/ext3/resources/css/"+storeTheme+".css"); 
			      
		    });
		    
		    var	__appSupport = '<%=AppUtil.getPropertity("app.support")%>';
		    var	__appSupportMail = '<%=AppUtil.getPropertity("app.supportMail")%>';
		    var	__appListForwardNode = '<%=AppUtil.getPropertity("app.listForwardNode")%>';
		    var __appShopingGuidRoles = '<%=AppUtil.getPropertity("app.shopingGuidRoles")%>';
		    
		    var __modifyPWD ;
		    if(<%=AppUtil.getPropertity("app.debug")%>){
		    	__modifyPWD=false;
		    }else{
		    	__modifyPWD=${sessionScope.modifyPWD}
		    }
	    </script>
	    <script type="text/javascript">
		 window.onbeforeunload=function(){		
		 	    App.Logout();
		 }
		 var oWin;
		 function startPrint(obj,title){
			oWin=window.open("","_blank");
			var strPrint= "<html><body><title>打印预览区</title>\n";
			
			strPrint=strPrint +"<div id='printPage'>";
			
			strPrint=strPrint +"<div style='font-size:12px;text-align:left;float:left;'>苏州朗捷通智能科技有限公司</div>\n";
			strPrint=strPrint +"<div style='font-size:12px;text-align:right;'>\n";
			strPrint=strPrint +formatDate(new Date(),'yyyy-MM-dd');
			strPrint=strPrint +"</div>\n";
			strPrint=strPrint + "<hr size='2' color='black' />\n";
		 	strPrint=strPrint +"<h4 style='font-size:18px; text-align:center;'>";
			strPrint=strPrint +title+"</h4>\n";
			strPrint=strPrint + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/print.css\" />\n";
			strPrint=strPrint + "<hr size='1' />\n";
			strPrint=strPrint + "<div id=\"content\">\n";
			strPrint=strPrint + obj.innerHTML + "\n";
			strPrint=strPrint + "</div>\n";
			strPrint=strPrint + "<hr size='1' />\n";
			
			strPrint=strPrint +"</div>";
			
			strPrint=strPrint + "<div style='text-align:center'><button onclick=\"window.opener.printdiv('printPage');\" style='padding-left:4px;padding-right:4px;'>打  印</button><button onclick='window.opener=null;window.close();' style='padding-left:4px;padding-right:4px;'>关  闭</button></div>\n</body></html>";
			oWin.document.write(strPrint);
			oWin.focus();
			oWin.document.close();
		}
		 function printdiv(printpage)
		{
			var headstr = "<html><head><title>&nbsp;</title></head><body>";
			var footstr = "</body></html>";
			var newstr = oWin.document.getElementById(printpage).innerHTML;
			var oldstr = oWin.document.body.innerHTML;
			oWin.document.body.innerHTML = headstr+newstr+footstr;
			oWin.print();
			oWin.document.body.innerHTML = oldstr;
			return false;
		}
		</script>
	    <script type="text/javascript" src="<%=basePath%>/js/IndexPage.js"></script>
	    <script type="text/javascript" src="<%=basePath%>/js/App.home.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/App.js"></script>	
		<script type="text/javascript" src="<%=basePath%>/js/UpdateWin.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/system/AnnounceRemindView.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/statistics/MoneyUtil.js"></script>	
		<script type="text/javascript" src="<%=basePath%>/js/system/ProvinceTreePanel.js"></script>	
	</head>
	<body >
		<div id="loading">
             <div class="loading-indicator">
                  <img src="<%=basePath%>/images/loading.gif" alt="" width="85" height="81" style="margin-left:10px;" align="absmiddle"/>
                  <div class="clear"></div>
         		    正在加载，请稍候......
             </div>
         </div>
        <div id="loading-mask"></div>
		
		<div id="app-header">
			<div id="up">
				<div id="header-left">
					<img id ="CompanyLogo" src="<%=basePath+AppUtil.getCompanyLogo()%>" height="50" style="max-width:230px;"/>
				</div>
				<div id="header-main">
					<div id="topInfoPanel" style="float:left;padding-bottom: 4px">
						<div id="welcomeMsg">欢迎您，<security:authentication property="principal.fullname"/>，[<a href="<%=basePath%>/j_logout.do">注销</a>]</div><span id='messageTipHtml'></span>
						<div id="currentTime"><span id="nowTime"></span><span id="nowTime2"></span></div>
					</div>
					<div class="clear"></div>
				</div>
				<div style="padding-right:350px;position: relative;float:right;width:250px;">
					<ul id="header-topnav">
						<li class="activeli"><a href="#" onclick="App.MyDesktopClick()" class="menu_desktop" title="桌面"><!-- 桌面 --></a></li><!--
						<li class="commonli"><a href="#" onclick="App.clickTopTab('PersonalMailBoxView')" class="menu_email" title="邮件"> 邮件 </a></li>
						--><li class="commonli"><a href="#" onclick="App.clickTopTab('CalendarPlanView')" class="menu_assign" title="任务"><!-- 任务 --></a></li>
						<!-- <li class="commonli"><a href="#" onclick="App.clickTopTab('WorkPlanView')" class="menu_plan" title="计划"></a></li> -->
						<li class="commonli"><a href="#" onclick="App.clickTopTab('BookManageView')" class="menu_doc" title="文档"><!-- 文档 --></a></li>
						<li class="commonli"><a href="#" onclick="App.clickTopTab('BbsTopicView')" class="menu_bbs" title="论坛"></a></li>
					</ul>
				</div>
				
			</div>
			
		</div>
		<div id="header-right">
					<div id="setting">
						<div style="position:relative;top:6px;padding-right:15px;float:right;">
							OA客户端<a href="<%=basePath%>/help/LJTOA v1.1_20121228Setup.zip" target="blank">通用安装包</a>|
							<a href="<%=basePath%>/help/J-IM_1_0_0.zip" target="blank">J-IM</a>
							<!-- 
							<c:if test="${IS_MANAGER ==true}">
								|&nbsp;<a href="#" onclick="App.clickTopTab('SysConfigView')">设置</a>
							</c:if>
							 -->
						</div>
		         </div>
				</div>
		<div id="down" class="down-tool">
		</div>
	</body>
</html>