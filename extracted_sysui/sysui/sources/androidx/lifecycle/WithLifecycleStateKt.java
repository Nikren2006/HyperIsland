package androidx.lifecycle;

import H0.j;
import H0.k;
import L0.h;
import androidx.lifecycle.Lifecycle;
import g1.B;
import g1.C0379l;
import g1.InterfaceC0377k;
import g1.Q;
import g1.w0;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class WithLifecycleStateKt {

    /* JADX INFO: renamed from: androidx.lifecycle.WithLifecycleStateKt$withStateAtLeastUnchecked$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        final /* synthetic */ Function0 $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Function0 function0) {
            super(0);
            this.$block = function0;
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function0
        public final R invoke() {
            return this.$block.invoke();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.lifecycle.LifecycleObserver, androidx.lifecycle.WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1] */
    public static final <R> Object suspendWithStateAtLeastUnchecked(final Lifecycle lifecycle, final Lifecycle.State state, boolean z2, B b2, final Function0 function0, L0.d dVar) {
        final C0379l c0379l = new C0379l(M0.b.b(dVar), 1);
        c0379l.A();
        final ?? r12 = new LifecycleEventObserver() { // from class: androidx.lifecycle.WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                Object objA;
                n.g(source, "source");
                n.g(event, "event");
                if (event != Lifecycle.Event.Companion.upTo(state)) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        lifecycle.removeObserver(this);
                        InterfaceC0377k interfaceC0377k = c0379l;
                        j.a aVar = j.f299a;
                        interfaceC0377k.resumeWith(j.a(k.a(new LifecycleDestroyedException())));
                        return;
                    }
                    return;
                }
                lifecycle.removeObserver(this);
                InterfaceC0377k interfaceC0377k2 = c0379l;
                Function0 function02 = function0;
                try {
                    j.a aVar2 = j.f299a;
                    objA = j.a(function02.invoke());
                } catch (Throwable th) {
                    j.a aVar3 = j.f299a;
                    objA = j.a(k.a(th));
                }
                interfaceC0377k2.resumeWith(objA);
            }
        };
        if (z2) {
            b2.dispatch(h.f402a, new Runnable() { // from class: androidx.lifecycle.WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$1
                @Override // java.lang.Runnable
                public final void run() {
                    lifecycle.addObserver(r12);
                }
            });
        } else {
            lifecycle.addObserver(r12);
        }
        c0379l.g(new WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$2(b2, lifecycle, r12));
        Object objX = c0379l.x();
        if (objX == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objX;
    }

    public static final <R> Object withCreated(Lifecycle lifecycle, Function0 function0, L0.d dVar) {
        Lifecycle.State state = Lifecycle.State.CREATED;
        w0 w0VarZ = Q.c().z();
        boolean zIsDispatchNeeded = w0VarZ.isDispatchNeeded(dVar.getContext());
        if (!zIsDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return function0.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, zIsDispatchNeeded, w0VarZ, new AnonymousClass2(function0), dVar);
    }

    private static final <R> Object withCreated$$forInline(Lifecycle lifecycle, Function0 function0, L0.d dVar) {
        Lifecycle.State state = Lifecycle.State.DESTROYED;
        Q.c().z();
        m.c(3);
        throw null;
    }

    public static final <R> Object withResumed(Lifecycle lifecycle, Function0 function0, L0.d dVar) {
        Lifecycle.State state = Lifecycle.State.RESUMED;
        w0 w0VarZ = Q.c().z();
        boolean zIsDispatchNeeded = w0VarZ.isDispatchNeeded(dVar.getContext());
        if (!zIsDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return function0.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, zIsDispatchNeeded, w0VarZ, new AnonymousClass2(function0), dVar);
    }

    private static final <R> Object withResumed$$forInline(Lifecycle lifecycle, Function0 function0, L0.d dVar) {
        Lifecycle.State state = Lifecycle.State.DESTROYED;
        Q.c().z();
        m.c(3);
        throw null;
    }

    public static final <R> Object withStarted(Lifecycle lifecycle, Function0 function0, L0.d dVar) {
        Lifecycle.State state = Lifecycle.State.STARTED;
        w0 w0VarZ = Q.c().z();
        boolean zIsDispatchNeeded = w0VarZ.isDispatchNeeded(dVar.getContext());
        if (!zIsDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return function0.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, zIsDispatchNeeded, w0VarZ, new AnonymousClass2(function0), dVar);
    }

    private static final <R> Object withStarted$$forInline(Lifecycle lifecycle, Function0 function0, L0.d dVar) {
        Lifecycle.State state = Lifecycle.State.DESTROYED;
        Q.c().z();
        m.c(3);
        throw null;
    }

    public static final <R> Object withStateAtLeast(Lifecycle lifecycle, Lifecycle.State state, Function0 function0, L0.d dVar) {
        if (state.compareTo(Lifecycle.State.CREATED) < 0) {
            throw new IllegalArgumentException(("target state must be CREATED or greater, found " + state).toString());
        }
        w0 w0VarZ = Q.c().z();
        boolean zIsDispatchNeeded = w0VarZ.isDispatchNeeded(dVar.getContext());
        if (!zIsDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return function0.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, zIsDispatchNeeded, w0VarZ, new AnonymousClass2(function0), dVar);
    }

    private static final <R> Object withStateAtLeast$$forInline(Lifecycle lifecycle, Lifecycle.State state, Function0 function0, L0.d dVar) {
        if (state.compareTo(Lifecycle.State.CREATED) >= 0) {
            Q.c().z();
            m.c(3);
            throw null;
        }
        throw new IllegalArgumentException(("target state must be CREATED or greater, found " + state).toString());
    }

    public static final <R> Object withStateAtLeastUnchecked(Lifecycle lifecycle, Lifecycle.State state, Function0 function0, L0.d dVar) {
        w0 w0VarZ = Q.c().z();
        boolean zIsDispatchNeeded = w0VarZ.isDispatchNeeded(dVar.getContext());
        if (!zIsDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return function0.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, zIsDispatchNeeded, w0VarZ, new AnonymousClass2(function0), dVar);
    }

    private static final <R> Object withStateAtLeastUnchecked$$forInline(Lifecycle lifecycle, Lifecycle.State state, Function0 function0, L0.d dVar) {
        Q.c().z();
        m.c(3);
        throw null;
    }

    private static final <R> Object withCreated$$forInline(LifecycleOwner lifecycleOwner, Function0 function0, L0.d dVar) {
        lifecycleOwner.getLifecycle();
        Lifecycle.State state = Lifecycle.State.DESTROYED;
        Q.c().z();
        m.c(3);
        throw null;
    }

    private static final <R> Object withResumed$$forInline(LifecycleOwner lifecycleOwner, Function0 function0, L0.d dVar) {
        lifecycleOwner.getLifecycle();
        Lifecycle.State state = Lifecycle.State.DESTROYED;
        Q.c().z();
        m.c(3);
        throw null;
    }

    private static final <R> Object withStarted$$forInline(LifecycleOwner lifecycleOwner, Function0 function0, L0.d dVar) {
        lifecycleOwner.getLifecycle();
        Lifecycle.State state = Lifecycle.State.DESTROYED;
        Q.c().z();
        m.c(3);
        throw null;
    }

    private static final <R> Object withStateAtLeast$$forInline(LifecycleOwner lifecycleOwner, Lifecycle.State state, Function0 function0, L0.d dVar) {
        lifecycleOwner.getLifecycle();
        if (state.compareTo(Lifecycle.State.CREATED) >= 0) {
            Q.c().z();
            m.c(3);
            throw null;
        }
        throw new IllegalArgumentException(("target state must be CREATED or greater, found " + state).toString());
    }

    public static final <R> Object withCreated(LifecycleOwner lifecycleOwner, Function0 function0, L0.d dVar) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Lifecycle.State state = Lifecycle.State.CREATED;
        w0 w0VarZ = Q.c().z();
        boolean zIsDispatchNeeded = w0VarZ.isDispatchNeeded(dVar.getContext());
        if (!zIsDispatchNeeded) {
            if (lifecycle.getCurrentState() != Lifecycle.State.DESTROYED) {
                if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                    return function0.invoke();
                }
            } else {
                throw new LifecycleDestroyedException();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, zIsDispatchNeeded, w0VarZ, new AnonymousClass2(function0), dVar);
    }

    public static final <R> Object withResumed(LifecycleOwner lifecycleOwner, Function0 function0, L0.d dVar) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Lifecycle.State state = Lifecycle.State.RESUMED;
        w0 w0VarZ = Q.c().z();
        boolean zIsDispatchNeeded = w0VarZ.isDispatchNeeded(dVar.getContext());
        if (!zIsDispatchNeeded) {
            if (lifecycle.getCurrentState() != Lifecycle.State.DESTROYED) {
                if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                    return function0.invoke();
                }
            } else {
                throw new LifecycleDestroyedException();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, zIsDispatchNeeded, w0VarZ, new AnonymousClass2(function0), dVar);
    }

    public static final <R> Object withStarted(LifecycleOwner lifecycleOwner, Function0 function0, L0.d dVar) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Lifecycle.State state = Lifecycle.State.STARTED;
        w0 w0VarZ = Q.c().z();
        boolean zIsDispatchNeeded = w0VarZ.isDispatchNeeded(dVar.getContext());
        if (!zIsDispatchNeeded) {
            if (lifecycle.getCurrentState() != Lifecycle.State.DESTROYED) {
                if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                    return function0.invoke();
                }
            } else {
                throw new LifecycleDestroyedException();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, zIsDispatchNeeded, w0VarZ, new AnonymousClass2(function0), dVar);
    }

    public static final <R> Object withStateAtLeast(LifecycleOwner lifecycleOwner, Lifecycle.State state, Function0 function0, L0.d dVar) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (state.compareTo(Lifecycle.State.CREATED) >= 0) {
            w0 w0VarZ = Q.c().z();
            boolean zIsDispatchNeeded = w0VarZ.isDispatchNeeded(dVar.getContext());
            if (!zIsDispatchNeeded) {
                if (lifecycle.getCurrentState() != Lifecycle.State.DESTROYED) {
                    if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                        return function0.invoke();
                    }
                } else {
                    throw new LifecycleDestroyedException();
                }
            }
            return suspendWithStateAtLeastUnchecked(lifecycle, state, zIsDispatchNeeded, w0VarZ, new AnonymousClass2(function0), dVar);
        }
        throw new IllegalArgumentException(("target state must be CREATED or greater, found " + state).toString());
    }
}
