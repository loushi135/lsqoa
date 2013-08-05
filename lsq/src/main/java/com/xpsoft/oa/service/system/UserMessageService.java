package com.xpsoft.oa.service.system;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.UserMessage;

public interface UserMessageService extends BaseService<UserMessage>{
	public Long getMessageCount(Long userId);
	
}


