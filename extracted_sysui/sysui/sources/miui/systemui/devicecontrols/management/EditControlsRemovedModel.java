package miui.systemui.devicecontrols.management;

import I0.u;
import android.content.ComponentName;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function2;
import miui.systemui.devicecontrols.ControlInterface;
import miui.systemui.devicecontrols.util.ControlsUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class EditControlsRemovedModel extends EditControlsModel {
    private final EditEmptyGuideWrapper guideWrapper = new EditEmptyGuideWrapper(null, null, null, false, 15, null);

    public static /* synthetic */ void updateEmptyGuide$default(EditControlsRemovedModel editControlsRemovedModel, CharSequence charSequence, ComponentName componentName, ComponentName componentName2, boolean z2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z2 = false;
        }
        editControlsRemovedModel.updateEmptyGuide(charSequence, componentName, componentName2, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.devicecontrols.management.EditControlsModel
    public void addToElements(ControlInterface target, Function2 updateMark) {
        int i2;
        kotlin.jvm.internal.n.g(target, "target");
        kotlin.jvm.internal.n.g(updateMark, "updateMark");
        if (getElements().contains(this.guideWrapper)) {
            ControlAdapter adapter = getAdapter();
            if (adapter != null) {
                adapter.notifyItemRemoved(getElements().indexOf(this.guideWrapper));
            }
            getElements().remove(this.guideWrapper);
        }
        int dividerPosition = getDividerPosition();
        if (ControlsUtils.INSTANCE.checkSenseType(target.getControlId())) {
            getSenseControls().add(target);
            i2 = 0;
        } else {
            getNormalControls().add(target);
            i2 = dividerPosition + 1;
        }
        updateDividerVisible((getSenseControls().isEmpty() || getNormalControls().isEmpty()) ? false : true);
        ControlAdapter adapter2 = getAdapter();
        if (adapter2 != null) {
            adapter2.notifyItemInserted(i2);
        }
        getElements().add(i2, target);
        ControlAdapter adapter3 = getAdapter();
        kotlin.jvm.internal.n.e(adapter3, "null cannot be cast to non-null type miui.systemui.devicecontrols.management.ControlAdapter");
        adapter3.onItemInsert(i2);
    }

    @Override // miui.systemui.devicecontrols.management.EditControlsModel
    public List<ElementWrapper> createWrapper(List<? extends ElementWrapper> allControls) {
        kotlin.jvm.internal.n.g(allControls, "allControls");
        ArrayList<ElementWrapper> arrayList = new ArrayList();
        for (Object obj : allControls) {
            if (!ControlsUtils.INSTANCE.getFavorite((ElementWrapper) obj)) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            for (ElementWrapper elementWrapper : arrayList) {
                if ((elementWrapper instanceof ControlStatusWrapper) || (elementWrapper instanceof ControlInfoWrapper)) {
                    return super.createWrapper(arrayList);
                }
            }
        }
        return u.b0(u.b0(I0.m.h(), new DividerWrapper(false, false, 3, null)), this.guideWrapper);
    }

    public final EditEmptyGuideWrapper getGuideWrapper() {
        return this.guideWrapper;
    }

    @Override // miui.systemui.devicecontrols.management.EditControlsModel
    public void removeFromElements(ControlInterface target, Function2 updateMark) {
        kotlin.jvm.internal.n.g(target, "target");
        kotlin.jvm.internal.n.g(updateMark, "updateMark");
        super.removeFromElements(target, updateMark);
        List<ElementWrapper> elements = getElements();
        if (elements == null || !elements.isEmpty()) {
            for (ElementWrapper elementWrapper : elements) {
                if ((elementWrapper instanceof ControlStatusWrapper) || (elementWrapper instanceof ControlInfoWrapper)) {
                    return;
                }
            }
        }
        if (getElements().contains(this.guideWrapper)) {
            return;
        }
        ControlAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.notifyItemInserted(0);
        }
        getElements().add(this.guideWrapper);
    }

    public final void updateEmptyGuide(CharSequence appName, ComponentName componentName, ComponentName componentName2, boolean z2) {
        kotlin.jvm.internal.n.g(appName, "appName");
        EditEmptyGuideWrapper editEmptyGuideWrapper = this.guideWrapper;
        editEmptyGuideWrapper.setApp(appName);
        editEmptyGuideWrapper.setComponent(componentName);
        editEmptyGuideWrapper.setPanelActivity(componentName2);
        editEmptyGuideWrapper.setVisibility(z2);
    }
}
