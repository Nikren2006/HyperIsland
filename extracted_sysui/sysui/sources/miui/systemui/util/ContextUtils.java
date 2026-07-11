package miui.systemui.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableInflater;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class ContextUtils {
    public static final ContextUtils INSTANCE = new ContextUtils();
    public static final String PLUGIN_HEADER = "miui.systemui";
    private static final String TAG = "ContextUtils";

    private ContextUtils() {
    }

    public static /* synthetic */ Context getNightContext$default(ContextUtils contextUtils, Context context, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return contextUtils.getNightContext(context, z2);
    }

    public final void clearDrawableConstructorCache() {
        try {
            Field declaredField = DrawableInflater.class.getDeclaredField("CONSTRUCTOR_MAP");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type java.util.HashMap<kotlin.String, java.lang.reflect.Constructor<out android.graphics.drawable.Drawable>>");
            HashMap map = (HashMap) obj;
            synchronized (map) {
                try {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        String str = (String) ((Map.Entry) it.next()).getKey();
                        if (f1.n.s(str, PLUGIN_HEADER, false, 2, null)) {
                            Constructor constructor = Class.forName(str).asSubclass(Drawable.class).getConstructor(null);
                            kotlin.jvm.internal.n.f(constructor, "getConstructor(...)");
                            map.put(str, constructor);
                        }
                    }
                    H0.s sVar = H0.s.f314a;
                } finally {
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "clear plugin drawable constructor cache in DrawableInflater failed: " + e2);
        }
    }

    public final Object fixClassLoader(Context context) {
        kotlin.jvm.internal.n.g(context, "<this>");
        try {
            ContextUtils contextUtils = INSTANCE;
            Resources resources = context.getResources();
            kotlin.jvm.internal.n.f(resources, "getResources(...)");
            ClassLoader classLoader = context.getClassLoader();
            kotlin.jvm.internal.n.f(classLoader, "getClassLoader(...)");
            contextUtils.fixClassLoader(resources, classLoader);
            return H0.s.f314a;
        } catch (Exception e2) {
            return Integer.valueOf(Log.e(TAG, "fix classloader for resources & drawable inflater of plugin context failed: " + e2));
        }
    }

    public final Context getDayContext(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        int i2 = context.getResources().getConfiguration().uiMode;
        if ((i2 & 48) == 16) {
            return context;
        }
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.uiMode = (i2 & (-49)) | 16;
        Context contextCreateConfigurationContext = context.createConfigurationContext(configuration);
        try {
            Field declaredField = Class.forName("android.app.ContextImpl").getDeclaredField("mClassLoader");
            kotlin.jvm.internal.n.f(declaredField, "getDeclaredField(...)");
            declaredField.setAccessible(true);
            declaredField.set(contextCreateConfigurationContext, context.getClassLoader());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        kotlin.jvm.internal.n.d(contextCreateConfigurationContext);
        return contextCreateConfigurationContext;
    }

    public final Context getNightContext(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        int i2 = context.getResources().getConfiguration().uiMode;
        if ((i2 & 48) == 32 && !z2) {
            return context;
        }
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.uiMode = (i2 & (-49)) | 32;
        Context contextCreateConfigurationContext = context.createConfigurationContext(configuration);
        try {
            Field declaredField = Class.forName("android.app.ContextImpl").getDeclaredField("mClassLoader");
            kotlin.jvm.internal.n.f(declaredField, "getDeclaredField(...)");
            declaredField.setAccessible(true);
            declaredField.set(contextCreateConfigurationContext, context.getClassLoader());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        kotlin.jvm.internal.n.d(contextCreateConfigurationContext);
        return contextCreateConfigurationContext;
    }

    private final void fixClassLoader(Resources resources, ClassLoader classLoader) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Field declaredField = Resources.class.getDeclaredField("mClassLoader");
        declaredField.setAccessible(true);
        declaredField.set(resources, classLoader);
        Constructor<?> constructor = Class.forName("android.graphics.drawable.DrawableInflater").getConstructor(Resources.class, ClassLoader.class);
        constructor.setAccessible(true);
        Object objNewInstance = constructor.newInstance(resources, classLoader);
        Field declaredField2 = Resources.class.getDeclaredField("mDrawableInflater");
        declaredField2.setAccessible(true);
        declaredField2.set(resources, objNewInstance);
    }
}
