package miui.systemui.notification.focus;

import F0.e;

/* JADX INFO: loaded from: classes4.dex */
public final class FocusNotifUtils_Factory implements e {

    public static final class InstanceHolder {
        private static final FocusNotifUtils_Factory INSTANCE = new FocusNotifUtils_Factory();

        private InstanceHolder() {
        }
    }

    public static FocusNotifUtils_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static FocusNotifUtils newInstance() {
        return new FocusNotifUtils();
    }

    @Override // G0.a
    public FocusNotifUtils get() {
        return newInstance();
    }
}
