package com.xpsoft.oa.service.system;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.City;

public interface CityService extends BaseService<City>{
	
	public List<City> findCityByProvinceId(Long provinceId);
}


