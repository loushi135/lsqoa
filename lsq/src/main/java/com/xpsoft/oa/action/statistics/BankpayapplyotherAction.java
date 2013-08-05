package com.xpsoft.oa.action.statistics;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.Bankpayapplyother;
import com.xpsoft.oa.service.statistics.BankpayapplyotherService;
/**
 * 
 * @author 
 *
 */
public class BankpayapplyotherAction extends BaseAction{
	@Resource
	private BankpayapplyotherService bankpayapplyotherService;
	private Bankpayapplyother bankpayapplyother;
	
	private Long bankPayApplyId;

	public Long getBankPayApplyId() {
		return bankPayApplyId;
	}

	public void setBankPayApplyId(Long bankPayApplyId) {
		this.bankPayApplyId = bankPayApplyId;
	}

	public Bankpayapplyother getBankpayapplyother() {
		return bankpayapplyother;
	}

	public void setBankpayapplyother(Bankpayapplyother bankpayapplyother) {
		this.bankpayapplyother = bankpayapplyother;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Bankpayapplyother> list= bankpayapplyotherService.getAll(filter);
		
		Type type=new TypeToken<List<Bankpayapplyother>>(){}.getType();
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
				bankpayapplyotherService.remove(new Long(id));
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
		Bankpayapplyother bankpayapplyother=bankpayapplyotherService.get(bankPayApplyId);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bankpayapplyother));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		bankpayapplyotherService.save(bankpayapplyother);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
