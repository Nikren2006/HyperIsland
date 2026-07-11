package com.miui.circulate.device.api;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.MotionEventCompat;
import com.miui.circulate.device.api.Icon;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class DeviceInfo implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private final String accountId;
    private final BatteryInfo battery;
    private final String category;
    private final String deviceType;
    private final Icon icon;
    private final String id;
    private final String mac;
    private final Icon pinIcon;
    private final long pinTime;
    private final int priority;
    private final String privateData;
    private final String ssid;
    private final int state;
    private final String subtitle;
    private final String title;
    private final long updateTime;

    public static final class CREATOR implements Parcelable.Creator<DeviceInfo> {

        public static final class Versions {
            public static final Versions INSTANCE = new Versions();
            public static final int V1 = 1;

            private Versions() {
            }
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final DeviceInfo readPropertyFromParcel(Parcel parcel, int i2) {
            String string = parcel.readString();
            n.d(string);
            String string2 = parcel.readString();
            n.d(string2);
            String string3 = parcel.readString();
            n.d(string3);
            String string4 = parcel.readString();
            String string5 = parcel.readString();
            Icon.Companion companion = Icon.Companion;
            return new DeviceInfo(string, string2, string3, string4, string5, companion.parse(parcel.readString()), parcel.readInt(), parcel.readString(), parcel.readString(), BatteryInfo.Companion.parse(parcel.readString()), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readInt(), parcel.readLong(), companion.parse(parcel.readString()));
        }

        private CREATOR() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceInfo createFromParcel(Parcel parcel) {
            n.g(parcel, "parcel");
            int iDataPosition = parcel.dataPosition();
            int i2 = parcel.readInt();
            DeviceInfo propertyFromParcel = readPropertyFromParcel(parcel, parcel.readInt());
            parcel.setDataPosition(iDataPosition + i2);
            return propertyFromParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceInfo[] newArray(int i2) {
            return new DeviceInfo[i2];
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceInfo(String id, String category, String deviceType, String str, String str2, Icon icon, int i2) {
        this(id, category, deviceType, str, str2, icon, i2, null, null, null, null, null, 0L, 0, 0L, null, 65408, null);
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
    }

    public static /* synthetic */ void getAccountId$annotations() {
    }

    public static /* synthetic */ void getBattery$annotations() {
    }

    public static /* synthetic */ void getCategory$annotations() {
    }

    public static /* synthetic */ void getDeviceType$annotations() {
    }

    public static /* synthetic */ void getIcon$annotations() {
    }

    public static /* synthetic */ void getId$annotations() {
    }

    public static /* synthetic */ void getMac$annotations() {
    }

    public static /* synthetic */ void getPinIcon$annotations() {
    }

    public static /* synthetic */ void getPinTime$annotations() {
    }

    public static /* synthetic */ void getPriority$annotations() {
    }

    public static /* synthetic */ void getPrivateData$annotations() {
    }

    public static /* synthetic */ void getSsid$annotations() {
    }

    public static /* synthetic */ void getState$annotations() {
    }

    public static /* synthetic */ void getSubtitle$annotations() {
    }

    public static /* synthetic */ void getTitle$annotations() {
    }

    public static /* synthetic */ void getUpdateTime$annotations() {
    }

    private final void writePropertyToParcel(Parcel parcel) {
        String strFlat;
        String strFlat2;
        String strFlat3;
        parcel.writeString(this.id);
        parcel.writeString(this.category);
        parcel.writeString(this.deviceType);
        parcel.writeString(this.title);
        parcel.writeString(this.subtitle);
        Icon icon = this.icon;
        String str = "";
        if (icon == null || (strFlat = icon.flat()) == null) {
            strFlat = "";
        }
        parcel.writeString(strFlat);
        parcel.writeInt(this.state);
        parcel.writeString(this.accountId);
        parcel.writeString(this.mac);
        BatteryInfo batteryInfo = this.battery;
        if (batteryInfo == null || (strFlat2 = batteryInfo.flat()) == null) {
            strFlat2 = "";
        }
        parcel.writeString(strFlat2);
        parcel.writeString(this.ssid);
        parcel.writeString(this.privateData);
        parcel.writeLong(this.updateTime);
        parcel.writeInt(this.priority);
        parcel.writeLong(this.pinTime);
        Icon icon2 = this.pinIcon;
        if (icon2 != null && (strFlat3 = icon2.flat()) != null) {
            str = strFlat3;
        }
        parcel.writeString(str);
    }

    public final String component1() {
        return this.id;
    }

    public final BatteryInfo component10() {
        return this.battery;
    }

    public final String component11() {
        return this.ssid;
    }

    public final String component12() {
        return this.privateData;
    }

    public final long component13() {
        return this.updateTime;
    }

    public final int component14() {
        return this.priority;
    }

    public final long component15() {
        return this.pinTime;
    }

    public final Icon component16() {
        return this.pinIcon;
    }

    public final String component2() {
        return this.category;
    }

    public final String component3() {
        return this.deviceType;
    }

    public final String component4() {
        return this.title;
    }

    public final String component5() {
        return this.subtitle;
    }

    public final Icon component6() {
        return this.icon;
    }

    public final int component7() {
        return this.state;
    }

    public final String component8() {
        return this.accountId;
    }

    public final String component9() {
        return this.mac;
    }

    public final DeviceInfo copy(String id, String category, String deviceType, String str, String str2, Icon icon, int i2, String str3, String str4, BatteryInfo batteryInfo, String str5, String str6, long j2, int i3, long j3, Icon icon2) {
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
        return new DeviceInfo(id, category, deviceType, str, str2, icon, i2, str3, str4, batteryInfo, str5, str6, j2, i3, j3, icon2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceInfo)) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) obj;
        return n.c(this.id, deviceInfo.id) && n.c(this.category, deviceInfo.category) && n.c(this.deviceType, deviceInfo.deviceType) && n.c(this.title, deviceInfo.title) && n.c(this.subtitle, deviceInfo.subtitle) && n.c(this.icon, deviceInfo.icon) && this.state == deviceInfo.state && n.c(this.accountId, deviceInfo.accountId) && n.c(this.mac, deviceInfo.mac) && n.c(this.battery, deviceInfo.battery) && n.c(this.ssid, deviceInfo.ssid) && n.c(this.privateData, deviceInfo.privateData) && this.updateTime == deviceInfo.updateTime && this.priority == deviceInfo.priority && this.pinTime == deviceInfo.pinTime && n.c(this.pinIcon, deviceInfo.pinIcon);
    }

    public final String getAccountId() {
        return this.accountId;
    }

    public final BatteryInfo getBattery() {
        return this.battery;
    }

    public final String getCategory() {
        return this.category;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final Icon getIcon() {
        return this.icon;
    }

    public final String getId() {
        return this.id;
    }

    public final String getMac() {
        return this.mac;
    }

    public final Icon getPinIcon() {
        return this.pinIcon;
    }

    public final long getPinTime() {
        return this.pinTime;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final String getPrivateData() {
        return this.privateData;
    }

    public final String getSsid() {
        return this.ssid;
    }

    public final int getState() {
        return this.state;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final String getTitle() {
        return this.title;
    }

    public final long getUpdateTime() {
        return this.updateTime;
    }

    public int hashCode() {
        int iHashCode = ((((this.id.hashCode() * 31) + this.category.hashCode()) * 31) + this.deviceType.hashCode()) * 31;
        String str = this.title;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.subtitle;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Icon icon = this.icon;
        int iHashCode4 = (((iHashCode3 + (icon == null ? 0 : icon.hashCode())) * 31) + Integer.hashCode(this.state)) * 31;
        String str3 = this.accountId;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.mac;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        BatteryInfo batteryInfo = this.battery;
        int iHashCode7 = (iHashCode6 + (batteryInfo == null ? 0 : batteryInfo.hashCode())) * 31;
        String str5 = this.ssid;
        int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.privateData;
        int iHashCode9 = (((((((iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31) + Long.hashCode(this.updateTime)) * 31) + Integer.hashCode(this.priority)) * 31) + Long.hashCode(this.pinTime)) * 31;
        Icon icon2 = this.pinIcon;
        return iHashCode9 + (icon2 != null ? icon2.hashCode() : 0);
    }

    public final boolean isPin() {
        return this.pinTime > 0;
    }

    public String toString() {
        return "DeviceInfo(id=" + this.id + ", category=" + this.category + ", deviceType=" + this.deviceType + ", title=" + this.title + ", subtitle=" + this.subtitle + ", icon=" + this.icon + ", state=" + this.state + ", accountId=" + this.accountId + ", mac=" + this.mac + ", battery=" + this.battery + ", ssid=" + this.ssid + ", privateData=" + this.privateData + ", updateTime=" + this.updateTime + ", priority=" + this.priority + ", pinTime=" + this.pinTime + ", pinIcon=" + this.pinIcon + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        n.g(parcel, "parcel");
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(1);
        writePropertyToParcel(parcel);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceInfo(String id, String category, String deviceType, String str, String str2, Icon icon, int i2, String str3) {
        this(id, category, deviceType, str, str2, icon, i2, str3, null, null, null, null, 0L, 0, 0L, null, MotionEventCompat.ACTION_POINTER_INDEX_MASK, null);
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceInfo(String id, String category, String deviceType, String str, String str2, Icon icon, int i2, String str3, String str4) {
        this(id, category, deviceType, str, str2, icon, i2, str3, str4, null, null, null, 0L, 0, 0L, null, 65024, null);
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceInfo(String id, String category, String deviceType, String str, String str2, Icon icon, int i2, String str3, String str4, BatteryInfo batteryInfo) {
        this(id, category, deviceType, str, str2, icon, i2, str3, str4, batteryInfo, null, null, 0L, 0, 0L, null, 64512, null);
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceInfo(String id, String category, String deviceType, String str, String str2, Icon icon, int i2, String str3, String str4, BatteryInfo batteryInfo, String str5) {
        this(id, category, deviceType, str, str2, icon, i2, str3, str4, batteryInfo, str5, null, 0L, 0, 0L, null, 63488, null);
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceInfo(String id, String category, String deviceType, String str, String str2, Icon icon, int i2, String str3, String str4, BatteryInfo batteryInfo, String str5, String str6) {
        this(id, category, deviceType, str, str2, icon, i2, str3, str4, batteryInfo, str5, str6, 0L, 0, 0L, null, 61440, null);
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceInfo(String id, String category, String deviceType, String str, String str2, Icon icon, int i2, String str3, String str4, BatteryInfo batteryInfo, String str5, String str6, long j2) {
        this(id, category, deviceType, str, str2, icon, i2, str3, str4, batteryInfo, str5, str6, j2, 0, 0L, null, 57344, null);
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceInfo(String id, String category, String deviceType, String str, String str2, Icon icon, int i2, String str3, String str4, BatteryInfo batteryInfo, String str5, String str6, long j2, int i3) {
        this(id, category, deviceType, str, str2, icon, i2, str3, str4, batteryInfo, str5, str6, j2, i3, 0L, null, 49152, null);
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceInfo(String id, String category, String deviceType, String str, String str2, Icon icon, int i2, String str3, String str4, BatteryInfo batteryInfo, String str5, String str6, long j2, int i3, long j3) {
        this(id, category, deviceType, str, str2, icon, i2, str3, str4, batteryInfo, str5, str6, j2, i3, j3, null, 32768, null);
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
    }

    public DeviceInfo(String id, String category, String deviceType, String str, String str2, Icon icon, int i2, String str3, String str4, BatteryInfo batteryInfo, String str5, String str6, long j2, int i3, long j3, Icon icon2) {
        n.g(id, "id");
        n.g(category, "category");
        n.g(deviceType, "deviceType");
        this.id = id;
        this.category = category;
        this.deviceType = deviceType;
        this.title = str;
        this.subtitle = str2;
        this.icon = icon;
        this.state = i2;
        this.accountId = str3;
        this.mac = str4;
        this.battery = batteryInfo;
        this.ssid = str5;
        this.privateData = str6;
        this.updateTime = j2;
        this.priority = i3;
        this.pinTime = j3;
        this.pinIcon = icon2;
    }

    public static final class Builder {
        private String accountId;
        private BatteryInfo battery;
        private String category;
        private String deviceType;
        private Icon icon;
        private String id;
        private String mac;
        private String privateData;
        private final JSONObject privateDataJson;
        private String ssid;
        private int state;
        private String subtitle;
        private String title;

        public static final class PrivateDataKey {
            public static final String BLE_GLASSES_TYPE = "bleGlassesType";
            public static final String BLUETOOTH_MAC = "bluetoothMac";
            public static final String BLUETOOTH_MAC_HASH = "btHash";
            public static final String CAN_ALONE_PLAY_CTRL = "canAlonePlayCtrl";
            public static final String COMPOSE_NUMBER = "device_compose_number";
            public static final PrivateDataKey INSTANCE = new PrivateDataKey();
            public static final String IP = "ip";
            public static final String MODEL = "model";
            public static final String P2PMAC = "p2pMac";
            public static final String PROTOCOL_TYPE = "protocolType";
            public static final String SAME_ACCOUNT = "sameAccount";

            private PrivateDataKey() {
            }
        }

        public Builder(DeviceInfo deviceInfo) {
            this.privateDataJson = new JSONObject();
            if (deviceInfo != null) {
                this.id = deviceInfo.getId();
            }
            if (deviceInfo != null) {
                this.category = deviceInfo.getCategory();
            }
            if (deviceInfo != null) {
                this.deviceType = deviceInfo.getDeviceType();
            }
            this.title = deviceInfo != null ? deviceInfo.getTitle() : null;
            this.subtitle = deviceInfo != null ? deviceInfo.getSubtitle() : null;
            this.icon = deviceInfo != null ? deviceInfo.getIcon() : null;
            this.state = deviceInfo != null ? deviceInfo.getState() : 0;
            this.mac = deviceInfo != null ? deviceInfo.getMac() : null;
            this.ssid = deviceInfo != null ? deviceInfo.getSsid() : null;
            this.battery = deviceInfo != null ? deviceInfo.getBattery() : null;
            this.accountId = deviceInfo != null ? deviceInfo.getAccountId() : null;
            this.privateData = deviceInfo != null ? deviceInfo.getPrivateData() : null;
        }

        public final DeviceInfo build() {
            String str;
            String str2;
            String str3;
            String str4;
            if (this.privateDataJson.length() > 0 && (str4 = this.privateData) != null && str4.length() != 0) {
                throw new IllegalArgumentException("do NOT use 'putPrivateData(key, value)' and 'setPrivateData(value)' at same time");
            }
            if (this.privateDataJson.length() > 0) {
                this.privateData = this.privateDataJson.toString();
            }
            String str5 = this.id;
            if (str5 == null) {
                n.w(Column.ID);
                str = null;
            } else {
                str = str5;
            }
            String str6 = this.category;
            if (str6 == null) {
                n.w("category");
                str2 = null;
            } else {
                str2 = str6;
            }
            String str7 = this.deviceType;
            if (str7 == null) {
                n.w("deviceType");
                str3 = null;
            } else {
                str3 = str7;
            }
            return new DeviceInfo(str, str2, str3, this.title, this.subtitle, this.icon, this.state, this.accountId, this.mac, this.battery, this.ssid, this.privateData, 0L, 0, 0L, null, 61440, null);
        }

        public final Builder putPrivateData(String key, Object value) throws JSONException {
            n.g(key, "key");
            n.g(value, "value");
            this.privateDataJson.put(key, value);
            return this;
        }

        public final Builder setAccountId(String str) {
            this.accountId = str;
            return this;
        }

        public final Builder setBattery(BatteryInfo batteryInfo) {
            this.battery = batteryInfo;
            return this;
        }

        public final Builder setCategory(String category) {
            n.g(category, "category");
            this.category = category;
            return this;
        }

        public final Builder setDeviceType(String deviceType) {
            n.g(deviceType, "deviceType");
            this.deviceType = deviceType;
            return this;
        }

        public final Builder setIcon(Icon icon) {
            this.icon = icon;
            return this;
        }

        public final Builder setId(String id) {
            n.g(id, "id");
            this.id = id;
            return this;
        }

        public final Builder setMac(String str) {
            this.mac = str;
            return this;
        }

        public final Builder setPrivateData(String str) {
            this.privateData = str;
            return this;
        }

        public final Builder setSSID(String str) {
            this.ssid = str;
            return this;
        }

        public final Builder setState(int i2) {
            this.state = i2;
            return this;
        }

        public final Builder setSubtitle(String str) {
            this.subtitle = str;
            return this;
        }

        public final Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder() {
            this(null);
        }
    }

    public /* synthetic */ DeviceInfo(String str, String str2, String str3, String str4, String str5, Icon icon, int i2, String str6, String str7, BatteryInfo batteryInfo, String str8, String str9, long j2, int i3, long j3, Icon icon2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, icon, i2, (i4 & 128) != 0 ? null : str6, (i4 & 256) != 0 ? null : str7, (i4 & 512) != 0 ? null : batteryInfo, (i4 & 1024) != 0 ? null : str8, (i4 & 2048) != 0 ? null : str9, (i4 & 4096) != 0 ? 0L : j2, (i4 & 8192) != 0 ? 0 : i3, (i4 & 16384) != 0 ? 0L : j3, (i4 & 32768) != 0 ? null : icon2);
    }
}
