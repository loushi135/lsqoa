package com.xpsoft.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.dao.ProMsgDetailDao;
import com.xpsoft.core.jbpm.pv.ParamField;
import com.xpsoft.core.model.ProContentMsg;
import com.xpsoft.core.model.ProMsgDetail;
import com.xpsoft.core.service.ProContentMsgService;
import com.xpsoft.core.service.ProMsgDetailService;
import com.xpsoft.core.sms.SMSFactory;
import com.xpsoft.core.sms.SMSMessageHandler;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.StringUtil;
import com.xpsoft.oa.model.system.AppUser;



public class ProMsgDetailServiceImpl extends BaseServiceImpl<ProMsgDetail>
		implements ProMsgDetailService {
	private ProMsgDetailDao dao;

	public ProMsgDetailServiceImpl(ProMsgDetailDao dao) {
		super(dao);
		this.dao = dao;
	}

	/**
	 * 
	 * 消息通知模块接口，存储相关短信提示需要的数据以及发送短信提醒
	 * 
	 * 参数： 
	 * userList：审批人
	 * processType：流程类型包含属性（该变量定义在Constants.java中）
	 * processId：流程编号
	 * taskId：任务编号
	 * taskName：任务名称 
	 * jsonBean：表单对象（使用json代替传入对象，解决对象多样性问题）
	 * dataMap : 参数  ,proDefName:流程名称，paramMap:流程参数名map
	 * 返回： boolean值，操作成功返回true
	 * */
	@Override
	public boolean insertMsgDetail(AppUser user,String isReceived, String processId, String taskId,
			String taskName, Map<String,String> conMap,Map<String,Object> dataMap) {
		boolean flag = false;
		
		//这里借用taskId来作为短信标识，方便的保证唯一性
		String strId = String.valueOf(taskId);
		
		//存储短信流程实例
		ProMsgDetail pmd = new ProMsgDetail();
		pmd.setProcessId(processId);
		pmd.setStrId(strId);
		pmd.setUserId(user.getUserId());
		pmd.setTaskId(taskId);
		pmd.setTaskName(taskName);
		//以下两个参数是用于在手机端回复时确定回复内容是不否合法
		pmd.setSucRes(strId+"1");
		pmd.setFaiRes(strId+"0");
		ProMsgDetail res = dao.save(pmd);
		
		ProContentMsgService proContentMsgService=(ProContentMsgService)AppUtil.getBean("proContentMsgService");
		//存储短信所需属性
		//List<ProContentMsg> list = new ArrayList<ProContentMsg>();
		String msgcontent = "";
		if(null!=dataMap.get("proDefName")){
			msgcontent+=dataMap.get("proDefName")+" ";
		}
		if(null!=dataMap.get("paramMap")){
			Map<String,ParamField> paramMap = (Map<String,ParamField>)dataMap.get("paramMap");
			for(ParamField pf:paramMap.values()){
				if(pf.getIsShowed().equals((short)1)&&!"file".equals(pf.getType())){//显示的字段并且不是附件
					String key = pf.getName();
					key = key.replaceAll("\\.","_");
					Object tempValue=conMap.get(key);
					String value="";
					if(tempValue instanceof Date){
						value=DateUtil.formatEnDate((Date)tempValue);
					}else if(tempValue instanceof BigDecimal){
						value=((BigDecimal)tempValue).toString();
					}else if(tempValue instanceof Integer){
						value=((Integer)tempValue).toString();
					}else{
						value=(String)tempValue;
					}
					
					ProContentMsg pcm = new ProContentMsg();
				    pcm.setName(key);
			    	pcm.setValue(value);
			    	pcm.setPromsgdetailId(res.getId());
				    proContentMsgService.save(pcm);
				    
			    	if(pf.getLabel()==null||"".equals(pf.getLabel())){
			    		msgcontent += tempValue+" ";
			    	} else {
			    		if(null==value){
			    			msgcontent += pf.getLabel()+"  ";
			    		}else{
			    			msgcontent += pf.getLabel()+":"+value+" ";
			    		}
			    	}
				}
			}
		}
	    
	    //发送短信
	    List<String> mobileList = new ArrayList<String>();
	    mobileList.add(user.getMobile());
	    
	    if("0".equals(isReceived)){
	    	msgcontent+="。您的审批表单需要您填写信息，请您登陆OA系统进行审批";
	    }else{
	    	 msgcontent+="。回复 "+strId+ "1表示审批同意，回复 "+strId+ "0表示审批不同意，如需要增加审批意见，请在后面加上井号(＃)并跟上您的意见。";
	    }
	   
//	    SMSMessageHandler sms = new SMSMessageHandler();
//	    flag = sms.sendSMS(mobileList, msgcontent);
	    flag = SMSFactory.send(mobileList, msgcontent);
	    
	    if(!flag){
	    	if(res!=null){
	    		dao.remove(res);
	    		proContentMsgService.deleteByProMsgDetailId(res.getId());
	    	}
	    }
		return flag;
	}

	@Override
	public ProMsgDetail getByStrId(String strId) {
		List<ProMsgDetail> list = dao.findByHql("from ProMsgDetail pmd where pmd.strId="+strId, null);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean checkReceivedMsgIfRight(String strId) {
		String hql = "from ProMsgDetail pmd where pmd.sucRes="+strId+" or pmd.faiRes="+strId;
		List<ProMsgDetail> list = dao.findByHql(hql, null);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
}