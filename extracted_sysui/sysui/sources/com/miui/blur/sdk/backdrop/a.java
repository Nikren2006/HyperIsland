package com.miui.blur.sdk.backdrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewRootImpl;
import android.widget.FrameLayout;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a extends FrameLayout implements ViewBlurDrawInfo {
    private Method createBackgroundBlurDrawable;
    private final Paint mBlendPaint;
    private Object mBlurBackground;
    private final j mBlurLayerHolder;
    private int mBlurRadius;
    private final b mBlurViewBinder;
    private float mCornerRadiusBL;
    private float mCornerRadiusBR;
    private float mCornerRadiusTL;
    private float mCornerRadiusTR;
    private Method setAlpha;
    private Method setBlurRadius;
    private Method setCornerRadius;

    public a(Context context) {
        this(context, null);
    }

    public void damageInParent() {
        super.damageInParent();
        invalidate();
    }

    public void dispatchAlphaChanged(float f2) {
        if (getBackground() != null) {
            for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                f2 *= ((ViewGroup) parent).getAlpha();
            }
            getBackground().setAlpha((int) (f2 * 255.0f));
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.mBlurViewBinder.h(canvas);
        if (!isGoogleBlurSupported() || canvas.isHardwareAccelerated()) {
            super.draw(canvas);
        }
        if (isGoogleBlurSupported()) {
            this.mBlurLayerHolder.a(canvas, this, this.mCornerRadiusTL);
        }
    }

    public float getParentAlpha() {
        float alpha = getAlpha();
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            alpha *= ((ViewGroup) parent).getAlpha();
        }
        return alpha;
    }

    public boolean isBackdropBlurSupported() {
        return this.mBlurViewBinder.d();
    }

    public boolean isBlurEnabledAndSupported() {
        return this.mBlurViewBinder.e();
    }

    public boolean isGoogleBlurSupported() {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mBlurViewBinder.f();
        if (isGoogleBlurSupported()) {
            if (getBackground() == null || (getBackground() instanceof BackgroundBlurDrawable)) {
                Object objB = m.b(getBlurViewRootImpl(), this.createBackgroundBlurDrawable, new Object[0]);
                this.mBlurBackground = objB;
                if (objB instanceof BackgroundBlurDrawable) {
                    m.b(objB, this.setBlurRadius, Integer.valueOf(this.mBlurRadius));
                    m.b(this.mBlurBackground, this.setCornerRadius, Float.valueOf(this.mCornerRadiusTL), Float.valueOf(this.mCornerRadiusTR), Float.valueOf(this.mCornerRadiusBL), Float.valueOf(this.mCornerRadiusBR));
                    if (getParentAlpha() == 0.0f) {
                        m.b(this.mBlurBackground, this.setAlpha, 0);
                    }
                    setBackground((Drawable) this.mBlurBackground);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mBlurViewBinder.g();
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean z2) {
        super.onVisibilityAggregated(z2);
        this.mBlurViewBinder.i(z2);
        postInvalidateOnAnimation();
    }

    public void setBlurEnabled(boolean z2) {
        this.mBlurViewBinder.j(z2);
        if (isGoogleBlurSupported()) {
            this.mBlurLayerHolder.e(z2);
            if (z2) {
                if (this.mBlurBackground instanceof BackgroundBlurDrawable) {
                    setBlurRadius(this.mBlurRadius);
                    setBackground((Drawable) this.mBlurBackground);
                }
            } else if (getBackground() instanceof BackgroundBlurDrawable) {
                setBlurRadius(0);
                setBackground(null);
            }
        }
        postInvalidateOnAnimation();
    }

    public void setBlurRadius(int i2) {
        if (this.mBlurRadius != i2) {
            this.mBlurRadius = i2;
            if (isGoogleBlurSupported()) {
                Object obj = this.mBlurBackground;
                if (obj instanceof BackgroundBlurDrawable) {
                    m.b(obj, this.setBlurRadius, Integer.valueOf(i2));
                }
            }
        }
    }

    public void setCornerRadius(float f2) {
        setCornerRadius(f2, f2, f2, f2);
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setCornerRadius(float f2, float f3, float f4, float f5) {
        if (this.mCornerRadiusTL == f2 && this.mCornerRadiusTR == f3 && this.mCornerRadiusBL == f4 && this.mCornerRadiusBR == f5) {
            return;
        }
        this.mCornerRadiusTL = f2;
        this.mCornerRadiusTR = f3;
        this.mCornerRadiusBL = f4;
        this.mCornerRadiusBR = f5;
        if (isGoogleBlurSupported()) {
            Object obj = this.mBlurBackground;
            if (obj instanceof BackgroundBlurDrawable) {
                m.b(obj, this.setCornerRadius, Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5));
            }
        }
    }

    public a(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Paint paint = new Paint();
        this.mBlendPaint = paint;
        this.mBlurViewBinder = new b(this, this);
        setWillNotDraw(false);
        paint.setAntiAlias(true);
        this.mBlurLayerHolder = new j();
        if (isGoogleBlurSupported()) {
            Class cls = Integer.TYPE;
            this.setBlurRadius = m.a(BackgroundBlurDrawable.class, "setBlurRadius", cls);
            Class cls2 = Float.TYPE;
            this.setCornerRadius = m.a(BackgroundBlurDrawable.class, "setCornerRadius", cls2, cls2, cls2, cls2);
            this.setAlpha = m.a(BackgroundBlurDrawable.class, "setAlpha", cls);
            this.createBackgroundBlurDrawable = m.a(ViewRootImpl.class, "createBackgroundBlurDrawable", new Class[0]);
        }
    }
}
