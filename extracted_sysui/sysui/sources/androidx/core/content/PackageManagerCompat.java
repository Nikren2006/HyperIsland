package androidx.core.content;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.os.UserManagerCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class PackageManagerCompat {

    @SuppressLint({"ActionValue"})
    public static final String ACTION_PERMISSION_REVOCATION_SETTINGS = "android.intent.action.AUTO_REVOKE_PERMISSIONS";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String LOG_TAG = "PackageManagerCompat";

    @RequiresApi(30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static boolean areUnusedAppRestrictionsEnabled(Context context) {
            return !context.getPackageManager().isAutoRevokeWhitelisted();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface UnusedAppRestrictionsStatus {
    }

    private PackageManagerCompat() {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static boolean areUnusedAppRestrictionsAvailable(PackageManager packageManager) {
        getPermissionRevocationVerifierApp(packageManager);
        return true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static String getPermissionRevocationVerifierApp(PackageManager packageManager) {
        String str = null;
        Iterator<ResolveInfo> it = packageManager.queryIntentActivities(new Intent(ACTION_PERMISSION_REVOCATION_SETTINGS).setData(Uri.fromParts("package", "com.example", null)), 0).iterator();
        while (it.hasNext()) {
            String str2 = it.next().activityInfo.packageName;
            if (packageManager.checkPermission("android.permission.PACKAGE_VERIFICATION_AGENT", str2) == 0) {
                if (str != null) {
                    return str;
                }
                str = str2;
            }
        }
        return str;
    }

    public static T.a getUnusedAppRestrictionsStatus(Context context) {
        ResolvableFuture resolvableFutureCreate = ResolvableFuture.create();
        if (!UserManagerCompat.isUserUnlocked(context)) {
            resolvableFutureCreate.set(0);
            Log.e(LOG_TAG, "User is in locked direct boot mode");
            return resolvableFutureCreate;
        }
        if (!areUnusedAppRestrictionsAvailable(context.getPackageManager())) {
            resolvableFutureCreate.set(1);
            return resolvableFutureCreate;
        }
        int i2 = context.getApplicationInfo().targetSdkVersion;
        if (i2 < 30) {
            resolvableFutureCreate.set(0);
            Log.e(LOG_TAG, "Target SDK version below API 30");
            return resolvableFutureCreate;
        }
        if (Api30Impl.areUnusedAppRestrictionsEnabled(context)) {
            resolvableFutureCreate.set(Integer.valueOf(i2 >= 31 ? 5 : 4));
        } else {
            resolvableFutureCreate.set(2);
        }
        return resolvableFutureCreate;
    }
}
