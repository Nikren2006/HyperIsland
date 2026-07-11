package p0;

import com.miui.miplay.audio.data.DeviceInfo;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: p0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0728a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6345a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f6346b;

    public AbstractC0728a(String str) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.f6346b = concurrentHashMap;
        this.f6345a = str;
        e eVar = new e();
        e eVar2 = new e();
        concurrentHashMap.put(0, eVar);
        concurrentHashMap.put(1, eVar2);
    }

    public synchronized int a(int i2) {
        int iA;
        iA = ((e) this.f6346b.get(Integer.valueOf(i2))).a();
        z0.e.c("AbsDevice", z0.i.a(b()) + ",getDeviceConnectionState: " + iA + ", mediaType: " + i2);
        return iA;
    }

    public String b() {
        return this.f6345a;
    }

    public abstract DeviceInfo c();

    public synchronized int d(int i2) {
        int iB;
        try {
            iB = ((e) this.f6346b.get(Integer.valueOf(i2))).b();
            if (z0.i.b(b())) {
                z0.e.c("AbsDevice", b() + ",getDeviceSelectStatus: " + iB + ", mediaType: " + i2);
            } else {
                z0.e.c("AbsDevice", z0.i.a(b()) + ",getDeviceSelectStatus: " + iB + ", mediaType: " + i2);
            }
        } catch (Throwable th) {
            throw th;
        }
        return iB;
    }

    public synchronized boolean e(int i2) {
        boolean z2;
        z2 = false;
        if (((e) this.f6346b.get(0)).c(i2)) {
            if (((e) this.f6346b.get(1)).c(i2)) {
                z2 = true;
            }
        }
        return z2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f6345a.equals(((AbstractC0728a) obj).f6345a);
    }

    public boolean f(int i2) {
        return g(i2, 1) | g(i2, 0);
    }

    public synchronized boolean g(int i2, int i3) {
        try {
            if (z0.i.b(b())) {
                z0.e.c("AbsDevice", b() + ",setDeviceSelectStatus: " + i2 + ", mediaType: " + i3);
            } else {
                z0.e.c("AbsDevice", z0.i.a(b()) + ",setDeviceSelectStatus: " + i2 + ", mediaType: " + i3);
            }
        } catch (Throwable th) {
            throw th;
        }
        return ((e) this.f6346b.get(Integer.valueOf(i3))).d(i2);
    }

    public int hashCode() {
        return Objects.hash(this.f6345a);
    }
}
