package com.xpsoft.oa.action.admin;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.admin.ExpressApply;
import com.xpsoft.oa.model.admin.ExpressApplyExport;
import com.xpsoft.oa.service.admin.ExpressApplyService;

import flexjson.JSONSerializer;
/**
 * 
 * @author 
 *
 */
public class ExpressApplyAction extends BaseAction{
	@Resource
	private ExpressApplyService expressApplyService;
	private ExpressApply expressApply;
	
	private Long id;

	private List<ExpressApply> expressApplies;
	private Map<Object,Object> parmsMap;
	
	public List<ExpressApply> getExpressApplies() {
		return expressApplies;
	}

	public void setExpressApplies(List<ExpressApply> expressApplies) {
		this.expressApplies = expressApplies;
	}

	public Map<Object, Object> getParmsMap() {
		return parmsMap;
	}

	public void setParmsMap(Map<Object, Object> parmsMap) {
		this.parmsMap = parmsMap;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExpressApply getExpressApply() {
		return expressApply;
	}

	public void setExpressApply(ExpressApply expressApply) {
		this.expressApply = expressApply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ExpressApply> list= expressApplyService.getAll(filter);
		
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer("applyDate");
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	/**
	 * 显示列表
	 */
	public String listForExport(){
		String beginMonth = getRequest().getParameter("beginMonth");
		String endMonth = getRequest().getParameter("endMonth");
		QueryFilter filter=new QueryFilter(getRequest());
		if(StringUtils.isNotBlank(beginMonth)&&!beginMonth.contains("格式")){
			String beign = beginMonth+"-01";//2013-06-01;
			filter.addFilter("Q_applyDate_D_GE", beign);
		}
		if(StringUtils.isNotBlank(endMonth)&&!endMonth.contains("格式")){
			Date end = DateUtil.parse(endMonth,"yyyy-MM");
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(end);
	        calendar.add(Calendar.MONTH, 1);
	        Date theDate = calendar.getTime();
	        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	        gcLast.setTime(theDate);
	        gcLast.set(Calendar.DAY_OF_MONTH, 1);
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        String day_first = df.format(gcLast.getTime());
	        filter.addFilter("Q_applyDate_D_LT", day_first);
		}
		List<ExpressApply> list= expressApplyService.getAll(filter);
		List<ExpressApplyExport> eaExportList = new ArrayList<ExpressApplyExport>();
		for(ExpressApply ea:list){
			ExpressApplyExport eaExport = new ExpressApplyExport();
			eaExport.setApplyDate(ea.getApplyDate());
			eaExport.setApplyer(ea.getApplyer());
			eaExport.setExpressType(ea.getExpressType()+"");
			eaExport.setProvince(ea.getProvince());
			eaExport.setCity(ea.getCity());
			eaExport.setToWhereName(ea.getToWhereName());
			eaExport.setExpressName(ea.getExpressName());
			eaExport.setExpressNo(ea.getExpressNo());
			eaExport.setDept(ea.getDept());
			eaExport.setProjectNew(ea.getProjectNew());
			if(ea.getDept()!=null){
				eaExport.setDispatchType("部门");
			}
			if(ea.getProjectNew()!=null){
				eaExport.setDispatchType("项目");
			}
			eaExportList.add(eaExport);
		}
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer("applyDate");
		buff.append(serializer.exclude(new String[] { "class" }).serialize(eaExportList));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	
	public String export()throws Exception{
		String beginMonth = getRequest().getParameter("beginMonth");
		String endMonth = getRequest().getParameter("endMonth");
		QueryFilter filter=new QueryFilter(getRequest());
		if(StringUtils.isNotBlank(beginMonth)){
			String beign = beginMonth+"-01";//2013-06-01;
			filter.addFilter("Q_applyDate_D_GE", beign);
		}
		if(StringUtils.isNotBlank(endMonth)){
			Date end = DateUtil.parse(endMonth,"yyyy-MM");
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(end);
	        calendar.add(Calendar.MONTH, 1);
	        Date theDate = calendar.getTime();
	        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	        gcLast.setTime(theDate);
	        gcLast.set(Calendar.DAY_OF_MONTH, 1);
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        String day_first = df.format(gcLast.getTime());
	        filter.addFilter("Q_applyDate_D_LT", day_first);
		}
		//其他查询条件
		String jsonStr = getRequest().getParameter("jsonStr");
		try {
			jsonStr = new String(jsonStr.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(jsonStr);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		Set<Entry<String, JsonElement>> params = jsonObject.entrySet();
		for (Entry<String, JsonElement> entry : params) {
			filter.addFilter(entry.getKey(),entry.getValue().toString().replace("\"", ""));
		}
		List<ExpressApply> list= expressApplyService.getAll(filter);
		List<ExpressApplyExport> eaExportList = new ArrayList<ExpressApplyExport>();
		for(ExpressApply ea:list){
			ExpressApplyExport eaExport = new ExpressApplyExport();
			eaExport.setApplyDate(ea.getApplyDate());
			eaExport.setApplyer(ea.getApplyer());
			eaExport.setExpressType(ea.getExpressType()+"");
			eaExport.setProvince(ea.getProvince());
			eaExport.setCity(ea.getCity());
			eaExport.setToWhereName(ea.getToWhereName());
			eaExport.setExpressName(ea.getExpressName());
			eaExport.setExpressNo(ea.getExpressNo());
			eaExport.setDept(ea.getDept());
			eaExport.setProjectNew(ea.getProjectNew());
			if(ea.getDept()!=null){
				eaExport.setDispatchType("部门");
			}
			if(ea.getProjectNew()!=null){
				eaExport.setDispatchType("项目");
			}
			eaExportList.add(eaExport);
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("eaExportList", eaExportList);
		expressApplyService.export(dataMap);
		return SUCCESS;
	}
	
	public String toExllist(){
		QueryFilter filter=new QueryFilter(getRequest());
		String jsonStr = getRequest().getParameter("jsonStr");
		try {
			jsonStr = new String(jsonStr.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(jsonStr);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		Set<Entry<String, JsonElement>> params = jsonObject.entrySet();
		for (Entry<String, JsonElement> entry : params) {
			filter.addFilter(entry.getKey(),entry.getValue().toString().replace("\"", ""));
		}
		List<ExpressApply> list= expressApplyService.getAll(filter);
		setExpressApplies(list);
		parmsMap = new HashMap<Object,Object>();
		parmsMap.put(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);//白色背景透明
		parmsMap.put(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, true);//忽略单元格背景
		parmsMap.put(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);//移除空白
		try {
			getResponse().setHeader("Content-Disposition", "inline;filename="+new String("快递报表.xls".getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "Excel";
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				expressApplyService.remove(new Long(id));
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
		ExpressApply expressApply=expressApplyService.get(id);
		
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer=JsonUtil.getJSONSerializer("applyDate");
		sb.append(serializer.exclude(new String[] { "class" }).serialize(expressApply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(expressApply.getDept()==null||expressApply.getDept().getDepId()==null){
			expressApply.setDept(null);
		}
		if(expressApply.getProjectNew()==null||expressApply.getProjectNew().getId()==null){
			expressApply.setProjectNew(null);
		}
		expressApplyService.save(expressApply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
