package miui.systemui.controlcenter.panel.secondary;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public interface SliderFromView extends SecondaryFromView {
    ViewGroup getContent();

    View getIcon();

    float getOutlineRadius();

    float getProgressRadius();

    View getTopText();

    void setOutlineRadius(float f2);

    void setProgressRadius(float f2);
}
