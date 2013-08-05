package com.xpsoft.oa.action.statistics;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.BankpayapplyUpdate;
import com.xpsoft.oa.service.statistics.BankpayapplyUpdateService;
/**
 * 
 * @author 
 *
 */
public class BankpayapplyUpdateAction extends BaseAction{
	@Resource
	private BankpayapplyUpdateService bankpayapplyUpdateService;
	private BankpayapplyUpdate bankpayapplyUpdate;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BankpayapplyUpdate getBankpayapplyUpdate() {
		return bankpayapplyUpdate;
	}

	public void setBankpayapplyUpdate(BankpayapplyUpdate bankpayapplyUpdate) {
		this.bankpayapplyUpdate = bankpayapplyUpdate;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BankpayapplyUpdate> list= bankpayapplyUpdateService.getAll(filter);
		
		Type type=new TypeToken<List<BankpayapplyUpdate>>(){}.getType();
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
				bankpayapplyUpdateService.remove(new Long(id));
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
		BankpayapplyUpdate bankpayapplyUpdate=bankpayapplyUpdateService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bankpayapplyUpdate));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		bankpayapplyUpdateService.save(bankpayapplyUpdate);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
