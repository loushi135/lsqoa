package com.xpsoft.oa.model.system;


import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * Smsmessage Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Smsmessage extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long smsId;
    @Expose
    protected String inceptId;
    @Expose
	protected String name;
	public String getInceptId() {
		return inceptId;
	}

	public void setInceptId(String inceptId) {
		this.inceptId = inceptId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	@Expose
	protected String phoneNo;
	@Expose
	protected String massage;
	@Expose
	protected String sender;
	@Expose
	protected Date sendTime;
	

	/**
	 * Default Empty Constructor for class Smsmessage
	 */
	public Smsmessage () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Smsmessage
	 */
	public Smsmessage (
		 Long in_smsId
        ) {
		this.setSmsId(in_smsId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="smsId" type="java.lang.Long" generator-class="native"
	 */
	public Long getSmsId() {
		return this.smsId;
	}
	
	/**
	 * Set the smsId
	 */	
	public void setSmsId(Long aValue) {
		this.smsId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="name" type="java.lang.String" length="40" not-null="true" unique="false"
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the name
	 * @spring.validator type="required"
	 */	
	public void setName(String aValue) {
		this.name = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="phoneNo" type="java.lang.String" length="40" not-null="true" unique="false"
	 */
	public String getPhoneNo() {
		return this.phoneNo;
	}
	
	/**
	 * Set the phoneNo
	 * @spring.validator type="required"
	 */	
	public void setPhoneNo(String aValue) {
		this.phoneNo = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="massage" type="java.lang.String" length="200" not-null="true" unique="false"
	 */
	public String getMassage() {
		return this.massage;
	}
	
	/**
	 * Set the massage
	 * @spring.validator type="required"
	 */	
	public void setMassage(String aValue) {
		this.massage = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Smsmessage)) {
			return false;
		}
		Smsmessage rhs = (Smsmessage) object;
		return new EqualsBuilder()
				.append(this.smsId, rhs.smsId)
				.append(this.name, rhs.name)
				.append(this.phoneNo, rhs.phoneNo)
				.append(this.massage, rhs.massage)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.smsId) 
				.append(this.name) 
				.append(this.phoneNo) 
				.append(this.massage) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("smsId", this.smsId) 
				.append("name", this.name) 
				.append("phoneNo", this.phoneNo) 
				.append("massage", this.massage) 
				.toString();
	}



}
