 package com.xpsoft.oa.dao.info.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.info.NewsCommentDao;
 import com.xpsoft.oa.model.info.NewsComment;
 
 public class NewsCommentDaoImpl extends BaseDaoImpl<NewsComment>
   implements NewsCommentDao
 {
   public NewsCommentDaoImpl()
   {
     super(NewsComment.class);
   }
 }

