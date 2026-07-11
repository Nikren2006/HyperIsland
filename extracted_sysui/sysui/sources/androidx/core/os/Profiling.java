package androidx.core.os;

import H0.k;
import H0.s;
import N0.l;
import android.content.Context;
import android.os.ProfilingManager;
import android.os.ProfilingResult;
import androidx.annotation.RequiresApi;
import androidx.core.os.Profiling;
import i1.q;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class Profiling {
    private static final String KEY_BUFFER_FILL_POLICY = "KEY_BUFFER_FILL_POLICY";
    private static final String KEY_DURATION_MS = "KEY_DURATION_MS";
    private static final String KEY_FREQUENCY_HZ = "KEY_FREQUENCY_HZ";
    private static final String KEY_SAMPLING_INTERVAL_BYTES = "KEY_SAMPLING_INTERVAL_BYTES";
    private static final String KEY_SIZE_KB = "KEY_SIZE_KB";
    private static final String KEY_TRACK_JAVA_ALLOCATIONS = "KEY_TRACK_JAVA_ALLOCATIONS";
    private static final int VALUE_BUFFER_FILL_POLICY_DISCARD = 1;
    private static final int VALUE_BUFFER_FILL_POLICY_RING_BUFFER = 2;

    /* JADX INFO: renamed from: androidx.core.os.Profiling$registerForAllProfilingResults$1, reason: invalid class name */
    @N0.f(c = "androidx.core.os.Profiling$registerForAllProfilingResults$1", f = "Profiling.kt", l = {79}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ Context $context;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.core.os.Profiling$registerForAllProfilingResults$1$2, reason: invalid class name */
        public static final class AnonymousClass2 extends o implements Function0 {
            final /* synthetic */ Consumer<ProfilingResult> $listener;
            final /* synthetic */ ProfilingManager $service;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(ProfilingManager profilingManager, Consumer<ProfilingResult> consumer) {
                super(0);
                this.$service = profilingManager;
                this.$listener = consumer;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m40invoke();
                return s.f314a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m40invoke() {
                this.$service.unregisterForAllProfilingResults(this.$listener);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Context context, L0.d dVar) {
            super(2, dVar);
            this.$context = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(q qVar, ProfilingResult result) {
            n.f(result, "result");
            qVar.j(result);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$context, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(q qVar, L0.d dVar) {
            return ((AnonymousClass1) create(qVar, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                final q qVar = (q) this.L$0;
                Consumer consumer = new Consumer() { // from class: androidx.core.os.f
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        Profiling.AnonymousClass1.invokeSuspend$lambda$0(qVar, (ProfilingResult) obj2);
                    }
                };
                ProfilingManager profilingManagerA = b.a(this.$context.getSystemService(a.a()));
                profilingManagerA.registerForAllProfilingResults(new Executor() { // from class: androidx.core.os.g
                    @Override // java.util.concurrent.Executor
                    public final void execute(Runnable runnable) {
                        runnable.run();
                    }
                }, consumer);
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(profilingManagerA, consumer);
                this.label = 1;
                if (i1.o.a(qVar, anonymousClass2, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            return s.f314a;
        }
    }

    @RequiresApi(api = 35)
    public static final InterfaceC0418f registerForAllProfilingResults(Context context) {
        n.g(context, "context");
        return AbstractC0420h.e(new AnonymousClass1(context, null));
    }

    @RequiresApi(api = 35)
    public static final void requestProfiling(Context context, ProfilingRequest profilingRequest, Executor executor, Consumer<ProfilingResult> consumer) {
        n.g(context, "context");
        n.g(profilingRequest, "profilingRequest");
        b.a(context.getSystemService(a.a())).requestProfiling(profilingRequest.getProfilingType(), profilingRequest.getParams(), profilingRequest.getTag(), profilingRequest.getCancellationSignal(), executor, consumer);
    }

    @RequiresApi(api = 35)
    public static final void unregisterForAllProfilingResults(Context context, Consumer<ProfilingResult> listener) {
        n.g(context, "context");
        n.g(listener, "listener");
        b.a(context.getSystemService(a.a())).unregisterForAllProfilingResults(listener);
    }

    @RequiresApi(api = 35)
    public static final void registerForAllProfilingResults(Context context, Executor executor, Consumer<ProfilingResult> listener) {
        n.g(context, "context");
        n.g(executor, "executor");
        n.g(listener, "listener");
        b.a(context.getSystemService(a.a())).registerForAllProfilingResults(executor, listener);
    }
}
