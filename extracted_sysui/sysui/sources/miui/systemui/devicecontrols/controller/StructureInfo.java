package miui.systemui.devicecontrols.controller;

import android.content.ComponentName;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes3.dex */
public final class StructureInfo {
    private final ComponentName componentName;
    private final List<ControlInfo> controls;
    private boolean edit;
    private final CharSequence structure;

    public StructureInfo(ComponentName componentName, CharSequence structure, List<ControlInfo> controls, boolean z2) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(structure, "structure");
        kotlin.jvm.internal.n.g(controls, "controls");
        this.componentName = componentName;
        this.structure = structure;
        this.controls = controls;
        this.edit = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StructureInfo copy$default(StructureInfo structureInfo, ComponentName componentName, CharSequence charSequence, List list, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            componentName = structureInfo.componentName;
        }
        if ((i2 & 2) != 0) {
            charSequence = structureInfo.structure;
        }
        if ((i2 & 4) != 0) {
            list = structureInfo.controls;
        }
        if ((i2 & 8) != 0) {
            z2 = structureInfo.edit;
        }
        return structureInfo.copy(componentName, charSequence, list, z2);
    }

    public final ComponentName component1() {
        return this.componentName;
    }

    public final CharSequence component2() {
        return this.structure;
    }

    public final List<ControlInfo> component3() {
        return this.controls;
    }

    public final boolean component4() {
        return this.edit;
    }

    public final StructureInfo copy(ComponentName componentName, CharSequence structure, List<ControlInfo> controls, boolean z2) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(structure, "structure");
        kotlin.jvm.internal.n.g(controls, "controls");
        return new StructureInfo(componentName, structure, controls, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StructureInfo)) {
            return false;
        }
        StructureInfo structureInfo = (StructureInfo) obj;
        return kotlin.jvm.internal.n.c(this.componentName, structureInfo.componentName) && kotlin.jvm.internal.n.c(this.structure, structureInfo.structure) && kotlin.jvm.internal.n.c(this.controls, structureInfo.controls) && this.edit == structureInfo.edit;
    }

    public final ComponentName getComponentName() {
        return this.componentName;
    }

    public final List<ControlInfo> getControls() {
        return this.controls;
    }

    public final boolean getEdit() {
        return this.edit;
    }

    public final CharSequence getStructure() {
        return this.structure;
    }

    public int hashCode() {
        return (((((this.componentName.hashCode() * 31) + this.structure.hashCode()) * 31) + this.controls.hashCode()) * 31) + Boolean.hashCode(this.edit);
    }

    public final void setEdit(boolean z2) {
        this.edit = z2;
    }

    public String toString() {
        ComponentName componentName = this.componentName;
        CharSequence charSequence = this.structure;
        return "StructureInfo(componentName=" + componentName + ", structure=" + ((Object) charSequence) + ", controls=" + this.controls + ", edit=" + this.edit + ")";
    }

    public final boolean equals(StructureInfo target) {
        kotlin.jvm.internal.n.g(target, "target");
        if (this.componentName.compareTo(target.componentName) != 0 || !kotlin.jvm.internal.n.c(this.structure, target.structure) || this.controls.size() != target.controls.size()) {
            return false;
        }
        int i2 = 0;
        for (Object obj : this.controls) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                I0.m.n();
            }
            if (!kotlin.jvm.internal.n.c(((ControlInfo) obj).getControlId(), target.controls.get(i2).getControlId())) {
                return false;
            }
            i2 = i3;
        }
        return true;
    }

    public /* synthetic */ StructureInfo(ComponentName componentName, CharSequence charSequence, List list, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(componentName, charSequence, list, (i2 & 8) != 0 ? false : z2);
    }
}
