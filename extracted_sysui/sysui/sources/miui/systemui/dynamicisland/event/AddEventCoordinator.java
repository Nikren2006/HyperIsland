package miui.systemui.dynamicisland.event;

import android.util.Log;
import android.view.View;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
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
public final class AddEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "AddEventCoordinator";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        DynamicIslandData currentIslandData;
        DynamicIslandState state;
        DynamicIslandData currentIslandData2;
        n.g(event, "event");
        if (event instanceof DynamicIslandEvent.AddDynamicIsland) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "handleEvent: " + event);
            if (((DynamicIslandEvent.AddDynamicIsland) event).getTempShow()) {
                getDynamicIslandEventCoordinator().getDynamicIslandWindowState().setTempHiddenType(DynamicIslandWindowState.TempHiddenType.SHOW_ONCE_PROP_ISLAND);
                getDynamicIslandEventCoordinator().getDynamicIslandWindowState().getShowOncePropIsland().setValue(Boolean.TRUE);
            }
            View view = null;
            properties = null;
            Integer properties = null;
            view = null;
            if (isTinyScreen()) {
                if (dynamicIslandContentView != null && (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) != null) {
                    properties = currentIslandData2.getProperties();
                }
                Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "handleEvent isInTinyScreen: prop=" + properties);
                getBigIslandStateHandler().handleReplacedStateInTinyScreen(dynamicIslandContentView, arrayList, Boolean.FALSE);
                Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "hiddenList: " + arrayList);
                getStateHandler().stop();
                return;
            }
            boolean z2 = false;
            if (dynamicIslandContentView != null && (state = dynamicIslandContentView.getState()) != null && state.getExpanded()) {
                z2 = true;
            }
            if (dynamicIslandContentView != null && (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) != null) {
                view = currentIslandData.getView();
            }
            if (canExpanded(z2, view)) {
                getExpandedStateHandler().handleReplacedState(dynamicIslandContentView, arrayList, Boolean.FALSE);
            } else {
                getBigIslandStateHandler().handleReplacedState(dynamicIslandContentView, arrayList, Boolean.FALSE);
            }
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "hiddenList: " + arrayList);
            getStateHandler().stop();
        }
    }
}
