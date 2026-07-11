package androidx.window.layout;

import android.app.Activity;
import androidx.annotation.RequiresApi;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(24)
public final class ActivityCompatHelperApi24 {
    public static final ActivityCompatHelperApi24 INSTANCE = new ActivityCompatHelperApi24();

    private ActivityCompatHelperApi24() {
    }

    public final boolean isInMultiWindowMode(Activity activity) {
        n.g(activity, "activity");
        return activity.isInMultiWindowMode();
    }
}
