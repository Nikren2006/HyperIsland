package g0;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.volume.MetricsEvent;
import g0.f;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController;
import miui.systemui.controlcenter.panel.secondary.SecondaryParamsKt;
import miuix.animation.utils.EaseManager;
import miuix.mgl.math.Vector2;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f4329a = {151, 160, 137, 91, 90, 15, 131, 13, EaseManager.EaseStyleDef.PERLIN, 95, 96, 53, HapticFeedbackConstants.MIUI_KEYBOARD_CLICKY_UP_RTP, MainPanelHeaderController.HEADER_TYPE_EMPTY, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, VolumePanelViewController.HAPTIC_V2_VOLUME_MAX, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, MetricsEvent.ACTION_VOLUME_KEY, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, MetricsEvent.ACTION_VOLUME_SLIDER, 76, 132, 187, MetricsEvent.VOLUME_DIALOG_DETAILS, 89, 18, 169, 200, HapticFeedbackConstants.MIUI_KEYBOARD_LINEAR_UP_RTP, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 124, 123, 5, VolumePanelViewController.HAPTIC_V2_VOLUME_MIN, 38, 147, 118, 126, 255, 82, 85, MetricsEvent.ACTION_VOLUME_ICON, MetricsEvent.VOLUME_DIALOG, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, MetricsEvent.ACTION_RINGER_MODE, 119, 248, SecondaryParamsKt.FROM_WIFI, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, HapticFeedbackConstants.MIUI_KEYBOARD_CLICKY_DOWN_RTP, 238, MetricsEvent.ACTION_VOLUME_STREAM, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, SecondaryParamsKt.FROM_BT, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, HapticFeedbackConstants.MIUI_KEYBOARD_LINEAR_DOWN_RTP, 78, 66, 215, 61, 156, 180, 151, 160, 137, 91, 90, 15, 131, 13, EaseManager.EaseStyleDef.PERLIN, 95, 96, 53, HapticFeedbackConstants.MIUI_KEYBOARD_CLICKY_UP_RTP, MainPanelHeaderController.HEADER_TYPE_EMPTY, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, VolumePanelViewController.HAPTIC_V2_VOLUME_MAX, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, MetricsEvent.ACTION_VOLUME_KEY, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, MetricsEvent.ACTION_VOLUME_SLIDER, 76, 132, 187, MetricsEvent.VOLUME_DIALOG_DETAILS, 89, 18, 169, 200, HapticFeedbackConstants.MIUI_KEYBOARD_LINEAR_UP_RTP, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 124, 123, 5, VolumePanelViewController.HAPTIC_V2_VOLUME_MIN, 38, 147, 118, 126, 255, 82, 85, MetricsEvent.ACTION_VOLUME_ICON, MetricsEvent.VOLUME_DIALOG, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, MetricsEvent.ACTION_RINGER_MODE, 119, 248, SecondaryParamsKt.FROM_WIFI, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, HapticFeedbackConstants.MIUI_KEYBOARD_CLICKY_DOWN_RTP, 238, MetricsEvent.ACTION_VOLUME_STREAM, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, SecondaryParamsKt.FROM_BT, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, HapticFeedbackConstants.MIUI_KEYBOARD_LINEAR_DOWN_RTP, 78, 66, 215, 61, 156, 180};

    public static final C0356a a(long j2, float f2, float f3, float f4, float f5, float f6, float f7) {
        int i2 = (int) (j2 & 4);
        int i3 = (int) (j2 & 2);
        int i4 = (int) (j2 & 1);
        long j3 = j2 >> 32;
        int i5 = (int) (4 & j3);
        int i6 = (int) (2 & j3);
        int i7 = (int) (j3 & 1);
        float f8 = i2 != 0 ? f4 : f3;
        float f9 = i5 != 0 ? f2 : f3;
        float f10 = i2 != 0 ? f3 : f4;
        float f11 = i5 != 0 ? f3 : f2;
        float f12 = i3 != 0 ? f6 : f7;
        float f13 = i6 != 0 ? f6 : f7;
        if (i4 != 0) {
            f10 = -f10;
        }
        if (i7 != 0) {
            f11 = -f11;
        }
        return new C0356a((f12 * f8) + f10, (f13 * f9) + f11);
    }

    public static final C0356a b(long j2, float f2, float f3, float f4, float f5, float f6, float f7) {
        int i2 = (int) (j2 & 4);
        int i3 = (int) (j2 & 2);
        int i4 = (int) (j2 & 1);
        long j3 = j2 >> 32;
        int i5 = (int) (4 & j3);
        int i6 = (int) (2 & j3);
        int i7 = (int) (j3 & 1);
        float f8 = i2 != 0 ? f4 : f5;
        float f9 = i5 != 0 ? f2 : f5;
        float f10 = i2 != 0 ? f5 : f4;
        float f11 = i5 != 0 ? f5 : f2;
        float f12 = i3 != 0 ? f6 : f7;
        float f13 = i6 != 0 ? f6 : f7;
        if (i4 != 0) {
            f10 = -f10;
        }
        if (i7 != 0) {
            f11 = -f11;
        }
        return new C0356a((f12 * f8) + f10, (f13 * f9) + f11);
    }

    public static final long c(int i2, int i3) {
        return (((long) i3) & 4294967295L) | (((long) i2) << 32);
    }

    public static final void d(d physics) {
        n.g(physics, "physics");
        float[] fArrA = physics.a().a();
        int i2 = 0;
        int i3 = 0;
        do {
            float fB = physics.a().b() * 0.05f;
            float f2 = i2;
            float fE = e(f2 - 8.5f, fArrA[i2] + fB);
            float fE2 = e((1.0f + f2) - 8.5f, fArrA[i2 + 1] + fB);
            float fE3 = e((f2 + 2.0f) - 8.5f, fArrA[i2 + 2] + fB);
            f.a aVar = f.f4351c;
            f fVarA = aVar.a(fE3 * 12.56637f);
            f fVarA2 = aVar.a(fE2 * 12.56637f);
            f fVarA3 = aVar.a(fE * 12.56637f);
            float fA = (fVarA.a() * (-fVarA2.a()) * fVarA3.a()) + (fVarA.b() * fVarA2.b());
            float fA2 = (-fVarA3.b()) * fVarA.a();
            if (((int) physics.j()) == 1) {
                fA2 = (-fVarA3.b()) * fVarA.a() * 0.5f;
                fA = i3 <= 2 ? (fA * 0.5f) - 0.375f : i3 < 7 ? (fA * 0.5f) + 0.375f : 150.0f;
            }
            physics.g()[i3] = new Vector2(fA * physics.e()[0], fA2 * physics.e()[1]);
            i3++;
            i2 += 3;
        } while (i2 != 33);
    }

    public static final float e(float f2, float f3) {
        int i2 = ((int) f2) - (((double) f2) <= 0.0d ? 1 : 0);
        int i3 = ((int) f3) - (((double) f3) <= 0.0d ? 1 : 0);
        float f4 = f2 - i2;
        float f5 = f3 - i3;
        float f6 = f4 - 1.0f;
        float f7 = f5 - 1.0f;
        float f8 = f4 * f4 * f4 * ((((f4 * 6.0f) - 15.0f) * f4) + 10.0f);
        float f9 = f5 * f5 * f5 * ((((6.0f * f5) - 15.0f) * f5) + 10.0f);
        int[] iArr = f4329a;
        int i4 = iArr[i3 & 255];
        int i5 = i2 & 255;
        int i6 = i2 + 1;
        long jC = c(iArr[i4 + i5], iArr[(i4 + i6) & 255]);
        int i7 = iArr[(i3 + 1) & 255];
        long jC2 = c(iArr[i5 + i7], iArr[(i7 + i6) & 255]);
        C0356a c0356aA = a(jC, f4, f5, f6, f7, -2.0f, 2.0f);
        float fB = c0356aA.b();
        float fA = c0356aA.a();
        C0356a c0356aB = b(jC2, f4, f5, f6, f7, -2.0f, 2.0f);
        float fB2 = c0356aB.b();
        float fA2 = ((c0356aB.a() - fA) * f9) + fA;
        return (fA2 + (f8 * ((((fB2 - fB) * f9) + fB) - fA2))) * 0.507f;
    }
}
