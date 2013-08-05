package com.xpsoft.oa.service.statistics.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.ConstructioncontractDao;
import com.xpsoft.oa.dao.statistics.ProjectNewDao;
import com.xpsoft.oa.model.statistics.Constructioncontract;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.service.statistics.ConstructioncontractService;

public class ConstructioncontractServiceImpl extends BaseServiceImpl<Constructioncontract> implements ConstructioncontractService{
	private ConstructioncontractDao dao;
	@Autowired
	private ProjectNewDao projectNewDao;
	public ConstructioncontractServiceImpl(ConstructioncontractDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void saveConstructioncontract(
			Constructioncontract constructioncontract) {
		
		dao.save(constructioncontract);
		//修改项目数据   先查找关联项目
		String hql = "from ProjectNew where contract.contractId=?";
		List<ProjectNew> projectList = projectNewDao.findByHql(hql, new Object[]{constructioncontract.getContractId()});
		if(projectList!=null&&projectList.size()>0){
			for(ProjectNew project:projectList){
				project.setProName(constructioncontract.getProjectName());
				project.setProCharger(constructioncontract.getProjectCharger());
				project.setProChargerUser(constructioncontract.getProjectChargerUser());
				if(constructioncontract.getProjectChargerUser()!=null){
					project.setProChargerTel(constructioncontract.getProjectChargerUser().getMobile());
				}
				if(constructioncontract.getDeptRegional()!=null){
					project.setArea(constructioncontract.getDeptRegional().getDepName());
				}
				project.setBusinessMain(constructioncontract.getBusinessCharger());
				projectNewDao.save(project);
			}
		}
	}

}