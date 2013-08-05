package com.xpsoft.oa.action.personal;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.personal.Leaveapply;
import com.xpsoft.oa.service.personal.LeaveapplyService;

import flexjson.JSONSerializer;
/**
 * 
 * @juql 
 *
 */
public class LeaveapplyAction extends BaseAction{
	@Resource
	private LeaveapplyService leaveapplyService;
	
	private Leaveapply leaveapply;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Leaveapply getLeaveapply() {
		return leaveapply;
	}

	public void setLeaveapply(Leaveapply leaveapply) {
		this.leaveapply = leaveapply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Leaveapply> list= leaveapplyService.getAll(filter);
		
		Type type=new TypeToken<List<Leaveapply>>(){}.getType();
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
				leaveapplyService.remove(leaveapplyService.get(new Long(id)));
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
		Leaveapply leaveapply=leaveapplyService.get(id);
		Gson gson=new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		 
	     StringBuffer sb = new StringBuffer("{success:true,data:");
	     sb.append(gson.toJson(leaveapply));
	     sb.append("}");
	     setJsonString(sb.toString());
		return SUCCESS;
	}
   public String save(){
	   String userId=getRequest().getParameter("userId");
	   String deptId=getRequest().getParameter("deptId");
	   leaveapply.setUserId(new Long(userId));
	   leaveapply.setDeptId(new Long(deptId));
	   leaveapply.setApplyUser(new Long(userId));
	   leaveapplyService.save(leaveapply);
	   setJsonString("{success:true}");
	   return SUCCESS;
	}
}
