 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.system.ReportTemplateDao;
 import com.xpsoft.oa.model.system.ReportTemplate;
 import com.xpsoft.oa.service.system.ReportTemplateService;
 
 public class ReportTemplateServiceImpl extends BaseServiceImpl<ReportTemplate>
   implements ReportTemplateService
 {
   private ReportTemplateDao dao;
 
   public ReportTemplateServiceImpl(ReportTemplateDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

