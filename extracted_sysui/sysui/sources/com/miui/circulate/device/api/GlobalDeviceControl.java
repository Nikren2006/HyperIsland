package com.miui.circulate.device.api;

import H0.s;
import I0.m;
import S0.a;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.miui.circulate.device.api.Constant;
import f1.q;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class GlobalDeviceControl {
    public static final GlobalDeviceControl INSTANCE = new GlobalDeviceControl();

    private GlobalDeviceControl() {
    }

    public static final boolean addOrUpdateDevice(ContentResolver resolver, DeviceInfo device) {
        Uri uriInsert;
        n.g(resolver, "resolver");
        n.g(device, "device");
        Log.i(Constant.TAG, "addOrUpdate: " + q.g0(device.getId(), 3) + ", " + device.getTitle() + ", " + device.getState());
        try {
            uriInsert = resolver.insert(Constant.INSTANCE.getDEVICE_META_LIST_URI(), DeviceInfo_BindEntityParserKt.convertToContentValues(device));
        } catch (Exception e2) {
            Log.e(Constant.TAG, "addDevice", e2);
            uriInsert = null;
        }
        return uriInsert != null;
    }

    public static final int atomicInsertDeviceByCategory(ContentResolver resolver, String category, List<DeviceInfo> deviceList) {
        n.g(resolver, "resolver");
        n.g(category, "category");
        n.g(deviceList, "deviceList");
        int size = deviceList.size();
        ContentValues[] contentValuesArr = new ContentValues[size];
        int i2 = 0;
        for (Object obj : deviceList) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                m.n();
            }
            contentValuesArr[i2] = DeviceInfo_BindEntityParserKt.convertToContentValues((DeviceInfo) obj);
            i2 = i3;
        }
        try {
            Uri uriBuild = Constant.INSTANCE.getCATEGORY_DEVICE_LIST_URI().buildUpon().appendPath(category).build();
            Log.i(Constant.TAG, "atomicInsertDeviceByCategory, " + category + ", " + size);
            return resolver.bulkInsert(uriBuild, contentValuesArr);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "bulkInsert", e2);
            return 0;
        }
    }

    public static final boolean deleteDevice(ContentResolver resolver, String id) {
        n.g(resolver, "resolver");
        n.g(id, "id");
        Log.i(Constant.TAG, "deleteDevice, " + q.g0(id, 3));
        try {
            return resolver.delete(UriBuilder.INSTANCE.withAppendedPath(Constant.INSTANCE.getDEVICE_META_LIST_URI(), id), null, null) > 0;
        } catch (Exception e2) {
            Log.e(Constant.TAG, "unpin device", e2);
            return false;
        }
    }

    public static final boolean getBoolean(ContentResolver resolver, String key) {
        n.g(resolver, "resolver");
        n.g(key, "key");
        return Boolean.parseBoolean(getString(resolver, key));
    }

    public static final DeviceInfo getDeviceById(ContentResolver resolver, String id) throws IllegalAccessException, IOException, InvocationTargetException {
        Cursor cursorQuery;
        n.g(resolver, "resolver");
        n.g(id, "id");
        try {
            cursorQuery = resolver.query(Uri.withAppendedPath(Constant.INSTANCE.getDEVICE_META_LIST_URI(), id), null, null, null, null);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "getPinDeviceList", e2);
            cursorQuery = null;
        }
        if (cursorQuery == null) {
            return null;
        }
        try {
            cursorQuery.moveToFirst();
            DeviceInfo deviceInfo = DeviceInfo_BindEntityParserKt.parseDeviceInfo(cursorQuery);
            a.a(cursorQuery, null);
            return deviceInfo;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                a.a(cursorQuery, th);
                throw th2;
            }
        }
    }

    public static final List<DeviceInfo> getDeviceListByUri(ContentResolver resolver, Uri uri) throws IllegalAccessException, IOException, InvocationTargetException {
        Cursor cursorQuery;
        n.g(resolver, "resolver");
        n.g(uri, "uri");
        try {
            cursorQuery = resolver.query(uri, null, null, null, null);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "getPinDeviceList", e2);
            cursorQuery = null;
        }
        ArrayList arrayList = new ArrayList();
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                try {
                    DeviceInfo deviceInfo = DeviceInfo_BindEntityParserKt.parseDeviceInfo(cursorQuery);
                    if (deviceInfo != null) {
                        arrayList.add(deviceInfo);
                    }
                } finally {
                }
            }
            s sVar = s.f314a;
            a.a(cursorQuery, null);
        }
        return arrayList;
    }

    public static final double getDouble(ContentResolver resolver, String key) {
        n.g(resolver, "resolver");
        n.g(key, "key");
        return Double.parseDouble(getString(resolver, key));
    }

    public static final int getInt(ContentResolver resolver, String key) {
        n.g(resolver, "resolver");
        n.g(key, "key");
        return Integer.parseInt(getString(resolver, key));
    }

    public static final long getLong(ContentResolver resolver, String key) {
        n.g(resolver, "resolver");
        n.g(key, "key");
        return Long.parseLong(getString(resolver, key));
    }

    public static final String getString(ContentResolver resolver, String key) throws IllegalAccessException, IOException, InvocationTargetException {
        Cursor cursorQuery;
        n.g(resolver, "resolver");
        n.g(key, "key");
        try {
            cursorQuery = resolver.query(Uri.withAppendedPath(Constant.INSTANCE.getKEY_VALUE_URI(), key), null, null, null, null);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "getString", e2);
            cursorQuery = null;
        }
        String str = "";
        if (cursorQuery != null) {
            try {
                cursorQuery.moveToFirst();
                int columnIndex = cursorQuery.getColumnIndex(Constant.KeyValue.KEY_COLUMN);
                int columnIndex2 = cursorQuery.getColumnIndex(Constant.KeyValue.VALUE_COLUMN);
                if (columnIndex >= 0 && columnIndex2 >= 0) {
                    String string = cursorQuery.getString(columnIndex);
                    String v2 = cursorQuery.getString(columnIndex2);
                    if (n.c(string, key)) {
                        n.f(v2, "v");
                        str = v2;
                    }
                }
                s sVar = s.f314a;
                a.a(cursorQuery, null);
            } finally {
            }
        }
        return str;
    }

    public static final boolean pin(ContentResolver resolver, String id, Icon icon) {
        Uri uriInsert;
        n.g(resolver, "resolver");
        n.g(id, "id");
        n.g(icon, "icon");
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column.ID, id);
        contentValues.put(Column.PIN_ICON, icon.flat());
        try {
            uriInsert = resolver.insert(Constant.INSTANCE.getPIN_URI(), contentValues);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "pin device", e2);
            uriInsert = null;
        }
        return uriInsert != null;
    }

    private final String printExtraValues(ContentValues contentValues) {
        StringBuilder sb = new StringBuilder();
        if (contentValues != null) {
            String asString = contentValues.getAsString("deviceType");
            String asString2 = contentValues.getAsString(Column.MAC);
            if (asString != null) {
                sb.append("deviceType: " + asString + ',');
            }
            if (asString2 != null) {
                sb.append("mac: " + q.h0(asString2, 4) + ',');
            }
        }
        String string = sb.toString();
        n.f(string, "extraHolder.toString()");
        return string;
    }

    public static final void setBoolean(ContentResolver resolver, String key, boolean z2) {
        n.g(resolver, "resolver");
        n.g(key, "key");
        setString(resolver, key, String.valueOf(z2));
    }

    public static final void setDouble(ContentResolver resolver, String key, double d2) {
        n.g(resolver, "resolver");
        n.g(key, "key");
        setString(resolver, key, String.valueOf(d2));
    }

    public static final void setInt(ContentResolver resolver, String key, int i2) {
        n.g(resolver, "resolver");
        n.g(key, "key");
        setString(resolver, key, String.valueOf(i2));
    }

    public static final void setLong(ContentResolver resolver, String key, long j2) {
        n.g(resolver, "resolver");
        n.g(key, "key");
        setString(resolver, key, String.valueOf(j2));
    }

    public static final void setString(ContentResolver resolver, String key, String value) {
        n.g(resolver, "resolver");
        n.g(key, "key");
        n.g(value, "value");
        if (value.length() > 5000) {
            throw new IllegalArgumentException("length of this value is greater than 5000");
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.KeyValue.KEY_COLUMN, key);
        contentValues.put(Constant.KeyValue.VALUE_COLUMN, value);
        try {
            resolver.insert(Constant.INSTANCE.getKEY_VALUE_URI(), contentValues);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "setString", e2);
        }
    }

    public static final boolean unpin(ContentResolver resolver, String id) {
        n.g(resolver, "resolver");
        n.g(id, "id");
        try {
            return resolver.delete(Uri.withAppendedPath(Constant.INSTANCE.getPIN_URI(), id), null, null) > 0;
        } catch (Exception e2) {
            Log.e(Constant.TAG, "unpin device", e2);
            return false;
        }
    }

    public static final boolean updateIconById(ContentResolver resolver, String id, String icon) {
        int iUpdate;
        n.g(resolver, "resolver");
        n.g(id, "id");
        n.g(icon, "icon");
        Log.i(Constant.TAG, "updateIconById: " + q.g0(id, 3) + ", " + icon);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column.ICON, icon);
        try {
            iUpdate = resolver.update(Constant.INSTANCE.getDEVICE_META_LIST_URI().buildUpon().appendPath(id).appendQueryParameter(Constant.QueryParameter.UPDATE_TYPE, Constant.UpdateType.UPDATE_NORMAL).build(), contentValues, null, null);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "updateIconById", e2);
            iUpdate = 0;
        }
        return iUpdate > 0;
    }

    public static final boolean updateStateById(ContentResolver resolver, String id, @Constant.StateMask int i2, boolean z2) {
        int iUpdate;
        n.g(resolver, "resolver");
        n.g(id, "id");
        Log.i(Constant.TAG, "updateStateById: " + q.g0(id, 3) + ", " + i2 + ", " + z2);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.State.STATE_MASK_KEY, Integer.valueOf(i2));
        contentValues.put("state", Boolean.valueOf(z2));
        try {
            iUpdate = resolver.update(Constant.INSTANCE.getDEVICE_META_LIST_URI().buildUpon().appendPath(id).appendQueryParameter(Constant.QueryParameter.UPDATE_TYPE, Constant.UpdateType.UPDATE_STATE).build(), contentValues, null, null);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "updateStateById", e2);
            iUpdate = 0;
        }
        return iUpdate > 0;
    }

    public static final boolean updateStateById(ContentResolver resolver, String id, String deviceType, @Constant.StateMask int i2, boolean z2) {
        int iUpdate;
        n.g(resolver, "resolver");
        n.g(id, "id");
        n.g(deviceType, "deviceType");
        Log.i(Constant.TAG, "updateStateById: " + q.g0(id, 3) + ", " + deviceType + ", " + i2 + ", " + z2);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.State.STATE_MASK_KEY, Integer.valueOf(i2));
        contentValues.put("state", Boolean.valueOf(z2));
        contentValues.put("deviceType", deviceType);
        try {
            iUpdate = resolver.update(Constant.INSTANCE.getDEVICE_META_LIST_URI().buildUpon().appendPath(id).appendQueryParameter(Constant.QueryParameter.UPDATE_TYPE, Constant.UpdateType.UPDATE_STATE).build(), contentValues, null, null);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "updateStateById", e2);
            iUpdate = 0;
        }
        return iUpdate > 0;
    }

    public static final boolean updateStateById(ContentResolver resolver, String id, @Constant.StateMask int i2, boolean z2, ContentValues contentValues) {
        int iUpdate;
        n.g(resolver, "resolver");
        n.g(id, "id");
        Log.i(Constant.TAG, "updateStateById: " + q.g0(id, 3) + ", " + INSTANCE.printExtraValues(contentValues) + ", " + i2 + ", " + z2);
        ContentValues contentValues2 = new ContentValues();
        if (contentValues != null) {
            contentValues2.putAll(contentValues);
        }
        contentValues2.put(Constant.State.STATE_MASK_KEY, Integer.valueOf(i2));
        contentValues2.put("state", Boolean.valueOf(z2));
        try {
            iUpdate = resolver.update(Constant.INSTANCE.getDEVICE_META_LIST_URI().buildUpon().appendPath(id).appendQueryParameter(Constant.QueryParameter.UPDATE_TYPE, Constant.UpdateType.UPDATE_STATE).build(), contentValues2, null, null);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "updateStateById", e2);
            iUpdate = 0;
        }
        return iUpdate > 0;
    }
}
