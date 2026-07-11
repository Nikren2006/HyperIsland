package L;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {
    public static TypedValue a(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static boolean b(Context context, int i2, boolean z2) {
        TypedValue typedValueA = a(context, i2);
        return (typedValueA == null || typedValueA.type != 18) ? z2 : typedValueA.data != 0;
    }

    public static int c(Context context, int i2, int i3) {
        TypedValue typedValueA = a(context, i2);
        return (typedValueA == null || typedValueA.type != 16) ? i3 : typedValueA.data;
    }

    public static int d(Context context, int i2, String str) {
        return e(context, i2, str).data;
    }

    public static TypedValue e(Context context, int i2, String str) {
        TypedValue typedValueA = a(context, i2);
        if (typedValueA != null) {
            return typedValueA;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i2)));
    }

    public static TypedValue f(View view, int i2) {
        return e(view.getContext(), i2, view.getClass().getCanonicalName());
    }
}
