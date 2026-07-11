package miui.systemui.flashlight;

import android.util.Log;
import android.view.WindowManager;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightManager$layoutParams$2 extends o implements Function0 {
    public static final MiFlashlightManager$layoutParams$2 INSTANCE = new MiFlashlightManager$layoutParams$2();

    public MiFlashlightManager$layoutParams$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final WindowManager.LayoutParams invoke() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 0, 0, 2038, -2121989856, -3);
        layoutParams.privateFlags |= 64;
        layoutParams.setTitle("MiFlashlightWindow");
        layoutParams.systemUiVisibility = 1792;
        layoutParams.extraFlags |= 32768;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.layoutInDisplayCutoutMode = 3;
        try {
            layoutParams.getClass().getMethod("setTrustedOverlay", null).invoke(layoutParams, null);
        } catch (Throwable th) {
            Log.e("MiFlash_MiFlashlightManager", "get setTrustedOverlay method failed.", th);
        }
        return layoutParams;
    }
}
