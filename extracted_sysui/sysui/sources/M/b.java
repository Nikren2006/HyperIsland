package M;

import android.R;
import android.content.res.ColorStateList;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f406a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f407b = {R.attr.state_pressed};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f408c = {R.attr.state_hovered, R.attr.state_focused};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f409d = {R.attr.state_focused};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f410e = {R.attr.state_hovered};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f411f = {R.attr.state_selected, R.attr.state_pressed};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f412g = {R.attr.state_selected, R.attr.state_hovered, R.attr.state_focused};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f413h = {R.attr.state_selected, R.attr.state_focused};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f414i = {R.attr.state_selected, R.attr.state_hovered};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f415j = {R.attr.state_selected};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f416k = {R.attr.state_enabled, R.attr.state_pressed};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f417l = b.class.getSimpleName();

    public static ColorStateList a(ColorStateList colorStateList) {
        return colorStateList != null ? colorStateList : ColorStateList.valueOf(0);
    }

    public static boolean b(int[] iArr) {
        boolean z2 = false;
        boolean z3 = false;
        for (int i2 : iArr) {
            if (i2 == 16842910) {
                z2 = true;
            } else if (i2 == 16842908 || i2 == 16842919 || i2 == 16843623) {
                z3 = true;
            }
        }
        return z2 && z3;
    }
}
