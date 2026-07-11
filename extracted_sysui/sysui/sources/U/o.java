package U;

import c0.C0226a;
import java.io.IOException;
import java.math.BigDecimal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public abstract class o implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f754a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final o f755b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o f756c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final o f757d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ o[] f758e;

    public enum a extends o {
        public a(String str, int i2) {
            super(str, i2, null);
        }

        @Override // U.p
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Double a(C0226a c0226a) {
            return Double.valueOf(c0226a.D());
        }
    }

    static {
        a aVar = new a("DOUBLE", 0);
        f754a = aVar;
        o oVar = new o("LAZILY_PARSED_NUMBER", 1) { // from class: U.o.b
            {
                a aVar2 = null;
            }

            @Override // U.p
            public Number a(C0226a c0226a) {
                return new W.g(c0226a.K());
            }
        };
        f755b = oVar;
        o oVar2 = new o("LONG_OR_DOUBLE", 2) { // from class: U.o.c
            {
                a aVar2 = null;
            }

            @Override // U.p
            public Number a(C0226a c0226a) throws IOException {
                String strK = c0226a.K();
                try {
                    try {
                        return Long.valueOf(Long.parseLong(strK));
                    } catch (NumberFormatException unused) {
                        Double dValueOf = Double.valueOf(strK);
                        if (dValueOf.isInfinite() || dValueOf.isNaN()) {
                            if (!c0226a.z()) {
                                throw new c0.d("JSON forbids NaN and infinities: " + dValueOf + "; at path " + c0226a.w());
                            }
                        }
                        return dValueOf;
                    }
                } catch (NumberFormatException e2) {
                    throw new j("Cannot parse " + strK + "; at path " + c0226a.w(), e2);
                }
            }
        };
        f756c = oVar2;
        o oVar3 = new o("BIG_DECIMAL", 3) { // from class: U.o.d
            {
                a aVar2 = null;
            }

            @Override // U.p
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public BigDecimal a(C0226a c0226a) throws IOException {
                String strK = c0226a.K();
                try {
                    return new BigDecimal(strK);
                } catch (NumberFormatException e2) {
                    throw new j("Cannot parse " + strK + "; at path " + c0226a.w(), e2);
                }
            }
        };
        f757d = oVar3;
        f758e = new o[]{aVar, oVar, oVar2, oVar3};
    }

    public o(String str, int i2) {
    }

    public static o valueOf(String str) {
        return (o) Enum.valueOf(o.class, str);
    }

    public static o[] values() {
        return (o[]) f758e.clone();
    }

    public /* synthetic */ o(String str, int i2, a aVar) {
        this(str, i2);
    }
}
