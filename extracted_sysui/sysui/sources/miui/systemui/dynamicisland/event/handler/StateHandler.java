package miui.systemui.dynamicisland.event.handler;

import H0.i;
import I0.m;
import android.content.Context;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.y;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.DynamicIslandViewModel;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes3.dex */
public class StateHandler {
    public static final int FLAG_BIG = 1;
    public static final int FLAG_BIG_EXPAND = 5;
    public static final int FLAG_BIG_HIDDEN = 9;
    public static final int FLAG_BIG_HIDDEN_EXPANDED = 13;
    public static final int FLAG_EXPANDED = 4;
    public static final int FLAG_HIDDEN = 8;
    public static final int FLAG_HIDDEN_EXPANDED = 12;
    public static final int FLAG_SMALL = 2;
    public static final int FLAG_SMALL_BIG = 3;
    public static final int FLAG_SMALL_BIG_EXPANDED = 7;
    public static final int FLAG_SMALL_BIG_HIDDEN = 11;
    public static final int NONE = 0;
    private final Context ctx;
    private DynamicIslandContentView current;
    private ArrayList<DynamicIslandContentView> currentList;
    private HashMap<String, ArrayList<DynamicIslandContentView>> currentMap;
    private DynamicIslandContentView currentTempShow;
    private long exposedTime;
    private DynamicIslandContentView lastTempShow;
    private StateHandler next;
    private final DynamicIslandTouchInteractor touchInteractor;
    public static final Companion Companion = new Companion(null);
    private static final ArrayList<DynamicIslandContentView> newList = new ArrayList<>();
    private static final List<Integer> COMBINATIONS = m.j(0, 1, 3, 4, 5, 7, 8, 9, 11, 12, 13);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<Integer> getCOMBINATIONS() {
            return StateHandler.COMBINATIONS;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.handler.StateHandler$addState$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        final /* synthetic */ y $initState;
        final /* synthetic */ DynamicIslandContentView $view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandContentView dynamicIslandContentView, y yVar) {
            super(1);
            this.$view = dynamicIslandContentView;
            this.$initState = yVar;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(DynamicIslandContentView dynamicIslandContentView) {
            DynamicIslandData currentIslandData;
            DynamicIslandData currentIslandData2;
            String key = (dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey();
            DynamicIslandContentView dynamicIslandContentView2 = this.$view;
            boolean zC = n.c(key, (dynamicIslandContentView2 == null || (currentIslandData = dynamicIslandContentView2.getCurrentIslandData()) == null) ? null : currentIslandData.getKey());
            if (zC) {
                this.$initState.f5059a = dynamicIslandContentView != null ? dynamicIslandContentView.getLastState() : null;
            }
            return Boolean.valueOf(zC);
        }
    }

    public StateHandler(DynamicIslandTouchInteractor touchInteractor) {
        n.g(touchInteractor, "touchInteractor");
        this.touchInteractor = touchInteractor;
        this.exposedTime = Long.MIN_VALUE;
        this.currentList = new ArrayList<>();
        this.currentMap = new HashMap<>();
        DynamicIslandContentView current = getCurrent();
        this.ctx = current != null ? current.getContext() : null;
    }

    public static /* synthetic */ void addState$default(StateHandler stateHandler, DynamicIslandContentView dynamicIslandContentView, DynamicIslandState dynamicIslandState, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addState");
        }
        if ((i2 & 2) != 0) {
            dynamicIslandState = null;
        }
        stateHandler.addState(dynamicIslandContentView, dynamicIslandState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addState$lambda$2(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    private final boolean checkHiddenList(ArrayList<DynamicIslandContentView> arrayList) {
        if (arrayList == null) {
            return false;
        }
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            if (((DynamicIslandContentView) it.next()).getState() instanceof DynamicIslandState.Hidden) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean compareState$default(StateHandler stateHandler, DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: compareState");
        }
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        return stateHandler.compareState(dynamicIslandContentView, dynamicIslandContentView2, z2);
    }

    public final void addState(DynamicIslandContentView dynamicIslandContentView, DynamicIslandState dynamicIslandState) {
        DynamicIslandData currentIslandData;
        String key = null;
        DynamicIslandState lastState = dynamicIslandContentView != null ? dynamicIslandContentView.getLastState() : null;
        DynamicIslandState state = dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null;
        if (dynamicIslandContentView != null && (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) != null) {
            key = currentIslandData.getKey();
        }
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "addState: lastState:" + lastState + ", State:" + state + ", key：" + key + " ");
        y yVar = new y();
        ArrayList<DynamicIslandContentView> arrayList = newList;
        final AnonymousClass1 anonymousClass1 = new AnonymousClass1(dynamicIslandContentView, yVar);
        arrayList.removeIf(new Predicate() { // from class: miui.systemui.dynamicisland.event.handler.b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return StateHandler.addState$lambda$2(anonymousClass1, obj);
            }
        });
        Object obj = yVar.f5059a;
        if (obj != null) {
            if (dynamicIslandContentView != null) {
                dynamicIslandContentView.setLastState((DynamicIslandState) obj);
            }
        } else if (dynamicIslandContentView != null) {
            dynamicIslandContentView.setLastState(dynamicIslandContentView.getState());
        }
        if (dynamicIslandContentView != null) {
            if (dynamicIslandState == null) {
                dynamicIslandState = dynamicIslandContentView.getState();
            }
            dynamicIslandContentView.setState(dynamicIslandState);
        }
        arrayList.add(dynamicIslandContentView);
    }

    public DynamicIslandState calculateCollapse(DynamicIslandContentView dynamicIslandContentView) {
        return null;
    }

    public final boolean checkStates(int i2) {
        return COMBINATIONS.contains(Integer.valueOf(i2));
    }

    public final boolean compareState(DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2, boolean z2) {
        Integer properties;
        DynamicIslandData currentIslandData;
        Integer properties2;
        Integer properties3;
        DynamicIslandData currentIslandData2;
        Integer properties4;
        Long time;
        Long time2;
        Integer priority;
        Integer priority2;
        if (dynamicIslandContentView == null || dynamicIslandContentView2 == null) {
            return false;
        }
        DynamicIslandData currentIslandData3 = dynamicIslandContentView.getCurrentIslandData();
        Integer properties5 = currentIslandData3 != null ? currentIslandData3.getProperties() : null;
        DynamicIslandData currentIslandData4 = dynamicIslandContentView2.getCurrentIslandData();
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "BigIslandHandler compareState " + properties5 + " " + (currentIslandData4 != null ? currentIslandData4.getProperties() : null));
        DynamicIslandData currentIslandData5 = dynamicIslandContentView.getCurrentIslandData();
        Integer properties6 = currentIslandData5 != null ? currentIslandData5.getProperties() : null;
        DynamicIslandData currentIslandData6 = dynamicIslandContentView2.getCurrentIslandData();
        if (!n.c(properties6, currentIslandData6 != null ? currentIslandData6.getProperties() : null)) {
            if (z2) {
                DynamicIslandData currentIslandData7 = dynamicIslandContentView.getCurrentIslandData();
                return (currentIslandData7 == null || (properties3 = currentIslandData7.getProperties()) == null || properties3.intValue() != 2 || (currentIslandData2 = dynamicIslandContentView2.getCurrentIslandData()) == null || (properties4 = currentIslandData2.getProperties()) == null || properties4.intValue() != 1) ? false : true;
            }
            DynamicIslandData currentIslandData8 = dynamicIslandContentView.getCurrentIslandData();
            return (currentIslandData8 == null || (properties = currentIslandData8.getProperties()) == null || properties.intValue() != 1 || (currentIslandData = dynamicIslandContentView2.getCurrentIslandData()) == null || (properties2 = currentIslandData.getProperties()) == null || properties2.intValue() != 2) ? false : true;
        }
        DynamicIslandData currentIslandData9 = dynamicIslandContentView2.getCurrentIslandData();
        int iIntValue = (currentIslandData9 == null || (priority2 = currentIslandData9.getPriority()) == null) ? 1 : priority2.intValue();
        DynamicIslandData currentIslandData10 = dynamicIslandContentView.getCurrentIslandData();
        int iIntValue2 = (currentIslandData10 == null || (priority = currentIslandData10.getPriority()) == null) ? 1 : priority.intValue();
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "compareState currentPriority " + iIntValue2 + "  originPriority " + iIntValue);
        if (iIntValue2 != iIntValue) {
            return iIntValue2 > iIntValue;
        }
        DynamicIslandState state = dynamicIslandContentView.getState();
        if (state == null || (time = state.getTime()) == null) {
            return false;
        }
        long jLongValue = time.longValue();
        DynamicIslandState state2 = dynamicIslandContentView2.getState();
        return (state2 == null || (time2 = state2.getTime()) == null || jLongValue > time2.longValue()) ? false : true;
    }

    public final int gatherStates(DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2, DynamicIslandContentView dynamicIslandContentView3, ArrayList<DynamicIslandContentView> arrayList) {
        int i2 = dynamicIslandContentView != null ? 1 : 0;
        if (dynamicIslandContentView2 != null) {
            i2 += 2;
        }
        if (dynamicIslandContentView3 != null) {
            i2 += 4;
        }
        return ((arrayList == null || arrayList.size() != 0) && checkHiddenList(arrayList)) ? i2 + 8 : i2;
    }

    public final Context getCtx() {
        return this.ctx;
    }

    public DynamicIslandContentView getCurrent() {
        return this.current;
    }

    public final String getCurrentKey() {
        String islandKey;
        DynamicIslandContentView current = getCurrent();
        return (current == null || (islandKey = current.getIslandKey()) == null) ? "null" : islandKey;
    }

    public final ArrayList<DynamicIslandContentView> getCurrentList() {
        return this.currentList;
    }

    public final HashMap<String, ArrayList<DynamicIslandContentView>> getCurrentMap() {
        return this.currentMap;
    }

    public final DynamicIslandContentView getCurrentTempShow() {
        return this.currentTempShow;
    }

    public final long getExposedTime() {
        return this.exposedTime;
    }

    public final DynamicIslandContentView getLastTempShow() {
        return this.lastTempShow;
    }

    public final StateHandler getNext() {
        return this.next;
    }

    public final DynamicIslandTouchInteractor getTouchInteractor() {
        return this.touchInteractor;
    }

    public void handleEnterBurnIn() {
    }

    public void handleExitBurnIn() {
    }

    public DynamicIslandContentView handleFillInState(ArrayList<DynamicIslandContentView> arrayList) {
        return null;
    }

    public DynamicIslandState handleFillInStateInTinyScreen(ArrayList<DynamicIslandState> arrayList) {
        return null;
    }

    public void handlePauseExpose() {
    }

    public void handleReplacedState(DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList, Boolean bool) {
    }

    public void handleReplacedStateInTinyScreen(DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList, Boolean bool) {
    }

    public final boolean hasEmpty(ArrayList<DynamicIslandContentView> arrayList) {
        if (arrayList == null) {
            return false;
        }
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            if (((DynamicIslandContentView) it.next()).getState() instanceof DynamicIslandState.Empty) {
                return true;
            }
        }
        return false;
    }

    public final boolean isSwipeTowardsSmallIsland(float f2, Context context) {
        if (context == null || !CommonUtils.isLayoutRtl(context)) {
            if (f2 < 0.0f) {
                return false;
            }
        } else if (f2 > 0.0f) {
            return false;
        }
        return true;
    }

    public final boolean isSwipeUp(DynamicIslandContentView view) {
        n.g(view, "view");
        float fFloatValue = ((Number) ((i) this.touchInteractor.getSwipeInfo().getValue()).e()).floatValue();
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator();
        return dynamicIslandEventCoordinator != null && dynamicIslandEventCoordinator.getVerticalSwipeDispatched() && fFloatValue < 0.0f;
    }

    public final boolean isTempHidden(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        return (dynamicIslandContentView == null || (dynamicIslandEventCoordinator = dynamicIslandContentView.getDynamicIslandEventCoordinator()) == null || !dynamicIslandEventCoordinator.isTempHidden(dynamicIslandContentView)) ? false : true;
    }

    public void onSwipe(float f2, float f3, DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2, DynamicIslandContentView dynamicIslandContentView3, ArrayList<DynamicIslandContentView> arrayList) {
    }

    public void setCurrent(DynamicIslandContentView dynamicIslandContentView) {
        this.current = dynamicIslandContentView;
    }

    public final void setCurrentList(ArrayList<DynamicIslandContentView> arrayList) {
        this.currentList = arrayList;
    }

    public final void setCurrentMap(HashMap<String, ArrayList<DynamicIslandContentView>> map) {
        n.g(map, "<set-?>");
        this.currentMap = map;
    }

    public final void setCurrentTempShow(DynamicIslandContentView dynamicIslandContentView) {
        this.lastTempShow = this.currentTempShow;
        this.currentTempShow = dynamicIslandContentView;
    }

    public final void setExposedTime(long j2) {
        this.exposedTime = j2;
    }

    public final void setLastTempShow(DynamicIslandContentView dynamicIslandContentView) {
        this.lastTempShow = dynamicIslandContentView;
    }

    public final void setNext(StateHandler stateHandler) {
        this.next = stateHandler;
    }

    public final void stop() {
        DynamicIslandViewModel viewModel;
        ArrayList<DynamicIslandContentView> arrayList = newList;
        Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "StateHandler stop->updateState, listSize=" + arrayList.size() + ", new:" + arrayList);
        if (arrayList.size() > 0) {
            boolean z2 = false;
            DynamicIslandContentView dynamicIslandContentView = arrayList.get(0);
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator = dynamicIslandContentView != null ? dynamicIslandContentView.getDynamicIslandEventCoordinator() : null;
            if (dynamicIslandEventCoordinator != null) {
                if (arrayList == null || !arrayList.isEmpty()) {
                    for (DynamicIslandContentView dynamicIslandContentView2 : arrayList) {
                        if (!((dynamicIslandContentView2 != null ? dynamicIslandContentView2.getState() : null) instanceof DynamicIslandState.AppExpanded)) {
                            if ((dynamicIslandContentView2 != null ? dynamicIslandContentView2.getState() : null) instanceof DynamicIslandState.SubAppExpanded) {
                            }
                        }
                        z2 = true;
                    }
                }
                dynamicIslandEventCoordinator.setHasAppExpandedState(z2);
            }
        }
        Iterator it = new ArrayList(newList).iterator();
        n.f(it, "iterator(...)");
        while (it.hasNext()) {
            DynamicIslandContentView dynamicIslandContentView3 = (DynamicIslandContentView) it.next();
            if (dynamicIslandContentView3 != null && (viewModel = dynamicIslandContentView3.getViewModel()) != null) {
                viewModel.updateState(dynamicIslandContentView3);
            }
        }
        newList.clear();
    }

    public final boolean swipeHorizontal(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        return (dynamicIslandContentView == null || (dynamicIslandEventCoordinator = dynamicIslandContentView.getDynamicIslandEventCoordinator()) == null || !dynamicIslandEventCoordinator.getHorizontalSwipeDispatched()) ? false : true;
    }
}
