package androidx.core.service.quicksettings;

import android.app.PendingIntent;
import android.content.Intent;
import android.service.quicksettings.TileService;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
public class TileServiceCompat {
    private static TileServiceWrapper sTileServiceWrapper;

    @RequiresApi(24)
    public static class Api24Impl {
        private Api24Impl() {
        }

        public static void startActivityAndCollapse(TileService tileService, Intent intent) {
            tileService.startActivityAndCollapse(intent);
        }
    }

    @RequiresApi(34)
    public static class Api34Impl {
        private Api34Impl() {
        }

        public static void startActivityAndCollapse(TileService tileService, PendingIntent pendingIntent) {
            tileService.startActivityAndCollapse(pendingIntent);
        }
    }

    public interface TileServiceWrapper {
        void startActivityAndCollapse(PendingIntent pendingIntent);

        void startActivityAndCollapse(Intent intent);
    }

    private TileServiceCompat() {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static void clearTileServiceWrapper() {
        sTileServiceWrapper = null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static void setTileServiceWrapper(TileServiceWrapper tileServiceWrapper) {
        sTileServiceWrapper = tileServiceWrapper;
    }

    public static void startActivityAndCollapse(TileService tileService, PendingIntentActivityWrapper pendingIntentActivityWrapper) {
        TileServiceWrapper tileServiceWrapper = sTileServiceWrapper;
        if (tileServiceWrapper != null) {
            tileServiceWrapper.startActivityAndCollapse(pendingIntentActivityWrapper.getPendingIntent());
        } else {
            Api34Impl.startActivityAndCollapse(tileService, pendingIntentActivityWrapper.getPendingIntent());
        }
    }
}
