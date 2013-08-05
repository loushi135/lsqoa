package com.xpsoft.oa.action.system;


import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.SendSmsUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.model.system.Smsmessage;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.SmsmessageService;
/**
 * 
 * @author 
 *
 */
public class SmsmessageAction extends BaseAction{
	@Resource
	private SmsmessageService smsmessageService;
	private Smsmessage smsmessage;
	@Resource
	private AppUserService userService;
	private AppUser user;
	private Long smsId;

	public Long getSmsId() {
		return smsId;
	}

	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}

	public Smsmessage getSmsmessage() {
		return smsmessage;
	}

	public void setSmsmessage(Smsmessage smsmessage) {
		this.smsmessage = smsmessage;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Smsmessage> list= smsmessageService.getAll(filter);
		Type type=new TypeToken<List<Smsmessage>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
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
				smsmessageService.remove(new Long(id));
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
		Smsmessage smsmessage=smsmessageService.get(smsId);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(smsmessage));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String isAddName = getRequest().getParameter("isAddName");
		String id=getRequest().getParameter("id");
		String[]Ids=id.split(",");
		String phoneNo="";
		String inceptId="";
		for(String i:Ids){
			user=userService.get(Long.valueOf(i));
			if(inceptId.length()==0){
				inceptId=i;
			}else{
				inceptId+=","+i;
			}
			if(phoneNo.length()==0){
				phoneNo=user.getMobile();
			}else{
				phoneNo+=","+user.getMobile();
			}
		}
		smsmessage.setInceptId(inceptId);
		smsmessage.setSendTime(new Date());
		smsmessage.setPhoneNo(phoneNo);
		String massage=smsmessage.getMassage();
		smsmessageService.save(smsmessage);
		if(StringUtils.isNotBlank(isAddName)){
			if("on".equals(isAddName)){
				massage+=" --"+ContextUtil.getCurrentUser().getFullname();
			}
		}
		SendSmsUtil.sendMessage(id,massage );
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
