package miui.systemui.flashlight;

import android.util.Log;
import androidx.activity.OnBackPressedCallback;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightActivity$onBackPressedCallback$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightActivity$onBackPressedCallback$2(MiFlashlightActivity miFlashlightActivity) {
        super(0);
        this.this$0 = miFlashlightActivity;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.flashlight.MiFlashlightActivity$onBackPressedCallback$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final MiFlashlightActivity miFlashlightActivity = this.this$0;
        return new OnBackPressedCallback() { // from class: miui.systemui.flashlight.MiFlashlightActivity$onBackPressedCallback$2.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                Log.i("MiFlash_MiFlashlightActivity", "onBackPressed");
                miFlashlightActivity.flashlightManager.hideFlashlight();
            }
        };
    }
}
