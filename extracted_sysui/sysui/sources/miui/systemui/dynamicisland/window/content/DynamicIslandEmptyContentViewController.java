package miui.systemui.dynamicisland.window.content;

import H0.k;
import H0.s;
import L0.d;
import N0.f;
import N0.l;
import g1.AbstractC0369g;
import g1.E;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.util.coroutines.flow.ViewUtilsKt;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandEmptyContentViewController implements DynamicIslandStartable {
    private final E scope;
    private final DynamicIslandTouchInteractor touchInteractor;
    private final DynamicIslandEmptyContentView view;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandEmptyContentViewController$start$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandEmptyContentViewController$start$1", f = "DynamicIslandEmptyContentViewController.kt", l = {33}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandEmptyContentViewController.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                DynamicIslandEmptyContentView dynamicIslandEmptyContentView = DynamicIslandEmptyContentViewController.this.view;
                n.f(dynamicIslandEmptyContentView, "access$getView$p(...)");
                InterfaceC0418f onClick = ViewUtilsKt.getOnClick(dynamicIslandEmptyContentView);
                final DynamicIslandEmptyContentViewController dynamicIslandEmptyContentViewController = DynamicIslandEmptyContentViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandEmptyContentViewController.start.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(s sVar, d dVar) {
                        dynamicIslandEmptyContentViewController.touchInteractor.performClick();
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (onClick.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandEmptyContentViewController$start$2, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandEmptyContentViewController$start$2", f = "DynamicIslandEmptyContentViewController.kt", l = {38}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends l implements Function2 {
        int label;

        public AnonymousClass2(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandEmptyContentViewController.this.new AnonymousClass2(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                DynamicIslandEmptyContentView dynamicIslandEmptyContentView = DynamicIslandEmptyContentViewController.this.view;
                n.f(dynamicIslandEmptyContentView, "access$getView$p(...)");
                InterfaceC0418f onLongClick = ViewUtilsKt.getOnLongClick(dynamicIslandEmptyContentView);
                final DynamicIslandEmptyContentViewController dynamicIslandEmptyContentViewController = DynamicIslandEmptyContentViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandEmptyContentViewController.start.2.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Function1 function1, d dVar) {
                        function1.invoke(N0.b.a(dynamicIslandEmptyContentViewController.touchInteractor.performLongClick()));
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (onLongClick.collect(interfaceC0419g, this) == objC) {
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

    public DynamicIslandEmptyContentViewController(DynamicIslandWindowView windowView, @DynamicIsland E scope, DynamicIslandTouchInteractor touchInteractor) {
        n.g(windowView, "windowView");
        n.g(scope, "scope");
        n.g(touchInteractor, "touchInteractor");
        this.scope = scope;
        this.touchInteractor = touchInteractor;
        this.view = (DynamicIslandEmptyContentView) windowView.requireViewById(R.id.empty_content_view);
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null);
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass2(null), 3, null);
    }
}
