package miui.systemui.devicecontrols.util;

import f1.o;
import kotlin.jvm.internal.n;
import miui.systemui.devicecontrols.ControlInterface;
import miui.systemui.devicecontrols.controller.ControlInfo;
import miui.systemui.devicecontrols.management.ControlInfoWrapper;
import miui.systemui.devicecontrols.management.ControlStatusWrapper;
import miui.systemui.devicecontrols.management.ElementWrapper;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsUtils {
    public static final ControlsUtils INSTANCE = new ControlsUtils();
    public static final String MIHOME_ACTIVITY = "com.xiaomi.smarthome/com.xiaomi.smarthome.SmartHomeMainActivity";
    public static final String MIHOME_LOGIN_URI = "mihome://home.mi.com/main/login";
    public static final String MIHOME_PACKAGE = "com.xiaomi.smarthome";
    public static final String MIHOME_SERVICE = "com.xiaomi.smarthome/com.xiaomi.smarthome.controls.MiControlsProviderService";
    public static final int NORMAL_DEVICE_MAX_COUNT = 50;
    public static final int SENSE_DEVICE_MAX_COUNT = 4;

    private ControlsUtils() {
    }

    public final boolean checkSenseType(String controlId) {
        n.g(controlId, "controlId");
        return o.v(controlId, "mise:", false, 2, null);
    }

    public final boolean getFavorite(ElementWrapper elementWrapper) {
        n.g(elementWrapper, "<this>");
        if (elementWrapper instanceof ControlStatusWrapper) {
            return ((ControlStatusWrapper) elementWrapper).getFavorite();
        }
        if (elementWrapper instanceof ControlInfoWrapper) {
            return ((ControlInfoWrapper) elementWrapper).getFavorite();
        }
        return false;
    }

    public final boolean getMarkVisible(ElementWrapper elementWrapper) {
        n.g(elementWrapper, "<this>");
        if (elementWrapper instanceof ControlStatusWrapper) {
            return ((ControlStatusWrapper) elementWrapper).getMarkVisible();
        }
        if (elementWrapper instanceof ControlInfoWrapper) {
            return ((ControlInfoWrapper) elementWrapper).getMarkVisible();
        }
        return false;
    }

    public final void setMarkVisible(ElementWrapper elementWrapper, boolean z2) {
        n.g(elementWrapper, "<this>");
        if (elementWrapper instanceof ControlStatusWrapper) {
            ((ControlStatusWrapper) elementWrapper).setMarkVisible(z2);
        } else if (elementWrapper instanceof ControlInfoWrapper) {
            ((ControlInfoWrapper) elementWrapper).setMarkVisible(z2);
        }
    }

    public final ControlInfo toControlInfo(ControlInterface controlInterface) {
        n.g(controlInterface, "<this>");
        return controlInterface instanceof ControlInfoWrapper ? ((ControlInfoWrapper) controlInterface).getControlInfo() : controlInterface instanceof ControlStatusWrapper ? new ControlInfo(controlInterface.getControlId(), controlInterface.getTitle(), controlInterface.getSubtitle(), ((ControlStatusWrapper) controlInterface).getControlStatus().getControl().getZone(), controlInterface.getDeviceType()) : new ControlInfo(controlInterface.getControlId(), controlInterface.getTitle(), controlInterface.getSubtitle(), "", controlInterface.getDeviceType());
    }
}
