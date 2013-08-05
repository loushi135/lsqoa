package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.hrm.Job;
import com.xpsoft.oa.model.statistics.StaffChangejobapply;
import com.xpsoft.oa.service.hrm.JobService;
import com.xpsoft.oa.service.statistics.StaffChangejobapplyService;
/**
 * 
 * @author 
 *
 */
public class StaffChangejobapplyAction extends BaseAction{
	@Resource
	private StaffChangejobapplyService staffChangejobapplyService;
	@Resource
	private JobService jobService;
	private StaffChangejobapply staffChangejobapply;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StaffChangejobapply getStaffChangejobapply() {
		return staffChangejobapply;
	}

	public void setStaffChangejobapply(StaffChangejobapply staffChangejobapply) {
		this.staffChangejobapply = staffChangejobapply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<StaffChangejobapply> list= staffChangejobapplyService.getAll(filter);
		
		Type type=new TypeToken<List<StaffChangejobapply>>(){}.getType();
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
				staffChangejobapplyService.remove(new Long(id));
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
		StaffChangejobapply staffChangejobapply=staffChangejobapplyService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(staffChangejobapply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(staffChangejobapply.getId()==null){//新增  查询出oldJobId
			String oldJobName = getRequest().getParameter("oldJobName");
			Long oldDepId = staffChangejobapply.getOldDept().getDepId();
			Job oldJob = jobService.findByDepAndJobName(oldDepId, oldJobName);
			staffChangejobapply.setOldJob(oldJob);
		}
		staffChangejobapplyService.save(staffChangejobapply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
