package miui.systemui.dynamicisland.event.handler;

import I0.u;
import java.util.ArrayList;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class MiniWindowStateHandler extends StateHandler {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiniWindowStateHandler(DynamicIslandTouchInteractor touchInteractor) {
        super(touchInteractor);
        n.g(touchInteractor, "touchInteractor");
    }

    public final DynamicIslandContentView getFirstElement(String pkg) {
        n.g(pkg, "pkg");
        ArrayList<DynamicIslandContentView> arrayList = getCurrentMap().get(pkg);
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return (DynamicIslandContentView) u.K(arrayList);
    }

    public final DynamicIslandContentView getMainElement(String pkg) {
        n.g(pkg, "pkg");
        ArrayList<DynamicIslandContentView> arrayList = getCurrentMap().get(pkg);
        if (arrayList != null) {
            for (DynamicIslandContentView dynamicIslandContentView : arrayList) {
                if ((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.MiniWindowExpanded) {
                    return dynamicIslandContentView;
                }
            }
        }
        return null;
    }

    public final DynamicIslandContentView getSubElement(String pkg) {
        n.g(pkg, "pkg");
        ArrayList<DynamicIslandContentView> arrayList = getCurrentMap().get(pkg);
        if (arrayList != null) {
            for (DynamicIslandContentView dynamicIslandContentView : arrayList) {
                if ((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.SubMiniWindowExpanded) {
                    return dynamicIslandContentView;
                }
            }
        }
        return null;
    }

    public final DynamicIslandContentView getTopLevel(String pkg) {
        n.g(pkg, "pkg");
        DynamicIslandContentView mainElement = getMainElement(pkg);
        ArrayList<DynamicIslandContentView> arrayList = getCurrentMap().get(pkg);
        if (arrayList == null) {
            return null;
        }
        for (DynamicIslandContentView dynamicIslandContentView : arrayList) {
            if (!n.c(mainElement, dynamicIslandContentView) && StateHandler.compareState$default(this, mainElement, dynamicIslandContentView, false, 4, null)) {
                return dynamicIslandContentView;
            }
        }
        return null;
    }

    public final int getTopLevelCount(String pkg) {
        n.g(pkg, "pkg");
        DynamicIslandContentView mainElement = getMainElement(pkg);
        ArrayList<DynamicIslandContentView> arrayList = getCurrentMap().get(pkg);
        if (arrayList == null) {
            return 0;
        }
        int i2 = 0;
        for (DynamicIslandContentView dynamicIslandContentView : arrayList) {
            if (!n.c(mainElement, dynamicIslandContentView) && StateHandler.compareState$default(this, mainElement, dynamicIslandContentView, false, 4, null)) {
                i2++;
            }
        }
        return i2;
    }
}
