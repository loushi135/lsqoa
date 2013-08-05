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
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.TicketApply;
import com.xpsoft.oa.model.statistics.TicketIdno;
import com.xpsoft.oa.service.statistics.TicketApplyService;
import com.xpsoft.oa.service.statistics.TicketIdnoService;
/**
 * 
 * @author 
 *
 */
public class TicketApplyAction extends BaseAction{
	@Resource
	private TicketApplyService ticketApplyService;
	private TicketApply ticketApply;
	@Resource
	private TicketIdnoService ticketIdnoService;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TicketApply getTicketApply() {
		return ticketApply;
	}

	public void setTicketApply(TicketApply ticketApply) {
		this.ticketApply = ticketApply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<TicketApply> list= ticketApplyService.getAll(filter);
		
		Type type=new TypeToken<List<TicketApply>>(){}.getType();
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
				ticketApplyService.remove(new Long(id));
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
		TicketApply ticketApply=ticketApplyService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(ticketApply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
	    String ticketDataList =getRequest().getParameter("ticketDataList");
		if(ticketApply.getProject()==null||ticketApply.getProject().getId()==null){
			ticketApply.setProject(null);
		}
		ticketApplyService.save(ticketApply);
		if(StringUtils.isNotBlank(ticketDataList)){
			List<TicketIdno> ticketIdnoList = JsonUtil.fromJsonTypesWithDate(ticketDataList,
					new TypeToken<List<TicketIdno>>() {
			}.getType());
			for (TicketIdno ticketIdno:ticketIdnoList) {
				ticketIdno.setTicketApply(ticketApply);
				ticketIdnoService.save(ticketIdno);
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String change(){
		TicketApply oldTicketApply = ticketApplyService.get(ticketApply.getId());
		oldTicketApply.setStatus(ticketApply.getStatus());
		if(ticketApply.getStatus().intValue() == 0){
			oldTicketApply.setDeparture(ticketApply.getDeparture());
			oldTicketApply.setDestination(ticketApply.getDestination());
			oldTicketApply.setDepartureTime(ticketApply.getDepartureTime());
			oldTicketApply.setDepartureDetail(ticketApply.getDepartureDetail());
			oldTicketApply.setBackOrNot(ticketApply.getBackOrNot());
			oldTicketApply.setBackTime(ticketApply.getBackTime());
			oldTicketApply.setBackDetail(ticketApply.getBackDetail());
			oldTicketApply.setAmount(ticketApply.getAmount());
			oldTicketApply.setAmountBig(ticketApply.getAmountBig());
		}
		ticketApplyService.merge(oldTicketApply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
