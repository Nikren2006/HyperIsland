package miuix.mgl.math;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.mgl.math.Vector3;

/* JADX INFO: loaded from: classes3.dex */
public final class Math {
    public static final int BIT_SIZE = 8;
    public static final float DEG_TO_RAD = 0.017453292f;
    public static final float FLT_EPSILON = 1.1920929E-7f;
    public static final float FOUR_PI = 12.566371f;
    public static final float FPI = 3.1415927f;
    public static final float HALF_PI = 1.5707964f;
    public static final float INV_FOUR_PI = 0.07957747f;
    public static final float INV_PI = 0.31830987f;
    public static final float INV_TWO_PI = 0.15915494f;
    public static final float RAD_TO_DEG = 57.295776f;
    public static final float TWO_PI = 6.2831855f;
    public static final Companion Companion = new Companion(null);
    private static final int[] NEXT_IJK = {1, 2, 0};

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final float catmullRomValue(float f2, float f3, float f4, float f5, float f6) {
            float f7 = -f3;
            float f8 = f2 * f2;
            return ((((((f4 * 3.0f) + f7) - (3.0f * f5)) + f6) * f8 * f2) + (((((f3 * 2.0f) - (5.0f * f4)) + (4.0f * f5)) - f6) * f8) + ((f7 + f5) * f2) + (f4 * 2.0f)) * 0.5f;
        }

        public final boolean approximately(float f2, float f3) {
            return java.lang.Math.abs(f2 - f3) < 1.1920929E-7f;
        }

        public final void calculateCatmullRomPosition(Vector3 result, float f2, Vector3 a2, Vector3 b2, Vector3 c2, Vector3 d2) {
            n.g(result, "result");
            n.g(a2, "a");
            n.g(b2, "b");
            n.g(c2, "c");
            n.g(d2, "d");
            result.setX(catmullRomValue(f2, a2.getX(), b2.getX(), c2.getX(), d2.getX()));
            result.setY(catmullRomValue(f2, a2.getY(), b2.getY(), c2.getY(), d2.getY()));
            result.setZ(catmullRomValue(f2, a2.getZ(), b2.getZ(), c2.getZ(), d2.getZ()));
        }

        public final float clamp(float f2, float f3, float f4) {
            return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
        }

        public final float clamp01(float f2) {
            return clamp(f2, 0.0f, 1.0f);
        }

        public final float degrees(float f2) {
            return f2 * 57.295776f;
        }

        public final int[] getNEXT_IJK() {
            return Math.NEXT_IJK;
        }

        public final boolean isAABBIntersectViewFrustum(Vector3 center, Vector3 extent, Vector4[] viewPlans) {
            n.g(center, "center");
            n.g(extent, "extent");
            n.g(viewPlans, "viewPlans");
            if (viewPlans.length != 6) {
                throw new RuntimeException("ViewPlans‘s size is not 6");
            }
            for (Vector4 vector4 : viewPlans) {
                if ((center.getX() * vector4.getX()) + (center.getY() * vector4.getY()) + (center.getZ() * vector4.getZ()) + vector4.getW() + (extent.getX() * java.lang.Math.abs(vector4.getX())) + (extent.getY() * java.lang.Math.abs(vector4.getY())) + (extent.getZ() * java.lang.Math.abs(vector4.getZ())) < 0.0f) {
                    return false;
                }
            }
            return true;
        }

        public final boolean isRayIntersectAABB(Vector3 rayPos, Vector3 rayDirection, Vector3 min, Vector3 max) {
            n.g(rayPos, "rayPos");
            n.g(rayDirection, "rayDirection");
            n.g(min, "min");
            n.g(max, "max");
            float x2 = 1.0f / rayDirection.getX();
            float y2 = 1.0f / rayDirection.getY();
            float z2 = 1.0f / rayDirection.getZ();
            float x3 = (min.getX() - rayPos.getX()) * x2;
            float x4 = (max.getX() - rayPos.getX()) * x2;
            float y3 = (min.getY() - rayPos.getY()) * y2;
            float y4 = (max.getY() - rayPos.getY()) * y2;
            float z3 = (min.getZ() - rayPos.getZ()) * z2;
            float z4 = (max.getZ() - rayPos.getZ()) * z2;
            float fMax = java.lang.Math.max(java.lang.Math.max(java.lang.Math.min(x3, x4), java.lang.Math.min(y3, y4)), java.lang.Math.min(z3, z4));
            float fMin = java.lang.Math.min(java.lang.Math.min(java.lang.Math.max(x3, x4), java.lang.Math.max(y3, y4)), java.lang.Math.max(z3, z4));
            return fMin >= 0.0f && fMax <= fMin;
        }

        public final boolean isSphereIntersectViewFrustum(Vector3 center, float f2, Vector4[] viewPlans) {
            n.g(center, "center");
            n.g(viewPlans, "viewPlans");
            if (viewPlans.length != 6) {
                throw new RuntimeException("ViewPlans‘s size is not 6");
            }
            for (Vector4 vector4 : viewPlans) {
                if ((center.getX() * vector4.getX()) + (center.getY() * vector4.getY()) + (center.getZ() * vector4.getZ()) + vector4.getW() + f2 < 0.0f) {
                    return false;
                }
            }
            return true;
        }

        public final float lerp(float f2, float f3, float f4) {
            return ((1.0f - f4) * f2) + (f3 * f4);
        }

        public final void lookForwardToNormalizedRUF(Vector3 resultRight, Vector3 resultUp, Vector3 resultForward, Vector3 forward, Vector3 up) {
            n.g(resultRight, "resultRight");
            n.g(resultUp, "resultUp");
            n.g(resultForward, "resultForward");
            n.g(forward, "forward");
            n.g(up, "up");
            Vector3.Companion companion = Vector3.Companion;
            companion.normalize(resultForward, forward);
            companion.normalize(resultUp, up);
            if (java.lang.Math.abs(companion.dot(resultForward, resultUp)) > 0.999f) {
                float x2 = resultUp.getX();
                float y2 = resultUp.getY();
                resultUp.setX(resultUp.getZ());
                resultUp.setY(x2);
                resultUp.setZ(y2);
            }
            companion.cross(resultRight, resultForward, resultUp);
            resultRight.setNormalize();
            companion.cross(resultUp, resultRight, resultForward);
        }

        public final float magnitude(float f2, float f3, float f4) {
            return (float) java.lang.Math.sqrt((f2 * f2) + (f3 * f3) + (f4 * f4));
        }

        public final float magnitude2(float f2, float f3) {
            return (f2 * f2) + (f3 * f3);
        }

        public final float pow(float f2, float f3) {
            return (float) StrictMath.pow(f2, f3);
        }

        public final float radians(float f2) {
            return f2 * 0.017453292f;
        }

        public final void rotateAroundAxis(Vector3 result, float f2, Vector3 axisNorm, Vector3 vec) {
            n.g(result, "result");
            n.g(axisNorm, "axisNorm");
            n.g(vec, "vec");
            double d2 = f2 * 0.017453292f;
            float fCos = (float) java.lang.Math.cos(d2);
            float fSin = (float) java.lang.Math.sin(d2);
            Matrix3x3 matrix3x3 = new Matrix3x3();
            float f3 = 1.0f - fCos;
            matrix3x3.getX().setX((axisNorm.getX() * axisNorm.getX() * f3) + fCos);
            matrix3x3.getX().setY((axisNorm.getX() * axisNorm.getY() * f3) + (axisNorm.getZ() * fSin));
            matrix3x3.getX().setZ(((axisNorm.getX() * axisNorm.getZ()) * f3) - (axisNorm.getY() * fSin));
            matrix3x3.getY().setX(((axisNorm.getX() * axisNorm.getY()) * f3) - (axisNorm.getZ() * fSin));
            matrix3x3.getY().setY((axisNorm.getY() * axisNorm.getY() * f3) + fCos);
            matrix3x3.getY().setZ((axisNorm.getZ() * axisNorm.getY() * f3) + (axisNorm.getX() * fSin));
            matrix3x3.getZ().setX((axisNorm.getX() * axisNorm.getZ() * f3) + (axisNorm.getY() * fSin));
            matrix3x3.getZ().setY(((axisNorm.getY() * axisNorm.getZ()) * f3) - (axisNorm.getX() * fSin));
            matrix3x3.getZ().setZ((axisNorm.getZ() * axisNorm.getZ() * f3) + fCos);
            Matrix3x3.Companion.times(result, matrix3x3, vec);
        }

        public final void rotateAroundYAxis(Vector3 result, float f2, Vector3 vec) {
            n.g(result, "result");
            n.g(vec, "vec");
            double d2 = f2 * 0.017453292f;
            float fCos = (float) java.lang.Math.cos(d2);
            float fSin = (float) java.lang.Math.sin(d2);
            Matrix3x3 matrix3x3 = new Matrix3x3();
            matrix3x3.getX().set(fCos, 0.0f, -fSin);
            matrix3x3.getY().set(0.0f, (1.0f - fCos) + fCos, 0.0f);
            matrix3x3.getZ().set(fSin, 0.0f, fCos);
            Matrix3x3.Companion.times(result, matrix3x3, vec);
        }

        public final void triangleToPlane(Vector4 plane, Vector3 a2, Vector3 b2, Vector3 c2) {
            n.g(plane, "plane");
            n.g(a2, "a");
            n.g(b2, "b");
            n.g(c2, "c");
            Vector3 vector3Minus = b2.minus(a2);
            Vector3 vector3Minus2 = c2.minus(a2);
            Vector3.Companion companion = Vector3.Companion;
            Vector3 vector3Cross = companion.cross(vector3Minus, vector3Minus2);
            vector3Cross.setNormalize();
            plane.set(vector3Cross, -companion.dot(vector3Cross, a2));
        }

        private Companion() {
        }

        public final Vector3 lerp(Vector3 start, Vector3 end, float f2) {
            n.g(start, "start");
            n.g(end, "end");
            Vector3 vector3 = new Vector3();
            lerp(vector3, start, end, f2);
            return vector3;
        }

        public final float magnitude(float f2, float f3) {
            return (float) java.lang.Math.sqrt((f2 * f2) + (f3 * f3));
        }

        public final float magnitude2(float f2, float f3, float f4) {
            return (f2 * f2) + (f3 * f3) + (f4 * f4);
        }

        public final void lerp(Vector3 result, Vector3 start, Vector3 end, float f2) {
            n.g(result, "result");
            n.g(start, "start");
            n.g(end, "end");
            float f3 = 1.0f - f2;
            result.setX((start.getX() * f3) + (end.getX() * f2));
            result.setY((start.getY() * f3) + (end.getY() * f2));
            result.setZ((f3 * start.getZ()) + (end.getZ() * f2));
        }
    }
}
