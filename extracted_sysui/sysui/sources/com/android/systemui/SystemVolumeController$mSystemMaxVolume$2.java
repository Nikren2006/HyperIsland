package com.android.systemui;

import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class SystemVolumeController$mSystemMaxVolume$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final SystemVolumeController$mSystemMaxVolume$2 INSTANCE = new SystemVolumeController$mSystemMaxVolume$2();

    public SystemVolumeController$mSystemMaxVolume$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Integer invoke() {
        return Integer.valueOf(SystemVolumeController.INSTANCE.getMAudioManager().getStreamMaxVolume(3));
    }
}
