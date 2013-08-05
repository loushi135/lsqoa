 package com.xpsoft.oa.dao.communicate.impl;
 
 import com.xpsoft.core.Constants;
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.core.util.ContextUtil;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.communicate.MailBoxDao;
 import com.xpsoft.oa.model.communicate.MailBox;
 import java.util.ArrayList;
 import java.util.List;
 import org.apache.commons.lang.StringUtils;
 import org.hibernate.Query;
 import org.hibernate.Session;
 
 public class MailBoxDaoImpl extends BaseDaoImpl<MailBox>
   implements MailBoxDao
 {
   public MailBoxDaoImpl()
   {
     super(MailBox.class);
   }
 
   public Long CountByFolderId(Long folderId)
   {
     String hql = "select count(*) from MailBox where folderId =?";
 
     Query query = getSession().createQuery(hql);
     query.setLong(0, folderId.longValue());
     return (Long)query.uniqueResult();
   }
 
   public List<MailBox> findByFolderId(Long folderId) {
     String hql = "from MailBox where folderId = ?";
     return findByHql(hql, new Object[] { folderId });
   }
 
   public List<MailBox> findBySearch(String searchContent, PagingBean pb)
   {
     ArrayList params = new ArrayList();
 
     StringBuffer hql = new StringBuffer("from MailBox mb where mb.delFlag = ? and mb.appUser.userId =?");
     params.add(Constants.FLAG_UNDELETED);
     params.add(ContextUtil.getCurrentUserId());
 
     if (StringUtils.isNotEmpty(searchContent)) {
       hql.append(" and (mb.mail.subject like ? or mb.mail.content like ?)");
       params.add("%" + searchContent + "%");
       params.add("%" + searchContent + "%");
     }
 
     hql.append(" order by mb.sendTime desc");
     return findByHql(hql.toString(), params.toArray(), pb);
   }
 }

