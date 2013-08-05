package com.xpsoft.oa.action.system;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.system.TreeType;
import com.xpsoft.oa.service.system.TreeTypeService;
/**
 * 
 * @author 
 *
 */
public class TreeTypeAction extends BaseAction{
	@Resource
	private TreeTypeService treeTypeService;
	private TreeType treeType;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TreeType getTreeType() {
		return treeType;
	}

	public void setTreeType(TreeType treeType) {
		this.treeType = treeType;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<TreeType> list= treeTypeService.getAll(filter);
		
		Type type=new TypeToken<List<TreeType>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	
	public String getTreeOf(){
		String opt=getRequest().getParameter("opt");
		String className = getRequest().getParameter("className");
		StringBuffer buff = new StringBuffer();
		if(StringUtils.isNotEmpty(opt)){
			buff.append("[");
		}else{
			buff.append("[{typeId:'-1',text:'"+AppUtil.getCompanyName()+"',expanded:true,children:[");
		}
		List<TreeType> firstLevelList = treeTypeService.findByParentIdAndClassName(-1L,className);
		for(TreeType treeType:firstLevelList){
			buff.append("{typeId:'"+treeType.getId()+"',text:'"+treeType.getTypeName()+"',");
		    buff.append(findChild(treeType.getId()));
		}
		if (!firstLevelList.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
	    }
		if(StringUtils.isNotEmpty(opt)){
		   buff.append("]");
		}else{
			buff.append("]}]");
		}
		setJsonString(buff.toString());
		return SUCCESS;
	}
	
	/**
	 * 寻找根节点
	 * @param typeId
	 * @return
	 */
	public String findChild(Long typeId){
		StringBuffer buff1=new StringBuffer("");
		List<TreeType> list=treeTypeService.findByParentId(typeId);
		if(list.size()==0){
			buff1.append("leaf:true},");
			return buff1.toString(); 
		}else {
			buff1.append("children:[");
			for(TreeType treeType:list){
				buff1.append("{typeId:'"+treeType.getId()+"',text:'"+treeType.getTypeName()+"',");
				buff1.append(findChild(treeType.getId()));
			}
			buff1.deleteCharAt(buff1.length() - 1);
			buff1.append("]},");
			return buff1.toString();
		}
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				treeTypeService.remove(new Long(id));
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
		TreeType treeType=treeTypeService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(treeType));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		treeTypeService.save(treeType);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
