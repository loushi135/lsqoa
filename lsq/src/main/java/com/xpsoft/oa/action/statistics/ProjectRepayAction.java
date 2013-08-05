package com.xpsoft.oa.action.statistics;


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
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectRepay;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.statistics.ProjectRepayService;
/**
 * 
 * @author 
 *
 */
public class ProjectRepayAction extends BaseAction{
	@Resource
	private ProjectRepayService projectRepayService;
	private ProjectRepay projectRepay;
	@Resource
	private ProjectNewService projectNewService;
	private String attachIds;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String attachIds) {
		this.attachIds = attachIds;
	}

	public ProjectRepay getProjectRepay() {
		return projectRepay;
	}

	public void setProjectRepay(ProjectRepay projectRepay) {
		this.projectRepay = projectRepay;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ProjectRepay> list= projectRepayService.getAll(filter);
		
		Type type=new TypeToken<List<ProjectRepay>>(){}.getType();
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
				projectRepayService.remove(new Long(id));
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
		ProjectRepay projectRepay=projectRepayService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(projectRepay));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		projectRepay.setCreatetime(new Date());
		projectRepay.setCreateUser(ContextUtil.getCurrentUser());
		projectRepayService.save(projectRepay);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	/**
	 * 查出项目报销总额
	 */
	public String getRepayByProNo(){
		String proNo = getRequest().getParameter("proNo");
		BigDecimal repayTotal = new BigDecimal(0);
		if(StringUtils.isNotBlank(proNo)){
			repayTotal = projectRepayService.getRePayedByProNo(proNo);
		}
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(repayTotal);
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	
	//导入收款信息
	public String imports(){
		System.out.println(id);
		ProjectNew project=null;
		if(null!=id){
			project=projectNewService.get(id);
			
			if(null==project){
				setJsonString("{success:false,data:'数据异常'}");
				return SUCCESS;
			}
		}
		
		String reulst;
		try {
			reulst = projectRepayService.imports(attachIds);
		} catch (Exception e) {
			e.printStackTrace();
			if(null!=e.getMessage()&&e.getMessage().startsWith("{")){
				reulst=e.getMessage();
			}else{
				reulst="{success:false,data:'系统异常'}";
			}
			
		}
		
		setJsonString(reulst);
		return SUCCESS;
	}
}
