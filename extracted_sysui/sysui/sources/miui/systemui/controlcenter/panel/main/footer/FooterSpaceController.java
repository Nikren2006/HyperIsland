package miui.systemui.controlcenter.panel.main.footer;

import I0.m;
import android.content.Context;
import android.database.ContentObserver;
import android.provider.MiuiSettings;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import c1.f;
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
import miuix.autodensity.DensityConfig;
import miuix.autodensity.DensityConfigManager;

/* JADX INFO: loaded from: classes.dex */
public final class FooterSpaceController extends MainPanelListItem.SimpleController<View> implements MainPanelContent {
    public static final Companion Companion = new Companion(null);
    private static final int TYPE_FOOTER_SPACE = 366837;
    private int bottomInset;
    private boolean fullscreenGesture;
    private final ArrayList<FooterSpaceController> listItems;
    private final int priority;
    private final boolean rightOrLeft;
    private final FooterSpaceController$settingsObserver$1 settingsObserver;
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
        private final ControlCenterWindowViewImpl windowView;

        public Factory(ControlCenterWindowViewImpl windowView) {
            n.g(windowView, "windowView");
            this.windowView = windowView;
        }

        public final FooterSpaceController create(boolean z2) {
            return new FooterSpaceController(this.windowView, z2, null);
        }
    }

    public static final class FooterSpaceViewHolder extends MainPanelItemViewHolder {
        private int bottomInset;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FooterSpaceViewHolder(View view) {
            super(view);
            n.g(view, "view");
            this.bottomInset = Integer.MIN_VALUE;
        }

        private final int getMinInsetWithoutAutoDensity() {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.minimum_bottom_inset);
            DensityConfig targetConfig = DensityConfigManager.getInstance().getTargetConfig();
            DensityConfig originConfig = DensityConfigManager.getInstance().getOriginConfig();
            return (targetConfig == null || originConfig == null) ? dimensionPixelSize : (int) (((float) Math.ceil(dimensionPixelSize / targetConfig.density)) * originConfig.density);
        }

        private final void updateHeight() {
            int iC = f.c(this.bottomInset, getMinInsetWithoutAutoDensity());
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            View itemView = this.itemView;
            n.f(itemView, "itemView");
            commonUtils.setLayoutHeight(itemView, iC, true);
        }

        public final int getBottomInset() {
            return this.bottomInset;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void onConfigurationChanged(int i2) {
            updateHeight();
        }

        public final void setBottomInset(int i2) {
            if (this.bottomInset == i2) {
                return;
            }
            this.bottomInset = i2;
            updateHeight();
        }
    }

    public /* synthetic */ FooterSpaceController(View view, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(view, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFullscreenGesture() {
        this.fullscreenGesture = MiuiSettings.Global.getBoolean(getContext().getContentResolver(), "force_fsg_nav_bar");
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return z2 || getRightOrLeft();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != TYPE_FOOTER_SPACE) {
            return null;
        }
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_space, parent, false);
        n.f(viewInflate, "inflate(...)");
        FooterSpaceViewHolder footerSpaceViewHolder = new FooterSpaceViewHolder(viewInflate);
        footerSpaceViewHolder.setBottomInset(this.bottomInset);
        return footerSpaceViewHolder;
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

    /* JADX WARN: Type inference failed for: r0v4, types: [android.view.View] */
    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onApplyWindowInsets(WindowInsets insets) {
        n.g(insets, "insets");
        if (!this.fullscreenGesture && insets.getStableInsetBottom() == 0) {
            Context context = getView().getContext();
            n.f(context, "getContext(...)");
            if (!CommonUtils.isTinyScreen(context)) {
                return;
            }
        }
        this.bottomInset = insets.getStableInsetBottom();
        MainPanelItemViewHolder holder = getHolder();
        FooterSpaceViewHolder footerSpaceViewHolder = holder instanceof FooterSpaceViewHolder ? (FooterSpaceViewHolder) holder : null;
        if (footerSpaceViewHolder == null) {
            return;
        }
        footerSpaceViewHolder.setBottomInset(this.bottomInset);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.View] */
    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        updateFullscreenGesture();
        getView().getContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor("force_fsg_nav_bar"), false, this.settingsObserver);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.View] */
    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        getView().getContext().getContentResolver().unregisterContentObserver(this.settingsObserver);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [miui.systemui.controlcenter.panel.main.footer.FooterSpaceController$settingsObserver$1] */
    private FooterSpaceController(View view, boolean z2) {
        super(view);
        this.rightOrLeft = z2;
        this.fullscreenGesture = true;
        this.settingsObserver = new ContentObserver() { // from class: miui.systemui.controlcenter.panel.main.footer.FooterSpaceController$settingsObserver$1
            {
                super(null);
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z3) {
                if (z3) {
                    return;
                }
                this.this$0.updateFullscreenGesture();
            }
        };
        this.priority = Integer.MAX_VALUE;
        this.listItems = m.f(this);
        this.type = TYPE_FOOTER_SPACE;
        this.spanSize = 4;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public ArrayList<FooterSpaceController> getListItems() {
        return this.listItems;
    }
}
