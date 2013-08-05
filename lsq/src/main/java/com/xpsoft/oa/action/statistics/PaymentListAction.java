package com.xpsoft.oa.action.statistics;


import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.PaymentList;
import com.xpsoft.oa.service.statistics.PaymentListService;
/**
 * 
 * @author 
 *
 */
public class PaymentListAction extends BaseAction{
	@Resource
	private PaymentListService paymentListService;
	private PaymentList paymentList;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentList getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(PaymentList paymentList) {
		this.paymentList = paymentList;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<PaymentList> list= paymentListService.getAll(filter);
		
		Type type=new TypeToken<List<PaymentList>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_YMD).excludeFieldsWithoutExposeAnnotation().create();
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
				paymentListService.remove(new Long(id));
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
		PaymentList paymentList=paymentListService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_YMD).excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(paymentList));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(paymentList.getProject()==null||paymentList.getProject().getId()==null){
			paymentList.setProject(null);
		}
		paymentListService.save(paymentList);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	/**
	 * 得到目前欠款金额
	 * @return
	 */
	public String getOwedSum(){
		String proId = getRequest().getParameter("proId");
		String userId = getRequest().getParameter("userId");
		BigDecimal owedSum = paymentListService.getOwedSum(proId,userId);
		setJsonString("{success:true,owedSum:'"+owedSum+"'}");
		return SUCCESS;
	}
}
