package miuix.overscroller.internal.dynamicanimation.animation;

import android.util.FloatProperty;

/* JADX INFO: loaded from: classes.dex */
public abstract class FloatPropertyCompat<T> {
    final String mPropertyName;

    public FloatPropertyCompat(String str) {
        this.mPropertyName = str;
    }

    public static <T> FloatPropertyCompat<T> createFloatPropertyCompat(final FloatProperty<T> floatProperty) {
        return new FloatPropertyCompat<T>(floatProperty.getName()) { // from class: miuix.overscroller.internal.dynamicanimation.animation.FloatPropertyCompat.1
            @Override // miuix.overscroller.internal.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(T t2) {
                return ((Float) floatProperty.get(t2)).floatValue();
            }

            @Override // miuix.overscroller.internal.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(T t2, float f2) {
                floatProperty.setValue(t2, f2);
            }
        };
    }

    public abstract float getValue(T t2);

    public abstract void setValue(T t2, float f2);
}
