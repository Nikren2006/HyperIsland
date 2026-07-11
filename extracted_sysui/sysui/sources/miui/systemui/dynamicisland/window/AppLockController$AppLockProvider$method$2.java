package miui.systemui.dynamicisland.window;

import H0.j;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import miui.security.SecurityManager;
import miui.systemui.dynamicisland.window.AppLockController;

/* JADX INFO: loaded from: classes3.dex */
public final class AppLockController$AppLockProvider$method$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ AppLockController.AppLockProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppLockController$AppLockProvider$method$2(AppLockController.AppLockProvider appLockProvider) {
        super(0);
        this.this$0 = appLockProvider;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        Object objA;
        try {
            j.a aVar = H0.j.f299a;
            objA = H0.j.a(SecurityManager.class.getDeclaredMethod("getAllApplicationsAccessControlNotPass", Integer.TYPE));
        } catch (Throwable th) {
            j.a aVar2 = H0.j.f299a;
            objA = H0.j.a(H0.k.a(th));
        }
        if (H0.j.b(objA) != null) {
            objA = null;
        }
        return (Method) objA;
    }
}
