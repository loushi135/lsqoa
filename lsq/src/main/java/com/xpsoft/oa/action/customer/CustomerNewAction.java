package com.xpsoft.oa.action.customer;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.PinyingUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.customer.CustomerNew;
import com.xpsoft.oa.model.system.City;
import com.xpsoft.oa.model.system.Province;
import com.xpsoft.oa.service.customer.CustomerNewService;
import com.xpsoft.oa.service.system.CityService;
import com.xpsoft.oa.service.system.ProvinceService;
/**
 * 
 * @author 
 *
 */
public class CustomerNewAction extends BaseAction{
	@Resource
	private CustomerNewService customerNewService;
	private CustomerNew customerNew;
	@Resource
	private CityService cityService;
	@Resource
	private ProvinceService provinceService;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerNew getCustomerNew() {
		return customerNew;
	}

	public void setCustomerNew(CustomerNew customerNew) {
		this.customerNew = customerNew;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<CustomerNew> list= customerNewService.getAll(filter);
		
		Type type=new TypeToken<List<CustomerNew>>(){}.getType();
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
				CustomerNew customerNew=customerNewService.get(new Long(id));
				customerNew.setDelFlag("1");
				customerNewService.merge(customerNew);
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
		CustomerNew customerNew=customerNewService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(customerNew));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(customerNew.getId()==null){
			String newCode="";
//			String fullname = customerNew.getFullname();
			String cityName = "";
			if(customerNew.getProvince()!=null&&customerNew.getProvince().getProvinceId()!=null){
				Province province = provinceService.get(customerNew.getProvince().getProvinceId());
				customerNew.setProvince(province);
				if(customerNew.getCity()!=null&&customerNew.getCity().getCityId()!=null){
					City city = cityService.get(customerNew.getCity().getCityId());
					cityName = city.getCityName();
					customerNew.setCity(city);
				}else{
					cityName = province.getProvinceName();
					customerNew.setCity(null);
				}
			}
//			if(fullname.contains("_")){
//				String area = fullname.substring(0,fullname.lastIndexOf("地区_"));
				String area = this.getSimpleCode(cityName);
				String signArea = PinyingUtil.getHeadString(area)+".";
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_code_S_LK", signArea);
				filter.addSorted("code", "desc");
				filter.addFilter("limit", "1");
				List<CustomerNew> cusList = customerNewService.getAll(filter);;
				if(cusList!=null&&cusList.size()>0){
					CustomerNew lastCus = cusList.get(0);
					String code = lastCus.getCode();
					Integer num = Integer.parseInt(code.substring(code
							.lastIndexOf(signArea)+signArea.length()));
					newCode = String.valueOf(num+1);
					if(newCode.length()<2){
						newCode = "000" + String.valueOf((num + 1));
					}else if(newCode.length()<3){
						newCode = "00" + String.valueOf((num + 1));
					}else if(newCode.length()<4){
						newCode = "0" + String.valueOf((num + 1));
					}
				}else{
					newCode = "0001";
				}
				customerNew.setCode(signArea+newCode);
//			}
		}else{
			if(customerNew.getCity()==null||customerNew.getCity().getCityId()==null){
				customerNew.setCity(null);
			}
		}
		customerNewService.save(customerNew);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	private String getSimpleCode(String area){
		 String specil[] = new String[]{"省","市","壮族自治区","回族自治区","维吾尔自治区","自治区","自治州","特别行政区","地区"};
		 for(String str:specil){
			 if(area.contains(str)){
				return area.substring(0,area.lastIndexOf(str));
			 }
		 }
		 return "";
	}
	
	public String updateCity(){
		QueryFilter filter=new QueryFilter(getRequest());
		filter.getPagingBean().setPageSize(Integer.MAX_VALUE);
		List<CustomerNew> list= customerNewService.getAll(filter);
		for(CustomerNew cus:list){
			  String area = cus.getFullname().substring(0,cus.getFullname().lastIndexOf("地区_"));
			  QueryFilter filter1=new QueryFilter(getRequest());
			  filter1.getPagingBean().setPageSize(Integer.MAX_VALUE);
			  filter1.addFilter("Q_provinceName_S_LK", area);
			  List<Province> provinceList = provinceService.getAll(filter1);
			  if(provinceList!=null&&provinceList.size()>0){
				  cus.setProvince(provinceList.get(0));
			  }
			  if(!area.contains("吉林")){//中国唯一一个与省相名的市   吉林省 吉林市
				  QueryFilter filter2=new QueryFilter(getRequest());
				  filter2.getPagingBean().setPageSize(Integer.MAX_VALUE);
				  filter2.addFilter("Q_cityName_S_LK", area);
				  List<City> cityList = cityService.getAll(filter2);
				  if(cityList!=null&&cityList.size()>0){
					  cus.setCity(cityList.get(0));
					  cus.setProvince(cityList.get(0).getProvince());
				  }
			  }
			  customerNewService.save(cus);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
