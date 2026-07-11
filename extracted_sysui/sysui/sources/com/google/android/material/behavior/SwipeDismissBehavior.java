package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper;

/* JADX INFO: loaded from: classes2.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewDragHelper f1614a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1615b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1616c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1618e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f1617d = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1619f = 2;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f1620g = 0.5f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f1621h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f1622i = 0.5f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ViewDragHelper.Callback f1623j = new a();

    public class a extends ViewDragHelper.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1624a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1625b = -1;

        public a() {
        }

        public final boolean a(View view, float f2) {
            if (f2 == 0.0f) {
                return Math.abs(view.getLeft() - this.f1624a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.f1620g);
            }
            boolean z2 = ViewCompat.getLayoutDirection(view) == 1;
            int i2 = SwipeDismissBehavior.this.f1619f;
            if (i2 == 2) {
                return true;
            }
            if (i2 == 0) {
                if (z2) {
                    if (f2 >= 0.0f) {
                        return false;
                    }
                } else if (f2 <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i2 != 1) {
                return false;
            }
            if (z2) {
                if (f2 <= 0.0f) {
                    return false;
                }
            } else if (f2 >= 0.0f) {
                return false;
            }
            return true;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            int width;
            int width2;
            int width3;
            boolean z2 = ViewCompat.getLayoutDirection(view) == 1;
            int i4 = SwipeDismissBehavior.this.f1619f;
            if (i4 == 0) {
                if (z2) {
                    width = this.f1624a - view.getWidth();
                    width2 = this.f1624a;
                } else {
                    width = this.f1624a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i4 != 1) {
                width = this.f1624a - view.getWidth();
                width2 = this.f1624a + view.getWidth();
            } else if (z2) {
                width = this.f1624a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f1624a - view.getWidth();
                width2 = this.f1624a;
            }
            return SwipeDismissBehavior.d(width, i2, width2);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewCaptured(View view, int i2) {
            this.f1625b = i2;
            this.f1624a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                SwipeDismissBehavior.this.f1616c = true;
                parent.requestDisallowInterceptTouchEvent(true);
                SwipeDismissBehavior.this.f1616c = false;
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            SwipeDismissBehavior.this.getClass();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            float width = view.getWidth() * SwipeDismissBehavior.this.f1621h;
            float width2 = view.getWidth() * SwipeDismissBehavior.this.f1622i;
            float fAbs = Math.abs(i2 - this.f1624a);
            if (fAbs <= width) {
                view.setAlpha(1.0f);
            } else if (fAbs >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.c(0.0f, 1.0f - SwipeDismissBehavior.f(width, width2, fAbs), 1.0f));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001d  */
        @Override // androidx.customview.widget.ViewDragHelper.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onViewReleased(android.view.View r3, float r4, float r5) {
            /*
                r2 = this;
                r5 = -1
                r2.f1625b = r5
                int r5 = r3.getWidth()
                boolean r0 = r2.a(r3, r4)
                if (r0 == 0) goto L23
                r0 = 0
                int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                if (r4 < 0) goto L1d
                int r4 = r3.getLeft()
                int r0 = r2.f1624a
                if (r4 >= r0) goto L1b
                goto L1d
            L1b:
                int r0 = r0 + r5
                goto L21
            L1d:
                int r4 = r2.f1624a
                int r0 = r4 - r5
            L21:
                r4 = 1
                goto L26
            L23:
                int r0 = r2.f1624a
                r4 = 0
            L26:
                com.google.android.material.behavior.SwipeDismissBehavior r5 = com.google.android.material.behavior.SwipeDismissBehavior.this
                androidx.customview.widget.ViewDragHelper r5 = r5.f1614a
                int r1 = r3.getTop()
                boolean r5 = r5.settleCapturedViewAt(r0, r1)
                if (r5 == 0) goto L3f
                com.google.android.material.behavior.SwipeDismissBehavior$c r5 = new com.google.android.material.behavior.SwipeDismissBehavior$c
                com.google.android.material.behavior.SwipeDismissBehavior r2 = com.google.android.material.behavior.SwipeDismissBehavior.this
                r5.<init>(r3, r4)
                androidx.core.view.ViewCompat.postOnAnimation(r3, r5)
                goto L46
            L3f:
                if (r4 == 0) goto L46
                com.google.android.material.behavior.SwipeDismissBehavior r2 = com.google.android.material.behavior.SwipeDismissBehavior.this
                r2.getClass()
            L46:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.a.onViewReleased(android.view.View, float, float):void");
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            int i3 = this.f1625b;
            return (i3 == -1 || i3 == i2) && SwipeDismissBehavior.this.b(view);
        }
    }

    public class b implements AccessibilityViewCommand {
        public b() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
            if (!SwipeDismissBehavior.this.b(view)) {
                return false;
            }
            boolean z2 = ViewCompat.getLayoutDirection(view) == 1;
            int i2 = SwipeDismissBehavior.this.f1619f;
            ViewCompat.offsetLeftAndRight(view, (!(i2 == 0 && z2) && (i2 != 1 || z2)) ? view.getWidth() : -view.getWidth());
            view.setAlpha(0.0f);
            SwipeDismissBehavior.this.getClass();
            return true;
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1628a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f1629b;

        public c(View view, boolean z2) {
            this.f1628a = view;
            this.f1629b = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper viewDragHelper = SwipeDismissBehavior.this.f1614a;
            if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.f1628a, this);
            } else if (this.f1629b) {
                SwipeDismissBehavior.this.getClass();
            }
        }
    }

    public static float c(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    public static int d(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }

    public static float f(float f2, float f3, float f4) {
        return (f4 - f2) / (f3 - f2);
    }

    public boolean b(View view) {
        return true;
    }

    public final void e(ViewGroup viewGroup) {
        if (this.f1614a == null) {
            this.f1614a = this.f1618e ? ViewDragHelper.create(viewGroup, this.f1617d, this.f1623j) : ViewDragHelper.create(viewGroup, this.f1623j);
        }
    }

    public void g(float f2) {
        this.f1622i = c(0.0f, f2, 1.0f);
    }

    public void h(float f2) {
        this.f1621h = c(0.0f, f2, 1.0f);
    }

    public void i(int i2) {
        this.f1619f = i2;
    }

    public final void j(View view) {
        ViewCompat.removeAccessibilityAction(view, 1048576);
        if (b(view)) {
            ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, null, new b());
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean zIsPointInChildBounds = this.f1615b;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zIsPointInChildBounds = coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f1615b = zIsPointInChildBounds;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f1615b = false;
        }
        if (!zIsPointInChildBounds) {
            return false;
        }
        e(coordinatorLayout);
        return !this.f1616c && this.f1614a.shouldInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        boolean zOnLayoutChild = super.onLayoutChild(coordinatorLayout, view, i2);
        if (ViewCompat.getImportantForAccessibility(view) == 0) {
            ViewCompat.setImportantForAccessibility(view, 1);
            j(view);
        }
        return zOnLayoutChild;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (this.f1614a == null) {
            return false;
        }
        if (this.f1616c && motionEvent.getActionMasked() == 3) {
            return true;
        }
        this.f1614a.processTouchEvent(motionEvent);
        return true;
    }
}
