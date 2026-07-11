package systemui.plugin.eventtracking.utils;

import H0.d;
import H0.e;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.SystemProperties;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.os.Build;
import miui.systemui.util.FoldUtils;

/* JADX INFO: loaded from: classes5.dex */
@SuppressLint({"SimpleDateFormat"})
public final class EventsUtils {
    public static final EventsUtils INSTANCE = new EventsUtils();
    private static final boolean IS_FOLD;
    private static final boolean IS_TABLET;
    private static final String TAG = "EventsUtils";
    private static final d isFlipDevice$delegate;
    private static final d isFlipDeviceMethod$delegate;
    private static final d isFoldDeviceInside$delegate;
    private static final d isFoldDeviceInsideMethod$delegate;
    private static long versionCode;
    private static String versionName;

    /* JADX INFO: renamed from: systemui.plugin.eventtracking.utils.EventsUtils$isFlipDevice$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            boolean zBooleanValue = false;
            try {
                Method methodIsFlipDeviceMethod = EventsUtils.INSTANCE.isFlipDeviceMethod();
                Object objInvoke = methodIsFlipDeviceMethod != null ? methodIsFlipDeviceMethod.invoke(null, null) : null;
                Boolean bool = objInvoke instanceof Boolean ? (Boolean) objInvoke : null;
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                }
            } catch (Throwable th) {
                Log.e(EventsUtils.TAG, "Invoke isFlipDevice method failed.", th);
            }
            return Boolean.valueOf(zBooleanValue);
        }
    }

    /* JADX INFO: renamed from: systemui.plugin.eventtracking.utils.EventsUtils$isFlipDeviceMethod$2, reason: invalid class name and case insensitive filesystem */
    public static final class C07382 extends o implements Function0 {
        public static final C07382 INSTANCE = new C07382();

        public C07382() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Method invoke() {
            try {
                return Class.forName("miui.util.MiuiMultiDisplayTypeInfo").getMethod("isFlipDevice", null);
            } catch (Throwable th) {
                Log.e(EventsUtils.TAG, "Get isFlipDevice method failed.", th);
                return null;
            }
        }
    }

    /* JADX INFO: renamed from: systemui.plugin.eventtracking.utils.EventsUtils$isFoldDeviceInside$2, reason: invalid class name and case insensitive filesystem */
    public static final class C07392 extends o implements Function0 {
        public static final C07392 INSTANCE = new C07392();

        public C07392() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            boolean zBooleanValue = false;
            try {
                Method methodIsFoldDeviceInsideMethod = EventsUtils.INSTANCE.isFoldDeviceInsideMethod();
                Object objInvoke = methodIsFoldDeviceInsideMethod != null ? methodIsFoldDeviceInsideMethod.invoke(null, null) : null;
                Boolean bool = objInvoke instanceof Boolean ? (Boolean) objInvoke : null;
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                }
            } catch (Throwable th) {
                Log.e(EventsUtils.TAG, "get isFoldDeviceInside method failed.", th);
            }
            return Boolean.valueOf(zBooleanValue);
        }
    }

    /* JADX INFO: renamed from: systemui.plugin.eventtracking.utils.EventsUtils$isFoldDeviceInsideMethod$2, reason: invalid class name and case insensitive filesystem */
    public static final class C07402 extends o implements Function0 {
        public static final C07402 INSTANCE = new C07402();

        public C07402() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Method invoke() {
            try {
                return Class.forName("miui.util.MiuiMultiDisplayTypeInfo").getMethod("isFoldDeviceInside", null);
            } catch (Throwable th) {
                Log.e(EventsUtils.TAG, "get isFoldDeviceInside method failed.", th);
                return null;
            }
        }
    }

    static {
        IS_FOLD = SystemProperties.getInt(FoldUtils.MUILT_DISPLAY_TYPE, 0) == 2;
        IS_TABLET = Build.IS_TABLET;
        isFoldDeviceInside$delegate = e.b(C07392.INSTANCE);
        isFoldDeviceInsideMethod$delegate = e.b(C07402.INSTANCE);
        isFlipDevice$delegate = e.b(AnonymousClass2.INSTANCE);
        isFlipDeviceMethod$delegate = e.b(C07382.INSTANCE);
    }

    private EventsUtils() {
    }

    public static final Point getScreenSize(Context context) {
        n.g(context, "context");
        Object systemService = context.getSystemService("window");
        n.e(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        if (defaultDisplay == null) {
            return null;
        }
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        return point;
    }

    public static final boolean isFlipDevice() {
        return ((Boolean) isFlipDevice$delegate.getValue()).booleanValue();
    }

    public static /* synthetic */ void isFlipDevice$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Method isFlipDeviceMethod() {
        return (Method) isFlipDeviceMethod$delegate.getValue();
    }

    public static final boolean isFoldDeviceInside() {
        return ((Boolean) isFoldDeviceInside$delegate.getValue()).booleanValue();
    }

    public static /* synthetic */ void isFoldDeviceInside$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Method isFoldDeviceInsideMethod() {
        return (Method) isFoldDeviceInsideMethod$delegate.getValue();
    }

    public static final boolean isTinyScreen(Context context) {
        n.g(context, "context");
        Point screenSize = getScreenSize(context);
        n.d(screenSize);
        return ((int) (((float) Math.max(screenSize.x, screenSize.y)) / context.getResources().getDisplayMetrics().density)) <= 670;
    }

    public final String getGetDate() {
        String str = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        n.f(str, "format(...)");
        return str;
    }

    public final boolean getIS_FOLD() {
        return IS_FOLD;
    }

    public final boolean getIS_TABLET() {
        return IS_TABLET;
    }

    public final long getVersionCode() {
        return versionCode;
    }

    public final String getVersionName() {
        String str = versionName;
        if (str != null) {
            return str;
        }
        n.w("versionName");
        return null;
    }

    public final void init(Context context) throws PackageManager.NameNotFoundException {
        n.g(context, "context");
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        String str = packageInfo.versionName;
        if (str == null) {
            str = "";
        } else {
            n.d(str);
        }
        versionName = str;
        versionCode = packageInfo.getLongVersionCode();
    }
}
