package com.anl.card.persistence.po;

import java.util.*;

public class ActivityCardInfo {
	private Integer id;
	private String iccid;
	private String msisdn;
	private String imsi;
	private String mobile;
	private Integer cardState;
	private Date startTime;
	private Date nextTime;
	private Integer validDays;
	private Integer surplusDays;
	private Integer price;
	private Integer sort;
	private String address;
	private String name;
	private String info;
	private Integer urlPageId;
	private Integer expressMsgId;
	private Integer returnVisitInformation;
	private Integer callStatus;
	private Integer state;
	private Date createTime;
	private Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setIccid(String value) {
		this.iccid = value;
	}
	
	public String getIccid() {
		return this.iccid;
	}
	public void setMsisdn(String value) {
		this.msisdn = value;
	}
	
	public String getMsisdn() {
		return this.msisdn;
	}
	public void setImsi(String value) {
		this.imsi = value;
	}
	
	public String getImsi() {
		return this.imsi;
	}
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	public void setCardState(Integer value) {
		this.cardState = value;
	}
	
	public Integer getCardState() {
		return this.cardState;
	}
	public void setStartTime(Date value) {
		this.startTime = value;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	public void setNextTime(Date value) {
		this.nextTime = value;
	}
	
	public Date getNextTime() {
		return this.nextTime;
	}
	public void setValidDays(Integer value) {
		this.validDays = value;
	}
	
	public Integer getValidDays() {
		return this.validDays;
	}
	public void setSurplusDays(Integer value) {
		this.surplusDays = value;
	}
	
	public Integer getSurplusDays() {
		return this.surplusDays;
	}
	public void setPrice(Integer value) {
		this.price = value;
	}
	
	public Integer getPrice() {
		return this.price;
	}
	public void setSort(Integer value) {
		this.sort = value;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return this.address;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setInfo(String value) {
		this.info = value;
	}
	
	public String getInfo() {
		return this.info;
	}
	public void setUrlPageId(Integer value) {
		this.urlPageId = value;
	}
	
	public Integer getUrlPageId() {
		return this.urlPageId;
	}
	public void setExpressMsgId(Integer value) {
		this.expressMsgId = value;
	}
	
	public Integer getExpressMsgId() {
		return this.expressMsgId;
	}
	public void setReturnVisitInformation(Integer value) {
		this.returnVisitInformation = value;
	}
	
	public Integer getReturnVisitInformation() {
		return this.returnVisitInformation;
	}
	public void setCallStatus(Integer value) {
		this.callStatus = value;
	}
	
	public Integer getCallStatus() {
		return this.callStatus;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
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

