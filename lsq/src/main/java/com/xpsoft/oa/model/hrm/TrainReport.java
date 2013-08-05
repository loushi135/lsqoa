package com.xpsoft.oa.model.hrm;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.xpsoft.oa.model.system.FileAttach;

/**
 * TrainReport Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TrainReport extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected TrainPlan trainPlan;
	protected String remark;
	protected String trainReportTarget;
	protected String trainReportAgenda;
	protected String examine;
	protected java.util.Set<FileAttach> trainReportFiles = new java.util.HashSet<FileAttach>();
    

	public java.util.Set<FileAttach> getTrainReportFiles() {
		return trainReportFiles;
	}

	public void setTrainReportFiles(java.util.Set<FileAttach> trainReportFiles) {
		this.trainReportFiles = trainReportFiles;
	}

	/**
	 * Default Empty Constructor for class TrainReport
	 */
	public TrainReport () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TrainReport
	 */
	public TrainReport (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Set the id
	 */	
	public void setId(Long aValue) {
		this.id = aValue;
	}	


	public TrainPlan getTrainPlan() {
		return trainPlan;
	}

	public void setTrainPlan(TrainPlan trainPlan) {
		this.trainPlan = trainPlan;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="remark" type="java.lang.String" length="200" not-null="false" unique="false"
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * Set the remark
	 */	
	public void setRemark(String aValue) {
		this.remark = aValue;
	}	

	public String getTrainReportTarget() {
		return trainReportTarget;
	}

	public void setTrainReportTarget(String trainReportTarget) {
		this.trainReportTarget = trainReportTarget;
	}

	public String getTrainReportAgenda() {
		return trainReportAgenda;
	}

	public void setTrainReportAgenda(String trainReportAgenda) {
		this.trainReportAgenda = trainReportAgenda;
	}

	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TrainReport)) {
			return false;
		}
		TrainReport rhs = (TrainReport) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.trainPlan, rhs.trainPlan)
				.append(this.remark, rhs.remark)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.trainPlan) 
				.append(this.remark) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("planId", this.trainPlan) 
				.append("remark", this.remark) 
				.toString();
	}



}
