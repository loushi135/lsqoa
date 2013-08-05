package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.Paybase;
import com.xpsoft.oa.service.statistics.PaybaseService;
/**
 * 
 * @author 
 *
 */
public class PaybaseAction extends BaseAction{
	@Resource
	private PaybaseService paybaseService;
	private Paybase paybase;
	
	private Long paymentBaseId;

	public Long getPaymentBaseId() {
		return paymentBaseId;
	}

	public void setPaymentBaseId(Long paymentBaseId) {
		this.paymentBaseId = paymentBaseId;
	}

	public Paybase getPaybase() {
		return paybase;
	}

	public void setPaybase(Paybase paybase) {
		this.paybase = paybase;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Paybase> list= paybaseService.getAll(filter);
		
		Type type=new TypeToken<List<Paybase>>(){}.getType();
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
				paybaseService.remove(new Long(id));
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
		Paybase paybase=paybaseService.get(paymentBaseId);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(paybase));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		paybase.setCreatetime(new Date());
		paybase.setCreateUser(ContextUtil.getCurrentUser());
		paybaseService.save(paybase);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
