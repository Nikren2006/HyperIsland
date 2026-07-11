package miui.systemui.dynamicisland.event;

import H0.s;
import I0.u;
import android.content.Context;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.StateHandler;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class SwipeEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "SwipeEventCoordinator";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.SwipeEventCoordinator$handleAppEvent$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function1 {
        final /* synthetic */ DynamicIslandContentView $bigState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(DynamicIslandContentView dynamicIslandContentView) {
            super(1);
            this.$bigState = dynamicIslandContentView;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(DynamicIslandContentView it) {
            n.g(it, "it");
            DynamicIslandData currentIslandData = it.getCurrentIslandData();
            String key = currentIslandData != null ? currentIslandData.getKey() : null;
            DynamicIslandData currentIslandData2 = this.$bigState.getCurrentIslandData();
            return Boolean.valueOf(n.c(key, currentIslandData2 != null ? currentIslandData2.getKey() : null));
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.SwipeEventCoordinator$handleSwipe$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final CharSequence invoke(DynamicIslandContentView it) {
            n.g(it, "it");
            DynamicIslandData currentIslandData = it.getCurrentIslandData();
            return String.valueOf(currentIslandData != null ? currentIslandData.getKey() : null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwipeEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean handleAppEvent$lambda$1(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        n.g(event, "event");
        if (event instanceof DynamicIslandEvent.SwipeLeft) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "handleEvent: " + event);
            DynamicIslandContentView current = getBigIslandStateHandler().getCurrent();
            DynamicIslandContentView current2 = getSmallIslandStateHandler().getCurrent();
            DynamicIslandContentView current3 = getHiddenStateHandler().getCurrent();
            if (current != null) {
                Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "states: " + current + " " + current2 + " " + current3);
                if (current2 != null || current3 == null || (current3.getState() instanceof DynamicIslandState.Empty)) {
                    DynamicIslandState state = current.getState();
                    DynamicIslandState dynamicIslandStateChangeToState = state != null ? state.changeToState(new DynamicIslandState.Hidden()) : null;
                    Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "SwipeLeft " + arrayList);
                    getBigIslandStateHandler().addState(current, dynamicIslandStateChangeToState);
                    if (!hasEmpty(arrayList) && arrayList != null) {
                        Context context = current.getContext();
                        n.f(context, "getContext(...)");
                        DynamicIslandContentView dynamicIslandContentView2 = new DynamicIslandContentView(context, null, 2, null);
                        dynamicIslandContentView2.setState(new DynamicIslandState.Empty());
                        arrayList.add(dynamicIslandContentView2);
                    }
                    if (arrayList != null) {
                        final AnonymousClass2 anonymousClass2 = new AnonymousClass2(current);
                        arrayList.removeIf(new Predicate() { // from class: miui.systemui.dynamicisland.event.a
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                return SwipeEventCoordinator.handleAppEvent$lambda$1(anonymousClass2, obj);
                            }
                        });
                    }
                    if (arrayList != null) {
                        arrayList.add(current);
                    }
                    getBigIslandStateHandler().handleFillInState(arrayList);
                } else {
                    StateHandler.addState$default(getSmallIslandStateHandler(), current, null, 2, null);
                    getSmallIslandStateHandler().handleFillInState(arrayList);
                }
            } else {
                if (!hasEmpty(arrayList) && arrayList != null) {
                    Context context2 = getDynamicIslandEventCoordinator().getWindowView().getContext();
                    n.f(context2, "getContext(...)");
                    DynamicIslandContentView dynamicIslandContentView3 = new DynamicIslandContentView(context2, null, 2, null);
                    dynamicIslandContentView3.setState(new DynamicIslandState.Empty());
                    arrayList.add(dynamicIslandContentView3);
                }
                getBigIslandStateHandler().handleFillInState(arrayList);
                getSmallIslandStateHandler().handleFillInState(arrayList);
            }
            DynamicIslandContentView current4 = getExpandedStateHandler().getCurrent();
            if (current4 != null) {
                getExpandedStateHandler().setCurrent(null);
                EventCoordinator.collapse$default(this, current4, arrayList, false, 4, null);
            }
            getStateHandler().stop();
        }
        if (event instanceof DynamicIslandEvent.SwipeRight) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "handleEvent: " + event);
            if (arrayList == null || arrayList.size() == 0) {
                Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "hiddenList is null");
                return;
            }
            DynamicIslandContentView dynamicIslandContentView4 = arrayList.get(arrayList.size() - 1);
            n.f(dynamicIslandContentView4, "get(...)");
            DynamicIslandContentView dynamicIslandContentView5 = dynamicIslandContentView4;
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "hiddenState: " + dynamicIslandContentView5);
            if (dynamicIslandContentView5.getState() instanceof DynamicIslandState.Empty) {
                DynamicIslandContentView current5 = getSmallIslandStateHandler().getCurrent();
                Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "smallState: " + current5);
                if (current5 != null) {
                    StateHandler.addState$default(getHiddenStateHandler(), current5, null, 2, null);
                    StateHandler hiddenStateHandler = getHiddenStateHandler();
                    Boolean bool = Boolean.TRUE;
                    hiddenStateHandler.handleReplacedState(current5, arrayList, bool);
                    DynamicIslandContentView current6 = getBigIslandStateHandler().getCurrent();
                    if (current6 != null) {
                        StateHandler.addState$default(getHiddenStateHandler(), current6, null, 2, null);
                        getHiddenStateHandler().handleReplacedState(current6, arrayList, bool);
                    }
                    DynamicIslandContentView dynamicIslandContentView6 = (DynamicIslandContentView) u.V(arrayList);
                    if (dynamicIslandContentView6.getState() instanceof DynamicIslandState.Empty) {
                        arrayList.remove(dynamicIslandContentView6);
                    }
                    getSmallIslandStateHandler().setCurrent(null);
                    getBigIslandStateHandler().setCurrent(null);
                } else {
                    DynamicIslandContentView current7 = getBigIslandStateHandler().getCurrent();
                    if (current7 != null) {
                        getHiddenStateHandler().handleReplacedState(current7, arrayList, Boolean.TRUE);
                        getBigIslandStateHandler().setCurrent(null);
                        DynamicIslandContentView dynamicIslandContentView7 = arrayList.get(arrayList.size() - 1);
                        n.f(dynamicIslandContentView7, "get(...)");
                        DynamicIslandContentView dynamicIslandContentView8 = dynamicIslandContentView7;
                        Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "hidden: " + dynamicIslandContentView8);
                        arrayList.remove(dynamicIslandContentView8);
                    }
                }
            } else {
                if (!hasEmpty(arrayList)) {
                    Context context3 = getDynamicIslandEventCoordinator().getWindowView().getContext();
                    n.f(context3, "getContext(...)");
                    DynamicIslandContentView dynamicIslandContentView9 = new DynamicIslandContentView(context3, null, 2, null);
                    dynamicIslandContentView9.setState(new DynamicIslandState.Empty());
                    s sVar = s.f314a;
                    arrayList.add(0, dynamicIslandContentView9);
                }
                arrayList.remove(dynamicIslandContentView5);
                getHiddenStateHandler().handleFillInState(arrayList);
                getBigIslandStateHandler().handleReplacedState(dynamicIslandContentView5, arrayList, Boolean.TRUE);
            }
            DynamicIslandContentView current8 = getExpandedStateHandler().getCurrent();
            if (current8 != null) {
                getExpandedStateHandler().setCurrent(null);
                EventCoordinator.collapse$default(this, current8, arrayList, false, 4, null);
            }
            getStateHandler().stop();
        }
    }

    public final void handleSwipe(float f2, float f3, boolean z2, ArrayList<DynamicIslandContentView> arrayList) {
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        DynamicIslandData currentIslandData3;
        DynamicIslandData currentIslandData4;
        DynamicIslandContentView current = getBigIslandStateHandler().getCurrent();
        DynamicIslandContentView current2 = getSmallIslandStateHandler().getCurrent();
        DynamicIslandContentView current3 = getHiddenStateHandler().getCurrent();
        DynamicIslandContentView current4 = getExpandedStateHandler().getCurrent();
        Log.e(DynamicIslandConstants.TAG_DEBUG_SWIPE, "handle swipe: big:" + ((current == null || (currentIslandData4 = current.getCurrentIslandData()) == null) ? null : currentIslandData4.getKey()) + ", small:" + ((current2 == null || (currentIslandData3 = current2.getCurrentIslandData()) == null) ? null : currentIslandData3.getKey()) + ", hidden:" + ((current3 == null || (currentIslandData2 = current3.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey()) + ", expanded:" + ((current4 == null || (currentIslandData = current4.getCurrentIslandData()) == null) ? null : currentIslandData.getKey()) + " \n hiddenList:" + (arrayList != null ? u.T(arrayList, ", ", null, null, 0, null, AnonymousClass1.INSTANCE, 30, null) : null));
        if (z2 && current4 != null) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_SWIPE, "swipe on expanded state. Only expanded view handles swipe");
            getExpandedStateHandler().onSwipe(f2, f3, current, current2, current4, arrayList);
        } else {
            getBigIslandStateHandler().onSwipe(f2, f3, current, current2, current4, arrayList);
            getSmallIslandStateHandler().onSwipe(f2, f3, current, current2, current4, arrayList);
            getHiddenStateHandler().onSwipe(f2, f3, current, current2, current4, arrayList);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void resetSwipe(float r10, float r11, java.util.ArrayList<miui.systemui.dynamicisland.window.content.DynamicIslandContentView> r12) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.event.SwipeEventCoordinator.resetSwipe(float, float, java.util.ArrayList):void");
    }
}
