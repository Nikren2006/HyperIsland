package v;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: v.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0749a extends AbstractC0751c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f6900d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OverScroller f6901e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6902f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6903g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f6904h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6905i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public VelocityTracker f6906j;

    /* JADX INFO: renamed from: v.a$a, reason: collision with other inner class name */
    public class RunnableC0168a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final CoordinatorLayout f6907a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f6908b;

        public RunnableC0168a(CoordinatorLayout coordinatorLayout, View view) {
            this.f6907a = coordinatorLayout;
            this.f6908b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.f6908b == null || (overScroller = AbstractC0749a.this.f6901e) == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                AbstractC0749a.this.i(this.f6907a, this.f6908b);
                return;
            }
            AbstractC0749a abstractC0749a = AbstractC0749a.this;
            abstractC0749a.k(this.f6907a, this.f6908b, abstractC0749a.f6901e.getCurrY());
            ViewCompat.postOnAnimation(this.f6908b, this);
        }
    }

    public AbstractC0749a() {
        this.f6903g = -1;
        this.f6905i = -1;
    }

    public abstract boolean c(View view);

    public final void d() {
        if (this.f6906j == null) {
            this.f6906j = VelocityTracker.obtain();
        }
    }

    public final boolean e(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, float f2) {
        Runnable runnable = this.f6900d;
        if (runnable != null) {
            view.removeCallbacks(runnable);
            this.f6900d = null;
        }
        if (this.f6901e == null) {
            this.f6901e = new OverScroller(view.getContext());
        }
        this.f6901e.fling(0, a(), 0, Math.round(f2), 0, 0, i2, i3);
        if (!this.f6901e.computeScrollOffset()) {
            i(coordinatorLayout, view);
            return false;
        }
        RunnableC0168a runnableC0168a = new RunnableC0168a(coordinatorLayout, view);
        this.f6900d = runnableC0168a;
        ViewCompat.postOnAnimation(view, runnableC0168a);
        return true;
    }

    public abstract int f(View view);

    public abstract int g(View view);

    public abstract int h();

    public abstract void i(CoordinatorLayout coordinatorLayout, View view);

    public final int j(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4) {
        return l(coordinatorLayout, view, h() - i2, i3, i4);
    }

    public int k(CoordinatorLayout coordinatorLayout, View view, int i2) {
        return l(coordinatorLayout, view, i2, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public abstract int l(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        int iFindPointerIndex;
        if (this.f6905i < 0) {
            this.f6905i = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f6902f) {
            int i2 = this.f6903g;
            if (i2 == -1 || (iFindPointerIndex = motionEvent.findPointerIndex(i2)) == -1) {
                return false;
            }
            int y2 = (int) motionEvent.getY(iFindPointerIndex);
            if (Math.abs(y2 - this.f6904h) > this.f6905i) {
                this.f6904h = y2;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f6903g = -1;
            int x2 = (int) motionEvent.getX();
            int y3 = (int) motionEvent.getY();
            boolean z2 = c(view) && coordinatorLayout.isPointInChildBounds(view, x2, y3);
            this.f6902f = z2;
            if (z2) {
                this.f6904h = y3;
                this.f6903g = motionEvent.getPointerId(0);
                d();
                OverScroller overScroller = this.f6901e;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.f6901e.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.f6906j;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r12, android.view.View r13, android.view.MotionEvent r14) {
        /*
            r11 = this;
            int r0 = r14.getActionMasked()
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 == r2) goto L4e
            r4 = 2
            if (r0 == r4) goto L2d
            r12 = 3
            if (r0 == r12) goto L72
            r12 = 6
            if (r0 == r12) goto L13
            goto L4c
        L13:
            int r12 = r14.getActionIndex()
            if (r12 != 0) goto L1b
            r12 = r2
            goto L1c
        L1b:
            r12 = r3
        L1c:
            int r13 = r14.getPointerId(r12)
            r11.f6903g = r13
            float r12 = r14.getY(r12)
            r13 = 1056964608(0x3f000000, float:0.5)
            float r12 = r12 + r13
            int r12 = (int) r12
            r11.f6904h = r12
            goto L4c
        L2d:
            int r0 = r11.f6903g
            int r0 = r14.findPointerIndex(r0)
            if (r0 != r1) goto L36
            return r3
        L36:
            float r0 = r14.getY(r0)
            int r0 = (int) r0
            int r1 = r11.f6904h
            int r7 = r1 - r0
            r11.f6904h = r0
            int r8 = r11.f(r13)
            r9 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r4.j(r5, r6, r7, r8, r9)
        L4c:
            r12 = r3
            goto L81
        L4e:
            android.view.VelocityTracker r0 = r11.f6906j
            if (r0 == 0) goto L72
            r0.addMovement(r14)
            android.view.VelocityTracker r0 = r11.f6906j
            r4 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r4)
            android.view.VelocityTracker r0 = r11.f6906j
            int r4 = r11.f6903g
            float r10 = r0.getYVelocity(r4)
            int r0 = r11.g(r13)
            int r8 = -r0
            r9 = 0
            r5 = r11
            r6 = r12
            r7 = r13
            r5.e(r6, r7, r8, r9, r10)
            r12 = r2
            goto L73
        L72:
            r12 = r3
        L73:
            r11.f6902f = r3
            r11.f6903g = r1
            android.view.VelocityTracker r13 = r11.f6906j
            if (r13 == 0) goto L81
            r13.recycle()
            r13 = 0
            r11.f6906j = r13
        L81:
            android.view.VelocityTracker r13 = r11.f6906j
            if (r13 == 0) goto L88
            r13.addMovement(r14)
        L88:
            boolean r11 = r11.f6902f
            if (r11 != 0) goto L90
            if (r12 == 0) goto L8f
            goto L90
        L8f:
            r2 = r3
        L90:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: v.AbstractC0749a.onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public AbstractC0749a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6903g = -1;
        this.f6905i = -1;
    }
}
