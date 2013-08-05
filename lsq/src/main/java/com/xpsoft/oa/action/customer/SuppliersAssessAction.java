package com.xpsoft.oa.action.customer;


import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.PinyingUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.customer.SuppliersAssess;
import com.xpsoft.oa.model.system.City;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.model.system.Province;
import com.xpsoft.oa.service.customer.SuppliersAssessService;
import com.xpsoft.oa.service.system.CityService;
import com.xpsoft.oa.service.system.FileAttachService;
import com.xpsoft.oa.service.system.ProvinceService;
/**
 * 
 * @author 
 *
 */
public class SuppliersAssessAction extends BaseAction{
	@Resource
	private SuppliersAssessService suppliersAssessService;
	private SuppliersAssess suppliersAssess;
	@Resource
	private ProvinceService provinceService;
	@Resource
	private CityService cityService;
	@Resource
	private FileAttachService fileAttachService;
	private Long suppliersId;

	public Long getSuppliersId() {
		return suppliersId;
	}

	public void setSuppliersId(Long suppliersId) {
		this.suppliersId = suppliersId;
	}

	public SuppliersAssess getSuppliersAssess() {
		return suppliersAssess;
	}

	public void setSuppliersAssess(SuppliersAssess suppliersAssess) {
		this.suppliersAssess = suppliersAssess;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_delFlag_S_LK", "0");
		List<SuppliersAssess> list= suppliersAssessService.getAll(filter);
		
		Type type=new TypeToken<List<SuppliersAssess>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
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
				SuppliersAssess suppliersAssess=suppliersAssessService.get(new Long(id));
				suppliersAssess.setDelFlag("1");
				suppliersAssessService.merge(suppliersAssess);
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
		SuppliersAssess suppliersAssess=suppliersAssessService.get(suppliersId);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(suppliersAssess));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String attachIds = getRequest().getParameter("attachIds");
		if(StringUtils.isNotBlank(attachIds)){
			Set<FileAttach> fileSet = new HashSet<FileAttach>();
			String fileIds[] = attachIds.split(",");
			suppliersAssess.getFileAttachs().clear();
			for(String fileId:fileIds){
				fileSet.add(fileAttachService.get(Long.valueOf(fileId)));
			}
			suppliersAssess.getFileAttachs().addAll(fileSet);
		}
		if(suppliersAssess.getProject()==null||suppliersAssess.getProject().getId()==null){
			suppliersAssess.setProject(null);
		}
		if(suppliersAssess.getCity()==null||suppliersAssess.getCity().getCityId()==null){
			suppliersAssess.setCity(null);
		}
		if(suppliersAssess.getApplyUser()==null||suppliersAssess.getApplyUser().getUserId()==null){
			suppliersAssess.setApplyUser(null);
		}
		if(suppliersAssess.getSuppliersId()==null){//新增
			Province province = provinceService.get(suppliersAssess.getProvince().getProvinceId());
			City city = cityService.get(suppliersAssess.getCity().getCityId());
			if(province!=null){
				String provinceName = province.getProvinceName();
				String cityName = "";
				if(city!=null){
					cityName = city.getCityName();
				}
				if(provinceName.contains("省")){
					provinceName = provinceName.substring(0,provinceName.lastIndexOf("省"));
				}else if(provinceName.contains("市")){
					provinceName = provinceName.substring(0,provinceName.lastIndexOf("市"));
				}
				if(provinceName.contains("江苏")){
					if("苏州市".equals(cityName)){
						provinceName = "苏州";
					}else{
						provinceName = "江苏其他";
					}
				}
				String search = PinyingUtil.getProvinceHeadString(provinceName)+".";
				String suppliersNo = suppliersAssessService.getMaxNo(search);
				suppliersAssess.setSuppliersNo(suppliersNo);
			}
			suppliersAssess.setCreatetime(new Date());
			suppliersAssess.setProcessRunId(-1L);
		}
		suppliersAssess.setCreatetime(new Date());
		suppliersAssessService.save(suppliersAssess);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String updateCity(){
		QueryFilter filter=new QueryFilter(getRequest());
		filter.getPagingBean().setPageSize(Integer.MAX_VALUE);
		List<SuppliersAssess> list= suppliersAssessService.getAll(filter);
		for(SuppliersAssess sa:list){
			  if(!sa.getSuppliersName().contains("地区_")){
				  continue;
			  }
			  String area = sa.getSuppliersName().substring(0,sa.getSuppliersName().lastIndexOf("地区_"));
			  if(area.contains("江苏其他")){
				  area = "江苏";
			  }
			  QueryFilter filter1=new QueryFilter(getRequest());
			  filter1.getPagingBean().setPageSize(Integer.MAX_VALUE);
			  filter1.addFilter("Q_provinceName_S_LK", area);
			  List<Province> provinceList = provinceService.getAll(filter1);
			  if(provinceList!=null&&provinceList.size()>0){
				  sa.setProvince(provinceList.get(0));
			  }
			  if(!area.contains("吉林")){//中国唯一一个与省相名的市   吉林省 吉林市
				  QueryFilter filter2=new QueryFilter(getRequest());
				  filter2.getPagingBean().setPageSize(Integer.MAX_VALUE);
				  filter2.addFilter("Q_cityName_S_LK", area);
				  List<City> cityList = cityService.getAll(filter2);
				  if(cityList!=null&&cityList.size()>0){
					  sa.setCity(cityList.get(0));
					  sa.setProvince(cityList.get(0).getProvince());
				  }
			  }
			  suppliersAssessService.save(sa);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String checkSuppliersName(){
		String suppliersName = getRequest().getParameter("suppliersName");
		SuppliersAssess suppliersAssess = suppliersAssessService.getByName(suppliersName);
		StringBuffer sb = new StringBuffer();
		if(suppliersAssess == null){
			sb.append("{success:true}");
		}else{
			sb.append("{success:false}");
		}
		jsonString = sb.toString();
		return SUCCESS;
	}
}
