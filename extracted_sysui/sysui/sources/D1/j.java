package D1;

/* JADX INFO: loaded from: classes5.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static i f107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static long f108b;

    public static void a(i iVar) {
        if (iVar.f105f != null || iVar.f106g != null) {
            throw new IllegalArgumentException();
        }
        if (iVar.f103d) {
            return;
        }
        synchronized (j.class) {
            try {
                long j2 = f108b;
                if (j2 + 8192 > 65536) {
                    return;
                }
                f108b = j2 + 8192;
                iVar.f105f = f107a;
                iVar.f102c = 0;
                iVar.f101b = 0;
                f107a = iVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static i b() {
        synchronized (j.class) {
            try {
                i iVar = f107a;
                if (iVar == null) {
                    return new i();
                }
                f107a = iVar.f105f;
                iVar.f105f = null;
                f108b -= 8192;
                return iVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
