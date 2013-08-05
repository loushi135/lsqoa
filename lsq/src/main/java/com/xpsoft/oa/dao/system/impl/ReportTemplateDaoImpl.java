 package com.xpsoft.oa.dao.system.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.system.ReportTemplateDao;
 import com.xpsoft.oa.model.system.ReportTemplate;
 
 public class ReportTemplateDaoImpl extends BaseDaoImpl<ReportTemplate>
   implements ReportTemplateDao
 {
   public ReportTemplateDaoImpl()
   {
     super(ReportTemplate.class);
   }
 }

