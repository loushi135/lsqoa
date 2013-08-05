package com.xpsoft.oa.action.statistics;



import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.StaffLeaveapply;
import com.xpsoft.oa.service.statistics.StaffLeaveapplyService;

/**
 * 
 * @author 
 *
 */
public class StaffLeaveapplyAction extends BaseAction{
	@Resource
	private StaffLeaveapplyService staffLeaveapplyService;
	private StaffLeaveapply staffLeaveapply;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StaffLeaveapply getStaffLeaveapply() {
		return staffLeaveapply;
	}

	public void setStaffLeaveapply(StaffLeaveapply staffLeaveapply) {
		this.staffLeaveapply = staffLeaveapply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<StaffLeaveapply> list= staffLeaveapplyService.getAll(filter);
		
		Type type=new TypeToken<List<StaffLeaveapply>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_YMD).create();
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
				staffLeaveapplyService.remove(new Long(id));
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
		StaffLeaveapply staffLeaveapply=staffLeaveapplyService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(staffLeaveapply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String signName=getRequest().getParameter("signName");
		staffLeaveapply.setSignDate(new Date());
		staffLeaveapply.setSignName(signName);
		staffLeaveapplyService.save(staffLeaveapply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
