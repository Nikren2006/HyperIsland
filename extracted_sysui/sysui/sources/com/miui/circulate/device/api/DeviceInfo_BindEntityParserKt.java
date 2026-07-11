package com.miui.circulate.device.api;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import com.miui.circulate.device.api.Icon;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class DeviceInfo_BindEntityParserKt {
    public static final ContentValues convertToContentValues(DeviceInfo deviceInfo) {
        String strFlat;
        String strFlat2;
        String strFlat3;
        n.g(deviceInfo, "<this>");
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column.ID, deviceInfo.getId());
        contentValues.put("category", deviceInfo.getCategory());
        contentValues.put("deviceType", deviceInfo.getDeviceType());
        String title = deviceInfo.getTitle();
        String str = "";
        if (title == null) {
            title = "";
        }
        contentValues.put("title", title);
        String subtitle = deviceInfo.getSubtitle();
        if (subtitle == null) {
            subtitle = "";
        }
        contentValues.put("subtitle", subtitle);
        Icon icon = deviceInfo.getIcon();
        if (icon == null || (strFlat = icon.flat()) == null) {
            strFlat = "";
        }
        contentValues.put(Column.ICON, strFlat);
        contentValues.put("state", Integer.valueOf(deviceInfo.getState()));
        String accountId = deviceInfo.getAccountId();
        if (accountId == null) {
            accountId = "";
        }
        contentValues.put(Column.ACCOUNT_ID, accountId);
        String mac = deviceInfo.getMac();
        if (mac == null) {
            mac = "";
        }
        contentValues.put(Column.MAC, mac);
        BatteryInfo battery = deviceInfo.getBattery();
        if (battery == null || (strFlat2 = battery.flat()) == null) {
            strFlat2 = "";
        }
        contentValues.put(Column.BATTERY, strFlat2);
        String ssid = deviceInfo.getSsid();
        if (ssid == null) {
            ssid = "";
        }
        contentValues.put(Column.SSID, ssid);
        String privateData = deviceInfo.getPrivateData();
        if (privateData == null) {
            privateData = "";
        }
        contentValues.put(Column.PRIVATE_DATA, privateData);
        contentValues.put(Column.UPDATE_TIME, Long.valueOf(deviceInfo.getUpdateTime()));
        contentValues.put("priority", Integer.valueOf(deviceInfo.getPriority()));
        contentValues.put(Column.PIN_TIME, Long.valueOf(deviceInfo.getPinTime()));
        Icon pinIcon = deviceInfo.getPinIcon();
        if (pinIcon != null && (strFlat3 = pinIcon.flat()) != null) {
            str = strFlat3;
        }
        contentValues.put(Column.PIN_ICON, str);
        return contentValues;
    }

    public static final DeviceInfo parseDeviceInfo(Cursor c2) {
        String string;
        String str;
        n.g(c2, "c");
        try {
            int columnIndex = c2.getColumnIndex(Column.ID);
            String id = columnIndex >= 0 ? c2.getString(columnIndex) : "";
            int columnIndex2 = c2.getColumnIndex("category");
            String category = columnIndex2 >= 0 ? c2.getString(columnIndex2) : "";
            int columnIndex3 = c2.getColumnIndex("deviceType");
            String deviceType = columnIndex3 >= 0 ? c2.getString(columnIndex3) : "";
            int columnIndex4 = c2.getColumnIndex("title");
            String string2 = columnIndex4 >= 0 ? c2.getString(columnIndex4) : "";
            int columnIndex5 = c2.getColumnIndex("subtitle");
            String string3 = columnIndex5 >= 0 ? c2.getString(columnIndex5) : "";
            int columnIndex6 = c2.getColumnIndex(Column.ICON);
            String string4 = columnIndex6 >= 0 ? c2.getString(columnIndex6) : "";
            Icon.Companion companion = Icon.Companion;
            Icon icon = companion.parse(string4);
            int columnIndex7 = c2.getColumnIndex("state");
            int i2 = columnIndex7 >= 0 ? c2.getInt(columnIndex7) : 0;
            int columnIndex8 = c2.getColumnIndex(Column.ACCOUNT_ID);
            String string5 = columnIndex8 >= 0 ? c2.getString(columnIndex8) : "";
            int columnIndex9 = c2.getColumnIndex(Column.MAC);
            String string6 = columnIndex9 >= 0 ? c2.getString(columnIndex9) : "";
            int columnIndex10 = c2.getColumnIndex(Column.BATTERY);
            if (columnIndex10 >= 0) {
                string = c2.getString(columnIndex10);
                str = "";
            } else {
                string = "";
                str = string;
            }
            BatteryInfo batteryInfo = BatteryInfo.Companion.parse(string);
            int columnIndex11 = c2.getColumnIndex(Column.SSID);
            String string7 = columnIndex11 >= 0 ? c2.getString(columnIndex11) : str;
            int columnIndex12 = c2.getColumnIndex(Column.PRIVATE_DATA);
            String string8 = columnIndex12 >= 0 ? c2.getString(columnIndex12) : str;
            int columnIndex13 = c2.getColumnIndex(Column.UPDATE_TIME);
            long j2 = columnIndex13 >= 0 ? c2.getLong(columnIndex13) : 0L;
            int columnIndex14 = c2.getColumnIndex("priority");
            int i3 = columnIndex14 >= 0 ? c2.getInt(columnIndex14) : 0;
            int columnIndex15 = c2.getColumnIndex(Column.PIN_TIME);
            long j3 = columnIndex15 >= 0 ? c2.getLong(columnIndex15) : 0L;
            int columnIndex16 = c2.getColumnIndex(Column.PIN_ICON);
            Icon icon2 = companion.parse(columnIndex16 >= 0 ? c2.getString(columnIndex16) : str);
            n.f(id, "id");
            n.f(category, "category");
            n.f(deviceType, "deviceType");
            return new DeviceInfo(id, category, deviceType, string2, string3, icon, i2, string5, string6, batteryInfo, string7, string8, j2, i3, j3, icon2);
        } catch (Exception e2) {
            Log.e("parseDeviceInfo", "parse", e2);
            return null;
        }
    }
}
