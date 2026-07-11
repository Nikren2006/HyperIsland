package g0;

import androidx.core.app.FrameMetricsAggregator;
import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.mgl.math.Vector2;

/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f4330a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f4331b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f4332c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f4333d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g f4334e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f4335f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4336g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g f4337h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g f4338i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f4339j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Vector2[] f4340k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float[] f4341l;

    public d(e common, g flameSpring, g onSpring, g volumeSpring, g glowSpring, long j2, boolean z2, g energySpring, g lightnessSpring, float f2, Vector2[] lights, float[] intelliMovementScale) {
        n.g(common, "common");
        n.g(flameSpring, "flameSpring");
        n.g(onSpring, "onSpring");
        n.g(volumeSpring, "volumeSpring");
        n.g(glowSpring, "glowSpring");
        n.g(energySpring, "energySpring");
        n.g(lightnessSpring, "lightnessSpring");
        n.g(lights, "lights");
        n.g(intelliMovementScale, "intelliMovementScale");
        this.f4330a = common;
        this.f4331b = flameSpring;
        this.f4332c = onSpring;
        this.f4333d = volumeSpring;
        this.f4334e = glowSpring;
        this.f4335f = j2;
        this.f4336g = z2;
        this.f4337h = energySpring;
        this.f4338i = lightnessSpring;
        this.f4339j = f2;
        this.f4340k = lights;
        this.f4341l = intelliMovementScale;
    }

    public final e a() {
        return this.f4330a;
    }

    public final g b() {
        return this.f4337h;
    }

    public final g c() {
        return this.f4331b;
    }

    public final g d() {
        return this.f4334e;
    }

    public final float[] e() {
        return this.f4341l;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.f4335f == dVar.f4335f && this.f4336g == dVar.f4336g && this.f4339j == dVar.f4339j && n.c(this.f4330a, dVar.f4330a) && n.c(this.f4331b, dVar.f4331b) && n.c(this.f4332c, dVar.f4332c) && n.c(this.f4333d, dVar.f4333d) && n.c(this.f4334e, dVar.f4334e) && n.c(this.f4337h, dVar.f4337h) && n.c(this.f4338i, dVar.f4338i) && Arrays.equals(this.f4340k, dVar.f4340k) && Arrays.equals(this.f4341l, dVar.f4341l);
    }

    public final g f() {
        return this.f4338i;
    }

    public final Vector2[] g() {
        return this.f4340k;
    }

    public final g h() {
        return this.f4332c;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.f4336g), Float.valueOf(this.f4339j), this.f4330a, this.f4331b, this.f4332c, this.f4333d, this.f4334e, this.f4337h, this.f4338i, this.f4340k, this.f4341l);
    }

    public final g i() {
        return this.f4333d;
    }

    public final long j() {
        return this.f4335f;
    }

    public final void k(boolean z2) {
        this.f4336g = z2;
    }

    public final void l(float f2) {
        this.f4339j = f2;
    }

    public final void m(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.f4341l = fArr;
    }

    public final void n(long j2) {
        this.f4335f = j2;
    }

    public final void o() {
        float f2 = this.f4330a.f();
        boolean zI = this.f4330a.i();
        float f3 = (f2 * f2 * 0.7f) + 0.7f;
        if (zI) {
            f3 = 0.7f;
        }
        this.f4331b.g(f3);
        this.f4332c.g(1.0f);
        float fH = this.f4330a.h();
        this.f4339j = this.f4331b.b() * 0.07f;
        if (!this.f4330a.c()) {
            this.f4333d.g(f2);
        }
        if (this.f4336g) {
            this.f4337h.g(0.0f);
            this.f4338i.g(this.f4337h.b());
        }
        float f4 = zI ? 0.3f : 2.5f;
        float fA = this.f4332c.a();
        float fB = f4 * fH * this.f4333d.b();
        float f5 = fA >= 0.0f ? 20.0f * fA : 0.0f;
        float f6 = 25.0f * fH;
        if (fB > f6) {
            fB = f6;
        }
        this.f4334e.g(c1.f.e((f5 * this.f4330a.d()) + this.f4333d.b(), 1.3f));
        float f7 = (fB * 0.5f) + (0.7f * fH);
        if (zI) {
            f7 *= 0.4f;
            float f8 = 0.6f * fH;
            if (f7 > f8) {
                f7 = f8;
            }
        }
        e eVar = this.f4330a;
        eVar.k(eVar.b() + f7);
    }

    public String toString() {
        return "Physics(common=" + this.f4330a + ", flameSpring=" + this.f4331b + ", onSpring=" + this.f4332c + ", volumeSpring=" + this.f4333d + ", glowSpring=" + this.f4334e + ", zoning=" + this.f4335f + ", isBuddy=" + this.f4336g + ", energySpring=" + this.f4337h + ", lightnessSpring=" + this.f4338i + ", flameDrawnSize=" + this.f4339j + ", lights=" + Arrays.toString(this.f4340k) + ", intelliMovementScale=" + Arrays.toString(this.f4341l) + ")";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ d(e eVar, g gVar, g gVar2, g gVar3, g gVar4, long j2, boolean z2, g gVar5, g gVar6, float f2, Vector2[] vector2Arr, float[] fArr, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        Vector2[] vector2Arr2;
        e eVar2 = (i2 & 1) != 0 ? new e(0L, 0, 0.0f, 0.0f, 0.0f, null, 0.0f, false, false, FrameMetricsAggregator.EVERY_DURATION, null) : eVar;
        g gVar7 = (i2 & 2) != 0 ? new g(0.0f, 0.0f, 0.0f, 0.0f, 15, null) : gVar;
        g gVar8 = (i2 & 4) != 0 ? new g(0.0f, 0.0f, 0.0f, 0.0f, 15, null) : gVar2;
        g gVar9 = (i2 & 8) != 0 ? new g(0.0f, 0.0f, 0.0f, 0.0f, 15, null) : gVar3;
        g gVar10 = (i2 & 16) != 0 ? new g(0.0f, 0.0f, 0.0f, 0.0f, 15, null) : gVar4;
        long j3 = (i2 & 32) != 0 ? 0L : j2;
        boolean z3 = (i2 & 64) != 0 ? false : z2;
        g gVar11 = (i2 & 128) != 0 ? new g(0.0f, 0.0f, 0.0f, 0.0f, 15, null) : gVar5;
        g gVar12 = (i2 & 256) != 0 ? new g(0.0f, 0.0f, 0.0f, 0.0f, 15, null) : gVar6;
        float f3 = (i2 & 512) != 0 ? 0.0f : f2;
        if ((i2 & 1024) != 0) {
            vector2Arr2 = new Vector2[11];
            int i3 = 0;
            for (int i4 = 11; i3 < i4; i4 = 11) {
                vector2Arr2[i3] = new Vector2(0.0f);
                i3++;
            }
        } else {
            vector2Arr2 = vector2Arr;
        }
        this(eVar2, gVar7, gVar8, gVar9, gVar10, j3, z3, gVar11, gVar12, f3, vector2Arr2, (i2 & 2048) != 0 ? new float[]{0.9f, 0.9f} : fArr);
    }
}
