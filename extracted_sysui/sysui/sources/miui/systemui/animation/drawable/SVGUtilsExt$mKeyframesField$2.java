package miui.systemui.animation.drawable;

import android.animation.PropertyValuesHolder;
import java.lang.reflect.Field;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public final class SVGUtilsExt$mKeyframesField$2 extends o implements Function0 {
    public static final SVGUtilsExt$mKeyframesField$2 INSTANCE = new SVGUtilsExt$mKeyframesField$2();

    public SVGUtilsExt$mKeyframesField$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Field invoke() {
        PropertyValuesHolder.class.getDeclaredField("mKeyframes").setAccessible(true);
        try {
            Field declaredField = PropertyValuesHolder.class.getDeclaredField("mKeyframes");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }
}
