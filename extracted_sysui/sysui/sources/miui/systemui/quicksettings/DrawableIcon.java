package miui.systemui.quicksettings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.systemui.plugins.qs.QSTile;

/* JADX INFO: loaded from: classes4.dex */
public class DrawableIcon extends QSTile.Icon {
    protected final Drawable mDrawable;
    protected final Drawable mInvisibleDrawable;

    public DrawableIcon(Drawable drawable) {
        this.mDrawable = drawable;
        this.mInvisibleDrawable = drawable.getConstantState().newDrawable();
    }

    public Drawable getDrawable(Context context) {
        return this.mDrawable;
    }

    public Drawable getInvisibleDrawable(Context context) {
        return this.mInvisibleDrawable;
    }
}
