package X;

import U.q;
import X.j;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: classes2.dex */
public final class l extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final U.d f893a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f894b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f895c;

    public l(U.d dVar, q qVar, Type type) {
        this.f893a = dVar;
        this.f894b = qVar;
        this.f895c = type;
    }

    public static Type e(Type type, Object obj) {
        return obj != null ? ((type instanceof Class) || (type instanceof TypeVariable)) ? obj.getClass() : type : type;
    }

    public static boolean f(q qVar) {
        q qVarE;
        while ((qVar instanceof k) && (qVarE = ((k) qVar).e()) != qVar) {
            qVar = qVarE;
        }
        return qVar instanceof j.b;
    }

    @Override // U.q
    public Object b(C0226a c0226a) {
        return this.f894b.b(c0226a);
    }

    @Override // U.q
    public void d(C0228c c0228c, Object obj) {
        q qVarL = this.f894b;
        Type typeE = e(this.f895c, obj);
        if (typeE != this.f895c) {
            qVarL = this.f893a.l(C0223a.get(typeE));
            if ((qVarL instanceof j.b) && !f(this.f894b)) {
                qVarL = this.f894b;
            }
        }
        qVarL.d(c0228c, obj);
    }
}
