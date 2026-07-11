package miui.systemui.dynamicisland.event.handler;

import H0.i;
import H0.s;
import I0.u;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.ArrayList;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.display.AntiBurnInManager;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class BigIslandStateHandler extends StateHandler {
    private final Context context;
    private DynamicIslandContentView current;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BigIslandStateHandler(DynamicIslandTouchInteractor touchInteractor, Context context) {
        super(touchInteractor);
        n.g(touchInteractor, "touchInteractor");
        n.g(context, "context");
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean doMultiAppExpanded(DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList, Boolean bool) {
        StateHandler next;
        StateHandler next2;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        AppStateHandler appStateHandler;
        AttributeSet attributeSet = null;
        Object[] objArr = 0;
        Integer numValueOf = (dynamicIslandContentView == null || (dynamicIslandEventCoordinator = dynamicIslandContentView.getDynamicIslandEventCoordinator()) == null || (appStateHandler = dynamicIslandEventCoordinator.getAppStateHandler()) == null) ? null : Integer.valueOf(appStateHandler.getTopLevelCount());
        if (numValueOf == null || numValueOf.intValue() != 0) {
            if (getNext() == null || (next = getNext()) == null) {
                return true;
            }
            next.handleReplacedState(dynamicIslandContentView, arrayList, bool);
            return true;
        }
        setCurrent(dynamicIslandContentView);
        DynamicIslandState state = dynamicIslandContentView.getState();
        addState(getCurrent(), state != null ? state.changeToState(new DynamicIslandState.BigIsland()) : null);
        if (!hasEmpty(arrayList)) {
            if (arrayList != null) {
                DynamicIslandContentView current = getCurrent();
                Context context = current != null ? current.getContext() : null;
                n.d(context);
                DynamicIslandContentView dynamicIslandContentView2 = new DynamicIslandContentView(context, attributeSet, 2, objArr == true ? 1 : 0);
                dynamicIslandContentView2.setState(new DynamicIslandState.Empty());
                s sVar = s.f314a;
                arrayList.add(0, dynamicIslandContentView2);
            }
            StateHandler next3 = getNext();
            if (next3 != null && (next2 = next3.getNext()) != null) {
                next2.handleFillInState(arrayList);
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean doMultiMiniWindow(DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList, Boolean bool) {
        StateHandler next;
        StateHandler next2;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        MiniWindowStateHandler miniWindowStateHandler;
        DynamicIslandData currentIslandData;
        Bundle extras;
        AttributeSet attributeSet = null;
        Object[] objArr = 0;
        String string = (dynamicIslandContentView == null || (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null || (extras = currentIslandData.getExtras()) == null) ? null : extras.getString("miui.pkg.name");
        Integer numValueOf = (string == null || (dynamicIslandEventCoordinator = dynamicIslandContentView.getDynamicIslandEventCoordinator()) == null || (miniWindowStateHandler = dynamicIslandEventCoordinator.getMiniWindowStateHandler()) == null) ? null : Integer.valueOf(miniWindowStateHandler.getTopLevelCount(string));
        if (numValueOf == null || numValueOf.intValue() != 0) {
            if (getNext() == null || (next = getNext()) == null) {
                return true;
            }
            next.handleReplacedState(dynamicIslandContentView, arrayList, bool);
            return true;
        }
        setCurrent(dynamicIslandContentView);
        DynamicIslandState state = dynamicIslandContentView.getState();
        addState(getCurrent(), state != null ? state.changeToState(new DynamicIslandState.BigIsland()) : null);
        if (!hasEmpty(arrayList)) {
            if (arrayList != null) {
                DynamicIslandContentView current = getCurrent();
                Context context = current != null ? current.getContext() : null;
                n.d(context);
                DynamicIslandContentView dynamicIslandContentView2 = new DynamicIslandContentView(context, attributeSet, 2, objArr == true ? 1 : 0);
                dynamicIslandContentView2.setState(new DynamicIslandState.Empty());
                s sVar = s.f314a;
                arrayList.add(0, dynamicIslandContentView2);
            }
            StateHandler next3 = getNext();
            if (next3 != null && (next2 = next3.getNext()) != null) {
                next2.handleFillInState(arrayList);
            }
        }
        return false;
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public DynamicIslandState calculateCollapse(DynamicIslandContentView dynamicIslandContentView) {
        StateHandler next;
        if (!StateHandler.compareState$default(this, getCurrent(), dynamicIslandContentView, false, 4, null) && getCurrent() != null) {
            if (dynamicIslandContentView == null || getNext() == null || (next = getNext()) == null) {
                return null;
            }
            return next.calculateCollapse(dynamicIslandContentView);
        }
        return new DynamicIslandState.BigIsland();
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public DynamicIslandContentView getCurrent() {
        return this.current;
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void handleEnterBurnIn() {
        DynamicIslandContentView current = getCurrent();
        if (current != null) {
            current.setHasEverBurnedIn(true);
        }
        DynamicIslandContentView current2 = getCurrent();
        if (current2 != null) {
            current2.setBurnInState(AntiBurnInManager.BurnInStates.BurnIn);
        }
        DynamicIslandContentView current3 = getCurrent();
        if (current3 != null) {
            current3.animBigIslandBurnIn(getCurrent());
        }
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void handleExitBurnIn() {
        DynamicIslandContentView current = getCurrent();
        if (current != null) {
            current.animBigIslandExitBurn();
        }
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public DynamicIslandContentView handleFillInState(ArrayList<DynamicIslandContentView> arrayList) {
        StateHandler next;
        StateHandler next2 = getNext();
        DynamicIslandContentView dynamicIslandContentViewHandleFillInState = next2 != null ? next2.handleFillInState(arrayList) : null;
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "BigIslandHandler handleFillInState " + getCurrent() + "  " + (dynamicIslandContentViewHandleFillInState != null ? dynamicIslandContentViewHandleFillInState.getState() : null));
        if (dynamicIslandContentViewHandleFillInState == null || (dynamicIslandContentViewHandleFillInState.getState() instanceof DynamicIslandState.Empty)) {
            setCurrent(null);
            DynamicIslandContentView dynamicIslandContentView = arrayList != null ? (DynamicIslandContentView) u.M(arrayList) : null;
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "BigIslandHandler handleFillInState hidden: " + dynamicIslandContentView);
            if ((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.Empty) {
                arrayList.remove(0);
                StateHandler next3 = getNext();
                if (next3 != null && (next = next3.getNext()) != null) {
                    next.handleFillInState(arrayList);
                }
            }
        } else {
            setCurrent(dynamicIslandContentViewHandleFillInState);
            DynamicIslandState state = dynamicIslandContentViewHandleFillInState.getState();
            addState(getCurrent(), state != null ? state.changeToState(new DynamicIslandState.BigIsland()) : null);
            StateHandler next4 = getNext();
            if (next4 != null) {
                next4.handleFillInState(arrayList);
            }
        }
        return getCurrent();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:115:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0244  */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.util.AttributeSet, kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARN: Type inference failed for: r0v1, types: [miui.systemui.dynamicisland.event.DynamicIslandState] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r11v0, types: [miui.systemui.dynamicisland.event.handler.BigIslandStateHandler, miui.systemui.dynamicisland.event.handler.StateHandler] */
    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleReplacedState(miui.systemui.dynamicisland.window.content.DynamicIslandContentView r12, java.util.ArrayList<miui.systemui.dynamicisland.window.content.DynamicIslandContentView> r13, java.lang.Boolean r14) {
        /*
            Method dump skipped, instruction units count: 688
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.event.handler.BigIslandStateHandler.handleReplacedState(miui.systemui.dynamicisland.window.content.DynamicIslandContentView, java.util.ArrayList, java.lang.Boolean):void");
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void handleReplacedStateInTinyScreen(DynamicIslandContentView dynamicIslandContentView, ArrayList<DynamicIslandContentView> arrayList, Boolean bool) {
        DynamicIslandState state;
        String key;
        String key2;
        DynamicIslandState state2;
        DynamicIslandData currentIslandData;
        DynamicIslandState state3;
        DynamicIslandData currentIslandData2;
        DynamicIslandState dynamicIslandStateChangeToState = null;
        String tickerData = (dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getTickerData();
        Boolean boolValueOf = (dynamicIslandContentView == null || (state3 = dynamicIslandContentView.getState()) == null) ? null : Boolean.valueOf(state3.getTempShow());
        DynamicIslandContentView current = getCurrent();
        DynamicIslandContentView current2 = getCurrent();
        String tickerData2 = (current2 == null || (currentIslandData = current2.getCurrentIslandData()) == null) ? null : currentIslandData.getTickerData();
        DynamicIslandContentView current3 = getCurrent();
        Boolean boolValueOf2 = (current3 == null || (state2 = current3.getState()) == null) ? null : Boolean.valueOf(state2.getTempShow());
        DynamicIslandContentView currentTempShow = getCurrentTempShow();
        Integer numValueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "BigIslandStateHandler handleReplacedStateInTinyScreen: originState: " + dynamicIslandContentView + "  ---- " + tickerData + "--- tempShow:" + boolValueOf + " current: " + current + "----" + tickerData2 + " " + boolValueOf2 + ", currentTempShow: " + currentTempShow + "hiddenList: " + numValueOf + "}, userSwipe: " + bool + " , next: " + getNext());
        DynamicIslandContentView current4 = getCurrent();
        String str = "";
        if (current4 != null) {
            DynamicIslandState.Deleted deleted = new DynamicIslandState.Deleted();
            DynamicIslandData currentIslandData3 = current4.getCurrentIslandData();
            if (currentIslandData3 == null || (key2 = currentIslandData3.getKey()) == null) {
                key2 = "";
            }
            deleted.setDeleteKey(key2);
            addState(current4, deleted);
        }
        if (dynamicIslandContentView == null || (state = dynamicIslandContentView.getState()) == null || !state.getTempShow()) {
            StateHandler next = getNext();
            if (next != null) {
                next.handleReplacedStateInTinyScreen(dynamicIslandContentView, arrayList, bool);
                return;
            }
            return;
        }
        DynamicIslandContentView currentTempShow2 = getCurrentTempShow();
        setCurrentTempShow(dynamicIslandContentView);
        DynamicIslandState state4 = dynamicIslandContentView.getState();
        if (state4 != null) {
            DynamicIslandState.BigIsland bigIsland = new DynamicIslandState.BigIsland();
            bigIsland.setTempShow(true);
            dynamicIslandStateChangeToState = state4.changeToState(bigIsland);
        }
        if (currentTempShow2 != null) {
            DynamicIslandState.Deleted deleted2 = new DynamicIslandState.Deleted();
            DynamicIslandData currentIslandData4 = currentTempShow2.getCurrentIslandData();
            if (currentIslandData4 != null && (key = currentIslandData4.getKey()) != null) {
                str = key;
            }
            deleted2.setDeleteKey(str);
            addState(currentTempShow2, deleted2);
        }
        addState(dynamicIslandContentView, dynamicIslandStateChangeToState);
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void onSwipe(float f2, float f3, DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2, DynamicIslandContentView dynamicIslandContentView3, ArrayList<DynamicIslandContentView> arrayList) {
        int iGatherStates = gatherStates(dynamicIslandContentView, dynamicIslandContentView2, dynamicIslandContentView3, arrayList);
        DynamicIslandState dynamicIslandStateChangeToState = null;
        DynamicIslandState state = dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null;
        if (dynamicIslandContentView == null) {
            return;
        }
        ((Number) ((i) getTouchInteractor().getSwipeInfo().getValue()).d()).floatValue();
        if (swipeHorizontal(dynamicIslandContentView)) {
            if (iGatherStates != 3) {
                if (iGatherStates != 5) {
                    if (iGatherStates != 9) {
                        if (iGatherStates != 11) {
                            if (state != null) {
                                dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Hidden());
                            }
                        } else if (isSwipeTowardsSmallIsland(f2, dynamicIslandContentView.getContext())) {
                            if (arrayList != null && arrayList.size() != 0) {
                                DynamicIslandContentView dynamicIslandContentView4 = arrayList.get(arrayList.size() - 1);
                                n.f(dynamicIslandContentView4, "get(...)");
                                if (dynamicIslandContentView4.getState() instanceof DynamicIslandState.Empty) {
                                    if (state != null) {
                                        dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Hidden());
                                    }
                                } else if (state != null) {
                                    dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.SmallIsland());
                                }
                            } else if (state != null) {
                                dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Hidden());
                            }
                        } else if (state != null) {
                            dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Hidden());
                        }
                    } else if (arrayList == null || arrayList.size() == 0) {
                        if (state != null) {
                            dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.BigIsland());
                        }
                    } else if (isSwipeTowardsSmallIsland(f2, dynamicIslandContentView.getContext())) {
                        if (arrayList.get(arrayList.size() - 1).getState() instanceof DynamicIslandState.Hidden) {
                            if (state != null) {
                                dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.SmallIsland());
                            }
                        } else if (state != null) {
                            dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Hidden());
                        }
                    } else if (arrayList.get(0).getState() instanceof DynamicIslandState.Hidden) {
                        if (state != null) {
                            dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.BigIsland());
                        }
                    } else if (state != null) {
                        dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Hidden());
                    }
                } else if (state != null) {
                    dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.SmallIsland());
                }
            } else if (state != null) {
                dynamicIslandStateChangeToState = state.changeToState(new DynamicIslandState.Hidden());
            }
            dynamicIslandContentView.onSwipe(f2, f3, dynamicIslandContentView, state, dynamicIslandStateChangeToState);
        }
    }

    @Override // miui.systemui.dynamicisland.event.handler.StateHandler
    public void setCurrent(DynamicIslandContentView dynamicIslandContentView) {
        this.current = dynamicIslandContentView;
    }
}
