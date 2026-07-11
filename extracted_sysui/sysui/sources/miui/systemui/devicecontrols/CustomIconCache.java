package miui.systemui.devicecontrols;

import H0.s;
import android.content.ComponentName;
import android.graphics.drawable.Icon;
import androidx.annotation.GuardedBy;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.n;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import systemui.plugin.eventtracking.events.DeviceCenterEventsKt;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public final class CustomIconCache {

    @GuardedBy(DeviceCenterEventsKt.REPORT_METHOD_CACHE)
    private final Map<String, Icon> cache = new LinkedHashMap();
    private ComponentName currentComponent;

    private final void clear() {
        synchronized (this.cache) {
            this.cache.clear();
            s sVar = s.f314a;
        }
    }

    public final Icon retrieve(ComponentName component, String controlId) {
        Icon icon;
        n.g(component, "component");
        n.g(controlId, "controlId");
        if (!n.c(component, this.currentComponent)) {
            return null;
        }
        synchronized (this.cache) {
            icon = this.cache.get(controlId);
        }
        return icon;
    }

    public final void store(ComponentName component, String controlId, Icon icon) {
        n.g(component, "component");
        n.g(controlId, "controlId");
        if (!n.c(component, this.currentComponent)) {
            clear();
            this.currentComponent = component;
        }
        synchronized (this.cache) {
            try {
                if (icon != null) {
                    this.cache.put(controlId, icon);
                } else {
                    this.cache.remove(controlId);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
