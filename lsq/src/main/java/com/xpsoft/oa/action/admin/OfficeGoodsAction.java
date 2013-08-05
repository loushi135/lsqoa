 package com.xpsoft.oa.action.admin;
 
 import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.admin.OfficeGoods;
import com.xpsoft.oa.service.admin.OfficeGoodsService;

import flexjson.JSONSerializer;
 
 public class OfficeGoodsAction extends BaseAction
 {
		@Resource
		private OfficeGoodsService officeGoodsService;
		private OfficeGoods officeGoods;
		
		private Long goodsId;

		public Long getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(Long goodsId) {
			this.goodsId = goodsId;
		}

		public OfficeGoods getOfficeGoods() {
			return officeGoods;
		}

		public void setOfficeGoods(OfficeGoods officeGoods) {
			this.officeGoods = officeGoods;
		}

		/**
		 * 显示列表
		 */
		public String list(){
			
			QueryFilter filter=new QueryFilter(getRequest());
			List<OfficeGoods> list= officeGoodsService.getAll(filter);
			StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
			.append(filter.getPagingBean().getTotalItems()).append(",result:");
			JSONSerializer serializer=JsonUtil.getJSONSerializer(new String[0]);
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
					officeGoodsService.remove(new Long(id));
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
			OfficeGoods officeGoods=officeGoodsService.get(goodsId);
			StringBuffer sb = new StringBuffer("{success:true,data:");
			JSONSerializer serializer=new JSONSerializer();
			sb.append(serializer.exclude(new String[]{"class"}).serialize(officeGoods));
			sb.append("}");
			setJsonString(sb.toString());
			return SUCCESS;
		}
		/**
		 * 添加及保存操作
		 */
		public String save(){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss-SSSS");//自动生成商品号
		    if(officeGoods.getGoodsId()==null){
		    	officeGoods.setGoodsNo(sdf.format(new Date()));
		    	officeGoods.setStockCounts(0);
		    }
			officeGoodsService.save(officeGoods);
			setJsonString("{success:true}");
			return SUCCESS;
		}
}

