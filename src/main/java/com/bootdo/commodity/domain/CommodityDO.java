package com.bootdo.commodity.domain;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 
 * 
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-12 16:37:04
 */
public class CommodityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//商品id
	private Integer tcId;
	//商家id
	private Integer tbId;
	//商品名字
	private String tcName;
	//商品图片
	private String tcPhoto;
	//商品价格
	private BigDecimal tcPrice;
	//商品描述
	private String tcDescription;

	/**
	 * 设置：商品id
	 */
	public void setTcId(Integer tcId) {
		this.tcId = tcId;
	}
	/**
	 * 获取：商品id
	 */
	public Integer getTcId() {
		return tcId;
	}
	/**
	 * 设置：商家id
	 */
	public void setTbId(Integer tbId) {
		this.tbId = tbId;
	}
	/**
	 * 获取：商家id
	 */
	public Integer getTbId() {
		return tbId;
	}
	/**
	 * 设置：商品名字
	 */
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	/**
	 * 获取：商品名字
	 */
	public String getTcName() {
		return tcName;
	}
	/**
	 * 设置：商品图片
	 */
	public void setTcPhoto(String tcPhoto) {
		this.tcPhoto = tcPhoto;
	}
	/**
	 * 获取：商品图片
	 */
	public String getTcPhoto() {
		return tcPhoto;
	}
	/**
	 * 设置：商品价格
	 */
	public void setTcPrice(BigDecimal tcPrice) {
		this.tcPrice = tcPrice;
	}
	/**
	 * 获取：商品价格
	 */
	public BigDecimal getTcPrice() {
		return tcPrice;
	}
	/**
	 * 设置：商品描述
	 */
	public void setTcDescription(String tcDescription) {
		this.tcDescription = tcDescription;
	}
	/**
	 * 获取：商品描述
	 */
	public String getTcDescription() {
		return tcDescription;
	}
}
