<%@ page pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.xpsoft.core.util.AppUtil"%>

<%@page import="com.xpsoft.oa.service.hrm.EmpProfileService"%>
<%@page import="com.xpsoft.oa.model.hrm.EmpProfile"%>

<%
	String profileId = request.getParameter("profileId");
	EmpProfileService empProfileService = (EmpProfileService)AppUtil.getBean("empProfileService");
	EmpProfile empProfile = empProfileService.get(new Long(profileId));
	request.setAttribute("empProfile",empProfile);
%>
<table class="table-info" style="table-layout: fixed;" cellpadding="0" cellspacing="1" width="98%" align="center">
	
		<tr>
			<td rowspan="9" width="15%">
				&nbsp;<img title="${empProfile.fullname}" width="88" height="120" src="<%=request.getContextPath()%>/attachFiles/${empProfile.photo}">
			</td>
		</tr>
		<tr >
			<th width="15%">
				档案号
			</th >
			<td colspan="3">
					${empProfile.profileNo}
					<c:if test="${empProfile.approvalStatus==1}"><img title="通过审核" src="<%=basePath%>/images/flag/customer/effective.png"/></c:if>
				   <c:if test="${empProfile.approvalStatus==2}"><img title="没通过审核" src="<%=basePath%>/images/flag/customer/invalid.png"/></c:if>
			</td>
		</tr>
		
		<tr>
			<th width="15%">
				姓名
			</th>
			<td colspan="3">
				${empProfile.fullname }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				身份证号
			</th>
			<td>
				${empProfile.idCard }
			</td>
			<th width="20%">
				政治面貌
			</th>
			<td>
				${empProfile.dicParty.itemValue }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				出生日期
			</th>
			<td>
			<fmt:formatDate value="${empProfile.birthday}" pattern="yyyy-MM-dd"/>
			</td>
			<th width="20%">
				国籍
			</th>
			<td>
				${empProfile.dicNationality.itemValue }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				性别
			</th>
			<td>
				${empProfile.sex }
			</td>
			<th width="20%">
				民族
			</th>
			<td>
				${empProfile.dicRace.itemValue }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				婚姻状况
			</th>
			<td>
				${empProfile.dicMarriage.itemValue }
			</td>
			<th width="20%">
				家庭地址
			</th>
			<td>
				${empProfile.address }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				编制
			</th>
			<td>
				${empProfile.dicPlait.itemValue }
			</td>
			<th width="20%">
				职位
			</th>
			<td>
				${empProfile.job.jobName }
			</td>
		</tr>
		<tr>
			<th width="20%">
				职类
			</th>
			<td>
				${empProfile.dicProfessionType.itemValue }
			</td>
			<th width="20%">
				职级
			</th>
			<td>
				${empProfile.dicProfessionLevel.itemValue }
			</td>
		</tr>
</table>
<table class="table-info" style="table-layout: fixed;word-spacing: normal;" cellpadding="0" cellspacing="1" width="98%" align="center">
		<tr >
			<th width="15%">
				家乡
			</th>
			<td>
				${empProfile.homeAddr }
			</td>
			<th width="15%">
				劳动合同类型
			</th>
			<td>
				${empProfile.dicWorkContractType.itemValue }
			</td>
		</tr>
		<tr>
			<th width="15%">
				学历
			</th>
			<td>
				${empProfile.dicEduDegree.itemValue }
			</td>
			<th  width="20%">
				毕业院校
			</th>
			<td>
				${empProfile.eduCollege }
			</td>
		</tr>
		<tr>
			<th width="15%">
				专业
			</th>
			<td>
				${empProfile.eduMajor }
			</td>
			<th  width="20%">
				参加工作时间
			</th>
			<td>
				<fmt:formatDate value="${empProfile.startWorkDate}" pattern="yyyy-MM-dd"/>
			</td>
		</tr>
		<tr>
			<th width="15%">
				毕业时间
			</th>
			<td>
				<fmt:formatDate value="${empProfile.graduateDate}" pattern="yyyy-MM-dd"/>
			</td>
			<th  width="20%">
				档案所在机构
			</th>
			<td>
				${empProfile.profilePlace }
			</td>
		</tr>
		<tr>
			<th width="15%">
				补发工资时间
			</th>
			<td>
				<fmt:formatDate value="${empProfile.rePayWageTime}" pattern="yyyy-MM-dd"/>
			</td>
			<th  width="20%">
				人事职称描述:
			</th>
			<td>
				${empProfile.hrmTitleDesc }
			</td>
		</tr>
		<tr>
			<th width="15%">
				人事职称
			</th>
			<td>
				${empProfile.dicHrmTitle.itemValue }
			</td>
			<th  width="20%">
				职级级别
			</th>
			<td>
				${empProfile.dicHrmTitleLevel.itemValue }
			</td>
		</tr>
		<tr>
			<th width="15%">
				健康状况
			</th>
			<td>
				${empProfile.healthStatus }
			</td>
			<th  width="20%">
				血型
			</th>
			<td>
				${empProfile.dicBloodType.itemValue }
			</td>
		</tr>
		<tr>
			<th width="20%">
				宅电
			</th>
			<td>
				${empProfile.homeTel}
			</td>
			<th width="20%">
				其他电话
			</th>
			<td>
				${empProfile.otherTel }
			</td>
		</tr>
		<tr>
			<th width="20%">
				新员工培训批次
			</th>
			<td>
				${empProfile.newStaffTrain }
			</td>
			<th width="20%">
				审批录入年月
			</th>
			<td>
				${empProfile.approvalTime }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				统计批次
			</th>
			<td>
				${empProfile.statisticsTime }
			</td>
			<th width="20%">
				内评职称/其他证书
			</th>
			<td>
				${empProfile.innerCommentTitle }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				新版承诺
			</th>
			<td>
				${empProfile.dicNewTypeCommitment.itemValue }
			</td>
			<th width="20%">
				项目经理等级
			</th>
			<td>
				${empProfile.dicProManagerLevel.itemValue }
			</td>
		</tr>
		<tr>
			<th width="20%">
				社保缴纳地
			</th>
			<td>
				${empProfile.dicSocialSecurityPlace.itemValue }
			</td>
			<th width="20%">
				拜师仪式
			</th>
			<td>
				${empProfile.masterCeremony }
			</td>
		</tr>
		<tr>
			<th width="20%">
				备注
			</th>
			<td colspan="3">
				${empProfile.memo }
			</td>
		</tr>
</table>
<c:if test="${empProfile.approvalStatus==1||empProfile.approvalStatus==2}">
<table class="table-info" style="table-layout: fixed;" cellpadding="0" cellspacing="1" width="98%" align="center">
 <tr>
		<th width="15%">
			建档人
		</th>
		<td>
			${empProfile.creator }
		</td>
		<th width="15%">
			建档时间
		</th>
		<td>
			<fmt:formatDate value="${empProfile.createtime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th width="15%">
			审核人
		</th>
		<td>
			${empProfile.checkName}
		</td>
		<th width="15%">
			审核时间
		</th>
		<td>
			<fmt:formatDate value="${empProfile.checktime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th width="15%">
			审核意见
		</th>
		<td colspan="3">
			<c:if test="${empProfile.approvalStatus!=0}">${empProfile.opprovalOpinion }</c:if>
		</td>
	</tr>
</table>
</c:if>	
	
	