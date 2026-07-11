package W;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f831a = c();

    public class a extends n {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Method f832b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f833c;

        public a(Method method, Object obj) {
            this.f832b = method;
            this.f833c = obj;
        }

        @Override // W.n
        public Object d(Class cls) {
            n.b(cls);
            return this.f832b.invoke(this.f833c, cls);
        }
    }

    public class b extends n {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Method f834b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f835c;

        public b(Method method, int i2) {
            this.f834b = method;
            this.f835c = i2;
        }

        @Override // W.n
        public Object d(Class cls) {
            n.b(cls);
            return this.f834b.invoke(null, cls, Integer.valueOf(this.f835c));
        }
    }

    public class c extends n {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Method f836b;

        public c(Method method) {
            this.f836b = method;
        }

        @Override // W.n
        public Object d(Class cls) {
            n.b(cls);
            return this.f836b.invoke(null, cls, Object.class);
        }
    }

    public class d extends n {
        @Override // W.n
        public Object d(Class cls) {
            throw new UnsupportedOperationException("Cannot allocate " + cls + ". Usage of JDK sun.misc.Unsafe is enabled, but it could not be used. Make sure your runtime is configured correctly.");
        }
    }

    public static void b(Class cls) {
        String strA = W.c.a(cls);
        if (strA == null) {
            return;
        }
        throw new AssertionError("UnsafeAllocator is used for non-instantiable type: " + strA);
    }

    public static n c() {
        try {
            try {
                try {
                    Class<?> cls = Class.forName("sun.misc.Unsafe");
                    Field declaredField = cls.getDeclaredField("theUnsafe");
                    declaredField.setAccessible(true);
                    return new a(cls.getMethod("allocateInstance", Class.class), declaredField.get(null));
                } catch (Exception unused) {
                    return new d();
                }
            } catch (Exception unused2) {
                Method declaredMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                declaredMethod.setAccessible(true);
                return new c(declaredMethod);
            }
        } catch (Exception unused3) {
            Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
            declaredMethod2.setAccessible(true);
            int iIntValue = ((Integer) declaredMethod2.invoke(null, Object.class)).intValue();
            Method declaredMethod3 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
            declaredMethod3.setAccessible(true);
            return new b(declaredMethod3, iIntValue);
        }
    }

    public abstract Object d(Class cls);
}
