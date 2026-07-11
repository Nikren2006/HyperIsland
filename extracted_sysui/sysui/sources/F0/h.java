package F0;

import F0.a;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class h extends F0.a implements E0.a {

    public static final class b extends a.AbstractC0008a {
        public h b() {
            return new h(this.f168a);
        }

        public b c(Object obj, G0.a aVar) {
            super.a(obj, aVar);
            return this;
        }

        public b(int i2) {
            super(i2);
        }
    }

    public static b b(int i2) {
        return new b(i2);
    }

    @Override // G0.a
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Map get() {
        return a();
    }

    public h(Map map) {
        super(map);
    }
}
