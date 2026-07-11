package miui.systemui.dynamicisland.touch.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.os.Bundle;
import android.util.Log;
import g1.AbstractC0369g;
import g1.E;
import j1.InterfaceC0419g;
import j1.y;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.touch.TouchEvent;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor;
import miui.systemui.util.MotionEventKt;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandExternalTouchDispatcher implements DynamicIslandStartable {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DynamicIslandExternalTouchDispatcher";
    private final E scope;
    private final DynamicIslandWindowView windowView;
    private final DynamicIslandWindowViewTouchInteractor windowViewTouchInteractor;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchDispatcher$dispatchInterceptTouchEvent$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchDispatcher$dispatchInterceptTouchEvent$1", f = "DynamicIslandExternalTouchDispatcher.kt", l = {36}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalTouchDispatcher.this.new AnonymousClass1(dVar);
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
                y dispatchInterceptTouchEvent = DynamicIslandExternalTouchDispatcher.this.windowViewTouchInteractor.getDispatchInterceptTouchEvent();
                final DynamicIslandExternalTouchDispatcher dynamicIslandExternalTouchDispatcher = DynamicIslandExternalTouchDispatcher.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchDispatcher.dispatchInterceptTouchEvent.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(TouchEvent touchEvent, d dVar) {
                        Boolean toNullableBoolean;
                        Bundle bundle = new Bundle();
                        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_EXTERNAL_INTERCEPT);
                        bundle.putParcelable(DynamicIslandConstants.EXTRA_MOTION_EVENT, touchEvent.getEvent());
                        bundle.putString(DynamicIslandConstants.EXTRA_MOTION_EVENT_SOURCE, touchEvent.getSource());
                        Bundle bundleNotifyIslandViewChanged = dynamicIslandExternalTouchDispatcher.windowView.notifyIslandViewChanged(bundle);
                        if (bundleNotifyIslandViewChanged != null) {
                            toNullableBoolean = TouchEvent.Companion.getToNullableBoolean(bundleNotifyIslandViewChanged.getInt(DynamicIslandConstants.EXTRA_MOTION_EVENT_RESULT, -1));
                        } else {
                            toNullableBoolean = null;
                        }
                        touchEvent.setResult(toNullableBoolean);
                        Log.d(DynamicIslandExternalTouchDispatcher.TAG, "dispatch intercept " + MotionEventKt.getMotionEventAction(touchEvent.getEvent()) + " " + touchEvent.getResult());
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (dispatchInterceptTouchEvent.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchDispatcher$dispatchTouchEvent$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchDispatcher$dispatchTouchEvent$1", f = "DynamicIslandExternalTouchDispatcher.kt", l = {55}, m = "invokeSuspend")
    public static final class C06291 extends l implements Function2 {
        int label;

        public C06291(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalTouchDispatcher.this.new C06291(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06291) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                y dispatchTouchEvent = DynamicIslandExternalTouchDispatcher.this.windowViewTouchInteractor.getDispatchTouchEvent();
                final DynamicIslandExternalTouchDispatcher dynamicIslandExternalTouchDispatcher = DynamicIslandExternalTouchDispatcher.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchDispatcher.dispatchTouchEvent.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(TouchEvent touchEvent, d dVar) {
                        Boolean toNullableBoolean;
                        Bundle bundle = new Bundle();
                        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_EXTERNAL_TOUCH);
                        bundle.putParcelable(DynamicIslandConstants.EXTRA_MOTION_EVENT, touchEvent.getEvent());
                        bundle.putString(DynamicIslandConstants.EXTRA_MOTION_EVENT_SOURCE, touchEvent.getSource());
                        Bundle bundleNotifyIslandViewChanged = dynamicIslandExternalTouchDispatcher.windowView.notifyIslandViewChanged(bundle);
                        if (bundleNotifyIslandViewChanged != null) {
                            toNullableBoolean = TouchEvent.Companion.getToNullableBoolean(bundleNotifyIslandViewChanged.getInt(DynamicIslandConstants.EXTRA_MOTION_EVENT_RESULT, -1));
                        } else {
                            toNullableBoolean = null;
                        }
                        touchEvent.setResult(toNullableBoolean);
                        Log.d(DynamicIslandExternalTouchDispatcher.TAG, "dispatch touch " + MotionEventKt.getMotionEventAction(touchEvent.getEvent()) + " " + touchEvent.getResult());
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (dispatchTouchEvent.collect(interfaceC0419g, this) == objC) {
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

    public DynamicIslandExternalTouchDispatcher(@DynamicIsland E scope, DynamicIslandWindowView windowView, DynamicIslandWindowViewTouchInteractor windowViewTouchInteractor) {
        n.g(scope, "scope");
        n.g(windowView, "windowView");
        n.g(windowViewTouchInteractor, "windowViewTouchInteractor");
        this.scope = scope;
        this.windowView = windowView;
        this.windowViewTouchInteractor = windowViewTouchInteractor;
    }

    private final void dispatchInterceptTouchEvent() {
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null);
    }

    private final void dispatchTouchEvent() {
        AbstractC0369g.b(this.scope, null, null, new C06291(null), 3, null);
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        dispatchInterceptTouchEvent();
        dispatchTouchEvent();
    }
}
