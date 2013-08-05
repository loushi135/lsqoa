 package com.xpsoft.oa.service.flow.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.flow.ProDefinitionDao;
 import com.xpsoft.oa.model.flow.ProDefinition;
 import com.xpsoft.oa.service.flow.ProDefinitionService;
 
 public class ProDefinitionServiceImpl extends BaseServiceImpl<ProDefinition>
   implements ProDefinitionService
 {
   private ProDefinitionDao dao;
 
   public ProDefinitionServiceImpl(ProDefinitionDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public ProDefinition getByDeployId(String deployId) {
     return this.dao.getByDeployId(deployId);
   }
 
   public ProDefinition getByName(String name) {
     return this.dao.getByName(name);
   }
 }

