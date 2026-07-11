package com.mi.widget.core;

import O0.a;
import O0.b;
import androidx.annotation.Keep;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
@Keep
public final class ShaderStrategy {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ ShaderStrategy[] $VALUES;
    public static final ShaderStrategy NONE = new ShaderStrategy("NONE", 0);
    public static final ShaderStrategy AGSL = new ShaderStrategy("AGSL", 1);
    public static final ShaderStrategy DRAWABLE = new ShaderStrategy("DRAWABLE", 2);

    private static final /* synthetic */ ShaderStrategy[] $values() {
        return new ShaderStrategy[]{NONE, AGSL, DRAWABLE};
    }

    static {
        ShaderStrategy[] shaderStrategyArr$values = $values();
        $VALUES = shaderStrategyArr$values;
        $ENTRIES = b.a(shaderStrategyArr$values);
    }

    private ShaderStrategy(String str, int i2) {
    }

    public static a getEntries() {
        return $ENTRIES;
    }

    public static ShaderStrategy valueOf(String str) {
        return (ShaderStrategy) Enum.valueOf(ShaderStrategy.class, str);
    }

    public static ShaderStrategy[] values() {
        return (ShaderStrategy[]) $VALUES.clone();
    }
}
