package com.android.systemui;

import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import com.android.systemui.QSControlMiPlayDetailHeader;
import com.android.systemui.miplay.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.animation.drawable.SVGUtils;
import miui.systemui.animation.drawable.SVGUtilsExt;
import miui.systemui.animation.drawable.VectorDrawableSetParams;
import miui.systemui.util.MiuiMathUtils;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayVolumeIconAnim {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MiPlayVolumeIconAnim";
    private final FloatProperty<QSControlMiPlayDetailHeader.VolumeColumn> MEDIA_ICON;
    private final VectorDrawableSetParams mMediaAnimateIconParams;
    private final IStateStyle mMediaIconAnim;
    private final QSControlMiPlayDetailHeader.VolumeColumn volumeColumn;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public MiPlayVolumeIconAnim(QSControlMiPlayDetailHeader.VolumeColumn volumeColumn) {
        kotlin.jvm.internal.n.g(volumeColumn, "volumeColumn");
        this.volumeColumn = volumeColumn;
        this.mMediaAnimateIconParams = new VectorDrawableSetParams();
        IStateStyle iStateStyleUseValue = Folme.useValue(volumeColumn);
        kotlin.jvm.internal.n.f(iStateStyleUseValue, "useValue(...)");
        this.mMediaIconAnim = iStateStyleUseValue;
        this.MEDIA_ICON = new FloatProperty<QSControlMiPlayDetailHeader.VolumeColumn>() { // from class: com.android.systemui.MiPlayVolumeIconAnim$MEDIA_ICON$1
            {
                super("miplay_media_icon");
            }

            @Override // miuix.animation.property.FloatProperty
            public float getValue(QSControlMiPlayDetailHeader.VolumeColumn volumeColumn2) {
                if (volumeColumn2 != null) {
                    return this.this$0.mMediaAnimateIconParams.getFraction();
                }
                return 0.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(QSControlMiPlayDetailHeader.VolumeColumn volumeColumn2, float f2) {
                if (volumeColumn2 != null) {
                    this.this$0.mMediaAnimateIconParams.setFraction(f2);
                    ImageView icon = volumeColumn2.getIcon();
                    if (icon != null) {
                        icon.invalidate();
                    }
                }
            }
        };
    }

    private final void doMediaIconAnim(IStateStyle iStateStyle, float f2) {
        if (iStateStyle != null) {
            iStateStyle.to(this.MEDIA_ICON, Float.valueOf(f2));
        }
    }

    public final QSControlMiPlayDetailHeader.VolumeColumn getVolumeColumn() {
        return this.volumeColumn;
    }

    public final void release() {
        this.mMediaIconAnim.cancel(this.MEDIA_ICON);
        this.mMediaIconAnim.clean();
        this.volumeColumn.setIcon(null);
        Folme.clean(this.volumeColumn);
    }

    public final void updateMediaIconAnimParams(QSControlMiPlayDetailHeader.VolumeColumn column, Context context) {
        AnimatorSet animatorSetCompat;
        kotlin.jvm.internal.n.g(column, "column");
        kotlin.jvm.internal.n.g(context, "context");
        ImageView icon = column.getIcon();
        Drawable drawable = icon != null ? icon.getDrawable() : null;
        if (drawable == null || !(drawable instanceof AnimatedVectorDrawable) || (animatorSetCompat = SVGUtilsExt.getAnimatorSetCompat((AnimatedVectorDrawable) drawable)) == null) {
            return;
        }
        VectorDrawableSetParams vectorDrawableSetParams = this.mMediaAnimateIconParams;
        vectorDrawableSetParams.clearDrawableParams();
        SVGUtils.analyzeAnimator(animatorSetCompat, vectorDrawableSetParams, context.getColor(R.color.miplay_detail_volume_icon_color), 0, 3017);
    }

    public final void updateMediaIconAnimProgress(int i2) {
        MiuiMathUtils miuiMathUtils = MiuiMathUtils.INSTANCE;
        float fCalculatePercentage = miuiMathUtils.calculatePercentage(0, 1000, i2);
        Log.d(TAG, "updateMediaIconAnimProgress: " + i2 + ", progress: " + fCalculatePercentage);
        float f2 = 0.0f;
        if (fCalculatePercentage != 0.0f) {
            f2 = 0.33333334f;
            if (fCalculatePercentage >= 0.33333334f) {
                f2 = 0.6666667f;
                if (fCalculatePercentage >= 0.6666667f) {
                    f2 = 1.0f;
                }
            }
        }
        doMediaIconAnim(this.mMediaIconAnim, miuiMathUtils.constrain(f2, 0.001f, 1.0f));
    }
}
