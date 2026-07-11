package miui.systemui.controlcenter.brightness;

import android.view.MotionEvent;
import com.android.systemui.plugins.miui.controlcenter.ToggleSliderBase;
import kotlin.jvm.internal.n;
import q.AbstractC0731a;

/* JADX INFO: loaded from: classes.dex */
public interface ToggleSlider extends ToggleSliderBase {

    public interface Listener extends ToggleSliderBase.Listener {
        default void onChanged(boolean z2, int i2, boolean z3) {
        }

        default void onInit(ToggleSliderBase toggleSliderBase) {
        }

        default void onStart(int i2) {
        }

        default void onStop(int i2) {
        }

        default void onChanged(ToggleSliderBase toggleSliderBase, boolean z2, int i2, boolean z3) {
            onChanged(z2, i2, z3);
        }

        default void onChanged(ToggleSliderBase toggleSliderBase, boolean z2, boolean z3, int i2, boolean z4) {
            n.d(toggleSliderBase);
            onChanged(toggleSliderBase, z2, i2, z4);
        }
    }

    int getMax();

    default int getMin() {
        return 0;
    }

    default boolean mirrorTouchEvent(MotionEvent event) {
        n.g(event, "event");
        return false;
    }

    void setContentDescription(String str);

    default void setEnforcedAdmin(AbstractC0731a.C0165a c0165a) {
    }

    default void setMin(int i2) {
    }
}
