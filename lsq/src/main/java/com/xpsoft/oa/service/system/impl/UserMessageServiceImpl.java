package com.xpsoft.oa.service.system.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.UserMessageDao;
import com.xpsoft.oa.model.system.UserMessage;
import com.xpsoft.oa.service.system.UserMessageService;

public class UserMessageServiceImpl extends BaseServiceImpl<UserMessage> implements UserMessageService{
	private UserMessageDao dao;
	
	public UserMessageServiceImpl(UserMessageDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Long getMessageCount(Long userId) {
		return dao.getMessageCount(userId);
	}

}