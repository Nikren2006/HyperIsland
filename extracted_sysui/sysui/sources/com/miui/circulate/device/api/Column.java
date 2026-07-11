package com.miui.circulate.device.api;

/* JADX INFO: loaded from: classes2.dex */
public final class Column {
    public static final String CATEGORY = "category";
    public static final String DEVICE_TYPE = "deviceType";
    public static final String PRIORITY = "priority";
    public static final String STATE = "state";
    public static final String SUBTITLE = "subtitle";
    public static final String TITLE = "title";
    public static final Column INSTANCE = new Column();
    public static final String ID = "id";
    public static final String ICON = "icon";
    public static final String MAC = "mac";
    public static final String SSID = "ssid";
    public static final String BATTERY = "battery";
    public static final String ACCOUNT_ID = "accountId";
    public static final String PRIVATE_DATA = "privateData";
    public static final String UPDATE_TIME = "updateTime";
    public static final String PIN_TIME = "pinTime";
    public static final String PIN_ICON = "pinIcon";
    private static final String[] ALL = {ID, "category", "deviceType", "title", "subtitle", ICON, "state", MAC, SSID, BATTERY, ACCOUNT_ID, PRIVATE_DATA, UPDATE_TIME, PIN_TIME, PIN_ICON, "priority"};

    private Column() {
    }

    public final String[] getALL() {
        return ALL;
    }
}
