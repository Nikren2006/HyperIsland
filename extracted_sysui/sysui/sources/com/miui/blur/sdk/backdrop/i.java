package com.miui.blur.sdk.backdrop;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.GraphicBuffer;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Trace;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.Type;
import android.util.Log;
import android.view.MiuiCompositionSamplingListener;
import android.view.SurfaceControl;
import android.view.ViewRootImpl;
import com.miui.blur.sdk.backdrop.BlurManager;
import com.miui.blur.sdk.backdrop.k;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final l f2480s = new l(10);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final int f2481t = k.f2514f.c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewRootImpl f2482a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f2483b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Paint f2484c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Paint f2485d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RenderScript f2486e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ScriptIntrinsicBlur f2487f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Handler f2488g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Outline f2489h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f2490i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f2491j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Rect f2492k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Point f2493l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public a f2494m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f2495n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f2496o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2497p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Set f2498q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public BlurManager.CompositionSamplingListenerWrapper f2499r;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2500a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f2501b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bitmap f2502c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Allocation f2503d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Allocation f2504e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final ScriptIntrinsicBlur f2505f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final BitmapShader f2506g;

        public a(int i2, int i3, RenderScript renderScript, ScriptIntrinsicBlur scriptIntrinsicBlur) {
            this.f2500a = i2;
            this.f2501b = i3;
            this.f2505f = scriptIntrinsicBlur;
            Type typeCreate = new Type.Builder(renderScript, Element.RGBA_8888(renderScript)).setX(i2).setY(i3).create();
            this.f2503d = Allocation.createTyped(renderScript, typeCreate, 35);
            this.f2504e = Allocation.createTyped(renderScript, typeCreate, 1);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            this.f2502c = bitmapCreateBitmap;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.f2506g = new BitmapShader(bitmapCreateBitmap, tileMode, tileMode);
        }

        public void d(GraphicBuffer graphicBuffer) {
            this.f2503d.getSurface().attachAndQueueBuffer(graphicBuffer);
            this.f2503d.ioReceive();
        }

        public void e() {
            Trace.beginSection("processBlur");
            this.f2505f.setInput(this.f2503d);
            this.f2505f.forEach(this.f2504e);
            this.f2504e.copyTo(this.f2502c);
            Trace.endSection();
        }

        public void f() {
            this.f2503d.destroy();
            this.f2504e.destroy();
            this.f2502c.recycle();
        }
    }

    public i(Context context, ViewRootImpl viewRootImpl, RenderScript renderScript, Handler handler) {
        Paint paint = new Paint();
        this.f2484c = paint;
        Paint paint2 = new Paint();
        this.f2485d = paint2;
        this.f2489h = new Outline();
        this.f2490i = new Object();
        this.f2491j = new int[2];
        this.f2492k = new Rect();
        this.f2493l = new Point();
        this.f2496o = f2481t;
        this.f2498q = new HashSet();
        this.f2483b = context;
        this.f2482a = viewRootImpl;
        this.f2488g = handler;
        this.f2486e = renderScript;
        paint.setAntiAlias(true);
        paint2.setAntiAlias(true);
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        this.f2487f = scriptIntrinsicBlurCreate;
        scriptIntrinsicBlurCreate.setRadius(this.f2496o);
    }

    public static /* synthetic */ int o(BlurDrawInfo blurDrawInfo) {
        return blurDrawInfo.getBlurStyle().c();
    }

    public final void d() {
        HashSet hashSet;
        synchronized (this.f2490i) {
            hashSet = new HashSet(this.f2498q);
        }
        hashSet.forEach(new Consumer() { // from class: com.miui.blur.sdk.backdrop.h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BlurDrawInfo) obj).postInvalidateOnAnimation();
            }
        });
    }

    public void e() {
        this.f2497p = true;
        Log.d("BlurLayerHolder", "unregister " + this.f2482a);
        BlurManager.CompositionSamplingListenerWrapper compositionSamplingListenerWrapper = this.f2499r;
        if (compositionSamplingListenerWrapper != null) {
            MiuiCompositionSamplingListener.unregister(compositionSamplingListenerWrapper);
            BlurManager.CompositionSamplingListenerWrapper.b(this.f2499r);
            this.f2499r = null;
        }
        this.f2488g.post(new Runnable() { // from class: com.miui.blur.sdk.backdrop.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f2478a.n();
            }
        });
    }

    public void f(Canvas canvas, BlurDrawInfo blurDrawInfo) {
        a aVar = this.f2494m;
        if (aVar == null || !BlurManager.f2462a) {
            return;
        }
        v(blurDrawInfo, aVar);
        blurDrawInfo.getBlurOutline(this.f2489h);
        Outline outline = this.f2489h;
        int i2 = outline.mMode;
        if (i2 == 2) {
            h(canvas, outline.mPath, blurDrawInfo);
        } else if (i2 != 1) {
            g(canvas, blurDrawInfo);
        } else {
            outline.getRect(this.f2492k);
            i(canvas, this.f2492k, this.f2489h.getRadius(), blurDrawInfo);
        }
    }

    public final void g(Canvas canvas, BlurDrawInfo blurDrawInfo) {
        canvas.drawRect(0.0f, 0.0f, blurDrawInfo.getWidth(), blurDrawInfo.getHeight(), this.f2484c);
        for (k.a aVar : blurDrawInfo.getBlurStyle().d()) {
            s(aVar.f2521a, aVar.f2522b);
            canvas.drawRect(0.0f, 0.0f, blurDrawInfo.getWidth(), blurDrawInfo.getHeight(), this.f2485d);
        }
    }

    public final void h(Canvas canvas, Path path, BlurDrawInfo blurDrawInfo) {
        canvas.drawPath(path, this.f2484c);
        for (k.a aVar : blurDrawInfo.getBlurStyle().d()) {
            s(aVar.f2521a, aVar.f2522b);
            canvas.drawPath(path, this.f2485d);
        }
    }

    public final void i(Canvas canvas, Rect rect, float f2, BlurDrawInfo blurDrawInfo) {
        canvas.drawRoundRect(rect.left, rect.top, rect.right, rect.bottom, f2, f2, this.f2484c);
        for (k.a aVar : blurDrawInfo.getBlurStyle().d()) {
            s(aVar.f2521a, aVar.f2522b);
            canvas.drawRoundRect(rect.left, rect.top, rect.right, rect.bottom, f2, f2, this.f2485d);
        }
    }

    public final void j(GraphicBuffer graphicBuffer) {
        if (this.f2494m == null) {
            a aVar = new a(graphicBuffer.getWidth(), graphicBuffer.getHeight(), this.f2486e, this.f2487f);
            this.f2494m = aVar;
            this.f2484c.setShader(aVar.f2506g);
            Context context = this.f2483b;
            if (context instanceof Application) {
                this.f2495n = this.f2494m.f2500a / context.getResources().getDisplayMetrics().widthPixels;
            } else {
                context.getDisplay().getRealSize(this.f2493l);
                this.f2495n = this.f2494m.f2500a / this.f2493l.x;
            }
        }
    }

    public final void k() {
        float fMin = Math.min(24, this.f2498q.stream().mapToInt(new ToIntFunction() { // from class: com.miui.blur.sdk.backdrop.f
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return i.o((BlurDrawInfo) obj);
            }
        }).min().orElse(f2481t));
        if (fMin != this.f2496o) {
            this.f2496o = fMin;
            this.f2487f.setRadius(fMin);
        }
    }

    public final void l(GraphicBuffer graphicBuffer) {
        a aVar = this.f2494m;
        if (aVar != null) {
            if (aVar.f2500a == graphicBuffer.getWidth() && this.f2494m.f2501b == graphicBuffer.getHeight()) {
                return;
            }
            final a aVar2 = this.f2494m;
            Handler handler = this.f2488g;
            aVar2.getClass();
            handler.post(new Runnable() { // from class: com.miui.blur.sdk.backdrop.g
                @Override // java.lang.Runnable
                public final void run() {
                    aVar2.f();
                }
            });
            this.f2494m = null;
        }
    }

    public boolean m() {
        return this.f2498q.isEmpty();
    }

    public final /* synthetic */ void n() {
        a aVar = this.f2494m;
        if (aVar != null) {
            aVar.f();
            this.f2494m = null;
        }
        this.f2487f.destroy();
    }

    public final /* synthetic */ void p(GraphicBuffer graphicBuffer) {
        if (this.f2497p) {
            return;
        }
        q(graphicBuffer);
    }

    public final void q(GraphicBuffer graphicBuffer) {
        l(graphicBuffer);
        j(graphicBuffer);
        Trace.beginSection("attachAndProcessBuffer");
        this.f2494m.d(graphicBuffer);
        this.f2494m.e();
        d();
        Trace.endSection();
    }

    public void r(BlurDrawInfo blurDrawInfo) {
        synchronized (this.f2490i) {
            this.f2498q.add(blurDrawInfo);
            k();
        }
    }

    public final void s(int i2, BlendMode blendMode) {
        this.f2485d.setColor(i2);
        this.f2485d.setBlendMode(blendMode);
    }

    public void t() {
        BlurManager.CompositionSamplingListenerWrapper compositionSamplingListenerWrapperA = BlurManager.CompositionSamplingListenerWrapper.a();
        this.f2499r = compositionSamplingListenerWrapperA;
        compositionSamplingListenerWrapperA.d(new Consumer() { // from class: com.miui.blur.sdk.backdrop.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f2477a.p((GraphicBuffer) obj);
            }
        });
        int iOrElse = this.f2498q.stream().mapToInt(new ToIntFunction() { // from class: com.miui.blur.sdk.backdrop.d
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((BlurDrawInfo) obj).getRequestedSamplingPeriodNs();
            }
        }).min().orElse(BlurDrawInfo.DEFAULT_SAMPLING_PERIOD_NS);
        Log.d("BlurLayerHolder", "register " + this.f2482a);
        Method methodA = m.a(this.f2482a.getClass(), "getSurfaceControl", new Class[0]);
        methodA.setAccessible(true);
        Object objB = m.b(this.f2482a, methodA, new Object[0]);
        if (objB instanceof SurfaceControl) {
            MiuiCompositionSamplingListener.register(this.f2499r, 0, (SurfaceControl) objB, 16.0f, iOrElse);
        }
    }

    public void u(BlurDrawInfo blurDrawInfo) {
        synchronized (this.f2490i) {
            this.f2498q.remove(blurDrawInfo);
            k();
        }
    }

    public final void v(BlurDrawInfo blurDrawInfo, a aVar) {
        boolean z2 = this.f2483b.getResources().getConfiguration().orientation == 2;
        blurDrawInfo.getLocationOnScreen(this.f2491j);
        int[] iArr = this.f2491j;
        int i2 = iArr[0];
        int i3 = iArr[1];
        float f2 = 1.0f / this.f2495n;
        l lVar = f2480s;
        Matrix matrix = (Matrix) lVar.a();
        if (matrix == null) {
            matrix = new Matrix();
        }
        matrix.reset();
        if (z2) {
            matrix.setRotate(180.0f, aVar.f2500a / 2.0f, aVar.f2501b / 2.0f);
        }
        matrix.postScale(f2, f2, 0.0f, 0.0f);
        matrix.postTranslate(-i2, -i3);
        lVar.c(matrix);
        aVar.f2506g.setLocalMatrix(matrix);
    }
}
