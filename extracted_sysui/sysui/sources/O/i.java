package O;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class i implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f502a;

    public i(float f2) {
        this.f502a = f2;
    }

    public static float b(RectF rectF) {
        return Math.min(rectF.width(), rectF.height());
    }

    @Override // O.c
    public float a(RectF rectF) {
        return this.f502a * b(rectF);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof i) && this.f502a == ((i) obj).f502a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f502a)});
    }
}
