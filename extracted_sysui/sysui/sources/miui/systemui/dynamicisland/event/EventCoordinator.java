package miui.systemui.dynamicisland.event;

import android.util.Log;
import android.view.View;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.AppStateHandler;
import miui.systemui.dynamicisland.event.handler.StateHandler;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
public class EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "EventCoordinator";
    private StateHandler appStateHandler;
    private StateHandler bigIslandStateHandler;
    private final DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
    private StateHandler expandedStateHandler;
    private StateHandler hiddenStateHandler;
    private StateHandler miniWindowStateHandler;
    private StateHandler smallIslandStateHandler;
    private StateHandler stateHandler;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public EventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
        this.dynamicIslandEventCoordinator = dynamicIslandEventCoordinator;
        this.expandedStateHandler = dynamicIslandEventCoordinator.getExpandedStateHandler();
        this.bigIslandStateHandler = dynamicIslandEventCoordinator.getBigIslandStateHandler();
        this.smallIslandStateHandler = dynamicIslandEventCoordinator.getSmallIslandStateHandler();
        this.hiddenStateHandler = dynamicIslandEventCoordinator.getHiddenStateHandler();
        this.miniWindowStateHandler = dynamicIslandEventCoordinator.getMiniWindowStateHandler();
        AppStateHandler appStateHandler = dynamicIslandEventCoordinator.getAppStateHandler();
        this.appStateHandler = appStateHandler;
        this.stateHandler = appStateHandler;
    }

    public static /* synthetic */ void collapse$default(EventCoordinator eventCoordinator, DynamicIslandContentView dynamicIslandContentView, ArrayList arrayList, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: collapse");
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        eventCoordinator.collapse(dynamicIslandContentView, arrayList, z2);
    }

    public final boolean canExpanded(boolean z2, View view) {
        return this.dynamicIslandEventCoordinator.canExpanded(z2, view);
    }

    public final void collapse(DynamicIslandContentView view, ArrayList<DynamicIslandContentView> arrayList, boolean z2) {
        n.g(view, "view");
        IslandTemplate template = view.getTemplate();
        Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "collapse" + (template != null ? template.getBigIslandArea() : null));
        IslandTemplate template2 = view.getTemplate();
        if ((template2 != null ? template2.getBigIslandArea() : null) != null) {
            if (z2) {
                this.bigIslandStateHandler.handleReplacedStateInTinyScreen(view, arrayList, Boolean.FALSE);
                return;
            } else {
                this.bigIslandStateHandler.handleReplacedState(view, arrayList, Boolean.FALSE);
                return;
            }
        }
        DynamicIslandState.Deleted deleted = new DynamicIslandState.Deleted();
        DynamicIslandContentView current = this.expandedStateHandler.getCurrent();
        if (current != null) {
            current.setState(deleted);
        }
        StateHandler.addState$default(this.expandedStateHandler, view, null, 2, null);
    }

    public final StateHandler getAppStateHandler() {
        return this.appStateHandler;
    }

    public final StateHandler getBigIslandStateHandler() {
        return this.bigIslandStateHandler;
    }

    public final DynamicIslandEventCoordinator getDynamicIslandEventCoordinator() {
        return this.dynamicIslandEventCoordinator;
    }

    public final StateHandler getExpandedStateHandler() {
        return this.expandedStateHandler;
    }

    public final StateHandler getHiddenStateHandler() {
        return this.hiddenStateHandler;
    }

    public final StateHandler getMiniWindowStateHandler() {
        return this.miniWindowStateHandler;
    }

    public final StateHandler getSmallIslandStateHandler() {
        return this.smallIslandStateHandler;
    }

    public final StateHandler getStateHandler() {
        return this.stateHandler;
    }

    public final DynamicIslandWindowState getWindowState() {
        return this.dynamicIslandEventCoordinator.getWindowState();
    }

    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        n.g(event, "event");
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

    public final boolean isTinyScreen() {
        return this.dynamicIslandEventCoordinator.isTinyScreen();
    }

    public final void removeHiddenState(ArrayList<DynamicIslandContentView> arrayList, String str) {
        this.hiddenStateHandler.setCurrent(null);
        Iterator<DynamicIslandContentView> it = arrayList != null ? arrayList.iterator() : null;
        while (it != null && it.hasNext()) {
            DynamicIslandContentView next = it.next();
            n.f(next, "next(...)");
            DynamicIslandData currentIslandData = next.getCurrentIslandData();
            if (n.c(currentIslandData != null ? currentIslandData.getKey() : null, str)) {
                it.remove();
            }
        }
        this.hiddenStateHandler.handleFillInState(arrayList);
    }

    public final void setAppStateHandler(StateHandler stateHandler) {
        n.g(stateHandler, "<set-?>");
        this.appStateHandler = stateHandler;
    }

    public final void setBigIslandStateHandler(StateHandler stateHandler) {
        n.g(stateHandler, "<set-?>");
        this.bigIslandStateHandler = stateHandler;
    }

    public final void setExpandedStateHandler(StateHandler stateHandler) {
        n.g(stateHandler, "<set-?>");
        this.expandedStateHandler = stateHandler;
    }

    public final void setHiddenStateHandler(StateHandler stateHandler) {
        n.g(stateHandler, "<set-?>");
        this.hiddenStateHandler = stateHandler;
    }

    public final void setMiniWindowStateHandler(StateHandler stateHandler) {
        n.g(stateHandler, "<set-?>");
        this.miniWindowStateHandler = stateHandler;
    }

    public final void setSmallIslandStateHandler(StateHandler stateHandler) {
        n.g(stateHandler, "<set-?>");
        this.smallIslandStateHandler = stateHandler;
    }

    public final void setStateHandler(StateHandler stateHandler) {
        n.g(stateHandler, "<set-?>");
        this.stateHandler = stateHandler;
    }

    public final void setTempHide(boolean z2, DynamicIslandEvent event, ArrayList<DynamicIslandContentView> arrayList) {
        DynamicIslandState state;
        n.g(event, "event");
        DynamicIslandContentView current = this.bigIslandStateHandler.getCurrent();
        DynamicIslandContentView currentTempShow = this.bigIslandStateHandler.getCurrentTempShow();
        DynamicIslandContentView current2 = this.smallIslandStateHandler.getCurrent();
        DynamicIslandContentView current3 = this.hiddenStateHandler.getCurrent();
        if (!z2) {
            if (current != null) {
                StateHandler.addState$default(this.bigIslandStateHandler, current, null, 2, null);
            }
            if (current2 != null) {
                StateHandler.addState$default(this.smallIslandStateHandler, current2, null, 2, null);
            }
            if (current3 != null && !(current3.getState() instanceof DynamicIslandState.Empty)) {
                StateHandler.addState$default(this.hiddenStateHandler, current3, null, 2, null);
            }
            if (arrayList != null) {
                for (DynamicIslandContentView dynamicIslandContentView : arrayList) {
                    if (dynamicIslandContentView.getState() instanceof DynamicIslandState.Hidden) {
                        StateHandler.addState$default(this.hiddenStateHandler, dynamicIslandContentView, null, 2, null);
                    }
                }
            }
            Iterator<Map.Entry<String, ArrayList<DynamicIslandContentView>>> it = this.miniWindowStateHandler.getCurrentMap().entrySet().iterator();
            while (it.hasNext()) {
                ArrayList<DynamicIslandContentView> value = it.next().getValue();
                if (value != null) {
                    Iterator<T> it2 = value.iterator();
                    while (it2.hasNext()) {
                        StateHandler.addState$default(this.miniWindowStateHandler, (DynamicIslandContentView) it2.next(), null, 2, null);
                    }
                }
            }
            ArrayList<DynamicIslandContentView> currentList = this.appStateHandler.getCurrentList();
            if (currentList != null) {
                Iterator<T> it3 = currentList.iterator();
                while (it3.hasNext()) {
                    StateHandler.addState$default(this.appStateHandler, (DynamicIslandContentView) it3.next(), null, 2, null);
                }
                return;
            }
            return;
        }
        if (currentTempShow != null) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "big temp show handleEvent: " + currentTempShow);
            StateHandler.addState$default(this.bigIslandStateHandler, currentTempShow, null, 2, null);
        }
        if (current != null && (state = current.getState()) != null && !state.getTempShow()) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "big handleEvent: " + current);
            StateHandler.addState$default(this.bigIslandStateHandler, current, null, 2, null);
        }
        if (current2 != null) {
            StateHandler stateHandler = this.smallIslandStateHandler;
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "small handleEvent " + current2 + " " + (stateHandler != null ? stateHandler.getCurrent() : null));
            StateHandler.addState$default(this.smallIslandStateHandler, current2, null, 2, null);
        }
        if (current3 != null && !(current3.getState() instanceof DynamicIslandState.Empty)) {
            StateHandler stateHandler2 = this.hiddenStateHandler;
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "hidden handleEvent: " + current3 + " " + (stateHandler2 != null ? stateHandler2.getCurrent() : null));
            StateHandler.addState$default(this.hiddenStateHandler, current3, null, 2, null);
        }
        if (arrayList != null) {
            for (DynamicIslandContentView dynamicIslandContentView2 : arrayList) {
                if (dynamicIslandContentView2.getState() instanceof DynamicIslandState.Hidden) {
                    StateHandler.addState$default(this.hiddenStateHandler, dynamicIslandContentView2, null, 2, null);
                }
            }
        }
        Iterator<Map.Entry<String, ArrayList<DynamicIslandContentView>>> it4 = this.miniWindowStateHandler.getCurrentMap().entrySet().iterator();
        while (it4.hasNext()) {
            ArrayList<DynamicIslandContentView> value2 = it4.next().getValue();
            if (value2 != null) {
                Iterator<T> it5 = value2.iterator();
                while (it5.hasNext()) {
                    StateHandler.addState$default(this.miniWindowStateHandler, (DynamicIslandContentView) it5.next(), null, 2, null);
                }
            }
        }
        ArrayList<DynamicIslandContentView> currentList2 = this.appStateHandler.getCurrentList();
        if (currentList2 != null) {
            Iterator<T> it6 = currentList2.iterator();
            while (it6.hasNext()) {
                StateHandler.addState$default(this.appStateHandler, (DynamicIslandContentView) it6.next(), null, 2, null);
            }
        }
    }
}
