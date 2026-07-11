package com.mi.widget.view;

import H0.s;
import I0.u;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.AnyThread;
import androidx.annotation.AttrRes;
import androidx.annotation.CheckResult;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.Size;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.AbsDrawable;
import com.mi.widget.core.AbsShaderView;
import com.mi.widget.core.DriverShareStrategy;
import com.mi.widget.core.IDriverShareStructure;
import com.mi.widget.core.IShaderViewListener;
import com.mi.widget.core.ShaderAnimator;
import com.mi.widget.core.ShaderStrategy;
import com.mi.widget.core.ShaderViewStateListenerDelegate;
import f0.AbstractC0354h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.C;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
public final class FlashLightView extends FrameLayout implements IShaderViewListener {
    private static final boolean DEBUG = true;
    private static final String LOG_TAG = "FlashLightShader";
    private static int gShaderNumber;
    private final /* synthetic */ ShaderViewStateListenerDelegate $$delegate_0;

    @Size(4)
    private final float[] flashLightOffset;
    private b mFlashLightDrawable;
    private final Bitmap mFlashLightGlowIcon;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private FlashLightShader mFlashLightShader;
    private final ShaderDriverData mShaderDriverData;
    private boolean mSingleDriver;
    private final ShaderStrategy strategy;
    private static final a Companion = new a(null);
    public static final int $stable = 8;
    private static final ShaderAnimator gShaderAnimator = new ShaderAnimator();

    @VisibleForTesting(otherwise = 2)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final class FlashLightShader extends AbsShaderView<AppCompatImageView> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public FlashLightJson f2399a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List f2400b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2401c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ FlashLightView f2402d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FlashLightShader(FlashLightView flashLightView, AppCompatImageView view, FlashLightView listener) {
            super(view, listener);
            n.g(view, "view");
            n.g(listener, "listener");
            this.f2402d = flashLightView;
            this.f2399a = new FlashLightJson(0.0f, 0.0f, 0.0f, (float[]) null, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, -1, 67108863, (DefaultConstructorMarker) null);
            this.f2400b = new ArrayList();
            Map map = (Map) v1.a.f6959d.a(r1.a.a(r1.a.c(C.f5034a), FlashLightJson.Companion.serializer()), readRawString(e0.d.f4034g));
            ArrayList arrayList = new ArrayList(map.size());
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add((FlashLightJson) ((Map.Entry) it.next()).getValue());
            }
            Iterator it2 = u.c0(arrayList).iterator();
            while (it2.hasNext()) {
                this.f2400b.add((FlashLightJson) it2.next());
            }
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(this.f2402d.getContext().getResources(), e0.c.f4024i);
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uIconSize", bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight());
            RuntimeShader mShader$hyper_widget_1_0_7_pluginRelease = getMShader$hyper_widget_1_0_7_pluginRelease();
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            mShader$hyper_widget_1_0_7_pluginRelease.setInputBuffer("uFlashlightUiIconTex", new BitmapShader(bitmapDecodeResource, tileMode, tileMode));
            Bitmap bitmapDecodeResource2 = BitmapFactory.decodeResource(this.f2402d.getContext().getResources(), e0.c.f4023h);
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uGlowSize", bitmapDecodeResource2.getWidth(), bitmapDecodeResource2.getHeight());
            getMShader$hyper_widget_1_0_7_pluginRelease().setInputBuffer("uFlashlightUiGlowTex", new BitmapShader(bitmapDecodeResource2, tileMode, tileMode));
        }

        @Override // com.mi.widget.core.AbsShaderView
        public boolean attachStateStopFrame() {
            return false;
        }

        public final void b(float f2, FlashLightJson flashLightJson, List list) {
            int iFloor = (int) Math.floor(r6 / r0);
            float f3 = (f2 * 100.0f) % 25;
            FlashLightJson flashLightJson2 = (FlashLightJson) list.get(iFloor);
            FlashLightJson flashLightJson3 = (FlashLightJson) list.get((int) Math.min(iFloor + 1, list.size() - 1));
            c(flashLightJson.getBeam1PointF(), f3, flashLightJson2.getBeam1PointF(), flashLightJson3.getBeam1PointF());
            flashLightJson.setBeam1LineY(c(Float.valueOf(flashLightJson.getBeam1LineY()), f3, Float.valueOf(flashLightJson2.getBeam1LineY()), Float.valueOf(flashLightJson3.getBeam1LineY())));
            flashLightJson.setBeam1E(c(Float.valueOf(flashLightJson.getBeam1E()), f3, Float.valueOf(flashLightJson2.getBeam1E()), Float.valueOf(flashLightJson3.getBeam1E())));
            flashLightJson.setBeam1Highband(c(Float.valueOf(flashLightJson.getBeam1Highband()), f3, Float.valueOf(flashLightJson2.getBeam1Highband()), Float.valueOf(flashLightJson3.getBeam1Highband())));
            c(flashLightJson.getBeam1Rgb(), f3, flashLightJson2.getBeam1Rgb(), flashLightJson3.getBeam1Rgb());
            flashLightJson.setBeam1Alpha(c(Float.valueOf(flashLightJson.getBeam1Alpha()), f3, Float.valueOf(flashLightJson2.getBeam1Alpha()), Float.valueOf(flashLightJson3.getBeam1Alpha())));
            flashLightJson.setBeam1ScaleX(c(Float.valueOf(flashLightJson.getBeam1ScaleX()), f3, Float.valueOf(flashLightJson2.getBeam1ScaleX()), Float.valueOf(flashLightJson3.getBeam1ScaleX())));
            flashLightJson.setBeam1Shift(c(Float.valueOf(flashLightJson.getBeam1Shift()), f3, Float.valueOf(flashLightJson2.getBeam1Shift()), Float.valueOf(flashLightJson3.getBeam1Shift())));
            flashLightJson.setBeam1Dispersion(c(Float.valueOf(flashLightJson.getBeam1Dispersion()), f3, Float.valueOf(flashLightJson2.getBeam1Dispersion()), Float.valueOf(flashLightJson3.getBeam1Dispersion())));
            c(flashLightJson.getBeam5PointF(), f3, flashLightJson2.getBeam5PointF(), flashLightJson3.getBeam5PointF());
            flashLightJson.setBeam5LineY(c(Float.valueOf(flashLightJson.getBeam5LineY()), f3, Float.valueOf(flashLightJson2.getBeam5LineY()), Float.valueOf(flashLightJson3.getBeam5LineY())));
            flashLightJson.setBeam5E(c(Float.valueOf(flashLightJson.getBeam5E()), f3, Float.valueOf(flashLightJson2.getBeam5E()), Float.valueOf(flashLightJson3.getBeam5E())));
            flashLightJson.setBeam5Highband(c(Float.valueOf(flashLightJson.getBeam5Highband()), f3, Float.valueOf(flashLightJson2.getBeam5Highband()), Float.valueOf(flashLightJson3.getBeam5Highband())));
            c(flashLightJson.getBeam5Rgb(), f3, flashLightJson2.getBeam5Rgb(), flashLightJson3.getBeam5Rgb());
            flashLightJson.setBeam5Alpha(c(Float.valueOf(flashLightJson.getBeam5Alpha()), f3, Float.valueOf(flashLightJson2.getBeam5Alpha()), Float.valueOf(flashLightJson3.getBeam5Alpha())));
            flashLightJson.setBeam5ScaleX(c(Float.valueOf(flashLightJson.getBeam5ScaleX()), f3, Float.valueOf(flashLightJson2.getBeam5ScaleX()), Float.valueOf(flashLightJson3.getBeam5ScaleX())));
            flashLightJson.setBeam5Shift(c(Float.valueOf(flashLightJson.getBeam5Shift()), f3, Float.valueOf(flashLightJson2.getBeam5Shift()), Float.valueOf(flashLightJson3.getBeam5Shift())));
            flashLightJson.setBeam5Dispersion(c(Float.valueOf(flashLightJson.getBeam5Dispersion()), f3, Float.valueOf(flashLightJson2.getBeam5Dispersion()), Float.valueOf(flashLightJson3.getBeam5Dispersion())));
            c(flashLightJson.getBeam6PointF(), f3, flashLightJson2.getBeam6PointF(), flashLightJson3.getBeam6PointF());
            flashLightJson.setBeam6LineY(c(Float.valueOf(flashLightJson.getBeam6LineY()), f3, Float.valueOf(flashLightJson2.getBeam6LineY()), Float.valueOf(flashLightJson3.getBeam6LineY())));
            flashLightJson.setBeam6E(c(Float.valueOf(flashLightJson.getBeam6E()), f3, Float.valueOf(flashLightJson2.getBeam6E()), Float.valueOf(flashLightJson3.getBeam6E())));
            flashLightJson.setBeam6Highband(c(Float.valueOf(flashLightJson.getBeam6Highband()), f3, Float.valueOf(flashLightJson2.getBeam6Highband()), Float.valueOf(flashLightJson3.getBeam6Highband())));
            c(flashLightJson.getBeam6Rgb(), f3, flashLightJson2.getBeam6Rgb(), flashLightJson3.getBeam6Rgb());
            flashLightJson.setBeam6Alpha(c(Float.valueOf(flashLightJson.getBeam6Alpha()), f3, Float.valueOf(flashLightJson2.getBeam6Alpha()), Float.valueOf(flashLightJson3.getBeam6Alpha())));
            flashLightJson.setBeam6ScaleX(c(Float.valueOf(flashLightJson.getBeam6ScaleX()), f3, Float.valueOf(flashLightJson2.getBeam6ScaleX()), Float.valueOf(flashLightJson3.getBeam6ScaleX())));
            flashLightJson.setBeam6Shift(c(Float.valueOf(flashLightJson.getBeam6Shift()), f3, Float.valueOf(flashLightJson2.getBeam6Shift()), Float.valueOf(flashLightJson3.getBeam6Shift())));
            flashLightJson.setBeam6Dispersion(c(Float.valueOf(flashLightJson.getBeam6Dispersion()), f3, Float.valueOf(flashLightJson2.getBeam6Dispersion()), Float.valueOf(flashLightJson3.getBeam6Dispersion())));
        }

        public final float c(Object obj, float f2, Object obj2, Object obj3) {
            if (!(obj instanceof float[])) {
                if (!(obj instanceof Float)) {
                    return 0.0f;
                }
                n.e(obj2, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue = ((Float) obj2).floatValue();
                n.e(obj3, "null cannot be cast to non-null type kotlin.Float");
                return d(f2 / 25, fFloatValue, ((Float) obj3).floatValue());
            }
            float[] fArr = (float[]) obj;
            int length = fArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                n.e(obj2, "null cannot be cast to non-null type kotlin.FloatArray");
                float f3 = ((float[]) obj2)[i2];
                n.e(obj3, "null cannot be cast to non-null type kotlin.FloatArray");
                fArr[i2] = d(f2 / 25, f3, ((float[]) obj3)[i2]);
            }
            return 0.0f;
        }

        public final float d(float f2, float f3, float f4) {
            return f3 + ((f4 - f3) * f2);
        }

        @Override // com.mi.widget.core.AbsShader
        public String getDebugName() {
            return "FlashLightView#" + this.f2402d.hashCode();
        }

        @Override // com.mi.widget.core.IShaderDriven
        public DriverShareStrategy getDriverShareStrategy() {
            return DriverShareStrategy.SHARE_SAME_TYPE;
        }

        @Override // com.mi.widget.core.AbsShader
        public int getShaderResId() {
            return e0.d.f4033f;
        }

        @Override // com.mi.widget.core.IShaderDriven
        public IDriverShareStructure onDriveFrameParameters(boolean z2, long j2, float f2, IDriverShareStructure data) {
            n.g(data, "data");
            ShaderDriverData shaderDriverData = data.isInitialized() ? (ShaderDriverData) data : this.f2402d.mShaderDriverData;
            if (z2) {
                b(shaderDriverData.a(), shaderDriverData.b(), this.f2400b);
            }
            this.f2399a = shaderDriverData.b();
            this.f2401c = shaderDriverData.a();
            return shaderDriverData;
        }

        @Override // com.mi.widget.core.AbsShader
        public void onStart() {
            AnimConfig animConfig = new AnimConfig();
            animConfig.setEase(FolmeEase.spring(1.8f, 0.6f));
            animConfig.setDelay(150L);
            if (this.f2402d.mSingleDriver) {
                Log.d(getDebugName(), "add new shader " + this + " to singleDriver");
                FlashLightView.gShaderAnimator.addShader(this);
                a unused = FlashLightView.Companion;
                int i2 = FlashLightView.gShaderNumber;
                FlashLightView.gShaderNumber = i2 + 1;
                if (i2 != 0) {
                    return;
                }
                Log.i(getDebugName(), "add first shader to singleDriver and start animation.");
                FlashLightView.gShaderAnimator.start();
            }
            Folme.useValue(this.f2402d.mShaderDriverData).setFlags(1L).setTo("progress", Float.valueOf(0.0f)).to("progress", Float.valueOf(1.0f), animConfig);
        }

        @Override // com.mi.widget.core.AbsShader
        public void onStop() {
            Folme.useValue(this.f2402d).cancel();
            if (this.f2402d.mSingleDriver) {
                Log.d(getDebugName(), "remove shader " + this + " from singleDriver");
                FlashLightView.gShaderAnimator.removeShader(this);
                a unused = FlashLightView.Companion;
                FlashLightView.gShaderNumber = FlashLightView.gShaderNumber + (-1);
                if (FlashLightView.gShaderNumber == 0) {
                    Log.i(getDebugName(), "remove last shader to singleDriver and stop animation.");
                    FlashLightView.gShaderAnimator.stop();
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.mi.widget.core.AbsShaderView
        public void updateContent() {
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uOffset", this.f2402d.getFlashLightOffset()[0], this.f2402d.getFlashLightOffset()[1]);
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uEffectProgress", this.f2401c);
            getMShader$hyper_widget_1_0_7_pluginRelease().setIntUniform("uRTL", ((AppCompatImageView) getView()).getLayoutDirection() == 1 ? 1 : 0);
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam1PointF", this.f2399a.getBeam1PointF());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam1LineY", this.f2399a.getBeam1LineY());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam1E", this.f2399a.getBeam1E());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam1Highband", this.f2399a.getBeam1Highband());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam1Color", this.f2399a.getBeam1Rgb());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam1Alpha", this.f2399a.getBeam1Alpha());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam1ScaleX", this.f2399a.getBeam1ScaleX());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam1Shift", this.f2399a.getBeam1Shift());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam1Dispersion", this.f2399a.getBeam1Dispersion());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam5PointF", this.f2399a.getBeam5PointF());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam5LineY", this.f2399a.getBeam5LineY());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam5E", this.f2399a.getBeam5E());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam5Highband", this.f2399a.getBeam5Highband());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam5Color", this.f2399a.getBeam5Rgb());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam5Alpha", this.f2399a.getBeam5Alpha());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam5ScaleX", this.f2399a.getBeam5ScaleX());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam5Shift", this.f2399a.getBeam5Shift());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam5Dispersion", this.f2399a.getBeam5Dispersion());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam6PointF", this.f2399a.getBeam6PointF());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam6LineY", this.f2399a.getBeam6LineY());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam6E", this.f2399a.getBeam6E());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam6Highband", this.f2399a.getBeam6Highband());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam6Color", this.f2399a.getBeam6Rgb());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam6Alpha", this.f2399a.getBeam6Alpha());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam6ScaleX", this.f2399a.getBeam6ScaleX());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam6Shift", this.f2399a.getBeam6Shift());
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uBeam6Dispersion", this.f2399a.getBeam6Dispersion());
        }
    }

    public static final class ShaderDriverData implements IDriverShareStructure {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final FlashLightJson f2403a = new FlashLightJson(0.0f, 0.0f, 0.0f, (float[]) null, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, -1, 67108863, (DefaultConstructorMarker) null);

        @Keep
        private float progress;

        public final float a() {
            return this.progress;
        }

        public final FlashLightJson b() {
            return this.f2403a;
        }

        @Override // com.mi.widget.core.IDriverShareStructure
        public boolean isInitialized() {
            return true;
        }
    }

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

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public final class b extends AbsDrawable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Paint f2404a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ FlashLightView f2405b;

        public static final class a extends o implements Function1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ FlashLightView f2406a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ float f2407b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ b f2408c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FlashLightView flashLightView, float f2, b bVar) {
                super(1);
                this.f2406a = flashLightView;
                this.f2407b = f2;
                this.f2408c = bVar;
            }

            public final void b(Canvas drawWithLayoutDirection) {
                n.g(drawWithLayoutDirection, "$this$drawWithLayoutDirection");
                float f2 = this.f2406a.getFlashLightOffset()[2];
                float f3 = this.f2406a.getFlashLightOffset()[3];
                FlashLightView flashLightView = this.f2406a;
                float f4 = this.f2407b;
                b bVar = this.f2408c;
                int iSave = drawWithLayoutDirection.save();
                drawWithLayoutDirection.translate(f2, f3);
                try {
                    drawWithLayoutDirection.drawBitmap(flashLightView.mFlashLightGlowIcon, 0.0f, f4, bVar.f2404a);
                } finally {
                    drawWithLayoutDirection.restoreToCount(iSave);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                b((Canvas) obj);
                return s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(FlashLightView flashLightView, AppCompatImageView view) {
            super(view);
            n.g(view, "view");
            this.f2405b = flashLightView;
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            setAutoMirrored(true);
            this.f2404a = paint;
        }

        public final void c(Canvas canvas, Function1 block) {
            n.g(canvas, "canvas");
            n.g(block, "block");
            if (getLayoutDirection() != 1) {
                block.invoke(canvas);
                return;
            }
            int iSave = canvas.save();
            canvas.scale(-1.0f, 1.0f, canvas.getWidth() / 2.0f, canvas.getHeight() / 2.0f);
            try {
                block.invoke(canvas);
            } finally {
                canvas.restoreToCount(iSave);
            }
        }

        @Override // com.mi.widget.core.AbsDrawable
        public long getAnimCycleTime() {
            return 0L;
        }

        @Override // com.mi.widget.core.AbsDrawable
        public String getDebugName() {
            return "FlashLightDrawable";
        }

        @Override // com.mi.widget.core.AbsDrawable
        public boolean getRefreshByActive$hyper_widget_1_0_7_pluginRelease() {
            return false;
        }

        @Override // com.mi.widget.core.AbsDrawable
        public void onUpdateDraw(Canvas canvas, long j2, long j3) {
            n.g(canvas, "canvas");
            c(canvas, new a(this.f2405b, Math.max(0.0f, (canvas.getHeight() - this.f2405b.mFlashLightGlowIcon.getHeight()) / 2.0f), this));
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FlashLightView(Context context) {
        this(context, null, 0, null, 14, null);
        n.g(context, "context");
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMFlashLightShader$annotations() {
    }

    @Override // com.mi.widget.core.IShaderViewListener
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public boolean addListener(IShaderViewListener.a listener) {
        n.g(listener, "listener");
        return this.$$delegate_0.addListener(listener);
    }

    @AnyThread
    @Size(4)
    public final float[] getFlashLightOffset() {
        return this.flashLightOffset;
    }

    public final FlashLightShader getMFlashLightShader() {
        FlashLightShader flashLightShader = this.mFlashLightShader;
        if (flashLightShader != null) {
            return flashLightShader;
        }
        n.w("mFlashLightShader");
        return null;
    }

    @AnyThread
    public final boolean getShareSingleDriver() {
        return this.mSingleDriver;
    }

    @Override // com.mi.widget.core.IShaderViewListener
    @CheckResult
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public boolean notifyVisibleChanged(View curView, View changedView, int i2) {
        n.g(curView, "curView");
        n.g(changedView, "changedView");
        return this.$$delegate_0.notifyVisibleChanged(curView, changedView, i2);
    }

    @Override // android.view.View
    @SuppressLint({"CheckResult"})
    public void onVisibilityChanged(View changedView, int i2) {
        n.g(changedView, "changedView");
        super.onVisibilityChanged(changedView, i2);
        notifyVisibleChanged(this, changedView, i2);
    }

    @Override // com.mi.widget.core.IShaderViewListener
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public boolean removeListener(IShaderViewListener.a listener) {
        n.g(listener, "listener");
        return this.$$delegate_0.removeListener(listener);
    }

    @AnyThread
    public final void setShareSingleDriver(boolean z2) {
        if (this.mFlashLightShader != null && getMFlashLightShader().isRunning()) {
            throw new IllegalStateException("Can't change shareSingleDriver while the FlashLightView is visible");
        }
        if (this.strategy != ShaderStrategy.AGSL) {
            Log.w(LOG_TAG, "shareSingleDriver is only supported when strategy == ShaderStrategy.AGSL");
        }
        Log.d(LOG_TAG, "shareSingleDriver=" + z2);
        this.mSingleDriver = z2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FlashLightView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, null, 12, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FlashLightView(Context context, AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, null, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ FlashLightView(Context context, AttributeSet attributeSet, int i2, ShaderStrategy shaderStrategy, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? AbstractC0354h.a() : shaderStrategy);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlashLightView(Context context, AttributeSet attributeSet, @AttrRes int i2, ShaderStrategy strategy) throws Exception {
        super(context, attributeSet, i2);
        n.g(context, "context");
        n.g(strategy, "strategy");
        this.strategy = strategy;
        this.$$delegate_0 = new ShaderViewStateListenerDelegate();
        this.mShaderDriverData = new ShaderDriverData();
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), e0.c.f4021f);
        this.mFlashLightGlowIcon = bitmapDecodeResource;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e0.e.f4040a, i2, 0);
        try {
            boolean z2 = typedArrayObtainStyledAttributes.getBoolean(e0.e.f4041b, false);
            this.mSingleDriver = z2;
            Log.i(LOG_TAG, "init view=" + this + " useSingleShaderDriver: " + z2);
            T0.a.a(typedArrayObtainStyledAttributes, null);
            AppCompatImageView appCompatImageView = new AppCompatImageView(context);
            appCompatImageView.setBackgroundColor(0);
            setBackgroundColor(0);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, bitmapDecodeResource.getHeight());
            layoutParams.gravity = 8388627;
            s sVar = s.f314a;
            addView(appCompatImageView, layoutParams);
            int i3 = WhenMappings.$EnumSwitchMapping$0[strategy.ordinal()];
            if (i3 == 1) {
                this.mFlashLightShader = new FlashLightShader(this, appCompatImageView, this);
            } else if (i3 == 2 || i3 == 3) {
                b bVar = new b(this, appCompatImageView);
                bVar.start();
                this.mFlashLightDrawable = bVar;
            }
            this.flashLightOffset = new float[]{-0.4f, 0.15f, 0.0f, 0.0f};
        } finally {
        }
    }
}
