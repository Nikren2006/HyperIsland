package com.android.systemui;

import android.media.AudioManager;
import com.miui.miplay.audio.data.DeviceInfo;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class SystemVolumeController$mAudioManager$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final SystemVolumeController$mAudioManager$2 INSTANCE = new SystemVolumeController$mAudioManager$2();

    public SystemVolumeController$mAudioManager$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final AudioManager invoke() {
        Object systemService = MiPlayController.INSTANCE.getContext().getSystemService(DeviceInfo.AUDIO_SUPPORT);
        kotlin.jvm.internal.n.e(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        return (AudioManager) systemService;
    }
}
