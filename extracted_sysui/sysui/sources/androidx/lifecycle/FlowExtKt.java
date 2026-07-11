package androidx.lifecycle;

import H0.k;
import H0.s;
import N0.l;
import androidx.lifecycle.Lifecycle;
import g1.E;
import i1.q;
import i1.t;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class FlowExtKt {

    /* JADX INFO: renamed from: androidx.lifecycle.FlowExtKt$flowWithLifecycle$1, reason: invalid class name */
    @N0.f(c = "androidx.lifecycle.FlowExtKt$flowWithLifecycle$1", f = "FlowExt.kt", l = {91}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ Lifecycle $lifecycle;
        final /* synthetic */ Lifecycle.State $minActiveState;
        final /* synthetic */ InterfaceC0418f $this_flowWithLifecycle;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.lifecycle.FlowExtKt$flowWithLifecycle$1$1, reason: invalid class name and collision with other inner class name */
        @N0.f(c = "androidx.lifecycle.FlowExtKt$flowWithLifecycle$1$1", f = "FlowExt.kt", l = {92}, m = "invokeSuspend")
        public static final class C00381 extends l implements Function2 {
            final /* synthetic */ q $$this$callbackFlow;
            final /* synthetic */ InterfaceC0418f $this_flowWithLifecycle;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00381(InterfaceC0418f interfaceC0418f, q qVar, L0.d dVar) {
                super(2, dVar);
                this.$this_flowWithLifecycle = interfaceC0418f;
                this.$$this$callbackFlow = qVar;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new C00381(this.$this_flowWithLifecycle, this.$$this$callbackFlow, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, L0.d dVar) {
                return ((C00381) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objC = M0.c.c();
                int i2 = this.label;
                if (i2 == 0) {
                    k.b(obj);
                    InterfaceC0418f interfaceC0418f = this.$this_flowWithLifecycle;
                    final q qVar = this.$$this$callbackFlow;
                    InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: androidx.lifecycle.FlowExtKt.flowWithLifecycle.1.1.1
                        @Override // j1.InterfaceC0419g
                        public final Object emit(T t2, L0.d dVar) {
                            Object objB = qVar.b(t2, dVar);
                            return objB == M0.c.c() ? objB : s.f314a;
                        }
                    };
                    this.label = 1;
                    if (interfaceC0418f.collect(interfaceC0419g, this) == objC) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Lifecycle lifecycle, Lifecycle.State state, InterfaceC0418f interfaceC0418f, L0.d dVar) {
            super(2, dVar);
            this.$lifecycle = lifecycle;
            this.$minActiveState = state;
            this.$this_flowWithLifecycle = interfaceC0418f;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$lifecycle, this.$minActiveState, this.$this_flowWithLifecycle, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(q qVar, L0.d dVar) {
            return ((AnonymousClass1) create(qVar, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            q qVar;
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                q qVar2 = (q) this.L$0;
                Lifecycle lifecycle = this.$lifecycle;
                Lifecycle.State state = this.$minActiveState;
                C00381 c00381 = new C00381(this.$this_flowWithLifecycle, qVar2, null);
                this.L$0 = qVar2;
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycle, state, c00381, this) == objC) {
                    return objC;
                }
                qVar = qVar2;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                qVar = (q) this.L$0;
                k.b(obj);
            }
            t.a.a(qVar, null, 1, null);
            return s.f314a;
        }
    }

    public static final <T> InterfaceC0418f flowWithLifecycle(InterfaceC0418f interfaceC0418f, Lifecycle lifecycle, Lifecycle.State minActiveState) {
        n.g(interfaceC0418f, "<this>");
        n.g(lifecycle, "lifecycle");
        n.g(minActiveState, "minActiveState");
        return AbstractC0420h.e(new AnonymousClass1(lifecycle, minActiveState, interfaceC0418f, null));
    }

    public static /* synthetic */ InterfaceC0418f flowWithLifecycle$default(InterfaceC0418f interfaceC0418f, Lifecycle lifecycle, Lifecycle.State state, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            state = Lifecycle.State.STARTED;
        }
        return flowWithLifecycle(interfaceC0418f, lifecycle, state);
    }
}
