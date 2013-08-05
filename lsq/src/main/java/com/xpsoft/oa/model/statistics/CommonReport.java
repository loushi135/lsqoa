package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * CommonReport Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class CommonReport extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected String reporter;
	protected String deptName;
	protected String state;
	protected String company;
	protected String sendDept;
	protected String content;


	/**
	 * Default Empty Constructor for class CommonReport
	 */
	public CommonReport () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class CommonReport
	 */
	public CommonReport (
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
	 * @hibernate.property column="reporter" type="java.lang.String" length="20" not-null="true" unique="false"
	 */
	public String getReporter() {
		return this.reporter;
	}
	
	/**
	 * Set the reporter
	 * @spring.validator type="required"
	 */	
	public void setReporter(String aValue) {
		this.reporter = aValue;
	}	

	/**
	 * 所在部门	 * @return String
	 * @hibernate.property column="deptName" type="java.lang.String" length="20" not-null="true" unique="false"
	 */
	public String getDeptName() {
		return this.deptName;
	}
	
	/**
	 * Set the deptName
	 * @spring.validator type="required"
	 */	
	public void setDeptName(String aValue) {
		this.deptName = aValue;
	}	

	/**
	 * 类型	 * @return String
	 * @hibernate.property column="state" type="java.lang.String" length="20" not-null="false" unique="false"
	 */
	public String getState() {
		return this.state;
	}
	
	/**
	 * Set the state
	 */	
	public void setState(String aValue) {
		this.state = aValue;
	}	

	/**
	 * 所在公司	 * @return String
	 * @hibernate.property column="company" type="java.lang.String" length="20" not-null="true" unique="false"
	 */
	public String getCompany() {
		return this.company;
	}
	
	/**
	 * Set the company
	 * @spring.validator type="required"
	 */	
	public void setCompany(String aValue) {
		this.company = aValue;
	}	

	/**
	 * 抄送部门	 * @return String
	 * @hibernate.property column="send_dept" type="java.lang.String" length="20" not-null="true" unique="false"
	 */
	public String getSendDept() {
		return this.sendDept;
	}
	
	/**
	 * Set the sendDept
	 * @spring.validator type="required"
	 */	
	public void setSendDept(String aValue) {
		this.sendDept = aValue;
	}	

	/**
	 * 报告内容	 * @return String
	 * @hibernate.property column="content" type="java.lang.String" length="65535" not-null="true" unique="false"
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Set the content
	 * @spring.validator type="required"
	 */	
	public void setContent(String aValue) {
		this.content = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CommonReport)) {
			return false;
		}
		CommonReport rhs = (CommonReport) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.reporter, rhs.reporter)
				.append(this.deptName, rhs.deptName)
				.append(this.state, rhs.state)
				.append(this.company, rhs.company)
				.append(this.sendDept, rhs.sendDept)
				.append(this.content, rhs.content)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.reporter) 
				.append(this.deptName) 
				.append(this.state) 
				.append(this.company) 
				.append(this.sendDept) 
				.append(this.content) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("reporter", this.reporter) 
				.append("deptName", this.deptName) 
				.append("state", this.state) 
				.append("company", this.company) 
				.append("sendDept", this.sendDept) 
				.append("content", this.content) 
				.toString();
	}



}
