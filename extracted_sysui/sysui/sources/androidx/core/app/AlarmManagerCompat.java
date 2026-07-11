package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class AlarmManagerCompat {

    @RequiresApi(21)
    public static class Api21Impl {
        private Api21Impl() {
        }

        public static AlarmManager.AlarmClockInfo createAlarmClockInfo(long j2, PendingIntent pendingIntent) {
            return new AlarmManager.AlarmClockInfo(j2, pendingIntent);
        }

        public static void setAlarmClock(AlarmManager alarmManager, Object obj, PendingIntent pendingIntent) {
            alarmManager.setAlarmClock((AlarmManager.AlarmClockInfo) obj, pendingIntent);
        }
    }

    @RequiresApi(23)
    public static class Api23Impl {
        private Api23Impl() {
        }

        public static void setAndAllowWhileIdle(AlarmManager alarmManager, int i2, long j2, PendingIntent pendingIntent) {
            alarmManager.setAndAllowWhileIdle(i2, j2, pendingIntent);
        }

        public static void setExactAndAllowWhileIdle(AlarmManager alarmManager, int i2, long j2, PendingIntent pendingIntent) {
            alarmManager.setExactAndAllowWhileIdle(i2, j2, pendingIntent);
        }
    }

    @RequiresApi(31)
    public static class Api31Impl {
        private Api31Impl() {
        }

        public static boolean canScheduleExactAlarms(AlarmManager alarmManager) {
            return alarmManager.canScheduleExactAlarms();
        }
    }

    private AlarmManagerCompat() {
    }

    public static boolean canScheduleExactAlarms(AlarmManager alarmManager) {
        return Api31Impl.canScheduleExactAlarms(alarmManager);
    }

    @SuppressLint({"MissingPermission"})
    public static void setAlarmClock(AlarmManager alarmManager, long j2, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        Api21Impl.setAlarmClock(alarmManager, Api21Impl.createAlarmClockInfo(j2, pendingIntent), pendingIntent2);
    }

    public static void setAndAllowWhileIdle(AlarmManager alarmManager, int i2, long j2, PendingIntent pendingIntent) {
        Api23Impl.setAndAllowWhileIdle(alarmManager, i2, j2, pendingIntent);
    }

    @ReplaceWith(expression = "alarmManager.setExact(type, triggerAtMillis, operation)")
    @Deprecated
    public static void setExact(AlarmManager alarmManager, int i2, long j2, PendingIntent pendingIntent) {
        alarmManager.setExact(i2, j2, pendingIntent);
    }

    public static void setExactAndAllowWhileIdle(AlarmManager alarmManager, int i2, long j2, PendingIntent pendingIntent) {
        Api23Impl.setExactAndAllowWhileIdle(alarmManager, i2, j2, pendingIntent);
    }
}
