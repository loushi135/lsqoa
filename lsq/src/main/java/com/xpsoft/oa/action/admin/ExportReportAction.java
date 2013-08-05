package com.xpsoft.oa.action.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ExportUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.service.statistics.BankpayapplyService;
/**
 * 
 * @author 
 *
 */
public class ExportReportAction extends BaseAction{
	@Resource
	private BankpayapplyService bankpayapplyService;
	private String format;
	private String jasperName;
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getJasperName() {
		return jasperName;
	}
	public void setJasperName(String jasperName) {
		this.jasperName = jasperName;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		QueryFilter filter=new QueryFilter(getRequest());
//		String bpaProjectName = getRequest().getParameter("bpaProjectName");
//		String bpaProjectNo = getRequest().getParameter("bpaProjectNo");
//		dataMap.put("bpaProjectName", bpaProjectName);
//		dataMap.put("bpaProjectNo", bpaProjectNo);
//		List list= bankpayapplyService.listForReport(filter,dataMap);
		List<?> list = bankpayapplyService.getAll(filter);
//	    list = ContactFactory.create();
		Map<Object,Object> parmsMap = new HashMap<Object,Object>();
//		parmsMap.put(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);//白色背景透明
//		parmsMap.put(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, true);//忽略单元格背景
//		parmsMap.put(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);//移除空白
        ExportUtil.export(list, jasperName,jasperName, format,parmsMap);
	    return null;
	}
}
