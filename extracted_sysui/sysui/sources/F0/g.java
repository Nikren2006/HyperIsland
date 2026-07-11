package F0;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f175a;

    public g(int i2) {
        this.f175a = b.b(i2);
    }

    public static g b(int i2) {
        return new g(i2);
    }

    public Map a() {
        return this.f175a.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.f175a);
    }

    public g c(Object obj, Object obj2) {
        this.f175a.put(obj, obj2);
        return this;
    }
}
