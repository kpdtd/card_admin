package com.anl.card.vo;

import com.anl.card.persistence.po.User;


public class UserExt extends User{

	private String channelName;

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
}

