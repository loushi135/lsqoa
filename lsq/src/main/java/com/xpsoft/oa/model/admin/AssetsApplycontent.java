package com.xpsoft.oa.model.admin;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * AssetsApplycontent Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class AssetsApplycontent extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String name;
	@Expose
	protected String model;
	@Expose
	protected String brand;
	@Expose
	protected java.math.BigDecimal num;
	@Expose
	protected String unit;
	@Expose
	protected java.math.BigDecimal price;
	@Expose
	protected java.util.Date arrivalDate;
	protected com.xpsoft.oa.model.admin.AssetsApply assetsApply;


	/**
	 * Default Empty Constructor for class AssetsApplycontent
	 */
	public AssetsApplycontent () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class AssetsApplycontent
	 */
	public AssetsApplycontent (
		 Long in_id
        ) {
		this.setId(in_id);
    }

	
	public com.xpsoft.oa.model.admin.AssetsApply getAssetsApply () {
		return assetsApply;
	}	
	
	public void setAssetsApply (com.xpsoft.oa.model.admin.AssetsApply in_assetsApply) {
		this.assetsApply = in_assetsApply;
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
	 * @hibernate.property column="name" type="java.lang.String" length="24" not-null="true" unique="false"
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
	 * @hibernate.property column="model" type="java.lang.String" length="24" not-null="true" unique="false"
	 */
	public String getModel() {
		return this.model;
	}
	
	/**
	 * Set the model
	 * @spring.validator type="required"
	 */	
	public void setModel(String aValue) {
		this.model = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="brand" type="java.lang.String" length="24" not-null="true" unique="false"
	 */
	public String getBrand() {
		return this.brand;
	}
	
	/**
	 * Set the brand
	 * @spring.validator type="required"
	 */	
	public void setBrand(String aValue) {
		this.brand = aValue;
	}	

	/**
	 * 	 * @return Integer
	 * @hibernate.property column="num" type="java.lang.Integer" length="10" not-null="true" unique="false"
	 */
	public java.math.BigDecimal getNum() {
		return this.num;
	}
	
	/**
	 * Set the num
	 * @spring.validator type="required"
	 */	
	public void setNum(java.math.BigDecimal aValue) {
		this.num = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="unit" type="java.lang.String" length="12" not-null="true" unique="false"
	 */
	public String getUnit() {
		return this.unit;
	}
	
	/**
	 * Set the unit
	 * @spring.validator type="required"
	 */	
	public void setUnit(String aValue) {
		this.unit = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="price" type="java.math.BigDecimal" length="10" not-null="true" unique="false"
	 */
	public java.math.BigDecimal getPrice() {
		return this.price;
	}
	
	/**
	 * Set the price
	 * @spring.validator type="required"
	 */	
	public void setPrice(java.math.BigDecimal aValue) {
		this.price = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="arrivalDate" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getArrivalDate() {
		return this.arrivalDate;
	}
	
	/**
	 * Set the arrivalDate
	 * @spring.validator type="required"
	 */	
	public void setArrivalDate(java.util.Date aValue) {
		this.arrivalDate = aValue;
	}	

	/**
	 * 	 * @return Long
	 */
	public Long getAssetsapplyId() {
		return this.getAssetsApply()==null?null:this.getAssetsApply().getId();
	}
	
	/**
	 * Set the assetsapplyId
	 */	
	public void setAssetsapplyId(Long aValue) {
	    if (aValue==null) {
	    	assetsApply = null;
	    } else if (assetsApply == null) {
	        assetsApply = new com.xpsoft.oa.model.admin.AssetsApply(aValue);
	        assetsApply.setVersion(new Integer(0));//set a version to cheat hibernate only
	    } else {
			assetsApply.setId(aValue);
	    }
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AssetsApplycontent)) {
			return false;
		}
		AssetsApplycontent rhs = (AssetsApplycontent) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.name, rhs.name)
				.append(this.model, rhs.model)
				.append(this.brand, rhs.brand)
				.append(this.num, rhs.num)
				.append(this.unit, rhs.unit)
				.append(this.price, rhs.price)
				.append(this.arrivalDate, rhs.arrivalDate)
						.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.name) 
				.append(this.model) 
				.append(this.brand) 
				.append(this.num) 
				.append(this.unit) 
				.append(this.price) 
				.append(this.arrivalDate) 
						.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("name", this.name) 
				.append("model", this.model) 
				.append("brand", this.brand) 
				.append("num", this.num) 
				.append("unit", this.unit) 
				.append("price", this.price) 
				.append("arrivalDate", this.arrivalDate) 
						.toString();
	}



}
