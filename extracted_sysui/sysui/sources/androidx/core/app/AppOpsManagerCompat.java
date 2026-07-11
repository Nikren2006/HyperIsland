package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class AppOpsManagerCompat {
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_ERRORED = 2;
    public static final int MODE_IGNORED = 1;

    @RequiresApi(23)
    public static class Api23Impl {
        private Api23Impl() {
        }

        public static <T> T getSystemService(Context context, Class<T> cls) {
            return (T) context.getSystemService(cls);
        }

        public static int noteProxyOp(AppOpsManager appOpsManager, String str, String str2) {
            return appOpsManager.noteProxyOp(str, str2);
        }

        public static int noteProxyOpNoThrow(AppOpsManager appOpsManager, String str, String str2) {
            return appOpsManager.noteProxyOpNoThrow(str, str2);
        }

        public static String permissionToOp(String str) {
            return AppOpsManager.permissionToOp(str);
        }
    }

    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static int checkOpNoThrow(AppOpsManager appOpsManager, String str, int i2, String str2) {
            if (appOpsManager == null) {
                return 1;
            }
            return appOpsManager.checkOpNoThrow(str, i2, str2);
        }

        public static String getOpPackageName(Context context) {
            return context.getOpPackageName();
        }

        public static AppOpsManager getSystemService(Context context) {
            return (AppOpsManager) context.getSystemService(AppOpsManager.class);
        }
    }

    private AppOpsManagerCompat() {
    }

    public static int checkOrNoteProxyOp(Context context, int i2, String str, String str2) {
        AppOpsManager systemService = Api29Impl.getSystemService(context);
        int iCheckOpNoThrow = Api29Impl.checkOpNoThrow(systemService, str, Binder.getCallingUid(), str2);
        return iCheckOpNoThrow != 0 ? iCheckOpNoThrow : Api29Impl.checkOpNoThrow(systemService, str, i2, Api29Impl.getOpPackageName(context));
    }

    public static int noteOp(Context context, String str, int i2, String str2) {
        return ((AppOpsManager) context.getSystemService("appops")).noteOp(str, i2, str2);
    }

    public static int noteOpNoThrow(Context context, String str, int i2, String str2) {
        return ((AppOpsManager) context.getSystemService("appops")).noteOpNoThrow(str, i2, str2);
    }

    public static int noteProxyOp(Context context, String str, String str2) {
        return Api23Impl.noteProxyOp((AppOpsManager) Api23Impl.getSystemService(context, AppOpsManager.class), str, str2);
    }

    public static int noteProxyOpNoThrow(Context context, String str, String str2) {
        return Api23Impl.noteProxyOpNoThrow((AppOpsManager) Api23Impl.getSystemService(context, AppOpsManager.class), str, str2);
    }

    public static String permissionToOp(String str) {
        return Api23Impl.permissionToOp(str);
    }
}
