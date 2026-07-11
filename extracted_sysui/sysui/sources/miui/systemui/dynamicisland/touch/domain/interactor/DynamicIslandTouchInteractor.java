package miui.systemui.dynamicisland.touch.domain.interactor;

import E0.a;
import H0.i;
import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import g1.AbstractC0369g;
import g1.E;
import j1.AbstractC0420h;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.K;
import j1.u;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationController;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.event.handler.ExpandedStateHandler;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler;
import miui.systemui.dynamicisland.touch.Direction;
import miui.systemui.dynamicisland.touch.TouchEvent;
import miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor;
import miui.systemui.util.BoostHelper;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandTouchInteractor implements DynamicIslandStartable {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DynamicIslandTouchInteractor";
    private final u _swipeInfo;
    private final DynamicIslandAnimationController animationController;
    private final a bigIslandStateHandler;
    private final Context context;
    private Direction direction;
    private boolean downInBigIsland;
    private boolean downInDefault;
    private boolean downInExpanded;
    private boolean downInFreeformAnim;
    private boolean downInMedia;
    private boolean downInSeekBar;
    private boolean downInSmallIsland;
    private final a eventCoordinator;
    private final a expandedStateHandler;
    private final DynamicIslandExternalTouchInteractor externalTouchInteractor;
    private float initRawY;
    private float initX;
    private float initY;
    private Boolean intercepted;
    private boolean longClickReceived;
    private final E scope;
    private final a smallIslandStateHandler;
    private boolean swipeDispatched;
    private final I swipeInfo;
    private final Context sysUIContext;
    private final DynamicIslandTouchConstantsRepository touchConstants;
    private final DynamicIslandTouchRegionInteractor touchRegionInteractor;
    private String trackingPackage;
    private final DynamicIslandWindowState windowState;
    private final DynamicIslandWindowView windowView;
    private final DynamicIslandWindowViewController windowViewController;
    private final DynamicIslandWindowViewTouchInteractor windowViewTouchInteractor;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor$handleOutsideEvents$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor$handleOutsideEvents$1", f = "DynamicIslandTouchInteractor.kt", l = {114}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandTouchInteractor.this.new AnonymousClass1(dVar);
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
                InterfaceC0418f outsideTouchEvent = DynamicIslandTouchInteractor.this.windowViewTouchInteractor.getOutsideTouchEvent();
                final DynamicIslandTouchInteractor dynamicIslandTouchInteractor = DynamicIslandTouchInteractor.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor.handleOutsideEvents.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(TouchEvent touchEvent, d dVar) throws PendingIntent.CanceledException {
                        I trackingToOpenMW;
                        dynamicIslandTouchInteractor.logEvent(touchEvent.getEvent(), "outside touch event " + touchEvent);
                        if (dynamicIslandTouchInteractor.getInHeadsUpZone(touchEvent.getEvent())) {
                            dynamicIslandTouchInteractor.windowView.setTouchOutsideInHeadsUp(true);
                        }
                        DynamicIslandContentFakeView fakeView = dynamicIslandTouchInteractor.getFakeView();
                        if (((DynamicIslandEventCoordinator) dynamicIslandTouchInteractor.eventCoordinator.get()).getUserExpanded() && !((DynamicIslandEventCoordinator) dynamicIslandTouchInteractor.eventCoordinator.get()).getEnterMiniWindow() && ((fakeView == null || (trackingToOpenMW = fakeView.getTrackingToOpenMW()) == null || !((Boolean) trackingToOpenMW.getValue()).booleanValue()) && !dynamicIslandTouchInteractor.getInHeadsUpZone(touchEvent.getEvent()))) {
                            dynamicIslandTouchInteractor.windowView.collapse("outside");
                        }
                        touchEvent.setResult(b.a(true));
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (outsideTouchEvent.collect(interfaceC0419g, this) == objC) {
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

    public DynamicIslandTouchInteractor(@DynamicIsland E scope, Context context, @SystemUI Context sysUIContext, DynamicIslandTouchConstantsRepository touchConstants, DynamicIslandWindowView windowView, DynamicIslandExternalTouchInteractor externalTouchInteractor, DynamicIslandWindowViewTouchInteractor windowViewTouchInteractor, a eventCoordinator, a bigIslandStateHandler, a smallIslandStateHandler, a expandedStateHandler, DynamicIslandWindowState windowState, DynamicIslandWindowViewController windowViewController, DynamicIslandAnimationController animationController, DynamicIslandTouchRegionInteractor touchRegionInteractor) {
        n.g(scope, "scope");
        n.g(context, "context");
        n.g(sysUIContext, "sysUIContext");
        n.g(touchConstants, "touchConstants");
        n.g(windowView, "windowView");
        n.g(externalTouchInteractor, "externalTouchInteractor");
        n.g(windowViewTouchInteractor, "windowViewTouchInteractor");
        n.g(eventCoordinator, "eventCoordinator");
        n.g(bigIslandStateHandler, "bigIslandStateHandler");
        n.g(smallIslandStateHandler, "smallIslandStateHandler");
        n.g(expandedStateHandler, "expandedStateHandler");
        n.g(windowState, "windowState");
        n.g(windowViewController, "windowViewController");
        n.g(animationController, "animationController");
        n.g(touchRegionInteractor, "touchRegionInteractor");
        this.scope = scope;
        this.context = context;
        this.sysUIContext = sysUIContext;
        this.touchConstants = touchConstants;
        this.windowView = windowView;
        this.externalTouchInteractor = externalTouchInteractor;
        this.windowViewTouchInteractor = windowViewTouchInteractor;
        this.eventCoordinator = eventCoordinator;
        this.bigIslandStateHandler = bigIslandStateHandler;
        this.smallIslandStateHandler = smallIslandStateHandler;
        this.expandedStateHandler = expandedStateHandler;
        this.windowState = windowState;
        this.windowViewController = windowViewController;
        this.animationController = animationController;
        this.touchRegionInteractor = touchRegionInteractor;
        this.direction = Direction.UNKNOWN;
        Float fValueOf = Float.valueOf(0.0f);
        u uVarA = K.a(new i(fValueOf, fValueOf));
        this._swipeInfo = uVarA;
        this.swipeInfo = AbstractC0420h.b(uVarA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DynamicIslandContentFakeView getFakeView() {
        DynamicIslandContentFakeView miniWindowContentFakeView;
        String str = this.trackingPackage;
        if (str != null && (miniWindowContentFakeView = this.windowView.getMiniWindowContentFakeView(str)) != null) {
            if (this.downInDefault || this.downInBigIsland || this.downInSmallIsland) {
                miniWindowContentFakeView = null;
            }
            if (miniWindowContentFakeView != null) {
                return miniWindowContentFakeView;
            }
        }
        DynamicIslandContentView currentExpandedState = this.windowView.getCurrentExpandedState();
        if (currentExpandedState != null) {
            return currentExpandedState.getFakeView();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getInHeadsUpZone(MotionEvent motionEvent) {
        i iVar = (i) ((DynamicIslandEventCoordinator) this.eventCoordinator.get()).getHeadsUpZone().getValue();
        if (iVar == null) {
            return false;
        }
        float y2 = motionEvent.getY();
        return ((Number) iVar.d()).intValue() != 0 && ((Number) iVar.e()).intValue() != 0 && y2 > ((float) ((Number) iVar.d()).intValue()) && y2 < ((float) ((Number) iVar.e()).intValue());
    }

    private final DynamicIslandExternalTouchInteractor handleExternalEvent() {
        DynamicIslandExternalTouchInteractor dynamicIslandExternalTouchInteractor = this.externalTouchInteractor;
        AbstractC0369g.b(this.scope, null, null, new DynamicIslandTouchInteractor$handleExternalEvent$1$1(dynamicIslandExternalTouchInteractor, this, null), 3, null);
        AbstractC0369g.b(this.scope, null, null, new DynamicIslandTouchInteractor$handleExternalEvent$1$2(dynamicIslandExternalTouchInteractor, this, null), 3, null);
        return dynamicIslandExternalTouchInteractor;
    }

    private final void handleOutsideEvents() {
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null);
    }

    private final DynamicIslandWindowViewTouchInteractor handleWindowViewEvent() {
        DynamicIslandWindowViewTouchInteractor dynamicIslandWindowViewTouchInteractor = this.windowViewTouchInteractor;
        AbstractC0369g.b(this.scope, null, null, new DynamicIslandTouchInteractor$handleWindowViewEvent$1$1(dynamicIslandWindowViewTouchInteractor, this, null), 3, null);
        AbstractC0369g.b(this.scope, null, null, new DynamicIslandTouchInteractor$handleWindowViewEvent$1$2(dynamicIslandWindowViewTouchInteractor, this, null), 3, null);
        return dynamicIslandWindowViewTouchInteractor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logEvent(MotionEvent motionEvent, String str) {
        if (TouchEvent.Companion.getShouldLog(motionEvent)) {
            Log.d(TAG, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v7 */
    public final Boolean onInterceptTouchEvent(MotionEvent motionEvent, String str) {
        Boolean bool;
        ?? r5;
        int i2;
        DynamicIslandContentView currentSmallIslandState;
        DynamicIslandContentFakeView fakeView;
        DynamicIslandData currentIslandData;
        Bundle extras;
        boolean zInRect;
        Rect expandedIslandRect;
        Rect bigIslandRect$default;
        int actionMasked = motionEvent.getActionMasked();
        DynamicIslandContentView current = ((BigIslandStateHandler) this.bigIslandStateHandler.get()).getCurrent();
        DynamicIslandContentView current2 = ((SmallIslandStateHandler) this.smallIslandStateHandler.get()).getCurrent();
        DynamicIslandContentView current3 = ((ExpandedStateHandler) this.expandedStateHandler.get()).getCurrent();
        if (actionMasked == 0) {
            this.initX = motionEvent.getX();
            this.initY = motionEvent.getY();
            this.initRawY = motionEvent.getRawY();
            this.intercepted = null;
            this.swipeDispatched = false;
            this.direction = Direction.UNKNOWN;
            this.longClickReceived = false;
            this.downInBigIsland = (current == null || (bigIslandRect$default = DynamicIslandBaseContentView.getBigIslandRect$default(current, null, 1, null)) == null) ? false : TouchEvent.Companion.inRect(motionEvent, bigIslandRect$default);
            if (current == null || current2 == null) {
                zInRect = false;
            } else {
                zInRect = TouchEvent.Companion.inRect(motionEvent, current2.getSmallIslandRect(CommonUtils.isLayoutRtl(this.context) ? (DynamicIslandBaseContentView.getCurrentBigIslandX$default(current, null, 1, null) - current.getSpace()) - current.getSmallIslandViewWidth() : DynamicIslandBaseContentView.getCurrentBigIslandX$default(current, null, 1, null) + DynamicIslandBaseContentView.getCurrentBigIslandWidth$default(current, null, 1, null) + current.getSpace()));
            }
            this.downInSmallIsland = zInRect;
            this.downInExpanded = (current3 == null || (expandedIslandRect = current3.getExpandedIslandRect()) == null) ? false : TouchEvent.Companion.inRect(motionEvent, expandedIslandRect);
            this.downInDefault = TouchEvent.Companion.inRegion(motionEvent, ((DynamicIslandEventCoordinator) this.eventCoordinator.get()).getDefaultIslandTouchRegion());
            this.downInFreeformAnim = ((Boolean) this.windowViewController.isFreeformAnimRunning().getValue()).booleanValue();
            bool = null;
            this.downInSeekBar = this.windowView.downInMedia(this.sysUIContext, this.initX, this.initY, current3, true);
            r5 = 0;
            i2 = 1;
            boolean zDownInMedia$default = DynamicIslandWindowView.downInMedia$default(this.windowView, this.sysUIContext, this.initX, this.initY, current3, false, 16, null);
            this.downInMedia = zDownInMedia$default;
            if (!zDownInMedia$default && !this.windowViewController.windowAnimating()) {
                ((DynamicIslandEventCoordinator) this.eventCoordinator.get()).dispatchPress(this.downInBigIsland, this.downInSmallIsland, this.downInExpanded);
            }
            DynamicIslandContentFakeView fakeView2 = getFakeView();
            if (fakeView2 != null) {
                fakeView2.resetTrackOpenMW();
            }
            BoostHelper.getInstance().setDynamicVIPTaskIfNeeded(1000, this.windowView);
            BoostHelper.getInstance().requestDynamicIslandThreadLevelPriority(1000, this.windowView);
            Log.d(TAG, "intercept down, downInBigIsland: " + this.downInBigIsland + ", downInSmallIsland: " + this.downInSmallIsland + ", downInExpanded: " + this.downInExpanded + ", downInDefault: " + this.downInDefault + ", downInSeekBar:" + this.downInSeekBar + ", downInMedia " + this.downInMedia + ".");
        } else {
            bool = null;
            r5 = 0;
            i2 = 1;
        }
        Boolean bool2 = this.intercepted;
        Boolean bool3 = Boolean.TRUE;
        if (n.c(bool2, bool3)) {
            return bool3;
        }
        Boolean bool4 = this.intercepted;
        Boolean boolValueOf = Boolean.FALSE;
        if (n.c(bool4, boolValueOf)) {
            return bool;
        }
        if (actionMasked == 2) {
            float x2 = motionEvent.getX() - this.initX;
            float y2 = motionEvent.getY() - this.initY;
            float fAbs = Math.abs(x2);
            float fAbs2 = Math.abs(y2);
            float fIntValue = ((Number) this.touchConstants.getTouchSlop().getValue()).intValue();
            if (fAbs > fIntValue || fAbs2 > fIntValue) {
                Direction direction = (fAbs <= fAbs2 || x2 <= 0.0f) ? fAbs > fAbs2 ? Direction.LEFT : y2 > 0.0f ? Direction.DOWN : Direction.UP : Direction.RIGHT;
                this.direction = direction;
                if (this.downInBigIsland || this.downInSmallIsland) {
                    boolValueOf = Boolean.valueOf(direction.isHorizontal());
                } else if (this.downInSeekBar) {
                    boolValueOf = Boolean.valueOf(direction.isVertical());
                } else if (this.downInExpanded) {
                    boolValueOf = bool3;
                } else if (this.downInDefault) {
                    boolValueOf = Boolean.valueOf(direction.isHorizontal());
                } else if (this.downInFreeformAnim) {
                    boolValueOf = Boolean.valueOf(direction.isUp());
                }
                this.intercepted = boolValueOf;
                Log.d(TAG, "intercepted: " + boolValueOf + ", source: " + str + ".");
                ((DynamicIslandEventCoordinator) this.eventCoordinator.get()).resetPress(this.downInBigIsland, this.downInSmallIsland, this.downInExpanded);
                if (this.downInExpanded && n.c(this.intercepted, bool3)) {
                    DynamicIslandContentView currentExpandedState = this.windowView.getCurrentExpandedState();
                    this.trackingPackage = (currentExpandedState == null || (currentIslandData = currentExpandedState.getCurrentIslandData()) == null || (extras = currentIslandData.getExtras()) == null) ? bool : extras.getString("miui.pkg.name");
                    MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                    motionEventObtain.setAction(r5);
                    DynamicIslandContentView currentExpandedState2 = this.windowView.getCurrentExpandedState();
                    if (currentExpandedState2 != null && (fakeView = currentExpandedState2.getFakeView()) != 0) {
                        fakeView.handleTouchEvent(motionEventObtain, r5);
                    }
                    motionEventObtain.recycle();
                }
            }
        }
        if (actionMasked == i2 || actionMasked == 3) {
            ((DynamicIslandEventCoordinator) this.eventCoordinator.get()).resetPress(this.downInBigIsland, this.downInSmallIsland, this.downInExpanded);
            if (this.downInBigIsland) {
                DynamicIslandContentView currentBigIslandState = this.windowView.getCurrentBigIslandState();
                if (currentBigIslandState != null) {
                    this.windowView.cancelLongPressRunnable(currentBigIslandState);
                }
            } else if (this.downInSmallIsland && (currentSmallIslandState = this.windowView.getCurrentSmallIslandState()) != null) {
                this.windowView.cancelLongPressRunnable(currentSmallIslandState);
            }
        }
        return (this.longClickReceived || n.c(this.intercepted, bool3)) ? bool3 : bool;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Boolean onTouchEvent(android.view.MotionEvent r14, java.lang.String r15) throws android.app.PendingIntent.CanceledException {
        /*
            Method dump skipped, instruction units count: 563
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor.onTouchEvent(android.view.MotionEvent, java.lang.String):java.lang.Boolean");
    }

    private static final void performPressed$press(DynamicIslandTouchInteractor dynamicIslandTouchInteractor, DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        if (z2) {
            dynamicIslandTouchInteractor.animationController.onPress(dynamicIslandContentView);
        } else {
            dynamicIslandTouchInteractor.animationController.resetPress(dynamicIslandContentView);
        }
    }

    public final I getSwipeInfo() {
        return this.swipeInfo;
    }

    public final void notifyLongClickReceived() {
        this.longClickReceived = true;
    }

    public final void performClick() {
        DynamicIslandContentView current;
        if (this.downInExpanded) {
            DynamicIslandContentView current2 = ((ExpandedStateHandler) this.expandedStateHandler.get()).getCurrent();
            if (current2 != null) {
                current2.onIslandClick();
                return;
            }
            return;
        }
        if (this.downInBigIsland) {
            DynamicIslandContentView current3 = ((BigIslandStateHandler) this.bigIslandStateHandler.get()).getCurrent();
            if (current3 != null) {
                current3.onIslandClick();
                return;
            }
            return;
        }
        if (!this.downInSmallIsland || (current = ((SmallIslandStateHandler) this.smallIslandStateHandler.get()).getCurrent()) == null) {
            return;
        }
        current.onIslandClick();
    }

    public final boolean performLongClick() {
        DynamicIslandContentView currentSmallIslandState;
        if (this.downInExpanded) {
            return false;
        }
        if (((BigIslandStateHandler) this.bigIslandStateHandler.get()).getCurrentTempShow() != null) {
            return true;
        }
        if (DynamicIslandUtils.INSTANCE.isInternationalBuild()) {
            return false;
        }
        this.longClickReceived = true;
        ((DynamicIslandEventCoordinator) this.eventCoordinator.get()).resetPress(this.downInBigIsland, this.downInSmallIsland, this.downInExpanded);
        if (this.downInBigIsland) {
            DynamicIslandContentView currentBigIslandState = this.windowView.getCurrentBigIslandState();
            if (currentBigIslandState != null) {
                this.windowView.onLongPress(currentBigIslandState, currentBigIslandState, this.initRawY);
            }
        } else if (this.downInSmallIsland && (currentSmallIslandState = this.windowView.getCurrentSmallIslandState()) != null) {
            this.windowView.onLongPress(currentSmallIslandState, currentSmallIslandState, this.initRawY);
        }
        return true;
    }

    public final void performPressed(boolean z2) {
        if (this.downInExpanded) {
            performPressed$press(this, ((ExpandedStateHandler) this.expandedStateHandler.get()).getCurrent(), z2);
        } else if (this.downInBigIsland) {
            performPressed$press(this, ((BigIslandStateHandler) this.bigIslandStateHandler.get()).getCurrent(), z2);
        } else if (this.downInSmallIsland) {
            performPressed$press(this, ((SmallIslandStateHandler) this.smallIslandStateHandler.get()).getCurrent(), z2);
        }
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        handleOutsideEvents();
        handleExternalEvent();
        handleWindowViewEvent();
    }
}
