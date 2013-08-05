 package com.xpsoft.oa.service.flow.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.flow.ProcessFormDao;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.service.flow.ProcessFormService;
import java.util.List;
import java.util.Map;
 
 public class ProcessFormServiceImpl extends BaseServiceImpl<ProcessForm>
   implements ProcessFormService
 {
   private ProcessFormDao dao;
 
   public ProcessFormServiceImpl(ProcessFormDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List getByRunId(Long runId)
   {
     return this.dao.getByRunId(runId);
   }
   
   public List getByRunId(Long runId,Integer version)
   {
     return this.dao.getByRunId(runId,version);
   }
 
   public ProcessForm getByRunIdActivityName(Long runId, String activityName)
   {
     return this.dao.getByRunIdActivityName(runId, activityName);
   }
 
   public Long getActvityExeTimes(Long runId, String activityName)
   {
     return this.dao.getActvityExeTimes(runId, activityName);
   }
 
   public Map getVariables(Long runId)
   {
     return this.dao.getVariables(runId);
   }

	@Override
	public List<ProcessForm> getBeforeProcessForm(Long processRunId,
			String activityName) {
		// TODO Auto-generated method stub
		return dao.getBeforeProcessForm(processRunId, activityName);
	}
   
 }

