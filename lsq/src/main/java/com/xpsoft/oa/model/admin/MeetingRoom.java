package com.xpsoft.oa.model.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.xpsoft.core.model.BaseModel;

/**
 * MeetingRoom Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class MeetingRoom extends BaseModel {

    protected Long id;
	protected String name;
	protected Integer maxPerson;
	protected Short microphone;
	protected Short speaker;
	protected Short projector;
	protected Short znhyt;
	protected Short notebook;
	protected Short wordPad;
	protected Short status;
	protected String remark;
	protected List<Meeting> meetingList = new ArrayList<Meeting>();


	/**
	 * Default Empty Constructor for class MeetingRoom
	 */
	public MeetingRoom () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class MeetingRoom
	 */
	public MeetingRoom (
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
	 * 	 * @return String
	 * @hibernate.property column="name" type="java.lang.String" length="64" not-null="true" unique="false"
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
	 * 	 * @return Integer
	 * @hibernate.property column="maxPerson" type="java.lang.Integer" length="10" not-null="true" unique="false"
	 */
	public Integer getMaxPerson() {
		return this.maxPerson;
	}
	
	/**
	 * Set the maxPerson
	 * @spring.validator type="required"
	 */	
	public void setMaxPerson(Integer aValue) {
		this.maxPerson = aValue;
	}	

	/**
	 * 	 * @return Short
	 * @hibernate.property column="microphone" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getMicrophone() {
		return this.microphone;
	}
	
	/**
	 * Set the microphone
	 * @spring.validator type="required"
	 */	
	public void setMicrophone(Short aValue) {
		this.microphone = aValue;
	}	

	/**
	 * 	 * @return Short
	 * @hibernate.property column="speaker" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getSpeaker() {
		return this.speaker;
	}
	
	/**
	 * Set the speaker
	 * @spring.validator type="required"
	 */	
	public void setSpeaker(Short aValue) {
		this.speaker = aValue;
	}	

	/**
	 * 	 * @return Short
	 * @hibernate.property column="projector" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getProjector() {
		return this.projector;
	}
	
	/**
	 * Set the projector
	 * @spring.validator type="required"
	 */	
	public void setProjector(Short aValue) {
		this.projector = aValue;
	}	

	/**
	 * 	 * @return Short
	 * @hibernate.property column="znhyt" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getZnhyt() {
		return this.znhyt;
	}
	
	/**
	 * Set the znhyt
	 * @spring.validator type="required"
	 */	
	public void setZnhyt(Short aValue) {
		this.znhyt = aValue;
	}	

	/**
	 * 	 * @return Short
	 * @hibernate.property column="notebook" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getNotebook() {
		return this.notebook;
	}
	
	/**
	 * Set the notebook
	 * @spring.validator type="required"
	 */	
	public void setNotebook(Short aValue) {
		this.notebook = aValue;
	}	

	/**
	 * 	 * @return Short
	 * @hibernate.property column="wordPad" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getWordPad() {
		return this.wordPad;
	}
	
	/**
	 * Set the wordPad
	 * @spring.validator type="required"
	 */	
	public void setWordPad(Short aValue) {
		this.wordPad = aValue;
	}	

	/**
	 * 	 * @return Short
	 * @hibernate.property column="status" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getStatus() {
		return this.status;
	}
	
	/**
	 * Set the status
	 * @spring.validator type="required"
	 */	
	public void setStatus(Short aValue) {
		this.status = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="remark" type="java.lang.String" length="500" not-null="false" unique="false"
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

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public List<Meeting> getMeetingList() {
		return meetingList;
	}
	public boolean equals(Object object) {
		if (!(object instanceof MeetingRoom)) {
			return false;
		}
		MeetingRoom rhs = (MeetingRoom) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.name, rhs.name)
				.append(this.maxPerson, rhs.maxPerson)
				.append(this.microphone, rhs.microphone)
				.append(this.speaker, rhs.speaker)
				.append(this.projector, rhs.projector)
				.append(this.znhyt, rhs.znhyt)
				.append(this.notebook, rhs.notebook)
				.append(this.wordPad, rhs.wordPad)
				.append(this.status, rhs.status)
				.append(this.remark, rhs.remark)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.name) 
				.append(this.maxPerson) 
				.append(this.microphone) 
				.append(this.speaker) 
				.append(this.projector) 
				.append(this.znhyt) 
				.append(this.notebook) 
				.append(this.wordPad) 
				.append(this.status) 
				.append(this.remark) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("name", this.name) 
				.append("maxPerson", this.maxPerson) 
				.append("microphone", this.microphone) 
				.append("speaker", this.speaker) 
				.append("projector", this.projector) 
				.append("znhyt", this.znhyt) 
				.append("notebook", this.notebook) 
				.append("wordPad", this.wordPad) 
				.append("status", this.status) 
				.append("remark", this.remark) 
				.toString();
	}

	public void setMeetingList(List<Meeting> meetingList) {
		this.meetingList = meetingList;
	}



}
