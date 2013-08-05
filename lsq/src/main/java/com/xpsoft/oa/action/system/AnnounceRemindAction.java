package com.xpsoft.oa.action.system;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.system.AnnounceRemind;
import com.xpsoft.oa.service.system.AnnounceRemindService;
/**
 * 
 * @author 
 *
 */
public class AnnounceRemindAction extends BaseAction{
	@Resource
	private AnnounceRemindService announceRemindService;
	private AnnounceRemind announceRemind;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AnnounceRemind getAnnounceRemind() {
		return announceRemind;
	}

	public void setAnnounceRemind(AnnounceRemind announceRemind) {
		this.announceRemind = announceRemind;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		QueryFilter filter=new QueryFilter(getRequest());
		List<AnnounceRemind> list= announceRemindService.getAll(filter);
		
		Type type=new TypeToken<List<AnnounceRemind>>(){}.getType();
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
				announceRemindService.remove(new Long(id));
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
		AnnounceRemind announceRemind=announceRemindService.get(id);
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(announceRemind));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		announceRemindService.save(announceRemind);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	public String needNotice(){
		String noticeMsg = announceRemindService.needNotice(ContextUtil.getCurrentUserId());
		setJsonString("{success:true,msg:"+noticeMsg+"}");
		return SUCCESS;
	}
	public String notice(){
		String id = getRequest().getParameter("id");
		AnnounceRemind announceRemind=announceRemindService.get(Long.parseLong(id));
		if(announceRemind.getFlag().intValue() == 1){
			announceRemind.setFlag(0);
		}else{
			announceRemind.setFlag(1);
		}
		announceRemindService.save(announceRemind);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
