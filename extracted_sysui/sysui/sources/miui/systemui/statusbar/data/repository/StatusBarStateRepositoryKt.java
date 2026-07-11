package miui.systemui.statusbar.data.repository;

import com.android.systemui.plugins.statusbar.StatusBarStateController;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import kotlin.jvm.internal.n;
import miui.systemui.util.coroutines.flow.FlowConflatedKt;

/* JADX INFO: loaded from: classes4.dex */
public final class StatusBarStateRepositoryKt {
    public static final InterfaceC0418f getStatusBarState(StatusBarStateController statusBarStateController) {
        n.g(statusBarStateController, "<this>");
        return AbstractC0420h.y(FlowConflatedKt.conflatedCallbackFlow(new StatusBarStateRepositoryKt$statusBarState$1(statusBarStateController, null)), new StatusBarStateRepositoryKt$statusBarState$2(statusBarStateController, null));
    }
}
