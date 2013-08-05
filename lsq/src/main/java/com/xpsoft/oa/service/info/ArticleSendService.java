package com.xpsoft.oa.service.info;


import java.util.Date;
import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.info.ArticleSend;

public interface ArticleSendService extends BaseService<ArticleSend>{
	  public abstract List<ArticleSend> findByNoticeId(Long paramLong, PagingBean paramPagingBean);

	  public abstract List<ArticleSend> findBySearch(ArticleSend paramNotice, Date paramDate1, Date paramDate2, PagingBean paramPagingBean);

	  public abstract List<ArticleSend> findBySearch(String paramString,String typeId, PagingBean paramPagingBean);
}


