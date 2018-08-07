package com.anl.card.persistence.po;

import java.util.*;

public class SupplierInterfaceItem {
	private Integer id;
	private Integer supplierId;
	private String ecCode;
	private Integer interfaceId;
	private String interfaceTag;
	private String className;
	private String url;
	private Date createTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setSupplierId(Integer value) {
		this.supplierId = value;
	}
	
	public Integer getSupplierId() {
		return this.supplierId;
	}
	public void setEcCode(String value) {
		this.ecCode = value;
	}
	
	public String getEcCode() {
		return this.ecCode;
	}
	public void setInterfaceId(Integer value) {
		this.interfaceId = value;
	}
	
	public Integer getInterfaceId() {
		return this.interfaceId;
	}
	public void setInterfaceTag(String value) {
		this.interfaceTag = value;
	}
	
	public String getInterfaceTag() {
		return this.interfaceTag;
	}
	public void setClassName(String value) {
		this.className = value;
	}
	
	public String getClassName() {
		return this.className;
	}
	public void setUrl(String value) {
		this.url = value;
	}
	
	public String getUrl() {
		return this.url;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
}

