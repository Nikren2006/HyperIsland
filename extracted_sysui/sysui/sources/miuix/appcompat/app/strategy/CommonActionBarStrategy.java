package miuix.appcompat.app.strategy;

import miuix.appcompat.app.ActionBar;
import miuix.core.util.ScreenModeHelper;

/* JADX INFO: loaded from: classes2.dex */
public class CommonActionBarStrategy implements IActionBarStrategy {
    @Override // miuix.appcompat.app.strategy.IActionBarStrategy
    public ActionBarConfig config(ActionBar actionBar, ActionBarSpec actionBarSpec) {
        if (actionBar == null || actionBarSpec == null) {
            return null;
        }
        ActionBarConfig actionBarConfig = new ActionBarConfig();
        int i2 = actionBarSpec.actionBarWidthDp;
        if (actionBarSpec.isInFloatingWindowMode || i2 >= 960) {
            actionBarConfig.expandState = 0;
            actionBarConfig.resizable = false;
            actionBarConfig.endMenuMaxItemCount = 3;
            return actionBarConfig;
        }
        float f2 = i2;
        int i3 = actionBarSpec.windowWidthDp;
        if (f2 < i3 * 0.8f) {
            if ((actionBarSpec.deviceType != 2 || i3 <= 640) && i2 <= 410) {
                actionBarConfig.resizable = true;
                actionBarConfig.endMenuMaxItemCount = 2;
                return actionBarConfig;
            }
            actionBarConfig.expandState = 0;
            actionBarConfig.resizable = false;
            if (i2 < 410) {
                actionBarConfig.endMenuMaxItemCount = 2;
                return actionBarConfig;
            }
            actionBarConfig.endMenuMaxItemCount = 3;
            return actionBarConfig;
        }
        int i4 = actionBarSpec.deviceType;
        if ((i4 == 2 && i3 > 640) || ((i4 == 1 && i3 > actionBarSpec.windowHeightDp) || (((i4 == 3 || i4 == 4) && Math.min(i3, actionBarSpec.windowHeightDp) <= 550 && actionBarSpec.windowWidthDp > actionBarSpec.windowHeightDp) || (actionBarSpec.deviceType == 4 && Math.min(actionBarSpec.windowWidthDp, actionBarSpec.windowHeightDp) <= 330)))) {
            actionBarConfig.expandState = 0;
            actionBarConfig.resizable = false;
        } else if (!ScreenModeHelper.isInSplitScreenMode(actionBarSpec.windowMode) || actionBarSpec.deviceType == 2) {
            actionBarConfig.resizable = true;
        } else if (actionBarSpec.windowHeightDp / actionBarSpec.windowWidthDp < 1.7f) {
            actionBarConfig.expandState = 0;
            actionBarConfig.resizable = false;
        }
        actionBarConfig.endMenuMaxItemCount = 3;
        return actionBarConfig;
    }
}
