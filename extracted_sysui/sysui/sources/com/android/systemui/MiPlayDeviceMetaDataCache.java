package com.android.systemui;

import com.miui.miplay.audio.data.MediaMetaData;
import m0.InterfaceC0464B;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDeviceMetaDataCache extends MiPlayDeviceInfoCache<MediaMetaData, InterfaceC0464B> {
    public static final MiPlayDeviceMetaDataCache INSTANCE = new MiPlayDeviceMetaDataCache();
    private static final String TAG = "MiPlayDeviceMetaDataCache";

    private MiPlayDeviceMetaDataCache() {
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public Object fetchValue(m0.i iVar, L0.d dVar) {
        return MiPlayExtentionsKt.fetchMediaMetaData(iVar, dVar);
    }

    public final String getTAG() {
        return TAG;
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public InterfaceC0464B createListener(m0.i device) {
        kotlin.jvm.internal.n.g(device, "device");
        return new MiPlayMediaChangeListener(device);
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public void registerListener(m0.i device, InterfaceC0464B listener) {
        kotlin.jvm.internal.n.g(device, "device");
        kotlin.jvm.internal.n.g(listener, "listener");
        device.n().u(listener, null);
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public void unregisterListener(m0.i device, InterfaceC0464B listener) {
        kotlin.jvm.internal.n.g(device, "device");
        kotlin.jvm.internal.n.g(listener, "listener");
        device.n().A(listener);
    }
}
