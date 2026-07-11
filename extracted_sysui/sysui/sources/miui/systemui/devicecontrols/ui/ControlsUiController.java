package miui.systemui.devicecontrols.ui;

import android.content.ComponentName;
import android.content.Context;
import android.service.controls.Control;
import android.view.ViewGroup;
import java.util.List;
import miui.systemui.devicecontrols.controller.StructureInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface ControlsUiController {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String EXTRA_ANIMATE = "extra_animate";
    public static final String TAG = "ControlsUiController";

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String EXTRA_ANIMATE = "extra_animate";
        public static final String TAG = "ControlsUiController";

        private Companion() {
        }
    }

    void closeDialogs(boolean z2);

    StructureInfo getPreferredStructure(List<StructureInfo> list);

    void hide();

    void onActionResponse(ComponentName componentName, String str, int i2);

    void onRefreshState(ComponentName componentName, List<Control> list);

    default void show(ViewGroup parent, Runnable onDismiss, Context activityContext) {
        kotlin.jvm.internal.n.g(parent, "parent");
        kotlin.jvm.internal.n.g(onDismiss, "onDismiss");
        kotlin.jvm.internal.n.g(activityContext, "activityContext");
    }
}
