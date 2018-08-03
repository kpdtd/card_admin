package com.anl.card.persistence.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

public class CashCouponDefinition {
	private Integer id;
	private String title;
	private String subtitle;
	private String tips;
	private String deatil;
	private Integer number;
	private Integer couponType;
	private Integer faceValue;
	private Integer dateRule;
	private Integer validDays;
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date startDate;
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date endDate;
	private String extensionInfo;
	private Date createTime;
	private Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setSubtitle(String value) {
		this.subtitle = value;
	}
	
	public String getSubtitle() {
		return this.subtitle;
	}
	public void setTips(String value) {
		this.tips = value;
	}
	
	public String getTips() {
		return this.tips;
	}
	public void setDeatil(String value) {
		this.deatil = value;
	}
	
	public String getDeatil() {
		return this.deatil;
	}
	public void setNumber(Integer value) {
		this.number = value;
	}
	
	public Integer getNumber() {
		return this.number;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public void setFaceValue(Integer value) {
		this.faceValue = value;
	}
	
	public Integer getFaceValue() {
		return this.faceValue;
	}
	public void setDateRule(Integer value) {
		this.dateRule = value;
	}
	
	public Integer getDateRule() {
		return this.dateRule;
	}
	public void setValidDays(Integer value) {
		this.validDays = value;
	}
	
	public Integer getValidDays() {
		return this.validDays;
	}
	public void setStartDate(Date value) {
		this.startDate = value;
	}

	public Date getStartDate() {
		return this.startDate;
	}
	public void setEndDate(Date value) {
		this.endDate = value;
	}

	public Date getEndDate() {
		return this.endDate;
	}
	public void setExtensionInfo(String value) {
		this.extensionInfo = value;
	}
	
	public String getExtensionInfo() {
		return this.extensionInfo;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
}

