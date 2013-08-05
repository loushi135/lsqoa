package com.xpsoft.oa.action.hrm;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.hrm.TrainReport;
import com.xpsoft.oa.service.hrm.TrainReportService;
/**
 * 
 * @author 
 *
 */
public class TrainReportAction extends BaseAction{
	@Resource
	private TrainReportService trainReportService;
	private TrainReport trainReport;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrainReport getTrainReport() {
		return trainReport;
	}

	public void setTrainReport(TrainReport trainReport) {
		this.trainReport = trainReport;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<TrainReport> list= trainReportService.getAll(filter);
		
		Type type=new TypeToken<List<TrainReport>>(){}.getType();
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
				trainReportService.remove(new Long(id));
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
		TrainReport trainReport=trainReportService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(trainReport));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String trainReportId=getRequest().getParameter("trainReportAttachIDs");
		String[] trainReportIds = trainReportId.split(",");
		trainReportService.saveTrainReport(trainReportIds, trainReport);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
