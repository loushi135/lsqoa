 package com.xpsoft.oa.action.customer;
 
 import com.xpsoft.core.command.QueryFilter;
 import com.xpsoft.core.util.JsonUtil;
 import com.xpsoft.core.web.action.BaseAction;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.model.customer.Product;
 import com.xpsoft.oa.service.customer.ProductService;
 import flexjson.JSONSerializer;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class ProductAction extends BaseAction
 {
 
   @Resource
   private ProductService productService;
   private Product product;
   private Long productId;
 
   public Long getProductId()
   {
     return this.productId;
   }
 
   public void setProductId(Long productId) {
     this.productId = productId;
   }
 
   public Product getProduct() {
     return this.product;
   }
 
   public void setProduct(Product product) {
     this.product = product;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.productService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "createtime", "updatetime" });
     buff.append(json.exclude(new String[] { "class" }).serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.productService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     Product product = (Product)this.productService.get(this.productId);
 
     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "createtime", "updatetime" });
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(json.exclude(new String[] { "class" }).serialize(product));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.product.setUpdatetime(new Date());
     this.productService.save(this.product);
     setJsonString("{success:true}");
     return "success";
   }
 }

