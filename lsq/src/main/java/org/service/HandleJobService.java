package org.service;

import org.model.HandleJob;

import com.xpsoft.core.service.BaseService;

public interface HandleJobService extends BaseService<HandleJob> {

	/**
	 * 根据实例id查
	 * @param piId
	 * @return
	 */
	public HandleJob getHandleJobByRunId(String piId);
	
	
	public String getMaxBillCode(String prebillCode);
}
