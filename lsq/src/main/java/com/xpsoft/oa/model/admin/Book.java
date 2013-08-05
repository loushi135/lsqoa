package com.xpsoft.oa.model.admin;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * Book Base Java Bean, base class for the.oa.model, mapped directly to database
 * table
 * 
 * Avoid changing this file if not necessary, will be overwritten.
 * 
 * ������
 */
public class Book extends com.xpsoft.core.model.BaseModel {

	protected Long bookId;
	protected String bookName;
	protected String sn;
	protected String author;
	protected String isbn;
	protected String publisher;
	protected String projectId;
	protected String projectName;
	protected String department;
	protected Integer amount;
	protected Integer pageCount;
	protected Integer leftAmount;
	protected Integer secretLevel;
	protected Date createDate;
	protected Date expirationDate;
	protected String createUser;
	protected Integer state=0;//0 可用 ，1禁用 ，2 删除了
	protected Boolean complete;
	protected Boolean pcFile;
	protected Boolean paperFile;
	protected String operator;
	protected String remark;
	protected String filePath;
	protected String fileName;
	protected Date fileDate;
	protected Date makeFileDate;

	protected com.xpsoft.oa.model.admin.BookType bookType;

	protected BookDel bookDel;
	
	protected java.util.Set bookSns = new java.util.HashSet();
	
	protected Set bookFiles = new HashSet();

	/**
	 * Default Empty Constructor for class Book
	 */
	public Book() {
		super();
	}
	
	

	public Set getBookFiles() {
		return bookFiles;
	}



	public void setBookFiles(Set bookFiles) {
		this.bookFiles = bookFiles;
	}



	public BookDel getBookDel() {
		return bookDel;
	}



	public void setBookDel(BookDel bookDel) {
		this.bookDel = bookDel;
	}



	/**
	 * Default Key Fields Constructor for class Book
	 */
	public Book(Long in_bookId) {
		this.setBookId(in_bookId);
	}

	public com.xpsoft.oa.model.admin.BookType getBookType() {
		return bookType;
	}

	public void setBookType(com.xpsoft.oa.model.admin.BookType in_bookType) {
		this.bookType = in_bookType;
	}

	public java.util.Set getBookSns() {
		return bookSns;
	}

	public void setBookSns(java.util.Set in_bookSns) {
		this.bookSns = in_bookSns;
	}

	/**
	 * *
	 * 
	 * @return Long
	 * @hibernate.id column="bookId" type="java.lang.Long"
	 *               generator-class="native"
	 */
	public Long getBookId() {
		return this.bookId;
	}

	/**
	 * Set the bookId
	 */
	public void setBookId(Long aValue) {
		this.bookId = aValue;
	}

	/**
	 * *
	 * 
	 * @return Long
	 */
	public Long getTypeId() {
		return this.getBookType() == null ? null : this.getBookType()
				.getTypeId();
	}

	/**
	 * Set the typeId
	 */
	public void setTypeId(Long aValue) {
		if (aValue == null) {
			bookType = null;
		} else if (bookType == null) {
			bookType = new com.xpsoft.oa.model.admin.BookType(aValue);
			bookType.setVersion(new Integer(0));// set a version to cheat
			// hibernate only
		} else {
			bookType.setTypeId(aValue);
		}
	}

	/**
	 * *
	 * 
	 * @return String
	 * @hibernate.property column="bookName" type="java.lang.String"
	 *                     length="128" not-null="true" unique="false"
	 */
	public String getBookName() {
		return this.bookName;
	}

	/**
	 * Set the bookName
	 * 
	 * @spring.validator type="required"
	 */
	public void setBookName(String aValue) {
		this.bookName = aValue;
	}

	/**
	 * *
	 * 
	 * @return String
	 * @hibernate.property column="author" type="java.lang.String" length="128"
	 *                     not-null="true" unique="false"
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * Set the author
	 * 
	 * @spring.validator type="required"
	 */
	public void setAuthor(String aValue) {
		this.author = aValue;
	}

	/**
	 * *
	 * 
	 * @return String
	 * @hibernate.property column="isbn" type="java.lang.String" length="64"
	 *                     not-null="true" unique="false"
	 */
	public String getIsbn() {
		return this.isbn;
	}

	/**
	 * Set the isbn
	 * 
	 * @spring.validator type="required"
	 */
	public void setIsbn(String aValue) {
		this.isbn = aValue;
	}

	/**
	 * *
	 * 
	 * @return String
	 * @hibernate.property column="publisher" type="java.lang.String"
	 *                     length="128" not-null="false" unique="false"
	 */
	public String getPublisher() {
		return this.publisher;
	}

	/**
	 * Set the publisher
	 */
	public void setPublisher(String aValue) {
		this.publisher = aValue;
	}

	

	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * *
	 * 
	 * @return String
	 * @hibernate.property column="department" type="java.lang.String"
	 *                     length="64" not-null="true" unique="false"
	 */
	public String getDepartment() {
		return this.department;
	}

	/**
	 * Set the department
	 * 
	 * @spring.validator type="required"
	 */
	public void setDepartment(String aValue) {
		this.department = aValue;
	}

	/**
	 * *
	 * 
	 * @return Integer
	 * @hibernate.property column="amount" type="java.lang.Integer" length="10"
	 *                     not-null="true" unique="false"
	 */
	public Integer getAmount() {
		return this.amount;
	}

	/**
	 * Set the amount
	 * 
	 * @spring.validator type="required"
	 */
	public void setAmount(Integer aValue) {
		this.amount = aValue;
	}

	public Integer getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(Integer leftAmount) {
		this.leftAmount = leftAmount;
	}
	

	public Date getFileDate() {
		return fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	public Date getMakeFileDate() {
		return makeFileDate;
	}

	public void setMakeFileDate(Date makeFileDate) {
		this.makeFileDate = makeFileDate;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Book)) {
			return false;
		}
		Book rhs = (Book) object;
		return new EqualsBuilder().append(this.bookId, rhs.bookId).append(
				this.bookName, rhs.bookName).append(this.author, rhs.author)
				.append(this.isbn, rhs.isbn).append(this.publisher,
						rhs.publisher).append(this.projectId, rhs.projectId).append(
						this.projectName, rhs.projectName).append(this.department,
						rhs.department).append(this.amount, rhs.amount).append(
						this.leftAmount, rhs.leftAmount).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.bookId)
				.append(this.bookName).append(this.author).append(this.isbn)
				.append(this.publisher).append(this.projectId)
				.append(this.projectName).append(this.department).append(
						this.amount).append(this.leftAmount).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("bookId", this.bookId).append(
				"bookName", this.bookName).append("author", this.author)
				.append("isbn", this.isbn).append("publisher", this.publisher)
				.append("projectId", this.projectId).append("projectName", this.projectName)
				.append("department", this.department).append("amount",
						this.amount).append("leftAmount", this.leftAmount)
				.toString();
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getSecretLevel() {
		return secretLevel;
	}

	public void setSecretLevel(Integer secretLevel) {
		this.secretLevel = secretLevel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Boolean getPcFile() {
		return pcFile;
	}

	public void setPcFile(Boolean pcFile) {
		this.pcFile = pcFile;
	}

	public Boolean getPaperFile() {
		return paperFile;
	}

	public void setPaperFile(Boolean paperFile) {
		this.paperFile = paperFile;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
