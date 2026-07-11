package n1;

import java.util.concurrent.TimeUnit;
import l1.G;
import l1.I;

/* JADX INFO: loaded from: classes2.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f6276a = G.e("kotlinx.coroutines.scheduler.default.name", "DefaultDispatcher");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f6277b = I.f("kotlinx.coroutines.scheduler.resolution.ns", 100000, 0, 0, 12, null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f6278c = I.e("kotlinx.coroutines.scheduler.core.pool.size", c1.f.c(G.a(), 2), 1, 0, 8, null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f6279d = I.e("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4, null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f6280e = TimeUnit.SECONDS.toNanos(I.f("kotlinx.coroutines.scheduler.keep.alive.sec", 60, 0, 0, 12, null));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static g f6281f = e.f6266a;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final i f6282g = new j(0);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final i f6283h = new j(1);
}
