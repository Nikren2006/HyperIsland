package androidx.window.layout;

import android.app.Activity;
import android.graphics.Rect;
import androidx.annotation.RequiresApi;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(30)
public final class ActivityCompatHelperApi30 {
    public static final ActivityCompatHelperApi30 INSTANCE = new ActivityCompatHelperApi30();

    private ActivityCompatHelperApi30() {
    }

    public final Rect currentWindowBounds(Activity activity) {
        n.g(activity, "activity");
        Rect bounds = activity.getWindowManager().getCurrentWindowMetrics().getBounds();
        n.f(bounds, "activity.windowManager.currentWindowMetrics.bounds");
        return bounds;
    }

    public final Rect maximumWindowBounds(Activity activity) {
        n.g(activity, "activity");
        Rect bounds = activity.getWindowManager().getMaximumWindowMetrics().getBounds();
        n.f(bounds, "activity.windowManager.maximumWindowMetrics.bounds");
        return bounds;
    }
}
