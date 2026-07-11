package U;

import c0.C0226a;
import c0.C0228c;
import c0.EnumC0227b;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class q {

    public class a extends q {
        public a() {
        }

        @Override // U.q
        public Object b(C0226a c0226a) throws IOException {
            if (c0226a.M() != EnumC0227b.NULL) {
                return q.this.b(c0226a);
            }
            c0226a.I();
            return null;
        }

        @Override // U.q
        public void d(C0228c c0228c, Object obj) throws IOException {
            if (obj == null) {
                c0228c.A();
            } else {
                q.this.d(c0228c, obj);
            }
        }
    }

    public final q a() {
        return new a();
    }

    public abstract Object b(C0226a c0226a);

    public final f c(Object obj) {
        try {
            X.f fVar = new X.f();
            d(fVar, obj);
            return fVar.R();
        } catch (IOException e2) {
            throw new g(e2);
        }
    }

    public abstract void d(C0228c c0228c, Object obj);
}
