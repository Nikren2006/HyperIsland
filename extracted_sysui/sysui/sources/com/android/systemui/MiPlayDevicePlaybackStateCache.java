package com.android.systemui;

import m0.InterfaceC0464B;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDevicePlaybackStateCache extends MiPlayDeviceInfoCache<Integer, InterfaceC0464B> {
    public static final MiPlayDevicePlaybackStateCache INSTANCE = new MiPlayDevicePlaybackStateCache();
    private static final String TAG = "MiPlayDevicePlaybackStateCache";

    private MiPlayDevicePlaybackStateCache() {
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public Object fetchValue(m0.i iVar, L0.d dVar) {
        return MiPlayExtentionsKt.fetchPlaybackState(iVar, dVar);
    }

    public final String getTAG() {
        return TAG;
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public InterfaceC0464B createListener(m0.i device) {
        kotlin.jvm.internal.n.g(device, "device");
        return new PlaybackStateChangeListener(device);
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public void putValue(m0.i device, Integer num) {
        kotlin.jvm.internal.n.g(device, "device");
        super.putValue(device, num);
        MiPlayDetailViewModel.INSTANCE.updateDeviceListNotCache();
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
