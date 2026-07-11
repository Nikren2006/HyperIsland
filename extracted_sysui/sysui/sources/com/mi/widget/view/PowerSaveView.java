package com.mi.widget.view;

import H0.s;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.AnyThread;
import androidx.annotation.AttrRes;
import androidx.annotation.CallSuper;
import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.AbsDrawable;
import com.mi.widget.core.AbsShaderIcon;
import com.mi.widget.core.IShaderViewListener;
import com.mi.widget.core.ShaderStrategy;
import com.mi.widget.core.ShaderViewStateListenerDelegate;
import com.mi.widget.view.PowerSaveView;
import e0.AbstractC0334a;
import e0.AbstractC0335b;
import f0.AbstractC0354h;
import java.lang.ref.WeakReference;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
public final class PowerSaveView extends LinearLayout implements IShaderViewListener {
    private static final boolean DEBUG = true;
    private static final String LOG_TAG = "PowerSaveView";
    private final /* synthetic */ ShaderViewStateListenerDelegate $$delegate_0;
    private final PowerSaveView$mBatteryChangeReceiver$1 mBatteryChangeReceiver;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private boolean mBatteryChangeReceiverRegistered;
    private float mBatteryLevel;
    private final Bitmap mIcon;
    private a mIconBlur;
    private b mIconDrawable;
    private BatteryShaderIcon mIconShader;
    private final View mIconView;
    private final View mIconViewBlur;
    private BatteryShaderText mTextShader;
    private final TextView mTextView;
    private static final c Companion = new c(null);
    public static final int $stable = 8;

    @VisibleForTesting(otherwise = 2)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final class BatteryShaderIcon extends AbsShaderIcon<View> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ PowerSaveView f2418a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BatteryShaderIcon(PowerSaveView powerSaveView, View view) {
            super(view);
            n.g(view, "view");
            this.f2418a = powerSaveView;
            RuntimeShader mShader = getMShader();
            Bitmap bitmap = powerSaveView.mIcon;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            mShader.setInputShader("uTexBattery", new BitmapShader(bitmap, tileMode, tileMode));
            getMShader().setFloatUniform("uBatterySize", powerSaveView.mIcon.getWidth(), powerSaveView.mIcon.getHeight());
        }

        @Override // com.mi.widget.core.AbsShaderIcon
        public String getDebugName() {
            return "PowerSaveView#BatteryShaderIcon";
        }

        @Override // com.mi.widget.core.AbsShaderIcon
        public int getShaderResId() {
            return e0.d.f4038k;
        }

        @Override // com.mi.widget.core.AbsShaderIcon
        public void updateFrame() {
            getMShader().setFloatUniform("uBatteryPercentage", this.f2418a.mBatteryLevel / 100.0f);
            super.updateFrame();
        }

        @Override // com.mi.widget.core.AbsShaderIcon
        public void updateSize(int i2, int i3) {
            super.updateSize(i2, i3);
            getMShader().setIntUniform("uRTL", getView().getLayoutDirection() != 1 ? 0 : 1);
        }
    }

    @StabilityInferred(parameters = 1)
    @VisibleForTesting(otherwise = 2)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final class BatteryShaderText extends AbsShaderIcon<TextView> {
        public static final int $stable = 0;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f2419a = new a(null);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final float[] f2420b = {0.86f, 0.5f};

        public static final class a {
            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public a() {
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BatteryShaderText(TextView view) {
            super(view);
            n.g(view, "view");
        }

        @Override // com.mi.widget.core.AbsShaderIcon
        public String getDebugName() {
            return "PowerSaveView#BatteryShaderText";
        }

        @Override // com.mi.widget.core.AbsShaderIcon
        public int getShaderResId() {
            return e0.d.f4039l;
        }

        @Override // com.mi.widget.core.AbsShaderIcon
        public void updateFrame() {
            getMShader().setFloatUniform("uRippleTime", 0.0f);
            super.updateFrame();
        }

        @Override // com.mi.widget.core.AbsShaderIcon
        public void updateSize(int i2, int i3) {
            super.updateSize(i2, i3);
            RuntimeShader mShader = getMShader();
            float[] fArr = f2420b;
            mShader.setFloatUniform("uRippleOrigin", i2 * fArr[0], i3 * fArr[1]);
            getMShader().setIntUniform("uRTL", getView().getLayoutDirection() == 1 ? 1 : 0);
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
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final class a extends b {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final Paint f2421i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final float f2422j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ PowerSaveView f2423k;

        /* JADX INFO: renamed from: com.mi.widget.view.PowerSaveView$a$a, reason: collision with other inner class name */
        public static final class C0061a extends o implements Function1 {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ PowerSaveView f2425b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0061a(PowerSaveView powerSaveView) {
                super(1);
                this.f2425b = powerSaveView;
            }

            public final void b(Canvas drawWithLayoutDirection) {
                n.g(drawWithLayoutDirection, "$this$drawWithLayoutDirection");
                drawWithLayoutDirection.drawBitmap(a.this.i(this.f2425b.mBatteryLevel / 100.0f), a.this.g(), a.this.h(), a.this.f2421i);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                b((Canvas) obj);
                return s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(PowerSaveView powerSaveView, View view) {
            super(powerSaveView, view);
            n.g(view, "view");
            this.f2423k = powerSaveView;
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            this.f2421i = paint;
            this.f2422j = view.getResources().getDimension(AbstractC0335b.f4012c);
        }

        @Override // com.mi.widget.view.PowerSaveView.b, com.mi.widget.core.AbsDrawable
        public String getDebugName() {
            return "BatteryDrawableBlur";
        }

        @Override // com.mi.widget.core.AbsDrawable
        public void onStart() {
            View view = getView();
            float f2 = this.f2422j;
            view.setRenderEffect(RenderEffect.createBlurEffect(f2, f2, Shader.TileMode.CLAMP));
        }

        @Override // com.mi.widget.view.PowerSaveView.b, com.mi.widget.core.AbsDrawable
        public void onUpdateDraw(Canvas canvas, long j2, long j3) {
            n.g(canvas, "canvas");
            f(canvas, new C0061a(this.f2423k));
        }
    }

    public class b extends AbsDrawable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Bitmap f2426a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Bitmap f2427b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Paint f2428c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f2429d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public WeakReference f2430e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f2431f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f2432g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ PowerSaveView f2433h;

        public static final class a extends o implements Function1 {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ PowerSaveView f2435b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(PowerSaveView powerSaveView) {
                super(1);
                this.f2435b = powerSaveView;
            }

            public final void b(Canvas drawWithLayoutDirection) {
                n.g(drawWithLayoutDirection, "$this$drawWithLayoutDirection");
                b.this.f2428c.setColorFilter(new PorterDuffColorFilter(b.this.getView().getContext().getColor(AbstractC0334a.f4003d), PorterDuff.Mode.SRC_IN));
                drawWithLayoutDirection.drawBitmap(b.this.f2426a, b.this.g(), b.this.h(), b.this.f2428c);
                b.this.f2428c.setColorFilter(null);
                drawWithLayoutDirection.drawBitmap(b.this.i(this.f2435b.mBatteryLevel / 100.0f), b.this.g(), b.this.h(), b.this.f2428c);
                b.this.f2428c.setXfermode(null);
                drawWithLayoutDirection.drawBitmap(b.this.f2427b, Math.max(0.0f, (drawWithLayoutDirection.getWidth() - b.this.f2427b.getWidth()) / 2.0f), Math.max(0.0f, (drawWithLayoutDirection.getHeight() - b.this.f2427b.getHeight()) / 2.0f), b.this.f2428c);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                b((Canvas) obj);
                return s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(PowerSaveView powerSaveView, View view) {
            super(view);
            n.g(view, "view");
            this.f2433h = powerSaveView;
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(view.getResources(), e0.c.f4026k);
            this.f2426a = bitmapDecodeResource;
            this.f2427b = BitmapFactory.decodeResource(view.getResources(), e0.c.f4027l);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            this.f2428c = paint;
            this.f2430e = new WeakReference(Bitmap.createBitmap(bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight(), Bitmap.Config.ARGB_8888));
        }

        public final void f(Canvas canvas, Function1 block) {
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

        public final float g() {
            return this.f2431f;
        }

        @Override // com.mi.widget.core.AbsDrawable
        public long getAnimCycleTime() {
            return 0L;
        }

        @Override // com.mi.widget.core.AbsDrawable
        public String getDebugName() {
            return "PowerSaveView#BatteryDrawableIcon";
        }

        @Override // com.mi.widget.core.AbsDrawable
        public boolean getRefreshByActive$hyper_widget_1_0_7_pluginRelease() {
            return false;
        }

        public final float h() {
            return this.f2432g;
        }

        public final Bitmap i(float f2) {
            Object obj = this.f2430e.get();
            if (obj != null && this.f2429d == f2) {
                return (Bitmap) obj;
            }
            this.f2429d = f2;
            Bitmap bitmapCreateBitmap = (Bitmap) obj;
            if (bitmapCreateBitmap == null) {
                bitmapCreateBitmap = Bitmap.createBitmap(this.f2426a.getWidth(), this.f2426a.getHeight(), Bitmap.Config.ARGB_8888);
                this.f2430e = new WeakReference(obj);
            }
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new LinearGradient(0.0f, this.f2426a.getHeight() / 2.0f, this.f2426a.getWidth() * f2, this.f2426a.getHeight() / 2.0f, getView().getContext().getColor(AbstractC0334a.f4005f), getView().getContext().getColor(AbstractC0334a.f4004e), Shader.TileMode.CLAMP));
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            float width = f2 * this.f2426a.getWidth();
            float height = this.f2426a.getHeight();
            float dimension = getView().getResources().getDimension(AbstractC0335b.f4013d);
            PorterDuff.Mode mode = PorterDuff.Mode.CLEAR;
            paint.setXfermode(new PorterDuffXfermode(mode));
            canvas.drawRect(0.0f, 0.0f, this.f2426a.getWidth(), this.f2426a.getHeight(), paint);
            paint.setXfermode(null);
            canvas.drawBitmap(this.f2426a, new Rect(0, 0, (int) width, (int) height), new RectF(0.0f, 0.0f, width, height), paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawRoundRect(0.0f, 0.0f, width, height, dimension, dimension, paint);
            paint.setXfermode(new PorterDuffXfermode(mode));
            float f3 = dimension * 2.0f;
            float f4 = width - f3;
            RectF rectF = new RectF(f4, height - f3, width, height);
            RectF rectF2 = new RectF(f4, 0.0f, width, f3);
            Path path = new Path();
            path.moveTo(f4, 0.0f);
            path.lineTo(width, 0.0f);
            path.lineTo(width, height);
            path.lineTo(f4, height);
            path.arcTo(rectF, 90.0f, -90.0f);
            path.lineTo(width, f3);
            path.arcTo(rectF2, 0.0f, -90.0f);
            path.close();
            paint.setXfermode(new PorterDuffXfermode(mode));
            canvas.drawPath(path, paint);
            return bitmapCreateBitmap;
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect bounds) {
            n.g(bounds, "bounds");
            this.f2431f = Math.max(0.0f, (bounds.width() - this.f2426a.getWidth()) / 2.0f);
            this.f2432g = Math.max(0.0f, (bounds.height() - this.f2426a.getHeight()) / 2.0f);
        }

        @Override // com.mi.widget.core.AbsDrawable
        public void onUpdateDraw(Canvas canvas, long j2, long j3) {
            n.g(canvas, "canvas");
            f(canvas, new a(this.f2433h));
        }
    }

    public static final class c {
        public /* synthetic */ c(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public c() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PowerSaveView(Context context) {
        this(context, null, 0, 0, null, 30, null);
        n.g(context, "context");
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMBatteryChangeReceiverRegistered$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMIconShader$annotations() {
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMTextShader$annotations() {
    }

    private final void setGradientTextColor(final TextView textView) {
        textView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.mi.widget.view.d
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                PowerSaveView.setGradientTextColor$lambda$9(textView, this, view, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setGradientTextColor$lambda$9(TextView this_setGradientTextColor, PowerSaveView this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this_setGradientTextColor, "$this_setGradientTextColor");
        n.g(this$0, "this$0");
        this$0.mTextView.getPaint().setShader(new LinearGradient(this_setGradientTextColor.getLayoutDirection() == 1 ? view.getWidth() : 0.0f, 0.0f, this_setGradientTextColor.getLayoutDirection() != 1 ? view.getWidth() : 0.0f, 0.0f, new int[]{this_setGradientTextColor.getContext().getColor(AbstractC0334a.f4009j), this_setGradientTextColor.getContext().getColor(AbstractC0334a.f4008i), this_setGradientTextColor.getContext().getColor(AbstractC0334a.f4007h)}, new float[]{0.0f, 0.6f, 1.0f}, Shader.TileMode.CLAMP));
    }

    private final void updateBatteryChangeReceiver(boolean z2) {
        Log.i(LOG_TAG, "updateBatteryChangeReceiver mBatteryChangeReceiverRegistered=" + this.mBatteryChangeReceiverRegistered + ", register=" + z2);
        if (this.mBatteryChangeReceiverRegistered) {
            if (z2) {
                return;
            }
            this.mBatteryChangeReceiverRegistered = false;
            getContext().unregisterReceiver(this.mBatteryChangeReceiver);
            return;
        }
        if (z2) {
            this.mBatteryChangeReceiverRegistered = true;
            getContext().registerReceiver(this.mBatteryChangeReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        }
    }

    @Override // com.mi.widget.core.IShaderViewListener
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public boolean addListener(IShaderViewListener.a listener) {
        n.g(listener, "listener");
        return this.$$delegate_0.addListener(listener);
    }

    public final boolean getMBatteryChangeReceiverRegistered$hyper_widget_1_0_7_pluginRelease() {
        return this.mBatteryChangeReceiverRegistered;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final BatteryShaderIcon getMIconShader() {
        BatteryShaderIcon batteryShaderIcon = this.mIconShader;
        if (batteryShaderIcon != null) {
            return batteryShaderIcon;
        }
        n.w("mIconShader");
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final BatteryShaderText getMTextShader() {
        BatteryShaderText batteryShaderText = this.mTextShader;
        if (batteryShaderText != null) {
            return batteryShaderText;
        }
        n.w("mTextShader");
        return null;
    }

    @AnyThread
    public final CharSequence getText() {
        CharSequence text = this.mTextView.getText();
        n.f(text, "getText(...)");
        return text;
    }

    @AnyThread
    public final float getTextSize() {
        return this.mTextView.getTextSize();
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

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateBatteryChangeReceiver(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        updateBatteryChangeReceiver(false);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    @CallSuper
    public void onVisibilityChanged(View changedView, int i2) {
        n.g(changedView, "changedView");
        super.onVisibilityChanged(changedView, i2);
        boolean zNotifyVisibleChanged = notifyVisibleChanged(this, changedView, i2);
        Log.d(LOG_TAG, "onVisibilityChanged viewTreeVisible=" + zNotifyVisibleChanged + ", changedView=" + changedView + ", visibility=" + i2);
        if (zNotifyVisibleChanged) {
            updateBatteryChangeReceiver(true);
        } else {
            if (zNotifyVisibleChanged) {
                return;
            }
            updateBatteryChangeReceiver(false);
        }
    }

    @Override // com.mi.widget.core.IShaderViewListener
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public boolean removeListener(IShaderViewListener.a listener) {
        n.g(listener, "listener");
        return this.$$delegate_0.removeListener(listener);
    }

    public final void setMBatteryChangeReceiverRegistered$hyper_widget_1_0_7_pluginRelease(boolean z2) {
        this.mBatteryChangeReceiverRegistered = z2;
    }

    @UiThread
    public final void setText(CharSequence value) {
        n.g(value, "value");
        this.mTextView.setText(value);
    }

    @UiThread
    public final void setTextColor(@ColorInt int i2) {
        this.mTextView.setTextColor(i2);
    }

    @UiThread
    public final void setTextSize(float f2) {
        this.mTextView.setTextSize(0, f2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PowerSaveView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, null, 28, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PowerSaveView(Context context, AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, 0, null, 24, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PowerSaveView(Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        this(context, attributeSet, i2, i3, null, 16, null);
        n.g(context, "context");
    }

    public /* synthetic */ PowerSaveView(Context context, AttributeSet attributeSet, int i2, int i3, ShaderStrategy shaderStrategy, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3, (i4 & 16) != 0 ? AbstractC0354h.a() : shaderStrategy);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r6v6, types: [com.mi.widget.view.PowerSaveView$mBatteryChangeReceiver$1] */
    public PowerSaveView(Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3, ShaderStrategy strategy) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
        n.g(strategy, "strategy");
        this.$$delegate_0 = new ShaderViewStateListenerDelegate();
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), e0.c.f4025j);
        n.f(bitmapDecodeResource, "decodeResource(...)");
        this.mIcon = bitmapDecodeResource;
        setOrientation(0);
        TextView appCompatTextView = new AppCompatTextView(context);
        this.mTextView = appCompatTextView;
        appCompatTextView.setGravity(16);
        appCompatTextView.setTextSize(1, 14.0f);
        appCompatTextView.setTextColor(context.getColor(AbstractC0334a.f4006g));
        appCompatTextView.setTypeface(Typeface.create("mipro-demibold", 0));
        appCompatTextView.setSingleLine(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388629;
        layoutParams.setMarginEnd(getResources().getDimensionPixelSize(AbstractC0335b.f4015f));
        s sVar = s.f314a;
        addView(appCompatTextView, layoutParams);
        FrameLayout frameLayout = new FrameLayout(context);
        View view = new View(context);
        frameLayout.addView(view);
        this.mIconViewBlur = view;
        View view2 = new View(context);
        frameLayout.addView(view2);
        view2.setBackgroundColor(0);
        this.mIconView = view2;
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight());
        layoutParams2.setMarginEnd(getResources().getDimensionPixelSize(AbstractC0335b.f4014e));
        layoutParams2.gravity = 8388629;
        addView(frameLayout, layoutParams2);
        int i4 = WhenMappings.$EnumSwitchMapping$0[strategy.ordinal()];
        if (i4 == 1) {
            this.mTextShader = new BatteryShaderText(appCompatTextView);
            this.mIconShader = new BatteryShaderIcon(this, view2);
        } else if (i4 != 2) {
            b bVar = new b(this, view2);
            bVar.start();
            this.mIconDrawable = bVar;
            setGradientTextColor(appCompatTextView);
        } else {
            a aVar = new a(this, view);
            aVar.start();
            this.mIconBlur = aVar;
            b bVar2 = new b(this, view2);
            bVar2.start();
            this.mIconDrawable = bVar2;
            setGradientTextColor(appCompatTextView);
        }
        this.mBatteryChangeReceiver = new BroadcastReceiver() { // from class: com.mi.widget.view.PowerSaveView$mBatteryChangeReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                this.f2436a.mBatteryLevel = intent != null ? intent.getIntExtra(com.xiaomi.onetrack.b.a.f3024d, 0) : 0.0f;
                if (this.f2436a.mIconShader != null) {
                    this.f2436a.getMIconShader().invalidate();
                }
                PowerSaveView.a aVar2 = null;
                if (this.f2436a.mIconDrawable != null) {
                    PowerSaveView.b bVar3 = this.f2436a.mIconDrawable;
                    if (bVar3 == null) {
                        n.w("mIconDrawable");
                        bVar3 = null;
                    }
                    bVar3.invalidateSelf();
                }
                if (this.f2436a.mIconBlur != null) {
                    PowerSaveView.a aVar3 = this.f2436a.mIconBlur;
                    if (aVar3 == null) {
                        n.w("mIconBlur");
                    } else {
                        aVar2 = aVar3;
                    }
                    aVar2.invalidateSelf();
                }
            }
        };
    }
}
