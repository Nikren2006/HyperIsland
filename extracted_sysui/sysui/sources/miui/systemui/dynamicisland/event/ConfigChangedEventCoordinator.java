package miui.systemui.dynamicisland.event;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.handler.StateHandler;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.util.FoldUtils;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class ConfigChangedEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "ConfigChangedEventCoordinator";
    private boolean currentFoldScreenLayoutLarge;
    private boolean isFirst;
    private boolean lastFoldScreenLayoutLarge;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfigChangedEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
        this.isFirst = true;
    }

    private final void updateHidden(DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        if (dynamicIslandContentView != null) {
            StateHandler.addState$default(getHiddenStateHandler(), dynamicIslandContentView, null, 2, null);
        }
        if (arrayList != null) {
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                StateHandler.addState$default(getHiddenStateHandler(), (DynamicIslandContentView) it.next(), null, 2, null);
            }
        }
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        n.g(event, "event");
        if (event instanceof DynamicIslandEvent.ConfigChanged) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "event: " + event);
            DynamicIslandContentView current = getExpandedStateHandler().getCurrent();
            DynamicIslandContentView current2 = getBigIslandStateHandler().getCurrent();
            DynamicIslandContentView current3 = getSmallIslandStateHandler().getCurrent();
            DynamicIslandContentView current4 = getHiddenStateHandler().getCurrent();
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "ConfigChangedEventCoordinator handleEvent: expandedState=" + current + " bigState=" + current2 + " smallState=" + current3);
            if (this.isFirst) {
                FoldUtils foldUtils = FoldUtils.INSTANCE;
                DynamicIslandContentView current5 = getStateHandler().getCurrent();
                this.lastFoldScreenLayoutLarge = foldUtils.isFoldScreenLayoutLarge(current5 != null ? current5.getContext() : null);
                this.isFirst = false;
            }
            DynamicIslandWindowState windowState = getWindowState();
            if (windowState.isTinyScreen() != windowState.getLastTinyScreenStatus()) {
                setTempHide(windowState.isTinyScreen(), event, arrayList);
                Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "handle tiny changed : isTiny= " + windowState.isTinyScreen() + " hiddenList: " + arrayList);
                getStateHandler().stop();
                return;
            }
            if (current != null) {
                getExpandedStateHandler().setCurrent(null);
                current.setAlpha(0.0f);
                EventCoordinator.collapse$default(this, current, arrayList, false, 4, null);
                updateHidden(current4, arrayList);
                if (current2 != null) {
                    current2.setAlpha(0.0f);
                }
            } else {
                if (current2 != null) {
                    current2.setAlpha(0.0f);
                    StateHandler.addState$default(getBigIslandStateHandler(), current2, null, 2, null);
                }
                if (current3 != null) {
                    current3.setAlpha(0.0f);
                    StateHandler.addState$default(getSmallIslandStateHandler(), current3, null, 2, null);
                }
                updateHidden(current4, arrayList);
            }
            getStateHandler().stop();
        }
    }
}
