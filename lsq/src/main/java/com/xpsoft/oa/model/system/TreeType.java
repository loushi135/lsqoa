package com.xpsoft.oa.model.system;


import com.google.gson.annotations.Expose;

/**
 * TreeType Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TreeType extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected String typeName;
	@Expose
	protected TreeType parent;
	@Expose
	protected String className;


	/**
	 * Default Empty Constructor for class TreeType
	 */
	public TreeType () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TreeType
	 */
	public TreeType (
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
	 * @hibernate.property column="typeName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTypeName() {
		return this.typeName;
	}
	
	/**
	 * Set the typeName
	 */	
	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}	


	public TreeType getParent() {
		return parent;
	}

	public void setParent(TreeType parent) {
		this.parent = parent;
	}

	/**
	 * 树类型	 * @return String
	 * @hibernate.property column="className" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getClassName() {
		return this.className;
	}
	
	/**
	 * Set the className
	 */	
	public void setClassName(String aValue) {
		this.className = aValue;
	}	

}
