package com.xpsoft.core.service;

import java.util.List;
import java.util.Map;

import com.xpsoft.core.model.ProMsgDetail;
import com.xpsoft.core.model.ProMsgReceived;



public interface ProSendMsgService {

	
	/**
	 * 调用发送短信的接口
	 * 分配任务时调用
	 * 
	 */
	public void SendMsg(List<String> userList, String processId, String taskId, String taskName,String deployName, Map variables);
	
	
	
	/**
	 * 短信回复处理待办事项
	 * result:短信回复结果，同意或不同意
	 * piId：流程实例ID
	 * taskId：任务实例ID
	 * taskName：任务名称
	 * 
	 * return ：true  or  false
	 */
	public boolean doTask(String result,String resNote,String piId,String taskId,String taskName,Long userId,boolean isAuto);
	
	/**
	 * 短信回复接口
	 * @param pmr 回复信息内容实体
	 * @param pmd 回复内容对应的流程信息
	 * @return true/fasle
	 */
	public boolean msgReplyDoTask(ProMsgReceived pmr,ProMsgDetail pmd);
	
	
	/**
	 * 判断任务实例ID是否存在
	 */
	public boolean isTask(String strId);
}
