 package com.xpsoft.oa.service.hrm.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.hrm.HireIssueDao;
 import com.xpsoft.oa.model.hrm.HireIssue;
 import com.xpsoft.oa.service.hrm.HireIssueService;
 
 public class HireIssueServiceImpl extends BaseServiceImpl<HireIssue>
   implements HireIssueService
 {
   private HireIssueDao dao;
 
   public HireIssueServiceImpl(HireIssueDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

