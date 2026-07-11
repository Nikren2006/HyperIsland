package androidx.core.content;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class IntentCompat {

    @SuppressLint({"ActionValue"})
    public static final String ACTION_CREATE_REMINDER = "android.intent.action.CREATE_REMINDER";
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TIME = "android.intent.extra.TIME";

    @RequiresApi(33)
    public static class Api33Impl {
        private Api33Impl() {
        }

        public static <T> T[] getParcelableArrayExtra(Intent intent, String str, Class<T> cls) {
            return (T[]) intent.getParcelableArrayExtra(str, cls);
        }

        public static <T> ArrayList<T> getParcelableArrayListExtra(Intent intent, String str, Class<? extends T> cls) {
            return intent.getParcelableArrayListExtra(str, cls);
        }

        public static <T> T getParcelableExtra(Intent intent, String str, Class<T> cls) {
            return (T) intent.getParcelableExtra(str, cls);
        }

        public static <T extends Serializable> T getSerializableExtra(Intent intent, String str, Class<T> cls) {
            return (T) intent.getSerializableExtra(str, cls);
        }
    }

    private IntentCompat() {
    }

    public static Intent createManageUnusedAppRestrictionsIntent(Context context, String str) {
        if (PackageManagerCompat.areUnusedAppRestrictionsAvailable(context.getPackageManager())) {
            return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", str, null));
        }
        throw new UnsupportedOperationException("Unused App Restriction features are not available on this device");
    }

    @SuppressLint({"ArrayReturn", "NullableCollection"})
    public static Parcelable[] getParcelableArrayExtra(Intent intent, String str, Class<? extends Parcelable> cls) {
        return (Parcelable[]) Api33Impl.getParcelableArrayExtra(intent, str, cls);
    }

    @SuppressLint({"ConcreteCollection", "NullableCollection"})
    public static <T> ArrayList<T> getParcelableArrayListExtra(Intent intent, String str, Class<? extends T> cls) {
        return Api33Impl.getParcelableArrayListExtra(intent, str, cls);
    }

    public static <T> T getParcelableExtra(Intent intent, String str, Class<T> cls) {
        return (T) Api33Impl.getParcelableExtra(intent, str, cls);
    }

    public static <T extends Serializable> T getSerializableExtra(Intent intent, String str, Class<T> cls) {
        return (T) Api33Impl.getSerializableExtra(intent, str, cls);
    }

    public static Intent makeMainSelectorActivity(String str, String str2) {
        return Intent.makeMainSelectorActivity(str, str2);
    }
}
