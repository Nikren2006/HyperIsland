package miui.systemui.util.coroutines.flow;

import android.view.View;
import j1.InterfaceC0418f;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class ViewUtilsKt {
    public static final InterfaceC0418f getOnClick(View view) {
        n.g(view, "<this>");
        return FlowConflatedKt.conflatedCallbackFlow(new ViewUtilsKt$onClick$1(view, null));
    }

    public static final InterfaceC0418f getOnLongClick(View view) {
        n.g(view, "<this>");
        return FlowConflatedKt.conflatedCallbackFlow(new ViewUtilsKt$onLongClick$1(view, null));
    }
}
