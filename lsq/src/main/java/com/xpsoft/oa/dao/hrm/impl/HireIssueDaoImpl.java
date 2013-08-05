 package com.xpsoft.oa.dao.hrm.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.hrm.HireIssueDao;
 import com.xpsoft.oa.model.hrm.HireIssue;
 
 public class HireIssueDaoImpl extends BaseDaoImpl<HireIssue>
   implements HireIssueDao
 {
   public HireIssueDaoImpl()
   {
     super(HireIssue.class);
   }
 }

