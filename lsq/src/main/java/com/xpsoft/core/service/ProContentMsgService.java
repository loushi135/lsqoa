package com.xpsoft.core.service;

import com.xpsoft.core.model.ProContentMsg;



public interface ProContentMsgService extends BaseService<ProContentMsg>{
	public void deleteByProMsgDetailId(Long pmdId);
}


