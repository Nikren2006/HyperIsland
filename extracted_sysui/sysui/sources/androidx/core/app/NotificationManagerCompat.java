package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.a;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;

/* JADX INFO: loaded from: classes.dex */
public final class NotificationManagerCompat {
    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    public static final int INTERRUPTION_FILTER_ALARMS = 4;
    public static final int INTERRUPTION_FILTER_ALL = 1;
    public static final int INTERRUPTION_FILTER_NONE = 3;
    public static final int INTERRUPTION_FILTER_PRIORITY = 2;
    public static final int INTERRUPTION_FILTER_UNKNOWN = 0;
    static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";

    @GuardedBy("sEnabledNotificationListenersLock")
    private static String sEnabledNotificationListeners;

    @GuardedBy("sLock")
    private static SideChannelManager sSideChannelManager;
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    private static final Object sEnabledNotificationListenersLock = new Object();

    @GuardedBy("sEnabledNotificationListenersLock")
    private static Set<String> sEnabledNotificationListenerPackages = new HashSet();
    private static final Object sLock = new Object();

    @RequiresApi(23)
    public static class Api23Impl {
        private Api23Impl() {
        }

        public static List<StatusBarNotification> getActiveNotifications(NotificationManager notificationManager) {
            StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
            return activeNotifications == null ? new ArrayList() : Arrays.asList(activeNotifications);
        }

        public static int getCurrentInterruptionFilter(NotificationManager notificationManager) {
            return notificationManager.getCurrentInterruptionFilter();
        }
    }

    @RequiresApi(24)
    public static class Api24Impl {
        private Api24Impl() {
        }

        public static boolean areNotificationsEnabled(NotificationManager notificationManager) {
            return notificationManager.areNotificationsEnabled();
        }

        public static int getImportance(NotificationManager notificationManager) {
            return notificationManager.getImportance();
        }
    }

    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        public static void createNotificationChannel(NotificationManager notificationManager, NotificationChannel notificationChannel) {
            notificationManager.createNotificationChannel(notificationChannel);
        }

        public static void createNotificationChannelGroup(NotificationManager notificationManager, NotificationChannelGroup notificationChannelGroup) {
            notificationManager.createNotificationChannelGroup(notificationChannelGroup);
        }

        public static void createNotificationChannelGroups(NotificationManager notificationManager, List<NotificationChannelGroup> list) {
            notificationManager.createNotificationChannelGroups(list);
        }

        public static void createNotificationChannels(NotificationManager notificationManager, List<NotificationChannel> list) {
            notificationManager.createNotificationChannels(list);
        }

        public static void deleteNotificationChannel(NotificationManager notificationManager, String str) {
            notificationManager.deleteNotificationChannel(str);
        }

        public static void deleteNotificationChannelGroup(NotificationManager notificationManager, String str) {
            notificationManager.deleteNotificationChannelGroup(str);
        }

        public static String getId(NotificationChannel notificationChannel) {
            return notificationChannel.getId();
        }

        public static NotificationChannel getNotificationChannel(NotificationManager notificationManager, String str) {
            return notificationManager.getNotificationChannel(str);
        }

        public static List<NotificationChannelGroup> getNotificationChannelGroups(NotificationManager notificationManager) {
            return notificationManager.getNotificationChannelGroups();
        }

        public static List<NotificationChannel> getNotificationChannels(NotificationManager notificationManager) {
            return notificationManager.getNotificationChannels();
        }

        public static String getId(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getId();
        }
    }

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static NotificationChannelGroup getNotificationChannelGroup(NotificationManager notificationManager, String str) {
            return notificationManager.getNotificationChannelGroup(str);
        }
    }

    @RequiresApi(30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static NotificationChannel getNotificationChannel(NotificationManager notificationManager, String str, String str2) {
            return notificationManager.getNotificationChannel(str, str2);
        }

        public static String getParentChannelId(NotificationChannel notificationChannel) {
            return notificationChannel.getParentChannelId();
        }
    }

    @RequiresApi(34)
    public static class Api34Impl {
        private Api34Impl() {
        }

        public static boolean canUseFullScreenIntent(NotificationManager notificationManager) {
            return notificationManager.canUseFullScreenIntent();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface InterruptionFilter {
    }

    public static class NotifyTask implements Task {
        final int id;
        final Notification notif;
        final String packageName;
        final String tag;

        public NotifyTask(String str, int i2, String str2, Notification notification) {
            this.packageName = str;
            this.id = i2;
            this.tag = str2;
            this.notif = notification;
        }

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void send(android.support.v4.app.a aVar) {
            aVar.notify(this.packageName, this.id, this.tag, this.notif);
        }

        public String toString() {
            return "NotifyTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + "]";
        }
    }

    public static class ServiceConnectedEvent {
        final ComponentName componentName;
        final IBinder iBinder;

        public ServiceConnectedEvent(ComponentName componentName, IBinder iBinder) {
            this.componentName = componentName;
            this.iBinder = iBinder;
        }
    }

    public static class SideChannelManager implements Handler.Callback, ServiceConnection {
        private static final int MSG_QUEUE_TASK = 0;
        private static final int MSG_RETRY_LISTENER_QUEUE = 3;
        private static final int MSG_SERVICE_CONNECTED = 1;
        private static final int MSG_SERVICE_DISCONNECTED = 2;
        private final Context mContext;
        private final Handler mHandler;
        private final HandlerThread mHandlerThread;
        private final Map<ComponentName, ListenerRecord> mRecordMap = new HashMap();
        private Set<String> mCachedEnabledPackages = new HashSet();

        public static class ListenerRecord {
            final ComponentName componentName;
            android.support.v4.app.a service;
            boolean bound = false;
            ArrayDeque<Task> taskQueue = new ArrayDeque<>();
            int retryCount = 0;

            public ListenerRecord(ComponentName componentName) {
                this.componentName = componentName;
            }
        }

        public SideChannelManager(Context context) {
            this.mContext = context;
            HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper(), this);
        }

        private boolean ensureServiceBound(ListenerRecord listenerRecord) {
            if (listenerRecord.bound) {
                return true;
            }
            boolean zBindService = this.mContext.bindService(new Intent(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL).setComponent(listenerRecord.componentName), this, 33);
            listenerRecord.bound = zBindService;
            if (zBindService) {
                listenerRecord.retryCount = 0;
            } else {
                Log.w(NotificationManagerCompat.TAG, "Unable to bind to listener " + listenerRecord.componentName);
                this.mContext.unbindService(this);
            }
            return listenerRecord.bound;
        }

        private void ensureServiceUnbound(ListenerRecord listenerRecord) {
            if (listenerRecord.bound) {
                this.mContext.unbindService(this);
                listenerRecord.bound = false;
            }
            listenerRecord.service = null;
        }

        private void handleQueueTask(Task task) {
            updateListenerMap();
            for (ListenerRecord listenerRecord : this.mRecordMap.values()) {
                listenerRecord.taskQueue.add(task);
                processListenerQueue(listenerRecord);
            }
        }

        private void handleRetryListenerQueue(ComponentName componentName) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                processListenerQueue(listenerRecord);
            }
        }

        private void handleServiceConnected(ComponentName componentName, IBinder iBinder) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                listenerRecord.service = a.AbstractBinderC0029a.asInterface(iBinder);
                listenerRecord.retryCount = 0;
                processListenerQueue(listenerRecord);
            }
        }

        private void handleServiceDisconnected(ComponentName componentName) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                ensureServiceUnbound(listenerRecord);
            }
        }

        private void processListenerQueue(ListenerRecord listenerRecord) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                Log.d(NotificationManagerCompat.TAG, "Processing component " + listenerRecord.componentName + ", " + listenerRecord.taskQueue.size() + " queued tasks");
            }
            if (listenerRecord.taskQueue.isEmpty()) {
                return;
            }
            if (!ensureServiceBound(listenerRecord) || listenerRecord.service == null) {
                scheduleListenerRetry(listenerRecord);
                return;
            }
            while (true) {
                Task taskPeek = listenerRecord.taskQueue.peek();
                if (taskPeek == null) {
                    break;
                }
                try {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        Log.d(NotificationManagerCompat.TAG, "Sending task " + taskPeek);
                    }
                    taskPeek.send(listenerRecord.service);
                    listenerRecord.taskQueue.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        Log.d(NotificationManagerCompat.TAG, "Remote service has died: " + listenerRecord.componentName);
                    }
                } catch (RemoteException e2) {
                    Log.w(NotificationManagerCompat.TAG, "RemoteException communicating with " + listenerRecord.componentName, e2);
                }
            }
            if (listenerRecord.taskQueue.isEmpty()) {
                return;
            }
            scheduleListenerRetry(listenerRecord);
        }

        private void scheduleListenerRetry(ListenerRecord listenerRecord) {
            if (this.mHandler.hasMessages(3, listenerRecord.componentName)) {
                return;
            }
            int i2 = listenerRecord.retryCount;
            int i3 = i2 + 1;
            listenerRecord.retryCount = i3;
            if (i3 <= 6) {
                int i4 = (1 << i2) * 1000;
                if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                    Log.d(NotificationManagerCompat.TAG, "Scheduling retry for " + i4 + " ms");
                }
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(3, listenerRecord.componentName), i4);
                return;
            }
            Log.w(NotificationManagerCompat.TAG, "Giving up on delivering " + listenerRecord.taskQueue.size() + " tasks to " + listenerRecord.componentName + " after " + listenerRecord.retryCount + " retries");
            listenerRecord.taskQueue.clear();
        }

        private void updateListenerMap() {
            Set<String> enabledListenerPackages = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
            if (enabledListenerPackages.equals(this.mCachedEnabledPackages)) {
                return;
            }
            this.mCachedEnabledPackages = enabledListenerPackages;
            List<ResolveInfo> listQueryIntentServices = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL), 0);
            HashSet<ComponentName> hashSet = new HashSet();
            for (ResolveInfo resolveInfo : listQueryIntentServices) {
                if (enabledListenerPackages.contains(resolveInfo.serviceInfo.packageName)) {
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        Log.w(NotificationManagerCompat.TAG, "Permission present on component " + componentName + ", not adding listener record.");
                    } else {
                        hashSet.add(componentName);
                    }
                }
            }
            for (ComponentName componentName2 : hashSet) {
                if (!this.mRecordMap.containsKey(componentName2)) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        Log.d(NotificationManagerCompat.TAG, "Adding listener record for " + componentName2);
                    }
                    this.mRecordMap.put(componentName2, new ListenerRecord(componentName2));
                }
            }
            Iterator<Map.Entry<ComponentName, ListenerRecord>> it = this.mRecordMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<ComponentName, ListenerRecord> next = it.next();
                if (!hashSet.contains(next.getKey())) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        Log.d(NotificationManagerCompat.TAG, "Removing listener record for " + next.getKey());
                    }
                    ensureServiceUnbound(next.getValue());
                    it.remove();
                }
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                handleQueueTask((Task) message.obj);
                return true;
            }
            if (i2 == 1) {
                ServiceConnectedEvent serviceConnectedEvent = (ServiceConnectedEvent) message.obj;
                handleServiceConnected(serviceConnectedEvent.componentName, serviceConnectedEvent.iBinder);
                return true;
            }
            if (i2 == 2) {
                handleServiceDisconnected((ComponentName) message.obj);
                return true;
            }
            if (i2 != 3) {
                return false;
            }
            handleRetryListenerQueue((ComponentName) message.obj);
            return true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                Log.d(NotificationManagerCompat.TAG, "Connected to service " + componentName);
            }
            this.mHandler.obtainMessage(1, new ServiceConnectedEvent(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                Log.d(NotificationManagerCompat.TAG, "Disconnected from service " + componentName);
            }
            this.mHandler.obtainMessage(2, componentName).sendToTarget();
        }

        public void queueTask(Task task) {
            this.mHandler.obtainMessage(0, task).sendToTarget();
        }
    }

    public interface Task {
        void send(android.support.v4.app.a aVar);
    }

    private NotificationManagerCompat(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
    }

    public static NotificationManagerCompat from(Context context) {
        return new NotificationManagerCompat(context);
    }

    public static Set<String> getEnabledListenerPackages(Context context) {
        Set<String> set;
        String string = Settings.Secure.getString(context.getContentResolver(), SETTING_ENABLED_NOTIFICATION_LISTENERS);
        synchronized (sEnabledNotificationListenersLock) {
            if (string != null) {
                try {
                    if (!string.equals(sEnabledNotificationListeners)) {
                        String[] strArrSplit = string.split(MethodCodeHelper.IDENTITY_INFO_SEPARATOR, -1);
                        HashSet hashSet = new HashSet(strArrSplit.length);
                        for (String str : strArrSplit) {
                            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                            if (componentNameUnflattenFromString != null) {
                                hashSet.add(componentNameUnflattenFromString.getPackageName());
                            }
                        }
                        sEnabledNotificationListenerPackages = hashSet;
                        sEnabledNotificationListeners = string;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            set = sEnabledNotificationListenerPackages;
        }
        return set;
    }

    private void pushSideChannelQueue(Task task) {
        synchronized (sLock) {
            try {
                if (sSideChannelManager == null) {
                    sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());
                }
                sSideChannelManager.queueTask(task);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static boolean useSideChannelForNotification(Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        return extras != null && extras.getBoolean(EXTRA_USE_SIDE_CHANNEL);
    }

    public boolean areNotificationsEnabled() {
        return Api24Impl.areNotificationsEnabled(this.mNotificationManager);
    }

    public boolean canUseFullScreenIntent() {
        return Api34Impl.canUseFullScreenIntent(this.mNotificationManager);
    }

    public void cancel(int i2) {
        cancel(null, i2);
    }

    public void cancelAll() {
        this.mNotificationManager.cancelAll();
    }

    public void createNotificationChannel(NotificationChannel notificationChannel) {
        Api26Impl.createNotificationChannel(this.mNotificationManager, notificationChannel);
    }

    public void createNotificationChannelGroup(NotificationChannelGroup notificationChannelGroup) {
        Api26Impl.createNotificationChannelGroup(this.mNotificationManager, notificationChannelGroup);
    }

    public void createNotificationChannelGroups(List<NotificationChannelGroup> list) {
        Api26Impl.createNotificationChannelGroups(this.mNotificationManager, list);
    }

    public void createNotificationChannelGroupsCompat(List<NotificationChannelGroupCompat> list) {
        if (list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<NotificationChannelGroupCompat> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getNotificationChannelGroup());
        }
        Api26Impl.createNotificationChannelGroups(this.mNotificationManager, arrayList);
    }

    public void createNotificationChannels(List<NotificationChannel> list) {
        Api26Impl.createNotificationChannels(this.mNotificationManager, list);
    }

    public void createNotificationChannelsCompat(List<NotificationChannelCompat> list) {
        if (list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<NotificationChannelCompat> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getNotificationChannel());
        }
        Api26Impl.createNotificationChannels(this.mNotificationManager, arrayList);
    }

    public void deleteNotificationChannel(String str) {
        Api26Impl.deleteNotificationChannel(this.mNotificationManager, str);
    }

    public void deleteNotificationChannelGroup(String str) {
        Api26Impl.deleteNotificationChannelGroup(this.mNotificationManager, str);
    }

    public void deleteUnlistedNotificationChannels(Collection<String> collection) {
        for (NotificationChannel notificationChannel : Api26Impl.getNotificationChannels(this.mNotificationManager)) {
            if (!collection.contains(Api26Impl.getId(notificationChannel)) && !collection.contains(Api30Impl.getParentChannelId(notificationChannel))) {
                Api26Impl.deleteNotificationChannel(this.mNotificationManager, Api26Impl.getId(notificationChannel));
            }
        }
    }

    public List<StatusBarNotification> getActiveNotifications() {
        return Api23Impl.getActiveNotifications(this.mNotificationManager);
    }

    public int getCurrentInterruptionFilter() {
        return Api23Impl.getCurrentInterruptionFilter(this.mNotificationManager);
    }

    public int getImportance() {
        return Api24Impl.getImportance(this.mNotificationManager);
    }

    public NotificationChannel getNotificationChannel(String str) {
        return Api26Impl.getNotificationChannel(this.mNotificationManager, str);
    }

    public NotificationChannelCompat getNotificationChannelCompat(String str) {
        NotificationChannel notificationChannel = getNotificationChannel(str);
        if (notificationChannel != null) {
            return new NotificationChannelCompat(notificationChannel);
        }
        return null;
    }

    public NotificationChannelGroup getNotificationChannelGroup(String str) {
        return Api28Impl.getNotificationChannelGroup(this.mNotificationManager, str);
    }

    public NotificationChannelGroupCompat getNotificationChannelGroupCompat(String str) {
        NotificationChannelGroup notificationChannelGroup = getNotificationChannelGroup(str);
        if (notificationChannelGroup != null) {
            return new NotificationChannelGroupCompat(notificationChannelGroup);
        }
        return null;
    }

    public List<NotificationChannelGroup> getNotificationChannelGroups() {
        return Api26Impl.getNotificationChannelGroups(this.mNotificationManager);
    }

    public List<NotificationChannelGroupCompat> getNotificationChannelGroupsCompat() {
        List<NotificationChannelGroup> notificationChannelGroups = getNotificationChannelGroups();
        if (notificationChannelGroups.isEmpty()) {
            return Collections.emptyList();
        }
        Collections.emptyList();
        ArrayList arrayList = new ArrayList(notificationChannelGroups.size());
        Iterator<NotificationChannelGroup> it = notificationChannelGroups.iterator();
        while (it.hasNext()) {
            arrayList.add(new NotificationChannelGroupCompat(it.next()));
        }
        return arrayList;
    }

    public List<NotificationChannel> getNotificationChannels() {
        return Api26Impl.getNotificationChannels(this.mNotificationManager);
    }

    public List<NotificationChannelCompat> getNotificationChannelsCompat() {
        List<NotificationChannel> notificationChannels = getNotificationChannels();
        if (notificationChannels.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(notificationChannels.size());
        Iterator<NotificationChannel> it = notificationChannels.iterator();
        while (it.hasNext()) {
            arrayList.add(new NotificationChannelCompat(it.next()));
        }
        return arrayList;
    }

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    public void notify(int i2, Notification notification) {
        notify(null, i2, notification);
    }

    public void cancel(String str, int i2) {
        this.mNotificationManager.cancel(str, i2);
    }

    public void createNotificationChannel(NotificationChannelCompat notificationChannelCompat) {
        createNotificationChannel(notificationChannelCompat.getNotificationChannel());
    }

    public void createNotificationChannelGroup(NotificationChannelGroupCompat notificationChannelGroupCompat) {
        createNotificationChannelGroup(notificationChannelGroupCompat.getNotificationChannelGroup());
    }

    public NotificationChannel getNotificationChannel(String str, String str2) {
        return Api30Impl.getNotificationChannel(this.mNotificationManager, str, str2);
    }

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    public void notify(String str, int i2, Notification notification) {
        if (!useSideChannelForNotification(notification)) {
            this.mNotificationManager.notify(str, i2, notification);
        } else {
            pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), i2, str, notification));
            this.mNotificationManager.cancel(str, i2);
        }
    }

    public NotificationChannelCompat getNotificationChannelCompat(String str, String str2) {
        NotificationChannel notificationChannel = getNotificationChannel(str, str2);
        if (notificationChannel != null) {
            return new NotificationChannelCompat(notificationChannel);
        }
        return null;
    }

    public static class NotificationWithIdAndTag {
        final int mId;
        Notification mNotification;
        final String mTag;

        public NotificationWithIdAndTag(String str, int i2, Notification notification) {
            this.mTag = str;
            this.mId = i2;
            this.mNotification = notification;
        }

        public NotificationWithIdAndTag(int i2, Notification notification) {
            this(null, i2, notification);
        }
    }

    @VisibleForTesting
    public NotificationManagerCompat(NotificationManager notificationManager, Context context) {
        this.mContext = context;
        this.mNotificationManager = notificationManager;
    }

    public static class CancelTask implements Task {
        final boolean all;
        final int id;
        final String packageName;
        final String tag;

        public CancelTask(String str) {
            this.packageName = str;
            this.id = 0;
            this.tag = null;
            this.all = true;
        }

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void send(android.support.v4.app.a aVar) {
            if (this.all) {
                aVar.cancelAll(this.packageName);
            } else {
                aVar.cancel(this.packageName, this.id, this.tag);
            }
        }

        public String toString() {
            return "CancelTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + ", all:" + this.all + "]";
        }

        public CancelTask(String str, int i2, String str2) {
            this.packageName = str;
            this.id = i2;
            this.tag = str2;
            this.all = false;
        }
    }

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    public void notify(List<NotificationWithIdAndTag> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            NotificationWithIdAndTag notificationWithIdAndTag = list.get(i2);
            notify(notificationWithIdAndTag.mTag, notificationWithIdAndTag.mId, notificationWithIdAndTag.mNotification);
        }
    }
}
