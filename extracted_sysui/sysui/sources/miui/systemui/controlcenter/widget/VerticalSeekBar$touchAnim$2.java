package miui.systemui.controlcenter.widget;

import android.content.Context;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class VerticalSeekBar$touchAnim$2 extends o implements Function0 {
    final /* synthetic */ Context $context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerticalSeekBar$touchAnim$2(Context context) {
        super(0);
        this.$context = context;
    }

    @Override // kotlin.jvm.functions.Function0
    public final VerticalSeekBarTouchAnim invoke() {
        return new VerticalSeekBarTouchAnim(this.$context);
    }
}
