package androidx.lifecycle;

import H0.k;
import H0.s;
import L0.g;
import N0.l;
import g1.AbstractC0369g;
import g1.E;
import g1.InterfaceC0380l0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class LifecycleCoroutineScope implements E {

    /* JADX INFO: renamed from: androidx.lifecycle.LifecycleCoroutineScope$launchWhenCreated$1, reason: invalid class name */
    @N0.f(c = "androidx.lifecycle.LifecycleCoroutineScope$launchWhenCreated$1", f = "Lifecycle.kt", l = {337}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ Function2 $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Function2 function2, L0.d dVar) {
            super(2, dVar);
            this.$block = function2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return LifecycleCoroutineScope.this.new AnonymousClass1(this.$block, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                Lifecycle lifecycle$lifecycle_common = LifecycleCoroutineScope.this.getLifecycle$lifecycle_common();
                Function2 function2 = this.$block;
                this.label = 1;
                if (PausingDispatcherKt.whenCreated(lifecycle$lifecycle_common, function2, this) == objC) {
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

    /* JADX INFO: renamed from: androidx.lifecycle.LifecycleCoroutineScope$launchWhenResumed$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "androidx.lifecycle.LifecycleCoroutineScope$launchWhenResumed$1", f = "Lifecycle.kt", l = {375}, m = "invokeSuspend")
    public static final class C02141 extends l implements Function2 {
        final /* synthetic */ Function2 $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02141(Function2 function2, L0.d dVar) {
            super(2, dVar);
            this.$block = function2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return LifecycleCoroutineScope.this.new C02141(this.$block, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C02141) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                Lifecycle lifecycle$lifecycle_common = LifecycleCoroutineScope.this.getLifecycle$lifecycle_common();
                Function2 function2 = this.$block;
                this.label = 1;
                if (PausingDispatcherKt.whenResumed(lifecycle$lifecycle_common, function2, this) == objC) {
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

    /* JADX INFO: renamed from: androidx.lifecycle.LifecycleCoroutineScope$launchWhenStarted$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "androidx.lifecycle.LifecycleCoroutineScope$launchWhenStarted$1", f = "Lifecycle.kt", l = {356}, m = "invokeSuspend")
    public static final class C02151 extends l implements Function2 {
        final /* synthetic */ Function2 $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02151(Function2 function2, L0.d dVar) {
            super(2, dVar);
            this.$block = function2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return LifecycleCoroutineScope.this.new C02151(this.$block, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C02151) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                Lifecycle lifecycle$lifecycle_common = LifecycleCoroutineScope.this.getLifecycle$lifecycle_common();
                Function2 function2 = this.$block;
                this.label = 1;
                if (PausingDispatcherKt.whenStarted(lifecycle$lifecycle_common, function2, this) == objC) {
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

    @Override // g1.E
    public abstract /* synthetic */ g getCoroutineContext();

    public abstract Lifecycle getLifecycle$lifecycle_common();

    public final InterfaceC0380l0 launchWhenCreated(Function2 block) {
        n.g(block, "block");
        return AbstractC0369g.b(this, null, null, new AnonymousClass1(block, null), 3, null);
    }

    public final InterfaceC0380l0 launchWhenResumed(Function2 block) {
        n.g(block, "block");
        return AbstractC0369g.b(this, null, null, new C02141(block, null), 3, null);
    }

    public final InterfaceC0380l0 launchWhenStarted(Function2 block) {
        n.g(block, "block");
        return AbstractC0369g.b(this, null, null, new C02151(block, null), 3, null);
    }
}
