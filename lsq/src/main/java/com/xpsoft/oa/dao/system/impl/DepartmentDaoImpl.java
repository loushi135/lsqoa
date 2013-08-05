 package com.xpsoft.oa.dao.system.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.system.DepartmentDao;
import com.xpsoft.oa.model.system.Department;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
 
 public class DepartmentDaoImpl extends BaseDaoImpl<Department>
   implements DepartmentDao
 {
   public DepartmentDaoImpl()
   {
     super(Department.class);
   }
 
   public List<Department> findByParentId(Long parentId)
   {
     String hql = "from Department d where d.parentId=?";
     Object[] params = { parentId };
     return findByHql("from Department d where d.parentId=?", params);
   }
   
   @Override
	public List<Department> findByParentIdAndStatus(Long parentId, Integer status) {
	   if(status!=null){
		   String hql = "from Department d where d.parentId=? and d.depLevel=?";
		   Object[] params = { parentId,status };
		   return findByHql(hql, params);
	   }else{
		   return this.findByParentId(parentId);
	   }
	}
 
   public List<Department> findByVo(Department department, PagingBean pb)
   {
     ArrayList paramList = new ArrayList();
     String hql = "from Department vo where 1=1";
     if (StringUtils.isNotEmpty(department.getDepName())) {
       hql = hql + " and vo.depName like ?";
       paramList.add("%" + department.getDepName() + "%");
     }
     if (StringUtils.isNotEmpty(department.getDepDesc())) {
       hql = hql + " and vo.depDesc=?";
       paramList.add("%" + department.getDepDesc() + "%");
     }
     return findByHql(hql, paramList.toArray(), pb);
   }
 
   public List<Department> findByDepName(String depName)
   {
     String hql = "from Department vo where vo.depName=?";
     String[] param = { depName };
     return findByHql(hql, param);
   }
 
   public List<Department> findByPath(String path)
   {
     String hql = "from Department vo where vo.path like ?";
     String[] param = { path + "%" };
     return findByHql(hql, param);
   }
 }

