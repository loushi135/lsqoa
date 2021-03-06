package com.xpsoft.oa.action.flow;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.TaskAgent;
import com.xpsoft.oa.service.flow.TaskAgentService;
/**
 * 
 * @author 
 *
 */
public class TaskAgentAction extends BaseAction{
	@Resource
	private TaskAgentService taskAgentService;
	private TaskAgent taskAgent;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TaskAgent getTaskAgent() {
		return taskAgent;
	}

	public void setTaskAgent(TaskAgent taskAgent) {
		this.taskAgent = taskAgent;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<TaskAgent> list= taskAgentService.getAll(filter);
		
		Type type=new TypeToken<List<TaskAgent>>(){}.getType();
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
				taskAgentService.remove(new Long(id));
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
		TaskAgent taskAgent=taskAgentService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(taskAgent));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		taskAgent.setOptDate(DateUtil.now());
		taskAgentService.save(taskAgent);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
