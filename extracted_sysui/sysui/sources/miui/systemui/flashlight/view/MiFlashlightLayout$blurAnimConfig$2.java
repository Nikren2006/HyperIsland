package miui.systemui.flashlight.view;

import android.app.Activity;
import android.content.Context;
import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.flashlight.R;
import miui.systemui.util.BlurUtils;
import miui.systemui.util.MiBlurCompat;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightLayout$blurAnimConfig$2 extends o implements Function0 {
    final /* synthetic */ Context $context;
    final /* synthetic */ MiFlashlightLayout this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightLayout$blurAnimConfig$2(MiFlashlightLayout miFlashlightLayout, Context context) {
        super(0);
        this.this$0 = miFlashlightLayout;
        this.$context = context;
    }

    @Override // kotlin.jvm.functions.Function0
    public final AnimConfig invoke() {
        AnimConfig animConfig = new AnimConfig();
        final MiFlashlightLayout miFlashlightLayout = this.this$0;
        final Context context = this.$context;
        return animConfig.addListeners(new TransitionListener() { // from class: miui.systemui.flashlight.view.MiFlashlightLayout$blurAnimConfig$2.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                miFlashlightLayout.isBlurAnimRunning = true;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                miFlashlightLayout.isBlurAnimRunning = false;
                miFlashlightLayout.onAnimEnd();
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, "property_blur_progress");
                if (updateInfoFindByName == null) {
                    return;
                }
                miFlashlightLayout.blurProgress = updateInfoFindByName.getFloatValue();
                if (!MiBlurCompat.getBackgroundBlurOpened(context)) {
                    BlurUtils blurUtils = miFlashlightLayout.getBlurUtils();
                    MiFlashlightLayout miFlashlightLayout2 = miFlashlightLayout;
                    Float fValueOf = Float.valueOf(miFlashlightLayout2.blurProgress * 2.0f);
                    Context context2 = context;
                    Activity activity = context2 instanceof Activity ? (Activity) context2 : null;
                    blurUtils.setBackgroundBlur(miFlashlightLayout2, fValueOf, activity != null ? activity.getWindow() : null);
                    return;
                }
                MiBlurCompat.setMiBackgroundBlurModeCompat(miFlashlightLayout, 1);
                MiBlurCompat.setMiBackgroundBlurRadiusCompat(miFlashlightLayout, (int) (((context.getResources().getDisplayMetrics().density * 100) + 60) * miFlashlightLayout.blurProgress));
                MiBlurCompat.setMiViewBlurModeCompat(miFlashlightLayout, 1);
                MiBlurCompat.setPassWindowBlurEnabledCompat(miFlashlightLayout, true);
                MiFlashlightLayout miFlashlightLayout3 = miFlashlightLayout;
                int[] intArray = context.getResources().getIntArray(R.array.flashlight_window_background_blend_colors);
                n.f(intArray, "getIntArray(...)");
                MiBlurCompat.setMiBackgroundBlendColors(miFlashlightLayout3, intArray, miFlashlightLayout.blurProgress);
            }
        });
    }
}
