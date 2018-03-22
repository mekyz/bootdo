package com.bootdo.order.domain;

import com.bootdo.business.domain.BusinessDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-19 22:36:31
 */
public class OrderDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //订单id
    private Integer toId;
    //用户id
    private Integer toMemberId;
    //订单编号
    private String toNumber;
    //1.待支付，2.待商家接单，3.待骑手抢单，4.骑手待取货，5.待配送，6.待评价，7已完成，0.删除
    private Integer toStatus;
    //收货人姓名
    private String toReName;
    //收货人电话
    private String toRePhone;
    //收货人地址
    private String toReAddress;
    //商家id（买的谁家的东西）
    private Integer toBussinessId;
    //下单时间
    private Date toCreateTime;
    //买的商品和数量（rg:[{"tcId":52,"count":89}]）
    private String toTcidNum;
    //订单金额
    private BigDecimal toPrice;

    //自己增加的
    private BusinessDO businessDO;
    private String shoppingInfo;
    private String status;

    /**
     * 设置：订单id
     */
    public void setToId(Integer toId) {
        this.toId = toId;
    }

    /**
     * 获取：订单id
     */
    public Integer getToId() {
        return toId;
    }

    /**
     * 设置：用户id
     */
    public void setToMemberId(Integer toMemberId) {
        this.toMemberId = toMemberId;
    }

    /**
     * 获取：用户id
     */
    public Integer getToMemberId() {
        return toMemberId;
    }

    /**
     * 设置：订单编号
     */
    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    /**
     * 获取：订单编号
     */
    public String getToNumber() {
        return toNumber;
    }

    /**
     * 设置：1.待支付，2.待商家接单，3.待骑手抢单，4.骑手待取货，5.待配送，6.待评价，7已完成，0.删除
     */
    public void setToStatus(Integer toStatus) {
        this.toStatus = toStatus;
    }

    /**
     * 获取：1.待支付，2.待商家接单，3.待骑手抢单，4.骑手待取货，5.待配送，6.待评价，7已完成，0.删除
     */
    public Integer getToStatus() {
        return toStatus;
    }

    /**
     * 设置：收货人姓名
     */
    public void setToReName(String toReName) {
        this.toReName = toReName;
    }

    /**
     * 获取：收货人姓名
     */
    public String getToReName() {
        return toReName;
    }

    /**
     * 设置：收货人电话
     */
    public void setToRePhone(String toRePhone) {
        this.toRePhone = toRePhone;
    }

    /**
     * 获取：收货人电话
     */
    public String getToRePhone() {
        return toRePhone;
    }

    /**
     * 设置：收货人地址
     */
    public void setToReAddress(String toReAddress) {
        this.toReAddress = toReAddress;
    }

    /**
     * 获取：收货人地址
     */
    public String getToReAddress() {
        return toReAddress;
    }

    /**
     * 设置：商家id（买的谁家的东西）
     */
    public void setToBussinessId(Integer toBussinessId) {
        this.toBussinessId = toBussinessId;
    }

    /**
     * 获取：商家id（买的谁家的东西）
     */
    public Integer getToBussinessId() {
        return toBussinessId;
    }

    /**
     * 设置：下单时间
     */
    public void setToCreateTime(Date toCreateTime) {
        this.toCreateTime = toCreateTime;
    }

    /**
     * 获取：下单时间
     */
    public Date getToCreateTime() {
        return toCreateTime;
    }

    /**
     * 设置：买的商品和数量（rg:[{"tcId":52,"count":89}]）
     */
    public void setToTcidNum(String toTcidNum) {
        this.toTcidNum = toTcidNum;
    }

    /**
     * 获取：买的商品和数量（rg:[{"tcId":52,"count":89}]）
     */
    public String getToTcidNum() {
        return toTcidNum;
    }

    /**
     * 设置：订单金额
     */
    public void setToPrice(BigDecimal toPrice) {
        this.toPrice = toPrice;
    }

    /**
     * 获取：订单金额
     */
    public BigDecimal getToPrice() {
        return toPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BusinessDO getBusinessDO() {
        return businessDO;
    }

    public void setBusinessDO(BusinessDO businessDO) {
        this.businessDO = businessDO;
    }

    public String getShoppingInfo() {
        return shoppingInfo;
    }

    public void setShoppingInfo(String shoppingInfo) {
        this.shoppingInfo = shoppingInfo;
    }
}
