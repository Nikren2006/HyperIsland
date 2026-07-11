package com.miui.maml.animation;

import android.animation.ArgbEvaluator;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.miui.maml.animation.BaseAnimation;
import com.miui.maml.elements.ScreenElement;
import com.miui.maml.util.ColorUtils;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes2.dex */
public class VariableAnimation extends BaseAnimation {
    public static final String INNER_TAG_NAME = "AniFrame";
    public static final String TAG_NAME = "VariableAnimation";
    private ArgbEvaluator mArgbEvaluator;
    private String[] mCurValuesStr;

    public static class Color extends BaseAnimation.AnimationItem {
        public String color;

        public Color(BaseAnimation baseAnimation, Element element) {
            super(baseAnimation, element);
            this.color = element.getAttribute(TypedValues.Custom.S_COLOR);
        }
    }

    public VariableAnimation(Element element, ScreenElement screenElement) {
        super(element, INNER_TAG_NAME, screenElement);
        if (TypedValues.Custom.S_COLOR.equals(this.mType)) {
            this.mCurValuesStr = new String[this.mAttrs.length];
            this.mArgbEvaluator = new ArgbEvaluator();
        }
    }

    public final double getValue() {
        return getCurValue(0);
    }

    public final String getValueStr() {
        String[] strArr = this.mCurValuesStr;
        return (strArr == null || strArr.length <= 0) ? "" : strArr[0];
    }

    @Override // com.miui.maml.animation.BaseAnimation
    public BaseAnimation.AnimationItem onCreateItem(BaseAnimation baseAnimation, Element element) {
        return TypedValues.Custom.S_COLOR.equals(this.mType) ? new Color(baseAnimation, element) : new BaseAnimation.AnimationItem(baseAnimation, element);
    }

    @Override // com.miui.maml.animation.BaseAnimation
    public void onTick(BaseAnimation.AnimationItem animationItem, BaseAnimation.AnimationItem animationItem2, float f2) {
        if (!TypedValues.Custom.S_COLOR.equals(this.mType)) {
            super.onTick(animationItem, animationItem2, f2);
            return;
        }
        if (animationItem == null || animationItem2 == null) {
            return;
        }
        int length = this.mAttrs.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.mCurValuesStr[i2] = ColorUtils.ColorToHex(((Integer) this.mArgbEvaluator.evaluate(f2, Integer.valueOf(android.graphics.Color.parseColor(((Color) animationItem).color)), Integer.valueOf(android.graphics.Color.parseColor(((Color) animationItem2).color)))).intValue());
        }
    }
}
