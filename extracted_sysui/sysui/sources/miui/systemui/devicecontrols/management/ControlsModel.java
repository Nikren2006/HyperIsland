package miui.systemui.devicecontrols.management;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.List;
import miui.systemui.devicecontrols.controller.ControlInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface ControlsModel {

    public interface ControlsModelCallback {
        void onFirstChange();
    }

    public interface MoveHelper {
        boolean canMoveAfter(int i2);

        boolean canMoveBefore(int i2);

        void moveAfter(int i2);

        void moveBefore(int i2);
    }

    default void attachAdapter(ControlAdapter adapter) {
        kotlin.jvm.internal.n.g(adapter, "adapter");
    }

    default void changeFavoriteStatus(String controlId, boolean z2) {
        kotlin.jvm.internal.n.g(controlId, "controlId");
    }

    List<ElementWrapper> getElements();

    default List<ControlInfo> getFavorites() {
        return I0.m.h();
    }

    default ItemTouchHelper.Callback getItemTouchHelperCallback() {
        return null;
    }

    default MoveHelper getMoveHelper() {
        return null;
    }

    default void onMoveItem(int i2, int i3) {
    }
}
