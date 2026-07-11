package com.google.android.material.timepicker;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Arrays;
import t.AbstractC0741a;
import t.g;
import t.i;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
class ClockFaceView extends d implements ClockHandView.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ClockHandView f2246d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f2247e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f2248f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f2249g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final SparseArray f2250h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AccessibilityDelegateCompat f2251i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f2252j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float[] f2253k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2254l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2255m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f2256n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f2257o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String[] f2258p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f2259q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ColorStateList f2260r;

    public class a implements ViewTreeObserver.OnPreDrawListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (!ClockFaceView.this.isShown()) {
                return true;
            }
            ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
            ClockFaceView.this.f(((ClockFaceView.this.getHeight() / 2) - ClockFaceView.this.f2246d.i()) - ClockFaceView.this.f2254l);
            return true;
        }
    }

    public class b extends AccessibilityDelegateCompat {
        public b() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            int iIntValue = ((Integer) view.getTag(t.e.f6625p)).intValue();
            if (iIntValue > 0) {
                accessibilityNodeInfoCompat.setTraversalAfter((View) ClockFaceView.this.f2250h.get(iIntValue - 1));
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, iIntValue, 1, false, view.isSelected()));
            accessibilityNodeInfoCompat.setClickable(true);
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            if (i2 != 16) {
                return super.performAccessibilityAction(view, i2, bundle);
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            view.getHitRect(ClockFaceView.this.f2247e);
            float fCenterX = ClockFaceView.this.f2247e.centerX();
            float fCenterY = ClockFaceView.this.f2247e.centerY();
            ClockFaceView.this.f2246d.onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, fCenterX, fCenterY, 0));
            ClockFaceView.this.f2246d.onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 1, fCenterX, fCenterY, 0));
            return true;
        }
    }

    public ClockFaceView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6521u);
    }

    public static float q(float f2, float f3, float f4) {
        return Math.max(Math.max(f2, f3), f4);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.b
    public void a(float f2, boolean z2) {
        if (Math.abs(this.f2259q - f2) > 0.001f) {
            this.f2259q = f2;
            n();
        }
    }

    @Override // com.google.android.material.timepicker.d
    public void f(int i2) {
        if (i2 != e()) {
            super.f(i2);
            this.f2246d.m(e());
        }
    }

    @Override // com.google.android.material.timepicker.d
    public void h() {
        super.h();
        for (int i2 = 0; i2 < this.f2250h.size(); i2++) {
            ((TextView) this.f2250h.get(i2)).setVisibility(0);
        }
    }

    public final void n() {
        RectF rectFE = this.f2246d.e();
        TextView textViewP = p(rectFE);
        for (int i2 = 0; i2 < this.f2250h.size(); i2++) {
            TextView textView = (TextView) this.f2250h.get(i2);
            if (textView != null) {
                textView.setSelected(textView == textViewP);
                textView.getPaint().setShader(o(rectFE, textView));
                textView.invalidate();
            }
        }
    }

    public final RadialGradient o(RectF rectF, TextView textView) {
        textView.getHitRect(this.f2247e);
        this.f2248f.set(this.f2247e);
        textView.getLineBounds(0, this.f2249g);
        RectF rectF2 = this.f2248f;
        Rect rect = this.f2249g;
        rectF2.inset(rect.left, rect.top);
        if (RectF.intersects(rectF, this.f2248f)) {
            return new RadialGradient(rectF.centerX() - this.f2248f.left, rectF.centerY() - this.f2248f.top, rectF.width() * 0.5f, this.f2252j, this.f2253k, Shader.TileMode.CLAMP);
        }
        return null;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.f2258p.length, false, 1));
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        n();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iQ = (int) (this.f2257o / q(this.f2255m / displayMetrics.heightPixels, this.f2256n / displayMetrics.widthPixels, 1.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iQ, BasicMeasure.EXACTLY);
        setMeasuredDimension(iQ, iQ);
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec);
    }

    public final TextView p(RectF rectF) {
        float f2 = Float.MAX_VALUE;
        TextView textView = null;
        for (int i2 = 0; i2 < this.f2250h.size(); i2++) {
            TextView textView2 = (TextView) this.f2250h.get(i2);
            if (textView2 != null) {
                textView2.getHitRect(this.f2247e);
                this.f2248f.set(this.f2247e);
                this.f2248f.union(rectF);
                float fWidth = this.f2248f.width() * this.f2248f.height();
                if (fWidth < f2) {
                    textView = textView2;
                    f2 = fWidth;
                }
            }
        }
        return textView;
    }

    public void r(String[] strArr, int i2) {
        this.f2258p = strArr;
        s(i2);
    }

    public final void s(int i2) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int size = this.f2250h.size();
        boolean z2 = false;
        for (int i3 = 0; i3 < Math.max(this.f2258p.length, size); i3++) {
            TextView textView = (TextView) this.f2250h.get(i3);
            if (i3 >= this.f2258p.length) {
                removeView(textView);
                this.f2250h.remove(i3);
            } else {
                if (textView == null) {
                    textView = (TextView) layoutInflaterFrom.inflate(g.f6640d, (ViewGroup) this, false);
                    this.f2250h.put(i3, textView);
                    addView(textView);
                }
                textView.setText(this.f2258p[i3]);
                textView.setTag(t.e.f6625p, Integer.valueOf(i3));
                int i4 = (i3 / 12) + 1;
                textView.setTag(t.e.f6620k, Integer.valueOf(i4));
                if (i4 > 1) {
                    z2 = true;
                }
                ViewCompat.setAccessibilityDelegate(textView, this.f2251i);
                textView.setTextColor(this.f2260r);
                if (i2 != 0) {
                    textView.setContentDescription(getResources().getString(i2, this.f2258p[i3]));
                }
            }
        }
        this.f2246d.q(z2);
    }

    public ClockFaceView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2247e = new Rect();
        this.f2248f = new RectF();
        this.f2249g = new Rect();
        this.f2250h = new SparseArray();
        this.f2253k = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f6735S0, i2, i.f6691n);
        Resources resources = getResources();
        ColorStateList colorStateListA = L.c.a(context, typedArrayObtainStyledAttributes, j.f6739U0);
        this.f2260r = colorStateListA;
        LayoutInflater.from(context).inflate(g.f6641e, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(t.e.f6619j);
        this.f2246d = clockHandView;
        this.f2254l = resources.getDimensionPixelSize(t.c.f6574q);
        int colorForState = colorStateListA.getColorForState(new int[]{R.attr.state_selected}, colorStateListA.getDefaultColor());
        this.f2252j = new int[]{colorForState, colorForState, colorStateListA.getDefaultColor()};
        clockHandView.b(this);
        int defaultColor = AppCompatResources.getColorStateList(context, t.b.f6528b).getDefaultColor();
        ColorStateList colorStateListA2 = L.c.a(context, typedArrayObtainStyledAttributes, j.f6737T0);
        setBackgroundColor(colorStateListA2 != null ? colorStateListA2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new a());
        setFocusable(true);
        typedArrayObtainStyledAttributes.recycle();
        this.f2251i = new b();
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        r(strArr, 0);
        this.f2255m = resources.getDimensionPixelSize(t.c.f6536D);
        this.f2256n = resources.getDimensionPixelSize(t.c.f6537E);
        this.f2257o = resources.getDimensionPixelSize(t.c.f6576s);
    }
}
