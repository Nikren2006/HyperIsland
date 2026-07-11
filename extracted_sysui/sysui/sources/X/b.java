package X;

import U.q;
import U.r;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import c0.EnumC0227b;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class b implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final W.c f840a;

    public static final class a extends q {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q f841a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final W.i f842b;

        public a(U.d dVar, Type type, q qVar, W.i iVar) {
            this.f841a = new l(dVar, qVar, type);
            this.f842b = iVar;
        }

        @Override // U.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Collection b(C0226a c0226a) throws IOException {
            if (c0226a.M() == EnumC0227b.NULL) {
                c0226a.I();
                return null;
            }
            Collection collection = (Collection) this.f842b.a();
            c0226a.a();
            while (c0226a.x()) {
                collection.add(this.f841a.b(c0226a));
            }
            c0226a.l();
            return collection;
        }

        @Override // U.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(C0228c c0228c, Collection collection) throws IOException {
            if (collection == null) {
                c0228c.A();
                return;
            }
            c0228c.d();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                this.f841a.d(c0228c, it.next());
            }
            c0228c.l();
        }
    }

    public b(W.c cVar) {
        this.f840a = cVar;
    }

    @Override // U.r
    public q a(U.d dVar, C0223a c0223a) {
        Type type = c0223a.getType();
        Class<Object> rawType = c0223a.getRawType();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        Type typeH = W.b.h(type, rawType);
        return new a(dVar, typeH, dVar.l(C0223a.get(typeH)), this.f840a.b(c0223a));
    }
}
