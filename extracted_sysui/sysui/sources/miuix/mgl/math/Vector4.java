package miuix.mgl.math;

import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.mgl.math.Math;

/* JADX INFO: loaded from: classes3.dex */
public final class Vector4 {
    private final float[] mArray;
    private int mOffset;
    public static final Companion Companion = new Companion(null);
    private static final Vector4 zero = new Vector4(0.0f, 0.0f, 0.0f, 0.0f);
    private static final Vector4 one = new Vector4(1.0f, 1.0f, 1.0f, 1.0f);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final float distance(Vector4 a2, Vector4 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            float x2 = a2.getX() - b2.getX();
            float y2 = a2.getY() - b2.getY();
            float z2 = a2.getZ() - b2.getZ();
            float w2 = a2.getW() - b2.getW();
            return (float) java.lang.Math.sqrt((x2 * x2) + (y2 * y2) + (z2 * z2) + (w2 * w2));
        }

        public final float distance2(Vector4 a2, Vector4 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            float x2 = a2.getX() - b2.getX();
            float y2 = a2.getY() - b2.getY();
            float z2 = a2.getZ() - b2.getZ();
            float w2 = a2.getW() - b2.getW();
            return (x2 * x2) + (y2 * y2) + (z2 * z2) + (w2 * w2);
        }

        public final void div(Vector4 result, Vector4 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() / f2);
            result.setY(a2.getY() / f2);
            result.setZ(a2.getZ() / f2);
            result.setW(a2.getW() / f2);
        }

        public final float dot(Vector4 a2, Vector4 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            return (a2.getX() * b2.getX()) + (a2.getY() * b2.getY()) + (a2.getZ() * b2.getZ()) + (a2.getW() * b2.getW());
        }

        public final void fromFloatArray(Vector4 result, float[] array) {
            n.g(result, "result");
            n.g(array, "array");
            if (array.length < 4) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result.setX(array[0]);
            result.setY(array[1]);
            result.setZ(array[2]);
            result.setW(array[3]);
        }

        public final void fromVector4(Vector4 result, Vector4 v2) {
            n.g(result, "result");
            n.g(v2, "v");
            result.setX(v2.getX());
            result.setY(v2.getY());
            result.setZ(v2.getZ());
            result.setW(v2.getW());
        }

        public final Vector4 getOne() {
            return Vector4.one;
        }

        public final Vector4 getZero() {
            return Vector4.zero;
        }

        public final void lerp(Vector4 result, Vector4 a2, Vector4 b2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            Math.Companion companion = Math.Companion;
            result.setX(companion.lerp(a2.getX(), b2.getX(), f2));
            result.setY(companion.lerp(a2.getY(), b2.getY(), f2));
            result.setZ(companion.lerp(a2.getZ(), b2.getZ(), f2));
            result.setW(companion.lerp(a2.getW(), b2.getW(), f2));
        }

        public final void minus(Vector4 result, Vector4 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() - f2);
            result.setY(a2.getY() - f2);
            result.setZ(a2.getZ() - f2);
            result.setW(a2.getW() - f2);
        }

        public final void normalize(Vector4 result, Vector4 a2) {
            n.g(result, "result");
            n.g(a2, "a");
            float length = a2.length();
            float f2 = length != 0.0f ? 1.0f / length : 1.0f;
            result.setX(a2.getX() * f2);
            result.setY(a2.getY() * f2);
            result.setZ(a2.getZ() * f2);
            result.setW(a2.getW() * f2);
        }

        public final void plus(Vector4 result, Vector4 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() + f2);
            result.setY(a2.getY() + f2);
            result.setZ(a2.getZ() + f2);
            result.setW(a2.getW() + f2);
        }

        public final void swap(Vector4 a2, Vector4 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            float x2 = a2.getX();
            float y2 = a2.getY();
            float z2 = a2.getZ();
            float w2 = a2.getW();
            a2.set(b2.getX(), b2.getY(), b2.getZ(), b2.getW());
            b2.set(x2, y2, z2, w2);
        }

        public final void times(Vector4 result, Vector4 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() * f2);
            result.setY(a2.getY() * f2);
            result.setZ(a2.getZ() * f2);
            result.setW(a2.getW() * f2);
        }

        public final void toFloatArray(float[] result, Vector4 vec) {
            n.g(result, "result");
            n.g(vec, "vec");
            if (result.length < 4) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result[0] = vec.getX();
            result[1] = vec.getY();
            result[2] = vec.getZ();
            result[3] = vec.getW();
        }

        private Companion() {
        }

        public final float dot(float f2, float f3, float f4, float f5, Vector4 b2) {
            n.g(b2, "b");
            return (f2 * b2.getX()) + (f3 * b2.getY()) + (f4 * b2.getZ()) + (f5 * b2.getW());
        }

        public final float dot(float f2, float f3, float f4, float f5, Vector3 b2, float f6) {
            n.g(b2, "b");
            return (f2 * b2.getX()) + (f3 * b2.getY()) + (f4 * b2.getZ()) + (f5 * f6);
        }

        public final void div(Vector4 result, float f2) {
            n.g(result, "result");
            result.setX(result.getX() / f2);
            result.setY(result.getY() / f2);
            result.setZ(result.getZ() / f2);
            result.setW(result.getW() / f2);
        }

        public final void minus(Vector4 result, Vector4 a2, Vector4 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() - b2.getX());
            result.setY(a2.getY() - b2.getY());
            result.setZ(a2.getZ() - b2.getZ());
            result.setW(a2.getW() - b2.getW());
        }

        public final void plus(Vector4 result, Vector4 a2, Vector4 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() + b2.getX());
            result.setY(a2.getY() + b2.getY());
            result.setZ(a2.getZ() + b2.getZ());
            result.setW(a2.getW() + b2.getW());
        }

        public final void times(Vector4 result, Vector4 a2, Vector4 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() * b2.getX());
            result.setY(a2.getY() * b2.getY());
            result.setZ(a2.getZ() * b2.getZ());
            result.setW(a2.getW() * b2.getW());
        }

        public final void div(Vector4 result, Vector4 a2, Vector4 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() / b2.getX());
            result.setY(a2.getY() / b2.getY());
            result.setZ(a2.getZ() / b2.getZ());
            result.setW(a2.getW() / b2.getW());
        }
    }

    public Vector4() {
        this.mArray = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
    }

    public final Vector4 dec() {
        setX(getX() - 1.0f);
        setY(getY() - 1.0f);
        setZ(getZ() - 1.0f);
        setW(getW() - 1.0f);
        return this;
    }

    public final Vector4 div(float f2) {
        return new Vector4(getX() / f2, getY() / f2, getZ() / f2, getW() / f2);
    }

    public boolean equals(Object obj) {
        Vector4 vector4 = obj instanceof Vector4 ? (Vector4) obj : null;
        if (vector4 == null) {
            return false;
        }
        Math.Companion companion = Math.Companion;
        return companion.approximately(getW(), vector4.getW()) & companion.approximately(getX(), vector4.getX()) & companion.approximately(getY(), vector4.getY()) & companion.approximately(getZ(), vector4.getZ());
    }

    public final float get(int i2) {
        if (i2 == 0) {
            return getX();
        }
        if (i2 == 1) {
            return getY();
        }
        if (i2 == 2) {
            return getZ();
        }
        if (i2 == 3) {
            return getW();
        }
        throw new IllegalArgumentException("index must be in 0..3");
    }

    public final float getA() {
        return getW();
    }

    public final float getB() {
        return getZ();
    }

    public final float getBottom() {
        return getY();
    }

    public final float[] getData() {
        return this.mArray;
    }

    public final int getDataOffset() {
        return this.mOffset;
    }

    public final float getG() {
        return getY();
    }

    public final float getHeight() {
        return getW();
    }

    public final float getLeft() {
        return getX();
    }

    public final float getP() {
        return getZ();
    }

    public final float getQ() {
        return getW();
    }

    public final float getR() {
        return getX();
    }

    public final Vector2 getRg() {
        return new Vector2(getX(), getY());
    }

    public final Vector3 getRgb() {
        return new Vector3(getX(), getY(), getZ());
    }

    public final Vector4 getRgba() {
        return new Vector4(getX(), getY(), getZ(), getW());
    }

    public final float getS() {
        return getX();
    }

    public final Vector2 getSt() {
        return new Vector2(getX(), getY());
    }

    public final Vector3 getStp() {
        return new Vector3(getX(), getY(), getZ());
    }

    public final Vector4 getStpq() {
        return new Vector4(getX(), getY(), getZ(), getW());
    }

    public final float getT() {
        return getY();
    }

    public final float getW() {
        return this.mArray[this.mOffset + 3];
    }

    public final float getWidth() {
        return getZ();
    }

    public final float getX() {
        return this.mArray[this.mOffset];
    }

    public final Vector2 getXy() {
        return new Vector2(getX(), getY());
    }

    public final Vector3 getXyz() {
        return new Vector3(getX(), getY(), getZ());
    }

    public final Vector4 getXyzw() {
        return new Vector4(getX(), getY(), getZ(), getW());
    }

    public final float getY() {
        return this.mArray[this.mOffset + 1];
    }

    public final float getZ() {
        return this.mArray[this.mOffset + 2];
    }

    public int hashCode() {
        return Objects.hash(Float.valueOf(getX()), Float.valueOf(getY()), Float.valueOf(getZ()), Float.valueOf(getW()));
    }

    public final Vector4 inc() {
        setX(getX() + 1.0f);
        setY(getY() + 1.0f);
        setZ(getZ() + 1.0f);
        setW(getW() + 1.0f);
        return this;
    }

    public final float length() {
        return (float) java.lang.Math.sqrt((getX() * getX()) + (getY() * getY()) + (getZ() * getZ()) + (getW() * getW()));
    }

    public final float length2() {
        return (getX() * getX()) + (getY() * getY()) + (getZ() * getZ()) + (getW() * getW());
    }

    public final Vector4 minus(float f2) {
        return new Vector4(getX() - f2, getY() - f2, getZ() - f2, getW() - f2);
    }

    public final Vector4 normalize() {
        Vector4 vector4 = new Vector4();
        Companion.normalize(vector4, this);
        return vector4;
    }

    public final Vector4 plus(float f2) {
        return new Vector4(getX() + f2, getY() + f2, getZ() + f2, getW() + f2);
    }

    public final void set(int i2, float f2) {
        if (i2 == 0) {
            setX(f2);
            return;
        }
        if (i2 == 1) {
            setY(f2);
        } else if (i2 == 2) {
            setZ(f2);
        } else {
            if (i2 != 3) {
                throw new IllegalArgumentException("index must be in 0..3");
            }
            setW(f2);
        }
    }

    public final void setA(float f2) {
        setW(f2);
    }

    public final void setB(float f2) {
        setZ(f2);
    }

    public final void setBottom(float f2) {
        setY(f2);
    }

    public final void setG(float f2) {
        setY(f2);
    }

    public final void setHeight(float f2) {
        setW(f2);
    }

    public final void setLeft(float f2) {
        setX(f2);
    }

    public final void setNormalize() {
        Companion.normalize(this, this);
    }

    public final void setP(float f2) {
        setZ(f2);
    }

    public final void setQ(float f2) {
        setW(f2);
    }

    public final void setR(float f2) {
        setX(f2);
    }

    public final void setRg(Vector2 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
    }

    public final void setRgb(Vector3 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
        setZ(value.getZ());
    }

    public final void setRgba(Vector4 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
        setZ(value.getZ());
        setW(value.getW());
    }

    public final void setS(float f2) {
        setX(f2);
    }

    public final void setSt(Vector2 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
    }

    public final void setStp(Vector3 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
        setZ(value.getZ());
    }

    public final void setStpq(Vector4 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
        setZ(value.getZ());
        setW(value.getW());
    }

    public final void setT(float f2) {
        setY(f2);
    }

    public final void setW(float f2) {
        this.mArray[this.mOffset + 3] = f2;
    }

    public final void setWidth(float f2) {
        setZ(f2);
    }

    public final void setX(float f2) {
        this.mArray[this.mOffset] = f2;
    }

    public final void setXy(Vector2 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
    }

    public final void setXyz(Vector3 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
        setZ(value.getZ());
    }

    public final void setXyzw(Vector4 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
        setZ(value.getZ());
        setW(value.getW());
    }

    public final void setY(float f2) {
        this.mArray[this.mOffset + 1] = f2;
    }

    public final void setZ(float f2) {
        this.mArray[this.mOffset + 2] = f2;
    }

    public final void setZero() {
        setX(0.0f);
        setY(0.0f);
        setZ(0.0f);
        setW(0.0f);
    }

    public final Vector4 times(float f2) {
        return new Vector4(getX() * f2, getY() * f2, getZ() * f2, getW() * f2);
    }

    public final Vector4 unaryMinus() {
        return new Vector4(-getX(), -getY(), -getZ(), -getW());
    }

    public final Vector4 div(Vector2 v2) {
        n.g(v2, "v");
        return new Vector4(getX() / v2.getX(), getY() / v2.getY(), getZ(), getW());
    }

    public final Vector4 minus(Vector2 v2) {
        n.g(v2, "v");
        return new Vector4(getX() - v2.getX(), getY() - v2.getY(), getZ(), getW());
    }

    public final Vector4 plus(Vector2 v2) {
        n.g(v2, "v");
        return new Vector4(getX() + v2.getX(), getY() + v2.getY(), getZ(), getW());
    }

    public final Vector4 times(Vector2 v2) {
        n.g(v2, "v");
        return new Vector4(getX() * v2.getX(), getY() * v2.getY(), getZ(), getW());
    }

    public Vector4(float f2) {
        this(f2, f2, f2, f2);
    }

    public final Vector4 div(Vector3 v2) {
        n.g(v2, "v");
        return new Vector4(getX() / v2.getX(), getY() / v2.getY(), getZ() / v2.getZ(), getW());
    }

    public final Vector4 minus(Vector3 v2) {
        n.g(v2, "v");
        return new Vector4(getX() - v2.getX(), getY() - v2.getY(), getZ() - v2.getZ(), getW());
    }

    public final Vector4 plus(Vector3 v2) {
        n.g(v2, "v");
        return new Vector4(getX() + v2.getX(), getY() + v2.getY(), getZ() + v2.getZ(), getW());
    }

    public final Vector4 times(Vector3 v2) {
        n.g(v2, "v");
        return new Vector4(getX() * v2.getX(), getY() * v2.getY(), getZ() * v2.getZ(), getW());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Vector4(Vector2 v2, float f2, float f3) {
        this(v2.getX(), v2.getY(), f2, f3);
        n.g(v2, "v");
    }

    public final Vector4 div(Vector4 v2) {
        n.g(v2, "v");
        return new Vector4(getX() / v2.getX(), getY() / v2.getY(), getZ() / v2.getZ(), getW() / v2.getW());
    }

    public final Vector4 minus(Vector4 v2) {
        n.g(v2, "v");
        return new Vector4(getX() - v2.getX(), getY() - v2.getY(), getZ() - v2.getZ(), getW() - v2.getW());
    }

    public final Vector4 plus(Vector4 v2) {
        n.g(v2, "v");
        return new Vector4(getX() + v2.getX(), getY() + v2.getY(), getZ() + v2.getZ(), getW() + v2.getW());
    }

    public final Vector4 times(Vector4 v2) {
        n.g(v2, "v");
        return new Vector4(getX() * v2.getX(), getY() * v2.getY(), getZ() * v2.getZ(), getW() * v2.getW());
    }

    public /* synthetic */ Vector4(Vector2 vector2, float f2, float f3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(vector2, (i2 & 2) != 0 ? 0.0f : f2, (i2 & 4) != 0 ? 0.0f : f3);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Vector4(Vector3 v2, float f2) {
        this(v2.getX(), v2.getY(), v2.getZ(), f2);
        n.g(v2, "v");
    }

    public final Vector2 get(int i2, int i3) {
        return new Vector2(get(i2), get(i3));
    }

    public final void set(int i2, int i3, float f2) {
        set(i2, f2);
        set(i3, f2);
    }

    public /* synthetic */ Vector4(Vector3 vector3, float f2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(vector3, (i2 & 2) != 0 ? 0.0f : f2);
    }

    public final Vector3 get(int i2, int i3, int i4) {
        return new Vector3(get(i2), get(i3), get(i4));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Vector4(Vector4 v2) {
        this(v2.getX(), v2.getY(), v2.getZ(), v2.getW());
        n.g(v2, "v");
    }

    public final Vector4 get(int i2, int i3, int i4, int i5) {
        return new Vector4(get(i2), get(i3), get(i4), get(i5));
    }

    public final void set(int i2, int i3, int i4, float f2) {
        set(i2, f2);
        set(i3, f2);
        set(i4, f2);
    }

    public Vector4(float f2, float f3, float f4, float f5) {
        this.mArray = new float[]{f2, f3, f4, f5};
    }

    public Vector4(float[] array, int i2) {
        n.g(array, "array");
        if (array.length - i2 >= 4) {
            this.mArray = array;
            this.mOffset = i2;
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public final void set(int i2, int i3, int i4, int i5, float f2) {
        set(i2, f2);
        set(i3, f2);
        set(i4, f2);
        set(i5, f2);
    }

    public final void set(float f2, float f3, float f4, float f5) {
        setX(f2);
        setY(f3);
        setZ(f4);
        setW(f5);
    }

    public /* synthetic */ Vector4(float[] fArr, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(fArr, (i3 & 2) != 0 ? 0 : i2);
    }

    public final void set(Vector3 vec, float f2) {
        n.g(vec, "vec");
        setX(vec.getX());
        setY(vec.getY());
        setZ(vec.getZ());
        setW(f2);
    }
}
