package com.xiaomi.onetrack.c;

import android.os.HandlerThread;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3234a = "config";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3235b = "appId";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3236c = "version";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3237d = "UploaderEngine";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3238e = "code";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f3239f = "UTF-8";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private v f3240g;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final y f3241a = new y();

        private a() {
        }
    }

    public static y a() {
        return a.f3241a;
    }

    private boolean b() {
        if (com.xiaomi.onetrack.b.n.a() && com.xiaomi.onetrack.g.c.a() && !com.xiaomi.onetrack.b.n.b()) {
            return true;
        }
        com.xiaomi.onetrack.util.q.a(f3237d, "不用处理消息, available=" + com.xiaomi.onetrack.b.n.a() + ", 是否有网=" + com.xiaomi.onetrack.g.c.a() + ", 数据库是否为空=" + com.xiaomi.onetrack.b.n.b());
        return false;
    }

    private void c() {
        HandlerThread handlerThread = new HandlerThread("onetrack_uploader_worker");
        handlerThread.start();
        this.f3240g = new v(handlerThread.getLooper());
    }

    private y() {
        c();
    }

    public synchronized void a(int i2, boolean z2) {
        try {
            v vVar = this.f3240g;
            if (vVar != null) {
                vVar.a(i2, z2);
            } else {
                com.xiaomi.onetrack.util.q.b(f3237d, "*** impossible, upload timer should not be null");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public void a(boolean z2) {
        v vVar = this.f3240g;
        if (vVar != null) {
            vVar.a(z2);
        } else {
            com.xiaomi.onetrack.util.q.b(f3237d, "*** impossible, upload timer should not be null");
        }
    }

    private boolean b(String str) {
        boolean z2 = false;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("code");
            if (iOptInt == 0) {
                com.xiaomi.onetrack.util.q.a(f3237d, "成功发送数据到服务端");
                com.xiaomi.onetrack.b.a.a().a(jSONObject);
                z2 = true;
            } else if (iOptInt == -3) {
                com.xiaomi.onetrack.util.q.b(f3237d, "signature expired, will update");
                com.xiaomi.onetrack.d.f.a().c();
            } else {
                com.xiaomi.onetrack.util.q.b(f3237d, "Error: status code=" + iOptInt);
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f3237d, "parseUploadingResult exception ", e2);
        }
        return z2;
    }

    public boolean a(int i2) {
        if (!b()) {
            return true;
        }
        com.xiaomi.onetrack.util.q.a(f3237d, "即将读取数据库并上传数据");
        while (true) {
            i iVarA = d.a().a(i2);
            if (iVarA == null) {
                com.xiaomi.onetrack.util.q.a(f3237d, "满足条件的记录为空，即将返回, priority=" + i2);
                return true;
            }
            ArrayList<Long> arrayList = iVarA.f3149c;
            boolean zA = a(iVarA.f3147a);
            com.xiaomi.onetrack.util.q.a(f3237d, "upload success:" + zA);
            if (!zA) {
                return false;
            }
            if (d.a().a(arrayList) == 0) {
                com.xiaomi.onetrack.util.q.b(f3237d, "delete DB failed!", new Throwable());
                break;
            }
            if (iVarA.f3150d) {
                com.xiaomi.onetrack.util.q.a(f3237d, "No more records for prio=" + i2);
                break;
            }
        }
        return true;
    }

    private boolean a(JSONArray jSONArray) {
        try {
            String strB = com.xiaomi.onetrack.util.y.a().b();
            String string = jSONArray.toString();
            com.xiaomi.onetrack.util.q.a(f3237d, " payload:" + string);
            byte[] bArrA = a(a(string));
            com.xiaomi.onetrack.util.q.a(f3237d, "before zip and encrypt, len=" + string.length() + ", after=" + bArrA.length);
            String strA = com.xiaomi.onetrack.g.b.a(strB, bArrA);
            StringBuilder sb = new StringBuilder();
            sb.append("sendDataToServer response: ");
            sb.append(strA);
            com.xiaomi.onetrack.util.q.a(f3237d, sb.toString());
            if (TextUtils.isEmpty(strA)) {
                return false;
            }
            return b(strA);
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f3237d, "Exception while uploading ", e2);
            return false;
        }
    }

    private static byte[] a(String str) throws Throwable {
        GZIPOutputStream gZIPOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        byte[] byteArray = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(str.getBytes(f3239f).length);
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    try {
                        gZIPOutputStream.write(str.getBytes(f3239f));
                        gZIPOutputStream.finish();
                        byteArray = byteArrayOutputStream.toByteArray();
                    } catch (Exception e2) {
                        e = e2;
                        com.xiaomi.onetrack.util.q.b(f3237d, " zipData failed! " + e.toString());
                    }
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    com.xiaomi.onetrack.util.n.a((OutputStream) byteArrayOutputStream2);
                    com.xiaomi.onetrack.util.n.a((OutputStream) gZIPOutputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                gZIPOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = null;
                byteArrayOutputStream2 = byteArrayOutputStream;
                com.xiaomi.onetrack.util.n.a((OutputStream) byteArrayOutputStream2);
                com.xiaomi.onetrack.util.n.a((OutputStream) gZIPOutputStream);
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream = null;
            com.xiaomi.onetrack.util.n.a((OutputStream) byteArrayOutputStream2);
            com.xiaomi.onetrack.util.n.a((OutputStream) gZIPOutputStream);
            throw th;
        }
        com.xiaomi.onetrack.util.n.a((OutputStream) byteArrayOutputStream);
        com.xiaomi.onetrack.util.n.a((OutputStream) gZIPOutputStream);
        return byteArray;
    }

    private byte[] a(byte[] bArr) {
        if (bArr == null) {
            com.xiaomi.onetrack.util.q.b(f3237d, "content is null");
            return null;
        }
        return com.xiaomi.onetrack.d.a.a(bArr, com.xiaomi.onetrack.d.c.a(com.xiaomi.onetrack.d.f.a().b()[0]));
    }
}
