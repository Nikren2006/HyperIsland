package P;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.sidesheet.SideSheetBehavior;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SideSheetBehavior f577a;

    public b(SideSheetBehavior sideSheetBehavior) {
        this.f577a = sideSheetBehavior;
    }

    @Override // P.c
    public int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.rightMargin;
    }

    @Override // P.c
    public float b(int i2) {
        float fD = d();
        return (fD - i2) / (fD - c());
    }

    @Override // P.c
    public int c() {
        return Math.max(0, (d() - this.f577a.u()) - this.f577a.z());
    }

    @Override // P.c
    public int d() {
        return this.f577a.C();
    }

    @Override // P.c
    public int e() {
        return this.f577a.C();
    }

    @Override // P.c
    public int f() {
        return c();
    }

    @Override // P.c
    public int g(View view) {
        return view.getLeft() - this.f577a.z();
    }

    @Override // P.c
    public int h(CoordinatorLayout coordinatorLayout) {
        return coordinatorLayout.getRight();
    }

    @Override // P.c
    public int i() {
        return 0;
    }

    @Override // P.c
    public boolean j(float f2) {
        return f2 < 0.0f;
    }

    @Override // P.c
    public boolean k(View view) {
        return view.getLeft() > (d() + c()) / 2;
    }

    @Override // P.c
    public boolean l(float f2, float f3) {
        return d.a(f2, f3) && Math.abs(f2) > ((float) this.f577a.D());
    }

    @Override // P.c
    public boolean m(View view, float f2) {
        return Math.abs(((float) view.getRight()) + (f2 * this.f577a.x())) > this.f577a.y();
    }

    @Override // P.c
    public void n(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3) {
        int iC = this.f577a.C();
        if (i2 <= iC) {
            marginLayoutParams.rightMargin = iC - i2;
        }
    }
}
