package miui.systemui.notification.stat;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.service.notification.StatusBarNotification;
import com.miui.circulate.device.api.Column;
import java.util.ArrayList;
import java.util.HashMap;
import miui.systemui.controlcenter.events.QsFlipEventsKt;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationStatForPkg implements MiuiNotificationStat {
    private static final int BUFFER_SIZE = 5;
    public static final int MSG_FLUSH = 1;
    private static final String STAT_ACTION = "miui.notification.action.NOTIFICATION_STAT";
    private static final long TIME_OUT = 5000;
    private final ArrayList<HashMap<String, Object>> buffers = new ArrayList<>(5);
    private final Handler mHandler = new Handler(Looper.myLooper()) { // from class: miui.systemui.notification.stat.NotificationStatForPkg.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            NotificationStatForPkg.this.checkFlush((Context) message.obj, true);
        }
    };
    private final String mPkg;

    public NotificationStatForPkg(String str) {
        this.mPkg = str;
    }

    public void check(Context context) {
        if (checkFlush(context, false) || this.mHandler.hasMessages(1)) {
            return;
        }
        this.mHandler.sendMessageDelayed(Message.obtain(this.mHandler, 1, context), TIME_OUT);
    }

    public boolean checkFlush(Context context, boolean z2) {
        if (this.buffers.size() < 5 && !z2) {
            return false;
        }
        send(context);
        this.buffers.clear();
        return true;
    }

    @Override // miui.systemui.notification.stat.MiuiNotificationStat
    public void handleVisibleEvent(Context context, StatusBarNotification statusBarNotification, HashMap<String, Object> map) {
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("event", "notification_visible");
        map2.put("source", map.get("source"));
        map2.put(QsFlipEventsKt.EVENT_KEY_FLIP_QS_INDEX, map.get(QsFlipEventsKt.EVENT_KEY_FLIP_QS_INDEX));
        map2.put("visualPosition", map.get("visualPosition"));
        map2.put("duration", map.get("duration"));
        map2.put("expanded", map.get("expanded"));
        map2.put("ts", Long.valueOf(System.currentTimeMillis()));
        map2.put(Column.ID, Integer.valueOf(statusBarNotification.getId()));
        map2.put("adId", Long.valueOf(statusBarNotification.getNotification().extras.getLong("adId", 0L)));
        this.buffers.add(map2);
        check(context);
    }

    public void send(Context context) {
        Intent intent = new Intent();
        intent.setPackage(this.mPkg);
        intent.setAction(STAT_ACTION);
        intent.putExtra("stat_info", this.buffers);
        context.sendBroadcast(intent);
    }
}
