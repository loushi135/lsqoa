<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="com.xpsoft.core.command.QueryFilter"%>
<%@page import="com.xpsoft.core.web.paging.PagingBean"%>
<%@page import="com.xpsoft.oa.model.hrm.Photo"%>
<%@page import="com.xpsoft.oa.service.hrm.PhotoService"%>
<%@page import="com.xpsoft.oa.service.hrm.impl.PhotoServiceImpl"%>
<%@page import="com.xpsoft.oa.model.system.FileAttach"%>

<%
	String basePath = request.getContextPath();
%>
<html>
<head>
<title></title>
	<%-- <link rel="stylesheet" type="text/css"
			href="<%=basePath%>/jquery/jquery.slideBox/css/jquery.slideBox.css" /> --%>
    <%-- <script type="text/javascript" src="<%=basePath%>/jquery/jquery.slideBox/js/jquery.slideBox.min.js"></script> --%>
    <%-- <script type="text/javascript" src="<%=basePath%>/ext3/adapter/jquery/jquery-1.4.2.js"></script> 
    <script type="text/javascript" src="<%=basePath%>/js/SlideTrans.js"></script>--%>
<style type="text/css"> 
.container, .container img{width:98%; height:95%;}
.container img{border:0;vertical-align:top;}
</style>
<style type="text/css">
.container ul, .container li{list-style:none;margin:0;padding:0;}

.num{ position:absolute; right:5px; bottom:5px; font:12px/1.5 tahoma, arial; height:18px;}
.num li{
	float: left;
	color: #d94b01;
	text-align: center;
	line-height: 16px;
	width: 16px;
	height: 16px;
	font-family: Arial;
	font-size: 11px;
	cursor: pointer;
	margin-left: 3px;
	border: 1px solid #f47500;
	background-color: #fcf2cf;
}
.num li.on{
	line-height: 18px;
	width: 18px;
	height: 18px;
	font-size: 14px;
	margin-top:-2px;
	background-color: #ff9415;
	font-weight: bold;
	color:#FFF;
}
</style>
<style type="text/css">
     <!--
    
    div, a {
        margin: 0;
        padding: 0;
    }
    img {
        border: 0px;
    }
    .imgsBox {
        overflow: hidden;
    }
    
    .imgs a {
        display: block;
    }
    
   .clickButton {
        width:98%;
  		padding-right:10px;
        background-color: #888888;
        height: 12px;
        position: relative;
        top: -12px;
       _top: -12px;
	    filter:alpha(opacity=60);
	    opacity:0.6;zoom:1;
    }
    
    .clickButton div {
        float: right;
    }
    
    .clickButton a {
        background-color: #666;
        border-left: #ccc 1px solid;
        line-height: 12px;
        height: 12px;
        font-size: 10px;
        float: left;
        padding: 0 7px;
        text-decoration: none;
        color: #fff;
    }
    
    .clickButton a.active, .clickButton a:hover {
        background-color: #d34600;
    }
    -->
    </style>
<script>
var array = new Array();
var idArr = new Array();
var nameArr = new Array();
</script>
</head>
<body>
<%
	 PhotoService photoService = (PhotoService) AppUtil.getBean("photoService");
	 QueryFilter queryFilter = new QueryFilter(request);
	 //queryFilter.setPagingBean(new PagingBean(0,10));
	 queryFilter.addFilter("start","0");
	 queryFilter.addFilter("limit","10");
	 queryFilter.addSorted("createDate","desc");
	 List<Photo> photoList = photoService.getAll(queryFilter);
%>
	<c:set value="<%=photoList %>" var="photoList"></c:set>
	<%-- <div id="demo1" class="slideBox">
	  <ul class="items">
		  <c:forEach items="${fileSet}" var="file">
		    <li><a href="" title="${file.fileName}"><img src="<%=basePath%>/attachFiles/${file.filePath}"></a></li>
		  </c:forEach>
		  <li><a href="http://www.cnblogs.com/cloudgamer/archive/2009/12/22/ImagePreview.html"> <img src="http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_song1.jpg" alt="图片上传预览" /> </a></li>
			<li><a href="http://www.cnblogs.com/cloudgamer/archive/2009/08/10/FixedMenu.html"> <img src="http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_song2.jpg" alt="多级联动菜单" /> </a></li>
			<li><a href="http://www.cnblogs.com/cloudgamer/archive/2009/07/07/FixedTips.html"> <img src="http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_song3.jpg" alt="浮动定位提示" /> </a></li>
			<li><a href="http://www.cnblogs.com/cloudgamer/archive/2010/02/01/LazyLoad.html"> <img src="http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_song4.jpg" alt="数据延迟加载" /> </a></li>
			<li><a href="http://www.cnblogs.com/cloudgamer/archive/2009/12/01/Quick_Upload.html"> <img src="http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_song5.jpg" alt="简便文件上传" /> </a></li>
	  </ul>
	</div> --%>
	<%-- <div class="container" id="idContainer2" style="display: none">
		<ul id="idSlider2">
			 <c:forEach items="${fileSet}" var="file">
		    	<li><a href="" title="${file.fileName }"><img src="<%=basePath%>/attachFiles/${file.filePath}"></a></li>
		  	</c:forEach>
		  	<li><a href="http://www.cnblogs.com/cloudgamer/archive/2009/12/22/ImagePreview.html"> <img src="http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_song1.jpg" alt="图片上传预览" /> </a></li>
			<li><a href="http://www.cnblogs.com/cloudgamer/archive/2009/08/10/FixedMenu.html"> <img src="http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_song2.jpg" alt="多级联动菜单" /> </a></li>
			<li><a href="http://www.cnblogs.com/cloudgamer/archive/2009/07/07/FixedTips.html"> <img src="http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_song3.jpg" alt="浮动定位提示" /> </a></li>
			<li><a href="http://www.cnblogs.com/cloudgamer/archive/2010/02/01/LazyLoad.html"> <img src="http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_song4.jpg" alt="数据延迟加载" /> </a></li>
			<li><a href="http://www.cnblogs.com/cloudgamer/archive/2009/12/01/Quick_Upload.html"> <img src="http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_song5.jpg" alt="简便文件上传" /> </a></li>
		</ul>
		<ul class="num" id="idNum">
		</ul>
	</div> --%>
		<div class="imgsBox">
	<div class="imgs">
		<a id="picHref" href="javascript:void(0)" photoId="" onclick="toPhotoDetail()">
			<img id="pic" src="" width="98%" height="350" />
		</a>
	</div>
	<div id="links" class="clickButton">
		<div>
		 <c:forEach items="${photoList}" var="photo" varStatus="i">
				<script>
					array.push("<%=basePath%>/attachFiles/${photo.file.filePath}");
					idArr.push("${photo.id}");
					nameArr.push("${photo.photoName}");
				</script>
				<a class="" href="javascript:void(0)">${i.count}</a>
		  	</c:forEach>
		</div>
	</div>
</div>
	<%-- <script>
	
		setTimeout(doSomething,500);
		function doSomething(){
			$$("idContainer2").style.display = "block";
			var nums = [], timer, n = $$("idSlider2").getElementsByTagName("li").length,
			st = new SlideTrans("idContainer2", "idSlider2", n, {
				onStart: function(){//设置按钮样式
					forEach(nums, function(o, i){ o.className = st.Index == i ? "on" : ""; })
				},
				Vertical:true
			});
			for(var i = 1; i <= n; AddNum(i++,st,nums)){};
			st.Run();
		}
		function AddNum(i,st,nums){
			var num = $$("idNum").appendChild(document.createElement("li"));
			num.innerHTML = i--;
			num.onmouseover = function(){
				timer = setTimeout(function(){ num.className = "on"; st.Auto = false; st.Run(i); }, 200);
			}
			num.onmouseout = function(){ clearTimeout(timer); num.className = ""; st.Auto = true; st.Run(); }
			nums[i] = num;
		}
		
	</script> --%>
	<script type="text/javascript">
    var times = 0;
    function changeIMG(){
        if (times == array.length) {
            times = 0;
        }
        if(typeof(document.getElementById("links"))=='undefined'||document.getElementById("links")==null){
        	return;
        }
        for (i = 0; i < document.getElementById("links").getElementsByTagName("A").length; i++) {
            document.getElementById("links").getElementsByTagName("A")[i].className = "";
        }
        if(document.getElementById("links").getElementsByTagName("A").length>0){
	        document.getElementById("links").getElementsByTagName("A")[times].className = "active";
	        document.getElementById("pic").src = array[times];
	        document.getElementById("picHref").photoId = idArr[times];
        }
        times++;
    }
    
    var interval = window.setInterval(function(){
        changeIMG();
    }, 5000);
    document.getElementById("links").onmouseover = function(event){
        event = event ? event : window.event
        var obj = event.srcElement ? event.srcElement : event.target;
        if (obj.tagName == "A") {
            for (i = 0; i < document.getElementById("links").getElementsByTagName("A").length; i++) {
                document.getElementById("links").getElementsByTagName("A")[i].className = "";
            }
            obj.className = "active";
            clearInterval(interval);
            document.getElementById("pic").src = array[Number(obj.innerHTML) - 1];
            times = Number(obj.innerHTML);//改变当前图片位置
            document.getElementById("picHref").photoId = idArr[Number(obj.innerHTML) - 1];
        }
    }
    document.getElementById("links").onmouseout = function(event){
        event = event ? event : window.event
        var obj = event.srcElement ? event.srcElement : event.target;
        if (obj.tagName == "A") {
            interval = window.setInterval(function(){
                changeIMG();
            }, 5000);
        }
    }
    
    function toPhotoDetail(){
    	 var photoId = document.getElementById("picHref").photoId;
    	 if(typeof(photoId)!='undefined'&&photoId!=''){
	    	 parent.App.clickTopTab('PhotoDetail',photoId,function(){
				AppUtil.removeTab('PhotoDetail');
			});
    	 }
    }
</script>
</body>
</html>