package com.miui.maml.elements;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.miui.maml.ObjectFactory;
import com.miui.maml.ResourceManager;
import com.miui.maml.ScreenElementRoot;
import com.miui.maml.data.Expression;
import com.miui.maml.data.IndexedVariable;
import com.miui.maml.util.MamlLog;
import com.miui.maml.util.Utils;
import com.xiaomi.onetrack.util.aa;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import miuix.core.util.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BitmapProvider {
    private static final String LOG_TAG = "BitmapProvider";
    protected ScreenElementRoot mRoot;
    protected VersionedBitmap mVersionedBitmap = new VersionedBitmap(null);

    public static class AppIconProvider extends BitmapProvider {
        public static final String TAG_NAME = "ApplicationIcon";
        private String mCls;
        private boolean mNoIcon;
        private String mPkg;
        private String mSrc;

        public AppIconProvider(ScreenElementRoot screenElementRoot) {
            super(screenElementRoot);
        }

        private void parseSrc(String str) {
            this.mNoIcon = false;
            this.mVersionedBitmap.mBitmap = null;
            if (TextUtils.isEmpty(str)) {
                MamlLog.e("BitmapProvider", "invalid src of ApplicationIcon type: " + str);
                this.mNoIcon = true;
                return;
            }
            String[] strArrSplit = str.split(aa.f3429b);
            if (strArrSplit.length == 2) {
                this.mPkg = strArrSplit[0];
                this.mCls = strArrSplit[1];
            } else {
                if (strArrSplit.length == 1) {
                    this.mPkg = strArrSplit[0];
                    return;
                }
                MamlLog.e("BitmapProvider", "invalid src of ApplicationIcon type: " + str);
                this.mNoIcon = true;
            }
        }

        private void tryToSetBitmap() {
            try {
                Drawable drawableLoadIcon = this.mCls != null ? this.mRoot.getContext().mContext.getPackageManager().getActivityInfo(new ComponentName(this.mPkg, this.mCls), 786432).loadIcon(this.mRoot.getContext().mContext.getPackageManager()) : this.mRoot.getContext().mContext.getPackageManager().getApplicationIcon(this.mPkg);
                if (drawableLoadIcon instanceof BitmapDrawable) {
                    this.mVersionedBitmap.setBitmap(((BitmapDrawable) drawableLoadIcon).getBitmap());
                    return;
                }
                int intrinsicWidth = drawableLoadIcon.getIntrinsicWidth();
                int intrinsicHeight = drawableLoadIcon.getIntrinsicHeight();
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawableLoadIcon.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                drawableLoadIcon.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
                drawableLoadIcon.draw(canvas);
                this.mVersionedBitmap.setBitmap(bitmapCreateBitmap);
            } catch (PackageManager.NameNotFoundException unused) {
                MamlLog.e("BitmapProvider", "fail to get icon for src of ApplicationIcon type: " + this.mSrc);
                this.mNoIcon = true;
            }
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public VersionedBitmap getBitmap(String str, boolean z2, int i2, int i3) {
            if (!TextUtils.equals(str, this.mSrc)) {
                this.mSrc = str;
                parseSrc(str);
            }
            if (this.mVersionedBitmap.getBitmap() == null && !this.mNoIcon) {
                tryToSetBitmap();
            }
            return this.mVersionedBitmap;
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public void init(String str) {
            super.init(str);
            this.mSrc = str;
            parseSrc(str);
        }
    }

    public static class BitmapHolderProvider extends BitmapProvider {
        public static final String TAG_NAME = "BitmapHolder";
        private IBitmapHolder mBitmapHolder;
        private String mId;

        public BitmapHolderProvider(ScreenElementRoot screenElementRoot) {
            super(screenElementRoot);
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public VersionedBitmap getBitmap(String str, boolean z2, int i2, int i3) {
            IBitmapHolder iBitmapHolder = this.mBitmapHolder;
            if (iBitmapHolder != null) {
                return iBitmapHolder.getBitmap(this.mId);
            }
            return null;
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public void init(String str) {
            super.init(str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            int iIndexOf = str.indexOf(46);
            if (iIndexOf != -1) {
                String strSubstring = str.substring(0, iIndexOf);
                this.mId = str.substring(iIndexOf + 1);
                str = strSubstring;
            }
            Object objFindElement = this.mRoot.findElement(str);
            if (objFindElement instanceof IBitmapHolder) {
                this.mBitmapHolder = (IBitmapHolder) objFindElement;
            }
        }
    }

    public static class BitmapVariableProvider extends BitmapProvider {
        public static final String TAG_NAME = "BitmapVar";
        private String mCurSrc;
        private Expression mIndexExpression;
        private IndexedVariable mVar;

        public BitmapVariableProvider(ScreenElementRoot screenElementRoot) {
            super(screenElementRoot);
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public VersionedBitmap getBitmap(String str, boolean z2, int i2, int i3) {
            int i4;
            Bitmap bitmap = null;
            if (!Utils.equals(this.mCurSrc, str)) {
                this.mVar = null;
                this.mIndexExpression = null;
                if (!TextUtils.isEmpty(str)) {
                    int iIndexOf = str.indexOf(91);
                    int length = str.length();
                    if (iIndexOf != -1 && iIndexOf < length - 1 && str.charAt(i4) == ']') {
                        this.mIndexExpression = Expression.build(this.mRoot.getVariables(), str.substring(iIndexOf + 1, i4));
                    }
                    this.mVar = new IndexedVariable(this.mIndexExpression == null ? str : str.substring(0, iIndexOf), this.mRoot.getVariables(), false);
                }
                this.mCurSrc = str;
            }
            try {
                IndexedVariable indexedVariable = this.mVar;
                if (indexedVariable != null) {
                    Expression expression = this.mIndexExpression;
                    bitmap = expression != null ? (Bitmap) indexedVariable.getArr((int) expression.evaluate()) : (Bitmap) indexedVariable.get();
                }
            } catch (ClassCastException unused) {
                MamlLog.w("BitmapProvider", "fail to cast as Bitmap from object: " + str);
            }
            this.mVersionedBitmap.setBitmap(bitmap);
            return this.mVersionedBitmap;
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public void init(String str) {
            super.init(str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.mVar = new IndexedVariable(str, this.mRoot.getVariables(), false);
            this.mCurSrc = str;
        }
    }

    public static class FileSystemProvider extends UriProvider {
        public static final String TAG_NAME = "FileSystem";

        public FileSystemProvider(ScreenElementRoot screenElementRoot) {
            super(screenElementRoot);
        }

        @Override // com.miui.maml.elements.BitmapProvider.UriProvider, com.miui.maml.elements.BitmapProvider
        public VersionedBitmap getBitmap(String str, boolean z2, int i2, int i3) {
            if (TextUtils.isEmpty(str)) {
                this.mVersionedBitmap.setBitmap(null);
                return this.mVersionedBitmap;
            }
            URI uri = new File(str).toURI();
            if (uri != null) {
                return super.getBitmap(uri.toString(), z2, i2, i3);
            }
            this.mVersionedBitmap.setBitmap(null);
            return this.mVersionedBitmap;
        }
    }

    public interface IBitmapHolder {
        VersionedBitmap getBitmap(String str);
    }

    public static class ResourceImageProvider extends BitmapProvider {
        public static final String TAG_NAME = "ResourceImage";
        private ResourceManager.AsyncLoadListener mAsyncLoadListener;
        private String mCachedBitmapName;
        private int mCachedDensity;
        String mLoadingBitmapName;
        Object mSrcNameLock;

        public ResourceImageProvider(ScreenElementRoot screenElementRoot) {
            super(screenElementRoot);
            this.mSrcNameLock = new Object();
            this.mAsyncLoadListener = new ResourceManager.AsyncLoadListener() { // from class: com.miui.maml.elements.BitmapProvider.ResourceImageProvider.1
                @Override // com.miui.maml.ResourceManager.AsyncLoadListener
                public void onLoadComplete(String str, ResourceManager.BitmapInfo bitmapInfo) {
                    synchronized (ResourceImageProvider.this.mSrcNameLock) {
                        try {
                            if (TextUtils.equals(str, ResourceImageProvider.this.mLoadingBitmapName)) {
                                MamlLog.i("BitmapProvider", "load image async complete: " + str + " last cached " + ResourceImageProvider.this.mCachedBitmapName);
                                ResourceImageProvider.this.mVersionedBitmap.setBitmap(bitmapInfo == null ? null : bitmapInfo.mBitmap);
                                ResourceImageProvider.this.mCachedBitmapName = str;
                                ResourceImageProvider.this.mLoadingBitmapName = null;
                            } else {
                                MamlLog.i("BitmapProvider", "load image async complete: " + str + " not equals " + ResourceImageProvider.this.mLoadingBitmapName);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    ResourceImageProvider.this.mRoot.requestUpdate();
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ ResourceManager.BitmapInfo lambda$loadBitmapSync$0(String str) {
            return this.mRoot.getContext().mResourceManager.getBitmapInfo(str);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0022  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0024 A[Catch: all -> 0x001b, TryCatch #0 {all -> 0x001b, blocks: (B:5:0x0013, B:8:0x0018, B:16:0x002d, B:11:0x001d, B:15:0x0026, B:14:0x0024), top: B:20:0x0013 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void loadBitmapAsync(java.lang.String r5) {
            /*
                r4 = this;
                com.miui.maml.ScreenElementRoot r0 = r4.mRoot
                com.miui.maml.ScreenContext r0 = r0.getContext()
                com.miui.maml.ResourceManager r0 = r0.mResourceManager
                com.miui.maml.ResourceManager$AsyncLoadListener r1 = r4.mAsyncLoadListener
                com.miui.maml.ResourceManager$BitmapInfo r0 = r0.getBitmapInfoAsync(r5, r1)
                java.lang.Object r1 = r4.mSrcNameLock
                monitor-enter(r1)
                if (r0 == 0) goto L1d
                boolean r2 = r0.mLoading     // Catch: java.lang.Throwable -> L1b
                if (r2 != 0) goto L18
                goto L1d
            L18:
                r4.mLoadingBitmapName = r5     // Catch: java.lang.Throwable -> L1b
                goto L2d
            L1b:
                r4 = move-exception
                goto L2f
            L1d:
                com.miui.maml.elements.BitmapProvider$VersionedBitmap r2 = r4.mVersionedBitmap     // Catch: java.lang.Throwable -> L1b
                r3 = 0
                if (r0 != 0) goto L24
                r0 = r3
                goto L26
            L24:
                android.graphics.Bitmap r0 = r0.mBitmap     // Catch: java.lang.Throwable -> L1b
            L26:
                r2.setBitmap(r0)     // Catch: java.lang.Throwable -> L1b
                r4.mCachedBitmapName = r5     // Catch: java.lang.Throwable -> L1b
                r4.mLoadingBitmapName = r3     // Catch: java.lang.Throwable -> L1b
            L2d:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L1b
                return
            L2f:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L1b
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.maml.elements.BitmapProvider.ResourceImageProvider.loadBitmapAsync(java.lang.String):void");
        }

        private void loadBitmapSync(final String str) {
            CompletableFuture completableFutureSupplyAsync = CompletableFuture.supplyAsync(new Supplier() { // from class: com.miui.maml.elements.a
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.f2548a.lambda$loadBitmapSync$0(str);
                }
            });
            try {
                ResourceManager.BitmapInfo bitmapInfo = (ResourceManager.BitmapInfo) completableFutureSupplyAsync.get(1000L, TimeUnit.MILLISECONDS);
                this.mVersionedBitmap.setBitmap(bitmapInfo == null ? null : bitmapInfo.mBitmap);
                this.mCachedBitmapName = str;
            } catch (InterruptedException e2) {
                MamlLog.w("BitmapProvider", "getBitmap interrupted, src = " + str + " " + e2);
                Thread.currentThread().interrupt();
            } catch (ExecutionException e3) {
                MamlLog.w("BitmapProvider", "getBitmap execute error, src = " + str + " " + e3);
            } catch (TimeoutException e4) {
                MamlLog.w("BitmapProvider", "getBitmap execute timeout, src = " + str + " Exception=" + e4 + ", try again getBitmapInfoAsync");
                completableFutureSupplyAsync.cancel(true);
                loadBitmapAsync(str);
            }
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public void finish() {
            super.finish();
            synchronized (this.mSrcNameLock) {
                this.mLoadingBitmapName = null;
                this.mCachedBitmapName = null;
                this.mVersionedBitmap.reset();
            }
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public VersionedBitmap getBitmap(String str, boolean z2, int i2, int i3) {
            Bitmap bitmap = this.mVersionedBitmap.getBitmap();
            int targetDensity = this.mRoot.getContext().mResourceManager.getTargetDensity();
            boolean z3 = targetDensity > 0 && targetDensity != this.mCachedDensity;
            if (z3) {
                this.mCachedDensity = targetDensity;
            }
            if ((bitmap != null && bitmap.isRecycled()) || !TextUtils.equals(this.mCachedBitmapName, str) || z3) {
                if (z2) {
                    loadBitmapSync(str);
                } else {
                    loadBitmapAsync(str);
                }
            }
            return this.mVersionedBitmap;
        }
    }

    public static class UriProvider extends BitmapProvider {
        public static final String TAG_NAME = "Uri";
        private String mCachedBitmapUri;
        private String mCurLoadingBitmapUri;
        private Object mLock;

        public class LoaderAsyncTask extends AsyncTask<Object, Object, Bitmap> {
            private int mHeight;
            private String mUri;
            private int mWidth;

            public LoaderAsyncTask(String str, int i2, int i3) {
                this.mUri = str;
                this.mWidth = i2;
                this.mHeight = i3;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.AsyncTask
            public Bitmap doInBackground(Object... objArr) throws Throwable {
                Bitmap bitmapFromUri = UriProvider.this.getBitmapFromUri(Uri.parse(this.mUri), this.mWidth, this.mHeight);
                if (bitmapFromUri == null) {
                    MamlLog.w("BitmapProvider", "fail to decode bitmap: " + this.mUri);
                }
                synchronized (UriProvider.this.mLock) {
                    try {
                        if (TextUtils.equals(this.mUri, UriProvider.this.mCurLoadingBitmapUri)) {
                            UriProvider.this.mVersionedBitmap.setBitmap(bitmapFromUri);
                            UriProvider uriProvider = UriProvider.this;
                            uriProvider.mCachedBitmapUri = uriProvider.mCurLoadingBitmapUri;
                            UriProvider.this.mRoot.requestUpdate();
                            UriProvider.this.mCurLoadingBitmapUri = null;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return bitmapFromUri;
            }
        }

        public UriProvider(ScreenElementRoot screenElementRoot) {
            super(screenElementRoot);
            this.mLock = new Object();
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public void finish() {
            super.finish();
            synchronized (this.mLock) {
                this.mCachedBitmapUri = null;
                this.mCurLoadingBitmapUri = null;
                this.mVersionedBitmap.reset();
            }
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public VersionedBitmap getBitmap(String str, boolean z2, int i2, int i3) {
            if (TextUtils.isEmpty(str)) {
                this.mVersionedBitmap.setBitmap(null);
                return this.mVersionedBitmap;
            }
            Bitmap bitmap = this.mVersionedBitmap.getBitmap();
            if ((bitmap != null && bitmap.isRecycled()) || !TextUtils.equals(this.mCachedBitmapUri, str)) {
                synchronized (this.mLock) {
                    try {
                        if (!TextUtils.equals(this.mCurLoadingBitmapUri, str) && !TextUtils.equals(this.mCachedBitmapUri, str)) {
                            this.mCurLoadingBitmapUri = str;
                            new LoaderAsyncTask(str, i2, i3).execute(new Object[0]);
                        }
                    } finally {
                    }
                }
            }
            this.mVersionedBitmap.setBitmap(bitmap);
            return this.mVersionedBitmap;
        }
    }

    public static class VersionedBitmap {
        private Bitmap mBitmap;
        private int mVersion;

        public VersionedBitmap(Bitmap bitmap) {
            this.mBitmap = bitmap;
        }

        public static boolean equals(VersionedBitmap versionedBitmap, VersionedBitmap versionedBitmap2) {
            return versionedBitmap != null && versionedBitmap2 != null && versionedBitmap.mBitmap == versionedBitmap2.mBitmap && versionedBitmap.mVersion == versionedBitmap2.mVersion;
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public void reset() {
            this.mBitmap = null;
            this.mVersion = 0;
        }

        public void set(VersionedBitmap versionedBitmap) {
            if (versionedBitmap == null) {
                reset();
            } else {
                this.mBitmap = versionedBitmap.mBitmap;
                this.mVersion = versionedBitmap.mVersion;
            }
        }

        public boolean setBitmap(Bitmap bitmap) {
            if (bitmap != this.mBitmap || bitmap == null) {
                this.mBitmap = bitmap;
                this.mVersion++;
            }
            return bitmap != this.mBitmap;
        }

        public int updateVersion() {
            int i2 = this.mVersion;
            this.mVersion = i2 + 1;
            return i2;
        }
    }

    public static class VirtualScreenProvider extends BitmapProvider {
        public static final String TAG_NAME = "VirtualScreen";
        private VirtualScreen mVirtualScreen;

        public VirtualScreenProvider(ScreenElementRoot screenElementRoot) {
            super(screenElementRoot);
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public VersionedBitmap getBitmap(String str, boolean z2, int i2, int i3) {
            VersionedBitmap versionedBitmap = this.mVersionedBitmap;
            VirtualScreen virtualScreen = this.mVirtualScreen;
            versionedBitmap.setBitmap(virtualScreen != null ? virtualScreen.getBitmap() : null);
            return this.mVersionedBitmap;
        }

        @Override // com.miui.maml.elements.BitmapProvider
        public void init(String str) {
            super.init(str);
            ScreenElement screenElementFindElement = this.mRoot.findElement(str);
            if (screenElementFindElement instanceof VirtualScreen) {
                this.mVirtualScreen = (VirtualScreen) screenElementFindElement;
            }
        }
    }

    public BitmapProvider(ScreenElementRoot screenElementRoot) {
        this.mRoot = screenElementRoot;
    }

    private static int computeSampleSize(BitmapFactory.Options options, int i2) {
        int i3 = 1;
        while (true) {
            int i4 = i3 * 2;
            if (i4 > Math.sqrt((((double) options.outHeight) * ((double) options.outWidth)) / ((double) i2))) {
                return i3;
            }
            i3 = i4;
        }
    }

    public static BitmapProvider create(ScreenElementRoot screenElementRoot, String str) {
        BitmapProvider bitmapProviderCreate;
        str.hashCode();
        switch (str) {
            case "BitmapVar":
                return new BitmapVariableProvider(screenElementRoot);
            case "BitmapHolder":
                return new BitmapHolderProvider(screenElementRoot);
            case "FileSystem":
                return new FileSystemProvider(screenElementRoot);
            case "Uri":
                return new UriProvider(screenElementRoot);
            case "VirtualScreen":
                return new VirtualScreenProvider(screenElementRoot);
            case "ResourceImage":
                return new ResourceImageProvider(screenElementRoot);
            case "ApplicationIcon":
                return new AppIconProvider(screenElementRoot);
            default:
                ObjectFactory.BitmapProviderFactory bitmapProviderFactory = (ObjectFactory.BitmapProviderFactory) screenElementRoot.getContext().getObjectFactory("BitmapProvider");
                return (bitmapProviderFactory == null || (bitmapProviderCreate = bitmapProviderFactory.create(screenElementRoot, str)) == null) ? new ResourceImageProvider(screenElementRoot) : bitmapProviderCreate;
        }
    }

    public void finish() {
        this.mVersionedBitmap.reset();
    }

    public VersionedBitmap getBitmap(String str, boolean z2, int i2, int i3) {
        return this.mVersionedBitmap;
    }

    public Bitmap getBitmapFromUri(Uri uri, int i2, int i3) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStreamOpenInputStream2;
        InputStream inputStream = null;
        try {
            inputStreamOpenInputStream2 = this.mRoot.getContext().mContext.getContentResolver().openInputStream(uri);
        } catch (Exception e2) {
            e = e2;
            inputStreamOpenInputStream = null;
            inputStreamOpenInputStream2 = null;
        } catch (Throwable th) {
            th = th;
            inputStreamOpenInputStream = null;
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(inputStreamOpenInputStream);
            throw th;
        }
        try {
            if (i2 <= 0 || i3 <= 0) {
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream2, null, null);
                IOUtils.closeQuietly(inputStreamOpenInputStream2);
                IOUtils.closeQuietly((InputStream) null);
                return bitmapDecodeStream;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStreamOpenInputStream2, null, options);
            options.inSampleSize = computeSampleSize(options, i2 * i3);
            options.inJustDecodeBounds = false;
            options.outHeight = i3;
            options.outWidth = i2;
            inputStreamOpenInputStream = this.mRoot.getContext().mContext.getContentResolver().openInputStream(uri);
            try {
                try {
                    Bitmap bitmapDecodeStream2 = BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
                    IOUtils.closeQuietly(inputStreamOpenInputStream2);
                    IOUtils.closeQuietly(inputStreamOpenInputStream);
                    return bitmapDecodeStream2;
                } catch (Exception e3) {
                    e = e3;
                    MamlLog.e("BitmapProvider", "getBitmapFromUri Exception", e);
                    IOUtils.closeQuietly(inputStreamOpenInputStream2);
                    IOUtils.closeQuietly(inputStreamOpenInputStream);
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = inputStreamOpenInputStream2;
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly(inputStreamOpenInputStream);
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            inputStreamOpenInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStreamOpenInputStream = null;
            inputStream = inputStreamOpenInputStream2;
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(inputStreamOpenInputStream);
            throw th;
        }
    }

    public void init(String str) {
        reset();
    }

    public void reset() {
    }
}
