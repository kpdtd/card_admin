package com.anl.card.vo;


import com.anl.card.persistence.po.UserPlan;


public class UserPlanExt extends UserPlan{

	private String planName;

	@Override
	public String getPlanName() {
		return planName;
	}

	@Override
	public void setPlanName(String planName) {
		this.planName = planName;
	}
}

