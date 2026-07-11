package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import t.AbstractC0741a;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1919a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f1920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f1921c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f1922d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f1923e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b f1924f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b f1925g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f1926h;

    public c(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(L.b.d(context, AbstractC0741a.f6520t, i.class.getCanonicalName()), t.j.A2);
        this.f1919a = b.a(context, typedArrayObtainStyledAttributes.getResourceId(t.j.E2, 0));
        this.f1925g = b.a(context, typedArrayObtainStyledAttributes.getResourceId(t.j.C2, 0));
        this.f1920b = b.a(context, typedArrayObtainStyledAttributes.getResourceId(t.j.D2, 0));
        this.f1921c = b.a(context, typedArrayObtainStyledAttributes.getResourceId(t.j.F2, 0));
        ColorStateList colorStateListA = L.c.a(context, typedArrayObtainStyledAttributes, t.j.G2);
        this.f1922d = b.a(context, typedArrayObtainStyledAttributes.getResourceId(t.j.I2, 0));
        this.f1923e = b.a(context, typedArrayObtainStyledAttributes.getResourceId(t.j.H2, 0));
        this.f1924f = b.a(context, typedArrayObtainStyledAttributes.getResourceId(t.j.J2, 0));
        Paint paint = new Paint();
        this.f1926h = paint;
        paint.setColor(colorStateListA.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
