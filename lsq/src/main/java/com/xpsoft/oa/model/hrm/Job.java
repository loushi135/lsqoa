 package com.xpsoft.oa.model.hrm;
 
 import com.google.gson.annotations.Expose;
import com.xpsoft.core.model.BaseModel;
 import com.xpsoft.oa.model.system.Department;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Job extends BaseModel
 {
   public static short DELFLAG_NOT = 0;
   public static short DELFLAG_HAD = 1;
   @Expose
   protected Long jobId;
   @Expose
   protected String jobName;
   @Expose
   protected String memo;
   @Expose
   protected Short delFlag;
   @Expose
   protected Department department;
   protected Set empProfiles = new HashSet();
 
   public Job()
   {
   }
 
   public Job(Long in_jobId)
   {
     setJobId(in_jobId);
   }
 
   public Department getDepartment()
   {
     return this.department;
   }
 
   public void setDepartment(Department in_department) {
     this.department = in_department;
   }
 
   public Set getEmpProfiles() {
     return this.empProfiles;
   }
 
   public void setEmpProfiles(Set in_empProfiles) {
     this.empProfiles = in_empProfiles;
   }
 
   public Long getJobId()
   {
     return this.jobId;
   }
 
   public void setJobId(Long aValue)
   {
     this.jobId = aValue;
   }
 
   public String getJobName()
   {
     return this.jobName;
   }
 
   public void setJobName(String aValue)
   {
     this.jobName = aValue;
   }
 
   public Long getDepId()
   {
     return getDepartment() == null ? null : getDepartment().getDepId();
   }
 
   public void setDepId(Long aValue)
   {
     if (aValue == null) {
       this.department = null;
     } else if (this.department == null) {
       this.department = new Department(aValue);
       this.department.setVersion(new Integer(0));
     } else {
       this.department.setDepId(aValue);
     }
   }
 
   public String getMemo()
   {
     return this.memo;
   }
 
   public void setMemo(String aValue)
   {
     this.memo = aValue;
   }
 
   public Short getDelFlag() {
     return this.delFlag;
   }
 
   public void setDelFlag(Short delFlag) {
     this.delFlag = delFlag;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof Job)) {
       return false;
     }
     Job rhs = (Job)object;
     return new EqualsBuilder()
       .append(this.jobId, rhs.jobId)
       .append(this.jobName, rhs.jobName)
       .append(this.memo, rhs.memo)
       .append(this.delFlag, rhs.delFlag)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.jobId)
       .append(this.jobName)
       .append(this.memo)
       .append(this.delFlag)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("jobId", this.jobId)
       .append("jobName", this.jobName)
       .append("memo", this.memo)
       .append("delFlag", this.delFlag)
       .toString();
   }
 }

