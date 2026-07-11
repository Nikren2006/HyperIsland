package miui.systemui.controlcenter.widget;

/* JADX INFO: loaded from: classes.dex */
public interface ExpandableView {
    float getCornerRadius();

    default boolean isExpandable() {
        return true;
    }

    void setCornerRadius(float f2);
}
