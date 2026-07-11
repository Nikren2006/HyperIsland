package miuix.graphics.shadow;

import android.content.Context;
import android.graphics.BlurMaskFilter;

/* JADX INFO: loaded from: classes3.dex */
public class DropShadowMaskHelper extends DropShadowHelper {
    private BlurMaskFilter mBlurMaskFilter;

    public DropShadowMaskHelper(Context context, DropShadowConfig dropShadowConfig, boolean z2) {
        super(context, dropShadowConfig, z2);
    }

    @Override // miuix.graphics.shadow.DropShadowHelper
    public void onDensityChanged(float f2, DropShadowConfig dropShadowConfig) {
        super.onDensityChanged(f2, dropShadowConfig);
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(this.mBlurRadiusPx, dropShadowConfig.blurStyle);
        this.mBlurMaskFilter = blurMaskFilter;
        this.mShadowPaint.setMaskFilter(blurMaskFilter);
    }

    @Override // miuix.graphics.shadow.DropShadowHelper
    public void updateShadowRect(int i2, int i3, int i4, int i5) {
        super.updateShadowRect(i2, i3, i4, i5);
        this.mShadowRect.offset(this.mOffsetXPx, this.mOffsetYPx);
    }
}
