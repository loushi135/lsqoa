 package com.xpsoft.oa.action.admin;
 
 import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.admin.GoodApplyTotal;
import com.xpsoft.oa.model.admin.GoodsApply;
import com.xpsoft.oa.model.admin.OfficeGoods;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.admin.GoodsApplyService;
import com.xpsoft.oa.service.admin.OfficeGoodsService;
import com.xpsoft.oa.service.info.ShortMessageService;

import flexjson.JSONSerializer;
 
 public class GoodsApplyAction extends BaseAction
 {
   private static short PASS_APPLY = 1;
   private static short NOTPASS_APPLY = 0;
 
   @Resource
   private GoodsApplyService goodsApplyService;
   private GoodsApply goodsApply;
 
   @Resource
   private ShortMessageService shortMessageService;
 
   @Resource
   private OfficeGoodsService officeGoodsService;
   private Long applyId;
 
   public Long getApplyId() { return this.applyId; }
 
   public void setApplyId(Long applyId)
   {
     this.applyId = applyId;
   }
 
   public GoodsApply getGoodsApply() {
     return this.goodsApply;
   }
 
   public void setGoodsApply(GoodsApply goodsApply) {
     this.goodsApply = goodsApply;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.goodsApplyService.getAll(filter);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "applyDate" });
     buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.goodsApplyService.remove(new Long(id));
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     GoodsApply goodsApply = (GoodsApply)this.goodsApplyService.get(this.applyId);
     StringBuffer sb = new StringBuffer("{success:true,data:");
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "applyDate" });
     sb.append(serializer.exclude(new String[] { "class" }).serialize(goodsApply));
     sb.append("}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String save()
   {
     OfficeGoods officeGoods = (OfficeGoods)this.officeGoodsService.get(this.goodsApply.getOfficeGoods().getGoodsId());
     Integer con = this.goodsApply.getUseCounts();
     Integer least = Integer.valueOf(officeGoods.getStockCounts().intValue() - con.intValue());
     if (least.intValue() > 0) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-SSSS");
       this.goodsApply.setApplyNo("GA" + sdf.format(new Date()));
       if (this.goodsApply.getApplyId() == null) {
         this.goodsApply.setApprovalStatus(Short.valueOf(NOTPASS_APPLY));
       }
       this.goodsApplyService.save(this.goodsApply);
       if (this.goodsApply.getApprovalStatus().shortValue() == PASS_APPLY) {
         Long receiveId = this.goodsApply.getUserId();
         String content = "你申请的办公用品为" + officeGoods.getGoodsName() + "已经通过审批，请查收";
         this.shortMessageService.save(AppUser.SYSTEM_USER, receiveId.toString(), content, ShortMessage.MSG_TYPE_SYS);
         officeGoods.setStockCounts(least);
         this.officeGoodsService.save(officeGoods);
       }
       setJsonString("{success:true}");
     } else {
       setJsonString("{success:false,message:'库存不足!'}");
     }
     return "success";
   }
   
   /**
	 * 显示列表
	 */
	public String listForReport(){
		
		String month = getRequest().getParameter("month");
		if(StringUtils.isBlank(month)){
			month = DateUtil.format(new Date(), "yyyy-MM");
		}
		List<GoodApplyTotal> gaTotal= goodsApplyService.getGoodApplyTotals(month);
		Type type=new TypeToken<List<GoodApplyTotal>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(gaTotal.size()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(gaTotal, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	
	public String genCm(){
		String month = getRequest().getParameter("month");
		if(StringUtils.isBlank(month)){
			month = DateUtil.format(new Date(), "yyyy-MM");
		}
		List<String> deptList = goodsApplyService.getDeptList(month);
		Type type=new TypeToken<List<String>>(){}.getType();
		StringBuffer sb = new StringBuffer("{success:true,data:");
		Gson gson=new Gson();
		sb.append(gson.toJson(deptList, type));
		sb.append("}");
		jsonString=sb.toString();
		return SUCCESS;
	}
	
	public String export(){
		String month = getRequest().getParameter("month");
		if(StringUtils.isBlank(month)){
			month = DateUtil.format(new Date(), "yyyy-MM");
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<GoodApplyTotal> gaTotal= goodsApplyService.getGoodApplyTotals(month);
		List<String> deptList = goodsApplyService.getDeptList(month);
		dataMap.put("gaTotal", gaTotal);
		dataMap.put("deptList", deptList);
		try {
			goodsApplyService.export(dataMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
 }

