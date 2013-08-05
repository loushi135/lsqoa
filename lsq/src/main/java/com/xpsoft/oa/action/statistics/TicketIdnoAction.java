package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.ModelTicket;
import com.xpsoft.oa.model.statistics.TicketIdno;
import com.xpsoft.oa.service.statistics.TicketIdnoService;
/**
 * 
 * @author 
 *
 */
public class TicketIdnoAction extends BaseAction{
	@Resource
	private TicketIdnoService ticketIdnoService;
	private TicketIdno ticketIdno;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TicketIdno getTicketIdno() {
		return ticketIdno;
	}

	public void setTicketIdno(TicketIdno ticketIdno) {
		this.ticketIdno = ticketIdno;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		String ticketId =getRequest().getParameter("ticketId");
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_ticketApply.id_L_EQ", ticketId);
		List<TicketIdno> list= ticketIdnoService.getAll(filter);
		List<ModelTicket> ticketList = new ArrayList<ModelTicket>();
		for(int i=0;i<list.size();i++){
			ModelTicket modelTicket =new ModelTicket();
			modelTicket.setId(list.get(i).getId());
			modelTicket.setIdCard(list.get(i).getIdCard());
			modelTicket.setUserId(list.get(i).getUserId());
			modelTicket.setUserName(list.get(i).getUserName());
			ticketList.add(modelTicket);
		}
		Type type=new TypeToken<List<ModelTicket>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(ticketList, type));
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
				ticketIdnoService.remove(new Long(id));
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
		TicketIdno ticketIdno=ticketIdnoService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(ticketIdno));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		ticketIdnoService.save(ticketIdno);
		setJsonString("{success:true}");
		return SUCCESS;
	}
		
}
