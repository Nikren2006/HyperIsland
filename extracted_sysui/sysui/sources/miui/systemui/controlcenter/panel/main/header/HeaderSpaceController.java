package miui.systemui.controlcenter.panel.main.header;

import I0.m;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes.dex */
public final class HeaderSpaceController extends MainPanelListItem.SimpleController<View> implements MainPanelContent {
    public static final Companion Companion = new Companion(null);
    private static final int TYPE_HEADER_SPACE = 432337;
    private final ArrayList<HeaderSpaceController> listItems;
    private final MainPanelHeaderController mainPanelHeaderController;
    private final int priority;
    private final boolean rightOrLeft;
    private final int spanSize;
    private final int type;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @ControlCenterScope
    public static final class Factory {
        private final MainPanelHeaderController mainPanelHeaderController;
        private final ControlCenterWindowViewImpl windowView;

        public Factory(ControlCenterWindowViewImpl windowView, MainPanelHeaderController mainPanelHeaderController) {
            n.g(windowView, "windowView");
            n.g(mainPanelHeaderController, "mainPanelHeaderController");
            this.windowView = windowView;
            this.mainPanelHeaderController = mainPanelHeaderController;
        }

        public final HeaderSpaceController create(boolean z2) {
            return new HeaderSpaceController(this.windowView, this.mainPanelHeaderController, z2, null);
        }
    }

    public final class HeaderSpaceViewHolder extends MainPanelItemViewHolder {
        final /* synthetic */ HeaderSpaceController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HeaderSpaceViewHolder(HeaderSpaceController headerSpaceController, View view) {
            super(view);
            n.g(view, "view");
            this.this$0 = headerSpaceController;
            updateHeight();
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void onConfigurationChanged(int i2) {
            updateHeight();
        }

        public final void updateHeight() {
            int i2 = this.this$0.mainPanelHeaderController.getLayoutParams().height;
            Log.d(MainPanelItemViewHolder.TAG, "updateHeight " + getContext().getResources().getConfiguration().orientation + " " + i2);
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            View itemView = this.itemView;
            n.f(itemView, "itemView");
            CommonUtils.setLayoutHeight$default(commonUtils, itemView, i2, false, 2, null);
        }
    }

    public /* synthetic */ HeaderSpaceController(View view, MainPanelHeaderController mainPanelHeaderController, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(view, mainPanelHeaderController, z2);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return z2 || getRightOrLeft();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != TYPE_HEADER_SPACE) {
            return null;
        }
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_space, parent, false);
        n.f(viewInflate, "inflate(...)");
        return new HeaderSpaceViewHolder(this, viewInflate);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public int getPriority() {
        return this.priority;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean getRightOrLeft() {
        return this.rightOrLeft;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getSpanSize() {
        return this.spanSize;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getType() {
        return this.type;
    }

    private HeaderSpaceController(View view, MainPanelHeaderController mainPanelHeaderController, boolean z2) {
        super(view);
        this.mainPanelHeaderController = mainPanelHeaderController;
        this.rightOrLeft = z2;
        this.listItems = m.f(this);
        this.type = TYPE_HEADER_SPACE;
        this.spanSize = 4;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public ArrayList<HeaderSpaceController> getListItems() {
        return this.listItems;
    }
}
