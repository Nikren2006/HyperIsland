package g0;

import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f4342a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4343b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f4344c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f4345d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4346e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float[] f4347f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4348g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4349h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4350i;

    public e(long j2, int i2, float f2, float f3, float f4, float[] chipRandomOffset, float f5, boolean z2, boolean z3) {
        n.g(chipRandomOffset, "chipRandomOffset");
        this.f4342a = j2;
        this.f4343b = i2;
        this.f4344c = f2;
        this.f4345d = f3;
        this.f4346e = f4;
        this.f4347f = chipRandomOffset;
        this.f4348g = f5;
        this.f4349h = z2;
        this.f4350i = z3;
    }

    public final float[] a() {
        return this.f4347f;
    }

    public final float b() {
        return this.f4348g;
    }

    public final boolean c() {
        return this.f4349h;
    }

    public final float d() {
        return this.f4345d;
    }

    public final int e() {
        return this.f4343b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.f4342a == eVar.f4342a && this.f4343b == eVar.f4343b && this.f4344c == eVar.f4344c && this.f4345d == eVar.f4345d && this.f4346e == eVar.f4346e && this.f4348g == eVar.f4348g && this.f4349h == eVar.f4349h && this.f4350i == eVar.f4350i && Arrays.equals(this.f4347f, eVar.f4347f);
    }

    public final float f() {
        return this.f4346e;
    }

    public final long g() {
        return this.f4342a;
    }

    public final float h() {
        return this.f4344c;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.f4342a), Integer.valueOf(this.f4343b), Float.valueOf(this.f4344c), Float.valueOf(this.f4345d), Float.valueOf(this.f4346e), Float.valueOf(this.f4348g), Boolean.valueOf(this.f4349h), Boolean.valueOf(this.f4350i), this.f4347f);
    }

    public final boolean i() {
        return this.f4350i;
    }

    public final void j(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.f4347f = fArr;
    }

    public final void k(float f2) {
        this.f4348g = f2;
    }

    public final void l(boolean z2) {
        this.f4349h = z2;
    }

    public final void m(float f2) {
        this.f4345d = f2;
    }

    public final void n(int i2) {
        this.f4343b = i2;
    }

    public final void o(float f2) {
        this.f4346e = f2;
    }

    public final void p(long j2) {
        this.f4342a = j2;
    }

    public final void q(float f2) {
        this.f4344c = f2;
    }

    public final void r(boolean z2) {
        this.f4350i = z2;
    }

    public String toString() {
        return "PhysicsCommon(physicsRate=" + this.f4342a + ", maxPhysicsIterationsPerFrame=" + this.f4343b + ", physicsTickDelta=" + this.f4344c + ", framerateEnergyModifier=" + this.f4345d + ", micPowerLevel=" + this.f4346e + ", chipRandomOffset=" + Arrays.toString(this.f4347f) + ", chipRotation=" + this.f4348g + ", drawingVeryLastFrame=" + this.f4349h + ", reduceMotion=" + this.f4350i + ")";
    }

    public /* synthetic */ e(long j2, int i2, float f2, float f3, float f4, float[] fArr, float f5, boolean z2, boolean z3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0L : j2, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? 0.0f : f2, (i3 & 8) != 0 ? 0.0f : f3, (i3 & 16) != 0 ? 0.0f : f4, (i3 & 32) != 0 ? new float[33] : fArr, (i3 & 64) == 0 ? f5 : 0.0f, (i3 & 128) != 0 ? false : z2, (i3 & 256) == 0 ? z3 : false);
    }
}
