package i1;

import androidx.core.location.LocationRequestCompat;
import d1.InterfaceC0326e;
import g1.InterfaceC0377k;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import l1.F;
import l1.I;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f4580a = new i(-1, null, null, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f4581b = I.e("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 0, 0, 12, null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f4582c = I.e("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 0, 0, 12, null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final F f4583d = new F("BUFFERED");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final F f4584e = new F("SHOULD_BUFFER");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final F f4585f = new F("S_RESUMING_BY_RCV");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final F f4586g = new F("RESUMING_BY_EB");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final F f4587h = new F("POISONED");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final F f4588i = new F("DONE_RCV");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final F f4589j = new F("INTERRUPTED_SEND");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final F f4590k = new F("INTERRUPTED_RCV");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final F f4591l = new F("CHANNEL_CLOSED");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final F f4592m = new F("SUSPEND");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final F f4593n = new F("SUSPEND_NO_WAITER");

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final F f4594o = new F("FAILED");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final F f4595p = new F("NO_RECEIVE_RESULT");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final F f4596q = new F("CLOSE_HANDLER_CLOSED");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final F f4597r = new F("CLOSE_HANDLER_INVOKED");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final F f4598s = new F("NO_CLOSE_CAUSE");

    public /* synthetic */ class a extends kotlin.jvm.internal.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f4599a = new a();

        public a() {
            super(2, c.class, "createSegment", "createSegment(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", 1);
        }

        public final i b(long j2, i iVar) {
            return c.x(j2, iVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return b(((Number) obj).longValue(), (i) obj2);
        }
    }

    public static final long A(int i2) {
        if (i2 != 0) {
            return i2 != Integer.MAX_VALUE ? i2 : LocationRequestCompat.PASSIVE_INTERVAL;
        }
        return 0L;
    }

    public static final boolean B(InterfaceC0377k interfaceC0377k, Object obj, Function1 function1) {
        Object objM = interfaceC0377k.m(obj, null, function1);
        if (objM == null) {
            return false;
        }
        interfaceC0377k.t(objM);
        return true;
    }

    public static /* synthetic */ boolean C(InterfaceC0377k interfaceC0377k, Object obj, Function1 function1, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        return B(interfaceC0377k, obj, function1);
    }

    public static final long v(long j2, boolean z2) {
        return (z2 ? 4611686018427387904L : 0L) + j2;
    }

    public static final long w(long j2, int i2) {
        return (((long) i2) << 60) + j2;
    }

    public static final i x(long j2, i iVar) {
        return new i(j2, iVar, iVar.u(), 0);
    }

    public static final InterfaceC0326e y() {
        return a.f4599a;
    }

    public static final F z() {
        return f4591l;
    }
}
