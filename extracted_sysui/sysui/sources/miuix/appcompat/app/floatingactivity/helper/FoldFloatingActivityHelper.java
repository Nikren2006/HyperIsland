package miuix.appcompat.app.floatingactivity.helper;

import android.graphics.Point;
import miuix.appcompat.app.AppCompatActivity;
import miuix.appcompat.app.floatingactivity.FloatingAnimHelper;
import miuix.core.util.EnvStateManager;
import miuix.core.util.ScreenModeHelper;
import miuix.core.util.WindowBaseInfo;

/* JADX INFO: loaded from: classes2.dex */
public class FoldFloatingActivityHelper extends TabletFloatingActivityHelper {
    public FoldFloatingActivityHelper(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.TabletFloatingActivityHelper
    public void execExitAnim() {
        if (FloatingAnimHelper.isSupportTransWithClipAnim()) {
            return;
        }
        if (isFloatingWindow()) {
            FloatingAnimHelper.clearFloatingWindowAnim(this.mActivity);
        } else if (FloatingAnimHelper.obtainPageIndex(this.mActivity) >= 0) {
            FloatingAnimHelper.execFloatingWindowExitAnimRomNormal(this.mActivity);
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public boolean isFloatingModeSupport() {
        WindowBaseInfo windowInfo = EnvStateManager.getWindowInfo(this.mActivity);
        if (EnvStateManager.getSmallestScreenWidthDp(this.mActivity) < 600) {
            return false;
        }
        int i2 = windowInfo.windowMode;
        if (i2 == 8195 || !ScreenModeHelper.isInFreeFormMode(i2)) {
            return true;
        }
        Point point = windowInfo.windowSizeDp;
        return point.y >= 747 && point.x > 670;
    }
}
