package com.xiaomi.onetrack.g;

import android.text.TextUtils;
import com.xiaomi.onetrack.OneTrack;
import com.xiaomi.onetrack.d.f;
import com.xiaomi.onetrack.util.n;
import com.xiaomi.onetrack.util.q;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3353a = 30000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f3354b = 30000;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3355c = "OT_SID";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f3356d = "OT_ts";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3357e = "OT_net";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f3358f = "OT_sender";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f3359g = "OT_protocol";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f3360h = "Connection";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int f3361i = 60000;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static String f3362j = "HttpUtil";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final String f3363k = "GET";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final String f3364l = "POST";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final String f3365m = "&";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final String f3366n = "=";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static final String f3367o = "UTF-8";

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static final String f3368p = "miui_sdkconfig_jafej!@#)(*e@!#";

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static final int f3369q = 3;

    private b() {
    }

    public static String a(String str, byte[] bArr) {
        HttpURLConnection httpURLConnection;
        OutputStream outputStream;
        InputStream inputStream;
        q.a(f3362j, "doPost url=" + str + ", len=" + bArr.length);
        InputStream inputStream2 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        } catch (IOException e2) {
            e = e2;
            httpURLConnection = null;
            outputStream = null;
        } catch (Throwable th) {
            th = th;
            httpURLConnection = null;
            outputStream = null;
        }
        try {
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod(f3364l);
            a(httpURLConnection);
            httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
            httpURLConnection.setRequestProperty(f3355c, f.a().b()[1]);
            httpURLConnection.setRequestProperty(f3356d, Long.toString(System.currentTimeMillis()));
            httpURLConnection.setRequestProperty(f3357e, c.a(com.xiaomi.onetrack.f.a.b()).toString());
            httpURLConnection.setRequestProperty(f3358f, com.xiaomi.onetrack.f.a.e());
            httpURLConnection.setRequestProperty(f3359g, "3.0");
            outputStream = httpURLConnection.getOutputStream();
            try {
                outputStream.write(bArr, 0, bArr.length);
                outputStream.flush();
                int responseCode = httpURLConnection.getResponseCode();
                inputStream = httpURLConnection.getInputStream();
                try {
                    try {
                        byte[] bArrB = n.b(inputStream);
                        q.a(f3362j, String.format("HttpUtils POST 上传成功 url: %s, code: %s", str, Integer.valueOf(responseCode)));
                        String str2 = new String(bArrB, f3367o);
                        n.a(inputStream);
                        n.a(outputStream);
                        n.a(httpURLConnection);
                        return str2;
                    } catch (IOException e3) {
                        e = e3;
                        q.b(f3362j, String.format("HttpUtils POST 上传失败, url: %s, error: %s", str, e.getMessage()));
                        n.a(inputStream);
                        n.a(outputStream);
                        n.a(httpURLConnection);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream2 = inputStream;
                    n.a(inputStream2);
                    n.a(outputStream);
                    n.a(httpURLConnection);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                inputStream = null;
            } catch (Throwable th3) {
                th = th3;
                n.a(inputStream2);
                n.a(outputStream);
                n.a(httpURLConnection);
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            outputStream = null;
            inputStream = outputStream;
            q.b(f3362j, String.format("HttpUtils POST 上传失败, url: %s, error: %s", str, e.getMessage()));
            n.a(inputStream);
            n.a(outputStream);
            n.a(httpURLConnection);
            return null;
        } catch (Throwable th4) {
            th = th4;
            outputStream = null;
        }
    }

    public static String b(String str, Map<String, String> map) {
        return b(str, map, true);
    }

    public static String b(String str, Map<String, String> map, boolean z2) {
        return a(f3364l, str, map, z2);
    }

    public static boolean b(String str) throws Throwable {
        HttpURLConnection httpURLConnection;
        int responseCode;
        if (TextUtils.isEmpty(str)) {
            q.a(f3362j, "doGetAdMonitor dbUrl is null");
            return true;
        }
        HttpURLConnection httpURLConnection2 = null;
        int i2 = 0;
        int i3 = 0;
        while (i2 < 3) {
            try {
                try {
                    try {
                        if (i3 / 100 == 3) {
                            i2++;
                            str = httpURLConnection2.getHeaderField("Location");
                            q.a(f3362j, "redirect url is:" + str);
                        }
                        httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (ProtocolException e2) {
                    e = e2;
                } catch (Exception e3) {
                    e = e3;
                }
                try {
                    httpURLConnection.setInstanceFollowRedirects(false);
                    httpURLConnection.setRequestMethod(f3363k);
                    httpURLConnection.setConnectTimeout(30000);
                    httpURLConnection.setReadTimeout(30000);
                    responseCode = httpURLConnection.getResponseCode();
                    q.a(f3362j, "AdMonitor get 请求url:" + str + "_ResponseCode：" + responseCode);
                } catch (ProtocolException e4) {
                    e = e4;
                    httpURLConnection2 = httpURLConnection;
                    if (TextUtils.isEmpty(e.getMessage()) && e.getMessage().contains("200 OK")) {
                        q.a(f3362j, "response code is 200, bug status line is invalid.");
                        try {
                            n.a(httpURLConnection2);
                        } catch (Exception unused) {
                        }
                        return true;
                    }
                    n.a(httpURLConnection2);
                } catch (Exception e5) {
                    e = e5;
                    httpURLConnection2 = httpURLConnection;
                    q.b(f3362j, "HttpUtils doGetAdMonitor 上传异常:" + e.getMessage());
                    n.a(httpURLConnection2);
                } catch (Throwable th2) {
                    th = th2;
                    httpURLConnection2 = httpURLConnection;
                    try {
                        n.a(httpURLConnection2);
                    } catch (Exception unused2) {
                    }
                    throw th;
                }
            } catch (Exception unused3) {
            }
            if (responseCode / 100 != 5 && responseCode / 100 != 3) {
                try {
                    n.a(httpURLConnection);
                } catch (Exception unused4) {
                }
                return true;
            }
            if (responseCode / 100 != 3) {
                n.a(httpURLConnection);
                return false;
            }
            i3 = responseCode;
            httpURLConnection2 = httpURLConnection;
        }
        q.a(f3362j, "redirectCount >= 3, return true");
        try {
            n.a(httpURLConnection2);
        } catch (Exception unused5) {
        }
        return true;
    }

    private static void a(HttpURLConnection httpURLConnection) {
        OneTrack.HttpRequestProperty httpReqPropConnection;
        if (httpURLConnection == null || (httpReqPropConnection = OneTrack.getHttpReqPropConnection()) == null) {
            return;
        }
        try {
            q.a(f3362j, String.format("setCustomRequestProperty->connection: %s", httpReqPropConnection.getType()));
            httpURLConnection.setRequestProperty(f3360h, httpReqPropConnection.getType());
        } catch (Exception e2) {
            q.b(f3362j, "setCustomRequestProperty error: " + e2.getMessage());
        }
    }

    public static String a(String str) {
        return a(str, (Map<String, String>) null, false);
    }

    public static String a(String str, Map<String, String> map) {
        return a(str, map, true);
    }

    public static String a(String str, Map<String, String> map, boolean z2) {
        return a(f3363k, str, map, z2);
    }

    private static String a(String str, String str2, Map<String, String> map, boolean z2) throws Throwable {
        String strA;
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        String str3;
        InputStream inputStream2 = null;
        if (map == null) {
            strA = null;
        } else {
            try {
                strA = a(map, z2);
            } catch (Exception e2) {
                e = e2;
                outputStream = null;
                httpURLConnection = null;
                inputStream = null;
                q.b(f3362j, "HttpUtils url:" + str2 + " ," + e.getMessage());
                n.a(inputStream);
                n.a(outputStream);
                n.a(httpURLConnection);
                return null;
            } catch (Throwable th) {
                th = th;
                outputStream = null;
                httpURLConnection = null;
                n.a(inputStream2);
                n.a(outputStream);
                n.a(httpURLConnection);
                throw th;
            }
        }
        if (!f3363k.equals(str) || strA == null) {
            str3 = str2;
        } else {
            str3 = str2 + "? " + strA;
        }
        httpURLConnection = (HttpURLConnection) new URL(str3).openConnection();
        try {
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            a(httpURLConnection);
            try {
                try {
                    if (f3363k.equals(str)) {
                        httpURLConnection.setRequestMethod(f3363k);
                    } else {
                        if (f3364l.equals(str) && strA != null) {
                            httpURLConnection.setRequestMethod(f3364l);
                            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            httpURLConnection.setDoOutput(true);
                            byte[] bytes = strA.getBytes(f3367o);
                            outputStream = httpURLConnection.getOutputStream();
                            try {
                                outputStream.write(bytes, 0, bytes.length);
                                outputStream.flush();
                            } catch (Exception e3) {
                                e = e3;
                                inputStream = null;
                                q.b(f3362j, "HttpUtils url:" + str2 + " ," + e.getMessage());
                                n.a(inputStream);
                                n.a(outputStream);
                                n.a(httpURLConnection);
                                return null;
                            } catch (Throwable th2) {
                                th = th2;
                                n.a(inputStream2);
                                n.a(outputStream);
                                n.a(httpURLConnection);
                                throw th;
                            }
                        }
                        int responseCode = httpURLConnection.getResponseCode();
                        inputStream = httpURLConnection.getInputStream();
                        byte[] bArrB = n.b(inputStream);
                        q.a(f3362j, String.format("HttpUtils POST 上传成功 url: %s, code: %s", str2, Integer.valueOf(responseCode)));
                        String str4 = new String(bArrB, f3367o);
                        n.a(inputStream);
                        n.a(outputStream);
                        n.a(httpURLConnection);
                        return str4;
                    }
                    byte[] bArrB2 = n.b(inputStream);
                    q.a(f3362j, String.format("HttpUtils POST 上传成功 url: %s, code: %s", str2, Integer.valueOf(responseCode)));
                    String str42 = new String(bArrB2, f3367o);
                    n.a(inputStream);
                    n.a(outputStream);
                    n.a(httpURLConnection);
                    return str42;
                } catch (Exception e4) {
                    e = e4;
                    q.b(f3362j, "HttpUtils url:" + str2 + " ," + e.getMessage());
                    n.a(inputStream);
                    n.a(outputStream);
                    n.a(httpURLConnection);
                    return null;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream2 = inputStream;
                n.a(inputStream2);
                n.a(outputStream);
                n.a(httpURLConnection);
                throw th;
            }
            outputStream = null;
            int responseCode2 = httpURLConnection.getResponseCode();
            inputStream = httpURLConnection.getInputStream();
        } catch (Exception e5) {
            e = e5;
            outputStream = null;
            inputStream = null;
        } catch (Throwable th4) {
            th = th4;
            outputStream = null;
        }
    }

    private static String a(Map<String, String> map, boolean z2) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                if (!TextUtils.isEmpty(entry.getKey())) {
                    if (sb.length() > 0) {
                        sb.append(f3365m);
                    }
                    sb.append(URLEncoder.encode(entry.getKey(), f3367o));
                    sb.append(f3366n);
                    sb.append(URLEncoder.encode(entry.getValue() == null ? "null" : entry.getValue(), f3367o));
                }
            } catch (UnsupportedEncodingException unused) {
                q.b(f3362j, "format params failed");
            }
        }
        if (z2) {
            String strA = a(map);
            if (sb.length() > 0) {
                sb.append(f3365m);
            }
            sb.append(URLEncoder.encode("sign", f3367o));
            sb.append(f3366n);
            sb.append(URLEncoder.encode(strA, f3367o));
        }
        return sb.toString();
    }

    public static String a(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (map != null) {
            ArrayList<String> arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList);
            for (String str : arrayList) {
                if (!TextUtils.isEmpty(str)) {
                    sb.append(str);
                    sb.append(map.get(str));
                }
            }
        }
        sb.append(f3368p);
        return com.xiaomi.onetrack.d.d.c(sb.toString());
    }

    public static d a(String str, String str2, String str3) throws Throwable {
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        OutputStream outputStream2;
        InputStream inputStream2;
        InputStream inputStream3 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setConnectTimeout(60000);
                httpURLConnection.setReadTimeout(60000);
                httpURLConnection.setRequestMethod(f3364l);
                httpURLConnection.setRequestProperty("accept", "*/*");
                httpURLConnection.setRequestProperty("connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Authorization", str3);
                httpURLConnection.setDoOutput(true);
                byte[] bytes = str2.getBytes(f3367o);
                outputStream = httpURLConnection.getOutputStream();
                try {
                    outputStream.write(bytes, 0, bytes.length);
                    outputStream.flush();
                    int responseCode = httpURLConnection.getResponseCode();
                    d dVar = new d();
                    dVar.f3379d = responseCode;
                    if (responseCode != 200) {
                        inputStream2 = httpURLConnection.getErrorStream();
                    } else {
                        inputStream2 = httpURLConnection.getInputStream();
                    }
                    try {
                        dVar.a(new String(n.b(inputStream2), f3367o));
                        if (dVar.f3379d == 200) {
                            q.a(f3362j, "POST 成功 publishResponse:" + dVar.toString());
                        } else {
                            q.b(f3362j, "POST 失败 publishResponse:" + dVar.toString());
                        }
                        n.a(inputStream2);
                        n.a(outputStream);
                        n.a(httpURLConnection);
                        return dVar;
                    } catch (Exception e2) {
                        inputStream = inputStream2;
                        e = e2;
                        outputStream2 = outputStream;
                        try {
                            q.b(f3362j, "HttpUtils post url:" + str + " ," + e.getMessage());
                            n.a(inputStream);
                            n.a(outputStream2);
                            n.a(httpURLConnection);
                            return null;
                        } catch (Throwable th) {
                            th = th;
                            inputStream3 = inputStream;
                            outputStream = outputStream2;
                            n.a(inputStream3);
                            n.a(outputStream);
                            n.a(httpURLConnection);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream3 = inputStream2;
                        n.a(inputStream3);
                        n.a(outputStream);
                        n.a(httpURLConnection);
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    outputStream2 = outputStream;
                    inputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Exception e4) {
                e = e4;
                inputStream = null;
                outputStream2 = null;
            } catch (Throwable th4) {
                th = th4;
                outputStream = null;
            }
        } catch (Exception e5) {
            e = e5;
            inputStream = null;
            outputStream2 = null;
            httpURLConnection = null;
        } catch (Throwable th5) {
            th = th5;
            outputStream = null;
            httpURLConnection = null;
        }
    }

    public static String a(String str, String str2, Map<String, String> map) throws Throwable {
        String strA;
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        InputStream inputStream2 = null;
        if (map == null) {
            strA = null;
        } else {
            try {
                strA = a(map, true);
            } catch (Exception e2) {
                e = e2;
                inputStream = null;
                outputStream = null;
                httpURLConnection = null;
                q.b(f3362j, "HttpUtils post token:" + str2 + " ," + e.getMessage());
                n.a(inputStream);
                n.a(outputStream);
                n.a(httpURLConnection);
                return null;
            } catch (Throwable th) {
                th = th;
                outputStream = null;
                httpURLConnection = null;
                n.a(inputStream2);
                n.a(outputStream);
                n.a(httpURLConnection);
                throw th;
            }
        }
        httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
        try {
            httpURLConnection.setConnectTimeout(60000);
            httpURLConnection.setReadTimeout(60000);
            httpURLConnection.setRequestMethod(f3364l);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + str);
            httpURLConnection.setDoOutput(true);
            byte[] bytes = strA.getBytes(f3367o);
            outputStream = httpURLConnection.getOutputStream();
            try {
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                int responseCode = httpURLConnection.getResponseCode();
                q.a(f3362j, "postGetToken responseData statusCode:" + responseCode);
                inputStream = httpURLConnection.getInputStream();
                try {
                    try {
                        String str3 = new String(n.b(inputStream), f3367o);
                        n.a(inputStream);
                        n.a(outputStream);
                        n.a(httpURLConnection);
                        return str3;
                    } catch (Exception e3) {
                        e = e3;
                        q.b(f3362j, "HttpUtils post token:" + str2 + " ," + e.getMessage());
                        n.a(inputStream);
                        n.a(outputStream);
                        n.a(httpURLConnection);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream2 = inputStream;
                    n.a(inputStream2);
                    n.a(outputStream);
                    n.a(httpURLConnection);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                inputStream = null;
            } catch (Throwable th3) {
                th = th3;
                n.a(inputStream2);
                n.a(outputStream);
                n.a(httpURLConnection);
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            inputStream = null;
            outputStream = null;
        } catch (Throwable th4) {
            th = th4;
            outputStream = null;
        }
    }
}
