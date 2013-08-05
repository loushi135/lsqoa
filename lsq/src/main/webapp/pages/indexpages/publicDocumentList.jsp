<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="public" items="${publicDocumentList}">
		<tr>
			<td width="26">
			      <img src="<%=request.getContextPath()%>/images/menus/document/personal_doc.png" />
		    </td>
			<td><a id="wwe" href="#" onclick='clickToDetail(${public.docId})'>${public.docName}</a></td>
			<td width="80" nowrap="nowrap"><a>${public.fullname}</a></td>
			<td width="80" nowrap="nowrap"><a><fmt:formatDate
				value="${public.createtime}" pattern="yyyy-MM-dd" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.MyDesktopClickTopTab('FindPublicDocumentView')">更多...</a></span></div>
	
	
<script>
	function clickToDetail(docId){
		var tabs = Ext.getCmp("centerTabPanel");
		var tabItem = tabs.getItem('PulicDocumentDetail');
		if (tabItem == null) {
			$ImportSimpleJs([
				__ctxPath + "/js/document/PublicDocumentView.js",
				__ctxPath + "/js/document/PublicDocumentDetail.js"
				], function() {
				PublicDocumentView.detail(docId);
			});
		}else{
			PublicDocumentView.detail(docId);
		}
		
	}
</script>