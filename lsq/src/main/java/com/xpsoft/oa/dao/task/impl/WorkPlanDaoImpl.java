 package com.xpsoft.oa.dao.task.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.task.WorkPlanDao;
 import com.xpsoft.oa.model.system.AppUser;
 import com.xpsoft.oa.model.system.Department;
 import com.xpsoft.oa.model.task.PlanType;
 import com.xpsoft.oa.model.task.WorkPlan;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Set;
 import org.apache.commons.lang.StringUtils;
 
 public class WorkPlanDaoImpl extends BaseDaoImpl<WorkPlan>
   implements WorkPlanDao
 {
   public WorkPlanDaoImpl()
   {
     super(WorkPlan.class);
   }
 
   public List<WorkPlan> findByDepartment(WorkPlan workPlan, AppUser user, PagingBean pb)
   {
     StringBuffer sb = new StringBuffer();
     ArrayList list = new ArrayList();
     if (!user.getRights().contains("__ALL")) {
       sb.append("select wp from WorkPlan wp,PlanAttend pa where pa.workPlan=wp and wp.status=1 and wp.isPersonal=0 and ((pa.appUser.userId=? and pa.isDep=0)");
       Department dep = user.getDepartment();
       list.add(user.getUserId());
       if (dep != null) {
         String path = dep.getPath();
         if (StringUtils.isNotEmpty(path)) {
           StringBuffer buff = new StringBuffer(path.replace(".", ","));
           buff.deleteCharAt(buff.length() - 1);
           sb.append(" or (pa.department.depId in (" + buff.toString() + ") and pa.isDep=1)");
         }
       }
       sb.append(")");
     } else {
       sb.append("select wp from WorkPlan wp where wp.status=1 and wp.isPersonal=0");
     }
     if (workPlan != null) {
       if (StringUtils.isNotEmpty(workPlan.getPlanName())) {
         sb.append(" and wp.planName like ?");
         list.add("%" + workPlan.getPlanName() + "%");
       }
       if (StringUtils.isNotEmpty(workPlan.getPrincipal())) {
         sb.append(" and wp.principal like ?");
         list.add("%" + workPlan.getPrincipal() + "%");
       }
       if ((workPlan.getPlanType() != null) && 
         (workPlan.getPlanType().getTypeId() != null)) {
         sb.append(" and wp.planType.typeId = ?");
         list.add(workPlan.getPlanType().getTypeId());
       }
 
     }
 
     sb.append(" group by wp.planId");
     return findByHql(sb.toString(), list.toArray(), pb);
   }
 }
