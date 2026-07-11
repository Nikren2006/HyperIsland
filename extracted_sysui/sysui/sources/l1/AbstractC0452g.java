package l1;

import e1.AbstractC0345j;
import java.util.Collection;
import java.util.ServiceLoader;

/* JADX INFO: renamed from: l1.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0452g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Collection f5219a = e1.l.r(AbstractC0345j.c(ServiceLoader.load(g1.C.class, g1.C.class.getClassLoader()).iterator()));

    public static final Collection a() {
        return f5219a;
    }

    public static final void b(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }
}
