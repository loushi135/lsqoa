package com.xpsoft.oa.model.statistics;

import java.math.BigDecimal;

public class PayAndBillDetailDTO {
	private String proNO;	//项目
	private String proName;	//项目名称
	private String supplierName;//供应商名称
	private BigDecimal billCount;//收到发票金额
	private BigDecimal payCount;//付款金额
	private BigDecimal billBalance;//发票余额
	
	
	
	public String getProNO() {
		return proNO;
	}
	public void setProNO(String proNO) {
		this.proNO = proNO;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public BigDecimal getBillCount() {
		return billCount;
	}
	public void setBillCount(BigDecimal billCount) {
		this.billCount = billCount;
	}
	public BigDecimal getPayCount() {
		return payCount;
	}
	public void setPayCount(BigDecimal payCount) {
		this.payCount = payCount;
	}
	public BigDecimal getBillBalance() {
		return billBalance;
	}
	public void setBillBalance(BigDecimal billBalance) {
		this.billBalance = billBalance;
	}
}
