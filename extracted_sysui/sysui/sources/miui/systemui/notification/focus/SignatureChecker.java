package miui.systemui.notification.focus;

import H0.d;
import H0.e;
import I0.AbstractC0181i;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.util.Log;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class SignatureChecker {
    public static final Companion Companion = new Companion(null);
    private static final int FIXED_CACHE_SIZE = 50;
    private static final String PKG_SYSTEM_UI = "com.android.systemui";
    private static final String TAG = "SignatureUtils";
    private final d packageManager$delegate;
    private Map<String, Boolean> resultCacheMap;
    private final d sysUISignature$delegate;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.SignatureChecker$toSHA256$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final CharSequence invoke(byte b2) {
            String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b2)}, 1));
            n.f(str, "format(...)");
            return str;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).byteValue());
        }
    }

    public SignatureChecker(Context sysUiContext) {
        n.g(sysUiContext, "sysUiContext");
        this.packageManager$delegate = e.b(new SignatureChecker$packageManager$2(sysUiContext));
        this.sysUISignature$delegate = e.b(new SignatureChecker$sysUISignature$2(this));
        this.resultCacheMap = new LinkedHashMap<String, Boolean>() { // from class: miui.systemui.notification.focus.SignatureChecker$resultCacheMap$1
            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ boolean containsKey(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return containsKey((String) obj);
                }
                return false;
            }

            public /* bridge */ boolean containsValue(Boolean bool) {
                return super.containsValue((Object) bool);
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Set<Map.Entry<String, Boolean>> entrySet() {
                return getEntries();
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Boolean get(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return get((String) obj);
                }
                return null;
            }

            public /* bridge */ Set<Map.Entry<String, Boolean>> getEntries() {
                return super.entrySet();
            }

            public /* bridge */ Set<String> getKeys() {
                return super.keySet();
            }

            public final /* bridge */ Boolean getOrDefault(Object obj, Boolean bool) {
                return !(obj == null ? true : obj instanceof String) ? bool : getOrDefault((String) obj, bool);
            }

            public /* bridge */ int getSize() {
                return super.size();
            }

            public /* bridge */ Collection<Boolean> getValues() {
                return super.values();
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Set<String> keySet() {
                return getKeys();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Boolean remove(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return remove((String) obj);
                }
                return null;
            }

            @Override // java.util.LinkedHashMap
            public boolean removeEldestEntry(Map.Entry<String, Boolean> entry) {
                return size() > 50;
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ int size() {
                return getSize();
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Collection<Boolean> values() {
                return getValues();
            }

            public /* bridge */ boolean containsKey(String str) {
                return super.containsKey((Object) str);
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ boolean containsValue(Object obj) {
                if (obj == null ? true : obj instanceof Boolean) {
                    return containsValue((Boolean) obj);
                }
                return false;
            }

            public /* bridge */ Boolean get(String str) {
                return (Boolean) super.get((Object) str);
            }

            public /* bridge */ Boolean getOrDefault(String str, Boolean bool) {
                return (Boolean) super.getOrDefault((Object) str, bool);
            }

            public /* bridge */ Boolean remove(String str) {
                return (Boolean) super.remove((Object) str);
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ /* synthetic */ Object get(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return get((String) obj);
                }
                return null;
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map
            public final /* bridge */ /* synthetic */ Object getOrDefault(Object obj, Object obj2) {
                return !(obj == null ? true : obj instanceof String) ? obj2 : getOrDefault((String) obj, (Boolean) obj2);
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ /* synthetic */ Object remove(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return remove((String) obj);
                }
                return null;
            }

            @Override // java.util.HashMap, java.util.Map
            public final /* bridge */ boolean remove(Object obj, Object obj2) {
                if (!(obj == null ? true : obj instanceof String)) {
                    return false;
                }
                if (obj2 != null ? obj2 instanceof Boolean : true) {
                    return remove((String) obj, (Boolean) obj2);
                }
                return false;
            }

            public /* bridge */ boolean remove(String str, Boolean bool) {
                return super.remove((Object) str, (Object) bool);
            }
        };
    }

    private final PackageInfo getPackageInfo(PackageManager packageManager, String str, int i2) {
        if (packageManager == null) {
            return null;
        }
        try {
            return packageManager.getPackageInfo(str, PackageManager.PackageInfoFlags.of(i2));
        } catch (Exception e2) {
            Log.e(TAG, "getPackageInfo exception", e2);
            return null;
        }
    }

    public static /* synthetic */ PackageInfo getPackageInfo$default(SignatureChecker signatureChecker, PackageManager packageManager, String str, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return signatureChecker.getPackageInfo(packageManager, str, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PackageManager getPackageManager() {
        Object value = this.packageManager$delegate.getValue();
        n.f(value, "getValue(...)");
        return (PackageManager) value;
    }

    private final Signature[] getSignatures(PackageManager packageManager, String str) {
        SigningInfo signingInfo;
        PackageInfo packageInfo = getPackageInfo(packageManager, str, 134217728);
        if (packageInfo == null || (signingInfo = packageInfo.signingInfo) == null) {
            return null;
        }
        return signingInfo.getApkContentsSigners();
    }

    private final Set<String> getSysUISignature() {
        return (Set) this.sysUISignature$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> getSysUISignatures(PackageManager packageManager) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Signature[] signatures = getSignatures(packageManager, PKG_SYSTEM_UI);
        if (signatures != null) {
            for (Signature signature : signatures) {
                byte[] byteArray = signature.toByteArray();
                n.f(byteArray, "toByteArray(...)");
                String sha256 = toSHA256(byteArray);
                if (sha256 != null) {
                    linkedHashSet.add(sha256);
                }
            }
        }
        return linkedHashSet;
    }

    private final String toSHA256(byte[] bArr) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(bArr);
            n.d(bArrDigest);
            return AbstractC0181i.G(bArrDigest, "", null, null, 0, null, AnonymousClass1.INSTANCE, 30, null);
        } catch (Exception e2) {
            Log.e(TAG, "ByteArray.toSHA256 error.", e2);
            return null;
        }
    }

    public final boolean checkSignatures(String packageName) {
        n.g(packageName, "packageName");
        if (getSysUISignature().isEmpty()) {
            Log.d(TAG, "checkSignatures: " + packageName + " , sysUISignature is invalid");
            return false;
        }
        if (this.resultCacheMap.containsKey(packageName)) {
            Boolean bool = this.resultCacheMap.get(packageName);
            boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
            Log.d(TAG, "getCacheResult: " + packageName + ", result: " + zBooleanValue);
            return zBooleanValue;
        }
        boolean zCheckSignatures = checkSignatures(getPackageManager(), packageName, getSysUISignature());
        this.resultCacheMap.put(packageName, Boolean.valueOf(zCheckSignatures));
        Log.d(TAG, "checkSignatures: " + packageName + ", result: " + zCheckSignatures);
        return zCheckSignatures;
    }

    private final boolean checkSignatures(PackageManager packageManager, String str, Set<String> set) {
        Signature[] signatures = getSignatures(packageManager, str);
        if (signatures == null) {
            return false;
        }
        for (Signature signature : signatures) {
            byte[] byteArray = signature.toByteArray();
            n.f(byteArray, "toByteArray(...)");
            String sha256 = toSHA256(byteArray);
            if (sha256 != null ? set.contains(sha256) : false) {
                return true;
            }
        }
        return false;
    }
}
