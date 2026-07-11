package m;

import android.util.Pair;
import d.AbstractC0315p;
import d.C0307h;
import d.L;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import p.AbstractC0724d;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f5264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f5265b;

    public h(g gVar, f fVar) {
        this.f5264a = gVar;
        this.f5265b = fVar;
    }

    public final C0307h a(String str, String str2) {
        Pair pairA;
        if (str2 == null || (pairA = this.f5264a.a(str)) == null) {
            return null;
        }
        c cVar = (c) pairA.first;
        InputStream inputStream = (InputStream) pairA.second;
        L lY = cVar == c.ZIP ? AbstractC0315p.y(new ZipInputStream(inputStream), str) : AbstractC0315p.o(inputStream, str);
        if (lY.b() != null) {
            return (C0307h) lY.b();
        }
        return null;
    }

    public final L b(String str, String str2) {
        AbstractC0724d.a("Fetching " + str);
        Closeable closeable = null;
        try {
            try {
                d dVarA = this.f5265b.a(str);
                if (!dVarA.m()) {
                    L l2 = new L((Throwable) new IllegalArgumentException(dVarA.s()));
                    try {
                        dVarA.close();
                    } catch (IOException e2) {
                        AbstractC0724d.d("LottieFetchResult close failed ", e2);
                    }
                    return l2;
                }
                L lD = d(str, dVarA.j(), dVarA.h(), str2);
                StringBuilder sb = new StringBuilder();
                sb.append("Completed fetch from network. Success: ");
                sb.append(lD.b() != null);
                AbstractC0724d.a(sb.toString());
                try {
                    dVarA.close();
                } catch (IOException e3) {
                    AbstractC0724d.d("LottieFetchResult close failed ", e3);
                }
                return lD;
            } catch (Exception e4) {
                L l3 = new L((Throwable) e4);
                if (0 != 0) {
                    try {
                        closeable.close();
                    } catch (IOException e5) {
                        AbstractC0724d.d("LottieFetchResult close failed ", e5);
                    }
                }
                return l3;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (IOException e6) {
                    AbstractC0724d.d("LottieFetchResult close failed ", e6);
                }
            }
            throw th;
        }
    }

    public L c(String str, String str2) {
        C0307h c0307hA = a(str, str2);
        if (c0307hA != null) {
            return new L(c0307hA);
        }
        AbstractC0724d.a("Animation for " + str + " not found in cache. Fetching from network.");
        return b(str, str2);
    }

    public final L d(String str, InputStream inputStream, String str2, String str3) {
        c cVar;
        L lF;
        if (str2 == null) {
            str2 = "application/json";
        }
        if (str2.contains("application/zip") || str2.contains("application/x-zip") || str2.contains("application/x-zip-compressed") || str.split("\\?")[0].endsWith(".lottie")) {
            AbstractC0724d.a("Handling zip response.");
            cVar = c.ZIP;
            lF = f(str, inputStream, str3);
        } else {
            AbstractC0724d.a("Received json response.");
            cVar = c.JSON;
            lF = e(str, inputStream, str3);
        }
        if (str3 != null && lF.b() != null) {
            this.f5264a.e(str, cVar);
        }
        return lF;
    }

    public final L e(String str, InputStream inputStream, String str2) {
        return str2 == null ? AbstractC0315p.o(inputStream, null) : AbstractC0315p.o(new FileInputStream(this.f5264a.f(str, inputStream, c.JSON).getAbsolutePath()), str);
    }

    public final L f(String str, InputStream inputStream, String str2) {
        return str2 == null ? AbstractC0315p.y(new ZipInputStream(inputStream), null) : AbstractC0315p.y(new ZipInputStream(new FileInputStream(this.f5264a.f(str, inputStream, c.ZIP))), str);
    }
}
