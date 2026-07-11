package miui.systemui.dynamicisland.event;

import android.os.Bundle;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.handler.StateHandler;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class UpdateEventCoordinator extends EventCoordinator {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "UpdateEventCoordinator";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.UpdateEventCoordinator$handleAppEvent$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        final /* synthetic */ DynamicIslandContentView $view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandContentView dynamicIslandContentView) {
            super(1);
            this.$view = dynamicIslandContentView;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(DynamicIslandContentView it) {
            n.g(it, "it");
            DynamicIslandData currentIslandData = it.getCurrentIslandData();
            String key = currentIslandData != null ? currentIslandData.getKey() : null;
            DynamicIslandData currentIslandData2 = this.$view.getCurrentIslandData();
            return Boolean.valueOf(n.c(key, currentIslandData2 != null ? currentIslandData2.getKey() : null));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(dynamicIslandEventCoordinator);
        n.g(dynamicIslandEventCoordinator, "dynamicIslandEventCoordinator");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean handleAppEvent$lambda$1(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    @Override // miui.systemui.dynamicisland.event.EventCoordinator
    public void handleAppEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList) {
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        DynamicIslandData currentIslandData3;
        DynamicIslandData currentIslandData4;
        DynamicIslandContentView current;
        DynamicIslandData currentIslandData5;
        DynamicIslandData currentIslandData6;
        DynamicIslandState state;
        DynamicIslandData currentIslandData7;
        DynamicIslandData currentIslandData8;
        DynamicIslandData currentIslandData9;
        DynamicIslandData currentIslandData10;
        DynamicIslandState state2;
        DynamicIslandData currentIslandData11;
        DynamicIslandData currentIslandData12;
        DynamicIslandData currentIslandData13;
        DynamicIslandData currentIslandData14;
        DynamicIslandData currentIslandData15;
        Bundle extras;
        n.g(event, "event");
        if (event instanceof DynamicIslandEvent.UpdateDynamicIsland) {
            if (dynamicIslandContentView != null && (currentIslandData15 = dynamicIslandContentView.getCurrentIslandData()) != null && (extras = currentIslandData15.getExtras()) != null) {
                extras.getString("miui.pkg.name");
            }
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "UpdateEventCoordinator handleEvent: " + event);
            DynamicIslandContentView dynamicIslandContentView2 = null;
            Object obj = null;
            if (isTinyScreen()) {
                String key = (dynamicIslandContentView == null || (currentIslandData14 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData14.getKey();
                DynamicIslandContentView current2 = getBigIslandStateHandler().getCurrent();
                if (n.c(key, (current2 == null || (currentIslandData13 = current2.getCurrentIslandData()) == null) ? null : currentIslandData13.getKey())) {
                    StateHandler.addState$default(getBigIslandStateHandler(), getBigIslandStateHandler().getCurrent(), null, 2, null);
                } else {
                    String key2 = (dynamicIslandContentView == null || (currentIslandData12 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData12.getKey();
                    DynamicIslandContentView current3 = getSmallIslandStateHandler().getCurrent();
                    if (n.c(key2, (current3 == null || (currentIslandData11 = current3.getCurrentIslandData()) == null) ? null : currentIslandData11.getKey())) {
                        StateHandler.addState$default(getSmallIslandStateHandler(), getSmallIslandStateHandler().getCurrent(), null, 2, null);
                    }
                }
                getStateHandler().stop();
                return;
            }
            boolean z2 = false;
            if (dynamicIslandContentView == null || (state2 = dynamicIslandContentView.getState()) == null || !state2.getExpanded()) {
                if (!((dynamicIslandContentView == null || (state = dynamicIslandContentView.getState()) == null) ? false : n.c(state.getUpdateOrder(), Boolean.TRUE))) {
                    Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "UpdateEventCoordinator event1: " + (dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null));
                    String key3 = (dynamicIslandContentView == null || (currentIslandData6 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData6.getKey();
                    DynamicIslandContentView current4 = getBigIslandStateHandler().getCurrent();
                    if (n.c(key3, (current4 == null || (currentIslandData5 = current4.getCurrentIslandData()) == null) ? null : currentIslandData5.getKey())) {
                        StateHandler.addState$default(getBigIslandStateHandler(), getBigIslandStateHandler().getCurrent(), null, 2, null);
                        if (getBigIslandStateHandler().getCurrentTempShow() == null && (current = getSmallIslandStateHandler().getCurrent()) != null) {
                            StateHandler.addState$default(getSmallIslandStateHandler(), current, null, 2, null);
                        }
                    } else {
                        DynamicIslandContentView currentTempShow = getBigIslandStateHandler().getCurrentTempShow();
                        if (n.c(key3, (currentTempShow == null || (currentIslandData4 = currentTempShow.getCurrentIslandData()) == null) ? null : currentIslandData4.getKey())) {
                            DynamicIslandContentView currentTempShow2 = getBigIslandStateHandler().getCurrentTempShow();
                            DynamicIslandContentView currentTempShow3 = getBigIslandStateHandler().getCurrentTempShow();
                            DynamicIslandState state3 = currentTempShow3 != null ? currentTempShow3.getState() : null;
                            if (state3 != null) {
                                state3.setTempShow(true);
                            }
                            StateHandler.addState$default(getBigIslandStateHandler(), currentTempShow2, null, 2, null);
                        } else {
                            DynamicIslandContentView current5 = getSmallIslandStateHandler().getCurrent();
                            if (n.c(key3, (current5 == null || (currentIslandData3 = current5.getCurrentIslandData()) == null) ? null : currentIslandData3.getKey())) {
                                StateHandler.addState$default(getSmallIslandStateHandler(), getSmallIslandStateHandler().getCurrent(), null, 2, null);
                            } else {
                                DynamicIslandContentView current6 = getExpandedStateHandler().getCurrent();
                                if (n.c(key3, (current6 == null || (currentIslandData2 = current6.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey())) {
                                    StateHandler.addState$default(getExpandedStateHandler(), getExpandedStateHandler().getCurrent(), null, 2, null);
                                } else {
                                    DynamicIslandContentView current7 = getHiddenStateHandler().getCurrent();
                                    if (n.c(key3, (current7 == null || (currentIslandData = current7.getCurrentIslandData()) == null) ? null : currentIslandData.getKey())) {
                                        StateHandler.addState$default(getHiddenStateHandler(), getHiddenStateHandler().getCurrent(), null, 2, null);
                                    }
                                }
                            }
                        }
                    }
                    getStateHandler().stop();
                    return;
                }
            }
            Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "UpdateEventCoordinator event: " + dynamicIslandContentView.getState());
            DynamicIslandData currentIslandData16 = dynamicIslandContentView.getCurrentIslandData();
            String key4 = currentIslandData16 != null ? currentIslandData16.getKey() : null;
            DynamicIslandContentView current8 = getBigIslandStateHandler().getCurrent();
            if (n.c(key4, (current8 == null || (currentIslandData10 = current8.getCurrentIslandData()) == null) ? null : currentIslandData10.getKey())) {
                DynamicIslandContentView current9 = getBigIslandStateHandler().getCurrent();
                getBigIslandStateHandler().setCurrent(null);
                getBigIslandStateHandler().handleFillInState(arrayList);
                DynamicIslandState state4 = dynamicIslandContentView.getState();
                if (state4 != null && state4.getExpanded()) {
                    z2 = true;
                }
                if (z2) {
                    getExpandedStateHandler().handleReplacedState(current9, arrayList, Boolean.FALSE);
                } else {
                    getBigIslandStateHandler().handleReplacedState(current9, arrayList, Boolean.FALSE);
                }
            } else {
                DynamicIslandContentView current10 = getSmallIslandStateHandler().getCurrent();
                if (n.c(key4, (current10 == null || (currentIslandData9 = current10.getCurrentIslandData()) == null) ? null : currentIslandData9.getKey())) {
                    DynamicIslandContentView current11 = getSmallIslandStateHandler().getCurrent();
                    getSmallIslandStateHandler().setCurrent(null);
                    getSmallIslandStateHandler().handleFillInState(arrayList);
                    DynamicIslandState state5 = dynamicIslandContentView.getState();
                    if (state5 != null && state5.getExpanded()) {
                        z2 = true;
                    }
                    if (z2) {
                        getExpandedStateHandler().handleReplacedState(current11, arrayList, Boolean.FALSE);
                    } else {
                        getBigIslandStateHandler().handleReplacedState(current11, arrayList, Boolean.FALSE);
                    }
                } else {
                    DynamicIslandContentView current12 = getExpandedStateHandler().getCurrent();
                    if (n.c(key4, (current12 == null || (currentIslandData8 = current12.getCurrentIslandData()) == null) ? null : currentIslandData8.getKey())) {
                        StateHandler.addState$default(getExpandedStateHandler(), getExpandedStateHandler().getCurrent(), null, 2, null);
                    } else {
                        DynamicIslandContentView current13 = getHiddenStateHandler().getCurrent();
                        if (n.c(key4, (current13 == null || (currentIslandData7 = current13.getCurrentIslandData()) == null) ? null : currentIslandData7.getKey())) {
                            DynamicIslandContentView current14 = getHiddenStateHandler().getCurrent();
                            DynamicIslandData currentIslandData17 = dynamicIslandContentView.getCurrentIslandData();
                            removeHiddenState(arrayList, currentIslandData17 != null ? currentIslandData17.getKey() : null);
                            DynamicIslandState state6 = dynamicIslandContentView.getState();
                            if (state6 != null && state6.getExpanded()) {
                                z2 = true;
                            }
                            if (z2) {
                                getExpandedStateHandler().handleReplacedState(current14, arrayList, Boolean.FALSE);
                            } else {
                                getBigIslandStateHandler().handleReplacedState(current14, arrayList, Boolean.FALSE);
                            }
                        } else {
                            if (arrayList != null) {
                                Iterator<T> it = arrayList.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    Object next = it.next();
                                    DynamicIslandData currentIslandData18 = ((DynamicIslandContentView) next).getCurrentIslandData();
                                    String key5 = currentIslandData18 != null ? currentIslandData18.getKey() : null;
                                    DynamicIslandData currentIslandData19 = dynamicIslandContentView.getCurrentIslandData();
                                    if (n.c(key5, currentIslandData19 != null ? currentIslandData19.getKey() : null)) {
                                        obj = next;
                                        break;
                                    }
                                }
                                dynamicIslandContentView2 = (DynamicIslandContentView) obj;
                            }
                            if (arrayList != null) {
                                final AnonymousClass1 anonymousClass1 = new AnonymousClass1(dynamicIslandContentView);
                                arrayList.removeIf(new Predicate() { // from class: miui.systemui.dynamicisland.event.b
                                    @Override // java.util.function.Predicate
                                    public final boolean test(Object obj2) {
                                        return UpdateEventCoordinator.handleAppEvent$lambda$1(anonymousClass1, obj2);
                                    }
                                });
                            }
                            DynamicIslandState state7 = dynamicIslandContentView.getState();
                            if (state7 != null && state7.getExpanded()) {
                                z2 = true;
                            }
                            if (z2) {
                                getExpandedStateHandler().handleReplacedState(dynamicIslandContentView2, arrayList, Boolean.FALSE);
                            } else {
                                getBigIslandStateHandler().handleReplacedState(dynamicIslandContentView2, arrayList, Boolean.FALSE);
                            }
                        }
                    }
                }
            }
            getStateHandler().stop();
        }
    }
}
