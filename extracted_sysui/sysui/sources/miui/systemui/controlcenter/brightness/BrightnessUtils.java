package miui.systemui.controlcenter.brightness;

import android.app.ActivityThread;
import android.content.res.Resources;
import android.util.MathUtils;
import miui.systemui.util.MiuiMathUtils;

/* JADX INFO: loaded from: classes.dex */
public class BrightnessUtils {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    private static final float f5340A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    private static final float f5341B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    private static final float f5342C;
    public static final int GAMMA_SPACE_MAX = 255;
    public static final int GAMMA_SPACE_MIN = 0;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    private static final float f5343R;
    private static final Resources resources;

    static {
        Resources resources2 = ActivityThread.currentApplication().getResources();
        resources = resources2;
        f5343R = resources2.getFloat(285671454);
        f5340A = resources2.getFloat(285671451);
        f5341B = resources2.getFloat(285671452);
        f5342C = resources2.getFloat(285671453);
    }

    public static final int convertGammaToLinear(int i2, int i3, int i4) {
        float fNorm = MathUtils.norm(0.0f, 255.0f, i2);
        float f2 = f5343R;
        int iRound = Math.round(MiuiMathUtils.INSTANCE.lerp(i3, i4, (fNorm <= f2 ? MathUtils.sq(fNorm / f2) : MathUtils.exp((fNorm - f5342C) / f5340A) + f5341B) / 12.0f));
        return iRound > i4 ? i4 : iRound;
    }

    public static final float convertGammaToLinearFloat(int i2, float f2, float f3) {
        float fNorm = MathUtils.norm(0.0f, 255.0f, i2);
        float f4 = f5343R;
        return MiuiMathUtils.INSTANCE.lerp(f2, f3, MathUtils.constrain(fNorm <= f4 ? MathUtils.sq(fNorm / f4) : MathUtils.exp((fNorm - f5342C) / f5340A) + f5341B, 0.0f, 12.0f) / 12.0f);
    }

    public static final int convertLinearToGamma(int i2, int i3, int i4) {
        return convertLinearToGammaFloat(i2, i3, i4);
    }

    public static final int convertLinearToGammaFloat(float f2, float f3, float f4) {
        float fLog;
        float fNorm = MathUtils.norm(f3, f4, f2) * 12.0f;
        if (fNorm <= 1.0f) {
            fLog = MathUtils.sqrt(fNorm) * f5343R;
        } else {
            fLog = f5342C + (f5340A * MathUtils.log(fNorm - f5341B));
        }
        return Math.round(MiuiMathUtils.INSTANCE.lerp(0.0f, 255.0f, fLog));
    }
}
