package t0;

import android.text.TextUtils;
import com.xiaomi.cast.api.DeviceInfo;
import java.util.Iterator;
import java.util.List;
import s0.h;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d {
    public static h a(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
        h hVar = new h(deviceInfo2);
        boolean zEquals = TextUtils.equals(deviceInfo.getName(), deviceInfo2.getName());
        boolean z2 = deviceInfo.getConnectState() == deviceInfo2.getConnectState();
        boolean z3 = deviceInfo.getVolume() == deviceInfo2.getVolume();
        hVar.d(1, !zEquals);
        hVar.d(2, !z2);
        hVar.d(4, !z3);
        return hVar;
    }

    public static void b(List list, List list2, List list3, List list4, List list5) {
        if (list.isEmpty() && list2.isEmpty()) {
            return;
        }
        if (list.isEmpty()) {
            list3.addAll(list2);
            return;
        }
        if (list2.isEmpty()) {
            list4.addAll(list);
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DeviceInfo deviceInfo = (DeviceInfo) it.next();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                DeviceInfo deviceInfo2 = (DeviceInfo) it2.next();
                if (deviceInfo == null || deviceInfo2 == null) {
                    z0.e.c("GoogleCastDiffUtils", "oldDevice," + deviceInfo + ",newDevice:" + deviceInfo2);
                } else if (TextUtils.equals(deviceInfo.getId(), deviceInfo2.getId())) {
                    h hVarA = a(deviceInfo, deviceInfo2);
                    if (hVarA.c()) {
                        list5.add(hVarA);
                    }
                }
            }
        }
        Iterator it3 = list.iterator();
        while (it3.hasNext()) {
            DeviceInfo deviceInfo3 = (DeviceInfo) it3.next();
            if (c(list2, deviceInfo3)) {
                list4.add(deviceInfo3);
            }
        }
        Iterator it4 = list2.iterator();
        while (it4.hasNext()) {
            DeviceInfo deviceInfo4 = (DeviceInfo) it4.next();
            if (c(list, deviceInfo4)) {
                list3.add(deviceInfo4);
            }
        }
    }

    public static boolean c(List list, DeviceInfo deviceInfo) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(((DeviceInfo) it.next()).getId(), deviceInfo.getId())) {
                return false;
            }
        }
        return true;
    }

    public static b d(List list, DeviceInfo deviceInfo) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (TextUtils.equals(bVar.j(), deviceInfo.getId())) {
                return bVar;
            }
        }
        return null;
    }
}
