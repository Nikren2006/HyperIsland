package miui.systemui.dynamicisland.window;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.MiuiSettings;
import android.provider.Settings;
import android.util.Log;
import android.util.MiuiMultiWindowUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandAnimUtils {
    private static final String KEY_HOME_SUPPORT_DYNAMIC_ISLAND_HIGH = "support_dynamic_island";
    private static final String KEY_HOME_SUPPORT_DYNAMIC_ISLAND_MIDDLE = "support_dynamic_island_middle";
    public static final String TAG = "DynamicIslandAnimUtils";
    private static ContentObserver sHomeElementContentObserverForHigh;
    private static ContentObserver sHomeElementContentObserverForMiddle;
    private static boolean sHomeSupportedDynamicIslandForHigh;
    private static boolean sHomeSupportedDynamicIslandForMiddle;
    public static final DynamicIslandAnimUtils INSTANCE = new DynamicIslandAnimUtils();
    private static final boolean ELEMENT_TRANSITION_SUPPORTED = SystemProperties.getBoolean("persist.sys.element_transition_supported", false);
    private static final boolean SUPPORT_FREEFORM = MiuiMultiWindowUtils.supportFreeform();

    private DynamicIslandAnimUtils() {
    }

    public final boolean featureDynamicIslandIsMiddle() {
        return sHomeSupportedDynamicIslandForMiddle;
    }

    public final boolean featureDynamicIslandNoElement() {
        return (sHomeSupportedDynamicIslandForHigh || sHomeSupportedDynamicIslandForMiddle) ? false : true;
    }

    public final boolean featureDynamicIslandNoElementButFreeform() {
        return (sHomeSupportedDynamicIslandForHigh || sHomeSupportedDynamicIslandForMiddle || !SUPPORT_FREEFORM) ? false : true;
    }

    public final boolean featureDynamicIslandNoNeedFakeView() {
        boolean z2 = ELEMENT_TRANSITION_SUPPORTED;
        boolean z3 = sHomeSupportedDynamicIslandForHigh;
        boolean z4 = sHomeSupportedDynamicIslandForMiddle;
        boolean z5 = SUPPORT_FREEFORM;
        Log.d(TAG, "ELEMENT_TRANSITION_SUPPORTED: " + z2 + ", sHomeSupportedDynamicIslandForHigh: " + z3 + ", sHomeSupportedDynamicIslandForMiddle: " + z4 + ", SUPPORT_FREEFORM: " + z5);
        return (sHomeSupportedDynamicIslandForHigh || sHomeSupportedDynamicIslandForMiddle || z5) ? false : true;
    }

    public final boolean getSUPPORT_FREEFORM() {
        return SUPPORT_FREEFORM;
    }

    public final void registerHomeDynamicIslandContentObserver(final Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        Log.d(TAG, "registerHomeDynamicIslandContentObserver");
        if (sHomeElementContentObserverForHigh == null) {
            sHomeElementContentObserverForHigh = new ContentObserver(new Handler()) { // from class: miui.systemui.dynamicisland.window.DynamicIslandAnimUtils.registerHomeDynamicIslandContentObserver.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z2) {
                    DynamicIslandAnimUtils dynamicIslandAnimUtils = DynamicIslandAnimUtils.INSTANCE;
                    DynamicIslandAnimUtils.sHomeSupportedDynamicIslandForHigh = MiuiSettings.Global.getBoolean(context.getContentResolver(), DynamicIslandAnimUtils.KEY_HOME_SUPPORT_DYNAMIC_ISLAND_HIGH);
                    Log.d(DynamicIslandAnimUtils.TAG, "onChange: sHomeSupportedDynamicIslandForHigh: " + DynamicIslandAnimUtils.sHomeSupportedDynamicIslandForHigh);
                }
            };
            context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(KEY_HOME_SUPPORT_DYNAMIC_ISLAND_HIGH), false, sHomeElementContentObserverForHigh, -1);
        }
        ContentObserver contentObserver = sHomeElementContentObserverForHigh;
        if (contentObserver != null) {
            contentObserver.onChange(false);
        }
        if (sHomeElementContentObserverForMiddle == null) {
            sHomeElementContentObserverForMiddle = new ContentObserver(new Handler()) { // from class: miui.systemui.dynamicisland.window.DynamicIslandAnimUtils.registerHomeDynamicIslandContentObserver.2
                @Override // android.database.ContentObserver
                public void onChange(boolean z2) {
                    DynamicIslandAnimUtils dynamicIslandAnimUtils = DynamicIslandAnimUtils.INSTANCE;
                    DynamicIslandAnimUtils.sHomeSupportedDynamicIslandForMiddle = MiuiSettings.Global.getBoolean(context.getContentResolver(), DynamicIslandAnimUtils.KEY_HOME_SUPPORT_DYNAMIC_ISLAND_MIDDLE);
                    Log.d(DynamicIslandAnimUtils.TAG, "onChange: sHomeSupportedDynamicIslandForMiddle: " + DynamicIslandAnimUtils.sHomeSupportedDynamicIslandForMiddle);
                }
            };
            context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(KEY_HOME_SUPPORT_DYNAMIC_ISLAND_MIDDLE), false, sHomeElementContentObserverForMiddle, -1);
        }
        ContentObserver contentObserver2 = sHomeElementContentObserverForMiddle;
        if (contentObserver2 != null) {
            contentObserver2.onChange(false);
        }
    }

    public final boolean supportShowElementAndFreeformAnim() {
        return ELEMENT_TRANSITION_SUPPORTED && sHomeSupportedDynamicIslandForHigh && SUPPORT_FREEFORM;
    }
}
