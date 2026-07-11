package I0;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public abstract class J {
    public static Set a(Object obj) {
        Set setSingleton = Collections.singleton(obj);
        kotlin.jvm.internal.n.f(setSingleton, "singleton(...)");
        return setSingleton;
    }
}
