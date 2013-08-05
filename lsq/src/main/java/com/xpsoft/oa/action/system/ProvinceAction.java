package com.xpsoft.oa.action.system;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.PinyingUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.system.City;
import com.xpsoft.oa.model.system.Province;
import com.xpsoft.oa.service.system.CityService;
import com.xpsoft.oa.service.system.ProvinceService;
/**
 * 
 * @author 
 *
 */
public class ProvinceAction extends BaseAction{
	@Resource
	private ProvinceService provinceService;
	@Resource
	private CityService cityService;
	private Province province;
	
	private Long provinceId;


	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Province> list= provinceService.getAll(filter);
		
		Type type=new TypeToken<List<Province>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	
	public String getTree(){
		String provinceId = getRequest().getParameter("provinceId");
		StringBuffer buff = new StringBuffer();
		if(StringUtils.isNotBlank(provinceId)){
			if(provinceId.startsWith("xnode")||"0".equals(provinceId)){
				buff.append("[");
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addSorted("sort", QueryFilter.ORDER_ASC);
				filter.getPagingBean().setPageSize(Integer.MAX_VALUE);
				List<Province> list= provinceService.getAll(filter);
				if(list!=null&&list.size()>0){
					for(Province pro:list){
						buff.append("{provinceId:'" + pro.getProvinceId()).append("',text:'" + pro.getProvinceName()).append("'},");
					}
				}
				if (!list.isEmpty()) {
			       buff.deleteCharAt(buff.length() - 1);
			    }
				buff.append("]");
			}else{
				List<City> cityList = cityService.findCityByProvinceId(Long.valueOf(provinceId));
				buff.append("[");
				if(cityList!=null&&cityList.size()>0){
					for(City city:cityList){
						buff.append("{cityId:'" + city.getCityId()).append("',text:'" + city.getCityName()).append("',leaf:true},");
					}
				}
				if(!cityList.isEmpty()) {
				       buff.deleteCharAt(buff.length() - 1);
				}
				buff.append("]");
			}
		}
		setJsonString(buff.toString());
		return SUCCESS;
	}
	
	public String tree(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addSorted("sort", "ASC");
		List<Province> list= provinceService.getAll(filter);
		if (null == list || list.size() == 0) {
			return "[]";
		}

		StringBuffer sb = new StringBuffer();
		sb.append("[{id:-1,text:'朗捷通智能',expanded:true,children:[");

		for (Province province : list) {
			sb.append("{id:'");
			sb.append(province.getProvinceId());
			sb.append("',expanded : true,text:'");
			sb.append(province.getProvinceName());			
			sb.append("',children:[]},");
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}]");
		
		setJsonString(sb.toString());
		return SUCCESS;
		
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				provinceService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		Province province=provinceService.get(provinceId);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(province));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		provinceService.save(province);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	public String testData(){
		List<Province> provinceList = provinceService.getAll();
		List<City> cityList = cityService.getAll();
		List<String> proStr = new ArrayList<String>();
		List<String> cityStr = new ArrayList<String>();
		Map<String,String> map = new HashMap<String, String>();
		Map<String,String> finalMap = new HashMap<String, String>();
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		StringBuilder sb1 = new StringBuilder();
		String py = "";
		for(Province province:provinceList){
			System.out.println(province.getProvinceName()+"："+PinyingUtil.getHeadString(province.getProvinceName()));
			sb1.append(province.getProvinceName()+"："+PinyingUtil.getHeadString(province.getProvinceName())+"\n");
			proStr.add(PinyingUtil.getHeadString(province.getProvinceName()));
			py = PinyingUtil.getHeadString(province.getProvinceName());
			if(StringUtils.isNotBlank(map.get(py))){
				map.put(py, map.get(py)+","+province.getProvinceName());
				finalMap.put(py, map.get(py));
			}else{
				map.put(py, province.getProvinceName());
			}
		}
//		for(City city:cityList){
//			System.out.println(city.getCityName()+"："+PinyingUtil.getHeadString(city.getCityName()));
//			sb1.append(city.getCityName()+"："+PinyingUtil.getHeadString(city.getCityName())+"\n");
//			cityStr.add(PinyingUtil.getHeadString(city.getCityName()));
//			
//			py = PinyingUtil.getHeadString(city.getCityName());
//			if(StringUtils.isNotBlank(map.get(py))){
//				map.put(py, map.get(py)+","+city.getCityName());
//				finalMap.put(py, map.get(py));
//			}else{
//				map.put(py, city.getCityName());
//			}
//		}
		System.out.println(proStr.retainAll(cityStr));
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,proStr:");
		sb.append(gson.toJson(finalMap));
//		sb.append("cityStr:"+gson.toJson(cityStr));
		sb.append("}");
		setJsonString(sb.toString());
		File file = new File("c://aa.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(gson.toJson(finalMap).replaceAll("\",\"", "\n"));
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
