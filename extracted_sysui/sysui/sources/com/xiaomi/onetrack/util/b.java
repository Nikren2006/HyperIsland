package com.xiaomi.onetrack.util;

import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.system.Os;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final String f3481a = "3.0.2";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final String f3482b = "OneTrack 3.0.2";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final String f3483c = "CrashUtil";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f3484d = "*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3485e = "--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f3486f = "+++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static final String f3487g = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f3488h = "java";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static final String f3489i = "tombstone";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    static final String f3490j = ".java.xcrash";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f3491k = "dynamic common property:";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f3492l = "dynamic common property:\n\t";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final String f3493m = "%21s %8s\n";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final String f3494n = "%21s %8s %21s %8s\n";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static final String[] f3495o = {"/data/local/su", "/data/local/bin/su", "/data/local/xbin/su", "/system/xbin/su", "/system/bin/su", "/system/bin/.ext/su", "/system/bin/failsafe/su", "/system/sd/xbin/su", "/system/usr/we-need-root/su", "/sbin/su", "/su/bin/su"};

    private b() {
    }

    public static String a(Context context, int i2) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i2 + "/cmdline"));
            try {
                String line = bufferedReader.readLine();
                if (!TextUtils.isEmpty(line)) {
                    String strTrim = line.trim();
                    if (!TextUtils.isEmpty(strTrim)) {
                        try {
                            bufferedReader.close();
                        } catch (Exception unused) {
                        }
                        return strTrim;
                    }
                }
            } catch (Exception unused2) {
                if (bufferedReader != null) {
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception unused3) {
                    }
                }
                throw th;
            }
        } catch (Exception unused4) {
            bufferedReader = null;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
        try {
            bufferedReader.close();
        } catch (Exception unused5) {
        }
        return null;
    }

    public static String b() {
        return TextUtils.join(aa.f3429b, Build.SUPPORTED_ABIS);
    }

    public static String c() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Process Summary (From: android.os.Debug.MemoryInfo)\n");
        Locale locale = Locale.US;
        sb.append(String.format(locale, f3493m, "", "Pss(KB)"));
        sb.append(String.format(locale, f3493m, "", "------"));
        try {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            sb.append(String.format(locale, f3493m, "Java Heap:", memoryInfo.getMemoryStat("summary.java-heap")));
            sb.append(String.format(locale, f3493m, "Native Heap:", memoryInfo.getMemoryStat("summary.native-heap")));
            sb.append(String.format(locale, f3493m, "Code:", memoryInfo.getMemoryStat("summary.code")));
            sb.append(String.format(locale, f3493m, "Stack:", memoryInfo.getMemoryStat("summary.stack")));
            sb.append(String.format(locale, f3493m, "Graphics:", memoryInfo.getMemoryStat("summary.graphics")));
            sb.append(String.format(locale, f3493m, "Private Other:", memoryInfo.getMemoryStat("summary.private-other")));
            sb.append(String.format(locale, f3493m, "System:", memoryInfo.getMemoryStat("summary.system")));
            sb.append(String.format(locale, f3494n, "TOTAL:", memoryInfo.getMemoryStat("summary.total-pss"), "TOTAL SWAP:", memoryInfo.getMemoryStat("summary.total-swap")));
        } catch (Exception e2) {
            q.b(f3483c, "CrashUtil getProcessMemoryInfo failed", e2);
        }
        return sb.toString();
    }

    public static String d() {
        return "memory info:\n System Summary (From: /proc/meminfo)\n" + c("/proc/meminfo") + "-\n Process Status (From: /proc/PID/status)\n" + c("/proc/self/status") + "-\n Process Limits (From: /proc/PID/limits)\n" + c("/proc/self/limits") + "-\n" + c() + "\n";
    }

    public static String e() {
        return "network info:\nNot supported on Android Q (API level 29) and later.\n\n";
    }

    public static String f() {
        String str;
        StringBuilder sb = new StringBuilder("open files:\n");
        try {
            File[] fileArrListFiles = new File("/proc/self/fd").listFiles(new c());
            if (fileArrListFiles != null) {
                int i2 = 0;
                for (File file : fileArrListFiles) {
                    try {
                        str = Os.readlink(file.getAbsolutePath());
                    } catch (Exception unused) {
                        str = null;
                    }
                    sb.append("    fd ");
                    sb.append(file.getName());
                    sb.append(": ");
                    sb.append(TextUtils.isEmpty(str) ? "???" : str.trim());
                    sb.append('\n');
                    i2++;
                    if (i2 > 1024) {
                        break;
                    }
                }
                if (fileArrListFiles.length > 1024) {
                    sb.append("    ......\n");
                }
                sb.append("    (number of FDs: ");
                sb.append(fileArrListFiles.length);
                sb.append(")\n");
            }
        } catch (Exception unused2) {
        }
        sb.append('\n');
        return sb.toString();
    }

    public static String b(String str) {
        int iIndexOf;
        String[] strArrSplit;
        try {
            int iIndexOf2 = str.indexOf("\nbacktrace:\n");
            if (iIndexOf2 == -1 || (iIndexOf = str.indexOf("\n\n", iIndexOf2 + 12)) == -1) {
                return "";
            }
            String strSubstring = str.substring(iIndexOf2, iIndexOf);
            if (TextUtils.isEmpty(strSubstring.trim()) || (strArrSplit = strSubstring.split("\n")) == null || strArrSplit.length <= 0) {
                return "";
            }
            for (String str2 : strArrSplit) {
                int iIndexOf3 = str2.indexOf("(");
                if (iIndexOf3 != -1) {
                    String strSubstring2 = str2.substring(iIndexOf3);
                    q.a(f3483c, "getErrorReason->errorReason: " + strSubstring2);
                    return strSubstring2;
                }
            }
            return "";
        } catch (Exception e2) {
            q.b(f3483c, "getErrorReason error, e: ", e2);
            return "";
        }
    }

    public static boolean a() {
        try {
            for (String str : f3495o) {
                if (new File(str).exists()) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static String a(Context context) {
        String str;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            str = null;
        }
        return TextUtils.isEmpty(str) ? "unknown" : str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v7 */
    private static String a(String str, int i2) throws Throwable {
        BufferedReader bufferedReader;
        int i3;
        StringBuilder sb = new StringBuilder();
        ?? r12 = 0;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader(str));
                    i3 = 0;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception unused) {
        }
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String strTrim = line.trim();
                if (strTrim.length() > 0) {
                    i3++;
                    if (i2 == 0 || i3 <= i2) {
                        sb.append("  ");
                        sb.append(strTrim);
                        sb.append("\n");
                    }
                }
            } catch (Exception e3) {
                e = e3;
                bufferedReader2 = bufferedReader;
                q.c(f3483c, "CrashUtil getInfo(" + str + ") failed", e);
                r12 = bufferedReader2;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                    r12 = bufferedReader2;
                }
            } catch (Throwable th2) {
                th = th2;
                r12 = bufferedReader;
                if (r12 != 0) {
                    try {
                        r12.close();
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
            return sb.toString();
        }
        if (i2 > 0 && i3 > i2) {
            sb.append("  ......\n");
            sb.append("  (number of records: ");
            sb.append(i3);
            sb.append(")\n");
        }
        bufferedReader.close();
        r12 = i3;
        return sb.toString();
    }

    private static String c(String str) {
        return a(str, 0);
    }

    public static String a(Date date, Date date2, String str, String str2, String str3) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(f3487g, Locale.US);
        StringBuilder sb = new StringBuilder();
        sb.append("*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***\nTombstone maker: 'OneTrack 3.0.2'\nCrash type: '");
        sb.append(str);
        sb.append("'\nStart time: '");
        sb.append(simpleDateFormat.format(date));
        sb.append("'\nCrash time: '");
        sb.append(simpleDateFormat.format(date2));
        sb.append("'\nApp ID: '");
        sb.append(str2);
        sb.append("'\nApp version: '");
        sb.append(str3);
        sb.append("'\nRooted: '");
        sb.append(a() ? "Yes" : "No");
        sb.append("'\nAPI level: '");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("'\nOS version: '");
        sb.append(Build.VERSION.RELEASE);
        sb.append("'\nABI list: '");
        sb.append(b());
        sb.append("'\nManufacturer: '");
        sb.append(Build.MANUFACTURER);
        sb.append("'\nBrand: '");
        sb.append(Build.BRAND);
        sb.append("'\nModel: '");
        sb.append(Build.MODEL);
        sb.append("'\nBuild fingerprint: '");
        sb.append(Build.FINGERPRINT);
        sb.append("'\n");
        return sb.toString();
    }

    public static String a(int i2, int i3, int i4) throws Throwable {
        int iMyPid = Process.myPid();
        StringBuilder sb = new StringBuilder();
        sb.append("logcat:\n");
        if (i2 > 0) {
            a(iMyPid, sb, "main", i2, 'D');
        }
        if (i3 > 0) {
            a(iMyPid, sb, "system", i3, 'W');
        }
        if (i4 > 0) {
            a(iMyPid, sb, "events", i3, 'I');
        }
        sb.append("\n");
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(int r3, java.lang.StringBuilder r4, java.lang.String r5, int r6, char r7) throws java.lang.Throwable {
        /*
            java.lang.String r3 = java.lang.Integer.toString(r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = " "
            r0.append(r1)
            r0.append(r3)
            r0.append(r1)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r2 = "/system/bin/logcat"
            r0.add(r2)
            java.lang.String r2 = "-b"
            r0.add(r2)
            r0.add(r5)
            java.lang.String r2 = "-d"
            r0.add(r2)
            java.lang.String r2 = "-v"
            r0.add(r2)
            java.lang.String r2 = "threadtime"
            r0.add(r2)
            java.lang.String r2 = "-t"
            r0.add(r2)
            java.lang.String r6 = java.lang.Integer.toString(r6)
            r0.add(r6)
            java.lang.String r6 = "--pid"
            r0.add(r6)
            r0.add(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "*:"
            r3.append(r6)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            r0.add(r3)
            java.lang.Object[] r3 = r0.toArray()
            java.lang.String r6 = "--------- tail end of log "
            r4.append(r6)
            r4.append(r5)
            java.lang.String r5 = " ("
            r4.append(r5)
            java.lang.String r3 = android.text.TextUtils.join(r1, r3)
            r4.append(r3)
            java.lang.String r3 = ")\n"
            r4.append(r3)
            r3 = 0
            java.lang.ProcessBuilder r5 = new java.lang.ProcessBuilder     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            r6 = 0
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            r5.<init>(r6)     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            java.lang.ProcessBuilder r5 = r5.command(r0)     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            java.lang.Process r5 = r5.start()     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            java.io.InputStream r5 = r5.getInputStream()     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            r7.<init>(r5)     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            r6.<init>(r7)     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
        L99:
            java.lang.String r3 = r6.readLine()     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Laa
            if (r3 == 0) goto Lac
            r4.append(r3)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Laa
            java.lang.String r3 = "\n"
            r4.append(r3)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Laa
            goto L99
        La8:
            r3 = move-exception
            goto Lc2
        Laa:
            r3 = move-exception
            goto Lb7
        Lac:
            r6.close()     // Catch: java.io.IOException -> Lc1
            goto Lc1
        Lb0:
            r4 = move-exception
            r6 = r3
            r3 = r4
            goto Lc2
        Lb4:
            r4 = move-exception
            r6 = r3
            r3 = r4
        Lb7:
            java.lang.String r4 = "CrashUtil"
            java.lang.String r5 = "CrashUtil run logcat command failed"
            com.xiaomi.onetrack.util.q.b(r4, r5, r3)     // Catch: java.lang.Throwable -> La8
            if (r6 == 0) goto Lc1
            goto Lac
        Lc1:
            return
        Lc2:
            if (r6 == 0) goto Lc7
            r6.close()     // Catch: java.io.IOException -> Lc7
        Lc7:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.onetrack.util.b.a(int, java.lang.StringBuilder, java.lang.String, int, char):void");
    }

    public static long a(String str) {
        try {
            return new SimpleDateFormat(f3487g, Locale.US).parse(str).getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }
}
