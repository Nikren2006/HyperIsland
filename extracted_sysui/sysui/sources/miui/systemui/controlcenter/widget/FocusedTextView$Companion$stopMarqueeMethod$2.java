package miui.systemui.controlcenter.widget;

import android.widget.TextView;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class FocusedTextView$Companion$stopMarqueeMethod$2 extends o implements Function0 {
    public static final FocusedTextView$Companion$stopMarqueeMethod$2 INSTANCE = new FocusedTextView$Companion$stopMarqueeMethod$2();

    public FocusedTextView$Companion$stopMarqueeMethod$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            Method declaredMethod = TextView.class.getDeclaredMethod("stopMarquee", null);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }
}
