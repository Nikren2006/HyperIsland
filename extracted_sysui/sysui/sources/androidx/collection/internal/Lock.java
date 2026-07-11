package androidx.collection.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class Lock {
    public final <T> T synchronizedImpl(Function0 block) {
        T t2;
        n.g(block, "block");
        synchronized (this) {
            try {
                t2 = (T) block.invoke();
                m.b(1);
            } catch (Throwable th) {
                m.b(1);
                m.a(1);
                throw th;
            }
        }
        m.a(1);
        return t2;
    }
}
