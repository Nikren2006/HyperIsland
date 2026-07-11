package com.mi.widget.view;

import H0.g;
import H0.s;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.AttrRes;
import androidx.annotation.CheckResult;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.compose.runtime.internal.StabilityInferred;
import com.airbnb.lottie.LottieAnimationView;
import com.mi.widget.core.AbsDrawable;
import com.mi.widget.core.AbsShaderView;
import com.mi.widget.core.DriverShareStrategy;
import com.mi.widget.core.IDriverShareStructure;
import com.mi.widget.core.IShaderViewListener;
import com.mi.widget.core.ShaderStrategy;
import com.mi.widget.core.ShaderViewStateListenerDelegate;
import com.mi.widget.core.ShaderViewWrapper;
import com.mi.widget.shader.ChargerTextShader;
import com.mi.widget.shader.WaveDrawable;
import com.mi.widget.utils.ViewsKt;
import e0.AbstractC0334a;
import e0.AbstractC0335b;
import f0.AbstractC0354h;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miuix.view.animation.CubicEaseOutInterpolator;
import miuix.view.animation.SineEaseInInterpolator;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
public final class ChargerView extends FrameLayout implements IShaderViewListener {
    private static final String FONT_FEATURE_MONOSPACED = "tnum";

    @Deprecated
    public static final float MIN_WIDTH_OF_DIFFUSION_AREA = 45.0f;
    private static final String TAG = "ChargerView";
    private final /* synthetic */ ShaderViewStateListenerDelegate $$delegate_0;
    private ChargerType chargerType;
    private final Bitmap mGlowIcon;
    private final LottieAnimationView mLottieView;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public ChargerShaderView mShaderView;
    private a mTextDrawable;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public ChargerShaderText mTextShader;
    private TextView mTextView;
    private ChargerDrawableView mViewDrawable;
    private static final b Companion = new b(null);
    public static final int $stable = 8;

    public static final class ChargerDrawableView extends AbsDrawable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final H0.d f2387a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final H0.d f2388b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Paint f2389c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final CubicEaseOutInterpolator f2390d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final SineEaseInInterpolator f2391e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Bitmap f2392f;

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ChargerType.values().length];
                try {
                    iArr[ChargerType.FAST.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ChargerType.NORMAL.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public static final class a extends o implements Function0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f2393a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(View view) {
                super(0);
                this.f2393a = view;
            }

            @Override // kotlin.jvm.functions.Function0
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public final Bitmap invoke() {
                return BitmapFactory.decodeResource(this.f2393a.getResources(), e0.c.f4017b);
            }
        }

        public static final class b extends o implements Function0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f2394a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(View view) {
                super(0);
                this.f2394a = view;
            }

            @Override // kotlin.jvm.functions.Function0
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public final Bitmap invoke() {
                return BitmapFactory.decodeResource(this.f2394a.getResources(), e0.c.f4019d);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChargerDrawableView(View view) {
            super(view);
            n.g(view, "view");
            this.f2387a = H0.e.b(new a(view));
            this.f2388b = H0.e.b(new b(view));
            this.f2389c = new Paint();
            this.f2390d = new CubicEaseOutInterpolator();
            this.f2391e = new SineEaseInInterpolator();
            this.f2392f = d();
        }

        public final float b(long j2) {
            if (0 <= j2 && j2 < 500) {
                return 0.4f + this.f2390d.getInterpolation(j2 / 500.0f);
            }
            if (500 <= j2 && j2 < 1000) {
                return 1.0f;
            }
            if (1000 > j2 || j2 >= 2001) {
                return 0.4f;
            }
            return 0.4f + (1.0f - this.f2391e.getInterpolation((j2 - 1000) / 1000.0f));
        }

        public final Bitmap c() {
            return (Bitmap) this.f2387a.getValue();
        }

        public final Bitmap d() {
            return (Bitmap) this.f2388b.getValue();
        }

        public final void e(ChargerType type) {
            n.g(type, "type");
            int i2 = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
            if (i2 == 1) {
                this.f2392f = c();
            } else {
                if (i2 != 2) {
                    throw new g();
                }
                this.f2392f = d();
            }
        }

        @Override // com.mi.widget.core.AbsDrawable
        public long getAnimCycleTime() {
            return 2000L;
        }

        @Override // com.mi.widget.core.AbsDrawable
        public String getDebugName() {
            return "ChargerDrawableView";
        }

        @Override // com.mi.widget.core.AbsDrawable
        public void onUpdateDraw(Canvas canvas, long j2, long j3) {
            n.g(canvas, "canvas");
            this.f2389c.setAlpha((int) (Math.max(0.0f, b(j3)) * 255));
            canvas.drawBitmap(this.f2392f, new Rect(Math.max(0, (this.f2392f.getWidth() - canvas.getWidth()) / 2), Math.max(0, (this.f2392f.getHeight() - canvas.getHeight()) / 2), this.f2392f.getWidth(), this.f2392f.getHeight()), new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), this.f2389c);
        }
    }

    @StabilityInferred(parameters = 1)
    @VisibleForTesting(otherwise = 2)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final class ChargerShaderText extends ShaderViewWrapper<ChargerTextShader<View>> {
        public static final int $stable = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChargerShaderText(ChargerTextShader<View> shader, ChargerView listener) {
            super(shader, listener, "ChargerShaderText");
            n.g(shader, "shader");
            n.g(listener, "listener");
        }
    }

    @VisibleForTesting(otherwise = 2)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final class ChargerShaderView extends AbsShaderView<LottieAnimationView> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f2395a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ChargerView f2396b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChargerShaderView(ChargerView chargerView, LottieAnimationView view, ChargerView listener) {
            super(view, listener);
            n.g(view, "view");
            n.g(listener, "listener");
            this.f2396b = chargerView;
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uGlowSize", chargerView.mGlowIcon.getWidth(), chargerView.mGlowIcon.getHeight());
            RuntimeShader mShader$hyper_widget_1_0_7_pluginRelease = getMShader$hyper_widget_1_0_7_pluginRelease();
            Bitmap bitmap = chargerView.mGlowIcon;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            mShader$hyper_widget_1_0_7_pluginRelease.setInputShader("uChargingIconGlow", new BitmapShader(bitmap, tileMode, tileMode));
        }

        @Override // com.mi.widget.core.AbsShader
        public String getDebugName() {
            return "ChargerShaderView";
        }

        @Override // com.mi.widget.core.IShaderDriven
        public DriverShareStrategy getDriverShareStrategy() {
            return DriverShareStrategy.SHARE_COMMON_TYPE;
        }

        @Override // com.mi.widget.core.AbsShader
        public int getShaderResId() {
            return e0.d.f4030c;
        }

        @Override // com.mi.widget.core.IShaderDriven
        public IDriverShareStructure onDriveFrameParameters(boolean z2, long j2, float f2, IDriverShareStructure data) {
            n.g(data, "data");
            this.f2395a = f2;
            return data;
        }

        @Override // com.mi.widget.core.AbsShaderView
        public void updateContent() {
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uGlowTime", this.f2395a);
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uGlowOffset", 0.92f);
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uP11", 0.0f);
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uP12", 0.25f);
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uP21", 0.5f);
            getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uP22", 1.0f);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Keep
    public static final class ChargerType {
        private static final /* synthetic */ O0.a $ENTRIES;
        private static final /* synthetic */ ChargerType[] $VALUES;

        @Keep
        public static final ChargerType NORMAL = new ChargerType("NORMAL", 0);

        @Keep
        public static final ChargerType FAST = new ChargerType("FAST", 1);

        private static final /* synthetic */ ChargerType[] $values() {
            return new ChargerType[]{NORMAL, FAST};
        }

        static {
            ChargerType[] chargerTypeArr$values = $values();
            $VALUES = chargerTypeArr$values;
            $ENTRIES = O0.b.a(chargerTypeArr$values);
        }

        private ChargerType(String str, int i2) {
        }

        public static O0.a getEntries() {
            return $ENTRIES;
        }

        public static ChargerType valueOf(String str) {
            return (ChargerType) Enum.valueOf(ChargerType.class, str);
        }

        public static ChargerType[] values() {
            return (ChargerType[]) $VALUES.clone();
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

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
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ChargerType.values().length];
            try {
                iArr2[ChargerType.FAST.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[ChargerType.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final class a extends WaveDrawable {

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f2397j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(View view) {
            super(view);
            n.g(view, "view");
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [android.view.View] */
        @Override // com.mi.widget.shader.WaveDrawable
        public float alphaByTime(long j2, int i2) {
            int iMin = (int) (500 * Math.min(1.0f, i2 / ViewsKt.dP(getView(), 45.0f)));
            if (0 <= j2 && j2 < 200) {
                return getMAlphaIncreaseInterpolator().getInterpolation(j2 / 200.0f);
            }
            if (200 <= j2 && j2 < 500) {
                return 1.0f;
            }
            if (500 > j2 || j2 > ((long) iMin) + 500) {
                return 0.0f;
            }
            return 1.0f - getMAlphaDecreaseOutInterpolator().getInterpolation((j2 - 500.0f) / iMin);
        }

        @Override // com.mi.widget.core.AbsDrawable
        public String getDebugName() {
            return "ChargerDrawableText";
        }

        @Override // com.mi.widget.shader.WaveDrawable
        public int getWaveBitmapRes() {
            return e0.c.f4020e;
        }

        @Override // com.mi.widget.shader.WaveDrawable
        public float getWaveFinalSize() {
            return this.f2397j;
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect bounds) {
            n.g(bounds, "bounds");
            this.f2397j = bounds.width();
        }
    }

    public static final class b {
        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public b() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ChargerView(Context context) {
        this(context, null, 0, null, 14, null);
        n.g(context, "context");
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMShaderView$annotations() {
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMTextShader$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    private final void setGradientTextColor(final TextView textView) {
        textView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.mi.widget.view.a
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                ChargerView.setGradientTextColor$lambda$10(textView, this, view, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setGradientTextColor$lambda$10(TextView this_setGradientTextColor, ChargerView this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this_setGradientTextColor, "$this_setGradientTextColor");
        n.g(this$0, "this$0");
        this$0.mTextView.getPaint().setShader(new LinearGradient(this_setGradientTextColor.getLayoutDirection() == 1 ? view.getWidth() : 0.0f, 0.0f, this_setGradientTextColor.getLayoutDirection() != 1 ? view.getWidth() : 0.0f, 0.0f, new int[]{this_setGradientTextColor.getContext().getColor(AbstractC0334a.f4002c), this_setGradientTextColor.getContext().getColor(AbstractC0334a.f4001b), this_setGradientTextColor.getContext().getColor(AbstractC0334a.f4000a)}, new float[]{0.0f, 0.6f, 1.0f}, Shader.TileMode.CLAMP));
    }

    @Override // com.mi.widget.core.IShaderViewListener
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public boolean addListener(IShaderViewListener.a listener) {
        n.g(listener, "listener");
        return this.$$delegate_0.addListener(listener);
    }

    @UiThread
    public final ChargerType getChargerType() {
        return this.chargerType;
    }

    public final ChargerShaderView getMShaderView() {
        ChargerShaderView chargerShaderView = this.mShaderView;
        if (chargerShaderView != null) {
            return chargerShaderView;
        }
        n.w("mShaderView");
        return null;
    }

    public final ChargerShaderText getMTextShader$hyper_widget_1_0_7_pluginRelease() {
        ChargerShaderText chargerShaderText = this.mTextShader;
        if (chargerShaderText != null) {
            return chargerShaderText;
        }
        n.w("mTextShader");
        return null;
    }

    @UiThread
    public final TextView getTextView() {
        return this.mTextView;
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

    @UiThread
    public final void setChargerType(ChargerType value) {
        n.g(value, "value");
        ChargerType chargerType = this.chargerType;
        this.chargerType = value;
        if (chargerType == value) {
            return;
        }
        if (this.mLottieView.isAnimating()) {
            this.mLottieView.cancelAnimation();
            this.mLottieView.setProgress(1.0f);
        }
        ChargerDrawableView chargerDrawableView = this.mViewDrawable;
        if (chargerDrawableView != null) {
            if (chargerDrawableView == null) {
                n.w("mViewDrawable");
                chargerDrawableView = null;
            }
            chargerDrawableView.e(value);
        }
        int i2 = WhenMappings.$EnumSwitchMapping$1[value.ordinal()];
        if (i2 == 1) {
            this.mLottieView.setSpeed(1.0f);
            this.mLottieView.playAnimation();
        } else {
            if (i2 != 2) {
                return;
            }
            this.mLottieView.setSpeed(-1.0f);
            this.mLottieView.playAnimation();
        }
    }

    public final void setMShaderView(ChargerShaderView chargerShaderView) {
        n.g(chargerShaderView, "<set-?>");
        this.mShaderView = chargerShaderView;
    }

    public final void setMTextShader$hyper_widget_1_0_7_pluginRelease(ChargerShaderText chargerShaderText) {
        n.g(chargerShaderText, "<set-?>");
        this.mTextShader = chargerShaderText;
    }

    @UiThread
    public final void setTextView(TextView value) {
        n.g(value, "value");
        if (value.getParent() != null) {
            throw new IllegalArgumentException("The view must not be a child of any ViewGroup");
        }
        removeView(this.mTextView);
        addView(value, 0);
        this.mTextView = value;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ChargerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, null, 12, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ChargerView(Context context, AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, null, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ ChargerView(Context context, AttributeSet attributeSet, int i2, ShaderStrategy shaderStrategy, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? AbstractC0354h.a() : shaderStrategy);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargerView(Context context, AttributeSet attributeSet, @AttrRes int i2, ShaderStrategy strategy) {
        super(context, attributeSet, i2);
        n.g(context, "context");
        n.g(strategy, "strategy");
        this.$$delegate_0 = new ShaderViewStateListenerDelegate();
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), e0.c.f4018c);
        this.mGlowIcon = bitmapDecodeResource;
        FrameLayout frameLayout = new FrameLayout(context);
        TextView textView = new TextView(context);
        textView.setGravity(16);
        textView.setTextSize(1, 14.0f);
        textView.setTypeface(Typeface.create("mipro-demibold", 0));
        textView.setTextColor(-16711936);
        textView.setSingleLine(true);
        textView.setFontFeatureSettings(FONT_FEATURE_MONOSPACED);
        this.mTextView = textView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388629;
        layoutParams.setMarginStart(frameLayout.getResources().getDimensionPixelSize(AbstractC0335b.f4011b));
        layoutParams.setMarginEnd(bitmapDecodeResource.getWidth() - ((int) Math.floor(bitmapDecodeResource.getWidth() * frameLayout.getResources().getFloat(AbstractC0335b.f4010a))));
        s sVar = s.f314a;
        frameLayout.addView(textView, layoutParams);
        addView(frameLayout, -1, bitmapDecodeResource.getHeight());
        LottieAnimationView lottieAnimationView = new LottieAnimationView(context);
        this.mLottieView = lottieAnimationView;
        lottieAnimationView.setRepeatCount(0);
        lottieAnimationView.setAnimation(e0.d.f4031d);
        lottieAnimationView.setProgress(0.0f);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight());
        layoutParams2.gravity = 8388629;
        addView(lottieAnimationView, layoutParams2);
        setGradientTextColor(this.mTextView);
        int i3 = WhenMappings.$EnumSwitchMapping$0[strategy.ordinal()];
        if (i3 == 1) {
            setMShaderView(new ChargerShaderView(this, lottieAnimationView, this));
            ChargerShaderText chargerShaderText = new ChargerShaderText(new ChargerTextShader(frameLayout), this);
            chargerShaderText.getShader().setGlowIconWidth(bitmapDecodeResource.getWidth());
            setMTextShader$hyper_widget_1_0_7_pluginRelease(chargerShaderText);
            getMShaderView().getShaderDriver().addShaderDriven(getMTextShader$hyper_widget_1_0_7_pluginRelease().getShader());
        } else if (i3 != 2) {
            ChargerDrawableView chargerDrawableView = new ChargerDrawableView(lottieAnimationView);
            chargerDrawableView.start();
            this.mViewDrawable = chargerDrawableView;
        } else {
            ChargerDrawableView chargerDrawableView2 = new ChargerDrawableView(lottieAnimationView);
            chargerDrawableView2.start();
            this.mViewDrawable = chargerDrawableView2;
            a aVar = new a(frameLayout);
            aVar.setGlowIconWidth$hyper_widget_1_0_7_pluginRelease(bitmapDecodeResource.getWidth());
            aVar.start();
            this.mTextDrawable = aVar;
        }
        this.chargerType = ChargerType.NORMAL;
    }
}
