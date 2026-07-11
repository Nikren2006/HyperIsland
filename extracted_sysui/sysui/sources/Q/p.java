package Q;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import t.AbstractC0741a;
import u.AbstractC0743a;

/* JADX INFO: loaded from: classes2.dex */
public class p extends r {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final boolean f612s = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f613e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f614f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final TimeInterpolator f615g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public AutoCompleteTextView f616h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final View.OnClickListener f617i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final View.OnFocusChangeListener f618j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AccessibilityManagerCompat.TouchExplorationStateChangeListener f619k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f620l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f621m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f622n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public long f623o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public AccessibilityManager f624p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ValueAnimator f625q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ValueAnimator f626r;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            p.this.r();
            p.this.f626r.start();
        }
    }

    public p(com.google.android.material.textfield.a aVar) {
        super(aVar);
        this.f617i = new View.OnClickListener() { // from class: Q.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f608a.J(view);
            }
        };
        this.f618j = new View.OnFocusChangeListener() { // from class: Q.m
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z2) {
                this.f609a.K(view, z2);
            }
        };
        this.f619k = new AccessibilityManagerCompat.TouchExplorationStateChangeListener() { // from class: Q.n
            @Override // androidx.core.view.accessibility.AccessibilityManagerCompat.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z2) {
                this.f610a.L(z2);
            }
        };
        this.f623o = LocationRequestCompat.PASSIVE_INTERVAL;
        Context context = aVar.getContext();
        int i2 = AbstractC0741a.f6486A;
        this.f614f = J.d.f(context, i2, 67);
        this.f613e = J.d.f(aVar.getContext(), i2, 50);
        this.f615g = J.d.g(aVar.getContext(), AbstractC0741a.f6490E, AbstractC0743a.f6834a);
    }

    public static AutoCompleteTextView D(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    private void F() {
        this.f626r = E(this.f614f, 0.0f, 1.0f);
        ValueAnimator valueAnimatorE = E(this.f613e, 1.0f, 0.0f);
        this.f625q = valueAnimatorE;
        valueAnimatorE.addListener(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J(View view) {
        Q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K(View view, boolean z2) {
        this.f620l = z2;
        r();
        if (z2) {
            return;
        }
        O(false);
        this.f621m = false;
    }

    public final ValueAnimator E(int i2, float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(this.f615g);
        valueAnimatorOfFloat.setDuration(i2);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: Q.i
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f605a.I(valueAnimator);
            }
        });
        return valueAnimatorOfFloat;
    }

    public final boolean G() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f623o;
        return jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300;
    }

    public final /* synthetic */ void H() {
        boolean zIsPopupShowing = this.f616h.isPopupShowing();
        O(zIsPopupShowing);
        this.f621m = zIsPopupShowing;
    }

    public final /* synthetic */ void I(ValueAnimator valueAnimator) {
        this.f631d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public final /* synthetic */ void L(boolean z2) {
        AutoCompleteTextView autoCompleteTextView = this.f616h;
        if (autoCompleteTextView == null || q.a(autoCompleteTextView)) {
            return;
        }
        ViewCompat.setImportantForAccessibility(this.f631d, z2 ? 2 : 1);
    }

    public final /* synthetic */ boolean M(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (G()) {
                this.f621m = false;
            }
            Q();
            R();
        }
        return false;
    }

    public final /* synthetic */ void N() {
        R();
        O(false);
    }

    public final void O(boolean z2) {
        if (this.f622n != z2) {
            this.f622n = z2;
            this.f626r.cancel();
            this.f625q.start();
        }
    }

    public final void P() {
        this.f616h.setOnTouchListener(new View.OnTouchListener() { // from class: Q.j
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f606a.M(view, motionEvent);
            }
        });
        if (f612s) {
            this.f616h.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() { // from class: Q.k
                @Override // android.widget.AutoCompleteTextView.OnDismissListener
                public final void onDismiss() {
                    this.f607a.N();
                }
            });
        }
        this.f616h.setThreshold(0);
    }

    public final void Q() {
        if (this.f616h == null) {
            return;
        }
        if (G()) {
            this.f621m = false;
        }
        if (this.f621m) {
            this.f621m = false;
            return;
        }
        if (f612s) {
            O(!this.f622n);
        } else {
            this.f622n = !this.f622n;
            r();
        }
        if (!this.f622n) {
            this.f616h.dismissDropDown();
        } else {
            this.f616h.requestFocus();
            this.f616h.showDropDown();
        }
    }

    public final void R() {
        this.f621m = true;
        this.f623o = System.currentTimeMillis();
    }

    @Override // Q.r
    public void a(Editable editable) {
        if (this.f624p.isTouchExplorationEnabled() && q.a(this.f616h) && !this.f631d.hasFocus()) {
            this.f616h.dismissDropDown();
        }
        this.f616h.post(new Runnable() { // from class: Q.o
            @Override // java.lang.Runnable
            public final void run() {
                this.f611a.H();
            }
        });
    }

    @Override // Q.r
    public int c() {
        return t.h.f6661g;
    }

    @Override // Q.r
    public int d() {
        return f612s ? t.d.f6590g : t.d.f6591h;
    }

    @Override // Q.r
    public View.OnFocusChangeListener e() {
        return this.f618j;
    }

    @Override // Q.r
    public View.OnClickListener f() {
        return this.f617i;
    }

    @Override // Q.r
    public AccessibilityManagerCompat.TouchExplorationStateChangeListener h() {
        return this.f619k;
    }

    @Override // Q.r
    public boolean i(int i2) {
        return i2 != 0;
    }

    @Override // Q.r
    public boolean j() {
        return true;
    }

    @Override // Q.r
    public boolean k() {
        return this.f620l;
    }

    @Override // Q.r
    public boolean l() {
        return true;
    }

    @Override // Q.r
    public boolean m() {
        return this.f622n;
    }

    @Override // Q.r
    public void n(EditText editText) {
        this.f616h = D(editText);
        P();
        this.f628a.setErrorIconDrawable((Drawable) null);
        if (!q.a(editText) && this.f624p.isTouchExplorationEnabled()) {
            ViewCompat.setImportantForAccessibility(this.f631d, 2);
        }
        this.f628a.setEndIconVisible(true);
    }

    @Override // Q.r
    public void o(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!q.a(this.f616h)) {
            accessibilityNodeInfoCompat.setClassName(Spinner.class.getName());
        }
        if (accessibilityNodeInfoCompat.isShowingHintText()) {
            accessibilityNodeInfoCompat.setHintText(null);
        }
    }

    @Override // Q.r
    public void p(View view, AccessibilityEvent accessibilityEvent) {
        if (!this.f624p.isEnabled() || q.a(this.f616h)) {
            return;
        }
        boolean z2 = accessibilityEvent.getEventType() == 32768 && this.f622n && !this.f616h.isPopupShowing();
        if (accessibilityEvent.getEventType() == 1 || z2) {
            Q();
            R();
        }
    }

    @Override // Q.r
    public void s() {
        F();
        this.f624p = (AccessibilityManager) this.f630c.getSystemService("accessibility");
    }

    @Override // Q.r
    public boolean t() {
        return true;
    }

    @Override // Q.r
    public void u() {
        AutoCompleteTextView autoCompleteTextView = this.f616h;
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setOnTouchListener(null);
            if (f612s) {
                this.f616h.setOnDismissListener(null);
            }
        }
    }
}
