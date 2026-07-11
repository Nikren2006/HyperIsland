package miui.systemui.util;

import android.os.Handler;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class ThreadUtilsExt {
    public static final ThreadUtilsExt INSTANCE = new ThreadUtilsExt();

    private ThreadUtilsExt() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runOrPost$lambda$0(Function0 tmp0) {
        kotlin.jvm.internal.n.g(tmp0, "$tmp0");
        tmp0.invoke();
    }

    public final boolean runOrPost(Handler handler, final Function0 action) {
        kotlin.jvm.internal.n.g(handler, "<this>");
        kotlin.jvm.internal.n.g(action, "action");
        if (handler.getLooper().isCurrentThread()) {
            action.invoke();
            return true;
        }
        handler.post(new Runnable() { // from class: miui.systemui.util.t
            @Override // java.lang.Runnable
            public final void run() {
                ThreadUtilsExt.runOrPost$lambda$0(action);
            }
        });
        return false;
    }
}
