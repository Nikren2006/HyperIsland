package miuix.mgl.math;

/* JADX INFO: loaded from: classes3.dex */
public class MatrixUtils {
    public static void perspectiveM(float[] fArr, float f2, float f3, float f4, float f5) {
        float fTan = (float) (1.0d / java.lang.Math.tan(((double) ((float) ((((double) f2) * 3.141592653589793d) / 180.0d))) / 2.0d));
        fArr[0] = fTan / f3;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = 0.0f;
        fArr[5] = fTan;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        float f6 = f5 - f4;
        fArr[10] = -((f5 + f4) / f6);
        fArr[11] = -1.0f;
        fArr[12] = 0.0f;
        fArr[13] = 0.0f;
        fArr[14] = -(((f5 * 2.0f) * f4) / f6);
        fArr[15] = 0.0f;
    }
}
