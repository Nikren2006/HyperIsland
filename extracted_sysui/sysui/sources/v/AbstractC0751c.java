package v;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: renamed from: v.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0751c extends CoordinatorLayout.Behavior {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0752d f6914a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6915b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f6916c;

    public AbstractC0751c() {
        this.f6915b = 0;
        this.f6916c = 0;
    }

    public int a() {
        C0752d c0752d = this.f6914a;
        if (c0752d != null) {
            return c0752d.b();
        }
        return 0;
    }

    public void b(CoordinatorLayout coordinatorLayout, View view, int i2) {
        coordinatorLayout.onLayoutChild(view, i2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        b(coordinatorLayout, view, i2);
        if (this.f6914a == null) {
            this.f6914a = new C0752d(view);
        }
        this.f6914a.c();
        this.f6914a.a();
        int i3 = this.f6915b;
        if (i3 != 0) {
            this.f6914a.e(i3);
            this.f6915b = 0;
        }
        int i4 = this.f6916c;
        if (i4 == 0) {
            return true;
        }
        this.f6914a.d(i4);
        this.f6916c = 0;
        return true;
    }

    public AbstractC0751c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6915b = 0;
        this.f6916c = 0;
    }
}
