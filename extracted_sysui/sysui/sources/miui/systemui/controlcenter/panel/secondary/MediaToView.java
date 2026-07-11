package miui.systemui.controlcenter.panel.secondary;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public interface MediaToView extends BlurBgToView, ContentBgToView, ChangeToView {
    ViewGroup getContent();

    ViewGroup getItemFrame();
}
