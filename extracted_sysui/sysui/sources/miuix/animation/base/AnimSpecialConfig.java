package miuix.animation.base;

import android.util.Log;
import miuix.animation.internal.DesignReview;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.CommonUtils;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes4.dex */
public class AnimSpecialConfig extends AnimConfig implements DesignReview {
    public boolean hasSetSafeValue;
    public double maxValue;
    public double minValue;

    public AnimSpecialConfig() {
        super(true);
    }

    @Override // miuix.animation.base.AnimConfig
    @Deprecated
    public AnimConfig addListeners(TransitionListener... transitionListenerArr) {
        Log.w(CommonUtils.TAG, "warning: AnimSpecialConfig should not use addListeners trace:" + Log.getStackTraceString(new Throwable()));
        return this;
    }

    @Override // miuix.animation.base.AnimConfig, miuix.animation.internal.DesignReview
    public String getDesignInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"");
        sb.append("ease");
        sb.append("\": ");
        sb.append(this.ease.getDesignInfo());
        if (this.delay > 0) {
            sb.append(", ");
            sb.append("\"");
            sb.append("delay");
            sb.append("\": ");
            sb.append(this.delay);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // miuix.animation.base.AnimConfig
    @Deprecated
    public AnimSpecialConfig getSpecialConfig(String str) {
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    @Deprecated
    public AnimSpecialConfig queryAndCreateSpecial(String str) {
        return this;
    }

    public AnimSpecialConfig setMinAndMax(double d2, double d3) {
        this.hasSetSafeValue = true;
        if (d2 > d3) {
            this.minValue = d3;
            this.maxValue = d2;
        } else {
            this.minValue = d2;
            this.maxValue = d3;
        }
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    @Deprecated
    public AnimConfig setSpecial(String str, AnimSpecialConfig animSpecialConfig) {
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    @Deprecated
    public AnimSpecialConfig getSpecialConfig(FloatProperty floatProperty) {
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    @Deprecated
    public AnimSpecialConfig queryAndCreateSpecial(FloatProperty floatProperty) {
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    @Deprecated
    public AnimConfig setSpecial(FloatProperty floatProperty, AnimSpecialConfig animSpecialConfig) {
        return this;
    }

    public AnimSpecialConfig setSpecial(long j2, float... fArr) {
        super.setSpecial(this, (EaseManager.EaseStyle) null, j2, fArr);
        return this;
    }

    public AnimSpecialConfig setSpecial(EaseManager.EaseStyle easeStyle, float... fArr) {
        super.setSpecial(this, easeStyle, -1L, fArr);
        return this;
    }

    public AnimSpecialConfig setSpecial(EaseManager.EaseStyle easeStyle, long j2, float... fArr) {
        super.setSpecial(this, easeStyle, j2, fArr);
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    public AnimConfig setSpecial(String str, long j2, float... fArr) {
        super.setSpecial(this, (EaseManager.EaseStyle) null, j2, fArr);
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    public AnimConfig setSpecial(String str, EaseManager.EaseStyle easeStyle, float... fArr) {
        super.setSpecial(this, easeStyle, -1L, fArr);
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    public AnimConfig setSpecial(String str, EaseManager.EaseStyle easeStyle, long j2, float... fArr) {
        super.setSpecial(this, easeStyle, j2, fArr);
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    public AnimConfig setSpecial(FloatProperty floatProperty, long j2, float... fArr) {
        super.setSpecial(this, (EaseManager.EaseStyle) null, j2, fArr);
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    public AnimConfig setSpecial(FloatProperty floatProperty, EaseManager.EaseStyle easeStyle, float... fArr) {
        super.setSpecial(this, easeStyle, -1L, fArr);
        return this;
    }

    @Override // miuix.animation.base.AnimConfig
    public AnimConfig setSpecial(FloatProperty floatProperty, EaseManager.EaseStyle easeStyle, long j2, float... fArr) {
        super.setSpecial(this, easeStyle, j2, fArr);
        return this;
    }
}
