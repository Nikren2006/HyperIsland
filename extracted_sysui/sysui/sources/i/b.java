package i;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4498a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f4499b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f4500c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f4501d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4502e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4503f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4504g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4505h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4506i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f4507j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f4508k;

    public enum a {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public b(String str, String str2, float f2, a aVar, int i2, float f3, float f4, int i3, int i4, float f5, boolean z2) {
        a(str, str2, f2, aVar, i2, f3, f4, i3, i4, f5, z2);
    }

    public void a(String str, String str2, float f2, a aVar, int i2, float f3, float f4, int i3, int i4, float f5, boolean z2) {
        this.f4498a = str;
        this.f4499b = str2;
        this.f4500c = f2;
        this.f4501d = aVar;
        this.f4502e = i2;
        this.f4503f = f3;
        this.f4504g = f4;
        this.f4505h = i3;
        this.f4506i = i4;
        this.f4507j = f5;
        this.f4508k = z2;
    }

    public int hashCode() {
        int iHashCode = (((((int) ((((this.f4498a.hashCode() * 31) + this.f4499b.hashCode()) * 31) + this.f4500c)) * 31) + this.f4501d.ordinal()) * 31) + this.f4502e;
        long jFloatToRawIntBits = Float.floatToRawIntBits(this.f4503f);
        return (((iHashCode * 31) + ((int) (jFloatToRawIntBits ^ (jFloatToRawIntBits >>> 32)))) * 31) + this.f4505h;
    }

    public b() {
    }
}
