package O;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f448a;

    public a(float f2) {
        this.f448a = f2;
    }

    @Override // O.c
    public float a(RectF rectF) {
        return this.f448a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f448a == ((a) obj).f448a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f448a)});
    }
}
