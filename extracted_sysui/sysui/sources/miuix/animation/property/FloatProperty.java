package miuix.animation.property;

import android.util.Property;

/* JADX INFO: loaded from: classes4.dex */
public abstract class FloatProperty<T> extends Property<T, Float> {
    float mMinVisibleChange;
    final String mPropertyName;

    public FloatProperty(String str) {
        this(str, -1.0f);
    }

    public float getMinVisibleChange() {
        return this.mMinVisibleChange;
    }

    public abstract float getValue(T t2);

    public void setMinVisibleChange(float f2) {
        this.mMinVisibleChange = f2;
    }

    public abstract void setValue(T t2, float f2);

    public String toString() {
        return getClass().getSimpleName() + "@" + hashCode() + "{name='" + this.mPropertyName + "',min='" + this.mMinVisibleChange + "'}";
    }

    public FloatProperty(String str, float f2) {
        super(Float.class, str);
        this.mPropertyName = str;
        this.mMinVisibleChange = f2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.util.Property
    public Float get(T t2) {
        return t2 == null ? Float.valueOf(0.0f) : Float.valueOf(getValue(t2));
    }

    @Override // android.util.Property
    public final void set(T t2, Float f2) {
        if (t2 != null) {
            setValue(t2, f2.floatValue());
        }
    }
}
