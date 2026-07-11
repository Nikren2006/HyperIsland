package miui.systemui.controlcenter.panel.secondary;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public interface DetailToView extends BlurBgToView, ContentBgToView, FakeChangeToView {
    ViewGroup getContainer();

    ViewGroup getContent();

    ViewGroup getScaleContent();
}
