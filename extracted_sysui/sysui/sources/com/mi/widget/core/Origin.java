package com.mi.widget.core;

import O0.a;
import O0.b;
import androidx.annotation.Keep;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
@Keep
public final class Origin {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ Origin[] $VALUES;
    public static final Origin LEFT = new Origin("LEFT", 0);
    public static final Origin RIGHT = new Origin("RIGHT", 1);
    public static final Origin START = new Origin("START", 2);
    public static final Origin END = new Origin("END", 3);

    private static final /* synthetic */ Origin[] $values() {
        return new Origin[]{LEFT, RIGHT, START, END};
    }

    static {
        Origin[] originArr$values = $values();
        $VALUES = originArr$values;
        $ENTRIES = b.a(originArr$values);
    }

    private Origin(String str, int i2) {
    }

    public static a getEntries() {
        return $ENTRIES;
    }

    public static Origin valueOf(String str) {
        return (Origin) Enum.valueOf(Origin.class, str);
    }

    public static Origin[] values() {
        return (Origin[]) $VALUES.clone();
    }
}
