package com.xpsoft.oa.action.system;


import java.util.List;

import javax.annotation.Resource;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.BeanUtil;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.TaskAgent;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.UserFlowconfig;
import com.xpsoft.oa.service.flow.TaskAgentService;
import com.xpsoft.oa.service.system.UserFlowconfigService;

import flexjson.JSONSerializer;
/**
 * 
 * @author 
 *
 */
public class UserFlowconfigAction extends BaseAction{
	@Resource
	private TaskAgentService taskAgentService;
	@Resource
	private UserFlowconfigService userFlowconfigService;
	private UserFlowconfig userFlowconfig;
	private TaskAgent taskAgent;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserFlowconfig getUserFlowconfig() {
		return userFlowconfig;
	}

	public void setUserFlowconfig(UserFlowconfig userFlowconfig) {
		this.userFlowconfig = userFlowconfig;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<UserFlowconfig> list= userFlowconfigService.getAll(filter);
		
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer=JsonUtil.getJSONSerializer("");
		buff.append(serializer.exclude(new String[]{"class"}).serialize(list));
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
				userFlowconfigService.remove(new Long(id));
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
		UserFlowconfig userFlowconfig=userFlowconfigService.get(id);
		
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer=JsonUtil.getJSONSerializer("");
		sb.append(serializer.exclude(new String[]{"class"}).serialize(userFlowconfig));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 更新操作
	 */
	public String update(){
		
		userFlowconfigService.save(userFlowconfig);
		
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	/**
	 * 添加操作
	 */
	public String save(){
		String strUserId=getRequest().getParameter("userIds");
		String[] userIds=strUserId.split(",");
		for (int i = 0; i < userIds.length; i++) {
			UserFlowconfig temp=userFlowconfigService.getByUserId(Long.parseLong(userIds[i]));
			
			if(temp==null){
				temp=new UserFlowconfig();
			}
			AppUser user=new AppUser(Long.parseLong(userIds[i]));
			try {
				BeanUtil.copyNotNullProperties(temp, userFlowconfig);
			} catch (Exception e) {					
				logger.debug(e.getMessage());
				setJsonString("{success:false}");
				return SUCCESS;
			} 
			temp.setAppUser(user);
			userFlowconfigService.save(temp);
		}
		
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	/**
	 * 获取处理待办事项的方式
	 * @return
	 */
	public String getDoMode(){
		TaskAgent ta=null;
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUser().getUserId().toString());
		
		List<UserFlowconfig> ufList=userFlowconfigService.getAll(filter);
		if(ufList.size()>0){
			UserFlowconfig userFlowconfig=userFlowconfigService.getAll(filter).get(0);
			//将数据转成JSON格式
			StringBuffer sb = new StringBuffer("{success:true,isEmail:");
			sb.append(userFlowconfig.getIsEmail());
			sb.append(",isMsg:");
			sb.append(userFlowconfig.getIsMsg());
			sb.append(",isAgent:");
			sb.append(userFlowconfig.getIsAgent());
			sb.append(",agentName:'");
			if(userFlowconfig.getIsAgent()){
				 ta=taskAgentService.isExist(ContextUtil.getCurrentUser().getUserId().toString());
				if(null!=ta){
					sb.append(ta.getAgentAssignName());
				}
			}
			sb.append("',agentId:'");
			if(userFlowconfig.getIsAgent()){
				 //ta=taskAgentService.isExist(user.getId());
				if(null!=ta){
					sb.append(ta.getAgentAssignId());
				}
			}
			sb.append("'}");
			setJsonString(sb.toString());
		}	
		return SUCCESS;
	}
	/**
	 * 保存处理待办事项的方式
	 * @return
	 */
	public String saveDoMode(){
		UserFlowconfig userFlowconfig=null;
		String strIsEmail=getRequest().getParameter("isEmail");
		String strIsMsg=getRequest().getParameter("isMsg");
		String strIsAgent=getRequest().getParameter("isAgent");
		String agentId=getRequest().getParameter("agentId");
		String agentName=getRequest().getParameter("agentName");
		
		
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUser().getUserId().toString());
		
		List<UserFlowconfig> ufList=userFlowconfigService.getAll(filter);
		
		if(ufList.size()>0){
			userFlowconfig=ufList.get(0);
		}else{
			userFlowconfig=new UserFlowconfig();
		}
		
		if(strIsEmail!=null&&!"".equals(strIsEmail)){
			userFlowconfig.setIsEmail("on".equals(strIsEmail));
		}else{
			userFlowconfig.setIsEmail(false);
		}
		if(strIsMsg!=null&&!"".equals(strIsMsg)){
			userFlowconfig.setIsMsg("on".equals(strIsMsg));
		}else{
			userFlowconfig.setIsMsg(false);
		}
		if(strIsAgent!=null&&!"".equals(strIsAgent)){
			userFlowconfig.setIsAgent("on".equals(strIsAgent));
		}else{
			userFlowconfig.setIsAgent(false);
		}
		
		userFlowconfig.setAppUser(new AppUser(ContextUtil.getCurrentUser().getUserId()));
		userFlowconfigService.save(userFlowconfig);
		
		if("on".equals(strIsAgent)){
			if(null!=agentId&&!"".equals(agentId)&&null!=agentName&&!"".equals(agentName)){
				taskAgent=taskAgentService.isExist(ContextUtil.getCurrentUser().getUserId().toString());
				if(null!=taskAgent){
					taskAgent.setStatus(0);
					taskAgentService.save(taskAgent);
					taskAgent=new TaskAgent();
				}else{
					taskAgent=new TaskAgent();
				}
				taskAgent.setAssignId(ContextUtil.getCurrentUser().getUserId());
				taskAgent.setAssignName(ContextUtil.getCurrentUser().getFullname());
				taskAgent.setAgentAssignId(Long.parseLong(agentId));
				taskAgent.setAgentAssignName(agentName);
				taskAgent.setStatus(1);
				taskAgent.setOptDate(DateUtil.now());
				taskAgent.setRemark("用户自定义代办");
				taskAgentService.save(taskAgent);
				
			}
		}else{
			
			taskAgent=taskAgentService.isExist(ContextUtil.getCurrentUser().getUserId().toString());
			if(null!=taskAgent){
				taskAgent.setStatus(0);
				taskAgentService.save(taskAgent);
			}
		}
		
		setJsonString("{success:true}");		
		return SUCCESS;
	}

	public TaskAgent getTaskAgent() {
		return taskAgent;
	}

	public void setTaskAgent(TaskAgent taskAgent) {
		this.taskAgent = taskAgent;
	}
}
