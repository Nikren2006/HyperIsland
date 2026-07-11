package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.tracing.Trace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class FontProvider {
    private static final LruCache<ProviderCacheKey, ProviderInfo> sProviderCache = new LruCache<>(2);
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator() { // from class: androidx.core.provider.a
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FontProvider.lambda$static$0((byte[]) obj, (byte[]) obj2);
        }
    };

    public interface ContentQueryWrapper {
        static ContentQueryWrapper make(Context context, Uri uri) {
            return new ContentQueryWrapperApi24Impl(context, uri);
        }

        void close();

        Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);
    }

    public static class ContentQueryWrapperApi16Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        public ContentQueryWrapperApi16Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.release();
            }
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e2) {
                Log.w("FontsProvider", "Unable to query the content provider", e2);
                return null;
            }
        }
    }

    @RequiresApi(24)
    public static class ContentQueryWrapperApi24Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        public ContentQueryWrapperApi24Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e2) {
                Log.w("FontsProvider", "Unable to query the content provider", e2);
                return null;
            }
        }
    }

    public static class ProviderCacheKey {
        String mAuthority;
        List<List<byte[]>> mCertificates;
        String mPackageName;

        public ProviderCacheKey(String str, String str2, List<List<byte[]>> list) {
            this.mAuthority = str;
            this.mPackageName = str2;
            this.mCertificates = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProviderCacheKey)) {
                return false;
            }
            ProviderCacheKey providerCacheKey = (ProviderCacheKey) obj;
            return Objects.equals(this.mAuthority, providerCacheKey.mAuthority) && Objects.equals(this.mPackageName, providerCacheKey.mPackageName) && Objects.equals(this.mCertificates, providerCacheKey.mCertificates);
        }

        public int hashCode() {
            return Objects.hash(this.mAuthority, this.mPackageName, this.mCertificates);
        }
    }

    private FontProvider() {
    }

    @VisibleForTesting
    public static void clearProviderCache() {
        sProviderCache.evictAll();
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!Arrays.equals(list.get(i2), list2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        return fontRequest.getCertificates() != null ? fontRequest.getCertificates() : FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    public static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context context, List<FontRequest> list, CancellationSignal cancellationSignal) {
        Trace.beginSection("FontProvider.getFontFamilyResult");
        try {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                FontRequest fontRequest = list.get(i2);
                ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
                if (provider == null) {
                    return FontsContractCompat.FontFamilyResult.create(1, (FontsContractCompat.FontInfo[]) null);
                }
                arrayList.add(query(context, fontRequest, provider.authority, cancellationSignal));
            }
            return FontsContractCompat.FontFamilyResult.create(0, arrayList);
        } finally {
            Trace.endSection();
        }
    }

    @VisibleForTesting
    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) {
        Trace.beginSection("FontProvider.getProvider");
        try {
            List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
            ProviderCacheKey providerCacheKey = new ProviderCacheKey(fontRequest.getProviderAuthority(), fontRequest.getProviderPackage(), certificates);
            ProviderInfo providerInfo = sProviderCache.get(providerCacheKey);
            if (providerInfo != null) {
                return providerInfo;
            }
            String providerAuthority = fontRequest.getProviderAuthority();
            ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
            if (providerInfoResolveContentProvider == null) {
                throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
            }
            if (!providerInfoResolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
                throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
            }
            List<byte[]> listConvertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
            Collections.sort(listConvertToByteArrayList, sByteArrayComparator);
            for (int i2 = 0; i2 < certificates.size(); i2++) {
                ArrayList arrayList = new ArrayList(certificates.get(i2));
                Collections.sort(arrayList, sByteArrayComparator);
                if (equalsByteArrayList(listConvertToByteArrayList, arrayList)) {
                    sProviderCache.put(providerCacheKey, providerInfoResolveContentProvider);
                    return providerInfoResolveContentProvider;
                }
            }
            Trace.endSection();
            return null;
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            byte b3 = bArr2[i2];
            if (b2 != b3) {
                return b2 - b3;
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00de  */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.core.provider.FontsContractCompat.FontInfo[] query(android.content.Context r16, androidx.core.provider.FontRequest r17, java.lang.String r18, android.os.CancellationSignal r19) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontProvider.query(android.content.Context, androidx.core.provider.FontRequest, java.lang.String, android.os.CancellationSignal):androidx.core.provider.FontsContractCompat$FontInfo[]");
    }
}
