package com.miui.miplay.audio.data;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaomi.onetrack.util.aa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceInfo implements Parcelable {
    public static final String AUDIO_SUPPORT = "audio";
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() { // from class: com.miui.miplay.audio.data.DeviceInfo.1
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
    public static final String EXTRA_IS_AUDIO_SHARE = "isAudioShare";
    public static final String EXTRA_KEY_BLE_DEVICE_TYPE = "ble_device_type";
    public static final String EXTRA_KEY_BLUETOOTH_CLASS = "BluetoothClass";
    public static final String EXTRA_KEY_BLUETOOTH_DEVICE_MODEL = "ble_device_model";
    public static final String EXTRA_KEY_BLUETOOTH_MAC = "bluetoothMac";
    public static final String EXTRA_KEY_DEVICE_MAC = "device_mac";
    public static final String EXTRA_KEY_DEVICE_SUPPORT_MP_ABILITY = "supportMpAbility";
    public static final String EXTRA_KEY_GROUP_ID = "groupId";
    public static final String EXTRA_KEY_IDHASH = "idhash";
    public static final String EXTRA_KEY_IP = "ip";
    public static final String EXTRA_KEY_IS_BLUETOOTH_GLASSES = "isGlasses";
    public static final String EXTRA_KEY_IS_BLUETOOTH_HEADSET = "isHeadset";
    public static final String EXTRA_KEY_IS_CACHE_FOUND = "is_cache_found";
    public static final String EXTRA_KEY_IS_GROUP_DEVICE = "isGroupDevice";
    public static final String EXTRA_KEY_IS_MIUI_PLUS_CAR = "miui_plus_car";
    public static final String EXTRA_KEY_IS_XIAOMI_CAR = "isMiCar";
    public static final String EXTRA_KEY_LYRA_ID = "lrayid";
    public static final String EXTRA_KEY_MI_ACCOUNT_ID = "miAccountId";
    public static final String EXTRA_KEY_MI_PLAY_DEVICE_TYPE = "miPlayDeviceType";
    public static final String EXTRA_KEY_PEER_MODEL = "peer_model";
    public static final String EXTRA_KEY_PEER_ROM_VERSION = "peer_rom_version";
    public static final String EXTRA_KEY_ROOM_NAME = "roomName";
    public static final String EXTRA_KEY_STEREO_DEVICE_LIST = "stereo_device_list";
    public static final String EXTRA_KEY_VIDEO_FLOW_CAPACITY = "video_flow_capacity";
    public static final String EXTRA_SUPPORT_ABSOLUTE_VOLUME = "supportAbsoluteVolume";
    public static final String Extra_KEY_IS_BLUETOOTH_SPEAKER = "isSpeaker";
    private static final String SUPPORT_VIDEO_FLOW_CAPACITY = "1";
    public static final String VIDEO_SUPPORT = "video";

    @Nullable
    private final Bundle mExtra;

    @Nullable
    private final Bitmap mIcon;

    @Nullable
    private String mName;
    private int mPriority;
    private final int mType;

    public DeviceInfo(Parcel parcel) {
        this.mName = parcel.readString();
        this.mIcon = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mType = parcel.readInt();
        this.mExtra = parcel.readBundle(getClass().getClassLoader());
        this.mPriority = parcel.readInt();
    }

    private static boolean equalBundles(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        if (bundle == null && bundle2 != null) {
            return false;
        }
        if (bundle2 == null) {
            return true;
        }
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        HashSet<String> hashSet = new HashSet(bundle.keySet());
        hashSet.addAll(bundle2.keySet());
        for (String str : hashSet) {
            if (bundle.containsKey(str) && bundle2.containsKey(str)) {
                Object obj = bundle.get(str);
                Object obj2 = bundle2.get(str);
                if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !equalBundles((Bundle) obj, (Bundle) obj2)) {
                    return false;
                }
                if (obj == null) {
                    if (obj2 != null) {
                        return false;
                    }
                } else if (!obj.equals(obj2)) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) obj;
        return this.mType == deviceInfo.mType && this.mPriority == deviceInfo.mPriority && Objects.equals(this.mName, deviceInfo.mName) && Objects.equals(this.mIcon, deviceInfo.mIcon) && equalBundles(this.mExtra, deviceInfo.mExtra);
    }

    public int getBleCustomDeviceType() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt(EXTRA_KEY_BLE_DEVICE_TYPE, 0);
    }

    public String getBluetoothDeviceModel() {
        Bundle bundle = this.mExtra;
        return bundle == null ? "" : bundle.getString(EXTRA_KEY_BLUETOOTH_DEVICE_MODEL, "");
    }

    @Nullable
    public Bundle getExtra() {
        return this.mExtra;
    }

    public String getGroupId() {
        Bundle bundle = this.mExtra;
        return bundle == null ? "" : bundle.getString(EXTRA_KEY_GROUP_ID, "");
    }

    @Nullable
    public Bitmap getIcon() {
        return this.mIcon;
    }

    public String getIdHash() {
        Bundle bundle = this.mExtra;
        return bundle == null ? "" : bundle.getString(EXTRA_KEY_IDHASH, "");
    }

    public String getLyraId() {
        Bundle bundle = this.mExtra;
        return bundle == null ? "" : bundle.getString(EXTRA_KEY_LYRA_ID, "");
    }

    public String getMac() {
        Bundle bundle = this.mExtra;
        return bundle == null ? "" : bundle.getString(EXTRA_KEY_DEVICE_MAC, "");
    }

    public String getMiAccountId() {
        Bundle bundle = this.mExtra;
        return bundle == null ? "" : bundle.getString(EXTRA_KEY_MI_ACCOUNT_ID, "");
    }

    @NonNull
    public String getName() {
        String str = this.mName;
        return str == null ? "" : str;
    }

    public String getPeerModel() {
        Bundle bundle = this.mExtra;
        return bundle == null ? "" : bundle.getString("peer_model", "");
    }

    public String getPeerRomVersion() {
        Bundle bundle = this.mExtra;
        return bundle == null ? "" : bundle.getString("peer_rom_version", "");
    }

    public int getPriority() {
        return this.mPriority;
    }

    public List<DeviceInfo> getStereoDeviceList() {
        ArrayList parcelableArrayList;
        Bundle bundle = this.mExtra;
        return (bundle == null || (parcelableArrayList = bundle.getParcelableArrayList(EXTRA_KEY_STEREO_DEVICE_LIST)) == null) ? Collections.emptyList() : parcelableArrayList;
    }

    public int getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mIcon, Integer.valueOf(this.mType), this.mExtra, Integer.valueOf(this.mPriority));
    }

    public boolean isAudioSharing() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(EXTRA_IS_AUDIO_SHARE, false);
    }

    public boolean isBluetoothGlasses() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(EXTRA_KEY_IS_BLUETOOTH_GLASSES);
    }

    public boolean isBluetoothHeadset() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(EXTRA_KEY_IS_BLUETOOTH_HEADSET);
    }

    public boolean isBluetoothSpeaker() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(Extra_KEY_IS_BLUETOOTH_SPEAKER);
    }

    public boolean isCar() {
        Bundle bundle = this.mExtra;
        return bundle != null && bundle.getInt(EXTRA_KEY_MI_PLAY_DEVICE_TYPE, 0) == 5;
    }

    public boolean isGroupDevice() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(EXTRA_KEY_IS_GROUP_DEVICE, false);
    }

    public boolean isHeadset() {
        return isBluetoothHeadset() || (isThirdPartyHeadset() && !isBluetoothGlasses());
    }

    public boolean isLocalSpeaker() {
        return this.mType == 0;
    }

    public boolean isMiCar() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(EXTRA_KEY_IS_XIAOMI_CAR, false);
    }

    public boolean isMiuiPlusCar() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(EXTRA_KEY_IS_MIUI_PLUS_CAR, false);
    }

    public boolean isPC() {
        Bundle bundle = this.mExtra;
        return bundle != null && bundle.getInt(EXTRA_KEY_MI_PLAY_DEVICE_TYPE, 0) == 3;
    }

    public boolean isThirdPartyHeadset() {
        return getBleCustomDeviceType() == 11;
    }

    public boolean isTv() {
        Bundle bundle = this.mExtra;
        return bundle != null && bundle.getInt(EXTRA_KEY_MI_PLAY_DEVICE_TYPE, 0) == 2;
    }

    public void setName(@Nullable String str) {
        this.mName = str;
    }

    public void setPriority(int i2) {
        this.mPriority = i2;
    }

    public boolean supportAbsoluteVolume() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(EXTRA_SUPPORT_ABSOLUTE_VOLUME, false);
    }

    public boolean supportAudio() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return Arrays.asList(bundle.getString(EXTRA_KEY_DEVICE_SUPPORT_MP_ABILITY, "").split(aa.f3429b)).contains(AUDIO_SUPPORT);
    }

    public boolean supportVideo() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return Arrays.asList(bundle.getString(EXTRA_KEY_DEVICE_SUPPORT_MP_ABILITY, "").split(aa.f3429b)).contains(VIDEO_SUPPORT);
    }

    public boolean supportVideoFlowCapacity() {
        Bundle bundle = this.mExtra;
        if (bundle == null) {
            return false;
        }
        return bundle.getString(EXTRA_KEY_VIDEO_FLOW_CAPACITY, "").equals("1");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mName);
        parcel.writeParcelable(this.mIcon, i2);
        parcel.writeInt(this.mType);
        parcel.writeBundle(this.mExtra);
        parcel.writeInt(this.mPriority);
    }

    public DeviceInfo(@Nullable String str, @Nullable Bitmap bitmap, int i2, @Nullable Bundle bundle) {
        this.mName = str;
        this.mIcon = bitmap;
        this.mType = i2;
        this.mExtra = bundle;
    }
}
