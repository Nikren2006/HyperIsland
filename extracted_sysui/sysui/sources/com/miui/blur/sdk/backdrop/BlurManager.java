package com.miui.blur.sdk.backdrop;

import android.graphics.Canvas;
import android.graphics.GraphicBuffer;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.HandlerThread;
import android.renderscript.RenderScript;
import android.util.Pools;
import android.view.MiuiCompositionSamplingListener;
import android.view.ViewRootImpl;
import java.util.HashMap;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BlurManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f2462a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HandlerThread f2463b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Handler f2464c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static RenderScript f2465d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final HashMap f2466e;

    public static class CompositionSamplingListenerWrapper extends MiuiCompositionSamplingListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Pools.SynchronizedPool f2467b = new Pools.SynchronizedPool(2);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Consumer f2468a;

        private CompositionSamplingListenerWrapper() {
            super(new HandlerExecutor(BlurManager.f2464c));
        }

        public static CompositionSamplingListenerWrapper a() {
            CompositionSamplingListenerWrapper compositionSamplingListenerWrapper = (CompositionSamplingListenerWrapper) f2467b.acquire();
            return compositionSamplingListenerWrapper == null ? new CompositionSamplingListenerWrapper() : compositionSamplingListenerWrapper;
        }

        public static void b(CompositionSamplingListenerWrapper compositionSamplingListenerWrapper) {
            compositionSamplingListenerWrapper.c();
            if (f2467b.release(compositionSamplingListenerWrapper)) {
                return;
            }
            compositionSamplingListenerWrapper.destroy();
        }

        public final void c() {
            this.f2468a = null;
        }

        public void d(Consumer consumer) {
            this.f2468a = consumer;
        }

        public void onSampleCollected(GraphicBuffer graphicBuffer) {
            Consumer consumer = this.f2468a;
            if (consumer != null) {
                consumer.accept(graphicBuffer);
            }
        }
    }

    static {
        HandlerThread handlerThread = new HandlerThread("rs_blur");
        f2463b = handlerThread;
        handlerThread.start();
        f2464c = new Handler(handlerThread.getLooper());
        f2466e = new HashMap();
    }

    public static void b(Canvas canvas, BlurDrawInfo blurDrawInfo) {
        i iVar = (i) f2466e.get(blurDrawInfo.getBlurViewRootImpl());
        if (iVar != null) {
            iVar.f(canvas, blurDrawInfo);
        }
    }

    public static boolean c() {
        return f2462a;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void d(android.content.Context r5, com.miui.blur.sdk.backdrop.BlurDrawInfo r6) {
        /*
            android.renderscript.RenderScript r0 = com.miui.blur.sdk.backdrop.BlurManager.f2465d
            if (r0 != 0) goto La
            android.renderscript.RenderScript r0 = android.renderscript.RenderScript.create(r5)
            com.miui.blur.sdk.backdrop.BlurManager.f2465d = r0
        La:
            android.view.ViewRootImpl r0 = r6.getBlurViewRootImpl()
            if (r0 == 0) goto L26
            java.util.HashMap r1 = com.miui.blur.sdk.backdrop.BlurManager.f2466e
            java.lang.Object r2 = r1.get(r0)
            if (r2 != 0) goto L26
            com.miui.blur.sdk.backdrop.i r2 = new com.miui.blur.sdk.backdrop.i
            android.renderscript.RenderScript r3 = com.miui.blur.sdk.backdrop.BlurManager.f2465d
            android.os.Handler r4 = com.miui.blur.sdk.backdrop.BlurManager.f2464c
            r2.<init>(r5, r0, r3, r4)
            r1.put(r0, r2)
            r5 = 1
            goto L27
        L26:
            r5 = 0
        L27:
            java.util.HashMap r1 = com.miui.blur.sdk.backdrop.BlurManager.f2466e
            java.lang.Object r0 = r1.get(r0)
            com.miui.blur.sdk.backdrop.i r0 = (com.miui.blur.sdk.backdrop.i) r0
            if (r0 == 0) goto L34
            r0.r(r6)
        L34:
            if (r5 == 0) goto L39
            r0.t()
        L39:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.blur.sdk.backdrop.BlurManager.d(android.content.Context, com.miui.blur.sdk.backdrop.BlurDrawInfo):void");
    }

    public static void e(BlurDrawInfo blurDrawInfo) {
        ViewRootImpl blurViewRootImpl = blurDrawInfo.getBlurViewRootImpl();
        HashMap map = f2466e;
        i iVar = (i) map.get(blurViewRootImpl);
        if (iVar != null) {
            iVar.u(blurDrawInfo);
            if (iVar.m()) {
                map.remove(blurViewRootImpl);
                iVar.e();
            }
        }
    }
}
