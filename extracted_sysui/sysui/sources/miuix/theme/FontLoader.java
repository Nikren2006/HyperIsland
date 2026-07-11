package miuix.theme;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes5.dex */
public class FontLoader {
    private static ConcurrentHashMap<String, Typeface> sFontCaches = new ConcurrentHashMap<>();

    @RequiresApi(api = 26)
    public static synchronized Typeface getFont(Resources resources, int i2) {
        if (resources == null) {
            return null;
        }
        String strValueOf = String.valueOf(i2);
        Typeface font = sFontCaches.get(strValueOf);
        if (font == null) {
            try {
                font = resources.getFont(i2);
                if (font != null) {
                    sFontCaches.put(strValueOf, font);
                }
            } catch (Resources.NotFoundException unused) {
                return null;
            }
        }
        return font;
    }

    public static synchronized Typeface getFont(Context context, int i2) {
        if (context != null) {
            if (context.getResources() != null) {
                String strValueOf = String.valueOf(i2);
                Typeface font = sFontCaches.get(strValueOf);
                if (font == null) {
                    try {
                        font = ResourcesCompat.getFont(context, i2);
                        if (font != null) {
                            sFontCaches.put(strValueOf, font);
                        }
                    } catch (Resources.NotFoundException unused) {
                        return null;
                    }
                }
                return font;
            }
        }
        return null;
    }

    public static synchronized Typeface getFont(String str, int i2) {
        if (str == null) {
            return null;
        }
        String str2 = str + "_" + i2;
        Typeface typefaceCreate = sFontCaches.get(str2);
        if (typefaceCreate == null && (typefaceCreate = Typeface.create(str, i2)) != null) {
            sFontCaches.put(str2, typefaceCreate);
        }
        return typefaceCreate;
    }

    public static synchronized Typeface getFont(File file) {
        if (file == null) {
            return null;
        }
        String absolutePath = file.getAbsolutePath();
        Typeface typefaceCreateFromFile = sFontCaches.get(absolutePath);
        if (typefaceCreateFromFile == null && (typefaceCreateFromFile = Typeface.createFromFile(file)) != null) {
            sFontCaches.put(absolutePath, typefaceCreateFromFile);
        }
        return typefaceCreateFromFile;
    }

    public static synchronized Typeface getFont(String str) {
        if (str != null) {
            if (new File(str).exists()) {
                Typeface typefaceCreateFromFile = sFontCaches.get(str);
                if (typefaceCreateFromFile == null && (typefaceCreateFromFile = Typeface.createFromFile(str)) != null) {
                    sFontCaches.put(str, typefaceCreateFromFile);
                }
                return typefaceCreateFromFile;
            }
        }
        return null;
    }

    public static synchronized Typeface getFont(AssetManager assetManager, String str) {
        if (str == null || assetManager == null) {
            return null;
        }
        Typeface typefaceCreateFromAsset = sFontCaches.get(str);
        if (typefaceCreateFromAsset == null) {
            try {
                typefaceCreateFromAsset = Typeface.createFromAsset(assetManager, str);
                if (typefaceCreateFromAsset != null) {
                    sFontCaches.put(str, typefaceCreateFromAsset);
                }
            } catch (Exception e2) {
                Log.w("FontLoader", "getFont fail: " + e2);
                return null;
            }
        }
        return typefaceCreateFromAsset;
    }
}
