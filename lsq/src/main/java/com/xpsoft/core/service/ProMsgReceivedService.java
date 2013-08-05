package com.xpsoft.core.service;

import java.util.List;

import com.xpsoft.core.model.ProMsgReceived;



public interface ProMsgReceivedService extends BaseService<ProMsgReceived>{
	public List<ProMsgReceived> getByStrId(String strId);
}


