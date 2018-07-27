package com.anl.card.vo;

import com.anl.card.persistence.po.User;
import com.anl.card.persistence.po.UserAccount;


public class UserAccountExt extends UserAccount{

	private String username;

	private String wxOpenid;

	private String iccid;

	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}

