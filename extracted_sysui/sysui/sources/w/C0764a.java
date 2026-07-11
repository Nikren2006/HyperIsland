package w;

import android.view.View;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Iterator;
import java.util.List;
import u.AbstractC0743a;

/* JADX INFO: renamed from: w.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0764a extends WindowInsetsAnimationCompat.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f6975a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6976b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f6977c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f6978d;

    public C0764a(View view) {
        super(0);
        this.f6978d = new int[2];
        this.f6975a = view;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        this.f6975a.setTranslationY(0.0f);
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void onPrepare(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        this.f6975a.getLocationOnScreen(this.f6978d);
        this.f6976b = this.f6978d[1];
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List list) {
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if ((((WindowInsetsAnimationCompat) it.next()).getTypeMask() & WindowInsetsCompat.Type.ime()) != 0) {
                this.f6975a.setTranslationY(AbstractC0743a.c(this.f6977c, 0, r0.getInterpolatedFraction()));
                break;
            }
        }
        return windowInsetsCompat;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
        this.f6975a.getLocationOnScreen(this.f6978d);
        int i2 = this.f6976b - this.f6978d[1];
        this.f6977c = i2;
        this.f6975a.setTranslationY(i2);
        return boundsCompat;
    }
}
