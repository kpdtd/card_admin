package com.anl.card.persistence.po;

import java.util.*;

public class UserFlowUsedDay {
	private Integer id;
	private Integer cardId;
	private Integer flow;
	private Date recordTime;
	private Date createTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setCardId(Integer value) {
		this.cardId = value;
	}
	
	public Integer getCardId() {
		return this.cardId;
	}
	public void setFlow(Integer value) {
		this.flow = value;
	}
	
	public Integer getFlow() {
		return this.flow;
	}
	public void setRecordTime(Date value) {
		this.recordTime = value;
	}
	
	public Date getRecordTime() {
		return this.recordTime;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
}

