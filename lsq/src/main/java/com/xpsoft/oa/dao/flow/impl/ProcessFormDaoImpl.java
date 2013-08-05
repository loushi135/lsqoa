 package com.xpsoft.oa.dao.flow.impl;
 
 import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.util.StringUtil;
import com.xpsoft.oa.dao.flow.ProcessFormDao;
import com.xpsoft.oa.model.flow.FormData;
import com.xpsoft.oa.model.flow.ProcessForm;
 
 public class ProcessFormDaoImpl extends BaseDaoImpl<ProcessForm>
   implements ProcessFormDao
 {
   public ProcessFormDaoImpl()
   {
     super(ProcessForm.class);
   }
 
   public List getByRunId(Long runId)
   {
     String hql = "from ProcessForm pf where pf.processRun.runId=? order by pf.formId asc";
     return findByHql(hql, new Object[] { runId });
   }
   
   public List getByRunId(Long runId,Integer maxVersion)
   {
     String hql = "from ProcessForm pf where pf.processRun.runId=? and pf.version =? order by pf.formId asc";
     return findByHql(hql, new Object[] { runId ,maxVersion});
   }
 
   public ProcessForm getByRunIdActivityName(Long runId, String activityName)
   {
     Integer maxSn = Integer.valueOf(getActvityExeTimes(runId, activityName).intValue());
     String hql = "from ProcessForm pf where pf.processRun.runId=? and pf.activityName=? and pf.sn=?";
     return (ProcessForm)findUnique(hql, new Object[] { runId, activityName, maxSn });
   }
 
   public Map getVariables(Long runId)
   {
     Map variables = new HashMap();
     String hql = "from ProcessForm pf where pf.processRun.runId=? order by pf.createtime desc";
     List<ProcessForm> forms = findByHql(hql, new Object[] { runId });
 
     for (ProcessForm form : forms) {
       Iterator formDataIt = form.getFormDatas().iterator();
       while (formDataIt.hasNext()) {
         FormData formData = (FormData)formDataIt.next();
         if (!variables.containsKey(formData.getFieldName())) {
        	 Object fieldVal = formData.getVal();
        	 if(fieldVal!=null&&fieldVal instanceof String){
					String val = StringUtil.replaceEnter(fieldVal.toString());
					variables.put(formData.getFieldName(), val);
        	 }else{
					variables.put(formData.getFieldName(), formData.getVal());
			 }
         }
       }
     }
     return variables;
   }
 
   public Long getActvityExeTimes(Long runId, String activityName)
   {
     String hql = "select count(pf.formId) from ProcessForm pf where pf.processRun.runId=? and pf.activityName=? ";
     return (Long)findUnique(hql, new Object[] { runId, activityName });
   }
   
   public Integer getMaxVersion(Long runId)
   {
     String hql = "select MAX(pf.version)  FROM ProcessForm pf  where pf.processRun.runId=?  ORDER BY pf.version desc";
     return (Integer)findUnique(hql, new Object[] { runId});
   }

	@Override
	public List<ProcessForm> getBeforeProcessForm(Long processRunId, String activityName) {
		// TODO Auto-generated method stub
		String hql = "from ProcessForm where processRun.runId=:processRunId ";
		String getFormIdHql = "select formId from ProcessForm where processRun.runId =:processRunId and activityName =:activityName order by formId asc";
		Query getFormQuery = this.getSession().createQuery(getFormIdHql);
		getFormQuery.setParameter("processRunId", processRunId).setParameter("activityName", activityName).setFirstResult(0).setMaxResults(1);
		Object obj = getFormQuery.uniqueResult();
		if(obj!=null){
			Long formId = (Long)obj;
			hql+=" and formId<:formId ";
			Query query = this.getSession().createQuery(hql);
			query.setParameter("processRunId", processRunId).setParameter("formId", formId);
			return query.list();
		}else{
			Query query = this.getSession().createQuery(hql);
			query.setParameter("processRunId", processRunId);
			return query.list();
		}
	}
   
 }
