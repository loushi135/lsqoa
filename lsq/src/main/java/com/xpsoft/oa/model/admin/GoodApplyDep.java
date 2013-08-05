package com.xpsoft.oa.model.admin;

import java.math.BigDecimal;

public class GoodApplyDep {

	private Long goodsId;
	private String goodsName;
	private int num;
	private BigDecimal amount;
	private BigDecimal price;
	private String depName;

	public GoodApplyDep() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodApplyDep(Long goodsId, String goodsName, int num,
			BigDecimal amount, BigDecimal price, String depName) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.num = num;
		this.amount = amount;
		this.price = price;
		this.depName = depName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

}
