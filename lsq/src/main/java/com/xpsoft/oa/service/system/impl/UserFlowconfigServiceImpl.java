package com.xpsoft.oa.service.system.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.UserFlowconfigDao;
import com.xpsoft.oa.model.system.UserFlowconfig;
import com.xpsoft.oa.service.system.UserFlowconfigService;



public class UserFlowconfigServiceImpl extends BaseServiceImpl<UserFlowconfig> implements UserFlowconfigService{
	private UserFlowconfigDao dao;
	
	public UserFlowconfigServiceImpl(UserFlowconfigDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public UserFlowconfig getByUserId(Long userId) {
		List<UserFlowconfig> ufList=this.dao.findByHql("from UserFlowconfig uf where uf.appUser.userId=?", new Object[]{userId});
		if(ufList.size()>0){
			return ufList.get(0);
		}else{
			return null;
		}
	}
	

}