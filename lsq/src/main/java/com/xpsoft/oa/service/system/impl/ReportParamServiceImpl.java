 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.system.ReportParamDao;
 import com.xpsoft.oa.model.system.ReportParam;
 import com.xpsoft.oa.service.system.ReportParamService;
 import java.util.List;
 
 public class ReportParamServiceImpl extends BaseServiceImpl<ReportParam>
   implements ReportParamService
 {
   private ReportParamDao dao;
 
   public ReportParamServiceImpl(ReportParamDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<ReportParam> findByRepTemp(Long reportId)
   {
     return this.dao.findByRepTemp(reportId);
   }
 }

