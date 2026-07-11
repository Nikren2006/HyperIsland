package miui.systemui.dynamicisland.event.handler;

import java.util.ArrayList;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class AppStateHandler extends StateHandler {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppStateHandler(DynamicIslandTouchInteractor touchInteractor) {
        super(touchInteractor);
        n.g(touchInteractor, "touchInteractor");
    }

    public final DynamicIslandContentView getMainElement() {
        ArrayList<DynamicIslandContentView> currentList = getCurrentList();
        if (currentList != null) {
            for (DynamicIslandContentView dynamicIslandContentView : currentList) {
                if ((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.AppExpanded) {
                    return dynamicIslandContentView;
                }
            }
        }
        return null;
    }

    public final DynamicIslandContentView getSubElement() {
        ArrayList<DynamicIslandContentView> currentList = getCurrentList();
        if (currentList != null) {
            for (DynamicIslandContentView dynamicIslandContentView : currentList) {
                if ((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.SubAppExpanded) {
                    return dynamicIslandContentView;
                }
            }
        }
        return null;
    }

    public final DynamicIslandContentView getTopLevel() {
        DynamicIslandContentView mainElement = getMainElement();
        ArrayList<DynamicIslandContentView> currentList = getCurrentList();
        if (currentList == null) {
            return null;
        }
        for (DynamicIslandContentView dynamicIslandContentView : currentList) {
            if (!n.c(mainElement, dynamicIslandContentView) && StateHandler.compareState$default(this, mainElement, dynamicIslandContentView, false, 4, null)) {
                return dynamicIslandContentView;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int getTopLevelCount() {
        /*
            r6 = this;
            miui.systemui.dynamicisland.window.content.DynamicIslandContentView r0 = r6.getMainElement()
            r1 = 0
            if (r0 == 0) goto L1c
            com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData r2 = r0.getCurrentIslandData()
            if (r2 == 0) goto L1c
            java.lang.Integer r2 = r2.getProperties()
            if (r2 != 0) goto L14
            goto L1c
        L14:
            int r2 = r2.intValue()
            r3 = 1
            if (r2 != r3) goto L1c
            goto L1d
        L1c:
            r3 = r1
        L1d:
            java.util.ArrayList r2 = r6.getCurrentList()
            if (r2 == 0) goto L42
            java.util.Iterator r2 = r2.iterator()
        L27:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L42
            java.lang.Object r4 = r2.next()
            miui.systemui.dynamicisland.window.content.DynamicIslandContentView r4 = (miui.systemui.dynamicisland.window.content.DynamicIslandContentView) r4
            boolean r5 = kotlin.jvm.internal.n.c(r0, r4)
            if (r5 != 0) goto L27
            boolean r4 = r6.compareState(r0, r4, r3)
            if (r4 == 0) goto L27
            int r1 = r1 + 1
            goto L27
        L42:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.event.handler.AppStateHandler.getTopLevelCount():int");
    }
}
