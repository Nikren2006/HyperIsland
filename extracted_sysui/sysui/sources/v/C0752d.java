package v;

import android.view.View;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: v.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0752d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f6917a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6918b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f6919c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f6920d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6921e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6922f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f6923g = true;

    public C0752d(View view) {
        this.f6917a = view;
    }

    public void a() {
        View view = this.f6917a;
        ViewCompat.offsetTopAndBottom(view, this.f6920d - (view.getTop() - this.f6918b));
        View view2 = this.f6917a;
        ViewCompat.offsetLeftAndRight(view2, this.f6921e - (view2.getLeft() - this.f6919c));
    }

    public int b() {
        return this.f6920d;
    }

    public void c() {
        this.f6918b = this.f6917a.getTop();
        this.f6919c = this.f6917a.getLeft();
    }

    public boolean d(int i2) {
        if (!this.f6923g || this.f6921e == i2) {
            return false;
        }
        this.f6921e = i2;
        a();
        return true;
    }

    public boolean e(int i2) {
        if (!this.f6922f || this.f6920d == i2) {
            return false;
        }
        this.f6920d = i2;
        a();
        return true;
    }
}
