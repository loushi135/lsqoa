package com.xpsoft.oa.action.system;


import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.SmsGroup;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.SmsGroupService;
/**
 * 
 * @author 
 *
 */
public class SmsGroupAction extends BaseAction{
	@Resource
	private SmsGroupService smsGroupService;
	@Resource
	private AppUserService appUserService;
	private SmsGroup smsGroup;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SmsGroup getSmsGroup() {
		return smsGroup;
	}

	public void setSmsGroup(SmsGroup smsGroup) {
		this.smsGroup = smsGroup;
	}

	/**
	 * 显示列表
	 */
	public String tree(){
		
		StringBuffer buff = new StringBuffer();
		buff.append("[{text:'"+AppUtil.getCompanyName()+"',groupId:'-1',expanded:true,children:[");
		List<SmsGroup> list = smsGroupService.getAll();
		for(SmsGroup group:list){
			buff.append("{groupId:'"+group.getId()+"',text:'"+group.getGroupName()+"',leaf:true},");
		}
		if (!list.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
	    }
		buff.append("]}]");
		setJsonString(buff.toString());
		return SUCCESS;
	}
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<SmsGroup> list= smsGroupService.getAll(filter);
		Type type=new TypeToken<List<SmsGroup>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
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
				smsGroupService.remove(new Long(id));
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
		SmsGroup smsGroup=smsGroupService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(smsGroup));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	public String getUserByGroupId(){
		Set<AppUser> userSet = new HashSet<AppUser>();
		if(null!=id){
			if(-1==id){
				userSet.addAll(appUserService.getAll());
			}else{
				SmsGroup smsGroup=smsGroupService.get(id);
				userSet= smsGroup.getUserSet();
			}
		}
		Type type=new TypeToken<Set<AppUser>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(userSet.size()).append(",result:");
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		buff.append(gson.toJson(userSet, type));
		buff.append("}");
		
		jsonString=buff.toString();
		return SUCCESS;
	}
	/**
	 * 删除组成员
	 */
	public String deleteGroupUser(){
		String userIds[] = getRequest().getParameterValues("userIds");
		SmsGroup smsGroup=smsGroupService.get(id);
		for(String userId:userIds){
			if(StringUtils.isNotBlank(userId)){
				smsGroup.getUserSet().remove(appUserService.get(Long.valueOf(userId)));
			}
		}
		smsGroupService.save(smsGroup);
		StringBuffer sb = new StringBuffer("{success:true}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 增加组成员
	 */
	public String addGroupUser(){
		String smsGroupUserIds = getRequest().getParameter("smsGroupUserIds");
		if(smsGroup!=null&&smsGroup.getId()!=null){
			smsGroup = smsGroupService.get(smsGroup.getId());
			
			if(StringUtils.isNotBlank(smsGroupUserIds)){
				String userIds[] = smsGroupUserIds.split(",");
				for(String userId:userIds){
					smsGroup.getUserSet().add(appUserService.get(Long.valueOf(userId)));
				}
			}
			smsGroupService.save(smsGroup);
		}
		StringBuffer sb = new StringBuffer("{success:true}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(smsGroup.getId()!=null){
			smsGroup.setUserSet(smsGroupService.get(smsGroup.getId()).getUserSet());
			smsGroupService.evict(smsGroup);
		}else{
			smsGroupService.save(smsGroup);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
