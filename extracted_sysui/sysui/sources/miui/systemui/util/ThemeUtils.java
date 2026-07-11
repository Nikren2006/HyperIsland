package miui.systemui.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.MiuiResources;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import java.io.File;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes4.dex */
public final class ThemeUtils {
    public static final ThemeUtils INSTANCE = new ThemeUtils();
    private static final String TAG = "ThemeUtils";
    private static boolean defaultPluginTheme;
    private static final Field resPkgField;
    private static int uiMode;

    static {
        Field declaredField = MiuiResources.class.getDeclaredField("mPackage");
        declaredField.setAccessible(true);
        resPkgField = declaredField;
        defaultPluginTheme = true;
    }

    private ThemeUtils() {
    }

    private final void invokeClearAllCaches(Resources resources) {
        if (resources == null) {
            return;
        }
        try {
            Object objInvoke = resources.getClass().getMethod("getImpl", null).invoke(resources, null);
            objInvoke.getClass().getMethod("clearAllCaches", null).invoke(objInvoke, null);
            Log.i(TAG, "Invoke clearAllCaches success.");
        } catch (Throwable th) {
            Log.w(TAG, "Invoke clearAllCaches failed.", th);
        }
    }

    private final void setDefaultPluginTheme(boolean z2) {
        if (z2 != defaultPluginTheme) {
            Log.i(TAG, "updating theme to " + z2);
            defaultPluginTheme = z2;
        }
    }

    public final void dispatchConfigurationChanged(Context context, Configuration configuration) {
        updateDefaultPluginTheme();
        int i2 = configuration != null ? configuration.uiMode : uiMode;
        if (uiMode != i2) {
            uiMode = i2;
            invokeClearAllCaches(context != null ? context.getResources() : null);
        }
    }

    public final void fixResourcesPackage(Context context) throws IllegalAccessException {
        kotlin.jvm.internal.n.g(context, "<this>");
        MiuiResources resources = context.getResources();
        if (resources instanceof MiuiResources) {
            Object obj = resPkgField.get(resources);
            if (kotlin.jvm.internal.n.c(obj, context.getPackageName())) {
                return;
            }
            resources.init(context.getPackageName());
            Log.i(TAG, "Fixed resources pkgName from " + obj + " to " + context.getPackageName());
        }
    }

    public final boolean getDefaultPluginTheme() {
        return defaultPluginTheme;
    }

    @ColorInt
    public final int getThemedAttrColor(Context context, @AttrRes int i2) {
        kotlin.jvm.internal.n.g(context, "<this>");
        Resources.Theme theme = context.getTheme();
        kotlin.jvm.internal.n.f(theme, "getTheme(...)");
        return getThemedAttrColor(theme, i2);
    }

    @ColorInt
    public final int getThemedColor(Context context, @ColorRes int i2) {
        kotlin.jvm.internal.n.g(context, "<this>");
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(i2, typedValue, true);
        if (typedValue.type == 2) {
            return INSTANCE.getThemedAttrColor(context, typedValue.data);
        }
        if (typedValue.isColorType()) {
            return typedValue.data;
        }
        return 0;
    }

    public final int getUiMode() {
        return uiMode;
    }

    public final void setUiMode(int i2) {
        uiMode = i2;
    }

    public final void updateDefaultPluginTheme() {
        setDefaultPluginTheme(!new File("/data/system/theme/miui.systemui.plugin").exists());
    }

    @ColorInt
    public final int getThemedAttrColor(Resources.Theme theme, @AttrRes int i2) {
        kotlin.jvm.internal.n.g(theme, "<this>");
        TypedValue typedValue = new TypedValue();
        if (theme.resolveAttribute(i2, typedValue, true) && typedValue.isColorType()) {
            return typedValue.data;
        }
        return 0;
    }
}
