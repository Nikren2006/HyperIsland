package androidx.core.content;

import androidx.annotation.RestrictTo;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback;

/* JADX INFO: loaded from: classes.dex */
public class UnusedAppRestrictionsBackportCallback {
    private IUnusedAppRestrictionsBackportCallback mCallback;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public UnusedAppRestrictionsBackportCallback(IUnusedAppRestrictionsBackportCallback iUnusedAppRestrictionsBackportCallback) {
        this.mCallback = iUnusedAppRestrictionsBackportCallback;
    }

    public void onResult(boolean z2, boolean z3) {
        this.mCallback.onIsPermissionRevocationEnabledForAppResult(z2, z3);
    }
}
