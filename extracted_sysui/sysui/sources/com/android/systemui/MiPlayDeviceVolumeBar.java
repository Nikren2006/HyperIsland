package com.android.systemui;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDeviceVolumeBar {
    public static final MiPlayDeviceVolumeBar INSTANCE = new MiPlayDeviceVolumeBar();
    private static final HashMap<m0.i, MiPlayVolumeBar> miPlayDeviceVolumeBarMap = new HashMap<>(8);
    private static MiPlayVolumeBar totalVolumeBar;

    private MiPlayDeviceVolumeBar() {
    }

    public final HashMap<m0.i, MiPlayVolumeBar> getMiPlayDeviceVolumeBarMap() {
        return miPlayDeviceVolumeBarMap;
    }

    public final MiPlayVolumeBar getTotalVolumeBar() {
        return totalVolumeBar;
    }

    public final void release() {
        miPlayDeviceVolumeBarMap.clear();
        totalVolumeBar = null;
    }

    public final void setTotalVolumeBar(MiPlayVolumeBar miPlayVolumeBar) {
        totalVolumeBar = miPlayVolumeBar;
    }
}
