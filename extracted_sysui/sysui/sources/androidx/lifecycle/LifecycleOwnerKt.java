package androidx.lifecycle;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class LifecycleOwnerKt {
    public static final LifecycleCoroutineScope getLifecycleScope(LifecycleOwner lifecycleOwner) {
        n.g(lifecycleOwner, "<this>");
        return LifecycleKt.getCoroutineScope(lifecycleOwner.getLifecycle());
    }
}
