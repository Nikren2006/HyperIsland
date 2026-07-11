package miui.systemui.controlcenter.qs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class DrawableIcon extends QSTile.Icon {
    private final Drawable drawable;
    private final Drawable invisibleDrawable;

    public DrawableIcon(Drawable drawable) {
        n.g(drawable, "drawable");
        this.drawable = drawable;
        Drawable.ConstantState constantState = drawable.getConstantState();
        this.invisibleDrawable = constantState != null ? constantState.newDrawable() : null;
    }

    public Drawable getDrawable(Context context) {
        return this.drawable;
    }

    public Drawable getInvisibleDrawable(Context context) {
        return this.invisibleDrawable;
    }

    public String toString() {
        return "DrawableIcon";
    }
}
