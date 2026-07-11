package com.mi.widget.core;

import O0.a;
import O0.b;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class DriverShareStrategy {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ DriverShareStrategy[] $VALUES;
    public static final DriverShareStrategy NOT_SUPPORTED = new DriverShareStrategy("NOT_SUPPORTED", 0);
    public static final DriverShareStrategy SHARE_SAME_TYPE = new DriverShareStrategy("SHARE_SAME_TYPE", 1);
    public static final DriverShareStrategy SHARE_COMMON_TYPE = new DriverShareStrategy("SHARE_COMMON_TYPE", 2);

    private static final /* synthetic */ DriverShareStrategy[] $values() {
        return new DriverShareStrategy[]{NOT_SUPPORTED, SHARE_SAME_TYPE, SHARE_COMMON_TYPE};
    }

    static {
        DriverShareStrategy[] driverShareStrategyArr$values = $values();
        $VALUES = driverShareStrategyArr$values;
        $ENTRIES = b.a(driverShareStrategyArr$values);
    }

    private DriverShareStrategy(String str, int i2) {
    }

    public static a getEntries() {
        return $ENTRIES;
    }

    public static DriverShareStrategy valueOf(String str) {
        return (DriverShareStrategy) Enum.valueOf(DriverShareStrategy.class, str);
    }

    public static DriverShareStrategy[] values() {
        return (DriverShareStrategy[]) $VALUES.clone();
    }
}
