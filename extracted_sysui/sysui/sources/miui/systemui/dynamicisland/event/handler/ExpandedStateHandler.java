package miui.systemui.dynamicisland.event.handler;

import H0.i;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class ExpandedStateHandler extends StateHandler {
    private DynamicIslandContentView current;
    private DynamicIslandContentView lastExpandedView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpandedStateHandler(DynamicIslandTouchInteractor touchInteractor) {
        super(touchInteractor);
        n.g(touchInteractor, "touchInteractor");
    }

    private final void handleHorizontalSwipe(float f2, float f3, DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandState state = dynamicIslandContentView.getState();
        dynamicIslandContentView.onSwipe(f2, f3, dynamicIslandContentView, dynamicIslandContentView.getState(), state != null ? state.changeToState(new DynamicIslandState.BigIsland()) : null);
    }

    private final void handleSwipeUp(float f2, float f3, DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandState state;
        DynamicIslandState dynamicIslandStateChangeToState = (dynamicIslandContentView == null || (state = dynamicIslandContentView.getState()) == null) ? null : state.changeToState(new DynamicIslandState.BigIsland());
        if (dynamicIslandContentView != null) {
            dynamicIslandContentView.onSwipe(f2, f3, dynamicIslandContentView, dynamicIslandContentView.getState(), dynamicIslandStateChangeToState);
        }
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public DynamicIslandContentView getCurrent() {
        return this.current;
    }

    public final DynamicIslandContentView getLastExpandedView() {
        return this.lastExpandedView;
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public DynamicIslandContentView handleFillInState(ArrayList<DynamicIslandContentView> arrayList) {
        DynamicIslandContentView current = getCurrent();
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "ExpandedStateHandler handleFillInState " + current);
        if (current != null) {
            addState(current, new DynamicIslandState.Deleted());
            setCurrent(null);
        }
        return current;
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void handleReplacedState(DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList, Boolean bool) {
        StateHandler next;
        DynamicIslandState state;
        DynamicIslandState state2;
        DynamicIslandState dynamicIslandStateChangeToState;
        DynamicIslandData currentIslandData;
        DynamicIslandState state3;
        DynamicIslandData currentIslandData2;
        DynamicIslandState dynamicIslandStateChangeToState2 = null;
        String tickerData = (dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getTickerData();
        Boolean boolValueOf = (dynamicIslandContentView == null || (state3 = dynamicIslandContentView.getState()) == null) ? null : Boolean.valueOf(state3.getTempShow());
        DynamicIslandContentView current = getCurrent();
        DynamicIslandContentView current2 = getCurrent();
        String tickerData2 = (current2 == null || (currentIslandData = current2.getCurrentIslandData()) == null) ? null : currentIslandData.getTickerData();
        Integer numValueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "ExpandedStateHandler handleReplacedState: originState: " + dynamicIslandContentView + "  ---- " + tickerData + "--- tempShow:" + boolValueOf + ", current: " + current + "----" + tickerData2 + ", hiddenList: " + numValueOf + "}, userSwipe: " + bool + " , next: " + getNext());
        DynamicIslandContentView current3 = getCurrent();
        if (dynamicIslandContentView != null && (state2 = dynamicIslandContentView.getState()) != null && state2.getTempShow()) {
            setCurrent(dynamicIslandContentView);
            DynamicIslandState state4 = dynamicIslandContentView.getState();
            if (state4 != null && (dynamicIslandStateChangeToState = state4.changeToState(new DynamicIslandState.Expanded())) != null) {
                dynamicIslandStateChangeToState.setTempShow(true);
                dynamicIslandStateChangeToState2 = dynamicIslandStateChangeToState;
            }
            addState(getCurrent(), dynamicIslandStateChangeToState2);
            return;
        }
        setCurrent(dynamicIslandContentView);
        if (dynamicIslandContentView != null && (state = dynamicIslandContentView.getState()) != null) {
            dynamicIslandStateChangeToState2 = state.changeToState(new DynamicIslandState.Expanded());
        }
        addState(getCurrent(), dynamicIslandStateChangeToState2);
        if (current3 == null || getNext() == null || (next = getNext()) == null) {
            return;
        }
        next.handleReplacedState(current3, arrayList, bool);
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void onSwipe(float f2, float f3, DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2, DynamicIslandContentView dynamicIslandContentView3, ArrayList<DynamicIslandContentView> arrayList) {
        checkStates(gatherStates(dynamicIslandContentView, dynamicIslandContentView2, dynamicIslandContentView3, arrayList));
        if (dynamicIslandContentView3 == null) {
            return;
        }
        i iVar = (i) getTouchInteractor().getSwipeInfo().getValue();
        ((Number) iVar.d()).floatValue();
        ((Number) iVar.e()).floatValue();
        if (swipeHorizontal(dynamicIslandContentView3) || isSwipeUp(dynamicIslandContentView3)) {
            if (swipeHorizontal(dynamicIslandContentView3)) {
                handleHorizontalSwipe(f2, f3, dynamicIslandContentView3);
            } else {
                handleSwipeUp(f2, f3, dynamicIslandContentView3);
            }
        }
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void setCurrent(DynamicIslandContentView dynamicIslandContentView) {
        setLastView(getCurrent());
        this.current = dynamicIslandContentView;
    }

    public final void setLastView(DynamicIslandContentView dynamicIslandContentView) {
        this.lastExpandedView = dynamicIslandContentView;
    }
}
