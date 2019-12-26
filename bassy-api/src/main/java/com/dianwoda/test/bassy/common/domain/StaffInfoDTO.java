package com.dianwoda.test.bassy.common.domain;

import java.io.Serializable;

/**
 * @author zcp
 * 2019/12/21 上午10:37
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
public class StaffInfoDTO implements Serializable {

    private Integer id; //自生成ID
    private Integer depart; // 部门ID
    private String code; //员工号
    private String name; //名字
    private boolean disable; //是否启用(|停用)
    private String creator; //创建者
    private boolean isLeader;
    private String creattime; //创建时间

    private String duty;
    private String mobile;
    private String email;

    private String thirdpartyAccount;

    private String lastLoginTime; 	//最后登录时间
    private String lastLoginIp;		//最后登录IP
    private String lastLoginCity;	//最后登录城市

    private String previousLoginTime;	//上一次登录时间
    private String previousLoginIp;		//上一次登录IP
    private String previousLoginCity;	//上一次登录城市

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepart() {
        return depart;
    }

    public void setDepart(Integer depart) {
        this.depart = depart;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getThirdpartyAccount() {
        return thirdpartyAccount;
    }

    public void setThirdpartyAccount(String thirdpartyAccount) {
        this.thirdpartyAccount = thirdpartyAccount;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginCity() {
        return lastLoginCity;
    }

    public void setLastLoginCity(String lastLoginCity) {
        this.lastLoginCity = lastLoginCity;
    }

    public String getPreviousLoginTime() {
        return previousLoginTime;
    }

    public void setPreviousLoginTime(String previousLoginTime) {
        this.previousLoginTime = previousLoginTime;
    }

    public String getPreviousLoginIp() {
        return previousLoginIp;
    }

    public void setPreviousLoginIp(String previousLoginIp) {
        this.previousLoginIp = previousLoginIp;
    }

    public String getPreviousLoginCity() {
        return previousLoginCity;
    }

    public void setPreviousLoginCity(String previousLoginCity) {
        this.previousLoginCity = previousLoginCity;
    }
}
