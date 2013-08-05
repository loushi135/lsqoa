package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;

/**
 * LocalProductApplyInfo Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class LocalProductApplyInfo extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected LocalProductApply localProductApply;
	@Expose
	protected String name;
	@Expose
	protected java.math.BigDecimal price;
	@Expose
	protected Integer num;
	@Expose
	protected java.math.BigDecimal amount;
	@Expose
	protected ProjectNew project;


	/**
	 * Default Empty Constructor for class LocalProductApplyInfo
	 */
	public LocalProductApplyInfo () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class LocalProductApplyInfo
	 */
	public LocalProductApplyInfo (
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
	 * 名称	 * @return String
	 * @hibernate.property column="name" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the name
	 */	
	public void setName(String aValue) {
		this.name = aValue;
	}	

	/**
	 * 单价	 * @return java.math.BigDecimal
	 * @hibernate.property column="price" type="java.math.BigDecimal" length="13" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getPrice() {
		return this.price;
	}
	
	/**
	 * Set the price
	 */	
	public void setPrice(java.math.BigDecimal aValue) {
		this.price = aValue;
	}	

	/**
	 * 数量	 * @return Integer
	 * @hibernate.property column="num" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getNum() {
		return this.num;
	}
	
	/**
	 * Set the num
	 */	
	public void setNum(Integer aValue) {
		this.num = aValue;
	}	

	/**
	 * 总价	 * @return java.math.BigDecimal
	 * @hibernate.property column="amount" type="java.math.BigDecimal" length="13" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}
	
	/**
	 * Set the amount
	 */	
	public void setAmount(java.math.BigDecimal aValue) {
		this.amount = aValue;
	}

	public LocalProductApply getLocalProductApply() {
		return localProductApply;
	}

	public void setLocalProductApply(LocalProductApply localProductApply) {
		this.localProductApply = localProductApply;
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}	

}
