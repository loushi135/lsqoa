package com.xpsoft.oa.action.hrm;

import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.hrm.EmpProfile;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.hrm.EmpProfileService;

import flexjson.DateTransformer;
import flexjson.JSONSerializer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class EmpProfileAction extends BaseAction {

	@Resource
	private EmpProfileService empProfileService;
	private EmpProfile empProfile;
	private Long profileId;

	public Long getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public EmpProfile getEmpProfile() {
		return this.empProfile;
	}

	public void setEmpProfile(EmpProfile empProfile) {
		this.empProfile = empProfile;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());

		List list = this.empProfileService.getAll(filter);

		StringBuffer buff = new StringBuffer(
				"{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(
				",result:");

		JSONSerializer serializer = JsonUtil
				.getJSONSerializer(new String[] { "birthday", "startWorkDate",
						"checktime", "createtime","workContractEndDate" });
		buff.append(serializer.exclude(
				new String[] { "class", "job.class", "job.department" })
				.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				EmpProfile deletePro = (EmpProfile) this.empProfileService
						.get(new Long(id));
				deletePro.setDelFlag(Short
						.valueOf(EmpProfile.DELETE_FLAG_HAD));
				this.empProfileService.save(deletePro);
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		EmpProfile empProfile = (EmpProfile) this.empProfileService
				.get(this.profileId);

		JSONSerializer json = new JSONSerializer();
		json.transform(new DateTransformer(Constants.DATE_FORMAT_YMD), new String[] {
			"birthday", "startWorkDate", "createtime", "checktime","graduateDate","rePayWageTime","workContractEndDate" });
		StringBuffer sb = new StringBuffer("{success:true,data:");

		sb.append(json.exclude(new String[] { "class"}).include("dicEduDegree",
				"dicWorkContractType","dicMarriage","dicParty","dicNationality","dicRace",
				"dicBloodType","dicPlait","dicProfessionType","dicProfessionLevel","dept","appUser","job",
				"dicHrmTitle","dicHrmTitleLevel","dicNewTypeCommitment","dicProManagerLevel","dicSocialSecurityPlace")
				.serialize(empProfile));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String save() {
		boolean pass = false;
		StringBuffer buff = new StringBuffer("{");
		if (this.empProfile.getProfileId() == null) {
//			if (this.empProfileService.checkProfileNo(this.empProfile
//					.getProfileNo())) {
				this.empProfile.setCreator(ContextUtil
						.getCurrentUser().getFullname());
				this.empProfile.setCreatetime(new Date());
				this.empProfile.setDelFlag(Short
						.valueOf(EmpProfile.DELETE_FLAG_NOT));
				pass = true;
//			} else {
//				buff.append("msg:'档案编号已存在,请重新输入.',");
//			}
		}
		else
			pass = true;

		if (pass) {
			this.empProfile.setApprovalStatus(Short
					.valueOf(EmpProfile.CHECK_FLAG_NONE));
			this.empProfileService.save(this.empProfile);
			buff.append("success:true}");
		} else {
			buff.append("failure:true}");
		}
		setJsonString(buff.toString());
		return "success";
	}

	public String number() {
		SimpleDateFormat date = new SimpleDateFormat(
				"yyyyMMddHHmmss-SSSS");
		String profileNo = date.format(new Date());
		setJsonString("{success:true,profileNo:'PN" + profileNo + "'}");
		return "success";
	}

	public String check() {
		EmpProfile checkProfile = (EmpProfile) this.empProfileService
				.get(this.profileId);
		checkProfile.setCheckName(ContextUtil.getCurrentUser()
				.getFullname());
		checkProfile.setChecktime(new Date());
		checkProfile.setApprovalStatus(this.empProfile
				.getApprovalStatus());
		checkProfile.setOpprovalOpinion(this.empProfile
				.getOpprovalOpinion());
		this.empProfileService.save(checkProfile);
		setJsonString("{success:true,msg:'审核通过'}");
		return "success";
	}

	public String recovery() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				EmpProfile deletePro = (EmpProfile) this.empProfileService
						.get(new Long(id));
				deletePro.setDelFlag(Short
						.valueOf(EmpProfile.DELETE_FLAG_NOT));
				this.empProfileService.save(deletePro);
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String delphoto() {
		if (this.profileId != null) {
			this.empProfile = ((EmpProfile) this.empProfileService
					.get(this.profileId));
			this.empProfile.setPhoto("");
			this.empProfileService.save(this.empProfile);
			this.jsonString = "{success:true}";
		}
		return "success";
	}
}

