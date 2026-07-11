package O;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f449a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f450b;

    public b(float f2, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f449a;
            f2 += ((b) cVar).f450b;
        }
        this.f449a = cVar;
        this.f450b = f2;
    }

    @Override // O.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f449a.a(rectF) + this.f450b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f449a.equals(bVar.f449a) && this.f450b == bVar.f450b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f449a, Float.valueOf(this.f450b)});
    }
}
