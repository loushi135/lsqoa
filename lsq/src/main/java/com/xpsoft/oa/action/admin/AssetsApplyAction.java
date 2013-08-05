package com.xpsoft.oa.action.admin;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.admin.AssetsApply;
import com.xpsoft.oa.service.admin.AssetsApplyService;

import flexjson.JSONSerializer;
/**
 * 
 * @author 
 *
 */
public class AssetsApplyAction extends BaseAction{
	@Resource
	private AssetsApplyService assetsApplyService;
	private AssetsApply assetsApply;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AssetsApply getAssetsApply() {
		return assetsApply;
	}

	public void setAssetsApply(AssetsApply assetsApply) {
		this.assetsApply = assetsApply;
	}
	/**
	 * 显示列表
	 */
	public String list2000(){
		
		QueryFilter filter = new QueryFilter(getRequest());

		// filter.addFilter("Q_bpaStauts_N_EQ", 0+"");

		List<AssetsApply> list = assetsApplyService.getAll2000(filter);

		Type type = new TypeToken<List<AssetsApply>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}
	
	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<AssetsApply> list= assetsApplyService.getAll(filter);
		
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer=JsonUtil.getJSONSerializer("applyDate");
		buff.append(serializer.exclude(new String[]{"class"}).serialize(list));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	
	
	/**
	 * 显示列表
	 */
	public String detailList(){
		
		AssetsApply assetsApply=assetsApplyService.get(id);
		Set assetsApplycontents=assetsApply.getAssetsApplycontents();
		
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(assetsApplycontents.size()).append(",result:");
		
		JSONSerializer serializer=JsonUtil.getJSONSerializer("arrivalDate");
		buff.append(serializer.exclude(new String[]{"class"}).serialize(assetsApplycontents));
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
				assetsApplyService.remove(new Long(id));
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
		AssetsApply assetsApply=assetsApplyService.get(id);
		
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer=JsonUtil.getJSONSerializer("applyDate");
		sb.append(serializer.exclude(new String[]{"class"}).serialize(assetsApply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		assetsApplyService.save(assetsApply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
