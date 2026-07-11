package miuix.appcompat.app;

import java.util.Collection;
import miuix.animation.listener.UpdateInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface ActionBarTransitionListener {
    @Deprecated
    default void onActionBarMove(float f2, float f3) {
    }

    void onActionBarResizing(int i2, float f2, int i3);

    void onExpandStateChanged(int i2);

    @Deprecated
    default void onTransitionBegin(Object obj) {
    }

    @Deprecated
    default void onTransitionComplete(Object obj) {
    }

    @Deprecated
    default void onTransitionUpdate(Object obj, Collection<UpdateInfo> collection) {
    }
}
