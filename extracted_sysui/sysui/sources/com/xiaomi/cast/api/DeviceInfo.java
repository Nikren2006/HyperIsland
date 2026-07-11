package com.xiaomi.cast.api;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() { // from class: com.xiaomi.cast.api.DeviceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceInfo createFromParcel(Parcel parcel) {
            return new DeviceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceInfo[] newArray(int i2) {
            return new DeviceInfo[i2];
        }
    };
    public static final int DEVICE_STATE_CONNECTED = 1;
    public static final int DEVICE_STATE_CONNECTING = 3;
    public static final int DEVICE_STATE_DISCONNECTED = 0;
    public static final int DEVICE_STATE_NONE = 2;
    public static final int DEVICE_TYPE_BLUETOOTH = 3;
    public static final int DEVICE_TYPE_GROUP = 1000;
    public static final int DEVICE_TYPE_LOCAL = -1;
    public static final int DEVICE_TYPE_SPEAKER = 2;
    public static final int DEVICE_TYPE_TV = 1;
    public static final int DEVICE_TYPE_UNKNOWN = 0;
    private boolean mCanVolumeChange;
    private int mConnectState;
    private Bundle mExtra;
    private String mId;
    private String mName;
    private int mType;
    private int mVolume;
    private int mVolumeMax;

    public DeviceInfo() {
        this.mConnectState = 2;
    }

    public boolean canVolumeChange() {
        return this.mCanVolumeChange;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getConnectState() {
        return this.mConnectState;
    }

    public Bundle getExtra() {
        return this.mExtra;
    }

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public int getType() {
        return this.mType;
    }

    public int getVolume() {
        return this.mVolume;
    }

    public int getVolumeMax() {
        return this.mVolumeMax;
    }

    public void setConnectState(int i2) {
        this.mConnectState = i2;
    }

    public void setExtra(Bundle bundle) {
        this.mExtra = bundle;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setType(int i2) {
        this.mType = i2;
    }

    public void setVolume(int i2) {
        this.mVolume = i2;
    }

    public void setVolumeHandling(boolean z2) {
        this.mCanVolumeChange = z2;
    }

    public void setVolumeMax(int i2) {
        this.mVolumeMax = i2;
    }

    public String toString() {
        return "DeviceInfo{mId='" + this.mId + "', mName='" + this.mName + "', mType=" + this.mType + ", mConnectState=" + this.mConnectState + ", mCanVolumeChange=" + this.mCanVolumeChange + ", mVolume=" + this.mVolume + ", mVolumeMax=" + this.mVolumeMax + ", mExtra=" + this.mExtra + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mId);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mType);
        parcel.writeInt(this.mConnectState);
        parcel.writeInt(this.mVolume);
        parcel.writeInt(this.mVolumeMax);
        parcel.writeInt(this.mCanVolumeChange ? 1 : 0);
        parcel.writeBundle(this.mExtra);
    }

    public DeviceInfo(Parcel parcel) {
        this.mConnectState = 2;
        this.mId = parcel.readString();
        this.mName = parcel.readString();
        this.mType = parcel.readInt();
        this.mConnectState = parcel.readInt();
        this.mVolume = parcel.readInt();
        this.mVolumeMax = parcel.readInt();
        this.mCanVolumeChange = parcel.readInt() == 1;
        this.mExtra = parcel.readBundle(getClass().getClassLoader());
    }
}
