package miui.systemui.controlcenter.panel.secondary;

import android.view.View;
import android.view.ViewGroup;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public interface MediaFromView extends ChangeFromView {
    View getArtistView();

    View getAudioContainerView();

    View getAudioNextView();

    View getAudioPlayView();

    View getAudioPrevView();

    ViewGroup getContentView();

    float getCornerRadius();

    float getCoverRadius();

    View getCoverView();

    View getNoPlayView();

    TextView getTitleView();

    MainPanelItemViewHolder getViewHolder();

    void setCornerRadius(float f2);
}
