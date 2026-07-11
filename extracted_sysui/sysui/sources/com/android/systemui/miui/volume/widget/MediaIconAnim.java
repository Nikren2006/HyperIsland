package com.android.systemui.miui.volume.widget;

import H0.d;
import H0.e;
import android.animation.AnimatorSet;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.android.systemui.miui.volume.R;
import com.android.systemui.miui.volume.VolumeColumn;
import com.android.systemui.miui.volume.widget.MediaIconAnim$property$2;
import kotlin.jvm.internal.n;
import miui.systemui.animation.drawable.SVGUtils;
import miui.systemui.animation.drawable.SVGUtilsExt;
import miui.systemui.animation.drawable.VectorDrawableSetParams;
import miui.systemui.util.MiuiMathUtils;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaIconAnim {
    private final ImageView icon;
    private IStateStyle mediaIconAnim;
    private VectorDrawableSetParams mediaIconAnimParams;
    private final d property$delegate;

    public MediaIconAnim(ImageView icon) {
        n.g(icon, "icon");
        this.icon = icon;
        this.property$delegate = e.b(new MediaIconAnim$property$2(this));
    }

    private final MediaIconAnim$property$2.AnonymousClass1 getProperty() {
        return (MediaIconAnim$property$2.AnonymousClass1) this.property$delegate.getValue();
    }

    private final boolean mediaIconNoAnim() {
        return (SVGUtilsExt.INSTANCE.getSupportVectorDrawableParams() && (this.icon.getDrawable() instanceof AnimatedVectorDrawable)) ? false : true;
    }

    private final void updateMediaIconAnimParams() {
        AnimatorSet animatorSetCompat;
        IStateStyle iStateStyleUseValue = this.mediaIconAnim;
        if (iStateStyleUseValue == null) {
            iStateStyleUseValue = Folme.useValue(this.icon);
        }
        this.mediaIconAnim = iStateStyleUseValue;
        Drawable drawable = this.icon.getDrawable();
        AnimatedVectorDrawable animatedVectorDrawable = drawable instanceof AnimatedVectorDrawable ? (AnimatedVectorDrawable) drawable : null;
        if (animatedVectorDrawable == null || (animatorSetCompat = SVGUtilsExt.getAnimatorSetCompat(animatedVectorDrawable)) == null) {
            return;
        }
        VectorDrawableSetParams vectorDrawableSetParams = this.mediaIconAnimParams;
        if (vectorDrawableSetParams == null) {
            vectorDrawableSetParams = new VectorDrawableSetParams();
        }
        this.mediaIconAnimParams = vectorDrawableSetParams;
        if (vectorDrawableSetParams != null) {
            vectorDrawableSetParams.clearDrawableParams();
        }
        SVGUtils.analyzeAnimator(animatorSetCompat, this.mediaIconAnimParams, this.icon.getContext().getColor(R.color.miui_volume_media_animate_icon), 0, 3017);
    }

    public final void release() {
        Folme.clean(this.icon);
        IStateStyle iStateStyle = this.mediaIconAnim;
        if (iStateStyle != null) {
            iStateStyle.clean();
        }
        this.mediaIconAnim = null;
        this.mediaIconAnimParams = null;
    }

    public final void updateMediaIconAnimProgress(boolean z2, float f2, float f3, boolean z3, boolean z4) {
        float f4;
        if (mediaIconNoAnim()) {
            return;
        }
        MiuiMathUtils miuiMathUtils = MiuiMathUtils.INSTANCE;
        float fRoundTo3DecimalPlaces = VolumeColumn.Companion.roundTo3DecimalPlaces(miuiMathUtils.lerpInv(0.0f, f2, f3));
        if (fRoundTo3DecimalPlaces == 0.0f || z3) {
            f4 = 0.0f;
        } else {
            f4 = 0.33333334f;
            if (fRoundTo3DecimalPlaces >= 0.33333334f) {
                f4 = 0.6666667f;
                if (fRoundTo3DecimalPlaces >= 0.6666667f) {
                    f4 = 1.0f;
                }
            }
        }
        float fConstrain = miuiMathUtils.constrain(f4, 5.0E-4f, 1.0f);
        if (this.mediaIconAnim == null) {
            updateMediaIconAnimParams();
        }
        IStateStyle iStateStyle = this.mediaIconAnim;
        if (iStateStyle != null) {
            if (z2 && z4) {
                iStateStyle.to(getProperty(), Float.valueOf(fConstrain));
            } else {
                iStateStyle.setTo(getProperty(), Float.valueOf(0.0f));
                iStateStyle.setTo(getProperty(), Float.valueOf(fConstrain));
            }
        }
    }
}
