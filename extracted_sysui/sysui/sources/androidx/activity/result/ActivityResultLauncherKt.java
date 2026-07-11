package androidx.activity.result;

import H0.s;
import androidx.core.app.ActivityOptionsCompat;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityResultLauncherKt {
    public static final void launch(ActivityResultLauncher<Void> activityResultLauncher, ActivityOptionsCompat activityOptionsCompat) {
        activityResultLauncher.launch(null, activityOptionsCompat);
    }

    public static /* synthetic */ void launch$default(ActivityResultLauncher activityResultLauncher, ActivityOptionsCompat activityOptionsCompat, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            activityOptionsCompat = null;
        }
        launch(activityResultLauncher, activityOptionsCompat);
    }

    public static final void launchUnit(ActivityResultLauncher<s> activityResultLauncher, ActivityOptionsCompat activityOptionsCompat) {
        activityResultLauncher.launch(s.f314a, activityOptionsCompat);
    }

    public static /* synthetic */ void launchUnit$default(ActivityResultLauncher activityResultLauncher, ActivityOptionsCompat activityOptionsCompat, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            activityOptionsCompat = null;
        }
        launchUnit(activityResultLauncher, activityOptionsCompat);
    }
}
