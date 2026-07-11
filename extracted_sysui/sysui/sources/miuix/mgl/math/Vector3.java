package miuix.mgl.math;

import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.mgl.math.Math;

/* JADX INFO: loaded from: classes3.dex */
public final class Vector3 {
    private final float[] mArray;
    private int mOffset;
    public static final Companion Companion = new Companion(null);
    private static final Vector3 right = new Vector3(1.0f, 0.0f, 0.0f);
    private static final Vector3 up = new Vector3(0.0f, 1.0f, 0.0f);
    private static final Vector3 down = new Vector3(0.0f, -1.0f, 0.0f);
    private static final Vector3 forward = new Vector3(0.0f, 0.0f, 1.0f);
    private static final Vector3 zero = new Vector3(0.0f, 0.0f, 0.0f);
    private static final Vector3 one = new Vector3(1.0f, 1.0f, 1.0f);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void abs(Vector3 result, Vector3 a2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(java.lang.Math.abs(a2.getX()));
            result.setY(java.lang.Math.abs(a2.getY()));
            result.setZ(java.lang.Math.abs(a2.getZ()));
        }

        public final float angle(Vector3 from, Vector3 to) {
            n.g(from, "from");
            n.g(to, "to");
            return ((float) java.lang.Math.acos(Math.Companion.clamp(dot(from.normalize(), to.normalize()), -1.0f, 1.0f))) * 57.295776f;
        }

        public final void cross(Vector3 result, Vector3 a2, Vector3 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX((a2.getY() * b2.getZ()) - (a2.getZ() * b2.getY()));
            result.setY((a2.getZ() * b2.getX()) - (a2.getX() * b2.getZ()));
            result.setZ((a2.getX() * b2.getY()) - (a2.getY() * b2.getX()));
        }

        public final float distance(Vector3 a2, Vector3 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            float x2 = a2.getX() - b2.getX();
            float y2 = a2.getY() - b2.getY();
            float z2 = a2.getZ() - b2.getZ();
            return (float) java.lang.Math.sqrt((x2 * x2) + (y2 * y2) + (z2 * z2));
        }

        public final float distance2(Vector3 a2, Vector3 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            float x2 = a2.getX() - b2.getX();
            float y2 = a2.getY() - b2.getY();
            float z2 = a2.getZ() - b2.getZ();
            return (x2 * x2) + (y2 * y2) + (z2 * z2);
        }

        public final void div(Vector3 result, Vector3 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() / f2);
            result.setY(a2.getY() / f2);
            result.setZ(a2.getZ() / f2);
        }

        public final float dot(Vector3 a2, Vector3 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            return (a2.getX() * b2.getX()) + (a2.getY() * b2.getY()) + (a2.getZ() * b2.getZ());
        }

        public final void fromFloatArray(Vector3 result, float[] array) {
            n.g(result, "result");
            n.g(array, "array");
            if (array.length < 3) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result.setX(array[0]);
            result.setY(array[1]);
            result.setZ(array[2]);
        }

        public final void fromVector2(Vector3 result, Vector2 v2) {
            n.g(result, "result");
            n.g(v2, "v");
            result.setX(v2.getX());
            result.setY(v2.getY());
            result.setZ(0.0f);
        }

        public final void fromVector3(Vector3 result, Vector3 v2) {
            n.g(result, "result");
            n.g(v2, "v");
            result.setX(v2.getX());
            result.setY(v2.getY());
            result.setZ(v2.getZ());
        }

        public final void fromVector4(Vector3 result, Vector4 v2) {
            n.g(result, "result");
            n.g(v2, "v");
            result.setX(v2.getX());
            result.setY(v2.getY());
            result.setZ(v2.getZ());
        }

        public final Vector3 getDown() {
            return Vector3.down;
        }

        public final Vector3 getForward() {
            return Vector3.forward;
        }

        public final Vector3 getOne() {
            return Vector3.one;
        }

        public final Vector3 getRight() {
            return Vector3.right;
        }

        public final Vector3 getUp() {
            return Vector3.up;
        }

        public final Vector3 getZero() {
            return Vector3.zero;
        }

        public final void lerp(Vector3 result, Vector3 a2, Vector3 b2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            Math.Companion companion = Math.Companion;
            result.setX(companion.lerp(a2.getX(), b2.getX(), f2));
            result.setY(companion.lerp(a2.getY(), b2.getY(), f2));
            result.setZ(companion.lerp(a2.getZ(), b2.getZ(), f2));
        }

        public final void max(Vector3 result, Vector3 a2, Vector3 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(java.lang.Math.max(a2.getX(), b2.getX()));
            result.setY(java.lang.Math.max(a2.getY(), b2.getY()));
            result.setZ(java.lang.Math.max(a2.getZ(), b2.getZ()));
        }

        public final void min(Vector3 result, Vector3 a2, Vector3 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(java.lang.Math.min(a2.getX(), b2.getX()));
            result.setY(java.lang.Math.min(a2.getY(), b2.getY()));
            result.setZ(java.lang.Math.min(a2.getZ(), b2.getZ()));
        }

        public final void minus(Vector3 result, Vector3 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() - f2);
            result.setY(a2.getY() - f2);
            result.setZ(a2.getZ() - f2);
        }

        public final void normalize(Vector3 result, Vector3 a2) {
            n.g(result, "result");
            n.g(a2, "a");
            float length = a2.length();
            float f2 = length != 0.0f ? 1.0f / length : 1.0f;
            result.setX(a2.getX() * f2);
            result.setY(a2.getY() * f2);
            result.setZ(a2.getZ() * f2);
        }

        public final void plus(Vector3 result, Vector3 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() + f2);
            result.setY(a2.getY() + f2);
            result.setZ(a2.getZ() + f2);
        }

        public final void reflect(Vector3 result, Vector3 a2, Vector3 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            float fDot = dot(a2, b2) * 2.0f;
            result.setX(a2.getX() - (b2.getX() * fDot));
            result.setY(a2.getY() - (b2.getY() * fDot));
            result.setZ(a2.getZ() - (fDot * b2.getZ()));
        }

        public final void refract(Vector3 result, Vector3 a2, Vector3 b2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            float fDot = dot(a2, b2) * 2.0f;
            float fSqrt = 1.0f - ((f2 * f2) * (1.0f - ((float) java.lang.Math.sqrt(fDot))));
            if (fSqrt < 0.0f) {
                result.setZero();
                return;
            }
            float f3 = fDot * f2;
            double d2 = fSqrt;
            result.setX((a2.getX() * f2) - ((((float) java.lang.Math.sqrt(d2)) + f3) * b2.getX()));
            result.setY((a2.getY() * f2) - ((((float) java.lang.Math.sqrt(d2)) + f3) * b2.getY()));
            result.setZ((f2 * a2.getZ()) - ((f3 + ((float) java.lang.Math.sqrt(d2))) * b2.getZ()));
        }

        public final void reverse(Vector3 result, Vector3 a2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(-a2.getX());
            result.setY(-a2.getY());
            result.setZ(-a2.getZ());
        }

        public final void times(Vector3 result, Vector3 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() * f2);
            result.setY(a2.getY() * f2);
            result.setZ(a2.getZ() * f2);
        }

        public final void toFloatArray(float[] result, Vector3 vec) {
            n.g(result, "result");
            n.g(vec, "vec");
            if (result.length < 3) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result[0] = vec.getX();
            result[1] = vec.getY();
            result[2] = vec.getZ();
        }

        private Companion() {
        }

        public final float dot(float f2, float f3, float f4, Vector3 b2) {
            n.g(b2, "b");
            return (f2 * b2.getX()) + (f3 * b2.getY()) + (f4 * b2.getZ());
        }

        public final Vector3 cross(Vector3 a2, Vector3 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            Vector3 vector3 = new Vector3();
            cross(vector3, a2, b2);
            return vector3;
        }

        public final void div(Vector3 result, Vector3 a2, Vector3 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() / b2.getX());
            result.setY(a2.getY() / b2.getY());
            result.setZ(a2.getZ() / b2.getZ());
        }

        public final void minus(Vector3 result, Vector3 a2, Vector3 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() - b2.getX());
            result.setY(a2.getY() - b2.getY());
            result.setZ(a2.getZ() - b2.getZ());
        }

        public final void plus(Vector3 result, Vector3 a2, Vector3 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() + b2.getX());
            result.setY(a2.getY() + b2.getY());
            result.setZ(a2.getZ() + b2.getZ());
        }

        public final void times(Vector3 result, Vector3 a2, Vector3 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() * b2.getX());
            result.setY(a2.getY() * b2.getY());
            result.setZ(a2.getZ() * b2.getZ());
        }
    }

    public Vector3() {
        this.mArray = new float[]{0.0f, 0.0f, 0.0f};
    }

    public final Vector3 dec() {
        setX(getX() - 1.0f);
        setY(getY() - 1.0f);
        setZ(getZ() - 1.0f);
        return this;
    }

    public final Vector3 div(float f2) {
        return new Vector3(getX() / f2, getY() / f2, getZ() / f2);
    }

    public boolean equals(Object obj) {
        Vector3 vector3 = obj instanceof Vector3 ? (Vector3) obj : null;
        if (vector3 == null) {
            return false;
        }
        Math.Companion companion = Math.Companion;
        return companion.approximately(getZ(), vector3.getZ()) & companion.approximately(getX(), vector3.getX()) & companion.approximately(getY(), vector3.getY());
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
        throw new IllegalArgumentException("index must be in 0..2");
    }

    public final float getB() {
        return getZ();
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

    public final float getP() {
        return getZ();
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

    public final float getS() {
        return getX();
    }

    public final Vector2 getSt() {
        return new Vector2(getX(), getY());
    }

    public final Vector3 getStp() {
        return new Vector3(getX(), getY(), getZ());
    }

    public final float getT() {
        return getY();
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

    public final float getY() {
        return this.mArray[this.mOffset + 1];
    }

    public final float getZ() {
        return this.mArray[this.mOffset + 2];
    }

    public int hashCode() {
        return Objects.hash(Float.valueOf(getX()), Float.valueOf(getY()), Float.valueOf(getZ()));
    }

    public final Vector3 inc() {
        setX(getX() + 1.0f);
        setY(getY() + 1.0f);
        setZ(getZ() + 1.0f);
        return this;
    }

    public final boolean isZero() {
        Math.Companion companion = Math.Companion;
        return companion.approximately(getX(), 0.0f) && companion.approximately(getY(), 0.0f) && companion.approximately(getZ(), 0.0f);
    }

    public final float length() {
        return (float) java.lang.Math.sqrt((getX() * getX()) + (getY() * getY()) + (getZ() * getZ()));
    }

    public final float length2() {
        return (getX() * getX()) + (getY() * getY()) + (getZ() * getZ());
    }

    public final Vector3 minus(float f2) {
        return new Vector3(getX() - f2, getY() - f2, getZ() - f2);
    }

    public final Vector3 normalize() {
        Vector3 vector3 = new Vector3();
        Companion.normalize(vector3, this);
        return vector3;
    }

    public final Vector3 plus(float f2) {
        return new Vector3(getX() + f2, getY() + f2, getZ() + f2);
    }

    public final void set(int i2, float f2) {
        if (i2 == 0) {
            setX(f2);
        } else if (i2 == 1) {
            setY(f2);
        } else {
            if (i2 != 2) {
                throw new IllegalArgumentException("index must be in 0..2");
            }
            setZ(f2);
        }
    }

    public final void setB(float f2) {
        setZ(f2);
    }

    public final void setG(float f2) {
        setY(f2);
    }

    public final void setNormalize() {
        Companion.normalize(this, this);
    }

    public final void setP(float f2) {
        setZ(f2);
    }

    public final void setR(float f2) {
        setX(f2);
    }

    public final void setReverse() {
        Companion.reverse(this, this);
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

    public final void setT(float f2) {
        setY(f2);
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
    }

    public final Vector3 times(float f2) {
        return new Vector3(getX() * f2, getY() * f2, getZ() * f2);
    }

    public final Vector3 unaryMinus() {
        return new Vector3(-getX(), -getY(), -getZ());
    }

    public final Vector3 div(Vector2 v2) {
        n.g(v2, "v");
        return new Vector3(getX() / v2.getX(), getY() / v2.getY(), getZ());
    }

    public final Vector3 minus(Vector2 v2) {
        n.g(v2, "v");
        return new Vector3(getX() - v2.getX(), getY() - v2.getY(), getZ());
    }

    public final Vector3 plus(Vector2 v2) {
        n.g(v2, "v");
        return new Vector3(getX() + v2.getX(), getY() + v2.getY(), getZ());
    }

    public final Vector3 times(Vector2 v2) {
        n.g(v2, "v");
        return new Vector3(getX() * v2.getX(), getY() * v2.getY(), getZ());
    }

    public Vector3(float f2) {
        this(f2, f2, f2);
    }

    public final Vector3 div(Vector3 v2) {
        n.g(v2, "v");
        return new Vector3(getX() / v2.getX(), getY() / v2.getY(), getZ() / v2.getZ());
    }

    public final Vector3 minus(Vector3 v2) {
        n.g(v2, "v");
        return new Vector3(getX() - v2.getX(), getY() - v2.getY(), getZ() - v2.getZ());
    }

    public final Vector3 plus(Vector3 v2) {
        n.g(v2, "v");
        return new Vector3(getX() + v2.getX(), getY() + v2.getY(), getZ() + v2.getZ());
    }

    public final Vector3 times(Vector3 v2) {
        n.g(v2, "v");
        return new Vector3(getX() * v2.getX(), getY() * v2.getY(), getZ() * v2.getZ());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Vector3(Vector2 v2, float f2) {
        this(v2.getX(), v2.getY(), f2);
        n.g(v2, "v");
    }

    public /* synthetic */ Vector3(Vector2 vector2, float f2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(vector2, (i2 & 2) != 0 ? 0.0f : f2);
    }

    public final Vector2 get(int i2, int i3) {
        return new Vector2(get(i2), get(i3));
    }

    public final void set(int i2, int i3, float f2) {
        set(i2, f2);
        set(i3, f2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Vector3(Vector3 v2) {
        this(v2.getX(), v2.getY(), v2.getZ());
        n.g(v2, "v");
    }

    public final Vector3 get(int i2, int i3, int i4) {
        return new Vector3(get(i2), get(i3), get(i4));
    }

    public Vector3(float f2, float f3, float f4) {
        this.mArray = new float[]{f2, f3, f4};
    }

    public final void set(int i2, int i3, int i4, float f2) {
        set(i2, f2);
        set(i3, f2);
        set(i4, f2);
    }

    public Vector3(float[] array, int i2) {
        n.g(array, "array");
        if (array.length - i2 >= 3) {
            this.mArray = array;
            this.mOffset = i2;
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public final void set(float f2, float f3, float f4) {
        setX(f2);
        setY(f3);
        setZ(f4);
    }

    public /* synthetic */ Vector3(float[] fArr, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(fArr, (i3 & 2) != 0 ? 0 : i2);
    }
}
