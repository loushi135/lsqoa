 package com.xpsoft.oa.service.hrm.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.hrm.JobDao;
import com.xpsoft.oa.model.hrm.Job;
import com.xpsoft.oa.service.hrm.JobService;
import java.util.List;
 
 public class JobServiceImpl extends BaseServiceImpl<Job>
   implements JobService
 {
   private JobDao dao;
 
   public JobServiceImpl(JobDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<Job> findByDep(Long depId)
   {
     return this.dao.findByDep(depId);
   }

	@Override
	public Job findByDepAndJobName(Long depId, String jobName) {
		// TODO Auto-generated method stub
		String hql = "from Job where department.depId=? and jobName=?";
		List<Job> list = this.dao.findByHql(hql, new Object[]{depId,jobName});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<String> getAllJobName() {
		return this.dao.getAllJobName();
	}
   
 }

