 package com.xpsoft.oa.dao.flow.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.flow.FormDefDao;
 import com.xpsoft.oa.model.flow.FormDef;
 import java.util.List;
 
 public class FormDefDaoImpl extends BaseDaoImpl<FormDef>
   implements FormDefDao
 {
   public FormDefDaoImpl()
   {
     super(FormDef.class);
   }
 
   public List<FormDef> getByDeployId(String deployId)
   {
     String hql = "from FormDef fd where deployId=?";
     return findByHql(hql, new Object[] { deployId });
   }
 
   public FormDef getByDeployIdActivityName(String deployId, String activityName)
   {
     String hql = "from FormDef fd where fd.deployId=? and fd.activityName=?";
     return (FormDef)findUnique(hql, new Object[] { deployId, activityName });
   }
 }

