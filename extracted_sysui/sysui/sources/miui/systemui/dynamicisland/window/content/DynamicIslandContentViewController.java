package miui.systemui.dynamicisland.window.content;

import H0.k;
import H0.s;
import L0.d;
import L0.g;
import N0.f;
import N0.l;
import android.graphics.Rect;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import g1.AbstractC0369g;
import g1.E;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.events.DynamicIslandExposureManager;
import miui.systemui.dynamicisland.template.IslandTemplateFactory;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.view.DynamicIslandBigIslandView;
import miui.systemui.dynamicisland.view.DynamicIslandExpandedView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController;
import miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandContentViewBinder;
import miui.systemui.lifecycle.RepeatWhenAttachedKt;
import miui.systemui.util.coroutines.flow.ViewUtilsKt;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandContentViewController extends DynamicIslandBaseContentViewController<DynamicIslandContentView> {
    private final float MIN_ALPHA;
    private long _actualTime;
    private long _hiddenTime;
    private long _revealTime;
    private boolean attached;
    private final DynamicIslandExposureManager expoManager;
    private boolean hasWindowFocus;
    private boolean isExposed;
    private boolean isVisible;
    private final Rect rect;
    private final E scope;
    private final DynamicIslandTouchInteractor touchInteractor;
    private final g uiContext;
    private final DynamicIslandWindowView windowView;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController$1", f = "DynamicIslandContentViewController.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function3 {
        final /* synthetic */ DynamicIslandContentView $view;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandContentView dynamicIslandContentView, d dVar) {
            super(3, dVar);
            this.$view = dynamicIslandContentView;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(E e2, View view, d dVar) {
            AnonymousClass1 anonymousClass1 = DynamicIslandContentViewController.this.new AnonymousClass1(this.$view, dVar);
            anonymousClass1.L$0 = e2;
            return anonymousClass1.invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            E e2 = (E) this.L$0;
            DynamicIslandContentViewController.this.listenForPressed(e2);
            DynamicIslandContentViewBinder dynamicIslandContentViewBinder = DynamicIslandContentViewBinder.INSTANCE;
            DynamicIslandContentView dynamicIslandContentView = this.$view;
            dynamicIslandContentViewBinder.bind(dynamicIslandContentView, e2, dynamicIslandContentView.getViewModel());
            return s.f314a;
        }
    }

    public interface Factory extends DynamicIslandBaseContentViewController.Factory<DynamicIslandContentView> {
        DynamicIslandContentViewController create(DynamicIslandContentView dynamicIslandContentView);
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController$listenForClick$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController$listenForClick$1", f = "DynamicIslandContentViewController.kt", l = {164}, m = "invokeSuspend")
    public static final class C06531 extends l implements Function2 {
        int label;

        public C06531(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandContentViewController.this.new C06531(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06531) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                InterfaceC0418f onClick = ViewUtilsKt.getOnClick(DynamicIslandContentViewController.this.getView());
                final DynamicIslandContentViewController dynamicIslandContentViewController = DynamicIslandContentViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController.listenForClick.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(s sVar, d dVar) {
                        dynamicIslandContentViewController.touchInteractor.performClick();
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController$listenForLongClick$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController$listenForLongClick$1", f = "DynamicIslandContentViewController.kt", l = {170}, m = "invokeSuspend")
    public static final class C06541 extends l implements Function2 {
        int label;

        public C06541(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandContentViewController.this.new C06541(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06541) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                InterfaceC0418f onLongClick = ViewUtilsKt.getOnLongClick(DynamicIslandContentViewController.this.getView());
                final DynamicIslandContentViewController dynamicIslandContentViewController = DynamicIslandContentViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController.listenForLongClick.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Function1 function1, d dVar) {
                        function1.invoke(N0.b.a(dynamicIslandContentViewController.touchInteractor.performLongClick()));
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController$listenForPressed$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController$listenForPressed$1", f = "DynamicIslandContentViewController.kt", l = {176}, m = "invokeSuspend")
    public static final class C06551 extends l implements Function2 {
        int label;

        public C06551(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandContentViewController.this.new C06551(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06551) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                I iIsPressed = DynamicIslandContentViewController.this.getView().isPressed();
                final DynamicIslandContentViewController dynamicIslandContentViewController = DynamicIslandContentViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController.listenForPressed.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, d dVar) {
                        dynamicIslandContentViewController.touchInteractor.performPressed(z2);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (iIsPressed.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController$log$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController$log$1", f = "DynamicIslandContentViewController.kt", l = {182}, m = "invokeSuspend")
    public static final class C06561 extends l implements Function2 {
        int label;

        public C06561(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandContentViewController.this.new C06561(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06561) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                I state = DynamicIslandContentViewController.this.getView().getViewModel().getState();
                final DynamicIslandContentViewController dynamicIslandContentViewController = DynamicIslandContentViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController.log.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(DynamicIslandState dynamicIslandState, d dVar) {
                        Log.e(DynamicIslandContentView.TAG, "initViewModel state: " + dynamicIslandContentViewController.getView().getViewModel().getOldState() + " ->" + dynamicIslandState);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (state.collect(interfaceC0419g, this) == objC) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandContentViewController(DynamicIslandContentView view, @DynamicIsland E scope, @Main g uiContext, DynamicIslandWindowView windowView, DynamicIslandRegionSamplingInteractor regionSamplingInteractor, IslandTemplateFactory islandTemplateFactory, DynamicIslandTouchInteractor touchInteractor, DynamicIslandExposureManager expoManager) {
        super(view, scope, regionSamplingInteractor, islandTemplateFactory, touchInteractor);
        n.g(view, "view");
        n.g(scope, "scope");
        n.g(uiContext, "uiContext");
        n.g(windowView, "windowView");
        n.g(regionSamplingInteractor, "regionSamplingInteractor");
        n.g(islandTemplateFactory, "islandTemplateFactory");
        n.g(touchInteractor, "touchInteractor");
        n.g(expoManager, "expoManager");
        this.scope = scope;
        this.uiContext = uiContext;
        this.windowView = windowView;
        this.touchInteractor = touchInteractor;
        this.expoManager = expoManager;
        getDisposables$miui_dynamicisland_release().plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(view, uiContext, new AnonymousClass1(view, null)));
        this.MIN_ALPHA = 0.1f;
        this.rect = new Rect();
    }

    private final boolean currentIslandVisible() {
        Float fValueOf;
        if (getView().getState() instanceof DynamicIslandState.BigIsland) {
            DynamicIslandBigIslandView bigIslandView = getView().getBigIslandView();
            fValueOf = bigIslandView != null ? Float.valueOf(bigIslandView.getAlpha()) : null;
            n.d(fValueOf);
            return fValueOf.floatValue() > this.MIN_ALPHA;
        }
        if (getView().getState() instanceof DynamicIslandState.SmallIsland) {
            FrameLayout smallIslandView = getView().getSmallIslandView();
            fValueOf = smallIslandView != null ? Float.valueOf(smallIslandView.getAlpha()) : null;
            n.d(fValueOf);
            return fValueOf.floatValue() > this.MIN_ALPHA;
        }
        if (!(getView().getState() instanceof DynamicIslandState.Expanded)) {
            return false;
        }
        DynamicIslandExpandedView expandedView = getView().getExpandedView();
        fValueOf = expandedView != null ? Float.valueOf(expandedView.getAlpha()) : null;
        n.d(fValueOf);
        return fValueOf.floatValue() > this.MIN_ALPHA;
    }

    private final void listenForClick(E e2) {
        AbstractC0369g.b(e2, null, null, new C06531(null), 3, null);
    }

    private final void listenForLongClick(E e2) {
        AbstractC0369g.b(e2, null, null, new C06541(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void listenForPressed(E e2) {
        AbstractC0369g.b(e2, null, null, new C06551(null), 3, null);
    }

    private final void log(E e2) {
        AbstractC0369g.b(e2, null, null, new C06561(null), 3, null);
    }

    private final void startExpose() {
        if (this.attached && this.isVisible && !this.isExposed) {
            this.isExposed = true;
            this._revealTime = System.currentTimeMillis();
            this.expoManager.startExpose(this);
        }
    }

    private final void stopExpose() {
        if (this.isExposed) {
            this.isExposed = false;
            this._actualTime = System.currentTimeMillis() - this._revealTime;
            this.expoManager.stopExpose(this);
        }
    }

    public final long getActualTime() {
        return this._actualTime;
    }

    public final String getIslandKey() {
        return getView().getIslandKey();
    }

    public final Integer getIslandProp() {
        return getView().getIslandProp();
    }

    public final StatusBarNotification getIslandSbn() {
        Bundle extras;
        DynamicIslandData currentIslandData = getView().getCurrentIslandData();
        if (currentIslandData == null || (extras = currentIslandData.getExtras()) == null) {
            return null;
        }
        return (StatusBarNotification) extras.getParcelable("miui.sbn", StatusBarNotification.class);
    }

    public final long getRevealTime() {
        return this._revealTime;
    }

    public final void onPreDraw() {
        if (!getView().getGlobalVisibleRect(this.rect) || !getView().isShown() || this.rect.width() <= 0 || this.rect.height() <= 0 || !currentIslandVisible()) {
            stopExpose();
        } else {
            if (getView().isAnimating()) {
                return;
            }
            startExpose();
        }
    }

    public final void onStateChange() {
        if (n.c(getView().getLastState(), getView().getState())) {
            return;
        }
        stopExpose();
    }

    public final void onViewAttached(boolean z2) {
        this.attached = z2;
        if (z2) {
            return;
        }
        stopExpose();
    }

    public final void onVisAggregated(boolean z2) {
        this.isVisible = z2;
        if (z2) {
            return;
        }
        stopExpose();
    }

    public final void onWindowFocusChanged(boolean z2) {
        this.hasWindowFocus = z2;
        if (z2) {
            return;
        }
        stopExpose();
    }
}
