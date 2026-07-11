package miui.systemui.devicecontrols.ui;

import android.content.ComponentName;
import android.view.View;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.devicecontrols.DCEntryInfo;
import miui.systemui.devicecontrols.controller.StructureInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface MiuiControlsUiController extends ControlsUiController, ConfigUtils.OnConfigChangeListener {
    static /* synthetic */ void show$default(MiuiControlsUiController miuiControlsUiController, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: show");
        }
        if ((i3 & 1) != 0) {
            i2 = -1;
        }
        miuiControlsUiController.show(i2);
    }

    void addDCEntryInfoCallback(Consumer<DCEntryInfo> consumer);

    View createDCView(Function0 function0, Function1 function1, Function0 function02);

    void destroy();

    CharSequence getCurrentMiHome();

    SelectionItem getSelectedItem();

    StructureInfo getSelectedStructure();

    boolean isShowForExpose();

    void loadStructure(Function0 function0);

    void reload();

    void removeDCEntryInfoCallback(Consumer<DCEntryInfo> consumer);

    void setCurrentMiHome(CharSequence charSequence);

    void setSelectedItem(SelectionItem selectionItem);

    void setSelectedStructure(StructureInfo structureInfo);

    void setShowForExpose(boolean z2);

    void show(int i2);

    void subscribe();

    void switchAppOrStructure(CharSequence charSequence, ComponentName componentName);

    void updatePreferences(ComponentName componentName, CharSequence charSequence);
}
