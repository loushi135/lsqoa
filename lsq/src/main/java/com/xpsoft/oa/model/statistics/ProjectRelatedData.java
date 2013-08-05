package com.xpsoft.oa.model.statistics;

import java.math.BigDecimal;

public class ProjectRelatedData {
	
	private Long id;
	private String area;
	private String proNo;
	private String proName;
	private String proChargerName;
	private BigDecimal contractAmount;
	private BigDecimal auditAmount;
	private BigDecimal receiveAmount;
	private BigDecimal managePercentage;
	private BigDecimal manageAmount;
	private BigDecimal bankPayAmount;
	private BigDecimal cashRePayAmount;
	private BigDecimal totalPayAmount;
	private BigDecimal leftAmount;
	public ProjectRelatedData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProjectRelatedData(Long id, String area, String proNo,
			String proName, String proChargerName, BigDecimal contractAmount,
			BigDecimal auditAmount, BigDecimal receiveAmount,
			BigDecimal managePercentage, BigDecimal manageAmount,
			BigDecimal bankPayAmount, BigDecimal cashRePayAmount,
			BigDecimal totalPayAmount, BigDecimal leftAmount) {
		super();
		this.id = id;
		this.area = area;
		this.proNo = proNo;
		this.proName = proName;
		this.proChargerName = proChargerName;
		this.contractAmount = contractAmount;
		this.auditAmount = auditAmount;
		this.receiveAmount = receiveAmount;
		this.managePercentage = managePercentage;
		this.manageAmount = manageAmount;
		this.bankPayAmount = bankPayAmount;
		this.cashRePayAmount = cashRePayAmount;
		this.totalPayAmount = totalPayAmount;
		this.leftAmount = leftAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProChargerName() {
		return proChargerName;
	}
	public void setProChargerName(String proChargerName) {
		this.proChargerName = proChargerName;
	}
	public BigDecimal getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
	public BigDecimal getAuditAmount() {
		return auditAmount;
	}
	public void setAuditAmount(BigDecimal auditAmount) {
		this.auditAmount = auditAmount;
	}
	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}
	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	public BigDecimal getManagePercentage() {
		return managePercentage;
	}
	public void setManagePercentage(BigDecimal managePercentage) {
		this.managePercentage = managePercentage;
	}
	public BigDecimal getManageAmount() {
		return manageAmount;
	}
	public void setManageAmount(BigDecimal manageAmount) {
		this.manageAmount = manageAmount;
	}
	public BigDecimal getBankPayAmount() {
		return bankPayAmount;
	}
	public void setBankPayAmount(BigDecimal bankPayAmount) {
		this.bankPayAmount = bankPayAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getCashRePayAmount() {
		return cashRePayAmount;
	}
	public void setCashRePayAmount(BigDecimal cashRePayAmount) {
		this.cashRePayAmount = cashRePayAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getTotalPayAmount() {
		return totalPayAmount;
	}
	public void setTotalPayAmount(BigDecimal totalPayAmount) {
		
		
		this.totalPayAmount = totalPayAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getLeftAmount() {
		return leftAmount;
	}
	public void setLeftAmount(BigDecimal leftAmount) {
		this.leftAmount = leftAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	
}
