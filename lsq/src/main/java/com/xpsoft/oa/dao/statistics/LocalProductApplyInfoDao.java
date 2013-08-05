package com.xpsoft.oa.dao.statistics;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.LocalProductApplyInfo;

/**
 * 
 * @author 
 *
 */
public interface LocalProductApplyInfoDao extends BaseDao<LocalProductApplyInfo>{
	
	public void deleteLocalProductApplyInfos(Long applyId,List<Long> idList);
}