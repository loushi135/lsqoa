package com.xpsoft.oa.service.system;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.Announce;

public interface AnnounceService extends BaseService<Announce>{
	public Announce getLastAnnounce(long userId);
	public void removeAnnounce(long announceId);
	public Announce saveAnnounce(Announce announce,String id);
}


