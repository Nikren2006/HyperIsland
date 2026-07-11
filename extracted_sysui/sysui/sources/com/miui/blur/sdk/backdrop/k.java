package com.miui.blur.sdk.backdrop;

import android.graphics.BlendMode;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k f2511c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f2512d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final k f2513e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k f2514f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final k f2515g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final k f2516h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final k f2517i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final k f2518j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a[] f2519a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2520b;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2521a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final BlendMode f2522b;

        public a(int i2, BlendMode blendMode) {
            this.f2521a = i2;
            this.f2522b = blendMode;
        }
    }

    public static class b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List f2524b = new ArrayList();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2523a = 10;

        public b a(int i2, BlendMode blendMode) {
            this.f2524b.add(new a(i2, blendMode));
            return this;
        }

        public k b() {
            return k.f2512d ? new k(this.f2523a, (a[]) this.f2524b.toArray(new a[0])) : k.f2511c;
        }

        public b c(int i2) {
            this.f2523a = i2;
            return this;
        }
    }

    static {
        k kVar = new k(0);
        f2511c = kVar;
        boolean z2 = BlurManager.f2462a;
        f2512d = z2;
        f2513e = z2 ? new b().c(8).a(-2074585000, BlendMode.COLOR_DODGE).a(1088676835, null).b() : kVar;
        f2514f = z2 ? new b().c(10).a(-1889509280, BlendMode.COLOR_DODGE).a(-1544359182, null).b() : kVar;
        f2515g = z2 ? new b().c(12).a(1970500467, BlendMode.COLOR_DODGE).a(-856295947, null).b() : kVar;
        f2516h = z2 ? new b().c(8).a(1636469386, BlendMode.COLOR_BURN).a(1296187970, null).b() : kVar;
        f2517i = z2 ? new b().c(10).a(1970500467, BlendMode.COLOR_BURN).a(-1977211354, null).b() : kVar;
        if (z2) {
            kVar = new b().c(12).a(2136759388, BlendMode.COLOR_BURN).a(-1088479457, null).b();
        }
        f2518j = kVar;
    }

    public k(int i2) {
        this.f2520b = i2;
        this.f2519a = null;
    }

    public final int c() {
        return this.f2520b;
    }

    public final a[] d() {
        return this.f2519a;
    }

    public k(int i2, a... aVarArr) {
        this.f2520b = i2;
        this.f2519a = aVarArr;
    }
}
