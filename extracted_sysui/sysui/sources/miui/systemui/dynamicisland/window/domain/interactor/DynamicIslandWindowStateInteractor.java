package miui.systemui.dynamicisland.window.domain.interactor;

import H0.s;
import M0.c;
import N0.d;
import N0.f;
import g1.E;
import j1.AbstractC0420h;
import j1.E;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandWindowStateInteractor {
    private final DynamicIslandEventCoordinator eventCoordinator;
    private final I isAppAnimRunning;
    private final E scope;
    private final I visibility;
    private final I visible;
    private final I watchOutsideTouch;
    private final DynamicIslandWindowViewController windowViewController;
    private final DynamicIslandWindowViewTouchInteractor windowViewTouchInteractor;

    public DynamicIslandWindowStateInteractor(@DynamicIsland E scope, DynamicIslandEventCoordinator eventCoordinator, DynamicIslandWindowViewController windowViewController, DynamicIslandWindowViewTouchInteractor windowViewTouchInteractor) {
        n.g(scope, "scope");
        n.g(eventCoordinator, "eventCoordinator");
        n.g(windowViewController, "windowViewController");
        n.g(windowViewTouchInteractor, "windowViewTouchInteractor");
        this.scope = scope;
        this.eventCoordinator = eventCoordinator;
        this.windowViewController = windowViewController;
        this.windowViewTouchInteractor = windowViewTouchInteractor;
        this.isAppAnimRunning = windowViewController.isAppAnimRunning();
        InterfaceC0418f interfaceC0418fL = AbstractC0420h.l(getWindowHeight(), windowViewTouchInteractor.isTracking(), new DynamicIslandWindowStateInteractor$visible$1(null));
        E.a aVar = j1.E.f4648a;
        j1.E eC = aVar.c();
        Boolean bool = Boolean.FALSE;
        final I iB = AbstractC0420h.B(interfaceC0418fL, scope, eC, bool);
        this.visible = iB;
        this.visibility = AbstractC0420h.B(new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$map$1

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
                @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$map$1$2", f = "DynamicIslandWindowStateInteractor.kt", l = {223}, m = "emit")
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

                public AnonymousClass2(InterfaceC0419g interfaceC0419g) {
                    this.$this_unsafeFlow = interfaceC0419g;
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
                        boolean r0 = r6 instanceof miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$map$1$2$1 r0 = (miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$map$1$2$1 r0 = new miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r6)
                        goto L4e
                    L29:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L31:
                        H0.k.b(r6)
                        j1.g r4 = r4.$this_unsafeFlow
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        boolean r5 = r5.booleanValue()
                        if (r5 == 0) goto L40
                        r5 = 0
                        goto L41
                    L40:
                        r5 = 4
                    L41:
                        java.lang.Integer r5 = N0.b.c(r5)
                        r0.label = r3
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4e
                        return r1
                    L4e:
                        H0.s r4 = H0.s.f314a
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                Object objCollect = iB.collect(new AnonymousClass2(interfaceC0419g), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        }, scope, aVar.c(), 4);
        this.watchOutsideTouch = AbstractC0420h.B(AbstractC0420h.C(windowViewController.getContentViews(), new DynamicIslandWindowStateInteractor$special$$inlined$flatMapLatest$1(null)), scope, aVar.c(), bool);
    }

    public static /* synthetic */ void getWatchOutsideTouch$annotations() {
    }

    public final I getVisibility() {
        return this.visibility;
    }

    public final I getVisible() {
        return this.visible;
    }

    public final I getWatchOutsideTouch() {
        return this.watchOutsideTouch;
    }

    public final I getWindowHeight() {
        return this.eventCoordinator.getState();
    }

    public final I isAppAnimRunning() {
        return this.isAppAnimRunning;
    }
}
