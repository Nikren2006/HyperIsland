package com.mi.widget.shader;

import android.graphics.Rect;
import android.graphics.RuntimeShader;
import android.util.Log;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.Keep;
import androidx.annotation.UiThread;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.Origin;
import com.mi.widget.core.ShaderStrategy;
import f0.AbstractC0354h;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
public final class CallingShader<T extends View> {
    private static final String TAG = "CallingShader";
    private final boolean expanded;
    private float glowIconOffset;
    private Origin glowIconOrigin;
    private float glowIconWidth;
    private final H0.d mDrawable$delegate;
    private final H0.d mShader$delegate;
    private final ShaderStrategy strategy;
    private final T view;
    private static final c Companion = new c(null);
    public static final int $stable = 8;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ShaderStrategy.values().length];
            try {
                iArr[ShaderStrategy.AGSL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ShaderStrategy.DRAWABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ShaderStrategy.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final class a extends WaveDrawable {

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f2357j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ CallingShader f2358k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(CallingShader callingShader, View view) {
            super(view);
            n.g(view, "view");
            this.f2358k = callingShader;
        }

        @Override // com.mi.widget.shader.WaveDrawable
        public float alphaByTime(long j2, int i2) {
            if (0 <= j2 && j2 < 200) {
                return getMAlphaIncreaseInterpolator().getInterpolation(j2 / 200.0f);
            }
            if (200 <= j2 && j2 < 500) {
                return 1.0f;
            }
            if (500 > j2 || j2 >= 1501) {
                return 0.0f;
            }
            return 1.0f - getMAlphaDecreaseOutInterpolator().getInterpolation((j2 - 500.0f) / 1000.0f);
        }

        @Override // com.mi.widget.core.AbsDrawable
        public String getDebugName() {
            return "CallDrawable";
        }

        @Override // com.mi.widget.shader.WaveDrawable
        public int getWaveBitmapRes() {
            return e0.c.f4016a;
        }

        @Override // com.mi.widget.shader.WaveDrawable
        public float getWaveFinalSize() {
            return this.f2357j / 2;
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect bounds) {
            n.g(bounds, "bounds");
            this.f2357j = bounds.width();
        }
    }

    public final class b extends WaveShader {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CallingShader f2359a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(CallingShader callingShader, View view) {
            super(view);
            n.g(view, "view");
            this.f2359a = callingShader;
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uAlpha", 1.0f);
        }

        @Override // com.mi.widget.core.AbsShader
        public String getDebugName() {
            return "CallShader_" + (this.f2359a.expanded ? "Expand" : "Collapse");
        }

        @Override // com.mi.widget.core.AbsShader
        public int getShaderResId() {
            return this.f2359a.expanded ? e0.d.f4028a : e0.d.f4029b;
        }

        @Override // com.mi.widget.shader.WaveShader, com.mi.widget.core.AbsShader
        public void updateShader() {
            RuntimeShader mShader$hyper_widget_1_0_7_pluginRelease;
            String str;
            float f2;
            if (this.f2359a.expanded) {
                mShader$hyper_widget_1_0_7_pluginRelease = getMShader$hyper_widget_1_0_7_pluginRelease();
                str = "uScale";
                f2 = 2.0f;
            } else {
                mShader$hyper_widget_1_0_7_pluginRelease = getMShader$hyper_widget_1_0_7_pluginRelease();
                str = "ds";
                f2 = 3.5f;
            }
            mShader$hyper_widget_1_0_7_pluginRelease.setFloatUniform(str, f2);
            super.updateShader();
        }
    }

    public static final class c {
        public /* synthetic */ c(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public c() {
        }
    }

    public static final class d extends o implements Function0 {
        public d() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final a invoke() {
            CallingShader callingShader = CallingShader.this;
            return new a(callingShader, callingShader.view);
        }
    }

    public static final class e extends o implements Function0 {
        public e() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final b invoke() {
            CallingShader callingShader = CallingShader.this;
            return new b(callingShader, callingShader.view);
        }
    }

    public CallingShader(T view, boolean z2, ShaderStrategy strategy) {
        n.g(view, "view");
        n.g(strategy, "strategy");
        this.view = view;
        this.expanded = z2;
        this.strategy = strategy;
        this.mShader$delegate = H0.e.b(new e());
        this.mDrawable$delegate = H0.e.b(new d());
        Origin origin = Origin.END;
        this.glowIconOrigin = origin;
        setGlowIconWidth(0.0f);
        setGlowIconOrigin(origin);
        setGlowIconOffset(0.0f);
    }

    private final WaveDrawable<T> getMDrawable() {
        return (WaveDrawable) this.mDrawable$delegate.getValue();
    }

    private final CallingShader<T>.b getMShader() {
        return (b) this.mShader$delegate.getValue();
    }

    @AnyThread
    public final float getGlowIconOffset() {
        return this.glowIconOffset;
    }

    @AnyThread
    public final Origin getGlowIconOrigin() {
        return this.glowIconOrigin;
    }

    @AnyThread
    public final float getGlowIconWidth() {
        return this.glowIconWidth;
    }

    @UiThread
    public final void setGlowIconOffset(float f2) {
        this.glowIconOffset = f2;
        ShaderStrategy shaderStrategy = this.strategy;
        if (shaderStrategy == ShaderStrategy.AGSL) {
            getMShader().setGlowIconOffset(f2);
            return;
        }
        if (shaderStrategy == ShaderStrategy.DRAWABLE) {
            getMDrawable().setGlowIconOffset$hyper_widget_1_0_7_pluginRelease(f2);
            return;
        }
        Log.w(TAG, "Ignore set glowIconOffset=" + f2 + " for strategy=" + shaderStrategy);
    }

    @UiThread
    public final void setGlowIconOrigin(Origin value) {
        n.g(value, "value");
        this.glowIconOrigin = value;
        ShaderStrategy shaderStrategy = this.strategy;
        if (shaderStrategy == ShaderStrategy.AGSL) {
            getMShader().setGlowIconOrigin(value);
            return;
        }
        if (shaderStrategy == ShaderStrategy.DRAWABLE) {
            getMDrawable().setGlowIconOrigin$hyper_widget_1_0_7_pluginRelease(value);
            return;
        }
        Log.w(TAG, "Ignore set glowIconOrigin=" + value + " for strategy=" + shaderStrategy);
    }

    @UiThread
    public final void setGlowIconWidth(float f2) {
        this.glowIconWidth = f2;
        ShaderStrategy shaderStrategy = this.strategy;
        if (shaderStrategy == ShaderStrategy.AGSL) {
            getMShader().setGlowIconWidth(f2);
            return;
        }
        if (shaderStrategy == ShaderStrategy.DRAWABLE) {
            getMDrawable().setGlowIconWidth$hyper_widget_1_0_7_pluginRelease(f2);
            return;
        }
        Log.w(TAG, "Ignore set glowIconWidth=" + f2 + " for strategy=" + shaderStrategy);
    }

    @AnyThread
    public final void start() {
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.strategy.ordinal()];
        if (i2 == 1) {
            getMShader().start();
            return;
        }
        if (i2 == 2) {
            getMDrawable().start();
            return;
        }
        if (i2 != 3) {
            return;
        }
        Log.i(TAG, "Ignore start shader for strategy=" + this.strategy);
    }

    @AnyThread
    public final void stop() {
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.strategy.ordinal()];
        if (i2 == 1) {
            getMShader().stop();
            return;
        }
        if (i2 == 2) {
            getMDrawable().stop();
            return;
        }
        if (i2 != 3) {
            return;
        }
        Log.i(TAG, "Ignore stop shader for strategy=" + this.strategy);
    }

    public /* synthetic */ CallingShader(View view, boolean z2, ShaderStrategy shaderStrategy, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(view, (i2 & 2) != 0 ? true : z2, (i2 & 4) != 0 ? AbstractC0354h.a() : shaderStrategy);
    }
}
