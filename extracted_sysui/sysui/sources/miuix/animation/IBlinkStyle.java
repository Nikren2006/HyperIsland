package miuix.animation;

import android.graphics.RectF;
import androidx.annotation.IntRange;
import miuix.animation.ITouchStyle;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes4.dex */
public interface IBlinkStyle extends IStateContainer {

    public enum BlinkType {
        HIGHLIGHT,
        NORMAL
    }

    IBlinkStyle resetConfig();

    IBlinkStyle setBlinkPadding(float f2, float f3, float f4, float f5);

    IBlinkStyle setBlinkRadius(float f2);

    IBlinkStyle setBlinkRadius(float f2, float f3, float f4, float f5);

    IBlinkStyle setBlinkRect(RectF rectF, ITouchStyle.TouchRectGravity touchRectGravity);

    IBlinkStyle setInterval(long j2);

    IBlinkStyle setLimitCount(int i2);

    IBlinkStyle setTintMode(@IntRange(from = -1, to = 3) int i2);

    IBlinkStyle setToHighlightConfig(AnimConfig animConfig);

    IBlinkStyle setToNormalConfig(AnimConfig animConfig);

    void startBlink(int i2, AnimConfig... animConfigArr);

    void startBlink(AnimConfig... animConfigArr);

    void stopBlink();
}
