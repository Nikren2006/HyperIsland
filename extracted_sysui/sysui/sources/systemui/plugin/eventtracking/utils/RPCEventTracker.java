package systemui.plugin.eventtracking.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.utils.RPCEventTracker;

/* JADX INFO: loaded from: classes5.dex */
public final class RPCEventTracker {
    public static final RPCEventTracker INSTANCE = new RPCEventTracker();
    private static final String TAG = "RPCEventTracker";
    private static final HashMap<String, EventRecord> mTrackingEvents = new HashMap<>();
    private static final Handler mHandler = new Handler(Looper.getMainLooper());

    public static final class EventRecord {
        private final WeakReference<Function3> callback;
        private final ArrayList<?> deviceList;
        private final long startTime;
        private final String tag;
        private final Runnable timeoutCallback;

        public EventRecord(String tag, long j2, WeakReference<Function3> callback, ArrayList<?> arrayList) {
            n.g(tag, "tag");
            n.g(callback, "callback");
            this.tag = tag;
            this.startTime = j2;
            this.callback = callback;
            this.deviceList = arrayList;
            this.timeoutCallback = new Runnable() { // from class: systemui.plugin.eventtracking.utils.a
                @Override // java.lang.Runnable
                public final void run() {
                    RPCEventTracker.EventRecord.timeoutCallback$lambda$1(this.f6485a);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void timeoutCallback$lambda$1(EventRecord this$0) {
            n.g(this$0, "this$0");
            Function3 function3 = this$0.callback.get();
            if (function3 != null) {
                Log.d(RPCEventTracker.INSTANCE.getTAG(), "run timeout callback: tag = " + this$0.tag);
                function3.invoke(Boolean.TRUE, Long.valueOf(System.currentTimeMillis() - this$0.startTime), this$0.deviceList);
            }
        }

        public final WeakReference<Function3> getCallback() {
            return this.callback;
        }

        public final ArrayList<?> getDeviceList() {
            return this.deviceList;
        }

        public final long getStartTime() {
            return this.startTime;
        }

        public final String getTag() {
            return this.tag;
        }

        public final Runnable getTimeoutCallback() {
            return this.timeoutCallback;
        }
    }

    private RPCEventTracker() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void start$default(RPCEventTracker rPCEventTracker, String str, long j2, ArrayList arrayList, Function3 function3, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            arrayList = null;
        }
        rPCEventTracker.start(str, j2, arrayList, function3);
    }

    public final void end(String tag) {
        n.g(tag, "tag");
        String str = TAG;
        Log.d(str, "end(): tag = " + tag);
        HashMap<String, EventRecord> map = mTrackingEvents;
        EventRecord eventRecord = map.get(tag);
        if (eventRecord != null) {
            mHandler.removeCallbacks(eventRecord.getTimeoutCallback());
            Function3 function3 = eventRecord.getCallback().get();
            if (function3 != null) {
                Log.d(str, "run end callback: tag = " + tag);
                function3.invoke(Boolean.FALSE, Long.valueOf(System.currentTimeMillis() - eventRecord.getStartTime()), eventRecord.getDeviceList());
            }
        }
        map.remove(tag);
    }

    public final Handler getMHandler() {
        return mHandler;
    }

    public final HashMap<String, EventRecord> getMTrackingEvents() {
        return mTrackingEvents;
    }

    public final String getTAG() {
        return TAG;
    }

    public final void start(String tag, long j2, ArrayList<?> arrayList, Function3 callback) {
        n.g(tag, "tag");
        n.g(callback, "callback");
        Log.d(TAG, "start(): tag = " + tag + ",timeoutMillis = " + j2);
        EventRecord eventRecord = new EventRecord(tag, System.currentTimeMillis(), new WeakReference(callback), arrayList);
        HashMap<String, EventRecord> map = mTrackingEvents;
        EventRecord eventRecord2 = map.get(tag);
        if (eventRecord2 != null) {
            mHandler.removeCallbacks(eventRecord2.getTimeoutCallback());
        }
        map.put(tag, eventRecord);
        mHandler.postDelayed(eventRecord.getTimeoutCallback(), j2);
    }
}
