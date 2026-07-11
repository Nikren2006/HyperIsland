package miui.systemui.dynamicisland.window;

import android.graphics.Paint;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowController$paint$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final DynamicIslandWindowController$paint$2 INSTANCE = new DynamicIslandWindowController$paint$2();

    public DynamicIslandWindowController$paint$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Paint invoke() {
        Paint paint = new Paint(1);
        paint.setColor(-65281);
        paint.setStrokeWidth(10.0f);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }
}
