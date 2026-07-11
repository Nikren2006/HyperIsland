package miui.systemui.devicecontrols.management;

import android.content.res.Resources;
import android.view.View;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.devicecontrols.R;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes3.dex */
final class DividerHolder extends BaseHolder {
    private final View divider;
    private final View frame;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DividerHolder(View view) {
        super(view);
        kotlin.jvm.internal.n.g(view, "view");
        View viewRequireViewById = this.itemView.requireViewById(R.id.frame);
        kotlin.jvm.internal.n.f(viewRequireViewById, "requireViewById(...)");
        this.frame = viewRequireViewById;
        View viewRequireViewById2 = this.itemView.requireViewById(R.id.divider);
        kotlin.jvm.internal.n.f(viewRequireViewById2, "requireViewById(...)");
        this.divider = viewRequireViewById2;
    }

    @Override // miui.systemui.devicecontrols.management.BaseHolder
    public void bindData(ElementWrapper wrapper) {
        kotlin.jvm.internal.n.g(wrapper, "wrapper");
        this.divider.setVisibility(((DividerWrapper) wrapper).getShowDivider() ? 0 : 8);
        super.bindData(wrapper);
    }

    @Override // miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        if (ConfigUtils.INSTANCE.dimensionsChanged(i2)) {
            Resources resources = this.itemView.getResources();
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            CommonUtils.setLayoutHeight$default(commonUtils, this.frame, resources.getDimensionPixelSize(R.dimen.device_controls_item_height), false, 2, null);
            CommonUtils.setLayoutHeight$default(commonUtils, this.divider, resources.getDimensionPixelSize(R.dimen.device_controls_divider_height), false, 2, null);
        }
    }
}
