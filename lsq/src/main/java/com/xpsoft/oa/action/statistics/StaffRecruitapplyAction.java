package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.StaffRecruitapply;
import com.xpsoft.oa.model.system.Dictionary;
import com.xpsoft.oa.service.statistics.StaffRecruitapplyService;
import com.xpsoft.oa.service.system.DictionaryService;
/**
 * 
 * @author 
 *
 */
public class StaffRecruitapplyAction extends BaseAction{
	@Resource
	private StaffRecruitapplyService staffRecruitapplyService;
	@Resource
	private DictionaryService dictionaryService;
	private StaffRecruitapply staffRecruitapply;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StaffRecruitapply getStaffRecruitapply() {
		return staffRecruitapply;
	}

	public void setStaffRecruitapply(StaffRecruitapply staffRecruitapply) {
		this.staffRecruitapply = staffRecruitapply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<StaffRecruitapply> list= staffRecruitapplyService.getAll(filter);
		
		Type type=new TypeToken<List<StaffRecruitapply>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				staffRecruitapplyService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		StaffRecruitapply staffRecruitapply=staffRecruitapplyService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(staffRecruitapply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		
		String mainPositionIDs = getRequest().getParameter("mainPositionIDs");
		if(StringUtils.isNotBlank(mainPositionIDs)){
			String[] dicIds = mainPositionIDs.split(",");
			Set<Dictionary> dicSet = new HashSet<Dictionary>();
			for(String dicId:dicIds){
				dicSet.add(dictionaryService.get(Long.valueOf(dicId)));
			}
			staffRecruitapply.setMainPositionDics(dicSet);
		}
		staffRecruitapplyService.save(staffRecruitapply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
