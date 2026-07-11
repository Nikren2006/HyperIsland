package miui.systemui.devicecontrols.ui;

import android.content.ComponentName;
import android.service.controls.Control;
import miui.systemui.devicecontrols.controller.ControlInfo;
import miui.systemui.devicecontrols.management.ElementWrapper;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlWithState extends ElementWrapper {
    private final ControlInfo ci;
    private final ComponentName componentName;
    private final Control control;

    public ControlWithState(ComponentName componentName, ControlInfo ci, Control control) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(ci, "ci");
        this.componentName = componentName;
        this.ci = ci;
        this.control = control;
    }

    public static /* synthetic */ ControlWithState copy$default(ControlWithState controlWithState, ComponentName componentName, ControlInfo controlInfo, Control control, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            componentName = controlWithState.componentName;
        }
        if ((i2 & 2) != 0) {
            controlInfo = controlWithState.ci;
        }
        if ((i2 & 4) != 0) {
            control = controlWithState.control;
        }
        return controlWithState.copy(componentName, controlInfo, control);
    }

    public final ComponentName component1() {
        return this.componentName;
    }

    public final ControlInfo component2() {
        return this.ci;
    }

    public final Control component3() {
        return this.control;
    }

    public final ControlWithState copy(ComponentName componentName, ControlInfo ci, Control control) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(ci, "ci");
        return new ControlWithState(componentName, ci, control);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlWithState)) {
            return false;
        }
        ControlWithState controlWithState = (ControlWithState) obj;
        return kotlin.jvm.internal.n.c(this.componentName, controlWithState.componentName) && kotlin.jvm.internal.n.c(this.ci, controlWithState.ci) && kotlin.jvm.internal.n.c(this.control, controlWithState.control);
    }

    public final ControlInfo getCi() {
        return this.ci;
    }

    public final ComponentName getComponentName() {
        return this.componentName;
    }

    public final Control getControl() {
        return this.control;
    }

    public int hashCode() {
        int iHashCode = ((this.componentName.hashCode() * 31) + this.ci.hashCode()) * 31;
        Control control = this.control;
        return iHashCode + (control == null ? 0 : control.hashCode());
    }

    public String toString() {
        return "ControlWithState(componentName=" + this.componentName + ", ci=" + this.ci + ", control=" + this.control + ")";
    }
}
