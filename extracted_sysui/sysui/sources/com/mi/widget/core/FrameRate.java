package com.mi.widget.core;

import O0.a;
import O0.b;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
@Keep
public final class FrameRate {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ FrameRate[] $VALUES;
    private final long delay;
    public static final FrameRate AtMost120 = new FrameRate("AtMost120", 0, 6);
    public static final FrameRate AtMost60 = new FrameRate("AtMost60", 1, 14);
    public static final FrameRate AtMost30 = new FrameRate("AtMost30", 2, 30);

    private static final /* synthetic */ FrameRate[] $values() {
        return new FrameRate[]{AtMost120, AtMost60, AtMost30};
    }

    static {
        FrameRate[] frameRateArr$values = $values();
        $VALUES = frameRateArr$values;
        $ENTRIES = b.a(frameRateArr$values);
    }

    private FrameRate(String str, int i2, long j2) {
        this.delay = j2;
    }

    public static a getEntries() {
        return $ENTRIES;
    }

    public static FrameRate valueOf(String str) {
        return (FrameRate) Enum.valueOf(FrameRate.class, str);
    }

    public static FrameRate[] values() {
        return (FrameRate[]) $VALUES.clone();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final long getDelay$hyper_widget_1_0_7_pluginRelease() {
        return this.delay;
    }
}
