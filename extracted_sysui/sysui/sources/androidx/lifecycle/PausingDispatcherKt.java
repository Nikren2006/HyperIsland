package androidx.lifecycle;

import H0.k;
import H0.s;
import N0.l;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.miui.volume.VolumePanelViewController;
import g1.AbstractC0367f;
import g1.E;
import g1.InterfaceC0380l0;
import g1.Q;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
public final class PausingDispatcherKt {

    /* JADX INFO: renamed from: androidx.lifecycle.PausingDispatcherKt$whenStateAtLeast$2, reason: invalid class name */
    @N0.f(c = "androidx.lifecycle.PausingDispatcherKt$whenStateAtLeast$2", f = "PausingDispatcher.kt", l = {VolumePanelViewController.HAPTIC_V2_VOLUME_MAX}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends l implements Function2 {
        final /* synthetic */ Function2 $block;
        final /* synthetic */ Lifecycle.State $minState;
        final /* synthetic */ Lifecycle $this_whenStateAtLeast;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Lifecycle lifecycle, Lifecycle.State state, Function2 function2, L0.d dVar) {
            super(2, dVar);
            this.$this_whenStateAtLeast = lifecycle;
            this.$minState = state;
            this.$block = function2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_whenStateAtLeast, this.$minState, this.$block, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            LifecycleController lifecycleController;
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 != 0) {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                lifecycleController = (LifecycleController) this.L$0;
                try {
                    k.b(obj);
                    lifecycleController.finish();
                    return obj;
                } catch (Throwable th) {
                    th = th;
                    lifecycleController.finish();
                    throw th;
                }
            }
            k.b(obj);
            InterfaceC0380l0 interfaceC0380l0 = (InterfaceC0380l0) ((E) this.L$0).getCoroutineContext().get(InterfaceC0380l0.f4430z);
            if (interfaceC0380l0 == null) {
                throw new IllegalStateException("when[State] methods should have a parent job");
            }
            PausingDispatcher pausingDispatcher = new PausingDispatcher();
            LifecycleController lifecycleController2 = new LifecycleController(this.$this_whenStateAtLeast, this.$minState, pausingDispatcher.dispatchQueue, interfaceC0380l0);
            try {
                Function2 function2 = this.$block;
                this.L$0 = lifecycleController2;
                this.label = 1;
                obj = AbstractC0367f.c(pausingDispatcher, function2, this);
                if (obj == objC) {
                    return objC;
                }
                lifecycleController = lifecycleController2;
                lifecycleController.finish();
                return obj;
            } catch (Throwable th2) {
                th = th2;
                lifecycleController = lifecycleController2;
                lifecycleController.finish();
                throw th;
            }
        }
    }

    public static final <T> Object whenCreated(LifecycleOwner lifecycleOwner, Function2 function2, L0.d dVar) {
        return whenCreated(lifecycleOwner.getLifecycle(), function2, dVar);
    }

    public static final <T> Object whenResumed(LifecycleOwner lifecycleOwner, Function2 function2, L0.d dVar) {
        return whenResumed(lifecycleOwner.getLifecycle(), function2, dVar);
    }

    public static final <T> Object whenStarted(LifecycleOwner lifecycleOwner, Function2 function2, L0.d dVar) {
        return whenStarted(lifecycleOwner.getLifecycle(), function2, dVar);
    }

    public static final <T> Object whenStateAtLeast(Lifecycle lifecycle, Lifecycle.State state, Function2 function2, L0.d dVar) {
        return AbstractC0367f.c(Q.c().z(), new AnonymousClass2(lifecycle, state, function2, null), dVar);
    }

    public static final <T> Object whenCreated(Lifecycle lifecycle, Function2 function2, L0.d dVar) {
        return whenStateAtLeast(lifecycle, Lifecycle.State.CREATED, function2, dVar);
    }

    public static final <T> Object whenResumed(Lifecycle lifecycle, Function2 function2, L0.d dVar) {
        return whenStateAtLeast(lifecycle, Lifecycle.State.RESUMED, function2, dVar);
    }

    public static final <T> Object whenStarted(Lifecycle lifecycle, Function2 function2, L0.d dVar) {
        return whenStateAtLeast(lifecycle, Lifecycle.State.STARTED, function2, dVar);
    }
}
