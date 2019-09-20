package com.game.majiang.constants;

/**
 * Created by len on 2016/12/16.
 * 页面跳转
 */
public enum PageEnum {

    LOGIN("login"),
    INDEX("index"),
    PRODUCT_LIST("product_list"),
    MENU_TOP("menu_top"),
    MANAGER_LIST("manager_list"),
    USER_LIST("user_list"),
    RECHARGE_LIST("recharge_list"),
    PRODUCT_EDIT("product_edit"),
    MANAGER_CREATE("manager_create"),
    MANAGER_BALANCE_LIST("manager_balance_list"),
    CONFIG_EDIT("config_edit"),
    SYSCONFIG_BATCH("sysconfig_batch"),
    UPDATE_PASSWORD("updte_password"),
    PAGE_CONSUMPTION("consumption");

    private String page;

    PageEnum(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
