package miui.systemui.util;

import android.content.Context;
import android.view.View;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.quicksettings.common.R;

/* JADX INFO: loaded from: classes4.dex */
public final class BlurUtilsExt {
    public static final Companion Companion = new Companion(null);
    private static final float MAX_SCALE = 1.0f;
    private static final float MIN_SCALE = 0.95f;
    private static final String TAG = "BlurUtilsExt";
    private final Context context;
    private boolean inited;
    private int maxBlurRadius;
    private int secondaryMaxBlurRadius;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final int blurRatio2Radius(int i2, int i3, float f2) {
            return c1.f.i((int) MiuiMathUtils.INSTANCE.lerp(i2, i3, f2), 0, 275);
        }

        public static /* synthetic */ int blurRatio2Radius$default(Companion companion, int i2, int i3, float f2, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i2 = 0;
            }
            if ((i4 & 2) != 0) {
                i3 = 275;
            }
            return companion.blurRatio2Radius(i2, i3, f2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float blurRatio2ScaleRatio(float f2) {
            return MiuiMathUtils.INSTANCE.lerp(0.0f, 0.05f, f2);
        }

        private final float radius2Scale(float f2, float f3, float f4) {
            return MiuiMathUtils.INSTANCE.lerp(f2, f3, f4);
        }

        public static /* synthetic */ float radius2Scale$default(Companion companion, float f2, float f3, float f4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                f2 = BlurUtilsExt.MIN_SCALE;
            }
            if ((i2 & 2) != 0) {
                f3 = 1.0f;
            }
            return companion.radius2Scale(f2, f3, f4);
        }

        private Companion() {
        }
    }

    public BlurUtilsExt(@Plugin Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        this.context = context;
        this.maxBlurRadius = 275;
        this.secondaryMaxBlurRadius = 275;
        updateResource();
    }

    private final void updateResource() {
        this.maxBlurRadius = this.context.getResources().getDimensionPixelSize(R.dimen.max_blur_radius);
        this.secondaryMaxBlurRadius = this.context.getResources().getDimensionPixelSize(R.dimen.secondary_max_blur_radius);
    }

    public final void applyAlphaScale(View view, float f2) {
        if (view != null) {
            float fRadius2Scale$default = Companion.radius2Scale$default(Companion, 0.0f, 0.0f, f2, 3, null);
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            commonUtils.setAlphaEx(view, f2);
            commonUtils.setScaleXEx(view, fRadius2Scale$default);
            commonUtils.setScaleYEx(view, fRadius2Scale$default);
        }
    }

    public final void applyBlur(View view, float f2) {
        if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.context) || view == null) {
            return;
        }
        MiBlurCompat.setMiViewBlurModeCompat(view, (f2 == 0.0f ? 1 : 0) ^ 1);
        Companion companion = Companion;
        MiBlurCompat.setMiBackgroundBlurRadiusCompat(view, Companion.blurRatio2Radius$default(companion, 0, this.secondaryMaxBlurRadius, f2, 1, null));
        MiBlurCompat.setMiBackgroundBlurScaleRatioCompat(view, companion.blurRatio2ScaleRatio(f2));
    }

    public final int getMaxBlurRadius() {
        return this.maxBlurRadius;
    }

    public final void onConfigurationChanged(int i2) {
        updateResource();
    }

    public final void onPluginCreated() {
    }

    public final void onPluginDestroyed() {
    }

    public final void setMaxBlurRadius(int i2) {
        this.maxBlurRadius = i2;
    }
}
