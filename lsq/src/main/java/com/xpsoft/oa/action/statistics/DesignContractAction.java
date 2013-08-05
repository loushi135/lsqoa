package com.xpsoft.oa.action.statistics;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.DesignContract;
import com.xpsoft.oa.service.statistics.DesignContractService;
/**
 * 
 * @author 
 *
 */
public class DesignContractAction extends BaseAction{
	@Resource
	private DesignContractService designContractService;
	private DesignContract designContract;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DesignContract getDesignContract() {
		return designContract;
	}

	public void setDesignContract(DesignContract designContract) {
		this.designContract = designContract;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<DesignContract> list= designContractService.getAll(filter);
		
		Type type=new TypeToken<List<DesignContract>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
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
				designContractService.remove(new Long(id));
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
		DesignContract designContract=designContractService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(designContract));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(designContract.getId()==null){
			String contractType = "01";
			String year = new SimpleDateFormat("yyyy").format(new Date());
			String search = "LJT"+contractType+year;
			HttpServletRequest request = ServletActionContext.getRequest();
			QueryFilter filter = new QueryFilter(request);
			filter.addFilter("Q_contractNo_S_LK", search);
			filter.addSorted("contractNo", "desc");
			filter.addFilter("limit", "1");
			List<DesignContract> conList = designContractService.getAll(filter);
			String newNo="";
			if(conList!=null&&conList.size()>0){
				DesignContract lastCon = conList.get(0);
				String contractNo = lastCon.getContractNo();
				Integer num = Integer.parseInt(contractNo.substring(contractNo
						.lastIndexOf(search)+search.length()));
				newNo = String.valueOf(num+1);
				if(newNo.length()<2){
					newNo = "00" + String.valueOf((num + 1));
				}else if(newNo.length()<3){
					newNo = "0" + String.valueOf((num + 1));
				}
			}else{
				newNo = "001";
			}
			String contractNo = search + newNo;
			designContract.setContractNo(contractNo);
		}
		designContractService.save(designContract);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String getNo(){
		StringBuffer sb = new StringBuffer("{success:true,data:");
		String contractType = getRequest().getParameter("contractType");
		String year = new SimpleDateFormat("yyyy").format(new Date());
		String search = "LJT"+contractType+year;
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_contractNo_S_LK", search);
		filter.addSorted("contractNo", "desc");
		filter.addFilter("limit", "1");
		List<DesignContract> conList = designContractService.getAll(filter);
		String newNo="";
		if(conList!=null&&conList.size()>0){
			DesignContract lastCon = conList.get(0);
			String contractNo = lastCon.getContractNo();
			Integer num = Integer.parseInt(contractNo.substring(contractNo
					.lastIndexOf(search)+search.length()));
			newNo = String.valueOf(num+1);
			if(newNo.length()<2){
				newNo = "00" + String.valueOf((num + 1));
			}else if(newNo.length()<3){
				newNo = "0" + String.valueOf((num + 1));
			}
		}else{
			newNo = "001";
		}
		sb.append("{contractNo:'" + search + newNo + "'}");
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
}
