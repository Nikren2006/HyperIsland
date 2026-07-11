package miui.systemui.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.SystemProperties;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public final class FoldUtils {
    public static final FoldUtils INSTANCE = new FoldUtils();
    private static final boolean IS_FOLD;
    public static final String MUILT_DISPLAY_TYPE = "persist.sys.muiltdisplay_type";

    static {
        IS_FOLD = SystemProperties.getInt(MUILT_DISPLAY_TYPE, 0) == 2;
    }

    private FoldUtils() {
    }

    public final boolean getIS_FOLD() {
        return IS_FOLD;
    }

    public final boolean isFoldScreenLayoutLarge(View view) {
        kotlin.jvm.internal.n.g(view, "view");
        if (!IS_FOLD) {
            return false;
        }
        int i2 = view.getResources().getConfiguration().screenLayout & 15;
        return i2 == 3 || i2 == 4;
    }

    public final boolean isFoldScreenLayoutLarge(Context context) {
        Resources resources;
        Configuration configuration;
        if (!IS_FOLD) {
            return false;
        }
        Integer numValueOf = (context == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? null : Integer.valueOf(configuration.screenLayout & 15);
        return (numValueOf != null && numValueOf.intValue() == 3) || (numValueOf != null && numValueOf.intValue() == 4);
    }
}
