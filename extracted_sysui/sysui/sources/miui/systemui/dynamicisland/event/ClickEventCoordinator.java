package miui.systemui.dynamicisland.event;

import android.util.Log;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.StateHandler;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class ClickEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "ClickEventCoordinator";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClickEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        n.g(event, "event");
        if (event instanceof DynamicIslandEvent.ClickDynamicIsland) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "event: " + event);
            if (isTinyScreen()) {
                Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "isTiny hiddenList: " + arrayList);
                getStateHandler().stop();
                return;
            }
            if ((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.BigIsland) {
                DynamicIslandContentView current = getBigIslandStateHandler().getCurrent();
                getBigIslandStateHandler().setCurrent(null);
                getBigIslandStateHandler().handleFillInState(arrayList);
                getExpandedStateHandler().handleReplacedState(current, arrayList, Boolean.FALSE);
            } else {
                if ((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.SmallIsland) {
                    StateHandler.addState$default(getBigIslandStateHandler(), getBigIslandStateHandler().getCurrent(), null, 2, null);
                    DynamicIslandContentView current2 = getSmallIslandStateHandler().getCurrent();
                    getSmallIslandStateHandler().setCurrent(null);
                    getSmallIslandStateHandler().handleFillInState(arrayList);
                    getExpandedStateHandler().handleReplacedState(current2, arrayList, Boolean.FALSE);
                }
            }
            getStateHandler().stop();
        }
    }
}
