package miui.systemui.controlcenter.widget;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class VerticalSeekBar$dragAnim$2 extends o implements Function0 {
    public static final VerticalSeekBar$dragAnim$2 INSTANCE = new VerticalSeekBar$dragAnim$2();

    public VerticalSeekBar$dragAnim$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final VerticalSeekBarDragAnim invoke() {
        return new VerticalSeekBarDragAnim();
    }
}
