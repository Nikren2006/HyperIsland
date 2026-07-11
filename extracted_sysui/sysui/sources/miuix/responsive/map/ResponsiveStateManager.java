package miuix.responsive.map;

import android.content.Context;
import androidx.annotation.Nullable;
import java.util.concurrent.ConcurrentHashMap;
import miuix.responsive.map.ResponsiveState;

/* JADX INFO: loaded from: classes5.dex */
public class ResponsiveStateManager {
    private static ConcurrentHashMap<Integer, ResponsiveState> mapState = new ConcurrentHashMap<>();
    private static volatile ResponsiveStateManager sInstance;

    public static ResponsiveStateManager getInstance() {
        if (sInstance == null) {
            synchronized (ResponsiveStateManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ResponsiveStateManager();
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    @Nullable
    public ResponsiveState getResponsiveState(Context context) {
        if (context == null) {
            return null;
        }
        int iHashCode = context.hashCode();
        ResponsiveState responsiveState = mapState.get(Integer.valueOf(iHashCode));
        if (responsiveState != null) {
            return responsiveState;
        }
        ResponsiveState responsiveState2 = new ResponsiveState();
        mapState.put(Integer.valueOf(iHashCode), responsiveState2);
        return responsiveState2;
    }

    @Deprecated
    public int getScreenMode(Context context) {
        if (context == null) {
            return ResponsiveState.RESPONSIVE_LAYOUT_FULL;
        }
        int iHashCode = context.hashCode();
        ResponsiveState responsiveState = mapState.get(Integer.valueOf(iHashCode));
        if (responsiveState == null) {
            responsiveState = new ResponsiveState();
            mapState.put(Integer.valueOf(iHashCode), responsiveState);
        }
        return responsiveState.getScreenMode();
    }

    @Nullable
    public ResponsiveState recordState(Context context, ResponsiveState.WindowInfoWrapper windowInfoWrapper) {
        if (context == null) {
            return null;
        }
        int iHashCode = context.hashCode();
        ResponsiveState responsiveState = mapState.get(Integer.valueOf(iHashCode));
        if (responsiveState == null) {
            responsiveState = new ResponsiveState();
            mapState.put(Integer.valueOf(iHashCode), responsiveState);
        }
        responsiveState.updateFromWindowInfoWrapper(windowInfoWrapper);
        return responsiveState;
    }

    public void remove(Context context) {
        mapState.remove(Integer.valueOf(context.hashCode()));
    }
}
