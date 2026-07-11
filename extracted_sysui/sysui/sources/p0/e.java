package p0;

import androidx.core.location.LocationRequestCompat;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6375a = 2;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6376b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f6377c = LocationRequestCompat.PASSIVE_INTERVAL;

    public int a() {
        return this.f6375a;
    }

    public int b() {
        return this.f6376b;
    }

    public boolean c(int i2) {
        boolean z2 = this.f6375a != i2;
        this.f6375a = i2;
        if (i2 == 1) {
            this.f6377c = System.currentTimeMillis();
        } else {
            this.f6377c = LocationRequestCompat.PASSIVE_INTERVAL;
        }
        return z2;
    }

    public boolean d(int i2) {
        boolean z2 = this.f6376b != i2;
        this.f6376b = i2;
        return z2;
    }
}
