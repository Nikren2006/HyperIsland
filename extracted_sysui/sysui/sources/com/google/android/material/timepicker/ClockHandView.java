package com.google.android.material.timepicker;

import H.n;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import t.AbstractC0741a;
import t.i;
import t.j;
import u.AbstractC0743a;

/* JADX INFO: loaded from: classes2.dex */
class ClockHandView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2263a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TimeInterpolator f2264b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ValueAnimator f2265c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2266d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f2267e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f2268f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2269g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f2270h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2271i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final List f2272j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f2273k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f2274l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Paint f2275m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final RectF f2276n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f2277o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f2278p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2279q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public double f2280r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f2281s;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2282x;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            animator.end();
        }
    }

    public interface b {
        void a(float f2, boolean z2);
    }

    public ClockHandView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6521u);
    }

    public void b(b bVar) {
        this.f2272j.add(bVar);
    }

    public final void c(float f2, float f3) {
        this.f2282x = I.a.a((float) (getWidth() / 2), (float) (getHeight() / 2), f2, f3) > ((float) h(2)) + n.c(getContext(), 12) ? 1 : 2;
    }

    public final void d(Canvas canvas) {
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float f2 = width;
        float fH = h(this.f2282x);
        float fCos = (((float) Math.cos(this.f2280r)) * fH) + f2;
        float f3 = height;
        float fSin = (fH * ((float) Math.sin(this.f2280r))) + f3;
        this.f2275m.setStrokeWidth(0.0f);
        canvas.drawCircle(fCos, fSin, this.f2273k, this.f2275m);
        double dSin = Math.sin(this.f2280r);
        double dCos = Math.cos(this.f2280r);
        this.f2275m.setStrokeWidth(this.f2277o);
        canvas.drawLine(f2, f3, width + ((int) (dCos * d)), height + ((int) (d * dSin)), this.f2275m);
        canvas.drawCircle(f2, f3, this.f2274l, this.f2275m);
    }

    public RectF e() {
        return this.f2276n;
    }

    public final int f(float f2, float f3) {
        int degrees = (int) Math.toDegrees(Math.atan2(f3 - (getHeight() / 2), f2 - (getWidth() / 2)));
        int i2 = degrees + 90;
        return i2 < 0 ? degrees + 450 : i2;
    }

    public float g() {
        return this.f2278p;
    }

    public final int h(int i2) {
        int i3 = this.f2281s;
        return i2 == 2 ? Math.round(i3 * 0.66f) : i3;
    }

    public int i() {
        return this.f2273k;
    }

    public final Pair j(float f2) {
        float fG = g();
        if (Math.abs(fG - f2) > 180.0f) {
            if (fG > 180.0f && f2 < 180.0f) {
                f2 += 360.0f;
            }
            if (fG < 180.0f && f2 > 180.0f) {
                fG += 360.0f;
            }
        }
        return new Pair(Float.valueOf(fG), Float.valueOf(f2));
    }

    public final boolean k(float f2, float f3, boolean z2, boolean z3, boolean z4) {
        float f4 = f(f2, f3);
        boolean z5 = false;
        boolean z6 = g() != f4;
        if (z3 && z6) {
            return true;
        }
        if (!z6 && !z2) {
            return false;
        }
        if (z4 && this.f2266d) {
            z5 = true;
        }
        o(f4, z5);
        return true;
    }

    public final /* synthetic */ void l(ValueAnimator valueAnimator) {
        p(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
    }

    public void m(int i2) {
        this.f2281s = i2;
        invalidate();
    }

    public void n(float f2) {
        o(f2, false);
    }

    public void o(float f2, boolean z2) {
        ValueAnimator valueAnimator = this.f2265c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z2) {
            p(f2, false);
            return;
        }
        Pair pairJ = j(f2);
        this.f2265c.setFloatValues(((Float) pairJ.first).floatValue(), ((Float) pairJ.second).floatValue());
        this.f2265c.setDuration(this.f2263a);
        this.f2265c.setInterpolator(this.f2264b);
        this.f2265c.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.timepicker.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f2294a.l(valueAnimator2);
            }
        });
        this.f2265c.addListener(new a());
        this.f2265c.start();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        d(canvas);
    }

    @Override // android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (this.f2265c.isRunning()) {
            return;
        }
        n(g());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        boolean z3;
        boolean z4;
        int actionMasked = motionEvent.getActionMasked();
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        if (actionMasked == 0) {
            this.f2267e = x2;
            this.f2268f = y2;
            this.f2269g = true;
            this.f2279q = false;
            z2 = true;
            z3 = false;
            z4 = false;
        } else if (actionMasked == 1 || actionMasked == 2) {
            int i2 = (int) (x2 - this.f2267e);
            int i3 = (int) (y2 - this.f2268f);
            this.f2269g = (i2 * i2) + (i3 * i3) > this.f2270h;
            z3 = this.f2279q;
            boolean z5 = actionMasked == 1;
            if (this.f2271i) {
                c(x2, y2);
            }
            z4 = z5;
            z2 = false;
        } else {
            z3 = false;
            z2 = false;
            z4 = false;
        }
        this.f2279q |= k(x2, y2, z3, z2, z4);
        return true;
    }

    public final void p(float f2, boolean z2) {
        float f3 = f2 % 360.0f;
        this.f2278p = f3;
        this.f2280r = Math.toRadians(f3 - 90.0f);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float fH = h(this.f2282x);
        float fCos = width + (((float) Math.cos(this.f2280r)) * fH);
        float fSin = height + (fH * ((float) Math.sin(this.f2280r)));
        RectF rectF = this.f2276n;
        int i2 = this.f2273k;
        rectF.set(fCos - i2, fSin - i2, fCos + i2, fSin + i2);
        Iterator it = this.f2272j.iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(f3, z2);
        }
        invalidate();
    }

    public void q(boolean z2) {
        if (this.f2271i && !z2) {
            this.f2282x = 1;
        }
        this.f2271i = z2;
        invalidate();
    }

    public ClockHandView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2265c = new ValueAnimator();
        this.f2272j = new ArrayList();
        Paint paint = new Paint();
        this.f2275m = paint;
        this.f2276n = new RectF();
        this.f2282x = 1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f6741V0, i2, i.f6691n);
        this.f2263a = J.d.f(context, AbstractC0741a.f6523w, 200);
        this.f2264b = J.d.g(context, AbstractC0741a.f6489D, AbstractC0743a.f6835b);
        this.f2281s = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.f6745X0, 0);
        this.f2273k = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.f6747Y0, 0);
        this.f2277o = getResources().getDimensionPixelSize(t.c.f6575r);
        this.f2274l = r7.getDimensionPixelSize(t.c.f6573p);
        int color = typedArrayObtainStyledAttributes.getColor(j.f6743W0, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        n(0.0f);
        this.f2270h = ViewConfiguration.get(context).getScaledTouchSlop();
        ViewCompat.setImportantForAccessibility(this, 2);
        typedArrayObtainStyledAttributes.recycle();
    }
}
