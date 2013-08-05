 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.DepartmentDao;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.service.system.DepartmentService;
import java.util.List;
 
 public class DepartmentServiceImpl extends BaseServiceImpl<Department>
   implements DepartmentService
 {
   private DepartmentDao dao;
 
   public DepartmentServiceImpl(DepartmentDao dao)
   {
     super(dao);
     this.dao = dao;
   }
   @Override
   public List<Department> findByParentIdAndStatus(Long paramLong, Integer status) {
		return this.dao.findByParentIdAndStatus(paramLong, status);
   }
   public List<Department> findByParentId(Long parentId) {
     return this.dao.findByParentId(parentId);
   }
 
   public List<Department> findByDepName(String depName) {
     return this.dao.findByDepName(depName);
   }
 
   public List<Department> findByPath(String path) {
     return this.dao.findByPath(path);
   }
 }

