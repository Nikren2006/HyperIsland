package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
class WrappedDrawableApi21 extends WrappedDrawableApi14 {
    private static final String TAG = "WrappedDrawableApi21";
    private static Method sIsProjectedDrawableMethod;

    public WrappedDrawableApi21(Drawable drawable) {
        super(drawable);
        findAndCacheIsProjectedDrawableMethod();
    }

    private void findAndCacheIsProjectedDrawableMethod() {
        if (sIsProjectedDrawableMethod == null) {
            try {
                sIsProjectedDrawableMethod = Drawable.class.getDeclaredMethod("isProjected", null);
            } catch (Exception e2) {
                Log.w(TAG, "Failed to retrieve Drawable#isProjected() method", e2);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        this.mDrawable.getOutline(outline);
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14
    public boolean isCompatTintEnabled() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isProjected() {
        Method method;
        Drawable drawable = this.mDrawable;
        if (drawable == null || (method = sIsProjectedDrawableMethod) == null) {
            return false;
        }
        try {
            return ((Boolean) method.invoke(drawable, null)).booleanValue();
        } catch (Exception e2) {
            Log.w(TAG, "Error calling Drawable#isProjected() method", e2);
            return false;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f2, float f3) {
        this.mDrawable.setHotspot(f2, f3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i2, int i3, int i4, int i5) {
        this.mDrawable.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        if (!super.setState(iArr)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(int i2) {
        if (isCompatTintEnabled()) {
            super.setTint(i2);
        } else {
            this.mDrawable.setTint(i2);
        }
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList) {
        if (isCompatTintEnabled()) {
            super.setTintList(colorStateList);
        } else {
            this.mDrawable.setTintList(colorStateList);
        }
    }

    @Override // androidx.core.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (isCompatTintEnabled()) {
            super.setTintMode(mode);
        } else {
            this.mDrawable.setTintMode(mode);
        }
    }

    public WrappedDrawableApi21(WrappedDrawableState wrappedDrawableState, Resources resources) {
        super(wrappedDrawableState, resources);
        findAndCacheIsProjectedDrawableMethod();
    }
}
