package z;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public final class g extends f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f7123b = {1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f7124c = {1, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7125a = 0;

    @Override // z.f
    public com.google.android.material.carousel.b c(b bVar, View view) {
        float containerHeight = bVar.getContainerHeight();
        if (bVar.c()) {
            containerHeight = bVar.a();
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f2 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        float measuredHeight = view.getMeasuredHeight();
        if (bVar.c()) {
            f2 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            measuredHeight = view.getMeasuredWidth();
        }
        float f3 = f2;
        float fH = com.google.android.material.carousel.a.h(view.getContext()) + f3;
        float fG = com.google.android.material.carousel.a.g(view.getContext()) + f3;
        float fMin = Math.min(measuredHeight + f3, containerHeight);
        float fClamp = MathUtils.clamp((measuredHeight / 3.0f) + f3, com.google.android.material.carousel.a.h(view.getContext()) + f3, com.google.android.material.carousel.a.g(view.getContext()) + f3);
        float f4 = (fMin + fClamp) / 2.0f;
        int[] iArrA = f7123b;
        if (containerHeight < 2.0f * fH) {
            iArrA = new int[]{0};
        }
        int[] iArrA2 = f7124c;
        if (bVar.b() == 1) {
            iArrA = f.a(iArrA);
            iArrA2 = f.a(iArrA2);
        }
        int[] iArr = iArrA;
        int[] iArr2 = iArrA2;
        int iMax = (int) Math.max(1.0d, Math.floor(((containerHeight - (com.google.android.material.carousel.a.i(iArr2) * f4)) - (com.google.android.material.carousel.a.i(iArr) * fG)) / fMin));
        int iCeil = (int) Math.ceil(containerHeight / fMin);
        int i2 = (iCeil - iMax) + 1;
        int[] iArr3 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr3[i3] = iCeil - i3;
        }
        C0775a c0775aC = C0775a.c(containerHeight, fClamp, fH, fG, iArr, f4, iArr2, fMin, iArr3);
        this.f7125a = c0775aC.e();
        if (e(c0775aC, bVar.getItemCount())) {
            c0775aC = C0775a.c(containerHeight, fClamp, fH, fG, new int[]{c0775aC.f7112c}, f4, new int[]{c0775aC.f7113d}, fMin, new int[]{c0775aC.f7116g});
        }
        return com.google.android.material.carousel.a.d(view.getContext(), f3, containerHeight, c0775aC, bVar.b());
    }

    @Override // z.f
    public boolean d(b bVar, int i2) {
        return (i2 < this.f7125a && bVar.getItemCount() >= this.f7125a) || (i2 >= this.f7125a && bVar.getItemCount() < this.f7125a);
    }

    public boolean e(C0775a c0775a, int i2) {
        int iE = c0775a.e() - i2;
        boolean z2 = iE > 0 && (c0775a.f7112c > 0 || c0775a.f7113d > 1);
        while (iE > 0) {
            int i3 = c0775a.f7112c;
            if (i3 > 0) {
                c0775a.f7112c = i3 - 1;
            } else {
                int i4 = c0775a.f7113d;
                if (i4 > 1) {
                    c0775a.f7113d = i4 - 1;
                }
            }
            iE--;
        }
        return z2;
    }
}
