package miui.systemui.devicecenter.devices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.miui.circulate.device.api.BatteryInfo;
import com.miui.circulate.device.api.Constant;
import com.miui.circulate.device.api.DeviceInfo;
import com.miui.circulate.device.api.Icon;
import h0.C0401a;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.devicecenter.R;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceInfoWrapper {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DeviceInfoWrapper";
    private final BatteryInfo battery;
    private final Integer color;
    private final Icon deviceIcon;
    private final DeviceInfo deviceInfo;
    private final Drawable drawable;
    private JSONObject extractPrivateData;
    private final android.graphics.drawable.Icon icon;
    private final boolean inSyncGlasses;
    private final boolean isCOmposeSpeaker;
    private final boolean isCameraGlasses;
    private final boolean isComposeTv;
    private final String privateData;
    private final int sourceState;
    private final int state;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Integer getColor(DeviceInfo deviceInfo) {
            Icon icon = deviceInfo.getIcon();
            if (icon == null || icon.getType() != 2) {
                return null;
            }
            Icon icon2 = deviceInfo.getIcon();
            Object data = icon2 != null ? icon2.getData() : null;
            if (data instanceof Integer) {
                return (Integer) data;
            }
            return null;
        }

        @SuppressLint({"UseCompatLoadingForDrawables"})
        private final Drawable getDrawable(DeviceInfo deviceInfo, Context context) {
            Icon icon = deviceInfo.getIcon();
            if (icon == null || icon.getType() != 1) {
                return null;
            }
            Icon icon2 = deviceInfo.getIcon();
            Object data = icon2 != null ? icon2.getData() : null;
            Icon.Res res = data instanceof Icon.Res ? (Icon.Res) data : null;
            if (res == null) {
                return null;
            }
            PackageManager packageManager = context.getPackageManager();
            try {
                return packageManager.getResourcesForApplication(packageManager.getApplicationInfo(res.getPkg(), 0)).getDrawable(res.getId(), null);
            } catch (Throwable th) {
                Log.e(DeviceInfoWrapper.TAG, "getDrawable failed ", th);
                return null;
            }
        }

        private final android.graphics.drawable.Icon getIcon(DeviceInfo deviceInfo) {
            Icon icon = deviceInfo.getIcon();
            if (icon == null || icon.getType() != 3) {
                return null;
            }
            Icon icon2 = deviceInfo.getIcon();
            Object data = icon2 != null ? icon2.getData() : null;
            String str = data instanceof String ? (String) data : null;
            if (str != null) {
                return android.graphics.drawable.Icon.createWithContentUri(str);
            }
            return null;
        }

        private final int getState(DeviceInfo deviceInfo) {
            if ((deviceInfo.getState() & 16) > 0) {
                return 16;
            }
            if ((deviceInfo.getState() & 1024) > 0) {
                return 1024;
            }
            if ((deviceInfo.getState() & 4) > 0) {
                return 4;
            }
            if ((deviceInfo.getState() & 2) > 0) {
                return 2;
            }
            if ((deviceInfo.getState() & 512) > 0) {
                return 512;
            }
            if ((deviceInfo.getState() & 32) > 0) {
                return 32;
            }
            if ((deviceInfo.getState() & 8) > 0) {
                return 8;
            }
            if ((deviceInfo.getState() & 4096) > 0) {
                return 4096;
            }
            if ((deviceInfo.getState() & 2048) > 0) {
                return 2048;
            }
            if ((deviceInfo.getState() & 128) > 0) {
                return 128;
            }
            if ((deviceInfo.getState() & 256) > 0) {
                return 256;
            }
            return (deviceInfo.getState() & 64) > 0 ? 64 : -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isOnline(DeviceInfo deviceInfo) {
            return (deviceInfo.getState() & 1) > 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isOpen(DeviceInfo deviceInfo) {
            return (deviceInfo.getState() & 64) > 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isTheSame(Icon icon, Object obj) {
            Icon icon2 = obj instanceof Icon ? (Icon) obj : null;
            if (icon2 == null || icon.getType() != icon2.getType() || icon.getData().getClass() != icon2.getData().getClass()) {
                return false;
            }
            int type = icon.getType();
            if (type == 1) {
                Object data = icon.getData();
                Icon.Res res = data instanceof Icon.Res ? (Icon.Res) data : null;
                Object data2 = icon2.getData();
                Icon.Res res2 = data2 instanceof Icon.Res ? (Icon.Res) data2 : null;
                if (res == null && res2 == null) {
                    return true;
                }
                if (res == null || res2 == null || res.getId() != res2.getId() || !n.c(res.getPkg(), res2.getPkg())) {
                    return false;
                }
            } else if (type != 2) {
                if (type == 3 && !n.c(icon.getData(), icon2.getData())) {
                    return false;
                }
            } else if (!n.c(icon.getData(), icon2.getData())) {
                return false;
            }
            return true;
        }

        public final DeviceInfoWrapper create(DeviceInfo deviceInfo, Context context) {
            n.g(deviceInfo, "deviceInfo");
            n.g(context, "context");
            return new DeviceInfoWrapper(deviceInfo, deviceInfo.getIcon(), getState(deviceInfo), getDrawable(deviceInfo, context), getIcon(deviceInfo), getColor(deviceInfo), deviceInfo.getBattery(), deviceInfo.getPrivateData(), deviceInfo.getState());
        }

        private Companion() {
        }
    }

    public DeviceInfoWrapper(DeviceInfo deviceInfo, Icon icon, int i2, Drawable drawable, android.graphics.drawable.Icon icon2, Integer num, BatteryInfo batteryInfo, String str, int i3) {
        n.g(deviceInfo, "deviceInfo");
        this.deviceInfo = deviceInfo;
        this.deviceIcon = icon;
        this.state = i2;
        this.drawable = drawable;
        this.icon = icon2;
        this.color = num;
        this.battery = batteryInfo;
        this.privateData = str;
        this.sourceState = i3;
        boolean zC = n.c(deviceInfo.getDeviceType(), Constant.DeviceType.CAMERAGLASSES);
        this.isCameraGlasses = zC;
        this.inSyncGlasses = zC && i2 == 4;
        this.isComposeTv = n.c(getType(), Constant.DeviceType.COMPOSE_TV);
        this.isCOmposeSpeaker = n.c(getType(), Constant.DeviceType.COMPOSE_SOUND);
    }

    private final boolean batteryIsEffective(BatteryInfo batteryInfo) {
        if (batteryInfo != null && batteryInfo.getValues().length >= 3) {
            return batteryInfo.getValues()[0].doubleValue() >= 0.0d || batteryInfo.getValues()[1].doubleValue() >= 0.0d || batteryInfo.getValues()[2].doubleValue() >= 0.0d;
        }
        return false;
    }

    private final JSONObject extractPrivate() {
        JSONObject jSONObject;
        if (this.extractPrivateData == null) {
            String str = this.privateData;
            if (str == null || str.length() == 0) {
                jSONObject = new JSONObject();
            } else {
                try {
                    jSONObject = new JSONObject(this.privateData);
                } catch (JSONException e2) {
                    Log.e(TAG, "parse privateData json fail ", e2);
                    jSONObject = new JSONObject();
                }
            }
            this.extractPrivateData = jSONObject;
        }
        JSONObject jSONObject2 = this.extractPrivateData;
        n.d(jSONObject2);
        return jSONObject2;
    }

    public final DeviceInfo component1() {
        return this.deviceInfo;
    }

    public final Icon component2$miui_devicecenter_release() {
        return this.deviceIcon;
    }

    public final int component3() {
        return this.state;
    }

    public final Drawable component4() {
        return this.drawable;
    }

    public final android.graphics.drawable.Icon component5() {
        return this.icon;
    }

    public final Integer component6() {
        return this.color;
    }

    public final BatteryInfo component7() {
        return this.battery;
    }

    public final String component8() {
        return this.privateData;
    }

    public final int component9() {
        return this.sourceState;
    }

    public final DeviceInfoWrapper copy(DeviceInfo deviceInfo, Icon icon, int i2, Drawable drawable, android.graphics.drawable.Icon icon2, Integer num, BatteryInfo batteryInfo, String str, int i3) {
        n.g(deviceInfo, "deviceInfo");
        return new DeviceInfoWrapper(deviceInfo, icon, i2, drawable, icon2, num, batteryInfo, str, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceInfoWrapper)) {
            return false;
        }
        DeviceInfoWrapper deviceInfoWrapper = (DeviceInfoWrapper) obj;
        return n.c(this.deviceInfo, deviceInfoWrapper.deviceInfo) && n.c(this.deviceIcon, deviceInfoWrapper.deviceIcon) && this.state == deviceInfoWrapper.state && n.c(this.drawable, deviceInfoWrapper.drawable) && n.c(this.icon, deviceInfoWrapper.icon) && n.c(this.color, deviceInfoWrapper.color) && n.c(this.battery, deviceInfoWrapper.battery) && n.c(this.privateData, deviceInfoWrapper.privateData) && this.sourceState == deviceInfoWrapper.sourceState;
    }

    public final BatteryInfo getBattery() {
        return this.battery;
    }

    public final Double[] getBatteryValues() {
        BatteryInfo battery = this.deviceInfo.getBattery();
        if (battery != null) {
            return battery.getValues();
        }
        return null;
    }

    public final Integer getColor() {
        return this.color;
    }

    public final int getComposeDeviceNumber() {
        return extractPrivate().optInt(DeviceInfo.Builder.PrivateDataKey.COMPOSE_NUMBER, 0);
    }

    public final Icon getDeviceIcon$miui_devicecenter_release() {
        return this.deviceIcon;
    }

    public final DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public final boolean getHasBatteryInfo() {
        return (n.c(this.deviceInfo.getDeviceType(), "headset") || n.c(this.deviceInfo.getDeviceType(), Constant.DeviceType.CAMERAGLASSES) || n.c(this.deviceInfo.getDeviceType(), Constant.DeviceType.AUDIOGLASSES)) && this.deviceInfo.getBattery() != null && batteryIsEffective(this.deviceInfo.getBattery());
    }

    public final android.graphics.drawable.Icon getIcon() {
        return this.icon;
    }

    public final String getId() {
        return this.deviceInfo.getId();
    }

    public final boolean getInSyncGlasses() {
        return this.inSyncGlasses;
    }

    public final int getIndicatorRes() {
        switch (this.state) {
            case 2:
                return R.drawable.ic_device_center_item_state_screen_synergy;
            case 4:
                return R.drawable.ic_device_center_item_state_camera_synergy;
            case 8:
                return R.drawable.ic_device_center_item_state_mirror;
            case 16:
                return R.drawable.ic_device_center_item_state_call_synergy;
            case 32:
                return R.drawable.ic_device_center_item_state_hid_synergy;
            case 64:
                return R.drawable.ic_device_center_item_state_open;
            case 128:
                if (Constant.State.INSTANCE.check(this.sourceState, 256)) {
                    return R.drawable.ic_device_center_item_state_music;
                }
                return 0;
            case 256:
                return R.drawable.ic_device_center_item_state_music;
            case 512:
                return n.c(getType(), "Car") ? R.drawable.ic_device_center_item_state_app : R.drawable.ic_device_center_item_state_screen_synergy;
            case 1024:
                return R.drawable.ic_device_center_item_state_camera_synergy;
            case 2048:
                return R.drawable.ic_device_center_item_state_video_play;
            case 4096:
                return R.drawable.ic_device_center_item_state_cellular;
            default:
                return 0;
        }
    }

    public final boolean getOnline() {
        return Companion.isOnline(this.deviceInfo);
    }

    public final boolean getOpen() {
        return Companion.isOpen(this.deviceInfo);
    }

    public final String getPrivateData() {
        return this.privateData;
    }

    public final int getSourceState() {
        return this.sourceState;
    }

    public final int getState() {
        return this.state;
    }

    public final String getTitle() {
        return this.deviceInfo.getTitle();
    }

    public final String getType() {
        return this.deviceInfo.getDeviceType();
    }

    public int hashCode() {
        int iHashCode = this.deviceInfo.hashCode() * 31;
        Icon icon = this.deviceIcon;
        int iHashCode2 = (((iHashCode + (icon == null ? 0 : icon.hashCode())) * 31) + Integer.hashCode(this.state)) * 31;
        Drawable drawable = this.drawable;
        int iHashCode3 = (iHashCode2 + (drawable == null ? 0 : drawable.hashCode())) * 31;
        android.graphics.drawable.Icon icon2 = this.icon;
        int iHashCode4 = (iHashCode3 + (icon2 == null ? 0 : icon2.hashCode())) * 31;
        Integer num = this.color;
        int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        BatteryInfo batteryInfo = this.battery;
        int iHashCode6 = (iHashCode5 + (batteryInfo == null ? 0 : batteryInfo.hashCode())) * 31;
        String str = this.privateData;
        return ((iHashCode6 + (str != null ? str.hashCode() : 0)) * 31) + Integer.hashCode(this.sourceState);
    }

    public final boolean isCOmposeSpeaker() {
        return this.isCOmposeSpeaker;
    }

    public final boolean isCameraGlasses() {
        return this.isCameraGlasses;
    }

    public final boolean isComposeTv() {
        return this.isComposeTv;
    }

    public final boolean isContentTheSame(Object obj) {
        Icon icon;
        DeviceInfoWrapper deviceInfoWrapper = obj instanceof DeviceInfoWrapper ? (DeviceInfoWrapper) obj : null;
        return deviceInfoWrapper != null && n.c(getId(), deviceInfoWrapper.getId()) && this.state == deviceInfoWrapper.state && this.sourceState == deviceInfoWrapper.sourceState && n.c(this.battery, deviceInfoWrapper.battery) && n.c(getType(), deviceInfoWrapper.getType()) && (icon = this.deviceIcon) != null && Companion.isTheSame(icon, ((DeviceInfoWrapper) obj).deviceIcon);
    }

    public final boolean isWrapperTheSame(Object obj) {
        DeviceInfoWrapper deviceInfoWrapper = obj instanceof DeviceInfoWrapper ? (DeviceInfoWrapper) obj : null;
        if (deviceInfoWrapper == null) {
            return false;
        }
        return n.c(getId(), deviceInfoWrapper.getId());
    }

    public final void performClicked(Context context) {
        n.g(context, "context");
        C0401a.H(C0401a.f4478i, context, this.deviceInfo, null, 4, null);
    }

    public final String state2ContentDescription(Context ctx) {
        n.g(ctx, "ctx");
        switch (this.state) {
            case 2:
                String string = ctx.getString(R.string.state_screen_synergy);
                n.f(string, "getString(...)");
                return string;
            case 4:
                String string2 = ctx.getString(R.string.state_camera_synergy);
                n.f(string2, "getString(...)");
                return string2;
            case 8:
                String string3 = ctx.getString(R.string.state_mirror);
                n.f(string3, "getString(...)");
                return string3;
            case 16:
                String string4 = ctx.getString(R.string.state_call_synergy);
                n.f(string4, "getString(...)");
                return string4;
            case 32:
                String string5 = ctx.getString(R.string.state_hid_synergy);
                n.f(string5, "getString(...)");
                return string5;
            case 64:
                String string6 = ctx.getString(R.string.state_open);
                n.f(string6, "getString(...)");
                return string6;
            case 128:
                String string7 = ctx.getString(R.string.state_audio_cast);
                n.f(string7, "getString(...)");
                return string7;
            case 256:
                String string8 = ctx.getString(R.string.state_music_playing);
                n.f(string8, "getString(...)");
                return string8;
            case 512:
                String string9 = ctx.getString(R.string.state_app_continuity);
                n.f(string9, "getString(...)");
                return string9;
            case 1024:
                String string10 = ctx.getString(R.string.state_take_photo);
                n.f(string10, "getString(...)");
                return string10;
            case 2048:
                String string11 = ctx.getString(R.string.state_video_cast);
                n.f(string11, "getString(...)");
                return string11;
            case 4096:
                String string12 = ctx.getString(R.string.state_cellular_synergy);
                n.f(string12, "getString(...)");
                return string12;
            default:
                return "";
        }
    }

    public String toString() {
        return "DeviceInfoWrapper(deviceInfo=" + this.deviceInfo + ", deviceIcon=" + this.deviceIcon + ", state=" + this.state + ", drawable=" + this.drawable + ", icon=" + this.icon + ", color=" + this.color + ", battery=" + this.battery + ", privateData=" + this.privateData + ", sourceState=" + this.sourceState + ")";
    }

    public /* synthetic */ DeviceInfoWrapper(DeviceInfo deviceInfo, Icon icon, int i2, Drawable drawable, android.graphics.drawable.Icon icon2, Integer num, BatteryInfo batteryInfo, String str, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(deviceInfo, icon, i2, drawable, icon2, num, batteryInfo, str, (i4 & 256) != 0 ? -1 : i3);
    }
}
