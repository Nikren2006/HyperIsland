package X;

import U.q;
import U.r;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import c0.EnumC0227b;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class g implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final W.c f853a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f854b;

    public final class a extends q {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q f855a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final q f856b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final W.i f857c;

        public a(U.d dVar, Type type, q qVar, Type type2, q qVar2, W.i iVar) {
            this.f855a = new l(dVar, qVar, type);
            this.f856b = new l(dVar, qVar2, type2);
            this.f857c = iVar;
        }

        public final String e(U.f fVar) {
            if (!fVar.g()) {
                if (fVar.e()) {
                    return "null";
                }
                throw new AssertionError();
            }
            U.k kVarC = fVar.c();
            if (kVarC.m()) {
                return String.valueOf(kVarC.i());
            }
            if (kVarC.k()) {
                return Boolean.toString(kVarC.h());
            }
            if (kVarC.n()) {
                return kVarC.j();
            }
            throw new AssertionError();
        }

        @Override // U.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Map b(C0226a c0226a) throws IOException {
            EnumC0227b enumC0227bM = c0226a.M();
            if (enumC0227bM == EnumC0227b.NULL) {
                c0226a.I();
                return null;
            }
            Map map = (Map) this.f857c.a();
            if (enumC0227bM == EnumC0227b.BEGIN_ARRAY) {
                c0226a.a();
                while (c0226a.x()) {
                    c0226a.a();
                    Object objB = this.f855a.b(c0226a);
                    if (map.put(objB, this.f856b.b(c0226a)) != null) {
                        throw new U.l("duplicate key: " + objB);
                    }
                    c0226a.l();
                }
                c0226a.l();
            } else {
                c0226a.c();
                while (c0226a.x()) {
                    W.f.f797a.a(c0226a);
                    Object objB2 = this.f855a.b(c0226a);
                    if (map.put(objB2, this.f856b.b(c0226a)) != null) {
                        throw new U.l("duplicate key: " + objB2);
                    }
                }
                c0226a.n();
            }
            return map;
        }

        @Override // U.q
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void d(C0228c c0228c, Map map) throws IOException {
            if (map == null) {
                c0228c.A();
                return;
            }
            if (!g.this.f854b) {
                c0228c.e();
                for (Map.Entry entry : map.entrySet()) {
                    c0228c.x(String.valueOf(entry.getKey()));
                    this.f856b.d(c0228c, entry.getValue());
                }
                c0228c.n();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i2 = 0;
            boolean z2 = false;
            for (Map.Entry entry2 : map.entrySet()) {
                U.f fVarC = this.f855a.c(entry2.getKey());
                arrayList.add(fVarC);
                arrayList2.add(entry2.getValue());
                z2 |= fVarC.d() || fVarC.f();
            }
            if (!z2) {
                c0228c.e();
                int size = arrayList.size();
                while (i2 < size) {
                    c0228c.x(e((U.f) arrayList.get(i2)));
                    this.f856b.d(c0228c, arrayList2.get(i2));
                    i2++;
                }
                c0228c.n();
                return;
            }
            c0228c.d();
            int size2 = arrayList.size();
            while (i2 < size2) {
                c0228c.d();
                W.m.a((U.f) arrayList.get(i2), c0228c);
                this.f856b.d(c0228c, arrayList2.get(i2));
                c0228c.l();
                i2++;
            }
            c0228c.l();
        }
    }

    public g(W.c cVar, boolean z2) {
        this.f853a = cVar;
        this.f854b = z2;
    }

    @Override // U.r
    public q a(U.d dVar, C0223a c0223a) {
        Type type = c0223a.getType();
        Class<Object> rawType = c0223a.getRawType();
        if (!Map.class.isAssignableFrom(rawType)) {
            return null;
        }
        Type[] typeArrJ = W.b.j(type, rawType);
        return new a(dVar, typeArrJ[0], b(dVar, typeArrJ[0]), typeArrJ[1], dVar.l(C0223a.get(typeArrJ[1])), this.f853a.b(c0223a));
    }

    public final q b(U.d dVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? m.f925f : dVar.l(C0223a.get(type));
    }
}
