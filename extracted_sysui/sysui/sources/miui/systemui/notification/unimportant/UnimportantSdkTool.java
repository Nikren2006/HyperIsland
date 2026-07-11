package miui.systemui.notification.unimportant;

import android.app.Notification;
import android.content.Context;
import android.service.notification.StatusBarNotification;
import androidx.core.app.NotificationCompat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import miui.systemui.Prefs;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class UnimportantSdkTool {
    private static final String EXTRA_SCORE_INFO = "score_info";
    private static final int FOLD_F_NOT_INIT = 303;
    private static final int FOLD_F_SDK = 307;
    private static final int FOLD_F_SDK_ERROR = 305;
    private static final int FOLD_T_SDK = 306;
    private static final String OLDEST_UPDATE_TIME = "oldest_update_time";
    private static final String PARAM_LOCAL_THRESHOLD = "localThreshold";
    private static final String PKG_CLICK_COUNT = "pkg_click_count";
    private static final String PKG_SHOW_COUNT = "pkg_show_count";
    private static final String RULE_DESC = "desc";
    private static final String RULE_PKG = "pkg";
    private static final String RULE_SCORE = "score";
    private static final String RULE_SEND_PKG = "sendPkg";
    private static final String RULE_TITLE = "title";
    private static final String TAG = "UnimportantSdkTool";
    private static final String TOTAL_CLICK_COUNT = "total_click_count";
    private static final String TOTAL_SHOW_COUNT = "total_show_count";
    private final long DAYS_TO_MILLIS;

    public static class UnimportantSdkToolInstance {
        private static final UnimportantSdkTool sInstance = new UnimportantSdkTool();

        private UnimportantSdkToolInstance() {
        }
    }

    private int getDigitalFormatDateToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return (calendar.get(1) * 10000) + ((calendar.get(2) + 1) * 100) + calendar.get(5);
    }

    public static UnimportantSdkTool getInstance() {
        return UnimportantSdkToolInstance.sInstance;
    }

    private long getOldestTime(Context context) {
        if (context == null) {
            return System.currentTimeMillis();
        }
        long j2 = Prefs.getNotif(context).getLong(OLDEST_UPDATE_TIME, 0L);
        if (j2 != 0) {
            return j2;
        }
        long oldestTimeCore = getOldestTimeCore();
        Prefs.getNotif(context).edit().putLong(OLDEST_UPDATE_TIME, oldestTimeCore).apply();
        return oldestTimeCore;
    }

    private long getOldestTimeCore() {
        int digitalFormatDateToday = getDigitalFormatDateToday();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j2 = this.DAYS_TO_MILLIS;
        return (jCurrentTimeMillis - (jCurrentTimeMillis % j2)) - (((long) digitalFormatDateToday) * j2);
    }

    private CharSequence resolveText(Notification notification) {
        CharSequence charSequence = notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT);
        if (charSequence == null) {
            charSequence = notification.extras.getCharSequence(NotificationCompat.EXTRA_BIG_TEXT);
        }
        return charSequence == null ? "" : charSequence;
    }

    private CharSequence resolveTitle(Notification notification) {
        CharSequence charSequence = notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE);
        if (charSequence == null) {
            charSequence = notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE_BIG);
        }
        return charSequence == null ? "" : charSequence;
    }

    public int fold(StatusBarNotification statusBarNotification, String str, Map<String, Integer> map, String str2) {
        return 303;
    }

    public void init(Context context) {
    }

    public void updatePushFilterLocalRules(List<JSONObject> list) {
    }

    private UnimportantSdkTool() {
        this.DAYS_TO_MILLIS = TimeUnit.DAYS.toMillis(1L);
    }
}
