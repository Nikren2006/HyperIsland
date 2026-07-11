package s0;

import com.xiaomi.cast.api.DeviceInfo;

/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DeviceInfo f6463a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6464b = 0;

    public h(DeviceInfo deviceInfo) {
        this.f6463a = deviceInfo;
    }

    public boolean a(int i2) {
        return (this.f6464b & i2) == i2;
    }

    public DeviceInfo b() {
        return this.f6463a;
    }

    public boolean c() {
        return a(1) || a(2) || a(4);
    }

    public void d(int i2, boolean z2) {
        if (z2) {
            this.f6464b = i2 | this.f6464b;
        } else {
            this.f6464b = (~i2) & this.f6464b;
        }
    }
}
