package h1;

import H0.j;
import H0.k;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f4494a;
    private static volatile Choreographer choreographer;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Object objA;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        try {
            j.a aVar = j.f299a;
            objA = j.a(new c(a(Looper.getMainLooper(), true), objArr2 == true ? 1 : 0, 2, objArr == true ? 1 : 0));
        } catch (Throwable th) {
            j.a aVar2 = j.f299a;
            objA = j.a(k.a(th));
        }
        f4494a = (d) (j.c(objA) ? null : objA);
    }

    public static final Handler a(Looper looper, boolean z2) throws IllegalAccessException, InvocationTargetException {
        if (!z2) {
            return new Handler(looper);
        }
        Object objInvoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        n.e(objInvoke, "null cannot be cast to non-null type android.os.Handler");
        return (Handler) objInvoke;
    }

    public static final d b(Handler handler, String str) {
        return new c(handler, str);
    }
}
