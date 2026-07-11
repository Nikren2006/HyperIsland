package miuix.mgl.math;

import f1.g;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.mgl.math.Math;
import miuix.mgl.math.Vector4;

/* JADX INFO: loaded from: classes3.dex */
public final class Matrix4x4 {
    public static final Companion Companion = new Companion(null);
    private final float[] mArray;
    private final Vector4 mColumn1;
    private final Vector4 mColumn2;
    private final Vector4 mColumn3;
    private final Vector4 mColumn4;
    private int mOffset;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void div(Matrix4x4 result, float f2) {
            n.g(result, "result");
            Vector4.Companion companion = Vector4.Companion;
            companion.div(result.getX(), f2);
            companion.div(result.getY(), f2);
            companion.div(result.getZ(), f2);
            companion.div(result.getW(), f2);
        }

        public final void fromFloatArrayColumnSeq(Matrix4x4 result, float[] array) {
            n.g(result, "result");
            n.g(array, "array");
            if (array.length < 16) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result.getX().set(array[0], array[1], array[2], array[3]);
            result.getY().set(array[4], array[5], array[6], array[7]);
            result.getZ().set(array[8], array[9], array[10], array[11]);
            result.getW().set(array[12], array[13], array[14], array[15]);
        }

        public final void fromFloatArrayRowSeq(Matrix4x4 result, float[] array) {
            n.g(result, "result");
            n.g(array, "array");
            if (array.length < 16) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result.getX().set(array[0], array[4], array[8], array[12]);
            result.getY().set(array[1], array[5], array[9], array[13]);
            result.getZ().set(array[2], array[6], array[10], array[14]);
            result.getW().set(array[3], array[7], array[11], array[15]);
        }

        public final void fromLookForward(Matrix4x4 result, Vector3 eye, Vector3 forward, Vector3 up) {
            n.g(result, "result");
            n.g(eye, "eye");
            n.g(forward, "forward");
            n.g(up, "up");
            Vector3 vector3 = new Vector3();
            Vector3 vector32 = new Vector3();
            Vector3 vector33 = new Vector3();
            Math.Companion.lookForwardToNormalizedRUF(vector3, vector32, vector33, forward, up);
            fromRUF(result, vector3, vector32, vector33);
            result.getW().set(eye.getX(), eye.getY(), eye.getZ(), 1.0f);
        }

        public final void fromLookForwardReverseZ(Matrix4x4 result, Vector3 eye, Vector3 forward, Vector3 up) {
            n.g(result, "result");
            n.g(eye, "eye");
            n.g(forward, "forward");
            n.g(up, "up");
            Vector3 vector3 = new Vector3();
            Vector3 vector32 = new Vector3();
            Vector3 vector33 = new Vector3();
            Math.Companion.lookForwardToNormalizedRUF(vector3, vector32, vector33, forward, up);
            vector33.setReverse();
            fromRUF(result, vector3, vector32, vector33);
            result.getW().set(eye.getX(), eye.getY(), eye.getZ(), 1.0f);
        }

        public final void fromMatrix3x3(Matrix4x4 result, Matrix3x3 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.getX().set(mat.getX().getX(), mat.getX().getY(), mat.getX().getZ(), 0.0f);
            result.getY().set(mat.getY().getX(), mat.getY().getY(), mat.getY().getZ(), 0.0f);
            result.getZ().set(mat.getZ().getX(), mat.getZ().getY(), mat.getZ().getZ(), 0.0f);
            result.getW().set(0.0f, 0.0f, 0.0f, 1.0f);
        }

        public final void fromMatrix4x4(Matrix4x4 result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.getX().set(mat.getX().getX(), mat.getX().getY(), mat.getX().getZ(), mat.getX().getW());
            result.getY().set(mat.getY().getX(), mat.getY().getY(), mat.getY().getZ(), mat.getY().getW());
            result.getZ().set(mat.getZ().getX(), mat.getZ().getY(), mat.getZ().getZ(), mat.getZ().getW());
            result.getW().set(mat.getW().getX(), mat.getW().getY(), mat.getW().getZ(), mat.getW().getW());
        }

        public final void fromOrtho(Matrix4x4 result, float f2, float f3, float f4, float f5, float f6, float f7) {
            n.g(result, "result");
            result.getX().set(2.0f / (f3 - 1.0f), 0.0f, 0.0f, 0.0f);
            float f8 = f5 - f4;
            result.getY().set(0.0f, 2.0f / f8, 0.0f, 0.0f);
            float f9 = f7 - f6;
            result.getZ().set(0.0f, 0.0f, (-2.0f) / f9, 0.0f);
            result.getW().set((-(f3 + f2)) / (f3 - f2), (-(f5 + f4)) / f8, (-(f7 + f6)) / f9, 1.0f);
        }

        public final void fromPerspective(Matrix4x4 result, float f2, float f3, float f4, float f5) {
            n.g(result, "result");
            float fTan = 1.0f / ((float) java.lang.Math.tan(Math.Companion.radians(f2) * 0.5f));
            float f6 = f5 - f4;
            float f7 = (f5 + f4) / f6;
            result.getX().set(fTan / f3, 0.0f, 0.0f, 0.0f);
            result.getY().set(0.0f, fTan, 0.0f, 0.0f);
            result.getZ().set(0.0f, 0.0f, f7, 1.0f);
            result.getW().set(0.0f, 0.0f, -(((f5 * 2.0f) * f4) / f6), 0.0f);
        }

        public final void fromPosition(Matrix4x4 result, Vector3 position) {
            n.g(result, "result");
            n.g(position, "position");
            fromPosition(result, position.getX(), position.getY(), position.getZ());
        }

        public final void fromRUF(Matrix4x4 result, Vector3 right, Vector3 up, Vector3 forward) {
            n.g(result, "result");
            n.g(right, "right");
            n.g(up, "up");
            n.g(forward, "forward");
            Vector4 x2 = result.getX();
            x2.setX(right.getX());
            x2.setY(right.getY());
            x2.setZ(right.getZ());
            result.getX().setW(0.0f);
            Vector4 y2 = result.getY();
            y2.setX(up.getX());
            y2.setY(up.getY());
            y2.setZ(up.getZ());
            result.getY().setW(0.0f);
            Vector4 z2 = result.getZ();
            z2.setX(forward.getX());
            z2.setY(forward.getY());
            z2.setZ(forward.getZ());
            result.getZ().setW(0.0f);
            result.getW().set(0.0f, 0.0f, 0.0f, 1.0f);
        }

        public final void fromScale(Matrix4x4 result, Vector3 scale) {
            n.g(result, "result");
            n.g(scale, "scale");
            result.getX().set(scale.getX(), 0.0f, 0.0f, 0.0f);
            result.getY().set(0.0f, scale.getY(), 0.0f, 0.0f);
            result.getZ().set(0.0f, 0.0f, scale.getZ(), 0.0f);
            result.getW().set(0.0f, 0.0f, 0.0f, 1.0f);
        }

        public final void fromTRS(Matrix4x4 result, Vector3 translation, Quaternion rotation, Vector3 scale) {
            n.g(result, "result");
            n.g(translation, "translation");
            n.g(rotation, "rotation");
            n.g(scale, "scale");
            float x2 = translation.getX();
            float y2 = translation.getY();
            float z2 = translation.getZ();
            float x3 = rotation.getX();
            float y3 = rotation.getY();
            float z3 = rotation.getZ();
            float w2 = rotation.getW();
            float x4 = scale.getX();
            float y4 = scale.getY();
            float z4 = scale.getZ();
            float f2 = y3 * 2.0f;
            float f3 = f2 * y3;
            float f4 = z3 * 2.0f;
            float f5 = f4 * z3;
            result.getX().setX(((1.0f - f3) - f5) * x4);
            float f6 = 2.0f * x3;
            float f7 = y3 * f6;
            float f8 = f4 * w2;
            result.getX().setY((f7 + f8) * x4);
            float f9 = f6 * z3;
            float f10 = f2 * w2;
            result.getX().setZ(x4 * (f9 - f10));
            result.getX().setW(0.0f);
            result.getY().setX((f7 - f8) * y4);
            float f11 = 1.0f - (x3 * f6);
            result.getY().setY((f11 - f5) * y4);
            float f12 = f2 * z3;
            float f13 = f6 * w2;
            result.getY().setZ((f12 + f13) * y4);
            result.getY().setW(0.0f);
            result.getZ().setX((f9 + f10) * z4);
            result.getZ().setY((f12 - f13) * z4);
            result.getZ().setZ((f11 - f3) * z4);
            result.getZ().setW(0.0f);
            result.getW().setX(x2);
            result.getW().setY(y2);
            result.getW().setZ(z2);
            result.getW().setW(1.0f);
        }

        public final Matrix4x4 identity() {
            return new Matrix4x4();
        }

        public final void inverse(Matrix4x4 result, Matrix4x4 m2) {
            n.g(result, "result");
            n.g(m2, "m");
            float z2 = m2.getZ().getZ() * m2.getW().getW();
            float z3 = m2.getW().getZ() * m2.getZ().getW();
            float z4 = m2.getY().getZ() * m2.getW().getW();
            float z5 = m2.getW().getZ() * m2.getY().getW();
            float z6 = m2.getY().getZ() * m2.getZ().getW();
            float z7 = m2.getZ().getZ() * m2.getY().getW();
            float z8 = m2.getX().getZ() * m2.getW().getW();
            float z9 = m2.getW().getZ() * m2.getX().getW();
            float z10 = m2.getX().getZ() * m2.getZ().getW();
            float z11 = m2.getZ().getZ() * m2.getX().getW();
            float z12 = m2.getX().getZ() * m2.getY().getW();
            float z13 = m2.getY().getZ() * m2.getX().getW();
            result.getX().setX((m2.getY().getY() * z2) + (m2.getZ().getY() * z5) + (m2.getW().getY() * z6));
            Vector4 x2 = result.getX();
            x2.setX(x2.getX() - (((m2.getY().getY() * z3) + (m2.getZ().getY() * z4)) + (m2.getW().getY() * z7)));
            result.getX().setY((m2.getX().getY() * z3) + (m2.getZ().getY() * z8) + (m2.getW().getY() * z11));
            Vector4 x3 = result.getX();
            x3.setY(x3.getY() - (((m2.getX().getY() * z2) + (m2.getZ().getY() * z9)) + (m2.getW().getY() * z10)));
            result.getX().setZ((m2.getX().getY() * z4) + (m2.getY().getY() * z9) + (m2.getW().getY() * z12));
            Vector4 x4 = result.getX();
            x4.setZ(x4.getZ() - (((m2.getX().getY() * z5) + (m2.getY().getY() * z8)) + (m2.getW().getY() * z13)));
            result.getX().setW((m2.getX().getY() * z7) + (m2.getY().getY() * z10) + (m2.getZ().getY() * z13));
            Vector4 x5 = result.getX();
            x5.setW(x5.getW() - (((m2.getX().getY() * z6) + (m2.getY().getY() * z11)) + (m2.getZ().getY() * z12)));
            result.getY().setX((m2.getY().getX() * z3) + (m2.getZ().getX() * z4) + (m2.getW().getX() * z7));
            Vector4 y2 = result.getY();
            y2.setX(y2.getX() - (((m2.getY().getX() * z2) + (m2.getZ().getX() * z5)) + (m2.getW().getX() * z6)));
            result.getY().setY((z2 * m2.getX().getX()) + (m2.getZ().getX() * z9) + (m2.getW().getX() * z10));
            Vector4 y3 = result.getY();
            y3.setY(y3.getY() - (((z3 * m2.getX().getX()) + (m2.getZ().getX() * z8)) + (m2.getW().getX() * z11)));
            result.getY().setZ((z5 * m2.getX().getX()) + (z8 * m2.getY().getX()) + (m2.getW().getX() * z13));
            Vector4 y4 = result.getY();
            y4.setZ(y4.getZ() - (((z4 * m2.getX().getX()) + (z9 * m2.getY().getX())) + (m2.getW().getX() * z12)));
            result.getY().setW((z6 * m2.getX().getX()) + (z11 * m2.getY().getX()) + (z12 * m2.getZ().getX()));
            Vector4 y5 = result.getY();
            y5.setW(y5.getW() - (((z7 * m2.getX().getX()) + (z10 * m2.getY().getX())) + (z13 * m2.getZ().getX())));
            float x6 = m2.getZ().getX() * m2.getW().getY();
            float x7 = m2.getW().getX() * m2.getZ().getY();
            float x8 = m2.getY().getX() * m2.getW().getY();
            float x9 = m2.getW().getX() * m2.getY().getY();
            float x10 = m2.getY().getX() * m2.getZ().getY();
            float x11 = m2.getZ().getX() * m2.getY().getY();
            float x12 = m2.getX().getX() * m2.getW().getY();
            float x13 = m2.getW().getX() * m2.getX().getY();
            float x14 = m2.getX().getX() * m2.getZ().getY();
            float x15 = m2.getZ().getX() * m2.getX().getY();
            float x16 = m2.getX().getX() * m2.getY().getY();
            float x17 = m2.getY().getX() * m2.getX().getY();
            result.getZ().setX((m2.getY().getW() * x6) + (m2.getZ().getW() * x9) + (m2.getW().getW() * x10));
            Vector4 z14 = result.getZ();
            z14.setX(z14.getX() - (((m2.getY().getW() * x7) + (m2.getZ().getW() * x8)) + (m2.getW().getW() * x11)));
            result.getZ().setY((m2.getX().getW() * x7) + (m2.getZ().getW() * x12) + (m2.getW().getW() * x15));
            Vector4 z15 = result.getZ();
            z15.setY(z15.getY() - (((m2.getX().getW() * x6) + (m2.getZ().getW() * x13)) + (m2.getW().getW() * x14)));
            result.getZ().setZ((m2.getX().getW() * x8) + (m2.getY().getW() * x13) + (m2.getW().getW() * x16));
            Vector4 z16 = result.getZ();
            z16.setZ(z16.getZ() - (((m2.getX().getW() * x9) + (m2.getY().getW() * x12)) + (m2.getW().getW() * x17)));
            result.getZ().setW((m2.getX().getW() * x11) + (m2.getY().getW() * x14) + (m2.getZ().getW() * x17));
            Vector4 z17 = result.getZ();
            z17.setW(z17.getW() - (((m2.getX().getW() * x10) + (m2.getY().getW() * x15)) + (m2.getZ().getW() * x16)));
            result.getW().setX((m2.getZ().getZ() * x8) + (m2.getW().getZ() * x11) + (m2.getY().getZ() * x7));
            Vector4 w2 = result.getW();
            w2.setX(w2.getX() - (((m2.getW().getZ() * x10) + (m2.getY().getZ() * x6)) + (m2.getZ().getZ() * x9)));
            result.getW().setY((m2.getW().getZ() * x14) + (x6 * m2.getX().getZ()) + (m2.getZ().getZ() * x13));
            Vector4 w3 = result.getW();
            w3.setY(w3.getY() - (((m2.getZ().getZ() * x12) + (m2.getW().getZ() * x15)) + (x7 * m2.getX().getZ())));
            result.getW().setZ((x12 * m2.getY().getZ()) + (m2.getW().getZ() * x17) + (x9 * m2.getX().getZ()));
            Vector4 w4 = result.getW();
            w4.setZ(w4.getZ() - (((m2.getW().getZ() * x16) + (x8 * m2.getX().getZ())) + (x13 * m2.getY().getZ())));
            result.getW().setW((x16 * m2.getZ().getZ()) + (x10 * m2.getX().getZ()) + (x15 * m2.getY().getZ()));
            Vector4 w5 = result.getW();
            w5.setW(w5.getW() - (((x14 * m2.getY().getZ()) + (x17 * m2.getZ().getZ())) + (x11 * m2.getX().getZ())));
            div(result, (m2.getX().getX() * result.getX().getX()) + (m2.getY().getX() * result.getX().getY()) + (m2.getZ().getX() * result.getX().getZ()) + (m2.getW().getX() * result.getX().getW()));
        }

        public final void inverseGaussJordan(Matrix4x4 result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromMatrix4x4(matrix4x4, mat);
            int i2 = 0;
            while (i2 < 4) {
                float f2 = matrix4x4.get(i2).get(i2) < 0.0f ? -matrix4x4.get(i2).get(i2) : matrix4x4.get(i2).get(i2);
                int i3 = i2 + 1;
                int i4 = i2;
                for (int i5 = i3; i5 < 4; i5++) {
                    float f3 = matrix4x4.get(i5).get(i2) < 0.0f ? -matrix4x4.get(i5).get(i2) : matrix4x4.get(i5).get(i2);
                    if (f3 > f2) {
                        i4 = i5;
                        f2 = f3;
                    }
                }
                if (i4 != i2) {
                    Vector4.Companion companion = Vector4.Companion;
                    companion.swap(matrix4x4.get(i2), matrix4x4.get(i4));
                    companion.swap(result.get(i2), result.get(i4));
                }
                float f4 = matrix4x4.get(i2).get(i2);
                for (int i6 = 0; i6 < 4; i6++) {
                    Vector4 vector4 = matrix4x4.get(i2);
                    vector4.set(i6, vector4.get(i6) / f4);
                    Vector4 vector42 = result.get(i2);
                    vector42.set(i6, vector42.get(i6) / f4);
                }
                for (int i7 = 0; i7 < 4; i7++) {
                    if (i7 != i2) {
                        float f5 = matrix4x4.get(i7).get(i2);
                        for (int i8 = 0; i8 < 4; i8++) {
                            Vector4 vector43 = matrix4x4.get(i7);
                            vector43.set(i8, vector43.get(i8) - (matrix4x4.get(i2).get(i8) * f5));
                            Vector4 vector44 = result.get(i7);
                            vector44.set(i8, vector44.get(i8) - (result.get(i2).get(i8) * f5));
                        }
                    }
                }
                i2 = i3;
            }
        }

        public final void setIdentity(Matrix4x4 result) {
            n.g(result, "result");
            result.getX().set(1.0f, 0.0f, 0.0f, 0.0f);
            result.getY().set(0.0f, 1.0f, 0.0f, 0.0f);
            result.getZ().set(0.0f, 0.0f, 1.0f, 0.0f);
            result.getW().set(0.0f, 0.0f, 0.0f, 1.0f);
        }

        public final void times(Matrix4x4 result, Matrix4x4 a2, Matrix4x4 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            Vector4 x2 = result.getX();
            Vector4.Companion companion = Vector4.Companion;
            x2.setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), a2.getW().getX(), b2.getX()));
            result.getX().setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), a2.getW().getY(), b2.getX()));
            result.getX().setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), a2.getW().getZ(), b2.getX()));
            result.getX().setW(companion.dot(a2.getX().getW(), a2.getY().getW(), a2.getZ().getW(), a2.getW().getW(), b2.getX()));
            result.getY().setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), a2.getW().getX(), b2.getY()));
            result.getY().setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), a2.getW().getY(), b2.getY()));
            result.getY().setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), a2.getW().getZ(), b2.getY()));
            result.getY().setW(companion.dot(a2.getX().getW(), a2.getY().getW(), a2.getZ().getW(), a2.getW().getW(), b2.getY()));
            result.getZ().setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), a2.getW().getX(), b2.getZ()));
            result.getZ().setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), a2.getW().getY(), b2.getZ()));
            result.getZ().setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), a2.getW().getZ(), b2.getZ()));
            result.getZ().setW(companion.dot(a2.getX().getW(), a2.getY().getW(), a2.getZ().getW(), a2.getW().getW(), b2.getZ()));
            result.getW().setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), a2.getW().getX(), b2.getW()));
            result.getW().setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), a2.getW().getY(), b2.getW()));
            result.getW().setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), a2.getW().getZ(), b2.getW()));
            result.getW().setW(companion.dot(a2.getX().getW(), a2.getY().getW(), a2.getZ().getW(), a2.getW().getW(), b2.getW()));
        }

        public final void toEulerAngle(Vector3 result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.set(0, (float) java.lang.Math.asin(-Math.Companion.clamp(mat.getZ().getY(), -1.0f, 1.0f)));
            if (java.lang.Math.abs(mat.getZ().getY()) < 0.9999999f) {
                result.set(1, (float) java.lang.Math.atan2(mat.getZ().getX(), mat.getZ().getZ()));
                result.set(2, (float) java.lang.Math.atan2(mat.getX().getY(), mat.getY().getY()));
            } else {
                result.set(1, (float) java.lang.Math.atan2(-mat.getX().getZ(), mat.getX().getX()));
                result.set(2, 0.0f);
            }
        }

        public final void toFloatArrayColumnSeq(float[] result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            if (result.length < 16) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result[0] = mat.getX().getX();
            result[1] = mat.getX().getY();
            result[2] = mat.getX().getZ();
            result[3] = mat.getX().getW();
            result[4] = mat.getY().getX();
            result[5] = mat.getY().getY();
            result[6] = mat.getY().getZ();
            result[7] = mat.getY().getW();
            result[8] = mat.getZ().getX();
            result[9] = mat.getZ().getY();
            result[10] = mat.getZ().getZ();
            result[11] = mat.getZ().getW();
            result[12] = mat.getW().getX();
            result[13] = mat.getW().getY();
            result[14] = mat.getW().getZ();
            result[15] = mat.getW().getW();
        }

        public final void toFloatArrayRowSeq(float[] result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            if (result.length < 16) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            result[0] = mat.getX().getX();
            result[1] = mat.getY().getX();
            result[2] = mat.getZ().getX();
            result[3] = mat.getW().getX();
            result[4] = mat.getX().getY();
            result[5] = mat.getY().getY();
            result[6] = mat.getZ().getY();
            result[7] = mat.getW().getY();
            result[8] = mat.getX().getZ();
            result[9] = mat.getY().getZ();
            result[10] = mat.getZ().getZ();
            result[11] = mat.getW().getZ();
            result[12] = mat.getX().getW();
            result[13] = mat.getY().getW();
            result[14] = mat.getZ().getW();
            result[15] = mat.getW().getW();
        }

        public final void toForward(Vector3 result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.set(mat.getZ().getX(), mat.getZ().getY(), mat.getZ().getZ());
        }

        public final void toMatrix3x3(Matrix3x3 result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.getX().set(mat.getX().getX(), mat.getX().getY(), mat.getX().getZ());
            result.getY().set(mat.getY().getX(), mat.getY().getY(), mat.getY().getZ());
            result.getZ().set(mat.getZ().getX(), mat.getZ().getY(), mat.getZ().getZ());
        }

        public final void toPosition(Vector3 result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.set(mat.getW().getX(), mat.getW().getY(), mat.getW().getZ());
        }

        public final void toQuaternion(Quaternion result, Matrix4x4 mat) {
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

        public final void toRight(Vector3 result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.set(mat.getX().getX(), mat.getX().getY(), mat.getX().getZ());
        }

        public final void toScale(Vector3 result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            Math.Companion companion = Math.Companion;
            result.set(companion.magnitude(mat.getX().getX(), mat.getX().getY(), mat.getX().getZ()), companion.magnitude(mat.getY().getX(), mat.getY().getY(), mat.getY().getZ()), companion.magnitude(mat.getZ().getX(), mat.getZ().getY(), mat.getZ().getZ()));
        }

        public final void toTRS(Vector3 translation, Quaternion rotation, Vector3 scale, Matrix4x4 mat) {
            n.g(translation, "translation");
            n.g(rotation, "rotation");
            n.g(scale, "scale");
            n.g(mat, "mat");
            translation.setX(mat.getW().getX());
            translation.setY(mat.getW().getY());
            translation.setZ(mat.getW().getZ());
            float x2 = mat.getX().getX();
            float y2 = mat.getX().getY();
            float z2 = mat.getX().getZ();
            float x3 = mat.getY().getX();
            float y3 = mat.getY().getY();
            float z3 = mat.getY().getZ();
            float x4 = mat.getZ().getX();
            float y4 = mat.getZ().getY();
            float z4 = mat.getZ().getZ();
            float f2 = (((y3 * z4) - (z3 * y4)) * x2) + (((z3 * x4) - (x3 * z4)) * y2) + (((x3 * y4) - (y3 * x4)) * z2);
            Math.Companion companion = Math.Companion;
            scale.setX(companion.magnitude(x2, y2, z2));
            scale.setY(companion.magnitude(x3, y3, z3));
            scale.setZ(companion.magnitude(x4, y4, z4));
            if (f2 < 0.0f) {
                scale.setX(-scale.getX());
                scale.setY(-scale.getY());
                scale.setZ(-scale.getZ());
            }
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromMatrix4x4(matrix4x4, mat);
            if (java.lang.Math.abs(f2) <= 1.1920929E-7f) {
                rotation.setIdentity();
                return;
            }
            Vector4.Companion companion2 = Vector4.Companion;
            companion2.div(matrix4x4.getX(), scale.getX());
            companion2.div(matrix4x4.getY(), scale.getY());
            companion2.div(matrix4x4.getZ(), scale.getZ());
            toQuaternion(rotation, matrix4x4);
        }

        public final void toUp(Vector3 result, Matrix4x4 mat) {
            n.g(result, "result");
            n.g(mat, "mat");
            result.set(mat.getY().getX(), mat.getY().getY(), mat.getY().getZ());
        }

        public final void transpose(Matrix4x4 result, Matrix4x4 m2) {
            n.g(result, "result");
            n.g(m2, "m");
            result.getX().set(m2.getX().getX(), m2.getY().getX(), m2.getZ().getX(), m2.getW().getX());
            result.getY().set(m2.getX().getY(), m2.getY().getY(), m2.getZ().getY(), m2.getW().getY());
            result.getZ().set(m2.getX().getZ(), m2.getY().getZ(), m2.getZ().getZ(), m2.getW().getZ());
            result.getW().set(m2.getX().getW(), m2.getY().getW(), m2.getZ().getW(), m2.getW().getW());
        }

        private Companion() {
        }

        public final Matrix4x4 fromPosition(Vector3 position) {
            n.g(position, "position");
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromPosition(matrix4x4, position);
            return matrix4x4;
        }

        public final Vector3 toForward(Matrix4x4 mat) {
            n.g(mat, "mat");
            Vector3 vector3 = new Vector3();
            toForward(vector3, mat);
            return vector3;
        }

        public final Vector3 toPosition(Matrix4x4 mat) {
            n.g(mat, "mat");
            Vector3 vector3 = new Vector3();
            toPosition(vector3, mat);
            return vector3;
        }

        public final Vector3 toRight(Matrix4x4 mat) {
            n.g(mat, "mat");
            Vector3 vector3 = new Vector3();
            toRight(vector3, mat);
            return vector3;
        }

        public final Vector3 toUp(Matrix4x4 mat) {
            n.g(mat, "mat");
            Vector3 vector3 = new Vector3();
            toUp(vector3, mat);
            return vector3;
        }

        public final void fromPosition(Matrix4x4 result, float f2, float f3, float f4) {
            n.g(result, "result");
            result.getX().set(1.0f, 0.0f, 0.0f, f2);
            result.getY().set(0.0f, 1.0f, 0.0f, f3);
            result.getZ().set(0.0f, 0.0f, 1.0f, f4);
            result.getW().set(0.0f, 0.0f, 0.0f, 1.0f);
        }

        public final Matrix3x3 toMatrix3x3(Matrix4x4 mat) {
            n.g(mat, "mat");
            Matrix3x3 matrix3x3 = new Matrix3x3();
            toMatrix3x3(matrix3x3, mat);
            return matrix3x3;
        }

        public final Matrix4x4 div(float f2) {
            Matrix4x4 matrix4x4 = new Matrix4x4();
            div(matrix4x4, f2);
            return matrix4x4;
        }

        public final Matrix4x4 fromMatrix3x3(Matrix3x3 mat) {
            n.g(mat, "mat");
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromMatrix3x3(matrix4x4, mat);
            return matrix4x4;
        }

        public final Matrix4x4 fromMatrix4x4(Matrix4x4 mat) {
            n.g(mat, "mat");
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromMatrix4x4(matrix4x4, mat);
            return matrix4x4;
        }

        public final Matrix4x4 fromOrtho(float f2, float f3, float f4, float f5, float f6, float f7) {
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromOrtho(matrix4x4, f2, f3, f4, f5, f6, f7);
            return matrix4x4;
        }

        public final Matrix4x4 fromScale(Vector3 scale) {
            n.g(scale, "scale");
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromScale(matrix4x4, scale);
            return matrix4x4;
        }

        public final Vector3 toScale(Matrix4x4 mat) {
            n.g(mat, "mat");
            Vector3 vector3 = new Vector3();
            toScale(vector3, mat);
            return vector3;
        }

        public final Matrix4x4 fromPerspective(float f2, float f3, float f4, float f5) {
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromPerspective(matrix4x4, f2, f3, f4, f5);
            return matrix4x4;
        }

        public final void fromFloatArrayColumnSeq(Matrix4x4 result, double[] array) {
            n.g(result, "result");
            n.g(array, "array");
            if (array.length >= 16) {
                result.getX().set((float) array[0], (float) array[1], (float) array[2], (float) array[3]);
                result.getY().set((float) array[4], (float) array[5], (float) array[6], (float) array[7]);
                result.getZ().set((float) array[8], (float) array[9], (float) array[10], (float) array[11]);
                result.getW().set((float) array[12], (float) array[13], (float) array[14], (float) array[15]);
                return;
            }
            throw new IllegalArgumentException("Failed requirement.");
        }

        public final Matrix4x4 fromLookForward(Vector3 eye, Vector3 forward, Vector3 up) {
            n.g(eye, "eye");
            n.g(forward, "forward");
            n.g(up, "up");
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromLookForward(matrix4x4, eye, forward, up);
            return matrix4x4;
        }

        public final Vector3 toEulerAngle(Matrix4x4 mat) {
            n.g(mat, "mat");
            Vector3 vector3 = new Vector3();
            toEulerAngle(vector3, mat);
            return vector3;
        }

        public final Matrix4x4 fromPosition(float f2, float f3, float f4) {
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromPosition(matrix4x4, f2, f3, f4);
            return matrix4x4;
        }

        public final Matrix4x4 inverseGaussJordan(Matrix4x4 mat) {
            n.g(mat, "mat");
            Matrix4x4 matrix4x4 = new Matrix4x4();
            inverse(matrix4x4, mat);
            return matrix4x4;
        }

        public final Quaternion toQuaternion(Matrix4x4 mat) {
            n.g(mat, "mat");
            Quaternion quaternion = new Quaternion();
            toQuaternion(quaternion, mat);
            return quaternion;
        }

        public final Matrix4x4 fromRUF(Vector3 right, Vector3 up, Vector3 forward) {
            n.g(right, "right");
            n.g(up, "up");
            n.g(forward, "forward");
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromRUF(matrix4x4, right, up, forward);
            return matrix4x4;
        }

        public final void times(Vector4 result, Matrix4x4 a2, Vector4 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            Vector4.Companion companion = Vector4.Companion;
            result.setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), a2.getW().getX(), b2));
            result.setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), a2.getW().getY(), b2));
            result.setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), a2.getW().getZ(), b2));
            result.setW(companion.dot(a2.getX().getW(), a2.getY().getW(), a2.getZ().getW(), a2.getW().getW(), b2));
        }

        public final void times(Vector3 result, Matrix4x4 a2, Vector4 b2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            Vector4.Companion companion = Vector4.Companion;
            result.setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), a2.getW().getX(), b2));
            result.setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), a2.getW().getY(), b2));
            result.setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), a2.getW().getZ(), b2));
        }

        public final void times(Vector3 result, Matrix4x4 a2, Vector3 b2, float f2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            Vector4.Companion companion = Vector4.Companion;
            result.setX(companion.dot(a2.getX().getX(), a2.getY().getX(), a2.getZ().getX(), a2.getW().getX(), b2, f2));
            result.setY(companion.dot(a2.getX().getY(), a2.getY().getY(), a2.getZ().getY(), a2.getW().getY(), b2, f2));
            result.setZ(companion.dot(a2.getX().getZ(), a2.getY().getZ(), a2.getZ().getZ(), a2.getW().getZ(), b2, f2));
        }

        public final Matrix4x4 fromTRS(Vector3 translation, Quaternion rotation, Vector3 scale) {
            n.g(translation, "translation");
            n.g(rotation, "rotation");
            n.g(scale, "scale");
            Matrix4x4 matrix4x4 = new Matrix4x4();
            fromTRS(matrix4x4, translation, rotation, scale);
            return matrix4x4;
        }
    }

    public Matrix4x4() {
        float[] fArr = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        this.mArray = fArr;
        this.mColumn1 = new Vector4(fArr, 0);
        this.mColumn2 = new Vector4(fArr, 4);
        this.mColumn3 = new Vector4(fArr, 8);
        this.mColumn4 = new Vector4(fArr, 12);
    }

    public final Matrix4x4 dec() {
        this.mColumn1.dec();
        this.mColumn2.dec();
        this.mColumn3.dec();
        this.mColumn4.dec();
        return this;
    }

    public final Matrix4x4 div(float f2) {
        return new Matrix4x4(getX().div(f2), getY().div(f2), getZ().div(f2), getW().div(f2));
    }

    public final Vector4 get(int i2) {
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
        throw new IllegalArgumentException("column must be in 0..3");
    }

    public final float[] getData() {
        return this.mArray;
    }

    public final int getDataOffset() {
        return this.mOffset;
    }

    public final Vector3 getEulerAngle() {
        return Companion.toEulerAngle(this);
    }

    public final Vector3 getForward() {
        Vector3 vector3 = new Vector3();
        Companion.toForward(vector3, this);
        return vector3;
    }

    public final Vector3 getPosition() {
        Vector3 vector3 = new Vector3();
        Companion.toPosition(vector3, this);
        return vector3;
    }

    public final Vector3 getRight() {
        Vector3 vector3 = new Vector3();
        Companion.toRight(vector3, this);
        return vector3;
    }

    public final Vector3 getScale() {
        Vector3 vector3 = new Vector3();
        Companion.toScale(vector3, this);
        return vector3;
    }

    public final Vector3 getUp() {
        Vector3 vector3 = new Vector3();
        Companion.toUp(vector3, this);
        return vector3;
    }

    public final Vector4 getW() {
        return this.mColumn4;
    }

    public final Vector4 getX() {
        return this.mColumn1;
    }

    public final Vector4 getY() {
        return this.mColumn2;
    }

    public final Vector4 getZ() {
        return this.mColumn3;
    }

    public final Matrix4x4 inc() {
        this.mColumn1.inc();
        this.mColumn2.inc();
        this.mColumn3.inc();
        this.mColumn4.inc();
        return this;
    }

    public final void inverseForward() {
        getZ().setX(-getZ().getX());
        getZ().setY(-getZ().getY());
        getZ().setZ(-getZ().getZ());
    }

    public final Matrix4x4 minus(float f2) {
        return new Matrix4x4(getX().minus(f2), getY().minus(f2), getZ().minus(f2), getW().minus(f2));
    }

    public final Matrix4x4 plus(float f2) {
        return new Matrix4x4(getX().plus(f2), getY().plus(f2), getZ().plus(f2), getW().plus(f2));
    }

    public final void set(int i2, Vector4 v2) {
        n.g(v2, "v");
        Vector4 vector4 = get(i2);
        vector4.setX(v2.getX());
        vector4.setY(v2.getY());
        vector4.setZ(v2.getZ());
        vector4.setW(v2.getW());
    }

    public final void setForward(Vector3 value) {
        n.g(value, "value");
        Vector4 z2 = getZ();
        z2.setX(value.getX());
        z2.setY(value.getY());
        z2.setZ(value.getZ());
    }

    public final void setPosition(Vector3 value) {
        n.g(value, "value");
        Vector4 w2 = getW();
        w2.setX(value.getX());
        w2.setY(value.getY());
        w2.setZ(value.getZ());
    }

    public final void setRight(Vector3 value) {
        n.g(value, "value");
        Vector4 x2 = getX();
        x2.setX(value.getX());
        x2.setY(value.getY());
        x2.setZ(value.getZ());
    }

    public final void setUp(Vector3 value) {
        n.g(value, "value");
        Vector4 y2 = getY();
        y2.setX(value.getX());
        y2.setY(value.getY());
        y2.setZ(value.getZ());
    }

    public final Matrix4x4 times(float f2) {
        return new Matrix4x4(getX().times(f2), getY().times(f2), getZ().times(f2), getW().times(f2));
    }

    public String toString() {
        return g.e("\n            |" + getX().getX() + ' ' + getY().getX() + ' ' + getZ().getX() + ' ' + getW().getX() + "|\n            |" + getX().getY() + ' ' + getY().getY() + ' ' + getZ().getY() + ' ' + getW().getY() + "|\n            |" + getX().getZ() + ' ' + getY().getZ() + ' ' + getZ().getZ() + ' ' + getW().getZ() + "|\n            |" + getX().getW() + ' ' + getY().getW() + ' ' + getZ().getW() + ' ' + getW().getW() + "|\n            ");
    }

    public final Matrix4x4 unaryMinus() {
        return new Matrix4x4(getX().unaryMinus(), getY().unaryMinus(), getZ().unaryMinus(), getW().unaryMinus());
    }

    public final Matrix4x4 times(Matrix4x4 m2) {
        n.g(m2, "m");
        Matrix4x4 matrix4x4 = new Matrix4x4();
        Companion.times(matrix4x4, this, m2);
        return matrix4x4;
    }

    public final Vector4 times(Vector4 v2) {
        n.g(v2, "v");
        Vector4 vector4 = new Vector4();
        Companion.times(vector4, this, v2);
        return vector4;
    }

    public final float get(int i2, int i3) {
        return get(i2).get(i3);
    }

    public final void set(int i2, int i3, float f2) {
        get(i2).set(i3, f2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Matrix4x4(Vector4 x2, Vector4 y2, Vector4 z2, Vector4 w2) {
        this();
        n.g(x2, "x");
        n.g(y2, "y");
        n.g(z2, "z");
        n.g(w2, "w");
        Vector4.Companion companion = Vector4.Companion;
        companion.fromVector4(this.mColumn1, x2);
        companion.fromVector4(this.mColumn2, y2);
        companion.fromVector4(this.mColumn3, z2);
        companion.fromVector4(this.mColumn4, w2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Matrix4x4(Matrix4x4 m2) {
        this(m2.getX(), m2.getY(), m2.getZ(), m2.getW());
        n.g(m2, "m");
    }

    public Matrix4x4(float[] array, int i2) {
        n.g(array, "array");
        if (array.length - i2 >= 16) {
            this.mArray = array;
            this.mOffset = i2;
            this.mColumn1 = new Vector4(array, i2);
            this.mColumn2 = new Vector4(array, i2 + 4);
            this.mColumn3 = new Vector4(array, i2 + 8);
            this.mColumn4 = new Vector4(array, i2 + 12);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public /* synthetic */ Matrix4x4(float[] fArr, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(fArr, (i3 & 2) != 0 ? 0 : i2);
    }
}
