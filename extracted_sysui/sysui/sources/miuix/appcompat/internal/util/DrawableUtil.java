package miuix.appcompat.internal.util;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import miuix.appcompat.internal.graphics.drawable.PlaceholderDrawable;

/* JADX INFO: loaded from: classes3.dex */
public class DrawableUtil {
    private DrawableUtil() {
    }

    public static boolean isPlaceholder(Drawable drawable) {
        return (drawable instanceof PlaceholderDrawable) || ((drawable instanceof ColorDrawable) && ((ColorDrawable) drawable).getColor() == 0);
    }
}
