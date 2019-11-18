package com.chinaums.commons;

/**
 * @author: lihw
 * @aate: 2018/12/25 12:32
 * @description:
 */
public enum AppIdEnum {

    APPROVAL_MANAGER("approval-manager", "综合审批系统"),
    AUTHCAS_MANAGER("authcas-manager", "统一授权"),
    FACTORING_MANAGER("factoring-manager", "保理系统"),

    ;

    private String appId;

    private String appName;

    AppIdEnum(String appId, String appName) {
        this.appId = appId;
        this.appName = appName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
