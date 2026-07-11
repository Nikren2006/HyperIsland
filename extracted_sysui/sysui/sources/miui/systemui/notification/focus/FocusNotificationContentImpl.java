package miui.systemui.notification.focus;

import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.notification.FullAodStatusManager;

/* JADX INFO: loaded from: classes4.dex */
public final class FocusNotificationContentImpl extends FocusNotificationContent {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "FocusNotificationContentImpl";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void updateFullAod(boolean z2) {
        FullAodStatusManager.INSTANCE.updateFullAod(z2);
    }
}
