package com.xpsoft.oa.action.statistics;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ExportUtil;
import com.xpsoft.core.util.PinyingUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectRelatedData;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;

/**
 * 
 * @author
 * 
 */
public class ProjectNewAction extends BaseAction {
	@Resource
	private ProjectNewService projectNewService;
	private ProjectNew projectNew;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private AppUserService appUserService;
	public static Map<String, String> deptMap = new HashMap<String, String>();
	private String attachIds;
	public String getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String attachIds) {
		this.attachIds = attachIds;
	}

	static {
		deptMap.put("直属一部", "A");
		deptMap.put("直属二部", "A");
		deptMap.put("工程一区", "B");
		deptMap.put("工程二区", "C");
		deptMap.put("工程三区", "D");
		deptMap.put("工程五区", "E");
		deptMap.put("工程六区", "F");
		deptMap.put("工程七区", "H");
	}
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProjectNew getProjectNew() {
		return projectNew;
	}

	public void setProjectNew(ProjectNew projectNew) {
		this.projectNew = projectNew;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<ProjectNew> list = projectNewService.getAll(filter);

		Type type = new TypeToken<List<ProjectNew>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_YMD).excludeFieldsWithoutExposeAnnotation()
				.create();
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
				ProjectNew projectNew = projectNewService.get(Long.valueOf(id));
				projectNew.setStatus(1);
				projectNewService.save(projectNew);
//				projectNewService.remove(new Long(id));
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
		ProjectNew projectNew = projectNewService.get(id);

		Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_YMD).excludeFieldsWithoutExposeAnnotation()
				.create();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(projectNew));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if(projectNew.getId()==null){
			String deptId = getRequest().getParameter("deptId");
			String proNo = this.createNo(deptId);
			projectNew.setProNo(proNo);
			projectNew.setStatus(0);
		}
		projectNewService.save(projectNew);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	private String createNo(String deptId){
		String proNo = "";
		if (StringUtils.isNotBlank(deptId)) {
			Department dept = departmentService.get(Long.valueOf(deptId));
			String signFirst = deptMap.get(dept.getDepName());
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_department.depId_L_EQ", deptId);
			List<AppUser> userList = appUserService.getAll(filter);
			String managerName = "";
			for (AppUser user : userList) {
				if (user.getPosition().equals("经理")) {
					managerName = user.getFullname();
					break;
				}
			}
			String signName = PinyingUtil.getHeadString(managerName);
			String signYear = new SimpleDateFormat("yy").format(new Date());
			String search = signFirst + "." + signName + "." + signYear;
			QueryFilter filter1 = new QueryFilter(getRequest());
			filter1.addFilter("Q_proNo_S_LK", search);
			filter1.addSorted("proNo", "desc");
			filter1.addFilter("limit", "1");
			List<ProjectNew> proList = projectNewService.getAll(filter1);
			if (proList != null && proList.size() > 0) {
				ProjectNew lastPro = proList.get(0);
				String lastNo = lastPro.getProNo();
				Integer pn = Integer.parseInt(lastNo.substring(lastNo
						.lastIndexOf(search)+search.length()));
				proNo = String.valueOf(pn + 1);
				if (proNo.length() < 2) {
					proNo = "0" + String.valueOf((pn + 1));
				}
			} else {
				proNo = "01";
			}
			return search + proNo;
		}else{
			return "";
		}
	}
	public String getProNo() {
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		String deptId = getRequest().getParameter("deptId");
		String proNo = "";
		if (StringUtils.isNotBlank(deptId)) {
			Department dept = departmentService.get(Long.valueOf(deptId));
			String signFirst = deptMap.get(dept.getDepName());
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_department.depId_L_EQ", deptId);
			List<AppUser> userList = appUserService.getAll(filter);
			String managerName = "";
			for (AppUser user : userList) {
				if (user.getPosition().equals("经理")) {
					managerName = user.getFullname();
					break;
				}
			}
			String signName = PinyingUtil.getHeadString(managerName);
			String signYear = new SimpleDateFormat("yy").format(new Date());
			String search = signFirst + "." + signName + "." + signYear;
			QueryFilter filter1 = new QueryFilter(getRequest());
			filter1.addFilter("Q_proNo_S_LK", search);
			filter1.addSorted("proNo", "desc");
			filter1.addFilter("limit", "1");
			List<ProjectNew> proList = projectNewService.getAll(filter1);
			if (proList != null && proList.size() > 0) {
				ProjectNew lastPro = proList.get(0);
				String lastNo = lastPro.getProNo();
				Integer pn = Integer.parseInt(lastNo.substring(lastNo
						.lastIndexOf(search)+search.length()));
				proNo = String.valueOf(pn + 1);
				if (proNo.length() < 2) {
					proNo = "0" + String.valueOf((pn + 1));
				}
			} else {
				proNo = "01";
			}
			sb.append("{proNo:'" + search + proNo + "'}");
		}
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	
	public String getProjectByNo(){
		QueryFilter filter = new QueryFilter(getRequest());
		List<ProjectNew> list = projectNewService.getAll(filter);
		if(list!=null&&list.size()>0){
			projectNew = list.get(0);
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_YMD).excludeFieldsWithoutExposeAnnotation()
			.create();
			StringBuffer sb = new StringBuffer("{success:true,data:");
			sb.append(gson.toJson(projectNew));
			sb.append("}");
			setJsonString(sb.toString());
		}else{
			StringBuffer sb = new StringBuffer("{success:true,data:''}");
			setJsonString(sb.toString());
		}
		return SUCCESS;
	}
	
	public String listAllData(){
		
		String area = getRequest().getParameter("area");
		String proName = getRequest().getParameter("proName");
		String proNo = getRequest().getParameter("proNo");
		String proChargerName = getRequest().getParameter("proChargerName");
		PagingBean pagingBean = getInitPagingBean();
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("area", area);
		dataMap.put("proName", proName);
		dataMap.put("proNo", proNo);
		dataMap.put("proChargerName", proChargerName);
		List<ProjectRelatedData> list = projectNewService.getAllData(pagingBean,dataMap);

		Type type = new TypeToken<List<ProjectRelatedData>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pagingBean.getTotalItems()).append(",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}
	
	public String reportAllData()throws Exception{
		String format = getRequest().getParameter("format");
		String jasperName= getRequest().getParameter("jasperName");
		String fileName="资金结余统计";
		String area = new String(getRequest().getParameter("area").getBytes("ISO-8859-1"), "UTF-8");
		String proName = new String(getRequest().getParameter("proName").getBytes("ISO-8859-1"), "UTF-8");
		String proNo = new String(getRequest().getParameter("proNo").getBytes("ISO-8859-1"), "UTF-8");
		String proChargerName = new String(getRequest().getParameter("proChargerName").getBytes("ISO-8859-1"), "UTF-8");
		PagingBean pagingBean = getInitPagingBean();
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("area",area );
		dataMap.put("proName", proName);
		dataMap.put("proNo", proNo);
		dataMap.put("proChargerName", proChargerName);
		List<ProjectRelatedData> list = projectNewService.getAllData(pagingBean,dataMap);
		Map<Object,Object> parmsMap = new HashMap<Object,Object>();
        ExportUtil.export(list, jasperName,fileName, format,parmsMap);
		return null;
	}
	
	public String checkProName(){
		String proName = getRequest().getParameter("proName");
		ProjectNew project = projectNewService.getByProName(proName);
		StringBuffer sb = new StringBuffer();
		if(project == null){
			sb.append("{success:true}");
		}else{
			sb.append("{success:false}");
		}
		jsonString = sb.toString();
		return SUCCESS;
	}
	
	public String importsUpdate(){

		String reulst;
		try {
			reulst = projectNewService.importsUpdate(attachIds);
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
//		jsonString = "{success:true,data:'数据导入更新成功'}";
	}
}
