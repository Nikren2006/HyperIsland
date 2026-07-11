package w1;

import I0.G;
import I0.u;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import w1.e;

/* JADX INFO: loaded from: classes2.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e.a f6993a = new e.a();

    public /* synthetic */ class a extends kotlin.jvm.internal.l implements Function0 {
        public a(Object obj) {
            super(0, obj, k.class, "buildAlternativeNamesMap", "buildAlternativeNamesMap(Lkotlinx/serialization/descriptors/SerialDescriptor;)Ljava/util/Map;", 1);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Map invoke() {
            return k.a((s1.c) this.receiver);
        }
    }

    public static final Map a(s1.c cVar) {
        String[] strArrNames;
        kotlin.jvm.internal.n.g(cVar, "<this>");
        int iD = cVar.d();
        Map mapA = null;
        for (int i2 = 0; i2 < iD; i2++) {
            List listF = cVar.f(i2);
            ArrayList arrayList = new ArrayList();
            for (Object obj : listF) {
                if (obj instanceof v1.d) {
                    arrayList.add(obj);
                }
            }
            v1.d dVar = (v1.d) u.f0(arrayList);
            if (dVar != null && (strArrNames = dVar.names()) != null) {
                for (String str : strArrNames) {
                    if (mapA == null) {
                        mapA = d.a(cVar.d());
                    }
                    kotlin.jvm.internal.n.d(mapA);
                    b(mapA, cVar, str, i2);
                }
            }
        }
        return mapA == null ? G.f() : mapA;
    }

    public static final void b(Map map, s1.c cVar, String str, int i2) {
        if (!map.containsKey(str)) {
            map.put(str, Integer.valueOf(i2));
            return;
        }
        throw new i("The suggested name '" + str + "' for property " + cVar.e(i2) + " is already one of the names for property " + cVar.e(((Number) G.g(map, str)).intValue()) + " in " + cVar);
    }

    public static final int c(s1.c cVar, v1.a json, String name) {
        kotlin.jvm.internal.n.g(cVar, "<this>");
        kotlin.jvm.internal.n.g(json, "json");
        kotlin.jvm.internal.n.g(name, "name");
        int iB = cVar.b(name);
        if (iB != -3 || !json.b().f()) {
            return iB;
        }
        Integer num = (Integer) ((Map) v1.e.a(json).b(cVar, f6993a, new a(cVar))).get(name);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }
}
