package miui.systemui.devicecenter;

import H0.d;
import H0.e;
import H0.s;
import I0.q;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.annotation.MainThread;
import com.android.systemui.plugins.ActivityStarter;
import com.miui.circulate.device.api.BatteryInfo;
import com.miui.circulate.device.api.Constant;
import com.miui.circulate.device.api.DeviceControlManager;
import com.miui.circulate.device.api.DeviceInfo;
import com.miui.circulate.device.api.Icon;
import h0.C0401a;
import i0.InterfaceC0406d;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.devicecenter.DeviceCenterController;
import miui.systemui.devicecenter.DeviceCenterController$deviceCenterReceiver$2;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;
import miui.systemui.devicecenter.devices.DeviceSortsProxy;
import miui.systemui.devicecenter.track.DeviceCenterTracker;
import miui.systemui.devicecenter.track.DeviceFoundTimeRecord;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ControlsUtils;
import miui.systemui.util.MiLinkController;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceCenterController {
    public static final String KEY_QUICK_CONTROL = "quick_control_all_devices";
    private static final int MAX_DEVICE_COUNT = 7;
    public static final String TAG = "DeviceCenterController";
    private final Looper bgLooper;
    private final Context context;
    private boolean controlCenterIsStart;
    private final d deviceCenterManager$delegate;
    private final d deviceCenterReceiver$delegate;
    private final DeviceCenterTracker deviceCenterTracker;
    private final ArrayList<DeviceInfo> deviceList;
    private final ArrayList<DeviceCenterListener> listeners;
    private boolean listening;
    private boolean mListening;
    private final BroadcastReceiver mPackageReceiver;
    private final AtomicBoolean resortDeviceFlag;
    private final SharedPreferences sharedPreferences;
    private final DeviceSortsProxy sortsProxy;
    private final Executor uiExecutor;
    private final ArrayList<DeviceInfoWrapper> wrapperList;
    public static final Companion Companion = new Companion(null);
    private static HashMap<View, Runnable> actionMap = new HashMap<>();

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ Runnable getAction$default(Companion companion, View view, Function0 function0, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                function0 = DeviceCenterController$Companion$getAction$1.INSTANCE;
            }
            return companion.getAction(view, function0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void getAction$lambda$0(Function0 action) {
            n.g(action, "$action");
            action.invoke();
        }

        public final Runnable getAction(View view, final Function0 action) {
            n.g(view, "view");
            n.g(action, "action");
            Runnable runnable = getActionMap().get(view);
            if (runnable != null) {
                return runnable;
            }
            Runnable runnable2 = new Runnable() { // from class: miui.systemui.devicecenter.b
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceCenterController.Companion.getAction$lambda$0(action);
                }
            };
            getActionMap().put(view, runnable2);
            return runnable2;
        }

        public final HashMap<View, Runnable> getActionMap() {
            return DeviceCenterController.actionMap;
        }

        public final void removeAction(View view) {
            n.g(view, "view");
            getActionMap().remove(view);
        }

        public final void setActionMap(HashMap<View, Runnable> map) {
            n.g(map, "<set-?>");
            DeviceCenterController.actionMap = map;
        }

        private Companion() {
        }
    }

    public DeviceCenterController(@Plugin Context context, @Background Looper bgLooper, @Main Executor uiExecutor, @Main SharedPreferences sharedPreferences) {
        n.g(context, "context");
        n.g(bgLooper, "bgLooper");
        n.g(uiExecutor, "uiExecutor");
        n.g(sharedPreferences, "sharedPreferences");
        this.context = context;
        this.bgLooper = bgLooper;
        this.uiExecutor = uiExecutor;
        this.sharedPreferences = sharedPreferences;
        C0401a.f4478i.d(new InterfaceC0406d() { // from class: miui.systemui.devicecenter.DeviceCenterController.1
            /* JADX INFO: renamed from: onCardContentErrCallback, reason: collision with other method in class */
            public void m104onCardContentErrCallback(int i2) {
            }

            /* JADX INFO: renamed from: onCardCreatedCallback, reason: collision with other method in class */
            public void m105onCardCreatedCallback(int i2) {
            }

            @Override // i0.InterfaceC0406d
            public /* bridge */ /* synthetic */ s onCardHiddenCallback(int i2) {
                m106onCardHiddenCallback(i2);
                return s.f314a;
            }

            @Override // i0.InterfaceC0406d
            public s onCardShowAndChangedCallback(int i2) {
                return null;
            }

            @Override // i0.InterfaceC0406d
            public /* bridge */ /* synthetic */ s onCardContentErrCallback(int i2) {
                m104onCardContentErrCallback(i2);
                return s.f314a;
            }

            @Override // i0.InterfaceC0406d
            public /* bridge */ /* synthetic */ s onCardCreatedCallback(int i2) {
                m105onCardCreatedCallback(i2);
                return s.f314a;
            }

            /* JADX INFO: renamed from: onCardHiddenCallback, reason: collision with other method in class */
            public void m106onCardHiddenCallback(int i2) {
                Log.i(DeviceCenterController.TAG, "onCardHiddenCallback: ");
                if (DeviceCenterController.this.getControlCenterIsStart()) {
                    DeviceCenterController.this.startDiscover(0L);
                }
            }
        });
        this.deviceCenterManager$delegate = e.b(new DeviceCenterController$deviceCenterManager$2(this));
        this.deviceList = new ArrayList<>();
        this.wrapperList = new ArrayList<>();
        this.sortsProxy = new DeviceSortsProxy();
        this.deviceCenterTracker = new DeviceCenterTracker();
        this.deviceCenterReceiver$delegate = e.b(new DeviceCenterController$deviceCenterReceiver$2(this));
        this.mPackageReceiver = new BroadcastReceiver() { // from class: miui.systemui.devicecenter.DeviceCenterController$mPackageReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Uri data;
                n.g(context2, "context");
                n.g(intent, "intent");
                Log.d(DeviceCenterController.TAG, "onReceive   : " + intent.getAction());
                if (!n.c("android.intent.action.PACKAGE_REMOVED", intent.getAction()) || (data = intent.getData()) == null) {
                    return;
                }
                String schemeSpecificPart = data.getSchemeSpecificPart();
                DeviceCenterController deviceCenterController = this.this$0;
                n.d(schemeSpecificPart);
                deviceCenterController.refreshDeviceControlsSettingsData(schemeSpecificPart);
            }
        };
        this.resortDeviceFlag = new AtomicBoolean(false);
        this.listeners = new ArrayList<>();
    }

    private final long getDelayTime() {
        MiLinkController.Companion companion = MiLinkController.Companion;
        Log.i(TAG, "getDelayTime cameraUsageState: " + companion.getCameraUsageState());
        if (companion.getCameraUsageState() == 1) {
            Log.i(TAG, "cameraUsageState : CAMERA_USAGE_IN_USE");
            return TimeUnit.MILLISECONDS.toMillis(350L);
        }
        boolean z2 = false;
        try {
            z2 = Settings.Secure.getInt(this.context.getContentResolver(), KEY_QUICK_CONTROL, 0) == 1;
        } catch (Exception unused) {
        }
        Log.i(TAG, "isQuickControl " + z2);
        return TimeUnit.SECONDS.toMillis(z2 ? 1L : 2L);
    }

    private final DeviceControlManager getDeviceCenterManager() {
        return (DeviceControlManager) this.deviceCenterManager$delegate.getValue();
    }

    private final DeviceCenterController$deviceCenterReceiver$2.AnonymousClass1 getDeviceCenterReceiver() {
        return (DeviceCenterController$deviceCenterReceiver$2.AnonymousClass1) this.deviceCenterReceiver$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void handleDeviceListUpdate(final boolean z2) {
        ArrayList<DeviceInfo> arrayList = this.deviceList;
        if (arrayList.size() > 1) {
            q.r(arrayList, new Comparator() { // from class: miui.systemui.devicecenter.DeviceCenterController$handleDeviceListUpdate$lambda$4$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t3) {
                    return K0.a.a(Integer.valueOf(((DeviceInfo) t2).getPriority()), Integer.valueOf(((DeviceInfo) t3).getPriority()));
                }
            });
        }
        boolean z3 = this.resortDeviceFlag.get();
        Log.d(TAG, "resortFlag: " + z3);
        this.sortsProxy.sortDevice(arrayList, z3);
        if (z3) {
            this.resortDeviceFlag.set(false);
        }
        int size = arrayList.size();
        ArrayList<DeviceInfo> arrayListSubList = arrayList;
        if (size > 7) {
            arrayListSubList = arrayList.subList(0, 7);
        }
        n.f(arrayListSubList, "let(...)");
        final ArrayList arrayList2 = new ArrayList(I0.n.o(arrayListSubList, 10));
        for (DeviceInfo deviceInfo : arrayListSubList) {
            DeviceInfoWrapper.Companion companion = DeviceInfoWrapper.Companion;
            n.d(deviceInfo);
            arrayList2.add(companion.create(deviceInfo, this.context));
        }
        this.uiExecutor.execute(new Runnable() { // from class: miui.systemui.devicecenter.a
            @Override // java.lang.Runnable
            public final void run() {
                DeviceCenterController.handleDeviceListUpdate$lambda$8(this.f5512a, arrayList2, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleDeviceListUpdate$lambda$8(DeviceCenterController this$0, List list, boolean z2) {
        n.g(this$0, "this$0");
        n.g(list, "$list");
        this$0.wrapperList.clear();
        this$0.wrapperList.addAll(list);
        Iterator<T> it = this$0.listeners.iterator();
        while (it.hasNext()) {
            ((DeviceCenterListener) it.next()).onDeviceListChanged(this$0.wrapperList);
        }
        this$0.deviceCenterTracker.onDeviceFound(this$0.wrapperList, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshDeviceControlsSettingsData(String str) {
        Log.d(TAG, "refreshDeviceControlsSettingsData packageName:" + str);
        if (TextUtils.equals(Settings.Secure.getString(this.context.getContentResolver(), ControlsUtils.SETTING_KEY_CONTROL_CENTER_DEVICE_CONTROL), str)) {
            Settings.Secure.putString(this.context.getContentResolver(), ControlsUtils.SETTING_KEY_CONTROL_CENTER_DEVICE_CONTROL, ControlsUtils.MI_SMART_HUB_ACTION);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateDeviceList(List<DeviceInfo> list, boolean z2) {
        Log.i(TAG, "onDeviceListChange size = " + list.size());
        StringBuilder sb = new StringBuilder();
        for (DeviceInfo deviceInfo : list) {
            String title = deviceInfo.getTitle();
            String deviceType = deviceInfo.getDeviceType();
            Icon icon = deviceInfo.getIcon();
            String id = deviceInfo.getId();
            int state = deviceInfo.getState();
            BatteryInfo battery = deviceInfo.getBattery();
            sb.append("title: " + title + " type = " + deviceType + " icon = " + icon + " id = " + id + " state = " + state + " battery = " + (battery != null ? battery.flat() : null) + " \n");
        }
        Log.i(TAG, "list content: " + ((Object) sb));
        this.deviceList.clear();
        this.deviceList.addAll(list);
        handleDeviceListUpdate(z2);
    }

    public static /* synthetic */ void updateDeviceList$default(DeviceCenterController deviceCenterController, List list, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        deviceCenterController.updateDeviceList(list, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSingleInfo(ArrayList<DeviceInfo> arrayList, DeviceInfo deviceInfo) {
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (n.c(arrayList.get(i2).getId(), deviceInfo.getId())) {
                break;
            } else {
                i2++;
            }
        }
        Log.i(TAG, "replace index = " + i2 + "  list size = " + arrayList.size());
        if (i2 == -1) {
            arrayList.add(deviceInfo);
        } else {
            if (i2 < 0 || i2 >= arrayList.size()) {
                return;
            }
            arrayList.remove(i2);
            arrayList.add(i2, deviceInfo);
        }
    }

    @MainThread
    public final void addListener(DeviceCenterListener listener) {
        n.g(listener, "listener");
        this.listeners.add(listener);
        this.resortDeviceFlag.compareAndSet(false, true);
        if (this.listeners.size() > 0) {
            setListening(true);
        }
    }

    public final void deviceClick(DeviceInfoWrapper deviceInfo, View view, int i2, int i3, ActivityStarter activityStarter) {
        n.g(deviceInfo, "deviceInfo");
        n.g(view, "view");
        n.g(activityStarter, "activityStarter");
        if (isMijiaIotControl(deviceInfo.getType())) {
            CommonUtils.setBackgroundResourceEx$default(CommonUtils.INSTANCE, view, i2, false, 2, null);
            Runnable action = Companion.getAction(view, new DeviceCenterController$deviceClick$action$1(view, i3));
            view.removeCallbacks(action);
            view.postDelayed(action, 2000L);
        } else {
            stopDiscover();
        }
        deviceInfo.performClicked(this.context);
    }

    public final void exitCard() {
        C0401a.E(C0401a.f4478i, 0, 1, null);
    }

    public final boolean getControlCenterIsStart() {
        return this.controlCenterIsStart;
    }

    public final DeviceCenterTracker getDeviceCenterTracker() {
        return this.deviceCenterTracker;
    }

    public final List<DeviceFoundTimeRecord> getDevicesCacheTrackData() {
        return this.deviceCenterTracker.getDevicesFromCache();
    }

    public final List<DeviceFoundTimeRecord> getDevicesTrackData() {
        return this.deviceCenterTracker.getDevices();
    }

    public final boolean getListening() {
        return this.listening;
    }

    public final boolean isMijiaIotControl(String type) {
        n.g(type, "type");
        if (type.length() == 0) {
            return false;
        }
        return TextUtils.equals(type, Constant.DeviceType.MIJIA_IOT_CONTROL);
    }

    @MainThread
    public final void removeListener(DeviceCenterListener listener) {
        n.g(listener, "listener");
        this.listeners.remove(listener);
        this.resortDeviceFlag.compareAndSet(true, false);
        if (this.listeners.size() == 0) {
            setListening(false);
        }
    }

    public final void setControlCenterIsStart(boolean z2) {
        this.controlCenterIsStart = z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setListening(boolean r8) {
        /*
            r7 = this;
            boolean r0 = r7.listening
            if (r0 != r8) goto L5
            return
        L5:
            r7.listening = r8
            java.lang.String r0 = "DeviceCenterController"
            if (r8 == 0) goto L7c
            android.content.SharedPreferences r8 = r7.sharedPreferences
            java.lang.String r1 = ""
            java.lang.String r2 = "last_scan_data"
            java.lang.String r8 = r8.getString(r2, r1)
            java.time.LocalDate r1 = java.time.LocalDate.now()
            r3 = 0
            if (r8 == 0) goto L28
            f1.e r4 = new f1.e
            java.lang.String r5 = "^\\d{4}-\\d{2}-\\d{2}$"
            r4.<init>(r5)
            boolean r4 = r4.a(r8)
            goto L29
        L28:
            r4 = r3
        L29:
            r5 = 1
            if (r4 == 0) goto L49
            java.time.LocalDate r8 = java.time.LocalDate.parse(r8)
            android.content.SharedPreferences r4 = r7.sharedPreferences
            android.content.SharedPreferences$Editor r4 = r4.edit()
            java.lang.String r6 = r1.toString()
            android.content.SharedPreferences$Editor r2 = r4.putString(r2, r6)
            r2.apply()
            boolean r8 = r8.isEqual(r1)
            if (r8 != 0) goto L5b
        L47:
            r3 = r5
            goto L5b
        L49:
            android.content.SharedPreferences r8 = r7.sharedPreferences
            android.content.SharedPreferences$Editor r8 = r8.edit()
            java.lang.String r1 = r1.toString()
            android.content.SharedPreferences$Editor r8 = r8.putString(r2, r1)
            r8.apply()
            goto L47
        L5b:
            if (r3 == 0) goto L60
            r1 = 0
            goto L64
        L60:
            long r1 = r7.getDelayTime()
        L64:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r3 = "deviceCenterManager start discover delayTime = "
            r8.append(r3)
            r8.append(r1)
            java.lang.String r8 = r8.toString()
            android.util.Log.i(r0, r8)
            r7.startDiscover(r1)
            goto L81
        L7c:
            java.lang.String r7 = "listen is null"
            android.util.Log.i(r0, r7)
        L81:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.devicecenter.DeviceCenterController.setListening(boolean):void");
    }

    public final void setPackageChangeListening(boolean z2) {
        if (this.mListening == z2) {
            return;
        }
        this.mListening = z2;
        if (!z2) {
            this.context.unregisterReceiver(this.mPackageReceiver);
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        this.context.registerReceiver(this.mPackageReceiver, intentFilter, 2);
    }

    public final void startDiscover(long j2) {
        Log.i(TAG, "startDiscover delay:" + j2);
        this.deviceCenterTracker.recordStartSearchTime(j2);
        getDeviceCenterManager().start(j2, getDeviceCenterReceiver());
    }

    public final void stopDiscover() {
        Log.i(TAG, "stopDiscover");
        getDeviceCenterManager().stop(getDeviceCenterReceiver());
    }
}
