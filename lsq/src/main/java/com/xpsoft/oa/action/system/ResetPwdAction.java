package com.xpsoft.oa.action.system;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.sms.SMSFactory;
import com.xpsoft.core.sms.SMSMessageHandler;
import com.xpsoft.core.sms.SmsClientStart;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.StringUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.hrm.EmpProfile;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.ResetPwd;
import com.xpsoft.oa.service.hrm.EmpProfileService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.ResetPwdService;
/**
 * 
 * @author 
 *
 */
public class ResetPwdAction extends BaseAction{
	@Resource
	private ResetPwdService resetPwdService;
	private ResetPwd resetPwd;
	@Resource
	private AppUserService userService;
	@Resource
	private EmpProfileService empProfileService;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ResetPwd getResetPwd() {
		return resetPwd;
	}

	public void setResetPwd(ResetPwd resetPwd) {
		this.resetPwd = resetPwd;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ResetPwd> list= resetPwdService.getAll(filter);
		
		Type type=new TypeToken<List<ResetPwd>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
		.setDateFormat(Constants.DATE_FORMAT_YMD).create();
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
				resetPwdService.remove(new Long(id));
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
		ResetPwd resetPwd=resetPwdService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(resetPwd));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		
		String userId=getRequest().getParameter("resetPwd.userId");
		String own=getRequest().getParameter("own");
		
		AppUser appUser=userService.get(Long.valueOf(userId));
		
		if(appUser.getUsername().equals("admin")){
			setJsonString("{failure:true,data:'此用户禁止重置密码'}");
			return SUCCESS;
		}
		
		resetPwd.setTimeCreate(new Date());
		resetPwd.setUserFullName(appUser.getFullname());
		resetPwd.setUserLoginName(appUser.getUsername());
		if(!"yes".equals(own)){
			resetPwd.setOpeaterUser(ContextUtil.getCurrentUser());
		}
		
		
		appUser.setPassword(StringUtil.encryptSha256(resetPwd.getResetPWD()));
		
		resetPwdService.resetPwd(appUser,resetPwd);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	/**
	 * 忘记密码
	 * @return
	 */
	public String forgot(){
		
		String email=getRequest().getParameter("email");
		String idCardNO=getRequest().getParameter("idCardNO");
		String workNO=getRequest().getParameter("workNO");
		
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.username_S_EQ", resetPwd.getUserLoginName());
		
		List<EmpProfile> listE= empProfileService.getAll(filter);
		String failMsg="{success:false,data:'填写信息不一至,请确认！'}";
		if(listE.isEmpty()){
			setJsonString(failMsg);
		}else{
			EmpProfile profile=listE.get(0);
			if((null==profile.getIdCard()||profile.getIdCard().equals(idCardNO))&&profile.getAppUser().getStaffNo().equals(workNO)
					&&profile.getAppUser().getEmail().equals(email)&&profile.getAppUser().getFullname().equals(resetPwd.getUserFullName())){
				
				//发短信给管理员
				List<String> list = new ArrayList<String>();
				
				AppUser admin=userService.get(1L);
				
				list.add(admin.getMobile());
		
				StringBuffer content=new StringBuffer("【密码找回】：");
				content.append("姓名：").append(resetPwd.getUserFullName());
				content.append("登陆名：").append(resetPwd.getUserLoginName());
		
				boolean res = SMSFactory.send(list, content.toString());
			
				if(res){
					resetPwd.setOpeaterUser(null);
					resetPwd.setTimeCreate(new Date());
					
					resetPwdService.save(resetPwd);
				}else{
					setJsonString("{success:false,data:'操作失败，请重新操作！'}");
				}
				
				setJsonString("{success:true}");
			}else{
				setJsonString(failMsg);
			}
		}
		
		return SUCCESS;
	}
}
