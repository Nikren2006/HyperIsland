package miui.systemui.devicecontrols.management;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CandidateInfo {
    public final boolean enabled;

    public CandidateInfo(boolean z2) {
        this.enabled = z2;
    }

    public abstract String getKey();

    public abstract Drawable loadIcon();

    public abstract CharSequence loadLabel();

    public abstract Drawable loadNormalIcon();
}
