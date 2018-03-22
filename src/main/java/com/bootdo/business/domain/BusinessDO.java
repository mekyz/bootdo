package com.bootdo.business.domain;

import java.io.Serializable;


/**
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-11 12:15:28
 */
public class BusinessDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //商家id
    private Integer tbId;
    //系统用户表id
    private Integer tbUserId;
    //商店名字
    private String tbStoreName;
    //起送价格
    private Integer tbStartingPrice;
    //商家地址
    private String tbAddress;
    //商家电话
    private String tbPhone;
    //店铺图片
    private String tbPhoto;
    //店铺位置（经度）
    private String tbLongitude;
    //店铺位置（纬度）
    private String tbLatitude;
    //geohash编码
    private String tbGeoCode;
    private double distance;

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
     * 设置：系统用户表id
     */
    public void setTbUserId(Integer tbUserId) {
        this.tbUserId = tbUserId;
    }

    /**
     * 获取：系统用户表id
     */
    public Integer getTbUserId() {
        return tbUserId;
    }

    /**
     * 设置：商店名字
     */
    public void setTbStoreName(String tbStoreName) {
        this.tbStoreName = tbStoreName;
    }

    /**
     * 获取：商店名字
     */
    public String getTbStoreName() {
        return tbStoreName;
    }

    /**
     * 设置：起送价格
     */
    public void setTbStartingPrice(Integer tbStartingPrice) {
        this.tbStartingPrice = tbStartingPrice;
    }

    /**
     * 获取：起送价格
     */
    public Integer getTbStartingPrice() {
        return tbStartingPrice;
    }

    /**
     * 设置：商家地址
     */
    public void setTbAddress(String tbAddress) {
        this.tbAddress = tbAddress;
    }

    /**
     * 获取：商家地址
     */
    public String getTbAddress() {
        return tbAddress;
    }

    /**
     * 设置：商家电话
     */
    public void setTbPhone(String tbPhone) {
        this.tbPhone = tbPhone;
    }

    /**
     * 获取：商家电话
     */
    public String getTbPhone() {
        return tbPhone;
    }

    /**
     * 设置：店铺图片
     */
    public void setTbPhoto(String tbPhoto) {
        this.tbPhoto = tbPhoto;
    }

    /**
     * 获取：店铺图片
     */
    public String getTbPhoto() {
        return tbPhoto;
    }

    /**
     * 设置：店铺位置（经度）
     */
    public void setTbLongitude(String tbLongitude) {
        this.tbLongitude = tbLongitude;
    }

    /**
     * 获取：店铺位置（经度）
     */
    public String getTbLongitude() {
        return tbLongitude;
    }

    /**
     * 设置：店铺位置（纬度）
     */
    public void setTbLatitude(String tbLatitude) {
        this.tbLatitude = tbLatitude;
    }

    /**
     * 获取：店铺位置（纬度）
     */
    public String getTbLatitude() {
        return tbLatitude;
    }

    /**
     * 设置：geohash编码
     */
    public void setTbGeoCode(String tbGeoCode) {
        this.tbGeoCode = tbGeoCode;
    }

    /**
     * 获取：geohash编码
     */
    public String getTbGeoCode() {
        return tbGeoCode;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
