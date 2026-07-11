package miui.systemui.controlcenter.panel.main;

import android.content.res.Configuration;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import java.util.List;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;

/* JADX INFO: loaded from: classes.dex */
public interface MainPanelContent {
    default void applyPayload(MainPanelItemViewHolder holder, MainPanelListItem item, Object payload) {
        n.g(holder, "holder");
        n.g(item, "item");
        n.g(payload, "payload");
    }

    boolean available(boolean z2);

    MainPanelItemViewHolder createViewHolder(ViewGroup viewGroup, int i2);

    List<MainPanelListItem> getListItems();

    int getPriority();

    boolean getRightOrLeft();

    default boolean moveElement(MainPanelListItem mainPanelListItem, MainPanelListItem mainPanelListItem2) {
        return false;
    }

    @CallSuper
    default void onBindViewHolder(MainPanelItemViewHolder holder, MainPanelListItem item) {
        n.g(holder, "holder");
        n.g(item, "item");
        item.onBindViewHolder();
    }

    default void onBrightnessChange(float f2, boolean z2) {
    }

    default void onExpandChange(MainPanelItemViewHolder holder, float f2) {
        n.g(holder, "holder");
        holder.itemView.setTranslationY(f2);
    }

    default void onSpreadChange(MainPanelItemViewHolder holder, float f2, float f3) {
        n.g(holder, "holder");
        holder.setScaleFactor(f2);
        holder.setSpreadSlideTransX(f3);
        holder.onFrameCallback();
    }

    @CallSuper
    default void onUnbindViewHolder(MainPanelItemViewHolder holder, MainPanelListItem item) {
        n.g(holder, "holder");
        n.g(item, "item");
        item.onUnbindViewHolder();
    }

    @CallSuper
    default void updateConfiguration(MainPanelItemViewHolder holder, MainPanelListItem item, Configuration config) {
        n.g(holder, "holder");
        n.g(item, "item");
        n.g(config, "config");
        holder.updateConfiguration(config);
    }

    @CallSuper
    default void updateMode(MainPanelItemViewHolder holder, MainPanelListItem item, MainPanelController.Mode mode, boolean z2) {
        n.g(holder, "holder");
        n.g(item, "item");
        n.g(mode, "mode");
        item.updateMode(mode, z2);
        holder.updateMode(mode, z2);
    }

    default void updateStyle(MainPanelItemViewHolder holder, MainPanelListItem item, MainPanelController.Style style) {
        n.g(holder, "holder");
        n.g(item, "item");
        n.g(style, "style");
        item.updateStyle(style);
        holder.updateStyle(style);
    }

    default void updateSuperSaveMode(MainPanelItemViewHolder holder, MainPanelListItem item) {
        n.g(holder, "holder");
        n.g(item, "item");
        holder.onSuperSaveModeChanged();
    }
}
