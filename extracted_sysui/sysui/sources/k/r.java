package k;

import android.graphics.Paint;
import d.F;
import f.t;
import j.C0408a;
import j.C0409b;
import j.C0411d;
import java.util.List;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class r implements k.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4916a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0409b f4917b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f4918c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0408a f4919d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0411d f4920e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0409b f4921f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b f4922g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final c f4923h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f4924i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f4925j;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4926a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f4927b;

        static {
            int[] iArr = new int[c.values().length];
            f4927b = iArr;
            try {
                iArr[c.BEVEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4927b[c.MITER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4927b[c.ROUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[b.values().length];
            f4926a = iArr2;
            try {
                iArr2[b.BUTT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4926a[b.ROUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4926a[b.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum b {
        BUTT,
        ROUND,
        UNKNOWN;

        public Paint.Cap a() {
            int i2 = a.f4926a[ordinal()];
            return i2 != 1 ? i2 != 2 ? Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }
    }

    public enum c {
        MITER,
        ROUND,
        BEVEL;

        public Paint.Join a() {
            int i2 = a.f4927b[ordinal()];
            if (i2 == 1) {
                return Paint.Join.BEVEL;
            }
            if (i2 == 2) {
                return Paint.Join.MITER;
            }
            if (i2 != 3) {
                return null;
            }
            return Paint.Join.ROUND;
        }
    }

    public r(String str, C0409b c0409b, List list, C0408a c0408a, C0411d c0411d, C0409b c0409b2, b bVar, c cVar, float f2, boolean z2) {
        this.f4916a = str;
        this.f4917b = c0409b;
        this.f4918c = list;
        this.f4919d = c0408a;
        this.f4920e = c0411d;
        this.f4921f = c0409b2;
        this.f4922g = bVar;
        this.f4923h = cVar;
        this.f4924i = f2;
        this.f4925j = z2;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new t(f2, abstractC0432b, this);
    }

    public b b() {
        return this.f4922g;
    }

    public C0408a c() {
        return this.f4919d;
    }

    public C0409b d() {
        return this.f4917b;
    }

    public c e() {
        return this.f4923h;
    }

    public List f() {
        return this.f4918c;
    }

    public float g() {
        return this.f4924i;
    }

    public String h() {
        return this.f4916a;
    }

    public C0411d i() {
        return this.f4920e;
    }

    public C0409b j() {
        return this.f4921f;
    }

    public boolean k() {
        return this.f4925j;
    }
}
