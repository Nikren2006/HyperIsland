package systemui.plugin.eventtracking;

import android.content.Context;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes5.dex */
public final class EventTrackerFactory {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Tracker createDesktopTracker(Context context) {
            return new DesktopTracker(context);
        }

        public final Tracker createDeviceCenterTracker(Context context) {
            return new DeviceCenterTracker(context);
        }

        public final Tracker createDynamicIslandTracker(Context context) {
            return new DynamicIslandTracker(context);
        }

        public final Tracker createMiPlayTracker(Context context) {
            return new MiPlayTracker(context);
        }

        public final Tracker createSystemUITracker(Context context) {
            return new SysuiTracker(context);
        }

        private Companion() {
        }
    }
}
