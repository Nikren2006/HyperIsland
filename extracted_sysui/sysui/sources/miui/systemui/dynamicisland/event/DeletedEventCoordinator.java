package miui.systemui.dynamicisland.event;

import android.os.Bundle;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.StateHandler;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DeletedEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "DeletedEventCoordinator";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeletedEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        DynamicIslandData currentIslandData;
        DynamicIslandState state;
        DynamicIslandState state2;
        DynamicIslandData currentIslandData2;
        DynamicIslandData currentIslandData3;
        DynamicIslandData currentIslandData4;
        DynamicIslandData currentIslandData5;
        DynamicIslandData currentIslandData6;
        DynamicIslandState state3;
        DynamicIslandState state4;
        DynamicIslandData currentIslandData7;
        DynamicIslandState state5;
        DynamicIslandState state6;
        DynamicIslandState state7;
        DynamicIslandData currentIslandData8;
        DynamicIslandState state8;
        DynamicIslandState state9;
        DynamicIslandState state10;
        DynamicIslandData currentIslandData9;
        DynamicIslandState state11;
        DynamicIslandState state12;
        DynamicIslandState state13;
        DynamicIslandData currentIslandData10;
        DynamicIslandState state14;
        DynamicIslandState state15;
        DynamicIslandData currentIslandData11;
        DynamicIslandData currentIslandData12;
        DynamicIslandData currentIslandData13;
        Bundle extras;
        DynamicIslandData currentIslandData14;
        DynamicIslandData currentIslandData15;
        DynamicIslandState state16;
        DynamicIslandData currentIslandData16;
        n.g(event, "event");
        if (event instanceof DynamicIslandEvent.DeletedDynamicIsland) {
            String key = null;
            key = null;
            String key2 = (dynamicIslandContentView == null || (currentIslandData16 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData16.getKey();
            Boolean boolValueOf = (dynamicIslandContentView == null || (state16 = dynamicIslandContentView.getState()) == null) ? null : Boolean.valueOf(state16.getDeleteNoAnimation());
            DynamicIslandContentView current = getBigIslandStateHandler().getCurrent();
            String key3 = (current == null || (currentIslandData15 = current.getCurrentIslandData()) == null) ? null : currentIslandData15.getKey();
            DynamicIslandContentView currentTempShow = getBigIslandStateHandler().getCurrentTempShow();
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "DeletedDynamicIsland event: " + key2 + " deleteNoAnimation：" + boolValueOf + " currentKey:" + key3 + " currentTempShowKey:" + ((currentTempShow == null || (currentIslandData14 = currentTempShow.getCurrentIslandData()) == null) ? null : currentIslandData14.getKey()));
            String string = (dynamicIslandContentView == null || (currentIslandData13 = dynamicIslandContentView.getCurrentIslandData()) == null || (extras = currentIslandData13.getExtras()) == null) ? null : extras.getString("miui.pkg.name");
            String key4 = (dynamicIslandContentView == null || (currentIslandData12 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData12.getKey();
            DynamicIslandContentView current2 = getExpandedStateHandler().getCurrent();
            boolean z2 = false;
            if (n.c(key4, (current2 == null || (currentIslandData11 = current2.getCurrentIslandData()) == null) ? null : currentIslandData11.getKey())) {
                if (dynamicIslandContentView != null && (state15 = dynamicIslandContentView.getState()) != null && state15.getDeleteNoAnimation()) {
                    DynamicIslandContentView current3 = getExpandedStateHandler().getCurrent();
                    DynamicIslandBackgroundView backgroundView = current3 != null ? current3.getBackgroundView() : null;
                    if (backgroundView != null) {
                        backgroundView.setVisibility(4);
                    }
                    DynamicIslandContentView current4 = getExpandedStateHandler().getCurrent();
                    if (current4 != null) {
                        current4.setVisibility(4);
                    }
                }
                DynamicIslandContentView current5 = getExpandedStateHandler().getCurrent();
                DynamicIslandState state17 = current5 != null ? current5.getState() : null;
                if (state17 != null) {
                    if (dynamicIslandContentView != null && (state14 = dynamicIslandContentView.getState()) != null && state14.getDeleteNoAnimation()) {
                        z2 = true;
                    }
                    state17.setDeleteNoAnimation(z2);
                }
                getExpandedStateHandler().handleFillInState(arrayList);
            } else {
                DynamicIslandContentView current6 = getBigIslandStateHandler().getCurrent();
                if (n.c(key4, (current6 == null || (currentIslandData10 = current6.getCurrentIslandData()) == null) ? null : currentIslandData10.getKey())) {
                    if (dynamicIslandContentView != null && (state13 = dynamicIslandContentView.getState()) != null && state13.getDeleteNoAnimation()) {
                        DynamicIslandContentView current7 = getBigIslandStateHandler().getCurrent();
                        DynamicIslandBackgroundView backgroundView2 = current7 != null ? current7.getBackgroundView() : null;
                        if (backgroundView2 != null) {
                            backgroundView2.setVisibility(4);
                        }
                        DynamicIslandContentView current8 = getBigIslandStateHandler().getCurrent();
                        if (current8 != null) {
                            current8.setVisibility(4);
                        }
                    }
                    DynamicIslandState.Deleted deleted = new DynamicIslandState.Deleted();
                    DynamicIslandContentView current9 = getBigIslandStateHandler().getCurrent();
                    DynamicIslandState state18 = current9 != null ? current9.getState() : null;
                    if (state18 != null) {
                        state18.setDeleteNoAnimation((dynamicIslandContentView == null || (state12 = dynamicIslandContentView.getState()) == null || !state12.getDeleteNoAnimation()) ? false : true);
                    }
                    if (dynamicIslandContentView != null && (state11 = dynamicIslandContentView.getState()) != null && state11.getDeleteNoAnimation()) {
                        z2 = true;
                    }
                    deleted.setDeleteNoAnimation(z2);
                    getBigIslandStateHandler().addState(getBigIslandStateHandler().getCurrent(), deleted);
                    getBigIslandStateHandler().setCurrent(null);
                    getBigIslandStateHandler().handleFillInState(arrayList);
                } else {
                    DynamicIslandContentView currentTempShow2 = getBigIslandStateHandler().getCurrentTempShow();
                    if (n.c(key4, (currentTempShow2 == null || (currentIslandData9 = currentTempShow2.getCurrentIslandData()) == null) ? null : currentIslandData9.getKey())) {
                        if (dynamicIslandContentView != null && (state10 = dynamicIslandContentView.getState()) != null && state10.getDeleteNoAnimation()) {
                            DynamicIslandContentView currentTempShow3 = getBigIslandStateHandler().getCurrentTempShow();
                            DynamicIslandBackgroundView backgroundView3 = currentTempShow3 != null ? currentTempShow3.getBackgroundView() : null;
                            if (backgroundView3 != null) {
                                backgroundView3.setVisibility(4);
                            }
                            DynamicIslandContentView currentTempShow4 = getBigIslandStateHandler().getCurrentTempShow();
                            if (currentTempShow4 != null) {
                                currentTempShow4.setVisibility(4);
                            }
                        }
                        DynamicIslandState.Deleted deleted2 = new DynamicIslandState.Deleted();
                        DynamicIslandContentView currentTempShow5 = getBigIslandStateHandler().getCurrentTempShow();
                        DynamicIslandState state19 = currentTempShow5 != null ? currentTempShow5.getState() : null;
                        if (state19 != null) {
                            state19.setDeleteNoAnimation((dynamicIslandContentView == null || (state9 = dynamicIslandContentView.getState()) == null || !state9.getDeleteNoAnimation()) ? false : true);
                        }
                        if (dynamicIslandContentView != null && (state8 = dynamicIslandContentView.getState()) != null && state8.getDeleteNoAnimation()) {
                            z2 = true;
                        }
                        deleted2.setDeleteNoAnimation(z2);
                        getBigIslandStateHandler().addState(getBigIslandStateHandler().getCurrentTempShow(), deleted2);
                        getDynamicIslandEventCoordinator().getDynamicIslandWindowState().setTempHiddenType(DynamicIslandWindowState.TempHiddenType.SHOW_ONCE_PROP_ISLAND);
                        getDynamicIslandEventCoordinator().getDynamicIslandWindowState().getShowOncePropIsland().setValue(Boolean.FALSE);
                        getBigIslandStateHandler().setCurrentTempShow(null);
                        StateHandler.addState$default(getBigIslandStateHandler(), getBigIslandStateHandler().getCurrent(), null, 2, null);
                        DynamicIslandContentView current10 = getSmallIslandStateHandler().getCurrent();
                        if (current10 != null) {
                            StateHandler.addState$default(getSmallIslandStateHandler(), current10, null, 2, null);
                        }
                        if (arrayList != null) {
                            Iterator<T> it = arrayList.iterator();
                            while (it.hasNext()) {
                                StateHandler.addState$default(getHiddenStateHandler(), (DynamicIslandContentView) it.next(), null, 2, null);
                            }
                        }
                    } else {
                        DynamicIslandContentView current11 = getSmallIslandStateHandler().getCurrent();
                        if (n.c(key4, (current11 == null || (currentIslandData8 = current11.getCurrentIslandData()) == null) ? null : currentIslandData8.getKey())) {
                            if (dynamicIslandContentView != null && (state7 = dynamicIslandContentView.getState()) != null && state7.getDeleteNoAnimation()) {
                                DynamicIslandContentView current12 = getSmallIslandStateHandler().getCurrent();
                                DynamicIslandBackgroundView backgroundView4 = current12 != null ? current12.getBackgroundView() : null;
                                if (backgroundView4 != null) {
                                    backgroundView4.setVisibility(4);
                                }
                                DynamicIslandContentView current13 = getSmallIslandStateHandler().getCurrent();
                                if (current13 != null) {
                                    current13.setVisibility(4);
                                }
                            }
                            DynamicIslandState.Deleted deleted3 = new DynamicIslandState.Deleted();
                            DynamicIslandContentView current14 = getSmallIslandStateHandler().getCurrent();
                            DynamicIslandState state20 = current14 != null ? current14.getState() : null;
                            if (state20 != null) {
                                state20.setDeleteNoAnimation((dynamicIslandContentView == null || (state6 = dynamicIslandContentView.getState()) == null || !state6.getDeleteNoAnimation()) ? false : true);
                            }
                            if (dynamicIslandContentView != null && (state5 = dynamicIslandContentView.getState()) != null && state5.getDeleteNoAnimation()) {
                                z2 = true;
                            }
                            deleted3.setDeleteNoAnimation(z2);
                            getSmallIslandStateHandler().addState(getSmallIslandStateHandler().getCurrent(), deleted3);
                            getSmallIslandStateHandler().setCurrent(null);
                            getSmallIslandStateHandler().handleFillInState(arrayList);
                            StateHandler.addState$default(getBigIslandStateHandler(), getBigIslandStateHandler().getCurrent(), null, 2, null);
                        } else {
                            DynamicIslandContentView current15 = getHiddenStateHandler().getCurrent();
                            if (n.c(key4, (current15 == null || (currentIslandData7 = current15.getCurrentIslandData()) == null) ? null : currentIslandData7.getKey())) {
                                DynamicIslandState.Deleted deleted4 = new DynamicIslandState.Deleted();
                                DynamicIslandContentView current16 = getHiddenStateHandler().getCurrent();
                                DynamicIslandState state21 = current16 != null ? current16.getState() : null;
                                if (state21 != null) {
                                    state21.setDeleteNoAnimation((dynamicIslandContentView == null || (state4 = dynamicIslandContentView.getState()) == null || !state4.getDeleteNoAnimation()) ? false : true);
                                }
                                if (dynamicIslandContentView != null && (state3 = dynamicIslandContentView.getState()) != null && state3.getDeleteNoAnimation()) {
                                    z2 = true;
                                }
                                deleted4.setDeleteNoAnimation(z2);
                                getHiddenStateHandler().addState(getHiddenStateHandler().getCurrent(), deleted4);
                                if (dynamicIslandContentView != null && (currentIslandData6 = dynamicIslandContentView.getCurrentIslandData()) != null) {
                                    key = currentIslandData6.getKey();
                                }
                                removeHiddenState(arrayList, key);
                            } else {
                                if ((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.Init) {
                                    getDynamicIslandEventCoordinator().getWindowView().removeView(dynamicIslandContentView.getBackgroundView());
                                }
                                ArrayList<DynamicIslandContentView> currentList = getAppStateHandler().getCurrentList();
                                Iterator<DynamicIslandContentView> it2 = currentList != null ? currentList.iterator() : null;
                                while (it2 != null && it2.hasNext()) {
                                    DynamicIslandContentView next = it2.next();
                                    if (n.c((next == null || (currentIslandData5 = next.getCurrentIslandData()) == null) ? null : currentIslandData5.getKey(), (dynamicIslandContentView == null || (currentIslandData4 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData4.getKey())) {
                                        it2.remove();
                                        getDynamicIslandEventCoordinator().getWindowView().removeView(next != null ? next.getBackgroundView() : null);
                                    }
                                }
                                ArrayList<DynamicIslandContentView> arrayList2 = getMiniWindowStateHandler().getCurrentMap().get(string);
                                Iterator<DynamicIslandContentView> it3 = arrayList2 != null ? arrayList2.iterator() : null;
                                while (it3 != null && it3.hasNext()) {
                                    DynamicIslandContentView next2 = it3.next();
                                    if (n.c((next2 == null || (currentIslandData3 = next2.getCurrentIslandData()) == null) ? null : currentIslandData3.getKey(), (dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey())) {
                                        DynamicIslandState.Deleted deleted5 = new DynamicIslandState.Deleted();
                                        DynamicIslandState state22 = next2 != null ? next2.getState() : null;
                                        if (state22 != null) {
                                            state22.setDeleteNoAnimation((dynamicIslandContentView == null || (state2 = dynamicIslandContentView.getState()) == null || !state2.getDeleteNoAnimation()) ? false : true);
                                        }
                                        deleted5.setDeleteNoAnimation((dynamicIslandContentView == null || (state = dynamicIslandContentView.getState()) == null || !state.getDeleteNoAnimation()) ? false : true);
                                        getMiniWindowStateHandler().addState(next2, deleted5);
                                        it3.remove();
                                        getDynamicIslandEventCoordinator().getWindowView().removeView(next2 != null ? next2.getBackgroundView() : null);
                                    }
                                }
                                Iterator<DynamicIslandContentView> it4 = arrayList != null ? arrayList.iterator() : null;
                                while (it4 != null && it4.hasNext()) {
                                    DynamicIslandContentView next3 = it4.next();
                                    n.f(next3, "next(...)");
                                    DynamicIslandContentView dynamicIslandContentView2 = next3;
                                    DynamicIslandData currentIslandData17 = dynamicIslandContentView2.getCurrentIslandData();
                                    if (n.c(currentIslandData17 != null ? currentIslandData17.getKey() : null, (dynamicIslandContentView == null || (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData.getKey())) {
                                        getHiddenStateHandler().addState(dynamicIslandContentView2, new DynamicIslandState.Deleted());
                                        it4.remove();
                                        getDynamicIslandEventCoordinator().getWindowView().removeView(dynamicIslandContentView2.getBackgroundView());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            getStateHandler().stop();
        }
    }
}
