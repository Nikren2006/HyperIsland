package miui.systemui.devicecontrols.management;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.devicecontrols.ControlInterface;
import miui.systemui.devicecontrols.controller.ControlInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlInfoWrapper extends ElementWrapper implements ControlInterface {
    private final ComponentName component;
    private final ControlInfo controlInfo;
    private Function2 customIconGetter;
    private boolean favorite;
    private boolean markVisible;

    public /* synthetic */ ControlInfoWrapper(ComponentName componentName, ControlInfo controlInfo, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(componentName, controlInfo, z2, (i2 & 8) != 0 ? true : z3);
    }

    public static /* synthetic */ ControlInfoWrapper copy$default(ControlInfoWrapper controlInfoWrapper, ComponentName componentName, ControlInfo controlInfo, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            componentName = controlInfoWrapper.component;
        }
        if ((i2 & 2) != 0) {
            controlInfo = controlInfoWrapper.controlInfo;
        }
        if ((i2 & 4) != 0) {
            z2 = controlInfoWrapper.favorite;
        }
        if ((i2 & 8) != 0) {
            z3 = controlInfoWrapper.markVisible;
        }
        return controlInfoWrapper.copy(componentName, controlInfo, z2, z3);
    }

    public final ComponentName component1() {
        return this.component;
    }

    public final ControlInfo component2() {
        return this.controlInfo;
    }

    public final boolean component3() {
        return this.favorite;
    }

    public final boolean component4() {
        return this.markVisible;
    }

    public final ControlInfoWrapper copy(ComponentName component, ControlInfo controlInfo, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(component, "component");
        kotlin.jvm.internal.n.g(controlInfo, "controlInfo");
        return new ControlInfoWrapper(component, controlInfo, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlInfoWrapper)) {
            return false;
        }
        ControlInfoWrapper controlInfoWrapper = (ControlInfoWrapper) obj;
        return kotlin.jvm.internal.n.c(this.component, controlInfoWrapper.component) && kotlin.jvm.internal.n.c(this.controlInfo, controlInfoWrapper.controlInfo) && this.favorite == controlInfoWrapper.favorite && this.markVisible == controlInfoWrapper.markVisible;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public ComponentName getComponent() {
        return this.component;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public String getControlId() {
        return this.controlInfo.getControlId();
    }

    public final ControlInfo getControlInfo() {
        return this.controlInfo;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public Icon getCustomIcon() {
        return (Icon) this.customIconGetter.invoke(getComponent(), getControlId());
    }

    public final Function2 getCustomIconGetter() {
        return this.customIconGetter;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public int getDeviceType() {
        return this.controlInfo.getDeviceType();
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public boolean getFavorite() {
        return this.favorite;
    }

    public final boolean getMarkVisible() {
        return this.markVisible;
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public CharSequence getSubtitle() {
        return this.controlInfo.getControlSubtitle();
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public CharSequence getTitle() {
        return this.controlInfo.getControlTitle();
    }

    public int hashCode() {
        return (((((this.component.hashCode() * 31) + this.controlInfo.hashCode()) * 31) + Boolean.hashCode(this.favorite)) * 31) + Boolean.hashCode(this.markVisible);
    }

    @Override // miui.systemui.devicecontrols.ControlInterface
    public void setFavorite(boolean z2) {
        this.favorite = z2;
    }

    public final void setMarkVisible(boolean z2) {
        this.markVisible = z2;
    }

    public String toString() {
        return "ControlInfoWrapper(component=" + this.component + ", controlInfo=" + this.controlInfo + ", favorite=" + this.favorite + ", markVisible=" + this.markVisible + ")";
    }

    public ControlInfoWrapper(ComponentName component, ControlInfo controlInfo, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(component, "component");
        kotlin.jvm.internal.n.g(controlInfo, "controlInfo");
        this.component = component;
        this.controlInfo = controlInfo;
        this.favorite = z2;
        this.markVisible = z3;
        this.customIconGetter = ControlInfoWrapper$customIconGetter$1.INSTANCE;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ControlInfoWrapper(ComponentName component, ControlInfo controlInfo, boolean z2, Function2 customIconGetter) {
        this(component, controlInfo, z2, false, 8, null);
        kotlin.jvm.internal.n.g(component, "component");
        kotlin.jvm.internal.n.g(controlInfo, "controlInfo");
        kotlin.jvm.internal.n.g(customIconGetter, "customIconGetter");
        this.customIconGetter = customIconGetter;
    }
}
