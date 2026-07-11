package com.android.systemui;

import androidx.lifecycle.MutableLiveData;

/* JADX INFO: loaded from: classes.dex */
public interface VolumeController {
    void doAdjustVolume(boolean z2);

    void doSetVolume(int i2);

    Integer getVolume();

    MutableLiveData<Integer> getVolumeLiveData();

    int progressToVolume(int i2);

    void release();

    int volumeToProgress(int i2);
}
