package miui.systemui.animation.drawable;

import H0.d;
import H0.e;
import android.animation.AnimatorSet;
import android.animation.Keyframes;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.VectorDrawable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"SoonBlockedPrivateApi"})
public final class SVGUtilsExt {
    public static final SVGUtilsExt INSTANCE = new SVGUtilsExt();
    private static final d supportVectorDrawableParams$delegate = e.b(SVGUtilsExt$supportVectorDrawableParams$2.INSTANCE);
    private static final d vectorDrawableParamsClass$delegate = e.b(SVGUtilsExt$vectorDrawableParamsClass$2.INSTANCE);
    private static final d getAnimatorSetMethod$delegate = e.b(SVGUtilsExt$getAnimatorSetMethod$2.INSTANCE);
    private static final d getVectorDrawableMethod$delegate = e.b(SVGUtilsExt$getVectorDrawableMethod$2.INSTANCE);
    private static final d mKeyframesField$delegate = e.b(SVGUtilsExt$mKeyframesField$2.INSTANCE);

    private SVGUtilsExt() {
    }

    public static final AnimatorSet getAnimatorSetCompat(AnimatedVectorDrawable animatedVectorDrawable) {
        n.g(animatedVectorDrawable, "<this>");
        try {
            Method getAnimatorSetMethod = INSTANCE.getGetAnimatorSetMethod();
            return (AnimatorSet) (getAnimatorSetMethod != null ? getAnimatorSetMethod.invoke(animatedVectorDrawable, null) : null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* synthetic */ void getAnimatorSetCompat$annotations(AnimatedVectorDrawable animatedVectorDrawable) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Method getGetAnimatorSetMethod() {
        return (Method) getAnimatorSetMethod$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Method getGetVectorDrawableMethod() {
        return (Method) getVectorDrawableMethod$delegate.getValue();
    }

    public static final Keyframes getKeyframesCompat(PropertyValuesHolder propertyValuesHolder) {
        n.g(propertyValuesHolder, "<this>");
        try {
            Field mKeyframesField = INSTANCE.getMKeyframesField();
            return (Keyframes) (mKeyframesField != null ? mKeyframesField.get(propertyValuesHolder) : null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* synthetic */ void getKeyframesCompat$annotations(PropertyValuesHolder propertyValuesHolder) {
    }

    private final Field getMKeyframesField() {
        return (Field) mKeyframesField$delegate.getValue();
    }

    public static final VectorDrawable getVectorDrawableCompat(AnimatedVectorDrawable animatedVectorDrawable) {
        n.g(animatedVectorDrawable, "<this>");
        try {
            Method getVectorDrawableMethod = INSTANCE.getGetVectorDrawableMethod();
            Object objInvoke = getVectorDrawableMethod != null ? getVectorDrawableMethod.invoke(animatedVectorDrawable, null) : null;
            n.e(objInvoke, "null cannot be cast to non-null type android.graphics.drawable.VectorDrawable");
            return (VectorDrawable) objInvoke;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* synthetic */ void getVectorDrawableCompat$annotations(AnimatedVectorDrawable animatedVectorDrawable) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getVectorDrawableParamsClass() {
        return (Class) vectorDrawableParamsClass$delegate.getValue();
    }

    public final boolean getSupportVectorDrawableParams() {
        return ((Boolean) supportVectorDrawableParams$delegate.getValue()).booleanValue();
    }
}
