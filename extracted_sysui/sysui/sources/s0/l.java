package s0;

import com.miui.miplay.audio.data.DeviceInfo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import l0.InterfaceC0442e;
import z0.h;

/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f6471a = new HashMap();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final InterfaceC0442e f6472a;

        public a(InterfaceC0442e interfaceC0442e) {
            this.f6472a = interfaceC0442e;
        }

        public final void d(h.a aVar) {
            try {
                aVar.invoke();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void e(final int i2) {
            d(new h.a() { // from class: s0.j
                @Override // z0.h.a
                public final void invoke() {
                    this.f6467a.i(i2);
                }
            });
        }

        public boolean equals(Object obj) {
            return obj != null && this.f6472a == ((a) obj).h();
        }

        public void f(final DeviceInfo deviceInfo) {
            d(new h.a() { // from class: s0.i
                @Override // z0.h.a
                public final void invoke() {
                    this.f6465a.j(deviceInfo);
                }
            });
        }

        public void g(final int i2) {
            d(new h.a() { // from class: s0.k
                @Override // z0.h.a
                public final void invoke() {
                    this.f6469a.k(i2);
                }
            });
        }

        public InterfaceC0442e h() {
            return this.f6472a;
        }

        public int hashCode() {
            return this.f6472a.hashCode();
        }

        public final /* synthetic */ void i(int i2) {
            this.f6472a.onDeviceConnectionStateChange(i2, 0);
        }

        public final /* synthetic */ void j(DeviceInfo deviceInfo) {
            this.f6472a.onDeviceInfoChange(deviceInfo);
        }

        public final /* synthetic */ void k(int i2) {
            this.f6472a.onVolumeChange(i2, 0);
        }
    }

    public void a(String str, InterfaceC0442e interfaceC0442e) {
        this.f6471a.putIfAbsent(str, new HashSet());
        Set set = (Set) this.f6471a.get(str);
        Objects.requireNonNull(set);
        set.add(new a(interfaceC0442e));
    }

    public void b(String str, int i2) {
        Set set = (Set) this.f6471a.get(str);
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((a) it.next()).e(i2);
        }
    }

    public void c(String str, DeviceInfo deviceInfo) {
        Set set = (Set) this.f6471a.get(str);
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((a) it.next()).f(deviceInfo);
        }
    }

    public void d(String str, int i2) {
        Set set = (Set) this.f6471a.get(str);
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((a) it.next()).g(i2);
        }
    }

    public void e(String str, InterfaceC0442e interfaceC0442e) {
        if (this.f6471a.containsKey(str)) {
            Set set = (Set) this.f6471a.get(str);
            Objects.requireNonNull(set);
            set.remove(interfaceC0442e);
        }
    }
}
