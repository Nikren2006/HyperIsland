package androidx.window.layout;

import H0.s;
import L0.d;
import N0.f;
import N0.l;
import android.app.Activity;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class WindowInfoTrackerImpl implements WindowInfoTracker {
    private static final int BUFFER_CAPACITY = 10;
    public static final Companion Companion = new Companion(null);
    private final WindowBackend windowBackend;
    private final WindowMetricsCalculator windowMetricsCalculator;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$1, reason: invalid class name */
    @f(c = "androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$1", f = "WindowInfoTrackerImpl.kt", l = {54, 55}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ Activity $activity;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Activity activity, d dVar) {
            super(2, dVar);
            this.$activity = activity;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeSuspend$lambda-0, reason: not valid java name */
        public static final void m55invokeSuspend$lambda0(i1.d dVar, WindowLayoutInfo info) {
            n.f(info, "info");
            dVar.j(info);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            AnonymousClass1 anonymousClass1 = WindowInfoTrackerImpl.this.new AnonymousClass1(this.$activity, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(InterfaceC0419g interfaceC0419g, d dVar) {
            return ((AnonymousClass1) create(interfaceC0419g, dVar)).invokeSuspend(s.f314a);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0072 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x007e A[Catch: all -> 0x001f, TRY_LEAVE, TryCatch #0 {all -> 0x001f, blocks: (B:7:0x001a, B:18:0x0064, B:22:0x0076, B:24:0x007e, B:14:0x0036, B:17:0x0060), top: B:31:0x0008 }] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0093  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0090 -> B:8:0x001d). Please report as a decompilation issue!!! */
        @Override // N0.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
            /*
                r9 = this;
                java.lang.Object r0 = M0.c.c()
                int r1 = r9.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L3a
                if (r1 == r3) goto L2a
                if (r1 != r2) goto L22
                java.lang.Object r1 = r9.L$2
                i1.f r1 = (i1.f) r1
                java.lang.Object r4 = r9.L$1
                androidx.core.util.Consumer r4 = (androidx.core.util.Consumer) r4
                java.lang.Object r5 = r9.L$0
                j1.g r5 = (j1.InterfaceC0419g) r5
                H0.k.b(r10)     // Catch: java.lang.Throwable -> L1f
            L1d:
                r10 = r5
                goto L64
            L1f:
                r10 = move-exception
                goto L9f
            L22:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L2a:
                java.lang.Object r1 = r9.L$2
                i1.f r1 = (i1.f) r1
                java.lang.Object r4 = r9.L$1
                androidx.core.util.Consumer r4 = (androidx.core.util.Consumer) r4
                java.lang.Object r5 = r9.L$0
                j1.g r5 = (j1.InterfaceC0419g) r5
                H0.k.b(r10)     // Catch: java.lang.Throwable -> L1f
                goto L76
            L3a:
                H0.k.b(r10)
                java.lang.Object r10 = r9.L$0
                j1.g r10 = (j1.InterfaceC0419g) r10
                i1.a r1 = i1.a.DROP_OLDEST
                r4 = 4
                r5 = 10
                r6 = 0
                i1.d r1 = i1.g.b(r5, r1, r6, r4, r6)
                androidx.window.layout.b r4 = new androidx.window.layout.b
                r4.<init>()
                androidx.window.layout.WindowInfoTrackerImpl r5 = androidx.window.layout.WindowInfoTrackerImpl.this
                androidx.window.layout.WindowBackend r5 = androidx.window.layout.WindowInfoTrackerImpl.access$getWindowBackend$p(r5)
                android.app.Activity r6 = r9.$activity
                androidx.profileinstaller.b r7 = new androidx.profileinstaller.b
                r7.<init>()
                r5.registerLayoutChangeCallback(r6, r7, r4)
                i1.f r1 = r1.iterator()     // Catch: java.lang.Throwable -> L1f
            L64:
                r9.L$0 = r10     // Catch: java.lang.Throwable -> L1f
                r9.L$1 = r4     // Catch: java.lang.Throwable -> L1f
                r9.L$2 = r1     // Catch: java.lang.Throwable -> L1f
                r9.label = r3     // Catch: java.lang.Throwable -> L1f
                java.lang.Object r5 = r1.a(r9)     // Catch: java.lang.Throwable -> L1f
                if (r5 != r0) goto L73
                return r0
            L73:
                r8 = r5
                r5 = r10
                r10 = r8
            L76:
                java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> L1f
                boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> L1f
                if (r10 == 0) goto L93
                java.lang.Object r10 = r1.next()     // Catch: java.lang.Throwable -> L1f
                androidx.window.layout.WindowLayoutInfo r10 = (androidx.window.layout.WindowLayoutInfo) r10     // Catch: java.lang.Throwable -> L1f
                r9.L$0 = r5     // Catch: java.lang.Throwable -> L1f
                r9.L$1 = r4     // Catch: java.lang.Throwable -> L1f
                r9.L$2 = r1     // Catch: java.lang.Throwable -> L1f
                r9.label = r2     // Catch: java.lang.Throwable -> L1f
                java.lang.Object r10 = r5.emit(r10, r9)     // Catch: java.lang.Throwable -> L1f
                if (r10 != r0) goto L1d
                return r0
            L93:
                androidx.window.layout.WindowInfoTrackerImpl r9 = androidx.window.layout.WindowInfoTrackerImpl.this
                androidx.window.layout.WindowBackend r9 = androidx.window.layout.WindowInfoTrackerImpl.access$getWindowBackend$p(r9)
                r9.unregisterLayoutChangeCallback(r4)
                H0.s r9 = H0.s.f314a
                return r9
            L9f:
                androidx.window.layout.WindowInfoTrackerImpl r9 = androidx.window.layout.WindowInfoTrackerImpl.this
                androidx.window.layout.WindowBackend r9 = androidx.window.layout.WindowInfoTrackerImpl.access$getWindowBackend$p(r9)
                r9.unregisterLayoutChangeCallback(r4)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.WindowInfoTrackerImpl.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public WindowInfoTrackerImpl(WindowMetricsCalculator windowMetricsCalculator, WindowBackend windowBackend) {
        n.g(windowMetricsCalculator, "windowMetricsCalculator");
        n.g(windowBackend, "windowBackend");
        this.windowMetricsCalculator = windowMetricsCalculator;
        this.windowBackend = windowBackend;
    }

    @Override // androidx.window.layout.WindowInfoTracker
    public InterfaceC0418f windowLayoutInfo(Activity activity) {
        n.g(activity, "activity");
        return AbstractC0420h.u(new AnonymousClass1(activity, null));
    }
}
