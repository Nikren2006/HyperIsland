package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.TextView;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f1913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f1914b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f1915c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f1916d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1917e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final O.k f1918f;

    public b(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i2, O.k kVar, Rect rect) {
        Preconditions.checkArgumentNonnegative(rect.left);
        Preconditions.checkArgumentNonnegative(rect.top);
        Preconditions.checkArgumentNonnegative(rect.right);
        Preconditions.checkArgumentNonnegative(rect.bottom);
        this.f1913a = rect;
        this.f1914b = colorStateList2;
        this.f1915c = colorStateList;
        this.f1916d = colorStateList3;
        this.f1917e = i2;
        this.f1918f = kVar;
    }

    public static b a(Context context, int i2) {
        Preconditions.checkArgument(i2 != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, t.j.K2);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(t.j.L2, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(t.j.N2, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(t.j.M2, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(t.j.O2, 0));
        ColorStateList colorStateListA = L.c.a(context, typedArrayObtainStyledAttributes, t.j.P2);
        ColorStateList colorStateListA2 = L.c.a(context, typedArrayObtainStyledAttributes, t.j.U2);
        ColorStateList colorStateListA3 = L.c.a(context, typedArrayObtainStyledAttributes, t.j.S2);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(t.j.T2, 0);
        O.k kVarM = O.k.b(context, typedArrayObtainStyledAttributes.getResourceId(t.j.Q2, 0), typedArrayObtainStyledAttributes.getResourceId(t.j.R2, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new b(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, kVarM, rect);
    }

    public void b(TextView textView) {
        c(textView, null, null);
    }

    public void c(TextView textView, ColorStateList colorStateList, ColorStateList colorStateList2) {
        O.g gVar = new O.g();
        O.g gVar2 = new O.g();
        gVar.setShapeAppearanceModel(this.f1918f);
        gVar2.setShapeAppearanceModel(this.f1918f);
        if (colorStateList == null) {
            colorStateList = this.f1915c;
        }
        gVar.T(colorStateList);
        gVar.Y(this.f1917e, this.f1916d);
        if (colorStateList2 == null) {
            colorStateList2 = this.f1914b;
        }
        textView.setTextColor(colorStateList2);
        RippleDrawable rippleDrawable = new RippleDrawable(this.f1914b.withAlpha(30), gVar, gVar2);
        Rect rect = this.f1913a;
        ViewCompat.setBackground(textView, new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
