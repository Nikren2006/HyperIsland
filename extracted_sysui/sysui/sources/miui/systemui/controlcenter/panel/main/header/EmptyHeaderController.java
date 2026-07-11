package miui.systemui.controlcenter.panel.main.header;

import android.content.Context;
import android.view.View;
import android.widget.Space;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.shade.ShadeHeader;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.utils.ControlCenterViewController;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class EmptyHeaderController extends ControlCenterViewController<View> implements ShadeHeader {
    private final E0.a brightnessSliderController;
    private final View headerView;
    private final Lifecycle lifecycle;
    private final E0.a secondaryPanelRouter;
    private final ShadeHeaderController shadeHeaderController;
    private final int type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.view.View] */
    public EmptyHeaderController(Context context, ShadeHeaderController shadeHeaderController, @Qualifiers.ControlCenter Lifecycle lifecycle, E0.a brightnessSliderController, E0.a secondaryPanelRouter) {
        super(new Space(context));
        n.g(context, "context");
        n.g(shadeHeaderController, "shadeHeaderController");
        n.g(lifecycle, "lifecycle");
        n.g(brightnessSliderController, "brightnessSliderController");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        this.shadeHeaderController = shadeHeaderController;
        this.lifecycle = lifecycle;
        this.brightnessSliderController = brightnessSliderController;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.headerView = getView();
        this.type = MainPanelHeaderController.HEADER_TYPE_EMPTY;
    }

    public View getHeaderView() {
        return this.headerView;
    }

    public float getHeight() {
        return this.shadeHeaderController.getCombinedHeaderHeight();
    }

    public boolean getShouldShow() {
        return this.lifecycle.getCurrentState().compareTo(Lifecycle.State.STARTED) >= 0 && !((SecondaryPanelRouter) this.secondaryPanelRouter.get()).getInMainPanel();
    }

    public int getType() {
        return this.type;
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        this.shadeHeaderController.registerHeader(this);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        this.shadeHeaderController.unregisterHeader(this);
    }

    public void transitionFrom(ShadeHeader from, boolean z2) {
        n.g(from, "from");
    }

    public void transitionTo(ShadeHeader to, boolean z2) {
        n.g(to, "to");
    }
}
