package com.anl.card.vo;


import com.anl.card.persistence.po.UserFlowUsedDay;


public class UserFlowUsedDayExt extends UserFlowUsedDay {

    private String userName;
    private Integer userId;
    private String iccid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
}

