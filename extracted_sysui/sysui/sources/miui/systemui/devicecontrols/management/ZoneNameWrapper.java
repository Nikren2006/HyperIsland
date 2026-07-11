package miui.systemui.devicecontrols.management;

/* JADX INFO: loaded from: classes3.dex */
public final class ZoneNameWrapper extends ElementWrapper {
    private final CharSequence zoneName;

    public ZoneNameWrapper(CharSequence zoneName) {
        kotlin.jvm.internal.n.g(zoneName, "zoneName");
        this.zoneName = zoneName;
    }

    public static /* synthetic */ ZoneNameWrapper copy$default(ZoneNameWrapper zoneNameWrapper, CharSequence charSequence, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = zoneNameWrapper.zoneName;
        }
        return zoneNameWrapper.copy(charSequence);
    }

    public final CharSequence component1() {
        return this.zoneName;
    }

    public final ZoneNameWrapper copy(CharSequence zoneName) {
        kotlin.jvm.internal.n.g(zoneName, "zoneName");
        return new ZoneNameWrapper(zoneName);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ZoneNameWrapper) && kotlin.jvm.internal.n.c(this.zoneName, ((ZoneNameWrapper) obj).zoneName);
    }

    public final CharSequence getZoneName() {
        return this.zoneName;
    }

    public int hashCode() {
        return this.zoneName.hashCode();
    }

    public String toString() {
        return "ZoneNameWrapper(zoneName=" + ((Object) this.zoneName) + ")";
    }
}
