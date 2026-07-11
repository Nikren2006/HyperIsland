package androidx.collection;

import H0.i;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ArrayMapKt {
    public static final <K, V> ArrayMap<K, V> arrayMapOf() {
        return new ArrayMap<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> ArrayMap<K, V> arrayMapOf(i... pairs) {
        n.g(pairs, "pairs");
        ArrayMap<K, V> arrayMap = (ArrayMap<K, V>) new ArrayMap(pairs.length);
        for (i iVar : pairs) {
            arrayMap.put(iVar.d(), iVar.e());
        }
        return arrayMap;
    }
}
