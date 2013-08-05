package com.xpsoft.oa.action.statistics;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.StaffEmployapply;
import com.xpsoft.oa.service.statistics.StaffEmployapplyService;
/**
 * 
 * @author 
 *
 */
public class StaffEmployapplyAction extends BaseAction{
	@Resource
	private StaffEmployapplyService staffEmployapplyService;
	private StaffEmployapply staffEmployapply;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StaffEmployapply getStaffEmployapply() {
		return staffEmployapply;
	}

	public void setStaffEmployapply(StaffEmployapply staffEmployapply) {
		this.staffEmployapply = staffEmployapply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<StaffEmployapply> list= staffEmployapplyService.getAll(filter);
		
		Type type=new TypeToken<List<StaffEmployapply>>(){}.getType();
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
				staffEmployapplyService.remove(new Long(id));
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
		StaffEmployapply staffEmployapply=staffEmployapplyService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(staffEmployapply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(staffEmployapply.getInviteUser()==null||staffEmployapply.getInviteUser().getUserId()==null){
			staffEmployapply.setInviteUser(null);
		}
		staffEmployapplyService.save(staffEmployapply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
