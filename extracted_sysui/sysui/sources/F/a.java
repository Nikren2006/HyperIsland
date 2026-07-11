package F;

import L.b;
import android.content.Context;
import android.graphics.Color;
import androidx.core.graphics.ColorUtils;
import t.AbstractC0741a;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f161f = (int) Math.round(5.1000000000000005d);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f163b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f164c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f165d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f166e;

    public a(Context context) {
        this(b.b(context, AbstractC0741a.f6515o, false), C.a.b(context, AbstractC0741a.f6514n, 0), C.a.b(context, AbstractC0741a.f6513m, 0), C.a.b(context, AbstractC0741a.f6511k, 0), context.getResources().getDisplayMetrics().density);
    }

    public float a(float f2) {
        if (this.f166e <= 0.0f || f2 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f2 / r2)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i2, float f2) {
        int i3;
        float fA = a(f2);
        int iAlpha = Color.alpha(i2);
        int iJ = C.a.j(ColorUtils.setAlphaComponent(i2, 255), this.f163b, fA);
        if (fA > 0.0f && (i3 = this.f164c) != 0) {
            iJ = C.a.i(iJ, ColorUtils.setAlphaComponent(i3, f161f));
        }
        return ColorUtils.setAlphaComponent(iJ, iAlpha);
    }

    public int c(int i2, float f2) {
        return (this.f162a && e(i2)) ? b(i2, f2) : i2;
    }

    public boolean d() {
        return this.f162a;
    }

    public final boolean e(int i2) {
        return ColorUtils.setAlphaComponent(i2, 255) == this.f165d;
    }

    public a(boolean z2, int i2, int i3, int i4, float f2) {
        this.f162a = z2;
        this.f163b = i2;
        this.f164c = i3;
        this.f165d = i4;
        this.f166e = f2;
    }
}
