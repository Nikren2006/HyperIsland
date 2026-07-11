package d;

import android.content.Context;
import androidx.core.os.TraceCompat;
import java.io.File;
import m.C0462b;

/* JADX INFO: renamed from: d.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0302c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3895a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3896b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String[] f3897c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long[] f3898d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f3899e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f3900f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static m.f f3901g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static m.e f3902h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static volatile m.h f3903i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static volatile m.g f3904j;

    /* JADX INFO: renamed from: d.c$a */
    public class a implements m.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3905a;

        public a(Context context) {
            this.f3905a = context;
        }

        @Override // m.e
        public File a() {
            return new File(this.f3905a.getCacheDir(), "lottie_network_cache");
        }
    }

    public static void a(String str) {
        if (f3896b) {
            int i2 = f3899e;
            if (i2 == 20) {
                f3900f++;
                return;
            }
            f3897c[i2] = str;
            f3898d[i2] = System.nanoTime();
            TraceCompat.beginSection(str);
            f3899e++;
        }
    }

    public static float b(String str) {
        int i2 = f3900f;
        if (i2 > 0) {
            f3900f = i2 - 1;
            return 0.0f;
        }
        if (!f3896b) {
            return 0.0f;
        }
        int i3 = f3899e - 1;
        f3899e = i3;
        if (i3 == -1) {
            throw new IllegalStateException("Can't end trace section. There are none.");
        }
        if (str.equals(f3897c[i3])) {
            TraceCompat.endSection();
            return (System.nanoTime() - f3898d[f3899e]) / 1000000.0f;
        }
        throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + f3897c[f3899e] + ".");
    }

    public static m.g c(Context context) {
        Context applicationContext = context.getApplicationContext();
        m.g gVar = f3904j;
        if (gVar == null) {
            synchronized (m.g.class) {
                try {
                    gVar = f3904j;
                    if (gVar == null) {
                        m.e aVar = f3902h;
                        if (aVar == null) {
                            aVar = new a(applicationContext);
                        }
                        gVar = new m.g(aVar);
                        f3904j = gVar;
                    }
                } finally {
                }
            }
        }
        return gVar;
    }

    public static m.h d(Context context) {
        m.h hVar = f3903i;
        if (hVar == null) {
            synchronized (m.h.class) {
                try {
                    hVar = f3903i;
                    if (hVar == null) {
                        m.g gVarC = c(context);
                        m.f c0462b = f3901g;
                        if (c0462b == null) {
                            c0462b = new C0462b();
                        }
                        hVar = new m.h(gVarC, c0462b);
                        f3903i = hVar;
                    }
                } finally {
                }
            }
        }
        return hVar;
    }
}
