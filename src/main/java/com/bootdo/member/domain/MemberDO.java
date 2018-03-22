package com.bootdo.member.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-18 21:47:36
 */
public class MemberDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用户id
	private Integer tmId;
	//用户名
	private String tmAccount;
	//密码
	private String tmPassword;
	//昵称
	private String tmName;
	//手机号码
	private String tmPhone;
	//头像
	private String tmPhoto;
	//用户类型（1.用户、2.骑手）
	private Integer tmType;
	//用户位置（经度）
	private String tmLongitude;
	//用户位置（纬度）
	private String tmLatitude;

	/**
	 * 设置：用户id
	 */
	public void setTmId(Integer tmId) {
		this.tmId = tmId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getTmId() {
		return tmId;
	}
	/**
	 * 设置：用户名
	 */
	public void setTmAccount(String tmAccount) {
		this.tmAccount = tmAccount;
	}
	/**
	 * 获取：用户名
	 */
	public String getTmAccount() {
		return tmAccount;
	}
	/**
	 * 设置：密码
	 */
	public void setTmPassword(String tmPassword) {
		this.tmPassword = tmPassword;
	}
	/**
	 * 获取：密码
	 */
	public String getTmPassword() {
		return tmPassword;
	}
	/**
	 * 设置：昵称
	 */
	public void setTmName(String tmName) {
		this.tmName = tmName;
	}
	/**
	 * 获取：昵称
	 */
	public String getTmName() {
		return tmName;
	}
	/**
	 * 设置：手机号码
	 */
	public void setTmPhone(String tmPhone) {
		this.tmPhone = tmPhone;
	}
	/**
	 * 获取：手机号码
	 */
	public String getTmPhone() {
		return tmPhone;
	}
	/**
	 * 设置：头像
	 */
	public void setTmPhoto(String tmPhoto) {
		this.tmPhoto = tmPhoto;
	}
	/**
	 * 获取：头像
	 */
	public String getTmPhoto() {
		return tmPhoto;
	}
	/**
	 * 设置：用户类型（1.用户、2.骑手）
	 */
	public void setTmType(Integer tmType) {
		this.tmType = tmType;
	}
	/**
	 * 获取：用户类型（1.用户、2.骑手）
	 */
	public Integer getTmType() {
		return tmType;
	}
	/**
	 * 设置：用户位置（经度）
	 */
	public void setTmLongitude(String tmLongitude) {
		this.tmLongitude = tmLongitude;
	}
	/**
	 * 获取：用户位置（经度）
	 */
	public String getTmLongitude() {
		return tmLongitude;
	}
	/**
	 * 设置：用户位置（纬度）
	 */
	public void setTmLatitude(String tmLatitude) {
		this.tmLatitude = tmLatitude;
	}
	/**
	 * 获取：用户位置（纬度）
	 */
	public String getTmLatitude() {
		return tmLatitude;
	}
}
