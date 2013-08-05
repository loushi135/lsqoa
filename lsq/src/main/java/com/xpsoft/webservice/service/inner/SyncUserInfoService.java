package com.xpsoft.webservice.service.inner;


public interface SyncUserInfoService {
	
	/**
	 * 
	 * @param updateDate 格式： 2013-05-05
	 * @return
	 */
	public String getUser(String updateDate);
	/**
	 * 返回所有部门与岗位信息
	 * @return
	 */
	public String getDepartmentAndJob();
	/**
	 * 通过部门id返回部门信息
	 * @param id
	 * @return
	 */
	public String getDeptInfoById(String id);
}
