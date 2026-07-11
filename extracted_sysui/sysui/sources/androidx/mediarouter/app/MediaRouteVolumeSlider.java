package androidx.mediarouter.app;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.widget.AppCompatSeekBar;

/* JADX INFO: loaded from: classes.dex */
class MediaRouteVolumeSlider extends AppCompatSeekBar {
    private static final String TAG = "MediaRouteVolumeSlider";
    private int mBackgroundColor;
    private final float mDisabledAlpha;
    private boolean mHideThumb;
    private int mProgressAndThumbColor;
    private Drawable mThumb;

    public MediaRouteVolumeSlider(Context context) {
        this(context, null);
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int i2 = isEnabled() ? 255 : (int) (this.mDisabledAlpha * 255.0f);
        Drawable drawable = this.mThumb;
        int i3 = this.mProgressAndThumbColor;
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        drawable.setColorFilter(i3, mode);
        this.mThumb.setAlpha(i2);
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) getProgressDrawable();
            Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.progress);
            layerDrawable.findDrawableByLayerId(R.id.background).setColorFilter(this.mBackgroundColor, mode);
            progressDrawable = drawableFindDrawableByLayerId;
        }
        progressDrawable.setColorFilter(this.mProgressAndThumbColor, mode);
        progressDrawable.setAlpha(i2);
    }

    public void setColor(int i2) {
        setColor(i2, i2);
    }

    public void setHideThumb(boolean z2) {
        if (this.mHideThumb == z2) {
            return;
        }
        this.mHideThumb = z2;
        super.setThumb(z2 ? null : this.mThumb);
    }

    @Override // android.widget.AbsSeekBar
    public void setThumb(Drawable drawable) {
        this.mThumb = drawable;
        if (this.mHideThumb) {
            drawable = null;
        }
        super.setThumb(drawable);
    }

    public MediaRouteVolumeSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.appcompat.R.attr.seekBarStyle);
    }

    public void setColor(int i2, int i3) {
        if (this.mProgressAndThumbColor != i2) {
            if (Color.alpha(i2) != 255) {
                Log.e(TAG, "Volume slider progress and thumb color cannot be translucent: #" + Integer.toHexString(i2));
            }
            this.mProgressAndThumbColor = i2;
        }
        if (this.mBackgroundColor != i3) {
            if (Color.alpha(i3) != 255) {
                Log.e(TAG, "Volume slider background color cannot be translucent: #" + Integer.toHexString(i3));
            }
            this.mBackgroundColor = i3;
        }
    }

    public MediaRouteVolumeSlider(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(context);
    }
}
