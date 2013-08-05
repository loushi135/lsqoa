package com.xpsoft.oa.service.customer.impl;


import java.text.DecimalFormat;
import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.customer.SuppliersAssessDao;
import com.xpsoft.oa.model.customer.SuppliersAssess;
import com.xpsoft.oa.service.customer.SuppliersAssessService;

public class SuppliersAssessServiceImpl extends BaseServiceImpl<SuppliersAssess> implements SuppliersAssessService{
	private SuppliersAssessDao dao;
	
	public SuppliersAssessServiceImpl(SuppliersAssessDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public String getMaxNo(String search) {
		// TODO Auto-generated method stub
		String newMaxNo = "";
		String hql = " from SuppliersAssess where suppliersNo like ?  order by suppliersNo desc ";
		List<SuppliersAssess> suppList = dao.findByHql(hql,new Object[]{search+"%"},new PagingBean(0, 1));
		if(suppList!=null&&suppList.size()>0){
			SuppliersAssess suppliersAssess = suppList.get(0);
			String oldMaxNo = suppliersAssess.getSuppliersNo();
			DecimalFormat decimalFormat = new DecimalFormat("0000");
			newMaxNo = search+decimalFormat.format(Integer.valueOf(oldMaxNo.substring(search.length()))+1);
		}else{
			newMaxNo = search+"0001";
		}
		return newMaxNo;
	}

	@Override
	public SuppliersAssess getByName(String assessName) {
		
		String hql=" from SuppliersAssess where (suppliersName like ? or suppliersName like ?)  and delFlag=0 order by suppliersNo desc ";
		
		List<SuppliersAssess> list=dao.findByHql(hql, new Object[]{"%"+assessName+"","%"+assessName+" "});
		
		if(null==list||list.isEmpty()){
			return null;
		}
		
		if(list.size()>1){
			String temp="(";
			for(SuppliersAssess s:list){
				temp+=s.getSuppliersName()+",";
			}
			temp+=")";
			throw new RuntimeException("供应商："+assessName+"，查到"+list.size()+"条记录为："+temp);
		}
		
		SuppliersAssess assess=list.get(0);
		return assess;
	}

	@Override
	public SuppliersAssess getByName(String assessName, String assessNO) {
		String hql=" from SuppliersAssess where suppliersName like ? and suppliersNo=? order by suppliersNo desc ";
		
		List<SuppliersAssess> list=dao.findByHql(hql, new Object[]{"%"+assessName,assessNO});
		
		if(null==list||list.isEmpty()){
			return null;
		}
		
		if(list.size()>1){
			throw new RuntimeException("查到"+list.size()+"条记录,"+hql+"入参："+assessName+","+assessNO);
		}
		
		SuppliersAssess assess=list.get(0);
		return assess;
	}

}