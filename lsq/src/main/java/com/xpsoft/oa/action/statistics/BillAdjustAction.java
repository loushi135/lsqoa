package com.xpsoft.oa.action.statistics;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.BillAdjust;
import com.xpsoft.oa.service.statistics.BillAdjustService;
/**
 * 
 * @author 
 *
 */
public class BillAdjustAction extends BaseAction{
	@Resource
	private BillAdjustService billAdjustService;
	private BillAdjust billAdjust;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BillAdjust getBillAdjust() {
		return billAdjust;
	}

	public void setBillAdjust(BillAdjust billAdjust) {
		this.billAdjust = billAdjust;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BillAdjust> list= billAdjustService.getAll(filter);
		
		Type type=new TypeToken<List<BillAdjust>>(){}.getType();
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
				billAdjustService.remove(new Long(id));
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
		BillAdjust billAdjust=billAdjustService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(billAdjust));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		billAdjustService.saveBillAndBillAdjust(billAdjust);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
