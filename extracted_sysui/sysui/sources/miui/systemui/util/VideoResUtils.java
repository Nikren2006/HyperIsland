package miui.systemui.util;

import android.content.Context;
import android.text.TextUtils;
import miui.systemui.quicksettings.common.R;

/* JADX INFO: loaded from: classes4.dex */
public final class VideoResUtils {
    public static final VideoResUtils INSTANCE = new VideoResUtils();

    private VideoResUtils() {
    }

    public final String getVideoRes(String str, Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        String str2 = "";
        if (str == null) {
            return "";
        }
        String str3 = "android.resource://" + context.getPackageName() + "/";
        if (kotlin.jvm.internal.n.c(str, "doubleCharge")) {
            str2 = str3 + R.raw.double_charge;
        }
        return TextUtils.isEmpty(str2) ? str : str2;
    }
}
