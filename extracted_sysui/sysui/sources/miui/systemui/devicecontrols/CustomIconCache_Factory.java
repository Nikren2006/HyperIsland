package miui.systemui.devicecontrols;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class CustomIconCache_Factory implements e {

    public static final class InstanceHolder {
        private static final CustomIconCache_Factory INSTANCE = new CustomIconCache_Factory();

        private InstanceHolder() {
        }
    }

    public static CustomIconCache_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static CustomIconCache newInstance() {
        return new CustomIconCache();
    }

    @Override // G0.a
    public CustomIconCache get() {
        return newInstance();
    }
}
