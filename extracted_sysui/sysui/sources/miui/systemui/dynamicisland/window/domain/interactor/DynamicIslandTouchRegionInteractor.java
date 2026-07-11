package miui.systemui.dynamicisland.window.domain.interactor;

import E0.a;
import H0.d;
import H0.e;
import H0.k;
import H0.s;
import M0.c;
import N0.f;
import N0.l;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Region;
import android.os.SystemProperties;
import android.util.Log;
import android.view.ViewTreeObserver;
import g1.AbstractC0369g;
import g1.E;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.y;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.util.ConvenienceExtensionsKt;
import miui.systemui.util.coroutines.flow.FlowConflatedKt;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandTouchRegionInteractor implements DynamicIslandStartable {
    public static final Companion Companion = new Companion(null);
    private static final boolean DEBUG = SystemProperties.getBoolean("debug.sysui.notif.island.touch_region", false);
    private static final String TAG = "DynamicIslandTouchRegionInteractor";
    private final InterfaceC0418f computeInternalInsetsEvent;
    private final Context context;
    private final d eventCoordinator$delegate;
    private final d paint$delegate;
    private final E scope;
    private final d touchRegion$delegate;
    private final DynamicIslandWindowView windowView;
    private final d windowViewController$delegate;
    private final d windowViewTouchRegion$delegate;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$drawDebugTouchRegion$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$drawDebugTouchRegion$1", f = "DynamicIslandTouchRegionInteractor.kt", l = {128}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandTouchRegionInteractor.this.new AnonymousClass1(dVar);
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
                y dispatchDrawEvent = DynamicIslandTouchRegionInteractor.this.windowView.getDispatchDrawEvent();
                final DynamicIslandTouchRegionInteractor dynamicIslandTouchRegionInteractor = DynamicIslandTouchRegionInteractor.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor.drawDebugTouchRegion.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Canvas canvas, L0.d dVar) {
                        canvas.drawRect(((Region) dynamicIslandTouchRegionInteractor.getWindowViewTouchRegion().getValue()).getBounds(), dynamicIslandTouchRegionInteractor.getPaint());
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (dispatchDrawEvent.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$listenForTouchRegionChanged$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$listenForTouchRegionChanged$1", f = "DynamicIslandTouchRegionInteractor.kt", l = {108}, m = "invokeSuspend")
    public static final class C06581 extends l implements Function2 {
        int label;

        public C06581(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandTouchRegionInteractor.this.new C06581(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06581) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                I windowViewTouchRegion = DynamicIslandTouchRegionInteractor.this.getWindowViewTouchRegion();
                final DynamicIslandTouchRegionInteractor dynamicIslandTouchRegionInteractor = DynamicIslandTouchRegionInteractor.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor.listenForTouchRegionChanged.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Region region, L0.d dVar) {
                        if (DynamicIslandTouchRegionInteractor.DEBUG) {
                            Log.d(DynamicIslandTouchRegionInteractor.TAG, "requestApplyInsets " + region);
                        }
                        dynamicIslandTouchRegionInteractor.windowView.requestApplyInsets();
                        if (DynamicIslandTouchRegionInteractor.DEBUG) {
                            dynamicIslandTouchRegionInteractor.windowView.invalidate();
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (windowViewTouchRegion.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$updateInternalInsets$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$updateInternalInsets$1", f = "DynamicIslandTouchRegionInteractor.kt", l = {118}, m = "invokeSuspend")
    public static final class C06591 extends l implements Function2 {
        int label;

        public C06591(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandTouchRegionInteractor.this.new C06591(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06591) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                InterfaceC0418f interfaceC0418f = DynamicIslandTouchRegionInteractor.this.computeInternalInsetsEvent;
                final DynamicIslandTouchRegionInteractor dynamicIslandTouchRegionInteractor = DynamicIslandTouchRegionInteractor.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor.updateInternalInsets.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo, L0.d dVar) {
                        if (DynamicIslandTouchRegionInteractor.DEBUG) {
                            Log.d(DynamicIslandTouchRegionInteractor.TAG, "updateInternalInsets");
                        }
                        internalInsetsInfo.setTouchableInsets(3);
                        internalInsetsInfo.touchableRegion.set((Region) dynamicIslandTouchRegionInteractor.getWindowViewTouchRegion().getValue());
                        return s.f314a;
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

    public DynamicIslandTouchRegionInteractor(@DynamicIsland E scope, Context context, DynamicIslandWindowView windowView, a windowViewControllerLazy, a eventCoordinatorLazy) {
        n.g(scope, "scope");
        n.g(context, "context");
        n.g(windowView, "windowView");
        n.g(windowViewControllerLazy, "windowViewControllerLazy");
        n.g(eventCoordinatorLazy, "eventCoordinatorLazy");
        this.scope = scope;
        this.context = context;
        this.windowView = windowView;
        this.windowViewController$delegate = ConvenienceExtensionsKt.getKotlinLazy(windowViewControllerLazy);
        this.eventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(eventCoordinatorLazy);
        this.paint$delegate = e.b(DynamicIslandTouchRegionInteractor$paint$2.INSTANCE);
        this.touchRegion$delegate = e.b(new DynamicIslandTouchRegionInteractor$touchRegion$2(this));
        this.windowViewTouchRegion$delegate = e.b(new DynamicIslandTouchRegionInteractor$windowViewTouchRegion$2(this));
        this.computeInternalInsetsEvent = FlowConflatedKt.conflatedCallbackFlow(new DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1(this, null));
    }

    private final void drawDebugTouchRegion() {
        if (DEBUG) {
            AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DynamicIslandEventCoordinator getEventCoordinator() {
        return (DynamicIslandEventCoordinator) this.eventCoordinator$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Region getFullscreenRegion() {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        int screenWidth = dynamicIslandUtils.getScreenWidth(this.context);
        int screenHeight = dynamicIslandUtils.getScreenHeight(this.context);
        int navigationBarFrameHeight = dynamicIslandUtils.getNavigationBarFrameHeight();
        if (navigationBarFrameHeight == 0) {
            navigationBarFrameHeight = dynamicIslandUtils.dpToPx(48, this.context);
        }
        Log.d(TAG, "getFullScreenRegion: " + screenWidth + ", " + screenHeight + ", " + navigationBarFrameHeight);
        return new Region(82, 0, screenWidth - 82, screenHeight - navigationBarFrameHeight);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Paint getPaint() {
        return (Paint) this.paint$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DynamicIslandWindowViewController getWindowViewController() {
        return (DynamicIslandWindowViewController) this.windowViewController$delegate.getValue();
    }

    private final void listenForTouchRegionChanged() {
        AbstractC0369g.b(this.scope, null, null, new C06581(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Region mergeTouchRegions(Region... regionArr) {
        Region region = new Region();
        for (Region region2 : regionArr) {
            region.op(region2, Region.Op.UNION);
        }
        return region;
    }

    private final void updateInternalInsets() {
        AbstractC0369g.b(this.scope, null, null, new C06591(null), 3, null);
    }

    public final I getTouchRegion() {
        return (I) this.touchRegion$delegate.getValue();
    }

    public final I getWindowViewTouchRegion() {
        return (I) this.windowViewTouchRegion$delegate.getValue();
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        listenForTouchRegionChanged();
        updateInternalInsets();
        drawDebugTouchRegion();
    }
}
