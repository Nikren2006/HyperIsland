package com.google.android.material.snackbar;

import H.n;
import O.g;
import O.k;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.behavior.SwipeDismissBehavior;
import t.AbstractC0741a;
import t.j;
import u.AbstractC0743a;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseTransientBottomBar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeInterpolator f2097a = AbstractC0743a.f6835b;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final TimeInterpolator f2098b = AbstractC0743a.f6834a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final TimeInterpolator f2099c = AbstractC0743a.f6837d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f2101e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f2102f = {AbstractC0741a.f6494I};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f2103g = BaseTransientBottomBar.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Handler f2100d = new Handler(Looper.getMainLooper(), new a());

    public static class Behavior extends SwipeDismissBehavior<View> {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final b f2104k = new b(this);

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean b(View view) {
            return this.f2104k.a(view);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            this.f2104k.b(coordinatorLayout, view, motionEvent);
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }
    }

    public class a implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                android.support.v4.media.a.a(message.obj);
                throw null;
            }
            if (i2 != 1) {
                return false;
            }
            android.support.v4.media.a.a(message.obj);
            throw null;
        }
    }

    public static class b {
        public b(SwipeDismissBehavior swipeDismissBehavior) {
            swipeDismissBehavior.h(0.1f);
            swipeDismissBehavior.g(0.6f);
            swipeDismissBehavior.i(0);
        }

        public boolean a(View view) {
            return view instanceof c;
        }

        public void b(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    com.google.android.material.snackbar.a.b().e(null);
                }
            } else if (actionMasked == 1 || actionMasked == 3) {
                com.google.android.material.snackbar.a.b().f(null);
            }
        }
    }

    public static class c extends FrameLayout {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final View.OnTouchListener f2105k = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public k f2106a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2107b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f2108c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f2109d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f2110e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int f2111f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f2112g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f2113h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Rect f2114i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f2115j;

        public class a implements View.OnTouchListener {
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        }

        public c(Context context, AttributeSet attributeSet) {
            super(S.a.c(context, attributeSet, 0, 0), attributeSet);
            Context context2 = getContext();
            TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, j.C4);
            if (typedArrayObtainStyledAttributes.hasValue(j.J4)) {
                ViewCompat.setElevation(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r2, 0));
            }
            this.f2107b = typedArrayObtainStyledAttributes.getInt(j.F4, 0);
            if (typedArrayObtainStyledAttributes.hasValue(j.L4) || typedArrayObtainStyledAttributes.hasValue(j.M4)) {
                this.f2106a = k.e(context2, attributeSet, 0, 0).m();
            }
            this.f2108c = typedArrayObtainStyledAttributes.getFloat(j.G4, 1.0f);
            setBackgroundTintList(L.c.a(context2, typedArrayObtainStyledAttributes, j.H4));
            setBackgroundTintMode(n.i(typedArrayObtainStyledAttributes.getInt(j.I4, -1), PorterDuff.Mode.SRC_IN));
            this.f2109d = typedArrayObtainStyledAttributes.getFloat(j.E4, 1.0f);
            this.f2110e = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.D4, -1);
            this.f2111f = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.K4, -1);
            typedArrayObtainStyledAttributes.recycle();
            setOnTouchListener(f2105k);
            setFocusable(true);
            if (getBackground() == null) {
                ViewCompat.setBackground(this, a());
            }
        }

        private void setBaseTransientBottomBar(BaseTransientBottomBar baseTransientBottomBar) {
        }

        public final Drawable a() {
            int iK = C.a.k(this, AbstractC0741a.f6511k, AbstractC0741a.f6508h, getBackgroundOverlayColorAlpha());
            k kVar = this.f2106a;
            Drawable drawableD = kVar != null ? BaseTransientBottomBar.d(iK, kVar) : BaseTransientBottomBar.c(iK, getResources());
            if (this.f2112g == null) {
                return DrawableCompat.wrap(drawableD);
            }
            Drawable drawableWrap = DrawableCompat.wrap(drawableD);
            DrawableCompat.setTintList(drawableWrap, this.f2112g);
            return drawableWrap;
        }

        public final void b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.f2114i = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }

        public float getActionTextColorAlpha() {
            return this.f2109d;
        }

        public int getAnimationMode() {
            return this.f2107b;
        }

        public float getBackgroundOverlayColorAlpha() {
            return this.f2108c;
        }

        public int getMaxInlineActionWidth() {
            return this.f2111f;
        }

        public int getMaxWidth() {
            return this.f2110e;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            ViewCompat.requestApplyInsets(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
            super.onLayout(z2, i2, i3, i4, i5);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            if (this.f2110e > 0) {
                int measuredWidth = getMeasuredWidth();
                int i4 = this.f2110e;
                if (measuredWidth > i4) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i4, BasicMeasure.EXACTLY), i3);
                }
            }
        }

        public void setAnimationMode(int i2) {
            this.f2107b = i2;
        }

        @Override // android.view.View
        public void setBackground(@Nullable Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(@Nullable Drawable drawable) {
            if (drawable != null && this.f2112g != null) {
                drawable = DrawableCompat.wrap(drawable.mutate());
                DrawableCompat.setTintList(drawable, this.f2112g);
                DrawableCompat.setTintMode(drawable, this.f2113h);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
            this.f2112g = colorStateList;
            if (getBackground() != null) {
                Drawable drawableWrap = DrawableCompat.wrap(getBackground().mutate());
                DrawableCompat.setTintList(drawableWrap, colorStateList);
                DrawableCompat.setTintMode(drawableWrap, this.f2113h);
                if (drawableWrap != getBackground()) {
                    super.setBackgroundDrawable(drawableWrap);
                }
            }
        }

        @Override // android.view.View
        public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
            this.f2113h = mode;
            if (getBackground() != null) {
                Drawable drawableWrap = DrawableCompat.wrap(getBackground().mutate());
                DrawableCompat.setTintMode(drawableWrap, mode);
                if (drawableWrap != getBackground()) {
                    super.setBackgroundDrawable(drawableWrap);
                }
            }
        }

        @Override // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            super.setLayoutParams(layoutParams);
            if (this.f2115j || !(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                return;
            }
            b((ViewGroup.MarginLayoutParams) layoutParams);
        }

        @Override // android.view.View
        public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : f2105k);
            super.setOnClickListener(onClickListener);
        }
    }

    public static GradientDrawable c(int i2, Resources resources) {
        float dimension = resources.getDimension(t.c.f6553U);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(i2);
        return gradientDrawable;
    }

    public static g d(int i2, k kVar) {
        g gVar = new g(kVar);
        gVar.T(ColorStateList.valueOf(i2));
        return gVar;
    }
}
