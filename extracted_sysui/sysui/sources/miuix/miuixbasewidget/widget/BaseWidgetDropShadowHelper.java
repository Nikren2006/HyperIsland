package miuix.miuixbasewidget.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import miuix.core.util.MaterialConfig;
import miuix.core.util.MiShadowUtils;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.RomUtils;
import miuix.miuixbasewidget.widget.DropShadowConfig;

/* JADX INFO: loaded from: classes.dex */
class BaseWidgetDropShadowHelper {
    protected Context mContext;
    protected DropShadowConfig mDropShadowConfig;
    protected boolean mEnableMiShadow;

    @Nullable
    protected boolean[] mOriginViewParentClipState;
    protected int mShadowColor;
    protected int mShadowColorAlpha;
    protected float mOffsetXPx = 0.0f;
    protected float mOffsetYPx = 0.0f;
    protected float mBlurRadiusPx = 0.0f;
    protected float mAlpha = 1.0f;
    protected float mLastDensity = 0.0f;
    protected RectF mShadowRect = new RectF();
    protected Paint mShadowPaint = new Paint();
    protected boolean mEnableShadow = false;

    public BaseWidgetDropShadowHelper(Context context, DropShadowConfig dropShadowConfig, boolean z2) {
        boolean z3 = false;
        this.mContext = context;
        this.mDropShadowConfig = dropShadowConfig;
        if (RomUtils.getHyperOsVersion() >= 2 && MiShadowUtils.SUPPORT_MI_SHADOW) {
            z3 = true;
        }
        this.mEnableMiShadow = z3;
        updateShadowValues(z2, context.getResources().getDisplayMetrics().density, dropShadowConfig);
    }

    private void enableViewShadowInternal(View view, boolean z2, int i2) {
        this.mEnableShadow = z2;
        refreshViewShadow(view);
        if (this.mEnableShadow) {
            this.mOriginViewParentClipState = new boolean[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                Object parent = view.getParent();
                if (parent == null) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) parent;
                this.mOriginViewParentClipState[i3] = viewGroup.getClipChildren();
                viewGroup.setClipChildren(false);
                view = (View) parent;
            }
            return;
        }
        boolean[] zArr = this.mOriginViewParentClipState;
        if (zArr != null && zArr.length >= i2) {
            for (int i4 = 0; i4 < i2; i4++) {
                Object parent2 = view.getParent();
                if (parent2 == null) {
                    break;
                }
                ((ViewGroup) parent2).setClipChildren(this.mOriginViewParentClipState[i4]);
                view = (View) parent2;
            }
        }
        this.mOriginViewParentClipState = null;
    }

    public void drawShadow(Canvas canvas, float f2) {
        if (this.mEnableMiShadow) {
            return;
        }
        canvas.drawRoundRect(this.mShadowRect, f2, f2, this.mShadowPaint);
    }

    public void enableViewShadow(View view, boolean z2, int i2) {
        if (this.mEnableShadow == z2) {
            return;
        }
        enableViewShadowInternal(view, z2, i2);
    }

    public void invalidateShadow(View view, float f2) {
        setAlpha(f2);
        if (this.mEnableMiShadow) {
            refreshViewShadow(view);
        }
    }

    public void onConfigChanged(View view, Configuration configuration, boolean z2) {
        updateShadowValues(z2, (configuration.densityDpi * 1.0f) / 160.0f, this.mDropShadowConfig);
        this.mEnableShadow = this.mBlurRadiusPx > 0.0f;
        refreshViewShadow(view);
    }

    public void onDensityChanged(float f2, DropShadowConfig dropShadowConfig) {
        this.mOffsetXPx = MiuixUIUtils.dp2px(f2, dropShadowConfig.offsetXDp);
        this.mOffsetYPx = MiuixUIUtils.dp2px(f2, dropShadowConfig.offsetYDp);
        this.mBlurRadiusPx = MiuixUIUtils.dp2px(f2, dropShadowConfig.blurRadiusDp);
    }

    public void refreshViewShadow(View view) {
        if (!this.mEnableMiShadow) {
            view.invalidate();
            return;
        }
        if (!this.mEnableShadow) {
            MiShadowUtils.clearMiShadow(view);
            return;
        }
        int i2 = this.mShadowColor;
        float f2 = this.mOffsetXPx;
        float f3 = this.mOffsetYPx;
        float f4 = this.mBlurRadiusPx;
        DropShadowConfig dropShadowConfig = this.mDropShadowConfig;
        MiShadowUtils.setMiShadow(view, i2, f2, f3, f4, dropShadowConfig.shadowDispersion, dropShadowConfig.clipShadowEnable);
    }

    public void setAlpha(float f2) {
        if (this.mAlpha != f2) {
            this.mAlpha = f2;
            int i2 = (((int) (this.mShadowColorAlpha * f2)) << 24) | (16777215 & this.mShadowColor);
            this.mShadowColor = i2;
            this.mShadowPaint.setColor(i2);
            this.mShadowPaint.setShadowLayer(this.mBlurRadiusPx, this.mOffsetXPx, this.mOffsetYPx, this.mShadowColor);
        }
    }

    public void setClipShadow(boolean z2) {
        DropShadowConfig dropShadowConfig = this.mDropShadowConfig;
        if (dropShadowConfig == null || dropShadowConfig.clipShadowEnable == z2) {
            return;
        }
        dropShadowConfig.clipShadowEnable = z2;
    }

    public void setEnableMiShadow(boolean z2) {
        this.mEnableMiShadow = z2;
    }

    public void updateDropShadowConfig(MaterialConfig.ShadowConfig shadowConfig) {
        this.mDropShadowConfig = new DropShadowConfig.Builder(shadowConfig).create();
        updateShadowValues(true, this.mContext.getResources().getDisplayMetrics().density, this.mDropShadowConfig);
        this.mEnableShadow = this.mBlurRadiusPx > 0.0f;
    }

    public void updateShadowRect(int i2, int i3, int i4, int i5) {
        this.mShadowRect.set(0.0f, 0.0f, i4 - i2, i5 - i3);
    }

    public void updateShadowValues(boolean z2, float f2, DropShadowConfig dropShadowConfig) {
        int i2 = z2 ? dropShadowConfig.shadowColor : dropShadowConfig.shadowDarkColor;
        this.mShadowColor = i2;
        this.mShadowColorAlpha = (i2 >> 24) & 255;
        this.mShadowPaint.setColor(i2);
        if (this.mLastDensity != f2) {
            this.mLastDensity = f2;
            onDensityChanged(f2, dropShadowConfig);
        }
        this.mShadowPaint.setShadowLayer(this.mBlurRadiusPx, this.mOffsetXPx, this.mOffsetYPx, this.mShadowColor);
    }

    public void updateViewShadow(View view, int i2) {
        enableViewShadowInternal(view, this.mEnableShadow, i2);
    }
}
