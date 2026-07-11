package miui.systemui.dynamicisland.window;

import H0.s;
import I0.u;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Parcelable;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.core.view.OneShotPreDrawListener;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandContent;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import com.android.systemui.settings.UserTracker;
import com.miui.circulate.device.api.Column;
import com.xiaomi.onetrack.util.aa;
import g1.AbstractC0369g;
import g1.E;
import j1.A;
import j1.AbstractC0420h;
import j1.I;
import j1.InterfaceC0419g;
import j1.t;
import j1.y;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate;
import miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.WindowAnimState;
import miui.systemui.dynamicisland.event.handler.AppStateHandler;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.event.handler.ExpandedStateHandler;
import miui.systemui.dynamicisland.event.handler.MiniWindowStateHandler;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.touch.TouchEvent;
import miui.systemui.dynamicisland.view.DynamicIslandBigIslandView;
import miui.systemui.dynamicisland.view.DynamicIslandExpandedView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController;
import miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.FoldUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.ReflectBuilderUtil;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowView extends FrameLayout implements LifecycleOwner, UserTracker.Callback {
    public static final Companion Companion = new Companion(null);
    private static final int SWIPE_THRESHOLD = 100;
    private static final String TAG = "DynamicIslandWindowViewImpl";
    private final t _dispatchDrawEvent;
    private int _headsUpHeight;
    private final t _onInterceptTouchEvent;
    private final t _onTouchEvent;
    private DynamicIslandViewComponent _viewComponent;
    private CopyOnWriteArrayList<DynamicIslandContentView> contentViewList;
    private boolean currentKeyguardShowing;
    private int currentUserId;
    private final y dispatchDrawEvent;
    private final List<DynamicIslandData> dynamicIslandDataList;
    private DynamicIslandEventCoordinator eventCoordinator;
    private CopyOnWriteArrayList<DynamicIslandContentFakeView> fakeViewList;
    private boolean isLight;
    private final H0.d lifecycle$delegate;
    private DynamicIslandContent.DynamicIslandViewChangedListener listener;
    private Runnable longPress;
    private ViewGroup mGlowEffectBottomContainer;
    private ViewGroup mGlowEffectTopContainer;
    private final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private final y onInterceptTouchEvent;
    private final y onTouchEvent;
    private Configuration previousConfig;
    private boolean touchOutsideInHeadsUp;
    private final H0.d windowViewController$delegate;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowView$buildDynamicIslandContentView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function1 {
        final /* synthetic */ DynamicIslandData $dynamicIslandData;
        final /* synthetic */ DynamicIslandWindowView this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandData dynamicIslandData, DynamicIslandWindowView dynamicIslandWindowView) {
            super(1);
            this.$dynamicIslandData = dynamicIslandData;
            this.this$0 = dynamicIslandWindowView;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(DynamicIslandContentView dynamicIslandContentView) {
            DynamicIslandData currentIslandData = dynamicIslandContentView.getCurrentIslandData();
            boolean zC = kotlin.jvm.internal.n.c(currentIslandData != null ? currentIslandData.getKey() : null, this.$dynamicIslandData.getKey());
            if (zC) {
                this.this$0.removeView(dynamicIslandContentView.getBackgroundView());
                dynamicIslandContentView.getController().destroy();
            }
            return Boolean.valueOf(zC);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowView$dispatchDraw$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowView$dispatchDraw$1", f = "DynamicIslandWindowView.kt", l = {1136}, m = "invokeSuspend")
    public static final class C06351 extends N0.l implements Function2 {
        final /* synthetic */ Canvas $canvas;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06351(Canvas canvas, L0.d dVar) {
            super(2, dVar);
            this.$canvas = canvas;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowView.this.new C06351(this.$canvas, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06351) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                t tVar = DynamicIslandWindowView.this._dispatchDrawEvent;
                Canvas canvas = this.$canvas;
                this.label = 1;
                if (tVar.emit(canvas, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowView$initEventCoordinator$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowView$initEventCoordinator$1", f = "DynamicIslandWindowView.kt", l = {283}, m = "invokeSuspend")
    public static final class C06361 extends N0.l implements Function2 {
        int label;

        public C06361(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowView.this.new C06361(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06361) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            I islandRegion;
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                DynamicIslandEventCoordinator eventCoordinator = DynamicIslandWindowView.this.getEventCoordinator();
                if (eventCoordinator == null || (islandRegion = eventCoordinator.getIslandRegion()) == null) {
                    return s.f314a;
                }
                final DynamicIslandWindowView dynamicIslandWindowView = DynamicIslandWindowView.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowView.initEventCoordinator.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Region region, L0.d dVar) {
                        boolean zIsToScreenLockNoAnimation = dynamicIslandWindowView.getWindowViewController().getWindowState().isToScreenLockNoAnimation();
                        Log.e(DynamicIslandWindowView.TAG, "islandRegion " + region + " " + zIsToScreenLockNoAnimation);
                        Bundle bundle = new Bundle();
                        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_BACK_ISLAND_WIDTH_CHANGED);
                        bundle.putBoolean(DynamicIslandConstants.EXTRA_BACK_CHANGE_ISLAND_REGION_NO_ANIM, zIsToScreenLockNoAnimation);
                        bundle.putParcelable(DynamicIslandConstants.EXTRA_BACK_ISLAND_REGION, region);
                        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = dynamicIslandWindowView.listener;
                        if (dynamicIslandViewChangedListener != null) {
                            dynamicIslandViewChangedListener.onIslandViewChanged(bundle);
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (islandRegion.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowView$initEventCoordinator$2, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowView$initEventCoordinator$2", f = "DynamicIslandWindowView.kt", l = {294}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends N0.l implements Function2 {
        int label;

        public AnonymousClass2(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowView.this.new AnonymousClass2(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            I bigIslandRegion;
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                DynamicIslandEventCoordinator eventCoordinator = DynamicIslandWindowView.this.getEventCoordinator();
                if (eventCoordinator == null || (bigIslandRegion = eventCoordinator.getBigIslandRegion()) == null) {
                    return s.f314a;
                }
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowView.initEventCoordinator.2.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Region region, L0.d dVar) {
                        Log.e(DynamicIslandWindowView.TAG, "bigIslandRegion " + region);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (bigIslandRegion.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowView$initEventCoordinator$3, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowView$initEventCoordinator$3", f = "DynamicIslandWindowView.kt", l = {299}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends N0.l implements Function2 {
        int label;

        public AnonymousClass3(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowView.this.new AnonymousClass3(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass3) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            I expandedViewRegion;
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                DynamicIslandEventCoordinator eventCoordinator = DynamicIslandWindowView.this.getEventCoordinator();
                if (eventCoordinator == null || (expandedViewRegion = eventCoordinator.getExpandedViewRegion()) == null) {
                    return s.f314a;
                }
                final DynamicIslandWindowView dynamicIslandWindowView = DynamicIslandWindowView.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowView.initEventCoordinator.3.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Region region, L0.d dVar) {
                        Log.e(DynamicIslandWindowView.TAG, "expandedViewRegion " + region);
                        DynamicIslandEventCoordinator eventCoordinator2 = dynamicIslandWindowView.getEventCoordinator();
                        if (eventCoordinator2 != null) {
                            eventCoordinator2.handleExpandGestureListener(region);
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (expandedViewRegion.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowView$initEventCoordinator$4, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowView$initEventCoordinator$4", f = "DynamicIslandWindowView.kt", l = {305}, m = "invokeSuspend")
    public static final class AnonymousClass4 extends N0.l implements Function2 {
        int label;

        public AnonymousClass4(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowView.this.new AnonymousClass4(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass4) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            I statusBarVisible;
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                DynamicIslandEventCoordinator eventCoordinator = DynamicIslandWindowView.this.getEventCoordinator();
                if (eventCoordinator == null || (statusBarVisible = eventCoordinator.getStatusBarVisible()) == null) {
                    return s.f314a;
                }
                final DynamicIslandWindowView dynamicIslandWindowView = DynamicIslandWindowView.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowView.initEventCoordinator.4.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Boolean bool, L0.d dVar) {
                        DynamicIslandEventCoordinator eventCoordinator2;
                        DynamicIslandEventCoordinator eventCoordinator3 = dynamicIslandWindowView.getEventCoordinator();
                        if (eventCoordinator3 != null) {
                            eventCoordinator3.updateTouchRegion();
                        }
                        if (kotlin.jvm.internal.n.c(bool, N0.b.a(false)) && dynamicIslandWindowView.getCurrentExpandedState() == null && (eventCoordinator2 = dynamicIslandWindowView.getEventCoordinator()) != null) {
                            eventCoordinator2.resetSwipe(0.0f, 0.0f);
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (statusBarVisible.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowView$onInterceptTouchEvent$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowView$onInterceptTouchEvent$1", f = "DynamicIslandWindowView.kt", l = {1041}, m = "invokeSuspend")
    public static final class C06371 extends N0.l implements Function2 {
        final /* synthetic */ TouchEvent $touchEvent;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06371(TouchEvent touchEvent, L0.d dVar) {
            super(2, dVar);
            this.$touchEvent = touchEvent;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowView.this.new C06371(this.$touchEvent, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06371) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                t tVar = DynamicIslandWindowView.this._onInterceptTouchEvent;
                TouchEvent touchEvent = this.$touchEvent;
                this.label = 1;
                if (tVar.emit(touchEvent, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowView$onTouchEvent$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowView$onTouchEvent$1", f = "DynamicIslandWindowView.kt", l = {1051}, m = "invokeSuspend")
    public static final class C06381 extends N0.l implements Function2 {
        final /* synthetic */ TouchEvent $touchEvent;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06381(TouchEvent touchEvent, L0.d dVar) {
            super(2, dVar);
            this.$touchEvent = touchEvent;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowView.this.new C06381(this.$touchEvent, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06381) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                t tVar = DynamicIslandWindowView.this._onTouchEvent;
                TouchEvent touchEvent = this.$touchEvent;
                this.label = 1;
                if (tVar.emit(touchEvent, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowView$updateViewStateWhenCloseEnd$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowView$updateViewStateWhenCloseEnd$1", f = "DynamicIslandWindowView.kt", l = {}, m = "invokeSuspend")
    public static final class C06391 extends N0.l implements Function2 {
        final /* synthetic */ String $from;
        final /* synthetic */ boolean $isFreeform;
        final /* synthetic */ DynamicIslandContentView $state;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06391(DynamicIslandContentView dynamicIslandContentView, boolean z2, String str, L0.d dVar) {
            super(2, dVar);
            this.$state = dynamicIslandContentView;
            this.$isFreeform = z2;
            this.$from = str;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new C06391(this.$state, this.$isFreeform, this.$from, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06391) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            DynamicIslandContentFakeView fakeView;
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            DynamicIslandContentView dynamicIslandContentView = this.$state;
            if (dynamicIslandContentView != null) {
                dynamicIslandContentView.updateViewStateWhenCloseEnd();
            }
            DynamicIslandContentView dynamicIslandContentView2 = this.$state;
            if (dynamicIslandContentView2 != null && (fakeView = dynamicIslandContentView2.getFakeView()) != null) {
                fakeView.updateViewStateWhenCloseEnd(this.$isFreeform, this.$from);
            }
            return s.f314a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandWindowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        kotlin.jvm.internal.n.g(context, "context");
        this.windowViewController$delegate = H0.e.b(new DynamicIslandWindowView$windowViewController$2(this));
        this.contentViewList = new CopyOnWriteArrayList<>();
        this.fakeViewList = new CopyOnWriteArrayList<>();
        this.dynamicIslandDataList = new ArrayList();
        this.currentUserId = ReflectBuilderUtil.getCurrentUserId();
        t tVarB = A.b(0, 0, null, 7, null);
        this._dispatchDrawEvent = tVarB;
        this.dispatchDrawEvent = AbstractC0420h.a(tVarB);
        this.lifecycle$delegate = H0.e.b(new DynamicIslandWindowView$lifecycle$2(this));
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: miui.systemui.dynamicisland.window.n
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                DynamicIslandWindowView.onGlobalLayoutListener$lambda$2(this.f5754a);
            }
        };
        this.onGlobalLayoutListener = onGlobalLayoutListener;
        getLifecycle().setCurrentState(Lifecycle.State.CREATED);
        getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        this.previousConfig = new Configuration(getResources().getConfiguration());
        updateBlurContainer(MiBlurCompat.getBackgroundBlurOpened(context));
        t tVarB2 = A.b(0, 0, null, 7, null);
        this._onInterceptTouchEvent = tVarB2;
        t tVarB3 = A.b(0, 0, null, 7, null);
        this._onTouchEvent = tVarB3;
        this.onInterceptTouchEvent = AbstractC0420h.a(tVarB2);
        this.onTouchEvent = AbstractC0420h.a(tVarB3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _set_headsUpHeight_$lambda$1(DynamicIslandWindowView this$0) throws PendingIntent.CanceledException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.collapse("heads up");
    }

    public static /* synthetic */ void addDynamicIslandData$default(DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandData dynamicIslandData, boolean z2, float f2, float f3, boolean z3, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            z3 = true;
        }
        dynamicIslandWindowView.addDynamicIslandData(dynamicIslandData, z2, f2, f3, z3);
    }

    private final boolean assertUserSpace(DynamicIslandData dynamicIslandData) {
        Bundle extras;
        Integer numValueOf = (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : Integer.valueOf(extras.getInt("miui.user.id"));
        int i2 = this.currentUserId;
        boolean z2 = true;
        if (i2 != 0 ? !(numValueOf != null && numValueOf.intValue() == i2) : !((numValueOf != null && numValueOf.intValue() == 0) || (numValueOf != null && numValueOf.intValue() == 999))) {
            z2 = false;
        }
        Log.e(TAG, "assert: currentUserId=" + this.currentUserId + ", appUid=" + numValueOf + ", result=" + z2);
        return z2;
    }

    private final DynamicIslandContentView buildDynamicIslandContentView(DynamicIslandData dynamicIslandData, float f2, float f3) {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dynamic_island_view, (ViewGroup) this, false);
        kotlin.jvm.internal.n.e(viewInflate, "null cannot be cast to non-null type miui.systemui.dynamicisland.DynamicIslandBackgroundView");
        DynamicIslandBackgroundView dynamicIslandBackgroundView = (DynamicIslandBackgroundView) viewInflate;
        dynamicIslandBackgroundView.setLayoutDirection(getContext().getResources().getConfiguration().getLayoutDirection());
        DynamicIslandContentView dynamicIslandContentView = (DynamicIslandContentView) dynamicIslandBackgroundView.requireViewById(R.id.island_content);
        DynamicIslandContentViewController.Factory contentViewControllerFactory = getViewComponent().getContentViewControllerFactory();
        kotlin.jvm.internal.n.d(dynamicIslandContentView);
        dynamicIslandContentView.setController(contentViewControllerFactory.create(dynamicIslandContentView));
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        kotlin.jvm.internal.n.f(getContext(), "getContext(...)");
        dynamicIslandContentView.setTranslationX(dynamicIslandUtils.getScreenWidthOld(r1) / 2.0f);
        kotlin.jvm.internal.n.f(getContext(), "getContext(...)");
        dynamicIslandContentView.setTranslationY(-dynamicIslandUtils.getScreenHeightOld(r1));
        dynamicIslandContentView.setBackgroundView(dynamicIslandBackgroundView);
        addView(dynamicIslandBackgroundView);
        CopyOnWriteArrayList<DynamicIslandContentView> copyOnWriteArrayList = this.contentViewList;
        final AnonymousClass1 anonymousClass1 = new AnonymousClass1(dynamicIslandData, this);
        copyOnWriteArrayList.removeIf(new Predicate() { // from class: miui.systemui.dynamicisland.window.l
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DynamicIslandWindowView.buildDynamicIslandContentView$lambda$19(anonymousClass1, obj);
            }
        });
        this.contentViewList.add(dynamicIslandContentView);
        onDynamicIslandDataChanged();
        DynamicIslandBaseContentViewBinder.INSTANCE.bind(dynamicIslandContentView, getViewComponent().getSizeRepository());
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        kotlin.jvm.internal.n.d(dynamicIslandEventCoordinator);
        dynamicIslandContentView.setEventHandler(dynamicIslandEventCoordinator);
        if (DynamicIslandBaseContentView.updateView$default(dynamicIslandContentView, dynamicIslandData, false, false, 4, null)) {
            return dynamicIslandContentView;
        }
        this.contentViewList.remove(dynamicIslandContentView);
        removeView(dynamicIslandBackgroundView);
        dynamicIslandContentView.getController().destroy();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean buildDynamicIslandContentView$lambda$19(Function1 tmp0, Object obj) {
        kotlin.jvm.internal.n.g(tmp0, "$tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    private final DynamicIslandContentFakeView buildDynamicIslandFakeContentView(DynamicIslandData dynamicIslandData, float f2, float f3) {
        if (DynamicIslandAnimUtils.INSTANCE.featureDynamicIslandNoNeedFakeView()) {
            return null;
        }
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dynamic_island_fake_content_view, (ViewGroup) this, false);
        kotlin.jvm.internal.n.e(viewInflate, "null cannot be cast to non-null type miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView");
        DynamicIslandContentFakeView dynamicIslandContentFakeView = (DynamicIslandContentFakeView) viewInflate;
        addView(dynamicIslandContentFakeView);
        dynamicIslandContentFakeView.setController(getViewComponent().getBaseContentViewControllerFactory().create(dynamicIslandContentFakeView));
        this.fakeViewList.add(dynamicIslandContentFakeView);
        DynamicIslandBaseContentViewBinder.INSTANCE.bind(dynamicIslandContentFakeView, getViewComponent().getSizeRepository());
        DynamicIslandBaseContentView.updateView$default(dynamicIslandContentFakeView, dynamicIslandData, false, false, 4, null);
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        kotlin.jvm.internal.n.d(dynamicIslandEventCoordinator);
        dynamicIslandContentFakeView.setEventHandler(dynamicIslandEventCoordinator);
        return dynamicIslandContentFakeView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean canUpdate(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandContentFakeView fakeView;
        I trackingToOpenMW;
        return (dynamicIslandContentView == null || (fakeView = dynamicIslandContentView.getFakeView()) == null || (trackingToOpenMW = fakeView.getTrackingToOpenMW()) == null || !((Boolean) trackingToOpenMW.getValue()).booleanValue()) && !((Boolean) getWindowViewController().isFreeformAnimRunning().getValue()).booleanValue();
    }

    public static /* synthetic */ void clearAfterDelete$default(DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandData dynamicIslandData, String str, boolean z2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        dynamicIslandWindowView.clearAfterDelete(dynamicIslandData, str, z2);
    }

    private final boolean compareConfigurations(Configuration configuration, Configuration configuration2) {
        if (configuration2 != null) {
            int i2 = configuration.orientation;
        }
        kotlin.jvm.internal.n.c(configuration.locale, configuration2 != null ? configuration2.locale : null);
        kotlin.jvm.internal.n.a(configuration.fontScale, configuration2 != null ? Float.valueOf(configuration2.fontScale) : null);
        return configuration2 == null || configuration.getLayoutDirection() != configuration2.getLayoutDirection() || configuration2 == null || configuration.densityDpi != configuration2.densityDpi;
    }

    public static /* synthetic */ boolean downInMedia$default(DynamicIslandWindowView dynamicIslandWindowView, Context context, float f2, float f3, View view, boolean z2, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            z2 = false;
        }
        return dynamicIslandWindowView.downInMedia(context, f2, f3, view, z2);
    }

    private final Integer getTargetResourceId(Context context, boolean z2) {
        String str = z2 ? "media_progress_bar" : "mi_media_controls";
        Resources resources = context.getResources();
        Integer numValueOf = resources != null ? Integer.valueOf(resources.getIdentifier(str, Column.ID, "com.android.systemui")) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            return null;
        }
        return numValueOf;
    }

    private final int getWindowInsetsRotation(WindowInsets windowInsets) {
        DisplayCutout displayCutout;
        if (windowInsets == null || (displayCutout = windowInsets.getDisplayCutout()) == null) {
            return -1;
        }
        if (displayCutout.getSafeInsetRight() > 0) {
            return 0;
        }
        if (displayCutout.getSafeInsetTop() > 0) {
            return 1;
        }
        if (displayCutout.getSafeInsetLeft() > 0) {
            return 2;
        }
        return displayCutout.getSafeInsetBottom() > 0 ? 3 : -1;
    }

    private final boolean hasDeviceNotification() {
        Integer properties;
        Integer properties2;
        List<DynamicIslandData> list = this.dynamicIslandDataList;
        if (list == null || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DynamicIslandData dynamicIslandData = (DynamicIslandData) it.next();
                if (dynamicIslandData != null && (properties = dynamicIslandData.getProperties()) != null && properties.intValue() == 0) {
                    CopyOnWriteArrayList<DynamicIslandContentView> copyOnWriteArrayList = this.contentViewList;
                    if (copyOnWriteArrayList == null || !copyOnWriteArrayList.isEmpty()) {
                        Iterator<T> it2 = copyOnWriteArrayList.iterator();
                        while (it2.hasNext()) {
                            DynamicIslandData currentIslandData = ((DynamicIslandContentView) it2.next()).getCurrentIslandData();
                            if (currentIslandData != null && (properties2 = currentIslandData.getProperties()) != null && properties2.intValue() == 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasSamePkgEnterFreeFrom(String str) {
        List<DynamicIslandContentView> listRequestHasIsland = str != null ? requestHasIsland(str) : null;
        if (listRequestHasIsland == null) {
            return false;
        }
        for (DynamicIslandContentView dynamicIslandContentView : listRequestHasIsland) {
            if (dynamicIslandContentView != null && (dynamicIslandContentView.getWindowAnimState() instanceof WindowAnimState.Opening)) {
                return true;
            }
        }
        return false;
    }

    private final void initEventCoordinator() {
        this.eventCoordinator = getViewComponent().getDynamicIslandEventCoordinator();
        AbstractC0369g.b(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C06361(null), 3, null);
        AbstractC0369g.b(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass2(null), 3, null);
        AbstractC0369g.b(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass3(null), 3, null);
        AbstractC0369g.b(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass4(null), 3, null);
    }

    private final boolean isDisableShare() {
        return ((Boolean) getWindowViewController().getWindowState().getControlCenterExpanded().getValue()).booleanValue() || this.currentKeyguardShowing;
    }

    private final boolean isTinyScreen() {
        return getWindowViewController().getWindowState().isTinyScreen();
    }

    private final boolean isTouchInsideRect(float f2, float f3, Rect rect) {
        return rect != null && f2 >= ((float) rect.left) && f2 <= ((float) rect.right) && f3 >= ((float) rect.top) && f3 <= ((float) rect.bottom);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void maybeCollapseExpand$lambda$12(DynamicIslandWindowView this$0) throws PendingIntent.CanceledException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.collapse("input monitor");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void measureExpandedViewHeight(DynamicIslandBaseContentView dynamicIslandBaseContentView) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(dynamicIslandBaseContentView.getExpandedViewMaxWidth(), Integer.MIN_VALUE);
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(dynamicIslandUtils.getScreenHeightOld(context), Integer.MIN_VALUE);
        DynamicIslandExpandedView expandedView = dynamicIslandBaseContentView.getExpandedView();
        if (expandedView != null) {
            expandedView.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        }
        DynamicIslandExpandedView expandedView2 = dynamicIslandBaseContentView.getExpandedView();
        Integer numValueOf = expandedView2 != null ? Integer.valueOf(expandedView2.getWidth()) : null;
        DynamicIslandExpandedView expandedView3 = dynamicIslandBaseContentView.getExpandedView();
        Integer numValueOf2 = expandedView3 != null ? Integer.valueOf(expandedView3.getHeight()) : null;
        DynamicIslandExpandedView expandedView4 = dynamicIslandBaseContentView.getExpandedView();
        Integer numValueOf3 = expandedView4 != null ? Integer.valueOf(expandedView4.getMeasuredWidth()) : null;
        DynamicIslandExpandedView expandedView5 = dynamicIslandBaseContentView.getExpandedView();
        Log.e(TAG, "expanded" + numValueOf + ", " + numValueOf2 + aa.f3429b + numValueOf3 + ", " + (expandedView5 != null ? Integer.valueOf(expandedView5.getMeasuredHeight()) : null));
        DynamicIslandData currentIslandData = dynamicIslandBaseContentView.getCurrentIslandData();
        if ((currentIslandData != null ? currentIslandData.getView() : null) != null) {
            DynamicIslandData currentIslandData2 = dynamicIslandBaseContentView.getCurrentIslandData();
            DynamicIslandExpandedView expandedView6 = dynamicIslandBaseContentView.getExpandedView();
            updateExpandedView(dynamicIslandBaseContentView, currentIslandData2, expandedView6 != null ? Integer.valueOf(expandedView6.getMeasuredHeight()) : null);
        }
    }

    private final void onDeviceNotificationChanged(boolean z2) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener;
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_ISLAND_DEVICE_NOTIFICATION_CHANGED);
        bundle.putBoolean(DynamicIslandConstants.EXTRA_DEVICE_NOTIFICATION_ADD, z2);
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (dynamicIslandViewChangedListener = dynamicIslandEventCoordinator.getDynamicIslandViewChangedListener()) == null) {
            return;
        }
        dynamicIslandViewChangedListener.onIslandViewChanged(bundle);
    }

    private final void onDynamicIslandDataChanged() {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener;
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_ISLAND_DATA_CHANGED);
        bundle.putInt(DynamicIslandConstants.EXTRA_DATA_SIZE, this.contentViewList.size());
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (dynamicIslandViewChangedListener = dynamicIslandEventCoordinator.getDynamicIslandViewChangedListener()) == null) {
            return;
        }
        dynamicIslandViewChangedListener.onIslandViewChanged(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onGlobalLayoutListener$lambda$2(DynamicIslandWindowView this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.getWindowViewController().setCurrentHeight(this$0.getHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onLongPress$lambda$35(DynamicIslandBaseContentView view, DynamicIslandContentView dynamicIslandContentView, float f2) {
        kotlin.jvm.internal.n.g(view, "$view");
        DynamicIslandBaseContentViewController<?> dynamicIslandBaseContentViewController = view.get_controller();
        if (dynamicIslandBaseContentViewController != null) {
            dynamicIslandBaseContentViewController.onLongPressed(view, dynamicIslandContentView != null ? dynamicIslandContentView.getCurrentIslandData() : null, f2);
        }
    }

    public static /* synthetic */ void removeDynamicIslandData$default(DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandData dynamicIslandData, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        dynamicIslandWindowView.removeDynamicIslandData(dynamicIslandData, z2);
    }

    private final void removeTempShowBigIslandOnFlipTinyChanged() {
        if (CommonUtils.isFlipDevice()) {
            if (getWindowViewController().getWindowState().isTinyScreen() == getWindowViewController().getWindowState().getLastTinyScreenStatus() && getWindowViewController().getLastDisplayOrientation() == getWindowViewController().getDisplayOrientation()) {
                return;
            }
            isTempShowBigIslandToBeRemoved();
        }
    }

    private final Float requestCutoutY() {
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_BACK_REQUEST_CUTOUT_Y);
        float dimension = getContext().getResources().getDimension(R.dimen.island_height);
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.listener;
        Bundle bundleOnIslandViewChanged = dynamicIslandViewChangedListener != null ? dynamicIslandViewChangedListener.onIslandViewChanged(bundle) : null;
        if (bundleOnIslandViewChanged == null) {
            return null;
        }
        float tinyScreenInsetTop = bundleOnIslandViewChanged.getFloat(DynamicIslandConstants.EXTRA_BACK_REQUEST_CUTOUT_Y);
        if (FlipUtils.isFlipTiny()) {
            tinyScreenInsetTop += DynamicIslandUtils.INSTANCE.getTinyScreenInsetTop();
        }
        Log.e(TAG, "requestCutoutY " + tinyScreenInsetTop);
        return CommonUtils.isNotchScreenDevice() ? Float.valueOf(dimension / 2) : Float.valueOf(tinyScreenInsetTop);
    }

    private final void requestImmersiveMode() {
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_BACK_REQUEST_IMMERSIVE_MODE);
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.listener;
        Bundle bundleOnIslandViewChanged = dynamicIslandViewChangedListener != null ? dynamicIslandViewChangedListener.onIslandViewChanged(bundle) : null;
        if (bundleOnIslandViewChanged != null) {
            Log.e(TAG, "requestImmersiveMode " + bundleOnIslandViewChanged.getBoolean(DynamicIslandConstants.EXTRA_BACK_REQUEST_IMMERSIVE_MODE));
        }
    }

    private final Float requestMaxIslandWidth() {
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_BACK_REQUEST_MAX_WIDTH);
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.listener;
        Bundle bundleOnIslandViewChanged = dynamicIslandViewChangedListener != null ? dynamicIslandViewChangedListener.onIslandViewChanged(bundle) : null;
        if (bundleOnIslandViewChanged == null) {
            return null;
        }
        float f2 = bundleOnIslandViewChanged.getFloat(DynamicIslandConstants.EXTRA_ISLAND_MAX_WIDTH);
        Log.e(TAG, "requestMaxIslandWidth " + f2);
        return Float.valueOf(f2);
    }

    private final void sendExitPendingIntent(Bundle bundle) throws PendingIntent.CanceledException {
        if (bundle == null || !bundle.containsKey("miui.exitFloating")) {
            return;
        }
        Log.e(TAG, "sendExitPendingIntent: ");
        Parcelable parcelable = bundle.getParcelable("miui.exitFloating");
        if (parcelable instanceof PendingIntent) {
            ((PendingIntent) parcelable).send();
        }
    }

    private final void updateBlurContainer(boolean z2) {
        MiBlurCompat.setMiBackgroundBlurModeCompat(this, z2 ? 1 : 0);
        MiBlurCompat.setMiBackgroundBlurRadiusCompat(this, z2 ? 100 : 0);
        MiBlurCompat.setPassWindowBlurEnabledCompat(this, z2);
    }

    private final void updateDynamicIslandDataList(DynamicIslandData dynamicIslandData) {
        boolean z2;
        Iterator<DynamicIslandData> it = this.dynamicIslandDataList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            } else if (kotlin.jvm.internal.n.c(it.next().getKey(), dynamicIslandData.getKey())) {
                it.remove();
                z2 = true;
                break;
            }
        }
        Log.e(TAG, "updateDynamicIslandDataList: " + dynamicIslandData.getKey() + "   " + z2);
        if (z2) {
            this.dynamicIslandDataList.add(dynamicIslandData);
        }
        onDeviceNotificationChanged(hasDeviceNotification());
    }

    private final void updateExpandedView(DynamicIslandBaseContentView dynamicIslandBaseContentView, DynamicIslandData dynamicIslandData, Integer num) {
        View view;
        DynamicIslandExpandedView expandedView;
        DynamicIslandExpandedView expandedView2;
        View view2;
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.expanded_min_height);
        Log.d(TAG, "height " + num + " " + ((dynamicIslandData == null || (view2 = dynamicIslandData.getView()) == null) ? null : Integer.valueOf(view2.getHeight())) + " " + dimensionPixelSize);
        int iIntValue = num != null ? num.intValue() : (dynamicIslandData == null || (view = dynamicIslandData.getView()) == null) ? 0 : view.getHeight();
        if ((dynamicIslandData != null ? dynamicIslandData.getView() : null) != null) {
            if (isMediaApp(dynamicIslandData)) {
                if (dynamicIslandBaseContentView != null) {
                    dynamicIslandBaseContentView.updateExpandedSize(dynamicIslandBaseContentView.getExpandedViewMaxWidth(), dynamicIslandBaseContentView.getExpandedViewMaxHeight(), dynamicIslandData);
                    return;
                }
                return;
            }
            if ((iIntValue > 0 ? iIntValue : dimensionPixelSize) < dimensionPixelSize) {
                if (dynamicIslandBaseContentView == null || (expandedView2 = dynamicIslandBaseContentView.getExpandedView()) == null) {
                    return;
                }
                dynamicIslandBaseContentView.updateExpandedSize(expandedView2.getWidth(), dimensionPixelSize, dynamicIslandData);
                return;
            }
            if (dynamicIslandBaseContentView == null || (expandedView = dynamicIslandBaseContentView.getExpandedView()) == null) {
                return;
            }
            dynamicIslandBaseContentView.updateExpandedSize(expandedView.getWidth(), iIntValue, dynamicIslandData);
        }
    }

    public static /* synthetic */ void updateExpandedView$default(DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandBaseContentView dynamicIslandBaseContentView, DynamicIslandData dynamicIslandData, Integer num, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            num = null;
        }
        dynamicIslandWindowView.updateExpandedView(dynamicIslandBaseContentView, dynamicIslandData, num);
    }

    public static /* synthetic */ void updateViewStateWhenCloseEnd$default(DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandContentView dynamicIslandContentView, boolean z2, String str, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = "";
        }
        dynamicIslandWindowView.updateViewStateWhenCloseEnd(dynamicIslandContentView, z2, str);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void addDynamicIslandData(final DynamicIslandData dynamicIslandData, boolean z2, float f2, float f3, boolean z3) {
        kotlin.jvm.internal.n.g(dynamicIslandData, "dynamicIslandData");
        Log.d(TAG, "addDynamicIslandData cutoutY=" + f3);
        if (assertUserSpace(dynamicIslandData)) {
            if (z3) {
                this.dynamicIslandDataList.add(dynamicIslandData);
            }
            Bundle extras = dynamicIslandData.getExtras();
            String string = extras != null ? extras.getString("miui.pkg.name") : null;
            String key = dynamicIslandData.getKey();
            DynamicIslandContentView viewFromList = key != null ? getViewFromList(key) : null;
            if ((viewFromList != null ? viewFromList.getState() : null) != null) {
                updateDynamicIslandView(dynamicIslandData, z2, f2);
                return;
            }
            Bundle extras2 = dynamicIslandData.getExtras();
            notifyAddIsland(string, extras2 != null ? Integer.valueOf(extras2.getInt("miui.user.id")) : null, dynamicIslandData.getKey(), dynamicIslandData.getProperties());
            requestImmersiveMode();
            final DynamicIslandContentView dynamicIslandContentViewBuildDynamicIslandContentView = buildDynamicIslandContentView(dynamicIslandData, f2, f3);
            if (dynamicIslandContentViewBuildDynamicIslandContentView == null) {
                return;
            }
            DynamicIslandContentFakeView dynamicIslandContentFakeViewBuildDynamicIslandFakeContentView = buildDynamicIslandFakeContentView(dynamicIslandData, f2, f3);
            onDeviceNotificationChanged(hasDeviceNotification());
            DynamicIslandState.Init init = new DynamicIslandState.Init();
            init.setTime(Long.valueOf(System.currentTimeMillis()));
            Integer properties = dynamicIslandData.getProperties();
            init.setTempShow(properties != null && properties.intValue() == 0);
            init.setExpanded(z2);
            dynamicIslandContentViewBuildDynamicIslandContentView.setState(init);
            dynamicIslandContentViewBuildDynamicIslandContentView.setFakeView(dynamicIslandContentFakeViewBuildDynamicIslandFakeContentView);
            ViewGroup viewGroup = this.mGlowEffectTopContainer;
            if (viewGroup == null) {
                kotlin.jvm.internal.n.w("mGlowEffectTopContainer");
                viewGroup = null;
            }
            ViewGroup viewGroup2 = this.mGlowEffectBottomContainer;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.n.w("mGlowEffectBottomContainer");
                viewGroup2 = null;
            }
            dynamicIslandContentViewBuildDynamicIslandContentView.initGlowEffect$miui_dynamicisland_release(viewGroup, viewGroup2);
            if (dynamicIslandContentFakeViewBuildDynamicIslandFakeContentView != null) {
                dynamicIslandContentFakeViewBuildDynamicIslandFakeContentView.setRealView(dynamicIslandContentViewBuildDynamicIslandContentView);
            }
            dynamicIslandContentViewBuildDynamicIslandContentView.getViewModel().setState(null, init);
            getWindowViewController().updateChronometersIn(dynamicIslandContentViewBuildDynamicIslandContentView, dynamicIslandContentFakeViewBuildDynamicIslandFakeContentView, dynamicIslandData);
            getWindowViewController().addLottieAnimView(dynamicIslandContentViewBuildDynamicIslandContentView, dynamicIslandContentFakeViewBuildDynamicIslandFakeContentView, dynamicIslandData.getKey());
            if (canExpanded(z2, dynamicIslandData.getView())) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
                if (dynamicIslandEventCoordinator != null) {
                    dynamicIslandEventCoordinator.onHeightChangedFirst();
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = this.eventCoordinator;
                if (dynamicIslandEventCoordinator2 != null) {
                    dynamicIslandEventCoordinator2.setUserExpanded(false);
                }
            }
            OneShotPreDrawListener.add(this, new Runnable() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowView$addDynamicIslandData$$inlined$doOnPreDraw$1
                @Override // java.lang.Runnable
                public final void run() {
                    if (this.getContentViewList().contains(dynamicIslandContentViewBuildDynamicIslandContentView)) {
                        AbstractC0369g.b(this.getWindowViewController().getIoScope(), null, null, new DynamicIslandWindowView$addDynamicIslandData$1$1(this, dynamicIslandContentViewBuildDynamicIslandContentView, dynamicIslandData, null), 3, null);
                    }
                }
            });
        }
    }

    public final void addOnDynamicIslandViewChangedListener(DynamicIslandContent.DynamicIslandViewChangedListener listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        this.listener = listener;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.addDynamicIslandViewChangedListener(listener);
        }
    }

    @Override // android.view.ViewGroup
    @CallSuper
    public void addView(View child, int i2, ViewGroup.LayoutParams params) {
        int childCount;
        kotlin.jvm.internal.n.g(child, "child");
        kotlin.jvm.internal.n.g(params, "params");
        ViewGroup viewGroup = this.mGlowEffectTopContainer;
        if (viewGroup != null) {
            if (viewGroup == null) {
                kotlin.jvm.internal.n.w("mGlowEffectTopContainer");
                viewGroup = null;
            }
            childCount = indexOfChild(viewGroup);
        } else {
            childCount = getChildCount();
        }
        if (i2 >= 0) {
            childCount = Math.min(childCount, i2);
        }
        super.addView(child, childCount, params);
    }

    public final void appEnter(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        if (dynamicIslandContentView == null || (dynamicIslandEventCoordinator = this.eventCoordinator) == null) {
            return;
        }
        dynamicIslandEventCoordinator.dispatchEvent(DynamicIslandEvent.EnterApp.INSTANCE, dynamicIslandContentView);
    }

    public final void appExit(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        if (dynamicIslandContentView == null || (dynamicIslandEventCoordinator = this.eventCoordinator) == null) {
            return;
        }
        dynamicIslandEventCoordinator.dispatchEvent(DynamicIslandEvent.ExitApp.INSTANCE, dynamicIslandContentView);
    }

    public final DynamicIslandState calculateCollapse(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            return dynamicIslandEventCoordinator.calculateCollapse(dynamicIslandContentView);
        }
        return null;
    }

    public final boolean canExpanded(boolean z2, View view) {
        return z2 && !((Boolean) getWindowViewController().getWindowState().getNotificationAppearance().getValue()).booleanValue() && (!((Boolean) getWindowViewController().getWindowState().getScreenLocked().getValue()).booleanValue() || ((Boolean) getWindowViewController().getWindowState().getControlCenterExpanded().getValue()).booleanValue()) && view != null;
    }

    public final void cancelExpandViewTrackingAnim(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandContentFakeView fakeView;
        if (dynamicIslandContentView == null || (fakeView = dynamicIslandContentView.getFakeView()) == null) {
            return;
        }
        fakeView.cancelExpandViewTrackingAnim();
    }

    public final void cancelLongPressRunnable(DynamicIslandBaseContentView view) {
        kotlin.jvm.internal.n.g(view, "view");
        Runnable runnable = this.longPress;
        if (runnable != null) {
            view.removeCallbacks(runnable);
            this.longPress = null;
        }
    }

    public final void clearAfterDelete(DynamicIslandData dynamicIslandData, String str, boolean z2) {
        Bundle extras;
        Bundle extras2;
        notifyRemoveIsland((dynamicIslandData == null || (extras2 = dynamicIslandData.getExtras()) == null) ? null : extras2.getString("miui.pkg.name"), (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : Integer.valueOf(extras.getInt("miui.user.id")), str, dynamicIslandData != null ? dynamicIslandData.getProperties() : null);
        if (str != null) {
            getViewComponent().getIslandTemplateFactory().removeTemplate(str);
        }
        for (DynamicIslandContentView dynamicIslandContentView : this.contentViewList) {
            onWindowAnimExtendLifetimeEnd(dynamicIslandContentView);
            DynamicIslandData currentIslandData = dynamicIslandContentView.getCurrentIslandData();
            if (kotlin.jvm.internal.n.c(currentIslandData != null ? currentIslandData.getKey() : null, str)) {
                this.contentViewList.remove(dynamicIslandContentView);
                onDynamicIslandDataChanged();
            }
        }
        if (z2) {
            Iterator<DynamicIslandData> it = this.dynamicIslandDataList.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(it.next().getKey(), str)) {
                    it.remove();
                }
            }
        }
        onDeviceNotificationChanged(hasDeviceNotification());
        for (DynamicIslandContentFakeView dynamicIslandContentFakeView : this.fakeViewList) {
            DynamicIslandData currentIslandData2 = dynamicIslandContentFakeView.getCurrentIslandData();
            if (kotlin.jvm.internal.n.c(currentIslandData2 != null ? currentIslandData2.getKey() : null, str)) {
                this.fakeViewList.remove(dynamicIslandContentFakeView);
                removeView(dynamicIslandContentFakeView);
                dynamicIslandContentFakeView.getController().destroy();
            }
        }
    }

    public final void collapse(String reason) throws PendingIntent.CanceledException {
        DynamicIslandData currentIslandData;
        DynamicIslandContentFakeView fakeView;
        kotlin.jvm.internal.n.g(reason, "reason");
        DynamicIslandContentView currentExpandedState = getCurrentExpandedState();
        boolean z2 = (currentExpandedState == null || (fakeView = currentExpandedState.getFakeView()) == null || !fakeView.getOpenAppFromIsland()) ? false : true;
        DynamicIslandContentView currentExpandedState2 = getCurrentExpandedState();
        boolean z3 = currentExpandedState2 != null && currentExpandedState2.getOpenAppFromIsland();
        boolean z4 = getCurrentExpandedState() != null;
        Log.d(TAG, "skip collapse=(" + z3 + "||" + z2 + "||" + z4 + "), reason=" + reason);
        if (z3 || z2 || !z4) {
            return;
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.setUserExpanded(false);
        }
        DynamicIslandContentView currentExpandedState3 = getCurrentExpandedState();
        if (currentExpandedState3 != null && (currentIslandData = currentExpandedState3.getCurrentIslandData()) != null) {
            sendExitPendingIntent(currentIslandData.getExtras());
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = this.eventCoordinator;
        if (dynamicIslandEventCoordinator2 != null) {
            DynamicIslandEventCoordinator.dispatchEvent$default(dynamicIslandEventCoordinator2, DynamicIslandEvent.Collapse.INSTANCE, null, 2, null);
        }
    }

    public final void destroy() {
        getLifecycle().setCurrentState(Lifecycle.State.DESTROYED);
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        }
        getWindowViewController().destroy();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        kotlin.jvm.internal.n.g(canvas, "canvas");
        super.dispatchDraw(canvas);
        AbstractC0369g.b(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C06351(canvas, null), 3, null);
    }

    public final boolean downInMedia(Context sysUIContext, float f2, float f3, View view, boolean z2) {
        View viewFindViewById;
        kotlin.jvm.internal.n.g(sysUIContext, "sysUIContext");
        if (view == null) {
            return false;
        }
        List<DynamicIslandData> list = this.dynamicIslandDataList;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (isMediaApp((DynamicIslandData) it.next())) {
                Integer targetResourceId = getTargetResourceId(sysUIContext, z2);
                if (targetResourceId == null || (viewFindViewById = view.findViewById(targetResourceId.intValue())) == null) {
                    return false;
                }
                try {
                    Rect rect = new Rect();
                    if (!viewFindViewById.getGlobalVisibleRect(rect) || f2 < rect.left || f2 > rect.right || f3 < rect.top) {
                        return false;
                    }
                    return f3 <= ((float) rect.bottom);
                } catch (Exception unused) {
                    return false;
                }
            }
        }
        return false;
    }

    public final void enterMiniWindow(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        if (dynamicIslandContentView == null || (dynamicIslandEventCoordinator = this.eventCoordinator) == null) {
            return;
        }
        dynamicIslandEventCoordinator.dispatchEvent(DynamicIslandEvent.EnterMiniWindow.INSTANCE, dynamicIslandContentView);
    }

    public final void enterMiniWindowEnd() {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null) {
            return;
        }
        dynamicIslandEventCoordinator.setEnterMiniWindow(false);
    }

    public final void exitMiniWindow(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        if (dynamicIslandContentView == null || (dynamicIslandEventCoordinator = this.eventCoordinator) == null) {
            return;
        }
        dynamicIslandEventCoordinator.dispatchEvent(DynamicIslandEvent.ExitMiniWindow.INSTANCE, dynamicIslandContentView);
    }

    public final CopyOnWriteArrayList<DynamicIslandContentView> getContentViewList() {
        return this.contentViewList;
    }

    public final List<DynamicIslandContentView> getCurrentAppExpandedState() {
        AppStateHandler appStateHandler;
        ArrayList<DynamicIslandContentView> currentList;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        return (dynamicIslandEventCoordinator == null || (appStateHandler = dynamicIslandEventCoordinator.getAppStateHandler()) == null || (currentList = appStateHandler.getCurrentList()) == null) ? new ArrayList() : currentList;
    }

    public final DynamicIslandContentView getCurrentBigIslandState() {
        BigIslandStateHandler bigIslandStateHandler;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (bigIslandStateHandler = dynamicIslandEventCoordinator.getBigIslandStateHandler()) == null) {
            return null;
        }
        return bigIslandStateHandler.getCurrent();
    }

    public final DynamicIslandContentView getCurrentExpandedState() {
        ExpandedStateHandler expandedStateHandler;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (expandedStateHandler = dynamicIslandEventCoordinator.getExpandedStateHandler()) == null) {
            return null;
        }
        return expandedStateHandler.getCurrent();
    }

    public final List<DynamicIslandContentView> getCurrentMiniWindowState(String pkg) {
        MiniWindowStateHandler miniWindowStateHandler;
        HashMap<String, ArrayList<DynamicIslandContentView>> currentMap;
        ArrayList<DynamicIslandContentView> arrayList;
        kotlin.jvm.internal.n.g(pkg, "pkg");
        ArrayList arrayList2 = new ArrayList();
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null && (miniWindowStateHandler = dynamicIslandEventCoordinator.getMiniWindowStateHandler()) != null && (currentMap = miniWindowStateHandler.getCurrentMap()) != null && (arrayList = currentMap.get(pkg)) != null) {
            arrayList2.addAll(arrayList);
        }
        return arrayList2;
    }

    public final DynamicIslandContentView getCurrentSmallIslandState() {
        SmallIslandStateHandler smallIslandStateHandler;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (smallIslandStateHandler = dynamicIslandEventCoordinator.getSmallIslandStateHandler()) == null) {
            return null;
        }
        return smallIslandStateHandler.getCurrent();
    }

    public final DynamicIslandContentView getCurrentTempShowBigIslandState() {
        BigIslandStateHandler bigIslandStateHandler;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (bigIslandStateHandler = dynamicIslandEventCoordinator.getBigIslandStateHandler()) == null) {
            return null;
        }
        return bigIslandStateHandler.getCurrentTempShow();
    }

    public final int getCurrentUserId() {
        return this.currentUserId;
    }

    public final int getCutoutHeight() {
        return Math.min(getCutoutWidth(), (int) (getContext().getResources().getDimensionPixelSize(R.dimen.island_height) * 0.9f));
    }

    public final Rect getCutoutRect() {
        Rect rect = new Rect();
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        rect.left = (dynamicIslandUtils.getScreenWidthOld(context) / 2) - (getCutoutWidth() / 2);
        rect.top = ((int) getWindowViewController().getCutoutY()) - (getCutoutHeight() / 2);
        rect.right = rect.left + getCutoutWidth();
        rect.bottom = rect.top + getCutoutHeight();
        return rect;
    }

    public final int getCutoutWidth() {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        int iDpToPx = dynamicIslandUtils.dpToPx(20, context);
        return (FoldUtils.INSTANCE.isFoldScreenLayoutLarge(this) || FlipUtils.isFlipTiny() || dynamicIslandUtils.getCutoutBoundingRectTopWidth() <= 0) ? iDpToPx : dynamicIslandUtils.getCutoutBoundingRectTopWidth();
    }

    public final y getDispatchDrawEvent() {
        return this.dispatchDrawEvent;
    }

    public final DynamicIslandEventCoordinator getEventCoordinator() {
        return this.eventCoordinator;
    }

    public final List<DynamicIslandContentFakeView> getFakeViews() {
        return u.k0(this.fakeViewList);
    }

    public final int getHeadsUpHeight() {
        return this._headsUpHeight;
    }

    public final DynamicIslandContentView getMainAppExpanded() {
        AppStateHandler appStateHandler;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (appStateHandler = dynamicIslandEventCoordinator.getAppStateHandler()) == null) {
            return null;
        }
        return appStateHandler.getMainElement();
    }

    public final DynamicIslandContentView getMainAppExpandedTopLeve() {
        AppStateHandler appStateHandler;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (appStateHandler = dynamicIslandEventCoordinator.getAppStateHandler()) == null) {
            return null;
        }
        return appStateHandler.getTopLevel();
    }

    public final int getMainAppExpandedTopLeveCount() {
        AppStateHandler appStateHandler;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (appStateHandler = dynamicIslandEventCoordinator.getAppStateHandler()) == null) {
            return 0;
        }
        return appStateHandler.getTopLevelCount();
    }

    public final DynamicIslandContentView getMainMiniWindow(String pkg) {
        MiniWindowStateHandler miniWindowStateHandler;
        kotlin.jvm.internal.n.g(pkg, "pkg");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (miniWindowStateHandler = dynamicIslandEventCoordinator.getMiniWindowStateHandler()) == null) {
            return null;
        }
        return miniWindowStateHandler.getMainElement(pkg);
    }

    public final DynamicIslandContentView getMainMiniWindowState(String pkg) {
        MiniWindowStateHandler miniWindowStateHandler;
        kotlin.jvm.internal.n.g(pkg, "pkg");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (miniWindowStateHandler = dynamicIslandEventCoordinator.getMiniWindowStateHandler()) == null) {
            return null;
        }
        return miniWindowStateHandler.getMainElement(pkg);
    }

    public final DynamicIslandContentView getMainMiniWindowTopLeve(String pkg) {
        MiniWindowStateHandler miniWindowStateHandler;
        kotlin.jvm.internal.n.g(pkg, "pkg");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (miniWindowStateHandler = dynamicIslandEventCoordinator.getMiniWindowStateHandler()) == null) {
            return null;
        }
        return miniWindowStateHandler.getTopLevel(pkg);
    }

    public final int getMainMiniWindowTopLeveCount(String pkg) {
        MiniWindowStateHandler miniWindowStateHandler;
        kotlin.jvm.internal.n.g(pkg, "pkg");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (miniWindowStateHandler = dynamicIslandEventCoordinator.getMiniWindowStateHandler()) == null) {
            return 0;
        }
        return miniWindowStateHandler.getTopLevelCount(pkg);
    }

    public final DynamicIslandContentFakeView getMiniWindowContentFakeView(String pkg) {
        MiniWindowStateHandler miniWindowStateHandler;
        DynamicIslandContentView mainElement;
        kotlin.jvm.internal.n.g(pkg, "pkg");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (miniWindowStateHandler = dynamicIslandEventCoordinator.getMiniWindowStateHandler()) == null || (mainElement = miniWindowStateHandler.getMainElement(pkg)) == null) {
            return null;
        }
        return mainElement.getFakeView();
    }

    public final y getOnInterceptTouchEvent() {
        return this.onInterceptTouchEvent;
    }

    public final y getOnTouchEvent() {
        return this.onTouchEvent;
    }

    public final DynamicIslandContentView getSubAppExpanded() {
        AppStateHandler appStateHandler;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (appStateHandler = dynamicIslandEventCoordinator.getAppStateHandler()) == null) {
            return null;
        }
        return appStateHandler.getSubElement();
    }

    public final DynamicIslandContentView getSubMiniWindow(String pkg) {
        MiniWindowStateHandler miniWindowStateHandler;
        kotlin.jvm.internal.n.g(pkg, "pkg");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator == null || (miniWindowStateHandler = dynamicIslandEventCoordinator.getMiniWindowStateHandler()) == null) {
            return null;
        }
        return miniWindowStateHandler.getSubElement(pkg);
    }

    public final boolean getTouchOutsideInHeadsUp() {
        return this.touchOutsideInHeadsUp;
    }

    public final DynamicIslandViewComponent getViewComponent() {
        DynamicIslandViewComponent dynamicIslandViewComponent = this._viewComponent;
        if (dynamicIslandViewComponent != null) {
            return dynamicIslandViewComponent;
        }
        throw new IllegalStateException("DynamicIslandViewComponent was not initialized.");
    }

    public final DynamicIslandContentView getViewFromList(String str) {
        Object obj;
        Iterator<T> it = this.contentViewList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            DynamicIslandData currentIslandData = ((DynamicIslandContentView) next).getCurrentIslandData();
            if (kotlin.jvm.internal.n.c(currentIslandData != null ? currentIslandData.getKey() : null, str)) {
                obj = next;
                break;
            }
        }
        return (DynamicIslandContentView) obj;
    }

    public final DynamicIslandWindowViewController getWindowViewController() {
        return (DynamicIslandWindowViewController) this.windowViewController$delegate.getValue();
    }

    public final boolean hasNoActiveDynamicIsland() {
        return this.dynamicIslandDataList.size() == 0;
    }

    public final boolean hasOtherBigIsland(DynamicIslandContentView dynamicIslandContentView) {
        BigIslandStateHandler bigIslandStateHandler;
        DynamicIslandContentView current;
        DynamicIslandData currentIslandData;
        BigIslandStateHandler bigIslandStateHandler2;
        DynamicIslandContentView current2;
        DynamicIslandData currentIslandData2;
        BigIslandStateHandler bigIslandStateHandler3;
        DynamicIslandData currentIslandData3;
        Integer properties = null;
        String key = (dynamicIslandContentView == null || (currentIslandData3 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData3.getKey();
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (((dynamicIslandEventCoordinator == null || (bigIslandStateHandler3 = dynamicIslandEventCoordinator.getBigIslandStateHandler()) == null) ? null : bigIslandStateHandler3.getCurrent()) != null) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = this.eventCoordinator;
            if (!kotlin.jvm.internal.n.c((dynamicIslandEventCoordinator2 == null || (bigIslandStateHandler2 = dynamicIslandEventCoordinator2.getBigIslandStateHandler()) == null || (current2 = bigIslandStateHandler2.getCurrent()) == null || (currentIslandData2 = current2.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey(), key)) {
                DynamicIslandWindowState windowState = getWindowViewController().getWindowState();
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = this.eventCoordinator;
                if (dynamicIslandEventCoordinator3 != null && (bigIslandStateHandler = dynamicIslandEventCoordinator3.getBigIslandStateHandler()) != null && (current = bigIslandStateHandler.getCurrent()) != null && (currentIslandData = current.getCurrentIslandData()) != null) {
                    properties = currentIslandData.getProperties();
                }
                if (!windowState.isTempHidden(properties)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean hasSubAppExpanded(String pkg) {
        AppStateHandler appStateHandler;
        ArrayList<DynamicIslandContentView> currentList;
        kotlin.jvm.internal.n.g(pkg, "pkg");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        return ((dynamicIslandEventCoordinator == null || (appStateHandler = dynamicIslandEventCoordinator.getAppStateHandler()) == null || (currentList = appStateHandler.getCurrentList()) == null) ? 0 : currentList.size()) > 1;
    }

    public final boolean hasSubMiniWindow(String pkg) {
        MiniWindowStateHandler miniWindowStateHandler;
        HashMap<String, ArrayList<DynamicIslandContentView>> currentMap;
        ArrayList<DynamicIslandContentView> arrayList;
        kotlin.jvm.internal.n.g(pkg, "pkg");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        return ((dynamicIslandEventCoordinator == null || (miniWindowStateHandler = dynamicIslandEventCoordinator.getMiniWindowStateHandler()) == null || (currentMap = miniWindowStateHandler.getCurrentMap()) == null || (arrayList = currentMap.get(pkg)) == null) ? 0 : arrayList.size()) > 1;
    }

    public final void hideAllElementSurface() {
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        DynamicIslandData currentIslandData3;
        Bundle extras;
        for (DynamicIslandContentView dynamicIslandContentView : this.contentViewList) {
            Bundle extras2 = null;
            Log.d(TAG, "hideAllElementSurface: " + ((dynamicIslandContentView == null || (currentIslandData3 = dynamicIslandContentView.getCurrentIslandData()) == null || (extras = currentIslandData3.getExtras()) == null) ? null : extras.getString("miui.pkg.name")));
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
            if (dynamicIslandEventCoordinator != null) {
                dynamicIslandEventCoordinator.alreadyCloseAppEnd((dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getExtras());
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = this.eventCoordinator;
            if (dynamicIslandEventCoordinator2 != null) {
                if (dynamicIslandContentView != null && (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) != null) {
                    extras2 = currentIslandData.getExtras();
                }
                dynamicIslandEventCoordinator2.onWindowAnimExtendLifetimeEnd(extras2);
            }
        }
    }

    public final boolean isLight() {
        return this.isLight;
    }

    public final boolean isMediaApp(DynamicIslandData dynamicIslandData) {
        Bundle extras;
        return ((dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : (PendingIntent) extras.getParcelable("miui.pending.intent", PendingIntent.class)) != null;
    }

    public final boolean isSwipeTowardsSmallIsland(float f2) {
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        if (CommonUtils.isLayoutRtl(context)) {
            if (f2 > 0.0f) {
                return false;
            }
        } else if (f2 < 0.0f) {
            return false;
        }
        return true;
    }

    public final boolean isTempShowBigIslandToBeRemoved() {
        DynamicIslandData currentIslandData;
        DynamicIslandContentView currentTempShowBigIslandState = getCurrentTempShowBigIslandState();
        if (currentTempShowBigIslandState == null || (currentIslandData = currentTempShowBigIslandState.getCurrentIslandData()) == null) {
            return false;
        }
        removeDynamicIslandData$default(this, currentIslandData, false, 2, null);
        return true;
    }

    public final boolean isUserExpanded() {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        return dynamicIslandEventCoordinator != null && dynamicIslandEventCoordinator.getUserExpanded();
    }

    public final void maybeCollapseExpand(int i2, int i3) {
        I touchRegion;
        Region region;
        I headsUpZone;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        H0.i iVar = (dynamicIslandEventCoordinator == null || (headsUpZone = dynamicIslandEventCoordinator.getHeadsUpZone()) == null) ? null : (H0.i) headsUpZone.getValue();
        if (iVar == null || i3 < ((Number) iVar.d()).intValue() || i3 > ((Number) iVar.e()).intValue()) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = this.eventCoordinator;
            if ((dynamicIslandEventCoordinator2 == null || (touchRegion = dynamicIslandEventCoordinator2.getTouchRegion()) == null || (region = (Region) touchRegion.getValue()) == null || !region.contains(i2, i3)) && isUserExpanded()) {
                post(new Runnable() { // from class: miui.systemui.dynamicisland.window.k
                    @Override // java.lang.Runnable
                    public final void run() throws PendingIntent.CanceledException {
                        DynamicIslandWindowView.maybeCollapseExpand$lambda$12(this.f5751a);
                    }
                });
            }
        }
    }

    public final boolean needExtendLifetime(String key) {
        kotlin.jvm.internal.n.g(key, "key");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            return dynamicIslandEventCoordinator.needExtendLifetime(key);
        }
        return false;
    }

    public final void notifyAddIsland(String str, Integer num, String str2, Integer num2) {
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_BACK_ADD_ISLAND);
        bundle.putString(DynamicIslandConstants.EXTRA_BACK_ISLAND_PKG, str);
        if (num != null) {
            bundle.putInt(DynamicIslandConstants.EXTRA_BACK_ISLAND_UID, num.intValue());
        }
        bundle.putString(DynamicIslandConstants.EXTRA_BACK_ISLAND_KEY, str2);
        if (num2 != null) {
            bundle.putInt(DynamicIslandConstants.EXTRA_BACK_ISLAND_PROP, num2.intValue());
        }
        bundle.putFloat(DynamicIslandConstants.EXTRA_BACK_ISLAND_RADIUS, getContext().getResources().getDimension(R.dimen.island_radius));
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.listener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onIslandViewChanged(bundle);
        }
    }

    public final Bundle notifyIslandViewChanged(Bundle bundle) {
        kotlin.jvm.internal.n.g(bundle, "bundle");
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.listener;
        if (dynamicIslandViewChangedListener != null) {
            return dynamicIslandViewChangedListener.onIslandViewChanged(bundle);
        }
        return null;
    }

    public final void notifyRemoveIsland(String str, Integer num, String str2, Integer num2) {
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_BACK_REMOVE_ISLAND);
        bundle.putString(DynamicIslandConstants.EXTRA_BACK_ISLAND_PKG, str);
        if (num != null) {
            bundle.putInt(DynamicIslandConstants.EXTRA_BACK_ISLAND_UID, num.intValue());
        }
        bundle.putString(DynamicIslandConstants.EXTRA_BACK_ISLAND_KEY, str2);
        if (num2 != null) {
            bundle.putInt(DynamicIslandConstants.EXTRA_BACK_ISLAND_PROP, num2.intValue());
        }
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.listener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onIslandViewChanged(bundle);
        }
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandContentView tempShow;
        int windowInsetsRotation = getWindowInsetsRotation(windowInsets);
        Log.d(TAG, "onApplyWindowInsets r=" + windowInsetsRotation);
        int displayOrientation = getWindowViewController().getDisplayOrientation();
        boolean z2 = false;
        boolean z3 = displayOrientation == 0 ? windowInsetsRotation == 2 : !(displayOrientation == 1 ? windowInsetsRotation != 3 : displayOrientation == 2 ? windowInsetsRotation != 0 : !(displayOrientation == 3 && windowInsetsRotation == 1));
        boolean zIsNotchScreenDevice = CommonUtils.isNotchScreenDevice();
        if (FlipUtils.isFlipTiny() && z3) {
            z2 = true;
        }
        if (zIsNotchScreenDevice || z2) {
            getWindowViewController().updateWindowState();
            Float fRequestCutoutY = requestCutoutY();
            if (fRequestCutoutY != null) {
                getViewComponent().getSizeRepository().updateCutoutY(fRequestCutoutY.floatValue());
            }
            if (z2 && (dynamicIslandEventCoordinator = this.eventCoordinator) != null && (tempShow = dynamicIslandEventCoordinator.getTempShow()) != null && !tempShow.isAnimating()) {
                removeDynamicIslandData(tempShow.getCurrentIslandData(), true);
            }
        }
        WindowInsets windowInsetsOnApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        kotlin.jvm.internal.n.f(windowInsetsOnApplyWindowInsets, "onApplyWindowInsets(...)");
        return windowInsetsOnApplyWindowInsets;
    }

    public final void onConfigChanged(Configuration newConfig) {
        DynamicIslandData currentIslandData;
        Integer properties;
        kotlin.jvm.internal.n.g(newConfig, "newConfig");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.updateTouchRegion();
        }
        Log.d(TAG, "isTiny=" + getWindowViewController().getWindowState().isTinyScreen() + " config changed to " + newConfig);
        getWindowViewController().getWindowState().setConfigChange(Boolean.TRUE);
        Float fRequestCutoutY = requestCutoutY();
        if (fRequestCutoutY != null) {
            getViewComponent().getSizeRepository().updateCutoutY(fRequestCutoutY.floatValue());
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = this.eventCoordinator;
        DynamicIslandContentView tempShow = dynamicIslandEventCoordinator2 != null ? dynamicIslandEventCoordinator2.getTempShow() : null;
        Configuration configuration = this.previousConfig;
        if (configuration == null) {
            kotlin.jvm.internal.n.w("previousConfig");
            configuration = null;
        }
        boolean z2 = configuration.isNightModeActive() == newConfig.isNightModeActive();
        if (tempShow != null && z2) {
            removeDynamicIslandData(tempShow.getCurrentIslandData(), true);
        }
        Configuration configuration2 = this.previousConfig;
        if (configuration2 == null) {
            kotlin.jvm.internal.n.w("previousConfig");
            configuration2 = null;
        }
        boolean z3 = MiBlurCompat.getBackgroundBlurOpened(configuration2) != MiBlurCompat.getBackgroundBlurOpened(newConfig);
        if (z3) {
            updateBlurContainer(MiBlurCompat.getBackgroundBlurOpened(newConfig));
        }
        Configuration configuration3 = this.previousConfig;
        if (configuration3 == null) {
            kotlin.jvm.internal.n.w("previousConfig");
            configuration3 = null;
        }
        boolean zCompareConfigurations = compareConfigurations(configuration3, newConfig);
        Configuration configuration4 = this.previousConfig;
        if (configuration4 == null) {
            kotlin.jvm.internal.n.w("previousConfig");
            configuration4 = null;
        }
        configuration4.updateFrom(newConfig);
        if (zCompareConfigurations) {
            getViewComponent().getSizeRepository().updateIslandMaxWidth(0.0f, 0.0f, 0.0f);
            DynamicIslandWindowViewController.DynamicIslandCallback dynamicIslandCallback = getWindowViewController().getDynamicIslandCallback();
            if (dynamicIslandCallback != null) {
                dynamicIslandCallback.onDynamicIslandConfigChange();
            }
            getWindowViewController().getWindowState().setConfigChange(Boolean.FALSE);
            return;
        }
        for (DynamicIslandContentView dynamicIslandContentView : this.contentViewList) {
            if (dynamicIslandContentView.getCurrentIslandData() != null && ((currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null || (properties = currentIslandData.getProperties()) == null || properties.intValue() != 0 || z2)) {
                dynamicIslandContentView.calculateBigIslandWidth();
                dynamicIslandContentView.updateView(dynamicIslandContentView.getCurrentIslandData(), true, false);
            }
            kotlin.jvm.internal.n.d(dynamicIslandContentView);
            measureExpandedViewHeight(dynamicIslandContentView);
            if (z3) {
                DynamicIslandExpandedView expandedView = dynamicIslandContentView.getExpandedView();
                kotlin.jvm.internal.n.e(expandedView, "null cannot be cast to non-null type android.view.View");
                dynamicIslandContentView.updateBackgroundBg(expandedView);
            }
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = this.eventCoordinator;
        if (dynamicIslandEventCoordinator3 != null) {
            DynamicIslandEventCoordinator.dispatchEvent$default(dynamicIslandEventCoordinator3, DynamicIslandEvent.ConfigChanged.INSTANCE, null, 2, null);
        }
        for (DynamicIslandBaseContentView dynamicIslandBaseContentView : this.fakeViewList) {
            if (dynamicIslandBaseContentView.getCurrentIslandData() != null) {
                dynamicIslandBaseContentView.updateView(dynamicIslandBaseContentView.getCurrentIslandData(), true, false);
            }
            kotlin.jvm.internal.n.d(dynamicIslandBaseContentView);
            measureExpandedViewHeight(dynamicIslandBaseContentView);
            if (z3) {
                FrameLayout fakeExpandedView = dynamicIslandBaseContentView.getFakeExpandedView();
                kotlin.jvm.internal.n.e(fakeExpandedView, "null cannot be cast to non-null type android.view.View");
                dynamicIslandBaseContentView.updateBackgroundBg(fakeExpandedView);
            }
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        View viewFindViewById = findViewById(R.id.glow_effect_bottom_container);
        kotlin.jvm.internal.n.f(viewFindViewById, "findViewById(...)");
        this.mGlowEffectBottomContainer = (ViewGroup) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.glow_effect_top_container);
        kotlin.jvm.internal.n.f(viewFindViewById2, "findViewById(...)");
        this.mGlowEffectTopContainer = (ViewGroup) viewFindViewById2;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        TouchEvent touchEvent = new TouchEvent(motionEvent, TouchEvent.SOURCE_DYNAMIC_ISLAND);
        AbstractC0369g.b(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C06371(touchEvent, null), 3, null);
        Boolean result = touchEvent.getResult();
        return result != null ? result.booleanValue() : super.onInterceptTouchEvent(motionEvent);
    }

    public final void onIslandTempHide(boolean z2, DynamicIslandWindowState.TempHiddenType tempHiddenType) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            DynamicIslandEvent.IslandTempHiddenChanged islandTempHiddenChanged = DynamicIslandEvent.IslandTempHiddenChanged.INSTANCE;
            islandTempHiddenChanged.setHide(z2);
            islandTempHiddenChanged.setType(tempHiddenType);
            DynamicIslandEventCoordinator.dispatchEvent$default(dynamicIslandEventCoordinator, islandTempHiddenChanged, null, 2, null);
        }
    }

    public final void onKeyguardShowing(boolean z2) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.onKeyguardShowing(z2);
        }
        this.currentKeyguardShowing = z2;
    }

    public final void onLongPress(final DynamicIslandBaseContentView view, final DynamicIslandContentView dynamicIslandContentView, final float f2) {
        kotlin.jvm.internal.n.g(view, "view");
        if (!isDisableShare()) {
            Runnable runnable = new Runnable() { // from class: miui.systemui.dynamicisland.window.o
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicIslandWindowView.onLongPress$lambda$35(view, dynamicIslandContentView, f2);
                }
            };
            view.postDelayed(runnable, 20L);
            this.longPress = runnable;
        } else {
            DynamicIslandAnimationDelegate animatorDelegate = view.getAnimatorDelegate();
            if (animatorDelegate != null) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator();
                animatorDelegate.isLandDragShake(dynamicIslandEventCoordinator != null ? dynamicIslandEventCoordinator.getBigIsLandAndSmallIsLandList() : null);
            }
        }
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return super.onTouchEvent(motionEvent);
        }
        TouchEvent touchEvent = new TouchEvent(motionEvent, TouchEvent.SOURCE_DYNAMIC_ISLAND);
        AbstractC0369g.b(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C06381(touchEvent, null), 3, null);
        Boolean result = touchEvent.getResult();
        return result != null ? result.booleanValue() : super.onTouchEvent(motionEvent);
    }

    @Override // com.android.systemui.settings.UserTracker.Callback
    public void onUserChanged(int i2, Context userContext) {
        kotlin.jvm.internal.n.g(userContext, "userContext");
        Log.e(TAG, "onUserChanged: " + this.currentUserId + "   " + i2);
        Iterator<T> it = this.dynamicIslandDataList.iterator();
        while (it.hasNext()) {
            removeDynamicIslandData$default(this, (DynamicIslandData) it.next(), false, 2, null);
        }
        this.currentUserId = i2;
        Iterator<DynamicIslandData> it2 = this.dynamicIslandDataList.iterator();
        while (it2.hasNext()) {
            Integer properties = it2.next().getProperties();
            if (properties != null && properties.intValue() == 0) {
                it2.remove();
            }
        }
        Iterator<T> it3 = this.dynamicIslandDataList.iterator();
        while (it3.hasNext()) {
            addDynamicIslandData((DynamicIslandData) it3.next(), false, getWindowViewController().getIslandMaxWidth(), getWindowViewController().getCutoutY(), false);
        }
        onDeviceNotificationChanged(hasDeviceNotification());
    }

    public final void onWindowAnimExtendLifetimeEnd(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandData currentIslandData;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.onWindowAnimExtendLifetimeEnd((dynamicIslandContentView == null || (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData.getExtras());
        }
    }

    public final void onWindowAnimExtendLifetimeStart(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandData currentIslandData;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.onWindowAnimExtendLifetimeStart((dynamicIslandContentView == null || (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData.getExtras());
        }
    }

    public final void preRemoveDynamicIsland(DynamicIslandContentView dynamicIslandContentView, DynamicIslandState dynamicIslandState) {
        if ((dynamicIslandState instanceof DynamicIslandState.Deleted) && ((DynamicIslandState.Deleted) dynamicIslandState).getDeleteByAddNew()) {
            removeDynamicIslandData$default(this, dynamicIslandContentView != null ? dynamicIslandContentView.getCurrentIslandData() : null, false, 2, null);
        }
    }

    public final void removeDynamicIslandData(DynamicIslandData dynamicIslandData, boolean z2) {
        Bundle extras;
        if (assertUserSpace(dynamicIslandData)) {
            removeDynamicIslandData(dynamicIslandData != null ? dynamicIslandData.getKey() : null, (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : extras.getString("miui.pkg.name"), false, z2);
        }
    }

    public final void removeExtendLifetime(String key) {
        kotlin.jvm.internal.n.g(key, "key");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.removeExtendLifetime(key);
        }
    }

    public final void removeNotification(StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(sbn, "sbn");
        DynamicIslandWindowViewController.DynamicIslandCallback dynamicIslandCallback = getWindowViewController().getDynamicIslandCallback();
        if (dynamicIslandCallback != null) {
            dynamicIslandCallback.removeNotification(sbn);
        }
    }

    public final List<DynamicIslandContentView> requestHasIsland(String pkg) {
        kotlin.jvm.internal.n.g(pkg, "pkg");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            return dynamicIslandEventCoordinator.hasIsland(pkg);
        }
        return null;
    }

    public final void resetHeadsUpLocation() {
        Log.d(TAG, "resetHeadsUpLocation");
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_LOCATION_CHANGED_FOR_HEADS_UP);
        bundle.putBoolean(DynamicIslandConstants.EXTRA_IS_EXPAND, false);
        bundle.putInt(DynamicIslandConstants.EXTRA_ISLAND_BOTTOM, 0);
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.listener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onIslandViewChanged(bundle);
        }
    }

    public final void setAnimRunning(boolean z2, boolean z3) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = this.eventCoordinator;
        if (dynamicIslandEventCoordinator2 != null) {
            dynamicIslandEventCoordinator2.updateWindowHeightInAnimState(z2, z3);
        }
        if (z2 || (dynamicIslandEventCoordinator = this.eventCoordinator) == null) {
            return;
        }
        dynamicIslandEventCoordinator.onAnimationFinished();
    }

    public final void setClosingToExpanded(DynamicIslandContentView dynamicIslandContentView, boolean z2, boolean z3) {
        DynamicIslandContentFakeView fakeView;
        if (dynamicIslandContentView == null || (fakeView = dynamicIslandContentView.getFakeView()) == null) {
            return;
        }
        fakeView.setClosingToExpanded(z2, z3);
    }

    public final void setContentViewList(CopyOnWriteArrayList<DynamicIslandContentView> copyOnWriteArrayList) {
        kotlin.jvm.internal.n.g(copyOnWriteArrayList, "<set-?>");
        this.contentViewList = copyOnWriteArrayList;
    }

    public final void setCurrentUserId(int i2) {
        this.currentUserId = i2;
    }

    public final void setEffectSize(Rect rect) {
        kotlin.jvm.internal.n.g(rect, "rect");
    }

    public final void setEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        this.eventCoordinator = dynamicIslandEventCoordinator;
    }

    public final void setHeadsUpHeight(int i2) {
        this._headsUpHeight = i2;
        if (i2 == 0 && this.touchOutsideInHeadsUp) {
            post(new Runnable() { // from class: miui.systemui.dynamicisland.window.m
                @Override // java.lang.Runnable
                public final void run() throws PendingIntent.CanceledException {
                    DynamicIslandWindowView._set_headsUpHeight_$lambda$1(this.f5753a);
                }
            });
            this.touchOutsideInHeadsUp = false;
        }
    }

    public final void setLight(boolean z2) {
        this.isLight = z2;
    }

    public final void setTouchOutsideInHeadsUp(boolean z2) {
        this.touchOutsideInHeadsUp = z2;
    }

    public final void setViewComponent(DynamicIslandViewComponent value) {
        kotlin.jvm.internal.n.g(value, "value");
        if (this._viewComponent != null) {
            throw new IllegalStateException("DynamicIslandViewComponent was already initialized.");
        }
        this._viewComponent = value;
        initEventCoordinator();
    }

    public final boolean touchInHeadsUpZone(int i2) {
        I headsUpZone;
        H0.i iVar;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        return (dynamicIslandEventCoordinator == null || (headsUpZone = dynamicIslandEventCoordinator.getHeadsUpZone()) == null || (iVar = (H0.i) headsUpZone.getValue()) == null || ((Number) iVar.d()).intValue() == 0 || ((Number) iVar.e()).intValue() == 0 || i2 <= ((Number) iVar.d()).intValue() || i2 >= ((Number) iVar.e()).intValue()) ? false : true;
    }

    public final void updateAppCloseRect(Rect rect, DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        Bundle extras;
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "updateAppCloseRect : " + rect);
        if (dynamicIslandContentView != null && (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) != null && (extras = currentIslandData2.getExtras()) != null) {
            extras.putParcelable("position", rect);
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.positionChanged((dynamicIslandContentView == null || (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData.getExtras());
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public final void updateDarkLightMode(boolean z2) {
        this.isLight = z2;
        for (DynamicIslandContentView dynamicIslandContentView : this.contentViewList) {
        }
    }

    public final void updateDynamicIslandView(final DynamicIslandData dynamicIslandData, final boolean z2, final float f2) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandContentFakeView fakeView;
        kotlin.jvm.internal.n.g(dynamicIslandData, "dynamicIslandData");
        Log.e(TAG, "updateDynamicIslandView");
        if (dynamicIslandData.getKey() == null) {
            return;
        }
        updateDynamicIslandDataList(dynamicIslandData);
        if (assertUserSpace(dynamicIslandData)) {
            Bundle extras = dynamicIslandData.getExtras();
            String string = extras != null ? extras.getString("miui.pkg.name") : null;
            final DynamicIslandContentView viewFromList = getViewFromList(dynamicIslandData.getKey());
            if ((viewFromList != null ? viewFromList.getState() : null) == null) {
                if (viewFromList != null) {
                    removeDynamicIslandData(viewFromList.getCurrentIslandData(), false);
                }
                addDynamicIslandData$default(this, dynamicIslandData, z2, getWindowViewController().getIslandMaxWidth(), getWindowViewController().getCutoutY(), false, 16, null);
                return;
            }
            Bundle extras2 = dynamicIslandData.getExtras();
            notifyAddIsland(string, extras2 != null ? Integer.valueOf(extras2.getInt("miui.user.id")) : null, dynamicIslandData.getKey(), dynamicIslandData.getProperties());
            if (viewFromList != null) {
                viewFromList.updateView(dynamicIslandData, true, canUpdate(viewFromList));
            }
            if (viewFromList != null && (fakeView = viewFromList.getFakeView()) != null) {
                DynamicIslandBaseContentView.updateView$default(fakeView, dynamicIslandData, true, false, 4, null);
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = this.eventCoordinator;
            if ((dynamicIslandEventCoordinator2 == null || !dynamicIslandEventCoordinator2.getUserExpanded()) && canExpanded(z2, dynamicIslandData.getView()) && (dynamicIslandEventCoordinator = this.eventCoordinator) != null) {
                dynamicIslandEventCoordinator.onHeightChangedFirst();
            }
            OneShotPreDrawListener.add(this, new Runnable() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowView$updateDynamicIslandView$$inlined$doOnPreDraw$1
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicIslandState state;
                    DynamicIslandExpandedView expandedView;
                    DynamicIslandExpandedView expandedView2;
                    View view = dynamicIslandData.getView();
                    Integer numValueOf = view != null ? Integer.valueOf(view.getWidth()) : null;
                    View view2 = dynamicIslandData.getView();
                    Integer numValueOf2 = view2 != null ? Integer.valueOf(view2.getHeight()) : null;
                    DynamicIslandContentView dynamicIslandContentView = viewFromList;
                    Integer numValueOf3 = (dynamicIslandContentView == null || (expandedView2 = dynamicIslandContentView.getExpandedView()) == null) ? null : Integer.valueOf(expandedView2.getWidth());
                    DynamicIslandContentView dynamicIslandContentView2 = viewFromList;
                    Log.e("DynamicIslandWindowViewImpl", "expanded" + numValueOf + ", " + numValueOf2 + ", " + numValueOf3 + ", " + ((dynamicIslandContentView2 == null || (expandedView = dynamicIslandContentView2.getExpandedView()) == null) ? null : Integer.valueOf(expandedView.getMeasuredHeight())));
                    boolean zContains = this.getContentViewList().contains(viewFromList);
                    StringBuilder sb = new StringBuilder();
                    sb.append("updateDynamicIslandView contentViewList ");
                    sb.append(zContains);
                    Log.e("DynamicIslandWindowViewImpl", sb.toString());
                    if (this.getContentViewList().contains(viewFromList)) {
                        DynamicIslandContentView viewFromList2 = this.getViewFromList(dynamicIslandData.getKey());
                        if (viewFromList2 == null) {
                            DynamicIslandWindowView dynamicIslandWindowView = this;
                            DynamicIslandWindowView.addDynamicIslandData$default(dynamicIslandWindowView, dynamicIslandData, z2, f2, dynamicIslandWindowView.getWindowViewController().getCutoutY(), false, 16, null);
                            return;
                        }
                        this.measureExpandedViewHeight(viewFromList2);
                        DynamicIslandState state2 = viewFromList2.getState();
                        if (state2 != null) {
                            String key = dynamicIslandData.getKey();
                            kotlin.jvm.internal.n.d(key);
                            state2.setUpdateKey(key);
                        }
                        viewFromList2.setCurrentIslandData(dynamicIslandData);
                        IslandTemplate islandTemplate = this.getWindowViewController().getIslandTemplate(dynamicIslandData);
                        Boolean boolValueOf = islandTemplate != null ? Boolean.valueOf(islandTemplate.getIslandOrder()) : null;
                        if ((z2 || kotlin.jvm.internal.n.c(boolValueOf, Boolean.TRUE)) && (state = viewFromList2.getState()) != null) {
                            state.setTime(Long.valueOf(System.currentTimeMillis()));
                        }
                        DynamicIslandState state3 = viewFromList2.getState();
                        if (state3 != null) {
                            state3.setUpdateOrder(boolValueOf);
                        }
                        DynamicIslandState state4 = viewFromList2.getState();
                        if (state4 != null) {
                            state4.setExpanded(this.canExpanded(z2, dynamicIslandData.getView()));
                        }
                        if (this.canUpdate(viewFromList2)) {
                            if (((Boolean) this.getWindowViewController().isAppAnimRunning().getValue()).booleanValue() && (viewFromList2.getState() instanceof DynamicIslandState.BigIsland)) {
                                this.getWindowViewController().updateAppRect(viewFromList2, false);
                                DynamicIslandContentFakeView fakeView2 = viewFromList2.getFakeView();
                                if (fakeView2 != null) {
                                    fakeView2.setForceUpdateBigIslandView(true);
                                }
                            }
                            DynamicIslandEventCoordinator eventCoordinator = this.getEventCoordinator();
                            if (eventCoordinator != null) {
                                eventCoordinator.dispatchEvent(DynamicIslandEvent.UpdateDynamicIsland.INSTANCE, viewFromList2);
                            }
                            DynamicIslandContentFakeView fakeView3 = viewFromList2.getFakeView();
                            if (fakeView3 == null) {
                                return;
                            }
                            fakeView3.setForceUpdateBigIslandView(false);
                        }
                    }
                }
            });
            getWindowViewController().updateChronometersIn(viewFromList, viewFromList.getFakeView(), dynamicIslandData);
        }
    }

    public final void updateExpandedViewScaleForFreeform(DynamicIslandContentView dynamicIslandContentView, Float f2, boolean z2) {
        DynamicIslandContentFakeView fakeView;
        if (f2 != null) {
            float fFloatValue = f2.floatValue();
            if (dynamicIslandContentView == null || (fakeView = dynamicIslandContentView.getFakeView()) == null) {
                return;
            }
            fakeView.updateExpandedViewScaleForFreeform(fFloatValue, z2);
        }
    }

    public final void updateFreeformFakeView(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandData currentIslandData;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            Bundle extras = null;
            DynamicIslandContentFakeView fakeView = dynamicIslandContentView != null ? dynamicIslandContentView.getFakeView() : null;
            if (dynamicIslandContentView != null && (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) != null) {
                extras = currentIslandData.getExtras();
            }
            dynamicIslandEventCoordinator.updateFreeformFakeView(fakeView, dynamicIslandContentView, extras);
        }
    }

    public final void updateHeadsUpZone(int i2) {
        int iDpToPx;
        int i3;
        setHeadsUpHeight(i2);
        DynamicIslandContentView currentExpandedState = getCurrentExpandedState();
        if (currentExpandedState == null || i2 == 0) {
            iDpToPx = 0;
            i3 = 0;
        } else {
            int expandedViewY = currentExpandedState.getExpandedViewY() + ((int) (currentExpandedState.getIslandViewHeight() * (currentExpandedState.getExpandedViewHeight() / currentExpandedState.getIslandViewHeight())));
            DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
            Context context = getContext();
            kotlin.jvm.internal.n.f(context, "getContext(...)");
            iDpToPx = expandedViewY + dynamicIslandUtils.dpToPx(8, context);
            i3 = i2 + iDpToPx;
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.updateHeadsUpZone(new H0.i(Integer.valueOf(iDpToPx), Integer.valueOf(i3)));
        }
    }

    public final void updateIslandWindowAnimRunningState(boolean z2, DynamicIslandContentView dynamicIslandContentView, boolean z3) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.updateIslandWindowAnimRunning(z2, dynamicIslandContentView, z3);
        }
    }

    public final void updatePkgSupportFreeform(String pkg) {
        kotlin.jvm.internal.n.g(pkg, "pkg");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            Context context = getContext();
            kotlin.jvm.internal.n.f(context, "getContext(...)");
            dynamicIslandEventCoordinator.updatePkgSupportFreeform(pkg, context);
        }
    }

    public final void updateStatusBarVisible(boolean z2) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.updateStatusBarVisible(z2);
        }
    }

    public final void updateTouchRegion() {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.updateTouchRegion();
        }
    }

    public final void updateViewStateWhenCloseEnd(DynamicIslandContentView dynamicIslandContentView, boolean z2, String from) {
        kotlin.jvm.internal.n.g(from, "from");
        AbstractC0369g.b(getWindowViewController().getUiScope(), null, null, new C06391(dynamicIslandContentView, z2, from, null), 3, null);
    }

    public final void updateViewStateWhenOpenAnimStart(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandContentFakeView fakeView;
        if (dynamicIslandContentView != null && (fakeView = dynamicIslandContentView.getFakeView()) != null) {
            fakeView.updateViewStateWhenOpenAnimStart();
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.updateAppExpandedStateWhenAnimStart(dynamicIslandContentView);
        }
    }

    public static /* synthetic */ void removeDynamicIslandData$default(DynamicIslandWindowView dynamicIslandWindowView, String str, String str2, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        if ((i2 & 8) != 0) {
            z3 = false;
        }
        dynamicIslandWindowView.removeDynamicIslandData(str, str2, z2, z3);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public LifecycleRegistry getLifecycle() {
        return (LifecycleRegistry) this.lifecycle$delegate.getValue();
    }

    public final void removeDynamicIslandData(String str, String str2, boolean z2, boolean z3) {
        s sVar;
        Log.d(TAG, "removeDynamicIslandData: " + str);
        DynamicIslandContentView viewFromList = getViewFromList(str);
        if (viewFromList != null) {
            DynamicIslandState state = viewFromList.getState();
            if (state != null) {
                state.setDeleteNoAnimation(z3);
            }
            DynamicIslandBigIslandView bigIslandView = viewFromList.getBigIslandView();
            if (bigIslandView != null) {
                bigIslandView.suppressAdaptiveGlowViewSize$miui_dynamicisland_release();
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
            if (dynamicIslandEventCoordinator != null) {
                dynamicIslandEventCoordinator.dispatchEvent(DynamicIslandEvent.DeletedDynamicIsland.INSTANCE, viewFromList);
            }
            clearAfterDelete(viewFromList.getCurrentIslandData(), str, z2);
            sVar = s.f314a;
        } else {
            sVar = null;
        }
        if (sVar == null) {
            clearAfterDelete(null, str, z2);
        }
    }
}
