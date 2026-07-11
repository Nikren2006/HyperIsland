package miui.systemui.controlcenter.panel.secondary;

/* JADX INFO: loaded from: classes.dex */
public interface DetailCallback {
    default void onDetailHidden() {
    }

    void onMoreButtonClicked();

    void onToggleClicked(boolean z2);
}
