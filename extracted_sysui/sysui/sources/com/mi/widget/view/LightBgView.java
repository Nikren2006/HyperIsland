package com.mi.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SizeF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.AnyThread;
import androidx.annotation.AttrRes;
import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.ShaderStrategy;
import com.mi.widget.shader.LightBgShader;
import f0.AbstractC0354h;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
public final class LightBgView extends FrameLayout {
    private static final boolean DEBUG = true;
    private static final String LOG_TAG = "LightBgView";
    private boolean autoApertureSize;
    private SizeF bottomApertureSize;
    private final int childStartIndex;
    private final TextView mBgView;
    private final TextView mFgView;
    private boolean mRunning;
    private LightBgShader mShader;
    private float sizeOfGlowArea;
    private float sizeOfGlowRadius;
    private final ShaderStrategy strategy;
    private SizeF upperApertureSize;
    private static final a Companion = new a(null);
    public static final int $stable = 8;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LightBgView(Context context) {
        this(context, null, 0, 0, null, 30, null);
        n.g(context, "context");
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMBgView$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMFgView$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMShader$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mShader$lambda$3$lambda$2(LightBgView this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this$0, "this$0");
        if (this$0.autoApertureSize) {
            float f2 = i4 - i2;
            float f3 = i5 - i3;
            this$0.setUpperApertureSize(new SizeF(f2, f3));
            this$0.setBottomApertureSize(new SizeF(f2, f3));
        }
    }

    public static /* synthetic */ void start$default(LightBgView lightBgView, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        lightBgView.start(z2);
    }

    public static /* synthetic */ void stop$default(LightBgView lightBgView, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        lightBgView.stop(z2);
    }

    @Override // android.view.ViewGroup
    @CallSuper
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (i2 < 0 || i2 >= this.childStartIndex) {
            super.addView(view, i2, layoutParams);
            return;
        }
        throw new IllegalArgumentException("The index must be greater than or equal to " + this.childStartIndex);
    }

    @AnyThread
    public final boolean getAutoApertureSize() {
        return this.autoApertureSize;
    }

    @AnyThread
    public final SizeF getBottomApertureSize() {
        return this.bottomApertureSize;
    }

    @Override // android.view.ViewGroup
    @CallSuper
    public int getChildDrawingOrder(int i2, int i3) {
        int i4 = i3 + 1;
        return (1 > i4 || i4 >= 2) ? i4 == 2 ? i2 - 1 : i3 - 1 : i3;
    }

    @AnyThread
    public final int getChildStartIndex() {
        return this.childStartIndex;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final TextView getMBgView$hyper_widget_1_0_7_pluginRelease() {
        return this.mBgView;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final TextView getMFgView$hyper_widget_1_0_7_pluginRelease() {
        return this.mFgView;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final LightBgShader getMShader$hyper_widget_1_0_7_pluginRelease() {
        return this.mShader;
    }

    @AnyThread
    public final float getSizeOfGlowArea() {
        return this.sizeOfGlowArea;
    }

    @AnyThread
    public final float getSizeOfGlowRadius() {
        return this.sizeOfGlowRadius;
    }

    @AnyThread
    public final SizeF getUpperApertureSize() {
        return this.upperApertureSize;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.mShader.invalidate$hyper_widget_1_0_7_pluginRelease();
    }

    @Override // android.view.ViewGroup, android.view.View
    @CallSuper
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRunning) {
            Log.i(LOG_TAG, "onAttachedToWindow restart view shader");
            this.mShader.startByAnim();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @CallSuper
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mRunning) {
            Log.i(LOG_TAG, "onDetachedFromWindow stop view shader");
            this.mShader.stopNoAnim$hyper_widget_1_0_7_pluginRelease();
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    @CallSuper
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (this.mFgView.getVisibility() == 0) {
            this.mFgView.layout(0, 0, i4 - i2, i5 - i3);
        }
        if (this.mBgView.getVisibility() == 0) {
            this.mBgView.layout(0, 0, i4 - i2, i5 - i3);
        }
    }

    @Override // android.view.View
    @CallSuper
    public void onVisibilityChanged(View changedView, int i2) {
        n.g(changedView, "changedView");
        super.onVisibilityChanged(changedView, i2);
        if (this.mRunning) {
            Log.d(LOG_TAG, "onVisibilityChanged current viewTree visible=" + isShown());
            boolean zIsShown = isShown();
            LightBgShader lightBgShader = this.mShader;
            if (zIsShown) {
                lightBgShader.startByTrans$hyper_widget_1_0_7_pluginRelease();
            } else {
                lightBgShader.stopByTrans$hyper_widget_1_0_7_pluginRelease();
            }
        }
    }

    @Override // android.view.ViewGroup
    @CallSuper
    public void removeAllViews() {
        if (getChildCount() > 2) {
            removeViews(2, getChildCount() - 2);
        }
    }

    @Override // android.view.ViewGroup
    @CallSuper
    public void removeViewAt(int i2) {
        if (i2 >= this.childStartIndex) {
            super.removeViewAt(i2);
            return;
        }
        throw new IllegalArgumentException("removeViewAt don't remove default bg/fg view, start index must be greater than or equal to " + this.childStartIndex + "\"");
    }

    @Override // android.view.ViewGroup
    @CallSuper
    public void removeViews(int i2, int i3) {
        if (i2 >= this.childStartIndex) {
            super.removeViews(i2, i3);
            return;
        }
        throw new IllegalArgumentException("removeViews don't remove default bg/fg view, start index must be greater than or equal to " + this.childStartIndex + "\"");
    }

    @Override // android.view.ViewGroup
    @CallSuper
    public void removeViewsInLayout(int i2, int i3) {
        if (i2 >= this.childStartIndex) {
            super.removeViewsInLayout(i2, i3);
            return;
        }
        throw new IllegalArgumentException("removeViewsInLayout don't remove default bg/fg view, start index must be greater than or equal to " + this.childStartIndex + "\"");
    }

    @AnyThread
    public final void setAutoApertureSize(boolean z2) {
        this.autoApertureSize = z2;
    }

    @AnyThread
    public final void setBottomApertureSize(SizeF value) {
        n.g(value, "value");
        this.bottomApertureSize = value;
        this.mShader.setBottomApertureSize(value);
    }

    public final void setEffectView(View view, View view2) {
        if (this.mRunning) {
            throw new IllegalStateException("Can't change effect view when effect is running.");
        }
        this.mBgView.setVisibility(view2 != null ? 4 : 0);
        this.mFgView.setVisibility(view != null ? 4 : 0);
        if (view2 == null) {
            view2 = this.mBgView;
        }
        if (view == null) {
            view = this.mFgView;
        }
        LightBgShader lightBgShader = new LightBgShader(view2, view, this.strategy);
        lightBgShader.setUpperApertureSize(this.upperApertureSize);
        lightBgShader.setBottomApertureSize(this.bottomApertureSize);
        lightBgShader.setGlowDistance(this.sizeOfGlowArea);
        lightBgShader.setGlowRDCornerRadius(this.sizeOfGlowRadius);
        this.mShader = lightBgShader;
    }

    public final void setMShader$hyper_widget_1_0_7_pluginRelease(LightBgShader lightBgShader) {
        n.g(lightBgShader, "<set-?>");
        this.mShader = lightBgShader;
    }

    @UiThread
    public final void setSizeOfGlowArea(float f2) {
        this.sizeOfGlowArea = f2;
        this.mShader.setGlowDistance(f2);
    }

    @AnyThread
    public final void setSizeOfGlowRadius(float f2) {
        this.sizeOfGlowRadius = f2;
        this.mShader.setGlowRDCornerRadius(f2);
    }

    @AnyThread
    public final void setUpperApertureSize(SizeF value) {
        n.g(value, "value");
        this.upperApertureSize = value;
        this.mShader.setUpperApertureSize(value);
    }

    public final synchronized void start(boolean z2) {
        try {
            if (this.mRunning) {
                return;
            }
            Log.d(LOG_TAG, "start view shader viewTreeVisible=" + isShown() + ", viewAttached=" + isAttachedToWindow());
            this.mRunning = true;
            if (z2 || isShown()) {
                this.mShader.startByAnim();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void stop(boolean z2) {
        try {
            if (this.mRunning) {
                Log.d(LOG_TAG, "stop view shader viewTreeVisible=" + isShown() + ", viewAttached=" + isAttachedToWindow());
                if (!isAttachedToWindow() || z2) {
                    this.mShader.stopNoAnim$hyper_widget_1_0_7_pluginRelease();
                } else {
                    this.mShader.stopByAnim();
                }
                this.mRunning = false;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LightBgView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, null, 28, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LightBgView(Context context, AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, 0, null, 24, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LightBgView(Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        this(context, attributeSet, i2, i3, null, 16, null);
        n.g(context, "context");
    }

    public /* synthetic */ LightBgView(Context context, AttributeSet attributeSet, int i2, int i3, ShaderStrategy shaderStrategy, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3, (i4 & 16) != 0 ? AbstractC0354h.a() : shaderStrategy);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LightBgView(Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3, ShaderStrategy strategy) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
        n.g(strategy, "strategy");
        this.strategy = strategy;
        TextView textView = new TextView(context);
        textView.setBackgroundColor(0);
        super.addView(textView, -2, -2);
        this.mBgView = textView;
        TextView textView2 = new TextView(context);
        textView2.setBackgroundColor(0);
        super.addView(textView2, -2, -2);
        this.mFgView = textView2;
        LightBgShader lightBgShader = new LightBgShader(textView, textView2, strategy);
        addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.mi.widget.view.b
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
                LightBgView.mShader$lambda$3$lambda$2(this.f2451a, view, i4, i5, i6, i7, i8, i9, i10, i11);
            }
        });
        this.mShader = lightBgShader;
        setChildrenDrawingOrderEnabled(true);
        setClipChildren(false);
        this.autoApertureSize = true;
        this.upperApertureSize = new SizeF(0.0f, 0.0f);
        this.bottomApertureSize = new SizeF(0.0f, 0.0f);
        this.childStartIndex = 2;
    }
}
