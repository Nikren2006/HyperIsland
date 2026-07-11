package miuix.mgl.math;

import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.mgl.math.Math;

/* JADX INFO: loaded from: classes3.dex */
public final class Vector2 {
    private final float[] mArray;
    private int mOffset;
    public static final Companion Companion = new Companion(null);
    private static final Vector2 zero = new Vector2(0.0f, 0.0f);
    private static final Vector2 one = new Vector2(1.0f, 1.0f);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final float distance(Vector2 a2, Vector2 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            float x2 = a2.getX() - b2.getX();
            float y2 = a2.getY() - b2.getY();
            return (float) java.lang.Math.sqrt((x2 * x2) + (y2 * y2));
        }

        public final float distance2(Vector2 a2, Vector2 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            float x2 = a2.getX() - b2.getX();
            float y2 = a2.getY() - b2.getY();
            return (x2 * x2) + (y2 * y2);
        }

        public final void div(Vector2 result, Vector2 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() / f2);
            result.setY(a2.getY() / f2);
        }

        public final float dot(Vector2 a2, Vector2 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            return (a2.getX() * b2.getX()) + (a2.getY() * b2.getY());
        }

        public final void fromFloatArray(Vector2 result, float[] array) {
            n.g(result, "result");
            n.g(array, "array");
            if (array.length < 2) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result.setX(array[0]);
            result.setY(array[1]);
        }

        public final void fromVector2(Vector2 result, Vector2 v2) {
            n.g(result, "result");
            n.g(v2, "v");
            result.setX(v2.getX());
            result.setY(v2.getY());
        }

        public final void fromVector3(Vector2 result, Vector3 v2) {
            n.g(result, "result");
            n.g(v2, "v");
            result.setX(v2.getX());
            result.setY(v2.getY());
        }

        public final void fromVector4(Vector2 result, Vector4 v2) {
            n.g(result, "result");
            n.g(v2, "v");
            result.setX(v2.getX());
            result.setY(v2.getY());
        }

        public final Vector2 getOne() {
            return Vector2.one;
        }

        public final Vector2 getZero() {
            return Vector2.zero;
        }

        public final void lerp(Vector2 result, Vector2 a2, Vector2 b2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            Math.Companion companion = Math.Companion;
            result.setX(companion.lerp(a2.getX(), b2.getX(), f2));
            result.setY(companion.lerp(a2.getY(), b2.getY(), f2));
        }

        public final void minus(Vector2 result, Vector2 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() - f2);
            result.setY(a2.getY() - f2);
        }

        public final void normalize(Vector2 result, Vector2 a2) {
            n.g(result, "result");
            n.g(a2, "a");
            float length = a2.length();
            float f2 = length != 0.0f ? 1.0f / length : 1.0f;
            result.setX(a2.getX() * f2);
            result.setY(a2.getY() * f2);
        }

        public final void plus(Vector2 result, Vector2 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() + f2);
            result.setY(a2.getY() + f2);
        }

        public final void reflect(Vector2 result, Vector2 a2, Vector2 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            float fDot = dot(a2, b2) * 2.0f;
            result.setX(a2.getX() - (b2.getX() * fDot));
            result.setY(a2.getY() - (fDot * b2.getY()));
        }

        public final void refract(Vector2 result, Vector2 a2, Vector2 b2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            float fDot = dot(a2, b2) * 2.0f;
            float fSqrt = 1.0f - ((f2 * f2) * (1.0f - ((float) java.lang.Math.sqrt(fDot))));
            if (fSqrt < 0.0f) {
                result.setX(0.0f);
                result.setY(0.0f);
            } else {
                float f3 = fDot * f2;
                double d2 = fSqrt;
                result.setX((a2.getX() * f2) - ((((float) java.lang.Math.sqrt(d2)) + f3) * b2.getX()));
                result.setY((f2 * a2.getY()) - ((f3 + ((float) java.lang.Math.sqrt(d2))) * b2.getY()));
            }
        }

        public final void times(Vector2 result, Vector2 a2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            result.setX(a2.getX() * f2);
            result.setY(a2.getY() * f2);
        }

        public final void toFloatArray(float[] result, Vector2 vec) {
            n.g(result, "result");
            n.g(vec, "vec");
            if (result.length < 2) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result[0] = vec.getX();
            result[1] = vec.getY();
        }

        private Companion() {
        }

        public final void div(Vector2 result, Vector2 a2, Vector2 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() / b2.getX());
            result.setY(a2.getY() / b2.getY());
        }

        public final void minus(Vector2 result, Vector2 a2, Vector2 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() - b2.getX());
            result.setY(a2.getY() - b2.getY());
        }

        public final void plus(Vector2 result, Vector2 a2, Vector2 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() + b2.getX());
            result.setY(a2.getY() + b2.getY());
        }

        public final void times(Vector2 result, Vector2 a2, Vector2 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX(a2.getX() * b2.getX());
            result.setY(a2.getY() * b2.getY());
        }
    }

    public Vector2() {
        this.mArray = new float[]{0.0f, 0.0f};
    }

    public final Vector2 dec() {
        setX(getX() - 1.0f);
        setY(getY() - 1.0f);
        return this;
    }

    public final Vector2 div(float f2) {
        return new Vector2(getX() / f2, getY() / f2);
    }

    public boolean equals(Object obj) {
        Vector2 vector2 = obj instanceof Vector2 ? (Vector2) obj : null;
        if (vector2 == null) {
            return false;
        }
        Math.Companion companion = Math.Companion;
        return companion.approximately(getY(), vector2.getY()) & companion.approximately(getX(), vector2.getX());
    }

    public final float get(int i2) {
        if (i2 == 0) {
            return getX();
        }
        if (i2 == 1) {
            return getY();
        }
        throw new IllegalArgumentException("index must be in 0..1");
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

    public final float getR() {
        return getX();
    }

    public final Vector2 getRg() {
        return new Vector2(getX(), getY());
    }

    public final float getS() {
        return getX();
    }

    public final Vector2 getSt() {
        return new Vector2(getX(), getY());
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

    public final float getY() {
        return this.mArray[this.mOffset + 1];
    }

    public int hashCode() {
        return Objects.hash(Float.valueOf(getX()), Float.valueOf(getY()));
    }

    public final Vector2 inc() {
        setX(getX() + 1.0f);
        setY(getY() + 1.0f);
        return this;
    }

    public final float length() {
        return (float) java.lang.Math.sqrt((getX() * getX()) + (getY() * getY()));
    }

    public final float length2() {
        return (getX() * getX()) + (getY() * getY());
    }

    public final Vector2 minus(float f2) {
        return new Vector2(getX() - f2, getY() - f2);
    }

    public final Vector2 normalize() {
        Vector2 vector2 = new Vector2();
        Companion.normalize(vector2, this);
        return vector2;
    }

    public final Vector2 plus(float f2) {
        return new Vector2(getX() + f2, getY() + f2);
    }

    public final void set(int i2, float f2) {
        if (i2 == 0) {
            setX(f2);
        } else {
            if (i2 != 1) {
                throw new IllegalArgumentException("index must be in 0..1");
            }
            setY(f2);
        }
    }

    public final void setG(float f2) {
        setY(f2);
    }

    public final void setNormalize() {
        Companion.normalize(this, this);
    }

    public final void setR(float f2) {
        setX(f2);
    }

    public final void setRg(Vector2 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
    }

    public final void setS(float f2) {
        setX(f2);
    }

    public final void setSt(Vector2 value) {
        n.g(value, "value");
        setX(value.getX());
        setY(value.getY());
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

    public final void setY(float f2) {
        this.mArray[this.mOffset + 1] = f2;
    }

    public final Vector2 times(float f2) {
        return new Vector2(getX() * f2, getY() * f2);
    }

    public final Vector2 unaryMinus() {
        return new Vector2(-getX(), -getY());
    }

    public final Vector2 div(Vector2 v2) {
        n.g(v2, "v");
        return new Vector2(getX() / v2.getX(), getY() / v2.getY());
    }

    public final Vector2 minus(Vector2 v2) {
        n.g(v2, "v");
        return new Vector2(getX() - v2.getX(), getY() - v2.getY());
    }

    public final Vector2 plus(Vector2 v2) {
        n.g(v2, "v");
        return new Vector2(getX() + v2.getX(), getY() + v2.getY());
    }

    public final Vector2 times(Vector2 v2) {
        n.g(v2, "v");
        return new Vector2(getX() * v2.getX(), getY() * v2.getY());
    }

    public Vector2(float f2) {
        this(f2, f2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Vector2(Vector2 v2) {
        this(v2.getX(), v2.getY());
        n.g(v2, "v");
    }

    public final Vector2 get(int i2, int i3) {
        return new Vector2(get(i2), get(i3));
    }

    public final void set(int i2, int i3, float f2) {
        set(i2, f2);
        set(i3, f2);
    }

    public Vector2(float f2, float f3) {
        this.mArray = new float[]{f2, f3};
    }

    public final void set(float f2, float f3) {
        setX(f2);
        setY(f3);
    }

    public Vector2(float[] array, int i2) {
        n.g(array, "array");
        if (array.length - i2 >= 2) {
            this.mArray = array;
            this.mOffset = i2;
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public /* synthetic */ Vector2(float[] fArr, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(fArr, (i3 & 2) != 0 ? 0 : i2);
    }
}
