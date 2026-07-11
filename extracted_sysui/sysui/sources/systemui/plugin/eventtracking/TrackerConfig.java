package systemui.plugin.eventtracking;

import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.os.Build;

/* JADX INFO: loaded from: classes5.dex */
public final class TrackerConfig {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String resolveChannelName() {
            return Build.IS_DEVELOPMENT_VERSION ? "MIUI12.5-dev" : "MIUI12.5";
        }

        private Companion() {
        }
    }
}
