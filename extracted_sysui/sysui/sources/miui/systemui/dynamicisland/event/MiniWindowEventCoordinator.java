package miui.systemui.dynamicisland.event;

import I0.m;
import android.os.Bundle;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.MiniWindowStateHandler;
import miui.systemui.dynamicisland.event.handler.StateHandler;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class MiniWindowEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "AppEventCoordinator";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiniWindowEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
    }

    private final boolean hasMiniWindowExpanded(String str) {
        ArrayList<DynamicIslandContentView> arrayList = getMiniWindowStateHandler().getCurrentMap().get(str);
        if (arrayList == null) {
            return false;
        }
        for (DynamicIslandContentView dynamicIslandContentView : arrayList) {
            if ((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.MiniWindowExpanded) {
                return true;
            }
        }
        return false;
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        DynamicIslandData currentIslandData;
        Bundle extras;
        DynamicIslandData currentIslandData2;
        DynamicIslandData currentIslandData3;
        DynamicIslandData currentIslandData4;
        DynamicIslandData currentIslandData5;
        DynamicIslandData currentIslandData6;
        DynamicIslandData currentIslandData7;
        DynamicIslandData currentIslandData8;
        DynamicIslandData currentIslandData9;
        DynamicIslandData currentIslandData10;
        DynamicIslandContentView firstElement;
        HashMap<String, ArrayList<DynamicIslandContentView>> currentMap;
        DynamicIslandData currentIslandData11;
        DynamicIslandData currentIslandData12;
        DynamicIslandData currentIslandData13;
        Bundle extras2;
        n.g(event, "event");
        int i2 = 0;
        if (!(event instanceof DynamicIslandEvent.EnterMiniWindow)) {
            if (event instanceof DynamicIslandEvent.ExitMiniWindow) {
                String string = (dynamicIslandContentView == null || (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null || (extras = currentIslandData.getExtras()) == null) ? null : extras.getString("miui.pkg.name");
                HashMap<String, ArrayList<DynamicIslandContentView>> currentMap2 = getMiniWindowStateHandler().getCurrentMap();
                ArrayList<DynamicIslandContentView> arrayList2 = currentMap2 != null ? currentMap2.get(string) : null;
                if (arrayList2 != null) {
                    for (Object obj : arrayList2) {
                        int i3 = i2 + 1;
                        if (i2 < 0) {
                            m.n();
                        }
                        DynamicIslandContentView dynamicIslandContentView2 = (DynamicIslandContentView) obj;
                        if (dynamicIslandContentView2 != null) {
                            collapse(dynamicIslandContentView2, arrayList, isTinyScreen());
                            getStateHandler().stop();
                        }
                        i2 = i3;
                    }
                }
                D.d(getMiniWindowStateHandler().getCurrentMap()).remove(string);
                return;
            }
            return;
        }
        DynamicIslandState state = dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null;
        String string2 = (dynamicIslandContentView == null || (currentIslandData13 = dynamicIslandContentView.getCurrentIslandData()) == null || (extras2 = currentIslandData13.getExtras()) == null) ? null : extras2.getString("miui.pkg.name");
        String key = (dynamicIslandContentView == null || (currentIslandData12 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData12.getKey();
        DynamicIslandContentView current = getBigIslandStateHandler().getCurrent();
        if (n.c(key, (current == null || (currentIslandData11 = current.getCurrentIslandData()) == null) ? null : currentIslandData11.getKey())) {
            getBigIslandStateHandler().setCurrent(null);
            getBigIslandStateHandler().handleFillInState(arrayList);
        } else {
            DynamicIslandContentView current2 = getSmallIslandStateHandler().getCurrent();
            if (n.c(key, (current2 == null || (currentIslandData10 = current2.getCurrentIslandData()) == null) ? null : currentIslandData10.getKey())) {
                getSmallIslandStateHandler().setCurrent(null);
                getSmallIslandStateHandler().handleFillInState(arrayList);
                StateHandler.addState$default(getBigIslandStateHandler(), getBigIslandStateHandler().getCurrent(), null, 2, null);
            } else {
                DynamicIslandContentView current3 = getExpandedStateHandler().getCurrent();
                if (n.c(key, (current3 == null || (currentIslandData9 = current3.getCurrentIslandData()) == null) ? null : currentIslandData9.getKey())) {
                    getExpandedStateHandler().setCurrent(null);
                } else {
                    DynamicIslandContentView current4 = getHiddenStateHandler().getCurrent();
                    if (n.c(key, (current4 == null || (currentIslandData8 = current4.getCurrentIslandData()) == null) ? null : currentIslandData8.getKey())) {
                        removeHiddenState(arrayList, (dynamicIslandContentView == null || (currentIslandData7 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData7.getKey());
                    } else {
                        ArrayList<DynamicIslandContentView> currentList = getAppStateHandler().getCurrentList();
                        Iterator<DynamicIslandContentView> it = currentList != null ? currentList.iterator() : null;
                        while (it != null && it.hasNext()) {
                            DynamicIslandContentView next = it.next();
                            if (n.c((next == null || (currentIslandData6 = next.getCurrentIslandData()) == null) ? null : currentIslandData6.getKey(), (dynamicIslandContentView == null || (currentIslandData5 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData5.getKey())) {
                                it.remove();
                            }
                        }
                        Iterator<DynamicIslandContentView> it2 = arrayList != null ? arrayList.iterator() : null;
                        while (it2 != null && it2.hasNext()) {
                            DynamicIslandContentView next2 = it2.next();
                            n.f(next2, "next(...)");
                            DynamicIslandData currentIslandData14 = next2.getCurrentIslandData();
                            if (n.c(currentIslandData14 != null ? currentIslandData14.getKey() : null, (dynamicIslandContentView == null || (currentIslandData4 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData4.getKey())) {
                                it2.remove();
                            }
                        }
                        HashMap<String, ArrayList<DynamicIslandContentView>> currentMap3 = getMiniWindowStateHandler().getCurrentMap();
                        ArrayList<DynamicIslandContentView> arrayList3 = currentMap3 != null ? currentMap3.get(string2) : null;
                        if (arrayList3 != null) {
                            Iterator<DynamicIslandContentView> it3 = arrayList3.iterator();
                            while (it3.hasNext()) {
                                DynamicIslandContentView next3 = it3.next();
                                if (n.c((next3 == null || (currentIslandData3 = next3.getCurrentIslandData()) == null) ? null : currentIslandData3.getKey(), (dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey())) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        HashMap<String, ArrayList<DynamicIslandContentView>> currentMap4 = getMiniWindowStateHandler().getCurrentMap();
        ArrayList<DynamicIslandContentView> arrayList4 = currentMap4 != null ? currentMap4.get(string2) : null;
        if (arrayList4 == null) {
            arrayList4 = new ArrayList<>();
            if (string2 != null && (currentMap = getMiniWindowStateHandler().getCurrentMap()) != null) {
                currentMap.put(string2, arrayList4);
            }
        }
        if (string2 != null) {
            StateHandler miniWindowStateHandler = getMiniWindowStateHandler();
            n.e(miniWindowStateHandler, "null cannot be cast to non-null type miui.systemui.dynamicisland.event.handler.MiniWindowStateHandler");
            firstElement = ((MiniWindowStateHandler) miniWindowStateHandler).getFirstElement(string2);
        } else {
            firstElement = null;
        }
        if (firstElement == null) {
            DynamicIslandState dynamicIslandStateChangeToState = state != null ? state.changeToState(new DynamicIslandState.MiniWindowExpanded()) : null;
            arrayList4.add(0, dynamicIslandContentView);
            getMiniWindowStateHandler().addState(dynamicIslandContentView, dynamicIslandStateChangeToState);
        } else if (hasMiniWindowExpanded(string2)) {
            DynamicIslandState dynamicIslandStateChangeToState2 = state != null ? state.changeToState(new DynamicIslandState.SubMiniWindowExpanded()) : null;
            arrayList4.add(dynamicIslandContentView);
            getMiniWindowStateHandler().addState(dynamicIslandContentView, dynamicIslandStateChangeToState2);
        } else {
            DynamicIslandState dynamicIslandStateChangeToState3 = state != null ? state.changeToState(new DynamicIslandState.MiniWindowExpanded()) : null;
            arrayList4.add(0, dynamicIslandContentView);
            getMiniWindowStateHandler().addState(dynamicIslandContentView, dynamicIslandStateChangeToState3);
        }
        getStateHandler().stop();
    }
}
