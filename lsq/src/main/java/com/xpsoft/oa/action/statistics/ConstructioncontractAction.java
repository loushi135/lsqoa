package com.xpsoft.oa.action.statistics;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.Constructioncontract;
import com.xpsoft.oa.model.statistics.DesignContract;
import com.xpsoft.oa.service.statistics.ConstructioncontractService;
/**
 * 
 * @author 
 *
 */
public class ConstructioncontractAction extends BaseAction{
	@Resource
	private ConstructioncontractService constructioncontractService;
	private Constructioncontract constructioncontract;
	
	private Long contractId;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Constructioncontract getConstructioncontract() {
		return constructioncontract;
	}

	public void setConstructioncontract(Constructioncontract constructioncontract) {
		this.constructioncontract = constructioncontract;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_status_N_EQ", "0");
		List<Constructioncontract> list= constructioncontractService.getAll(filter);
		
		Type type=new TypeToken<List<Constructioncontract>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
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
				Constructioncontract constructioncontract=constructioncontractService.get(Long.valueOf(id));
				constructioncontract.setStatus(1);
				constructioncontractService.save(constructioncontract);
//				constructioncontractService.remove(new Long(id));
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
		Constructioncontract constructioncontract=constructioncontractService.get(contractId);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(constructioncontract));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(constructioncontract.getContractId()==null){
			String contractNo = this.createNo();
			constructioncontract.setContractNo(contractNo);
			constructioncontract.setStatus(0);
		}
		constructioncontractService.save(constructioncontract);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	private String createNo(){
		String contractType = "02";
		String year = new SimpleDateFormat("yyyy").format(new Date());
		String search = "LJT"+contractType+year;
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_contractNo_S_LK", search);
		filter.addSorted("contractNo", "desc");
		filter.addFilter("limit", "1");
		List<Constructioncontract> conList = constructioncontractService.getAll(filter);
		String newNo="";
		if(conList!=null&&conList.size()>0){
			Constructioncontract lastCon = conList.get(0);
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
		return search + newNo;
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
		List<Constructioncontract> conList = constructioncontractService.getAll(filter);
		String newNo="";
		if(conList!=null&&conList.size()>0){
			Constructioncontract lastCon = conList.get(0);
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
