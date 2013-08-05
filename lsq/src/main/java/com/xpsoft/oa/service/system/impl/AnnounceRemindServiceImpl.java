package com.xpsoft.oa.service.system.impl;


import java.util.List;

import javax.annotation.Resource;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.AnnounceDao;
import com.xpsoft.oa.dao.system.AnnounceRemindDao;
import com.xpsoft.oa.model.system.Announce;
import com.xpsoft.oa.model.system.AnnounceRemind;
import com.xpsoft.oa.service.system.AnnounceRemindService;

public class AnnounceRemindServiceImpl extends BaseServiceImpl<AnnounceRemind> implements AnnounceRemindService{
	private AnnounceRemindDao dao;
	@Resource
	private AnnounceDao announceDao;
	public AnnounceRemindServiceImpl(AnnounceRemindDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public String needNotice(Long userid) {
		List<Announce> announces = announceDao.getAll();
		if(announces == null || announces.isEmpty()){
			return "false";
		}else{
			int announceSize = announces.size();
			String hql = "select ar from AnnounceRemind ar where user.userId = ? and flag = 1";
			List<AnnounceRemind> announceReminds = dao.findByHql(hql, new Object[]{userid});
			int announceRemindSize = announceReminds.size();
			if(announceSize != announceRemindSize){
				return "true";
			}else{
				return "false";
			}
		}
	}

	@Override
	public void deleteByAnnounceId(long announceId) {
		dao.deleteByAnnounceId(announceId);
	}

}