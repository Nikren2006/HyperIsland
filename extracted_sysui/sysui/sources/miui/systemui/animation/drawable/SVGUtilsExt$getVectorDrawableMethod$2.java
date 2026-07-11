package miui.systemui.animation.drawable;

import android.graphics.drawable.AnimatedVectorDrawable;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public final class SVGUtilsExt$getVectorDrawableMethod$2 extends o implements Function0 {
    public static final SVGUtilsExt$getVectorDrawableMethod$2 INSTANCE = new SVGUtilsExt$getVectorDrawableMethod$2();

    public SVGUtilsExt$getVectorDrawableMethod$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            Method declaredMethod = AnimatedVectorDrawable.class.getDeclaredMethod("getVectorDrawable", null);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }
}
