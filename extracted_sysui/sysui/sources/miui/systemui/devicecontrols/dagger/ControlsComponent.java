package miui.systemui.devicecontrols.dagger;

import E0.a;
import O0.b;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.settings.UserTracker;
import java.util.Optional;
import kotlin.jvm.internal.n;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.devicecontrols.ui.MiuiControlsUiController;
import miui.systemui.util.SmartDeviceUtils;
import miui.systemui.util.settings.SecureSettings;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public final class ControlsComponent {
    private boolean canShowWhileLockedSetting;
    private final Context context;
    private final boolean featureEnabled;
    private final a lazyControlsController;
    private final a lazyControlsListingController;
    private final a lazyControlsUiController;
    private final LockPatternUtils lockPatternUtils;
    private final SecureSettings secureSettings;
    private final ContentObserver showWhileLockedObserver;
    private final UserTracker userTracker;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Visibility {
        private static final /* synthetic */ O0.a $ENTRIES;
        private static final /* synthetic */ Visibility[] $VALUES;
        public static final Visibility AVAILABLE = new Visibility("AVAILABLE", 0);
        public static final Visibility AVAILABLE_AFTER_UNLOCK = new Visibility("AVAILABLE_AFTER_UNLOCK", 1);
        public static final Visibility UNAVAILABLE = new Visibility("UNAVAILABLE", 2);

        private static final /* synthetic */ Visibility[] $values() {
            return new Visibility[]{AVAILABLE, AVAILABLE_AFTER_UNLOCK, UNAVAILABLE};
        }

        static {
            Visibility[] visibilityArr$values = $values();
            $VALUES = visibilityArr$values;
            $ENTRIES = b.a(visibilityArr$values);
        }

        private Visibility(String str, int i2) {
        }

        public static O0.a getEntries() {
            return $ENTRIES;
        }

        public static Visibility valueOf(String str) {
            return (Visibility) Enum.valueOf(Visibility.class, str);
        }

        public static Visibility[] values() {
            return (Visibility[]) $VALUES.clone();
        }
    }

    public ControlsComponent(@ControlsFeatureEnabled boolean z2, Context context, a lazyControlsController, a lazyControlsUiController, a lazyControlsListingController, LockPatternUtils lockPatternUtils, UserTracker userTracker, SecureSettings secureSettings) {
        n.g(context, "context");
        n.g(lazyControlsController, "lazyControlsController");
        n.g(lazyControlsUiController, "lazyControlsUiController");
        n.g(lazyControlsListingController, "lazyControlsListingController");
        n.g(lockPatternUtils, "lockPatternUtils");
        n.g(userTracker, "userTracker");
        n.g(secureSettings, "secureSettings");
        this.featureEnabled = z2;
        this.context = context;
        this.lazyControlsController = lazyControlsController;
        this.lazyControlsUiController = lazyControlsUiController;
        this.lazyControlsListingController = lazyControlsListingController;
        this.lockPatternUtils = lockPatternUtils;
        this.userTracker = userTracker;
        this.secureSettings = secureSettings;
        ContentObserver contentObserver = new ContentObserver() { // from class: miui.systemui.devicecontrols.dagger.ControlsComponent$showWhileLockedObserver$1
            {
                super(null);
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z3) {
                this.this$0.updateShowWhileLocked();
            }
        };
        this.showWhileLockedObserver = contentObserver;
        if (z2) {
            secureSettings.registerContentObserver(SmartDeviceUtils.INSTANCE.getURI_LOCKSCREEN_SMART_DEVICE_CONTROL(), false, contentObserver);
            updateShowWhileLocked();
        }
    }

    private final ContentResolver getContentResolver() {
        ContentResolver contentResolver = this.context.getContentResolver();
        n.f(contentResolver, "getContentResolver(...)");
        return contentResolver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateShowWhileLocked() {
        this.canShowWhileLockedSetting = this.secureSettings.getInt(SmartDeviceUtils.LOCKSCREEN_SMART_DEVICE_CONTROL, 0) != 0;
    }

    public final Optional<ControlsController> getControlsController() {
        Optional<ControlsController> optionalEmpty;
        String str;
        if (this.featureEnabled) {
            optionalEmpty = Optional.of(this.lazyControlsController.get());
            str = "of(...)";
        } else {
            optionalEmpty = Optional.empty();
            str = "empty(...)";
        }
        n.f(optionalEmpty, str);
        return optionalEmpty;
    }

    public final Optional<ControlsListingController> getControlsListingController() {
        if (this.featureEnabled) {
            Optional<ControlsListingController> optionalOf = Optional.of(this.lazyControlsListingController.get());
            n.d(optionalOf);
            return optionalOf;
        }
        Optional<ControlsListingController> optionalEmpty = Optional.empty();
        n.d(optionalEmpty);
        return optionalEmpty;
    }

    public final Optional<MiuiControlsUiController> getControlsUiController() {
        Optional<MiuiControlsUiController> optionalEmpty;
        String str;
        if (this.featureEnabled) {
            optionalEmpty = Optional.of(this.lazyControlsUiController.get());
            str = "of(...)";
        } else {
            optionalEmpty = Optional.empty();
            str = "empty(...)";
        }
        n.f(optionalEmpty, str);
        return optionalEmpty;
    }

    public final ContentObserver getShowWhileLockedObserver() {
        return this.showWhileLockedObserver;
    }

    public final boolean isEnabled() {
        return this.featureEnabled;
    }
}
