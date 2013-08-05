 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.archive.ArchivesDao;
 import com.xpsoft.oa.model.archive.Archives;
 import com.xpsoft.oa.model.system.AppRole;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 
 public class ArchivesDaoImpl extends BaseDaoImpl<Archives>
   implements ArchivesDao
 {
   public ArchivesDaoImpl()
   {
     super(Archives.class);
   }
 
   public List<Archives> findByUserOrRole(Long userId, Set<AppRole> roles, PagingBean pb)
   {
     Iterator it = roles.iterator();
     StringBuffer sb = new StringBuffer();
     while (it.hasNext()) {
       if (sb.length() > 0) {
         sb.append(",");
       }
       sb.append(((AppRole)it.next()).getRoleId().toString());
     }
     StringBuffer hql = new StringBuffer("select vo1 from Archives vo1,ArchDispatch vo2 where vo2.archives=vo1 and vo2.archUserType=2 and (vo2.userId=?");
     if (sb.length() > 0) {
       hql.append(" or vo2.disRoleId in (" + sb + ")");
     }
     hql.append(")  group by vo1");
     Object[] objs = { userId };
     return findByHql(hql.toString(), objs, pb);
   }
 }

