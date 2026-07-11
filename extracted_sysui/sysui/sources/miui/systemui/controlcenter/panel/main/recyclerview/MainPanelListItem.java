package miui.systemui.controlcenter.panel.main.recyclerview;

import android.view.View;
import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;

/* JADX INFO: loaded from: classes.dex */
public interface MainPanelListItem {

    public static abstract class Base implements MainPanelListItem {
        private MainPanelItemViewHolder holder;
        private final Lifecycle lifecycle;

        public Base(Lifecycle lifecycle) {
            n.g(lifecycle, "lifecycle");
            this.lifecycle = lifecycle;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public MainPanelItemViewHolder getHolder() {
            return this.holder;
        }

        public final Lifecycle getLifecycle() {
            return this.lifecycle;
        }

        public abstract boolean getListening();

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public void onViewAttachedToWindow() {
            if (this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                setListening(true);
            }
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public void onViewDetachedFromWindow() {
            setListening(false);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public void setHolder(MainPanelItemViewHolder mainPanelItemViewHolder) {
            this.holder = mainPanelItemViewHolder;
        }

        public abstract void setListening(boolean z2);
    }

    public static abstract class Controller<T extends View> extends SimpleController<T> implements MainPanelListItem {
        private final Lifecycle lifecycle;
        private boolean listening;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Controller(T view, Lifecycle lifecycle) {
            super(view);
            n.g(view, "view");
            n.g(lifecycle, "lifecycle");
            this.lifecycle = lifecycle;
        }

        public boolean getListening() {
            return this.listening;
        }

        @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
        public void onStart() {
            MainPanelItemViewHolder holder = getHolder();
            if (holder == null || !holder.getAttached$miui_controlcenter_release()) {
                return;
            }
            setListening(true);
        }

        @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
        public void onStop() {
            setListening(false);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public void onViewAttachedToWindow() {
            if (this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                setListening(true);
            }
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public void onViewDetachedFromWindow() {
            setListening(false);
        }

        public void setListening(boolean z2) {
            this.listening = z2;
        }
    }

    public static abstract class SimpleController<T extends View> extends ControlCenterViewController<T> implements MainPanelListItem {
        private MainPanelItemViewHolder holder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SimpleController(T view) {
            super(view);
            n.g(view, "view");
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public MainPanelItemViewHolder getHolder() {
            return this.holder;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public void setHolder(MainPanelItemViewHolder mainPanelItemViewHolder) {
            this.holder = mainPanelItemViewHolder;
        }
    }

    MainPanelItemViewHolder getHolder();

    int getSpanSize();

    int getType();

    default void onBindViewHolder() {
    }

    default void onUnbindViewHolder() {
    }

    default void onViewAttachedToWindow() {
    }

    default void onViewDetachedFromWindow() {
    }

    void setHolder(MainPanelItemViewHolder mainPanelItemViewHolder);

    default void updateMode(MainPanelController.Mode mode, boolean z2) {
        n.g(mode, "mode");
    }

    default void updateStyle(MainPanelController.Style style) {
        n.g(style, "style");
    }
}
