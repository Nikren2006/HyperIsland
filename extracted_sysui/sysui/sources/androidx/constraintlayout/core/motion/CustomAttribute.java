package androidx.constraintlayout.core.motion;

import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
public class CustomAttribute {
    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    private boolean mMethod;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE,
        REFERENCE_TYPE
    }

    public CustomAttribute(String str, AttributeType attributeType) {
        this.mMethod = false;
        this.mName = str;
        this.mType = attributeType;
    }

    private static int clamp(int i2) {
        int i3 = (i2 & (~(i2 >> 31))) - 255;
        return (i3 & (i3 >> 31)) + 255;
    }

    public static int hsvToRgb(float f2, float f3, float f4) {
        float f5 = f2 * 6.0f;
        int i2 = (int) f5;
        float f6 = f5 - i2;
        float f7 = f4 * 255.0f;
        int i3 = (int) (((1.0f - f3) * f7) + 0.5f);
        int i4 = (int) (((1.0f - (f6 * f3)) * f7) + 0.5f);
        int i5 = (int) (((1.0f - ((1.0f - f6) * f3)) * f7) + 0.5f);
        int i6 = (int) (f7 + 0.5f);
        if (i2 == 0) {
            return ((i6 << 16) + (i5 << 8) + i3) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i2 == 1) {
            return ((i4 << 16) + (i6 << 8) + i3) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i2 == 2) {
            return ((i3 << 16) + (i6 << 8) + i5) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i2 == 3) {
            return ((i3 << 16) + (i4 << 8) + i6) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i2 == 4) {
            return ((i5 << 16) + (i3 << 8) + i6) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i2 != 5) {
            return 0;
        }
        return ((i6 << 16) + (i3 << 8) + i4) | ViewCompat.MEASURED_STATE_MASK;
    }

    public boolean diff(CustomAttribute customAttribute) {
        AttributeType attributeType;
        if (customAttribute == null || (attributeType = this.mType) != customAttribute.mType) {
            return false;
        }
        switch (attributeType) {
            case INT_TYPE:
            case REFERENCE_TYPE:
                return this.mIntegerValue == customAttribute.mIntegerValue;
            case FLOAT_TYPE:
                return this.mFloatValue == customAttribute.mFloatValue;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                return this.mColorValue == customAttribute.mColorValue;
            case STRING_TYPE:
                return this.mIntegerValue == customAttribute.mIntegerValue;
            case BOOLEAN_TYPE:
                return this.mBooleanValue == customAttribute.mBooleanValue;
            case DIMENSION_TYPE:
                return this.mFloatValue == customAttribute.mFloatValue;
            default:
                return false;
        }
    }

    public AttributeType getType() {
        return this.mType;
    }

    public float getValueToInterpolate() {
        switch (this.mType) {
            case INT_TYPE:
                return this.mIntegerValue;
            case FLOAT_TYPE:
                return this.mFloatValue;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case STRING_TYPE:
                throw new RuntimeException("Cannot interpolate String");
            case BOOLEAN_TYPE:
                return this.mBooleanValue ? 1.0f : 0.0f;
            case DIMENSION_TYPE:
                return this.mFloatValue;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] fArr) {
        switch (this.mType) {
            case INT_TYPE:
                fArr[0] = this.mIntegerValue;
                return;
            case FLOAT_TYPE:
                fArr[0] = this.mFloatValue;
                return;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                int i2 = (this.mColorValue >> 24) & 255;
                float fPow = (float) Math.pow(((r9 >> 16) & 255) / 255.0f, 2.2d);
                float fPow2 = (float) Math.pow(((r9 >> 8) & 255) / 255.0f, 2.2d);
                float fPow3 = (float) Math.pow((r9 & 255) / 255.0f, 2.2d);
                fArr[0] = fPow;
                fArr[1] = fPow2;
                fArr[2] = fPow3;
                fArr[3] = i2 / 255.0f;
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case BOOLEAN_TYPE:
                fArr[0] = this.mBooleanValue ? 1.0f : 0.0f;
                return;
            case DIMENSION_TYPE:
                fArr[0] = this.mFloatValue;
                return;
            default:
                return;
        }
    }

    public boolean isContinuous() {
        int iOrdinal = this.mType.ordinal();
        return (iOrdinal == 4 || iOrdinal == 5 || iOrdinal == 7) ? false : true;
    }

    public int numberOfInterpolatedValues() {
        int iOrdinal = this.mType.ordinal();
        return (iOrdinal == 2 || iOrdinal == 3) ? 4 : 1;
    }

    public void setColorValue(int i2) {
        this.mColorValue = i2;
    }

    public void setFloatValue(float f2) {
        this.mFloatValue = f2;
    }

    public void setIntValue(int i2) {
        this.mIntegerValue = i2;
    }

    public void setStringValue(String str) {
        this.mStringValue = str;
    }

    public void setValue(float[] fArr) {
        switch (this.mType) {
            case INT_TYPE:
            case REFERENCE_TYPE:
                this.mIntegerValue = (int) fArr[0];
                return;
            case FLOAT_TYPE:
                this.mFloatValue = fArr[0];
                return;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                int iHsvToRgb = hsvToRgb(fArr[0], fArr[1], fArr[2]);
                this.mColorValue = iHsvToRgb;
                this.mColorValue = (clamp((int) (fArr[3] * 255.0f)) << 24) | (iHsvToRgb & ViewCompat.MEASURED_SIZE_MASK);
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case BOOLEAN_TYPE:
                this.mBooleanValue = ((double) fArr[0]) > 0.5d;
                return;
            case DIMENSION_TYPE:
                this.mFloatValue = fArr[0];
                return;
            default:
                return;
        }
    }

    public CustomAttribute(String str, AttributeType attributeType, Object obj, boolean z2) {
        this.mName = str;
        this.mType = attributeType;
        this.mMethod = z2;
        setValue(obj);
    }

    public void setValue(Object obj) {
        switch (this.mType) {
            case INT_TYPE:
            case REFERENCE_TYPE:
                this.mIntegerValue = ((Integer) obj).intValue();
                break;
            case FLOAT_TYPE:
                this.mFloatValue = ((Float) obj).floatValue();
                break;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                this.mColorValue = ((Integer) obj).intValue();
                break;
            case STRING_TYPE:
                this.mStringValue = (String) obj;
                break;
            case BOOLEAN_TYPE:
                this.mBooleanValue = ((Boolean) obj).booleanValue();
                break;
            case DIMENSION_TYPE:
                this.mFloatValue = ((Float) obj).floatValue();
                break;
        }
    }

    public CustomAttribute(CustomAttribute customAttribute, Object obj) {
        this.mMethod = false;
        this.mName = customAttribute.mName;
        this.mType = customAttribute.mType;
        setValue(obj);
    }
}
