package com.xpsoft.oa.action.statistics;

import java.io.File;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectReceive;
import com.xpsoft.oa.service.customer.ProjectService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.statistics.ProjectReceiveService;

/**
 * 
 * @author
 * 
 */
public class ProjectReceiveAction extends BaseAction {
	@Resource
	private ProjectReceiveService projectReceiveService;
	@Resource
	private ProjectNewService projectNewService;
	private ProjectReceive projectReceive;
	// private File importFileExcle;
	// private String importFileExcleFileName; //文件名称
	// private String importFileExcleContentType; //文件类型
	private String attachIds;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProjectReceive getProjectReceive() {
		return projectReceive;
	}

	public void setProjectReceive(ProjectReceive projectReceive) {
		this.projectReceive = projectReceive;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<ProjectReceive> list = projectReceiveService.getAll(filter);

		Type type = new TypeToken<List<ProjectReceive>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat(Constants.DATE_FORMAT_YMD).create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				projectReceiveService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		ProjectReceive projectReceive = projectReceiveService.get(id);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat(Constants.DATE_FORMAT_YMD).create();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(projectReceive));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		projectReceive.setCreatetime(new Date());
		projectReceive.setCreateUser(ContextUtil.getCurrentUser());
		projectReceiveService.save(projectReceive);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	// 导入收款信息
	public String imports() {
		System.out.println(id);
		ProjectNew project = null;
		if (null != id) {
			project = projectNewService.get(id);

			if (null == project) {
				setJsonString("{success:false,data:'数据异常'}");
				return SUCCESS;
			}
		}

		// System.out.println(importFileExcleContentType);//application/vnd.ms-excel

		// if(!"application/vnd.ms-excel".equals(importFileExcleContentType)){
		// setJsonString("{success:false,data:'数据异常'}");
		// return SUCCESS;
		// }

		String reulst;
		try {
			reulst = projectReceiveService.imports(project, attachIds);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().startsWith("{")) {
				reulst = e.getMessage();
			} else {
				reulst = "{success:false,data:'系统异常'}";
			}
		}

		// System.out.println(importFileExcleFileName);
		setJsonString(reulst);
		return SUCCESS;
	}

	/**
	 * 查出项目收款总额
	 */
	public String getReceiveByProNo() {
		String proNo = getRequest().getParameter("proNo");
		BigDecimal receiveTotal = new BigDecimal(0);
		if (StringUtils.isNotBlank(proNo)) {
			receiveTotal = projectReceiveService.getTotalReceiveByProNo(proNo);
		}
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(receiveTotal);
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	public String listForExport() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ProjectReceive> list = null;
		if (
				ContextUtil.getCurrentUser().getRights().contains("__ALL")|| 
				ContextUtil.getCurrentUser().getRights().contains(
						"_ALLProjectReceiveExportQuery")) {
			list = projectReceiveService.getAll(filter);
		} else {

			list = projectReceiveService.getMyData(filter, ContextUtil
					.getCurrentUser());
		}

		Type type = new TypeToken<List<ProjectReceive>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat(Constants.DATE_FORMAT_YMD).create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}

	public String getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String attachIds) {
		this.attachIds = attachIds;
	}
}
