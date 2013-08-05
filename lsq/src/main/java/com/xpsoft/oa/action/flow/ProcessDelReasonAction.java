package com.xpsoft.oa.action.flow;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.ProcessDelReason;
import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessDelReasonService;
/**
 * 
 * @author 
 *
 */
public class ProcessDelReasonAction extends BaseAction{
	@Resource
	private ProcessDelReasonService processDelReasonService;
	private ProcessDelReason processDelReason;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProcessDelReason getProcessDelReason() {
		return processDelReason;
	}

	public void setProcessDelReason(ProcessDelReason processDelReason) {
		this.processDelReason = processDelReason;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		AppUser currentUser=ContextUtil.getCurrentUser();
		boolean flag=false;
		for (AppRole appRole : currentUser.getRoles()) {
			if("-1".equals(appRole.getId())){
				flag=true;
			}
		}
		if(!flag){//不是超级管理员权限
			filter.addFilter("Q_flowCreaterId_L_EQ", currentUser.getUserId().toString());
		}
		
		List<ProcessDelReason> list= processDelReasonService.getAll(filter);
		
		Type type=new TypeToken<List<ProcessDelReason>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
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
				processDelReasonService.remove(new Long(id));
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
		ProcessDelReason processDelReason=processDelReasonService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(processDelReason));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		processDelReasonService.save(processDelReason);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
