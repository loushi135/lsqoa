package com.xpsoft.oa.model.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GoodApplyTotal {

	private Long goodsId;
	private String goodsName;
	private int totalNum;
	private BigDecimal price;
	private BigDecimal totalAmount;
	private List<GoodApplyDep> goodApplyDepList = new ArrayList<GoodApplyDep>();

	
	public GoodApplyTotal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodApplyTotal(Long goodsId, String goodsName, int totalNum,
			BigDecimal price, BigDecimal totalAmount,
			List<GoodApplyDep> goodApplyDepList) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.totalNum = totalNum;
		this.price = price;
		this.totalAmount = totalAmount;
		this.goodApplyDepList = goodApplyDepList;
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

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<GoodApplyDep> getGoodApplyDepList() {
		return goodApplyDepList;
	}

	public void setGoodApplyDepList(List<GoodApplyDep> goodApplyDepList) {
		this.goodApplyDepList = goodApplyDepList;
	}

}
