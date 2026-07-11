package miuix.appcompat.internal.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import miuix.appcompat.R;
import miuix.graphics.shadow.DropShadowConfig;
import miuix.graphics.shadow.DropShadowHelper;
import miuix.graphics.shadow.DropShadowLayerHelper;
import miuix.internal.util.AttributeResolver;

/* JADX INFO: loaded from: classes3.dex */
public class OutDropShadowView extends View {
    private Path mClipOutPath;
    private float mDensityDpi;
    private DropShadowHelper mDropShadowHelper;
    private int mHostViewRadius;

    public OutDropShadowView(Context context) {
        super(context);
        this.mHostViewRadius = 0;
        this.mClipOutPath = new Path();
        init();
    }

    private void init() {
        this.mDensityDpi = getContext().getResources().getDisplayMetrics().densityDpi;
        DropShadowLayerHelper dropShadowLayerHelper = new DropShadowLayerHelper(getContext(), new DropShadowConfig.Builder(50.0f).setOffsetYDp(6).create(), AttributeResolver.resolveBoolean(getContext(), R.attr.isLightTheme, true));
        this.mDropShadowHelper = dropShadowLayerHelper;
        dropShadowLayerHelper.setEnableMiShadow(false);
        this.mDropShadowHelper.updateShadowRect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        setBackgroundColor(0);
        setImportantForAccessibility(4);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        canvas.save();
        if (this.mDropShadowHelper != null) {
            canvas.clipOutPath(this.mClipOutPath);
            this.mDropShadowHelper.drawShadow(canvas, this.mHostViewRadius);
        }
        super.draw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mDropShadowHelper.enableViewShadow(this, true, 2);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        DropShadowHelper dropShadowHelper;
        if (configuration.densityDpi == this.mDensityDpi || (dropShadowHelper = this.mDropShadowHelper) == null) {
            return;
        }
        dropShadowHelper.onConfigChanged(this, configuration, AttributeResolver.resolveBoolean(getContext(), R.attr.isLightTheme, true));
    }

    @Override // android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        DropShadowHelper dropShadowHelper = this.mDropShadowHelper;
        if (dropShadowHelper != null) {
            dropShadowHelper.updateShadowRect(i2, i3, i4, i5);
            this.mClipOutPath.reset();
            Path path = this.mClipOutPath;
            RectF shadowRect = this.mDropShadowHelper.getShadowRect();
            int i6 = this.mHostViewRadius;
            path.addRoundRect(shadowRect, i6, i6, Path.Direction.CW);
        }
    }

    public void onWillRemoved() {
        this.mDropShadowHelper.enableViewShadow(this, false, 2);
    }

    public void setShadowHostViewRadius(int i2) {
        this.mHostViewRadius = i2;
        this.mClipOutPath.reset();
        Path path = this.mClipOutPath;
        RectF shadowRect = this.mDropShadowHelper.getShadowRect();
        int i3 = this.mHostViewRadius;
        path.addRoundRect(shadowRect, i3, i3, Path.Direction.CW);
    }

    public OutDropShadowView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHostViewRadius = 0;
        this.mClipOutPath = new Path();
        init();
    }

    public OutDropShadowView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mHostViewRadius = 0;
        this.mClipOutPath = new Path();
        init();
    }

    public OutDropShadowView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mHostViewRadius = 0;
        this.mClipOutPath = new Path();
        init();
    }
}
