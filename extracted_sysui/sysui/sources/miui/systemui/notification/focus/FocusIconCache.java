package miui.systemui.notification.focus;

import H0.s;
import android.graphics.drawable.Icon;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class FocusIconCache {
    public static final FocusIconCache INSTANCE = new FocusIconCache();
    private static final Map<String, Map<String, Icon>> cache = new HashMap();

    private FocusIconCache() {
    }

    public final Icon get(String notificationKey, String str) {
        n.g(notificationKey, "notificationKey");
        Map<String, Map<String, Icon>> map = cache;
        synchronized (map) {
            Map<String, Icon> map2 = map.get(notificationKey);
            if (map2 == null) {
                return null;
            }
            return map2.get(str);
        }
    }

    public final void put(String notificationKey, String iconKey, Icon icon) {
        n.g(notificationKey, "notificationKey");
        n.g(iconKey, "iconKey");
        Map<String, Map<String, Icon>> map = cache;
        synchronized (map) {
            try {
                Map<String, Icon> map2 = map.get(notificationKey);
                if (map2 == null) {
                    map2 = new HashMap<>();
                    map.put(notificationKey, map2);
                }
                if (icon != null) {
                    map2.put(iconKey, icon);
                }
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void remove(String notificationKey) {
        n.g(notificationKey, "notificationKey");
        Map<String, Map<String, Icon>> map = cache;
        synchronized (map) {
            map.remove(notificationKey);
        }
    }
}
