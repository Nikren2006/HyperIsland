package miui.systemui.devicecontrols;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes3.dex */
public final class DCEntryInfo {
    private Drawable appIcon;
    private String appLabel;
    private final boolean available;
    private ComponentName componentName;

    public DCEntryInfo(boolean z2) {
        this.available = z2;
    }

    public static /* synthetic */ DCEntryInfo copy$default(DCEntryInfo dCEntryInfo, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = dCEntryInfo.available;
        }
        return dCEntryInfo.copy(z2);
    }

    public final boolean component1() {
        return this.available;
    }

    public final DCEntryInfo copy(boolean z2) {
        return new DCEntryInfo(z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DCEntryInfo) && this.available == ((DCEntryInfo) obj).available;
    }

    public final Drawable getAppIcon() {
        return this.appIcon;
    }

    public final String getAppLabel() {
        return this.appLabel;
    }

    public final boolean getAvailable() {
        return this.available;
    }

    public final ComponentName getComponentName() {
        return this.componentName;
    }

    public int hashCode() {
        return Boolean.hashCode(this.available);
    }

    public final void setAppIcon(Drawable drawable) {
        this.appIcon = drawable;
    }

    public final void setAppLabel(String str) {
        this.appLabel = str;
    }

    public final void setComponentName(ComponentName componentName) {
        this.componentName = componentName;
    }

    public String toString() {
        return "DCEntryInfo(available=" + this.available + ")";
    }
}
