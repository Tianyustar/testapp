package com.example.yunxi.testapp.global;

/**
 * Created by 天宇 on 2018/2/3.
 */

public enum URLEnum {

    Login("usrCloud/user/login"),
    Register("user/register"),
    CheckToken("user/checkToken"),
    UpdateUserInfo("user/updateUserInfo"),
    GetAllMsgs("msg/getAllMsgs"),
    AddMsg("msg/addMsg"),
    GetUserInfo("user/getUserInfo"),
    GetUserAttentionList("information/getUserAttentionList"),
    GetInformationById("information/getInformationById"),
    AddAttentions("userAttention/addAttentions"),
    DeleteAttentions("userAttention/deleteAttentions"),
    GetAllReminds("remind/getAllReminds"),
    GetAllKinds("kind/getAllKinds"),
    AddRemind("remind/addRemind"),
    UpdateRemind("remind/updateRemind"),
    GetRemindById("remind/getRemind"),
    DeleteRemind("remind/deleteRemind"),
    GetAllDevice("usrCloud/dev/getDevs");

    private String url;

    URLEnum(String url) {
        this.url = SysConfig.SERVER_URL + url;
    }

    public String getUrl() {
        return url;
    }
}