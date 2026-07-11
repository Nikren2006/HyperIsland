package miui.systemui.dynamicisland.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.app.PendingIntent;
import g1.AbstractC0369g;
import g1.E;
import j1.I;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandStateInteractor implements DynamicIslandStartable {
    private final ControlCenterExpandRepository controlCenterExpandRepository;
    private final DynamicIslandWindowViewController dynamicIslandWindowViewController;
    private final E scope;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.domain.interactor.DynamicIslandStateInteractor$listenForControlCenterAppearance$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.domain.interactor.DynamicIslandStateInteractor$listenForControlCenterAppearance$1", f = "DynamicIslandStateInteractor.kt", l = {31}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandStateInteractor.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                I appearance = DynamicIslandStateInteractor.this.controlCenterExpandRepository.getAppearance();
                final DynamicIslandStateInteractor dynamicIslandStateInteractor = DynamicIslandStateInteractor.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.domain.interactor.DynamicIslandStateInteractor.listenForControlCenterAppearance.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, d dVar) throws PendingIntent.CanceledException {
                        dynamicIslandStateInteractor.dynamicIslandWindowViewController.controlCenterExpanded(z2);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (appearance.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            throw new H0.c();
        }
    }

    public DynamicIslandStateInteractor(@DynamicIsland E scope, DynamicIslandWindowViewController dynamicIslandWindowViewController, ControlCenterExpandRepository controlCenterExpandRepository) {
        n.g(scope, "scope");
        n.g(dynamicIslandWindowViewController, "dynamicIslandWindowViewController");
        n.g(controlCenterExpandRepository, "controlCenterExpandRepository");
        this.scope = scope;
        this.dynamicIslandWindowViewController = dynamicIslandWindowViewController;
        this.controlCenterExpandRepository = controlCenterExpandRepository;
    }

    private final void listenForControlCenterAppearance() {
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null);
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        listenForControlCenterAppearance();
    }
}
