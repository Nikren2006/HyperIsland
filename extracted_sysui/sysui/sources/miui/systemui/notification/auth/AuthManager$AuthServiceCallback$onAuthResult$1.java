package miui.systemui.notification.auth;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.provider.FontsContractCompat;
import g1.E;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.notification.auth.AuthManager;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.notification.auth.AuthManager$AuthServiceCallback$onAuthResult$1", f = "AuthManager.kt", l = {}, m = "invokeSuspend")
public final class AuthManager$AuthServiceCallback$onAuthResult$1 extends l implements Function2 {
    final /* synthetic */ Bundle $authBundle;
    int label;
    final /* synthetic */ AuthManager.AuthServiceCallback this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthManager$AuthServiceCallback$onAuthResult$1(Bundle bundle, AuthManager.AuthServiceCallback authServiceCallback, d dVar) {
        super(2, dVar);
        this.$authBundle = bundle;
        this.this$0 = authServiceCallback;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new AuthManager$AuthServiceCallback$onAuthResult$1(this.$authBundle, this.this$0, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((AuthManager$AuthServiceCallback$onAuthResult$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k.b(obj);
        Bundle bundle = this.$authBundle.getBundle("result_auth_params");
        String string = bundle != null ? bundle.getString(DynamicIslandConstants.EXTRA_PACKAGE_NAME) : null;
        String string2 = bundle != null ? bundle.getString("notification_key") : null;
        AuthManager authManager = AuthManager.INSTANCE;
        AuthManager.isLowVersion = TextUtils.isEmpty(string);
        int i2 = this.$authBundle.getInt(FontsContractCompat.Columns.RESULT_CODE);
        Log.d("AuthManager", string + " auth result code: " + i2 + ", isLowVersion: " + AuthManager.isLowVersion);
        if (i2 == -400 || i2 == 0) {
            Log.d("AuthManager", "Auth success.");
            if (string2 != null && string != null) {
                this.this$0.inflateCallBack.onAuthSuccess(string2, string);
            }
        } else {
            Log.d("AuthManager", "Auth error message: " + this.$authBundle.getString("result_msg"));
            if (string2 != null && string != null) {
                this.this$0.inflateCallBack.onAuthFailed(string2, string);
            }
        }
        return s.f314a;
    }
}
