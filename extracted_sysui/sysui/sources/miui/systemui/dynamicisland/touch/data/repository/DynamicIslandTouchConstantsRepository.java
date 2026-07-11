package miui.systemui.dynamicisland.touch.data.repository;

import H0.s;
import M0.c;
import N0.d;
import N0.f;
import android.content.Context;
import g1.E;
import j1.AbstractC0420h;
import j1.E;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.internal.n;
import miui.systemui.autodensity.AutoDensityController;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.util.coroutines.flow.FlowConflatedKt;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandTouchConstantsRepository {
    private final AutoDensityController autoDensityController;
    private final Context context;
    private final InterfaceC0418f densityChanged;
    private final E scope;
    private final I swipeThreshold;
    private final I touchSlop;

    public DynamicIslandTouchConstantsRepository(@DynamicIsland E scope, Context context, AutoDensityController autoDensityController) {
        n.g(scope, "scope");
        n.g(context, "context");
        n.g(autoDensityController, "autoDensityController");
        this.scope = scope;
        this.context = context;
        this.autoDensityController = autoDensityController;
        final InterfaceC0418f interfaceC0418fConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new DynamicIslandTouchConstantsRepository$densityChanged$1(this, null));
        this.densityChanged = interfaceC0418fConflatedCallbackFlow;
        InterfaceC0418f interfaceC0418f = new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$1

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;
                final /* synthetic */ DynamicIslandTouchConstantsRepository this$0;

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$1$2$1, reason: invalid class name */
                @f(c = "miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$1$2", f = "DynamicIslandTouchConstantsRepository.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends d {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(L0.d dVar) {
                        super(dVar);
                    }

                    @Override // N0.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(InterfaceC0419g interfaceC0419g, DynamicIslandTouchConstantsRepository dynamicIslandTouchConstantsRepository) {
                    this.$this_unsafeFlow = interfaceC0419g;
                    this.this$0 = dynamicIslandTouchConstantsRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // j1.InterfaceC0419g
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, L0.d r6) throws java.lang.Throwable {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$1$2$1 r0 = (miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$1$2$1 r0 = new miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r6)
                        goto L53
                    L29:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L31:
                        H0.k.b(r6)
                        j1.g r6 = r4.$this_unsafeFlow
                        H0.s r5 = (H0.s) r5
                        miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository r4 = r4.this$0
                        android.content.Context r4 = miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository.access$getContext$p(r4)
                        android.view.ViewConfiguration r4 = android.view.ViewConfiguration.get(r4)
                        int r4 = r4.getScaledTouchSlop()
                        java.lang.Integer r4 = N0.b.c(r4)
                        r0.label = r3
                        java.lang.Object r4 = r6.emit(r4, r0)
                        if (r4 != r1) goto L53
                        return r1
                    L53:
                        H0.s r4 = H0.s.f314a
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                Object objCollect = interfaceC0418fConflatedCallbackFlow.collect(new AnonymousClass2(interfaceC0419g, this), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        };
        E.a aVar = j1.E.f4648a;
        this.touchSlop = AbstractC0420h.B(interfaceC0418f, scope, aVar.c(), Integer.MIN_VALUE);
        this.swipeThreshold = AbstractC0420h.B(new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$2

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$2$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;
                final /* synthetic */ DynamicIslandTouchConstantsRepository this$0;

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$2$2$1, reason: invalid class name */
                @f(c = "miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$2$2", f = "DynamicIslandTouchConstantsRepository.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends d {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(L0.d dVar) {
                        super(dVar);
                    }

                    @Override // N0.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(InterfaceC0419g interfaceC0419g, DynamicIslandTouchConstantsRepository dynamicIslandTouchConstantsRepository) {
                    this.$this_unsafeFlow = interfaceC0419g;
                    this.this$0 = dynamicIslandTouchConstantsRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // j1.InterfaceC0419g
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, L0.d r6) throws java.lang.Throwable {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$2$2$1 r0 = (miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$2$2$1 r0 = new miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r6)
                        goto L55
                    L29:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L31:
                        H0.k.b(r6)
                        j1.g r6 = r4.$this_unsafeFlow
                        H0.s r5 = (H0.s) r5
                        miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository r4 = r4.this$0
                        android.content.Context r4 = miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository.access$getContext$p(r4)
                        android.content.res.Resources r4 = r4.getResources()
                        int r5 = miui.systemui.dynamicisland.R.dimen.island_swipe_threshold
                        int r4 = r4.getDimensionPixelSize(r5)
                        java.lang.Integer r4 = N0.b.c(r4)
                        r0.label = r3
                        java.lang.Object r4 = r6.emit(r4, r0)
                        if (r4 != r1) goto L55
                        return r1
                    L55:
                        H0.s r4 = H0.s.f314a
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                Object objCollect = interfaceC0418fConflatedCallbackFlow.collect(new AnonymousClass2(interfaceC0419g, this), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        }, scope, aVar.c(), Integer.MIN_VALUE);
    }

    public final I getSwipeThreshold() {
        return this.swipeThreshold;
    }

    public final I getTouchSlop() {
        return this.touchSlop;
    }
}
