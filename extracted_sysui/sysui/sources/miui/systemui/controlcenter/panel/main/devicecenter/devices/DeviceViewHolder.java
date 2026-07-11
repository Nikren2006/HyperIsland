package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import android.content.res.Configuration;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import miui.systemui.controlcenter.ConfigUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class DeviceViewHolder extends RecyclerView.ViewHolder {
    private Configuration configuration;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceViewHolder(View itemView) {
        super(itemView);
        kotlin.jvm.internal.n.g(itemView, "itemView");
        this.configuration = new Configuration(itemView.getResources().getConfiguration());
    }

    public abstract void onConfigurationChanged(int i2);

    public final void updateConfiguration(Configuration config) {
        kotlin.jvm.internal.n.g(config, "config");
        int iUpdate = ConfigUtils.INSTANCE.update(this.configuration, config);
        if (iUpdate != 0) {
            onConfigurationChanged(iUpdate);
        }
    }
}
