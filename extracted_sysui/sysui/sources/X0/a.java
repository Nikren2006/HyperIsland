package X0;

import java.util.Optional;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static final Object a(Optional optional, Object obj) {
        n.g(optional, "<this>");
        return optional.isPresent() ? optional.get() : obj;
    }
}
