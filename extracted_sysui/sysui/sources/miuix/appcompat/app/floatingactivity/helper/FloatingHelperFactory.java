package miuix.appcompat.app.floatingactivity.helper;

import androidx.annotation.NonNull;
import miuix.appcompat.app.AppCompatActivity;
import miuix.core.util.IntentUtils;
import miuix.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public class FloatingHelperFactory {
    public static final int TYPE_FOLD = 2;
    public static final int TYPE_PAD = 1;
    public static final int TYPE_PHONE = 0;

    @NonNull
    public static BaseFloatingActivityHelper get(@NonNull AppCompatActivity appCompatActivity) {
        int floatingHelperType = getFloatingHelperType(appCompatActivity);
        return floatingHelperType != 1 ? floatingHelperType != 2 ? new PhoneFloatingActivityHelper(appCompatActivity) : new FoldFloatingActivityHelper(appCompatActivity) : new PadFloatingActivityHelper(appCompatActivity);
    }

    public static int getFloatingHelperType(@NonNull AppCompatActivity appCompatActivity) {
        boolean zIsIntentFromSettingsSplit = IntentUtils.isIntentFromSettingsSplit(appCompatActivity.getIntent());
        if (zIsIntentFromSettingsSplit || !(Build.IS_FOLD_INSIDE || Build.IS_FOLD_OUTSIDE)) {
            return (zIsIntentFromSettingsSplit || !Build.IS_TABLET) ? 0 : 1;
        }
        return 2;
    }
}
