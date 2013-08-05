package com.xpsoft.oa.action.flow;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.ProDefNotice;
import com.xpsoft.oa.service.flow.ProDefNoticeService;
/**
 * 
 * @author 
 *
 */
public class ProDefNoticeAction extends BaseAction{
	@Resource
	private ProDefNoticeService proDefNoticeService;
	private ProDefNotice proDefNotice;
	
	private Long defId;

	public Long getDefId() {
		return defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}

	public ProDefNotice getProDefNotice() {
		return proDefNotice;
	}

	public void setProDefNotice(ProDefNotice proDefNotice) {
		this.proDefNotice = proDefNotice;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ProDefNotice> list= proDefNoticeService.getAll(filter);
		
		Type type=new TypeToken<List<ProDefNotice>>(){}.getType();
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
				proDefNoticeService.remove(new Long(id));
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
		ProDefNotice proDefNotice=proDefNoticeService.get(defId);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		if(proDefNotice!=null){
			sb.append(gson.toJson(proDefNotice));
		}else{
			sb.append("''");
		}
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if((!proDefNotice.getNoticeDepIds().isEmpty())||(!proDefNotice.getNoticeRoleIds().isEmpty())||(!proDefNotice.getNoticeUserIds().isEmpty())){
			proDefNoticeService.save(proDefNotice);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
