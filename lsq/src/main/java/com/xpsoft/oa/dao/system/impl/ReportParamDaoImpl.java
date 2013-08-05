 package com.xpsoft.oa.dao.system.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.system.ReportParamDao;
 import com.xpsoft.oa.model.system.ReportParam;
 import java.util.List;
 
 public class ReportParamDaoImpl extends BaseDaoImpl<ReportParam>
   implements ReportParamDao
 {
   public ReportParamDaoImpl()
   {
     super(ReportParam.class);
   }
 
   public List<ReportParam> findByRepTemp(Long reportId)
   {
     String hql = "from ReportParam vo where vo.reportTemplate.reportId=? order by vo.sn asc";
     Object[] objs = { reportId };
     return findByHql(hql, objs);
   }
 }

