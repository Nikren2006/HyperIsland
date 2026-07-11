package miui.systemui.nfcphonecase;

import android.util.Log;
import android.view.WindowManager;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class MiuiNfcPhoneCaseService$layoutParams$2 extends o implements Function0 {
    public static final MiuiNfcPhoneCaseService$layoutParams$2 INSTANCE = new MiuiNfcPhoneCaseService$layoutParams$2();

    public MiuiNfcPhoneCaseService$layoutParams$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final WindowManager.LayoutParams invoke() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 0, 0, 2009, -2121989848, -3);
        layoutParams.privateFlags |= 64;
        layoutParams.setTitle("miui_nfc_phone_case");
        layoutParams.systemUiVisibility = 1792;
        layoutParams.extraFlags |= 32768;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.layoutInDisplayCutoutMode = 3;
        try {
            layoutParams.getClass().getMethod("setTrustedOverlay", null).invoke(layoutParams, null);
        } catch (Throwable th) {
            Log.e(MiuiNfcPhoneCaseService.TAG, "get setTrustedOverlay method failed.", th);
        }
        return layoutParams;
    }
}
