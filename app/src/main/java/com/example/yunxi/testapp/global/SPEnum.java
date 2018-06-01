package com.example.yunxi.testapp.global;

public enum SPEnum {
    Token("token"),
    Username("username"),
    BaseInfoComplete("baseinfo_complete");

    private String key;
    SPEnum(String key) {
        this.key = key;
    }
    public String getKey(){
        return key;
    }
}
