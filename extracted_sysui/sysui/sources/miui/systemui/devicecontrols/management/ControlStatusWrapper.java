package miui.systemui.devicecontrols.management;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.devicecontrols.ControlInterface;
import miui.systemui.devicecontrols.ControlStatus;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlStatusWrapper extends ElementWrapper implements ControlInterface {
    private final ControlStatus controlStatus;
    private boolean markVisible;

    public /* synthetic */ ControlStatusWrapper(ControlStatus controlStatus, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(controlStatus, (i2 & 2) != 0 ? true : z2);
    }

    public static /* synthetic */ ControlStatusWrapper copy$default(ControlStatusWrapper controlStatusWrapper, ControlStatus controlStatus, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            controlStatus = controlStatusWrapper.controlStatus;
        }
        if ((i2 & 2) != 0) {
            z2 = controlStatusWrapper.markVisible;
        }
        return controlStatusWrapper.copy(controlStatus, z2);
    }

    public final ControlStatus component1() {
        return this.controlStatus;
    }

    public final boolean component2() {
        return this.markVisible;
    }

    public final ControlStatusWrapper copy(ControlStatus controlStatus, boolean z2) {
        kotlin.jvm.internal.n.g(controlStatus, "controlStatus");
        return new ControlStatusWrapper(controlStatus, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlStatusWrapper)) {
            return false;
        }
        ControlStatusWrapper controlStatusWrapper = (ControlStatusWrapper) obj;
        return kotlin.jvm.internal.n.c(this.controlStatus, controlStatusWrapper.controlStatus) && this.markVisible == controlStatusWrapper.markVisible;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public ComponentName getComponent() {
        return this.controlStatus.getComponent();
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public String getControlId() {
        return this.controlStatus.getControlId();
    }

    public final ControlStatus getControlStatus() {
        return this.controlStatus;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public Icon getCustomIcon() {
        return this.controlStatus.getCustomIcon();
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public int getDeviceType() {
        return this.controlStatus.getDeviceType();
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public boolean getFavorite() {
        return this.controlStatus.getFavorite();
    }

    public final boolean getMarkVisible() {
        return this.markVisible;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public boolean getRemoved() {
        return this.controlStatus.getRemoved();
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public CharSequence getSubtitle() {
        return this.controlStatus.getSubtitle();
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public CharSequence getTitle() {
        return this.controlStatus.getTitle();
    }

    public int hashCode() {
        return (this.controlStatus.hashCode() * 31) + Boolean.hashCode(this.markVisible);
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public void setFavorite(boolean z2) {
        this.controlStatus.setFavorite(z2);
    }

    public final void setMarkVisible(boolean z2) {
        this.markVisible = z2;
    }

    public String toString() {
        return "ControlStatusWrapper(controlStatus=" + this.controlStatus + ", markVisible=" + this.markVisible + ")";
    }

    public ControlStatusWrapper(ControlStatus controlStatus, boolean z2) {
        kotlin.jvm.internal.n.g(controlStatus, "controlStatus");
        this.controlStatus = controlStatus;
        this.markVisible = z2;
    }
}
