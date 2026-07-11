package miui.systemui.controlcenter.external;

/* JADX INFO: loaded from: classes.dex */
public interface ExternalEntry {
    default void destroy() {
    }

    boolean getCollapse();

    default int getExpandHeight() {
        return -2;
    }

    void setCollapse(boolean z2);

    default void setListening(boolean z2) {
    }

    default void updateResources() {
    }
}
