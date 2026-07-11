package miui.systemui.dynamicisland.event;

import android.util.Log;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.handler.StateHandler;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class AvoidScreenBurnInEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "AvoidScreenBurnInEventCoordinator";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AvoidScreenBurnInEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        n.g(event, "event");
        if (event instanceof DynamicIslandEvent.AvoidScreenReset) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "event: " + event);
            DynamicIslandContentView current = getBigIslandStateHandler().getCurrent();
            DynamicIslandContentView current2 = getSmallIslandStateHandler().getCurrent();
            if (current != null) {
                StateHandler.addState$default(getBigIslandStateHandler(), current, null, 2, null);
            }
            if (current2 != null) {
                StateHandler.addState$default(getSmallIslandStateHandler(), current2, null, 2, null);
            }
            getStateHandler().stop();
        }
    }
}
