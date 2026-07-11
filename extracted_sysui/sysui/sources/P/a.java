package P;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.sidesheet.SideSheetBehavior;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SideSheetBehavior f576a;

    public a(SideSheetBehavior sideSheetBehavior) {
        this.f576a = sideSheetBehavior;
    }

    @Override // P.c
    public int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.leftMargin;
    }

    @Override // P.c
    public float b(int i2) {
        float fD = d();
        return (i2 - fD) / (c() - fD);
    }

    @Override // P.c
    public int c() {
        return Math.max(0, this.f576a.B() + this.f576a.z());
    }

    @Override // P.c
    public int d() {
        return (-this.f576a.u()) - this.f576a.z();
    }

    @Override // P.c
    public int e() {
        return this.f576a.z();
    }

    @Override // P.c
    public int f() {
        return -this.f576a.u();
    }

    @Override // P.c
    public int g(View view) {
        return view.getRight() + this.f576a.z();
    }

    @Override // P.c
    public int h(CoordinatorLayout coordinatorLayout) {
        return coordinatorLayout.getLeft();
    }

    @Override // P.c
    public int i() {
        return 1;
    }

    @Override // P.c
    public boolean j(float f2) {
        return f2 > 0.0f;
    }

    @Override // P.c
    public boolean k(View view) {
        return view.getRight() < (c() - d()) / 2;
    }

    @Override // P.c
    public boolean l(float f2, float f3) {
        return d.a(f2, f3) && Math.abs(f2) > ((float) this.f576a.D());
    }

    @Override // P.c
    public boolean m(View view, float f2) {
        return Math.abs(((float) view.getLeft()) + (f2 * this.f576a.x())) > this.f576a.y();
    }

    @Override // P.c
    public void n(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3) {
        if (i2 <= this.f576a.C()) {
            marginLayoutParams.leftMargin = i3;
        }
    }
}
