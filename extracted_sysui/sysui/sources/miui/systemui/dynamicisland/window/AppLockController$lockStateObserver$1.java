package miui.systemui.dynamicisland.window;

import H0.j;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public final class AppLockController$lockStateObserver$1 extends ContentObserver {
    final /* synthetic */ AppLockController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppLockController$lockStateObserver$1(AppLockController appLockController, Handler handler) {
        super(handler);
        this.this$0 = appLockController;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z2) {
        Object objA;
        AppLockController appLockController = this.this$0;
        try {
            j.a aVar = H0.j.f299a;
            objA = H0.j.a(Integer.valueOf(Settings.Secure.getIntForUser(appLockController.context.getContentResolver(), "access_control_lock_enabled", -1, appLockController.userTracker.getUserId())));
        } catch (Throwable th) {
            j.a aVar2 = H0.j.f299a;
            objA = H0.j.a(H0.k.a(th));
        }
        if (H0.j.b(objA) != null) {
            objA = -1;
        }
        int iIntValue = ((Number) objA).intValue();
        this.this$0.lockEnable = iIntValue == 1;
        if (this.this$0.lockEnable) {
            this.this$0.provider.updateUser();
        } else {
            this.this$0.provider.clearCache();
        }
        Log.d(AppLockController.TAG, "onChange: " + iIntValue + " user = " + this.this$0.userTracker.getUserId());
    }
}
