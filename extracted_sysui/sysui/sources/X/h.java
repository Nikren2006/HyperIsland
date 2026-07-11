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

/* JADX INFO: loaded from: classes2.dex */
public final class h extends q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r f859b = f(o.f755b);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f860a;

    public class a implements r {
        public a() {
        }

        @Override // U.r
        public q a(U.d dVar, C0223a c0223a) {
            if (c0223a.getRawType() == Number.class) {
                return h.this;
            }
            return null;
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f862a;

        static {
            int[] iArr = new int[EnumC0227b.values().length];
            f862a = iArr;
            try {
                iArr[EnumC0227b.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f862a[EnumC0227b.NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f862a[EnumC0227b.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public h(p pVar) {
        this.f860a = pVar;
    }

    public static r e(p pVar) {
        return pVar == o.f755b ? f859b : f(pVar);
    }

    public static r f(p pVar) {
        return new h(pVar).new a();
    }

    @Override // U.q
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public Number b(C0226a c0226a) throws IOException {
        EnumC0227b enumC0227bM = c0226a.M();
        int i2 = b.f862a[enumC0227bM.ordinal()];
        if (i2 == 1) {
            c0226a.I();
            return null;
        }
        if (i2 == 2 || i2 == 3) {
            return this.f860a.a(c0226a);
        }
        throw new U.l("Expecting number, got: " + enumC0227bM + "; at path " + c0226a.t());
    }

    @Override // U.q
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public void d(C0228c c0228c, Number number) throws IOException {
        c0228c.N(number);
    }
}
