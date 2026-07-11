package miuix.graphics.shadow;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public class DropShadowLayerHelper extends DropShadowHelper {
    public DropShadowLayerHelper(Context context, DropShadowConfig dropShadowConfig, boolean z2) {
        super(context, dropShadowConfig, z2);
    }

    @Override // miuix.graphics.shadow.DropShadowHelper
    public void onDensityChanged(float f2, DropShadowConfig dropShadowConfig) {
        super.onDensityChanged(f2, dropShadowConfig);
        this.mShadowPaint.setShadowLayer(this.mBlurRadiusPx, this.mOffsetXPx, this.mOffsetYPx, this.mShadowColor);
    }
}
