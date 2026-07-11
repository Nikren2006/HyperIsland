package miui.systemui.dynamicisland.window;

import H0.s;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Binder;
import android.os.SystemProperties;
import android.util.Log;
import android.view.WindowManager;
import g1.AbstractC0369g;
import g1.E;
import g1.M;
import j1.I;
import j1.InterfaceC0419g;
import j1.y;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandWindowController implements DynamicIslandStartable {
    public static final Companion Companion = new Companion(null);
    private static final boolean DRAW_WINDOW_SIZE = SystemProperties.getBoolean("debug.sysui.notif.island.window_size", false);
    private static final String TAG = "DynamicIslandWindowController";
    private final Context context;
    private final WindowManager.LayoutParams lp;
    private final WindowManager.LayoutParams lpChanged;
    private final H0.d paint$delegate;
    private final E scope;
    private final WindowManager windowManager;
    private final DynamicIslandWindowStateInteractor windowStateInteractor;
    private final DynamicIslandWindowView windowView;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowController$attach$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowController$attach$1", f = "DynamicIslandWindowController.kt", l = {84}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowController.this.new AnonymousClass1(dVar);
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
                H0.k.b(obj);
                DynamicIslandWindowController.this.windowManager.addView(DynamicIslandWindowController.this.windowView, DynamicIslandWindowController.this.lp);
                DynamicIslandWindowController.this.lpChanged.copyFrom(DynamicIslandWindowController.this.lp);
                this.label = 1;
                if (M.a(this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowController$attach$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return s.f314a;
        }

        public final void invoke(Throwable th) {
            DynamicIslandWindowController.this.windowManager.removeView(DynamicIslandWindowController.this.windowView);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowController$drawDebugWindowSize$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowController$drawDebugWindowSize$1", f = "DynamicIslandWindowController.kt", l = {133}, m = "invokeSuspend")
    public static final class C06311 extends N0.l implements Function2 {
        int label;

        public C06311(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowController.this.new C06311(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06311) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                final Rect rect = new Rect();
                y dispatchDrawEvent = DynamicIslandWindowController.this.windowView.getDispatchDrawEvent();
                final DynamicIslandWindowController dynamicIslandWindowController = DynamicIslandWindowController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowController.drawDebugWindowSize.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Canvas canvas, L0.d dVar) {
                        dynamicIslandWindowController.windowView.getDrawingRect(rect);
                        canvas.drawRect(rect, dynamicIslandWindowController.getPaint());
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
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowController$listenForVisibility$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowController$listenForVisibility$1", f = "DynamicIslandWindowController.kt", l = {95}, m = "invokeSuspend")
    public static final class C06321 extends N0.l implements Function2 {
        int label;

        public C06321(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowController.this.new C06321(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06321) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                I visibility = DynamicIslandWindowController.this.windowStateInteractor.getVisibility();
                final DynamicIslandWindowController dynamicIslandWindowController = DynamicIslandWindowController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowController.listenForVisibility.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Number) obj2).intValue(), dVar);
                    }

                    public final Object emit(int i3, L0.d dVar) {
                        dynamicIslandWindowController.windowView.setVisibility(i3);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (visibility.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowController$listenForWatchOutsideTouch$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowController$listenForWatchOutsideTouch$1", f = "DynamicIslandWindowController.kt", l = {116}, m = "invokeSuspend")
    public static final class C06331 extends N0.l implements Function2 {
        int label;

        public C06331(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowController.this.new C06331(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06331) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                I watchOutsideTouch = DynamicIslandWindowController.this.windowStateInteractor.getWatchOutsideTouch();
                final DynamicIslandWindowController dynamicIslandWindowController = DynamicIslandWindowController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowController.listenForWatchOutsideTouch.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) {
                        Log.d(DynamicIslandWindowController.TAG, "update watch outside touch to " + z2);
                        dynamicIslandWindowController.lpChanged.flags = z2 ? dynamicIslandWindowController.lp.flags | 262144 : dynamicIslandWindowController.lp.flags & (-262145);
                        dynamicIslandWindowController.apply();
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (watchOutsideTouch.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowController$listenForWindowHeight$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowController$listenForWindowHeight$1", f = "DynamicIslandWindowController.kt", l = {106}, m = "invokeSuspend")
    public static final class C06341 extends N0.l implements Function2 {
        int label;

        public C06341(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowController.this.new C06341(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06341) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                I windowHeight = DynamicIslandWindowController.this.windowStateInteractor.getWindowHeight();
                final DynamicIslandWindowController dynamicIslandWindowController = DynamicIslandWindowController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowController.listenForWindowHeight.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Number) obj2).intValue(), dVar);
                    }

                    public final Object emit(int i3, L0.d dVar) {
                        Log.d(DynamicIslandWindowController.TAG, "update window height " + i3);
                        dynamicIslandWindowController.lpChanged.height = i3;
                        dynamicIslandWindowController.apply();
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (windowHeight.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    public DynamicIslandWindowController(@DynamicIsland E scope, DynamicIslandWindowView windowView, Context context, DynamicIslandWindowStateInteractor windowStateInteractor) {
        kotlin.jvm.internal.n.g(scope, "scope");
        kotlin.jvm.internal.n.g(windowView, "windowView");
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(windowStateInteractor, "windowStateInteractor");
        this.scope = scope;
        this.windowView = windowView;
        this.context = context;
        this.windowStateInteractor = windowStateInteractor;
        Object systemService = context.getSystemService("window");
        kotlin.jvm.internal.n.e(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        this.windowManager = (WindowManager) systemService;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, 0, 2009, -2147483608, -3);
        layoutParams.privateFlags |= 553648128;
        layoutParams.token = new Binder();
        layoutParams.gravity = 48;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.setTitle("DynamicIslandWindow");
        layoutParams.packageName = context.getPackageName();
        layoutParams.layoutInDisplayCutoutMode = 3;
        this.lp = layoutParams;
        this.lpChanged = new WindowManager.LayoutParams();
        this.paint$delegate = H0.e.b(DynamicIslandWindowController$paint$2.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void apply() {
        if (this.lp.copyFrom(this.lpChanged) != 0) {
            this.windowManager.updateViewLayout(this.windowView, this.lp);
        }
    }

    private final void attach() {
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null).l(new AnonymousClass2());
    }

    private final void drawDebugWindowSize() {
        if (DRAW_WINDOW_SIZE) {
            AbstractC0369g.b(this.scope, null, null, new C06311(null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Paint getPaint() {
        return (Paint) this.paint$delegate.getValue();
    }

    private final void listenForVisibility() {
        AbstractC0369g.b(this.scope, null, null, new C06321(null), 3, null);
    }

    private final void listenForWatchOutsideTouch() {
        AbstractC0369g.b(this.scope, null, null, new C06331(null), 3, null);
    }

    private final void listenForWindowHeight() {
        AbstractC0369g.b(this.scope, null, null, new C06341(null), 3, null);
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        attach();
        listenForVisibility();
        listenForWindowHeight();
        listenForWatchOutsideTouch();
        drawDebugWindowSize();
    }
}
