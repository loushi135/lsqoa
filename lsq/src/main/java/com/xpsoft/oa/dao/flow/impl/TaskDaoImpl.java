 package com.xpsoft.oa.dao.flow.impl;
 
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.jdbc.core.RowMapper;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.flow.TaskDao;
import com.xpsoft.oa.model.flow.JbpmTask;
import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;
 
 public class TaskDaoImpl extends BaseDaoImpl<TaskImpl>
   implements TaskDao
 {
   public TaskDaoImpl()
   {
     super(TaskImpl.class);
   }
 
   public List<TaskImpl> getTasksByUserId(String userId, PagingBean pb)
   {
     AppUser user = (AppUser)getHibernateTemplate().load(AppUser.class, new Long(userId));
     Iterator rolesIt = user.getRoles().iterator();
     StringBuffer groupIds = new StringBuffer();
     int i = 0;
     while (rolesIt.hasNext()) {
       if (i++ > 0) groupIds.append(",");
       groupIds.append("'" + ((AppRole)rolesIt.next()).getRoleId().toString() + "'");
     }
 
     StringBuffer hqlSb = new StringBuffer();
     hqlSb.append("select task from org.jbpm.pvm.internal.task.TaskImpl task left join task.participations pt where task.assignee=?");
     hqlSb.append(" or ( task.assignee is null and pt.type = 'candidate' and ((pt.userId=?)");
 
     if (user.getRoles().size() > 0) {
       hqlSb.append(" or (pt.groupId in (" + groupIds.toString() + "))");
     }
     hqlSb.append("))");
     hqlSb.append(" order by task.createTime desc");
 
     return findByHql(hqlSb.toString(), new Object[] { userId, userId }, pb);
   }
 
   public List<JbpmTask> getByActivityNameVarKeyLongVal(String activityName, String varKey, Long value)
   {
     String sql = "select task.DBID_ taskId, task.ASSIGNEE_ assignee from jbpm4_task task join jbpm4_variable var on task.EXECUTION_=var.EXECUTION_ where  task.ACTIVITY_NAME_=? and var.KEY_=? and var.LONG_VALUE_=?";
     Collection jbpmtask = this.jdbcTemplate.query(sql, new Object[] { activityName, varKey, value }, 
       new RowMapper()
     {
       public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
         JbpmTask task = new JbpmTask();
         Long taskId = Long.valueOf(rs.getLong("taskId"));
         String assignee = rs.getString("assignee");
         task.setAssignee(assignee);
         task.setTaskId(taskId);
         return task;
       }
     });
     return new ArrayList(jbpmtask);
   }
 
   public List<Long> getGroupByTask(Long taskId) {
     String sql = "select pa.GROUPID_ groupId from jbpm4_participation pa  where pa.TYPE_ = 'candidate'and pa.TASK_=?";
     Collection groupIds = this.jdbcTemplate.query(sql, new Object[] { taskId }, 
       new RowMapper()
     {
       public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
         String groupId = rs.getString("groupId");
         return groupId;
       }
     });
     return new ArrayList(groupIds);
   }
 
   public List<Long> getUserIdByTask(Long taskId) {
     String hql = "from org.jbpm.pvm.internal.task.TaskImpl task where task.superTask.id=?";
     Object[] objs = { taskId };
     List<TaskImpl> taskList = findByHql(hql, objs);
     List list = new ArrayList();
     for (TaskImpl task : taskList) {
       list.add(new Long(task.getAssignee()));
     }
     return list;
   }
 }
