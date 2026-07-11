package X;

import U.n;
import U.q;
import U.r;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import c0.EnumC0227b;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class j implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final W.c f868a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final U.c f869b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final W.d f870c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final X.e f871d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f872e;

    public class a extends c {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ boolean f873f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ Method f874g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ boolean f875h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ q f876i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ U.d f877j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ C0223a f878k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ boolean f879l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final /* synthetic */ boolean f880m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Field field, boolean z2, boolean z3, boolean z4, Method method, boolean z5, q qVar, U.d dVar, C0223a c0223a, boolean z6, boolean z7) {
            super(str, field, z2, z3);
            this.f873f = z4;
            this.f874g = method;
            this.f875h = z5;
            this.f876i = qVar;
            this.f877j = dVar;
            this.f878k = c0223a;
            this.f879l = z6;
            this.f880m = z7;
        }

        @Override // X.j.c
        public void a(C0226a c0226a, int i2, Object[] objArr) {
            Object objB = this.f876i.b(c0226a);
            if (objB != null || !this.f879l) {
                objArr[i2] = objB;
                return;
            }
            throw new U.j("null is not allowed as value for record component '" + this.f885c + "' of primitive type; at path " + c0226a.t());
        }

        @Override // X.j.c
        public void b(C0226a c0226a, Object obj) throws IllegalAccessException {
            Object objB = this.f876i.b(c0226a);
            if (objB == null && this.f879l) {
                return;
            }
            if (this.f873f) {
                j.c(obj, this.f884b);
            } else if (this.f880m) {
                throw new U.g("Cannot set value of 'static final' " + Z.a.g(this.f884b, false));
            }
            this.f884b.set(obj, objB);
        }

        @Override // X.j.c
        public void c(C0228c c0228c, Object obj) throws IllegalAccessException {
            Object objInvoke;
            if (this.f886d) {
                if (this.f873f) {
                    Method method = this.f874g;
                    if (method == null) {
                        j.c(obj, this.f884b);
                    } else {
                        j.c(obj, method);
                    }
                }
                Method method2 = this.f874g;
                if (method2 != null) {
                    try {
                        objInvoke = method2.invoke(obj, null);
                    } catch (InvocationTargetException e2) {
                        throw new U.g("Accessor " + Z.a.g(this.f874g, false) + " threw exception", e2.getCause());
                    }
                } else {
                    objInvoke = this.f884b.get(obj);
                }
                if (objInvoke == obj) {
                    return;
                }
                c0228c.x(this.f883a);
                (this.f875h ? this.f876i : new l(this.f877j, this.f876i, this.f878k.getType())).d(c0228c, objInvoke);
            }
        }
    }

    public static abstract class b extends q {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map f882a;

        public b(Map map) {
            this.f882a = map;
        }

        @Override // U.q
        public Object b(C0226a c0226a) throws IOException {
            if (c0226a.M() == EnumC0227b.NULL) {
                c0226a.I();
                return null;
            }
            Object objE = e();
            try {
                c0226a.c();
                while (c0226a.x()) {
                    c cVar = (c) this.f882a.get(c0226a.G());
                    if (cVar == null || !cVar.f887e) {
                        c0226a.W();
                    } else {
                        g(objE, c0226a, cVar);
                    }
                }
                c0226a.n();
                return f(objE);
            } catch (IllegalAccessException e2) {
                throw Z.a.e(e2);
            } catch (IllegalStateException e3) {
                throw new U.l(e3);
            }
        }

        @Override // U.q
        public void d(C0228c c0228c, Object obj) throws IOException {
            if (obj == null) {
                c0228c.A();
                return;
            }
            c0228c.e();
            try {
                Iterator it = this.f882a.values().iterator();
                while (it.hasNext()) {
                    ((c) it.next()).c(c0228c, obj);
                }
                c0228c.n();
            } catch (IllegalAccessException e2) {
                throw Z.a.e(e2);
            }
        }

        public abstract Object e();

        public abstract Object f(Object obj);

        public abstract void g(Object obj, C0226a c0226a, c cVar);
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f883a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Field f884b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final String f885c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f886d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final boolean f887e;

        public c(String str, Field field, boolean z2, boolean z3) {
            this.f883a = str;
            this.f884b = field;
            this.f885c = field.getName();
            this.f886d = z2;
            this.f887e = z3;
        }

        public abstract void a(C0226a c0226a, int i2, Object[] objArr);

        public abstract void b(C0226a c0226a, Object obj);

        public abstract void c(C0228c c0228c, Object obj);
    }

    public static final class d extends b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final W.i f888b;

        public d(W.i iVar, Map map) {
            super(map);
            this.f888b = iVar;
        }

        @Override // X.j.b
        public Object e() {
            return this.f888b.a();
        }

        @Override // X.j.b
        public Object f(Object obj) {
            return obj;
        }

        @Override // X.j.b
        public void g(Object obj, C0226a c0226a, c cVar) {
            cVar.b(c0226a, obj);
        }
    }

    public static final class e extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final Map f889e = j();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Constructor f890b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Object[] f891c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Map f892d;

        public e(Class cls, Map map, boolean z2) {
            super(map);
            this.f892d = new HashMap();
            Constructor constructorI = Z.a.i(cls);
            this.f890b = constructorI;
            if (z2) {
                j.c(null, constructorI);
            } else {
                Z.a.l(constructorI);
            }
            String[] strArrJ = Z.a.j(cls);
            for (int i2 = 0; i2 < strArrJ.length; i2++) {
                this.f892d.put(strArrJ[i2], Integer.valueOf(i2));
            }
            Class<?>[] parameterTypes = this.f890b.getParameterTypes();
            this.f891c = new Object[parameterTypes.length];
            for (int i3 = 0; i3 < parameterTypes.length; i3++) {
                this.f891c[i3] = f889e.get(parameterTypes[i3]);
            }
        }

        public static Map j() {
            HashMap map = new HashMap();
            map.put(Byte.TYPE, (byte) 0);
            map.put(Short.TYPE, (short) 0);
            map.put(Integer.TYPE, 0);
            map.put(Long.TYPE, 0L);
            map.put(Float.TYPE, Float.valueOf(0.0f));
            map.put(Double.TYPE, Double.valueOf(0.0d));
            map.put(Character.TYPE, (char) 0);
            map.put(Boolean.TYPE, Boolean.FALSE);
            return map;
        }

        @Override // X.j.b
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public Object[] e() {
            return (Object[]) this.f891c.clone();
        }

        @Override // X.j.b
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Object f(Object[] objArr) {
            try {
                return this.f890b.newInstance(objArr);
            } catch (IllegalAccessException e2) {
                throw Z.a.e(e2);
            } catch (IllegalArgumentException | InstantiationException e3) {
                throw new RuntimeException("Failed to invoke constructor '" + Z.a.c(this.f890b) + "' with args " + Arrays.toString(objArr), e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to invoke constructor '" + Z.a.c(this.f890b) + "' with args " + Arrays.toString(objArr), e4.getCause());
            }
        }

        @Override // X.j.b
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public void g(Object[] objArr, C0226a c0226a, c cVar) {
            Integer num = (Integer) this.f892d.get(cVar.f885c);
            if (num != null) {
                cVar.a(c0226a, num.intValue(), objArr);
                return;
            }
            throw new IllegalStateException("Could not find the index in the constructor '" + Z.a.c(this.f890b) + "' for field with name '" + cVar.f885c + "', unable to determine which argument in the constructor the field corresponds to. This is unexpected behavior, as we expect the RecordComponents to have the same names as the fields in the Java class, and that the order of the RecordComponents is the same as the order of the canonical constructor parameters.");
        }
    }

    public j(W.c cVar, U.c cVar2, W.d dVar, X.e eVar, List list) {
        this.f868a = cVar;
        this.f869b = cVar2;
        this.f870c = dVar;
        this.f871d = eVar;
        this.f872e = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void c(Object obj, AccessibleObject accessibleObject) {
        if (Modifier.isStatic(((Member) accessibleObject).getModifiers())) {
            obj = null;
        }
        if (W.l.a(accessibleObject, obj)) {
            return;
        }
        throw new U.g(Z.a.g(accessibleObject, true) + " is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type.");
    }

    @Override // U.r
    public q a(U.d dVar, C0223a c0223a) {
        Class<Object> rawType = c0223a.getRawType();
        if (!Object.class.isAssignableFrom(rawType)) {
            return null;
        }
        n nVarB = W.l.b(this.f872e, rawType);
        if (nVarB != n.BLOCK_ALL) {
            boolean z2 = nVarB == n.BLOCK_INACCESSIBLE;
            return Z.a.k(rawType) ? new e(rawType, e(dVar, c0223a, rawType, z2, true), z2) : new d(this.f868a.b(c0223a), e(dVar, c0223a, rawType, z2, false));
        }
        throw new U.g("ReflectionAccessFilter does not permit using reflection for " + rawType + ". Register a TypeAdapter for this type or adjust the access filter.");
    }

    public final c d(U.d dVar, Field field, Method method, String str, C0223a c0223a, boolean z2, boolean z3, boolean z4) {
        boolean zA = W.k.a(c0223a.getRawType());
        int modifiers = field.getModifiers();
        boolean z5 = Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
        V.b bVar = (V.b) field.getAnnotation(V.b.class);
        q qVarB = bVar != null ? this.f871d.b(this.f868a, dVar, c0223a, bVar) : null;
        boolean z6 = qVarB != null;
        if (qVarB == null) {
            qVarB = dVar.l(c0223a);
        }
        return new a(str, field, z2, z3, z4, method, z6, qVarB, dVar, c0223a, zA, z5);
    }

    public final Map e(U.d dVar, C0223a c0223a, Class cls, boolean z2, boolean z3) {
        boolean z4;
        Method method;
        int i2;
        int i3;
        boolean z5;
        j jVar = this;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        C0223a c0223a2 = c0223a;
        boolean z6 = z2;
        Class rawType = cls;
        while (rawType != Object.class) {
            Field[] declaredFields = rawType.getDeclaredFields();
            boolean z7 = true;
            boolean z8 = false;
            if (rawType != cls && declaredFields.length > 0) {
                n nVarB = W.l.b(jVar.f872e, rawType);
                if (nVarB == n.BLOCK_ALL) {
                    throw new U.g("ReflectionAccessFilter does not permit using reflection for " + rawType + " (supertype of " + cls + "). Register a TypeAdapter for this type or adjust the access filter.");
                }
                z6 = nVarB == n.BLOCK_INACCESSIBLE;
            }
            boolean z9 = z6;
            int length = declaredFields.length;
            int i4 = 0;
            while (i4 < length) {
                Field field = declaredFields[i4];
                boolean zG = jVar.g(field, z7);
                boolean zG2 = jVar.g(field, z8);
                if (zG || zG2) {
                    c cVar = null;
                    if (!z3) {
                        z4 = zG2;
                        method = null;
                    } else if (Modifier.isStatic(field.getModifiers())) {
                        method = null;
                        z4 = z8;
                    } else {
                        Method methodH = Z.a.h(rawType, field);
                        if (!z9) {
                            Z.a.l(methodH);
                        }
                        if (methodH.getAnnotation(V.c.class) != null && field.getAnnotation(V.c.class) == null) {
                            throw new U.g("@SerializedName on " + Z.a.g(methodH, z8) + " is not supported");
                        }
                        z4 = zG2;
                        method = methodH;
                    }
                    if (!z9 && method == null) {
                        Z.a.l(field);
                    }
                    Type typeO = W.b.o(c0223a2.getType(), rawType, field.getGenericType());
                    List listF = jVar.f(field);
                    int size = listF.size();
                    int i5 = z8;
                    while (i5 < size) {
                        String str = (String) listF.get(i5);
                        boolean z10 = i5 != 0 ? z8 : zG;
                        int i6 = i5;
                        c cVar2 = cVar;
                        int i7 = size;
                        List list = listF;
                        Field field2 = field;
                        int i8 = i4;
                        int i9 = length;
                        boolean z11 = z8;
                        cVar = cVar2 == null ? (c) linkedHashMap.put(str, d(dVar, field, method, str, C0223a.get(typeO), z10, z4, z9)) : cVar2;
                        i5 = i6 + 1;
                        zG = z10;
                        i4 = i8;
                        size = i7;
                        listF = list;
                        field = field2;
                        length = i9;
                        z8 = z11;
                    }
                    c cVar3 = cVar;
                    Field field3 = field;
                    i2 = i4;
                    i3 = length;
                    z5 = z8;
                    if (cVar3 != null) {
                        throw new IllegalArgumentException("Class " + cls.getName() + " declares multiple JSON fields named '" + cVar3.f883a + "'; conflict is caused by fields " + Z.a.f(cVar3.f884b) + " and " + Z.a.f(field3));
                    }
                } else {
                    i2 = i4;
                    i3 = length;
                    z5 = z8;
                }
                i4 = i2 + 1;
                z7 = true;
                jVar = this;
                length = i3;
                z8 = z5;
            }
            c0223a2 = C0223a.get(W.b.o(c0223a2.getType(), rawType, rawType.getGenericSuperclass()));
            rawType = c0223a2.getRawType();
            jVar = this;
            z6 = z9;
        }
        return linkedHashMap;
    }

    public final List f(Field field) {
        V.c cVar = (V.c) field.getAnnotation(V.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f869b.a(field));
        }
        String strValue = cVar.value();
        String[] strArrAlternate = cVar.alternate();
        if (strArrAlternate.length == 0) {
            return Collections.singletonList(strValue);
        }
        ArrayList arrayList = new ArrayList(strArrAlternate.length + 1);
        arrayList.add(strValue);
        Collections.addAll(arrayList, strArrAlternate);
        return arrayList;
    }

    public final boolean g(Field field, boolean z2) {
        return (this.f870c.c(field.getType(), z2) || this.f870c.f(field, z2)) ? false : true;
    }
}
