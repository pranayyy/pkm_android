package com.example.ekatechhp.pkmapplication;

public class SetupUser {
    public String id;
    public String nickName;
    public String organization;
    public String mobile;
    public String designation;
    public String joblevel;
    public String sharewith;

    public SetupUser(String id, String nickName, String organization, String mobile, String designation, String joblevel, String sharewith) {
        this.id = id;
        this.nickName = nickName;
        this.organization = organization;
        this.mobile = mobile;
        this.designation = designation;
        this.joblevel = joblevel;
        this.sharewith = sharewith;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getJoblevel() {
        return joblevel;
    }
    public void setJoblevel(String joblevel) {
        this.joblevel = joblevel;
    }
    public String getSharewith() {
        return sharewith;
    }
    public void setSharewith(String sharewith) {
        this.sharewith = sharewith;
    }
}
