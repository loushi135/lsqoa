package com.xpsoft.oa.service.bbs;


import java.util.Date;
import java.util.List;
import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.bbs.BbsTopic;
public interface BbsTopicService extends BaseService<BbsTopic>{
	public void topList(String ids[]);
	public abstract Long getMaxId();
	
}


