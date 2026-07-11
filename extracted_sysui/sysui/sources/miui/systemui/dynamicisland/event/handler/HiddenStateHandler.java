package miui.systemui.dynamicisland.event.handler;

import H0.i;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class HiddenStateHandler extends StateHandler {

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.handler.HiddenStateHandler$handleReplacedState$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(DynamicIslandContentView it) {
            DynamicIslandData currentIslandData;
            n.g(it, "it");
            DynamicIslandData currentIslandData2 = it.getCurrentIslandData();
            String key = null;
            String key2 = currentIslandData2 != null ? currentIslandData2.getKey() : null;
            DynamicIslandContentView current = HiddenStateHandler.this.getCurrent();
            if (current != null && (currentIslandData = current.getCurrentIslandData()) != null) {
                key = currentIslandData.getKey();
            }
            return Boolean.valueOf(n.c(key2, key));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HiddenStateHandler(DynamicIslandTouchInteractor touchInteractor) {
        super(touchInteractor);
        n.g(touchInteractor, "touchInteractor");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean handleReplacedState$lambda$0(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public DynamicIslandContentView handleFillInState(ArrayList<DynamicIslandContentView> arrayList) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "handleFillInState " + (arrayList != null ? Integer.valueOf(arrayList.size()) : null));
        if (!isTempHidden(getCurrent())) {
            Integer numValueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
            n.d(numValueOf);
            if (numValueOf.intValue() > 0) {
                setCurrent(arrayList.get(0));
            } else {
                setCurrent(null);
            }
            return getCurrent();
        }
        DynamicIslandContentView current = getCurrent();
        Integer numValueOf2 = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
        n.d(numValueOf2);
        if (numValueOf2.intValue() > 0) {
            setCurrent(arrayList.get(0));
        } else {
            setCurrent(null);
        }
        return current;
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void handleReplacedState(DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList, Boolean bool) {
        DynamicIslandState state;
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        DynamicIslandState dynamicIslandStateChangeToState = null;
        String tickerData = (dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getTickerData();
        DynamicIslandContentView current = getCurrent();
        DynamicIslandContentView current2 = getCurrent();
        String tickerData2 = (current2 == null || (currentIslandData = current2.getCurrentIslandData()) == null) ? null : currentIslandData.getTickerData();
        Integer numValueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "HiddenStateHandler handleReplacedState: originState: " + dynamicIslandContentView + "  ---- " + tickerData + ", current: " + current + "----" + tickerData2 + ", hiddenList: " + numValueOf + "}, userSwipe: " + bool + " , next: " + getNext());
        setCurrent(dynamicIslandContentView);
        if (dynamicIslandContentView != null && (state = dynamicIslandContentView.getState()) != null) {
            dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Hidden());
        }
        if (arrayList != null) {
            final AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            arrayList.removeIf(new Predicate() { // from class: miui.systemui.dynamicisland.event.handler.a
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return HiddenStateHandler.handleReplacedState$lambda$0(anonymousClass1, obj);
                }
            });
        }
        if (arrayList != null) {
            DynamicIslandContentView current3 = getCurrent();
            n.d(current3);
            arrayList.add(0, current3);
        }
        addState(getCurrent(), dynamicIslandStateChangeToState);
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void onSwipe(float f2, float f3, DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2, DynamicIslandContentView dynamicIslandContentView3, ArrayList<DynamicIslandContentView> arrayList) {
        DynamicIslandState dynamicIslandStateChangeToState;
        DynamicIslandState dynamicIslandState;
        if (arrayList == null || arrayList.size() < 1) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_SWIPE, "HiddenStateHandler handles empty list");
            return;
        }
        int iGatherStates = gatherStates(dynamicIslandContentView, dynamicIslandContentView2, dynamicIslandContentView3, arrayList);
        boolean zCheckStates = checkStates(iGatherStates);
        ((Number) ((i) getTouchInteractor().getSwipeInfo().getValue()).d()).floatValue();
        if (swipeHorizontal(arrayList.get(0)) && zCheckStates) {
            DynamicIslandContentView dynamicIslandContentView4 = !isSwipeTowardsSmallIsland(f2, arrayList.get(0).getContext()) ? arrayList.get(0) : arrayList.get(arrayList.size() - 1);
            n.d(dynamicIslandContentView4);
            DynamicIslandState state = dynamicIslandContentView4.getState();
            if (iGatherStates == 8) {
                if (state != null) {
                    dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.BigIsland());
                    dynamicIslandState = dynamicIslandStateChangeToState;
                }
                dynamicIslandState = null;
            } else if (iGatherStates != 9) {
                if (iGatherStates != 11) {
                    if (state != null) {
                        dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Empty());
                        dynamicIslandState = dynamicIslandStateChangeToState;
                    }
                    dynamicIslandState = null;
                } else if (isSwipeTowardsSmallIsland(f2, dynamicIslandContentView4.getContext())) {
                    if (state != null) {
                        dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Empty());
                        dynamicIslandState = dynamicIslandStateChangeToState;
                    }
                    dynamicIslandState = null;
                } else {
                    if (state != null) {
                        dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.SmallIsland());
                        dynamicIslandState = dynamicIslandStateChangeToState;
                    }
                    dynamicIslandState = null;
                }
            } else if (isSwipeTowardsSmallIsland(f2, dynamicIslandContentView4.getContext())) {
                if (state != null) {
                    dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Empty());
                    dynamicIslandState = dynamicIslandStateChangeToState;
                }
                dynamicIslandState = null;
            } else {
                if (state != null) {
                    dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.SmallIsland());
                    dynamicIslandState = dynamicIslandStateChangeToState;
                }
                dynamicIslandState = null;
            }
            dynamicIslandContentView4.onSwipe(f2, f3, dynamicIslandContentView4, state, dynamicIslandState);
        }
    }
}
