package miui.systemui.controlcenter.panel.secondary;

import android.view.View;
import android.view.ViewGroup;
import miui.systemui.controlcenter.widget.ToggleSliderView;

/* JADX INFO: loaded from: classes.dex */
public interface BrightnessToView extends BlurBgToView, ContentBgToView {
    ViewGroup getItemFrame();

    float getOutlineRadius();

    float getProgressRadius();

    View getSliderIcon();

    ViewGroup getTilesContainer();

    ViewGroup getTilesContent();

    ToggleSliderView getToggleSlider();

    void setOutlineRadius(float f2);

    void setProgressRadius(float f2);
}
