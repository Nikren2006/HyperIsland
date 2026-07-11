package m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import p.AbstractC0724d;

/* JADX INFO: renamed from: m.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0461a implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HttpURLConnection f5258a;

    public C0461a(HttpURLConnection httpURLConnection) {
        this.f5258a = httpURLConnection;
    }

    public final String a(HttpURLConnection httpURLConnection) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                    sb.append('\n');
                } else {
                    try {
                        break;
                    } catch (Exception unused) {
                    }
                }
            } finally {
                try {
                    bufferedReader.close();
                } catch (Exception unused2) {
                }
            }
        }
        return sb.toString();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5258a.disconnect();
    }

    @Override // m.d
    public String h() {
        return this.f5258a.getContentType();
    }

    @Override // m.d
    public InputStream j() {
        return this.f5258a.getInputStream();
    }

    @Override // m.d
    public boolean m() {
        try {
            return this.f5258a.getResponseCode() / 100 == 2;
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // m.d
    public String s() {
        try {
            if (m()) {
                return null;
            }
            return "Unable to fetch " + this.f5258a.getURL() + ". Failed with " + this.f5258a.getResponseCode() + "\n" + a(this.f5258a);
        } catch (IOException e2) {
            AbstractC0724d.d("get error failed ", e2);
            return e2.getMessage();
        }
    }
}
