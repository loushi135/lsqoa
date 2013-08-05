package com.xpsoft.oa.action.statistics;



import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.StaffActiveapply;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.statistics.StaffActiveapplyService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.FileAttachService;



/**
 * 
 * @author 
 *
 */
public class StaffActiveapplyAction extends BaseAction{
	@Resource
	private StaffActiveapplyService staffActiveapplyService;
	private StaffActiveapply staffActiveapply;
	@Resource
	private AppUserService appUserService;
	
	private Long id;
	@Resource
	private FileAttachService fileAttachService;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StaffActiveapply getStaffActiveapply() {
		return staffActiveapply;
	}

	public void setStaffActiveapply(StaffActiveapply staffActiveapply) {
		this.staffActiveapply = staffActiveapply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<StaffActiveapply> list= staffActiveapplyService.getAll(filter);
		
		Type type=new TypeToken<List<StaffActiveapply>>(){}.getType();
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
				staffActiveapplyService.remove(new Long(id));
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
		StaffActiveapply staffActiveapply=staffActiveapplyService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(staffActiveapply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
//		String staffActiveAttachIDs=getRequest().getParameter("staffActiveAttachIDs");
//		if(StringUtils.isNotEmpty(staffActiveAttachIDs)){
//			staffActiveapply.getStaffApplyFiles().clear();
//			String[]fIds=staffActiveAttachIDs.split(",");
//			for(int i=0;i<fIds.length;i++){
//				FileAttach fileAttach=fileAttachService.get(new Long(fIds[i]));
//				staffActiveapply.getStaffApplyFiles().add(fileAttach);
//			}
//		}
		if(null==staffActiveapply.getApplyUser().getUserId()){
			setJsonString("{success:false}");
			return SUCCESS;
		}else {
			staffActiveapply.setApplyUser(appUserService.get(staffActiveapply.getApplyUser().getUserId()));
		}
		staffActiveapplyService.save(staffActiveapply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
