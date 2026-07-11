package miui.systemui.notification.auth;

import android.os.Bundle;
import miui.systemui.dynamicisland.DynamicIslandConstants;

/* JADX INFO: loaded from: classes4.dex */
public final class AuthParams {
    private String packageName;
    private long timeoutInMillis;
    private long SCOPE = 20032;
    private final String PARAM_SCOPE = "scope";
    private final String PARAM_PACKAGE_NAME = DynamicIslandConstants.EXTRA_PACKAGE_NAME;

    public final String getPackageName() {
        return this.packageName;
    }

    public final long getTimeoutInMillis() {
        return this.timeoutInMillis;
    }

    public final void setPackageName(String str) {
        this.packageName = str;
    }

    public final void setTimeoutInMillis(long j2) {
        this.timeoutInMillis = j2;
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong(this.PARAM_SCOPE, this.SCOPE);
        bundle.putString(this.PARAM_PACKAGE_NAME, this.packageName);
        return bundle;
    }
}
