package L;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import androidx.core.math.MathUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class g {
    public static Typeface a(Context context, Typeface typeface) {
        return b(context.getResources().getConfiguration(), typeface);
    }

    public static Typeface b(Configuration configuration, Typeface typeface) {
        int i2 = configuration.fontWeightAdjustment;
        if (i2 == Integer.MAX_VALUE || i2 == 0 || typeface == null) {
            return null;
        }
        return Typeface.create(typeface, MathUtils.clamp(typeface.getWeight() + configuration.fontWeightAdjustment, 1, 1000), typeface.isItalic());
    }
}
