package androidx.lifecycle;

import H0.s;
import L0.h;
import g1.B;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$2 extends o implements Function1 {
    final /* synthetic */ B $lifecycleDispatcher;
    final /* synthetic */ WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1 $observer;
    final /* synthetic */ Lifecycle $this_suspendWithStateAtLeastUnchecked;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$2(B b2, Lifecycle lifecycle, WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1 withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1) {
        super(1);
        this.$lifecycleDispatcher = b2;
        this.$this_suspendWithStateAtLeastUnchecked = lifecycle;
        this.$observer = withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return s.f314a;
    }

    public final void invoke(Throwable th) {
        B b2 = this.$lifecycleDispatcher;
        h hVar = h.f402a;
        if (!b2.isDispatchNeeded(hVar)) {
            this.$this_suspendWithStateAtLeastUnchecked.removeObserver(this.$observer);
            return;
        }
        B b3 = this.$lifecycleDispatcher;
        final Lifecycle lifecycle = this.$this_suspendWithStateAtLeastUnchecked;
        final WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1 withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1 = this.$observer;
        b3.dispatch(hVar, new Runnable() { // from class: androidx.lifecycle.WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$2.1
            @Override // java.lang.Runnable
            public final void run() {
                lifecycle.removeObserver(withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1);
            }
        });
    }
}
