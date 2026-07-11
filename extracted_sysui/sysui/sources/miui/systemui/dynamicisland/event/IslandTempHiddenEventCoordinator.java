package miui.systemui.dynamicisland.event;

import android.util.Log;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class IslandTempHiddenEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "IslandTempHiddenEventCoordinator";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandTempHiddenEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        DynamicIslandState state;
        n.g(event, "event");
        if (event instanceof DynamicIslandEvent.IslandTempHiddenChanged) {
            DynamicIslandContentView current = getExpandedStateHandler().getCurrent();
            Boolean boolValueOf = null;
            if (current != null && !((Boolean) getWindowState().getStatusBarDisappearance().getValue()).booleanValue() && ((DynamicIslandEvent.IslandTempHiddenChanged) event).getHide() && getWindowState().getTempHiddenType() != DynamicIslandWindowState.TempHiddenType.SHOW_ONCE_PROP_ISLAND) {
                getExpandedStateHandler().setCurrent(null);
                EventCoordinator.collapse$default(this, current, arrayList, false, 4, null);
            }
            DynamicIslandContentView current2 = getBigIslandStateHandler().getCurrent();
            DynamicIslandContentView current3 = getSmallIslandStateHandler().getCurrent();
            getHiddenStateHandler().getCurrent();
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "IslandTempHiddenEventCoordinator handleEvent: expandedState=" + current + " bigState=" + current2 + " smallState=" + current3);
            if (!isTinyScreen()) {
                setTempHide(((DynamicIslandEvent.IslandTempHiddenChanged) event).getHide(), event, arrayList);
                getStateHandler().stop();
                return;
            }
            if (current2 != null && (state = current2.getState()) != null) {
                boolValueOf = Boolean.valueOf(state.getTempShow());
            }
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "isTiny handleEvent1: " + current2 + " " + boolValueOf);
            getStateHandler().stop();
        }
    }
}
