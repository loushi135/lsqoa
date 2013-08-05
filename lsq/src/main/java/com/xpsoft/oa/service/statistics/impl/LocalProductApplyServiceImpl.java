package com.xpsoft.oa.service.statistics.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.oa.dao.statistics.LocalProductApplyDao;
import com.xpsoft.oa.dao.statistics.LocalProductApplyInfoDao;
import com.xpsoft.oa.model.statistics.LocalProductApply;
import com.xpsoft.oa.model.statistics.LocalProductApplyInfo;
import com.xpsoft.oa.service.statistics.LocalProductApplyService;

public class LocalProductApplyServiceImpl extends BaseServiceImpl<LocalProductApply> implements LocalProductApplyService{
	private LocalProductApplyDao dao;
	@Resource
	private LocalProductApplyInfoDao localProductApplyInfoDao;
	public LocalProductApplyServiceImpl(LocalProductApplyDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void saveLocalProductApply(LocalProductApply localProductApply, String dataList) {
		Boolean isNew = true;//新增标志
		if(localProductApply.getId()!=null){
			isNew = false;
		}
		dao.save(localProductApply);
		if(StringUtils.isNotBlank(dataList)){
			List<LocalProductApplyInfo> localProductApplyInfoList = JsonUtil.fromJsonTypes(dataList,
					new TypeToken<List<LocalProductApplyInfo>>() {
					}.getType());
			if(!isNew){//修改
				List<Long> idList = new ArrayList<Long>();
				for(LocalProductApplyInfo localProductApplyInfo:localProductApplyInfoList){
					if(localProductApplyInfo.getId()!=null){//修改的记录
						idList.add(localProductApplyInfo.getId());
					}
				}
				if(idList.size()>0){//先进行删除
					localProductApplyInfoDao.deleteLocalProductApplyInfos(localProductApply.getId(), idList);
				}
			}
			for(LocalProductApplyInfo localProductApplyInfo:localProductApplyInfoList){
				localProductApplyInfo.setLocalProductApply(localProductApply);
				localProductApplyInfoDao.save(localProductApplyInfo);
			}
		}
	}

}