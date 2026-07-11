package miui.systemui.dynamicisland.window.domain.interactor;

import android.graphics.Paint;
import androidx.core.view.InputDeviceCompat;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandTouchRegionInteractor$paint$2 extends o implements Function0 {
    public static final DynamicIslandTouchRegionInteractor$paint$2 INSTANCE = new DynamicIslandTouchRegionInteractor$paint$2();

    public DynamicIslandTouchRegionInteractor$paint$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Paint invoke() {
        Paint paint = new Paint(1);
        paint.setColor(InputDeviceCompat.SOURCE_ANY);
        paint.setStrokeWidth(3.0f);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }
}
