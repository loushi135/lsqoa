package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * TicketIdno Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TicketIdno extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected Long userId;
	@Expose
	protected String userName;
	@Expose
	protected String idCard;
	@Expose
	protected TicketApply ticketApply;


	/**
	 * Default Empty Constructor for class TicketIdno
	 */
	public TicketIdno () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TicketIdno
	 */
	public TicketIdno (
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
	 * 	 * @return Long
	 * @hibernate.property column="userId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getUserId() {
		return this.userId;
	}
	
	/**
	 * Set the userId
	 * @spring.validator type="required"
	 */	
	public void setUserId(Long aValue) {
		this.userId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="userName" type="java.lang.String" length="200" not-null="false" unique="false"
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * Set the userName
	 */	
	public void setUserName(String aValue) {
		this.userName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="idCard" type="java.lang.String" length="200" not-null="false" unique="false"
	 */
	public String getIdCard() {
		return this.idCard;
	}
	
	/**
	 * Set the idCard
	 */	
	public void setIdCard(String aValue) {
		this.idCard = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="ticketId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TicketIdno)) {
			return false;
		}
		TicketIdno rhs = (TicketIdno) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.userId, rhs.userId)
				.append(this.userName, rhs.userName)
				.append(this.idCard, rhs.idCard)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.userId) 
				.append(this.userName) 
				.append(this.idCard) 
				.toHashCode();
	}

	public TicketApply getTicketApply() {
		return ticketApply;
	}

	public void setTicketApply(TicketApply ticketApply) {
		this.ticketApply = ticketApply;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("userId", this.userId) 
				.append("userName", this.userName) 
				.append("idCard", this.idCard) 
				.toString();
	}



}
