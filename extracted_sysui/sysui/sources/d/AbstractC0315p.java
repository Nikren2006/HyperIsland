package d;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import o.AbstractC0715c;
import p.AbstractC0724d;

/* JADX INFO: renamed from: d.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0315p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map f3943a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f3944b = {80, 75, 3, 4};

    public static boolean A(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static Boolean B(D1.c cVar) {
        try {
            D1.c cVarPeek = cVar.peek();
            for (byte b2 : f3944b) {
                if (cVarPeek.readByte() != b2) {
                    return Boolean.FALSE;
                }
            }
            cVarPeek.close();
            return Boolean.TRUE;
        } catch (Exception e2) {
            AbstractC0724d.b("Failed to check zip file header", e2);
            return Boolean.FALSE;
        } catch (NoSuchMethodError unused) {
            return Boolean.FALSE;
        }
    }

    public static /* synthetic */ void C(String str, AtomicBoolean atomicBoolean, Throwable th) {
        f3943a.remove(str);
        atomicBoolean.set(true);
    }

    public static /* synthetic */ L D(C0307h c0307h) {
        return new L(c0307h);
    }

    public static /* synthetic */ void E(String str, AtomicBoolean atomicBoolean, C0307h c0307h) {
        f3943a.remove(str);
        atomicBoolean.set(true);
    }

    public static /* synthetic */ L H(WeakReference weakReference, Context context, int i2, String str) {
        Context context2 = (Context) weakReference.get();
        if (context2 != null) {
            context = context2;
        }
        return v(context, i2, str);
    }

    public static /* synthetic */ L I(Context context, String str, String str2) {
        L lC = AbstractC0302c.d(context).c(str, str2);
        if (str2 != null && lC.b() != null) {
            i.g.b().c(str2, (C0307h) lC.b());
        }
        return lC;
    }

    public static String J(Context context, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("rawRes");
        sb.append(A(context) ? "_night_" : "_day_");
        sb.append(i2);
        return sb.toString();
    }

    public static N h(final String str, Callable callable) {
        final C0307h c0307hA = str == null ? null : i.g.b().a(str);
        if (c0307hA != null) {
            return new N(new Callable() { // from class: d.m
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return AbstractC0315p.D(c0307hA);
                }
            });
        }
        if (str != null) {
            Map map = f3943a;
            if (map.containsKey(str)) {
                return (N) map.get(str);
            }
        }
        N n2 = new N(callable);
        if (str != null) {
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            n2.d(new H() { // from class: d.n
                @Override // d.H
                public final void onResult(Object obj) {
                    AbstractC0315p.E(str, atomicBoolean, (C0307h) obj);
                }
            });
            n2.c(new H() { // from class: d.o
                @Override // d.H
                public final void onResult(Object obj) {
                    AbstractC0315p.C(str, atomicBoolean, (Throwable) obj);
                }
            });
            if (!atomicBoolean.get()) {
                f3943a.put(str, n2);
            }
        }
        return n2;
    }

    public static G i(C0307h c0307h, String str) {
        for (G g2 : c0307h.j().values()) {
            if (g2.b().equals(str)) {
                return g2;
            }
        }
        return null;
    }

    public static N j(Context context, String str) {
        return k(context, str, "asset_" + str);
    }

    public static N k(Context context, final String str, final String str2) {
        final Context applicationContext = context.getApplicationContext();
        return h(str2, new Callable() { // from class: d.l
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AbstractC0315p.m(applicationContext, str, str2);
            }
        });
    }

    public static L l(Context context, String str) {
        return m(context, str, "asset_" + str);
    }

    public static L m(Context context, String str, String str2) {
        try {
            if (!str.endsWith(".zip") && !str.endsWith(".lottie")) {
                return o(context.getAssets().open(str), str2);
            }
            return y(new ZipInputStream(context.getAssets().open(str)), str2);
        } catch (IOException e2) {
            return new L((Throwable) e2);
        }
    }

    public static N n(final InputStream inputStream, final String str) {
        return h(str, new Callable() { // from class: d.j
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AbstractC0315p.o(inputStream, str);
            }
        });
    }

    public static L o(InputStream inputStream, String str) {
        return p(inputStream, str, true);
    }

    public static L p(InputStream inputStream, String str, boolean z2) {
        try {
            return q(AbstractC0715c.z(D1.e.a(D1.e.c(inputStream))), str);
        } finally {
            if (z2) {
                p.h.c(inputStream);
            }
        }
    }

    public static L q(AbstractC0715c abstractC0715c, String str) {
        return r(abstractC0715c, str, true);
    }

    public static L r(AbstractC0715c abstractC0715c, String str, boolean z2) {
        try {
            try {
                C0307h c0307hA = n.w.a(abstractC0715c);
                if (str != null) {
                    i.g.b().c(str, c0307hA);
                }
                L l2 = new L(c0307hA);
                if (z2) {
                    p.h.c(abstractC0715c);
                }
                return l2;
            } catch (Exception e2) {
                L l3 = new L((Throwable) e2);
                if (z2) {
                    p.h.c(abstractC0715c);
                }
                return l3;
            }
        } catch (Throwable th) {
            if (z2) {
                p.h.c(abstractC0715c);
            }
            throw th;
        }
    }

    public static N s(Context context, int i2) {
        return t(context, i2, J(context, i2));
    }

    public static N t(Context context, final int i2, final String str) {
        final WeakReference weakReference = new WeakReference(context);
        final Context applicationContext = context.getApplicationContext();
        return h(str, new Callable() { // from class: d.k
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AbstractC0315p.H(weakReference, applicationContext, i2, str);
            }
        });
    }

    public static L u(Context context, int i2) {
        return v(context, i2, J(context, i2));
    }

    public static L v(Context context, int i2, String str) {
        try {
            D1.c cVarA = D1.e.a(D1.e.c(context.getResources().openRawResource(i2)));
            return B(cVarA).booleanValue() ? y(new ZipInputStream(cVarA.y()), str) : o(cVarA.y(), str);
        } catch (Resources.NotFoundException e2) {
            return new L((Throwable) e2);
        }
    }

    public static N w(Context context, String str) {
        return x(context, str, "url_" + str);
    }

    public static N x(final Context context, final String str, final String str2) {
        return h(str2, new Callable() { // from class: d.i
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AbstractC0315p.I(context, str, str2);
            }
        });
    }

    public static L y(ZipInputStream zipInputStream, String str) {
        try {
            return z(zipInputStream, str);
        } finally {
            p.h.c(zipInputStream);
        }
    }

    public static L z(ZipInputStream zipInputStream, String str) {
        HashMap map = new HashMap();
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            C0307h c0307h = null;
            while (nextEntry != null) {
                String name = nextEntry.getName();
                if (name.contains("__MACOSX")) {
                    zipInputStream.closeEntry();
                } else if (nextEntry.getName().equalsIgnoreCase("manifest.json")) {
                    zipInputStream.closeEntry();
                } else if (nextEntry.getName().contains(".json")) {
                    c0307h = (C0307h) r(AbstractC0715c.z(D1.e.a(D1.e.c(zipInputStream))), null, false).b();
                } else if (name.contains(".png") || name.contains(".webp") || name.contains(".jpg") || name.contains(".jpeg")) {
                    map.put(name.split("/")[r1.length - 1], BitmapFactory.decodeStream(zipInputStream));
                } else {
                    zipInputStream.closeEntry();
                }
                nextEntry = zipInputStream.getNextEntry();
            }
            if (c0307h == null) {
                return new L((Throwable) new IllegalArgumentException("Unable to parse composition"));
            }
            for (Map.Entry entry : map.entrySet()) {
                G gI = i(c0307h, (String) entry.getKey());
                if (gI != null) {
                    gI.f(p.h.l((Bitmap) entry.getValue(), gI.e(), gI.c()));
                }
            }
            for (Map.Entry entry2 : c0307h.j().entrySet()) {
                if (((G) entry2.getValue()).a() == null) {
                    return new L((Throwable) new IllegalStateException("There is no image for " + ((G) entry2.getValue()).b()));
                }
            }
            if (str != null) {
                i.g.b().c(str, c0307h);
            }
            return new L(c0307h);
        } catch (IOException e2) {
            return new L((Throwable) e2);
        }
    }
}
