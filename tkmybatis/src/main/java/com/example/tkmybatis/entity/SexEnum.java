package com.example.tkmybatis.entity;

public enum SexEnum {
    MAN("M","男"),
    WOMAN("F","女")
    ;
    public final String code;
    public final String msg;

    SexEnum(String respCode, String respMsg) {
        this.code = respCode;
        this.msg = respMsg;
    }
    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
