package j1;

import androidx.core.location.LocationRequestCompat;

/* JADX INFO: loaded from: classes2.dex */
public interface E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f4648a = a.f4649a;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ a f4649a = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final E f4650b = new F();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final E f4651c = new G();

        public static /* synthetic */ E b(a aVar, long j2, long j3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = 0;
            }
            if ((i2 & 2) != 0) {
                j3 = LocationRequestCompat.PASSIVE_INTERVAL;
            }
            return aVar.a(j2, j3);
        }

        public final E a(long j2, long j3) {
            return new H(j2, j3);
        }

        public final E c() {
            return f4650b;
        }

        public final E d() {
            return f4651c;
        }
    }

    InterfaceC0418f a(I i2);
}
