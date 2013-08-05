package com.xpsoft.oa.service.statistics;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.BillAdjust;

public interface BillAdjustService extends BaseService<BillAdjust>{
	
	public void saveBillAndBillAdjust(BillAdjust billAdjust);
}


