package com.bber.company.android.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/14 0014.
 */
public class BuyerUserEntity implements Serializable {


    private Integer ubuyer;

    private Integer ubPoptype;

    private String ubPopularrize;


    private String ubName;

    private String ubPhone;

    private String ubEmail;

    private String ubPassword;

    private String ubHeadm;

    private String ubHeadbig;

    private Integer ubSex;
    private Integer fbSex;

    private Double ubMoney;

    private Integer ubState;

    private Boolean ubKade;

    private Integer ubKey;

    private String inviteCode;

    private String vipEndTime;
    private String vipStartTime;
    private String vipName;
    private String uBirthday;
    private Integer vipId;
    private Integer vipLevel;
    private Double gameFreeMoney;

    private String iSVerify;

    public String getiSVerify() {
        return iSVerify;
    }
    public void setiSVerify(String iSVerify) {
        this.iSVerify = iSVerify;
    }
    public Double getGameFreeMoney() {
        return gameFreeMoney;
    }
    public String getuBirthday() {
        return uBirthday;
    }
    public String getInviteCode() {
        return inviteCode;
    }
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
    public String getUbName() {
        return ubName;
    }
    public String getUbPhone() {
        return ubPhone;
    }
    public String getUbHeadm() {
        return ubHeadm;
    }
    public void setUbHeadm(String ubHeadm) {
        this.ubHeadm = ubHeadm;
    }
    public Integer getUbSex() {
        return ubSex;
    }
    public Double getUbMoney() {
        return ubMoney;
    }
    public String getVipEndTime() {
        return vipEndTime;
    }
    public String getVipStartTime() {
        return vipStartTime;
    }
    public String getVipName() {
        return vipName;
    }
    public Integer getVipId() {
        return vipId;
    }
    public Integer getVipLevel() {
        return vipLevel;
    }
}
