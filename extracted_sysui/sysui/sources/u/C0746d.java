package u;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* JADX INFO: renamed from: u.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0746d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f6841a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f6842b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TimeInterpolator f6843c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f6844d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6845e;

    public C0746d(long j2, long j3) {
        this.f6843c = null;
        this.f6844d = 0;
        this.f6845e = 1;
        this.f6841a = j2;
        this.f6842b = j3;
    }

    public static C0746d a(ValueAnimator valueAnimator) {
        C0746d c0746d = new C0746d(valueAnimator.getStartDelay(), valueAnimator.getDuration(), e(valueAnimator));
        c0746d.f6844d = valueAnimator.getRepeatCount();
        c0746d.f6845e = valueAnimator.getRepeatMode();
        return c0746d;
    }

    public static TimeInterpolator e(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        return ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) ? AbstractC0743a.f6835b : interpolator instanceof AccelerateInterpolator ? AbstractC0743a.f6836c : interpolator instanceof DecelerateInterpolator ? AbstractC0743a.f6837d : interpolator;
    }

    public long b() {
        return this.f6841a;
    }

    public long c() {
        return this.f6842b;
    }

    public TimeInterpolator d() {
        TimeInterpolator timeInterpolator = this.f6843c;
        return timeInterpolator != null ? timeInterpolator : AbstractC0743a.f6835b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0746d)) {
            return false;
        }
        C0746d c0746d = (C0746d) obj;
        if (b() == c0746d.b() && c() == c0746d.c() && f() == c0746d.f() && g() == c0746d.g()) {
            return d().getClass().equals(c0746d.d().getClass());
        }
        return false;
    }

    public int f() {
        return this.f6844d;
    }

    public int g() {
        return this.f6845e;
    }

    public int hashCode() {
        return (((((((((int) (b() ^ (b() >>> 32))) * 31) + ((int) (c() ^ (c() >>> 32)))) * 31) + d().getClass().hashCode()) * 31) + f()) * 31) + g();
    }

    public String toString() {
        return '\n' + getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + b() + " duration: " + c() + " interpolator: " + d().getClass() + " repeatCount: " + f() + " repeatMode: " + g() + "}\n";
    }

    public C0746d(long j2, long j3, TimeInterpolator timeInterpolator) {
        this.f6844d = 0;
        this.f6845e = 1;
        this.f6841a = j2;
        this.f6842b = j3;
        this.f6843c = timeInterpolator;
    }
}
