package androidx.lifecycle;

import H0.k;
import H0.s;
import N0.l;
import androidx.lifecycle.Lifecycle;
import g1.AbstractC0367f;
import g1.E;
import g1.F;
import g1.Q;
import g1.w0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
public final class RepeatOnLifecycleKt {

    /* JADX INFO: renamed from: androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3, reason: invalid class name */
    @N0.f(c = "androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3", f = "RepeatOnLifecycle.kt", l = {84}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends l implements Function2 {
        final /* synthetic */ Function2 $block;
        final /* synthetic */ Lifecycle.State $state;
        final /* synthetic */ Lifecycle $this_repeatOnLifecycle;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1, reason: invalid class name */
        @N0.f(c = "androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1", f = "RepeatOnLifecycle.kt", l = {166}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends l implements Function2 {
            final /* synthetic */ E $$this$coroutineScope;
            final /* synthetic */ Function2 $block;
            final /* synthetic */ Lifecycle.State $state;
            final /* synthetic */ Lifecycle $this_repeatOnLifecycle;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(Lifecycle lifecycle, Lifecycle.State state, E e2, Function2 function2, L0.d dVar) {
                super(2, dVar);
                this.$this_repeatOnLifecycle = lifecycle;
                this.$state = state;
                this.$$this$coroutineScope = e2;
                this.$block = function2;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new AnonymousClass1(this.$this_repeatOnLifecycle, this.$state, this.$$this$coroutineScope, this.$block, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, L0.d dVar) {
                return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            /* JADX WARN: Removed duplicated region for block: B:28:0x00b7  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00c1  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x00cf  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x00d9  */
            @Override // N0.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 223
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.RepeatOnLifecycleKt.AnonymousClass3.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Lifecycle lifecycle, Lifecycle.State state, Function2 function2, L0.d dVar) {
            super(2, dVar);
            this.$this_repeatOnLifecycle = lifecycle;
            this.$state = state;
            this.$block = function2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$this_repeatOnLifecycle, this.$state, this.$block, dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass3) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                E e2 = (E) this.L$0;
                w0 w0VarZ = Q.c().z();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_repeatOnLifecycle, this.$state, e2, this.$block, null);
                this.label = 1;
                if (AbstractC0367f.c(w0VarZ, anonymousClass1, this) == objC) {
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

    public static final Object repeatOnLifecycle(Lifecycle lifecycle, Lifecycle.State state, Function2 function2, L0.d dVar) {
        if (state == Lifecycle.State.INITIALIZED) {
            throw new IllegalArgumentException("repeatOnLifecycle cannot start work with the INITIALIZED lifecycle state.");
        }
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            return s.f314a;
        }
        Object objF = F.f(new AnonymousClass3(lifecycle, state, function2, null), dVar);
        return objF == M0.c.c() ? objF : s.f314a;
    }

    public static final Object repeatOnLifecycle(LifecycleOwner lifecycleOwner, Lifecycle.State state, Function2 function2, L0.d dVar) {
        Object objRepeatOnLifecycle = repeatOnLifecycle(lifecycleOwner.getLifecycle(), state, function2, dVar);
        return objRepeatOnLifecycle == M0.c.c() ? objRepeatOnLifecycle : s.f314a;
    }
}
