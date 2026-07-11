package miuix.view.animation;

import android.graphics.Path;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes5.dex */
@RequiresApi(api = 26)
public class PathInterpolator implements Interpolator {
    private static final float PRECISION = 0.002f;
    private float[] mX;
    private float[] mY;

    public PathInterpolator(@NonNull Path path) {
        initPath(path);
    }

    private void initCubic(float f2, float f3, float f4, float f5) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f2, f3, f4, f5, 1.0f, 1.0f);
        initPath(path);
    }

    private void initPath(Path path) {
        float[] fArrApproximate = path.approximate(0.002f);
        int length = fArrApproximate.length / 3;
        float f2 = 0.0f;
        if (fArrApproximate[1] != 0.0f || fArrApproximate[2] != 0.0f || fArrApproximate[fArrApproximate.length - 2] != 1.0f || fArrApproximate[fArrApproximate.length - 1] != 1.0f) {
            throw new IllegalArgumentException("The Path must start at (0,0) and end at (1,1)");
        }
        this.mX = new float[length];
        this.mY = new float[length];
        int i2 = 0;
        int i3 = 0;
        float f3 = 0.0f;
        while (i2 < length) {
            float f4 = fArrApproximate[i3];
            int i4 = i3 + 2;
            float f5 = fArrApproximate[i3 + 1];
            i3 += 3;
            float f6 = fArrApproximate[i4];
            if (f4 == f2 && f5 != f3) {
                throw new IllegalArgumentException("The Path cannot have discontinuity in the X axis.");
            }
            if (f5 < f3) {
                throw new IllegalArgumentException("The Path cannot loop back on itself.");
            }
            this.mX[i2] = f5;
            this.mY[i2] = f6;
            i2++;
            f3 = f5;
            f2 = f4;
        }
    }

    private void initQuad(float f2, float f3) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f2, f3, 1.0f, 1.0f);
        initPath(path);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        int length = this.mX.length - 1;
        int i2 = 0;
        while (length - i2 > 1) {
            int i3 = (i2 + length) / 2;
            if (f2 < this.mX[i3]) {
                length = i3;
            } else {
                i2 = i3;
            }
        }
        float[] fArr = this.mX;
        float f3 = fArr[length];
        float f4 = fArr[i2];
        float f5 = f3 - f4;
        if (f5 == 0.0f) {
            return this.mY[i2];
        }
        float[] fArr2 = this.mY;
        float f6 = fArr2[i2];
        return f6 + (((f2 - f4) / f5) * (fArr2[length] - f6));
    }

    public PathInterpolator(float f2, float f3) {
        initQuad(f2, f3);
    }

    public PathInterpolator(float f2, float f3, float f4, float f5) {
        initCubic(f2, f3, f4, f5);
    }
}
