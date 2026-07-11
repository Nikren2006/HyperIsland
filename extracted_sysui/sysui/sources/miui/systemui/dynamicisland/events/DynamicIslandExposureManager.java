package miui.systemui.dynamicisland.events;

import Y0.b;
import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.display.AntiBurnInManager;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandExposureManager {
    public static final Companion Companion = new Companion(null);
    public static final boolean DEBUG = true;
    private static final String ISLAND_FORM_BIG = "big";
    private static final String ISLAND_FORM_EXPAND = "expand";
    private static final String ISLAND_FORM_SMALL = "small";
    private static final String TAG = "DynamicIslandExpoMan";
    private final AntiBurnInManager antiBurnInMan;
    private final List<Callback> callbacks;
    private final Context context;
    private final DynamicIslandEventCoordinator eventCoordinator;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public DynamicIslandExposureManager(Context context, DynamicIslandEventCoordinator eventCoordinator, AntiBurnInManager antiBurnInMan) {
        n.g(context, "context");
        n.g(eventCoordinator, "eventCoordinator");
        n.g(antiBurnInMan, "antiBurnInMan");
        this.context = context;
        this.eventCoordinator = eventCoordinator;
        this.antiBurnInMan = antiBurnInMan;
        this.callbacks = new ArrayList();
    }

    private final String getStateStr(DynamicIslandState dynamicIslandState) {
        if (dynamicIslandState == null) {
            return "null";
        }
        if (dynamicIslandState instanceof DynamicIslandState.BigIsland) {
            return ISLAND_FORM_BIG;
        }
        if (dynamicIslandState instanceof DynamicIslandState.SmallIsland) {
            return ISLAND_FORM_SMALL;
        }
        if (dynamicIslandState instanceof DynamicIslandState.Expanded) {
            return ISLAND_FORM_EXPAND;
        }
        String simpleName = dynamicIslandState.getClass().getSimpleName();
        n.f(simpleName, "getSimpleName(...)");
        return simpleName;
    }

    private final String resolveDisappearType() {
        DynamicIslandEvent handlingEvent = this.eventCoordinator.getHandlingEvent();
        return handlingEvent instanceof DynamicIslandEvent.SwipeRight ? true : handlingEvent instanceof DynamicIslandEvent.SwipeLeft ? DynamicIslandEventsConstants.Values.VALUE_DISAPPEAR_TYPE_HIDDEN : handlingEvent instanceof DynamicIslandEvent.EnterMiniWindow ? DynamicIslandEventsConstants.Values.VALUE_DISAPPEAR_TYPE_PULL_TO_EXPAND : handlingEvent instanceof DynamicIslandEvent.DeletedDynamicIsland ? DynamicIslandEventsConstants.Values.VALUE_DISAPPEAR_TYPE_DELETE : "其他";
    }

    private final String resolveExpandType() {
        return this.eventCoordinator.getUserExpanded() ? DynamicIslandEventsConstants.Values.EXPAND_TYPE_ACTIVE : DynamicIslandEventsConstants.Values.EXPAND_TYPE_PASSIVE;
    }

    private final boolean shouldHandleBurnIn(String str, Integer num) {
        return (num == null || num.intValue() != 0) && (n.c(str, ISLAND_FORM_BIG) || n.c(str, ISLAND_FORM_SMALL));
    }

    private final void trackExpand(int i2, StatusBarNotification statusBarNotification) {
        DynamicIslandEventTracker.Companion.trackExpandedExpose(this.context, statusBarNotification, i2, resolveExpandType(), EventTracker.Companion.getScreenType(this.context));
    }

    private final void trackSummary(int i2, String str, StatusBarNotification statusBarNotification) {
        DynamicIslandEventTracker.Companion.trackSummaryExpose(this.context, statusBarNotification, i2, resolveDisappearType(), str, EventTracker.Companion.getScreenType(this.context));
    }

    public final void startExpose(DynamicIslandContentViewController con) {
        n.g(con, "con");
        String islandKey = con.getIslandKey();
        if (islandKey == null) {
            return;
        }
        String stateStr = getStateStr(con.getView().getState());
        Log.d(TAG, "start expose cur:[" + stateStr + "] - " + islandKey + ", start time: " + DynamicIslandExposureManagerKt.toDateTime$default(con.getRevealTime(), null, 1, null));
        if (shouldHandleBurnIn(stateStr, con.getIslandProp())) {
            this.antiBurnInMan.scheduleEnter(islandKey, con.getView());
        }
    }

    public final void stopExpose(DynamicIslandContentViewController con) {
        String islandKey;
        n.g(con, "con");
        long actualTime = con.getActualTime();
        StatusBarNotification islandSbn = con.getIslandSbn();
        String stateStr = getStateStr(con.getView().getLastState());
        if (shouldHandleBurnIn(stateStr, con.getIslandProp()) && (islandKey = con.getIslandKey()) != null) {
            this.antiBurnInMan.pauseExpose(con.getView(), actualTime, islandKey);
        }
        int iA = b.a(actualTime / 1000.0d);
        if (999 <= actualTime) {
            String islandKey2 = con.getIslandKey();
            DynamicIslandEvent handlingEvent = this.eventCoordinator.getHandlingEvent();
            Log.d(TAG, "stop expose [" + stateStr + "] - " + islandKey2 + "\n expose time: " + actualTime + " ms, in Sec.:" + iA + " s, event: " + (handlingEvent != null ? handlingEvent.getClass().getSimpleName() : null));
            int iHashCode = stateStr.hashCode();
            if (iHashCode != -1289167206) {
                if (iHashCode != 97536) {
                    if (iHashCode == 109548807 && stateStr.equals(ISLAND_FORM_SMALL)) {
                        trackSummary(iA, DynamicIslandEventsConstants.Other.VALUE_ISLAND_FORM_SMALL_ISLAND, islandSbn);
                        return;
                    }
                } else if (stateStr.equals(ISLAND_FORM_BIG)) {
                    trackSummary(iA, DynamicIslandEventsConstants.Other.VALUE_ISLAND_FORM_BIG_ISLAND, islandSbn);
                    return;
                }
            } else if (stateStr.equals(ISLAND_FORM_EXPAND)) {
                trackExpand(iA, islandSbn);
                return;
            }
            Log.e(TAG, "failed to get exposed island " + stateStr);
        }
    }
}
