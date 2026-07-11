package miui.systemui.controlcenter.widget;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;

/* JADX INFO: loaded from: classes.dex */
public final class VerticalSeekBarTouchAnim$scaleAnim$2 extends o implements Function0 {
    final /* synthetic */ VerticalSeekBarTouchAnim this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerticalSeekBarTouchAnim$scaleAnim$2(VerticalSeekBarTouchAnim verticalSeekBarTouchAnim) {
        super(0);
        this.this$0 = verticalSeekBarTouchAnim;
    }

    @Override // kotlin.jvm.functions.Function0
    public final IStateStyle invoke() {
        IStateStyle iStateStyleUseValue = Folme.useValue(this.this$0);
        iStateStyleUseValue.setTo("scaleXY", Float.valueOf(1.0f));
        return iStateStyleUseValue;
    }
}
