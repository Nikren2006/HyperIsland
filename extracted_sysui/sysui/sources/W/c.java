package W;

import b0.C0223a;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f768b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f769c;

    public class a implements W.i {
        @Override // W.i
        public Object a() {
            return new TreeSet();
        }
    }

    public class b implements W.i {
        @Override // W.i
        public Object a() {
            return new LinkedHashSet();
        }
    }

    /* JADX INFO: renamed from: W.c$c, reason: collision with other inner class name */
    public class C0023c implements W.i {
        @Override // W.i
        public Object a() {
            return new ArrayDeque();
        }
    }

    public class d implements W.i {
        @Override // W.i
        public Object a() {
            return new ArrayList();
        }
    }

    public class e implements W.i {
        @Override // W.i
        public Object a() {
            return new ConcurrentSkipListMap();
        }
    }

    public class f implements W.i {
        @Override // W.i
        public Object a() {
            return new ConcurrentHashMap();
        }
    }

    public class g implements W.i {
        @Override // W.i
        public Object a() {
            return new TreeMap();
        }
    }

    public class h implements W.i {
        @Override // W.i
        public Object a() {
            return new LinkedHashMap();
        }
    }

    public class i implements W.i {
        @Override // W.i
        public Object a() {
            return new W.h();
        }
    }

    public class j implements W.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Class f770a;

        public j(Class cls) {
            this.f770a = cls;
        }

        @Override // W.i
        public Object a() {
            try {
                return W.n.f831a.d(this.f770a);
            } catch (Exception e2) {
                throw new RuntimeException("Unable to create instance of " + this.f770a + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e2);
            }
        }
    }

    public class k implements W.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f772a;

        public k(String str) {
            this.f772a = str;
        }

        @Override // W.i
        public Object a() {
            throw new U.g(this.f772a);
        }
    }

    public class l implements W.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f774a;

        public l(String str) {
            this.f774a = str;
        }

        @Override // W.i
        public Object a() {
            throw new U.g(this.f774a);
        }
    }

    public class m implements W.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f776a;

        public m(String str) {
            this.f776a = str;
        }

        @Override // W.i
        public Object a() {
            throw new U.g(this.f776a);
        }
    }

    public class n implements W.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f778a;

        public n(Type type) {
            this.f778a = type;
        }

        @Override // W.i
        public Object a() {
            Type type = this.f778a;
            if (!(type instanceof ParameterizedType)) {
                throw new U.g("Invalid EnumSet type: " + this.f778a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return EnumSet.noneOf((Class) type2);
            }
            throw new U.g("Invalid EnumSet type: " + this.f778a.toString());
        }
    }

    public class o implements W.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f779a;

        public o(Type type) {
            this.f779a = type;
        }

        @Override // W.i
        public Object a() {
            Type type = this.f779a;
            if (!(type instanceof ParameterizedType)) {
                throw new U.g("Invalid EnumMap type: " + this.f779a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return new EnumMap((Class) type2);
            }
            throw new U.g("Invalid EnumMap type: " + this.f779a.toString());
        }
    }

    public class p implements W.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f780a;

        public p(String str) {
            this.f780a = str;
        }

        @Override // W.i
        public Object a() {
            throw new U.g(this.f780a);
        }
    }

    public class q implements W.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f781a;

        public q(String str) {
            this.f781a = str;
        }

        @Override // W.i
        public Object a() {
            throw new U.g(this.f781a);
        }
    }

    public class r implements W.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Constructor f782a;

        public r(Constructor constructor) {
            this.f782a = constructor;
        }

        @Override // W.i
        public Object a() {
            try {
                return this.f782a.newInstance(null);
            } catch (IllegalAccessException e2) {
                throw Z.a.e(e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Failed to invoke constructor '" + Z.a.c(this.f782a) + "' with no args", e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to invoke constructor '" + Z.a.c(this.f782a) + "' with no args", e4.getCause());
            }
        }
    }

    public c(Map map, boolean z2, List list) {
        this.f767a = map;
        this.f768b = z2;
        this.f769c = list;
    }

    public static String a(Class cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            return "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: " + cls.getName();
        }
        if (!Modifier.isAbstract(modifiers)) {
            return null;
        }
        return "Abstract classes can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Class name: " + cls.getName();
    }

    public static W.i c(Class cls, U.n nVar) {
        String strM;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(null);
            U.n nVar2 = U.n.ALLOW;
            if (nVar == nVar2 || (W.l.a(declaredConstructor, null) && (nVar != U.n.BLOCK_ALL || Modifier.isPublic(declaredConstructor.getModifiers())))) {
                return (nVar != nVar2 || (strM = Z.a.m(declaredConstructor)) == null) ? new r(declaredConstructor) : new q(strM);
            }
            return new p("Unable to invoke no-args constructor of " + cls + "; constructor is not accessible and ReflectionAccessFilter does not permit making it accessible. Register an InstanceCreator or a TypeAdapter for this type, change the visibility of the constructor or adjust the access filter.");
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static W.i d(Type type, Class cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new a() : Set.class.isAssignableFrom(cls) ? new b() : Queue.class.isAssignableFrom(cls) ? new C0023c() : new d();
        }
        if (Map.class.isAssignableFrom(cls)) {
            return ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new e() : ConcurrentMap.class.isAssignableFrom(cls) ? new f() : SortedMap.class.isAssignableFrom(cls) ? new g() : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(C0223a.get(((ParameterizedType) type).getActualTypeArguments()[0]).getRawType())) ? new i() : new h();
        }
        return null;
    }

    public static W.i e(Type type, Class cls) {
        if (EnumSet.class.isAssignableFrom(cls)) {
            return new n(type);
        }
        if (cls == EnumMap.class) {
            return new o(type);
        }
        return null;
    }

    public W.i b(C0223a c0223a) {
        Type type = c0223a.getType();
        Class<Object> rawType = c0223a.getRawType();
        android.support.v4.media.a.a(this.f767a.get(type));
        android.support.v4.media.a.a(this.f767a.get(rawType));
        W.i iVarE = e(type, rawType);
        if (iVarE != null) {
            return iVarE;
        }
        U.n nVarB = W.l.b(this.f769c, rawType);
        W.i iVarC = c(rawType, nVarB);
        if (iVarC != null) {
            return iVarC;
        }
        W.i iVarD = d(type, rawType);
        if (iVarD != null) {
            return iVarD;
        }
        String strA = a(rawType);
        if (strA != null) {
            return new l(strA);
        }
        if (nVarB == U.n.ALLOW) {
            return f(rawType);
        }
        return new m("Unable to create instance of " + rawType + "; ReflectionAccessFilter does not permit using reflection or Unsafe. Register an InstanceCreator or a TypeAdapter for this type or adjust the access filter to allow using reflection.");
    }

    public final W.i f(Class cls) {
        if (this.f768b) {
            return new j(cls);
        }
        return new k("Unable to create instance of " + cls + "; usage of JDK Unsafe is disabled. Registering an InstanceCreator or a TypeAdapter for this type, adding a no-args constructor, or enabling usage of JDK Unsafe may fix this problem.");
    }

    public String toString() {
        return this.f767a.toString();
    }
}
