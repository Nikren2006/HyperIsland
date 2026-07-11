package com.xiaomi.onetrack;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.onetrack.api.c;
import com.xiaomi.onetrack.d.d;
import com.xiaomi.onetrack.f.a;
import com.xiaomi.onetrack.util.DeviceUtil;
import com.xiaomi.onetrack.util.ab;
import com.xiaomi.onetrack.util.ad;
import com.xiaomi.onetrack.util.b;
import com.xiaomi.onetrack.util.l;
import com.xiaomi.onetrack.util.q;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CrashAnalysis {
    public static final String ANR_CRASH = "anr";
    public static final String JAVA_CRASH = "java";
    public static final String NATIVE_CRASH = "native";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2606a = "CrashAnalysis";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f2607b = "backtrace feature id:\n\t";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f2608c = "error reason:\n\t";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2609d = "Crash time: '";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f2610e = ".xcrash";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f2611f = 604800000;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int f2612g = 358400;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int f2613h = 10;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int f2614i = 20;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final String f2615j = "@[0-9a-fA-F]{1,10}";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final String f2616k = "\\$[0-9a-fA-F]{1,10}@[0-9a-fA-F]{1,10}";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final String f2617l = "0x[0-9a-fA-F]{1,10}";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final String f2618m = "\\d+[B,KB,MB]*";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final String f2619n = "((java:)|(length=)|(index=)|(Index:)|(Size:))\\d+";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static final int f2620o = 20;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static final boolean f2621p = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private static volatile CrashAnalysis f2622s;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final FileProcessor[] f2623q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private final c f2624r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private boolean f2625t;

    private CrashAnalysis(Context context, c cVar) {
        try {
            Object objNewInstance = Class.forName("xcrash.XCrash$InitParameters").getConstructor(null).newInstance(null);
            Boolean bool = Boolean.FALSE;
            a(objNewInstance, "setNativeDumpAllThreads", bool);
            a(objNewInstance, "setLogDir", getCrashPath());
            a(objNewInstance, "setNativeDumpMap", bool);
            a(objNewInstance, "setNativeDumpFds", bool);
            a(objNewInstance, "setJavaDumpAllThreads", bool);
            if (DeviceUtil.g()) {
                q.a(f2606a, "isMiTv:true");
                a(objNewInstance, "setAnrCheckProcessState", bool);
            } else {
                q.a(f2606a, "isMiTv:false");
            }
            Class.forName("xcrash.XCrash").getDeclaredMethod("init", Context.class, objNewInstance.getClass()).invoke(null, context.getApplicationContext(), objNewInstance);
            q.a(f2606a, "XCrash init success");
            this.f2625t = true;
        } catch (Throwable th) {
            q.c(f2606a, "XCrash init failed: ", th);
        }
        this.f2624r = cVar;
        this.f2623q = new FileProcessor[]{new FileProcessor("java"), new FileProcessor(ANR_CRASH), new FileProcessor(NATIVE_CRASH)};
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str, String str2) {
        int iIndexOf;
        int iIndexOf2;
        String strSubstring = "uncategoried";
        if (!TextUtils.isEmpty(str)) {
            try {
                if (str2.equals(ANR_CRASH)) {
                    int iIndexOf3 = str.indexOf(" tid=1 ");
                    if (iIndexOf3 != -1 && (iIndexOf = str.indexOf("\n  at ", iIndexOf3)) != -1 && (iIndexOf2 = str.indexOf(10, iIndexOf + 6)) != -1) {
                        strSubstring = str.substring(iIndexOf + 2, iIndexOf2);
                    }
                } else {
                    int iIndexOf4 = str.indexOf(f2608c);
                    if (iIndexOf4 != -1) {
                        int i2 = iIndexOf4 + 15;
                        int iIndexOf5 = str.indexOf("\n\n", i2);
                        if (iIndexOf5 != -1) {
                            strSubstring = str.substring(i2, iIndexOf5);
                        }
                    } else if (str2.equals(NATIVE_CRASH)) {
                        String strB = b.b(str);
                        if (!TextUtils.isEmpty(strB)) {
                            strSubstring = strB;
                        }
                    }
                }
            } catch (Exception e2) {
                q.b(f2606a, "getErrorReasonString error: " + e2.toString());
            }
        }
        return strSubstring;
    }

    public static String calculateJavaDigest(String str) {
        String[] strArrSplit = str.replaceAll("\\t", "").split("\\n");
        StringBuilder sb = new StringBuilder();
        int iMin = Math.min(strArrSplit.length, 20);
        for (int i2 = 0; i2 < iMin; i2++) {
            strArrSplit[i2] = strArrSplit[i2].replaceAll(f2619n, "$1XX").replaceAll("\\$[0-9a-fA-F]{1,10}@[0-9a-fA-F]{1,10}|@[0-9a-fA-F]{1,10}|0x[0-9a-fA-F]{1,10}", "XX").replaceAll(f2618m, "");
        }
        for (int i3 = 0; i3 < iMin && (!strArrSplit[i3].contains("...") || !strArrSplit[i3].contains("more")); i3++) {
            sb.append(strArrSplit[i3]);
            sb.append('\n');
        }
        return d.h(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(String str, String str2) {
        int i2;
        int iIndexOf;
        int iIndexOf2;
        String strSubstring = "";
        if (!TextUtils.isEmpty(str)) {
            try {
                if (str2.equals(ANR_CRASH)) {
                    int iIndexOf3 = str.indexOf(" tid=1 ");
                    if (iIndexOf3 != -1 && (iIndexOf2 = str.indexOf("\n\n", iIndexOf3)) != -1) {
                        strSubstring = calculateJavaDigest(str.substring(iIndexOf3, iIndexOf2));
                    }
                } else {
                    int iIndexOf4 = str.indexOf(f2607b);
                    if (iIndexOf4 != -1 && (iIndexOf = str.indexOf("\n\n", (i2 = iIndexOf4 + 23))) != -1) {
                        strSubstring = str.substring(i2, iIndexOf);
                    }
                }
            } catch (Exception e2) {
                q.b(f2606a, "calculateFeatureId error: " + e2.toString());
            }
        }
        return strSubstring;
    }

    private void e() throws Throwable {
        for (FileProcessor fileProcessor : this.f2623q) {
            fileProcessor.a();
        }
    }

    public static String getCrashPath() {
        return l.a();
    }

    public static CrashAnalysis getInstance(Context context, c cVar) {
        if (f2622s == null) {
            synchronized (CrashAnalysis.class) {
                try {
                    if (f2622s == null) {
                        f2622s = new CrashAnalysis(context, cVar);
                    }
                } finally {
                }
            }
        }
        return f2622s;
    }

    public boolean isSupport() {
        return this.f2625t;
    }

    public class FileProcessor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final List<File> f2627a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final String f2628b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final String f2629c;

        public FileProcessor(String str) {
            this.f2629c = str;
            this.f2628b = str + CrashAnalysis.f2610e;
        }

        public boolean a(File file) {
            if (!file.getName().contains(this.f2628b)) {
                return false;
            }
            this.f2627a.add(file);
            return true;
        }

        private String a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String[] strArrSplit = str.split("__");
            if (strArrSplit.length != 2) {
                return null;
            }
            String[] strArrSplit2 = strArrSplit[0].split("_");
            if (strArrSplit2.length == 3) {
                return strArrSplit2[2];
            }
            return null;
        }

        public void a() throws Throwable {
            for (int i2 = 0; i2 < this.f2627a.size(); i2++) {
                String absolutePath = this.f2627a.get(i2).getAbsoluteFile().getAbsolutePath();
                String strA = a(absolutePath);
                String[] strArrA = l.a(absolutePath, CrashAnalysis.f2612g);
                q.a(CrashAnalysis.f2606a, "crash content size: " + strArrA[1].length());
                if (!TextUtils.isEmpty(strArrA[1]) && CrashAnalysis.this.f2624r != null) {
                    String strD = CrashAnalysis.d(strArrA[1], this.f2629c);
                    String strC = CrashAnalysis.c(strArrA[1], this.f2629c);
                    long jD = CrashAnalysis.d(strArrA[1]);
                    Map<String, Object> mapC = CrashAnalysis.c(strArrA[0]);
                    q.a(CrashAnalysis.f2606a, "fileName: " + absolutePath);
                    q.a(CrashAnalysis.f2606a, "feature id: " + strD);
                    q.a(CrashAnalysis.f2606a, "error: " + strC);
                    q.a(CrashAnalysis.f2606a, "crashTimeStamp: " + jD);
                    StringBuilder sb = new StringBuilder();
                    sb.append("dynamicCommonProperty: ");
                    sb.append(mapC == null ? "" : mapC.toString());
                    q.a(CrashAnalysis.f2606a, sb.toString());
                    CrashAnalysis.this.f2624r.a(strArrA[1], strC, this.f2629c, strA, strD, jD, mapC);
                    l.a(new File(absolutePath));
                    q.a(CrashAnalysis.f2606a, "remove reported crash file");
                }
            }
        }
    }

    private long b() {
        long jC = ab.c();
        if (jC == 0) {
            q.a(f2606a, "no ticket data found, return max count");
            return 10L;
        }
        long jB = ad.b();
        if (jC / 100 != jB) {
            q.a(f2606a, "no today's ticket, return max count");
            return 10L;
        }
        long j2 = jC - (jB * 100);
        q.a(f2606a, "today's remain ticket is " + j2);
        return j2;
    }

    public static void a(Context context) {
        try {
            a.a(context.getApplicationContext());
            Class.forName("xcrash.XCrash").getDeclaredMethod("initHooker", Context.class, String.class).invoke(null, context.getApplicationContext(), getCrashPath());
            q.a(f2606a, "registerHook succeeded");
        } catch (Throwable th) {
            q.c(f2606a, "registerHook failed: ", th);
        }
    }

    private void a() {
        try {
            if (!d()) {
                q.a(f2606a, "no crash file found");
            } else {
                e();
            }
        } catch (Throwable th) {
            q.b(f2606a, "processCrash error: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long d(String str) {
        int i2;
        int iIndexOf;
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            int iIndexOf2 = str.indexOf(f2609d);
            if (iIndexOf2 == -1 || (iIndexOf = str.indexOf("'\n", (i2 = iIndexOf2 + 13))) == -1) {
                return 0L;
            }
            return b.a(str.substring(i2, iIndexOf));
        } catch (Exception e2) {
            q.b(f2606a, "getCrashTimeStamp error: " + e2.toString());
            return 0L;
        }
    }

    private void a(Object obj, String str, Object obj2) throws IllegalAccessException, InvocationTargetException {
        obj.getClass().getDeclaredMethod(str, obj2.getClass() == Boolean.class ? Boolean.TYPE : obj2.getClass()).invoke(obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, Object> c(String str) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str.substring(1));
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject.opt(next));
                }
            } catch (Exception e2) {
                q.b(f2606a, "getDynamicCommonProperty error：", e2);
            }
        }
        return map;
    }

    private boolean d() {
        boolean z2;
        Iterator<File> it;
        List<File> listC = c();
        long jB = b();
        if (listC == null || listC.size() <= 0) {
            z2 = false;
        } else {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jB2 = ab.b();
            long j2 = ad.f3470a;
            if (jB2 > jCurrentTimeMillis) {
                jB2 = jCurrentTimeMillis - ad.f3470a;
            }
            Iterator<File> it2 = listC.iterator();
            long j3 = 0;
            long j4 = 0;
            boolean z3 = false;
            while (it2.hasNext()) {
                File next = it2.next();
                long jLastModified = next.lastModified();
                if (jLastModified < jCurrentTimeMillis - j2 || jLastModified > jCurrentTimeMillis) {
                    it = it2;
                    q.a(f2606a, "remove obsolete crash files: " + next.getName());
                    l.a(next);
                } else {
                    if (jLastModified <= jB2) {
                        q.a(f2606a, "found already reported crash file, ignore");
                    } else if (jB > j3) {
                        FileProcessor[] fileProcessorArr = this.f2623q;
                        int length = fileProcessorArr.length;
                        int i2 = 0;
                        while (i2 < length) {
                            Iterator<File> it3 = it2;
                            if (fileProcessorArr[i2].a(next)) {
                                q.a(f2606a, "find crash file:" + next.getName());
                                jB--;
                                z3 = true;
                                if (j4 < jLastModified) {
                                    j4 = jLastModified;
                                }
                            }
                            i2++;
                            it2 = it3;
                        }
                    }
                    it = it2;
                }
                it2 = it;
                j2 = ad.f3470a;
                j3 = 0;
            }
            if (j4 > j3) {
                ab.c(j4);
            }
            z2 = z3;
        }
        if (z2) {
            a(jB);
        }
        return z2;
    }

    private void a(long j2) {
        ab.d((ad.b() * 100) + j2);
    }

    private List<File> c() {
        File[] fileArrListFiles = new File(getCrashPath()).listFiles();
        if (fileArrListFiles == null) {
            q.a(f2606a, "this path does not denote a directory, or if an I/O error occurs.");
            return null;
        }
        List<File> listAsList = Arrays.asList(fileArrListFiles);
        Collections.sort(listAsList, new Comparator<File>() { // from class: com.xiaomi.onetrack.CrashAnalysis.1
            @Override // java.util.Comparator
            public int compare(File file, File file2) {
                return (int) (file.lastModified() - file2.lastModified());
            }
        });
        int size = listAsList.size();
        if (size <= 20) {
            return listAsList;
        }
        int i2 = size - 20;
        for (int i3 = 0; i3 < i2; i3++) {
            l.a(listAsList.get(i3));
        }
        return listAsList.subList(i2, size);
    }
}
