package K;

import H.k;
import L.c;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.widget.CompoundButtonCompat;
import t.AbstractC0741a;
import t.i;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public class a extends AppCompatRadioButton {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f363c = i.f6688k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[][] f364d = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ColorStateList f365a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f366b;

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6493H);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f365a == null) {
            int iD = C.a.d(this, AbstractC0741a.f6505e);
            int iD2 = C.a.d(this, AbstractC0741a.f6508h);
            int iD3 = C.a.d(this, AbstractC0741a.f6511k);
            int[][] iArr = f364d;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = C.a.j(iD3, iD, 1.0f);
            iArr2[1] = C.a.j(iD3, iD2, 0.54f);
            iArr2[2] = C.a.j(iD3, iD2, 0.38f);
            iArr2[3] = C.a.j(iD3, iD2, 0.38f);
            this.f365a = new ColorStateList(iArr, iArr2);
        }
        return this.f365a;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f366b && CompoundButtonCompat.getButtonTintList(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z2) {
        this.f366b = z2;
        if (z2) {
            CompoundButtonCompat.setButtonTintList(this, getMaterialThemeColorsTintList());
        } else {
            CompoundButtonCompat.setButtonTintList(this, null);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f363c;
        super(S.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        Context context2 = getContext();
        TypedArray typedArrayI = k.i(context2, attributeSet, j.j3, i2, i3, new int[0]);
        int i4 = j.k3;
        if (typedArrayI.hasValue(i4)) {
            CompoundButtonCompat.setButtonTintList(this, c.a(context2, typedArrayI, i4));
        }
        this.f366b = typedArrayI.getBoolean(j.l3, false);
        typedArrayI.recycle();
    }
}
