package miuix.animation;

import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;
import miuix.animation.Folme;
import miuix.animation.property.ColorProperty;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.IIntValueProperty;
import miuix.animation.property.IntValueProperty;
import miuix.animation.property.ValueProperty;
import miuix.animation.property.ValueTargetObject;
import miuix.animation.property.ViewProperty;

/* JADX INFO: loaded from: classes4.dex */
public class ValueTarget extends IAnimTarget implements FolmeObject {
    private static final float DEFAULT_MIN_VALUE = 0.002f;
    static ITargetCreator sCreator = new ITargetCreator() { // from class: miuix.animation.ValueTarget.1
        @Override // miuix.animation.ITargetCreator
        public IAnimTarget createTarget(Object obj) {
            return new ValueTarget(Looper.myLooper(), obj);
        }
    };

    @Nullable
    private Folme.ObjectFolmeImpl mCoreAnimator;
    private final AtomicInteger mMaxType;
    private final ValueTargetObject mTargetObj;
    private final boolean mWithoutRealObj;

    public static FloatProperty createProperty(String str, Class<?> cls) {
        return (cls == Integer.TYPE || cls == Integer.class) ? new IntValueProperty(str) : new ValueProperty(str);
    }

    public static FloatProperty getFloatProperty(String str) {
        return createProperty(str, Float.TYPE);
    }

    public static IIntValueProperty getIntValueProperty(String str) {
        return (IIntValueProperty) createProperty(str, Integer.TYPE);
    }

    private static boolean isPredefinedProperty(Object obj) {
        return (obj instanceof ValueProperty) || (obj instanceof ViewProperty) || (obj instanceof ColorProperty);
    }

    @Override // miuix.animation.IAnimTarget
    public void clean() {
        if (isAnimRunning(new FloatProperty[0])) {
            cancelRunningAnim();
        }
        this.animManager.clear();
        getNotifier().removeListeners();
    }

    @Override // miuix.animation.IAnimTarget
    public void doSetIntValue(IIntValueProperty iIntValueProperty, int i2) {
        if (this.mWithoutRealObj) {
            this.mTargetObj.setField(this, iIntValueProperty.getName(), Integer.TYPE, Integer.valueOf(i2));
        }
        if (isPredefinedProperty(iIntValueProperty)) {
            this.mTargetObj.setPropertyValue(iIntValueProperty.getName(), Integer.TYPE, Integer.valueOf(i2));
        }
        Class<?> genericClass = this.mTargetObj.getGenericClass(iIntValueProperty);
        Object realObject = this.mTargetObj.getRealObject();
        if (realObject != null && realObject.getClass() == genericClass) {
            iIntValueProperty.setIntValue(realObject, i2);
            return;
        }
        if (getTargetObject().getClass() == genericClass) {
            iIntValueProperty.setIntValue(getTargetObject(), i2);
            return;
        }
        if (getClass() == genericClass) {
            iIntValueProperty.setIntValue(this, i2);
            return;
        }
        try {
            try {
                try {
                    iIntValueProperty.setIntValue(realObject, i2);
                } catch (Exception unused) {
                    iIntValueProperty.setIntValue(getTargetObject(), i2);
                }
            } catch (Exception unused2) {
            }
        } catch (Exception unused3) {
            iIntValueProperty.setIntValue(this, i2);
        }
    }

    @Override // miuix.animation.IAnimTarget
    public void doSetValue(FloatProperty floatProperty, float f2) {
        if (this.mWithoutRealObj) {
            this.mTargetObj.setField(this, floatProperty.getName(), Float.TYPE, Float.valueOf(f2));
        }
        if (isPredefinedProperty(floatProperty)) {
            this.mTargetObj.setPropertyValue(floatProperty.getName(), Float.TYPE, Float.valueOf(f2));
        }
        Object realObject = this.mTargetObj.getRealObject();
        Class<?> genericClass = this.mTargetObj.getGenericClass(floatProperty);
        if (realObject != null && realObject.getClass() == genericClass) {
            floatProperty.setValue(realObject, f2);
            return;
        }
        if (getTargetObject().getClass() == genericClass) {
            floatProperty.setValue(getTargetObject(), f2);
            return;
        }
        if (getClass() == genericClass) {
            floatProperty.setValue(this, f2);
            return;
        }
        try {
            try {
                try {
                    floatProperty.setValue(realObject, f2);
                } catch (Exception unused) {
                    floatProperty.setValue(getTargetObject(), f2);
                }
            } catch (Exception unused2) {
            }
        } catch (Exception unused3) {
            floatProperty.setValue(this, f2);
        }
    }

    @Override // miuix.animation.FolmeObject
    public Folme.ObjectFolmeImpl folme() {
        return this.mCoreAnimator;
    }

    @Override // miuix.animation.IAnimTarget
    public float getDefaultMinVisible() {
        return 0.002f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002b A[PHI: r0
      0x002b: PHI (r0v9 float) = (r0v1 float), (r0v11 float) binds: [B:9:0x0020, B:12:0x0029] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    @Override // miuix.animation.IAnimTarget
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public double getDoubleValue(@androidx.annotation.NonNull miuix.animation.property.FloatProperty r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof miuix.animation.property.IIntValueProperty
            r1 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r2 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            if (r0 == 0) goto L23
            r0 = r5
            miuix.animation.property.IIntValueProperty r0 = (miuix.animation.property.IIntValueProperty) r0     // Catch: java.lang.Exception -> L1a
            int r0 = r4.getIntValue(r0)     // Catch: java.lang.Exception -> L1a
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r1) goto L2d
            double r0 = (double) r0
            goto L2e
        L1a:
            float r0 = r4.getValue(r5)
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 == 0) goto L2d
            goto L2b
        L23:
            float r0 = r4.getValue(r5)
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 == 0) goto L2d
        L2b:
            double r0 = (double) r0
            goto L2e
        L2d:
            r0 = r2
        L2e:
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 != 0) goto L48
            miuix.animation.property.ValueTargetObject r2 = r4.mTargetObj
            java.lang.String r3 = r5.getName()
            boolean r2 = r2.containProperty(r3)
            if (r2 == 0) goto L48
            miuix.animation.property.ValueTargetObject r4 = r4.mTargetObj
            java.lang.String r5 = r5.getName()
            double r0 = r4.getPropertyValue(r5)
        L48:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.animation.ValueTarget.getDoubleValue(miuix.animation.property.FloatProperty):double");
    }

    public int getIntValue(String str) {
        return this.mTargetObj.containProperty(str) ? (int) this.mTargetObj.getPropertyValue(str) : getIntValue(getIntValueProperty(str));
    }

    @Override // miuix.animation.IAnimTarget
    public float getMinVisibleChange(Object obj) {
        if (!(obj instanceof IIntValueProperty) || (obj instanceof ColorProperty)) {
            return super.getMinVisibleChange(obj);
        }
        return 1.0f;
    }

    @Override // miuix.animation.IAnimTarget
    public Object getTargetObject() {
        return this.mTargetObj;
    }

    public float getValue(String str) {
        return this.mTargetObj.containProperty(str) ? (float) this.mTargetObj.getPropertyValue(str) : getValue(getFloatProperty(str));
    }

    @Override // miuix.animation.IAnimTarget
    @Deprecated
    public double getVelocity(String str) {
        return getVelocity(getFloatProperty(str));
    }

    @Override // miuix.animation.IAnimTarget
    public boolean isValid() {
        ValueTargetObject valueTargetObject = this.mTargetObj;
        if (valueTargetObject == null) {
            return false;
        }
        return valueTargetObject.isValid();
    }

    @Override // miuix.animation.FolmeObject
    public void setFolmeImpl(@NonNull Folme.ObjectFolmeImpl objectFolmeImpl) {
        this.mCoreAnimator = objectFolmeImpl;
    }

    public void setIntValue(String str, int i2) {
        if (this.mTargetObj.containProperty(str)) {
            this.mTargetObj.setPropertyValue(str, Integer.TYPE, Integer.valueOf(i2));
        } else {
            setIntValue(getIntValueProperty(str), i2);
        }
    }

    public void setValue(String str, float f2) {
        if (this.mTargetObj.containProperty(str)) {
            this.mTargetObj.setPropertyValue(str, Float.TYPE, Float.valueOf(f2));
        } else {
            setValue(getFloatProperty(str), f2);
        }
    }

    @Override // miuix.animation.IAnimTarget
    @Deprecated
    public void setVelocity(String str, double d2) {
        setVelocity(getFloatProperty(str), d2);
    }

    public ValueTarget(Looper looper) {
        this(looper, null);
    }

    public ValueTarget() {
        this(Looper.myLooper(), null);
    }

    private ValueTarget(Looper looper, Object obj) {
        super(looper);
        this.mMaxType = new AtomicInteger(1000);
        this.mWithoutRealObj = obj == null;
        this.mTargetObj = new ValueTargetObject(obj == null ? Integer.valueOf(getId()) : obj);
    }

    @Override // miuix.animation.IAnimTarget
    public int getIntValue(IIntValueProperty iIntValueProperty) {
        Class<?> genericClass = this.mTargetObj.getGenericClass(iIntValueProperty);
        Object realObject = this.mTargetObj.getRealObject();
        Integer numValueOf = (realObject == null || realObject.getClass() != genericClass) ? null : Integer.valueOf(iIntValueProperty.getIntValue(realObject));
        if (getTargetObject().getClass() == genericClass) {
            numValueOf = Integer.valueOf(iIntValueProperty.getIntValue(getTargetObject()));
        }
        if (getClass() == genericClass) {
            numValueOf = Integer.valueOf(iIntValueProperty.getIntValue(this));
        }
        if (numValueOf == null || numValueOf.intValue() == Integer.MAX_VALUE) {
            try {
                numValueOf = Integer.valueOf(iIntValueProperty.getIntValue(realObject));
            } catch (Exception unused) {
            }
        }
        if (numValueOf == null || numValueOf.intValue() == Integer.MAX_VALUE) {
            try {
                numValueOf = Integer.valueOf(iIntValueProperty.getIntValue(getTargetObject()));
            } catch (Exception unused2) {
            }
        }
        if (numValueOf == null || numValueOf.intValue() == Integer.MAX_VALUE) {
            try {
                numValueOf = Integer.valueOf(iIntValueProperty.getIntValue(this));
            } catch (Exception unused3) {
            }
        }
        if (numValueOf == null || numValueOf.intValue() == Integer.MAX_VALUE) {
            Object field = this.mWithoutRealObj ? this.mTargetObj.getField(this, iIntValueProperty.getName(), Integer.TYPE) : null;
            if (field != null) {
                return ((Integer) field).intValue();
            }
            if (isPredefinedProperty(iIntValueProperty)) {
                numValueOf = (Integer) this.mTargetObj.getPropertyValue(iIntValueProperty.getName(), Integer.TYPE);
            }
        }
        if (numValueOf == null) {
            return Integer.MAX_VALUE;
        }
        return numValueOf.intValue();
    }

    @Override // miuix.animation.IAnimTarget
    public float getValue(FloatProperty floatProperty) {
        Class<?> genericClass = this.mTargetObj.getGenericClass(floatProperty);
        Object realObject = this.mTargetObj.getRealObject();
        Float fValueOf = (realObject == null || realObject.getClass() != genericClass) ? null : Float.valueOf(floatProperty.getValue(realObject));
        if (fValueOf == null && getTargetObject().getClass() == genericClass) {
            fValueOf = Float.valueOf(floatProperty.getValue(getTargetObject()));
        }
        if (fValueOf == null && getClass() == genericClass) {
            fValueOf = Float.valueOf(floatProperty.getValue(this));
        }
        if (fValueOf == null || fValueOf.floatValue() == Float.MAX_VALUE) {
            try {
                fValueOf = Float.valueOf(floatProperty.getValue(realObject));
            } catch (Exception unused) {
            }
        }
        if (fValueOf == null || fValueOf.floatValue() == Float.MAX_VALUE) {
            try {
                fValueOf = Float.valueOf(floatProperty.getValue(getTargetObject()));
            } catch (Exception unused2) {
            }
        }
        if (fValueOf == null || fValueOf.floatValue() == Float.MAX_VALUE) {
            try {
                fValueOf = Float.valueOf(floatProperty.getValue(this));
            } catch (Exception unused3) {
            }
        }
        if (fValueOf == null || fValueOf.floatValue() == Float.MAX_VALUE) {
            Object field = this.mWithoutRealObj ? this.mTargetObj.getField(this, floatProperty.getName(), Float.TYPE) : null;
            if (field != null) {
                return ((Float) field).floatValue();
            }
            if (isPredefinedProperty(floatProperty)) {
                fValueOf = (Float) this.mTargetObj.getPropertyValue(floatProperty.getName(), Float.TYPE);
            }
        }
        if (fValueOf == null) {
            return Float.MAX_VALUE;
        }
        return fValueOf.floatValue();
    }
}
