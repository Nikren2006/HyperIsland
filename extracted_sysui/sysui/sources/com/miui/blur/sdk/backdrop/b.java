package com.miui.blur.sdk.backdrop;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2470b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2471c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2472d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View f2473e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final BlurDrawInfo f2474f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2469a = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ViewTreeObserver.OnPreDrawListener f2475g = new a();

    public class a implements ViewTreeObserver.OnPreDrawListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            b.this.f2473e.getViewTreeObserver().removeOnPreDrawListener(this);
            b.this.c();
            return true;
        }
    }

    public b(View view, BlurDrawInfo blurDrawInfo) {
        this.f2473e = view;
        this.f2474f = blurDrawInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c() {
        /*
            r6 = this;
            android.view.View r0 = r6.f2473e
            android.view.ViewRootImpl r0 = r0.getViewRootImpl()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L2c
            java.lang.Class r3 = r0.getClass()
            java.lang.String r4 = "getSurfaceControl"
            java.lang.Class[] r5 = new java.lang.Class[r2]
            java.lang.reflect.Method r3 = com.miui.blur.sdk.backdrop.m.a(r3, r4, r5)
            if (r3 == 0) goto L2c
            r3.setAccessible(r1)
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.Object r3 = com.miui.blur.sdk.backdrop.m.b(r0, r3, r4)
            boolean r4 = r3 instanceof android.view.SurfaceControl
            if (r4 == 0) goto L2c
            android.view.SurfaceControl r3 = (android.view.SurfaceControl) r3
            boolean r3 = r3.isValid()
            goto L2d
        L2c:
            r3 = r2
        L2d:
            boolean r4 = r6.d()
            if (r4 == 0) goto L39
            if (r0 == 0) goto L39
            if (r3 == 0) goto L39
            r0 = r1
            goto L3a
        L39:
            r0 = r2
        L3a:
            boolean r3 = r6.d()
            if (r3 == 0) goto L4e
            boolean r3 = r6.f2471c
            if (r3 == 0) goto L4e
            boolean r3 = r6.f2472d
            if (r3 == 0) goto L4e
            boolean r3 = r6.f2469a
            if (r3 == 0) goto L4e
            r3 = r1
            goto L4f
        L4e:
            r3 = r2
        L4f:
            if (r3 == 0) goto L54
            if (r0 == 0) goto L54
            goto L55
        L54:
            r1 = r2
        L55:
            if (r3 == 0) goto L64
            if (r0 != 0) goto L64
            android.view.View r0 = r6.f2473e
            android.view.ViewTreeObserver r0 = r0.getViewTreeObserver()
            android.view.ViewTreeObserver$OnPreDrawListener r2 = r6.f2475g
            r0.addOnPreDrawListener(r2)
        L64:
            boolean r0 = r6.f2470b
            if (r0 == r1) goto L7b
            if (r1 == 0) goto L76
            android.view.View r0 = r6.f2473e
            android.content.Context r0 = r0.getContext()
            com.miui.blur.sdk.backdrop.BlurDrawInfo r2 = r6.f2474f
            com.miui.blur.sdk.backdrop.BlurManager.d(r0, r2)
            goto L7b
        L76:
            com.miui.blur.sdk.backdrop.BlurDrawInfo r0 = r6.f2474f
            com.miui.blur.sdk.backdrop.BlurManager.e(r0)
        L7b:
            r6.f2470b = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.blur.sdk.backdrop.b.c():void");
    }

    public boolean d() {
        return BlurManager.f2462a;
    }

    public boolean e() {
        return this.f2469a && d();
    }

    public void f() {
        this.f2472d = true;
        c();
    }

    public void g() {
        this.f2472d = false;
        c();
    }

    public void h(Canvas canvas) {
        if (e()) {
            BlurManager.b(canvas, this.f2474f);
        }
    }

    public void i(boolean z2) {
        this.f2471c = z2;
        c();
    }

    public void j(boolean z2) {
        this.f2469a = z2;
        c();
    }
}
