package com.android.systemui.miui.volume;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public interface VolumeToView {
    int getExpandBgRadius();

    View getPanelBg();

    int getVolumeRadius();

    void setExpandBgRadius(int i2);

    void setPanelBg(View view);

    void setVolumeRadius(int i2);
}
