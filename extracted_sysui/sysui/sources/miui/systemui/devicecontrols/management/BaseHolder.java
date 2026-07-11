package miui.systemui.devicecontrols.management;

import android.content.res.Configuration;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import miui.systemui.controlcenter.ConfigUtils;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseHolder extends RecyclerView.ViewHolder implements ConfigUtils.OnConfigChangeListener {
    private Configuration config;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseHolder(View view) {
        super(view);
        kotlin.jvm.internal.n.g(view, "view");
    }

    private final void resolveConfig() {
        Configuration configuration = this.config;
        if (configuration == null) {
            this.config = new Configuration(this.itemView.getResources().getConfiguration());
            return;
        }
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        kotlin.jvm.internal.n.d(configuration);
        Configuration configuration2 = this.itemView.getResources().getConfiguration();
        kotlin.jvm.internal.n.f(configuration2, "getConfiguration(...)");
        onConfigurationChanged(configUtils.update(configuration, configuration2));
    }

    @CallSuper
    public void bindData(ElementWrapper wrapper) {
        kotlin.jvm.internal.n.g(wrapper, "wrapper");
        resolveConfig();
    }

    public void bindData(ElementWrapper wrapper, int i2, List<Object> payloads) {
        kotlin.jvm.internal.n.g(wrapper, "wrapper");
        kotlin.jvm.internal.n.g(payloads, "payloads");
        resolveConfig();
    }
}
