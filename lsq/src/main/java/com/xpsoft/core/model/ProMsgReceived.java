package com.xpsoft.core.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * ProMsgReceived Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProMsgReceived extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected String mobile;
	protected String result;
	protected String resNote;
	protected java.util.Date receivetime;
	protected String strId;
	protected int finished;


	/**
	 * Default Empty Constructor for class ProMsgReceived
	 */
	public ProMsgReceived () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProMsgReceived
	 */
	public ProMsgReceived (
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
	 * 	 * @return java.util.Date
	 * @hibernate.property column="receivetime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getReceivetime() {
		return this.receivetime;
	}
	
	/**
	 * Set the receivetime
	 */	
	public void setReceivetime(java.util.Date aValue) {
		this.receivetime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="str_id" type="java.lang.String" length="100" not-null="false" unique="false"
	 */
	public String getStrId() {
		return this.strId;
	}
	
	/**
	 * Set the strId
	 */	
	public void setStrId(String aValue) {
		this.strId = aValue;
	}	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResNote() {
		return resNote;
	}

	public void setResNote(String resNote) {
		this.resNote = resNote;
	}

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProMsgReceived)) {
			return false;
		}
		ProMsgReceived rhs = (ProMsgReceived) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.mobile, rhs.mobile)
				.append(this.result, rhs.result)
				.append(this.resNote, rhs.resNote)
				.append(this.receivetime, rhs.receivetime)
				.append(this.strId, rhs.strId)
				.append(this.finished, rhs.finished)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.mobile) 
				.append(this.result) 
				.append(this.resNote) 
				.append(this.receivetime) 
				.append(this.strId) 
				.append(this.finished) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("promsgdetailId", this.mobile) 
				.append("result", this.result) 
				.append("resNote", this.resNote) 
				.append("receivetime", this.receivetime) 
				.append("strId", this.strId) 
				.append("finished", this.finished) 
				.toString();
	}



}
