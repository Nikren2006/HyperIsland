package com.android.systemui;

import androidx.lifecycle.MutableLiveData;
import com.miui.miplay.audio.data.MediaMetaData;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDeviceVolumeCache extends MiPlayDeviceInfoCache<Integer, m0.y> {
    public static final String TAG = "MiPlayDeviceVolumeCache";
    public static final MiPlayDeviceVolumeCache INSTANCE = new MiPlayDeviceVolumeCache();
    private static final HashMap<m0.i, Float> deviceVisualMaxVolumeMap = new HashMap<>();

    private MiPlayDeviceVolumeCache() {
    }

    public final void calVisualMax() {
        Integer volume = MiPlayOverallVolumeController.INSTANCE.getVolume();
        if (volume != null) {
            int iIntValue = volume.intValue();
            for (Map.Entry<m0.i, MutableLiveData<Integer>> entry : INSTANCE.getDeviceVolumeMap$miui_miplay_release().entrySet()) {
                m0.i key = entry.getKey();
                if (entry.getValue().getValue() != null) {
                    MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
                    if (MiPlayExtentionsKt.isSelectedDevice(key, value != null ? Integer.valueOf(value.getMediaType()) : null) && (MiPlayExtentionsKt.isMiPlayDevice(key) || MiPlayExtentionsKt.isForeignCastDevice(key))) {
                        deviceVisualMaxVolumeMap.put(key, Float.valueOf(iIntValue == 0 ? 1.0f : r1.intValue() / iIntValue));
                    } else {
                        deviceVisualMaxVolumeMap.remove(key);
                    }
                }
            }
        }
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public Object fetchValue(m0.i iVar, L0.d dVar) {
        return MiPlayExtentionsKt.fetchVolume(iVar, dVar);
    }

    public final HashMap<m0.i, Float> getDeviceVisualMaxVolumeMap() {
        return deviceVisualMaxVolumeMap;
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public m0.y createListener(m0.i device) {
        kotlin.jvm.internal.n.g(device, "device");
        return new MiPlayDeviceChangeListener(device);
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public void registerListener(m0.i device, m0.y listener) {
        kotlin.jvm.internal.n.g(device, "device");
        kotlin.jvm.internal.n.g(listener, "listener");
        device.u(listener, null);
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public void unregisterListener(m0.i device, m0.y listener) {
        kotlin.jvm.internal.n.g(device, "device");
        kotlin.jvm.internal.n.g(listener, "listener");
        device.y(listener);
    }
}
