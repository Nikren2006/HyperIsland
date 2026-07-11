package miui.systemui.controlcenter.panel.secondary;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public interface ChangeFromView extends SecondaryFromView {
    void changeAction();

    ViewGroup getChangeFrame();

    ChangeToView getChangeToView();

    void setChangeToView(ChangeToView changeToView);
}
