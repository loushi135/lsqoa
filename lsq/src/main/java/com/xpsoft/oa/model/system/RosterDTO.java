package com.xpsoft.oa.model.system;

import java.util.Date;
/**
 *员工花名册
 */
public class RosterDTO {
	private String staffNO;	//工号
	private String fullname;//姓名 
	private String sex;		//性别
	private String departmentName;//部门
	private String formation;//编制
	private String position;//职位
	private Date accessionTime;//入司时间
	private String workType;//劳动关系
	private Date  workContractEndDate;//劳动到期时间
	private String mobile;//手机
	
	
	public RosterDTO(String staffNO, String fullname, String sex, String departmentName, String formation, String position, Date accessionTime,String workType,
			Date workContractEndDate, String mobile) {
		super();
		this.staffNO = staffNO;
		this.fullname = fullname;
		this.sex = sex;
		this.departmentName = departmentName;
		this.formation = formation;
		this.position = position;
		this.accessionTime = accessionTime;
		this.workType = workType;
		this.workContractEndDate = workContractEndDate;
		this.mobile = mobile;
	}
	public String getStaffNO() {
		return staffNO;
	}
	public void setStaffNO(String staffNO) {
		this.staffNO = staffNO;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getAccessionTime() {
		return accessionTime;
	}
	public void setAccessionTime(Date accessionTime) {
		this.accessionTime = accessionTime;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public Date getWorkContractEndDate() {
		return workContractEndDate;
	}
	public void setWorkContractEndDate(Date workContractEndDate) {
		this.workContractEndDate = workContractEndDate;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
