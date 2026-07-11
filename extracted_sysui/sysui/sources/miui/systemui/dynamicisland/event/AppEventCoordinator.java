package miui.systemui.dynamicisland.event;

import I0.m;
import android.os.Bundle;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.D;
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
public final class AppEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "AppEventCoordinator";
    private List<DynamicIslandContentView> exitAppStateList;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
        this.exitAppStateList = new ArrayList();
    }

    private final boolean hasAppExpanded() {
        ArrayList<DynamicIslandContentView> currentList = getAppStateHandler().getCurrentList();
        if (currentList == null) {
            return false;
        }
        for (DynamicIslandContentView dynamicIslandContentView : currentList) {
            if ((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.AppExpanded) {
                return true;
            }
        }
        return false;
    }

    public final List<DynamicIslandContentView> getExitAppStateList() {
        return this.exitAppStateList;
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        DynamicIslandData currentIslandData;
        Bundle extras;
        DynamicIslandData currentIslandData2;
        Bundle extras2;
        DynamicIslandData currentIslandData3;
        DynamicIslandData currentIslandData4;
        DynamicIslandData currentIslandData5;
        DynamicIslandData currentIslandData6;
        boolean z2;
        DynamicIslandState dynamicIslandStateChangeToState;
        DynamicIslandData currentIslandData7;
        DynamicIslandData currentIslandData8;
        ArrayList<DynamicIslandContentView> currentList;
        DynamicIslandData currentIslandData9;
        Bundle extras3;
        DynamicIslandData currentIslandData10;
        DynamicIslandData currentIslandData11;
        DynamicIslandData currentIslandData12;
        DynamicIslandData currentIslandData13;
        DynamicIslandData currentIslandData14;
        Bundle extras4;
        n.g(event, "event");
        int i2 = 0;
        if (!(event instanceof DynamicIslandEvent.EnterApp)) {
            if (event instanceof DynamicIslandEvent.ExitApp) {
                Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, " handleEvent: " + event);
                String string = (dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null || (extras2 = currentIslandData2.getExtras()) == null) ? null : extras2.getString("miui.pkg.name");
                ArrayList<DynamicIslandContentView> currentList2 = getAppStateHandler().getCurrentList();
                if (currentList2 != null) {
                    for (Object obj : currentList2) {
                        int i3 = i2 + 1;
                        if (i2 < 0) {
                            m.n();
                        }
                        DynamicIslandContentView dynamicIslandContentView2 = (DynamicIslandContentView) obj;
                        String string2 = (dynamicIslandContentView2 == null || (currentIslandData = dynamicIslandContentView2.getCurrentIslandData()) == null || (extras = currentIslandData.getExtras()) == null) ? null : extras.getString("miui.pkg.name");
                        Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "ExitApp: " + string + " " + string2);
                        if (!n.c(string2, string)) {
                            return;
                        }
                        if (dynamicIslandContentView2 != null) {
                            collapse(dynamicIslandContentView2, arrayList, isTinyScreen());
                            getStateHandler().stop();
                        }
                        i2 = i3;
                    }
                }
                ArrayList<DynamicIslandContentView> currentList3 = getAppStateHandler().getCurrentList();
                if (currentList3 != null) {
                    currentList3.clear();
                    return;
                }
                return;
            }
            return;
        }
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "handleEvent: " + event);
        DynamicIslandState state = dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null;
        String string3 = (dynamicIslandContentView == null || (currentIslandData14 = dynamicIslandContentView.getCurrentIslandData()) == null || (extras4 = currentIslandData14.getExtras()) == null) ? null : extras4.getString("miui.pkg.name");
        String key = (dynamicIslandContentView == null || (currentIslandData13 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData13.getKey();
        DynamicIslandContentView current = getBigIslandStateHandler().getCurrent();
        if (n.c(key, (current == null || (currentIslandData12 = current.getCurrentIslandData()) == null) ? null : currentIslandData12.getKey())) {
            getBigIslandStateHandler().setCurrent(null);
            getBigIslandStateHandler().handleFillInState(arrayList);
        } else {
            DynamicIslandContentView current2 = getSmallIslandStateHandler().getCurrent();
            if (n.c(key, (current2 == null || (currentIslandData6 = current2.getCurrentIslandData()) == null) ? null : currentIslandData6.getKey())) {
                getSmallIslandStateHandler().setCurrent(null);
                getSmallIslandStateHandler().handleFillInState(arrayList);
                StateHandler.addState$default(getBigIslandStateHandler(), getBigIslandStateHandler().getCurrent(), null, 2, null);
            } else {
                DynamicIslandContentView current3 = getExpandedStateHandler().getCurrent();
                if (n.c(key, (current3 == null || (currentIslandData5 = current3.getCurrentIslandData()) == null) ? null : currentIslandData5.getKey())) {
                    getExpandedStateHandler().setCurrent(null);
                } else {
                    DynamicIslandContentView current4 = getHiddenStateHandler().getCurrent();
                    if (n.c(key, (current4 == null || (currentIslandData4 = current4.getCurrentIslandData()) == null) ? null : currentIslandData4.getKey())) {
                        removeHiddenState(arrayList, (dynamicIslandContentView == null || (currentIslandData3 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData3.getKey());
                    }
                }
            }
        }
        Iterator<DynamicIslandContentView> it = arrayList != null ? arrayList.iterator() : null;
        while (true) {
            if (it == null || !it.hasNext()) {
                break;
            }
            DynamicIslandContentView next = it.next();
            n.f(next, "next(...)");
            DynamicIslandData currentIslandData15 = next.getCurrentIslandData();
            if (n.c(currentIslandData15 != null ? currentIslandData15.getKey() : null, (dynamicIslandContentView == null || (currentIslandData11 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData11.getKey())) {
                it.remove();
            }
        }
        ArrayList arrayList2 = (ArrayList) D.d(getMiniWindowStateHandler().getCurrentMap()).remove(string3);
        Iterator it2 = arrayList2 != null ? arrayList2.iterator() : null;
        while (it2 != null && it2.hasNext()) {
            DynamicIslandContentView dynamicIslandContentView3 = (DynamicIslandContentView) it2.next();
            getDynamicIslandEventCoordinator().onWindowAnimExtendLifetimeEnd((dynamicIslandContentView3 == null || (currentIslandData10 = dynamicIslandContentView3.getCurrentIslandData()) == null) ? null : currentIslandData10.getExtras());
        }
        ArrayList<DynamicIslandContentView> currentList4 = getAppStateHandler().getCurrentList();
        if (currentList4 != null) {
            z2 = false;
            int i4 = 0;
            for (Object obj2 : currentList4) {
                int i5 = i4 + 1;
                if (i4 < 0) {
                    m.n();
                }
                DynamicIslandContentView dynamicIslandContentView4 = (DynamicIslandContentView) obj2;
                if (!n.c((dynamicIslandContentView4 == null || (currentIslandData9 = dynamicIslandContentView4.getCurrentIslandData()) == null || (extras3 = currentIslandData9.getExtras()) == null) ? null : extras3.getString("miui.pkg.name"), string3)) {
                    if (dynamicIslandContentView4 != null) {
                        collapse(dynamicIslandContentView4, arrayList, isTinyScreen());
                        this.exitAppStateList.add(dynamicIslandContentView4);
                    }
                    z2 = true;
                }
                i4 = i5;
            }
        } else {
            z2 = false;
        }
        if (z2 && (currentList = getAppStateHandler().getCurrentList()) != null) {
            currentList.clear();
        }
        ArrayList<DynamicIslandContentView> currentList5 = getAppStateHandler().getCurrentList();
        if (currentList5 != null) {
            for (DynamicIslandContentView dynamicIslandContentView5 : currentList5) {
                if (n.c((dynamicIslandContentView5 == null || (currentIslandData8 = dynamicIslandContentView5.getCurrentIslandData()) == null) ? null : currentIslandData8.getKey(), (dynamicIslandContentView == null || (currentIslandData7 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData7.getKey())) {
                    return;
                }
            }
        }
        ArrayList<DynamicIslandContentView> currentList6 = getAppStateHandler().getCurrentList();
        if (!(currentList6 != null && (currentList6.isEmpty() ^ true))) {
            getStateHandler();
            dynamicIslandStateChangeToState = state != null ? state.changeToState(new DynamicIslandState.AppExpanded()) : null;
            ArrayList<DynamicIslandContentView> currentList7 = getAppStateHandler().getCurrentList();
            if (currentList7 != null) {
                currentList7.add(dynamicIslandContentView);
            }
            getAppStateHandler().addState(dynamicIslandContentView, dynamicIslandStateChangeToState);
        } else if (hasAppExpanded()) {
            getStateHandler();
            dynamicIslandStateChangeToState = state != null ? state.changeToState(new DynamicIslandState.SubAppExpanded()) : null;
            ArrayList<DynamicIslandContentView> currentList8 = getAppStateHandler().getCurrentList();
            if (currentList8 != null) {
                currentList8.add(dynamicIslandContentView);
            }
            getAppStateHandler().addState(dynamicIslandContentView, dynamicIslandStateChangeToState);
        } else {
            getStateHandler();
            dynamicIslandStateChangeToState = state != null ? state.changeToState(new DynamicIslandState.AppExpanded()) : null;
            ArrayList<DynamicIslandContentView> currentList9 = getAppStateHandler().getCurrentList();
            if (currentList9 != null) {
                currentList9.add(0, dynamicIslandContentView);
            }
            getAppStateHandler().addState(dynamicIslandContentView, dynamicIslandStateChangeToState);
        }
        getStateHandler().stop();
    }

    public final void setExitAppStateList(List<DynamicIslandContentView> list) {
        n.g(list, "<set-?>");
        this.exitAppStateList = list;
    }
}
