package com.xpsoft.core.service;

import java.util.Map;

import com.xpsoft.core.model.ProMsgDetail;
import com.xpsoft.oa.model.system.AppUser;



public interface ProMsgDetailService extends BaseService<ProMsgDetail>{
	public ProMsgDetail getByStrId(String strId);
	public boolean insertMsgDetail(AppUser user,String isReceived, String process_id, String task_id, String task_name,  Map<String,String> conMap,Map<String,Object> dataMap);
	public boolean checkReceivedMsgIfRight(String strId);
}


