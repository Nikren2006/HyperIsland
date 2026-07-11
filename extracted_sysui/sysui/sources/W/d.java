package W;

import U.q;
import U.r;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class d implements r, Cloneable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final d f783g = new d();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f787d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f784a = -1.0d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f785b = 136;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f786c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List f788e = Collections.emptyList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List f789f = Collections.emptyList();

    public class a extends q {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public q f790a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f791b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f792c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ U.d f793d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ C0223a f794e;

        public a(boolean z2, boolean z3, U.d dVar, C0223a c0223a) {
            this.f791b = z2;
            this.f792c = z3;
            this.f793d = dVar;
            this.f794e = c0223a;
        }

        @Override // U.q
        public Object b(C0226a c0226a) throws IOException {
            if (!this.f791b) {
                return e().b(c0226a);
            }
            c0226a.W();
            return null;
        }

        @Override // U.q
        public void d(C0228c c0228c, Object obj) throws IOException {
            if (this.f792c) {
                c0228c.A();
            } else {
                e().d(c0228c, obj);
            }
        }

        public final q e() {
            q qVar = this.f790a;
            if (qVar != null) {
                return qVar;
            }
            q qVarN = this.f793d.n(d.this, this.f794e);
            this.f790a = qVarN;
            return qVarN;
        }
    }

    @Override // U.r
    public q a(U.d dVar, C0223a c0223a) {
        Class<Object> rawType = c0223a.getRawType();
        boolean zD = d(rawType);
        boolean z2 = zD || e(rawType, true);
        boolean z3 = zD || e(rawType, false);
        if (z2 || z3) {
            return new a(z3, z2, dVar, c0223a);
        }
        return null;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public d clone() {
        try {
            return (d) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean c(Class cls, boolean z2) {
        return d(cls) || e(cls, z2);
    }

    public final boolean d(Class cls) {
        if (this.f784a != -1.0d && !l((V.d) cls.getAnnotation(V.d.class), (V.e) cls.getAnnotation(V.e.class))) {
            return true;
        }
        if (this.f786c || !h(cls)) {
            return g(cls);
        }
        return true;
    }

    public final boolean e(Class cls, boolean z2) {
        Iterator it = (z2 ? this.f788e : this.f789f).iterator();
        if (!it.hasNext()) {
            return false;
        }
        android.support.v4.media.a.a(it.next());
        throw null;
    }

    public boolean f(Field field, boolean z2) {
        V.a aVar;
        if ((this.f785b & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f784a != -1.0d && !l((V.d) field.getAnnotation(V.d.class), (V.e) field.getAnnotation(V.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f787d && ((aVar = (V.a) field.getAnnotation(V.a.class)) == null || (!z2 ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.f786c && h(field.getType())) || g(field.getType())) {
            return true;
        }
        List list = z2 ? this.f788e : this.f789f;
        if (list.isEmpty()) {
            return false;
        }
        new U.a(field);
        Iterator it = list.iterator();
        if (!it.hasNext()) {
            return false;
        }
        android.support.v4.media.a.a(it.next());
        throw null;
    }

    public final boolean g(Class cls) {
        return (Enum.class.isAssignableFrom(cls) || i(cls) || (!cls.isAnonymousClass() && !cls.isLocalClass())) ? false : true;
    }

    public final boolean h(Class cls) {
        return cls.isMemberClass() && !i(cls);
    }

    public final boolean i(Class cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    public final boolean j(V.d dVar) {
        if (dVar != null) {
            return this.f784a >= dVar.value();
        }
        return true;
    }

    public final boolean k(V.e eVar) {
        if (eVar != null) {
            return this.f784a < eVar.value();
        }
        return true;
    }

    public final boolean l(V.d dVar, V.e eVar) {
        return j(dVar) && k(eVar);
    }
}
