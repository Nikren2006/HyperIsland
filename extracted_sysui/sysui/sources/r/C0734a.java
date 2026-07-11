package r;

import android.media.audiofx.AudioEffect;
import android.util.Log;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import java.lang.reflect.Method;
import java.util.UUID;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: renamed from: r.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0734a extends AudioEffect {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final UUID f6417f = UUID.fromString("9d4921da-8225-4f29-aefa-39537a04bcaa");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final UUID f6418g = UUID.fromString("ec7178ec-e5e1-4432-a3f4-4657e6795210");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static int f6419h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static int f6420i = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f6421a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6422b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f6423c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Method f6424d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Method f6425e;

    public C0734a(int i2, int i3) {
        super(f6418g, f6417f, i2, i3);
        this.f6421a = true;
        this.f6423c = new Object();
        try {
            Class cls = Integer.TYPE;
            this.f6424d = AudioEffect.class.getMethod("setParameter", cls, byte[].class);
            this.f6425e = AudioEffect.class.getMethod("getParameter", cls, byte[].class);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (i3 == 0) {
            Log.i("DolbyAudioEffect", "Creating a DolbyAudioEffect to global output mix!");
        }
        this.f6422b = i3;
        if (f6419h == 0) {
            f6419h = g();
        }
        if (f6420i == 0) {
            f6420i = f();
        }
    }

    public static int a(byte[] bArr) {
        return (bArr[0] & TransitionInfo.INIT) | ((bArr[3] & TransitionInfo.INIT) << 24) | ((bArr[2] & TransitionInfo.INIT) << 16) | ((bArr[1] & TransitionInfo.INIT) << 8);
    }

    public static int i(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 & 255);
        bArr[i3 + 1] = (byte) ((i2 >>> 8) & 255);
        bArr[i3 + 2] = (byte) ((i2 >>> 16) & 255);
        bArr[i3 + 3] = (byte) ((i2 >>> 24) & 255);
        return 4;
    }

    public final void b(int i2) {
        if (i2 < 0) {
            if (i2 == -5) {
                throw new UnsupportedOperationException("DolbyAudioEffect: invalid parameter operation");
            }
            if (i2 == -4) {
                throw new IllegalArgumentException("DolbyAudioEffect: bad parameter value");
            }
            throw new RuntimeException("DolbyAudioEffect: set/get parameter error");
        }
    }

    public boolean c(int i2) {
        byte[] bArr = new byte[12];
        i(i2, bArr, 0);
        b(h(i2 + 5, bArr));
        return a(bArr) > 0;
    }

    public boolean d() {
        return c(0);
    }

    public int e(int i2) {
        byte[] bArr = new byte[12];
        i(i2, bArr, 0);
        b(h(i2 + 5, bArr));
        return a(bArr);
    }

    public int f() {
        return e(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
    }

    public int g() {
        return e(50331648);
    }

    public final int h(int i2, byte[] bArr) {
        try {
            return ((Integer) this.f6425e.invoke(this, Integer.valueOf(i2), bArr)).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // android.media.audiofx.AudioEffect
    public boolean hasControl() {
        try {
            return super.hasControl();
        } catch (IllegalStateException e2) {
            Log.e("DolbyAudioEffect", e2.toString());
            return false;
        }
    }

    public int j(int i2, boolean z2) {
        byte[] bArr = new byte[12];
        int i3 = i(i2, bArr, 0);
        i(z2 ? 1 : 0, bArr, i3 + i(1, bArr, i3));
        b(l(5, bArr));
        return 0;
    }

    public void k(boolean z2) {
        j(0, z2);
        super.setEnabled(z2);
    }

    public final int l(int i2, byte[] bArr) {
        try {
            return ((Integer) this.f6424d.invoke(this, Integer.valueOf(i2), bArr)).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }
}
