package com.xpsoft.oa.service.statistics.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.SignApplyDao;
import com.xpsoft.oa.model.statistics.SignApply;
import com.xpsoft.oa.service.statistics.SignApplyService;

public class SignApplyServiceImpl extends BaseServiceImpl<SignApply> implements SignApplyService{
	private SignApplyDao dao;
	
	public SignApplyServiceImpl(SignApplyDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public String getMaxSignNo() {
		// TODO Auto-generated method stub
		String hql = "from SignApply order by signNo desc";
		List<SignApply> list = this.dao.findByHql(hql, null, 0,1);
		String maxNo = "";
		if(list!=null&&list.size()>0){
			maxNo = list.get(0).getSignNo();
			Integer no = Integer.valueOf(maxNo)+1;
			maxNo = String.valueOf(no);
			if(maxNo.length()<3){
				maxNo = "0"+String.valueOf(no);
			}else if(maxNo.length()<2){
				maxNo = "00"+String.valueOf(no);
			}
		}else{
			maxNo = "001";
		}
		return maxNo;
	}

	
}