package miuix.animation.property;

import com.miui.maml.folme.AnimatedProperty;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class ValueProperty<T> extends FloatProperty<T> {
    private volatile String mName;
    public static final ValueProperty FRACTION = new ValueProperty("fraction", 0.002f);
    public static final ValueProperty TRANSLATION_X = new ValueProperty("translationX", 1.0f);
    public static final ValueProperty TRANSLATION_Y = new ValueProperty("translationY", 1.0f);
    public static final ValueProperty TRANSLATION_Z = new ValueProperty("translationZ", 1.0f);
    public static final ValueProperty SCALE = new ValueProperty("scale", 0.004f);
    public static final ValueProperty SCALE_X = new ValueProperty("scaleX", 0.004f);
    public static final ValueProperty SCALE_Y = new ValueProperty("scaleY", 0.004f);
    public static final ValueProperty ROTATION = new ValueProperty("rotation", 0.1f);
    public static final ValueProperty ROTATION_X = new ValueProperty("rotationX", 0.1f);
    public static final ValueProperty ROTATION_Y = new ValueProperty("rotationY", 0.1f);

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public static final ValueProperty f5998X = new ValueProperty(AnimatedProperty.PROPERTY_NAME_X, 1.0f);

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public static final ValueProperty f5999Y = new ValueProperty(AnimatedProperty.PROPERTY_NAME_Y, 1.0f);

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public static final ValueProperty f6000Z = new ValueProperty("z", 1.0f);
    public static final ValueProperty HEIGHT = new ValueProperty("height", 1.0f);
    public static final ValueProperty WIDTH = new ValueProperty("width", 1.0f);
    public static final ValueProperty ALPHA = new ValueProperty("alpha", 0.00390625f) { // from class: miuix.animation.property.ValueProperty.1
        @Override // miuix.animation.property.ValueProperty, miuix.animation.property.FloatProperty
        public void setValue(Object obj, float f2) {
            if (f2 > 1.0f) {
                super.setValue(obj, 1.0f);
            } else if (f2 < 0.0f) {
                super.setValue(obj, 0.0f);
            }
            super.setValue(obj, f2);
        }
    };
    public static final ValueProperty ALPHA_INT = new IntValueProperty("alphaInt", 1.0f) { // from class: miuix.animation.property.ValueProperty.2
        @Override // miuix.animation.property.IntValueProperty, miuix.animation.property.IIntValueProperty
        public void setIntValue(Object obj, int i2) {
            if (i2 > 255) {
                super.setIntValue(obj, 255);
            } else if (i2 < 0) {
                super.setIntValue(obj, 0);
            }
            super.setIntValue(obj, i2);
        }
    };
    public static final ColorProperty COLOR_INT = new ColorProperty("colorInt");

    public ValueProperty(String str) {
        this(str, -1.0f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !ValueProperty.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        return Objects.equals(getName(), ((ValueProperty) obj).getName());
    }

    @Override // android.util.Property
    public String getName() {
        return this.mName != null ? this.mName : super.getName();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.animation.property.FloatProperty
    public float getValue(T t2) {
        Float f2;
        if (!(t2 instanceof ValueTargetObject) || (f2 = (Float) ((ValueTargetObject) t2).getPropertyValue(getName(), Float.TYPE)) == null) {
            return Float.MAX_VALUE;
        }
        return f2.floatValue();
    }

    public int hashCode() {
        return Objects.hash(getName());
    }

    public void setName(String str) {
        this.mName = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.animation.property.FloatProperty
    public void setValue(T t2, float f2) {
        if (t2 instanceof ValueTargetObject) {
            ((ValueTargetObject) t2).setPropertyValue(getName(), Float.TYPE, Float.valueOf(f2));
        }
    }

    @Override // miuix.animation.property.FloatProperty
    public String toString() {
        return "ValueProperty@" + hashCode() + "{name='" + getName() + "',min='" + this.mMinVisibleChange + "'}";
    }

    public ValueProperty(String str, float f2) {
        super(str, f2);
    }
}
