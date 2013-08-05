package com.xpsoft.oa.action.flow;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.flow.HandleTask;
import com.xpsoft.oa.service.flow.HandleTaskService;
/**
 * 
 * @author 
 *
 */
public class HandleTaskAction extends BaseAction{
	@Resource
	private HandleTaskService handleTaskService;
	private HandleTask handleTask;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HandleTask getHandleTask() {
		return handleTask;
	}

	public void setHandleTask(HandleTask handleTask) {
		this.handleTask = handleTask;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<HandleTask> list= handleTaskService.getAll(filter);
		
		Type type=new TypeToken<List<HandleTask>>(){}.getType();
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
				handleTaskService.remove(new Long(id));
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
		HandleTask handleTask=handleTaskService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(handleTask));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		handleTaskService.save(handleTask);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
