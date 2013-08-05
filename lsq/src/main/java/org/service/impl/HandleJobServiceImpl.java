package org.service.impl;

import java.util.List;

import org.dao.HandleJobDao;
import org.model.HandleJob;
import org.service.HandleJobService;

import com.xpsoft.core.service.impl.BaseServiceImpl;

public class HandleJobServiceImpl extends BaseServiceImpl<HandleJob> implements
		HandleJobService {

	private HandleJobDao dao;
	
	public HandleJobServiceImpl(HandleJobDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public HandleJob getHandleJobByRunId(String runId) {
		// TODO Auto-generated method stub
		List<HandleJob> handleList = dao.findByHql("from HandleJob where runId=? order by id desc", new Object[]{runId});
		if(handleList!=null&&handleList.size()>0){
			return handleList.get(0);
		}else{
			return null;
		}
	}
	
	
	public String getMaxBillCode(String prebillCode){
		List<HandleJob> list=dao.findByHql("from HandleJob hj where hj.billCode like ?  order by hj.id desc", new Object[]{prebillCode+"%"});
		return list!=null&&list.size()>0?list.get(0).getBillCode():"";
	}

	
}
