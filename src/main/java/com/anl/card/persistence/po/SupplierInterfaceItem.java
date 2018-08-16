package com.anl.card.persistence.po;

import java.util.*;

public class SupplierInterfaceItem {
	private Integer id;
	private Integer supplierId;
	//private String ecCode;
	private Integer interfaceId;
	private String interfaceInfo;
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

	public void setInterfaceId(Integer value) {
		this.interfaceId = value;
	}
	
	public Integer getInterfaceId() {
		return this.interfaceId;
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

	public String getInterfaceInfo() {
		return interfaceInfo;
	}

	public void setInterfaceInfo(String interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}
}

