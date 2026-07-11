package W;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class l {

    public static abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f825a;

        public class a extends b {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Method f826b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Method method) {
                super();
                this.f826b = method;
            }

            @Override // W.l.b
            public boolean a(AccessibleObject accessibleObject, Object obj) {
                try {
                    return ((Boolean) this.f826b.invoke(accessibleObject, obj)).booleanValue();
                } catch (Exception e2) {
                    throw new RuntimeException("Failed invoking canAccess", e2);
                }
            }
        }

        /* JADX INFO: renamed from: W.l$b$b, reason: collision with other inner class name */
        public class C0024b extends b {
            public C0024b() {
                super();
            }

            @Override // W.l.b
            public boolean a(AccessibleObject accessibleObject, Object obj) {
                return true;
            }
        }

        static {
            b aVar;
            if (e.d()) {
                try {
                    aVar = new a(AccessibleObject.class.getDeclaredMethod("canAccess", Object.class));
                } catch (NoSuchMethodException unused) {
                    aVar = null;
                }
            } else {
                aVar = null;
            }
            if (aVar == null) {
                aVar = new C0024b();
            }
            f825a = aVar;
        }

        public b() {
        }

        public abstract boolean a(AccessibleObject accessibleObject, Object obj);
    }

    public static boolean a(AccessibleObject accessibleObject, Object obj) {
        return b.f825a.a(accessibleObject, obj);
    }

    public static U.n b(List list, Class cls) {
        Iterator it = list.iterator();
        if (!it.hasNext()) {
            return U.n.ALLOW;
        }
        android.support.v4.media.a.a(it.next());
        throw null;
    }
}
