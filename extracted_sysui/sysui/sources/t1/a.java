package t1;

import kotlin.jvm.internal.n;
import t1.b;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a implements d, b {
    @Override // t1.b
    public final float c(s1.c descriptor, int i2) {
        n.g(descriptor, "descriptor");
        return f();
    }

    @Override // t1.b
    public boolean d() {
        return b.a.b(this);
    }

    @Override // t1.b
    public int e(s1.c cVar) {
        return b.a.a(this, cVar);
    }

    @Override // t1.d
    public abstract float f();

    @Override // t1.b
    public Object i(s1.c descriptor, int i2, q1.a deserializer, Object obj) {
        n.g(descriptor, "descriptor");
        n.g(deserializer, "deserializer");
        return k(deserializer, obj);
    }

    public abstract Object j(q1.a aVar);

    public Object k(q1.a deserializer, Object obj) {
        n.g(deserializer, "deserializer");
        return j(deserializer);
    }
}
