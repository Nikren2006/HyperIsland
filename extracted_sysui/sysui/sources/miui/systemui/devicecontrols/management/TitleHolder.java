package miui.systemui.devicecontrols.management;

import android.view.View;
import android.widget.TextView;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
final class TitleHolder extends BaseHolder {
    private final TextView title;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TitleHolder(View view) {
        super(view);
        kotlin.jvm.internal.n.g(view, "view");
        View view2 = this.itemView;
        kotlin.jvm.internal.n.e(view2, "null cannot be cast to non-null type android.widget.TextView");
        this.title = (TextView) view2;
    }

    @Override // miui.systemui.devicecontrols.management.BaseHolder
    public void bindData(ElementWrapper wrapper) {
        kotlin.jvm.internal.n.g(wrapper, "wrapper");
        super.bindData(wrapper);
        TitleWrapper titleWrapper = (TitleWrapper) wrapper;
        this.title.setText(!kotlin.jvm.internal.n.c(titleWrapper.getTitle(), "") ? titleWrapper.getTitle() : this.itemView.getContext().getString(R.string.controls_default_structure));
    }

    @Override // miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        if (ConfigUtils.INSTANCE.dimensionsChanged(i2)) {
            TextView textView = this.title;
            textView.setTextSize(0, textView.getResources().getDimension(R.dimen.device_controls_subtitle_text_size));
        }
    }
}
