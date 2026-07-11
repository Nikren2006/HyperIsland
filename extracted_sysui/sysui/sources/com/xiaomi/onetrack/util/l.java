package com.xiaomi.onetrack.util;

import android.text.TextUtils;
import android.util.LruCache;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3520a = "FileUtil";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3521b = "onetrack";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3522c = "tombstone";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static LruCache<String, a> f3523d = new m(1048576);

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f3524a;

        private a() {
        }

        public /* synthetic */ a(m mVar) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.xiaomi.onetrack.util.m] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public static void a(String str, String str2) throws Throwable {
        ?? r02;
        BufferedWriter bufferedWriter;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        ?? r03 = 0;
        r03 = 0;
        try {
            try {
                a aVar = new a(r03);
                aVar.f3524a = str2;
                f3523d.put(str, aVar);
                String strB = b();
                File file = new File(strB);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(strB, str);
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                bufferedWriter = new BufferedWriter(new FileWriter(file2), 1024);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            r02 = r03;
        }
        try {
            bufferedWriter.write(str2);
            bufferedWriter.flush();
            n.a(bufferedWriter);
        } catch (Exception e3) {
            r03 = bufferedWriter;
            e = e3;
            q.c(f3520a, "put error:" + e.toString());
            n.a((Closeable) r03);
        } catch (Throwable th2) {
            r02 = bufferedWriter;
            th = th2;
            n.a((Closeable) r02);
            throw th;
        }
    }

    private static String b() {
        return c("onetrack");
    }

    private static String c(String str) {
        String str2 = com.xiaomi.onetrack.f.a.a().getFilesDir().getAbsolutePath() + File.separator + str;
        File file = new File(str2);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return str2;
    }

    public static void b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            f3523d.remove(str);
            File file = new File(b(), str);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        } catch (Exception e2) {
            q.c(f3520a, "clear error:" + e2.toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.xiaomi.onetrack.util.m] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public static String a(String str) throws Throwable {
        ?? r02;
        BufferedReader bufferedReader;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        a aVar = f3523d.get(str);
        if (aVar != null) {
            return aVar.f3524a;
        }
        ?? r03 = 0;
        r03 = 0;
        try {
            try {
                File file = new File(b(), str);
                StringBuilder sb = new StringBuilder();
                if (file.exists()) {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line);
                        } catch (Exception e2) {
                            e = e2;
                            r03 = bufferedReader;
                            q.c(f3520a, "get error:" + e.toString());
                            n.a((Closeable) r03);
                            return "";
                        } catch (Throwable th) {
                            th = th;
                            r02 = bufferedReader;
                            n.a((Closeable) r02);
                            throw th;
                        }
                    }
                } else {
                    bufferedReader = null;
                }
                String string = sb.toString();
                a aVar2 = new a(r03);
                aVar2.f3524a = string;
                f3523d.put(str, aVar2);
                n.a(bufferedReader);
                return string;
            } catch (Throwable th2) {
                th = th2;
                r02 = r03;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static String a() {
        return c(f3522c);
    }

    public static String[] a(String str, int i2) throws Throwable {
        String[] strArr = new String[2];
        BufferedReader bufferedReader = null;
        try {
            try {
                File file = new File(str);
                StringBuilder sb = new StringBuilder();
                if (file.exists()) {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                    boolean z2 = false;
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                break;
                            }
                            if (!z2) {
                                if (b.f3491k.equals(line)) {
                                    z2 = true;
                                }
                            } else {
                                strArr[0] = line;
                                z2 = false;
                            }
                            if (sb.length() <= i2) {
                                sb.append(line);
                                sb.append("\n");
                            } else if (!TextUtils.isEmpty(strArr[0])) {
                                break;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            bufferedReader = bufferedReader2;
                            q.c(f3520a, "readCrashFile error:", e);
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            n.a(bufferedReader);
                            throw th;
                        }
                    }
                    bufferedReader = bufferedReader2;
                }
                if (sb.length() > i2) {
                    strArr[1] = sb.substring(0, i2 - 1);
                } else {
                    strArr[1] = sb.toString();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        n.a(bufferedReader);
        return strArr;
    }

    public static void a(File file) {
        try {
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        } catch (Exception e2) {
            q.c(f3520a, "failed to remove file " + file.getName() + ", e :" + e2.getMessage());
        }
    }
}
