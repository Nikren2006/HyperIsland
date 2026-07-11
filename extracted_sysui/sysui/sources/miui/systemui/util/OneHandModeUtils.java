package miui.systemui.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

/* JADX INFO: loaded from: classes4.dex */
public final class OneHandModeUtils {
    public static final OneHandModeUtils INSTANCE = new OneHandModeUtils();
    private static boolean isOneHandMode;
    private static ContentObserver mOneHandModeObserver;

    private OneHandModeUtils() {
    }

    public final boolean isOneHandMode() {
        return isOneHandMode;
    }

    public final void registerOneHandSettingsObserver(final Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        mOneHandModeObserver = new ContentObserver(new Handler()) { // from class: miui.systemui.util.OneHandModeUtils.registerOneHandSettingsObserver.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2) {
                super.onChange(z2);
                OneHandModeUtils.INSTANCE.setOneHandMode(Settings.Secure.getInt(context.getContentResolver(), "one_handed_mode_activated", 0) != 0);
            }
        };
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriFor = Settings.Secure.getUriFor("one_handed_mode_activated");
        ContentObserver contentObserver = mOneHandModeObserver;
        kotlin.jvm.internal.n.e(contentObserver, "null cannot be cast to non-null type android.database.ContentObserver");
        contentResolver.registerContentObserver(uriFor, true, contentObserver);
    }

    public final void setOneHandMode(boolean z2) {
        isOneHandMode = z2;
    }
}
