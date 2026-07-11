package miui.systemui.controlcenter.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.text.BidiFormatter;
import android.view.View;
import android.view.ViewParent;
import c1.f;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.qs.tileview.QSItemView;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.ThemeUtils;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterUtils {
    public static final ControlCenterUtils INSTANCE = new ControlCenterUtils();
    private static boolean superPowerModeOn;

    private ControlCenterUtils() {
    }

    public static final boolean getBackgroundBlurOpenedInDefaultTheme(Context context) {
        n.g(context, "<this>");
        if (MiBlurCompat.INSTANCE.getBACKGROUND_BLUR_SUPPORTED()) {
            Configuration configuration = context.getResources().getConfiguration();
            n.f(configuration, "getConfiguration(...)");
            if (MiBlurCompat.getBlurCompat(configuration) == 1 && ThemeUtils.INSTANCE.getDefaultPluginTheme() && !superPowerModeOn) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ void getBackgroundBlurOpenedInDefaultTheme$annotations(Context context) {
    }

    public static final boolean getCompleteControlCenter() {
        return !(DeviceUtils.isLowLevel() || CommonUtils.INSTANCE.getMIUI_LITE_V2()) || CommonUtils.INSTANCE.getIS_TABLET();
    }

    public static /* synthetic */ void getCompleteControlCenter$annotations() {
    }

    public final float afterFriction(float f2, float f3) {
        float fE = f.e(f2 / f3, 1.0f);
        float f4 = fE * fE;
        return ((((f4 * fE) / 3) - f4) + fE) * f3;
    }

    public final void createCardFolmeTouchStyle(View view) {
        Folme.useAt(view).touch().setTint(0.0f, 0.0f, 0.0f, 0.0f).handleTouchOf(view, new AnimConfig());
    }

    public final String getEventActionDesc(Integer num) {
        String string;
        return (num != null && num.intValue() == 0) ? "ACTION_DOWN" : (num != null && num.intValue() == 1) ? "ACTION_UP" : (num != null && num.intValue() == 2) ? "ACTION_MOVE" : (num != null && num.intValue() == 3) ? "ACTION_CANCEL" : (num != null && num.intValue() == 4) ? "ACTION_OUTSIDE" : (num == null || (string = num.toString()) == null) ? "unknown" : string;
    }

    public final boolean getRestrictedState(QSTile.State state, boolean z2) {
        if (state == null || !QSItemView.Companion.isRestrictedCompat(state)) {
            return z2 && state != null && state.state == 2;
        }
        return true;
    }

    public final boolean getSuperPowerModeOn() {
        return superPowerModeOn;
    }

    public final String getUnicodeWrapped(CharSequence charSequence) {
        n.g(charSequence, "<this>");
        String strUnicodeWrap = BidiFormatter.getInstance().unicodeWrap(charSequence.toString());
        n.f(strUnicodeWrap, "unicodeWrap(...)");
        return strUnicodeWrap;
    }

    public final boolean moveAccept(float f2, float f3, float f4) {
        return Math.abs(f2 * f2) + Math.abs(f3 * f3) > Math.abs(f4 * f4);
    }

    public final void requestParentDisallowInterceptTouchEvent(View view, boolean z2) {
        n.g(view, "<this>");
        ViewParent parent = view.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z2);
        }
    }

    public final void setSuperPowerModeOn(boolean z2) {
        superPowerModeOn = z2;
    }
}
