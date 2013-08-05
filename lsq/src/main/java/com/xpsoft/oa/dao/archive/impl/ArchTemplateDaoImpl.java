 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.archive.ArchTemplateDao;
 import com.xpsoft.oa.model.archive.ArchTemplate;
 
 public class ArchTemplateDaoImpl extends BaseDaoImpl<ArchTemplate>
   implements ArchTemplateDao
 {
   public ArchTemplateDaoImpl()
   {
     super(ArchTemplate.class);
   }
 }

