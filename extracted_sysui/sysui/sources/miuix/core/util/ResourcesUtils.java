package miuix.core.util;

import android.content.res.AssetManager;
import android.content.res.Resources;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class ResourcesUtils {
    private static Method ASSET_MANAGER_ADD_ASSET_PATH;
    private static Constructor<AssetManager> ASSET_MANAGER_CONSTRUCTOR;

    static {
        try {
            ASSET_MANAGER_ADD_ASSET_PATH = AssetManager.class.getMethod("addAssetPath", String.class);
            ASSET_MANAGER_CONSTRUCTOR = AssetManager.class.getConstructor(null);
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        }
    }

    private ResourcesUtils() {
    }

    public static Resources createResources(String... strArr) {
        return createResources(null, strArr);
    }

    public static Resources createResources(Resources resources, String... strArr) {
        AssetManager assetManagerNewInstance;
        try {
            assetManagerNewInstance = ASSET_MANAGER_CONSTRUCTOR.newInstance(null);
            try {
                for (String str : strArr) {
                    ASSET_MANAGER_ADD_ASSET_PATH.invoke(assetManagerNewInstance, str);
                }
            } catch (IllegalAccessException e2) {
                e = e2;
                e.printStackTrace();
            } catch (InstantiationException e3) {
                e = e3;
                e.printStackTrace();
            } catch (InvocationTargetException e4) {
                e = e4;
                e.printStackTrace();
            }
        } catch (IllegalAccessException e5) {
            e = e5;
            assetManagerNewInstance = null;
        } catch (InstantiationException e6) {
            e = e6;
            assetManagerNewInstance = null;
        } catch (InvocationTargetException e7) {
            e = e7;
            assetManagerNewInstance = null;
        }
        return resources == null ? new Resources(assetManagerNewInstance, null, null) : new Resources(assetManagerNewInstance, resources.getDisplayMetrics(), resources.getConfiguration());
    }
}
