package com.xpsoft.oa.service.system.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.AnnounceDao;
import com.xpsoft.oa.dao.system.AnnounceRemindDao;
import com.xpsoft.oa.dao.system.AppUserDao;
import com.xpsoft.oa.dao.system.FileAttachDao;
import com.xpsoft.oa.model.system.Announce;
import com.xpsoft.oa.model.system.AnnounceRemind;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.system.AnnounceService;

public class AnnounceServiceImpl extends BaseServiceImpl<Announce> implements AnnounceService{
	private AnnounceDao dao;
	@Resource
	private AnnounceRemindDao announceRemindDao;
	@Resource
	private AppUserDao appUserDao;
	@Resource(name="fileAttachDao")
	private FileAttachDao fileAttachDao;
	
	public AnnounceServiceImpl(AnnounceDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Announce getLastAnnounce(long userId) {
		String sql = "select MAX(ar.announceId) from announce_remind as ar where userId = "+userId+" and flag <> 1";
		Object obj = announceRemindDao.findBySql(sql).get(0);
		if(obj != null){
			return dao.get(Long.parseLong(obj.toString()));
		}
		return null;
	}

	@Override
	public void removeAnnounce(long announceId) {
		dao.remove(announceId);
		announceRemindDao.deleteByAnnounceId(announceId);
	}

	@Override
	public Announce saveAnnounce(Announce announce,String id) {
		//添加附件
		Set<FileAttach> fileAttachs = new HashSet<FileAttach>(0);
		if(StringUtils.isNotEmpty(id)){
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				FileAttach fileAttach = fileAttachDao.get(new Long(ids[i].toString()));
				if(fileAttach != null){
					fileAttachs.add(fileAttach);
				}
			}
		}
		if(!fileAttachs.isEmpty()){
			announce.setFileAttachs(fileAttachs);
		}
		//拍段新增还是修改，来取决于是否要新增通知列表的数据
		if(announce.getId() == null){
			dao.save(announce);
			List<AppUser> appUsers = appUserDao.getAll();
			for (AppUser appUser : appUsers) {
				if(appUser.getStatus() == 1){
					AnnounceRemind announceRemind = new AnnounceRemind();
					announceRemind.setFlag(0);
					announceRemind.setAnnounce(announce);
					announceRemind.setUser(appUser);
					announceRemindDao.save(announceRemind);
				}
			}
		}else{
			dao.merge(announce);
		}
		return announce;
	}

}