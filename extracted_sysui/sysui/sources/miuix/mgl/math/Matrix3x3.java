package miuix.mgl.math;

import f1.g;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.mgl.math.Math;
import miuix.mgl.math.Vector3;

/* JADX INFO: loaded from: classes3.dex */
public final class Matrix3x3 {
    public static final Companion Companion = new Companion(null);
    private final float[] mArray;
    private final Vector3 mColumn1;
    private final Vector3 mColumn2;
    private final Vector3 mColumn3;
    private int mOffset;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void toTangentSpace$default(Companion companion, Quaternion quaternion, Matrix3x3 matrix3x3, int i2, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                i2 = 2;
            }
            companion.toTangentSpace(quaternion, matrix3x3, i2);
        }

        public final void fromFloatArrayColumnSeq(Matrix3x3 result, float[] array) {
            n.g(result, "result");
            n.g(array, "array");
            if (array.length < 9) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result.getX().set(array[0], array[1], array[2]);
            result.getY().set(array[3], array[4], array[5]);
            result.getZ().set(array[6], array[7], array[8]);
        }

        public final void fromFloatArrayRowSeq(Matrix3x3 result, float[] array) {
            n.g(result, "result");
            n.g(array, "array");
            if (array.length < 9) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result.getX().set(array[0], array[3], array[6]);
            result.getY().set(array[1], array[4], array[7]);
            result.getZ().set(array[2], array[5], array[8]);
        }

        public final void fromQuaternion(Matrix3x3 result, Quaternion q2) {
            n.g(result, "result");
            n.g(q2, "q");
            float x2 = (q2.getX() * q2.getX()) + (q2.getY() * q2.getY()) + (q2.getZ() * q2.getZ()) + (q2.getW() * q2.getW());
            float f2 = x2 > 0.0f ? 2.0f / x2 : 0.0f;
            float x3 = q2.getX() * f2;
            float y2 = q2.getY() * f2;
            float z2 = f2 * q2.getZ();
            float x4 = q2.getX() * x3;
            float y3 = q2.getY() * x3;
            float z3 = q2.getZ() * x3;
            float w2 = x3 * q2.getW();
            float y4 = q2.getY() * y2;
            float z4 = q2.getZ() * y2;
            float w3 = y2 * q2.getW();
            float z5 = q2.getZ() * z2;
            float w4 = z2 * q2.getW();
            float f3 = 1;
            result.getX().setX((f3 - y4) - z5);
            result.getX().setY(y3 + w4);
            result.getX().setZ(z3 - w3);
            result.getY().setX(y3 - w4);
            float f4 = f3 - x4;
            result.getY().setY(f4 - z5);
            result.getY().setZ(z4 + w2);
            result.getZ().setX(z3 + w3);
            result.getZ().setY(z4 - w2);
            result.getZ().setZ(f4 - y4);
        }

        public final void fromRUF(Matrix3x3 result, Vector3 right, Vector3 up, Vector3 forward) {
            n.g(result, "result");
            n.g(right, "right");
            n.g(up, "up");
            n.g(forward, "forward");
            result.getX().setXyz(right);
            result.getY().setXyz(up);
            result.getZ().setXyz(forward);
        }

        public final Matrix3x3 identity() {
            return new Matrix3x3();
        }

        public final void inverse(Matrix3x3 result, Matrix3x3 m2) {
            n.g(result, "result");
            n.g(m2, "m");
            float f2 = m2.get(0).get(0);
            float f3 = m2.get(1).get(0);
            float f4 = m2.get(2).get(0);
            float f5 = m2.get(0).get(1);
            float f6 = m2.get(1).get(1);
            float f7 = m2.get(2).get(1);
            float f8 = m2.get(0).get(2);
            float f9 = m2.get(1).get(2);
            float f10 = m2.get(2).get(2);
            float f11 = (f6 * f10) - (f7 * f9);
            float f12 = (f7 * f8) - (f5 * f10);
            float f13 = (f5 * f9) - (f6 * f8);
            result.get(0).set(0, f11);
            result.get(0).set(1, f12);
            result.get(0).set(2, f13);
            result.get(1).set(0, (f4 * f9) - (f3 * f10));
            result.get(1).set(1, (f10 * f2) - (f4 * f8));
            result.get(1).set(2, (f8 * f3) - (f9 * f2));
            result.get(2).set(0, (f3 * f7) - (f4 * f6));
            result.get(2).set(1, (f4 * f5) - (f7 * f2));
            result.get(2).set(2, (f6 * f2) - (f5 * f3));
            float f14 = (f2 * f11) + (f3 * f12) + (f4 * f13);
            for (int i2 = 0; i2 < 3; i2++) {
                for (int i3 = 0; i3 < 3; i3++) {
                    Vector3 vector3 = result.get(i2);
                    vector3.set(i3, vector3.get(i3) / f14);
                }
            }
        }

        public final void times(Matrix3x3 result, Matrix3x3 a2, Matrix3x3 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            Vector3 x2 = result.getX();
            Vector3.Companion companion = Vector3.Companion;
            x2.setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), b2.getX()));
            result.getX().setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), b2.getX()));
            result.getX().setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), b2.getX()));
            result.getY().setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), b2.getY()));
            result.getY().setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), b2.getY()));
            result.getY().setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), b2.getY()));
            result.getZ().setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), b2.getZ()));
            result.getZ().setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), b2.getZ()));
            result.getZ().setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), b2.getZ()));
        }

        public final void toFloatArrayColumnSeq(float[] result, Matrix3x3 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            if (result.length < 9) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result[0] = mat.getX().getX();
            result[1] = mat.getX().getY();
            result[2] = mat.getX().getZ();
            result[3] = mat.getY().getX();
            result[4] = mat.getY().getY();
            result[5] = mat.getY().getZ();
            result[6] = mat.getZ().getX();
            result[7] = mat.getZ().getY();
            result[8] = mat.getZ().getZ();
        }

        public final void toFloatArrayRowSeq(float[] result, Matrix3x3 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            if (result.length < 9) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result[0] = mat.getX().getX();
            result[1] = mat.getY().getX();
            result[2] = mat.getZ().getX();
            result[3] = mat.getX().getY();
            result[4] = mat.getY().getY();
            result[5] = mat.getZ().getY();
            result[6] = mat.getX().getZ();
            result[7] = mat.getY().getZ();
            result[8] = mat.getZ().getZ();
        }

        public final void toForward(Vector3 result, Matrix3x3 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.set(mat.getZ().getX(), mat.getZ().getY(), mat.getZ().getZ());
        }

        public final void toQuaternion(Quaternion result, Matrix3x3 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            if (mat.getX().getX() + mat.getY().getY() + mat.getZ().getZ() > 0.0f) {
                float fSqrt = (float) java.lang.Math.sqrt(r8 + 1.0f);
                result.setW(fSqrt * 0.5f);
                float f2 = 0.5f / fSqrt;
                result.setX((mat.getY().getZ() - mat.getZ().getY()) * f2);
                result.setY((mat.getZ().getX() - mat.getX().getZ()) * f2);
                result.setZ((mat.getX().getY() - mat.getY().getX()) * f2);
                return;
            }
            int i2 = mat.getY().getY() > mat.getX().getX() ? 1 : 0;
            if (mat.getZ().getZ() > mat.get(i2).get(i2)) {
                i2 = 2;
            }
            Math.Companion companion = Math.Companion;
            int i3 = companion.getNEXT_IJK()[i2];
            int i4 = companion.getNEXT_IJK()[i3];
            float fSqrt2 = (float) java.lang.Math.sqrt((mat.get(i2).get(i2) - (mat.get(i3).get(i3) + mat.get(i4).get(i4))) + 1.0f);
            result.set(i2, fSqrt2 * 0.5f);
            if (fSqrt2 != 0.0f) {
                fSqrt2 = 0.5f / fSqrt2;
            }
            result.setW((mat.get(i3).get(i4) - mat.get(i4).get(i3)) * fSqrt2);
            result.set(i3, (mat.get(i2).get(i3) + mat.get(i3).get(i2)) * fSqrt2);
            result.set(i4, (mat.get(i2).get(i4) + mat.get(i4).get(i2)) * fSqrt2);
        }

        public final void toRight(Vector3 result, Matrix3x3 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.set(mat.getX().getX(), mat.getX().getY(), mat.getX().getZ());
        }

        public final void toTangentSpace(Quaternion result, Matrix3x3 mat, int i2) {
            n.g(result, "result");
            n.g(mat, "mat");
            toQuaternion(result, mat);
            result.setNormalize();
            result.setPositive();
            float f2 = 1.0f / ((1 << ((i2 * 8) - 1)) - 1);
            if (result.getW() < f2) {
                result.setW(f2);
                float fSqrt = (float) java.lang.Math.sqrt(1.0f - (f2 * f2));
                result.setX(result.getX() * fSqrt);
                result.setY(result.getY() * fSqrt);
                result.setZ(result.getZ() * fSqrt);
            }
            Vector3 vector3 = new Vector3();
            Vector3.Companion companion = Vector3.Companion;
            companion.cross(vector3, mat.get(0), mat.get(2));
            if (companion.dot(vector3, mat.get(1)) < 0.0f) {
                result.setX(-result.getX());
                result.setY(-result.getY());
                result.setZ(-result.getZ());
                result.setW(-result.getW());
            }
        }

        public final void toUp(Vector3 result, Matrix3x3 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.set(mat.getY().getX(), mat.getY().getY(), mat.getY().getZ());
        }

        public final void transpose(Matrix3x3 result, Matrix3x3 m2) {
            n.g(result, "result");
            n.g(m2, "m");
            result.getX().set(m2.getX().getX(), m2.getY().getX(), m2.getZ().getX());
            result.getY().set(m2.getX().getY(), m2.getY().getY(), m2.getZ().getY());
            result.getZ().set(m2.getX().getZ(), m2.getY().getZ(), m2.getZ().getZ());
        }

        private Companion() {
        }

        public final Vector3 toForward(Matrix3x3 mat) {
            n.g(mat, "mat");
            Vector3 vector3 = new Vector3();
            toForward(vector3, mat);
            return vector3;
        }

        public final Vector3 toRight(Matrix3x3 mat) {
            n.g(mat, "mat");
            Vector3 vector3 = new Vector3();
            toRight(vector3, mat);
            return vector3;
        }

        public final Vector3 toUp(Matrix3x3 mat) {
            n.g(mat, "mat");
            Vector3 vector3 = new Vector3();
            toUp(vector3, mat);
            return vector3;
        }

        public final Matrix3x3 fromRUF(Vector3 right, Vector3 up, Vector3 forward) {
            n.g(right, "right");
            n.g(up, "up");
            n.g(forward, "forward");
            Matrix3x3 matrix3x3 = new Matrix3x3();
            fromRUF(matrix3x3, right, up, forward);
            return matrix3x3;
        }

        public final Matrix3x3 transpose(Matrix3x3 m2) {
            n.g(m2, "m");
            Matrix3x3 matrix3x3 = new Matrix3x3();
            transpose(matrix3x3, m2);
            return matrix3x3;
        }

        public final Matrix3x3 fromFloatArrayColumnSeq(float[] array) {
            n.g(array, "array");
            if (array.length >= 9) {
                Matrix3x3 matrix3x3 = new Matrix3x3();
                fromFloatArrayColumnSeq(matrix3x3, array);
                return matrix3x3;
            }
            throw new IllegalArgumentException("Failed requirement.");
        }

        public final Matrix3x3 fromFloatArrayRowSeq(float[] array) {
            n.g(array, "array");
            if (array.length >= 9) {
                Matrix3x3 matrix3x3 = new Matrix3x3();
                fromFloatArrayRowSeq(matrix3x3, array);
                return matrix3x3;
            }
            throw new IllegalArgumentException("Failed requirement.");
        }

        public final Matrix3x3 times(Matrix3x3 a2, Matrix3x3 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            Matrix3x3 matrix3x3 = new Matrix3x3();
            times(matrix3x3, a2, b2);
            return matrix3x3;
        }

        public final void times(Vector3 result, Matrix3x3 a2, Vector3 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            Vector3.Companion companion = Vector3.Companion;
            result.setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), b2));
            result.setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), b2));
            result.setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), b2));
        }

        public final float[] toFloatArrayColumnSeq(Matrix3x3 mat) {
            n.g(mat, "mat");
            float[] fArr = new float[9];
            toFloatArrayColumnSeq(fArr, mat);
            return fArr;
        }

        public final float[] toFloatArrayRowSeq(Matrix3x3 mat) {
            n.g(mat, "mat");
            float[] fArr = new float[9];
            toFloatArrayRowSeq(fArr, mat);
            return fArr;
        }

        public final Vector3 times(Matrix3x3 a2, Vector3 b2) {
            n.g(a2, "a");
            n.g(b2, "b");
            Vector3 vector3 = new Vector3();
            times(vector3, a2, b2);
            return vector3;
        }

        public final Quaternion toQuaternion(Matrix3x3 mat) {
            n.g(mat, "mat");
            Quaternion quaternion = new Quaternion();
            toQuaternion(quaternion, mat);
            return quaternion;
        }

        public final Matrix3x3 inverse(Matrix3x3 m2) {
            n.g(m2, "m");
            Matrix3x3 matrix3x3 = new Matrix3x3();
            inverse(matrix3x3, m2);
            return matrix3x3;
        }

        public final Matrix3x3 fromQuaternion(Quaternion q2) {
            n.g(q2, "q");
            Matrix3x3 matrix3x3 = new Matrix3x3();
            fromQuaternion(matrix3x3, q2);
            return matrix3x3;
        }
    }

    public Matrix3x3() {
        float[] fArr = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        this.mArray = fArr;
        this.mColumn1 = new Vector3(fArr, 0);
        this.mColumn2 = new Vector3(fArr, 3);
        this.mColumn3 = new Vector3(fArr, 6);
    }

    public final Matrix3x3 dec() {
        this.mColumn1.dec();
        this.mColumn2.dec();
        this.mColumn3.dec();
        return this;
    }

    public final Matrix3x3 div(float f2) {
        return new Matrix3x3(getX().div(f2), getY().div(f2), getZ().div(f2));
    }

    public final Vector3 get(int i2) {
        if (i2 == 0) {
            return getX();
        }
        if (i2 == 1) {
            return getY();
        }
        if (i2 == 2) {
            return getZ();
        }
        throw new IllegalArgumentException("column must be in 0..2");
    }

    public final float[] getData() {
        return this.mArray;
    }

    public final int getDataOffset() {
        return this.mOffset;
    }

    public final Vector3 getX() {
        return this.mColumn1;
    }

    public final Vector3 getY() {
        return this.mColumn2;
    }

    public final Vector3 getZ() {
        return this.mColumn3;
    }

    public final Matrix3x3 inc() {
        this.mColumn1.inc();
        this.mColumn2.inc();
        this.mColumn3.inc();
        return this;
    }

    public final Matrix3x3 minus(float f2) {
        return new Matrix3x3(getX().minus(f2), getY().minus(f2), getZ().minus(f2));
    }

    public final Matrix3x3 plus(float f2) {
        return new Matrix3x3(getX().plus(f2), getY().plus(f2), getZ().plus(f2));
    }

    public final void set(int i2, Vector3 v2) {
        n.g(v2, "v");
        get(i2).setXyz(v2);
    }

    public final Matrix3x3 times(float f2) {
        return new Matrix3x3(getX().times(f2), getY().times(f2), getZ().times(f2));
    }

    public final float[] toFloatArrayColumnSeq() {
        float[] fArr = new float[9];
        Companion.toFloatArrayColumnSeq(fArr, this);
        return fArr;
    }

    public final float[] toFloatArrayRowSeq() {
        float[] fArr = new float[9];
        Companion.toFloatArrayRowSeq(fArr, this);
        return fArr;
    }

    public String toString() {
        return g.e("\n            |" + getX().getX() + ' ' + getY().getX() + ' ' + getZ().getX() + "|\n            |" + getX().getY() + ' ' + getY().getY() + ' ' + getZ().getY() + "|\n            |" + getX().getZ() + ' ' + getY().getZ() + ' ' + getZ().getZ() + "|\n            ");
    }

    public final Matrix3x3 transpose() {
        Matrix3x3 matrix3x3 = new Matrix3x3();
        Companion.transpose(matrix3x3, this);
        return matrix3x3;
    }

    public final Matrix3x3 unaryMinus() {
        return new Matrix3x3(getX().unaryMinus(), getY().unaryMinus(), getZ().unaryMinus());
    }

    public final void set(int i2, int i3, float f2) {
        get(i2).set(i3, f2);
    }

    public final Matrix3x3 times(Matrix3x3 m2) {
        n.g(m2, "m");
        Matrix3x3 matrix3x3 = new Matrix3x3();
        Companion.times(matrix3x3, this, m2);
        return matrix3x3;
    }

    public final Vector3 times(Vector3 v2) {
        n.g(v2, "v");
        Vector3 vector3 = new Vector3();
        Companion.times(vector3, this, v2);
        return vector3;
    }

    public final float get(int i2, int i3) {
        return get(i2).get(i3);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Matrix3x3(Vector3 x2, Vector3 y2, Vector3 z2) {
        this();
        n.g(x2, "x");
        n.g(y2, "y");
        n.g(z2, "z");
        Vector3.Companion companion = Vector3.Companion;
        companion.fromVector3(this.mColumn1, x2);
        companion.fromVector3(this.mColumn2, y2);
        companion.fromVector3(this.mColumn3, z2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Matrix3x3(Matrix3x3 m2) {
        this(m2.getX(), m2.getY(), m2.getZ());
        n.g(m2, "m");
    }

    public Matrix3x3(float[] array, int i2) {
        n.g(array, "array");
        if (array.length - i2 >= 9) {
            this.mArray = array;
            this.mOffset = i2;
            this.mColumn1 = new Vector3(array, i2);
            this.mColumn2 = new Vector3(array, i2 + 3);
            this.mColumn3 = new Vector3(array, i2 + 6);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public /* synthetic */ Matrix3x3(float[] fArr, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(fArr, (i3 & 2) != 0 ? 0 : i2);
    }
}
