 package com.xpsoft.oa.model.admin;
 
 import com.xpsoft.core.model.BaseModel;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class GoodsApply extends BaseModel
 {
   protected Long applyId;
   protected Date applyDate;
   protected String applyNo;
   protected Integer useCounts;
   protected String proposer;
   protected Long userId;
   protected String notes;
   protected Short approvalStatus;
   protected OfficeGoods officeGoods;
   protected String useType;
   protected Long ProId;
   protected Long processRunId;
 
   public GoodsApply()
   {
   }
 
   public GoodsApply(Long in_applyId)
   {
     setApplyId(in_applyId);
   }
 
   public OfficeGoods getOfficeGoods()
   {
     return this.officeGoods;
   }
 
   public void setOfficeGoods(OfficeGoods in_officeGoods) {
     this.officeGoods = in_officeGoods;
   }
 
   
   
   public Long getProcessRunId() {
	return processRunId;
	}
	
	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}
	
	public Long getUserId() {
     return this.userId;
   }
 
   public void setUserId(Long userId) {
     this.userId = userId;
   }
 
   public Long getApplyId()
   {
     return this.applyId;
   }
 
   public void setApplyId(Long aValue)
   {
     this.applyId = aValue;
   }
 
   public Long getGoodsId()
   {
     return getOfficeGoods() == null ? null : getOfficeGoods().getGoodsId();
   }
 
   public void setGoodsId(Long aValue)
   {
     if (aValue == null) {
       this.officeGoods = null;
     } else if (this.officeGoods == null) {
       this.officeGoods = new OfficeGoods(aValue);
       this.officeGoods.setVersion(new Integer(0));
     } else {
       this.officeGoods.setGoodsId(aValue);
     }
   }
 
   public Date getApplyDate()
   {
     return this.applyDate;
   }
 
   public void setApplyDate(Date aValue)
   {
     this.applyDate = aValue;
   }
 
   public String getApplyNo()
   {
     return this.applyNo;
   }
 
   public void setApplyNo(String aValue)
   {
     this.applyNo = aValue;
   }
 
   public Integer getUseCounts()
   {
     return this.useCounts;
   }
 
   public void setUseCounts(Integer aValue)
   {
     this.useCounts = aValue;
   }
 
   public String getProposer()
   {
     return this.proposer;
   }
 
   public void setProposer(String aValue)
   {
     this.proposer = aValue;
   }
 
   public String getNotes()
   {
     return this.notes;
   }
 
   public void setNotes(String aValue)
   {
     this.notes = aValue;
   }
 
   public Short getApprovalStatus()
   {
     return this.approvalStatus;
   }
 
   public void setApprovalStatus(Short aValue)
   {
     this.approvalStatus = aValue;
   }
   
   public String getUseType() {
	return useType;
}

public void setUseType(String useType) {
	this.useType = useType;
}

public Long getProId() {
	return ProId;
}

public void setProId(Long proId) {
	ProId = proId;
}

public boolean equals(Object object)
   {
     if (!(object instanceof GoodsApply)) {
       return false;
     }
     GoodsApply rhs = (GoodsApply)object;
     return new EqualsBuilder()
       .append(this.applyId, rhs.applyId)
       .append(this.applyDate, rhs.applyDate)
       .append(this.applyNo, rhs.applyNo)
       .append(this.useCounts, rhs.useCounts)
       .append(this.userId, rhs.userId)
       .append(this.proposer, rhs.proposer)
       .append(this.notes, rhs.notes)
       .append(this.approvalStatus, rhs.approvalStatus)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.applyId)
       .append(this.applyDate)
       .append(this.applyNo)
       .append(this.useCounts)
       .append(this.proposer)
       .append(this.userId)
       .append(this.notes)
       .append(this.approvalStatus)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("applyId", this.applyId)
       .append("applyDate", this.applyDate)
       .append("applyNo", this.applyNo)
       .append("useCounts", this.useCounts)
       .append("proposer", this.proposer)
       .append("userId", this.userId)
       .append("notes", this.notes)
       .append("approvalStatus", this.approvalStatus)
       .toString();
   }
 }

