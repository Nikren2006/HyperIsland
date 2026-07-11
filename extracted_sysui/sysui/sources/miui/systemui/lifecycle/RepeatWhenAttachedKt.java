package miui.systemui.lifecycle;

import H0.k;
import H0.s;
import L0.g;
import L0.h;
import M0.c;
import N0.f;
import N0.l;
import android.view.View;
import androidx.annotation.MainThread;
import androidx.lifecycle.LifecycleOwnerKt;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.S;
import h1.d;
import i1.q;
import j1.InterfaceC0418f;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.y;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.util.Assert;
import miui.systemui.util.coroutines.flow.FlowConflatedKt;

/* JADX INFO: loaded from: classes3.dex */
public final class RepeatWhenAttachedKt {
    private static final d MAIN_DISPATCHER_SINGLETON = Dispatchers.INSTANCE.getMain().z();

    /* JADX INFO: renamed from: miui.systemui.lifecycle.RepeatWhenAttachedKt$isAttached$1, reason: invalid class name */
    @f(c = "miui.systemui.lifecycle.RepeatWhenAttachedKt$isAttached$1", f = "RepeatWhenAttached.kt", l = {205}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ View $this_isAttached;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: miui.systemui.lifecycle.RepeatWhenAttachedKt$isAttached$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C01481 extends o implements Function0 {
            final /* synthetic */ RepeatWhenAttachedKt$isAttached$1$onAttachListener$1 $onAttachListener;
            final /* synthetic */ View $this_isAttached;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01481(View view, RepeatWhenAttachedKt$isAttached$1$onAttachListener$1 repeatWhenAttachedKt$isAttached$1$onAttachListener$1) {
                super(0);
                this.$this_isAttached = view;
                this.$onAttachListener = repeatWhenAttachedKt$isAttached$1$onAttachListener$1;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m140invoke();
                return s.f314a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m140invoke() {
                this.$this_isAttached.removeOnAttachStateChangeListener(this.$onAttachListener);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(View view, L0.d dVar) {
            super(2, dVar);
            this.$this_isAttached = view;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_isAttached, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(q qVar, L0.d dVar) {
            return ((AnonymousClass1) create(qVar, dVar)).invokeSuspend(s.f314a);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [android.view.View$OnAttachStateChangeListener, miui.systemui.lifecycle.RepeatWhenAttachedKt$isAttached$1$onAttachListener$1] */
        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                final q qVar = (q) this.L$0;
                ?? r12 = new View.OnAttachStateChangeListener() { // from class: miui.systemui.lifecycle.RepeatWhenAttachedKt$isAttached$1$onAttachListener$1
                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View v2) {
                        n.g(v2, "v");
                        Assert.isMainThread();
                        qVar.j(Boolean.TRUE);
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View v2) {
                        n.g(v2, "v");
                        qVar.j(Boolean.FALSE);
                    }
                };
                this.$this_isAttached.addOnAttachStateChangeListener(r12);
                qVar.j(N0.b.a(this.$this_isAttached.isAttachedToWindow()));
                C01481 c01481 = new C01481(this.$this_isAttached, r12);
                this.label = 1;
                if (i1.o.a(qVar, c01481, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttachedToWindow$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.lifecycle.RepeatWhenAttachedKt", f = "RepeatWhenAttached.kt", l = {186, 187}, m = "repeatWhenAttachedToWindow")
    public static final class C06701 extends N0.d {
        int label;
        /* synthetic */ Object result;

        public C06701(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RepeatWhenAttachedKt.repeatWhenAttachedToWindow(null, null, this);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttachedToWindow$2, reason: invalid class name */
    @f(c = "miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttachedToWindow$2", f = "RepeatWhenAttached.kt", l = {186}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends l implements Function2 {
        final /* synthetic */ Function2 $block;
        /* synthetic */ boolean Z$0;
        int label;

        /* JADX INFO: renamed from: miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttachedToWindow$2$1, reason: invalid class name */
        @f(c = "miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttachedToWindow$2$1", f = "RepeatWhenAttached.kt", l = {186}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends l implements Function2 {
            final /* synthetic */ Function2 $block;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(Function2 function2, L0.d dVar) {
                super(2, dVar);
                this.$block = function2;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$block, dVar);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, L0.d dVar) {
                return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objC = c.c();
                int i2 = this.label;
                if (i2 == 0) {
                    k.b(obj);
                    E e2 = (E) this.L$0;
                    Function2 function2 = this.$block;
                    this.label = 1;
                    if (function2.invoke(e2, this) == objC) {
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
        public AnonymousClass2(Function2 function2, L0.d dVar) {
            super(2, dVar);
            this.$block = function2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$block, dVar);
            anonymousClass2.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Boolean) obj).booleanValue(), (L0.d) obj2);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                if (this.Z$0) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$block, null);
                    this.label = 1;
                    if (F.f(anonymousClass1, this) == objC) {
                        return objC;
                    }
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            return s.f314a;
        }

        public final Object invoke(boolean z2, L0.d dVar) {
            return ((AnonymousClass2) create(Boolean.valueOf(z2), dVar)).invokeSuspend(s.f314a);
        }
    }

    private static final ViewLifecycleOwner createLifecycleOwnerAndRun(View view, g gVar, Function3 function3) {
        ViewLifecycleOwner viewLifecycleOwner = new ViewLifecycleOwner(view);
        viewLifecycleOwner.onCreate();
        AbstractC0369g.b(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), gVar, null, new RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1(function3, viewLifecycleOwner, view, null), 2, null);
        return viewLifecycleOwner;
    }

    private static final InterfaceC0418f isAttached(View view) {
        return FlowConflatedKt.conflatedCallbackFlow(new AnonymousClass1(view, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.view.View$OnAttachStateChangeListener, miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1] */
    @MainThread
    public static final S repeatWhenAttached(final View view, g coroutineContext, final Function3 block) {
        n.g(view, "<this>");
        n.g(coroutineContext, "coroutineContext");
        n.g(block, "block");
        Assert.isMainThread();
        final g gVarPlus = MAIN_DISPATCHER_SINGLETON.plus(coroutineContext);
        final y yVar = new y();
        final ?? r12 = new View.OnAttachStateChangeListener() { // from class: miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v2) {
                n.g(v2, "v");
                Assert.isMainThread();
                RepeatWhenAttachedKt.repeatWhenAttached$create(yVar, gVarPlus, block, view);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v2) {
                n.g(v2, "v");
                RepeatWhenAttachedKt.repeatWhenAttached$destroy(yVar);
            }
        };
        view.addOnAttachStateChangeListener(r12);
        if (view.isAttachedToWindow()) {
            repeatWhenAttached$create(yVar, gVarPlus, block, view);
        }
        return new S() { // from class: miui.systemui.lifecycle.b
            @Override // g1.S
            public final void dispose() {
                RepeatWhenAttachedKt.repeatWhenAttached$lambda$1(view, r12, yVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void repeatWhenAttached$create(y yVar, g gVar, Function3 function3, View view) {
        E e2 = (E) yVar.f5059a;
        if (e2 != null) {
            F.e(e2, null, 1, null);
        }
        E eA = F.a(MAIN_DISPATCHER_SINGLETON);
        AbstractC0369g.b(eA, gVar, null, new RepeatWhenAttachedKt$repeatWhenAttached$create$1$1(function3, view, null), 2, null);
        yVar.f5059a = eA;
    }

    public static /* synthetic */ S repeatWhenAttached$default(View view, g gVar, Function3 function3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            gVar = h.f402a;
        }
        return repeatWhenAttached(view, gVar, function3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void repeatWhenAttached$destroy(y yVar) {
        E e2 = (E) yVar.f5059a;
        if (e2 != null) {
            F.e(e2, null, 1, null);
        }
        yVar.f5059a = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void repeatWhenAttached$lambda$1(View view, RepeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1 onAttachListener, y scope) {
        n.g(view, "$view");
        n.g(onAttachListener, "$onAttachListener");
        n.g(scope, "$scope");
        Assert.isMainThread();
        repeatWhenAttached$destroy(scope);
        view.removeOnAttachStateChangeListener(onAttachListener);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @androidx.annotation.MainThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object repeatWhenAttachedToWindow(android.view.View r5, kotlin.jvm.functions.Function2 r6, L0.d r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof miui.systemui.lifecycle.RepeatWhenAttachedKt.C06701
            if (r0 == 0) goto L13
            r0 = r7
            miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttachedToWindow$1 r0 = (miui.systemui.lifecycle.RepeatWhenAttachedKt.C06701) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttachedToWindow$1 r0 = new miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttachedToWindow$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L38
            if (r2 == r4) goto L34
            if (r2 == r3) goto L30
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L30:
            H0.k.b(r7)
            goto L5a
        L34:
            H0.k.b(r7)
            goto L51
        L38:
            H0.k.b(r7)
            miui.systemui.util.Assert.isMainThread()
            j1.f r5 = isAttached(r5)
            miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttachedToWindow$2 r7 = new miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttachedToWindow$2
            r2 = 0
            r7.<init>(r6, r2)
            r0.label = r4
            java.lang.Object r5 = j1.AbstractC0420h.h(r5, r7, r0)
            if (r5 != r1) goto L51
            return r1
        L51:
            r0.label = r3
            java.lang.Object r5 = g1.M.a(r0)
            if (r5 != r1) goto L5a
            return r1
        L5a:
            H0.c r5 = new H0.c
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.lifecycle.RepeatWhenAttachedKt.repeatWhenAttachedToWindow(android.view.View, kotlin.jvm.functions.Function2, L0.d):java.lang.Object");
    }
}
