package com.android.systemui;

import android.util.Log;
import com.miui.miplay.audio.data.AdvertisementParam;
import com.miui.miplay.audio.data.MediaMetaData;
import java.util.List;
import m0.InterfaceC0464B;

/* JADX INFO: loaded from: classes.dex */
public final class PlaybackStateChangeListener implements InterfaceC0464B {
    private final m0.i device;

    public PlaybackStateChangeListener(m0.i device) {
        kotlin.jvm.internal.n.g(device, "device");
        this.device = device;
    }

    public final m0.i getDevice() {
        return this.device;
    }

    @Override // m0.InterfaceC0464B
    public void onBufferStateChange(int i2) {
    }

    @Override // m0.InterfaceC0464B
    public /* bridge */ /* synthetic */ void onCastModeChange(int i2, int i3) {
        super.onCastModeChange(i2, i3);
    }

    @Override // m0.InterfaceC0464B
    public /* bridge */ /* synthetic */ void onCpAppStateChange(int i2, String str) {
        super.onCpAppStateChange(i2, str);
    }

    @Override // m0.InterfaceC0464B
    public /* bridge */ /* synthetic */ void onCpStateChange(String str, int i2) {
        super.onCpStateChange(str, i2);
    }

    @Override // m0.InterfaceC0464B
    public void onMediaMetaChange(MediaMetaData metaData) {
        kotlin.jvm.internal.n.g(metaData, "metaData");
    }

    @Override // m0.InterfaceC0464B
    public void onPlaySpeedChange(float f2) {
    }

    @Override // m0.InterfaceC0464B
    public /* bridge */ /* synthetic */ void onPlaySpeedListChange(List list) {
        super.onPlaySpeedListChange(list);
    }

    @Override // m0.InterfaceC0464B
    public void onPlaybackStateChange(int i2) {
        MiPlayDevicePlaybackStateCache miPlayDevicePlaybackStateCache = MiPlayDevicePlaybackStateCache.INSTANCE;
        Log.d(miPlayDevicePlaybackStateCache.getTAG(), "onPlaybackStateChange(): device.deviceInfo.name =" + this.device.k().getName() + " , state = " + i2);
        miPlayDevicePlaybackStateCache.putValue(this.device, Integer.valueOf(i2));
        MiPlayDetailViewModel.INSTANCE.updateDeviceListNotCache();
    }

    @Override // m0.InterfaceC0464B
    public /* bridge */ /* synthetic */ void onPlayingAdvertisementChange(AdvertisementParam advertisementParam) {
        super.onPlayingAdvertisementChange(advertisementParam);
    }

    @Override // m0.InterfaceC0464B
    public void onPositionChange(long j2) {
    }
}
