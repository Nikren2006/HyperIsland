package com.mi.widget.shader;

import H0.d;
import H0.e;
import H0.i;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Size;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.DrawableRes;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.AbsDrawable;
import com.mi.widget.core.Origin;
import f0.AbstractC0352f;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miuix.view.animation.CubicEaseOutInterpolator;
import miuix.view.animation.SineEaseInInterpolator;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
public abstract class WaveDrawable<T extends View> extends AbsDrawable<T> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CubicEaseOutInterpolator f2368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f2369b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f2370c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CubicEaseOutInterpolator f2371d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SineEaseInInterpolator f2372e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f2373f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f2374g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Origin f2375h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f2376i;

    public static final class a extends o implements Function0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f2377a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ WaveDrawable f2378b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(View view, WaveDrawable waveDrawable) {
            super(0);
            this.f2377a = view;
            this.f2378b = waveDrawable;
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Bitmap invoke() {
            return BitmapFactory.decodeResource(this.f2377a.getResources(), this.f2378b.getWaveBitmapRes());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WaveDrawable(T view) {
        super(view);
        n.g(view, "view");
        this.f2368a = new CubicEaseOutInterpolator();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        this.f2369b = paint;
        this.f2370c = e.b(new a(view, this));
        this.f2371d = new CubicEaseOutInterpolator();
        this.f2372e = new SineEaseInInterpolator();
        this.f2375h = Origin.END;
    }

    public abstract float alphaByTime(long j2, int i2);

    public final Bitmap b() {
        return (Bitmap) this.f2370c.getValue();
    }

    public final float c(long j2, long j3) {
        return this.f2368a.getInterpolation(j2 / j3) + (getWaveStartSize() / getWaveFinalSize());
    }

    @Override // com.mi.widget.core.AbsDrawable
    public long getAnimCycleTime() {
        return 2000L;
    }

    @AnyThread
    public final float getGlowIconOffset$hyper_widget_1_0_7_pluginRelease() {
        return this.f2376i;
    }

    @AnyThread
    public final Origin getGlowIconOrigin$hyper_widget_1_0_7_pluginRelease() {
        return this.f2375h;
    }

    @AnyThread
    public final float getGlowIconWidth$hyper_widget_1_0_7_pluginRelease() {
        return this.f2374g;
    }

    public final SineEaseInInterpolator getMAlphaDecreaseOutInterpolator() {
        return this.f2372e;
    }

    public final CubicEaseOutInterpolator getMAlphaIncreaseInterpolator() {
        return this.f2371d;
    }

    @DrawableRes
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public abstract int getWaveBitmapRes();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public abstract float getWaveFinalSize();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public float getWaveStartSize() {
        return this.f2373f;
    }

    @Override // com.mi.widget.core.AbsDrawable
    public void onUpdateDraw(Canvas canvas, long j2, long j3) {
        n.g(canvas, "canvas");
        float fAlphaByTime = alphaByTime(j3, canvas.getWidth());
        float fC = c(j3, getAnimCycleTime()) * (getWaveFinalSize() / b().getWidth()) * 2.0f;
        i iVarA = AbstractC0352f.a(this.f2375h, new Size(canvas.getWidth(), canvas.getHeight()), this.f2374g, this.f2376i, getLayoutDirection(), getDebugName());
        this.f2369b.setAlpha((int) (Math.max(0.0f, fAlphaByTime) * 255));
        int iSave = canvas.save();
        try {
            canvas.scale(fC, fC, ((Number) iVarA.d()).floatValue(), ((Number) iVarA.e()).floatValue());
            canvas.drawBitmap(b(), ((Number) iVarA.d()).floatValue() - (b().getWidth() / 2.0f), ((Number) iVarA.e()).floatValue() - (b().getHeight() / 2.0f), this.f2369b);
        } finally {
            canvas.restoreToCount(iSave);
        }
    }

    @UiThread
    public final void setGlowIconOffset$hyper_widget_1_0_7_pluginRelease(float f2) {
        this.f2376i = f2;
    }

    @UiThread
    public final void setGlowIconOrigin$hyper_widget_1_0_7_pluginRelease(Origin origin) {
        n.g(origin, "<set-?>");
        this.f2375h = origin;
    }

    @UiThread
    public final void setGlowIconWidth$hyper_widget_1_0_7_pluginRelease(float f2) {
        this.f2374g = f2;
    }
}
