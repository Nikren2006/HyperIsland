package miui.systemui.devicecontrols;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import android.service.controls.Control;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlStatus implements ControlInterface {
    private final ComponentName component;
    private final Control control;
    private boolean favorite;
    private final boolean removed;

    public ControlStatus(Control control, ComponentName component, boolean z2, boolean z3) {
        n.g(control, "control");
        n.g(component, "component");
        this.control = control;
        this.component = component;
        this.favorite = z2;
        this.removed = z3;
    }

    public static /* synthetic */ ControlStatus copy$default(ControlStatus controlStatus, Control control, ComponentName componentName, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            control = controlStatus.control;
        }
        if ((i2 & 2) != 0) {
            componentName = controlStatus.component;
        }
        if ((i2 & 4) != 0) {
            z2 = controlStatus.favorite;
        }
        if ((i2 & 8) != 0) {
            z3 = controlStatus.removed;
        }
        return controlStatus.copy(control, componentName, z2, z3);
    }

    public static /* synthetic */ void getDeviceType$annotations() {
    }

    public final Control component1() {
        return this.control;
    }

    public final ComponentName component2() {
        return this.component;
    }

    public final boolean component3() {
        return this.favorite;
    }

    public final boolean component4() {
        return this.removed;
    }

    public final ControlStatus copy(Control control, ComponentName component, boolean z2, boolean z3) {
        n.g(control, "control");
        n.g(component, "component");
        return new ControlStatus(control, component, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlStatus)) {
            return false;
        }
        ControlStatus controlStatus = (ControlStatus) obj;
        return n.c(this.control, controlStatus.control) && n.c(this.component, controlStatus.component) && this.favorite == controlStatus.favorite && this.removed == controlStatus.removed;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public ComponentName getComponent() {
        return this.component;
    }

    public final Control getControl() {
        return this.control;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public String getControlId() {
        String controlId = this.control.getControlId();
        n.f(controlId, "getControlId(...)");
        return controlId;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public Icon getCustomIcon() {
        return this.control.getCustomIcon();
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public int getDeviceType() {
        return this.control.getDeviceType();
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public boolean getFavorite() {
        return this.favorite;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public boolean getRemoved() {
        return this.removed;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public CharSequence getSubtitle() {
        CharSequence subtitle = this.control.getSubtitle();
        n.f(subtitle, "getSubtitle(...)");
        return subtitle;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public CharSequence getTitle() {
        CharSequence title = this.control.getTitle();
        n.f(title, "getTitle(...)");
        return title;
    }

    public int hashCode() {
        return (((((this.control.hashCode() * 31) + this.component.hashCode()) * 31) + Boolean.hashCode(this.favorite)) * 31) + Boolean.hashCode(this.removed);
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public void setFavorite(boolean z2) {
        this.favorite = z2;
    }

    public String toString() {
        return "ControlStatus(control=" + this.control + ", component=" + this.component + ", favorite=" + this.favorite + ", removed=" + this.removed + ")";
    }

    public /* synthetic */ ControlStatus(Control control, ComponentName componentName, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(control, componentName, z2, (i2 & 8) != 0 ? false : z3);
    }
}
