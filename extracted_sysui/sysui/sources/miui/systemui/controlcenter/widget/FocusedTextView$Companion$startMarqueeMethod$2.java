package miui.systemui.controlcenter.widget;

import android.widget.TextView;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class FocusedTextView$Companion$startMarqueeMethod$2 extends o implements Function0 {
    public static final FocusedTextView$Companion$startMarqueeMethod$2 INSTANCE = new FocusedTextView$Companion$startMarqueeMethod$2();

    public FocusedTextView$Companion$startMarqueeMethod$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            Method declaredMethod = TextView.class.getDeclaredMethod("startMarquee", null);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }
}
