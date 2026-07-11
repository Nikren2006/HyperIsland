package androidx.core.util;

import android.util.Half;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class HalfKt {
    @RequiresApi(26)
    public static final Half toHalf(short s2) {
        return Half.valueOf(s2);
    }

    @RequiresApi(26)
    public static final Half toHalf(float f2) {
        return Half.valueOf(f2);
    }

    @RequiresApi(26)
    public static final Half toHalf(String str) {
        return Half.valueOf(str);
    }

    @RequiresApi(26)
    public static final Half toHalf(double d2) {
        return Half.valueOf((float) d2);
    }
}
