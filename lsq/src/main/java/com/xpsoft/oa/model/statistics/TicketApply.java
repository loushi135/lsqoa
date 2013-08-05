package com.xpsoft.oa.model.statistics;


import java.math.BigDecimal;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.Department;

/**
 * TicketApply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TicketApply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected String reporter;
	@Expose
	protected Department dept;
	@Expose
	protected String bookUsers;
	@Expose
	protected String bookUserIds;
	@Expose
	protected Integer ticketNum;
	@Expose
	protected String businessType;
	@Expose
	protected ProjectNew project;
	@Expose
	protected String departure;
	@Expose
	protected String destination;
	@Expose
	protected String ticketType;
	@Expose
	protected java.util.Date departureTime;
	@Expose
	protected String departureDetail;
	@Expose
	protected java.util.Date backTime;
	@Expose
	protected String backDetail;
	@Expose
	protected String backOrNot;
	@Expose
	protected String applyReason;
	@Expose
	protected String company;
	@Expose
	protected Long processRunId;
	@Expose
	protected BigDecimal amount;
	@Expose
	protected String amountBig;
	@Expose
	protected Integer status;//是否退签 0正常 1退签
	public final static Integer STATUS_NORMAL = 0;
	public final static Integer STATUS_RETRUN = 1;
	/**
	 * Default Empty Constructor for class TicketApply
	 */
	public TicketApply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TicketApply
	 */
	public TicketApply (
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

	/**
	 * 报告人	 * @return String
	 * @hibernate.property column="reporter" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getReporter() {
		return this.reporter;
	}
	
	public String getAmountBig() {
		return amountBig;
	}

	public void setAmountBig(String amountBig) {
		this.amountBig = amountBig;
	}

	/**
	 * Set the reporter
	 */	
	public void setReporter(String aValue) {
		this.reporter = aValue;
	}	

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 部门主键	 * @return Long
	 * @hibernate.property column="deptId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */

	public String getDepartureDetail() {
		return departureDetail;
	}

	public void setDepartureDetail(String departureDetail) {
		this.departureDetail = departureDetail;
	}

	public String getBackDetail() {
		return backDetail;
	}

	public void setBackDetail(String backDetail) {
		this.backDetail = backDetail;
	}

	/**
	 * 出行人员	 * @return String
	 * @hibernate.property column="bookUsers" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getBookUsers() {
		return this.bookUsers;
	}
	
	

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}

	/**
	 * Set the bookUsers
	 */	
	public void setBookUsers(String aValue) {
		this.bookUsers = aValue;
	}	

	/**
	 * 出行人员主键	 * @return String
	 * @hibernate.property column="bookUserIds" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getBookUserIds() {
		return this.bookUserIds;
	}
	
	/**
	 * Set the bookUserIds
	 */	
	public void setBookUserIds(String aValue) {
		this.bookUserIds = aValue;
	}	

	/**
	 * 需要申请票数	 * @return Integer
	 * @hibernate.property column="ticketNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getTicketNum() {
		return this.ticketNum;
	}
	
	/**
	 * Set the ticketNum
	 */	
	public void setTicketNum(Integer aValue) {
		this.ticketNum = aValue;
	}	

	/**
	 * 出差类型	 * @return String
	 * @hibernate.property column="businessType" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getBusinessType() {
		return this.businessType;
	}
	
	/**
	 * Set the businessType
	 */	
	public void setBusinessType(String aValue) {
		this.businessType = aValue;
	}	


	/**
	 * 出发地	 * @return String
	 * @hibernate.property column="departure" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDeparture() {
		return this.departure;
	}
	
	/**
	 * Set the departure
	 */	
	public void setDeparture(String aValue) {
		this.departure = aValue;
	}	

	/**
	 * 目的地	 * @return String
	 * @hibernate.property column="destination" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDestination() {
		return this.destination;
	}
	
	/**
	 * Set the destination
	 */	
	public void setDestination(String aValue) {
		this.destination = aValue;
	}	

	/**
	 * 票务类型	 * @return String
	 * @hibernate.property column="ticketType" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTicketType() {
		return this.ticketType;
	}
	
	/**
	 * Set the ticketType
	 */	
	public void setTicketType(String aValue) {
		this.ticketType = aValue;
	}	

	/**
	 * 出发时间	 * @return java.util.Date
	 * @hibernate.property column="departureTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getDepartureTime() {
		return this.departureTime;
	}
	
	/**
	 * Set the departureTime
	 */	
	public void setDepartureTime(java.util.Date aValue) {
		this.departureTime = aValue;
	}	

	/**
	 * 返程时间	 * @return java.util.Date
	 * @hibernate.property column="backTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getBackTime() {
		return this.backTime;
	}
	
	/**
	 * Set the backTime
	 */	
	public void setBackTime(java.util.Date aValue) {
		this.backTime = aValue;
	}	

	/**
	 * 是否需要预订返程票	 * @return String
	 * @hibernate.property column="backOrNot" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getBackOrNot() {
		return this.backOrNot;
	}
	
	/**
	 * Set the backOrNot
	 */	
	public void setBackOrNot(String aValue) {
		this.backOrNot = aValue;
	}	

	/**
	 * 申请原因	 * @return String
	 * @hibernate.property column="applyReason" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getApplyReason() {
		return this.applyReason;
	}
	
	/**
	 * Set the applyReason
	 */	
	public void setApplyReason(String aValue) {
		this.applyReason = aValue;
	}	
   
	public boolean equals(Object object) {
		if (!(object instanceof TicketApply)) {
			return false;
		}
		TicketApply rhs = (TicketApply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.reporter, rhs.reporter)
				.append(this.bookUsers, rhs.bookUsers)
				.append(this.bookUserIds, rhs.bookUserIds)
				.append(this.ticketNum, rhs.ticketNum)
				.append(this.businessType, rhs.businessType)
				.append(this.departure, rhs.departure)
				.append(this.destination, rhs.destination)
				.append(this.ticketType, rhs.ticketType)
				.append(this.departureTime, rhs.departureTime)
				.append(this.backTime, rhs.backTime)
				.append(this.backOrNot, rhs.backOrNot)
				.append(this.applyReason, rhs.applyReason)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.reporter) 
				.append(this.bookUsers) 
				.append(this.bookUserIds) 
				.append(this.ticketNum) 
				.append(this.businessType) 
				.append(this.departure) 
				.append(this.destination) 
				.append(this.ticketType) 
				.append(this.departureTime) 
				.append(this.backTime) 
				.append(this.backOrNot) 
				.append(this.applyReason) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("reporter", this.reporter) 
				.append("bookUsers", this.bookUsers) 
				.append("bookUserIds", this.bookUserIds) 
				.append("ticketNum", this.ticketNum) 
				.append("businessType", this.businessType) 
				.append("departure", this.departure) 
				.append("destination", this.destination) 
				.append("ticketType", this.ticketType) 
				.append("departureTime", this.departureTime) 
				.append("backTime", this.backTime) 
				.append("backOrNot", this.backOrNot) 
				.append("applyReason", this.applyReason) 
				.toString();
	}



}
