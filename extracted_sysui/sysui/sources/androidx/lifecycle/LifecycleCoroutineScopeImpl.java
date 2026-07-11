package androidx.lifecycle;

import H0.k;
import H0.s;
import L0.g;
import N0.l;
import androidx.lifecycle.Lifecycle;
import g1.AbstractC0369g;
import g1.E;
import g1.Q;
import g1.r0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class LifecycleCoroutineScopeImpl extends LifecycleCoroutineScope implements LifecycleEventObserver {
    private final g coroutineContext;
    private final Lifecycle lifecycle;

    /* JADX INFO: renamed from: androidx.lifecycle.LifecycleCoroutineScopeImpl$register$1, reason: invalid class name */
    @N0.f(c = "androidx.lifecycle.LifecycleCoroutineScopeImpl$register$1", f = "Lifecycle.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            AnonymousClass1 anonymousClass1 = LifecycleCoroutineScopeImpl.this.new AnonymousClass1(dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            E e2 = (E) this.L$0;
            if (LifecycleCoroutineScopeImpl.this.getLifecycle$lifecycle_common().getCurrentState().compareTo(Lifecycle.State.INITIALIZED) >= 0) {
                LifecycleCoroutineScopeImpl.this.getLifecycle$lifecycle_common().addObserver(LifecycleCoroutineScopeImpl.this);
            } else {
                r0.d(e2.getCoroutineContext(), null, 1, null);
            }
            return s.f314a;
        }
    }

    public LifecycleCoroutineScopeImpl(Lifecycle lifecycle, g coroutineContext) {
        n.g(lifecycle, "lifecycle");
        n.g(coroutineContext, "coroutineContext");
        this.lifecycle = lifecycle;
        this.coroutineContext = coroutineContext;
        if (getLifecycle$lifecycle_common().getCurrentState() == Lifecycle.State.DESTROYED) {
            r0.d(getCoroutineContext(), null, 1, null);
        }
    }

    @Override // androidx.lifecycle.LifecycleCoroutineScope, g1.E
    public g getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // androidx.lifecycle.LifecycleCoroutineScope
    public Lifecycle getLifecycle$lifecycle_common() {
        return this.lifecycle;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        n.g(source, "source");
        n.g(event, "event");
        if (getLifecycle$lifecycle_common().getCurrentState().compareTo(Lifecycle.State.DESTROYED) <= 0) {
            getLifecycle$lifecycle_common().removeObserver(this);
            r0.d(getCoroutineContext(), null, 1, null);
        }
    }

    public final void register() {
        AbstractC0369g.b(this, Q.c().z(), null, new AnonymousClass1(null), 2, null);
    }
}
