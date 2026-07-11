package com.android.systemui.miui.volume;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VolumePanelView extends FrameLayout {
    private OnConfigChangeListener mOnConfigChangeListener;

    public interface OnConfigChangeListener {
        void onConfigChange();
    }

    public VolumePanelView(@NonNull Context context) {
        this(context, null);
    }

    public void destroy() {
        this.mOnConfigChangeListener = null;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        OnConfigChangeListener onConfigChangeListener = this.mOnConfigChangeListener;
        if (onConfigChangeListener != null) {
            onConfigChangeListener.onConfigChange();
        }
    }

    public void setOnConfigChangeListener(OnConfigChangeListener onConfigChangeListener) {
        this.mOnConfigChangeListener = onConfigChangeListener;
    }

    public VolumePanelView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VolumePanelView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        LayoutInflater.from(context).inflate(R.layout.miui_volume_dialog, this);
    }
}
