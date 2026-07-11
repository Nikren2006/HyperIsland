package miui.systemui.devicecenter.devices;

import I0.m;
import I0.q;
import I0.u;
import android.util.Log;
import com.miui.circulate.device.api.Constant;
import com.miui.circulate.device.api.DeviceInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceSortsProxy {
    public static final Companion Companion = new Companion(null);
    public static final String tag = "DeviceSortsProxy";
    private final List<DeviceInfo> cache;
    private final HashMap<String, Integer> devicePriorityMap;
    private int priorityCounter;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecenter.devices.DeviceSortsProxy$doSort$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function2 {
        public AnonymousClass1() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Integer invoke(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
            String deviceType;
            String deviceType2;
            int nearbyPriority = 0;
            int nearbyPriority2 = (deviceInfo == null || (deviceType2 = deviceInfo.getDeviceType()) == null) ? 0 : DeviceSortsProxy.this.toNearbyPriority(deviceType2);
            if (deviceInfo2 != null && (deviceType = deviceInfo2.getDeviceType()) != null) {
                nearbyPriority = DeviceSortsProxy.this.toNearbyPriority(deviceType);
            }
            return Integer.valueOf(nearbyPriority2 - nearbyPriority);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecenter.devices.DeviceSortsProxy$doSort$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function2 {
        public AnonymousClass2() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Integer invoke(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
            String deviceType;
            String deviceType2;
            int nearbyPriority = 0;
            int nearbyPriority2 = (deviceInfo == null || (deviceType2 = deviceInfo.getDeviceType()) == null) ? 0 : DeviceSortsProxy.this.toNearbyPriority(deviceType2);
            if (deviceInfo2 != null && (deviceType = deviceInfo2.getDeviceType()) != null) {
                nearbyPriority = DeviceSortsProxy.this.toNearbyPriority(deviceType);
            }
            return Integer.valueOf(nearbyPriority2 - nearbyPriority);
        }
    }

    public DeviceSortsProxy() {
        this.priorityCounter = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for (String str : m.j(Constant.DeviceType.CAMERA, Constant.DeviceType.CAMERAGLASSES, Constant.DeviceType.AUDIOGLASSES, "headset", Constant.DeviceType.GLASSES, Constant.DeviceType.BLUETOOTH, Constant.DeviceType.BLUETOOTH_CAR, Constant.DeviceType.THIRD_HEADSET, Constant.DeviceType.THIRD_HEARING_AID, Constant.DeviceType.THIRD_WATCH, Constant.DeviceType.THIRD_CAR_KIT, Constant.DeviceType.THIRD_SPEAKER, Constant.DeviceType.THIRD_OTHER, Constant.DeviceType.ANDROID_PHONE, Constant.DeviceType.ANDROID_PAD, Constant.DeviceType.WINDOWS_PC, Constant.DeviceType.ANDROID_TV, "Car", "audio_group", Constant.DeviceType.AUDIO_STEREO, Constant.DeviceType.SCREEN_SOUND, Constant.DeviceType.SOUND, Constant.DeviceType.WATCH, Constant.DeviceType.BAND)) {
            int i2 = this.priorityCounter;
            this.priorityCounter = i2 + 1;
            map.put(str, Integer.valueOf(i2));
        }
        this.devicePriorityMap = map;
        List<DeviceInfo> listSynchronizedList = Collections.synchronizedList(new ArrayList());
        n.f(listSynchronizedList, "synchronizedList(...)");
        this.cache = listSynchronizedList;
    }

    private final void doSort(ArrayList<DeviceInfo> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        DeviceInfo deviceInfo = null;
        for (DeviceInfo deviceInfo2 : arrayList) {
            if (n.c(deviceInfo2.getDeviceType(), Constant.DeviceType.CAMERA)) {
                deviceInfo = deviceInfo2;
            } else {
                n.d(deviceInfo2);
                if (DeviceSortsProxyKt.isSyncDevice(deviceInfo2)) {
                    arrayList2.add(deviceInfo2);
                } else if (DeviceSortsProxyKt.isAudioCastDevice(deviceInfo2)) {
                    arrayList3.add(deviceInfo2);
                } else {
                    arrayList4.add(deviceInfo2);
                }
            }
        }
        final AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        q.r(arrayList2, new Comparator() { // from class: miui.systemui.devicecenter.devices.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DeviceSortsProxy.doSort$lambda$3(anonymousClass1, obj, obj2);
            }
        });
        final AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        q.r(arrayList3, new Comparator() { // from class: miui.systemui.devicecenter.devices.b
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DeviceSortsProxy.doSort$lambda$4(anonymousClass2, obj, obj2);
            }
        });
        arrayList.clear();
        if (deviceInfo != null) {
            arrayList.add(deviceInfo);
        }
        arrayList.addAll(arrayList2);
        arrayList.addAll(arrayList3);
        arrayList.addAll(arrayList4);
        Log.i(tag, "doSort, sync devices size: " + arrayList2.size() + ", audio cast devices size: " + arrayList3.size() + ", left devices size: " + arrayList4.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int doSort$lambda$3(Function2 tmp0, Object obj, Object obj2) {
        n.g(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(obj, obj2)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int doSort$lambda$4(Function2 tmp0, Object obj, Object obj2) {
        n.g(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(obj, obj2)).intValue();
    }

    private final boolean sameDevices(ArrayList<DeviceInfo> arrayList, List<DeviceInfo> list) {
        Set setN0 = u.n0(list);
        for (DeviceInfo deviceInfo : list) {
            Iterator<DeviceInfo> it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    DeviceInfo next = it.next();
                    n.d(next);
                    if (DeviceSortsProxyKt.compare(next, deviceInfo)) {
                        setN0.remove(deviceInfo);
                        break;
                    }
                }
            }
        }
        return setN0.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int toNearbyPriority(String str) {
        Integer num = this.devicePriorityMap.get(str);
        if (num == null) {
            return Integer.MAX_VALUE;
        }
        return num.intValue();
    }

    public final void sortDevice(ArrayList<DeviceInfo> deviceList, boolean z2) {
        n.g(deviceList, "deviceList");
        if (z2) {
            Log.d(tag, "sortDevice, need sort, cause force");
            doSort(deviceList);
        } else if (this.cache.size() != deviceList.size()) {
            Log.d(tag, "sortDevice, need sort, cause cache size not same");
            doSort(deviceList);
        } else if (sameDevices(deviceList, this.cache)) {
            Log.d(tag, "sortDevice, lite sort");
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(deviceList);
            deviceList.clear();
            for (DeviceInfo deviceInfo : this.cache) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        DeviceInfo deviceInfo2 = (DeviceInfo) it.next();
                        n.d(deviceInfo2);
                        if (DeviceSortsProxyKt.compare(deviceInfo, deviceInfo2)) {
                            deviceList.add(deviceInfo2);
                            break;
                        }
                    }
                }
            }
        } else {
            Log.d(tag, "sortDevice, need sort, cause not same");
            doSort(deviceList);
        }
        this.cache.clear();
        this.cache.addAll(deviceList);
    }
}
