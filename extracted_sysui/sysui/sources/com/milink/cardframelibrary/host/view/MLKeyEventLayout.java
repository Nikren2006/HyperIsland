package com.milink.cardframelibrary.host.view;

import H0.d;
import H0.e;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public final class MLKeyEventLayout extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f2456a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Function1 f2457b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Function0 f2458c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f2459d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f2460e;

    public static final class a extends o implements Function0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f2461a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f2461a = context;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Double invoke() {
            return Double.valueOf(((double) (this.f2461a.getResources().getDisplayMetrics().density * 30)) + 0.5d);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MLKeyEventLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
        this.f2456a = e.b(new a(context));
        this.f2459d = -1.0f;
    }

    private final double getFinishBarHeight() {
        return ((Number) this.f2456a.getValue()).doubleValue();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Function1 function1 = this.f2457b;
        if (function1 != null) {
            ((Boolean) function1.invoke(keyEvent)).booleanValue();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public final Function0 getMBottomUpListener() {
        return this.f2458c;
    }

    public final Function1 getMDispatchKeyEventListener() {
        return this.f2457b;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null || motionEvent.getAction() != 0 || motionEvent.getY() <= ((double) getHeight()) - getFinishBarHeight()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Function0 function0;
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            if (motionEvent.getY() > ((double) getHeight()) - getFinishBarHeight()) {
                this.f2459d = motionEvent.getY();
                this.f2460e = System.currentTimeMillis();
                return true;
            }
        } else if (numValueOf != null && numValueOf.intValue() == 2) {
            if (this.f2459d != -1.0f) {
                return true;
            }
        } else if (numValueOf != null && numValueOf.intValue() == 1) {
            float f2 = this.f2459d;
            if (f2 != -1.0f) {
                float y2 = f2 - motionEvent.getY();
                long jCurrentTimeMillis = System.currentTimeMillis() - this.f2460e;
                this.f2459d = -1.0f;
                this.f2460e = 0L;
                double d2 = y2;
                if ((d2 > ((double) 3) * getFinishBarHeight() || (d2 > ((double) 2) * getFinishBarHeight() && jCurrentTimeMillis < 500)) && (function0 = this.f2458c) != null) {
                    function0.invoke();
                }
                return true;
            }
        } else {
            this.f2459d = -1.0f;
            this.f2460e = 0L;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setMBottomUpListener(Function0 function0) {
        this.f2458c = function0;
    }

    public final void setMDispatchKeyEventListener(Function1 function1) {
        this.f2457b = function1;
    }
}
