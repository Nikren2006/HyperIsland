package com.mi.widget.shader;

import H0.d;
import H0.e;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.os.SystemClock;
import android.util.Log;
import android.util.SizeF;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.annotation.UiThread;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.AbsShader;
import com.mi.widget.core.DriverShareStrategy;
import com.mi.widget.core.IDriverShareStructure;
import com.mi.widget.core.ShaderStrategy;
import f0.AbstractC0354h;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
public class LightBgShader extends AbsShader<View> {
    private static final boolean DEBUG = true;
    private SizeF bottomApertureSize;
    private final View fgView;
    private float glowDistance;
    private float glowRDCornerRadius;
    private final d mFgShader$delegate;
    private FolmeAnim mFolmeAnim;
    private g0.b mFragData;
    private long mLastFrameTime;
    private View.OnLayoutChangeListener mLayoutChangeListener;
    private float[] mLights;
    private g0.d mPhysics;
    private boolean mRunningByAnim;
    private StartFolmeAnim mStartFolmeAnim;
    private StopFolmeAnim mStopFolmeAnim;
    private final d mTextureShader$delegate;
    private float mTime;
    private final ShaderStrategy strategy;
    private SizeF upperApertureSize;
    private static final a Companion = new a(null);
    public static final int $stable = 8;
    private static final float[] U_LIGHT_COLORS = {0.502f, 0.525f, 1.0f, 1.0f, 0.827f, 0.702f, 1.0f, 0.525f, 0.208f, 0.518f, 0.494f, 1.0f, 0.071f, 0.412f, 0.949f, 0.502f, 0.525f, 1.0f, 1.0f, 0.827f, 0.702f, 1.0f, 0.525f, 0.208f, 0.518f, 0.494f, 1.0f, 0.071f, 0.412f, 0.949f, 1.0f, 0.525f, 0.208f};
    private static final float[] U_INTELLI_MOVEMENT_SCALE = {0.9f, 0.9f};
    private static final float[] U_ORIGIN = {0.0f, 1.35f};
    private static final SizeF U_TEXTURE_SIZE = new SizeF(60.0f, 37.0f);

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public static final class b extends o implements Function0 {
        public b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final RuntimeShader invoke() {
            RuntimeShader runtimeShader = new RuntimeShader(LightBgShader.this.readRawString(e0.d.f4036i));
            runtimeShader.setFloatUniform("uInputResolution", LightBgShader.U_TEXTURE_SIZE.getWidth(), LightBgShader.U_TEXTURE_SIZE.getHeight());
            runtimeShader.setFloatUniform("uSdfRadius", 0.0f);
            runtimeShader.setFloatUniform("uEdgeHighlightDim", 0.0f);
            return runtimeShader;
        }
    }

    public static final class c extends o implements Function0 {
        public c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final RuntimeShader invoke() {
            RuntimeShader runtimeShader = new RuntimeShader(LightBgShader.this.readRawString(e0.d.f4037j));
            runtimeShader.setFloatUniform("uLightColors", LightBgShader.U_LIGHT_COLORS);
            runtimeShader.setFloatUniform("uResolution", new float[]{LightBgShader.U_TEXTURE_SIZE.getWidth(), LightBgShader.U_TEXTURE_SIZE.getHeight()});
            return runtimeShader;
        }
    }

    public /* synthetic */ LightBgShader(View view, View view2, ShaderStrategy shaderStrategy, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(view, view2, (i2 & 4) != 0 ? AbstractC0354h.a() : shaderStrategy);
    }

    private final RuntimeShader getMFgShader() {
        return (RuntimeShader) this.mFgShader$delegate.getValue();
    }

    private final RuntimeShader getMTextureShader() {
        return (RuntimeShader) this.mTextureShader$delegate.getValue();
    }

    private final void initPhysics(g0.d dVar) {
        dVar.a().p(60L);
        dVar.a().n(1);
        dVar.a().q(0.033333335f);
        dVar.a().m(0.3f);
        dVar.a().o(0.0f);
        dVar.a().j(new float[]{-80.79679f, -151.77667f, 289.53015f, 133.59625f, 152.01352f, 90.91115f, -256.38846f, 78.98083f, 230.8243f, -136.37402f, -38.153152f, 159.89685f, -13.360941f, -157.33534f, -135.0559f, -84.44102f, -200.09569f, -8.089579f, 238.59375f, 245.52487f, -263.6614f, 242.79184f, 2.7137518f, 9.775186f, -108.58024f, 291.98526f, -3.6139905f, -140.3133f, -245.56026f, 268.65857f, -255.75055f, 0.42425394f, -69.5147f});
        dVar.a().k(0.0f);
        dVar.a().l(false);
        dVar.a().r(false);
        dVar.c().f(0.0f);
        dVar.c().c(0.0f);
        dVar.c().d(0.018f);
        dVar.c().e(0.19000006f);
        dVar.h().f(0.0f);
        dVar.h().c(0.0f);
        dVar.h().d(0.018f);
        dVar.h().e(0.19000006f);
        dVar.i().f(0.0f);
        dVar.i().c(0.0f);
        dVar.i().d(0.026999999f);
        dVar.i().e(0.31937504f);
        dVar.d().f(0.0f);
        dVar.d().c(0.0f);
        dVar.d().d(0.026999999f);
        dVar.d().e(0.31937504f);
        dVar.n(0L);
        dVar.k(false);
        dVar.b().f(0.0f);
        dVar.b().c(0.0f);
        dVar.b().d(0.012f);
        dVar.b().e(0.097500026f);
        dVar.f().f(5.0f);
        dVar.f().c(0.0f);
        dVar.f().d(0.009f);
        dVar.f().e(0.059099972f);
        dVar.l(0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStart$lambda$2(LightBgShader this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this$0, "this$0");
        this$0.getMFgShader().setFloatUniform("uResolution", new float[]{i4 - i2, i5 - i3});
    }

    private final void updateFragData() {
        this.mFragData.e(this.mPhysics.d().b());
        this.mFragData.d(this.mPhysics.f().b() + 10.0f);
        for (int i2 = 0; i2 < 11; i2++) {
            this.mFragData.b()[i2].setXy(this.mPhysics.g()[i2].getXy());
            int i3 = i2 * 2;
            this.mLights[i3] = this.mFragData.b()[i2].getX();
            this.mLights[i3 + 1] = this.mFragData.b()[i2].getY();
        }
    }

    private final void updatePhysics(float f2) {
        this.mPhysics.m(U_INTELLI_MOVEMENT_SCALE);
        int iB = Y0.b.b(f2 / this.mPhysics.a().g());
        int iE = this.mPhysics.a().e();
        if (iB <= iE) {
            this.mPhysics.a().n(iB);
        }
        if (iE < 2) {
            iE = 1;
        }
        do {
            iE--;
            this.mPhysics.o();
            if (!this.mPhysics.a().c()) {
                break;
            }
        } while (iE != 0);
        g0.c.d(this.mPhysics);
    }

    public static /* synthetic */ void updatePhysics$default(LightBgShader lightBgShader, float f2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updatePhysics");
        }
        if ((i2 & 1) != 0) {
            f2 = 70.0f;
        }
        lightBgShader.updatePhysics(f2);
    }

    @AnyThread
    public final SizeF getBottomApertureSize() {
        return this.bottomApertureSize;
    }

    @Override // com.mi.widget.core.AbsShader
    public String getDebugName() {
        return "LightBgShader";
    }

    @Override // com.mi.widget.core.IShaderDriven
    public DriverShareStrategy getDriverShareStrategy() {
        return DriverShareStrategy.NOT_SUPPORTED;
    }

    public final View getFgView() {
        return this.fgView;
    }

    @AnyThread
    public final float getGlowDistance() {
        return this.glowDistance;
    }

    @AnyThread
    public final float getGlowRDCornerRadius() {
        return this.glowRDCornerRadius;
    }

    @Override // com.mi.widget.core.AbsShader
    public int getShaderResId() {
        return e0.d.f4035h;
    }

    @AnyThread
    public final SizeF getUpperApertureSize() {
        return this.upperApertureSize;
    }

    @Override // com.mi.widget.core.IShaderDriven
    @CallSuper
    public IDriverShareStructure onDriveFrameParameters(boolean z2, long j2, float f2, IDriverShareStructure data) {
        n.g(data, "data");
        this.mTime = (SystemClock.elapsedRealtime() - j2) / 1000.0f;
        this.mPhysics.a().q(this.mLastFrameTime <= 0 ? 0.033333335f : (SystemClock.elapsedRealtime() - this.mLastFrameTime) / 1000.0f);
        this.mLastFrameTime = SystemClock.elapsedRealtime();
        updatePhysics$default(this, 0.0f, 1, null);
        updateFragData();
        return data;
    }

    @Override // com.mi.widget.core.AbsShader
    public boolean onInterceptStart() {
        return this.strategy != ShaderStrategy.AGSL;
    }

    @Override // com.mi.widget.core.AbsShader
    public boolean onInterceptStop() {
        return this.strategy != ShaderStrategy.AGSL;
    }

    @Override // com.mi.widget.core.AbsShader, com.mi.widget.core.IShaderDriven
    @CallSuper
    public void onResetFrameParameters() {
        this.mLights = new float[22];
        this.mFragData = new g0.b(null, 0.0f, 0.0f, 7, null);
        g0.d dVar = new g0.d(null, null, null, null, null, 0L, false, null, null, 0.0f, null, null, 4095, null);
        initPhysics(dVar);
        this.mPhysics = dVar;
        this.mLastFrameTime = 0L;
    }

    @Override // com.mi.widget.core.AbsShader
    @CallSuper
    public void onStart() {
        getMFgShader().setFloatUniform("uResolution", new float[]{this.fgView.getWidth(), this.fgView.getHeight()});
        View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: com.mi.widget.shader.a
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                LightBgShader.onStart$lambda$2(this.f2386a, view, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        };
        this.fgView.addOnLayoutChangeListener(onLayoutChangeListener);
        this.mLayoutChangeListener = onLayoutChangeListener;
    }

    @Override // com.mi.widget.core.AbsShader
    @CallSuper
    public void onStop() {
        View.OnLayoutChangeListener onLayoutChangeListener = null;
        this.fgView.setRenderEffect(null);
        View.OnLayoutChangeListener onLayoutChangeListener2 = this.mLayoutChangeListener;
        if (onLayoutChangeListener2 != null) {
            View view = this.fgView;
            if (onLayoutChangeListener2 == null) {
                n.w("mLayoutChangeListener");
            } else {
                onLayoutChangeListener = onLayoutChangeListener2;
            }
            view.removeOnLayoutChangeListener(onLayoutChangeListener);
        }
    }

    @UiThread
    public final void setBottomApertureSize(SizeF value) {
        n.g(value, "value");
        this.bottomApertureSize = value;
        invalidate$hyper_widget_1_0_7_pluginRelease();
    }

    @UiThread
    public final void setGlowDistance(float f2) {
        this.glowDistance = f2;
        if (this.strategy == ShaderStrategy.AGSL) {
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uGlareWidth", f2);
            invalidate$hyper_widget_1_0_7_pluginRelease();
        }
    }

    @UiThread
    public final void setGlowRDCornerRadius(float f2) {
        this.glowRDCornerRadius = f2;
        if (this.strategy == ShaderStrategy.AGSL) {
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uSdfRadius", f2);
            getMFgShader().setFloatUniform("uSdfRadius", f2);
            invalidate$hyper_widget_1_0_7_pluginRelease();
        }
    }

    @UiThread
    public final void setUpperApertureSize(SizeF value) {
        n.g(value, "value");
        this.upperApertureSize = value;
        invalidate$hyper_widget_1_0_7_pluginRelease();
    }

    @UiThread
    public final void startByAnim() {
        if (this.strategy != ShaderStrategy.AGSL) {
            Log.i(getDebugName(), "startByAnim ignore Shader Effect for strategy=" + this.strategy);
            return;
        }
        if (this.mRunningByAnim) {
            return;
        }
        this.mRunningByAnim = true;
        Log.d(getDebugName(), "startByAnim bgAlpha=" + this.mFolmeAnim.mBgBackgroundAlpha + ", fgAlpha=" + this.mStopFolmeAnim.mFgAlphaProgress);
        Folme.clean(this.mFolmeAnim);
        Folme.clean(this.mStopFolmeAnim);
        Folme.useValue(this.mStartFolmeAnim).setFlags(1L).setTo("mFgEdgeHighlightProgress", Float.valueOf(0.0f)).to("mFgEdgeHighlightProgress", Float.valueOf(1.0f), new AnimConfig().setEase(FolmeEase.spring(3.0f, 0.8f)).setDelay(100L));
        Folme.useValue(this.mStartFolmeAnim).setFlags(1L).setTo("mBgEdgeHighlightProgress", Float.valueOf(0.0f)).to("mBgEdgeHighlightProgress", Float.valueOf(1.0f), new AnimConfig().setEase(FolmeEase.spring(3.0f, 0.8f)).setDelay(100L));
        Folme.useValue(this.mFolmeAnim).setFlags(1L).to("mBgBackgroundAlpha", Float.valueOf(1.0f), new AnimConfig().setEase(FolmeEase.spring(0.7f, 0.5f)).setDelay(200L).addListeners(new TransitionListener() { // from class: com.mi.widget.shader.LightBgShader.startByAnim.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                LightBgShader.this.mStopFolmeAnim.mFgAlphaProgress = 1.0f;
            }
        }));
        start();
    }

    public final void startByTrans$hyper_widget_1_0_7_pluginRelease() {
        if (this.strategy != ShaderStrategy.AGSL) {
            Log.i(getDebugName(), "startByTrans ignore Shader Effect for strategy=" + this.strategy);
            return;
        }
        if (this.mRunningByAnim) {
            return;
        }
        this.mRunningByAnim = true;
        Log.d(getDebugName(), "startByTrans bgAlpha=" + this.mFolmeAnim.mBgBackgroundAlpha + ", fgAlpha=" + this.mStopFolmeAnim.mFgAlphaProgress);
        Folme.clean(this.mStopFolmeAnim);
        Folme.clean(this.mFolmeAnim);
        StartFolmeAnim startFolmeAnim = this.mStartFolmeAnim;
        startFolmeAnim.mFgEdgeHighlightProgress = 1.0f;
        startFolmeAnim.mBgEdgeHighlightProgress = 1.0f;
        Folme.useValue(this.mStopFolmeAnim).setFlags(1L).to("mFgAlphaProgress", Float.valueOf(1.0f), new AnimConfig().setEase(FolmeEase.spring(0.95f, 0.35f)));
        Folme.useValue(this.mFolmeAnim).setFlags(1L).to("mBgBackgroundAlpha", Float.valueOf(1.0f), new AnimConfig().setEase(FolmeEase.spring(0.95f, 0.35f)).addListeners(new TransitionListener() { // from class: com.mi.widget.shader.LightBgShader$startByTrans$1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                this.f2365a.mStopFolmeAnim.mFgAlphaProgress = 1.0f;
            }
        }));
        start();
    }

    public final void startNoAnim$hyper_widget_1_0_7_pluginRelease() {
        if (this.strategy != ShaderStrategy.AGSL) {
            Log.i(getDebugName(), "startNoAnim ignore Shader Effect for strategy=" + this.strategy);
            return;
        }
        if (this.mRunningByAnim) {
            Log.w(getDebugName(), "startNoAnim interrupt other start-animation for strategy=" + this.strategy);
        }
        this.mRunningByAnim = true;
        Log.d(getDebugName(), "startNoAnim bgAlpha=" + this.mFolmeAnim.mBgBackgroundAlpha + ", fgAlpha=" + this.mStopFolmeAnim.mFgAlphaProgress);
        Folme.clean(this.mStopFolmeAnim);
        Folme.clean(this.mFolmeAnim);
        StartFolmeAnim startFolmeAnim = this.mStartFolmeAnim;
        startFolmeAnim.mFgEdgeHighlightProgress = 1.0f;
        startFolmeAnim.mBgEdgeHighlightProgress = 1.0f;
        this.mFolmeAnim.mBgBackgroundAlpha = 1.0f;
        this.mStopFolmeAnim.mFgAlphaProgress = 1.0f;
        start();
    }

    @UiThread
    public final void stopByAnim() {
        if (this.strategy != ShaderStrategy.AGSL) {
            Log.i(getDebugName(), "stopByAnim ignore Shader Effect for strategy=" + this.strategy);
            return;
        }
        if (this.mRunningByAnim) {
            this.mRunningByAnim = false;
            Log.d(getDebugName(), "stopByAnim bgAlpha=" + this.mFolmeAnim.mBgBackgroundAlpha + ", fgHighlightProgress=" + this.mStartFolmeAnim.mFgEdgeHighlightProgress + ", fgAlpha=" + this.mStopFolmeAnim.mFgAlphaProgress);
            Folme.clean(this.mStartFolmeAnim);
            Folme.clean(this.mFolmeAnim);
            Folme.useValue(this.mStopFolmeAnim).setFlags(1L).to("mFgAlphaProgress", Float.valueOf(0.0f), new AnimConfig().setEase(FolmeEase.spring(0.95f, 0.35f)).addListeners(new TransitionListener() { // from class: com.mi.widget.shader.LightBgShader.stopByAnim.1
                @Override // miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                    LightBgShader.this.stop();
                }
            }));
            Folme.useValue(this.mFolmeAnim).setFlags(1L).to("mBgBackgroundAlpha", Float.valueOf(0.0f), new AnimConfig().setEase(FolmeEase.spring(0.95f, 0.35f)));
        }
    }

    public final void stopByTrans$hyper_widget_1_0_7_pluginRelease() {
        if (this.strategy != ShaderStrategy.AGSL) {
            Log.i(getDebugName(), "stopByTrans ignore Shader Effect for strategy=" + this.strategy);
            return;
        }
        if (this.mRunningByAnim) {
            this.mRunningByAnim = false;
            Log.d(getDebugName(), "stopByTrans bgAlpha=" + this.mFolmeAnim.mBgBackgroundAlpha + ", fgHighlightProgress=" + this.mStartFolmeAnim.mFgEdgeHighlightProgress + ", fgAlpha=" + this.mStopFolmeAnim.mFgAlphaProgress);
            Folme.clean(this.mStartFolmeAnim);
            Folme.clean(this.mFolmeAnim);
            Folme.useValue(this.mStopFolmeAnim).setFlags(1L).to("mFgAlphaProgress", Float.valueOf(0.0f), new AnimConfig().setEase(FolmeEase.spring(0.95f, 0.35f)).addListeners(new TransitionListener() { // from class: com.mi.widget.shader.LightBgShader$stopByTrans$1
                @Override // miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                    this.f2367a.stop();
                }
            }));
            Folme.useValue(this.mFolmeAnim).setFlags(1L).to("mBgBackgroundAlpha", Float.valueOf(0.0f), new AnimConfig().setEase(FolmeEase.spring(0.95f, 0.35f)));
        }
    }

    public final void stopNoAnim$hyper_widget_1_0_7_pluginRelease() {
        if (this.strategy != ShaderStrategy.AGSL) {
            Log.i(getDebugName(), "stopNoAnim ignore Shader Effect for strategy=" + this.strategy);
            return;
        }
        if (!this.mRunningByAnim) {
            Log.w(getDebugName(), "stopNoAnim interrupt other start-animation for strategy=" + this.strategy);
        }
        this.mRunningByAnim = false;
        Log.d(getDebugName(), "stopNoAnim bgAlpha=" + this.mFolmeAnim.mBgBackgroundAlpha + ", fgHighlightProgress=" + this.mStartFolmeAnim.mFgEdgeHighlightProgress + ", fgAlpha=" + this.mStopFolmeAnim.mFgAlphaProgress);
        Folme.clean(this.mStartFolmeAnim);
        Folme.clean(this.mFolmeAnim);
        this.mFolmeAnim.mBgBackgroundAlpha = 0.0f;
        this.mStopFolmeAnim.mFgAlphaProgress = 0.0f;
        stop();
        invalidate$hyper_widget_1_0_7_pluginRelease();
    }

    @Override // com.mi.widget.core.AbsShader
    public void updateShader() {
        getMTextureShader().setFloatUniform("uTime", this.mTime);
        getMTextureShader().setFloatUniform("uLights", this.mLights);
        getMTextureShader().setFloatUniform("uPower", this.mFragData.c());
        getMTextureShader().setFloatUniform("uLightness", this.mFragData.a());
        getMFgShader().setInputShader("uTex1", getMTextureShader());
        getMFgShader().setFloatUniform("uTime", this.mTime);
        getMFgShader().setFloatUniform("uEdgeHighlightProgress", this.mStartFolmeAnim.mFgEdgeHighlightProgress);
        getMFgShader().setFloatUniform("uSize", this.upperApertureSize.getWidth(), this.upperApertureSize.getHeight());
        getMFgShader().setFloatUniform("uAlpha", this.mStopFolmeAnim.mFgAlphaProgress);
        getMShader$hyper_widget_1_0_7_pluginRelease().setInputShader("uTex1", getMTextureShader());
        getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uTime", this.mTime);
        getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uEdgeHighlightProgress", this.mStartFolmeAnim.mBgEdgeHighlightProgress);
        getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uAlpha", this.mFolmeAnim.mBgBackgroundAlpha);
        getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uSize", this.bottomApertureSize.getWidth(), this.bottomApertureSize.getHeight());
        if (this.fgView.isHardwareAccelerated()) {
            this.fgView.setRenderEffect(RenderEffect.createRuntimeShaderEffect(getMFgShader(), "uTex"));
        }
        super.updateShader();
    }

    public static final class FolmeAnim {

        @Keep
        public float mBgBackgroundAlpha;

        public FolmeAnim(float f2) {
            this.mBgBackgroundAlpha = f2;
        }

        public /* synthetic */ FolmeAnim(float f2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0.0f : f2);
        }
    }

    public static final class StopFolmeAnim {

        @Keep
        public float mFgAlphaProgress;

        public StopFolmeAnim(float f2) {
            this.mFgAlphaProgress = f2;
        }

        public /* synthetic */ StopFolmeAnim(float f2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 1.0f : f2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LightBgShader(View bgView, View fgView, ShaderStrategy strategy) {
        super(bgView);
        n.g(bgView, "bgView");
        n.g(fgView, "fgView");
        n.g(strategy, "strategy");
        this.fgView = fgView;
        this.strategy = strategy;
        this.mLights = new float[22];
        this.mFragData = new g0.b(null, 0.0f, 0.0f, 7, null);
        g0.d dVar = new g0.d(null, null, null, null, null, 0L, false, null, null, 0.0f, null, null, 4095, null);
        initPhysics(dVar);
        this.mPhysics = dVar;
        float f2 = 0.0f;
        int i2 = 1;
        DefaultConstructorMarker defaultConstructorMarker = null;
        this.mFolmeAnim = new FolmeAnim(f2, i2, defaultConstructorMarker);
        this.mStartFolmeAnim = new StartFolmeAnim(f2, f2, 3, defaultConstructorMarker);
        this.mStopFolmeAnim = new StopFolmeAnim(f2, i2, defaultConstructorMarker);
        this.mTextureShader$delegate = e.b(new c());
        this.mFgShader$delegate = e.b(new b());
        RuntimeShader mShader$hyper_widget_1_0_7_pluginRelease = getMShader$hyper_widget_1_0_7_pluginRelease();
        SizeF sizeF = U_TEXTURE_SIZE;
        mShader$hyper_widget_1_0_7_pluginRelease.setFloatUniform("uInputResolution", sizeF.getWidth(), sizeF.getHeight());
        mShader$hyper_widget_1_0_7_pluginRelease.setFloatUniform("uGlareWidth", 0.0f);
        mShader$hyper_widget_1_0_7_pluginRelease.setFloatUniform("uEdgeHighlightDim", 0.0f);
        mShader$hyper_widget_1_0_7_pluginRelease.setFloatUniform("uLightness", 0.8f);
        mShader$hyper_widget_1_0_7_pluginRelease.setFloatUniform("uOrigin", U_ORIGIN);
        mShader$hyper_widget_1_0_7_pluginRelease.setFloatUniform("uMin", 0.02f);
        mShader$hyper_widget_1_0_7_pluginRelease.setFloatUniform("uMax", 0.1f);
        mShader$hyper_widget_1_0_7_pluginRelease.setFloatUniform("uSdfRadius", 0.0f);
        this.upperApertureSize = new SizeF(0.0f, 0.0f);
        this.bottomApertureSize = new SizeF(0.0f, 0.0f);
    }

    public static final class StartFolmeAnim {

        @Keep
        public float mBgEdgeHighlightProgress;

        @Keep
        public float mFgEdgeHighlightProgress;

        public StartFolmeAnim(float f2, float f3) {
            this.mFgEdgeHighlightProgress = f2;
            this.mBgEdgeHighlightProgress = f3;
        }

        public /* synthetic */ StartFolmeAnim(float f2, float f3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0.0f : f2, (i2 & 2) != 0 ? 0.0f : f3);
        }
    }
}
