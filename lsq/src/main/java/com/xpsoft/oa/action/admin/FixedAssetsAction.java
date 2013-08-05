package com.xpsoft.oa.action.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.admin.DepreRecord;
import com.xpsoft.oa.model.admin.DepreType;
import com.xpsoft.oa.model.admin.FixedAssets;
import com.xpsoft.oa.service.admin.DepreRecordService;
import com.xpsoft.oa.service.admin.DepreTypeService;
import com.xpsoft.oa.service.admin.FixedAssetsService;

import flexjson.DateTransformer;
import flexjson.JSONSerializer;
/**
 * 
 * @author csx
 *
 */
public class FixedAssetsAction extends BaseAction{
	@Resource
	private FixedAssetsService fixedAssetsService;
	private FixedAssets fixedAssets;
	

	
	private Long assetsId;

	public Long getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}

	public FixedAssets getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(FixedAssets fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<FixedAssets> list= fixedAssetsService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer=JsonUtil.getJSONSerializer("buyDate");
		buff.append(serializer.exclude(new String[]{"class"}).serialize(list));
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
				fixedAssetsService.remove(new Long(id));
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
		FixedAssets fixedAssets=fixedAssetsService.get(assetsId);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer= JsonUtil.getJSONSerializer("manuDate","buyDate","startDepre");
		sb.append(serializer.exclude(new String[]{"class"}).serialize(fixedAssets));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss-SSSS");//自动生成商品号
	    if(fixedAssets.getAssetsNo()==null&&!"".equals(fixedAssets.getAssetsNo())){
	    	fixedAssets.setAssetsNo(sdf.format(new Date()));
	    }
//	    Long typeId=fixedAssets.getDepreType().getDepreTypeId();
//	    if(typeId!=null){
//	    	DepreType depreType =depreTypeService.get(typeId);
//		    if(depreType.getCalMethod()!=2){//不为工作量折算时
//			    BigDecimal remainRate=fixedAssets.getRemainValRate();
//			    BigDecimal depreRate=new BigDecimal("1").subtract(remainRate.divide(new BigDecimal("100"))).divide(fixedAssets.getIntendTerm(),8,BigDecimal.ROUND_HALF_UP);  //折旧率
//			    fixedAssets.setDepreRate(depreRate);
//		    }
//	    }
	    fixedAssetsService.save(fixedAssets);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	

}
