package com.xpsoft.oa.service.admin;

import java.util.List;
import java.util.Map;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.admin.GoodApplyTotal;
import com.xpsoft.oa.model.admin.GoodsApply;

public abstract interface GoodsApplyService extends BaseService<GoodsApply> {
	public List<GoodApplyTotal> getGoodApplyTotals(String month);
	
	public List<String> getDeptList(String month);
	
	public void export(Map<String,Object> dataMap)throws Exception;
}
