package miui.systemui.dynamicisland.events;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandExposureManagerKt {
    public static final String toDateTime(long j2, String pattern) {
        n.g(pattern, "pattern");
        String str = new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date(j2));
        n.f(str, "format(...)");
        return str;
    }

    public static /* synthetic */ String toDateTime$default(long j2, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        return toDateTime(j2, str);
    }
}
