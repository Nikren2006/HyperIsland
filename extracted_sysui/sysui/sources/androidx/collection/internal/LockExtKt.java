package androidx.collection.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class LockExtKt {
    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final <T> T m32synchronized(Lock lock, Function0 block) {
        T t2;
        n.g(lock, "<this>");
        n.g(block, "block");
        synchronized (lock) {
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
