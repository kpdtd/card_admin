package com.anl.card.persistence.po;

import java.util.*;

public class UserPlan {
	private Integer id;
	private Integer userId;
	private Integer planId;
	private String planName;
	private Date startTime;
	private Date endTime;
	private Integer newPlanId;
	private Date createTime;
	private Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	public void setPlanId(Integer value) {
		this.planId = value;
	}
	
	public Integer getPlanId() {
		return this.planId;
	}
	public void setPlanName(String value) {
		this.planName = value;
	}
	
	public String getPlanName() {
		return this.planName;
	}
	public void setStartTime(Date value) {
		this.startTime = value;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	public void setEndTime(Date value) {
		this.endTime = value;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
	public void setNewPlanId(Integer value) {
		this.newPlanId = value;
	}
	
	public Integer getNewPlanId() {
		return this.newPlanId;
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

