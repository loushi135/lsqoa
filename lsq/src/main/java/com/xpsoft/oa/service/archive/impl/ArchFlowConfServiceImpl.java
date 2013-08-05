 package com.xpsoft.oa.service.archive.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.archive.ArchFlowConfDao;
 import com.xpsoft.oa.model.archive.ArchFlowConf;
 import com.xpsoft.oa.service.archive.ArchFlowConfService;
 
 public class ArchFlowConfServiceImpl extends BaseServiceImpl<ArchFlowConf>
   implements ArchFlowConfService
 {
   private ArchFlowConfDao dao;
 
   public ArchFlowConfServiceImpl(ArchFlowConfDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public ArchFlowConf getByFlowType(Short archType)
   {
     return this.dao.getByFlowType(archType);
   }
 }

