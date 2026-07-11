package miui.systemui.dynamicisland.event;

import android.util.Log;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class CollapseEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "CollapseEventCoordinator";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollapseEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        n.g(event, "event");
        if (event instanceof DynamicIslandEvent.Collapse) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "event: " + event);
            DynamicIslandContentView current = getExpandedStateHandler().getCurrent();
            if (current != null) {
                getExpandedStateHandler().setCurrent(null);
                EventCoordinator.collapse$default(this, current, arrayList, false, 4, null);
            }
            getStateHandler().stop();
        }
    }
}
