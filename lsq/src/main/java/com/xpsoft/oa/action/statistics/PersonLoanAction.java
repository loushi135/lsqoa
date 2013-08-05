package com.xpsoft.oa.action.statistics;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.PersonLoan;
import com.xpsoft.oa.service.statistics.PersonLoanService;
/**
 * 
 * @author 
 *
 */
public class PersonLoanAction extends BaseAction{
	@Resource
	private PersonLoanService personLoanService;
	private PersonLoan personLoan;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonLoan getPersonLoan() {
		return personLoan;
	}

	public void setPersonLoan(PersonLoan personLoan) {
		this.personLoan = personLoan;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<PersonLoan> list= personLoanService.getAll(filter);
		
		Type type=new TypeToken<List<PersonLoan>>(){}.getType();
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
				personLoanService.remove(new Long(id));
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
		PersonLoan personLoan=personLoanService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(personLoan));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		personLoanService.save(personLoan);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
