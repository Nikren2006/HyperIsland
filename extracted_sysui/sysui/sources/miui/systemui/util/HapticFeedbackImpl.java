package miui.systemui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.UiBackground;
import miui.systemui.util.HapticFeedback;
import miui.util.HapticFeedbackUtil;
import miuix.view.HapticCompat;

/* JADX INFO: loaded from: classes4.dex */
@SuppressLint({"MissingPermission"})
public final class HapticFeedbackImpl implements HapticFeedback {
    public static final Companion Companion = new Companion(null);
    private static final int EFFECT_CLEAR_ALL_NOTIFICATIONS = 93;
    private static final int EFFECT_CLEAR_NOTIFICATION = 92;
    private HapticFeedbackUtil hapticFeedbackUtil;
    private final Executor uiBgExecutor;
    private Vibrator vibrator;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public HapticFeedbackImpl(Context context, @UiBackground Executor uiBgExecutor) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(uiBgExecutor, "uiBgExecutor");
        this.uiBgExecutor = uiBgExecutor;
        this.hapticFeedbackUtil = new HapticFeedbackUtil(context, false);
        Object systemService = context.getSystemService("vibrator");
        kotlin.jvm.internal.n.e(systemService, "null cannot be cast to non-null type android.os.Vibrator");
        this.vibrator = (Vibrator) systemService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postClick$lambda$1(HapticFeedbackImpl this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.click();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postHapticFeedback$lambda$3(HapticFeedbackImpl this$0, String str, boolean z2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.hapticFeedback(str, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postHapticFeedback$lambda$4(HapticFeedbackImpl this$0, int i2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.hapticFeedbackUtil.performExtHapticFeedback(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postLongClick$lambda$2(HapticFeedbackImpl this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.longClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postMeshNormal$lambda$0(HapticFeedbackImpl this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.meshNormal();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postPerformHapticFeedback$lambda$5(View view, int i2) {
        kotlin.jvm.internal.n.g(view, "$view");
        HapticCompat.performHapticFeedback(view, i2);
    }

    @Override // miui.systemui.util.HapticFeedback
    public void clearAllNotifications() {
        if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
            try {
                this.hapticFeedbackUtil.performExtHapticFeedback(93);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // miui.systemui.util.HapticFeedback
    public void clearNotification() {
        if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
            try {
                this.hapticFeedbackUtil.performExtHapticFeedback(92);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // miui.systemui.util.HapticFeedback
    public void click() {
        hapticFeedback(HapticConstants.INSTANCE.getEFFECT_ID_BUTTON_LIGHT(), "flick", false);
    }

    @Override // miui.systemui.util.HapticFeedback
    public void extHapticFeedback(int i2, boolean z2, int i3) {
        if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
            this.hapticFeedbackUtil.performExtHapticFeedback(i2);
        } else if (z2) {
            this.vibrator.vibrate(i3);
        }
    }

    @Override // miui.systemui.util.HapticFeedback
    public void extLongHapticFeedback(int i2, boolean z2, int i3) {
        if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE() && this.hapticFeedbackUtil.isSupportExtHapticFeedback(i2)) {
            this.hapticFeedbackUtil.performExtHapticFeedback(i2);
        } else if (z2) {
            this.vibrator.vibrate(i3);
        }
    }

    @Override // miui.systemui.util.HapticFeedback
    public void extMulHapticFeedback(int i2, int i3, boolean z2, boolean z3, int i4) {
        if (!HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
            if (z3) {
                this.vibrator.vibrate(i4);
            }
        } else if (this.hapticFeedbackUtil.isSupportExtHapticFeedback(i2)) {
            this.hapticFeedbackUtil.performExtHapticFeedback(i2);
        } else {
            this.hapticFeedbackUtil.performHapticFeedback(i3, z2);
        }
    }

    @Override // miui.systemui.util.HapticFeedback
    public void hapticFeedback(String str, boolean z2) {
        if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
            this.hapticFeedbackUtil.performHapticFeedback(str, z2);
        }
    }

    @Override // miui.systemui.util.HapticFeedback
    public boolean isSupportExtHapticFeedback(int i2) {
        return HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE() && this.hapticFeedbackUtil.isSupportExtHapticFeedback(i2);
    }

    @Override // miui.systemui.util.HapticFeedback
    public void longClick() {
        hapticFeedback(HapticConstants.INSTANCE.getEFFECT_ID_BOUNDARY_TIME(), "long_press", false);
    }

    @Override // miui.systemui.util.HapticFeedback
    public void meshNormal() {
        hapticFeedback("mesh_normal", false);
    }

    @Override // miui.systemui.util.HapticFeedback
    public void postClick() {
        this.uiBgExecutor.execute(new Runnable() { // from class: miui.systemui.util.l
            @Override // java.lang.Runnable
            public final void run() {
                HapticFeedbackImpl.postClick$lambda$1(this.f5913a);
            }
        });
    }

    @Override // miui.systemui.util.HapticFeedback
    public void postHapticFeedback(final String str, final boolean z2) {
        this.uiBgExecutor.execute(new Runnable() { // from class: miui.systemui.util.k
            @Override // java.lang.Runnable
            public final void run() {
                HapticFeedbackImpl.postHapticFeedback$lambda$3(this.f5910a, str, z2);
            }
        });
    }

    @Override // miui.systemui.util.HapticFeedback
    public void postLongClick() {
        this.uiBgExecutor.execute(new Runnable() { // from class: miui.systemui.util.n
            @Override // java.lang.Runnable
            public final void run() {
                HapticFeedbackImpl.postLongClick$lambda$2(this.f5916a);
            }
        });
    }

    @Override // miui.systemui.util.HapticFeedback
    public void postMeshNormal() {
        this.uiBgExecutor.execute(new Runnable() { // from class: miui.systemui.util.o
            @Override // java.lang.Runnable
            public final void run() {
                HapticFeedbackImpl.postMeshNormal$lambda$0(this.f5917a);
            }
        });
    }

    @Override // miui.systemui.util.HapticFeedback
    public void postPerformHapticFeedback(final View view, final int i2) {
        kotlin.jvm.internal.n.g(view, "view");
        this.uiBgExecutor.execute(new Runnable() { // from class: miui.systemui.util.m
            @Override // java.lang.Runnable
            public final void run() {
                HapticFeedbackImpl.postPerformHapticFeedback$lambda$5(view, i2);
            }
        });
    }

    @Override // miui.systemui.util.HapticFeedback
    public void postHapticFeedback(final int i2, View view, int i3) {
        kotlin.jvm.internal.n.g(view, "view");
        HapticFeedback.Companion companion = HapticFeedback.Companion;
        if (companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE() && companion.getIS_SUPPORT_HAPTIC_V2()) {
            this.uiBgExecutor.execute(new Runnable() { // from class: miui.systemui.util.j
                @Override // java.lang.Runnable
                public final void run() {
                    HapticFeedbackImpl.postHapticFeedback$lambda$4(this.f5908a, i2);
                }
            });
        } else {
            postPerformHapticFeedback(view, i3);
        }
    }

    @Override // miui.systemui.util.HapticFeedback
    public boolean hapticFeedback(int i2, String str, boolean z2) {
        HapticFeedback.Companion companion = HapticFeedback.Companion;
        if (!companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
            return false;
        }
        if (companion.getIS_SUPPORT_HAPTIC_V2()) {
            return this.hapticFeedbackUtil.performExtHapticFeedback(i2);
        }
        return this.hapticFeedbackUtil.performHapticFeedback(str, z2);
    }
}
