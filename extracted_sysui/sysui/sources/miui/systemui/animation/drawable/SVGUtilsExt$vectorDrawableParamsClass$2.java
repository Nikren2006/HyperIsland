package miui.systemui.animation.drawable;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public final class SVGUtilsExt$vectorDrawableParamsClass$2 extends o implements Function0 {
    public static final SVGUtilsExt$vectorDrawableParamsClass$2 INSTANCE = new SVGUtilsExt$vectorDrawableParamsClass$2();

    public SVGUtilsExt$vectorDrawableParamsClass$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Class<?> invoke() {
        try {
            return Class.forName("android.graphics.drawable.VectorDrawableParams");
        } catch (Throwable unused) {
            return null;
        }
    }
}
