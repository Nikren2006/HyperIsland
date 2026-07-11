package miui.systemui.shade;

import com.android.systemui.plugins.miui.shade.PanelExpandController;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import kotlin.jvm.internal.n;
import miui.systemui.util.coroutines.flow.FlowConflatedKt;

/* JADX INFO: loaded from: classes4.dex */
public final class PanelExpandControllerExtKt {
    public static final InterfaceC0418f getAppearanceState(PanelExpandController panelExpandController) {
        n.g(panelExpandController, "<this>");
        return AbstractC0420h.y(FlowConflatedKt.conflatedCallbackFlow(new PanelExpandControllerExtKt$appearanceState$1(panelExpandController, null)), new PanelExpandControllerExtKt$appearanceState$2(panelExpandController, null));
    }

    public static final InterfaceC0418f getVisibleState(PanelExpandController panelExpandController) {
        n.g(panelExpandController, "<this>");
        return AbstractC0420h.y(FlowConflatedKt.conflatedCallbackFlow(new PanelExpandControllerExtKt$visibleState$1(panelExpandController, null)), new PanelExpandControllerExtKt$visibleState$2(panelExpandController, null));
    }
}
