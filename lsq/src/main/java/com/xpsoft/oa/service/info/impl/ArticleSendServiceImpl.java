package com.xpsoft.oa.service.info.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.Constants;
import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.info.ArticleSendDao;
import com.xpsoft.oa.model.info.ArticleSend;
import com.xpsoft.oa.service.info.ArticleSendService;

public class ArticleSendServiceImpl extends BaseServiceImpl<ArticleSend> implements ArticleSendService{
	private ArticleSendDao dao;
	
	public ArticleSendServiceImpl(ArticleSendDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	public List<ArticleSend> findBySearch(ArticleSend articleSend, Date from, Date to, PagingBean pb)
	   {
	     StringBuffer hql = new StringBuffer("from ArticleSend articleSend where 1=1");
	     List params = new ArrayList();
	     if ((!"".equals(articleSend.getPostName())) && (articleSend.getPostName() != null)) {
	       hql.append("and articleSend.postName like ?");
	       params.add("%" + articleSend.getPostName() + "%");
	     }
	     if ((!"".equals(articleSend.getNoticeTitle())) && (articleSend.getNoticeTitle() != null)) {
	       hql.append("and articleSend.noticeTitle like ?");
	       params.add("%" + articleSend.getNoticeTitle() + "%");
	     }
	     if (from != null) {
	       hql.append("and articleSend.effectivDate > ?");
	       params.add(from);
	     }
	     if (to != null) {
	       hql.append("and articleSend.expirationDate < ?");
	       params.add(to);
	     }
	     return dao.findByHql(hql.toString(), params.toArray(), pb);
	   }
	 
	   public List<ArticleSend> findByNoticeId(Long paramLong, PagingBean pb)
	   {
	     String hql = "from ArticleSend articleSend where articleSend.noticeId=?";
	     Object[] params = { paramLong };
	     return dao.findByHql(hql, params, pb);
	   }
	 
	   public List<ArticleSend> findBySearch(String searchContent,String typeId, PagingBean pb)
	   {
	     ArrayList params = new ArrayList();
	     StringBuffer hql = new StringBuffer("from ArticleSend nt where nt.state = ?");
	     params.add(Constants.FLAG_ACTIVATION);
	     if (StringUtils.isNotEmpty(searchContent)) {
	       hql.append(" and (nt.noticeTitle like ? or nt.noticeContent like ?)");
	       params.add("%" + searchContent + "%");
	       params.add("%" + searchContent + "%");
	     }
	     if(StringUtils.isNotBlank(typeId)){
	       hql.append(" and nt.treeType.id = ?");
	       params.add(Long.valueOf(typeId));
	     }
	     hql.append(" order by nt.noticeId desc");
	     return dao.findByHql(hql.toString(), params.toArray(), pb);
	   }
}