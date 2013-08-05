package com.xpsoft.oa.dao.admin;

import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.admin.GoodApplyTotal;
import com.xpsoft.oa.model.admin.GoodsApply;

public abstract interface GoodsApplyDao extends BaseDao<GoodsApply> {
	public List<GoodApplyTotal> getGoodApplyTotals(String month);
	
	public List<String> getDeptList(String month);
}
