package miui.systemui.notification.focus;

import F0.e;
import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final class SignatureChecker_Factory implements e {
    private final G0.a sysUiContextProvider;

    public SignatureChecker_Factory(G0.a aVar) {
        this.sysUiContextProvider = aVar;
    }

    public static SignatureChecker_Factory create(G0.a aVar) {
        return new SignatureChecker_Factory(aVar);
    }

    public static SignatureChecker newInstance(Context context) {
        return new SignatureChecker(context);
    }

    @Override // G0.a
    public SignatureChecker get() {
        return newInstance((Context) this.sysUiContextProvider.get());
    }
}
