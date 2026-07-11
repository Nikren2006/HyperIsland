package miui.systemui.controlcenter.panel.main.devicecenter;

import I0.m;
import U.d;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceItem;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;
import miui.systemui.devicecenter.track.DeviceFoundTimeRecord;
import miui.systemui.devicecenter.track.TrackHelper;
import miui.systemui.devicecontrols.eventtracking.DeviceControlsEventTracker;
import org.json.JSONArray;
import org.json.JSONObject;
import systemui.plugin.eventtracking.events.DeviceCenterCardClickMijiaEvent;
import systemui.plugin.eventtracking.events.DeviceCenterCardClickOtherEvent;
import systemui.plugin.eventtracking.events.DeviceCenterCardClickTVEvent;
import systemui.plugin.eventtracking.events.DeviceCenterCardExposeMijiaEvent;
import systemui.plugin.eventtracking.events.DeviceCenterCardExposeOtherEvent;
import systemui.plugin.eventtracking.events.DeviceCenterCardExposeTVEvent;
import systemui.plugin.eventtracking.events.DeviceCenterDeviceFoundDuration;
import systemui.plugin.eventtracking.events.DeviceCenterDeviceFoundDurationEvent;
import systemui.plugin.eventtracking.events.DeviceCenterEventsKt;
import systemui.plugin.eventtracking.events.DeviceCenterExposeEvent;
import systemui.plugin.eventtracking.events.SecondaryPageClickEvent;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterTrackHelper {
    public static final String CLICK_CONTENT_HOTSPOT = "热区";
    public static final String CLICK_CONTENT_MORE_DEVICE = "更多设备";
    public static final DeviceCenterTrackHelper INSTANCE = new DeviceCenterTrackHelper();
    public static final String TAG = "DeviceCenterTrackHelper";

    private DeviceCenterTrackHelper() {
    }

    private final int reformDeviceSum(int i2, HashMap<String, Integer> map) {
        if (map.containsKey(TrackHelper.TV_GROUP)) {
            n.d(map.get(TrackHelper.TV_GROUP));
            i2 += r1.intValue() - 1;
        }
        if (!map.containsKey(TrackHelper.SPEAKER_GROUP)) {
            return i2;
        }
        n.d(map.get(TrackHelper.SPEAKER_GROUP));
        return i2 + (r1.intValue() - 1);
    }

    private final void refromComposedDeviceNum(HashMap<String, Integer> map, List<? extends DeviceItem> list) {
        if (map.containsKey(TrackHelper.TV_GROUP)) {
            DeviceInfoWrapper deviceInfoWrapperRefromComposedDeviceNum$findComposeDeviceTv = refromComposedDeviceNum$findComposeDeviceTv(list);
            map.put(TrackHelper.TV_GROUP, Integer.valueOf(deviceInfoWrapperRefromComposedDeviceNum$findComposeDeviceTv != null ? deviceInfoWrapperRefromComposedDeviceNum$findComposeDeviceTv.getComposeDeviceNumber() : 0));
        }
        if (map.containsKey(TrackHelper.SPEAKER_GROUP)) {
            DeviceInfoWrapper deviceInfoWrapperRefromComposedDeviceNum$findComposeDeviceSpeaker = refromComposedDeviceNum$findComposeDeviceSpeaker(list);
            map.put(TrackHelper.SPEAKER_GROUP, Integer.valueOf(deviceInfoWrapperRefromComposedDeviceNum$findComposeDeviceSpeaker != null ? deviceInfoWrapperRefromComposedDeviceNum$findComposeDeviceSpeaker.getComposeDeviceNumber() : 0));
        }
    }

    private static final DeviceInfoWrapper refromComposedDeviceNum$findComposeDeviceSpeaker(List<? extends DeviceItem> list) {
        Object next;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof DeviceItem.DeviceInfoItem) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            DeviceInfoWrapper deviceInfo = ((DeviceItem.DeviceInfoItem) next).getDeviceInfo();
            if (deviceInfo != null && deviceInfo.isCOmposeSpeaker()) {
                break;
            }
        }
        DeviceItem.DeviceInfoItem deviceInfoItem = (DeviceItem.DeviceInfoItem) next;
        if (deviceInfoItem != null) {
            return deviceInfoItem.getDeviceInfo();
        }
        return null;
    }

    private static final DeviceInfoWrapper refromComposedDeviceNum$findComposeDeviceTv(List<? extends DeviceItem> list) {
        Object next;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof DeviceItem.DeviceInfoItem) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            DeviceInfoWrapper deviceInfo = ((DeviceItem.DeviceInfoItem) next).getDeviceInfo();
            if (deviceInfo != null && deviceInfo.isComposeTv()) {
                break;
            }
        }
        DeviceItem.DeviceInfoItem deviceInfoItem = (DeviceItem.DeviceInfoItem) next;
        if (deviceInfoItem != null) {
            return deviceInfoItem.getDeviceInfo();
        }
        return null;
    }

    public final void trackDeviceCard(List<? extends DeviceItem> deviceItems) {
        DeviceInfoWrapper deviceInfo;
        n.g(deviceItems, "deviceItems");
        try {
            int i2 = 0;
            for (Object obj : deviceItems) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    m.n();
                }
                DeviceItem deviceItem = (DeviceItem) obj;
                if ((deviceItem instanceof DeviceItem.DeviceInfoItem) && (deviceInfo = ((DeviceItem.DeviceInfoItem) deviceItem).getDeviceInfo()) != null) {
                    TrackHelper trackHelper = TrackHelper.INSTANCE;
                    BaseEventTracker.Companion.get().track(trackHelper.isMijiaType(deviceInfo) ? new DeviceCenterCardExposeMijiaEvent(trackHelper.getClassification(deviceInfo), trackHelper.getDevice(deviceInfo), trackHelper.getRefId(deviceInfo), trackHelper.getDeviceModel(deviceInfo), trackHelper.getDeviceStatus(deviceInfo), trackHelper.getSmartHomeDeviceType(deviceInfo), String.valueOf(i2), null, null, 384, null) : trackHelper.isTvType(deviceInfo) ? new DeviceCenterCardExposeTVEvent(trackHelper.getClassification(deviceInfo), trackHelper.getDevice(deviceInfo), trackHelper.getRefId(deviceInfo), trackHelper.getDeviceModel(deviceInfo), trackHelper.getDeviceStatus(deviceInfo), trackHelper.getPlatFormNumber(deviceInfo), String.valueOf(i2), null, null, 384, null) : new DeviceCenterCardExposeOtherEvent(trackHelper.getClassification(deviceInfo), trackHelper.getDevice(deviceInfo), trackHelper.getRefId(deviceInfo), trackHelper.getDeviceModel(deviceInfo), trackHelper.getDeviceStatus(deviceInfo), String.valueOf(i2), null, null, 192, null));
                }
                i2 = i3;
            }
        } catch (Exception e2) {
            Log.i(TAG, "trackDeviceCard error " + e2.getMessage());
        }
    }

    public final void trackDeviceClick(DeviceInfoWrapper deviceInfo, int i2) {
        n.g(deviceInfo, "deviceInfo");
        try {
            TrackHelper trackHelper = TrackHelper.INSTANCE;
            BaseEventTracker.Companion.get().track(trackHelper.isMijiaType(deviceInfo) ? new DeviceCenterCardClickMijiaEvent(trackHelper.getClassification(deviceInfo), trackHelper.getDevice(deviceInfo), trackHelper.getRefId(deviceInfo), trackHelper.getDeviceModel(deviceInfo), trackHelper.getDeviceStatus(deviceInfo), trackHelper.getSmartHomeDeviceType(deviceInfo), String.valueOf(i2), null, null, 384, null) : trackHelper.isTvType(deviceInfo) ? new DeviceCenterCardClickTVEvent(trackHelper.getClassification(deviceInfo), trackHelper.getDevice(deviceInfo), trackHelper.getRefId(deviceInfo), trackHelper.getDeviceModel(deviceInfo), trackHelper.getDeviceStatus(deviceInfo), trackHelper.getPlatFormNumber(deviceInfo), String.valueOf(i2), null, null, 384, null) : new DeviceCenterCardClickOtherEvent(trackHelper.getClassification(deviceInfo), trackHelper.getDevice(deviceInfo), trackHelper.getRefId(deviceInfo), trackHelper.getDeviceModel(deviceInfo), trackHelper.getDeviceStatus(deviceInfo), String.valueOf(i2), null, null, 192, null));
        } catch (Exception e2) {
            Log.i(TAG, "trackDeviceClick error " + e2.getMessage());
        }
    }

    public final void trackDeviceFoundDuration(List<DeviceFoundTimeRecord> devicesCache, List<DeviceFoundTimeRecord> devices) {
        n.g(devicesCache, "devicesCache");
        n.g(devices, "devices");
        Log.i(TAG, "trackDurationStart ---");
        Log.i(TAG, "deviceCacheSize: " + devicesCache.size() + " , deviceSize: " + devices.size());
        if (devicesCache.isEmpty() && devices.isEmpty()) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        ArrayList arrayList = new ArrayList(I0.n.o(devicesCache, 10));
        for (DeviceFoundTimeRecord deviceFoundTimeRecord : devicesCache) {
            TrackHelper trackHelper = TrackHelper.INSTANCE;
            arrayList.add(new DeviceCenterDeviceFoundDuration(trackHelper.getClassification(deviceFoundTimeRecord.getDevice()), trackHelper.getDevice(deviceFoundTimeRecord.getDevice()), trackHelper.getRefId(deviceFoundTimeRecord.getDevice()), trackHelper.getDeviceModel(deviceFoundTimeRecord.getDevice()), DeviceCenterEventsKt.REPORT_METHOD_CACHE, deviceFoundTimeRecord.calculateDuration(), deviceFoundTimeRecord.isHeadInSameType()));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            jSONArray.put(new JSONObject(new d().s((DeviceCenterDeviceFoundDuration) it.next())));
        }
        ArrayList arrayList2 = new ArrayList(I0.n.o(devices, 10));
        for (DeviceFoundTimeRecord deviceFoundTimeRecord2 : devices) {
            TrackHelper trackHelper2 = TrackHelper.INSTANCE;
            arrayList2.add(new DeviceCenterDeviceFoundDuration(trackHelper2.getClassification(deviceFoundTimeRecord2.getDevice()), trackHelper2.getDevice(deviceFoundTimeRecord2.getDevice()), trackHelper2.getRefId(deviceFoundTimeRecord2.getDevice()), trackHelper2.getDeviceModel(deviceFoundTimeRecord2.getDevice()), DeviceCenterEventsKt.REPORT_METHOD_REALTIME, deviceFoundTimeRecord2.calculateDuration(), deviceFoundTimeRecord2.isHeadInSameType()));
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            jSONArray.put(new JSONObject(new d().s((DeviceCenterDeviceFoundDuration) it2.next())));
        }
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        String string = jSONArray.toString();
        n.f(string, "toString(...)");
        companion.track(new DeviceCenterDeviceFoundDurationEvent(string, null, null, 6, null));
    }

    public final void trackExposed(List<? extends DeviceItem> deviceItems) {
        String device;
        String device2;
        n.g(deviceItems, "deviceItems");
        try {
            int i2 = 0;
            int size = (deviceItems.size() == 1 && (deviceItems.get(0) instanceof DeviceItem.EmptyDeviceItem)) ? 0 : deviceItems.size() - 1;
            String str = DeviceControlsEventTracker.NOT_SKIP;
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            HashMap<String, Integer> map = new HashMap<>();
            String str2 = "无习惯";
            for (Object obj : deviceItems) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    m.n();
                }
                DeviceItem deviceItem = (DeviceItem) obj;
                if (deviceItem instanceof DeviceItem.DeviceInfoItem) {
                    DeviceInfoWrapper deviceInfo = ((DeviceItem.DeviceInfoItem) deviceItem).getDeviceInfo();
                    String str3 = "";
                    if (deviceInfo == null || (device = TrackHelper.INSTANCE.getDevice(deviceInfo)) == null) {
                        device = "";
                    }
                    Integer num = map.get(device);
                    int iValueOf = num == null ? 1 : Integer.valueOf(num.intValue() + 1);
                    DeviceInfoWrapper deviceInfo2 = ((DeviceItem.DeviceInfoItem) deviceItem).getDeviceInfo();
                    if (deviceInfo2 != null && (device2 = TrackHelper.INSTANCE.getDevice(deviceInfo2)) != null) {
                        str3 = device2;
                    }
                    map.put(str3, iValueOf);
                    str2 = "有习惯";
                    str = DeviceControlsEventTracker.IS_SKIP;
                }
                i2 = i3;
            }
            refromComposedDeviceNum(map, deviceItems);
            int iReformDeviceSum = reformDeviceSum(size, map);
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getKey().length() > 0) {
                    jSONObject.put("device", entry.getKey());
                    jSONObject.put("device_number", String.valueOf(entry.getValue().intValue()));
                    arrayList.add(jSONObject.toString());
                }
            }
            Log.d(TAG, "trackExposed deviceNumberStatus: " + arrayList + ", sum: " + iReformDeviceSum);
            BaseEventTracker.Companion.get().track(new DeviceCenterExposeEvent(arrayList, iReformDeviceSum, str, str2, null, null, 48, null));
        } catch (Exception e2) {
            Log.i(TAG, "trackExposed error " + e2.getMessage());
        }
    }

    public final void trackSecondaryPageClick(String clickContent) {
        n.g(clickContent, "clickContent");
        try {
            BaseEventTracker.Companion.get().track(new SecondaryPageClickEvent(clickContent, null, null, 6, null));
        } catch (Exception e2) {
            Log.i(TAG, "trackSecondaryPageClick error " + e2.getMessage());
        }
    }
}
