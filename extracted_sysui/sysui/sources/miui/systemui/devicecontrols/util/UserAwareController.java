package miui.systemui.devicecontrols.util;

import android.os.UserHandle;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public interface UserAwareController {
    default void changeUser(UserHandle newUser) {
        n.g(newUser, "newUser");
    }

    int getCurrentUserId();
}
