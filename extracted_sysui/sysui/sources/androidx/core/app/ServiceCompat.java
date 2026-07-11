package androidx.core.app;

import android.app.Notification;
import android.app.Service;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceCompat {
    private static final int FOREGROUND_SERVICE_TYPE_ALLOWED_SINCE_Q = 255;
    private static final int FOREGROUND_SERVICE_TYPE_ALLOWED_SINCE_U = 1073745919;
    public static final int START_STICKY = 1;
    public static final int STOP_FOREGROUND_DETACH = 2;
    public static final int STOP_FOREGROUND_REMOVE = 1;

    @RequiresApi(24)
    public static class Api24Impl {
        private Api24Impl() {
        }

        public static void stopForeground(Service service, int i2) {
            service.stopForeground(i2);
        }
    }

    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static void startForeground(Service service, int i2, Notification notification, int i3) {
            if (i3 == 0 || i3 == -1) {
                service.startForeground(i2, notification, i3);
            } else {
                service.startForeground(i2, notification, i3 & 255);
            }
        }
    }

    @RequiresApi(34)
    public static class Api34Impl {
        private Api34Impl() {
        }

        public static void startForeground(Service service, int i2, Notification notification, int i3) {
            if (i3 == 0 || i3 == -1) {
                service.startForeground(i2, notification, i3);
            } else {
                service.startForeground(i2, notification, i3 & ServiceCompat.FOREGROUND_SERVICE_TYPE_ALLOWED_SINCE_U);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface StopForegroundFlags {
    }

    private ServiceCompat() {
    }

    public static void startForeground(Service service, int i2, Notification notification, int i3) {
        Api34Impl.startForeground(service, i2, notification, i3);
    }

    public static void stopForeground(Service service, int i2) {
        Api24Impl.stopForeground(service, i2);
    }
}
