package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import miui.systemui.devicecenter.devices.DeviceInfoWrapper;

/* JADX INFO: loaded from: classes.dex */
public interface DeviceItem {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int TYPE_DETAIL = 338245;
    public static final int TYPE_DEVICE = 348423;
    public static final int TYPE_EMPTY = 36789;
    public static final int TYPE_OTHER_DEVICE_CONTROLLER = 348429;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int TYPE_DETAIL = 338245;
        public static final int TYPE_DEVICE = 348423;
        public static final int TYPE_EMPTY = 36789;
        public static final int TYPE_OTHER_DEVICE_CONTROLLER = 348429;

        private Companion() {
        }
    }

    public static final class DetailItem implements DeviceItem {
        private final int viewType = 338245;

        @Override // miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceItem
        public int getViewType() {
            return this.viewType;
        }
    }

    public static final class DeviceInfoItem implements DeviceItem {
        private DeviceInfoWrapper deviceInfo;
        private final String id;
        private final int viewType;

        public DeviceInfoItem(String id) {
            kotlin.jvm.internal.n.g(id, "id");
            this.id = id;
            this.viewType = 348423;
        }

        public final DeviceInfoWrapper getDeviceInfo() {
            return this.deviceInfo;
        }

        public final String getId() {
            return this.id;
        }

        @Override // miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceItem
        public int getViewType() {
            return this.viewType;
        }

        public final void setDeviceInfo(DeviceInfoWrapper deviceInfoWrapper) {
            this.deviceInfo = deviceInfoWrapper;
        }
    }

    public static final class EmptyDeviceItem implements DeviceItem {
        private final int viewType = 36789;

        @Override // miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceItem
        public int getViewType() {
            return this.viewType;
        }
    }

    public static final class OtherDeviceControllerItem implements DeviceItem {
        private DevicesServiceInfo devicesServiceInfo;
        private String packagename = "";
        private final int viewType = 348429;

        public final DevicesServiceInfo getDevicesServiceInfo() {
            return this.devicesServiceInfo;
        }

        public final String getPackagename() {
            return this.packagename;
        }

        @Override // miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceItem
        public int getViewType() {
            return this.viewType;
        }

        public final void setDevicesServiceInfo(DevicesServiceInfo devicesServiceInfo) {
            this.devicesServiceInfo = devicesServiceInfo;
        }

        public final void setPackagename(String str) {
            kotlin.jvm.internal.n.g(str, "<set-?>");
            this.packagename = str;
        }
    }

    int getViewType();
}
