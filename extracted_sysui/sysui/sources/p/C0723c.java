package p;

import android.util.Log;
import d.AbstractC0302c;
import d.I;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: renamed from: p.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0723c implements I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Set f6326a = new HashSet();

    @Override // d.I
    public void a(String str) {
        d(str, null);
    }

    @Override // d.I
    public void b(String str, Throwable th) {
        if (AbstractC0302c.f3895a) {
            Log.d("LOTTIE", str, th);
        }
    }

    @Override // d.I
    public void c(String str) {
        e(str, null);
    }

    @Override // d.I
    public void d(String str, Throwable th) {
        Set set = f6326a;
        if (set.contains(str)) {
            return;
        }
        Log.w("LOTTIE", str, th);
        set.add(str);
    }

    public void e(String str, Throwable th) {
        if (AbstractC0302c.f3895a) {
            Log.d("LOTTIE", str, th);
        }
    }
}
