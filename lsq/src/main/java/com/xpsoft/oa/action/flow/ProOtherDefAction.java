package com.xpsoft.oa.action.flow;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.flow.ProOtherDef;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.service.flow.ProOtherDefService;
import com.xpsoft.oa.service.flow.ProcessRunService;
/**
 * 
 * @author 
 *
 */
public class ProOtherDefAction extends BaseAction{
	@Resource
	private ProOtherDefService proOtherDefService;
	private ProOtherDef proOtherDef;
	@Resource
	private ProcessRunService processRunService;
	
	private Long proid;

	public Long getProid() {
		return proid;
	}

	public void setProid(Long proid) {
		this.proid = proid;
	}

	public ProOtherDef getProOtherDef() {
		return proOtherDef;
	}

	public void setProOtherDef(ProOtherDef proOtherDef) {
		this.proOtherDef = proOtherDef;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ProOtherDef> list= proOtherDefService.getAll(filter);
		
		Type type=new TypeToken<List<ProOtherDef>>(){}.getType();
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
				proOtherDefService.remove(new Long(id));
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
		ProOtherDef proOtherDef=proOtherDefService.get(proid);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(proOtherDef));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		proOtherDefService.save(proOtherDef);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String canPrint(){
		
		if(ContextUtil.getCurrentUser().getRights().contains("__ALL")){
			setJsonString("{success:true}");
			return SUCCESS;
		}
		
		
		if(null==getRequest().getParameter("runId")||getRequest().getParameter("runId").equals("-1")){
			setJsonString("{success:false}");
			
			return SUCCESS;
		}
		
		Long runId=Long.parseLong(getRequest().getParameter("runId"));
		
		ProcessRun processRun= processRunService.get(runId);
		
		
		
		ProOtherDef proOtherDef=proOtherDefService.get(processRun.getProDefinition().getDefId());
		
		if(processRun.getRunStatus()==2){
			if(null==proOtherDef||proOtherDef.getPrintUserIds().isEmpty()
					||proOtherDef.getPrintUserIds().contains(ContextUtil.getCurrentUserId().toString())
					){
				
				setJsonString("{success:true}");
				return SUCCESS;
			}
		}
		setJsonString("{success:false}");
		
		return SUCCESS;
	}
	
}
