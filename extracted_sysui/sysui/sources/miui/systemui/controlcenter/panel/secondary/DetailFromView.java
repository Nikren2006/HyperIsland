package miui.systemui.controlcenter.panel.secondary;

import android.view.View;
import android.view.ViewGroup;
import miui.systemui.controlcenter.qs.tileview.QSItemIconView;

/* JADX INFO: loaded from: classes.dex */
public interface DetailFromView extends ChangeFromView {
    float getCornerRadius();

    QSItemIconView getIcon();

    View getIconFrame();

    ViewGroup getItemFrame();

    void setCornerRadius(float f2);
}
