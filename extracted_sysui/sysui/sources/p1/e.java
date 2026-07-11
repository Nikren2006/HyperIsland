package p1;

import l1.F;
import l1.I;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f6406a = I.e("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final F f6407b = new F("PERMIT");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final F f6408c = new F("TAKEN");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final F f6409d = new F("BROKEN");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final F f6410e = new F("CANCELLED");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f6411f = I.e("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12, null);

    public static final f h(long j2, f fVar) {
        return new f(j2, fVar, 0);
    }
}
