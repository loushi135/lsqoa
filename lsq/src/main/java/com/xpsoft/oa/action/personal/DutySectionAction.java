package com.xpsoft.oa.action.personal;


import com.xpsoft.core.command.QueryFilter;

import com.xpsoft.core.util.JsonUtil;

import com.xpsoft.core.web.action.BaseAction;

import com.xpsoft.core.web.paging.PagingBean;

import com.xpsoft.oa.model.personal.DutySection;

import com.xpsoft.oa.service.personal.DutySectionService;

import flexjson.JSONSerializer;

import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;


public class DutySectionAction extends BaseAction
{
	
	@Resource
	private DutySectionService dutySectionService;
	private DutySection dutySection;
	private Long sectionId;

	
	public Long getSectionId()
	{
		return this.sectionId;
		}

	
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
		}

	
	public DutySection getDutySection() {
		return this.dutySection;
		}

	
	public void setDutySection(DutySection dutySection) {
		this.dutySection = dutySection;
		}

	
	public String list()
	{
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.dutySectionService.getAll(filter);
		
		StringBuffer buff = new StringBuffer(
				"{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(
				",result:");
		
		JSONSerializer serializer = JsonUtil
				.getJSONSerializer(new String[0]);
		buff.append(serializer.serialize(list));
		buff.append("}");
		
		this.jsonString = buff.toString();
		
		return "success";
		}

	
	public String multiDel()
	{
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.dutySectionService.remove(new Long(id));
				}
			}
		this.jsonString = "{success:true}";
		return "success";
		}

	
	public String combo() {
		StringBuffer sb = new StringBuffer();
		
		List<DutySection> dutySectionList = this.dutySectionService
				.getAll();
		sb.append("[");
		for (DutySection dutySection : dutySectionList) {
			sb.append("['").append(dutySection.getSectionId())
					.append("','").append(dutySection.getSectionName())
					.append("'],");
			}
		if (dutySectionList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
			}
		sb.append("]");
		setJsonString(sb.toString());
		return "success";
		}

	
	public String get()
	{
		DutySection dutySection = (DutySection) this.dutySectionService
				.get(this.sectionId);
		
		JSONSerializer serializer = JsonUtil
				.getJSONSerializer(new String[0]);
		
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(serializer.serialize(dutySection));
		sb.append("}");
		setJsonString(sb.toString());
		
		return "success";
		}

	
	public String save()
	{
		this.dutySectionService.save(this.dutySection);
		setJsonString("{success:true}");
		return "success";
		}
	
}

