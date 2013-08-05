 package com.xpsoft.oa.action.admin;
 
 import com.xpsoft.core.command.QueryFilter;
 import com.xpsoft.core.util.JsonUtil;
 import com.xpsoft.core.web.action.BaseAction;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.model.admin.Car;
 import com.xpsoft.oa.model.admin.CarApply;
 import com.xpsoft.oa.model.info.ShortMessage;
 import com.xpsoft.oa.model.system.AppUser;
 import com.xpsoft.oa.service.admin.CarApplyService;
 import com.xpsoft.oa.service.admin.CarService;
 import com.xpsoft.oa.service.info.ShortMessageService;
 import flexjson.JSONSerializer;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class CarApplyAction extends BaseAction
 {
 
   @Resource
   private CarApplyService carApplyService;
   private CarApply carApply;
 
   @Resource
   private ShortMessageService shortMessageService;
 
   @Resource
   private CarService carService;
   private Long applyId;
 
   public Long getApplyId()
   {
     return this.applyId;
   }
 
   public void setApplyId(Long applyId) {
     this.applyId = applyId;
   }
 
   public CarApply getCarApply() {
     return this.carApply;
   }
 
   public void setCarApply(CarApply carApply) {
     this.carApply = carApply;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.carApplyService.getAll(filter);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "applyDate", "startTime", "endTime" });
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
         this.carApplyService.remove(new Long(id));
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     CarApply carApply = (CarApply)this.carApplyService.get(this.applyId);
     StringBuffer sb = new StringBuffer("{success:true,data:");
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "applyDate", "startTime", "endTime" });
     sb.append(serializer.exclude(new String[] { "class", "car.carApplys" }).serialize(carApply));
     sb.append("}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String save()
   {
     this.carApplyService.save(this.carApply);
     if (this.carApply.getApprovalStatus().shortValue() == Car.PASS_APPLY) {
       Long receiveId = this.carApply.getUserId();
       Car car = (Car)this.carService.get(this.carApply.getCar().getCarId());
       String content = "你申请的车牌号为" + car.getCarNo() + "已经通过审批，请注意查收";
       this.shortMessageService.save(AppUser.SYSTEM_USER, receiveId.toString(), content, ShortMessage.MSG_TYPE_SYS);
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

