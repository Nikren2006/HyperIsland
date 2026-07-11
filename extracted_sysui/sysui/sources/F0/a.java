package F0;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f167a;

    /* JADX INFO: renamed from: F0.a$a, reason: collision with other inner class name */
    public static abstract class AbstractC0008a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final LinkedHashMap f168a;

        public AbstractC0008a(int i2) {
            this.f168a = b.b(i2);
        }

        public AbstractC0008a a(Object obj, G0.a aVar) {
            this.f168a.put(i.c(obj, "key"), i.c(aVar, "provider"));
            return this;
        }
    }

    public a(Map map) {
        this.f167a = Collections.unmodifiableMap(map);
    }

    public final Map a() {
        return this.f167a;
    }
}
