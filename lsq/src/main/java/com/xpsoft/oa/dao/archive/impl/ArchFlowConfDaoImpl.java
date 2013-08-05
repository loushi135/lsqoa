 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.archive.ArchFlowConfDao;
 import com.xpsoft.oa.model.archive.ArchFlowConf;
 import java.util.List;
 
 public class ArchFlowConfDaoImpl extends BaseDaoImpl<ArchFlowConf>
   implements ArchFlowConfDao
 {
   public ArchFlowConfDaoImpl()
   {
     super(ArchFlowConf.class);
   }
 
   public ArchFlowConf getByFlowType(Short archType)
   {
     String hql = "from ArchFlowConf vo where vo.archType=?";
     Object[] objs = { archType };
     List list = findByHql(hql, objs);
     if (list.size() == 1) {
       return (ArchFlowConf)list.get(0);
     }
     return null;
   }
 }

