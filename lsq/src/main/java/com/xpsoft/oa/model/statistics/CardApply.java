package com.xpsoft.oa.model.statistics;


import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.xpsoft.oa.model.system.Department;

/**
 * CardApply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class CardApply extends com.xpsoft.core.model.BaseModel {

    protected Long cardId;
	protected String cardProposer;
	protected String phone;
	protected String companyName;
	protected String fax;
	protected Department dept;
	protected String mobile;
	protected String position;
	protected Integer postalCode;
	protected String email;
	protected String other;
	protected Integer amount;
	protected String type;
	protected Integer isType;
	protected Long processRunId;
	protected java.util.Set CardFiles = new java.util.HashSet();
	protected Date timeCreate;
	
	/**
	 * Default Empty Constructor for class CardApply
	 */
	public CardApply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class CardApply
	 */
	public CardApply (
		 Long in_cardId
        ) {
		this.setCardId(in_cardId);
    }

    

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * 	 * @return Long
     * @hibernate.id column="cardId" type="java.lang.Long" generator-class="native"
	 */
	public Long getCardId() {
		return this.cardId;
	}
	
	/**
	 * Set the cardId
	 */	
	public void setCardId(Long aValue) {
		this.cardId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="cardProposer" type="java.lang.String" length="120" not-null="true" unique="false"
	 */
	public String getCardProposer() {
		return this.cardProposer;
	}
	
	/**
	 * Set the cardProposer
	 * @spring.validator type="required"
	 */	
	public void setCardProposer(String aValue) {
		this.cardProposer = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="phone" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * Set the phone
	 */	
	public void setPhone(String aValue) {
		this.phone = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="companyName" type="java.lang.String" length="120" not-null="false" unique="false"
	 */
	public String getCompanyName() {
		return this.companyName;
	}
	
	/**
	 * Set the companyName
	 */	
	public void setCompanyName(String aValue) {
		this.companyName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="fax" type="java.lang.String" length="80" not-null="true" unique="false"
	 */
	public String getFax() {
		return this.fax;
	}
	
	/**
	 * Set the fax
	 * @spring.validator type="required"
	 */	
	public void setFax(String aValue) {
		this.fax = aValue;
	}	

	public String getMobile() {
		return this.mobile;
	}
	
	/**
	 * Set the mobile
	 */	
	public void setMobile(String aValue) {
		this.mobile = aValue;
	}	

	/**
	 * 部门	 * @return String
	 * @hibernate.property column="position" type="java.lang.String" length="120" not-null="false" unique="false"
	 */
	public String getPosition() {
		return this.position;
	}
	
	/**
	 * Set the position
	 */	
	public void setPosition(String aValue) {
		this.position = aValue;
	}	

	/**
	 * 邮编	 * @return Integer
	 * @hibernate.property column="postalCode" type="java.lang.Integer" length="10" not-null="true" unique="false"
	 */
	public Integer getPostalCode() {
		return this.postalCode;
	}
	
	/**
	 * Set the postalCode
	 * @spring.validator type="required"
	 */	
	public void setPostalCode(Integer aValue) {
		this.postalCode = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="Email" type="java.lang.String" length="120" not-null="false" unique="false"
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Set the email
	 */	
	public void setEmail(String aValue) {
		this.email = aValue;
	}	

	/**
	 * 其他	 * @return String
	 * @hibernate.property column="other" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getOther() {
		return this.other;
	}
	
	/**
	 * Set the other
	 */	
	public void setOther(String aValue) {
		this.other = aValue;
	}	

	/**
	 * 印制盒数	 * @return Integer
	 * @hibernate.property column="amount" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getAmount() {
		return this.amount;
	}
	
	/**
	 * Set the amount
	 */	
	public void setAmount(Integer aValue) {
		this.amount = aValue;
	}	

	/**
	 * 印制类型	 * @return String
	 * @hibernate.property column="type" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Set the type
	 */	
	public void setType(String aValue) {
		this.type = aValue;
	}	

	/**
	 * 是否高印	 * @return Integer
	 * @hibernate.property column="isType" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getIsType() {
		return this.isType;
	}
	
	/**
	 * Set the isType
	 */	
	public void setIsType(Integer aValue) {
		this.isType = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CardApply)) {
			return false;
		}
		CardApply rhs = (CardApply) object;
		return new EqualsBuilder()
				.append(this.cardId, rhs.cardId)
				.append(this.cardProposer, rhs.cardProposer)
				.append(this.phone, rhs.phone)
				.append(this.companyName, rhs.companyName)
				.append(this.fax, rhs.fax)
				.append(this.mobile, rhs.mobile)
				.append(this.position, rhs.position)
				.append(this.postalCode, rhs.postalCode)
				.append(this.email, rhs.email)
				.append(this.other, rhs.other)
				.append(this.amount, rhs.amount)
				.append(this.type, rhs.type)
				.append(this.isType, rhs.isType)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.cardId) 
				.append(this.cardProposer) 
				.append(this.phone) 
				.append(this.companyName) 
				.append(this.fax) 
				.append(this.mobile) 
				.append(this.position) 
				.append(this.postalCode) 
				.append(this.email) 
				.append(this.other) 
				.append(this.amount) 
				.append(this.type) 
				.append(this.isType) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("cardId", this.cardId) 
				.append("cardProposer", this.cardProposer) 
				.append("phone", this.phone) 
				.append("companyName", this.companyName) 
				.append("fax", this.fax) 
				.append("mobile", this.mobile) 
				.append("position", this.position) 
				.append("postalCode", this.postalCode) 
				.append("email", this.email) 
				.append("other", this.other) 
				.append("amount", this.amount) 
				.append("type", this.type) 
				.append("isType", this.isType) 
				.toString();
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public java.util.Set getCardFiles() {
		return CardFiles;
	}

	public void setCardFiles(java.util.Set cardFiles) {
		CardFiles = cardFiles;
	}

	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

}
