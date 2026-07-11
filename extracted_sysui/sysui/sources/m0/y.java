package m0;

import com.miui.miplay.audio.data.DeviceInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface y {
    void onDeviceConnectionStateChange(int i2, int i3);

    void onDeviceInfoChange(DeviceInfo deviceInfo);

    void onDeviceSelectStatusChange(int i2, int i3);

    void onVolumeChange(int i2, int i3);
}
