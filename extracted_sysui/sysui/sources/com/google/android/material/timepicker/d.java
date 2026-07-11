package com.google.android.material.timepicker;

import O.g;
import O.i;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d extends ConstraintLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f2297a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2298b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f2299c;

    public d(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        LayoutInflater.from(context).inflate(t.g.f6642f, this);
        ViewCompat.setBackground(this, c());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.X3, i2, 0);
        this.f2298b = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.Y3, 0);
        this.f2297a = new Runnable() { // from class: com.google.android.material.timepicker.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f2296a.h();
            }
        };
        typedArrayObtainStyledAttributes.recycle();
    }

    public static boolean g(View view) {
        return "skip".equals(view.getTag());
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (view.getId() == -1) {
            view.setId(ViewCompat.generateViewId());
        }
        i();
    }

    public final void b(List list, ConstraintSet constraintSet, int i2) {
        Iterator it = list.iterator();
        float size = 0.0f;
        while (it.hasNext()) {
            constraintSet.constrainCircle(((View) it.next()).getId(), t.e.f6612c, i2, size);
            size += 360.0f / list.size();
        }
    }

    public final Drawable c() {
        g gVar = new g();
        this.f2299c = gVar;
        gVar.R(new i(0.5f));
        this.f2299c.T(ColorStateList.valueOf(-1));
        return this.f2299c;
    }

    public int d(int i2) {
        int i3 = this.f2298b;
        return i2 == 2 ? Math.round(i3 * 0.66f) : i3;
    }

    public int e() {
        return this.f2298b;
    }

    public void f(int i2) {
        this.f2298b = i2;
        h();
    }

    public void h() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getId() != t.e.f6612c && !g(childAt)) {
                int i3 = (Integer) childAt.getTag(t.e.f6620k);
                if (i3 == null) {
                    i3 = 1;
                }
                if (!map.containsKey(i3)) {
                    map.put(i3, new ArrayList());
                }
                ((List) map.get(i3)).add(childAt);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            b((List) entry.getValue(), constraintSet, d(((Integer) entry.getKey()).intValue()));
        }
        constraintSet.applyTo(this);
    }

    public final void i() {
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.f2297a);
            handler.post(this.f2297a);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        h();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        i();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.f2299c.T(ColorStateList.valueOf(i2));
    }
}
