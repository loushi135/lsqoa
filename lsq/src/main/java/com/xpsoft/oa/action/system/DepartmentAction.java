package com.xpsoft.oa.action.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.CompanyService;
import com.xpsoft.oa.service.system.DepartmentService;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class DepartmentAction extends BaseAction {
	private Department department;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private CompanyService companyService;

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String select() {
		String depId = getRequest().getParameter("depId");
		QueryFilter filter = new QueryFilter(getRequest());
		if ((StringUtils.isNotEmpty(depId)) && (!"0".equals(depId))) {
			this.department = ((Department) this.departmentService
					.get(new Long(depId)));
			filter.addFilter("Q_path_S_LFK", this.department.getPath());
		}
		filter.addFilter("Q_depLevel_N_EQ", "0");
		filter.addSorted("path", "asc");
		List<Department> list = this.departmentService.getAll(filter);
		Type type = new TypeToken<List<Department>>() {
		}
		.getType();
		StringBuffer buff = new StringBuffer(
				"{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();

		return "success";
	}

	public String list() {
		String opt = getRequest().getParameter("opt");
		String queryAll = getRequest().getParameter("queryAll");
		Integer status = 0;
		if(StringUtils.isNotBlank(queryAll)){//查询全部
			status = null;
		}
		StringBuffer buff = new StringBuffer();
		if (StringUtils.isNotEmpty(opt))
			buff.append("[");
		else {
			buff.append("[{id:'0',text:'" + AppUtil.getCompanyName()
					+ "',expanded:true,children:[");
		}

		List<Department> listParent = this.departmentService
				.findByParentIdAndStatus(new Long(0L),status);
		for (Department dep : listParent) {
			String depName = dep.getDepName();
			if(status == null&&dep.getDepLevel() == Constants.FLAG_DELETED.intValue()){
				depName+="(已删除)";
			}
			buff.append("{id:'" + dep.getDepId() + "',text:'"
					+ depName + "',");
			buff.append(findChild(dep.getDepId(),status));
		}
		if (!listParent.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		if (StringUtils.isNotEmpty(opt))
			buff.append("]");
		else {
			buff.append("]}]");
		}
		setJsonString(buff.toString());
		return "success";
	}
	public String listWork() {
		String opt = getRequest().getParameter("opt");
		String queryAll = getRequest().getParameter("queryAll");
		Integer status = 0;
		if(StringUtils.isNotBlank(queryAll)){//查询全部
			status = null;
		}
		StringBuffer buff = new StringBuffer();
		if (StringUtils.isNotEmpty(opt))
			buff.append("[");
		else {
			buff.append("[{id:'0',text:'" + AppUtil.getCompanyName()
					+ "',expanded:true,children:[");
		}

		List<Department> listParent = this.departmentService
				.findByParentId(new Long(11L));
		for (Department dep : listParent) {
			if(!dep.getDepId().equals(12L)){
				buff.append("{id:'" + dep.getDepId() + "',text:'"
						+ dep.getDepName() + "',");
				buff.append(findChild(dep.getDepId(),status));
			}
		}
		
		if("yes".equals(getRequest().getParameter("hasUnknow"))){
			buff.append("{id:'0',text:'不确定',leaf:true},");
		}
		
		
		
		if (!listParent.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		if (StringUtils.isNotEmpty(opt))
			buff.append("]");
		else {
			buff.append("]}]");
		}
		setJsonString(buff.toString());
		return "success";
	}

	public String listOther() {
		String opt = getRequest().getParameter("opt");
		String queryAll = getRequest().getParameter("queryAll");
		Integer status = 0;
		if(StringUtils.isNotBlank(queryAll)){//查询全部
			status = null;
		}
		StringBuffer buff = new StringBuffer();
		if (StringUtils.isNotEmpty(opt))
			buff.append("[");
		else {
			buff.append("[{id:'0',text:'" + AppUtil.getCompanyName()
					+ "',expanded:true,children:[");
		}
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addSorted("path", "asc");
		try {
			String depName = new String(getRequest().getParameter("depName").getBytes("ISO-8859-1"), "UTF-8");
			filter.addFilter("Q_depName_S_LK", depName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Department> listParent = this.departmentService.getAll(filter);
		for (Department dep : listParent) {
			buff.append("{id:'" + dep.getDepId() + "',text:'"
					+ dep.getDepName() + "',");
			buff.append(findChild(dep.getDepId(),status));
		}
		if (!listParent.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		if (StringUtils.isNotEmpty(opt))
			buff.append("]");
		else {
			buff.append("]}]");
		}
		setJsonString(buff.toString());
		return "success";
	}
	
	public String findChild(Long depId,Integer status) {
		StringBuffer buff1 = new StringBuffer("");
		List<Department> list = this.departmentService
				.findByParentIdAndStatus(depId,status);
		if (list.size() == 0) {
			buff1.append("leaf:true},");
			return buff1.toString();
		}
		buff1.append("children:[");
		for (Department dep2 : list) {
			String depName = dep2.getDepName();
			if(status == null&&dep2.getDepLevel() == Constants.FLAG_DELETED.intValue()){
				depName+="(已删除)";
			}
			buff1.append("{id:'" + dep2.getDepId() + "',text:'"
					+ depName + "',");
			buff1.append(findChild(dep2.getDepId(),status));
		}
		buff1.deleteCharAt(buff1.length() - 1);
		buff1.append("]},");
		return buff1.toString();
	}

	public String add() {
		Long parentId = this.department.getParentId();
		String depPath = "";
		int level = 0;
		if (parentId.longValue() < 1L) {
			parentId = new Long(0L);
			depPath = "0.";
		} else {
			depPath = ((Department) this.departmentService
					.get(parentId)).getPath();
			level = ((Department) this.departmentService.get(parentId))
					.getDepLevel().intValue();
		}
		if (level < 1) {
			level = 1;
		}
		this.department.setDepLevel(Integer.valueOf(level + 1));
		this.departmentService.save(this.department);
		if (this.department != null) {
			depPath = depPath + this.department.getDepId().toString()
					+ ".";
			this.department.setPath(depPath);
			this.departmentService.save(this.department);
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}

	public String remove() {
		PagingBean pb = getInitPagingBean();
		Long depId = Long.valueOf(Long.parseLong(getRequest()
				.getParameter("depId")));
		Department department = (Department) this.departmentService
				.get(depId);
		department.setDepLevel(Constants.FLAG_DELETED.intValue());
		departmentService.save(department);
//		List userList = this.appUserService.findByDepartment(
//				department.getPath(), pb);
//		if (userList.size() > 0) {
//			setJsonString("{success:false,message:'该部门还有人员，请将人员转移后再删除部门!'}");
//			return "success";
//		}
//		this.departmentService.remove(depId);
//		List<Department> list = this.departmentService
//				.findByParentId(depId);
//		for (Department dep : list) {
//			List users = this.appUserService.findByDepartment(
//					dep.getPath(), pb);
//			if (users.size() > 0) {
//				setJsonString("{success:false,message:'该部门还有人员，请将人员转移后再删除部门!'}");
//				return "success";
//			}
//			this.departmentService.remove(dep.getDepId());
//		}
		setJsonString("{success:true}");
		return "success";
	}

	public String detail() {
		Long depId = Long.valueOf(Long.parseLong(getRequest()
				.getParameter("depId")));
		setDepartment((Department) this.departmentService.get(depId));
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer("{success:true,data:[");
		sb.append(gson.toJson(this.department));
		sb.append("]}");
		setJsonString(sb.toString());
		return "success";
	}
	public String getDeptByName(){
		String depName = getRequest().getParameter("depName");
		List<Department> deptList = departmentService.findByDepName(depName);
		if(deptList!=null&&deptList.size()>0){
			department = deptList.get(0);
		}
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer("{success:true,data:");
		if(department!=null){
			sb.append(gson.toJson(this.department));
		}else{
			sb.append("{}");
		}
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}
}
