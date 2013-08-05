 package com.xpsoft.oa.service.info.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.info.NewsDao;
 import com.xpsoft.oa.model.info.News;
 import com.xpsoft.oa.service.info.NewsService;
 import java.util.List;
 
 public class NewsServiceImpl extends BaseServiceImpl<News>
   implements NewsService
 {
   private NewsDao newsDao;
 
   public NewsServiceImpl(NewsDao dao)
   {
     super(dao);
     this.newsDao = dao;
   }
 
   public List<News> findByTypeId(Long typeId, PagingBean pb)
   {
     return this.newsDao.findByTypeId(typeId, pb);
   }
 
   public List<News> findBySearch(String searchContent, PagingBean pb)
   {
     return this.newsDao.findBySearch(searchContent, pb);
   }
 
   public List<News> findImageNews(PagingBean pb)
   {
     return this.newsDao.findImageNews(pb);
   }
 }

