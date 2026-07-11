package g0;

import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.mgl.math.Vector2;

/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Vector2[] f4326a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f4327b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f4328c;

    public b(Vector2[] lights, float f2, float f3) {
        n.g(lights, "lights");
        this.f4326a = lights;
        this.f4327b = f2;
        this.f4328c = f3;
    }

    public final float a() {
        return this.f4328c;
    }

    public final Vector2[] b() {
        return this.f4326a;
    }

    public final float c() {
        return this.f4327b;
    }

    public final void d(float f2) {
        this.f4328c = f2;
    }

    public final void e(float f2) {
        this.f4327b = f2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f4327b == bVar.f4327b && this.f4328c != bVar.f4328c && Arrays.equals(this.f4326a, bVar.f4326a);
    }

    public int hashCode() {
        return Objects.hash(Float.valueOf(this.f4327b), Float.valueOf(this.f4328c), this.f4326a);
    }

    public String toString() {
        return "IntelligentLightFragUniforms(lights=" + Arrays.toString(this.f4326a) + ", power=" + this.f4327b + ", lightness=" + this.f4328c + ")";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ b(Vector2[] vector2Arr, float f2, float f3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 1) != 0) {
            Vector2[] vector2Arr2 = new Vector2[11];
            for (int i3 = 0; i3 < 11; i3++) {
                vector2Arr2[i3] = new Vector2(0.0f);
            }
            vector2Arr = vector2Arr2;
        }
        this(vector2Arr, (i2 & 2) != 0 ? 0.0f : f2, (i2 & 4) != 0 ? 0.0f : f3);
    }
}
