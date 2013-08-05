package com.xpsoft.oa.action.statistics;


import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.MaterialContract;
import com.xpsoft.oa.service.statistics.MaterialContractService;
/**
 * 
 * @author 
 *
 */
public class MaterialContractAction extends BaseAction{
	@Resource
	private MaterialContractService materialContractService;
	private MaterialContract materialContract;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MaterialContract getMaterialContract() {
		return materialContract;
	}

	public void setMaterialContract(MaterialContract materialContract) {
		this.materialContract = materialContract;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<MaterialContract> list= materialContractService.getAll(filter);
		
		Type type=new TypeToken<List<MaterialContract>>(){}.getType();
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
				materialContractService.remove(new Long(id));
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
		MaterialContract materialContract=materialContractService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(materialContract));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(materialContract.getId()==null){
			String projectNo = materialContract.getProNo();
			if(StringUtils.isNotBlank(projectNo)){
				QueryFilter queryFilter = new QueryFilter(ServletActionContext.getRequest());
				queryFilter.getPagingBean().setPageSize(1);
				queryFilter.addFilter("Q_proNo_S_EQ", projectNo);
				queryFilter.addSorted("contractNo", QueryFilter.ORDER_DESC);
				List<MaterialContract> conList = materialContractService.getAll(queryFilter);
				String newNo="";
				if(conList!=null&&conList.size()>0){
					MaterialContract mc = conList.get(0);
					String conNo = mc.getContractNo();
					Integer num = Integer.parseInt(conNo.substring(conNo.length()-4));
					newNo = String.valueOf(num+1);
					if(newNo.length()<2){
						newNo = "000" + String.valueOf((num + 1));
					}else if(newNo.length()<3){
						newNo = "00" + String.valueOf((num + 1));
					}else if(newNo.length()<4){
						newNo = "0" + String.valueOf((num + 1));
					}
				}else{
					newNo="0001";
				}
				materialContract.setContractNo(projectNo+newNo);
			}
		}
		if(materialContract.getMaterialDeptCharger()==null||materialContract.getMaterialDeptCharger().getUserId()==null){
			materialContract.setMaterialDeptCharger(null);
		}
		materialContractService.save(materialContract);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
