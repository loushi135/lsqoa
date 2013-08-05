package com.xpsoft.oa.service.system;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.AnnounceRemind;

public interface AnnounceRemindService extends BaseService<AnnounceRemind>{
	public String needNotice(Long userid);
	public void deleteByAnnounceId(long announceId);
}


