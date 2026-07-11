package miui.systemui.drawable;

import H0.s;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import com.miui.maml.AnimatingDrawable;
import com.miui.maml.FancyDrawable;
import com.miui.maml.MamlAdaptiveIconDrawable;
import com.miui.maml.util.AppIconsHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class AppIconsManager {
    private static final boolean DEBUG = false;
    private static final String TAG = "AppIconsManager";
    public static final AppIconsManager INSTANCE = new AppIconsManager();
    private static final SparseArray<ConcurrentHashMap<String, WeakReference<Bitmap>>> mIconsCache = new SparseArray<>();

    public static final class NoCacheBitmapDrawable extends BitmapDrawable {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoCacheBitmapDrawable(Resources res, Bitmap bitmap) {
            super(res, bitmap);
            n.g(res, "res");
        }
    }

    private AppIconsManager() {
    }

    private final void cacheBitmap(int i2, String str, Drawable drawable) {
        if (drawable == null || !(drawable instanceof BitmapDrawable) || (drawable instanceof NoCacheBitmapDrawable)) {
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        n.f(bitmap, "getBitmap(...)");
        putBitmapToCache(i2, str, bitmap);
        log("Bitmap cached for " + str + " (userId: " + i2 + ")");
    }

    private final void drawDrawable(Drawable drawable, Canvas canvas) {
        if (drawable instanceof FancyDrawable) {
            FancyDrawable fancyDrawable = (FancyDrawable) drawable;
            Drawable quietDrawable = fancyDrawable.getQuietDrawable();
            if (quietDrawable != null) {
                drawable = quietDrawable;
            } else {
                fancyDrawable.getRoot().tick(SystemClock.elapsedRealtime());
            }
        }
        drawable.draw(canvas);
    }

    private final Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        boolean z2 = drawable instanceof MamlAdaptiveIconDrawable;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, ((drawable instanceof FancyDrawable) || z2 || drawable.getOpacity() != -1) ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        if (z2) {
            MamlAdaptiveIconDrawable mamlAdaptiveIconDrawable = (MamlAdaptiveIconDrawable) drawable;
            canvas.clipPath(mamlAdaptiveIconDrawable.getIconMask());
            Iterator<Drawable> it = getRealDrawables(mamlAdaptiveIconDrawable, true).iterator();
            while (it.hasNext()) {
                drawDrawable(it.next(), canvas);
            }
        } else {
            drawDrawable(drawable, canvas);
        }
        return bitmapCreateBitmap;
    }

    private final Bitmap getBitmapFromCache(int i2, String str) {
        SparseArray<ConcurrentHashMap<String, WeakReference<Bitmap>>> sparseArray = mIconsCache;
        synchronized (sparseArray) {
            ConcurrentHashMap<String, WeakReference<Bitmap>> concurrentHashMap = sparseArray.get(i2);
            if (concurrentHashMap == null) {
                return null;
            }
            n.d(concurrentHashMap);
            WeakReference<Bitmap> weakReference = concurrentHashMap.get(str);
            if (weakReference == null) {
                return null;
            }
            n.d(weakReference);
            Bitmap bitmap = weakReference.get();
            if (bitmap == null) {
                concurrentHashMap.remove(str);
            }
            return bitmap;
        }
    }

    private final List<Drawable> getRealDrawables(MamlAdaptiveIconDrawable mamlAdaptiveIconDrawable, boolean z2) {
        ArrayList arrayList = new ArrayList();
        Drawable background = mamlAdaptiveIconDrawable.getBackground();
        n.d(background);
        arrayList.add(getFancyChildOrSelf(background, z2));
        Drawable foreground = mamlAdaptiveIconDrawable.getForeground();
        if (foreground instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) foreground;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                Drawable drawable = layerDrawable.getDrawable(i2);
                n.d(drawable);
                arrayList.add(getFancyChildOrSelf(drawable, z2));
            }
        } else {
            n.d(foreground);
            arrayList.add(getFancyChildOrSelf(foreground, z2));
        }
        return arrayList;
    }

    private final boolean hasFancyDrawableWithoutQuietDrawable(Drawable drawable) {
        if (drawable instanceof FancyDrawable) {
            return ((FancyDrawable) drawable).getQuietDrawable() == null;
        }
        if (!(drawable instanceof MamlAdaptiveIconDrawable)) {
            return false;
        }
        for (Drawable drawable2 : getRealDrawables((MamlAdaptiveIconDrawable) drawable, false)) {
            if ((drawable2 instanceof FancyDrawable) && ((FancyDrawable) drawable2).getQuietDrawable() == null) {
                return true;
            }
        }
        return false;
    }

    private final Drawable loadAppIcon(String str, int i2, ApplicationInfo applicationInfo, PackageManager packageManager, Context context) {
        Drawable iconDrawable;
        Bitmap bitmapDrawable2Bitmap;
        Bitmap bitmapFromCache = getBitmapFromCache(i2, str);
        if (bitmapFromCache != null) {
            log("Bitmap cache hit for " + str + " (userId: " + i2 + ")");
            Resources system = Resources.getSystem();
            n.f(system, "getSystem(...)");
            return new BitmapDrawable(system, bitmapFromCache);
        }
        if (applicationInfo == null) {
            try {
                applicationInfo = context.getPackageManager().getApplicationInfo(str, i2);
            } catch (Exception e2) {
                Log.e(TAG, "Failed to load icon for " + str + " (userId: " + i2 + ")", e2);
                return null;
            }
        }
        if (packageManager == null) {
            packageManager = context.getPackageManager();
        }
        if (applicationInfo != null) {
            iconDrawable = AppIconsHelper.getIconDrawable(context, applicationInfo, packageManager);
            if (hasFancyDrawableWithoutQuietDrawable(iconDrawable) && (bitmapDrawable2Bitmap = drawable2Bitmap(iconDrawable)) != null) {
                Resources system2 = Resources.getSystem();
                n.f(system2, "getSystem(...)");
                iconDrawable = new NoCacheBitmapDrawable(system2, bitmapDrawable2Bitmap);
            }
        } else {
            iconDrawable = null;
        }
        cacheBitmap(i2, str, iconDrawable);
        return iconDrawable;
    }

    private final void log(String str) {
    }

    private final boolean noFancyDrawable(Drawable drawable) {
        if (drawable instanceof FancyDrawable) {
            return false;
        }
        if (!(drawable instanceof MamlAdaptiveIconDrawable)) {
            return true;
        }
        Iterator<Drawable> it = getRealDrawables((MamlAdaptiveIconDrawable) drawable, false).iterator();
        while (it.hasNext()) {
            if (it.next() instanceof FancyDrawable) {
                return false;
            }
        }
        return true;
    }

    private final void putBitmapToCache(int i2, String str, Bitmap bitmap) {
        SparseArray<ConcurrentHashMap<String, WeakReference<Bitmap>>> sparseArray = mIconsCache;
        synchronized (sparseArray) {
            try {
                ConcurrentHashMap<String, WeakReference<Bitmap>> concurrentHashMap = sparseArray.get(i2);
                if (concurrentHashMap == null) {
                    concurrentHashMap = new ConcurrentHashMap<>();
                    sparseArray.put(i2, concurrentHashMap);
                }
                concurrentHashMap.put(str, new WeakReference<>(bitmap));
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void clearAllIconCache() {
        SparseArray<ConcurrentHashMap<String, WeakReference<Bitmap>>> sparseArray = mIconsCache;
        synchronized (sparseArray) {
            sparseArray.clear();
            s sVar = s.f314a;
        }
    }

    public final void clearIconCache(int i2, String packageName) {
        n.g(packageName, "packageName");
        SparseArray<ConcurrentHashMap<String, WeakReference<Bitmap>>> sparseArray = mIconsCache;
        synchronized (sparseArray) {
            try {
                ConcurrentHashMap<String, WeakReference<Bitmap>> concurrentHashMap = sparseArray.get(i2);
                if (concurrentHashMap == null) {
                    return;
                }
                n.d(concurrentHashMap);
                concurrentHashMap.remove(packageName);
                if (concurrentHashMap.isEmpty()) {
                    sparseArray.remove(i2);
                }
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void clearUserIconCache(int i2) {
        SparseArray<ConcurrentHashMap<String, WeakReference<Bitmap>>> sparseArray = mIconsCache;
        synchronized (sparseArray) {
            sparseArray.remove(i2);
            s sVar = s.f314a;
        }
    }

    public final Drawable getAppIcon(String packageName, int i2, ApplicationInfo applicationInfo, PackageManager packageManager, Context context) {
        n.g(packageName, "packageName");
        n.g(context, "context");
        return loadAppIcon(packageName, i2, applicationInfo, packageManager, context);
    }

    public final Drawable getFancyChildOrSelf(Drawable drawable, boolean z2) {
        n.g(drawable, "drawable");
        if (drawable instanceof AnimatingDrawable) {
            AnimatingDrawable animatingDrawable = (AnimatingDrawable) drawable;
            if (animatingDrawable.isOnlyFancyWork()) {
                drawable = animatingDrawable.getFancyDrawable();
                if (z2) {
                    drawable.setBounds(animatingDrawable.getBounds());
                }
                n.d(drawable);
            }
        }
        return drawable;
    }
}
