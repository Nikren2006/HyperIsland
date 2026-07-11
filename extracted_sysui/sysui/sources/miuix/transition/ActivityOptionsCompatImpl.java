package miuix.transition;

import android.app.ActivityOptions;
import android.os.Bundle;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class ActivityOptionsCompatImpl extends androidx.core.app.ActivityOptionsCompat {
    private final ActivityOptions mActivityOptions;

    public ActivityOptionsCompatImpl(ActivityOptions activityOptions) {
        this.mActivityOptions = activityOptions;
    }

    @Override // androidx.core.app.ActivityOptionsCompat
    @Nullable
    public Bundle toBundle() {
        return this.mActivityOptions.toBundle();
    }
}
