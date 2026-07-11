package miui.systemui.notification.auth;

import android.content.Context;
import android.os.IBinder;
import android.util.Log;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class AuthManager$mDeathRecipient$1 implements IBinder.DeathRecipient {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void binderDied$lambda$1(AuthManager$mDeathRecipient$1 this$0) {
        IBinder iBinderAsBinder;
        n.g(this$0, "this$0");
        Log.d("AuthManager", "Auth service died");
        if (AuthManager.authService == null) {
            return;
        }
        try {
            com.xiaomi.xms.auth.a aVar = AuthManager.authService;
            if (aVar != null && (iBinderAsBinder = aVar.asBinder()) != null) {
                iBinderAsBinder.unlinkToDeath(this$0, 0);
            }
        } catch (Exception e2) {
            Log.e("AuthManager", "binderDied: ", e2);
        }
        AuthManager.authService = null;
        AuthManager._connectionFlow.setValue(Boolean.FALSE);
        Context context = AuthManager.sysuiContext;
        if (context != null) {
            AuthManager.INSTANCE.connect(context);
        } else {
            Log.e("AuthManager", "Context is null when reconnecting");
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        AuthManager.mainThreadHandler.post(new Runnable() { // from class: miui.systemui.notification.auth.b
            @Override // java.lang.Runnable
            public final void run() {
                AuthManager$mDeathRecipient$1.binderDied$lambda$1(this.f5789a);
            }
        });
    }
}
