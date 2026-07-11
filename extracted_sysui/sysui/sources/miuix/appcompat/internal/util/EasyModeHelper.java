package miuix.appcompat.internal.util;

import android.content.Context;
import android.provider.Settings;
import android.widget.TextView;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class EasyModeHelper {
    private static boolean isInEasyMode(@Nullable Context context) {
        return context != null && Settings.System.getInt(context.getContentResolver(), "elderly_mode", 0) == 1;
    }

    public static void updateTextViewSize(@Nullable TextView textView) {
        if (textView == null || !isInEasyMode(textView.getContext())) {
            return;
        }
        textView.setTextSize(0, 88.0f);
    }
}
