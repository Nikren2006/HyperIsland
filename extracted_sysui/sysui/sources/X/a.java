package X;

import U.q;
import U.r;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import c0.EnumC0227b;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final r f837c = new C0025a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f838a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f839b;

    /* JADX INFO: renamed from: X.a$a, reason: collision with other inner class name */
    public class C0025a implements r {
        @Override // U.r
        public q a(U.d dVar, C0223a c0223a) {
            Type type = c0223a.getType();
            if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class) type).isArray())) {
                return null;
            }
            Type typeG = W.b.g(type);
            return new a(dVar, dVar.l(C0223a.get(typeG)), W.b.k(typeG));
        }
    }

    public a(U.d dVar, q qVar, Class cls) {
        this.f839b = new l(dVar, qVar, cls);
        this.f838a = cls;
    }

    @Override // U.q
    public Object b(C0226a c0226a) throws IOException {
        if (c0226a.M() == EnumC0227b.NULL) {
            c0226a.I();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        c0226a.a();
        while (c0226a.x()) {
            arrayList.add(this.f839b.b(c0226a));
        }
        c0226a.l();
        int size = arrayList.size();
        if (!this.f838a.isPrimitive()) {
            return arrayList.toArray((Object[]) Array.newInstance((Class<?>) this.f838a, size));
        }
        Object objNewInstance = Array.newInstance((Class<?>) this.f838a, size);
        for (int i2 = 0; i2 < size; i2++) {
            Array.set(objNewInstance, i2, arrayList.get(i2));
        }
        return objNewInstance;
    }

    @Override // U.q
    public void d(C0228c c0228c, Object obj) throws IOException {
        if (obj == null) {
            c0228c.A();
            return;
        }
        c0228c.d();
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            this.f839b.d(c0228c, Array.get(obj, i2));
        }
        c0228c.l();
    }
}
