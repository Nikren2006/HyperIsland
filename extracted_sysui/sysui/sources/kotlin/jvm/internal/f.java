package kotlin.jvm.internal;

import I0.F;
import I0.G;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import d1.InterfaceC0324c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes2.dex */
public final class f implements InterfaceC0324c, e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f5045b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Map f5046c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final HashMap f5047d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final HashMap f5048e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final HashMap f5049f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Map f5050g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f5051a;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x003b, code lost:
        
            if (r1 == null) goto L13;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.String a(java.lang.Class r6) {
            /*
                r5 = this;
                java.lang.String r5 = "jClass"
                kotlin.jvm.internal.n.g(r6, r5)
                boolean r5 = r6.isAnonymousClass()
                r0 = 0
                if (r5 == 0) goto Le
                goto Lb3
            Le:
                boolean r5 = r6.isLocalClass()
                if (r5 == 0) goto L6a
                java.lang.String r5 = r6.getSimpleName()
                java.lang.reflect.Method r1 = r6.getEnclosingMethod()
                r2 = 2
                r3 = 36
                if (r1 == 0) goto L41
                kotlin.jvm.internal.n.d(r5)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r1 = r1.getName()
                r4.append(r1)
                r4.append(r3)
                java.lang.String r1 = r4.toString()
                java.lang.String r1 = f1.o.a0(r5, r1, r0, r2, r0)
                if (r1 != 0) goto L3e
                goto L41
            L3e:
                r0 = r1
                goto Lb3
            L41:
                java.lang.reflect.Constructor r6 = r6.getEnclosingConstructor()
                if (r6 == 0) goto L62
                kotlin.jvm.internal.n.d(r5)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r6 = r6.getName()
                r1.append(r6)
                r1.append(r3)
                java.lang.String r6 = r1.toString()
                java.lang.String r0 = f1.o.a0(r5, r6, r0, r2, r0)
                goto Lb3
            L62:
                kotlin.jvm.internal.n.d(r5)
                java.lang.String r0 = f1.o.Z(r5, r3, r0, r2, r0)
                goto Lb3
            L6a:
                boolean r5 = r6.isArray()
                if (r5 == 0) goto L9e
                java.lang.Class r5 = r6.getComponentType()
                boolean r6 = r5.isPrimitive()
                java.lang.String r1 = "Array"
                if (r6 == 0) goto L9b
                java.util.Map r6 = kotlin.jvm.internal.f.d()
                java.lang.String r5 = r5.getName()
                java.lang.Object r5 = r6.get(r5)
                java.lang.String r5 = (java.lang.String) r5
                if (r5 == 0) goto L9b
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                r6.append(r5)
                r6.append(r1)
                java.lang.String r0 = r6.toString()
            L9b:
                if (r0 != 0) goto Lb3
                goto L3e
            L9e:
                java.util.Map r5 = kotlin.jvm.internal.f.d()
                java.lang.String r0 = r6.getName()
                java.lang.Object r5 = r5.get(r0)
                r0 = r5
                java.lang.String r0 = (java.lang.String) r0
                if (r0 != 0) goto Lb3
                java.lang.String r0 = r6.getSimpleName()
            Lb3:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.internal.f.a.a(java.lang.Class):java.lang.String");
        }

        public a() {
        }
    }

    static {
        List listJ = I0.m.j(Function0.class, Function1.class, Function2.class, Function3.class, V0.n.class, V0.o.class, V0.p.class, V0.q.class, V0.r.class, V0.s.class, V0.a.class, V0.b.class, V0.c.class, V0.d.class, V0.e.class, V0.f.class, V0.g.class, V0.h.class, V0.i.class, V0.j.class, V0.k.class, V0.l.class, V0.m.class);
        ArrayList arrayList = new ArrayList(I0.n.o(listJ, 10));
        int i2 = 0;
        for (Object obj : listJ) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                I0.m.n();
            }
            arrayList.add(H0.o.a((Class) obj, Integer.valueOf(i2)));
            i2 = i3;
        }
        f5046c = G.l(arrayList);
        HashMap map = new HashMap();
        map.put(TypedValues.Custom.S_BOOLEAN, "kotlin.Boolean");
        map.put("char", "kotlin.Char");
        map.put("byte", "kotlin.Byte");
        map.put("short", "kotlin.Short");
        map.put("int", "kotlin.Int");
        map.put(TypedValues.Custom.S_FLOAT, "kotlin.Float");
        map.put("long", "kotlin.Long");
        map.put("double", "kotlin.Double");
        f5047d = map;
        HashMap map2 = new HashMap();
        map2.put("java.lang.Boolean", "kotlin.Boolean");
        map2.put("java.lang.Character", "kotlin.Char");
        map2.put("java.lang.Byte", "kotlin.Byte");
        map2.put("java.lang.Short", "kotlin.Short");
        map2.put("java.lang.Integer", "kotlin.Int");
        map2.put("java.lang.Float", "kotlin.Float");
        map2.put("java.lang.Long", "kotlin.Long");
        map2.put("java.lang.Double", "kotlin.Double");
        f5048e = map2;
        HashMap map3 = new HashMap();
        map3.put("java.lang.Object", "kotlin.Any");
        map3.put("java.lang.String", "kotlin.String");
        map3.put("java.lang.CharSequence", "kotlin.CharSequence");
        map3.put("java.lang.Throwable", "kotlin.Throwable");
        map3.put("java.lang.Cloneable", "kotlin.Cloneable");
        map3.put("java.lang.Number", "kotlin.Number");
        map3.put("java.lang.Comparable", "kotlin.Comparable");
        map3.put("java.lang.Enum", "kotlin.Enum");
        map3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        map3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        map3.put("java.util.Iterator", "kotlin.collections.Iterator");
        map3.put("java.util.Collection", "kotlin.collections.Collection");
        map3.put("java.util.List", "kotlin.collections.List");
        map3.put("java.util.Set", "kotlin.collections.Set");
        map3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        map3.put("java.util.Map", "kotlin.collections.Map");
        map3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        map3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        map3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        map3.putAll(map);
        map3.putAll(map2);
        Collection<String> collectionValues = map.values();
        n.f(collectionValues, "<get-values>(...)");
        for (String str : collectionValues) {
            StringBuilder sb = new StringBuilder();
            sb.append("kotlin.jvm.internal.");
            n.d(str);
            sb.append(f1.o.d0(str, '.', null, 2, null));
            sb.append("CompanionObject");
            H0.i iVarA = H0.o.a(sb.toString(), str + ".Companion");
            map3.put(iVarA.d(), iVarA.e());
        }
        for (Map.Entry entry : f5046c.entrySet()) {
            map3.put(((Class) entry.getKey()).getName(), "kotlin.Function" + ((Number) entry.getValue()).intValue());
        }
        f5049f = map3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(F.c(map3.size()));
        for (Map.Entry entry2 : map3.entrySet()) {
            linkedHashMap.put(entry2.getKey(), f1.o.d0((String) entry2.getValue(), '.', null, 2, null));
        }
        f5050g = linkedHashMap;
    }

    public f(Class jClass) {
        n.g(jClass, "jClass");
        this.f5051a = jClass;
    }

    @Override // kotlin.jvm.internal.e
    public Class b() {
        return this.f5051a;
    }

    @Override // d1.InterfaceC0324c
    public String c() {
        return f5045b.a(b());
    }

    public boolean equals(Object obj) {
        return (obj instanceof f) && n.c(U0.a.b(this), U0.a.b((InterfaceC0324c) obj));
    }

    public int hashCode() {
        return U0.a.b(this).hashCode();
    }

    public String toString() {
        return b().toString() + " (Kotlin reflection is not available)";
    }
}
