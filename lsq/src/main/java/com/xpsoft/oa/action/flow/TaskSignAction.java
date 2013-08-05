

package com.xpsoft.oa.action.flow;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.BeanUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.ProUserAssign;
import com.xpsoft.oa.model.flow.TaskSign;
import com.xpsoft.oa.service.flow.ProUserAssignService;
import com.xpsoft.oa.service.flow.TaskSignService;

public class TaskSignAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private TaskSignService taskSignService;
	@Resource
	private ProUserAssignService proUserAssignService;
	private TaskSign taskSign;
	private Long signId;
	private Long assignId;

	public TaskSignAction()
	{
	}

	public TaskSign getTaskSign() {
		return taskSign;
	}
	public void setTaskSign(TaskSign taskSign) {
		this.taskSign = taskSign;
	}
	public Long getSignId() {
		return signId;
	}
	public void setSignId(Long signId) {
		this.signId = signId;
	}
	public Long getAssignId() {
		return assignId;
	}
	public void setAssignId(Long assignId) {
		this.assignId = assignId;
	}
	
	
	public String list()
	{
		QueryFilter queryfilter = new QueryFilter(getRequest());
		List<TaskSign> list = taskSignService.getAll(queryfilter);
		
		Type type=new TypeToken<List<TaskSign>>(){}.getType();
		
		StringBuffer stringbuffer = (new StringBuffer("{success:true,'totalCounts':")).append(queryfilter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson = new Gson();
		stringbuffer.append(gson.toJson(list, type));
		stringbuffer.append("}");
		jsonString = stringbuffer.toString();
		return "success";
	}

	public String multiDel()
	{
		String as[] = getRequest().getParameterValues("ids");
		if (as != null)
		{
			String as1[] = as;
			int i = as1.length;
			for (int j = 0; j < i; j++)
			{
				String s = as1[j];
				taskSignService.remove(new Long(s));
			}

		}
		jsonString = "{success:true}";
		return "success";
	}

	public String get()
	{
		TaskSign tasksign = (TaskSign)taskSignService.get(signId);
		Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd").create();
		StringBuffer stringbuffer = new StringBuffer("{success:true,data:");
		stringbuffer.append(gson.toJson(tasksign));
		stringbuffer.append("}");
		setJsonString(stringbuffer.toString());
		return "success";
	}

	public String find()
	{
		TaskSign tasksign = taskSignService.findByAssignId(assignId);
		Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd").create();
		StringBuffer stringbuffer = new StringBuffer("{success:true,data:");
		if (tasksign != null)
			stringbuffer.append(gson.toJson(tasksign));
		else
			stringbuffer.append("[]");
		stringbuffer.append("}");
		setJsonString(stringbuffer.toString());
		return "success";
	}

	public String save()
	{
		if (taskSign.getSignId() == null)
		{
			ProUserAssign prouserassign = (ProUserAssign)proUserAssignService.get(assignId);
			taskSign.setProUserAssign(prouserassign);
			taskSignService.save(taskSign);
		} else
		{
			TaskSign tasksign = (TaskSign)taskSignService.get(taskSign.getSignId());
			try
			{
				BeanUtil.copyNotNullProperties(tasksign, taskSign);
				taskSignService.save(tasksign);
			}
			catch (Exception exception)
			{
				logger.error(exception.getMessage());
			}
		}
		setJsonString("{success:true}");
		return "success";
	}
}
