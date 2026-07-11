package miuix.mgl.math;

import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.mgl.math.Math;
import miuix.mgl.math.Matrix3x3;

/* JADX INFO: loaded from: classes3.dex */
public final class Quaternion {
    public static final Companion Companion = new Companion(null);
    public static final float EPS = 1.1920929E-6f;
    private final float[] mArray;
    private int mOffset;

    public static final class Companion {

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[SpaceType.values().length];
                iArr[SpaceType.SELF.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final float angle(Quaternion a2, Quaternion b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            return ((float) java.lang.Math.acos(java.lang.Math.abs(Math.Companion.clamp(dot(a2, b2), -1.0f, 1.0f)))) * 2.0f * 57.295776f;
        }

        public final void conj(Quaternion result, Quaternion quat) {
            n.g(result, "result");
            n.g(quat, "quat");
            result.setX(-quat.getX());
            result.setY(-quat.getY());
            result.setZ(-quat.getZ());
            result.setW(quat.getW());
        }

        public final float dot(Quaternion a2, Quaternion b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            return (a2.getX() * b2.getX()) + (a2.getY() * b2.getY()) + (a2.getZ() * b2.getZ()) + (a2.getW() * b2.getW());
        }

        public final void fromAxisAngle(Quaternion result, Vector3 axis, float f2) {
            n.g(result, "result");
            n.g(axis, "axis");
            float fRadians = Math.Companion.radians(f2);
            Vector3 vector3Normalize = axis.normalize();
            double d2 = fRadians * 0.5f;
            Vector3.Companion.times(vector3Normalize, vector3Normalize, (float) java.lang.Math.sin(d2));
            result.setX(vector3Normalize.getX());
            result.setY(vector3Normalize.getY());
            result.setZ(vector3Normalize.getZ());
            result.setW((float) java.lang.Math.cos(d2));
        }

        public final void fromEulerAngle(Quaternion result, Vector3 angle) {
            n.g(result, "result");
            n.g(angle, "angle");
            fromEulerAngle(result, angle.getX(), angle.getY(), angle.getZ());
        }

        public final void fromFloatArray(Quaternion result, float[] array) {
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

        public final void fromLookRotation(Quaternion result, Vector3 forward, Vector3 up) {
            n.g(result, "result");
            n.g(forward, "forward");
            n.g(up, "up");
            Vector3 vector3 = new Vector3();
            Vector3 vector32 = new Vector3();
            Vector3 vector33 = new Vector3();
            Matrix3x3 matrix3x3 = new Matrix3x3();
            Math.Companion.lookForwardToNormalizedRUF(vector3, vector32, vector33, forward, up);
            float x2 = vector3.getX() + vector32.getY() + vector33.getZ();
            if (x2 <= 0.0f) {
                Matrix3x3.Companion companion = Matrix3x3.Companion;
                companion.fromRUF(matrix3x3, vector3, vector32, vector33);
                companion.toQuaternion(result, matrix3x3);
                result.setNormalize();
                return;
            }
            float f2 = x2 + 1.0f;
            float fSqrt = 0.5f / ((float) java.lang.Math.sqrt(f2));
            result.set((vector32.getZ() - vector33.getY()) * fSqrt, (vector33.getX() - vector3.getZ()) * fSqrt, (vector3.getY() - vector32.getX()) * fSqrt, f2 * fSqrt);
            result.setNormalize();
        }

        public final void fromQuaternion(Quaternion result, Quaternion quat) {
            n.g(result, "result");
            n.g(quat, "quat");
            result.setX(quat.getX());
            result.setY(quat.getY());
            result.setZ(quat.getZ());
            result.setW(quat.getW());
        }

        public final Quaternion identity() {
            return new Quaternion();
        }

        public final void inverse(Quaternion result, Quaternion q2) {
            n.g(result, "result");
            n.g(q2, "q");
            float fDot = 1.0f / dot(q2, q2);
            result.setX((-q2.getX()) * fDot);
            result.setY((-q2.getY()) * fDot);
            result.setZ((-q2.getZ()) * fDot);
            result.setW(q2.getW() * fDot);
        }

        public final void lerp(Quaternion result, Quaternion from, Quaternion to, float f2) {
            n.g(result, "result");
            n.g(from, "from");
            n.g(to, "to");
            float fClamp = Math.Companion.clamp(f2, 0.0f, 1.0f);
            float f3 = 1.0f - fClamp;
            result.setX((from.getX() * f3) + (to.getX() * fClamp));
            result.setY((from.getY() * f3) + (to.getY() * fClamp));
            result.setZ((from.getZ() * f3) + (to.getZ() * fClamp));
            result.setW((f3 * from.getW()) + (fClamp * to.getW()));
        }

        public final void nLerp(Quaternion result, Quaternion from, Quaternion to, float f2) {
            n.g(result, "result");
            n.g(from, "from");
            n.g(to, "to");
            lerp(result, from, to, f2);
            result.setNormalize();
        }

        public final void normalize(Quaternion result, Quaternion quat) {
            n.g(result, "result");
            n.g(quat, "quat");
            float fDot = dot(quat, quat);
            if (fDot <= 0.0f) {
                result.setIdentity();
                return;
            }
            float fSqrt = 1.0f / ((float) java.lang.Math.sqrt(fDot));
            result.setX(quat.getX() * fSqrt);
            result.setY(quat.getY() * fSqrt);
            result.setZ(quat.getZ() * fSqrt);
            result.setW(fSqrt * quat.getW());
        }

        public final void positive(Quaternion result, Quaternion q2) {
            n.g(result, "result");
            n.g(q2, "q");
            if (q2.getW() >= 0.0f) {
                fromQuaternion(result, q2);
                return;
            }
            result.setX(-q2.getX());
            result.setY(-q2.getY());
            result.setZ(-q2.getZ());
            result.setW(-q2.getW());
        }

        public final void rotateAround(Quaternion result, Quaternion q2, Vector3 axis, float f2, SpaceType space) {
            n.g(result, "result");
            n.g(q2, "q");
            n.g(axis, "axis");
            n.g(space, "space");
            if (WhenMappings.$EnumSwitchMapping$0[space.ordinal()] == 1) {
                Quaternion quaternion = new Quaternion();
                fromAxisAngle(quaternion, axis, f2);
                times(result, q2, quaternion);
            } else {
                Quaternion quaternion2 = new Quaternion();
                fromAxisAngle(quaternion2, axis, f2);
                times(result, quaternion2, q2);
            }
        }

        public final void rotateTowards(Quaternion result, Quaternion from, Quaternion to, float f2) {
            n.g(result, "result");
            n.g(from, "from");
            n.g(to, "to");
            float fAngle = angle(from, to);
            if (Math.Companion.approximately(fAngle, 0.0f)) {
                result.fromQuaternion(to);
            } else {
                slerp(result, from, to, java.lang.Math.min(1.0f, f2 / fAngle));
            }
        }

        public final void slerp(Quaternion result, Quaternion from, Quaternion to, float f2) {
            n.g(result, "result");
            n.g(from, "from");
            n.g(to, "to");
            float fDot = dot(from, to);
            if (1.0f - java.lang.Math.abs(fDot) < 1.1920929E-6f) {
                if (fDot < 0.0f) {
                    from = from.unaryMinus();
                }
                nLerp(result, from, to, f2);
                return;
            }
            float fAcos = (float) java.lang.Math.acos(Math.Companion.clamp(r1 / ((float) java.lang.Math.sqrt(dot(from, from) * dot(to, to))), -1.0f, 1.0f));
            float fSin = (float) java.lang.Math.sin(fAcos);
            if (fSin < 1.1920929E-6f) {
                nLerp(result, from, to, f2);
                return;
            }
            float f3 = 1;
            float f4 = (f3 - f2) * fAcos;
            float f5 = fAcos * f2;
            float f6 = f3 / fSin;
            float fSin2 = ((float) java.lang.Math.sin(f4)) * f6;
            float fSin3 = ((float) java.lang.Math.sin(f5)) * f6;
            if (fDot < 0.0f) {
                fSin3 = -fSin3;
            }
            result.setX((from.getX() * fSin2) + (to.getX() * fSin3));
            result.setY((from.getY() * fSin2) + (to.getY() * fSin3));
            result.setZ((from.getZ() * fSin2) + (to.getZ() * fSin3));
            result.setW((fSin2 * from.getW()) + (fSin3 * to.getW()));
            result.setNormalize();
        }

        public final void tangentFrameToNormal(Vector3 result, Quaternion q2) {
            n.g(result, "result");
            n.g(q2, "q");
            result.setX((q2.getX() * 2.0f * q2.getZ()) + (q2.getY() * 2.0f * q2.getW()));
            result.setY((q2.getX() * (-2.0f) * q2.getW()) + (q2.getY() * 2.0f * q2.getZ()));
            result.setZ((1.0f - ((q2.getX() * 2.0f) * q2.getX())) - ((q2.getY() * 2.0f) * q2.getY()));
        }

        public final void times(Quaternion result, Quaternion a2, Quaternion b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            result.setX((((a2.getW() * b2.getX()) + (a2.getX() * b2.getW())) + (a2.getY() * b2.getZ())) - (a2.getZ() * b2.getY()));
            result.setY(((a2.getW() * b2.getY()) - (a2.getX() * b2.getZ())) + (a2.getY() * b2.getW()) + (a2.getZ() * b2.getX()));
            result.setZ((((a2.getW() * b2.getZ()) + (a2.getX() * b2.getY())) - (a2.getY() * b2.getX())) + (a2.getZ() * b2.getW()));
            result.setW((((a2.getW() * b2.getW()) - (a2.getX() * b2.getX())) - (a2.getY() * b2.getY())) - (a2.getZ() * b2.getZ()));
        }

        public final void toEulerAngles(Vector3 result, Quaternion q2) {
            n.g(result, "result");
            n.g(q2, "q");
            float x2 = ((q2.getX() * q2.getW()) - (q2.getY() * q2.getZ())) * 2.0f;
            if (java.lang.Math.abs(x2) < 0.999999f) {
                result.setX(((float) java.lang.Math.asin(x2)) * 57.295776f);
                float f2 = 2;
                float f3 = 1;
                result.setY(((float) java.lang.Math.atan2((q2.getX() * f2 * q2.getZ()) + (q2.getY() * f2 * q2.getW()), (((q2.getW() * f2) * q2.getW()) + ((q2.getZ() * f2) * q2.getZ())) - f3)) * 57.295776f);
                result.setZ(((float) java.lang.Math.atan2((q2.getX() * f2 * q2.getY()) + (q2.getZ() * f2 * q2.getW()), (((q2.getW() * f2) * q2.getW()) + ((f2 * q2.getY()) * q2.getY())) - f3)) * 57.295776f);
                return;
            }
            result.setX(x2 < 0.0f ? -1.5707964f : 1.5707964f);
            result.setX(result.getX() * 57.295776f);
            float f4 = 2;
            result.setY(((float) java.lang.Math.atan2(-(((q2.getX() * f4) * q2.getZ()) - ((q2.getY() * f4) * q2.getW())), (((q2.getW() * f4) * q2.getW()) + ((f4 * q2.getX()) * q2.getX())) - 1)) * 57.295776f);
            result.setZ(0.0f);
        }

        public final void toFloatArray(float[] result, Quaternion quat) {
            n.g(result, "result");
            n.g(quat, "quat");
            if (result.length < 4) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result[0] = quat.getX();
            result[1] = quat.getY();
            result[2] = quat.getZ();
            result[3] = quat.getW();
        }

        private Companion() {
        }

        public final void fromEulerAngle(Quaternion result, float f2, float f3, float f4) {
            n.g(result, "result");
            double d2 = f3 * 0.017453292f * 0.5f;
            float fCos = (float) java.lang.Math.cos(d2);
            float fSin = (float) java.lang.Math.sin(d2);
            double d3 = f2 * 0.017453292f * 0.5f;
            float fCos2 = (float) java.lang.Math.cos(d3);
            float fSin2 = (float) java.lang.Math.sin(d3);
            double d4 = f4 * 0.017453292f * 0.5f;
            float fCos3 = (float) java.lang.Math.cos(d4);
            float fSin3 = (float) java.lang.Math.sin(d4);
            float f5 = fSin * fCos2;
            float f6 = fCos * fSin2;
            result.setX((f5 * fSin3) + (f6 * fCos3));
            result.setY((f5 * fCos3) - (f6 * fSin3));
            float f7 = fCos * fCos2;
            float f8 = fSin * fSin2;
            result.setZ((f7 * fSin3) - (f8 * fCos3));
            result.setW((f8 * fSin3) + (f7 * fCos3));
        }

        public final void times(Vector3 result, Quaternion quat, Vector3 v2) {
            n.g(result, "result");
            n.g(quat, "quat");
            n.g(v2, "v");
            Quaternion quaternionTimes = quat.times(new Quaternion(v2, 0.0f)).times(inverse(quat));
            result.setX(quaternionTimes.getX());
            result.setY(quaternionTimes.getY());
            result.setZ(quaternionTimes.getZ());
        }

        public final Quaternion inverse(Quaternion q2) {
            n.g(q2, "q");
            Quaternion quaternion = new Quaternion();
            inverse(quaternion, q2);
            return quaternion;
        }
    }

    public Quaternion() {
        this.mArray = new float[]{0.0f, 0.0f, 0.0f, 1.0f};
    }

    public final Quaternion conj() {
        return new Quaternion(-getX(), -getY(), -getZ(), getW());
    }

    public boolean equals(Object obj) {
        Quaternion quaternion = obj instanceof Quaternion ? (Quaternion) obj : null;
        if (quaternion == null) {
            return false;
        }
        Math.Companion companion = Math.Companion;
        return companion.approximately(getW(), quaternion.getW()) & companion.approximately(getX(), quaternion.getX()) & companion.approximately(getY(), quaternion.getY()) & companion.approximately(getZ(), quaternion.getZ());
    }

    public final void fromQuaternion(Quaternion quat) {
        n.g(quat, "quat");
        Companion.fromQuaternion(this, quat);
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
        throw new IllegalArgumentException("index must 0..3");
    }

    public final float[] getData() {
        return this.mArray;
    }

    public final int getDataOffset() {
        return this.mOffset;
    }

    public final float getW() {
        return this.mArray[this.mOffset + 3];
    }

    public final float getX() {
        return this.mArray[this.mOffset];
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
        return Objects.hash(Float.valueOf(getX()), Float.valueOf(getY()), Float.valueOf(getZ()), Float.valueOf(getW()));
    }

    public final Quaternion inverse() {
        Quaternion quaternion = new Quaternion();
        Companion.inverse(quaternion, this);
        return quaternion;
    }

    public final void lookRotation(Vector3 forward, Vector3 up) {
        n.g(forward, "forward");
        n.g(up, "up");
        Companion.fromLookRotation(this, forward, up);
    }

    public final Quaternion normalize() {
        Quaternion quaternion = new Quaternion();
        Companion.fromQuaternion(quaternion, this);
        quaternion.setNormalize();
        return quaternion;
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

    public final void setConj() {
        Companion.conj(this, this);
    }

    public final void setIdentity() {
        setX(0.0f);
        setY(0.0f);
        setZ(0.0f);
        setW(1.0f);
    }

    public final void setNormalize() {
        Companion.normalize(this, this);
    }

    public final void setPositive() {
        Companion.positive(this, this);
    }

    public final void setW(float f2) {
        this.mArray[this.mOffset + 3] = f2;
    }

    public final void setX(float f2) {
        this.mArray[this.mOffset] = f2;
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

    public final Quaternion times(float f2) {
        return new Quaternion(getX() * f2, getY() * f2, getZ() * f2, getW() * f2);
    }

    public final Quaternion unaryMinus() {
        return new Quaternion(-getX(), -getY(), -getZ(), -getW());
    }

    public final Quaternion times(Quaternion r2) {
        n.g(r2, "r");
        Quaternion quaternion = new Quaternion();
        Companion.times(quaternion, this, r2);
        return quaternion;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Quaternion(Matrix4x4 matrix) {
        this();
        n.g(matrix, "matrix");
        Matrix4x4.Companion.toQuaternion(this, matrix);
    }

    public final Vector3 times(Vector3 r2) {
        n.g(r2, "r");
        Vector3 vector3 = new Vector3();
        Companion.times(vector3, this, r2);
        return vector3;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Quaternion(Vector3 v2, float f2) {
        this(v2.getX(), v2.getY(), v2.getZ(), f2);
        n.g(v2, "v");
    }

    public Quaternion(float f2, float f3, float f4, float f5) {
        this.mArray = new float[]{f2, f3, f4, f5};
    }

    public final void set(float f2, float f3, float f4, float f5) {
        setX(f2);
        setY(f3);
        setZ(f4);
        setW(f5);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Quaternion(Quaternion quat) {
        this();
        n.g(quat, "quat");
        Companion.fromQuaternion(this, quat);
    }

    public Quaternion(float[] array, int i2) {
        n.g(array, "array");
        if (array.length - i2 >= 4) {
            this.mArray = array;
            this.mOffset = i2;
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public /* synthetic */ Quaternion(float[] fArr, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(fArr, (i3 & 2) != 0 ? 0 : i2);
    }
}
