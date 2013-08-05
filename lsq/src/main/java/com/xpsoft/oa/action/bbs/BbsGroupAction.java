package com.xpsoft.oa.action.bbs;


import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.bbs.BbsGroup;
import com.xpsoft.oa.service.bbs.BbsGroupService;
/**
 * 
 * @author 
 *
 */
public class BbsGroupAction extends BaseAction{
	@Resource
	private BbsGroupService bbsGroupService;
	private BbsGroup bbsGroup;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BbsGroup getBbsGroup() {
		return bbsGroup;
	}

	public void setBbsGroup(BbsGroup bbsGroup) {
		this.bbsGroup = bbsGroup;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BbsGroup> list= bbsGroupService.getAll(filter);
		
		Type type=new TypeToken<List<BbsGroup>>(){}.getType();
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
				bbsGroupService.remove(new Long(id));
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
		BbsGroup bbsGroup=bbsGroupService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bbsGroup));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		bbsGroupService.save(bbsGroup);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	public String treeMenu(){

		String opt = getRequest().getParameter("opt");
		StringBuffer buff = new StringBuffer();
		if (StringUtils.isNotEmpty(opt))
			buff.append("[");
		else {
			buff.append("[{id:'0',text:'" + "论坛主题分类"
					+ "',expanded:true,children:[");
		}
		List<BbsGroup> listParent = this.bbsGroupService.
				findByParentId(new Long(0L));
		for (BbsGroup group : listParent) {
			buff.append("{id:'" + group.getId() + "',text:'"
					+ group.getGroupName() + "',");
			buff.append(findChild(group.getId()));
		}
		if (!listParent.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		if (StringUtils.isNotEmpty(opt))
			buff.append("]");
		else {
			buff.append("]}]");
		}
		setJsonString(buff.toString());
		
		return SUCCESS;
		
	}
	public String findChild(Long id) {
		StringBuffer buff1 = new StringBuffer("");
		List<BbsGroup> list = this.bbsGroupService
				.findByParentId(id);
		if (list.size() == 0) {
			buff1.append("leaf:true},");
			return buff1.toString();
		}
		buff1.append("children:[");
		for (BbsGroup group: list) {
			buff1.append("{id:'" + group.getId() + "',text:'"
					+ group.getGroupName() + "',");
			buff1.append(findChild(group.getId()));
		}
		buff1.deleteCharAt(buff1.length() - 1);
		buff1.append("]},");
		return buff1.toString();
	}
	public String singleDel(){
		if(id!=null){
			BbsGroup bbsGroup =bbsGroupService.get(id);
			bbsGroupService.remove(bbsGroup);
		}
		jsonString="{success:true}";
		return SUCCESS;
	}
}
