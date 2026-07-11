package com.google.android.material.carousel;

import android.content.Context;
import com.google.android.material.carousel.b;
import z.C0775a;
import z.f;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static float a(float f2, float f3, int i2) {
        return f2 + (Math.max(0, i2 - 1) * f3);
    }

    public static float b(float f2, float f3, int i2) {
        return i2 > 0 ? f2 + (f3 / 2.0f) : f2;
    }

    public static b c(Context context, float f2, float f3, C0775a c0775a) {
        float f4;
        float f5;
        float fMin = Math.min(f(context) + f2, c0775a.f7115f);
        float f6 = fMin / 2.0f;
        float f7 = 0.0f - f6;
        float fB = b(0.0f, c0775a.f7111b, c0775a.f7112c);
        float fJ = j(0.0f, a(fB, c0775a.f7111b, (int) Math.floor(c0775a.f7112c / 2.0f)), c0775a.f7111b, c0775a.f7112c);
        float fB2 = b(fJ, c0775a.f7114e, c0775a.f7113d);
        float fJ2 = j(fJ, a(fB2, c0775a.f7114e, (int) Math.floor(c0775a.f7113d / 2.0f)), c0775a.f7114e, c0775a.f7113d);
        float fB3 = b(fJ2, c0775a.f7115f, c0775a.f7116g);
        float fJ3 = j(fJ2, a(fB3, c0775a.f7115f, c0775a.f7116g), c0775a.f7115f, c0775a.f7116g);
        float fB4 = b(fJ3, c0775a.f7114e, c0775a.f7113d);
        float fB5 = b(j(fJ3, a(fB4, c0775a.f7114e, (int) Math.ceil(c0775a.f7113d / 2.0f)), c0775a.f7114e, c0775a.f7113d), c0775a.f7111b, c0775a.f7112c);
        float f8 = f6 + f3;
        float fB6 = f.b(fMin, c0775a.f7115f, f2);
        float fB7 = f.b(c0775a.f7111b, c0775a.f7115f, f2);
        float fB8 = f.b(c0775a.f7114e, c0775a.f7115f, f2);
        b.C0054b c0054bA = new b.C0054b(c0775a.f7115f, f3).a(f7, fB6, fMin);
        if (c0775a.f7112c > 0) {
            f4 = f8;
            c0054bA.f(fB, fB7, c0775a.f7111b, (int) Math.floor(r7 / 2.0f));
        } else {
            f4 = f8;
        }
        if (c0775a.f7113d > 0) {
            c0054bA.f(fB2, fB8, c0775a.f7114e, (int) Math.floor(r4 / 2.0f));
        }
        c0054bA.g(fB3, 0.0f, c0775a.f7115f, c0775a.f7116g, true);
        if (c0775a.f7113d > 0) {
            f5 = 2.0f;
            c0054bA.f(fB4, fB8, c0775a.f7114e, (int) Math.ceil(r4 / 2.0f));
        } else {
            f5 = 2.0f;
        }
        if (c0775a.f7112c > 0) {
            c0054bA.f(fB5, fB7, c0775a.f7111b, (int) Math.ceil(r0 / f5));
        }
        c0054bA.a(f4, fB6, fMin);
        return c0054bA.h();
    }

    public static b d(Context context, float f2, float f3, C0775a c0775a, int i2) {
        return i2 == 1 ? c(context, f2, f3, c0775a) : e(context, f2, f3, c0775a);
    }

    public static b e(Context context, float f2, float f3, C0775a c0775a) {
        float fMin = Math.min(f(context) + f2, c0775a.f7115f);
        float f4 = fMin / 2.0f;
        float f5 = 0.0f - f4;
        float fB = b(0.0f, c0775a.f7115f, c0775a.f7116g);
        float fJ = j(0.0f, a(fB, c0775a.f7115f, c0775a.f7116g), c0775a.f7115f, c0775a.f7116g);
        float fB2 = b(fJ, c0775a.f7114e, c0775a.f7113d);
        float fB3 = b(j(fJ, fB2, c0775a.f7114e, c0775a.f7113d), c0775a.f7111b, c0775a.f7112c);
        float f6 = f4 + f3;
        float fB4 = f.b(fMin, c0775a.f7115f, f2);
        float fB5 = f.b(c0775a.f7111b, c0775a.f7115f, f2);
        float fB6 = f.b(c0775a.f7114e, c0775a.f7115f, f2);
        b.C0054b c0054bG = new b.C0054b(c0775a.f7115f, f3).a(f5, fB4, fMin).g(fB, 0.0f, c0775a.f7115f, c0775a.f7116g, true);
        if (c0775a.f7113d > 0) {
            c0054bG.b(fB2, fB6, c0775a.f7114e);
        }
        int i2 = c0775a.f7112c;
        if (i2 > 0) {
            c0054bG.f(fB3, fB5, c0775a.f7111b, i2);
        }
        c0054bG.a(f6, fB4, fMin);
        return c0054bG.h();
    }

    public static float f(Context context) {
        return context.getResources().getDimension(t.c.f6569l);
    }

    public static float g(Context context) {
        return context.getResources().getDimension(t.c.f6570m);
    }

    public static float h(Context context) {
        return context.getResources().getDimension(t.c.f6571n);
    }

    public static int i(int[] iArr) {
        int i2 = Integer.MIN_VALUE;
        for (int i3 : iArr) {
            if (i3 > i2) {
                i2 = i3;
            }
        }
        return i2;
    }

    public static float j(float f2, float f3, float f4, int i2) {
        return i2 > 0 ? f3 + (f4 / 2.0f) : f2;
    }
}
