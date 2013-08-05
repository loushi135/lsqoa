 package com.xpsoft.oa.service.archive.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.archive.ArchTemplateDao;
 import com.xpsoft.oa.model.archive.ArchTemplate;
 import com.xpsoft.oa.service.archive.ArchTemplateService;
 import com.xpsoft.oa.service.system.FileAttachService;
 import javax.annotation.Resource;
 
 public class ArchTemplateServiceImpl extends BaseServiceImpl<ArchTemplate>
   implements ArchTemplateService
 {
   private ArchTemplateDao dao;
 
   @Resource
   FileAttachService fileAttachService;
 
   public ArchTemplateServiceImpl(ArchTemplateDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public void remove(Long id)
   {
     ArchTemplate template = (ArchTemplate)this.dao.get(id);
     remove(template);
     this.fileAttachService.removeByPath(template.getTempPath());
   }
 }

