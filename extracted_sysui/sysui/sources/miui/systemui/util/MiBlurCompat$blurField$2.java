package miui.systemui.util;

import android.content.res.Configuration;
import java.lang.reflect.Field;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class MiBlurCompat$blurField$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final MiBlurCompat$blurField$2 INSTANCE = new MiBlurCompat$blurField$2();

    public MiBlurCompat$blurField$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Field invoke() {
        try {
            return Configuration.class.getDeclaredField("blur");
        } catch (Throwable unused) {
            return null;
        }
    }
}
