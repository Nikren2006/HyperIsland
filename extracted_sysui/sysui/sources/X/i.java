package X;

import U.o;
import U.p;
import U.q;
import U.r;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import c0.EnumC0227b;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class i extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final r f863c = f(o.f754a);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final U.d f864a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f865b;

    public class a implements r {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ p f866a;

        public a(p pVar) {
            this.f866a = pVar;
        }

        @Override // U.r
        public q a(U.d dVar, C0223a c0223a) {
            a aVar = null;
            if (c0223a.getRawType() == Object.class) {
                return new i(dVar, this.f866a, aVar);
            }
            return null;
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f867a;

        static {
            int[] iArr = new int[EnumC0227b.values().length];
            f867a = iArr;
            try {
                iArr[EnumC0227b.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f867a[EnumC0227b.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f867a[EnumC0227b.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f867a[EnumC0227b.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f867a[EnumC0227b.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f867a[EnumC0227b.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public /* synthetic */ i(U.d dVar, p pVar, a aVar) {
        this(dVar, pVar);
    }

    public static r e(p pVar) {
        return pVar == o.f754a ? f863c : f(pVar);
    }

    private static r f(p pVar) {
        return new a(pVar);
    }

    @Override // U.q
    public Object b(C0226a c0226a) throws IOException {
        EnumC0227b enumC0227bM = c0226a.M();
        Object objH = h(c0226a, enumC0227bM);
        if (objH == null) {
            return g(c0226a, enumC0227bM);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (c0226a.x()) {
                String strG = objH instanceof Map ? c0226a.G() : null;
                EnumC0227b enumC0227bM2 = c0226a.M();
                Object objH2 = h(c0226a, enumC0227bM2);
                boolean z2 = objH2 != null;
                if (objH2 == null) {
                    objH2 = g(c0226a, enumC0227bM2);
                }
                if (objH instanceof List) {
                    ((List) objH).add(objH2);
                } else {
                    ((Map) objH).put(strG, objH2);
                }
                if (z2) {
                    arrayDeque.addLast(objH);
                    objH = objH2;
                }
            } else {
                if (objH instanceof List) {
                    c0226a.l();
                } else {
                    c0226a.n();
                }
                if (arrayDeque.isEmpty()) {
                    return objH;
                }
                objH = arrayDeque.removeLast();
            }
        }
    }

    @Override // U.q
    public void d(C0228c c0228c, Object obj) throws IOException {
        if (obj == null) {
            c0228c.A();
            return;
        }
        q qVarM = this.f864a.m(obj.getClass());
        if (!(qVarM instanceof i)) {
            qVarM.d(c0228c, obj);
        } else {
            c0228c.e();
            c0228c.n();
        }
    }

    public final Object g(C0226a c0226a, EnumC0227b enumC0227b) throws IOException {
        int i2 = b.f867a[enumC0227b.ordinal()];
        if (i2 == 3) {
            return c0226a.K();
        }
        if (i2 == 4) {
            return this.f865b.a(c0226a);
        }
        if (i2 == 5) {
            return Boolean.valueOf(c0226a.C());
        }
        if (i2 == 6) {
            c0226a.I();
            return null;
        }
        throw new IllegalStateException("Unexpected token: " + enumC0227b);
    }

    public final Object h(C0226a c0226a, EnumC0227b enumC0227b) throws IOException {
        int i2 = b.f867a[enumC0227b.ordinal()];
        if (i2 == 1) {
            c0226a.a();
            return new ArrayList();
        }
        if (i2 != 2) {
            return null;
        }
        c0226a.c();
        return new W.h();
    }

    public i(U.d dVar, p pVar) {
        this.f864a = dVar;
        this.f865b = pVar;
    }
}
