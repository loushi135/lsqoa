package com.xpsoft.oa.action.hrm;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;

import com.xpsoft.core.util.ContextUtil;

import com.xpsoft.core.web.action.BaseAction;

import com.xpsoft.core.web.paging.PagingBean;

import com.xpsoft.oa.model.hrm.StandSalary;

import com.xpsoft.oa.model.hrm.StandSalaryItem;

import com.xpsoft.oa.model.system.AppUser;

import com.xpsoft.oa.service.hrm.StandSalaryItemService;

import com.xpsoft.oa.service.hrm.StandSalaryService;

import java.lang.reflect.Type;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class StandSalaryAction extends BaseAction {
	private static short STATUS_DRAFT = 0;
	private static short STATUS_PASS = 1;
	private static short STATUS_NOT_PASS = 2;

	@Resource
	private StandSalaryService standSalaryService;

	@Resource
	private StandSalaryItemService standSalaryItemService;
	private StandSalary standSalary;
	private String data;
	private Long standardId;
	private String deleteItemIds;

	public String getDeleteItemIds() {
		return this.deleteItemIds;
	}

	public void setDeleteItemIds(String deleteItemIds) {
		this.deleteItemIds = deleteItemIds;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getStandardId() {
		return this.standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	public StandSalary getStandSalary() {
		return this.standSalary;
	}

	public void setStandSalary(StandSalary standSalary) {
		this.standSalary = standSalary;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<StandSalary> list = this.standSalaryService.getAll(filter);

		Type type = new TypeToken<List<StandSalary>>() {
		}.getType();
		StringBuffer buff = new StringBuffer(
				"{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(
				",result:");

		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.standSalaryService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		StandSalary standSalary = (StandSalary) this.standSalaryService
				.get(this.standardId);

		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation().create();

		StringBuffer sb = new StringBuffer("{success:true,data:[");
		sb.append(gson.toJson(standSalary));
		sb.append("]}");
		setJsonString(sb.toString());

		return "success";
	}

	public String getform() {
		StandSalary standSalary = (StandSalary) this.standSalaryService
				.get(this.standardId);

		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation().create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(standSalary));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		boolean pass = false;
		StringBuffer buff = new StringBuffer("{");
		if (this.standSalary.getStandardId() == null) {
			if (this.standSalaryService.checkStandNo(this.standSalary
					.getStandardNo()))
				pass = true;
			else
				buff.append("msg:'标准编号已存在,请重新输入.',");
		} else {
			pass = true;
		}

		if (pass) {
			if (this.standSalary.getStandardId() != null) {
				this.standSalary.setModifyName(ContextUtil
						.getCurrentUser().getFullname());
				this.standSalary.setModifyTime(new Date());
			} else {
				this.standSalary.setSetdownTime(new Date());
				this.standSalary.setFramer(ContextUtil
						.getCurrentUser().getFullname());
			}
			this.standSalary.setStatus(Short.valueOf(STATUS_DRAFT));
			if (StringUtils.isNotEmpty(this.deleteItemIds)) {
				String[] ids = this.deleteItemIds.split(",");
				for (String id : ids) {
					if (StringUtils.isNotEmpty(id)) {
						this.standSalaryItemService
								.remove(new Long(id));
					}
				}
			}
			this.standSalaryService.save(this.standSalary);
			if (StringUtils.isNotEmpty(this.data)) {
				Gson gson = new Gson();
				StandSalaryItem[] standSalaryItems = gson.fromJson(
						this.data, StandSalaryItem[].class);
				for (StandSalaryItem standSalaryItem : standSalaryItems) {
					if (standSalaryItem.getItemId().longValue() == -1L) {
						standSalaryItem.setItemId(null);
					}
					standSalaryItem.setStandardId(this.standSalary
							.getStandardId());
					this.standSalaryItemService.save(standSalaryItem);
				}
			}
			buff.append("success:true}");
		} else {
			buff.append("failure:true}");
		}
		setJsonString(buff.toString());
		return "success";
	}

	public String check() {
		String status = getRequest().getParameter("status");
		StandSalary checkStandard = (StandSalary) this.standSalaryService
				.get(this.standSalary.getStandardId());
		checkStandard.setCheckName(ContextUtil.getCurrentUser()
				.getFullname());
		checkStandard.setCheckTime(new Date());
		checkStandard.setCheckOpinion(this.standSalary
				.getCheckOpinion());
		if ((StringUtils.isNotEmpty(status))
				&& (Short.valueOf(status).shortValue() == STATUS_PASS))
			checkStandard.setStatus(Short.valueOf(STATUS_PASS));
		else {
			checkStandard.setStatus(Short.valueOf(STATUS_NOT_PASS));
		}
		this.standSalaryService.save(checkStandard);
		setJsonString("{success:true,msg:'审核通过'}");
		return "success";
	}

	public String number() {
		SimpleDateFormat date = new SimpleDateFormat(
				"yyyyMMddHHmmss-SSSS");
		String standardNo = date.format(new Date());
		setJsonString("{success:true,standardNo:'SN" + standardNo
				+ "'}");
		return "success";
	}

	public String combo() {
		List<StandSalary> list = this.standSalaryService.findByPassCheck();
		Type type = new TypeToken<List<StandSalary>>() {
		}
		.getType();
		StringBuffer buff = new StringBuffer();
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		this.jsonString = buff.toString();
		return "success";
	}

}
