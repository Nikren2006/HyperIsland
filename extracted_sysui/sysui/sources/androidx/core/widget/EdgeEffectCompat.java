package androidx.core.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.EdgeEffect;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class EdgeEffectCompat {
    private final EdgeEffect mEdgeEffect;

    @RequiresApi(21)
    public static class Api21Impl {
        private Api21Impl() {
        }

        public static void onPull(EdgeEffect edgeEffect, float f2, float f3) {
            edgeEffect.onPull(f2, f3);
        }
    }

    @RequiresApi(31)
    public static class Api31Impl {
        private Api31Impl() {
        }

        public static EdgeEffect create(Context context, AttributeSet attributeSet) {
            try {
                return new EdgeEffect(context, attributeSet);
            } catch (Throwable unused) {
                return new EdgeEffect(context);
            }
        }

        public static float getDistance(EdgeEffect edgeEffect) {
            try {
                return edgeEffect.getDistance();
            } catch (Throwable unused) {
                return 0.0f;
            }
        }

        public static float onPullDistance(EdgeEffect edgeEffect, float f2, float f3) {
            try {
                return edgeEffect.onPullDistance(f2, f3);
            } catch (Throwable unused) {
                edgeEffect.onPull(f2, f3);
                return 0.0f;
            }
        }
    }

    @Deprecated
    public EdgeEffectCompat(Context context) {
        this.mEdgeEffect = new EdgeEffect(context);
    }

    public static EdgeEffect create(Context context, AttributeSet attributeSet) {
        return Api31Impl.create(context, attributeSet);
    }

    public static float getDistance(EdgeEffect edgeEffect) {
        return Api31Impl.getDistance(edgeEffect);
    }

    public static float onPullDistance(EdgeEffect edgeEffect, float f2, float f3) {
        return Api31Impl.onPullDistance(edgeEffect, f2, f3);
    }

    @Deprecated
    public boolean draw(Canvas canvas) {
        return this.mEdgeEffect.draw(canvas);
    }

    @Deprecated
    public void finish() {
        this.mEdgeEffect.finish();
    }

    @Deprecated
    public boolean isFinished() {
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated
    public boolean onAbsorb(int i2) {
        this.mEdgeEffect.onAbsorb(i2);
        return true;
    }

    @Deprecated
    public boolean onPull(float f2) {
        this.mEdgeEffect.onPull(f2);
        return true;
    }

    @Deprecated
    public boolean onRelease() {
        this.mEdgeEffect.onRelease();
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated
    public void setSize(int i2, int i3) {
        this.mEdgeEffect.setSize(i2, i3);
    }

    @Deprecated
    public boolean onPull(float f2, float f3) {
        onPull(this.mEdgeEffect, f2, f3);
        return true;
    }

    public static void onPull(EdgeEffect edgeEffect, float f2, float f3) {
        Api21Impl.onPull(edgeEffect, f2, f3);
    }
}
