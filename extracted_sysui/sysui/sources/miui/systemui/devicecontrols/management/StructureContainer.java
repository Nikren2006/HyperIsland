package miui.systemui.devicecontrols.management;

/* JADX INFO: loaded from: classes3.dex */
public final class StructureContainer {
    private final ControlsModel model;
    private final CharSequence structureName;

    public StructureContainer(CharSequence structureName, ControlsModel model) {
        kotlin.jvm.internal.n.g(structureName, "structureName");
        kotlin.jvm.internal.n.g(model, "model");
        this.structureName = structureName;
        this.model = model;
    }

    public static /* synthetic */ StructureContainer copy$default(StructureContainer structureContainer, CharSequence charSequence, ControlsModel controlsModel, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = structureContainer.structureName;
        }
        if ((i2 & 2) != 0) {
            controlsModel = structureContainer.model;
        }
        return structureContainer.copy(charSequence, controlsModel);
    }

    public final CharSequence component1() {
        return this.structureName;
    }

    public final ControlsModel component2() {
        return this.model;
    }

    public final StructureContainer copy(CharSequence structureName, ControlsModel model) {
        kotlin.jvm.internal.n.g(structureName, "structureName");
        kotlin.jvm.internal.n.g(model, "model");
        return new StructureContainer(structureName, model);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StructureContainer)) {
            return false;
        }
        StructureContainer structureContainer = (StructureContainer) obj;
        return kotlin.jvm.internal.n.c(this.structureName, structureContainer.structureName) && kotlin.jvm.internal.n.c(this.model, structureContainer.model);
    }

    public final ControlsModel getModel() {
        return this.model;
    }

    public final CharSequence getStructureName() {
        return this.structureName;
    }

    public int hashCode() {
        return (this.structureName.hashCode() * 31) + this.model.hashCode();
    }

    public String toString() {
        CharSequence charSequence = this.structureName;
        return "StructureContainer(structureName=" + ((Object) charSequence) + ", model=" + this.model + ")";
    }
}
