package miui.systemui.dynamicisland.event.handler;

import H0.i;
import android.content.Context;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.display.AntiBurnInManager;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class SmallIslandStateHandler extends StateHandler {
    private final Context context;
    private DynamicIslandContentView current;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmallIslandStateHandler(DynamicIslandTouchInteractor touchInteractor, Context context) {
        super(touchInteractor);
        n.g(touchInteractor, "touchInteractor");
        n.g(context, "context");
        this.context = context;
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public DynamicIslandState calculateCollapse(DynamicIslandContentView dynamicIslandContentView) {
        if (!compareState(getCurrent(), dynamicIslandContentView, false) && getCurrent() != null) {
            if (dynamicIslandContentView == null || getNext() == null) {
                return null;
            }
            return new DynamicIslandState.Hidden();
        }
        return new DynamicIslandState.SmallIsland();
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public DynamicIslandContentView getCurrent() {
        return this.current;
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void handleEnterBurnIn() {
        DynamicIslandContentView current = getCurrent();
        if (current != null) {
            current.setHasEverBurnedIn(true);
        }
        DynamicIslandContentView current2 = getCurrent();
        if (current2 != null) {
            current2.setBurnInState(AntiBurnInManager.BurnInStates.BurnIn);
        }
        DynamicIslandContentView current3 = getCurrent();
        if (current3 != null) {
            current3.animSmallIslandBurnIn(getCurrent());
        }
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void handleExitBurnIn() {
        DynamicIslandContentView current = getCurrent();
        if (current != null) {
            current.animSmallIslandExitBurn();
        }
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public DynamicIslandContentView handleFillInState(ArrayList<DynamicIslandContentView> arrayList) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "SmallIslandStateHandler handleFillInState: " + getCurrent());
        DynamicIslandContentView current = getCurrent();
        if (current != null) {
            setCurrent(null);
            return current;
        }
        StateHandler next = getNext();
        DynamicIslandContentView dynamicIslandContentViewHandleFillInState = next != null ? next.handleFillInState(arrayList) : null;
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "SmallIslandStateHandler handleFillInState nextState: " + (dynamicIslandContentViewHandleFillInState != null ? dynamicIslandContentViewHandleFillInState.getState() : null));
        if (dynamicIslandContentViewHandleFillInState == null || (dynamicIslandContentViewHandleFillInState.getState() instanceof DynamicIslandState.Empty)) {
            setCurrent(null);
        } else {
            if (arrayList != null) {
                arrayList.remove(0);
            }
            StateHandler next2 = getNext();
            if (next2 != null) {
                next2.handleFillInState(arrayList);
            }
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "SmallIslandStateHandler isTempHidden: " + isTempHidden(dynamicIslandContentViewHandleFillInState));
            setCurrent(dynamicIslandContentViewHandleFillInState);
            DynamicIslandState state = dynamicIslandContentViewHandleFillInState.getState();
            addState(getCurrent(), state != null ? state.changeToState(new DynamicIslandState.SmallIsland()) : null);
        }
        return dynamicIslandContentViewHandleFillInState;
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void handleReplacedState(DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList, Boolean bool) {
        StateHandler next;
        DynamicIslandState state;
        StateHandler next2;
        DynamicIslandState state2;
        DynamicIslandState state3;
        DynamicIslandState state4;
        DynamicIslandData currentIslandData;
        DynamicIslandState state5;
        DynamicIslandData currentIslandData2;
        DynamicIslandState dynamicIslandStateChangeToState = null;
        String tickerData = (dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getTickerData();
        Boolean boolValueOf = (dynamicIslandContentView == null || (state5 = dynamicIslandContentView.getState()) == null) ? null : Boolean.valueOf(state5.getTempShow());
        DynamicIslandContentView current = getCurrent();
        DynamicIslandContentView current2 = getCurrent();
        String tickerData2 = (current2 == null || (currentIslandData = current2.getCurrentIslandData()) == null) ? null : currentIslandData.getTickerData();
        DynamicIslandContentView current3 = getCurrent();
        Boolean boolValueOf2 = (current3 == null || (state4 = current3.getState()) == null) ? null : Boolean.valueOf(state4.getTempShow());
        Integer numValueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "SmallIslandStateHandler handleReplacedState: originState: " + dynamicIslandContentView + "  ---- " + tickerData + "--- tempShow:" + boolValueOf + " current: " + current + "----" + tickerData2 + " " + boolValueOf2 + ", hiddenList: " + numValueOf + "}, userSwipe: " + bool + " , next: " + getNext());
        DynamicIslandContentView current4 = getCurrent();
        if (dynamicIslandContentView != null && (state3 = dynamicIslandContentView.getState()) != null && state3.getTempShow()) {
            StateHandler.addState$default(this, current4, null, 2, null);
            return;
        }
        if (compareState(getCurrent(), dynamicIslandContentView, false) || n.c(bool, Boolean.TRUE)) {
            setCurrent(dynamicIslandContentView);
            if (dynamicIslandContentView != null && (state = dynamicIslandContentView.getState()) != null) {
                dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.SmallIsland());
            }
            addState(getCurrent(), dynamicIslandStateChangeToState);
            if (current4 == null || getNext() == null || (next = getNext()) == null) {
                return;
            }
            next.handleReplacedState(current4, arrayList, bool);
            return;
        }
        if (getCurrent() == null) {
            setCurrent(dynamicIslandContentView);
            if (dynamicIslandContentView != null && (state2 = dynamicIslandContentView.getState()) != null) {
                dynamicIslandStateChangeToState = state2.changeToState(new DynamicIslandState.SmallIsland());
            }
            addState(getCurrent(), dynamicIslandStateChangeToState);
            return;
        }
        StateHandler.addState$default(this, current4, null, 2, null);
        if (dynamicIslandContentView == null || getNext() == null || (next2 = getNext()) == null) {
            return;
        }
        next2.handleReplacedState(dynamicIslandContentView, arrayList, bool);
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void handleReplacedStateInTinyScreen(DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList, Boolean bool) {
        String key;
        Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "SmallIslandHandler isTiny not supposed to be here");
        DynamicIslandContentView current = getCurrent();
        if (current != null) {
            DynamicIslandState.Deleted deleted = new DynamicIslandState.Deleted();
            DynamicIslandData currentIslandData = current.getCurrentIslandData();
            if (currentIslandData == null || (key = currentIslandData.getKey()) == null) {
                key = "";
            }
            deleted.setDeleteKey(key);
            addState(current, deleted);
        }
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void onSwipe(float f2, float f3, DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2, DynamicIslandContentView dynamicIslandContentView3, ArrayList<DynamicIslandContentView> arrayList) {
        checkStates(gatherStates(dynamicIslandContentView, dynamicIslandContentView2, dynamicIslandContentView3, arrayList));
        DynamicIslandState dynamicIslandStateChangeToState = null;
        DynamicIslandState state = dynamicIslandContentView2 != null ? dynamicIslandContentView2.getState() : null;
        if (dynamicIslandContentView2 == null) {
            return;
        }
        ((Number) ((i) getTouchInteractor().getSwipeInfo().getValue()).d()).floatValue();
        if (swipeHorizontal(dynamicIslandContentView2)) {
            if (isSwipeTowardsSmallIsland(f2, dynamicIslandContentView2.getContext())) {
                if (state != null) {
                    dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Hidden());
                }
            } else if (state != null) {
                dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.BigIsland());
            }
            dynamicIslandContentView2.onSwipe(f2, f3, dynamicIslandContentView2, state, dynamicIslandStateChangeToState);
        }
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void setCurrent(DynamicIslandContentView dynamicIslandContentView) {
        this.current = dynamicIslandContentView;
    }
}
