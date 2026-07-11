package com.miui.maml;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.AsyncTask;
import android.os.MemoryFile;
import android.text.TextUtils;
import android.util.LruCache;
import android.util.MiuiDisplayMetrics;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import com.miui.maml.LanguageHelper;
import com.miui.maml.util.MamlLog;
import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes2.dex */
public class ResourceManager {
    private static final int DENSITY_HIGH_R = 240;
    private static final int DENSITY_XHIGH_R = 360;
    private static final int DENSITY_XXHIGH_R = 540;
    private static final int DENSITY_XXXHIGH = 640;
    private static final int DENSITY_XXXHIGH_R = 720;
    private static final String LOG_TAG = "ResourceManager";
    private static final int RESOURCE_FALLBACK_DENSITY = 480;
    private static final String RESOURCE_FALLBACK_EXTRA_FOLDER = "den480/";
    private static volatile int sRef;
    private long lastPluralsUpdateTime;
    private int mDefaultResourceDensity;
    private int mExtraResourceDensity;
    private String mExtraResourceFolder;
    private ResourceLoader mResourceLoader;
    private int mTargetDensity;
    private static final String RESOURCE_DENSITY_DEVICE_FALLBACK_EXTRA_FOLDER = "den" + MiuiDisplayMetrics.DENSITY_DEVICE + "/";
    private static final Object sLock = new Object();
    private static final int DEF_CACHE_SIZE = 268435456;
    protected static LruCache<String, BitmapInfo> sBitmapsCache = new LruCache<String, BitmapInfo>(DEF_CACHE_SIZE) { // from class: com.miui.maml.ResourceManager.1
        @Override // android.util.LruCache
        public int sizeOf(String str, BitmapInfo bitmapInfo) {
            Bitmap bitmap;
            if (bitmapInfo == null || (bitmap = bitmapInfo.mBitmap) == null) {
                return 0;
            }
            return bitmap.getAllocationByteCount();
        }
    };
    private static ConcurrentHashMap<String, WeakReference<BitmapInfo>> sWeakRefBitmapsCache = new ConcurrentHashMap<>();
    private HashMap<LanguageHelper.Plurals, HashMap<Integer, String>> pluralsMap = new HashMap<>();
    protected final Object mBitmapKeysLock = new Object();
    protected ArraySet<String> mBitmapKeys = new ArraySet<>();
    private final ArraySet<String> mLoadingBitmaps = new ArraySet<>();

    public interface AsyncLoadListener {
        void onLoadComplete(String str, BitmapInfo bitmapInfo);
    }

    public class LoadBitmapAsyncTask extends AsyncTask<String, Object, BitmapInfo> {
        private AsyncLoadListener mLoadListener;
        private String mSrc;

        public LoadBitmapAsyncTask(AsyncLoadListener asyncLoadListener) {
            this.mLoadListener = asyncLoadListener;
        }

        @Override // android.os.AsyncTask
        public BitmapInfo doInBackground(String... strArr) {
            String str = strArr[0];
            this.mSrc = str;
            if (str != null) {
                return ResourceManager.this.loadBitmap(str);
            }
            return null;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(BitmapInfo bitmapInfo) {
            synchronized (ResourceManager.this.mLoadingBitmaps) {
                this.mLoadListener.onLoadComplete(this.mSrc, bitmapInfo);
                ResourceManager.this.mLoadingBitmaps.remove(this.mSrc);
            }
        }
    }

    public ResourceManager(ResourceLoader resourceLoader) {
        synchronized (sLock) {
            sRef++;
        }
        this.mResourceLoader = resourceLoader;
    }

    public static void clear() {
        evictCacheSafety();
        sWeakRefBitmapsCache.clear();
    }

    private static void evictCacheSafety() {
        try {
            if (sBitmapsCache.size() > 0) {
                sBitmapsCache.evictAll();
            }
        } catch (Exception e2) {
            MamlLog.e(LOG_TAG, "evictCacheSafety exception", e2);
        }
    }

    private BitmapInfo getCache(String str) {
        String str2 = this.mResourceLoader.getID() + str;
        BitmapInfo bitmapInfo = sBitmapsCache.get(str2);
        WeakReference<BitmapInfo> weakReference = sWeakRefBitmapsCache.get(str2);
        if (bitmapInfo != null) {
            bitmapInfo.mLastVisitTime = System.currentTimeMillis();
            if (weakReference == null || weakReference.get() == null) {
                sWeakRefBitmapsCache.put(str2, new WeakReference<>(bitmapInfo));
            }
        } else if (weakReference != null) {
            bitmapInfo = weakReference.get();
            if (bitmapInfo != null) {
                bitmapInfo.mLastVisitTime = System.currentTimeMillis();
                synchronized (this.mBitmapKeysLock) {
                    this.mBitmapKeys.add(str2);
                }
                sBitmapsCache.put(str2, bitmapInfo);
            } else {
                sWeakRefBitmapsCache.remove(str2);
            }
        }
        if (bitmapInfo == null || bitmapInfo.mDensity == this.mTargetDensity) {
            return bitmapInfo;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BitmapInfo loadBitmap(String str) {
        BitmapInfo bitmapInfo;
        String str2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        boolean z2 = true;
        options.inScaled = true;
        options.inTargetDensity = this.mTargetDensity;
        if (this.mExtraResourceFolder != null) {
            MamlLog.i(LOG_TAG, "try to load resource from extra resource: " + this.mExtraResourceFolder + " of " + str);
            options.inDensity = this.mExtraResourceDensity;
            if (TextUtils.isEmpty(this.mExtraResourceFolder)) {
                str2 = str;
            } else {
                str2 = this.mExtraResourceFolder + "/" + str;
            }
            bitmapInfo = this.mResourceLoader.getBitmapInfo(str2, options);
            if (bitmapInfo != null) {
                z2 = false;
            }
        } else {
            bitmapInfo = null;
        }
        if (bitmapInfo == null) {
            options.inDensity = this.mDefaultResourceDensity;
            bitmapInfo = this.mResourceLoader.getBitmapInfo(str, options);
        }
        if (bitmapInfo == null) {
            options.inDensity = MiuiDisplayMetrics.DENSITY_DEVICE;
            bitmapInfo = this.mResourceLoader.getBitmapInfo(RESOURCE_DENSITY_DEVICE_FALLBACK_EXTRA_FOLDER + str, options);
        }
        if (bitmapInfo == null) {
            options.inDensity = 480;
            bitmapInfo = this.mResourceLoader.getBitmapInfo(RESOURCE_FALLBACK_EXTRA_FOLDER + str, options);
        }
        if (bitmapInfo != null) {
            if (!z2) {
                MamlLog.i(LOG_TAG, "load image from extra resource: " + this.mExtraResourceFolder + " of " + str);
            }
            bitmapInfo.mKey = this.mResourceLoader.getID() + str;
            int i2 = this.mTargetDensity;
            bitmapInfo.mDensity = i2;
            bitmapInfo.mBitmap.setDensity(i2);
            bitmapInfo.mLastVisitTime = System.currentTimeMillis();
            synchronized (this.mBitmapKeysLock) {
                this.mBitmapKeys.add(bitmapInfo.mKey);
            }
            sBitmapsCache.put(bitmapInfo.mKey, bitmapInfo);
            sWeakRefBitmapsCache.put(bitmapInfo.mKey, new WeakReference<>(bitmapInfo));
        }
        return bitmapInfo;
    }

    public static int retranslateDensity(int i2) {
        return (i2 <= DENSITY_HIGH_R || i2 > 360) ? (i2 <= 360 || i2 > DENSITY_XXHIGH_R) ? (i2 <= DENSITY_XXHIGH_R || i2 > DENSITY_XXXHIGH_R) ? i2 : ((int) (((double) (i2 - DENSITY_XXHIGH_R)) * 0.8888888888888888d)) + 480 : ((int) (((double) (i2 - 360)) * 0.8888888888888888d)) + 320 : ((int) (((double) (i2 - DENSITY_HIGH_R)) * 0.6666666666666666d)) + DENSITY_HIGH_R;
    }

    public static int translateDensity(int i2) {
        return (i2 <= DENSITY_HIGH_R || i2 > 320) ? (i2 <= 320 || i2 > 480) ? (i2 <= 480 || i2 > 640) ? i2 : ((int) (((double) (i2 - 480)) * 1.125d)) + DENSITY_XXHIGH_R : ((int) (((double) (i2 - 320)) * 1.125d)) + 360 : ((int) (((double) (i2 - DENSITY_HIGH_R)) * 1.5d)) + DENSITY_HIGH_R;
    }

    public void clearByKeys() {
        synchronized (this.mBitmapKeysLock) {
            try {
                for (int size = this.mBitmapKeys.size() - 1; size >= 0; size--) {
                    String strValueAt = this.mBitmapKeys.valueAt(size);
                    sBitmapsCache.remove(strValueAt);
                    sWeakRefBitmapsCache.remove(strValueAt);
                    this.mBitmapKeys.removeAt(size);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void finalize() throws Throwable {
        synchronized (sLock) {
            sRef--;
        }
        finish(sRef > 0);
        super.finalize();
    }

    public void finish(boolean z2) {
        if (!z2) {
            evictCacheSafety();
            synchronized (this.mBitmapKeysLock) {
                this.mBitmapKeys.clear();
            }
            sWeakRefBitmapsCache.clear();
        }
        synchronized (this.mLoadingBitmaps) {
            this.mLoadingBitmaps.clear();
        }
        this.mResourceLoader.finish();
    }

    public Bitmap getBitmap(String str) {
        BitmapInfo bitmapInfo = getBitmapInfo(str);
        if (bitmapInfo != null) {
            return bitmapInfo.mBitmap;
        }
        return null;
    }

    public BitmapInfo getBitmapInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BitmapInfo cache = getCache(str);
        return cache != null ? cache : loadBitmap(str);
    }

    public BitmapInfo getBitmapInfoAsync(String str, AsyncLoadListener asyncLoadListener) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BitmapInfo cache = getCache(str);
        if (cache != null) {
            return cache;
        }
        synchronized (this.mLoadingBitmaps) {
            try {
                if (!this.mLoadingBitmaps.contains(str)) {
                    BitmapInfo cache2 = getCache(str);
                    if (cache2 != null) {
                        return cache2;
                    }
                    this.mLoadingBitmaps.add(str);
                    MamlLog.i(LOG_TAG, "load image async: " + str);
                    new LoadBitmapAsyncTask(asyncLoadListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, str);
                }
                BitmapInfo bitmapInfo = new BitmapInfo();
                bitmapInfo.mLoading = true;
                return bitmapInfo;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Element getConfigRoot() {
        return this.mResourceLoader.getConfigRoot();
    }

    public Drawable getDrawable(Resources resources, String str) {
        Bitmap bitmap;
        BitmapInfo bitmapInfo = getBitmapInfo(str);
        if (bitmapInfo == null || (bitmap = bitmapInfo.mBitmap) == null) {
            return null;
        }
        if (bitmapInfo.mNinePatch != null) {
            NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(resources, bitmap, bitmap.getNinePatchChunk(), bitmapInfo.mPadding, str);
            ninePatchDrawable.setTargetDensity(this.mTargetDensity);
            return ninePatchDrawable;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmap);
        bitmapDrawable.setTargetDensity(this.mTargetDensity);
        return bitmapDrawable;
    }

    @Nullable
    public File getExtraFile(String str) {
        return this.mResourceLoader.getExtraFile(str);
    }

    public MemoryFile getFile(String str) {
        return this.mResourceLoader.getFile(str);
    }

    public final InputStream getInputStream(String str) {
        return this.mResourceLoader.getInputStream(str);
    }

    public long getLastPluralsUpdateTime() {
        return this.lastPluralsUpdateTime;
    }

    public Element getManifestRoot() {
        return this.mResourceLoader.getManifestRoot();
    }

    public NinePatch getNinePatch(String str) {
        BitmapInfo bitmapInfo = getBitmapInfo(str);
        if (bitmapInfo != null) {
            return bitmapInfo.mNinePatch;
        }
        return null;
    }

    public String getPathForLanguage(String str) {
        return this.mResourceLoader.getPathForLanguage(str);
    }

    public HashMap<LanguageHelper.Plurals, HashMap<Integer, String>> getPluralsMap() {
        return this.pluralsMap;
    }

    public ResourceLoader getResourceLoader() {
        return this.mResourceLoader;
    }

    public int getTargetDensity() {
        return this.mTargetDensity;
    }

    public void init() {
        this.mResourceLoader.init();
    }

    public void pause() {
    }

    public final boolean resourceExists(String str) {
        return this.mResourceLoader.resourceExists(str);
    }

    public void resume() {
    }

    public void setCacheSize(int i2) {
        sBitmapsCache.resize(i2);
    }

    public void setDefaultResourceDensity(int i2) {
        this.mDefaultResourceDensity = i2;
    }

    public void setExtraResource(String str, int i2) {
        this.mExtraResourceFolder = str;
        this.mExtraResourceDensity = i2;
    }

    public void setExtraResourceDensity(int i2) {
        this.mExtraResourceDensity = i2;
    }

    public void setLastPluralsUpdateTime(long j2) {
        this.lastPluralsUpdateTime = j2;
    }

    public void setLocal(Locale locale) {
        if (locale == null || locale.equals(this.mResourceLoader.getLocale())) {
            return;
        }
        this.mResourceLoader.setLocal(locale);
        finish(false);
    }

    public void setTargetDensity(int i2) {
        this.mTargetDensity = i2;
    }

    public final InputStream getInputStream(String str, long[] jArr) {
        return this.mResourceLoader.getInputStream(str, jArr);
    }

    public void clear(String str) {
        String str2 = this.mResourceLoader.getID() + str;
        sBitmapsCache.remove(str2);
        synchronized (this.mBitmapKeysLock) {
            this.mBitmapKeys.remove(str2);
        }
    }

    public void setExtraResource(String str) {
        this.mExtraResourceFolder = str;
    }

    public static class BitmapInfo {
        public final Bitmap mBitmap;
        public int mDensity;
        public String mKey;
        public long mLastVisitTime;
        public boolean mLoading;
        public final NinePatch mNinePatch;
        public final Rect mPadding;

        public BitmapInfo() {
            this.mBitmap = null;
            this.mPadding = null;
            this.mNinePatch = null;
        }

        public BitmapInfo(Bitmap bitmap, Rect rect) {
            this.mBitmap = bitmap;
            this.mPadding = rect;
            if (bitmap != null && bitmap.getNinePatchChunk() != null) {
                this.mNinePatch = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
            } else {
                this.mNinePatch = null;
            }
            this.mLastVisitTime = System.currentTimeMillis();
        }
    }
}
