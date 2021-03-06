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
import com.xpsoft.oa.model.statistics.Entrant;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.statistics.EntrantService;
import com.xpsoft.oa.service.system.FileAttachService;
/**
 * 
 * @author 
 *
 */
public class EntrantAction extends BaseAction{
	@Resource
	private EntrantService entrantService;
	private Entrant entrant;
	
	private Long entrantId;
	@Resource
	private FileAttachService fileAttachService;
	public Long getEntrantId() {
		return entrantId;
	}

	public void setEntrantId(Long entrantId) {
		this.entrantId = entrantId;
	}

	public Entrant getEntrant() {
		return entrant;
	}

	public void setEntrant(Entrant entrant) {
		this.entrant = entrant;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Entrant> list= entrantService.getAll(filter);
		
		Type type=new TypeToken<List<Entrant>>(){}.getType();
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
				entrantService.remove(new Long(id));
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
		Entrant entrant=entrantService.get(entrantId);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(entrant));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String entrantAttachIDs=getRequest().getParameter("entrantAttachIDs");
		if(StringUtils.isNotEmpty(entrantAttachIDs)){
			entrant.getEntrantFiles().clear();
			String[]fIds=entrantAttachIDs.split(",");
			for(int i=0;i<fIds.length;i++){
				FileAttach fileAttach=fileAttachService.get(new Long(fIds[i]));
				entrant.getEntrantFiles().add(fileAttach);
			}
		}
		String signName=getRequest().getParameter("signName");
		entrant.setSignName(signName);
		entrantService.save(entrant);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
