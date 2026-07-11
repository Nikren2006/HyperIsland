package miui.systemui.notification.focus;

import android.content.Context;
import android.content.pm.PackageManager;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class SignatureChecker$packageManager$2 extends o implements Function0 {
    final /* synthetic */ Context $sysUiContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignatureChecker$packageManager$2(Context context) {
        super(0);
        this.$sysUiContext = context;
    }

    @Override // kotlin.jvm.functions.Function0
    public final PackageManager invoke() {
        return this.$sysUiContext.getPackageManager();
    }
}
