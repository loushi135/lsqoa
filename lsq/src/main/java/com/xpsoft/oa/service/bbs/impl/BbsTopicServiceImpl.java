package com.xpsoft.oa.service.bbs.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.bbs.BbsTopicDao;
import com.xpsoft.oa.model.bbs.BbsTopic;
import com.xpsoft.oa.service.bbs.BbsTopicService;

public class BbsTopicServiceImpl extends BaseServiceImpl<BbsTopic> implements BbsTopicService{
	private BbsTopicDao dao;
	
	public BbsTopicServiceImpl(BbsTopicDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Long getMaxId() {
		
		return dao.getMaxId();
	}

	@Override
	public void topList(String ids[]) {
		BbsTopic bbsTopic=new BbsTopic();
		Long topId=getMaxId();
		for(String id:ids){
			bbsTopic=dao.get(new Long(id));
			if(bbsTopic.getIsTop()==0){
				bbsTopic.setIsTop(1);
			}
			topId+=1;
			bbsTopic.setTopId(topId);
			dao.save(bbsTopic);
		}
		
	}

}