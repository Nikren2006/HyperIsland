package N0;

import com.xiaomi.onetrack.api.au;
import java.lang.reflect.Method;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f442a = new i();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f443b = new a(null, null, null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static a f444c;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Method f445a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f446b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Method f447c;

        public a(Method method, Method method2, Method method3) {
            this.f445a = method;
            this.f446b = method2;
            this.f447c = method3;
        }
    }

    public final a a(N0.a aVar) {
        try {
            a aVar2 = new a(Class.class.getDeclaredMethod("getModule", null), aVar.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), aVar.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod(au.f2921a, null));
            f444c = aVar2;
            return aVar2;
        } catch (Exception unused) {
            a aVar3 = f443b;
            f444c = aVar3;
            return aVar3;
        }
    }

    public final String b(N0.a continuation) {
        n.g(continuation, "continuation");
        a aVarA = f444c;
        if (aVarA == null) {
            aVarA = a(continuation);
        }
        if (aVarA == f443b) {
            return null;
        }
        Method method = aVarA.f445a;
        Object objInvoke = method != null ? method.invoke(continuation.getClass(), null) : null;
        if (objInvoke == null) {
            return null;
        }
        Method method2 = aVarA.f446b;
        Object objInvoke2 = method2 != null ? method2.invoke(objInvoke, null) : null;
        if (objInvoke2 == null) {
            return null;
        }
        Method method3 = aVarA.f447c;
        Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, null) : null;
        if (objInvoke3 instanceof String) {
            return (String) objInvoke3;
        }
        return null;
    }
}
