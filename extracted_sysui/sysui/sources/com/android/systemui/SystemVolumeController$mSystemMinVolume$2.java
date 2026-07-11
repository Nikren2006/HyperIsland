package com.android.systemui;

import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class SystemVolumeController$mSystemMinVolume$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final SystemVolumeController$mSystemMinVolume$2 INSTANCE = new SystemVolumeController$mSystemMinVolume$2();

    public SystemVolumeController$mSystemMinVolume$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Integer invoke() {
        return Integer.valueOf(SystemVolumeController.INSTANCE.getMAudioManager().getStreamMinVolume(3));
    }
}
