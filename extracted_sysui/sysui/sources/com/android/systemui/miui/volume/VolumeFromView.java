package com.android.systemui.miui.volume;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public interface VolumeFromView {
    View getContent();

    View getIcon();

    int getVolumeRadius();

    void setContent(View view);

    void setIcon(View view);

    void setVolumeRadius(int i2);
}
