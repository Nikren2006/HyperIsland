package miuix.responsive;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import miuix.core.util.WindowBaseInfo;
import miuix.responsive.map.ResponsiveState;
import miuix.responsive.map.ResponsiveStateManager;

/* JADX INFO: loaded from: classes5.dex */
public class ResponsiveStateHelper {
    @Nullable
    public static ResponsiveState computeResponsiveState(Context context, WindowBaseInfo windowBaseInfo) {
        return ResponsiveStateManager.getInstance().recordState(context, wrapperWindowInfo(context, windowBaseInfo));
    }

    @Nullable
    public static ResponsiveState computeResponsiveStateOnConfigChanged(Context context, WindowBaseInfo windowBaseInfo, Configuration configuration) {
        return ResponsiveStateManager.getInstance().recordState(context, wrapperWindowInfoByConfig(configuration, windowBaseInfo));
    }

    public static int detectResponsiveWindowType(int i2, int i3) {
        if (i2 <= 640) {
            return 1;
        }
        if (i2 >= 960) {
            return 3;
        }
        return i3 > 550 ? 2 : 1;
    }

    public static boolean isLargerThanCompact(int i2) {
        return i2 == 2 || i2 == 3;
    }

    private static int windowModeWrapper(int i2) {
        if (i2 == 0) {
            return ResponsiveState.RESPONSIVE_LAYOUT_FULL;
        }
        switch (i2) {
            case 4097:
                break;
            case 4098:
                break;
            case 4099:
                break;
            default:
                switch (i2) {
                    case 8192:
                        break;
                    case 8193:
                        break;
                    case 8194:
                        break;
                    case 8195:
                        break;
                    case 8196:
                        break;
                    default:
                        Log.w("MiuixWarning", "Unknown window mode for : " + Integer.toHexString(i2));
                        break;
                }
                break;
        }
        return ResponsiveState.RESPONSIVE_LAYOUT_FULL;
    }

    private static ResponsiveState.WindowInfoWrapper wrapWindowInfo(WindowBaseInfo windowBaseInfo, float f2) {
        ResponsiveState.WindowInfoWrapper windowInfoWrapper = new ResponsiveState.WindowInfoWrapper();
        Point point = windowBaseInfo.windowSize;
        windowInfoWrapper.windowWidth = point.x;
        windowInfoWrapper.windowHeight = point.y;
        Point point2 = windowBaseInfo.windowSizeDp;
        windowInfoWrapper.windowWidthDp = point2.x;
        windowInfoWrapper.windowHeightDp = point2.y;
        windowInfoWrapper.windowType = windowBaseInfo.windowType;
        windowInfoWrapper.windowMode = windowModeWrapper(windowBaseInfo.windowMode);
        windowInfoWrapper.windowDensity = windowBaseInfo.windowDensity;
        return windowInfoWrapper;
    }

    @NonNull
    private static ResponsiveState.WindowInfoWrapper wrapperWindowInfo(Context context, WindowBaseInfo windowBaseInfo) {
        return wrapWindowInfo(windowBaseInfo, context.getResources().getDisplayMetrics().density);
    }

    @NonNull
    private static ResponsiveState.WindowInfoWrapper wrapperWindowInfoByConfig(Configuration configuration, WindowBaseInfo windowBaseInfo) {
        return wrapWindowInfo(windowBaseInfo, configuration.densityDpi / 160.0f);
    }

    public static int detectResponsiveWindowType(int i2, int i3, int i4, int i5) {
        if (i2 <= 0) {
            i2 = i4;
        }
        if (i2 <= 640) {
            return 1;
        }
        if (i2 >= 960) {
            return 3;
        }
        return i5 > 550 ? 2 : 1;
    }
}
